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
	protected NamedZoneData name;
	private Set<Object> data;
	private Set<AID> subscribers;
	private Logger log;
	private String UpdateMessageID;
	private Agent bb; //needed for sending update message
	
	public ZoneData(NamedZoneData name2, String UpdateMsgID, Agent blackboard){
		log=LogManager.getLogger();
		this.name = name2;
		this.data = new HashSet<Object>();
		this.subscribers=new HashSet<AID>();
		this.UpdateMessageID=UpdateMessageID; //ID of message to be used while sending update of data
		this.bb=blackboard;
	}
	

	public String getName(){
		return this.name.getName();
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
		log.info("updated "+data);
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
		log.info(agent.getLocalName()+" subscribed for "+name+" "+subscribers);
		
		
	}

	@Override
	public void unsubscribe(AID agent) {
		subscribers.remove(agent);
		
	}

	@Override
	public void RemoveAllnAdd(Object obj) {
		data.clear();
		data.add(obj);
		log.info("updated "+data);
		sendUpdate();
	
	}

	public String getUpdateMessageID(){
		return UpdateMessageID;
	}
	
	public Set<AID> getSubscribers(){
		return subscribers;
	}
	
	public Set<Object> getData(){
		return data;
	}
	public void sendUpdate(){
		
		ACLMessage update=new ACLMessage(ACLMessage.INFORM);		
		update.setConversationId(UpdateMessageID);
		
		for(AID reciever : getSubscribers()){
			update.addReceiver(reciever);
			log.info("sent update of "+name.getName()+" to "+reciever);
		}
		try {
			update.setContentObject((Serializable) getData());
		} catch (IOException e) {
			e.printStackTrace();
		}
		bb.send(update);
		
	}



	
	

}
