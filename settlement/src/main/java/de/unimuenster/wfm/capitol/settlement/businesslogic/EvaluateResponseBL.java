package de.unimuenster.wfm.capitol.settlement.businesslogic;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.entities.Car;
import de.unimuenster.wfm.capitol.entities.Claim;
import de.unimuenster.wfm.capitol.enums.ClaimDecision;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ClaimCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;

@Stateless
@Named
public class EvaluateResponseBL {

	private static Logger LOGGER = Logger.getLogger(EvaluateResponseBL.class.getName());

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

		Claim claim = claimCRUD.find((Integer) delegateExecution.getVariable("claim_id_internal"));
		ClaimDecision claimDecision = claim.getClaimDecision();

		//Decision by BVIS: 1 = accepted, 0 = declined, 2 = remarks
		int externalDecision = (Integer) delegateExecution.getVariable("claim_status");

		boolean sendRefund = false;
		boolean reviewFeedback = false;
		if(externalDecision == 1) {
			if(claimDecision == ClaimDecision.COVERED  || claimDecision == ClaimDecision.PARTIALLY_COVERED) {
				sendRefund = true;
			}
		}
		else if(externalDecision == 2) {
			reviewFeedback = true;
		}


		delegateExecution.setVariable("review_feedback", reviewFeedback);
		delegateExecution.setVariable("send_refund", sendRefund);
	}
}