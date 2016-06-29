package de.unimuenster.wfm.capitol.contracting.businesslogic;


import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.service.MessageService;
import de.unimuenster.wfm.capitol.jpa.*;


@Stateless
@Named
public class CheckExistingCustomerIdBL {
	
	private static Logger LOGGER = Logger.getLogger(CheckExistingCustomerIdBL.class.getName());

	@EJB(lookup="java:global/MessagingService/MessageServiceImpl!de.unimuenster.wfm.capitol.service.MessageService")
	private MessageService messageService;

	@EJB
	private AccessCustomer customerAccess = new AccessCustomer();

//	public void performBusinessLogic(DelegateExecution delegateExecution) {
//		//messageService.sendContractProposal(null);
//		
//		//no need: results only needed at connector
//		this.findExistingCustomer(delegateExecution);
//	}
	
	/**
	 * Verifies if customer is present in database. If so, customer id is passed into delegateExecution and method returns true.
	 * Else -1 is passed into delegateExecution and method returns false
	 * @param delegateExecution
	 * @return
	 */
	public void findExistingCustomer(DelegateExecution delegateExecution) {
		
		Map<String, Object> dataMap = delegateExecution.getVariables();
		//iterating over values only
		for (Object value : dataMap.values()) {
		    LOGGER.log(Level.INFO, "dataMap - Value = " + value);
		}
		
		int customerId = customerAccess.findCustomerId(dataMap);
		
		if (customerId != -1) {
			delegateExecution.setVariable("customerIdFound", true);
			delegateExecution.setVariable("customerId", customerId);
		}
		else {
			delegateExecution.setVariable("customerIdFound", false);
		}
		
	}
	
//	public Map<String, Object> getProposedCustomer() {
//		return delegateExecution.getVariables();
//	}
	
//	public Customer getCustomer(int customerId) {
//		
//	}
//	



}