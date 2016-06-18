package de.unimuenster.wfm.capitol.dto.claim;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Feedback {
	
	private int process_id;
	private Decision decision; 
	
	public int getProcess_id() {
		return process_id;
	}

	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}



	class Decision{
		private int claim_id;
		private int claim_status;
		private String description;
		public int getClaim_id() {
			return claim_id;
		}
		public void setClaim_id(int claim_id) {
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
	
	

}
