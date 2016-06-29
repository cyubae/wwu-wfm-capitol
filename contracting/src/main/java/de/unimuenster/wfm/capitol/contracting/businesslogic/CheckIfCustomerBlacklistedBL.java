package de.unimuenster.wfm.capitol.contracting.businesslogic;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.delegate.DelegateExecution;

@Stateless
@Named
public class CheckIfCustomerBlacklistedBL {

	  // Inject the entity manager
	  @PersistenceContext
	  private EntityManager entityManager;
	  
	  public void performBusinessLogic(DelegateExecution delegateExecution) {
		  
	  }


}