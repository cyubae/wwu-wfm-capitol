package de.unimuenster.wfm.capitol.service;

import de.unimuenster.wfm.capitol.dto.CaseDecision;
import de.unimuenster.wfm.capitol.dto.ContractProposal;

public interface MessageService {
	
	public void sendContractProposal(ContractProposal proposal);
	
	public void sendCaseDecision(CaseDecision decision);

}
