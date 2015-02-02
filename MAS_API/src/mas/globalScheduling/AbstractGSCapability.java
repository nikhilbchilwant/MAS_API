package mas.globalScheduling;

import jade.core.AID;

import java.util.HashSet;
import java.util.Set;

import mas.customer.goal.GenerateJobGoal;
import mas.customer.goal.dispatchJobGoal;
import mas.customer.plan.DispatchJobPlan;
import mas.customer.plan.jobGeneratePlan;
import mas.globalScheduling.belief.customBelief;
import bdi4jade.belief.Belief;
import bdi4jade.belief.BeliefSet;
import bdi4jade.belief.TransientBeliefSet;
import bdi4jade.core.BeliefBase;
import bdi4jade.core.Capability;
import bdi4jade.core.PlanLibrary;
import bdi4jade.plan.Plan;
import bdi4jade.util.plan.SimplePlan;

public class AbstractGSCapability  extends Capability {


	private static final long serialVersionUID = 1L;
	public static final String MACHINES = "machines-in-Shop-floor";

	public AbstractGSCapability(){
		super(new BeliefBase(getBeliefs()), new PlanLibrary(getPlans()));
}
	
	public static Set<Belief<?>> getBeliefs() {
		Set<Belief<?>> beliefs = new HashSet<Belief<?>>();

		BeliefSet<AID> mSet = 
				new TransientBeliefSet<AID>(MACHINES);
		
		beliefs.add(mSet);
		return beliefs;
	}
	
	public static Set<Plan> getPlans() {
		Set<Plan> plans = new HashSet<Plan>();

		plans.add(new SimplePlan(dispatchJobGoal.class,
				DispatchJobPlan.class));
		
		return plans;
	}	
	
	@Override
	protected void setup() {
		myAgent.addGoal(new GenerateJobGoal());
	}

}
