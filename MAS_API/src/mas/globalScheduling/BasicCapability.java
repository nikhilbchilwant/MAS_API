package mas.globalScheduling;

import mas.globalScheduling.goal.RegisterServiceGoal;



public class BasicCapability extends AbstractGSCapability{

	protected void setup() {
	myAgent.addGoal(new RegisterServiceGoal());
	}
}
