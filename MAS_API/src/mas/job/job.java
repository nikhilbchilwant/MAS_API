package mas.job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class job implements Serializable{

	/** Represents a manufacturing shop floor job
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Required parameters
	private int jobNo;
	private int jobID;
	private double CPN;
	private double Cost;
	private double Penalty;
	private Date startTime;
	private Date duedate;
	private long processingTime;
	private Date generationTime;

	//Optional parameters initialized to default values
	public double completionTime;
	public int operationNo = 1;
	public int acceptance = 0;
	public double slack;
	private double regret;
	private int position;

	private static final double lowRegretMultiplier = 1,
			MediumRegretMultiplier = 2,
			HighRegretMultiplier = 3;

	private ArrayList<jobDimension> dimensions;						
	private ArrayList<jobAttribute> attributes;	
	private ArrayList<jobOperation> operations;
	private ArrayList<Date> globalDueDate;
	private ArrayList<Date> localDueDate;

	public ArrayList<jobOperation> getOperations() {
		return operations;
	}

	public void setOperations(ArrayList<jobOperation> operations) {
		this.operations = operations;
	}

	public static final double BidByLSA = 777;//LSA:LocalSchedulingAgent
	public double waitingTime;
	public double profit;
	public double delTime;
	public double delStatus;

	public int getPosition() {
		return position;
	}

	public double getSlack() {
		return slack;
	}

	public void setSlack(double slack) {
		this.slack = slack;
	}

	public double getRegret() {
		return regret;
	}

	public void setRegret(double regret) {
		this.regret = regret;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public static class Builder {
		//Required parameters
		private int jobNo;
		private int jobID;
		private double CPN;
		private double Cost;
		private double Penalty;
		private Date startTime;
		private Date dDate;
		private long procTime;
		private Date genTime;
		// Optional parameters - initialized to default values
		private ArrayList<jobDimension> dimensions;						
		private ArrayList<jobAttribute> attributes;	

		public Builder(int jobID) {
			this.jobID = jobID;
			this.dimensions = new ArrayList<jobDimension>();
			this.attributes = new ArrayList<jobAttribute>();
		}

		public Builder jobCost(double val)
		{ Cost = val; return this; }

		public Builder jobCPN(double val)
		{ CPN = val; return this; }

		public Builder jobPenalty(double val)
		{ Penalty = val; return this; }

		public Builder jobDueDateTime(Date val)
		{ dDate = val; return this; }

		public Builder jobStartTime(Date val)
		{ startTime = val; return this; }

		public Builder jobProcTime(long val)
		{ procTime = val; return this; }

		public Builder jobGenTime(Date val)
		{ genTime = val; return this; }
		
		public Builder jobGenTime(Long val)
		{ genTime.setTime(val); return this; }

		public Builder jobDimensions(ArrayList<jobDimension> val)
		{ dimensions.addAll(val); return this; }

		public Builder jobAttrbitues(ArrayList<jobAttribute> val)
		{ attributes.addAll(val); return this; }

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
		this.dimensions = new ArrayList<jobDimension>();
		this.attributes = new ArrayList<jobAttribute>();
		dimensions.addAll(builder.dimensions);
		attributes.addAll(builder.attributes);
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

	public int getJobID(){
		return this.jobID;
	}

	public int getJobNo() {
		return jobNo;
	}

	public void setJobNo(int jobNo) {
		this.jobNo = jobNo;
	}

	public double getCPN() {
		return CPN;
	}

	public void setCPN(double cPN) {
		CPN = cPN;
	}

	public double getPenalty() {
		return Penalty;
	}

	public void setPenalty(double penalty) {
		Penalty = penalty;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public void setStartTime(long startTime) {
		this.startTime.setTime(startTime);
	}

	public long getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(long processingTime) {
		this.processingTime = processingTime;
	}

	public Date getGenerationTime() {
		return generationTime;
	}

	public void setGenerationTime(Date generationTime) {
		this.generationTime = generationTime;
	}
	
	public void setGenerationTime(long generationTime) {
		this.generationTime.setTime(generationTime);
	}

	public double getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(double completionTime) {
		this.completionTime = completionTime;
	}

	public int getAcceptance() {
		return acceptance;
	}

	public void setAcceptance(int acceptance) {
		this.acceptance = acceptance;
	}

	public ArrayList<jobAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<jobAttribute> attributes) {
		this.attributes = attributes;
	}

	public ArrayList<Date> getGlobalDueDate() {
		return globalDueDate;
	}

	public void setGlobalDueDate(ArrayList<Date> globalDueDate) {
		this.globalDueDate = globalDueDate;
	}

	public double getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}

	public static double getBidbylsa() {
		return BidByLSA;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public Date getDuedate() {
		return duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public ArrayList<jobDimension> getDimensions() {
		return dimensions;
	}

	public void setDimensions(ArrayList<jobDimension> dimensions) {
		this.dimensions = dimensions;
	}

}

