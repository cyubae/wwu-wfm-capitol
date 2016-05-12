package de.unimuenster.wfm.capitol.service;

import javax.ejb.Remote;

import de.unimuenster.wfm.capitol.dto.CaseDecision;
import de.unimuenster.wfm.capitol.dto.ContractProposal;

@Remote
public interface MessageService {
	
	public void sendContractProposal(ContractProposal proposal);
	
	public void sendCaseDecision(CaseDecision decision);

}
