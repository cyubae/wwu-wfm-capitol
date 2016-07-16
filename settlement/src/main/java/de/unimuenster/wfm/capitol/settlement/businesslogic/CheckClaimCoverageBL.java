package de.unimuenster.wfm.capitol.settlement.businesslogic;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.entities.Claim;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ClaimCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;


@Stateless
@Named
public class CheckClaimCoverageBL {
	
	//Limit in euro above which claim must be handled manually
	private static int AUTOMATIC_COVERAGE_LIMIT = 1000;

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
		
		int internalClaimId = (Integer) delegateExecution.getVariable("claim_id_internal");
		Claim claim = claimCRUD.find(internalClaimId);
		boolean handleManually = true;
		
		if(claim.getClaimValue().doubleValue() > AUTOMATIC_COVERAGE_LIMIT || claim.getPolicy() == null) {
			handleManually = true;
		}
		else if(claim.isHandleManually()) {
			handleManually = true;
		}
		else {
			handleManually = false;
		}
		
		delegateExecution.setVariable("handle_manually", handleManually);
		
	}
}