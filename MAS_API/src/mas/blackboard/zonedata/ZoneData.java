package mas.blackboard.zonedata;

import jade.core.AID;

import java.util.HashSet;
import java.util.Set;

import mas.blackboard.nameZoneData.NamedZoneData;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ZoneData implements ZoneDataIFace{
	private NamedZoneData name;
	private Set<Object> data;
	private Set<AID> subscribers;
	private Logger log;
	
	public ZoneData(NamedZoneData name2){
		log=LogManager.getLogger();
		this.name = name2;
		this.data = new HashSet<Object>();
		this.subscribers=new HashSet<AID>();
	}
	
	/*public static ZoneData newInstance(NamedZone title){
		return new ZoneData(title.name());
	}*/
	
	public String getName(){
		return this.name.name();
	}
	
	@Override
	public boolean updateItem(Object oldObj, Object newObj){
		if(this.data.contains(oldObj)){
			this.data.remove(oldObj);
			this.data.add(newObj);
		}
//			this.data.removeAll(arg0)
		
		//How hashset compares object? => .equals()
		return true;
	}
		
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ZoneData){
	        final ZoneData other = (ZoneData) obj;
	        return new EqualsBuilder()
	            .append(name, other.name)
	            .append(data, other.data)
	            .isEquals();
	    } else {
	        return false;
	    }
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(name)
				.append(data)
				.toHashCode();
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append(name)
				.append(data.toString())
				.toString();
	}

	@Override
	public void addItem(Object obj) {
		this.data.add(obj);
	}

	@Override
	public void removeItem(Object obj) {
		if(this.data.contains(obj)){
			this.data.remove(obj);
		}
	}

	@Override
	public Object[] getAllItem() {
		return this.data.toArray();
	}

	@Override
	public void subscribe(AID agent) {
		subscribers.add(agent);
		log.info(agent.getLocalName()+" subscribed for "+name);
		
	}

	@Override
	public void unsubscribe(AID agent) {
		subscribers.remove(agent);
		
	}

	@Override
	public void RemoveAllnAdd(Object obj) {
		data.clear();
		data.add(obj);		
	}
}
