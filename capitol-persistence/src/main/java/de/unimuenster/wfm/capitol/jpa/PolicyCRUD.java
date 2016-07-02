package de.unimuenster.wfm.capitol.jpa;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.entities.Policy;

/**
 * DAO for entity Policy
 *
 */
@Stateless
@Named
public class PolicyCRUD extends AbstractCRUDService<Policy> {
	
	private static Logger LOGGER = Logger.getLogger(AccessCustomer.class.getName());
	
    public PolicyCRUD() {
        super(Policy.class);
    }
        
    /**
     * Finds list of policies associated with given contract id
     */
	public List<Policy> findPoliciesForContractId(int contractId) {

		LOGGER.log(Level.INFO, "Prepare query string");				
		String query = "Select p FROM Policy p WHERE"
				+ " p.contract.contractId = " + contractId;

		LOGGER.log(Level.INFO, "Query string: " + query);		
		LOGGER.log(Level.INFO, "ACCESSSTEP_1");				
		TypedQuery<Policy> typedQuery = entityManager.createQuery(query, Policy.class);
		List<Policy> reusltList = typedQuery.getResultList();
		
		return reusltList;

	}

}
