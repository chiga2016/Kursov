
package com.jpasample.model;

import javax.persistence.*;

public class EventMonitor {
   @PrePersist
   public void prePersistMethod(Object e) {   System.err.println(">>>>>>>>>>>>> PrePersist на объекте "+e + "<<<<<<<<<<<<<<<<<<");   }
   @PostPersist
   public void postPersistMethod(Object e) {  System.err.println(">>>>>>>>>>>>> PostPersist на объекте "+e + "<<<<<<<<<<<<<<<<<<");   }

   @PreRemove
   public void preRemoveMethod(Object e) {   System.err.println(">>>>>>>>>>>>> PreRemove на объекте "+e + "<<<<<<<<<<<<<<<<<<");   }
   @PostRemove
   public void postRemoveMethod(Object e) {   System.err.println(">>>>>>>>>>>>> PostRemove на объекте "+e + "<<<<<<<<<<<<<<<<<<");   }

   @PreUpdate
   public void preUpdateMethod(Object e) {   System.err.println(">>>>>>>>>>>>> PreUpdate на объекте "+e + "<<<<<<<<<<<<<<<<<<");   }
   @PostUpdate
   public void postUpdateMethod(Object e) {   System.err.println(">>>>>>>>>>>>> PostUpdate на объекте "+e + "<<<<<<<<<<<<<<<<<<");   }

   @PostLoad
   public void postLoadMethod(Object e) {   System.err.println(">>>>>>>>>>>>> PostLoad на объекте "+e + "<<<<<<<<<<<<<<<<<<");   }

};