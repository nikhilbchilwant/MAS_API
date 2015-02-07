/*package mas.globalScheduling.plan;


//----------------------------------------------------------------------------
// Copyright (C) 2011  Ingrid Nunes
// 
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
// 
// To contact the authors:
// http://inf.ufrgs.br/~ingridnunes/bdi4jade/
//
//----------------------------------------------------------------------------

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;









import mas.job.job;
import mas.util.ID;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.math3.analysis.function.Log;





import bdi4jade.belief.Belief;
import bdi4jade.belief.BeliefSet;
import bdi4jade.core.BeliefBase;
import bdi4jade.message.MessageGoal;
import bdi4jade.plan.PlanBody;
import bdi4jade.plan.PlanInstance;
import bdi4jade.plan.PlanInstance.EndState;
public class SendReplyToCustomerPlan extends Behaviour implements PlanBody {

	private static final long serialVersionUID = -6288758975856575305L;
	private String agent;
	private boolean done;
	private Log log;
	private MessageTemplate mt;
	private boolean sent=false;
	private int times;
	private int counter;
	public AID[] machines;
	public ACLMessage msg ;
	public Iterator<Belief<?>> machineAIDiterator;
	private job order;
	private Object Blackboard_AID;
	
	@Override
	public void action() {
			try {
				
				
				
				ACLMessage update=new ACLMessage(ACLMessage.CFP);
				update.addReceiver(new AID(ID.Blackboard.LocalName, false));
				Object[] UpdateToSend={order, MASconstants.parameters.JobList};
				try {
					update.setContentObject(UpdateToSend);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update.setConversationId(MessageIds.UpdateParameter);
				myAgent.send(update);
				
				
				ACLMessage MessageToLS=new ACLMessage(ACLMessage.PROPOSE);
//				MessageToLS.setContent(MessageIds.AskWaitTime);
				int NoOfmachines=0;
				while(machineAIDiterator.hasNext()){
					MessageToLS.addReceiver((AID) machineAIDiterator.next().getValue());
					NoOfmachines++;
				}
				MessageToLS.setConversationId(MessageIds.WaitTime);
				MessageToLS.setContentObject(order);
//				myAgent.send(MessageToLS);
				
				myAgent.addBehaviour(new WaitTime(NoOfmachines, agent));

				SendReplyToCustomerPlan.this.sent = true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.print(e);
			}
//					log.info("Ping sent to agent " + agent + "!");
	}
	
	@Override
	public boolean done() {
		return (this.sent==true);
	}

	public EndState getEndState() {
		return EndState.SUCCESSFUL;
	}

	public void init(PlanInstance planInstance) {
		Blackboard_AID=planInstance.getBeliefBase().getBelief(ID.Blackboard.LocalName).getValue();
		
		try {
			order=(job) ((MessageGoal)planInstance.getGoal()).getMessage().getContentObject();
			
		} catch (UnreadableException e) {
			e.printStackTrace();
		}
	
		BeliefBase MachineBeliefBase=planInstance.getBeliefBase();
		
//		System.out.println("MachineBeliefBase collection:"+MachineBeliefBase.getAllBeliefs());
//		BeliefSet<AID> tempAID=(BeliefSet<AID>)MachineBeliefBase.getBelief("machinesAIDs");
//		machineAIDiterator=tempAID.iterator();
		
//		System.out.println("GSA is recieving BB update");
		machineAIDiterator = MachineBeliefBase.getAllBeliefs().iterator();
		
		this.agent = AgentStarter.customerAgent;
		this.sent = false;
		this.done = false;
		this.counter = 0;
		this.times = 1;
	}
}
*/