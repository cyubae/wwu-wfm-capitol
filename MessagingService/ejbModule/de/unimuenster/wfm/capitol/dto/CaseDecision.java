package de.unimuenster.wfm.capitol.dto;

import java.io.Serializable;

public class CaseDecision implements Serializable{

	private static final long serialVersionUID = -3245066618598216266L;
	
	private String processinstance_id_bvis;
	private String processinstance_id_capitol;
	private Decision decision;

	public String getProcessinstance_id_bvis() {
		return processinstance_id_bvis;
	}


	public void setProcessinstance_id_bvis(String processinstance_id_bvis) {
		this.processinstance_id_bvis = processinstance_id_bvis;
	}


	public String getProcessinstance_id_capitol() {
		return processinstance_id_capitol;
	}


	public void setProcessinstance_id_capitol(String processinstance_id_capitol) {
		this.processinstance_id_capitol = processinstance_id_capitol;
	}


	public Decision getDecision() {
		return decision;
	}


	public void setDecision(Decision decision) {
		this.decision = decision;
	}



	public class Decision implements Serializable{
		
		private static final long serialVersionUID = 1852574274546178491L;
		private int claim_id;
        private double coverage_costs;
        private double customer_costs;
        private int insurance_decision;
        private String description;
		public int getClaim_id() {
			return claim_id;
		}
		public void setClaim_id(int claim_id) {
			this.claim_id = claim_id;
		}
		public double getCoverage_costs() {
			return coverage_costs;
		}
		public void setCoverage_costs(double d) {
			this.coverage_costs = d;
		}
		public double getCustomer_costs() {
			return customer_costs;
		}
		public void setCustomer_costs(double customer_costs) {
			this.customer_costs = customer_costs;
		}
		public int getInsurance_decision() {
			return insurance_decision;
		}
		public void setInsurance_decision(int insurance_decision) {
			this.insurance_decision = insurance_decision;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
        
        
	}
}
