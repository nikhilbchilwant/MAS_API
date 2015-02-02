package mas.util;

public class ID {
	public class Agents {
		public static final String Customer="customer";
		public static final String GlobalScheduling="scheduling-agent";
		public static final String LocalScheduling="machine-simulator-schedule";
		public static final String Maintenance="machine-simulator-maint";
		public static final String Machine="machine-simulator-machine";
		public static final String Blackboard="blackboard";
	}
	public class CustomeParameters {
		public static final String JobList="JobList";
		public static final String NegotiationBidList="NegotiationBids";
		public static final String IsJobAcceptedList="IsJobAcceptedList";
	}
		
	public	class CustomerZone {
		public static final String JobList = "JobList";
		public static final String Negotiation = "jobsUnderNegotiation";
		public static final String acceptedJobs = "jobsAccepted";
	}
	
	public class LocalSchedulerParameters {
		public static final String WaitingTime="WaititngTimeData";
		public static final String Bid="BidData";
		public static final String JobForMachine="JobForMachineData";
		public static final String WorkOrder="WorkOrderData";
	}
	
	public class GSAParameters {
		public static final String WorkOrder="WorkOrderData";
		public static final String Negotiation = "jobsUnderNegotiation";
		public static final String ConfirmedOrder = "ConfirmedOrder";
	}
	
	public class MachineParameters {
	}
	
	public class BeliefId {
		public static final String Customer="customer";
		public static final String GlobalScheduling="globalscheduling";
		public static final String LocalScheduling="localscheduling";
		public static final String Maintenance="maintenance";
		public static final String Machine="machine";
	}
}
