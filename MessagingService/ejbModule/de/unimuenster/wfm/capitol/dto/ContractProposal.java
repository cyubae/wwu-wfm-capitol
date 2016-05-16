package de.unimuenster.wfm.capitol.dto;

import java.io.Serializable;

public class ContractProposal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6826615438362854220L;
	String name;
	String firm;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirm() {
		return firm;
	}
	public void setFirm(String firm) {
		this.firm = firm;
	}
	
	

}
