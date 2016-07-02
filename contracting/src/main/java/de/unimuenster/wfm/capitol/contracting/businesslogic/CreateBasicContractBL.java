package de.unimuenster.wfm.capitol.contracting.businesslogic;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.contracting.helper.DateConverter;
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
	
	private static Logger LOGGER = Logger.getLogger(CheckExistingCustomerIdBL.class.getName());

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

		//for each car in process variables --> create car object --> create policy object
		//Create car objects
		LOGGER.log(Level.INFO, "CONTRACT CREATION - STEP 1");
		ArrayList<Car> cars = createCars(delegateExecution);

		//Create policy objects (one per car)
		LOGGER.log(Level.INFO, "CONTRACT CREATION - STEP 2");
		ArrayList<Policy> policies =  createPolicies(delegateExecution, cars);

		//Create one contract object for all policy objects created here
		LOGGER.log(Level.INFO, "CONTRACT CREATION - STEP 3");
		Contract contract = createContract(delegateExecution, policies);
		
		LOGGER.log(Level.INFO, "CONTRACT CREATION - STEP 4");
		delegateExecution.setVariable("contract_id", contract.getContractId());
		
		LOGGER.log(Level.INFO, "CONTRACT CREATION - FINISHED");
	}

	private ArrayList<Car> createCars(DelegateExecution delegateExecution) {
		LOGGER.log(Level.INFO, "CAR CREATION - STEP 0a");
		int totalNumberOfCars = (Integer) delegateExecution.getVariable("cars_total_number");
		LOGGER.log(Level.INFO, "CAR CREATION - STEP 0b");
		ArrayList<Car> cars = new ArrayList<Car>();

		for(int i = 1; i <= totalNumberOfCars; i++) {
			int logVar = 1;
			LOGGER.log(Level.INFO, "CAR CREATION - STEP " + logVar++);

			Car newCar = new Car();
			LOGGER.log(Level.INFO, "CAR CREATION - STEP " + logVar++);
			newCar.setRegistrationNumber((String) delegateExecution.getVariable("car_registration_number"+i));
			LOGGER.log(Level.INFO, "CAR CREATION - STEP " + logVar++);
			newCar.setBrand((String) delegateExecution.getVariable("car_brand"+i));
			LOGGER.log(Level.INFO, "CAR CREATION - STEP " + logVar++);
			newCar.setType(STRING_TO_CARTYPE.get((String) delegateExecution.getVariable("car_type"+i)));
			LOGGER.log(Level.INFO, "CAR CREATION - STEP " + logVar++);
			newCar.setModel((String) delegateExecution.getVariable("car_model"+i));
			LOGGER.log(Level.INFO, "CAR CREATION - STEP " + logVar++);
			newCar.setVehicleIdentificationNumber((String) delegateExecution.getVariable("car_vehicle_identification_number"+i));
			LOGGER.log(Level.INFO, "CAR CREATION - STEP " + logVar++);
			newCar.setFuelType((String) delegateExecution.getVariable("car_fuel_type"+i));
			LOGGER.log(Level.INFO, "CAR CREATION - STEP " + logVar++);
			newCar.setPs((Integer) delegateExecution.getVariable("car_ps"+i));
			LOGGER.log(Level.INFO, "CAR CREATION - STEP " + logVar++);
			newCar.setPs((Integer) delegateExecution.getVariable("car_construction_year"+i));
			LOGGER.log(Level.INFO, "CAR CREATION - STEP " + logVar++);

			newCar = carCRUD.create(newCar);
			LOGGER.log(Level.INFO, "CAR CREATION - STEP " + logVar++);

			cars.add(newCar);
			LOGGER.log(Level.INFO, "CAR CREATION - STEP " + logVar++);
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

			int dailyPremium = PremiumCalculator.getDailyPremium(currentCarType, currentInsuranceType, horsePower, yearOfConstruction);

			Policy newPolicy = new Policy();
			newPolicy.setCar(currentCar);
			newPolicy.setDailyPremium(dailyPremium);

			newPolicy = policyCRUD.create(newPolicy);

			policies.add(newPolicy);

		}

		return policies;
	}

	private Contract createContract(DelegateExecution delegateExecution, ArrayList<Policy> policies) {

		InsuranceType currentInsuranceType = STRING_TO_INSURANCETYPE.get((String) delegateExecution.getVariable("insurance_type"));
			Customer currentCustomer = customerCRUD.find((Integer) (delegateExecution.getVariable("customerId")));

			Contract newContract = new Contract();
			newContract.setPolicies(policies);
			newContract.setInsuranceType(currentInsuranceType);
			newContract.setCustomer(currentCustomer);
			newContract.setValidated(false);;
			newContract.setReleased(false);
			
			//parse dates			
			try {
				Date pickUpDate = DateConverter.convertStringToDate((String) delegateExecution.getVariable("insurance_pick_up_date"));
				Date returnDate = DateConverter.convertStringToDate((String) delegateExecution.getVariable("insurance_return_date"));

				newContract.setPickUpDate(pickUpDate);
				newContract.setReturnDate(returnDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			newContract = contractCRUD.create(newContract);

			return newContract;
	}


}