// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.usuario.nextgen;

import java.io.Serializable;

public class CredencialNextGen implements Serializable {
   private String value;

   public CredencialNextGen() {
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CredencialNextGen)) {
         return false;
      } else {
         CredencialNextGen other = (CredencialNextGen)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$value = this.getValue();
            Object other$value = other.getValue();
            if (this$value == null) {
               if (other$value != null) {
                  return false;
               }
            } else if (!this$value.equals(other$value)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CredencialNextGen;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $value = this.getValue();
      result = result * 59 + ($value == null ? 43 : $value.hashCode());
      return result;
   }

   public String toString() {
      return "CredencialNextGen(value=" + this.getValue() + ")";
   }
}
