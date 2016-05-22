package de.unimuenster.wfm.capitol.rest;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.*;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;

import de.unimuenster.wfm.capitol.dto.ClaimFeedback;

@Path( "claimfeedback" )
public class ClaimConfirmation {
	public static final String MESSAGENAME = "claim_feedback";
	@Inject
	private RuntimeService runtimeService;
	@POST
	@Consumes("application/json")
	public String receiveCase(ClaimFeedback claimFeedback){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("claim_feedback", claimFeedback);
		try {
			runtimeService.correlateMessage(MESSAGENAME, claimFeedback.getClaim_id(), variables);
		} catch (MismatchingMessageCorrelationException e) {
			return "Failure";
		}
		return "Success";

	}
}