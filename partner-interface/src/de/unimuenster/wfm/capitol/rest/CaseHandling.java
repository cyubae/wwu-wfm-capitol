package de.unimuenster.wfm.capitol.rest;

import javax.ws.rs.*;
import de.unimuenster.wfm.capitol.dto.LiabilityCase;

@Path( "casehandling" )
public class CaseHandling {
  @POST
  @Consumes("application/json")
  public void receiveCase(LiabilityCase liabilityCase){
	  System.out.println(liabilityCase.getDescription());
	  System.out.println(liabilityCase.getId());
	  System.out.println(liabilityCase.getName());
	  System.out.println(liabilityCase.getSum());	  
  }
}