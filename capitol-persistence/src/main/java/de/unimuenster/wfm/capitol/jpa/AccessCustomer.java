package de.unimuenster.wfm.capitol.jpa;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.unimuenster.wfm.capitol.entities.Customer;

/**
 * DAO for entity customer
 * @author mkubicki
 *
 */
@Stateless
@Named
public class AccessCustomer {
		
	// Inject the entity manager
	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger LOGGER = Logger.getLogger(AccessCustomer.class.getName());
	
	/**
	 * Returns customerId if available for variables given in Map processVariables
	 * Returns -1 if no appropriate entry is found for given customer data
	 */
	public int findCustomerId(Map<String, Object> dataMap) {
						
		LOGGER.log(Level.INFO, "entityManager.toString(): " + entityManager.toString());
				
		String query = "Select c FROM Customer c";

//		String query = "SELECT customerId FROM Customer WHERE"
//				+ "firstName = " + dataMap.get("firstname")
//				+ " AND surname = " + dataMap.get("surname") 
//				+ " AND email = " + dataMap.get("email")
//				+ " AND phoneNumber = " + dataMap.get("phone_number")
//				+ " AND street = " + dataMap.get("street")
//				+ " AND houseNumber = " + dataMap.get("house_number")
//				+ " AND postcode = " + dataMap.get("postcode")
//				+ " AND city = " + dataMap.get("city")
//				+ " AND country = " + dataMap.get("country")
//				+ " AND date_of_birth = " + dataMap.get("date_of_birth");
//
//		//if customer is company, append to companyQuery
//		if ( dataMap.get("company") != null && dataMap.get("company").equals("true")) {
//			query += " AND company = true"
//				   + " AND companyName = " + dataMap.get("company_name");
//		}
		
		LOGGER.log(Level.INFO, "ACCESSSTEP_1");				
		TypedQuery<Customer> customerQuery = entityManager.createQuery(query, Customer.class);
		List<Customer> arr_cust = customerQuery.getResultList();
		
		LOGGER.log(Level.INFO, "ACCESSSTEP_2");	
		if (arr_cust.size() == 1) {
			int customerId = arr_cust.get(0).getCustomerId();
			LOGGER.log(Level.INFO, "Customer found with ID: " + customerId);
			return customerId;
		}
		else {
			LOGGER.log(Level.INFO, "No valid customer found");
			return -1;
		}
		
		
//		if (arr_cust.size() != 1) {
//			System.out.println("ACCESSSTEP_4a");
//			return -1;
//		}
//		else {
//			System.out.println("ACCESSSTEP_4b");
//			return 1;
////			return arr_cust.get(0).getCustomerId();
//		}

	}
}
