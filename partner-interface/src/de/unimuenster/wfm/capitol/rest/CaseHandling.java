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
	public static final String MESSAGENAME = "claim";

	@Inject
	private RuntimeService runtimeService;
	@POST
	@Consumes("application/json")
	public void receiveCase(LiabilityCase liabilityCase){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("case", liabilityCase);
		runtimeService.startProcessInstanceByMessage(MESSAGENAME, variables);

	}
}