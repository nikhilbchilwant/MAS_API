package mas.util;

public class ID {

//It is recommonded to keep each String in ID.java to be kept unique in order to avoid confusion and debugging purpose
	//It is recommonded to keep each String in ID.java to be kept unique in order to avoid confusion and debugging purpose
	public class MAS {
		public static final String main_container_ipaddress = "127.0.0.1";
	}
	
	public class Blackboard{
		public static final String Service="blackboard";
		public static final String LocalName="blackboard";

		
		public class ZoneData{
			
		}
	}
	
	public class Customer{
		public static final String Service="customer";
		public static final String LocalName="customer";
		

		public class BeliefBase {
			public static final String JobList="JobList";
			public static final String JOB_GENERATOR = "JOB-GENERATOR";
			public static final String blackAgent = "blackboard-agent";
			public static final String CURRENT_JOB = "Current-job-Customer";
		}
		
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
		public class BeliefBase {
			public static final String blackAgent = "blackboard-agent";
			public static final String machine = "my-machine";
			public static final String jobQueue = "my-job-list";
			public static final String maintAgent = "my-maintenanceAgent";
			public static final String globalSchAgent = "my-gsAgent";
			public static final String dataTracker = "my-data-tracker";
		}

		
		public class ZoneData{
			public static final String WaitingTime="WaitingTime";
			public static final String bidForJob ="myBidForJob";
			public static final String jobQueue ="myJobQueueFormyMachine";
			
		}
	}

	
	public class GlobalScheduler{
		public static final String Service ="global-scheduling-agent";
		public static final String LocalName ="GlobalSchedulingAgent";
		
		/*public class Parameters{

		}
*/		
		public class ZoneData{
			//contains confirmed jobs
			public static final String WorkOrder="WorkOrderData"; 
			//jobs under negotiation
			public static final String NegotiationJob = "jobsUnderNegotiation"; 
			public static final String ConfirmedOrder = "ConfirmedOrder";
			public static final String jobForMachine = "job-for-machine";
			public static final String askforBid = "ask-for-bid";
			public static final String waitingTime = "waiting-time"; //queue of jobs with calculated expected waiting time
			public static final String GetWaitingTime = "LocalSchedulingwaiting-time";//contains queue of jobs with  
		}
	}
	
	public class Maintenance{
		public static final String Service="machine-simulator-maint";
		public static final String LocalName ="machine-simulator-maint";
		
/*		public class Parameters{
			
		}*/
		
		public class ZoneData{
			
		}
		
		public class BeliefBase {
			public static final String blackAgent = "blackboard-agent";
			public static final String machine = "my-machine";
			public static final String globalSchAgent = "my-gsAgent";
			public static final String dataTracker = "my-data-tracker";
			public static final String maintenanceJob = "my-machine-aintenance-tracker";
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
