package persisting;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.jpa.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named
public class PersistBL {
	
	  public void persistCustomer(DelegateExecution delegateExecution) {
		  
		  HashMap<String, String> inputs = new HashMap<String, String>();
		  inputs.put("firstName", "Max");
		  inputs.put("surName", "Mustermann");
		  
		  new Persister().persistCustomer(inputs);
		  
	  }	

}
