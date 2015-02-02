package mas.blackboard.nameZoneData;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class NamedZoneData implements ZoneDataName
{
   private String name = null;

   public NamedZoneData(String name) {
//      Validate.notNull(name);
      this.name = name;
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
