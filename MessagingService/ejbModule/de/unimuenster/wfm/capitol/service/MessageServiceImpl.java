package de.unimuenster.wfm.capitol.service;

import javax.ejb.Stateless;

import de.unimuenster.wfm.capitol.dto.CaseDecision;
import de.unimuenster.wfm.capitol.dto.ContractProposal;

@Stateless
public class MessageServiceImpl implements MessageService{

	@Override
	public void sendContractProposal(ContractProposal proposal) {
		// TODO Auto-generated method stub
		System.out.println("Erfolg! Methode sendContractProposal wurde aufgerufen");
		
	}

	@Override
	public void sendCaseDecision(CaseDecision decision) {
		// TODO Auto-generated method stub
		System.out.println("Erfolg! Methode sendCaseDecision wurde aufgerufen");
	}

}
