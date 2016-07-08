package de.unimuenster.wfm.capitol.settlement.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.dto.CaseDecision;
import de.unimuenster.wfm.capitol.dto.CaseDecision.Decision;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;
import de.unimuenster.wfm.capitol.service.MessageService;


@Stateless
@Named
public class SendDecisionBL {
	
	private static Logger LOGGER = Logger.getLogger(SendDecisionBL.class.getName());

	// Inject CRUD-Services to access persistence unit	
	@Inject
	private ContractCRUD contractCRUD;

	@Inject
	private PolicyCRUD policyCRUD;

	@Inject
	private CarCRUD carCRUD;

	@Inject
	private CustomerCRUD customerCRUD;	
	
	@EJB(lookup="java:global/MessagingService/MessageServiceImpl!de.unimuenster.wfm.capitol.service.MessageService")
	private MessageService messageService;

	public void performBusinessLogic(DelegateExecution delegateExecution) {
		LOGGER.log(Level.INFO, "Decision sending invoked!");
		
		CaseDecision caseDecision = new CaseDecision();
		Decision decision = caseDecision.new Decision();
		caseDecision.setDecision(decision);
		
		caseDecision.setProcessinstance_id_bvis(String.valueOf(delegateExecution.getVariable("processinstance_id_bvis")));
		caseDecision.setProcessinstance_id_capitol(delegateExecution.getProcessInstanceId());
		
//		decision.setClaim_id(claim_id);
//		decision.setCoverage_costs(d);
//		decision.setCustomer_costs(customer_costs);
//		decision.setDescription(description);
//		decision.setInsurance_decision(insurance_decision);		
		
		//TODO Fill the contractProposal with the correct data, find out the correct URL to send the file to
//		messageService.sendJSON(contractProposal, "http://camunda-bvis.uni-muenster.de/???");
		
		LOGGER.log(Level.INFO, "Decision sent successfully!");
	}


}