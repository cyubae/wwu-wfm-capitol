package de.unimuenster.wfm.capitol.entities;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@OneToOne(cascade = {DETACH, MERGE, PERSIST, REFRESH}, fetch=FetchType.EAGER, mappedBy="policy")
//    @JoinColumn(name="carId")
	protected Car car;
	
	@ManyToOne(cascade = {DETACH, MERGE, PERSIST, REFRESH}, fetch=FetchType.EAGER)
	protected Contract contract;

	//daily insurance premium (in euro cents)
	protected int dailyPremium;
	

	public Policy() {

	}

	public Policy(Car car, Contract contract, int dailyPremium) {
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

	public int getDailyPremium() {
		return dailyPremium;
	}

	public void setDailyPremium(int dailyPremium) {
		this.dailyPremium = dailyPremium;
	}

	@Override
	public String toString() {
		return "Policy [policyId=" + policyId + ", version=" + version + ", car=" + car + ", contract=" + contract
				+ ", dailyPremium=" + dailyPremium + "]";
	}



}

