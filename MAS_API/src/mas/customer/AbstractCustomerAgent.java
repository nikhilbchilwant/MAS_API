package mas.customer;

import bdi4jade.core.BDIAgent;
import bdi4jade.core.Capability;

public abstract class AbstractCustomerAgent extends BDIAgent {
	
	public static String service = "Customer";
	private static final long serialVersionUID = 1L;
	public static String main_container_ipaddress = "127.0.0.1";
	
	@Override
	protected void init() {
		super.init();
		addCapability(new basicCapability());
	}
	

}
