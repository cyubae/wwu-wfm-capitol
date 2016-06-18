package de.unimuenster.wfm.capitol.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Customer implements Serializable {

  private static  final long serialVersionUID = 1L;

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
  protected Date dateOfBirth; //DD-MM-YYYY
  protected	boolean company;
  protected String companyName;
  protected String address;
  
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
 * @return the dateOfBirth
 */
public Date getDateOfBirth() {
	return dateOfBirth;
}
/**
 * @param dateOfBirth the dateOfBirth to set
 */
public void setDateOfBirth(Date dateOfBirth) {
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
 * @return the address
 */
public String getAddress() {
	return address;
}
/**
 * @param address the address to set
 */
public void setAddress(String address) {
	this.address = address;
}

  
  
}

