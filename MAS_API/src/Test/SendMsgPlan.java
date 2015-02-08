package Test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;






import mas.util.AgentUtil;
import mas.util.ID;


import mas.util.MessageIds;
import mas.util.SubscriptionForm;
import mas.util.ZoneDataUpdate;
import bdi4jade.plan.PlanBody;
import bdi4jade.plan.PlanInstance;
import bdi4jade.plan.PlanInstance.EndState;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class SendMsgPlan extends OneShotBehaviour implements PlanBody{

	private AID bb;
	private Logger log;
	
	@Override
	public EndState getEndState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(PlanInstance arg0) {
		log=LogManager.getLogger();
		
	}

	@Override
	public void action() {
        
        DFAgentDescription dfd2 = new DFAgentDescription();
        ServiceDescription sd2  = new ServiceDescription();
        sd2.setType( ID.Blackboard.Service );
        dfd2.addServices(sd2);
        
        DFAgentDescription[] result;
		try {
			
			result = DFService.search(myAgent, dfd2);
//			 System.out.println(result.length + " results" );
	            if (result.length>0)
	                {//System.out.println(" " + result[0].getName() );
	                bb=result[0].getName();
	                }
		} catch (FIPAException e) {

			e.printStackTrace();

		}
		
        ACLMessage msg2=new ACLMessage(ACLMessage.CFP);
		msg2.setConversationId(MessageIds.RegisterMe);
		String[] ZoneDataNames={ID.Customer.ZoneData.JobList,ID.Customer.ZoneData.acceptedJobs,ID.Customer.ZoneData.Negotiation};
		try {
			msg2.setContentObject(ZoneDataNames);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AgentUtil.makeZoneBB(myAgent, ZoneDataNames);
//		msg2.addReceiver(bb);
//		myAgent.send(msg2);
//		log.info(msg2.getSender().getLocalName()+" sent "+MessageIds.RegisterMe);
		
//		ParameterSubscription ps=new ParameterSubscription(MASconstants.AgentService.Customer);
		
		SubscriptionForm sf=new SubscriptionForm();
		String[] PSstring=new String[2];
		PSstring[0]=ID.GlobalScheduler.ZoneData.NegotiationJob;
		PSstring[1]=ID.GlobalScheduler.ZoneData.waitingTime;
		sf.AddSubscriptionReq(new AID(ID.GlobalScheduler.LocalName,true), PSstring );
		
		AgentUtil.subscribeToParam(myAgent, bb, sf);

		
		log.info("sent "+MessageIds.SubscribeParameter);
		
		ACLMessage msg4=new ACLMessage(ACLMessage.CFP);
		msg4.setConversationId(MessageIds.UpdateParameter);
		try {
			ZoneDataUpdate zdu=new ZoneDataUpdate(ID.Customer.ZoneData.JobList, (Object)1, true);
			msg4.setContentObject(zdu);
		} catch (IOException e) {
			e.printStackTrace();
		}
		msg4.addReceiver(bb);
		myAgent.send(msg4);
		
		
		log.info("update sent");
		
		/*msg4.setConversationId(mas.blackboard.MessageIds.UpdateParameter);
		try {
			ZoneDataUpdate zdu=new ZoneDataUpdate("test", (Object)2, true);
			msg4.setContentObject(zdu);
		} catch (IOException e) {
			e.printStackTrace();
		}
		msg4.addReceiver(bb);
		myAgent.send(msg4);
		log.info(msg4.getSender().getLocalName()+" sent "+mas.blackboard.MessageIds.UpdateParameter);*/
		
	}

}
