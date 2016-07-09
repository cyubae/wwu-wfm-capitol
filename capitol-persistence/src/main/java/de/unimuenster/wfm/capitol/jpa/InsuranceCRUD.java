package de.unimuenster.wfm.capitol.jpa;

import javax.ejb.Stateless;
import javax.inject.Named;

import de.unimuenster.wfm.capitol.entities.Insurance;

/**
 * DAO for entity Contract
 *
 */
@Stateless
@Named
public class InsuranceCRUD extends AbstractCRUDService<Insurance> {
	
    public InsuranceCRUD() {
        super(Insurance.class);
    }

}
