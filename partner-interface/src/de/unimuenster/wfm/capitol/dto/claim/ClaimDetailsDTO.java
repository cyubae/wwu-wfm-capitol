package de.unimuenster.wfm.capitol.dto.claim;

import java.util.Calendar;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ClaimDetailsDTO {

	private String process_id;
	private Calendar request_date;
	private Claim claim;
	private Collection<Involved_party> involved_parties;

	public String getProcess_id() {
		return process_id;
	}

	public void setProcess_id(String process_id) {
		this.process_id = process_id;
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

	public Collection<Involved_party> getInvolved_parties() {
		return involved_parties;
	}

	public void setInvolved_parties(Collection<Involved_party> involved_parties) {
		this.involved_parties = involved_parties;
	}

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
	}
	
	public class Involved_party {
		private String firstname;
		private String surname;
		private String email;
		private String phone_number;
		private String street;
		private String house_number;
		private String postcode;
		private String city;
		private String country;
		private Calendar date_of_birth;
		private String company;
		private boolean has_insurance;
		private Insurance insurance;

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone_number() {
			return phone_number;
		}

		public void setPhone_number(String phone_number) {
			this.phone_number = phone_number;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getHouse_number() {
			return house_number;
		}

		public void setHouse_number(String house_number) {
			this.house_number = house_number;
		}

		public String getPostcode() {
			return postcode;
		}

		public void setPostcode(String postcode) {
			this.postcode = postcode;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public Calendar getDate_of_birth() {
			return date_of_birth;
		}

		public void setDate_of_birth(Calendar date_of_birth) {
			this.date_of_birth = date_of_birth;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public boolean isHas_insurance() {
			return has_insurance;
		}

		public void setHas_insurance(boolean has_insurance) {
			this.has_insurance = has_insurance;
		}

		public Insurance getInsurance() {
			return insurance;
		}

		public void setInsurance(Insurance insurance) {
			this.insurance = insurance;
		}

		public class Insurance {
			private String company;
			private String street;
			private String house_number;
			private String postcode;
			private String city;
			private String country;

			public String getCompany() {
				return company;
			}
			public void setCompany(String company) {
				this.company = company;
			}
			public String getStreet() {
				return street;
			}
			public void setStreet(String street) {
				this.street = street;
			}
			public String getHouse_number() {
				return house_number;
			}
			public void setHouse_number(String house_number) {
				this.house_number = house_number;
			}
			public String getPostcode() {
				return postcode;
			}
			public void setPostcode(String postcode) {
				this.postcode = postcode;
			}
			public String getCity() {
				return city;
			}
			public void setCity(String city) {
				this.city = city;
			}
			public String getCountry() {
				return country;
			}
			public void setCountry(String country) {
				this.country = country;
			}
		}
	}
}
