package mas.customer.plan;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class NegotiateBehavior extends CyclicBehaviour{

	private static final long serialVersionUID = 1L;
	int step = 0;
	@Override
	public void action() {
		
		switch(step){
		case 0:
			ACLMessage msg = myAgent.receive();
			if(msg != null){
				ACLMessage reply = msg.createReply();
				reply.setContent("negotiation");
				myAgent.send(reply);
			}else
				block();
			break;
		case 1:
			break;
		}
	}

}
