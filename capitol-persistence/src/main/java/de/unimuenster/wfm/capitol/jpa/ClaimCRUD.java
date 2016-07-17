package de.unimuenster.wfm.capitol.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;
import javax.inject.Named;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Hibernate;

import de.unimuenster.wfm.capitol.entities.Claim;
import de.unimuenster.wfm.capitol.entities.ExternalParty;

/**
 * DAO for entity Contract
 *
 */
@Stateful
@Named
public class ClaimCRUD extends AbstractCRUDService<Claim> {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager extendedEntityManager;

	public ClaimCRUD() {
		super(Claim.class);
	}

	public Claim findClaimExternalParties(int id) {
		EntityGraph<Claim> entityGraph = extendedEntityManager.createEntityGraph(Claim.class);
		entityGraph.addAttributeNodes("externalParties");

		Map<String, Object> hints = new HashMap<String, Object>();
		hints.put("javax.persistence.fetchgraph", entityGraph);
		Claim claim = extendedEntityManager.find(Claim.class, id, hints);

		Hibernate.initialize(claim.getExternalParties());
		for(ExternalParty externalParty : claim.getExternalParties()) {
			Hibernate.initialize(externalParty);
		}

		return claim;

		//    	EntityManagerFactory factory = entityManager.getEntityManagerFactory();
		//    	factory.addNamedEntityGraph("Claim.externalParties", entityGraph);
	}


}
