package de.unimuenster.wfm.capitol.dto;

import java.io.Serializable;
import java.util.Date;

public class ContractProposal implements Serializable{
	

	private static final long serialVersionUID = -6826615438362854220L;
	
	private String processinstance_id_bvis;
	private String processinstance_id_capitol;
	private Order order;

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


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public class Order{
	    private int order_id;
	    private Date request_date;
	    private String inquiry_text;
	    private int result;
	    private double final_price;
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
		public String getInquiry_text() {
			return inquiry_text;
		}
		public void setInquiry_text(String inquiry_text) {
			this.inquiry_text = inquiry_text;
		}
		public int getResult() {
			return result;
		}
		public void setResult(int result) {
			this.result = result;
		}
		public double getFinal_price() {
			return final_price;
		}
		public void setFinal_price(double final_price) {
			this.final_price = final_price;
		}
	}
}
