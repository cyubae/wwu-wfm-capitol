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

import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.jpa.AccessCustomer;
import de.unimuenster.wfm.capitol.service.MessageService;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ConversationScoped
public class CheckCreateCustomerController implements Serializable {

	private static  final long serialVersionUID = 1L;
	
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

	// Caches the Customer during the conversation
	private Customer customer;

	public Customer getCustomer() {
		if (customer == null) {
			// Create the new customer from the input data if not already cached
			customer = createCustomer();
		}
		return customer;			
	}

	private Customer createCustomer() {
		Customer newCustomer = new Customer();

		newCustomer.setFirstName((String) businessProcess.getVariable("firstname"));
		newCustomer.setSurname((String) businessProcess.getVariable("surname"));
		newCustomer.setEmail((String) businessProcess.getVariable("email"));
		newCustomer.setPhoneNumber((String) businessProcess.getVariable("phone_number"));
		newCustomer.setStreet((String) businessProcess.getVariable("street"));
		newCustomer.setHouseNumber((String) businessProcess.getVariable("house_number"));
		newCustomer.setPostcode((String) businessProcess.getVariable("postcode"));
		newCustomer.setCity((String) businessProcess.getVariable("city"));
		newCustomer.setCountry((String) businessProcess.getVariable("country"));
		newCustomer.setDateOfBirth((String) businessProcess.getVariable("date_of_birth"));
		newCustomer.setCompany((Boolean) businessProcess.getVariable("company"));
		newCustomer.setCompanyName((String) businessProcess.getVariable("company_name"));

		return newCustomer;
	}

	public void submitForm() throws IOException {
		// Persist updated order entity and complete task form
		customerAccess.persistCustomer(customer);;
	}
}
