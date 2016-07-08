package de.unimuenster.wfm.capitol.entities;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
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

import de.unimuenster.wfm.capitol.enums.InsuranceType;
import de.unimuenster.wfm.capitol.helper.DateTools;

@Entity
@BusinessProcessScoped
public class Claim implements Serializable {

	private static  final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(Claim.class.getName());

	@Id
	@GeneratedValue
	protected int claimId;
	
	@Version
	protected long version;

	protected int externalClaimId;
	
	@ManyToOne(cascade = {DETACH, MERGE, PERSIST, REFRESH}, fetch=FetchType.EAGER)
	protected Policy policy;
	
	protected String vehicleIDNumber;
	protected Date damageDate;	
	protected String damageAddress;
	protected String claimDescription;
	protected boolean partiesInvolved;
	protected boolean accepted;
	
	
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public int getExternalClaimId() {
		return externalClaimId;
	}
	public void setExternalClaimId(int externalClaimId) {
		this.externalClaimId = externalClaimId;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public String getVehicleIDNumber() {
		return vehicleIDNumber;
	}
	public void setVehicleIDNumber(String vehicleIDNumber) {
		this.vehicleIDNumber = vehicleIDNumber;
	}
	public Date getDamageDate() {
		return damageDate;
	}
	public void setDamageDate(Date damageDate) {
		this.damageDate = damageDate;
	}
	public String getDamageAddress() {
		return damageAddress;
	}
	public void setDamageAddress(String damageAddress) {
		this.damageAddress = damageAddress;
	}
	public String getClaimDescription() {
		return claimDescription;
	}
	public void setClaimDescription(String claimDescription) {
		this.claimDescription = claimDescription;
	}
	public boolean isPartiesInvolved() {
		return partiesInvolved;
	}
	public void setPartiesInvolved(boolean partiesInvolved) {
		this.partiesInvolved = partiesInvolved;
	}
	public int getClaimId() {
		return claimId;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

}

