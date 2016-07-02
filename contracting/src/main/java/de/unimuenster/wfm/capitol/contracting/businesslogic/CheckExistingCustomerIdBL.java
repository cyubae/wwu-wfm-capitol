package de.unimuenster.wfm.capitol.contracting.businesslogic;


import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.jpa.AccessCustomer;
import de.unimuenster.wfm.capitol.service.MessageService;


@Stateless
@Named
public class CheckExistingCustomerIdBL {
	
	private static Logger LOGGER = Logger.getLogger(CheckExistingCustomerIdBL.class.getName());

	@EJB
	private AccessCustomer customerAccess = new AccessCustomer();
	
	/**
	 * Verifies if customer is present in database. 
	 * If so, set process variables:
	 * 	customerIdFound: true
	 * 	customerId: id of existing customer
	 * Else create new customer using process variables and set process variables: 
	 * 	customerIdFound: false
	 * 	customerId: id of new customer
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
			Customer newCustomer = customerAccess.createCustomer(
					String.valueOf(delegateExecution.getVariable("user_firstname")),
					String.valueOf(delegateExecution.getVariable("user_surname")), 
					String.valueOf(delegateExecution.getVariable("user_email")), 
					String.valueOf(delegateExecution.getVariable("user_phone_number")), 
					String.valueOf(delegateExecution.getVariable("user_street")), 
					String.valueOf(delegateExecution.getVariable("user_house_number")), 
					String.valueOf(delegateExecution.getVariable("user_postcode")), 
					String.valueOf(delegateExecution.getVariable("user_city")), 
					String.valueOf(delegateExecution.getVariable("user_country")), 
					String.valueOf(delegateExecution.getVariable("user_date_of_birth")),
					String.valueOf(delegateExecution.getVariable("user_iscompany")).equals("true") ? true : false,
					String.valueOf(delegateExecution.getVariable("user_company_name")), 
					false);
			delegateExecution.setVariable("customerId", newCustomer.getCustomerId());
		}	
	}
}