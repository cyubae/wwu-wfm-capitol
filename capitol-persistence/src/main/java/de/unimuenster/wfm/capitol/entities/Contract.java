package de.unimuenster.wfm.capitol.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.camunda.bpm.engine.cdi.annotation.BusinessProcessScoped;

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
	
	protected Customer customer;
	protected Collection<Policy> policies;
	protected InsuranceType insuranceType;
	protected Date pickUpDate;
	protected Date returnDate;

	public Contract() {

	}

	@Override
	public String toString(){
		return "";
	}
	
	public int getTotalPremium() {
		//getTotalDailyPremium *(returnDate - pickUpDate)
		return 0;
	}
	
	/**
	 * Returns sum of daily premium of all policies
	 * @return
	 */
	public int getTotalDailyPremium() {
		return 0;
	}



}

