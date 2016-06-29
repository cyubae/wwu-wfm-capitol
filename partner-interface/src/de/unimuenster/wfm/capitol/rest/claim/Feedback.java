package de.unimuenster.wfm.capitol.rest.claim;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.*;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;

import de.unimuenster.wfm.capitol.dto.claim.FeedbackDTO;

@Path( "feedback" )
public class Feedback {
	public static final String MESSAGENAME = "claim_feedback";

	@Inject
	private RuntimeService runtimeService;
	@POST
	@Consumes("application/json")
	public String receiveCase(FeedbackDTO feedback){
		// TESTCODE
		System.out.println(feedback.getProcessinstance_id_bvis());
		System.out.println(feedback.getProcessinstance_id_capitol());
		System.out.println(feedback.getDecision().getClaim_id());
		System.out.println(feedback.getDecision().getClaim_status());
		System.out.println(feedback.getDecision().getDescription());
		// END TESTCODE
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("processinstance_id_bvis", feedback.getProcessinstance_id_bvis());
		variables.put("claim_id", feedback.getDecision().getClaim_id());
		variables.put("claim_status", feedback.getDecision().getClaim_status());
		variables.put("claim_description", feedback.getDecision().getDescription());		
		try {
			// correlate the message
			  runtimeService.createMessageCorrelation("Message-Approve")
		      .processInstanceId(feedback.getProcessinstance_id_capitol())
		      .setVariables(variables)
		      .correlate();
		} catch (MismatchingMessageCorrelationException e) {
			return "Failure";
		}
		
		return "Success";
	}
}