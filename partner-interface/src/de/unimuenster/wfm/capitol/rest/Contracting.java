package de.unimuenster.wfm.capitol.rest;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.camunda.bpm.engine.RuntimeService;

import de.unimuenster.wfm.capitol.dto.ContractRequest;
import de.unimuenster.wfm.capitol.dto.LiabilityCase;

@Path( "contracting" )
public class Contracting {
	public static final String MESSAGENAME = "contractRequest";
	@Inject
	private RuntimeService runtimeService;
	@POST
	@Consumes("application/json")
	public void receiveCase(ContractRequest contractRequest){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("contractRequest", contractRequest);
		runtimeService.startProcessInstanceByMessage(MESSAGENAME, variables);

	}
}