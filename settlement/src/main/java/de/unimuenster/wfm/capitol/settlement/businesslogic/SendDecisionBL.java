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
import de.unimuenster.wfm.capitol.helper.DateTools;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ClaimCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;
import de.unimuenster.wfm.capitol.service.MessageService;


@Stateless
@Named
public class SendDecisionBL {
	
	private static Logger LOGGER = Logger.getLogger(SendDecisionBL.class.getName());
	
	//TODO: set URL to send JSON to
	//"http://camunda-bvis.uni-muenster.de/???"
	private static String DESTINATION_URL;

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
		
//		decision.setClaim_id(claim_id);
//		decision.setCoverage_costs(d);
//		decision.setCustomer_costs(customer_costs);
//		decision.setDescription(description);
//		decision.setInsurance_decision(insurance_decision);
//		
//		
//		LOGGER.log(Level.INFO, "Decision sent successfully! Decision Result: " + order.getResult());

		
		
		
		
		
//		
//		Contract contract = contractCRUD.find((Integer) (delegateExecution.getVariable("contract_id")));	
//		
//		ContractProposal contractProposal = new ContractProposal();
//		Order order = contractProposal.new Order();
//		contractProposal.setOrder(order);
//
//		contractProposal.setProcessinstance_id_bvis(String.valueOf(delegateExecution.getVariable("processinstance_id_bvis")));
//		contractProposal.setProcessinstance_id_capitol(delegateExecution.getProcessInstanceId());
//
//		order.setFinal_price(contract.getTotalCost());
//		order.setInquiry_text("");
//		order.setOrder_id((Integer) delegateExecution.getVariable("order_id"));
//		try {
//			order.setRequest_date(DateTools.convertStringToDate((String) delegateExecution.getVariable("request_date")));
//		} catch (ParseException e) {
//			LOGGER.log(Level.SEVERE, "Contract Request Date could not be parsed!");
//			e.printStackTrace();
//		}
//		order.setResult((Integer) delegateExecution.getVariable("contract_result"));		
//
//		if(DESTINATION_URL != null) {
//			messageService.sendJSON(contractProposal, DESTINATION_URL);			
//		}
//		
//		LOGGER.log(Level.INFO, "Final contract price: " + contract.getTotalCost());
		
	}


}