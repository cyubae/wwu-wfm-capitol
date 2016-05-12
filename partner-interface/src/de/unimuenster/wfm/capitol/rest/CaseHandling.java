package de.unimuenster.wfm.capitol.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.*;

import org.camunda.bpm.engine.RuntimeService;

import de.unimuenster.wfm.capitol.dto.LiabilityCase;

@Path( "casehandling" )
public class CaseHandling {
	public static final String CALLBACK_URL = "callbackURL";
	public static final String CALLBACK_CORRELATION_ID = "callbackCorrelationId";
	public static final String PAYLOAD = "payload";
	//TODO Make sure the casename is correct
	public static final String MESSAGENAME = "claim";

	@Inject
	private RuntimeService runtimeService;
	@POST
	@Consumes("application/json")
	public void receiveCase(LiabilityCase liabilityCase){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("amount", new Double(liabilityCase.getSum()));
		variables.put("invoiceNumber", "Fake Invoice");
		variables.put("creditor", liabilityCase.getName());
		variables.put("invoiceCategory", liabilityCase.getDescription());
		variables.put("approverGroups", ArrayList.class);

		runtimeService.startProcessInstanceByMessage(MESSAGENAME, variables);

	}
}