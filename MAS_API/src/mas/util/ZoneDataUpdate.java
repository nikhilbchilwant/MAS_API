package mas.util;

import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
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

	public void send(AID blackboard_AID, ZoneDataUpdate zdu, Agent Sender) {
		ACLMessage update=new ACLMessage(ACLMessage.INFORM);
		update.addReceiver(blackboard_AID);
		try {
			update.setContentObject(zdu);
		} catch (IOException e) {
			e.printStackTrace();
		}
		update.setConversationId(MessageIds.UpdateParameter);
		Sender.send(update);
		
	}
}
