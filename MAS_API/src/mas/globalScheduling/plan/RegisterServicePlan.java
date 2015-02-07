package mas.globalScheduling.plan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mas.customer.AbstractCustomerAgent;
import mas.util.ID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import bdi4jade.plan.PlanBody;
import bdi4jade.plan.PlanInstance;
import bdi4jade.plan.PlanInstance.EndState;

public class RegisterServicePlan extends Behaviour implements PlanBody{
	private static final long serialVersionUID = 1L;
	private Logger log;

	@Override
	public EndState getEndState() {
		return null;
	}

	@Override
	public void init(PlanInstance pInstance) {
		log=LogManager.getLogger();
	}

	@Override
	public void action() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(myAgent.getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType(ID.GlobalScheuler.Service);
		sd.setName(myAgent.getLocalName());
		dfd.addServices(sd);
		try {
			DFService.register(myAgent, dfd);
			log.info(myAgent+" registered with DF");
			
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}

	@Override
	public boolean done() {
		return false;
	}
}
