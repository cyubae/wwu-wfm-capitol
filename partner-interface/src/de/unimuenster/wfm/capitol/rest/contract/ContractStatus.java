package de.unimuenster.wfm.capitol.rest.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.*;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;

import de.unimuenster.wfm.capitol.dto.contract.ContractStatusDTO;

@Path( "contractstatus" )

public class ContractStatus {
	public static final String MESSAGENAME = "contract_status";

	@Inject
	private RuntimeService runtimeService;
	@POST
	@Consumes("application/json")
	public String receiveCase(ContractStatusDTO contractstatus){
		// TESTCODE
		System.out.println(contractstatus.getProcess_id());
		System.out.println(contractstatus.getOrder().getOrder_id());
		System.out.println(contractstatus.getOrder().getRequest_date());
		System.out.println(contractstatus.getOrder().getContract_status());
		
		// END TESTCODE
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("order_id", contractstatus.getOrder().getOrder_id());
		variables.put("request_date", contractstatus.getOrder().getRequest_date());
		variables.put("contract_status", contractstatus.getOrder().getContract_status());

		try {
			// correlate the message
			  runtimeService.createMessageCorrelation("Message-Approve")
		      .processInstanceId(contractstatus.getProcess_id())
		      .setVariables(variables)
		      .correlate();
		} catch (MismatchingMessageCorrelationException e) {
			return "Failure";
		}
		
		return "Success";
	}

}
