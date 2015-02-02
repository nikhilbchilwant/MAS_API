package mas.util;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AgentUtil {
	private static Logger log;
	public static String GetAgentService(AID AgentToFind, Agent CurrentAgent) {
		log=LogManager.getLogger();
		DFAgentDescription dfd = new DFAgentDescription();

		ServiceDescription sd = new ServiceDescription();
		log.info("Queried for "+AgentToFind.getLocalName());
		
		dfd.addServices(sd);

		try {

			  DFAgentDescription[] result = DFService.search(CurrentAgent,dfd);

			  AID s = null;
			  ServiceDescription sdd;
			      if ((result != null) && (result.length > 0)){
			    	  for(DFAgentDescription d : result){
						  if(d.getName().equals(AgentToFind)){
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


}
