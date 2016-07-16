package de.unimuenster.wfm.capitol.jpa;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import de.unimuenster.wfm.capitol.entities.Contract;

/**
 * DAO for entity Contract
 *
 */
@Stateless
@Named
public class ContractCRUD extends AbstractCRUDService<Contract> {
	
	private static Logger LOGGER = Logger.getLogger(ContractCRUD.class.getName());	
	
    public ContractCRUD() {
        super(Contract.class);
    }
    
	/**
	 * Returns contract if available for given insuranceId
	 * Else returns null
	 */
	public List<Contract> findContractsByInsuranceId(int insuranceId) {

		LOGGER.log(Level.INFO, "entityManager.toString(): " + entityManager.toString());
		LOGGER.log(Level.INFO, "Prepare query string");				
		String query = "Select c FROM Contract c WHERE"
				+ " c.insuranceId = '" + insuranceId + "'";

		LOGGER.log(Level.INFO, "Query string: " + query);
		TypedQuery<Contract> typedQuery = entityManager.createQuery(query, Contract.class);
		try {			
			return typedQuery.getResultList();
		}
		catch(Exception e) {
			LOGGER.log(Level.INFO, "Found no contract");
			return null;
		}
	}  
    
    
	/**
	 * Returns contract if available for given insuranceId
	 * Else returns null
	 */
	public Contract findContractByInsuranceId(int insuranceId) {

		LOGGER.log(Level.INFO, "entityManager.toString(): " + entityManager.toString());
		LOGGER.log(Level.INFO, "Prepare query string");				
		String query = "Select c FROM Contract c WHERE"
				+ " c.insuranceId = '" + insuranceId + "'";

		LOGGER.log(Level.INFO, "Query string: " + query);
		TypedQuery<Contract> typedQuery = entityManager.createQuery(query, Contract.class);
		try {
			Contract contract = typedQuery.getResultList().get(0);
			
			if(typedQuery.getResultList().size() != 1) {
				LOGGER.log(Level.SEVERE, "Found more than one contract matching insuranceId: " + insuranceId 
						+ ". Returned first contract found!");
			}
			else {
				LOGGER.log(Level.INFO, "Found contract");							
			}
			
			return contract;
		}
		catch(Exception e) {
			LOGGER.log(Level.INFO, "Found no contract");
			return null;
		}
	}  

}
