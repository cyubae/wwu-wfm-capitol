/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.unimuenster.wfm.capitol.contracting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.annotation.ProcessVariable;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.cdi.*;

import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.jpa.AccessCustomer;
import de.unimuenster.wfm.capitol.service.MessageService;

/**
 * Controller class for check_create_customer.xhtml
 *
 */
@Named
@ManagedBean
//@ConversationScoped
public class CheckCreateCustomerController implements Serializable, TaskListener   {

	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = Logger.getLogger(CheckCreateCustomerController.class.getName());

	@EJB(lookup="java:global/MessagingService/MessageServiceImpl!de.unimuenster.wfm.capitol.service.MessageService")
	private static MessageService MESSAGESERVICE;

	private String firstName;




	//	@EJB
	//	private static AccessCustomer CUSTOMERACCESS = new AccessCustomer();

	//	// Inject task form available through the camunda cdi artifact
	//	@Inject
	//	private TaskForm taskForm;

	//	private void initializeVariables() throws NullPointerException {
	//
	//		
	//		LOGGER.log(Level.INFO, "injectFirstName: " + injectFirstName);
	//		
	//		LOGGER.log(Level.INFO, "injectFirstName: " + injectFirstName);
	//		firstName = (String) injectFirstName;
	//		LOGGER.log(Level.INFO, "Initialize firstName: " + firstName);
	//		
	//		surname = (String) injectSurname;
	//		LOGGER.log(Level.INFO, "Initialize surname: " + surname);
	//		
	//		email = (String) injectEmail;
	//		
	//		phoneNumber = (String) injectPhoneNumber;
	//		
	//		street = (String) injectStreet;
	//
	//		houseNumber = (String) injectHouseNumber;
	//
	//		postcode = (String) injectPostcode;
	//		
	//		city = (String) injectCity;
	//		
	//		country = (String) injectCountry;
	//		
	//		dateOfBirth = (String) injectDateOfBirth;
	//		
	//		company = ((String) injectCompany).equals("true") ? true : false;
	//
	//		companyName = (String) injectCompany;		
	//	}
	//	
	//	public CheckCreateCustomerController() {
	//		try{
	//			this.initializeVariables();
	//		}
	//		catch(NullPointerException e) {
	//			LOGGER.log(Level.SEVERE, "Caught NPE!");
	//		}
	//		
	//	}

	/**
	 * Creates a new Customer object with attribute values taken from process variables
	 * @return
	 */
	//	private Customer createCustomer() {
	//
	//		Customer newCustomer = customerAccess.createCustomer(firstName, surname, email, phoneNumber, street, houseNumber, postcode, city, country, dateOfBirth, company, companyName, false);
	//
	//		LOGGER.log(Level.INFO, "New customer created: " + newCustomer.toString());
	//		customerAccess.updateCustomer(newCustomer);
	//		LOGGER.log(Level.INFO, "Successfully updated customer");
	//
	//		return newCustomer;
	//	}


	/**
	 * Persist customer created with process variables passed from form and complete task form
	 * @throws IOException
	 */
	//	public void submitForm() throws IOException {
	//
	//		LOGGER.log(Level.INFO, "BEFORE COMPLETE TASK: businessProcess.getCachedLocalVariableMap");
	//
	//		Map<String, Object> dataMap = businessProcess.getCachedLocalVariableMap();
	//		//iterating over values only
	//		for (Object value : dataMap.values()) {
	//			LOGGER.log(Level.INFO, "dataMap - Value = " + value);
	//		}
	//
	//		LOGGER.log(Level.INFO, "BEFORE COMPLETE TASK: businessProcess.getCachedVariableMap");
	//
	//		dataMap = businessProcess.getCachedVariableMap();
	//		//iterating over values only
	//		for (Object value : dataMap.values()) {
	//			LOGGER.log(Level.INFO, "dataMap - Value = " + value);
	//		}
	//
	//		this.createCustomer();
	//		taskForm.completeTask();
	//
	//
	//		LOGGER.log(Level.INFO, "AFTER COMPLETE TASK: businessProcess.getCachedLocalVariableMap");
	//
	//		dataMap = businessProcess.getCachedLocalVariableMap();
	//		//iterating over values only
	//		for (Object value : dataMap.values()) {
	//			LOGGER.log(Level.INFO, "dataMap - Value = " + value);
	//		}
	//
	//		LOGGER.log(Level.INFO, "AFTER COMPLETE TASK: businessProcess.getCachedVariableMap");
	//
	//		dataMap = businessProcess.getCachedVariableMap();
	//		//iterating over values only
	//		for (Object value : dataMap.values()) {
	//			LOGGER.log(Level.INFO, "dataMap - Value = " + value);
	//		}
	//	}

	/**
	 * Transfers all process variables to temp_variables
	 * 
	 */
	public void transferToTempVariables(DelegateTask delegateTask) {
		LOGGER.log(Level.INFO, "Called transferToTempVariables");
//		Map<String, Object> dataMap = delegateTask.getVariables();
////		for (Object value : dataMap.values()) {
////			LOGGER.log(Level.INFO, "dataMap - Value = " + value);
////			
////		}
//		for (String key : dataMap.keySet()) {
//			String value = (String) dataMap.get(key);
//			LOGGER.log(Level.INFO, "Key: " + key + ", Value: " + value );
//			delegateTask.setVariable(key, value + "_temp");
//		}

	}

	/**
	 * Transfers all temp_variables to process variables
	 */
	public void retransferToProcessVariables(DelegateTask delegateTask) {
		LOGGER.log(Level.INFO, "Called transferToTempVariables");
//		Map<String, Object> dataMap = delegateTask.getVariables();
//		for (String key : dataMap.keySet()) {
//			String value = (String) dataMap.get(key);
//			LOGGER.log(Level.INFO, "Key: " + key + ", Value: " + value );
//			delegateTask.setVariable(key, value + "_temp");
//		}		
	}	


	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		LOGGER.log(Level.INFO, "Called execute");
		this.setFirstName((String) delegateTask.getVariable("user_firstname"));
		LOGGER.log(Level.INFO, "first name set: " + this.getFirstName());

		//		Map<String, Object> dataMap = delegateTask.getVariables();
		//		LOGGER.log(Level.INFO, "Line 2");
		//		for (Object value : dataMap.values()) {
		//			LOGGER.log(Level.INFO, "dataMap - Value = " + value);
		//		}
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		LOGGER.log(Level.INFO, "getFirstName invoked: " + this.firstName);
		return firstName;
	}




	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}	

	//	@Override
	//	public void execute(DelegateExecution execution) throws Exception {
	//		// TODO Auto-generated method stub
	//		LOGGER.log(Level.INFO, "Called execute");
	//		Map<String, Object> dataMap = execution.getVariables();
	//		LOGGER.log(Level.INFO, "Line 2");
	//		for (Object value : dataMap.values()) {
	//			LOGGER.log(Level.INFO, "dataMap - Value = " + value);
	//		}
	//	}
}
