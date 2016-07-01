package de.unimuenster.wfm.capitol.jpa;

import javax.ejb.Stateless;
import javax.inject.Named;

import de.unimuenster.wfm.capitol.entities.Car;

/**
 * DAO for entity Car
 *
 */
@Stateless
@Named
public class CarCRUD extends AbstractCRUDService<Car> {
	
    public CarCRUD() {
        super(Car.class);
    }

}
