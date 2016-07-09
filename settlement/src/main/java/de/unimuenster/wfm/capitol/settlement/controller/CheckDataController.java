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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;

import de.unimuenster.wfm.capitol.entities.Car;
import de.unimuenster.wfm.capitol.entities.Claim;
import de.unimuenster.wfm.capitol.entities.Contract;
import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.entities.Policy;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;
import de.unimuenster.wfm.capitol.jpa.ClaimCRUD;;

@Named
@ConversationScoped
public class CheckDataController implements Serializable {

	private static Logger LOGGER = Logger.getLogger(CheckDataController.class.getName());

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
//	private Car car;
//	private Policy policy;
//	private Contract contract;
//	private Customer customer;


	public Claim getClaim() {
		if(claim == null) {
			claim = claimCRUD.find((Integer) (businessProcess.getVariable("claim_id")));
		}
		return claim;
	}

	//	public Policy getPolicy() {
	//		if (policy == null) {
	//			policy = policyCRUD.find(this.getClaim().getPolicy().getPolicyId());
	//		}
	//	}
	//
	//	public Car getCar() {
	//		if (car == null) {
	//			car = carCRUD.find(this.getPolicy().getCar().getCarId());
	//		}
	//	}
	//	
	//	public Contract getContract() {
	//		if (contract == null) {
	//			contract = contractCRUD.find(this.getPolicy().getContract().getContractId());
	//		}
	//	}
	//	



	public void submitResult(boolean result) throws IOException {
		//update process variable
		businessProcess.setVariable("data_correct", result);

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

