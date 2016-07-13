package de.unimuenster.wfm.capitol.jpa;

import java.util.Date;
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
	 * Creates and persists a new Customer object 
	 * @return customerId of new Customer
	 */
	public Customer createCustomer() {
		Customer newCustomer = new Customer();
		entityManager.persist(newCustomer);
		entityManager.flush();
		LOGGER.log(Level.INFO, "New empty Customer object created - customerId: " + newCustomer.getCustomerId());	

		return newCustomer;
	}

	public Customer createCustomer(String firstName, String surname, String email, String phoneNumber, String street,
			String houseNumber, String postcode, String city, String country, Date dateOfBirth, boolean company,
			String companyName, boolean blacklisted) {
		Customer newCustomer = new Customer(firstName, surname, email, phoneNumber, street, houseNumber, postcode, 
				city, country, dateOfBirth, company, companyName, blacklisted);
		entityManager.persist(newCustomer);
		entityManager.flush();
		LOGGER.log(Level.INFO, "New Customer object created - customerId: " + newCustomer.getCustomerId());
		LOGGER.log(Level.INFO, "New Customer properties : " + newCustomer.toString());

		return newCustomer;
	}	

	/**
	 * Updates customer object pre-existing in database 
	 * @param customer
	 */
	public void updateCustomer(Customer customer) {
		LOGGER.log(Level.INFO, "Update Customer properties : " + customer.toString());
		entityManager.merge(customer);
		entityManager.flush();
	}

	/**
	 * Retrieves customer from database for given id
	 * @param customerId
	 * @return
	 */
	public Customer getCustomer(int customerId) {
		// Load order entity from database
		return entityManager.find(Customer.class, customerId);
	}
}
