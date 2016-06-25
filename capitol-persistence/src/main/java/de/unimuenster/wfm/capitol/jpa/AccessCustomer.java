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
		
	private static Logger LOGGER = Logger.getLogger(AccessCustomer.class.getName());

	// Inject the entity manager
	@PersistenceContext
	private EntityManager entityManager;
	
	
	/**
	 * Returns customerId if available for variables given in Map processVariables
	 * Returns -1 if no appropriate entry is found for given customer data
	 */
	public int findCustomerId(Map<String, Object> dataMap) {
						
		LOGGER.log(Level.INFO, "entityManager.toString(): " + entityManager.toString());
		LOGGER.log(Level.INFO, "Prepare query string");				
		String query = "Select c FROM Customer c WHERE"
				+ " c.firstName = '" + dataMap.get("user_firstname") + "'"
				+ " AND c.surname = '" + dataMap.get("user_surname")  + "'"
				+ " AND c.email = '" + dataMap.get("user_email") + "'"
				+ " AND c.phoneNumber = '" + dataMap.get("user_phone_number") + "'"
				+ " AND c.street = '" + dataMap.get("user_street") + "'"
				+ " AND c.houseNumber = '" + dataMap.get("user_house_number") + "'"
				+ " AND c.postcode = '" + dataMap.get("user_postcode") + "'"
				+ " AND c.city = '" + dataMap.get("user_city") + "'"
				+ " AND c.country = '" + dataMap.get("user_country") + "'"
				+ " AND c.dateOfBirth = '" + dataMap.get("user_date_of_birth") + "'";

		//if customer is company, append to companyQuery
		if ( dataMap.get("user_iscompany") != null && dataMap.get("user_iscompany").equals("true")) {
			query += " AND c.company = true"
				   + " AND c.companyName = '" + dataMap.get("user_company_name") + "'";
		}
		else {
			query += " AND c.company = false";
		}
		
		LOGGER.log(Level.INFO, "Query string: " + query);		
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

	}
	
	
	/**
	 * Creates, persists and returns a new Customer object 
	 * @return
	 */
	public Customer createCustomer() {
		Customer newCustomer = new Customer();
		entityManager.persist(newCustomer);
		return newCustomer;		
	}
	
	/**
	 * Updates given customer object (precondition: customer object is not detached)
	 * @param customer
	 */
	public void updateCustomer(Customer customer) {
		entityManager.merge(customer);
		entityManager.persist(customer);
	}
}
