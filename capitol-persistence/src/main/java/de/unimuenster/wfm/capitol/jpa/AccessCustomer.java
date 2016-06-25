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
	 * Creates and persists a new Customer object 
	 * @return customerId of new Customer
	 */
	public int createCustomer() {
		Customer newCustomer = new Customer();
		entityManager.persist(newCustomer);
		entityManager.flush();

		return newCustomer.getCustomerId();
	}

	public Customer getCustomerById(int customerId) throws IllegalArgumentException{
		return entityManager.find(Customer.class, customerId);
	}

	/**
	 * Generic setter for Customer object
	 * @param customerId the customerId (primary key) of existing Customer object to set attribute on
	 * @param attribute the attribute to set (must be a settable attribute of Customer)
	 * @param value the value the settable attribute is to take
	 */
	public void updateCustomer(int customerId, String attribute, String value) {

		if(customerId < 0) {
			throw new IllegalArgumentException("customerId must be >= 0");
		}
		if (attribute == null) {
			throw new IllegalArgumentException("attribute must be non-null");
		}

		try {
			Customer customer = this.getCustomerById(customerId);
			switch(attribute) {
			case "firstName":
				customer.setFirstName(attribute);
				break;
			case "surname":
				customer.setSurname(attribute);
				break;
			case "email":
				customer.setEmail(attribute);
				break;
			case "phoneNumber":
				customer.setPhoneNumber(attribute);
				break;
			case "street":
				customer.setStreet(attribute);
				break;
			case "postcode":
				customer.setPostcode(attribute);
				break;
			case "city":
				customer.setCity(attribute);
				break;
			case "country":
				customer.setCountry(attribute);
				break;
			case "dateOfBirth":
				customer.setDateOfBirth(attribute);
				break;
			case "company":
				if(attribute.equals("true")) {
					customer.setCompany(true);
				}
				else {
					customer.setCompany(false);
				}
				break;
			case "companyName":
				if(customer.isCompany()) {
					customer.setCompanyName(attribute);
				}
				else {
					customer.setCompanyName(null);
				}
				break;
			default:
				throw new IllegalArgumentException("given attribute " + attribute + " is not a settable attribute of customer");
			}
			entityManager.merge(customer);
		}
		catch(NullPointerException e) {
			LOGGER.log(Level.SEVERE, "no customer found");
		}		
	}

	/**
	 * Updates given customer object in database
	 * @param customer customer object to update which already exists in database
	 */
	public void mergeCustomer(Customer customer) {
		entityManager.merge(customer);
		entityManager.flush();
	}
}
