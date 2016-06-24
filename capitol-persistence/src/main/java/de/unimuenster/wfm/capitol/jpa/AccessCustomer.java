package de.unimuenster.wfm.capitol.jpa;

import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

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
	
	/**
	 * Returns customerId if available for variables given in Map processVariables
	 * Returns -1 if no appropriate entry is found for given customer data
	 */
	public int findCustomerId(Map<String, Object> dataMap) {
				
		//create dummy customer
		Customer dummy = new Customer();
		
		//EntityManager localEm = this.entityManager;		
		System.out.println(entityManager.toString());
		//persist(dummy);
		
		return 1;
		
//		String query = "Select c FROM Customer c";
//
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
//		
//		System.out.println("ACCESSSTEP_1");
//		
//		Query jpaQuery = entityManager.createQuery(query);
//		
//		TypedQuery<Customer> customerQuery = entityManager.createQuery("Select c from Customer c", Customer.class);
//		List<Customer> arr_cust = customerQuery.getResultList();
//		
//		System.out.println("ACCESSSTEP_2");
//		
//		if (arr_cust.size() <= 0) {
//			System.out.println("No output found");
//		}
//		else {
//			System.out.println("output found!");
////			System.out.println("DB_OUTPUT: " + arr_cust.get(0));			
//		}
//		
//		
//		System.out.println("ACCESSSTEP_3");
//		
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
