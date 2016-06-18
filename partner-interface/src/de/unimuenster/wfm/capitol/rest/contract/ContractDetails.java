package de.unimuenster.wfm.capitol.rest.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.*;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;

import de.unimuenster.wfm.capitol.dto.contract.ContractDetailsDTO;
import de.unimuenster.wfm.capitol.dto.contract.ContractDetailsDTO.Order.Car;

@Path( "contractdetails" )

public class ContractDetails {
	public static final String MESSAGENAME = "contract_details";

	@Inject
	private RuntimeService runtimeService;
	@POST
	@Consumes("application/json")
	public String receiveCase(ContractDetailsDTO contractdetails){
		// TESTCODE
		System.out.println(contractdetails.getProcess_id());
		System.out.println(contractdetails.getOrder().getOrder_id());
		System.out.println(contractdetails.getOrder().getRequest_date());
		System.out.println(contractdetails.getOrder().isFleet_rental());
		System.out.println(contractdetails.getOrder().getInquiry_text());
		System.out.println(contractdetails.getOrder().getUser().getFirstname());
		System.out.println(contractdetails.getOrder().getUser().getSurname());
		System.out.println(contractdetails.getOrder().getUser().getEmail());
		System.out.println(contractdetails.getOrder().getUser().getPhone_number());
		System.out.println(contractdetails.getOrder().getUser().getStreet());
		System.out.println(contractdetails.getOrder().getUser().getHouse_number());
		System.out.println(contractdetails.getOrder().getUser().getPostcode());
		System.out.println(contractdetails.getOrder().getUser().getCity());
		System.out.println(contractdetails.getOrder().getUser().getCountry());
		System.out.println(contractdetails.getOrder().getUser().getDate_of_birth());
		System.out.println(contractdetails.getOrder().getUser().isCompany());
		System.out.println(contractdetails.getOrder().getUser().getCompany_name());
		for(Car single_car : contractdetails.getOrder().getCar()) {
			System.out.println(single_car.getRegistration_number());
			System.out.println(single_car.getBrand());
			System.out.println(single_car.getType());
			System.out.println(single_car.getModel());
			System.out.println(single_car.getVehicle_identification_number());
			System.out.println(single_car.getFuel_type());
			System.out.println(single_car.getPs());
			System.out.println(single_car.getConstruction_year());
		}
		System.out.println(contractdetails.getOrder().getInsurance().getInsurance_id());
		System.out.println(contractdetails.getOrder().getInsurance().getType());
		System.out.println(contractdetails.getOrder().getInsurance().getDeductible());
		System.out.println(contractdetails.getOrder().getInsurance().getPick_up_date());
		System.out.println(contractdetails.getOrder().getInsurance().getReturn_date());
		System.out.println(contractdetails.getOrder().getInsurance().getEstimated_of_cost());
		// END TESTCODE
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("order_id", contractdetails.getOrder().getOrder_id());
		variables.put("request_date", contractdetails.getOrder().getRequest_date());
		variables.put("fleet_rental", contractdetails.getOrder().isFleet_rental());
		variables.put("inquiry_text", contractdetails.getOrder().getInquiry_text());
		variables.put("user_firstname", contractdetails.getOrder().getUser().getFirstname());
		variables.put("user_surname", contractdetails.getOrder().getUser().getSurname());
		variables.put("user_email", contractdetails.getOrder().getUser().getEmail());
		variables.put("user_phone_number", contractdetails.getOrder().getUser().getPhone_number());
		variables.put("user_street", contractdetails.getOrder().getUser().getStreet());
		variables.put("user_house_number", contractdetails.getOrder().getUser().getHouse_number());
		variables.put("user_postcode", contractdetails.getOrder().getUser().getPostcode());
		variables.put("user_city", contractdetails.getOrder().getUser().getCity());
		variables.put("user_country", contractdetails.getOrder().getUser().getCountry());
		variables.put("user_date_of_birth", contractdetails.getOrder().getUser().getDate_of_birth());
		variables.put("user_iscompany", contractdetails.getOrder().getUser().isCompany());
		variables.put("user_company_name", contractdetails.getOrder().getUser().getCompany_name());
		for(Car single_car : contractdetails.getOrder().getCar()) {
			variables.put("car_registration_number", single_car.getRegistration_number());
			variables.put("car_brand", single_car.getBrand());
			variables.put("car_type", single_car.getType());
			variables.put("car_model", single_car.getModel());
			variables.put("car_vehicle_identification_number", single_car.getVehicle_identification_number());
			variables.put("car_fuel_type", single_car.getFuel_type());
			variables.put("car_ps", single_car.getPs());
			variables.put("car_construction_year", single_car.getConstruction_year());
		}
		variables.put("insurance_insurance_id", contractdetails.getOrder().getInsurance().getInsurance_id());
		variables.put("insurance_type", contractdetails.getOrder().getInsurance().getType());
		variables.put("insurance_deductible", contractdetails.getOrder().getInsurance().getDeductible());
		variables.put("insurance_pick_up_date", contractdetails.getOrder().getInsurance().getPick_up_date());
		variables.put("insurance_return_date", contractdetails.getOrder().getInsurance().getReturn_date());
		variables.put("insurance_estimated_of_cost", contractdetails.getOrder().getInsurance().getEstimated_of_cost());
		
		try {
			// correlate the message
			  runtimeService.createMessageCorrelation("Message-Approve")
		      .processInstanceId(contractdetails.getProcess_id())
		      .setVariables(variables)
		      .correlate();
		} catch (MismatchingMessageCorrelationException e) {
			return "Failure";
		}
		
		return "Success";
	}
}
