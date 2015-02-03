package mas.blackboard.plan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import mas.blackboard.nameZoneData.NamedZoneData;
import mas.blackboard.namezonespace.NamedZoneSpace;
import mas.blackboard.workspace.WorkspaceBeliefSet;
import mas.blackboard.zonedata.ZoneData;
import mas.blackboard.zonespace.ZoneSpace;
import mas.util.AgentUtil;
import bdi4jade.belief.BeliefSet;
import bdi4jade.core.BDIAgent;
import bdi4jade.core.BeliefBase;
import bdi4jade.message.MessageGoal;
import bdi4jade.plan.PlanBody;
import bdi4jade.plan.PlanInstance;
import bdi4jade.plan.PlanInstance.EndState;

public class AddAgent extends OneShotBehaviour implements PlanBody{

	private AID AgentToReg;
	private BeliefBase belief; 
	private String AgentType; //To be taken from message content
	private String[] ZoneDataNameArray;
	private Logger log;

	@Override
	public EndState getEndState() {		
		return null;
	}

	@Override
	public void init(PlanInstance PI) {
		log=LogManager.getLogger();
		MessageGoal goal = (MessageGoal) PI.getGoal();
		ACLMessage Message = goal.getMessage();
		AgentToReg=Message.getSender();
		belief=PI.getBeliefBase();
		try {
			Object temp=Message.getContentObject();
			ZoneDataNameArray=(String[])temp;			
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}

	@Override
	public void action() {		  
		AgentType=AgentUtil.GetAgentService(AgentToReg,myAgent);
		log.info("Adding Agent :"+AgentToReg.getLocalName());
		BeliefSet<ZoneSpace> wspace;
		if(!belief.hasBelief(AgentType)){
			wspace=new WorkspaceBeliefSet(AgentType);			
			belief.addBelief(wspace);			
		}
		else{
			wspace=(BeliefSet<ZoneSpace>)belief.getBelief(AgentType);
		}
		NamedZoneSpace nz=new NamedZoneSpace(AgentToReg);
		ZoneSpace zs=new ZoneSpace(nz);
		for(int i=0;i<ZoneDataNameArray.length;i++){
			NamedZoneData nzd=new NamedZoneData(ZoneDataNameArray[i]);			
			
			zs.createZoneData(nzd);
		}
		wspace.addValue(zs);
		((BDIAgent)myAgent).getRootCapability().getBeliefBase().addBelief(wspace);
		log.info(AgentType +" type Agent added");
//		log.info(((BDIAgent)myAgent).getRootCapability().getBeliefBase());
	}
}
