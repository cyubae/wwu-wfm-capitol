package de.unimuenster.wfm.capitol.contracting.businesslogic;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.jpa.AccessCustomer;

@Stateless
@Named
public class CheckIfCustomerBlacklistedBL {

	// Inject the AccessCustomer to access the persisted customer
	@Inject
	private AccessCustomer accessCustomer;
	  
	  public void performBusinessLogic(DelegateExecution delegateExecution) {
		  //get Customer object
		  Customer customer = accessCustomer.getCustomer((Integer) (delegateExecution.getVariable("customerId")));
		  		  
		  //get blacklist attribute
		  if(customer.isBlacklisted()) {
			  delegateExecution.setVariable("user_is_blacklisted", true);
		  }
		  else {
			  delegateExecution.setVariable("user_is_blacklisted", false);
		  }
	  }


}