package de.unimuenster.wfm.capitol.jpa;

import javax.ejb.Stateless;
import javax.inject.Named;

import de.unimuenster.wfm.capitol.entities.Policy;

/**
 * DAO for entity Policy
 *
 */
@Stateless
@Named
public class PolicyCRUD extends AbstractCRUDService<Policy> {
	
    public PolicyCRUD() {
        super(Policy.class);
    }

}
