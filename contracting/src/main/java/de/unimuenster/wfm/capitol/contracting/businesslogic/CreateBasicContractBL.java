package de.unimuenster.wfm.capitol.contracting.businesslogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.contracting.helper.EnumMapper;
import de.unimuenster.wfm.capitol.contracting.helper.PremiumCalculator;
import de.unimuenster.wfm.capitol.entities.Car;
import de.unimuenster.wfm.capitol.entities.Contract;
import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.entities.Policy;
import de.unimuenster.wfm.capitol.enums.CarType;
import de.unimuenster.wfm.capitol.enums.InsuranceType;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;

@Stateless
@Named
public class CreateBasicContractBL {
	
	private static Logger LOGGER = Logger.getLogger(CreateBasicContractBL.class.getName());

	// Inject CRUD-Services to access persistence unit	
	@Inject
	private ContractCRUD contractCRUD;

	@Inject
	private PolicyCRUD policyCRUD;

	@Inject
	private CarCRUD carCRUD;

	@Inject
	private CustomerCRUD customerCRUD;	

	private final HashMap<String, CarType> STRING_TO_CARTYPE = EnumMapper.STRING_TO_CARTYPE;

	private final HashMap<String, InsuranceType> STRING_TO_INSURANCETYPE = EnumMapper.STRING_TO_INSURANCETYPE;

	public void performBusinessLogic(DelegateExecution delegateExecution) {
		
		//http://stackoverflow.com/questions/18373383/jpa-onetoone-difference-between-cascade-merge-and-persist

		//for each car in process variables --> create car object --> store into and create one policy object 
		//--> store all policy objects into one contract object (associate each contract object with exactly one Customer object) 
		//Create car objects
		ArrayList<Car> cars = createCars(delegateExecution);

		//Create policy objects (one per car)
		ArrayList<Policy> policies =  createPolicies(delegateExecution, cars);

		//Create one contract object for all policy objects created here
		Contract contract = createContract(delegateExecution, policies);
		
		delegateExecution.setVariable("contract_id", contract.getContractId());
		LOGGER.log(Level.INFO, "CONTRACT CREATION - FINISHED - contractId: " + contract.getContractId());
	}

	private ArrayList<Car> createCars(DelegateExecution delegateExecution) {

		int totalNumberOfCars = (Integer) delegateExecution.getVariable("cars_total_number");
		ArrayList<Car> cars = new ArrayList<Car>();

		for(int i = 1; i <= totalNumberOfCars; i++) {
			Car newCar = new Car();
			newCar.setRegistrationNumber((String) delegateExecution.getVariable("car_registration_number"+i));
			newCar.setBrand((String) delegateExecution.getVariable("car_brand"+i));
			newCar.setType(STRING_TO_CARTYPE.get((String) delegateExecution.getVariable("car_type"+i)));
			newCar.setModel((String) delegateExecution.getVariable("car_model"+i));
			newCar.setVehicleIdentificationNumber((String) delegateExecution.getVariable("car_vehicle_identification_number"+i));
			newCar.setFuelType((String) delegateExecution.getVariable("car_fuel_type"+i));
			newCar.setPs((Integer) delegateExecution.getVariable("car_ps"+i));
			newCar.setConstructionYear((Integer) delegateExecution.getVariable("car_construction_year"+i));
			
			newCar = carCRUD.createAndFlush(newCar);
			LOGGER.log(Level.INFO, "NEW CAR PERSISTED: newCar.toString(): " + newCar.toString());
			
			cars.add(newCar);
		}
		return cars;
	}

	private ArrayList<Policy> createPolicies(DelegateExecution delegateExecution, ArrayList<Car> cars) {
		ArrayList<Policy> policies = new ArrayList<Policy>();

		for(Car currentCar : cars) {

			CarType currentCarType = currentCar.getType();
			InsuranceType currentInsuranceType = STRING_TO_INSURANCETYPE.get((String) delegateExecution.getVariable("insurance_type"));
			int horsePower = currentCar.getPs();
			int yearOfConstruction = currentCar.getConstructionYear();

			BigDecimal dailyPremium = PremiumCalculator.getDailyPremium(currentCarType, currentInsuranceType, horsePower, yearOfConstruction);

			Policy newPolicy = new Policy();
			newPolicy.setDailyPremium(dailyPremium);

			newPolicy.setCar(currentCar);

			newPolicy = policyCRUD.createAndFlush(newPolicy);
			
			LOGGER.log(Level.INFO, "NEW POLICY PERSISTED: newPolicy.toString(): " + newPolicy.toString());
			
			policies.add(newPolicy);

		}

		return policies;
	}

	private Contract createContract(DelegateExecution delegateExecution, ArrayList<Policy> policies) {

		InsuranceType currentInsuranceType = STRING_TO_INSURANCETYPE.get((String) delegateExecution.getVariable("insurance_type"));
			Customer currentCustomer = customerCRUD.find((Integer) (delegateExecution.getVariable("customerId")));

			Contract newContract = new Contract();
			newContract.setInsuranceId((Integer) delegateExecution.getVariable("insurance_insurance_id"));
			newContract.setInsuranceType(currentInsuranceType);
			newContract.setValidated(false);;
			newContract.setReleased(false);
			
			Date pickUpDate = (Date) delegateExecution.getVariable("insurance_pick_up_date");
			Date returnDate = (Date) delegateExecution.getVariable("insurance_return_date");

			newContract.setPickUpDate(pickUpDate);
			newContract.setReturnDate(returnDate);
			
			LOGGER.log(Level.INFO, "SETTING POLICIES");
			for(Policy policy : policies) {
				policy.setContract(newContract);
//				newContract.addPolicy(policy);
			}
//			newContract.setPolicies(policies);
			LOGGER.log(Level.INFO, "POLICIES SET");
			
			newContract.setCustomer(currentCustomer);
			LOGGER.log(Level.INFO, "CUSTOMER SET");
			
			newContract = contractCRUD.createAndFlush(newContract);

//			LOGGER.log(Level.INFO, "NEW CONTRACT PERSISTED: newContract.toString(): " + newContract.toString());
//			LOGGER.log(Level.INFO, "NEW CONTRACT HAS CUSTOMER: " + newContract.getCustomer().toString());
//			
//			LOGGER.log(Level.INFO, "NEW CONTRACT HAS POLICIES: ");
//			Collection<Policy> policiesEntity = newContract.getPolicies();
//			int policyCount = 0;
//			for(Policy p : policiesEntity) {
//				LOGGER.log(Level.INFO, "POLICY " + policyCount + ": " + p.toString());
//			}
			
			return newContract;
	}


}