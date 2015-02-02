package Test;

import java.util.HashSet;
import java.util.Set;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bdi4jade.belief.Belief;
import bdi4jade.core.BeliefBase;
import bdi4jade.core.Capability;
import bdi4jade.core.PlanLibrary;
import bdi4jade.plan.Plan;
import bdi4jade.util.plan.SimplePlan;

public class TestCapability extends Capability {

	private Logger log;
	public TestCapability(){
		super(new BeliefBase(getBeliefs()), new PlanLibrary(getPlans()));
		
	}

	private static Set<Plan> getPlans() {
		Set<Plan> plans = new HashSet<Plan>();
		plans.add(new SimplePlan(SendMsgGoal.class, SendMsgPlan.class));
		
		return plans;
	}

	private static Set<Belief<?>> getBeliefs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void setup() {
		// Add the plan to send jobs.
		//The other plan will run by itself by virtue of receiving messages
		log=LogManager.getLogger();
		myAgent.addGoal(new SendMsgGoal());
		log.info("Send message goal is added");
		
	}
	
}
