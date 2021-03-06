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
package de.unimuenster.wfm.capitol.contracting.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import de.unimuenster.wfm.capitol.contracting.enums.ContractResult;
import de.unimuenster.wfm.capitol.contracting.helper.EnumMapper;
import de.unimuenster.wfm.capitol.entities.Car;
import de.unimuenster.wfm.capitol.entities.Contract;
import de.unimuenster.wfm.capitol.entities.Customer;
import de.unimuenster.wfm.capitol.entities.Policy;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;

@Named
@ConversationScoped
public class ValidateContractController implements Serializable {
	
	private static Logger LOGGER = Logger.getLogger(ValidateContractController.class.getName());

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

	// Inject task form available through the camunda cdi artifact
	@Inject
	private TaskForm taskForm;	

	// Caches the Contract during the conversation
	private Contract contract;
		
	public Contract getContract() {
		if (contract == null) {
			contract = contractCRUD.find((Integer) (businessProcess.getVariable("contract_id")));
		}
		return contract;
	}	

	public void submitValidation(boolean validated) throws IOException {
		//update process variable
		businessProcess.setVariable("contract_validated", validated);
		
		//update contract persistence object
		this.getContract().setValidated(validated);
		contractCRUD.update(this.getContract());

		//Set contract_result to accepted
		businessProcess.setVariable("contract_result", EnumMapper.CONTRACTRESULT_TO_INTEGER.get(ContractResult.ACCEPTED));
				
		try {
			// Complete user task from
			LOGGER.log(Level.INFO, "submitValidation - STEP: TRY");
			taskForm.completeTask();
			LOGGER.log(Level.INFO, "submitValidation - STEP: TRY - FINISHED");
		} catch (IOException e) {
			// Rollback both transactions on error
			LOGGER.log(Level.INFO, "submitValidation - STEP: CATCH");
			throw new RuntimeException("Cannot complete task", e);
		}
	}
	
}

