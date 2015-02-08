package mas.globalScheduling.belief;

import java.util.Set;

import bdi4jade.belief.Belief;
import bdi4jade.core.BeliefBase;

public class customBelief extends BeliefBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public customBelief(Set<Belief<?>> beliefs) {
		super(beliefs);
	}

	@Override
	public void reviewBeliefs() {
		super.reviewBeliefs();
	}

}
