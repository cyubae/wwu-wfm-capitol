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
  protected Long customerId;

  @Version
  protected long version;

  protected String firstName;
  protected String lastName;
  protected Date dateOfBirth;
  protected String address;
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
 * @return the lastName
 */
public String getLastName() {
	return lastName;
}
/**
 * @param lastName the lastName to set
 */
public void setLastName(String lastName) {
	this.lastName = lastName;
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

