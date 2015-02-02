package mas.customer;

import java.util.HashSet;
import java.util.Set;
import mas.customer.goal.GenerateJobGoal;
import mas.customer.goal.dispatchJobGoal;
import mas.customer.plan.DispatchJobPlan;
import mas.customer.plan.jobGeneratePlan;
import mas.job.job;
import bdi4jade.belief.Belief;
import bdi4jade.belief.TransientBelief;
import bdi4jade.plan.Plan;
import bdi4jade.util.plan.SimplePlan;

public class basicCapability extends AbstractbasicCapability{

	private static final long serialVersionUID = 1L;
	public static String CURR_JOB = "Current-job-Customer";
	// If you want to change the jobgenerator,
	// simply override the getbelief method
	
	@Override
	protected void setup() {
		super.setup();
		
		Belief<job> currentJob = 
				new TransientBelief<job>(CURR_JOB);
		this.getBeliefBase().addBelief(currentJob);
	}
}
