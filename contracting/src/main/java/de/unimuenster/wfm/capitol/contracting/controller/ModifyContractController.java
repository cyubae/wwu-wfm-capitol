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
import java.math.BigDecimal;
import java.util.Collection;
import java.util.logging.Logger;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import org.camunda.bpm.engine.cdi.jsf.TaskForm;

import de.unimuenster.wfm.capitol.contracting.enums.ContractResult;
import de.unimuenster.wfm.capitol.contracting.helper.EnumMapper;
import de.unimuenster.wfm.capitol.contracting.helper.PremiumCalculator;
import de.unimuenster.wfm.capitol.entities.Contract;
import de.unimuenster.wfm.capitol.entities.Policy;
import de.unimuenster.wfm.capitol.jpa.CarCRUD;
import de.unimuenster.wfm.capitol.jpa.ContractCRUD;
import de.unimuenster.wfm.capitol.jpa.CustomerCRUD;
import de.unimuenster.wfm.capitol.jpa.PolicyCRUD;

@Named
@ConversationScoped
public class ModifyContractController implements Serializable {

	private static Logger LOGGER = Logger.getLogger(ModifyContractController.class.getName());

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

	//	private Collection<Policy> policies;

	public Contract getContract() {
		if (contract == null) {
			contract = contractCRUD.find((Integer) (businessProcess.getVariable("contract_id")));
		}
		return contract;
	}

	private int totalCost = -1;

	public int getTotalCost() {
		if(totalCost<0) {

		}
		return this.totalCost;
	}

	public void changeDailyPremium(Policy policy) {
		BigDecimal dailyPremium = PremiumCalculator.getDailyPremium(
				policy.getCar().getType(), 
				policy.getContract().getInsuranceType(), 
				policy.getCar().getPs(), 
				policy.getCar().getConstructionYear());
		int discount = policy.getDiscount();
		BigDecimal finalPremium = dailyPremium.multiply(new BigDecimal(1 - ((double)discount)/100) );		
		policy.setDailyPremium(finalPremium);
	}
	
	public void changeAllDailyPremiums() {
		Collection<Policy> policies = this.getContract().getPolicies();
		for(Policy policy : policies) {
			this.changeDailyPremium(policy);
		}
	}




	public void submitValidation() throws IOException {
		//update process variable
		businessProcess.setVariable("contract_validated", true);
		businessProcess.setVariable("contract_result", EnumMapper.CONTRACTRESULT_TO_INTEGER.get(ContractResult.ACCEPTED));

		//update contract persistence object
		this.getContract().setValidated(true);		
		contractCRUD.update(this.getContract());


		try {
			// Complete user task from

			taskForm.completeTask();

		} catch (IOException e) {
			// Rollback both transactions on error

			throw new RuntimeException("Cannot complete task", e);
		}
	}


}

