package de.unimuenster.wfm.capitol.dto;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderFeedback", propOrder = {
    "process_id",
    "order_id",
    "request_date",
    "contract_status"
})
public class OrderFeedback {
    @XmlElement(required = true)
	private String process_id;
    @XmlElement(required = true)
	private String order_id;
    @XmlElement(required = true)
    private Calendar request_date;
    @XmlElement(required = true)
	private int contract_status;
	public String getProcess_id() {
		return process_id;
	}
	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
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
