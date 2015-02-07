package mas.blackboard.behvr;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mas.blackboard.nameZoneData.NamedZoneData;
import mas.blackboard.namezonespace.NamedZoneSpace;
import mas.blackboard.zonespace.ZoneSpace;
import mas.util.AgentUtil;
import mas.util.SubscriptionForm;
import bdi4jade.belief.BeliefSet;
import bdi4jade.core.BeliefBase;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class SubscribeAgentBehvr extends Behaviour {

	BeliefBase BBbeliefBase;
	SubscriptionForm.parameterSubscription tempSubscription;
	String AgentLocalName;
	private int step;
	private AID subscriber;
	private String AgentType;
	private Logger log;
	private static NamedZoneData nzd;
	private AID AgentToReg;
	
	public SubscribeAgentBehvr(AID agent_to_reg, BeliefBase tempBeliefbase,
			SubscriptionForm.parameterSubscription subscription, AID whoWantsTOSubscribe) {
		this.BBbeliefBase=tempBeliefbase;
		this.tempSubscription=subscription;
		this.subscriber=whoWantsTOSubscribe;
		DFAgentDescription dfa=new DFAgentDescription();
		ServiceDescription sd=new ServiceDescription();
		dfa.addServices(sd);
		this.AgentToReg=agent_to_reg;
		

	}

	@Override
	public void action() {
		log=LogManager.getLogger();
		step=1;
		switch(step){
			case 1:
//				log.info(AgentToReg);
				this.AgentType=AgentUtil.GetAgentService(AgentToReg,myAgent);
				if(AgentType!=null){
					step++;
				}
						
			case 2:
				BeliefSet<ZoneSpace> ws=(BeliefSet<ZoneSpace>)BBbeliefBase.getBelief(AgentType);
				
				if(ws==null){
					
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
									log.error("Couldn't find zone "+zs.getName());
								}

							}
							
						}
						
						
					}
				}
		}
				

	}

	@Override
	public boolean done() {
		return step>2;
	}

}
