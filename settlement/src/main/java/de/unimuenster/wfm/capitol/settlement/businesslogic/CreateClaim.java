package de.unimuenster.wfm.capitol.settlement.businesslogic;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ClaimCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;

@Stateless
@Named
public class CreateClaim {
	
	private static Logger LOGGER = Logger.getLogger(CreateClaim.class.getName());

	// Inject CRUD-Services to access persistence unit	
	@Inject
	private ContractCRUD contractCRUD;

	@Inject
	private PolicyCRUD policyCRUD;

	@Inject
	private CarCRUD carCRUD;

	@Inject
	private CustomerCRUD customerCRUD;	
	
	@Inject
	private ClaimCRUD claimCRUD;

	public void performBusinessLogic(DelegateExecution delegateExecution) {

	}


}