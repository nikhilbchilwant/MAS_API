package mas.job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class job implements Serializable{
	
	private static final long serialVersionUID = 4489382432552880608L;
	//Required parameters
	private int jobNo;
	private int jobID;
	private double CPN;
	private double Cost;
	private double Penalty;
	private double startTime;
	private Date duedate;
	private double processingTime;
	private double generationTime;
	
	//Optional parameters initialized to default values
	public double completionTime;
    public int operationNo = 1;
	public int acceptance = 0;
	public double slack;
	private double regret;
	
	private static final double lowRegretMultiplier = 1,
				MediumRegretMultiplier = 2,
				HighRegretMultiplier = 3;
	
	private ArrayList<jobDimension> dimensions;						
	private ArrayList<jobAttribute> attributes;	
	private ArrayList<jobOperation> operations;
	private ArrayList<Date> globalDueDate;
	private ArrayList<Date> localDueDate;
	
	public static final double BidByLSA = 777;//LSA:LocalSchedulingAgent
	public double waitingTime;
	public double profit;
	public double delTime;
	public double delStatus;

	public static class Builder {
		//Required parameters
		private int jobNo;
		private int jobID;
		private double CPN;
		private double Cost;
		private double Penalty;
		private double startTime;
		private Date dDate;
		private double procTime;
		private double genTime;
		// Optional parameters - initialized to default values
		
		public Builder(int jobID) {
			this.jobID = jobID;
		}
		
		public Builder jobCost(double val)
		{ Cost = val; return this; }
		
		public Builder jobCPN(double val)
		{ CPN = val; return this; }
		
		public Builder jobPenalty(double val)
		{ Penalty = val; return this; }
		
		public Builder jobDueDateTime(Date val)
		{ dDate = val; return this; }
		
		public Builder jobStartTime(double val)
		{ startTime = val; return this; }
		
		public Builder jobProcTime(double val)
		{ procTime = val; return this; }
		
		public Builder jobGenTime(double val)
		{ genTime = val; return this; }
		
		public Builder jobDimensions(ArrayList<jobDimension> val)
		{ jobDimensions(val); return this; }
		
		public Builder jobAttrbitues(ArrayList<jobAttribute> val)
		{ jobAttrbitues(val); return this; }
		
		public job build() {
			return new job(this);
		}
	}
	private job(Builder builder) {
		jobID = builder.jobID;
		jobNo = builder.jobNo;
		CPN = builder.CPN;
		Cost = builder.Cost;
		Penalty = builder.Penalty;
		processingTime = builder.procTime;
		duedate = builder.dDate;
        generationTime = builder.genTime;
	}
        
   public double getRegretMultiplier(){
    	if(this.regret < 1.0)
    		return lowRegretMultiplier;
    	else if( this.regret < 1.1)
    		return MediumRegretMultiplier;
    	else
    		return HighRegretMultiplier;
    }
   
   @Override
   public boolean equals(Object o) {
	   if( o == this )
		   return true;
	   if( !(o instanceof job))
		   return false;
	   
	   job j = (job)o;
	   return (this.jobID == j.jobID) &&
			   (this.jobNo == j.jobNo);
   }
}

