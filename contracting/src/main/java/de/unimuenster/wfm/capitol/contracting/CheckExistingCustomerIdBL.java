package de.unimuenster.wfm.capitol.contracting;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.jpa.Customer;
import de.unimuenster.wfm.capitol.service.MessageService;


@Stateless
@Named
public class CheckExistingCustomerIdBL {

	// Inject the entity manager
	@PersistenceContext
	private EntityManager entityManager;

	@EJB(lookup="java:global/MessagingService/MessageServiceImpl!de.unimuenster.wfm.capitol.service.MessageService")
	private MessageService messageService;

	public void performBusinessLogic(DelegateExecution delegateExecution) {
		//messageService.sendContractProposal(null);

		//get all process variables
		Map<String, Object> variables = delegateExecution.getVariables();
		int customerId = findCustomerId(variables);
	}
	
    	

	/**
	 * Returns customerId if available for variables given in Map processVariables
	 * Returns -1 if no appropriate entry is found for given customer data
	 */
	private int findCustomerId(Map<String, Object> processVariables) {

		String query = "SELECT customerId FROM Customer WHERE"
				+ "firstName = " + processVariables.get("firstname")
				+ " AND surname = " + processVariables.get("surname") 
				+ " AND email = " + processVariables.get("email")
				+ " AND phoneNumber = " + processVariables.get("phone_number")
				+ " AND street = " + processVariables.get("street")
				+ " AND houseNumber = " + processVariables.get("house_number")
				+ " AND postcode = " + processVariables.get("postcode")
				+ " AND city = " + processVariables.get("city")
				+ " AND country = " + processVariables.get("country")
				+ " AND date_of_birth = " + processVariables.get("date_of_birth");

		//if customer is company, append to companyQuery
		if ( processVariables.get("company").equals("true")) {
			query += " AND company = true"
					+ "AND companyName = " + processVariables.get("company_name");
		}
		
		List<Customer> arr_cust = (List<Customer>)entityManager.createQuery(query).getResultList();
		
		if (arr_cust.size() != 1) {
			return -1;
		}
		else {
			return arr_cust.get(0);
		}
		
		

	}


}