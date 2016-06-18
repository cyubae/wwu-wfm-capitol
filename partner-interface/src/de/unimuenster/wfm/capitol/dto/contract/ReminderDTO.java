package de.unimuenster.wfm.capitol.dto.contract;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReminderDTO {
	private String process_id;
	private Order order;
	
	public String getProcess_id() {
		return process_id;
	}

	public void setProcess_id(String process_id) {
		this.process_id = process_id;
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
	}

}
