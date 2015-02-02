package mas.blackboard.zonespace;

import jade.core.AID;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import mas.blackboard.nameZoneData.NamedZoneData;
import mas.blackboard.namezonespace.NamedZoneSpace;
import mas.blackboard.zonedata.ZoneData;

/**
 * @author Anand Prajapati
 * @param <V>
 */

public class ZoneSpace implements ZoneSpaceIFace{
	
	private String Zname;
	private HashMap<NamedZoneData, ZoneData> Zdata;
	private Set<AID> subscribers;
	
	public ZoneSpace(NamedZoneSpace n){
		this.Zname = n.getLocalName();
		this.Zdata = new HashMap<NamedZoneData,ZoneData>();
	}
	
	/*public static ZoneSpace newInstance(NamedZone name){
		return new ZoneSpace(name);
	}*/
	@Override
	public void createZoneData(NamedZoneData name) {
		if(! Zdata.containsKey(name)){
			ZoneData zd = new ZoneData(name);
			Zdata.put(name, zd);
		}
	}
	
	public void subscribeZoneData(String ZoneDataName, AID subscriber){
		NamedZoneData nzd=new NamedZoneData(ZoneDataName);
		
		if(findZoneData(nzd)!=null){
			Zdata.get(nzd).subscribe(subscriber);
		}
	}
	
	public String getName(){
		return this.Zname;
	}
	
	@Override
	public void dropZone() {
		Zdata = null;
	}
	@Override
	public void insertItem(NamedZoneData var, Object obj) {
		if(findZoneData(var)==null){
			createZoneData(var);
		}
		findZoneData(var).addItem(obj);
		
	}
	@Override
	public void removeItem(Object obj) {
		Zdata.remove(obj);
	}

	@Override
	public ZoneData findZoneData(NamedZoneData var) {
		return Zdata.get(var);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			        .append(Zname)
			        .append(Zdata)
			        .toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ZoneSpace other = (ZoneSpace) obj;
//		if (Zdata == null) {
//			if (other.Zdata != null)
//				return false;
//		} else if (!Zdata.equals(other.Zdata))
//			return false;
//		if (Zname == null) {
//			if (other.Zname != null)
//				return false;
//		} else if (!Zname.equals(other.Zname))
//			return false;
//		return true;
		if(obj instanceof ZoneSpace){
	        final ZoneSpace other = (ZoneSpace) obj;
	        return new EqualsBuilder()
	            .append(Zname, other.Zname)
	            .append(Zdata, other.Zdata)
	            .isEquals();
	    } else {
	        return false;
	    }
	}

	@Override
	public void unsubscribeZoneData(String ZoneDataName, AID subscriber) {
		NamedZoneData nzd=new NamedZoneData(ZoneDataName);
		
		if(findZoneData(nzd)!=null){
			Zdata.get(nzd).unsubscribe(subscriber);
		}
		
	}
	
	public void subscribeZoneSpace(AID subscriber){
		Iterator it= Zdata.entrySet().iterator();
		while(it.hasNext()){
			
		}
	}

	
	
}
