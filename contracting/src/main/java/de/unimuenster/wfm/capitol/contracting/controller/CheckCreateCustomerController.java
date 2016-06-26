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
import org.camunda.bpm.engine.cdi.jsf.TaskForm;

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
	
	// Inject task form available through the camunda cdi artifact
	@Inject
	private TaskForm taskForm;

//	// Caches the Customer during the conversation
//	private Customer customer;
//
//	public Customer getCustomer() {
//		if (customer == null) {
//			// Create the new customer from the input data if not already cached
//			customer = createCustomer();
//		}
//		return customer;			
//	}

	/**
	 * Creates a new Customer object with attribute values taken from process variables
	 * @return
	 */
	private Customer createCustomer() {
		Customer newCustomer = customerAccess.createCustomer();
		
		newCustomer.setFirstName((String) businessProcess.getVariable("user_firstname"));
		newCustomer.setSurname((String) businessProcess.getVariable("user_surname"));
		newCustomer.setEmail((String) businessProcess.getVariable("user_email"));
		newCustomer.setPhoneNumber((String) businessProcess.getVariable("user_phone_number"));
		newCustomer.setStreet((String) businessProcess.getVariable("user_street"));
		newCustomer.setHouseNumber((String) businessProcess.getVariable("user_house_number"));
		newCustomer.setPostcode((String) businessProcess.getVariable("user_postcode"));
		newCustomer.setCity((String) businessProcess.getVariable("user_city"));
		newCustomer.setCountry((String) businessProcess.getVariable("user_country"));
		newCustomer.setDateOfBirth((String) businessProcess.getVariable("user_date_of_birth"));
		if (((String)businessProcess.getVariable("user_iscompany")).equals("true")) {
			newCustomer.setCompany(true);
			newCustomer.setCompanyName((String) businessProcess.getVariable("user_company_name"));			
		}
		else {
			newCustomer.setCompany(false);
			newCustomer.setCompanyName(null);
		}
		newCustomer.setBlacklisted(false);
		
		LOGGER.log(Level.INFO, "New customer created: " + newCustomer.toString());
		customerAccess.updateCustomer(newCustomer);
		LOGGER.log(Level.INFO, "Successfully updated customer");

		return newCustomer;
	}

	
	/**
	 * Persist customer created with process variables passed from form and complete task form
	 * @throws IOException
	 */
	public void submitForm() throws IOException {
		this.createCustomer();
		taskForm.completeTask();
	}
}
