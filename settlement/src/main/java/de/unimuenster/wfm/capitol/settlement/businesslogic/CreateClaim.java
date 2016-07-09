package de.unimuenster.wfm.capitol.settlement.businesslogic;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.entities.Car;
import de.unimuenster.wfm.capitol.entities.Claim;
import de.unimuenster.wfm.capitol.helper.DateTools;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ClaimCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;

@Stateless
@Named
public class CreateClaim {

	private static Logger LOGGER = Logger.getLogger(CreateClaim.class.getName());

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

	public void performBusinessLogic(DelegateExecution delegateExecution) {

		Claim newClaim = new Claim();
		newClaim.setClaimDescription((String) delegateExecution.getVariable("claim_description"));
		newClaim.setDamageAddress((String) delegateExecution.getVariable("damage_address"));
		newClaim.setExternalClaimId( (Integer) delegateExecution.getVariable("claim_id") );
		newClaim.setClaimValue( ( (Integer) delegateExecution.getVariable("workshop_price") )*100); 
				
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
		}
		
		//persist new claim
		newClaim = claimCRUD.createAndFlush(newClaim);
		delegateExecution.setVariable("claim_id_internal", Integer.valueOf(newClaim.getClaimId()));

	}


}