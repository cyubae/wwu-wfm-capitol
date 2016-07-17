package de.unimuenster.wfm.capitol.jpa;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import de.unimuenster.wfm.capitol.entities.Claim;

/**
 * DAO for entity Contract
 *
 */
@Stateless
@Named
public class ClaimCRUD extends AbstractCRUDService<Claim> {
	
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    protected EntityManager entityManager;
	
    public ClaimCRUD() {
        super(Claim.class);
    }

}
