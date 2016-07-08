package de.unimuenster.wfm.capitol.contracting.businesslogic;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.entities.Contract;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;

@Stateless
@Named
public class ReleaseContractBL {

	private static Logger LOGGER = Logger.getLogger(ReleaseContractBL.class.getName());

	// Inject CRUD-Services to access persistence unit	
	@Inject
	private ContractCRUD contractCRUD;

	public void performBusinessLogic(DelegateExecution delegateExecution) {
		LOGGER.log(Level.INFO, "ReleaseContractBL invoked");
		
		Contract contract  = contractCRUD.find((Integer) (delegateExecution.getVariable("contract_id")));

		Integer contractStatus = (Integer) delegateExecution.getVariable("contract_status");
		
		if(contractStatus == 1) {			
			contract.setReleased(true);
		}
		else {
			contract.setReleased(false);
		}
		
		contract = contractCRUD.update(contract);
		
		LOGGER.log(Level.INFO, "ReleaseContractBL finished. Contract release status " + contract.isReleased());
		
	}

}