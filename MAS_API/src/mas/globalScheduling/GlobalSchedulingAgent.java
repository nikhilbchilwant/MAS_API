package mas.globalScheduling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jade.core.AID;
import mas.customer.AbstractbasicCapability;
import mas.customer.basicCapability;
import mas.util.AgentUtil;
import mas.util.ID;
import mas.util.SubscriptionForm;
import bdi4jade.core.Capability;

public class GlobalSchedulingAgent extends AbstractGlobalSchedulingAgent{

	

	private Logger log;

	@Override
	protected void init() {
		super.init();		
		addCapability(new BasicCapability());
		
		
/*		
		AID bba = AgentUtil.findBlackboardAgent(this);
		bCap.getBeliefBase().updateBelief(
					ID.Blackboard.LocalName, bba);
		
		
		
		String[] zones = {ID.GlobalScheuler.ZoneData.NegotiationJob,
				ID.GlobalScheuler.ZoneData.WorkOrder				
		};
			
		AgentUtil.makeZoneBB(this,zones);
				
		SubscriptionForm subform = new SubscriptionForm();
		AID target = new AID(ID.Customer.LocalName, AID.ISLOCALNAME);
		String[] params = {ID.Customer.ZoneData.acceptedJobs,ID.Customer.ZoneData.JobList,
						ID.Customer.ZoneData.Negotiation};
		
		subform.AddSubscriptionReq(target, params);
		
		AID target_LSA=new AID(ID.LocalScheduler.LocalName,AID.ISLOCALNAME);
		
		String[] params_LSA={ID.LocalScheduler.ZoneData.WaitingTime};
		subform.AddSubscriptionReq(target_LSA, params_LSA);
		
		AgentUtil.subscribeToParam(this, bba, subform);*/
	}
}
