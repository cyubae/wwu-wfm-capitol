package de.unimuenster.wfm.capitol.contracting;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.delegate.DelegateExecution;

@Stateless
@Named
public class CheckExistingCustomerIdBL {

	  // Inject the entity manager
	  @PersistenceContext
	  private EntityManager entityManager;
	  
	  public void performCheck(DelegateExecution delegateExecution) {
		  
	  }


}
