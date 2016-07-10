package de.unimuenster.wfm.capitol.settlement.businesslogic;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.entities.Car;
import de.unimuenster.wfm.capitol.entities.Claim;
import de.unimuenster.wfm.capitol.entities.ExternalParty;
import de.unimuenster.wfm.capitol.entities.Insurance;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ClaimCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.ExternalPartyCRUD;
import de.unimuenster.wfm.capitol.jpa.InsuranceCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;

@Stateless
@Named
public class CreateClaimBL {

	private static Logger LOGGER = Logger.getLogger(CreateClaimBL.class.getName());

	// Inject CRUD-Services to access persistence unit	
	@Inject
	private ContractCRUD contractCRUD;

	@Inject
	private PolicyCRUD policyCRUD;

	@Inject
	private CarCRUD carCRUD;

	@Inject
	private CustomerCRUD customerCRUD;	

	@Inject
	private ClaimCRUD claimCRUD;
	
	@Inject
	private ExternalPartyCRUD externalPartyCRUD;
	
	@Inject
	private InsuranceCRUD insuranceCRUD;

	public void performBusinessLogic(DelegateExecution delegateExecution) {

		Claim newClaim = new Claim();
		newClaim.setClaimDescription((String) delegateExecution.getVariable("claim_description"));
		newClaim.setDamageAddress((String) delegateExecution.getVariable("damage_address"));
		newClaim.setExternalClaimId( (Integer) delegateExecution.getVariable("claim_id") );
		
		Double workshopPrice = (Double) delegateExecution.getVariable("workshop_price");
		int claimValue = (int) (workshopPrice.doubleValue()*100);
		newClaim.setClaimValue(claimValue);
						
		newClaim.setPartiesInvolved(
				((String) delegateExecution.getVariable("claim_description")).equals("true") ? true : false);
		
		newClaim.setClaimDescription((String) delegateExecution.getVariable("claim_description"));
		newClaim.setVehicleIDNumber((String) delegateExecution.getVariable("vehicle_identification_number"));
		
		//parse dates			
		try {
			Date damageDate = (Date) delegateExecution.getVariable("damage_date");
			newClaim.setDamageDate(damageDate);
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Could not parse damageDate!");
//			e.printStackTrace();
		}

		//try to find policy associated to the claim
		Car car = carCRUD.findCarByVehicleId(newClaim.getVehicleIDNumber());
		if(car!=null) {
			newClaim.setPolicy(car.getPolicy());
			delegateExecution.setVariable("contract_found", true);
		}
		else{
			delegateExecution.setVariable("contract_found", false);
		}
		
		//associate involved parties
		int numberOfExternalParties = (Integer) delegateExecution.getVariable("number_of_involved_parties");
		if (numberOfExternalParties > 0) {
			for (int partyCount = 1; partyCount<=numberOfExternalParties; partyCount++) {
				newClaim.addExternalParty(createExternalParty(delegateExecution, partyCount));
			}
		}
		
		//persist new claim
		newClaim = claimCRUD.createAndFlush(newClaim);
		delegateExecution.setVariable("claim_id_internal", Integer.valueOf(newClaim.getClaimId()));

	}
	
	
	public ExternalParty createExternalParty(DelegateExecution delegateExecution, int partyCount) {
		ExternalParty externalParty = new ExternalParty();
		externalParty.setFirstName((String) delegateExecution.getVariable("involved_party_firstname" + partyCount));
		externalParty.setSurname((String) delegateExecution.getVariable("involved_party_surname" + partyCount));
		externalParty.setEmail((String) delegateExecution.getVariable("involved_party_email" + partyCount));
		externalParty.setPhoneNumber((String) delegateExecution.getVariable("involved_party_phone_number" + partyCount));
		externalParty.setStreet((String) delegateExecution.getVariable("involved_party_street" + partyCount));		
		externalParty.setHouseNumber((String) delegateExecution.getVariable("involved_party_house_number" + partyCount));
		externalParty.setPostcode((String) delegateExecution.getVariable("involved_party_postcode" + partyCount));
		externalParty.setCity((String) delegateExecution.getVariable("involved_party_city" + partyCount));
		externalParty.setCountry((String) delegateExecution.getVariable("involved_party_country" + partyCount));
		externalParty.setDateOfBirth((String) delegateExecution.getVariable("involved_party_date_of_birth" + partyCount));
		externalParty.setCompany((String) delegateExecution.getVariable("involved_party_company" + partyCount));
				
		if(delegateExecution.getVariable("involved_party_has_insurance" + partyCount) != null) {
			externalParty.setHasInsurance((((Boolean)delegateExecution.getVariable("involved_party_has_insurance" + partyCount))));			
		}
		else {
			externalParty.setHasInsurance(false);
		}
		
		if(externalParty.isHasInsurance()) {
			externalParty.setInsurance(createInsurance(delegateExecution, partyCount));
		}
		
		externalParty = externalPartyCRUD.createAndFlush(externalParty);
		
		return externalParty;
	}
	
	public Insurance createInsurance(DelegateExecution delegateExecution, int partyCount) {
		
		Insurance insurance = new Insurance();
		insurance.setCompany((String) delegateExecution.getVariable("involved_party_insurance_company" + partyCount));
		insurance.setStreet((String) delegateExecution.getVariable("involved_party_insurance_street" + partyCount));
		insurance.setHouseNumber((String) delegateExecution.getVariable("involved_party_insurance_house_number" + partyCount));
		insurance.setPostcode((String) delegateExecution.getVariable("involved_party_insurance_postcode" + partyCount));
		insurance.setCity((String) delegateExecution.getVariable("involved_party_insurance_city" + partyCount));
		insurance.setCountry((String) delegateExecution.getVariable("involved_party_insurance_country" + partyCount));
		
		insurance = insuranceCRUD.createAndFlush(insurance);
		
		return insurance;
	}


}