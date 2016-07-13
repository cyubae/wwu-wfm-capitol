package de.unimuenster.wfm.capitol.settlement.businesslogic;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.dto.CaseDecision;
import de.unimuenster.wfm.capitol.dto.CaseDecision.Decision;
import de.unimuenster.wfm.capitol.dto.ContractProposal;
import de.unimuenster.wfm.capitol.dto.ContractProposal.Order;
import de.unimuenster.wfm.capitol.entities.Claim;
import de.unimuenster.wfm.capitol.entities.Contract;
import de.unimuenster.wfm.capitol.enums.ClaimDecision;
import de.unimuenster.wfm.capitol.helper.DateTools;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ClaimCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;
import de.unimuenster.wfm.capitol.service.MessageService;
import de.unimuenster.wfm.capitol.settlement.helper.EnumMapper;


@Stateless
@Named
public class SendDecisionBL {
	
	private static Logger LOGGER = Logger.getLogger(SendDecisionBL.class.getName());
	
	//TODO: set URL to send JSON to
	//"http://camunda-bvis.uni-muenster.de/???"
	private static String DESTINATION_URL = "http://camunda-bvis.uni-muenster.de/bvis/claimdecision";

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
	
	@EJB(lookup="java:global/MessagingService/MessageServiceImpl!de.unimuenster.wfm.capitol.service.MessageService")
	private MessageService messageService;

	public void performBusinessLogic(DelegateExecution delegateExecution) {
		LOGGER.log(Level.INFO, "SendDecisionBL invoked!");
		
		Claim claim = claimCRUD.find((Integer) delegateExecution.getVariable("claim_id_internal"));
		
		CaseDecision caseDecision = new CaseDecision();
		Decision decision = caseDecision.new Decision();
		caseDecision.setDecision(decision);
		
		caseDecision.setProcessinstance_id_bvis((String) delegateExecution.getVariable("processinstance_id_bvis"));
		caseDecision.setProcessinstance_id_capitol((String) delegateExecution.getVariable(delegateExecution.getProcessInstanceId()));
		
		decision.setClaim_id(claim.getExternalClaimId());
		decision.setCoverage_costs(claim.getCoverageCosts());
		decision.setCustomer_costs(claim.getCustomerCosts());
		decision.setDescription("");
		decision.setInsurance_decision(EnumMapper.CLAIMDECISION_TO_INTEGER.get(claim.getClaimDecision()));
		
		sendCaseDecision(caseDecision);
		
		LOGGER.log(Level.INFO, "Decision sent successfully! Decision Result: " + caseDecision.getDecision().getInsurance_decision());
		
	}
	
	
	public void sendIncompleteDataNotification(DelegateExecution delegateExecution) {
		
		LOGGER.log(Level.INFO, "sendIncompleteDataNotification invoked");
		CaseDecision caseDecision = new CaseDecision();
		Decision decision = caseDecision.new Decision();
		caseDecision.setDecision(decision);
		
		caseDecision.setProcessinstance_id_bvis((String) delegateExecution.getVariable("processinstance_id_bvis"));
		caseDecision.setProcessinstance_id_capitol((String) delegateExecution.getVariable(delegateExecution.getProcessInstanceId()));
		
		decision.setDescription("We could not find a valid policy for received vehicle_identification_number: "
		+ (String) delegateExecution.getVariable("vehicle_identification_number") );		
		
		decision.setInsurance_decision(EnumMapper.CLAIMDECISION_TO_INTEGER.get(ClaimDecision.NOT_COVERED));
	}
	
	
	public void sendCaseDecision(CaseDecision caseDecision) {
		if(DESTINATION_URL != null) {
			messageService.sendJSON(caseDecision, DESTINATION_URL);			
		}
	}


}