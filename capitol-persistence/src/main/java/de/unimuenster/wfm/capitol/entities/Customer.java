package de.unimuenster.wfm.capitol.entities;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.camunda.bpm.engine.cdi.annotation.BusinessProcessScoped;

@Entity
@BusinessProcessScoped
public class Customer implements Serializable {

	private static  final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(Customer.class.getName());

	@Id
	@GeneratedValue
	protected int customerId;

	@Version
	protected long version;

	protected String firstName;
	protected String surname;
	protected String email;
	protected String phoneNumber;
	protected String street;
	protected String houseNumber;
	protected String postcode;
	protected String city;
	protected String country;
	protected String dateOfBirth; //DD-MM-YYYY
	protected	boolean company;
	protected String companyName;
	protected boolean blacklisted;
	
	@OneToMany(cascade = {DETACH, MERGE, PERSIST, REFRESH}, mappedBy="customer", fetch=FetchType.EAGER)
	protected Collection<Contract> contracts = new ArrayList<Contract>();

	public Customer() {
	}

	public Customer(String firstName, String surname, String email, String phoneNumber, String street,
			String houseNumber, String postcode, String city, String country, String dateOfBirth, boolean company,
			String companyName, boolean blacklisted) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.houseNumber = houseNumber;
		this.postcode = postcode;
		this.city = city;
		this.country = country;
		this.dateOfBirth = dateOfBirth;
		this.company = company;
		this.companyName = companyName;
		this.blacklisted = blacklisted;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		LOGGER.log(Level.INFO, "Called getFirstName");
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		LOGGER.log(Level.INFO, "Called setFirstName: setName=" + firstName);
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
	 * @return the blacklisted
	 */
	public boolean isBlacklisted() {
		return blacklisted;
	}

	/**
	 * @param blacklisted the blacklisted to set
	 */
	public void setBlacklisted(boolean blacklisted) {
		this.blacklisted = blacklisted;
	}

	public Collection<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Collection<Contract> contracts) {
		this.contracts = contracts;
	}

	public void addContract(Contract contract) {
		this.contracts.add(contract);
	}
	
	public void removeContract(Contract contract) {
		this.contracts.remove(contract);
	}


	@Override
	public String toString() {
		return "";
//		return "Customer [customerId=" + customerId + ", version=" + version + ", firstName=" + firstName + ", surname="
//				+ surname + ", email=" + email + ", phoneNumber=" + phoneNumber + ", street=" + street
//				+ ", houseNumber=" + houseNumber + ", postcode=" + postcode + ", city=" + city + ", country=" + country
//				+ ", dateOfBirth=" + dateOfBirth + ", company=" + company + ", companyName=" + companyName
//				+ ", blacklisted=" + blacklisted
//				//+ ", contracts=" + contracts 
//				+ "]";
	}



}

