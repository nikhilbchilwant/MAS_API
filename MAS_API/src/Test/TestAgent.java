package Test;





import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mas.blackboard.BlackboardId;
import bdi4jade.core.BDIAgent;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class TestAgent extends BDIAgent {
	
	private Logger log;
	public void init() {
		log=LogManager.getLogger();
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType(BlackboardId.Agents.Customer);

		sd.setName(getLocalName());
		dfd.addServices(sd);

		log.info(this.getLocalName()+" registered with DF");
		try {

			DFService.register(this, dfd);
		} catch (FIPAException fe) {
			fe.printStackTrace();
		}

		this.addCapability(new TestCapability());

	}

}
