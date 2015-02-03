package mas.customer;

import java.util.HashSet;
import java.util.Set;
import mas.customer.goal.GenerateJobGoal;
import mas.customer.goal.dispatchJobGoal;
import mas.customer.plan.DispatchJobPlan;
import mas.customer.plan.jobGeneratePlan;
import bdi4jade.belief.Belief;
import bdi4jade.belief.BeliefSet;
import bdi4jade.belief.TransientBelief;
import bdi4jade.belief.TransientBeliefSet;
import bdi4jade.core.Capability;
import bdi4jade.plan.Plan;
import bdi4jade.util.plan.SimplePlan;

/**
 * @author Anand Prajapati
 * 
 * This capability contains two goals of customer - one for generating jobs
 * and one for dispatching them to Global scheduling agent
 * 
 */

public class AbstractbasicCapability extends Capability {

	private static final long serialVersionUID = 1L;
	public static final String JOB_GENERATOR = "JOB-GENERATOR";
	
	public AbstractbasicCapability(){
		
	}

	public Set<Belief<?>> getBeliefs() {
		Set<Belief<?>> beliefs = new HashSet<Belief<?>>();

		Belief<JobGeneratorIFace> generator = 
				new TransientBelief<JobGeneratorIFace>(JOB_GENERATOR);
		
		beliefs.add(generator);
		return beliefs;
	}
	
	public Set<Plan> getPlans() {
		Set<Plan> plans = new HashSet<Plan>();

		plans.add(new SimplePlan(dispatchJobGoal.class,
				DispatchJobPlan.class));
		plans.add(new SimplePlan(GenerateJobGoal.class,
				jobGeneratePlan.class));
		
		return plans;
	}	
	
	@Override
	protected void setup() {
		myAgent.addGoal(new GenerateJobGoal());
	}
}
