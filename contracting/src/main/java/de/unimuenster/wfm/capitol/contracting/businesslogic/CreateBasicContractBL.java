package de.unimuenster.wfm.capitol.contracting.businesslogic;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.contracting.helper.PremiumCalculator;
import de.unimuenster.wfm.capitol.entities.Car;
import de.unimuenster.wfm.capitol.entities.CarType;
import de.unimuenster.wfm.capitol.entities.Contract;
import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.entities.InsuranceType;
import de.unimuenster.wfm.capitol.entities.Policy;
import de.unimuenster.wfm.capitol.jpa.AbstractCRUDService;

@Stateless
@Named
public class CreateBasicContractBL {

	// Inject the AbstractCRUDService to access persistence unit
	@Inject
	private AbstractCRUDService<Contract> contractCRUD;

	@Inject
	private AbstractCRUDService<Policy> policyCRUD;
	
	@Inject
	private AbstractCRUDService<Car> carCRUD;
	
	@Inject
	private AbstractCRUDService<Customer> customerCRUD;	
	
	
	private static final HashMap<String, CarType> STRING_TO_CARTYPE;
	static {
		STRING_TO_CARTYPE = new HashMap<>();
		STRING_TO_CARTYPE.put("mini_car", CarType.MINI_CAR);
		STRING_TO_CARTYPE.put("small_car", CarType.SMALL_CAR);
		STRING_TO_CARTYPE.put("car", CarType.CAR);
		STRING_TO_CARTYPE.put("kombi", CarType.KOMBI);
		STRING_TO_CARTYPE.put("pickup", CarType.KOMBI);
		STRING_TO_CARTYPE.put("van", CarType.VAN);
		STRING_TO_CARTYPE.put("limousine", CarType.LIMOUSINE);
		STRING_TO_CARTYPE.put("truck", CarType.TRUCK);
	}
	
	private static final HashMap<String, InsuranceType> STRING_TO_INSURANCETYPE;
	static {
		STRING_TO_INSURANCETYPE = new HashMap<>();
		STRING_TO_INSURANCETYPE.put("total", InsuranceType.TOTAL);
		STRING_TO_INSURANCETYPE.put("partial", InsuranceType.PARTIAL);
		STRING_TO_INSURANCETYPE.put("liability", InsuranceType.LIABILITY);
	}	

	public void performBusinessLogic(DelegateExecution delegateExecution) {
		
		//for each car in process variables --> create car object --> create policy object
		//Create car objects
		ArrayList<Car> cars = createCars(delegateExecution);
		
		//Create policy objects (one per car)
		ArrayList<Policy> policies =  createPolicies(delegateExecution, cars);

		//Create one contract object for all policy objects created here		
		Contract contract = createContract(delegateExecution, policies);
	}





	private ArrayList<Car> createCars(DelegateExecution delegateExecution) {
		int totalNumberOfCars = (Integer) delegateExecution.getVariable("cars_total_number");
		ArrayList<Car> cars = new ArrayList<>();
		
		for(int i = 1; i <= totalNumberOfCars; i++) {
			Car newCar = new Car();
			newCar.setRegistration_number((String) delegateExecution.getVariable("car_registration_number"+i));
			newCar.setBrand((String) delegateExecution.getVariable("car_brand"+i));
			newCar.setType(STRING_TO_CARTYPE.get((String) delegateExecution.getVariable("car_type"+i)));
			newCar.setModel((String) delegateExecution.getVariable("car_model"+i));
			newCar.setVehicle_identification_number((String) delegateExecution.getVariable("car_vehicle_identification_number"+i));
			newCar.setFuel_type((String) delegateExecution.getVariable("car_fuel_type"+i));
			newCar.setPs(Integer.valueOf((String) delegateExecution.getVariable("car_ps"+i)));
			newCar.setConstruction_year(Integer.valueOf((String) delegateExecution.getVariable("car_construction_year"+i)));
			
			newCar = carCRUD.create(newCar);
			
			cars.add(newCar);
		}
		return cars;
	}
	
	private ArrayList<Policy> createPolicies(DelegateExecution delegateExecution, ArrayList<Car> cars) {
		ArrayList<Policy> policies = new ArrayList<>();
		
		for(Car currentCar : cars) {
			
			CarType currentCarType = currentCar.getType();
			InsuranceType currentInsuranceType = STRING_TO_INSURANCETYPE.get((String) delegateExecution.getVariable("insurance_type"));
			int horsePower = currentCar.getPs();
			int yearOfConstruction = currentCar.getConstruction_year();
			
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
		// TODO Auto-generated method stub
		
		Date pickUpDate = (String) delegateExecution.getVariable("insurance_pick_up_date");
		Date returnDate = (String) delegateExecution.getVariable("insurance_return_date");
		InsuranceType currentInsuranceType = STRING_TO_INSURANCETYPE.get((String) delegateExecution.getVariable("insurance_type"));
		Customer currentCustomer = customerCRUD.find((Integer) (delegateExecution.getVariable("customerId")));
		
		Contract newContract = new Contract();
		newContract.setPolicies(policies);
		newContract.setInsuranceType(currentInsuranceType);
		newContract.setCustomer(currentCustomer);
		newContract.setPickUpDate(pickUpDate);
		newContract.setReturnDate(returnDate);
		newContract.setReleased(false);
		
		newContract = contractCRUD.create(newContract);
				
		return newContract;
	}
	

}