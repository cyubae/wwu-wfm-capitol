package de.unimuenster.wfm.capitol.jpa;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.entities.Customer;

@Stateless
@Named
public class Persister {
	
	  // Inject the entity manager
	  @PersistenceContext
	  private EntityManager entityManager;

	  public void persistCustomer(HashMap<String,String> data) {
	    // Create new order instance
	    Customer customer = new Customer();
	    
	    customer.setFirstName(data.get("firstName"));
	    customer.setSurname(data.get("surname"));
	    
	    /*
	      Persist order instance and flush. After the flush the
	      id of the order instance is set.
	    */
	    entityManager.persist(customer);
	    entityManager.flush();
	    
//
//	    // Get all process variables
//	    Map<String, Object> variables = delegateExecution.getVariables();
//
//	    // Set order attributes
//	    orderEntity.setCustomer((String) variables.get("customer"));
//	    orderEntity.setAddress((String) variables.get("address"));
//	    orderEntity.setPizza((String) variables.get("pizza"));
//
//	    /*
//	      Persist order instance and flush. After the flush the
//	      id of the order instance is set.
//	    */
//	    entityManager.persist(orderEntity);
//	    entityManager.flush();
//
//	    // Remove no longer needed process variables
//	    delegateExecution.removeVariables(variables.keySet());
//
//	    // Add newly created order id as process variable
//	    delegateExecution.setVariable("orderId", orderEntity.getId());
	  }	

}
