package mas.job;
import java.io.Serializable;


public class jobOperation implements operationInterface,Serializable{
	private double processingTime;

	@Override
	public double meanProcessingTime() {
		return processingTime;
	}
}
