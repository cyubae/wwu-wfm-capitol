package de.unimuenster.wfm.capitol.dto.claim;

import java.util.Calendar;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import de.unimuenster.wfm.capitol.dto.claim.Involved_party;

@XmlAccessorType(XmlAccessType.FIELD)
public class ClaimDetailsDTO {

	private String processinstance_id_bvis;
	private Calendar request_date;
	private Claim claim;

	

	public String getProcessinstance_id_bvis() {
		return processinstance_id_bvis;
	}

	public void setProcessinstance_id_bvis(String processinstance_id_bvis) {
		this.processinstance_id_bvis = processinstance_id_bvis;
	}

	public Calendar getRequest_date() {
		return request_date;
	}

	public void setRequest_date(Calendar request_date) {
		this.request_date = request_date;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public class Claim {
		private int claim_id;
		private int insurance_id;
		private String vehicle_identification_number;
		private int order_id;
		private Calendar damage_date;
		private String damage_address;
		private String claim_description;
		private double workshop_price;
		private boolean parties_involved;
		@XmlElement(name = "involved_parties")
		private Collection<Involved_party> involvedParties;

		public int getClaim_id() {
			return claim_id;
		}

		public void setClaim_id(int claim_id) {
			this.claim_id = claim_id;
		}

		public int getInsurance_id() {
			return insurance_id;
		}

		public void setInsurance_id(int insurance_id) {
			this.insurance_id = insurance_id;
		}

		public String getVehicle_identification_number() {
			return vehicle_identification_number;
		}

		public void setVehicle_identification_number(String vehicle_identification_number) {
			this.vehicle_identification_number = vehicle_identification_number;
		}

		public int getOrder_id() {
			return order_id;
		}

		public void setOrder_id(int order_id) {
			this.order_id = order_id;
		}

		public Calendar getDamage_date() {
			return damage_date;
		}

		public void setDamage_date(Calendar damage_date) {
			this.damage_date = damage_date;
		}

		public String getDamage_address() {
			return damage_address;
		}

		public void setDamage_address(String damage_address) {
			this.damage_address = damage_address;
		}

		public String getClaim_description() {
			return claim_description;
		}

		public void setClaim_description(String claim_description) {
			this.claim_description = claim_description;
		}

		public double getWorkshop_price() {
			return workshop_price;
		}

		public void setWorkshop_price(double workshop_price) {
			this.workshop_price = workshop_price;
		}

		public boolean isParties_involved() {
			return parties_involved;
		}

		public void setParties_involved(boolean parties_involved) {
			this.parties_involved = parties_involved;
		}

		public Collection<Involved_party> getInvolvedParties() {
			return involvedParties;
		}

		public void setInvolvedParties(Collection<Involved_party> involvedParties) {
			this.involvedParties = involvedParties;
		}
	}

	

}
