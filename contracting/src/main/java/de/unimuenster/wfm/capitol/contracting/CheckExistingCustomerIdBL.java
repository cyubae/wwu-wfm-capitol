package de.unimuenster.wfm.capitol.contracting;


import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.service.MessageService;
import de.unimuenster.wfm.capitol.jpa.*;


@Stateless
@Named
public class CheckExistingCustomerIdBL {

	@EJB(lookup="java:global/MessagingService/MessageServiceImpl!de.unimuenster.wfm.capitol.service.MessageService")
	private MessageService messageService;

	@EJB
	private OrderBusinessLogic obl = new OrderBusinessLogic();

	public void performBusinessLogic(DelegateExecution delegateExecution) {
		//messageService.sendContractProposal(null);
		this.customerExists(delegateExecution);
	}
	
	/**
	 * Verifies if customer is present in database. If so, customer id is passed into delegateExecution and method returns true.
	 * Else -1 is passed into delegateExecution and method returns false
	 * @param delegateExecution
	 * @return
	 */
	public boolean customerExists(DelegateExecution delegateExecution) {
		
		obl.persistOrder();
		return true;
		
//		Map<String, Object> dataMap = delegateExecution.getVariables();
//		int customerId = new AccessCustomer().findCustomerId(dataMap);
//		
//		delegateExecution.setVariable("customerId", customerId);
//		
//		if (customerId == -1) {
//			return false;
//		}
//		else {
//			return true;
//		}
	}
	



}