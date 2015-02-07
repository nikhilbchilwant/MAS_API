package mas.globalScheduling;

import jade.core.AID;
import jade.lang.acl.MessageTemplate;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mas.blackboard.MessageIds;
import mas.customer.goal.GenerateJobGoal;
import mas.customer.goal.dispatchJobGoal;
import mas.customer.plan.DispatchJobPlan;
import mas.customer.plan.RegisterServicePlan;
import mas.customer.plan.jobGeneratePlan;
import mas.globalScheduling.belief.customBelief;
import mas.globalScheduling.goal.RegisterAgentGoal;
import mas.globalScheduling.goal.RegisterServiceGoal;
import mas.util.AgentUtil;
import mas.util.ID;
import bdi4jade.belief.Belief;
import bdi4jade.belief.BeliefSet;
import bdi4jade.belief.TransientBeliefSet;
import bdi4jade.core.BeliefBase;
import bdi4jade.core.Capability;
import bdi4jade.core.PlanLibrary;
import bdi4jade.plan.Plan;
import bdi4jade.util.plan.SimplePlan;

public abstract class AbstractGSCapability  extends Capability {


	private static final long serialVersionUID = 1L;
//	public static final String MACHINES = "machines-in-Shop-floor";
	private Logger log;

	public AbstractGSCapability(){
		super(new BeliefBase(getBeliefs()), new PlanLibrary(getPlans()));
}
	
	public static Set<Belief<?>> getBeliefs() {
		Set<Belief<?>> beliefs = new HashSet<Belief<?>>();

		BeliefSet<AID> mSet = 
				new TransientBeliefSet<AID>(ID.Blackboard.LocalName);
				
		beliefs.add(mSet);
		
		return beliefs;
	}
	
	public static Set<Plan> getPlans() {
		Set<Plan> plans = new HashSet<Plan>();		
		plans.add(new SimplePlan(RegisterAgentGoal.class, RegisterServicePlan.class));
		
		/*plans.add(new SimplePlan(MessageTemplate.MatchConversationId(
							MessageIds.JobFromCustomer),SendReplyToCustomer.class));*/
		
		return plans;
	}	
	
	@Override
	protected void setup() {
		log=LogManager.getLogger();
		myAgent.addGoal(new RegisterServiceGoal());
		log.info("Inside AbstractGSACapab");
	//Plan to register with bb is to be implemented by user
	}

}
