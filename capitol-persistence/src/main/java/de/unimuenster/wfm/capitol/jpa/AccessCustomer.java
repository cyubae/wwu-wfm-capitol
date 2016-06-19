package de.unimuenster.wfm.capitol.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO for entity customer
 * @author mkubicki
 *
 */
public class AccessCustomer {
	
	// Inject the entity manager
	@PersistenceContext
	private static EntityManager entityManager;
	
	/**
	 * Returns customerId if available for variables given in Map processVariables
	 * Returns -1 if no appropriate entry is found for given customer data
	 */
	public static int findCustomerId(Map<String, Object> dataMap) {
		

		String query = "SELECT customerId FROM Customer WHERE"
				+ "firstName = " + dataMap.get("firstname")
				+ " AND surname = " + dataMap.get("surname") 
				+ " AND email = " + dataMap.get("email")
				+ " AND phoneNumber = " + dataMap.get("phone_number")
				+ " AND street = " + dataMap.get("street")
				+ " AND houseNumber = " + dataMap.get("house_number")
				+ " AND postcode = " + dataMap.get("postcode")
				+ " AND city = " + dataMap.get("city")
				+ " AND country = " + dataMap.get("country")
				+ " AND date_of_birth = " + dataMap.get("date_of_birth");

		//if customer is company, append to companyQuery
		if ( dataMap.get("company").equals("true")) {
			query += " AND company = true"
				   + " AND companyName = " + dataMap.get("company_name");
		}
		
		List<Integer> arr_cust = (List<Integer>) entityManager.createQuery(query).getResultList();
		
		System.out.println("DB_OUTPUT: " + arr_cust.get(0));
		
		if (arr_cust.size() != 1) {
			return -1;
		}
		else {
			return arr_cust.get(0);
		}

	}
}
