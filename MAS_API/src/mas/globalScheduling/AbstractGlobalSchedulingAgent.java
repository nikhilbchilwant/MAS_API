package mas.globalScheduling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bdi4jade.core.BDIAgent;
import bdi4jade.core.Capability;

public abstract class AbstractGlobalSchedulingAgent extends BDIAgent{

	
	private static final long serialVersionUID = 1L;
	private Logger log;
	@Override
	protected void init() {		
		super.init();
		log=LogManager.getLogger();
		/*Capability bCap = new BasicCapability();
		addCapability(bCap);*/
		
	}
}
