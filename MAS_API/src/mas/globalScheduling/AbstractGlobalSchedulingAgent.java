package mas.globalScheduling;

import bdi4jade.core.BDIAgent;

public class AbstractGlobalSchedulingAgent extends BDIAgent{

	private static final long serialVersionUID = 1L;
	@Override
	protected void init() {
		super.init();
		addCapability(new GSABasicCapability());
	}
}
