package de.unimuenster.wfm.capitol.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import de.unimuenster.wfm.capitol.dto.ContractRequest;
import de.unimuenster.wfm.capitol.dto.LiabilityCase;

@Path( "contracting" )
public class Contracting
{
	@POST
	@Consumes("application/json")
	public void receiveCase(ContractRequest contractRequest){
		System.out.println(contractRequest.getDescription());
		System.out.println(contractRequest.getId());
		System.out.println(contractRequest.getName());
		System.out.println(contractRequest.getSum());	  
	}
}