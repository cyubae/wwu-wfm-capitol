package de.unimuenster.wfm.capitol.contracting.helper;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.entities.CarType;
import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.entities.InsuranceType;
import de.unimuenster.wfm.capitol.jpa.AccessCustomer;

@Stateless
@Named
public class PremiumCalculator {

	// Inject the AccessCustomer to access the persisted customer
	@Inject
	private AccessCustomer accessCustomer;

	public static int getDailyPremium(CarType carType, InsuranceType insuranceType, int horsePower, int yearOfConstruction) {

		//"type": "partial" * "type": "truck" + "ps": 102 * 0,15 + 20-1,2ˆ(actual year- "construction_year")
		double carCostFactor = getCarCostFactor(carType);
		int dailyInsurancePremium = getDailyInsuranceTypePremium(insuranceType);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		
		int dailyPremium = dailyInsurancePremium * carCostFactor + horsePower * 0.15 + 20 - 1.2 * (currentYear-yearOfConstruction);

		return dailyPremium;
	}
	
	/**
	 * Returns cost factor for given car type
	 * @param insuranceType
	 * @return
	 */
	private static double getCarCostFactor(CarType carType) {
		double carCostFactor;

		switch(carType) {
		case MINI_CAR:
			carCostFactor = 0.85;
			break;
		case SMALL_CAR:
			carCostFactor = 1;
			break;			
		case CAR:
			carCostFactor = 1.2;
			break;
		case KOMBI:
			carCostFactor = 1.4;
			break;
		case PICK_UP:
			carCostFactor = 1.65;
			break;
		case VAN:
			carCostFactor = 1.8;
			break;
		case LIMOUSINE:
			carCostFactor = 2.25;
			break;			
		case TRUCK:
			carCostFactor = 2.5;
			break;			
		default: 
			throw new IllegalArgumentException("No valid CarType!");
		}
		
		return carCostFactor;
	}	

	/**
	 * Returns Premium/day for given insurance type in euro cents
	 * @param insuranceType
	 * @return
	 */
	private static int getDailyInsuranceTypePremium(InsuranceType insuranceType) {
		int dailyInsurancePremium;

		switch(insuranceType) {
		case TOTAL:
			dailyInsurancePremium = 220;
			break;
		case PARTIAL:
			dailyInsurancePremium = 110;
			break;			
		case LIABILITY:
			dailyInsurancePremium = 70;
			break;			
		default: 
			throw new IllegalArgumentException("No valid InsuranceType!");
		}
		
		return dailyInsurancePremium;
	}

	public void performBusinessLogic(DelegateExecution delegateExecution) {
		//get Customer object
		Customer customer = accessCustomer.getCustomer((Integer) (delegateExecution.getVariable("customerId")));

		//get blacklist attribute
		if(customer.isBlacklisted()) {
			delegateExecution.setVariable("user_is_blacklisted", true);
		}
		else {
			delegateExecution.setVariable("user_is_blacklisted", false);
		}
	}


}