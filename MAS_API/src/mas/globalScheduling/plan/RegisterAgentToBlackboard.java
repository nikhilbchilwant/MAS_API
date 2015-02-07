package mas.globalScheduling.plan;

import java.io.IOException;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import mas.util.AgentUtil;
import mas.util.ID;
import bdi4jade.plan.PlanBody;
import bdi4jade.plan.PlanInstance;
import bdi4jade.plan.PlanInstance.EndState;

public class RegisterAgentToBlackboard extends OneShotBehaviour implements PlanBody {
	int step;
	
	@Override
	public EndState getEndState() {

		return EndState.SUCCESSFUL;
	}

	@Override
	public void init(PlanInstance planInstance) {
		step=0;
		
		
		
	}

	@Override
	public void action() {
		AID bb_aid=new AID(ID.Blackboard.LocalName,AID.ISLOCALNAME);
	    ACLMessage msg2=new ACLMessage(ACLMessage.CFP);
		msg2.setConversationId(mas.blackboard.MessageIds.RegisterMe);
		String[] ZoneDataNames={"JobStatus"};
		try {
			msg2.setContentObject(ZoneDataNames);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		msg2.addReceiver(bb_aid);
		myAgent.send(msg2);
		
	}

}
