package de.unimuenster.wfm.capitol.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Version;

import org.camunda.bpm.engine.cdi.annotation.BusinessProcessScoped;

import de.unimuenster.wfm.capitol.enums.InsuranceType;

@Entity
@BusinessProcessScoped
public class Contract implements Serializable {

	private static  final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(Contract.class.getName());

	@Id
	@GeneratedValue
	protected int contractId;

	@Version
	protected long version;
	
	@ManyToOne
	protected Customer customer;
	
	@OneToMany(mappedBy="contract")
	protected Collection<Policy> policies;
	protected InsuranceType insuranceType;

	protected Date pickUpDate;

	protected Date returnDate;
	
	protected boolean validated;
	
	protected boolean released;

	public Contract() {
	}

	public Contract(Customer customer, Collection<Policy> policies, InsuranceType insuranceType, Date pickUpDate,
			Date returnDate, boolean released) {
		super();
		this.customer = customer;
		this.policies = policies;
		this.insuranceType = insuranceType;
		this.pickUpDate = pickUpDate;
		this.returnDate = returnDate;
		this.released = released;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(Collection<Policy> policies) {
		this.policies = policies;
	}

	public InsuranceType getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}

	public Date getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		LOGGER.log(Level.INFO, "Called setValidated: " + validated);
		this.validated = validated;
	}

	public int getContractId() {
		return contractId;
	}

	public long getVersion() {
		return version;
	}

	public boolean isReleased() {
		return released;
	}

	public void setReleased(boolean released) {
		this.released = released;
	}

	@Override
	public String toString(){
		return "";
	}

}

