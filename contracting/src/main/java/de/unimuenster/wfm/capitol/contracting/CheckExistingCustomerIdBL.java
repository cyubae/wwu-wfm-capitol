package de.unimuenster.wfm.capitol.contracting;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.service.MessageService;


@Stateless
@Named
public class CheckExistingCustomerIdBL {

	  // Inject the entity manager
	  @PersistenceContext
	  private EntityManager entityManager;
	  
	  @EJB(lookup="java:global/MessagingService/MessageServiceImpl!de.unimuenster.wfm.capitol.service.MessageService")
	  private MessageService messageService;
	  
	  public void performBusinessLogic(DelegateExecution delegateExecution) {
		  //messageService.sendContractProposal(null);
		  
		  
	  }


}