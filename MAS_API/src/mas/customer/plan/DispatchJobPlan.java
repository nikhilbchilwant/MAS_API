package mas.customer.plan;

import jade.core.behaviours.Behaviour;
import mas.customer.basicCapability;
import mas.job.job;
import bdi4jade.core.BeliefBase;
import bdi4jade.plan.PlanBody;
import bdi4jade.plan.PlanInstance;
import bdi4jade.plan.PlanInstance.EndState;

public class DispatchJobPlan extends Behaviour implements PlanBody{

	private static final long serialVersionUID = 1L;
//	private JobGenerator jGen;
	private BeliefBase bfBase;
	private job jobToDispatch;

	public DispatchJobPlan(job j){
		this.jobToDispatch = j;
	}
	
	@Override
	public EndState getEndState() {
		return null;
	}

	@Override
	public void init(PlanInstance pInstance) {
		bfBase = pInstance.getBeliefBase();
		jobToDispatch = (job) bfBase
					.getBelief(basicCapability.CURR_JOB)
					.getValue();
	}

	@Override
	public void action() {
		
	}

	@Override
	public boolean done() {
		return false;
	}
}
