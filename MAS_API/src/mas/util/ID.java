package mas.util;

public class ID {

//It is recommonded to keep each String in ID.java to be kept unique in order to avoid confusion and debugging purpose
	public class Blackboard{
		public static final String Service="blackboard";
		public static final String LocalName="blackboard";

		
		public class ZoneData{
			
		}
	}
	
	public class Customer{
		public static final String Service="customer";
		public static final String LocalName="customer";
/*		public class Parameters{
			public static final String JobList="JobList";
			public static final String NegotiationBidList="NegotiationBids";
			public static final String IsJobAcceptedList="IsJobAcceptedList";
		}*/
		
		public class ZoneData{
			public static final String JobList = "JobList";
			public static final String Negotiation = "jobsUnderNegotiation";
			public static final String acceptedJobs = "jobsAccepted";
		}
	}
	
	public class LocalScheduler{
		public static final String Service="machine-simulator-schedule";
		public static final String LocalName="LocalSchedulingAgent";
		/*public class Parameters{
			public static final String WaitingTime="WaititngTimeData";
			public static final String Bid="BidData";
			public static final String JobForMachine="JobForMachineData";
			public static final String WorkOrder="WorkOrderData";
		}*/
		
		public class ZoneData{
			public static final String WaitingTime="WaitingTime";
		}
	}

	
	public class GlobalScheduler{
		public static final String Service ="global-scheduling-agent";
		public static final String LocalName ="GlobalSchedulingAgent";
		/*public class Parameters{

		}
*/		
		public class ZoneData{
			public static final String WorkOrder="WorkOrderData"; //contains confirmed jobs
			public static final String NegotiationJob = "jobsUnderNegotiation"; //jobs under negotiation
//			public static final String ConfirmedOrder = "ConfirmedOrder";
		}
	}
	
	public class Maintenance{
		public static final String Service="machine-simulator-maint";
		public static final String LocalName ="machine-simulator-maint";
		
/*		public class Parameters{
			
		}*/
		
		public class ZoneData{
			
		}
	}
	
	public class Machine{
		public static final String Service="machine-simulator-machine";
		public static final String LocalName ="machine-simulator-machine";
		
		/*public class Parameters{
			
		}*/
		
		public class ZoneData{
			
		}
	}
	
	

		
}
