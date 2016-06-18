package de.unimuenster.wfm.capitol.rest.claim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.*;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;

import de.unimuenster.wfm.capitol.dto.claim.ClaimDetailsDTO;
import de.unimuenster.wfm.capitol.dto.claim.ClaimDetailsDTO.Involved_party;
@Path( "claimdetails" )

public class ClaimDetails {
	public static final String MESSAGENAME = "claim_details";

	@Inject
	private RuntimeService runtimeService;
	@POST
	@Consumes("application/json")
	public String receiveCase(ClaimDetailsDTO claimdetails){
		// TESTCODE
		System.out.println(claimdetails.getProcess_id());
		System.out.println(claimdetails.getRequest_date());
		System.out.println(claimdetails.getClaim().getClaim_id());
		System.out.println(claimdetails.getClaim().getInsurance_id());
		System.out.println(claimdetails.getClaim().getVehicle_identification_number());
		System.out.println(claimdetails.getClaim().getOrder_id());
		System.out.println(claimdetails.getClaim().getDamage_date());
		System.out.println(claimdetails.getClaim().getDamage_address());
		System.out.println(claimdetails.getClaim().getClaim_description());
		System.out.println(claimdetails.getClaim().getWorkshop_price());
		System.out.println(claimdetails.getClaim().isParties_involved());
		for(Involved_party single_party : claimdetails.getInvolved_parties()) {
			System.out.println(single_party.getFirstname());
			System.out.println(single_party.getSurname());
			System.out.println(single_party.getEmail());
			System.out.println(single_party.getPhone_number());
			System.out.println(single_party.getStreet());
			System.out.println(single_party.getHouse_number());
			System.out.println(single_party.getPostcode());
			System.out.println(single_party.getCity());
			System.out.println(single_party.getCountry());
			System.out.println(single_party.getDate_of_birth());
			System.out.println(single_party.getCompany());
			System.out.println(single_party.isHas_insurance());
			System.out.println(single_party.getInsurance().getCompany());
			System.out.println(single_party.getInsurance().getStreet());
			System.out.println(single_party.getInsurance().getHouse_number());
			System.out.println(single_party.getInsurance().getPostcode());
			System.out.println(single_party.getInsurance().getCity());
			System.out.println(single_party.getInsurance().getCountry());
		}
		// END TESTCODE
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("request_date", claimdetails.getRequest_date());
		variables.put("claim_id", claimdetails.getClaim().getClaim_id());
		variables.put("insurance_id", claimdetails.getClaim().getInsurance_id());
		variables.put("vehicle_identification_number", claimdetails.getClaim().getVehicle_identification_number());
		variables.put("order_id", claimdetails.getClaim().getOrder_id());
		variables.put("damage_date", claimdetails.getClaim().getDamage_date());
		variables.put("damage_address", claimdetails.getClaim().getDamage_address());
		variables.put("claim_description", claimdetails.getClaim().getClaim_description());
		variables.put("workshop_price", claimdetails.getClaim().getWorkshop_price());
		variables.put("parties_involved", claimdetails.getClaim().isParties_involved());
		for(Involved_party single_party : claimdetails.getInvolved_parties()) {
			variables.put("involved_party_firstname", single_party.getFirstname());
			variables.put("involved_party_surname", single_party.getSurname());
			variables.put("involved_party_email", single_party.getEmail());
			variables.put("involved_party_phone_number", single_party.getPhone_number());
			variables.put("involved_party_street", single_party.getStreet());
			variables.put("involved_party_house_number", single_party.getHouse_number());
			variables.put("involved_party_postcode", single_party.getPostcode());
			variables.put("involved_party_city", single_party.getCity());
			variables.put("involved_party_country", single_party.getCountry());
			variables.put("involved_party_date_of_birth", single_party.getDate_of_birth());
			variables.put("involved_party_company", single_party.getCompany());
			variables.put("involved_party_has_insurance", single_party.isHas_insurance());
			variables.put("involved_party_insurance_company", single_party.getInsurance().getCompany());
			variables.put("involved_party_insurance_street", single_party.getInsurance().getStreet());
			variables.put("involved_party_insurance_house_number", single_party.getInsurance().getHouse_number());
			variables.put("involved_party_insurance_postcode", single_party.getInsurance().getPostcode());
			variables.put("involved_party_insurance_city", single_party.getInsurance().getCity());
			variables.put("involved_party_insurance_country", single_party.getInsurance().getCountry());
		}			
				
		try {
			// correlate the message
			  runtimeService.createMessageCorrelation("Message-Approve")
		      .processInstanceId(claimdetails.getProcess_id())
		      .setVariables(variables)
		      .correlate();
		} catch (MismatchingMessageCorrelationException e) {
			return "Failure";
		}
		
		return "Success";
	}
	
}
