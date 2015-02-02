package mas.job;
import java.io.Serializable;


public class jobDimension implements Serializable{
		private String title;
    	private double target;
    	private double achieved;
    	
    	public void setTitle(String name){
    		this.title = name;
    	}
    	
    	public void setTargetDimension(double d){
    		this.target = d;
    	}
    	public void setAchievedDimension(double d){
    		this.achieved = d;
    	}
}
