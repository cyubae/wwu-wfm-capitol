package de.unimuenster.wfm.capitol.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClaimFeedback", propOrder = {
    "process_id",
    "claim_id",
    "claim_status",
    "description"
})
public class ClaimFeedback {
    @XmlElement(required = true)
	private String process_id;
    @XmlElement(required = true)
	private String claim_id;
    @XmlElement(required = true)
	private int claim_status;
    @XmlElement(required = true)
	private String description;
	public String getProcess_id() {
		return process_id;
	}
	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}
	public String getClaim_id() {
		return claim_id;
	}
	public void setClaim_id(String claim_id) {
		this.claim_id = claim_id;
	}
	public int getClaim_status() {
		return claim_status;
	}
	public void setClaim_status(int claim_status) {
		this.claim_status = claim_status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
   
    
}
