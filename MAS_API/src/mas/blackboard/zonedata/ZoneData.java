package mas.blackboard.zonedata;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import mas.blackboard.nameZoneData.NamedZoneData;
import mas.util.MessageIds;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ZoneData implements ZoneDataIFace, Serializable{
	private NamedZoneData name;
	private Set<Object> data;
	public Set<AID> subscribers;
	private Logger log;
	public String UpdateMessageID;
	private Agent bb; //needed for sending update message
	
	public ZoneData(NamedZoneData name2, String UpdateMsgID, Agent blackboard){
		log=LogManager.getLogger();
		this.name = name2;
		this.data = new HashSet<Object>();
		this.subscribers=new HashSet<AID>();
		this.UpdateMessageID=UpdateMessageID; //ID of message to be used while sending update of data
		this.bb=blackboard;
	}
	
	/*public static ZoneData newInstance(NamedZone title){
		return new ZoneData(title.name());
	}*/
	
	public String getName(){
		return this.name.name();
	}
	
	/*@Override
	public boolean updateItem(Object oldObj, Object newObj){
		if(this.data.contains(oldObj)){
			this.data.remove(oldObj);
			this.data.add(newObj);
		}

		return true;
	}*/
		
	
	
	
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
		sendUpdate();
	
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

	public void sendUpdate(){
		ACLMessage update=new ACLMessage(ACLMessage.INFORM);		
		update.setConversationId(UpdateMessageID);
		for(AID reciever : subscribers){
			update.addReceiver(reciever);
			
		}
		try {
			update.setContentObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bb.send(update);
		log.info("sent update of "+name.name());
	}

}
