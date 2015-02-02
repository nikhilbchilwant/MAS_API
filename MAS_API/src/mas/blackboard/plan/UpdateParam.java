package mas.blackboard.plan;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mas.blackboard.nameZoneData.NamedZoneData;
import mas.blackboard.zonespace.ZoneSpace;
import mas.util.AgentUtil;
import mas.util.ZoneDataUpdate;
import bdi4jade.belief.BeliefSet;
import bdi4jade.core.BeliefBase;
import bdi4jade.message.MessageGoal;
import bdi4jade.plan.PlanBody;
import bdi4jade.plan.PlanInstance;
import bdi4jade.plan.PlanInstance.EndState;

public class UpdateParam extends OneShotBehaviour implements PlanBody {

	private ACLMessage msg;
	private AID Agent;
	private ZoneDataUpdate info;
	private BeliefBase BBBeliefBase;
	private Logger log;
	@Override
	public EndState getEndState() {
		return null;
	}

	@Override
	public void init(PlanInstance arg0) {
		log=LogManager.getLogger();
		MessageGoal goal=(MessageGoal) arg0.getGoal();
		msg=goal.getMessage();
		Agent=msg.getSender();
		
		try {
			info=(ZoneDataUpdate) msg.getContentObject();
		} catch (UnreadableException e) {
			e.printStackTrace();
		}		
		BBBeliefBase= arg0.getBeliefBase();
	}

	@Override
	public void action() {
		String AgentType = AgentUtil.GetAgentService(Agent, myAgent);
		
				
				BeliefSet<ZoneSpace> ws=(BeliefSet<ZoneSpace>)BBBeliefBase.getBelief(AgentType);
				
				if(ws==null){
					log.error("Could not find workspace for "+AgentType);
				}
				else{					
					Iterator<ZoneSpace> it=ws.iterator();
					while(it.hasNext()){
						ZoneSpace zs=it.next();

						if(zs.getName().equalsIgnoreCase(Agent.getLocalName())){
							NamedZoneData nzd = new NamedZoneData(info.getName());
							((BeliefSet<ZoneSpace>)BBBeliefBase.getBelief(AgentType)).removeValue(zs);

							if(zs.findZoneData(nzd)!=null){
								if(info.toAppendToCurrentValue()){
									zs.findZoneData(nzd).addItem(info.getValue());
								}
								else{
									zs.findZoneData(nzd).RemoveAllnAdd(info.getValue());
								}
								
								((BeliefSet<ZoneSpace>)BBBeliefBase.getBelief(AgentType)).addValue(zs);
								log.info("updated value of "+nzd.name());
								log.info("updated ZoneData: "+(((BeliefSet<ZoneSpace>)BBBeliefBase.getBelief(AgentType)).getValue().iterator().next().findZoneData(new NamedZoneData(info.getName()))));
								
								
																
							}
							else{
								log.info("couldn't find zone for "+nzd.name());
							}

							
							
						}
						
						
					}
				}
//				System.out.println(((BeliefSet<ZoneSpace>)BBBeliefBase.getBelief(AgentType)).getValue().iterator().next().findZoneData(new NamedZoneData(info.getName())));
	}
	
}

