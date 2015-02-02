package mas.customer;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import mas.blackboard.BlackboardId;

public class CustomerAgent extends AbstractCustomerAgent{

	private static final long serialVersionUID = 1L;

	public static AID findBlackboardAgent(Agent a){
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd  = new ServiceDescription();
		sd.setType( BlackboardId.Agents.Blackboard );
		dfd.addServices(sd);

		DFAgentDescription[] result;
		try {
			result = DFService.search(a, dfd);
			//				 System.out.println(result.length + " results" );
			if (result.length > 0)
			{//System.out.println(" " + result[0].getName() );
				return result[0].getName();
			}
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		return null;
	}
}
