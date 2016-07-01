package de.unimuenster.wfm.capitol.jpa;

import javax.ejb.Stateless;
import javax.inject.Named;

import de.unimuenster.wfm.capitol.entities.Contract;

/**
 * DAO for entity Contract
 *
 */
@Stateless
@Named
public class ContractCRUD extends AbstractCRUDService<Contract> {
	
    public ContractCRUD() {
        super(Contract.class);
    }

}
