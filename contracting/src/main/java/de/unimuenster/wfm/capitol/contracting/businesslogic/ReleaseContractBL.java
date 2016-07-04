package de.unimuenster.wfm.capitol.contracting.businesslogic;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.entities.Contract;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;

@Stateless
@Named
public class ReleaseContractBL {

	// Inject CRUD-Services to access persistence unit	
	@Inject
	private ContractCRUD contractCRUD;

	public void performBusinessLogic(DelegateExecution delegateExecution) {
		Contract contract  = contractCRUD.find((Integer) (delegateExecution.getVariable("contract_id")));
		contract.setReleased(true);
	}


}