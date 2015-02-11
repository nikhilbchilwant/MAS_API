package mas.globalScheduling.plan;

/*When customer places order for first time, this plan triggers plan of asking waiting plans from Local Scheduling agent
 * */


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
import mas.util.ID.Customer.ZoneData;
import mas.util.ZoneDataUpdate;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.math3.analysis.function.Log;







import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bdi4jade.belief.Belief;
import bdi4jade.belief.BeliefSet;
import bdi4jade.core.BeliefBase;
import bdi4jade.message.MessageGoal;
import bdi4jade.plan.PlanBody;
import bdi4jade.plan.PlanInstance;
import bdi4jade.plan.PlanInstance.EndState;


public class TakeOrder extends Behaviour implements PlanBody {

	private Logger log;
	private static final long serialVersionUID = -6288758975856575305L;
	private String agent;
	private boolean done;
	private MessageTemplate mt;
	private boolean sent=false;
	private int times;
	private int counter;
	public AID[] machines;
	public ACLMessage msg ;
	public Iterator<Belief<?>> machineAIDiterator;
	private job order;
	private AID Blackboard_AID;


public void init(PlanInstance planInstance) {
	log=LogManager.getLogger();
	log.info("update recieved");
	Blackboard_AID=(AID) planInstance.getBeliefBase().getBelief(ID.Blackboard.LocalName).getValue();
	
	try {
		order=(job) ((MessageGoal)planInstance.getGoal()).getMessage().getContentObject();
		
	} catch (UnreadableException e) {
		e.printStackTrace();
	}

	BeliefBase MachineBeliefBase=planInstance.getBeliefBase();
	machineAIDiterator = MachineBeliefBase.getAllBeliefs().iterator();
	
	this.agent = ID.Customer.LocalName;
	this.sent = false;
	this.done = false;
	this.counter = 0;
	this.times = 1;
}
	
@Override
public void action() {
		try {
			
			ZoneDataUpdate zdu=new ZoneDataUpdate(ID.GlobalScheduler.ZoneData.GetWaitingTime, order, true);
			zdu.send(Blackboard_AID,zdu, myAgent);
			log.info("zodeDataUpdate sent");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
		}

}

@Override
public boolean done() {
	return (this.sent==true);
}

public EndState getEndState() {
	return EndState.SUCCESSFUL;
}

}
