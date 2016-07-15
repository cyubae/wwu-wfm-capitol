package de.unimuenster.wfm.capitol.entities;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.camunda.bpm.engine.cdi.annotation.BusinessProcessScoped;

import de.unimuenster.wfm.capitol.enums.InsuranceType;
import de.unimuenster.wfm.capitol.helper.DateTools;

@Entity
@BusinessProcessScoped
public class Contract implements Serializable {

	private static  final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(Contract.class.getName());

	@Id
	@GeneratedValue
	protected int contractId;
	
	//id used by external partner (corresponds to process variable insurance_insurance_id)
	protected int insuranceId;

	@Version
	protected long version;
	
	@ManyToOne(cascade = {DETACH, MERGE, PERSIST, REFRESH}, fetch=FetchType.EAGER)
	protected Customer customer;
	
	@OneToMany(cascade = {DETACH, MERGE, PERSIST, REFRESH}, mappedBy = "contract",fetch=FetchType.EAGER)
	protected Collection<Policy> policies = new ArrayList<Policy>();
	
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

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
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
	
	//https://notesonjava.wordpress.com/2008/11/03/managing-the-bidirectional-relationship/
	public void addPolicy(Policy policy) {
		this.policies.add(policy);
//		policy.setContract(this);
	}
	
	public void removePolicy(Policy policy) {
		this.policies.remove(policy);
//		policy.setContract(null);
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

	/**
	 * Returns sum of daily premiums of all associated policies
	 * @return
	 */
	public BigDecimal getTotalDailyPremium() {
		BigDecimal totalDailyPremium = new BigDecimal(0);
		for(Policy policy : this.getPolicies()) {
			totalDailyPremium = totalDailyPremium.add(policy.getDailyPremium());
		}
		return totalDailyPremium.setScale(2, RoundingMode.HALF_EVEN);
	}
	
	/**
	 * Returns costs of all associated polices for entire contract duration in euro cents
	 * @return
	 */
	public BigDecimal getTotalCost() {
		int numberOfDays = DateTools.getDaysBetweenDates(this.getPickUpDate(), this.getReturnDate());		
		return this.getTotalDailyPremium().multiply(new BigDecimal(numberOfDays)).setScale(2, RoundingMode.HALF_EVEN); 
	}

	@Override
	public String toString() {
		return "";
//		return "Contract [contractId=" + contractId + ", version=" + version 
//				//+ ", customer=" + customer 
//				+ ", policies="
//				+ policies + ", insuranceType=" + insuranceType + ", pickUpDate=" + pickUpDate + ", returnDate="
//				+ returnDate + ", validated=" + validated + ", released=" + released + "]";
	}
	
	
//	public static BigDecimal bdCalc() {
//		BigDecimal bd = new BigDecimal(1);
//		for(int i = 1; i<=10; i++) {
//			bd = bd.multiply(new BigDecimal(i));
//		}
//		return bd;
//	}
//	
//	public static void main(String[] args) {
//		System.out.println(bdCalc());
//	}

}

