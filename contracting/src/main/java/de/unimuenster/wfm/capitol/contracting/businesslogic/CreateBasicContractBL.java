package de.unimuenster.wfm.capitol.contracting.businesslogic;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.entities.Car;
import de.unimuenster.wfm.capitol.entities.CarType;
import de.unimuenster.wfm.capitol.entities.Contract;
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

	public void performBusinessLogic(DelegateExecution delegateExecution) {

		//for each car in process variables --> create car object --> create policy object


		//create one contract object for all policy objects created here
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
	
	private Policy createPolicy(DelegateExecution delegateExecution) {
		ArrayList<Car> cars = createCars(delegateExecution);
	}
	
	

	private Car createCar() {

	}	

}