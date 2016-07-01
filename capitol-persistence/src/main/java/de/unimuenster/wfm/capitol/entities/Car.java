package de.unimuenster.wfm.capitol.entities;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.camunda.bpm.engine.cdi.annotation.BusinessProcessScoped;

@Entity
@BusinessProcessScoped
public class Car implements Serializable {
	
	private static  final long serialVersionUID = 1L;

	private static Logger LOGGER = Logger.getLogger(Contract.class.getName());

	@Id
	@GeneratedValue
	protected int contractId;

	@Version
	protected long version;
	
	private String registration_number;
	private String brand;
	private String type;
	private String model;
	private String vehicle_identification_number;
	private String fuel_type;
	private int ps;
	private int construction_year;
	
	public Car(){
		super();
	}
	
	/**
	 * @return the contractId
	 */
	public int getContractId() {
		return contractId;
	}

	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return the version
	 */
	public long getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(long version) {
		this.version = version;
	}

	public String getRegistration_number() {
		return registration_number;
	}
	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVehicle_identification_number() {
		return vehicle_identification_number;
	}
	public void setVehicle_identification_number(String vehicle_identification_number) {
		this.vehicle_identification_number = vehicle_identification_number;
	}
	public String getFuel_type() {
		return fuel_type;
	}
	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public int getConstruction_year() {
		return construction_year;
	}
	public void setConstruction_year(int construction_year) {
		this.construction_year = construction_year;
	}
}