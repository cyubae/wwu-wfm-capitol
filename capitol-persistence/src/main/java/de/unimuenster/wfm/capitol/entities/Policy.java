package de.unimuenster.wfm.capitol.entities;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class Policy implements Serializable {

	private static  final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(Policy.class.getName());

	@Id
	@GeneratedValue
	protected int policyId;

	@Version
	protected long version;
	
	@OneToOne(cascade = {DETACH, MERGE, PERSIST, REFRESH}, fetch=FetchType.EAGER)
	protected Car car;
	
	@ManyToOne(cascade = {DETACH, MERGE, PERSIST, REFRESH}, fetch=FetchType.EAGER)
	protected Contract contract;
	
	@OneToMany(cascade = {DETACH, MERGE, PERSIST, REFRESH}, mappedBy = "policy",fetch=FetchType.EAGER)
	protected Collection<Claim> claims = new ArrayList<Claim>();	

	//daily insurance premium (in euro cents)
	protected BigDecimal dailyPremium;
	protected int discount = 0;
	

	public Policy() {

	}

	public Policy(Car car, Contract contract, BigDecimal dailyPremium) {
		super();
		this.car = car;
		this.contract = contract;
		this.dailyPremium = dailyPremium;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public BigDecimal getDailyPremium() {
		return dailyPremium;
	}

	public void setDailyPremium(BigDecimal dailyPremium) {
		this.dailyPremium = dailyPremium;
	}
	
	public Collection<Claim> getClaims() {
		return claims;
	}

	public void setClaims(Collection<Claim> claims) {
		this.claims = claims;
	}
	
	public void addClaim(Claim claim) {
		this.claims.add(claim);
	}
	
	public void removeClaim(Claim claim) {
		this.claims.remove(claim);
	}	

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "";
//		return "Policy [policyId=" + policyId + ", version=" + version + ", car=" + car + ", contract=" + contract
//				+ ", dailyPremium=" + dailyPremium + "]";
	}



}

