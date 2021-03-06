package de.unimuenster.wfm.capitol.contracting.businesslogic;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.dto.ContractProposal;
import de.unimuenster.wfm.capitol.dto.ContractProposal.Order;
import de.unimuenster.wfm.capitol.helper.DateTools;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;
import de.unimuenster.wfm.capitol.service.MessageService;


@Stateless
@Named
public class SendRejectionBL {
	
	private static Logger LOGGER = Logger.getLogger(SendRejectionBL.class.getName());
	
	//TODO: set URL to send JSON to
	//"http://camunda-bvis.uni-muenster.de/???"
	private static String DESTINATION_URL =  "http://camunda-bvis.uni-muenster.de/bvis/api/insurancedetails";

	// Inject CRUD-Services to access persistence unit	
	@Inject
	private ContractCRUD contractCRUD;

	@Inject
	private PolicyCRUD policyCRUD;

	@Inject
	private CarCRUD carCRUD;

	@Inject
	private CustomerCRUD customerCRUD;	
	
	@EJB(lookup="java:global/MessagingService/MessageServiceImpl!de.unimuenster.wfm.capitol.service.MessageService")
	private MessageService messageService;

	public void performBusinessLogic(DelegateExecution delegateExecution) {
		LOGGER.log(Level.INFO, "Rejection sending invoked!");
		ContractProposal contractProposal = new ContractProposal();
		Order order = contractProposal.new Order();
		contractProposal.setOrder(order);

		contractProposal.setProcessinstance_id_bvis(String.valueOf(delegateExecution.getVariable("processinstance_id_bvis")));
		contractProposal.setProcessinstance_id_capitol(delegateExecution.getProcessInstanceId());

		order.setFinal_price(0);
		order.setInquiry_text("");
		order.setOrder_id((Integer) delegateExecution.getVariable("order_id"));
		order.setRequest_date((Date) delegateExecution.getVariable("request_date"));
		order.setResult((Integer) delegateExecution.getVariable("contract_result"));		
		
		if(DESTINATION_URL != null) {
			messageService.sendJSON(contractProposal, DESTINATION_URL);			
			LOGGER.log(Level.INFO, "Contract rejction sent successfully! Contract Result: " + order.getResult());
		}
		
	}


}