package de.unimuenster.wfm.capitol.jpa;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.unimuenster.wfm.capitol.entities.Contract;

/**
 * DAO for entity contract
 *
 */
@Stateless
@Named
public class AccessContract {

	private static Logger LOGGER = Logger.getLogger(AccessContract.class.getName());

	// Inject the entity manager
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Creates and persists a new Contract object 
	 * @return contractId of new Contract
	 */
	public Contract createContract() {
		Contract newContract = new Contract();
		entityManager.persist(newContract);
		entityManager.flush();
//		LOGGER.log(Level.INFO, "New empty Contract object created - contractId: " + newContract.getContractId());	

		return newContract;
	}

	/**
	 * Updates contract object pre-existing in database 
	 * @param contract
	 */
	public void updateContract(Contract contract) {
		LOGGER.log(Level.INFO, "Update Contract properties : " + contract.toString());
		entityManager.merge(contract);
		entityManager.flush();
	}

	/**
	 * Retrieves contract from database for given id
	 * @param contractId
	 * @return
	 */
	public Contract getContract(int contractId) {
		// Load order entity from database
		return entityManager.find(Contract.class, contractId);
	}
}
