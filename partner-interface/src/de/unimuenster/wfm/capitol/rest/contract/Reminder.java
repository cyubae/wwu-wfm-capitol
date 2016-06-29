package de.unimuenster.wfm.capitol.rest.contract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.*;

import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;

import de.unimuenster.wfm.capitol.dto.contract.ReminderDTO;

@Path( "reminder" )


public class Reminder {
	public static final String MESSAGENAME = "reminder";

	@Inject
	private RuntimeService runtimeService;
	@POST
	@Consumes("application/json")
	public String receiveCase(ReminderDTO reminder){
		// TESTCODE
		System.out.println(reminder.getProcessinstance_id_bvis());
		System.out.println(reminder.getProcessinstance_id_capitol());
		System.out.println(reminder.getOrder().getOrder_id());
		System.out.println(reminder.getOrder().getRequest_date());
		// END TESTCODE
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("processinstance_id_bvis", reminder.getProcessinstance_id_bvis());
		variables.put("order_id", reminder.getOrder().getOrder_id());
		variables.put("request_date", reminder.getOrder().getRequest_date());

		try {
			// correlate the message
			  runtimeService.createMessageCorrelation("Message-Approve")
		      .processInstanceId(reminder.getProcessinstance_id_capitol())
		      .setVariables(variables)
		      .correlate();
		} catch (MismatchingMessageCorrelationException e) {
			return "Failure";
		}
		
		return "Success";
	}
}
