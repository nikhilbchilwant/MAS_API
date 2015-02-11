package mas.globalScheduling;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mas.globalScheduling.goal.RegisterServiceGoal;



public class BasicCapability extends AbstractGSCapability{
	
	private  Logger log;

	public BasicCapability(){
		super();
	}
	
	@Override
	protected void setup() {
		log=LogManager.getLogger();
		super.setup();
	}
}
