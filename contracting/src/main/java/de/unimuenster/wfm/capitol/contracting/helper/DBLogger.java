package de.unimuenster.wfm.capitol.contracting.helper;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;

import de.unimuenster.wfm.capitol.contracting.businesslogic.CheckExistingCustomerIdBL;
import de.unimuenster.wfm.capitol.entities.Car;
import de.unimuenster.wfm.capitol.entities.Contract;
import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.entities.Policy;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;

@Named
@ConversationScoped
public class DBLogger implements Serializable{

	private static Logger LOGGER = Logger.getLogger(CheckExistingCustomerIdBL.class.getName());

	// the BusinessProcess to access the process variables
	@Inject
	private BusinessProcess businessProcess;

	// Inject CRUD-Services to access persistence unit	
	@Inject
	private ContractCRUD contractCRUD;

	@Inject
	private PolicyCRUD policyCRUD;

	@Inject
	private CarCRUD carCRUD;

	@Inject
	private CustomerCRUD customerCRUD;

	public void printContract(int contractId) {
		
		LOGGER.log(Level.INFO, "-- PRINT CONTRACT STARTED -- contractId: " + contractId);
		
		LOGGER.log(Level.INFO, "-- contractCRUD.toString(): " + contractCRUD.toString());
		
		Contract contract = contractCRUD.find(contractId);
		LOGGER.log(Level.INFO, "contract.toString(): " + contract.toString());
		
		int customerId = contract.getCustomer().getCustomerId();
		LOGGER.log(Level.INFO, "-- FIND CUSTOMER OF CONTRACT -- customerId: " + customerId);
		printCustomer(customerId);
		
		LOGGER.log(Level.INFO, "-- CONTRACT HAS NUMBER OF POLICIES:--" + contract.getPolicies().size());
		LOGGER.log(Level.INFO, "-- ITERATE THROUGH POLICIES OF CONTRACT --");
		int policyCount = 1;
		for (Policy policy : contract.getPolicies()) {
			LOGGER.log(Level.INFO, "-- PRINT POLICY NO: " + policyCount++);
			printPolicy(policy.getPolicyId());
		}				
		LOGGER.log(Level.INFO, "-- PRINT CONTRACT FINISHED --");	
	}

	public void printPolicy(int policyId) {
		LOGGER.log(Level.INFO, "-- PRINT POLICY STARTED -- policyId: " + policyId);
		
		Policy policy = policyCRUD.find(policyId);
		LOGGER.log(Level.INFO, "policy.toString(): " + policy.toString());
		
		int carId = policy.getCar().getCarId();
		LOGGER.log(Level.INFO, "-- CONTRACT HAS CAR with carId: " + carId);
		printCar(carId);
		
		LOGGER.log(Level.INFO, "-- PRINT POLICY FINISHED --");
	}
	
	public void printCar(int carId) {
		LOGGER.log(Level.INFO, "-- PRINT CAR STARTED -- carId: " + carId);
		
		Car car = carCRUD.find(carId);
		LOGGER.log(Level.INFO, "car.toString(): " + car.toString());
		
		LOGGER.log(Level.INFO, "-- PRINT CAR FINISHED --");
	}
	
	public void printCustomer(int customerId) {
		
		LOGGER.log(Level.INFO, "-- PRINT CUSTOMER STARTED -- customerId: " + customerId);
		
		Customer customer = customerCRUD.find(customerId);
		LOGGER.log(Level.INFO, "customer.toString(): " + customer.toString());
				
		LOGGER.log(Level.INFO, "-- PRINT CUSTOMER FINISHED --");		
		
	}
	
}
