package mas.job;
import java.io.Serializable;

 public class jobAttribute implements Serializable{
    	private String Name;
    	private boolean Conforming;
    	
    	public void setTitle(String s){
    		this.Name = s;
    	}
    	public void setConforming(Boolean b){
    		this.Conforming = b;
    	}
    }