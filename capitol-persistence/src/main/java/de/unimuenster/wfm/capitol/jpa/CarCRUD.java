package de.unimuenster.wfm.capitol.jpa;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import de.unimuenster.wfm.capitol.entities.Car;
import de.unimuenster.wfm.capitol.entities.Customer;

/**
 * DAO for entity Car
 *
 */
@Stateless
@Named
public class CarCRUD extends AbstractCRUDService<Car> {
	
	private static Logger LOGGER = Logger.getLogger(CarCRUD.class.getName());	
	
    public CarCRUD() {
        super(Car.class);
    }
    
	/**
	 * Returns car if available for given vehicleIdentificationNumber
	 * Else returns null
	 */
	public Car findCarByVehicleId(String vehicleIdentificationNumber) {

		LOGGER.log(Level.INFO, "entityManager.toString(): " + entityManager.toString());
		LOGGER.log(Level.INFO, "Prepare query string");				
		String query = "Select c FROM Car c WHERE"
				+ " c.vehicleIdentificationNumber = '" + vehicleIdentificationNumber + "'";

		LOGGER.log(Level.INFO, "Query string: " + query);
		TypedQuery<Car> typedQuery = entityManager.createQuery(query, Car.class);
		try {
			Car car = typedQuery.getSingleResult();
			
			LOGGER.log(Level.INFO, "Found car");
			return car;
		}
		catch(Exception e) {
			LOGGER.log(Level.INFO, "Found no car");
			return null;
		}
	}    

}
