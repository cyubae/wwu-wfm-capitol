package de.unimuenster.wfm.capitol.entities;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import org.camunda.bpm.engine.cdi.annotation.BusinessProcessScoped;

@Entity
@BusinessProcessScoped
public class Insurance implements Serializable {

	private static  final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(Insurance.class.getName());

	@Id
	@GeneratedValue
	protected int insuranceId;

	@Version
	protected long version;

	protected String company;
	protected String street;
	protected String houseNumber;
	protected String postcode;
	protected String city;
	protected String country;

	@OneToMany(cascade = {DETACH, MERGE, PERSIST, REFRESH}, mappedBy = "insurance",fetch=FetchType.EAGER)
	protected Collection<ExternalParty> claims = new ArrayList<ExternalParty>();	

	public Insurance() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Collection<ExternalParty> getClaims() {
		return claims;
	}

	public void setClaims(Collection<ExternalParty> claims) {
		this.claims = claims;
	}

	public int getInsuranceId() {
		return insuranceId;
	}
}

