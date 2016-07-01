package de.unimuenster.wfm.capitol.jpa;

import javax.ejb.Stateless;
import javax.inject.Named;

import de.unimuenster.wfm.capitol.entities.Customer;

/**
 * DAO for entity Customer
 *
 */
@Stateless
@Named
public class CustomerCRUD extends AbstractCRUDService<Customer> {
	
    public CustomerCRUD() {
        super(Customer.class);
    }

}
