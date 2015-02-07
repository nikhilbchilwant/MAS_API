package mas.globalScheduling.plan;

import java.io.IOException;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import mas.util.AgentUtil;
import mas.util.ID;
import mas.util.MessageIds;
import mas.util.SubscriptionForm;
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
		
		/*						
		AID bba = AgentUtil.findBlackboardAgent(this);
		bCap.getBeliefBase().updateBelief(
					ID.Blackboard.LocalName, bba);
		
		
		
		String[] zones = {ID.GlobalScheuler.ZoneData.NegotiationJob,
				ID.GlobalScheuler.ZoneData.WorkOrder				
		};
			
		AgentUtil.makeZoneBB(this,zones);
				
		SubscriptionForm subform = new SubscriptionForm();
		AID target = new AID(ID.Customer.LocalName, AID.ISLOCALNAME);
		String[] params = {ID.Customer.ZoneData.acceptedJobs,ID.Customer.ZoneData.JobList,
						ID.Customer.ZoneData.Negotiation};
		
		subform.AddSubscriptionReq(target, params);
		
		AID target_LSA=new AID(ID.Customer.LocalName,AID.ISLOCALNAME);
		
		String[] params_LSA={ID.Customer.ZoneData.JobList};
		subform.AddSubscriptionReq(target_LSA, params_LSA);
		
		AgentUtil.subscribeToParam(this, bba, subform);*/
		
		
		
		
		
		
	}

	@Override
	public void action() {
		AID bb_aid=AgentUtil.findBlackboardAgent(myAgent);
	    ACLMessage msg2=new ACLMessage(ACLMessage.CFP);
		msg2.setConversationId(MessageIds.RegisterMe);
		String[] ZoneDataNames={"JobStatus"};
		try {
			msg2.setContentObject(ZoneDataNames);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		String[] zones = {ID.GlobalScheduler.ZoneData.NegotiationJob,
				ID.GlobalScheduler.ZoneData.WorkOrder				
		};
			
		AgentUtil.makeZoneBB(myAgent,zones);
		
		
		
		SubscriptionForm subform = new SubscriptionForm();
		AID target = new AID(ID.Customer.LocalName, AID.ISLOCALNAME);
		String[] params = {ID.Customer.ZoneData.acceptedJobs,ID.Customer.ZoneData.JobList,
						ID.Customer.ZoneData.Negotiation};
		
		subform.AddSubscriptionReq(target, params);
		

		
		AgentUtil.subscribeToParam(myAgent, bb_aid, subform);
		
	}

}
