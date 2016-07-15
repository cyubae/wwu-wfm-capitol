package de.unimuenster.wfm.capitol.contracting.helper;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;

import de.unimuenster.wfm.capitol.enums.CarType;
import de.unimuenster.wfm.capitol.enums.InsuranceType;

@Stateless
@Named
public class PremiumCalculator {
	
	public static void main(String[] args) {
		
	}
	
	private static Logger LOGGER = Logger.getLogger(PremiumCalculator.class.getName());

	private static double PS_FACTOR = 0.15;

	public static BigDecimal getDailyPremium(CarType carType, InsuranceType insuranceType, int horsePower, int yearOfConstruction) {
		
		LOGGER.log(Level.INFO, "invoked getDailyPremium: " + carType + insuranceType + horsePower + yearOfConstruction);

		double carCostFactor = getCarCostFactor(carType);
		LOGGER.log(Level.INFO, "carCostFactor: " + carCostFactor);
		int dailyInsurancePremium = getDailyInsuranceTypePremium(insuranceType);
		LOGGER.log(Level.INFO, "dailyInsurancePremium: " + dailyInsurancePremium);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		LOGGER.log(Level.INFO, "currentYear: " + currentYear);

		//"type": "partial" * "type": "truck" + "ps": 102 * 0,15 + 20-1,2ˆ(actual year- "construction_year")
		int dailyPremiumCents = (int) Math.round(
				dailyInsurancePremium * carCostFactor 
				+ horsePower * PS_FACTOR 
				+ (20 - Math.pow(1.2, currentYear-yearOfConstruction)/30)
				);
		LOGGER.log(Level.INFO, "dailyPremium: " + dailyPremiumCents);
		
		//format cents to BigDecimal "Euros,Cents"
		BigDecimal dailyPremiumEuros = new BigDecimal(dailyPremiumCents).divide(new BigDecimal(100), MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_EVEN);

		return dailyPremiumEuros;
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
}