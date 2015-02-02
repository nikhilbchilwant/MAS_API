package mas.util;

import jade.util.leap.Serializable;

public class ZoneDataUpdate implements Serializable {
	String name;
	Object value;
	boolean toBeAppended;
	public ZoneDataUpdate(String ZoneDataName, Object value, boolean ToAppend) {
		this.name=ZoneDataName;
		this.value=value;
		this.toBeAppended=ToAppend;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Object getValue(){
		return this.value;
	}
	
	public boolean toAppendToCurrentValue(){
		return this.toBeAppended;
	}
}
