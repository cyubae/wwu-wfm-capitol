package de.unimuenster.wfm.capitol.jpa;

import javax.ejb.Stateless;
import javax.inject.Named;

import de.unimuenster.wfm.capitol.entities.Claim;

/**
 * DAO for entity Contract
 *
 */
@Stateless
@Named
public class ClaimCRUD extends AbstractCRUDService<Claim> {
	
    public ClaimCRUD() {
        super(Claim.class);
    }

}
