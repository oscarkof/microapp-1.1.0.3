// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.seguridad;

import java.io.Serializable;

public class PermisoPojo implements Serializable {
   public PermisoPojo() {
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PermisoPojo)) {
         return false;
      } else {
         PermisoPojo other = (PermisoPojo)o;
         return other.canEqual(this);
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PermisoPojo;
   }

   public int hashCode() {
      int result = 1;
      return 1;
   }

   public String toString() {
      return "PermisoPojo()";
   }
}
