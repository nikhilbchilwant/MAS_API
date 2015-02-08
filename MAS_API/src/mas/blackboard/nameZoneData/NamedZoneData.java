package mas.blackboard.nameZoneData;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class NamedZoneData implements ZoneDataName
{
   private String name = null;
   public String UpdateMsgID; //message ID of update message to be sent by Blackboard to subscribers of ZoneData


   public NamedZoneData(String name) {
      this.name = name;

   }
   
   public NamedZoneData(String name, String UpdateMsgID){
	   this.name=name;
	   this.UpdateMsgID=UpdateMsgID;
   }

   public String name() {
      return this.name;
   }

   public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof NamedZoneData)) return false;

      return this.name().equals(((NamedZoneData)obj).name());
   }

   public int hashCode() {
      return new HashCodeBuilder().append(this.name).append(NamedZoneData.class).toHashCode();
   }

   public String toString() {
      return "a NamedParameter \"" + this.name() + "\"";
   }
}
