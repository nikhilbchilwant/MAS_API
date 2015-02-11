package mas.globalScheduling.plan;

import java.io.IOException;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import mas.blackboard.nameZoneData.NamedZoneData;
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
		
	
	}

	@Override
	public void action() {
		AID bb_aid=AgentUtil.findBlackboardAgent(myAgent);
	    ACLMessage msg2=new ACLMessage(ACLMessage.CFP);
		msg2.setConversationId(MessageIds.RegisterMe);
		
		NamedZoneData ZoneDataName1=new NamedZoneData.Builder(ID.GlobalScheduler.ZoneData.jobForMachine).MsgID(MessageIds.ReplyFromScheduler).build();
		NamedZoneData ZoneDataName2=new NamedZoneData.Builder(ID.GlobalScheduler.ZoneData.ConfirmedOrder).MsgID("").build();
		NamedZoneData ZoneDataName3=new NamedZoneData.Builder(ID.GlobalScheduler.ZoneData.askforBid).MsgID("").build();
		NamedZoneData ZoneDataName4=new NamedZoneData.Builder(ID.GlobalScheduler.ZoneData.GetWaitingTime).MsgID("").build();
		NamedZoneData ZoneDataName5=new NamedZoneData.Builder(ID.GlobalScheduler.ZoneData.NegotiationJob).MsgID("").build();
		NamedZoneData ZoneDataName6=new NamedZoneData.Builder(ID.GlobalScheduler.ZoneData.waitingTime).MsgID("").build();
		NamedZoneData ZoneDataName7=new NamedZoneData.Builder(ID.GlobalScheduler.ZoneData.WorkOrder).MsgID("").build();
		NamedZoneData[] ZoneDataNames={ZoneDataName1,ZoneDataName2,ZoneDataName3,ZoneDataName4,ZoneDataName5,ZoneDataName6,ZoneDataName7};
		try {
			msg2.setContentObject(ZoneDataNames);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		/*String[] zones = {ID.GlobalScheduler.ZoneData.NegotiationJob,
				ID.GlobalScheduler.ZoneData.WorkOrder				
		};*/
			
		AgentUtil.makeZoneBB(myAgent,ZoneDataNames);
		
		
		
		SubscriptionForm subform = new SubscriptionForm();
		AID target = new AID(ID.Customer.LocalName, AID.ISLOCALNAME);
		String[] params = {ID.Customer.ZoneData.acceptedJobs,ID.Customer.ZoneData.JobList,
						ID.Customer.ZoneData.Negotiation};
		
		subform.AddSubscriptionReq(target, params);
		

		
		AgentUtil.subscribeToParam(myAgent, bb_aid, subform);
		
	}

}
