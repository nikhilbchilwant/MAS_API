package mas.util;

import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;




import jade.lang.acl.ACLMessage;


import mas.blackboard.nameZoneData.NamedZoneData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AgentUtil {
	private static Logger log;
	
	public static String GetAgentService(AID AgentToFind, Agent CurrentAgent) {
		log=LogManager.getLogger();
		log.info("GetAgentService called by "+CurrentAgent.getLocalName());
		DFAgentDescription dfd = new DFAgentDescription();

		ServiceDescription sd = new ServiceDescription();
		
		
		dfd.addServices(sd);

		try {

			  DFAgentDescription[] result = DFService.search(CurrentAgent,dfd);

			  AID s = null;
			  ServiceDescription sdd;
			      if ((result != null) && (result.length > 0)){
			    	  for(DFAgentDescription d : result){
//			    		  log.info("found :"+d.getName()+" VS "+AgentToFind);
						  if(d.getName().getLocalName().equals(AgentToFind.getLocalName())){
							  log.info("Found service type "+((ServiceDescription) d.getAllServices().next()).getType());
							  return ((ServiceDescription) d.getAllServices().next()).getType();
							}

			    	  }
			      }
			  } catch (Exception fe) {
			      fe.printStackTrace();
			      System.out.println(fe);
			  }
		log.info("Service not found");
		return null;
	}


	public static AID findBlackboardAgent(Agent a){
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd  = new ServiceDescription();
		sd.setType(ID.Blackboard.Service);
		dfd.addServices(sd);
	
		DFAgentDescription[] result;
		try {
			result = DFService.search(a, dfd);
			while(result.length == 0){
				//				 System.out.println(result.length + " results" );
				result = DFService.search(a, dfd);
				Thread.sleep(1000);
			} 
			if (result.length > 0) {
				//System.out.println(" " + result[0].getName() );
				return result[0].getName();
			}
			}catch (FIPAException e) {
				e.printStackTrace();
		} catch (InterruptedException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public static void subscribeToParam(Agent sender, AID bb,
					SubscriptionForm sform){
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		try {
			msg.setContentObject(sform);
			msg.setConversationId(MessageIds.SubscribeParameter);
			msg.addReceiver(bb);
			sender.send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void makeZoneBB(Agent sender, NamedZoneData[] zones) {
		 ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
			msg.setConversationId(MessageIds.RegisterMe);
			try {
				msg.setContentObject(zones);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			msg.addReceiver(findBlackboardAgent(sender));
			sender.send(msg);
		}
}
