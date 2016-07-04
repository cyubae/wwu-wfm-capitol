package de.unimuenster.wfm.capitol.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import de.unimuenster.wfm.capitol.entities.Contract;
import de.unimuenster.wfm.capitol.entities.Policy;

/**
 * DAO for entity Policy
 *
 */
@Stateless
@Named
public class PolicyCRUD extends AbstractCRUDService<Policy> {
	
	private static Logger LOGGER = Logger.getLogger(PolicyCRUD.class.getName());
	
	// Inject CRUD-Services to access persistence unit	
	@Inject
	private ContractCRUD contractCRUD;
	
    public PolicyCRUD() {
        super(Policy.class);
    }
        
    /**
     * Finds list of policies associated with given contract id
     */
	public Collection<Policy> findPoliciesForContractId(int contractId) {

		LOGGER.log(Level.INFO, "");
		Contract contract = contractCRUD.find(contractId);
		Collection<Policy> policies = contract.getPolicies();

		LOGGER.log(Level.INFO, "Found Contract with contractId: " + contract.getContractId());
		LOGGER.log(Level.INFO, "Contract has number of policies: " + contract.getPolicies().size());
		
		LOGGER.log(Level.INFO, "Found resultsList: ");
		int i = 1;
		for (Policy p : policies) {
			LOGGER.log(Level.INFO, "ResultNo: " + i++);
			LOGGER.log(Level.INFO, "PolicyId: " + p.getPolicyId());
		}
		LOGGER.log(Level.INFO, "Finished printing resultsList");
		
		return policies;

	}

}
