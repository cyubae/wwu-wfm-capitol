/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.unimuenster.wfm.capitol.settlement.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;

import de.unimuenster.wfm.capitol.entities.Claim;
import de.unimuenster.wfm.capitol.enums.ClaimDecision;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ClaimCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;;

@Named
@ConversationScoped
public class ReviewClaimController implements Serializable {

	private static Logger LOGGER = Logger.getLogger(ReviewClaimController.class.getName());

	private static  final long serialVersionUID = 1L;

	// the BusinessProcess to access the process variables
	@Inject
	private BusinessProcess businessProcess;

	// Inject CRUD-Services to access persistence unit	
	@Inject
	private ContractCRUD contractCRUD;

	@Inject
	private PolicyCRUD policyCRUD;

	@Inject
	private CarCRUD carCRUD;

	@Inject
	private CustomerCRUD customerCRUD;		
	
	@Inject
	private ClaimCRUD claimCRUD;	

	// Inject task form available through the camunda cdi artifact
	@Inject
	private TaskForm taskForm;	

	// Caches database objects during the conversation
	private Claim claim;

	public Claim getClaim() {
		if(claim == null) {
			claim = claimCRUD.find((Integer) (businessProcess.getVariable("claim_id_internal")));
		}
		return claim;
	}
	

	public BigDecimal getCoverageCosts() {
		return this.getClaim().getCoverageCosts();
	}

	public void setCoverageCosts(BigDecimal coverageCosts) {
		this.getClaim().setCoverageCosts(coverageCosts);
		this.setCoverageDecision();
	}
	
	public void setCoverageDecision() {
		BigDecimal claimValue = claim.getClaimValue();
		BigDecimal claimCoverageCosts = claim.getCoverageCosts();

		claim.setCustomerCosts(claimValue.subtract(claimCoverageCosts));

		ClaimDecision claimDecision;
		if (claimValue.compareTo(claimCoverageCosts) > 0) {
			if (claimCoverageCosts.compareTo(new BigDecimal(0)) > 0) {
				claimDecision = ClaimDecision.PARTIALLY_COVERED;
			}
			else {
				claimDecision = ClaimDecision.NOT_COVERED;
			}
		}
		else {
			claimDecision = ClaimDecision.COVERED;
		}
		claim.setClaimDecision(claimDecision);
	}
	
	public ClaimDecision getCoverageDecision() {
		return this.claim.getClaimDecision();
	}


	public void submitResult(boolean result) throws IOException {

		this.setCoverageDecision();
			
		LOGGER.log(Level.INFO, "Found new Claim Decision:" + claim.getClaimDecision());
		
		claimCRUD.update(claim);

		try {
			// Complete user task from
			LOGGER.log(Level.INFO, "submitResult - STEP: TRY");
			taskForm.completeTask();
			LOGGER.log(Level.INFO, "submitResult - STEP: TRY - FINISHED");
		} catch (IOException e) {
			// Rollback both transactions on error
			LOGGER.log(Level.INFO, "submitResult - STEP: CATCH");
			throw new RuntimeException("Cannot complete task", e);
		}
	}

}

