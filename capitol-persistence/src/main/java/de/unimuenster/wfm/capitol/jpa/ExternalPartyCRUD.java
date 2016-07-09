package de.unimuenster.wfm.capitol.jpa;

import javax.ejb.Stateless;
import javax.inject.Named;

import de.unimuenster.wfm.capitol.entities.ExternalParty;

/**
 * DAO for entity Contract
 *
 */
@Stateless
@Named
public class ExternalPartyCRUD extends AbstractCRUDService<ExternalParty> {
	
    public ExternalPartyCRUD() {
        super(ExternalParty.class);
    }

}
