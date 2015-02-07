package mas.globalScheduling.plan;

import java.io.IOException;

import mas.globalScheduling.AbstractGSCapability;
import mas.job.job;
import mas.util.MessageIds;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import bdi4jade.core.BeliefBase;
import bdi4jade.plan.PlanBody;
import bdi4jade.plan.PlanInstance;
import bdi4jade.plan.PlanInstance.EndState;

public class AskForBidPlan extends Behaviour implements PlanBody{

	private int step = 0;
	private ACLMessage[] bids;
	private int numLSA;
	private int repliesCnt;
	private BeliefBase bfBase;
	private MessageTemplate mt;
	private job JobToSend;
	private static final long serialVersionUID = 1L;

	@Override
	public EndState getEndState() {
		return null;
	}

	@Override
	public void init(PlanInstance pInstance) {
		bfBase = pInstance.getBeliefBase();
		mt = MessageTemplate.MatchConversationId(MessageIds.BidForJob);
		repliesCnt = 0;
	}

	@Override
	public void action() {
		
		switch (step) {
			case 0:
			/*		numLSA = (Integer) bfBase.getBelief(AbstractGSCapability
								.MACHINES).getValue();*/
					
					bids = new ACLMessage[numLSA];
					step = 1;
				break;
				
			case 1:
				try{
					ACLMessage reply = myAgent.receive(mt);
					if (reply != null) {
						bids[repliesCnt]=reply;
						repliesCnt++;				
						if (repliesCnt == numLSA) {				
							step = 2; 
						}
					} else {
							block();
						}
					} catch (Exception e3) {
					
					}
				break;
				
			case 2:			
				
				ACLMessage min = bids[0];			
				for(int i = 0; i < bids.length;i++){
		//				if(Double.parseDouble(Bids[i].getContent())<Double.parseDouble(min.getContent())){
					try {
						if(((job)(bids[i].getContentObject())).BidByLSA > 
								((job)(min.getContentObject())).BidByLSA){
								min = bids[i];
							}
					} catch (UnreadableException e) {
						e.printStackTrace();
						}
				}
	//			try {
	//				System.out.println("Min Bid is:"+((job)(min.getContentObject())).BidByLSA);
	//			} catch (UnreadableException e1) {
	//				// TODO Auto-generated catch block
	//				e1.printStackTrace();
	//			}
				
				ACLMessage OrderToLSA = new ACLMessage(ACLMessage.REQUEST);
				try {
					OrderToLSA.setContentObject(JobToSend);
				} catch (IOException e) {
					e.printStackTrace();
				}
				OrderToLSA.addReceiver(min.getSender());
				OrderToLSA.setConversationId(MessageIds.WorkOrder);
				myAgent.send(OrderToLSA);
	//			System.out.println("OrderToLSA" + OrderToLSA);
				step = 3;
				break;
			case 3:      			
					step = 4;
				break;
		}   
	}

	@Override
	public boolean done() {
		return step >= 4;
	}

}
