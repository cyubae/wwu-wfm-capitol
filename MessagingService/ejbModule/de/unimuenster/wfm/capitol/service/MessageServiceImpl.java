package de.unimuenster.wfm.capitol.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.annotation.Resource;
import javax.ejb.Stateless;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import de.unimuenster.wfm.capitol.dto.CaseDecision;
import de.unimuenster.wfm.capitol.dto.ContractProposal;

@Stateless
public class MessageServiceImpl implements MessageService{
	

	@Override
	public void sendContractProposal(ContractProposal proposal) {
		//TODO Requires correct URL
		sendJSON(proposal, "localhost:8080/proposal");
	}

	@Override
	public void sendCaseDecision(CaseDecision decision) {
		//TODO Requires correct URL
		sendJSON(decision, "http://CAMUNDA-BVIS.uni-muenster.de/case");
	}
	
	private void sendJSON(Object input, String urlString){
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			URL url = new URL(urlString);
			String json = ow.writeValueAsString(input);
			System.out.println(json);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(json);
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while (in.readLine() != null) {
				System.out.println(in.toString());
			}
			System.out.println("REST Service Invoked Successfully..");
			in.close();
		} catch (Exception e) {
			System.out.println("Error while calling  REST Service");
			System.out.println(e);
		}
	}

}
