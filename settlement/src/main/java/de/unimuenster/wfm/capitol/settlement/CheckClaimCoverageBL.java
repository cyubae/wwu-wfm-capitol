package de.unimuenster.wfm.capitol.settlement;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.camunda.bpm.engine.delegate.DelegateExecution;


/**
 * @author Christoph
 */
@Stateless
@Named
public class CheckClaimCoverageBL {

	  // Inject the entity manager
	  @PersistenceContext
	  private EntityManager entityManager;
	  
	  public void performBusinessLogic(DelegateExecution delegateExecution) {
		  
	  }
}