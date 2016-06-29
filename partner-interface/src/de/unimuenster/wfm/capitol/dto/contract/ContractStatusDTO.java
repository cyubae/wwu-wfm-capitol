package de.unimuenster.wfm.capitol.dto.contract;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContractStatusDTO {
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
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public class Order {
		private int order_id;
		private Calendar request_date;
		private int contract_status;
		
		public int getOrder_id() {
			return order_id;
		}
		public void setOrder_id(int order_id) {
			this.order_id = order_id;
		}
		public Calendar getRequest_date() {
			return request_date;
		}
		public void setRequest_date(Calendar request_date) {
			this.request_date = request_date;
		}
		public int getContract_status() {
			return contract_status;
		}
		public void setContract_status(int contract_status) {
			this.contract_status = contract_status;
		}
	}

}
