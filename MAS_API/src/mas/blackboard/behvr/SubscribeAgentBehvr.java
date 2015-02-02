package mas.blackboard.behvr;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mas.blackboard.nameZoneData.NamedZoneData;
import mas.blackboard.namezonespace.NamedZoneSpace;
import mas.blackboard.zonespace.ZoneSpace;
import mas.util.ParameterSubscription.subscription;
import bdi4jade.belief.BeliefSet;
import bdi4jade.core.BeliefBase;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class SubscribeAgentBehvr extends Behaviour {

	BeliefBase BBbeliefBase;
	subscription tempSubscription;
	String AgentLocalName;
	private int step;
	private AID subscriber;
	private String AgentType;
	private Logger log;
	public static NamedZoneData nzd;
	
	public SubscribeAgentBehvr(String AgentServiceType, BeliefBase tempBeliefbase,
			subscription subscription, AID whoWantsTOSubscribe) {
		this.BBbeliefBase=tempBeliefbase;
		this.tempSubscription=subscription;
		this.subscriber=whoWantsTOSubscribe;
		DFAgentDescription dfa=new DFAgentDescription();
		ServiceDescription sd=new ServiceDescription();
		dfa.addServices(sd);
		this.AgentType=AgentServiceType;

	}

	@Override
	public void action() {
		log=LogManager.getLogger();
		step=1;
		switch(step){
			case 1:
				BeliefSet<ZoneSpace> ws=(BeliefSet<ZoneSpace>)BBbeliefBase.getBelief(AgentType);
				
				if(ws==null){
					System.out.println("1");
				}
				else{
					
					Iterator<ZoneSpace> it=ws.iterator();
					while(it.hasNext()){
						

						ZoneSpace zs=it.next();
						
						if(zs.getName().equalsIgnoreCase(tempSubscription.Agent.getLocalName())){
							for (String parameter : tempSubscription.parameters) {

								nzd=new NamedZoneData(parameter);
								((BeliefSet<ZoneSpace>)BBbeliefBase.getBelief(AgentType)).removeValue(zs);

								if(zs.findZoneData(nzd)!=null){
									zs.findZoneData(nzd).subscribe(subscriber); //Throws null pointer exception if ZoneData doesnn't exists
									((BeliefSet<ZoneSpace>)BBbeliefBase.getBelief(AgentType)).addValue(zs);								

									step++;									
								}
								else{
									log.error("Couldn't fid zone "+zs.getName());
								}

							}
							
						}
						
						
					}
				}
		}
				

	}

	@Override
	public boolean done() {
		return step>1;
	}

}
