package de.unimuenster.wfm.capitol.rest.claim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.*;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;

import de.unimuenster.wfm.capitol.dto.claim.ClaimDetailsDTO;
import de.unimuenster.wfm.capitol.dto.claim.ClaimDetailsDTO.Order.Car;

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
		System.out.println(claimdetails.getOrder().getOrder_id());
		System.out.println(claimdetails.getOrder().getRequest_date());
		System.out.println(claimdetails.getOrder().isFleet_rental());
		System.out.println(claimdetails.getOrder().getInquiry_text());
		System.out.println(claimdetails.getOrder().getUser().getFirstname());
		System.out.println(claimdetails.getOrder().getUser().getSurname());
		System.out.println(claimdetails.getOrder().getUser().getEmail());
		System.out.println(claimdetails.getOrder().getUser().getPhone_number());
		System.out.println(claimdetails.getOrder().getUser().getStreet());
		System.out.println(claimdetails.getOrder().getUser().getHouse_number());
		System.out.println(claimdetails.getOrder().getUser().getPostcode());
		System.out.println(claimdetails.getOrder().getUser().getCity());
		System.out.println(claimdetails.getOrder().getUser().getCountry());
		System.out.println(claimdetails.getOrder().getUser().getDate_of_birth());
		System.out.println(claimdetails.getOrder().getUser().isCompany());
		System.out.println(claimdetails.getOrder().getUser().getCompany_name());
		for(Car single_car : claimdetails.getOrder().getCars()) {
			System.out.println(single_car.getRegistration_number());
			System.out.println(single_car.getBrand());
			System.out.println(single_car.getType());
			System.out.println(single_car.getModel());
			System.out.println(single_car.getVehicle_identification_number());
			System.out.println(single_car.getFuel_type());
			System.out.println(single_car.getPs());
			System.out.println(single_car.getConstruction_year());
		}
		System.out.println(claimdetails.getOrder().getInsurance().getInsurance_id());
		System.out.println(claimdetails.getOrder().getInsurance().getType());
		System.out.println(claimdetails.getOrder().getInsurance().getDeductible());
		System.out.println(claimdetails.getOrder().getInsurance().getPick_up_date());
		System.out.println(claimdetails.getOrder().getInsurance().getReturn_date());
		System.out.println(claimdetails.getOrder().getInsurance().getEstimated_of_cost());
		// END TESTCODE
		
		return "Success";
	}
}
