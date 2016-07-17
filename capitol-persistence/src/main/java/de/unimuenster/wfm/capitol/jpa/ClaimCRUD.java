package de.unimuenster.wfm.capitol.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityGraph;

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
    
    public Claim findClaimExternalParties(int id) {
    	EntityGraph<Claim> entityGraph = entityManager.createEntityGraph(Claim.class);
    	entityGraph.addAttributeNodes("externalParties");
    	
    	Map<String, Object> hints = new HashMap<String, Object>();
    	hints.put("javax.persistence.fetchgraph", entityGraph);
    	Claim claim = entityManager.find(Claim.class, id, hints);
    	
    	return claim;
    	 
//    	EntityManagerFactory factory = entityManager.getEntityManagerFactory();
//    	factory.addNamedEntityGraph("Claim.externalParties", entityGraph);
    }

}
