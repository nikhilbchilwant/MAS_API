package mas.customer.plan;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import mas.customer.CustomerAgent;
import bdi4jade.belief.Belief;
import bdi4jade.belief.TransientBelief;
import bdi4jade.core.BeliefBase;
import bdi4jade.plan.PlanBody;
import bdi4jade.plan.PlanInstance;
import bdi4jade.plan.PlanInstance.EndState;

public class SubscribeToBlackboardPlan extends Behaviour implements PlanBody{

	private static final long serialVersionUID = 1L;
	private BeliefBase bfBase;
	public static String bbService = "Blackboard-service";
	private Agent blackAge;
	
	@Override
	public EndState getEndState() {
		return null;
	}

	@Override
	public void init(PlanInstance pInstance) {
		bfBase = pInstance.getBeliefBase();
		Belief<AID> bbAgent = new TransientBelief<AID>(bbService);
		bbAgent.setValue(CustomerAgent.findBlackboardAgent(myAgent));
		bfBase.addBelief(bbAgent);
	}

	@Override
	public void action() {
			this.blackAge = (Agent) bfBase.getBelief(bbService).getValue();
	}

	@Override
	public boolean done() {
		return true;
	}

}
