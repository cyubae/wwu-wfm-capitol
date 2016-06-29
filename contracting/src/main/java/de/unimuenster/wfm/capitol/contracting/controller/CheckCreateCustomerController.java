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
import org.camunda.bpm.engine.cdi.*;

import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.jpa.AccessCustomer;
import de.unimuenster.wfm.capitol.service.MessageService;

/**
 * Controller class for check_create_customer.xhtml
 * @author mkubicki
 *
 */
@Named
@ManagedBean
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

	// Inject the process variables during the conversation
	@Inject
	@ProcessVariable("user_firstname")
	private Object injectFirstName;
	
	@Inject
	@ProcessVariable("user_surname")
	private Object injectSurname;
	
	@Inject
	@ProcessVariable("user_email")
	private Object injectEmail;
	
	@Inject
	@ProcessVariable("user_phone_number")
	private Object injectPhoneNumber;
	
	@Inject
	@ProcessVariable("user_street")
	private Object injectStreet;	

	@Inject
	@ProcessVariable("user_house_number")
	private Object injectHouseNumber;
	
	@Inject
	@ProcessVariable("user_postcode")
	private Object injectPostcode;	
	
	@Inject
	@ProcessVariable("user_city")
	private Object injectCity;	
	
	@Inject
	@ProcessVariable("user_country")
	private Object injectCountry;	
	
	@Inject
	@ProcessVariable("user_date_of_birth")
	private Object injectDateOfBirth;
	
	@Inject
	@ProcessVariable("user_iscompany")
	private Object injectCompany;
	
	@Inject
	@ProcessVariable("user_company_name")
	private Object injectCompanyName;	
	
	// Cache the process variables during the conversation
	private String firstName;
	
	private String surname;
	
	private String email;
	
	private String phoneNumber;
	
	private String street;

	private String houseNumber;

	private String postcode;
	
	private String city;
	
	private String country;
	
	private String dateOfBirth;
	
	private boolean company;

	private String companyName;
	
	public CheckCreateCustomerController() {
		try{
			firstName = (String) injectFirstName;
			
			surname = (String) injectSurname;
			
			email = (String) injectEmail;
			
			phoneNumber = (String) injectPhoneNumber;
			
			street = (String) injectStreet;

			houseNumber = (String) injectHouseNumber;

			postcode = (String) injectPostcode;
			
			city = (String) injectCity;
			
			country = (String) injectCountry;
			
			dateOfBirth = (String) injectDateOfBirth;
			
			company = ((String) injectCompany).equals("true") ? true : false;

			companyName = (String) injectCompany;			
		}
		catch(NullPointerException e) {
			LOGGER.log(Level.SEVERE, "Caught NPE!");
		}
		
	}

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

		Customer newCustomer = customerAccess.createCustomer(firstName, surname, email, phoneNumber, street, houseNumber, postcode, city, country, dateOfBirth, company, companyName, false);

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

		LOGGER.log(Level.INFO, "BEFORE COMPLETE TASK: businessProcess.getCachedLocalVariableMap");

		Map<String, Object> dataMap = businessProcess.getCachedLocalVariableMap();
		//iterating over values only
		for (Object value : dataMap.values()) {
			LOGGER.log(Level.INFO, "dataMap - Value = " + value);
		}

		LOGGER.log(Level.INFO, "BEFORE COMPLETE TASK: businessProcess.getCachedVariableMap");

		dataMap = businessProcess.getCachedVariableMap();
		//iterating over values only
		for (Object value : dataMap.values()) {
			LOGGER.log(Level.INFO, "dataMap - Value = " + value);
		}

		this.createCustomer();
		taskForm.completeTask();


		LOGGER.log(Level.INFO, "AFTER COMPLETE TASK: businessProcess.getCachedLocalVariableMap");

		dataMap = businessProcess.getCachedLocalVariableMap();
		//iterating over values only
		for (Object value : dataMap.values()) {
			LOGGER.log(Level.INFO, "dataMap - Value = " + value);
		}

		LOGGER.log(Level.INFO, "AFTER COMPLETE TASK: businessProcess.getCachedVariableMap");

		dataMap = businessProcess.getCachedVariableMap();
		//iterating over values only
		for (Object value : dataMap.values()) {
			LOGGER.log(Level.INFO, "dataMap - Value = " + value);
		}
	}


	/**
	 * @return the messageService
	 */
	public MessageService getMessageService() {
		return messageService;
	}


	/**
	 * @param messageService the messageService to set
	 */
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}


	/**
	 * @return the customerAccess
	 */
	public AccessCustomer getCustomerAccess() {
		return customerAccess;
	}


	/**
	 * @param customerAccess the customerAccess to set
	 */
	public void setCustomerAccess(AccessCustomer customerAccess) {
		this.customerAccess = customerAccess;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}


	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}


	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}


	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}


	/**
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}


	/**
	 * @param houseNumber the houseNumber to set
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}


	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}


	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}


	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	/**
	 * @return the company
	 */
	public boolean isCompany() {
		return company;
	}


	/**
	 * @param company the company to set
	 */
	public void setCompany(boolean company) {
		this.company = company;
	}


	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}


	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
