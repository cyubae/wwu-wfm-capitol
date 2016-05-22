package de.unimuenster.wfm.capitol.dto;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LiabilityCase", propOrder = {
    "id",
    "name",
    "description",
    "sum"
})
public class LiabilityCase implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1312193785526043742L;
	protected String id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String description;
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	@XmlElement(required = true)
    protected int sum;


}