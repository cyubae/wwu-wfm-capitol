package de.unimuenster.wfm.capitol.rest;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.*;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;

import de.unimuenster.wfm.capitol.dto.OrderFeedback;

@Path( "orderfeedback" )
public class OrderConfirmation {
	public static final String MESSAGENAME = "order_feedback";
	@Inject
	private RuntimeService runtimeService;
	@POST
	@Consumes("application/json")
	public String receiveCase(OrderFeedback orderFeedback){
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("order_feedback", orderFeedback);
		try{
			runtimeService.correlateMessage(MESSAGENAME, orderFeedback.getOrder_id(), variables);
		} catch(MismatchingMessageCorrelationException e){
			return "Failure";
		}
		return "Success";
	}
}