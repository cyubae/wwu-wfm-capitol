package de.unimuenster.wfm.capitol.contracting;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.dto.ContractProposal;
import de.unimuenster.wfm.capitol.dto.ContractProposal.Order;
import de.unimuenster.wfm.capitol.service.MessageService;


@Stateless
@Named
public class SendContractProposalBL {

	  // Inject the entity manager
	  @PersistenceContext
	  private EntityManager entityManager;
	  
	  @EJB(lookup="java:global/MessagingService/MessageServiceImpl!de.unimuenster.wfm.capitol.service.MessageService")
	  private MessageService messageService;
	  
	  public void performBusinessLogic(DelegateExecution delegateExecution) {
		  ContractProposal contractProposal = new ContractProposal();
		  Order order = contractProposal.new Order();
		  contractProposal.setOrder(order);
		  //TODO Fill the contractProposal with the correct files, find out the correct URL to send the file to
		  messageService.sendJSON(contractProposal, "http://camunda-bvis.uni-muenster.de/???");
	  }


}