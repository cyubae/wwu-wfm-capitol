package de.unimuenster.wfm.capitol.jpa;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import de.unimuenster.wfm.capitol.entities.Car;
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
	 * Returns car if available for given vehicleIdentificationNumber
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
			
			LOGGER.log(Level.INFO, "Found contract");
			return contract;
		}
		catch(Exception e) {
			LOGGER.log(Level.INFO, "Found no contract");
			return null;
		}
	}  

}
