package de.unimuenster.wfm.capitol.dto.contract;

import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContractDetailsDTO {
	private String processinstance_id_bvis;
	private Order order;

	public String getProcessinstance_id_bvis() {
		return processinstance_id_bvis;
	}

	public void setProcessinstance_id_bvis(String processinstance_id_bvis) {
		this.processinstance_id_bvis = processinstance_id_bvis;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public class Order {
		private int order_id;
		private Date request_date;
		private boolean fleet_rental;
		private String inquiry_text;
		private User user;
		private Collection<Car> car;
		private Insurance insurance;
		
		public int getOrder_id() {
			return order_id;
		}

		public void setOrder_id(int order_id) {
			this.order_id = order_id;
		}

		public Date getRequest_date() {
			return request_date;
		}

		public void setRequest_date(Date request_date) {
			this.request_date = request_date;
		}

		public boolean isFleet_rental() {
			return fleet_rental;
		}

		public void setFleet_rental(boolean fleet_rental) {
			this.fleet_rental = fleet_rental;
		}

		public String getInquiry_text() {
			return inquiry_text;
		}

		public void setInquiry_text(String inquiry_text) {
			this.inquiry_text = inquiry_text;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Collection<Car> getCar() {
			return car;
		}

		public void setCar(Collection<Car> car) {
			this.car = car;
		}

		public Insurance getInsurance() {
			return insurance;
		}

		public void setInsurance(Insurance insurance) {
			this.insurance = insurance;
		}
		
		@XmlAccessorType(XmlAccessType.FIELD)
		public class User {
			private String firstname;
			private String surname;
			private String email;
			private String phone_number;
			private String street;
			private String house_number;
			private String postcode;
			private String city;
			private String country;
			private Date date_of_birth;
			private String company;
			private String company_name;
			
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
			public Date getDate_of_birth() {
				return date_of_birth;
			}
			public void setDate_of_birth(Date date_of_birth) {
				this.date_of_birth = date_of_birth;
			}
			public String isCompany() {
				return company;
			}
			public void setCompany(boolean company) {
				if (company) {
					this.company = "true";					
				}
				else {
					this.company = "false";
				}
			}
			public void setCompany(String company) {
				if (company.equals("true")) {
					this.company = "true";					
				}
				else {
					this.company = "false";
				}
			}
			public String getCompany_name() {
				return company_name;
			}
			public void setCompany_name(String company_name) {
				this.company_name = company_name;
			}
		}
		
		@XmlAccessorType(XmlAccessType.FIELD)
		public class Insurance {
			private int insurance_id;
			private String type;
			private double deductible;
			private Date pick_up_date;
			private Date return_date;
			private double estimated_of_cost;
			
			public int getInsurance_id() {
				return insurance_id;
			}
			public void setInsurance_id(int insurance_id) {
				this.insurance_id = insurance_id;
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			public double getDeductible() {
				return deductible;
			}
			public void setDeductible(double deductible) {
				this.deductible = deductible;
			}
			public Date getPick_up_date() {
				return pick_up_date;
			}
			public void setPick_up_date(Date pick_up_date) {
				this.pick_up_date = pick_up_date;
			}
			public Date getReturn_date() {
				return return_date;
			}
			public void setReturn_date(Date return_date) {
				this.return_date = return_date;
			}
			public double getEstimated_of_cost() {
				return estimated_of_cost;
			}
			public void setEstimated_of_cost(double estimated_of_cost) {
				this.estimated_of_cost = estimated_of_cost;
			}
		}

	}

}
