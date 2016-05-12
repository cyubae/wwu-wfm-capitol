package de.unimuenster.wfm.capitol.contracting;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
	  @Inject
	  private MessageService messageService;
	  
	  public void performCheck(DelegateExecution delegateExecution) {
		  messageService.sendContractProposal(null);
	  }


}