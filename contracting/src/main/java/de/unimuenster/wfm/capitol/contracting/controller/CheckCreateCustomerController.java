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

import org.camunda.bpm.engine.cdi.BusinessProcess;

import com.fasterxml.jackson.annotation.JsonProperty.Access;

import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.jpa.AccessCustomer;
import de.unimuenster.wfm.capitol.service.MessageService;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller class for check_create_customer.xhtml
 * @author mkubicki
 *
 */
@Named
@ConversationScoped
public class CheckCreateCustomerController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger LOGGER = Logger.getLogger(CheckCreateCustomerController.class.getName());
	
	@EJB(lookup="java:global/MessagingService/MessageServiceImpl!de.unimuenster.wfm.capitol.service.MessageService")
	private MessageService messageService;

	@EJB
	private AccessCustomer customerAccess = new AccessCustomer();
	

	// the BusinessProcess to access the process variables
	@Inject
	private BusinessProcess businessProcess;

//	// Inject the CheckExistingCustomerIdBL to update the persisted order
//	@Inject
//	private CheckExistingCustomerIdBL checkExistingCustBL;

	// Caches the customerId during the conversation
	private int customerId;
	
	// Caches the customer during the conversation
	private Customer customer;	

	public Customer getCustomer() {
		if (customerId < 0 || customer == null) {
			// Create the new customer from the input data if not already cached
			customerId = createCustomer();
			customer = customerAccess.getCustomerById(customerId);
		}
		return customer;			
	}

	private int createCustomer() {
		int newCustomerId = customerAccess.createCustomer();
		
		customerAccess.updateCustomer(newCustomerId, "firstName", (String) businessProcess.getVariable("user_firstname"));
		customerAccess.updateCustomer(newCustomerId, "surname", (String) businessProcess.getVariable("user_surname"));
		customerAccess.updateCustomer(newCustomerId, "email", (String) businessProcess.getVariable("user_email"));
		customerAccess.updateCustomer(newCustomerId, "phoneNumber", (String) businessProcess.getVariable("user_phone_number"));
		customerAccess.updateCustomer(newCustomerId, "street", (String) businessProcess.getVariable("user_street"));
		customerAccess.updateCustomer(newCustomerId, "houseNumber", (String) businessProcess.getVariable("user_house_number"));
		customerAccess.updateCustomer(newCustomerId, "postcode", (String) businessProcess.getVariable("user_postcode"));
		customerAccess.updateCustomer(newCustomerId, "city", (String) businessProcess.getVariable("user_city"));
		customerAccess.updateCustomer(newCustomerId, "country", (String) businessProcess.getVariable("user_country"));
		customerAccess.updateCustomer(newCustomerId, "dateOfBirth", (String) businessProcess.getVariable("user_date_of_birth"));
		customerAccess.updateCustomer(newCustomerId, "company", (String) businessProcess.getVariable("user_iscompany"));
		customerAccess.updateCustomer(newCustomerId, "companyName", (String) businessProcess.getVariable("user_company_name"));
		
		LOGGER.log(Level.INFO, "New customer created: " + customerAccess.getCustomerById(newCustomerId).toString());	

		return newCustomerId;
	}

	public void submitForm() throws IOException {
		// Persist updated customer and complete task form
	}
}
