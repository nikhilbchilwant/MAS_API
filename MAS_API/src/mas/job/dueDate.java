package mas.job;
import java.io.Serializable;
import java.util.Date;


public class dueDate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Date ddate;
	
	public String getDate(){
		return ddate.toString();
	}
	
}
