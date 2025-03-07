// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.seguridad;

import org.apache.wicket.util.io.IClusterable;

public class CredencialPojo implements IClusterable {
   private String passActual;
   private String passNuevo;
   private String passConfirmar;

   public CredencialPojo() {
   }

   public String getPassActual() {
      return this.passActual;
   }

   public String getPassNuevo() {
      return this.passNuevo;
   }

   public String getPassConfirmar() {
      return this.passConfirmar;
   }

   public void setPassActual(String passActual) {
      this.passActual = passActual;
   }

   public void setPassNuevo(String passNuevo) {
      this.passNuevo = passNuevo;
   }

   public void setPassConfirmar(String passConfirmar) {
      this.passConfirmar = passConfirmar;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CredencialPojo)) {
         return false;
      } else {
         CredencialPojo other = (CredencialPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label47: {
               Object this$passActual = this.getPassActual();
               Object other$passActual = other.getPassActual();
               if (this$passActual == null) {
                  if (other$passActual == null) {
                     break label47;
                  }
               } else if (this$passActual.equals(other$passActual)) {
                  break label47;
               }

               return false;
            }

            Object this$passNuevo = this.getPassNuevo();
            Object other$passNuevo = other.getPassNuevo();
            if (this$passNuevo == null) {
               if (other$passNuevo != null) {
                  return false;
               }
            } else if (!this$passNuevo.equals(other$passNuevo)) {
               return false;
            }

            Object this$passConfirmar = this.getPassConfirmar();
            Object other$passConfirmar = other.getPassConfirmar();
            if (this$passConfirmar == null) {
               if (other$passConfirmar != null) {
                  return false;
               }
            } else if (!this$passConfirmar.equals(other$passConfirmar)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CredencialPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $passActual = this.getPassActual();
      result = result * 59 + ($passActual == null ? 43 : $passActual.hashCode());
      Object $passNuevo = this.getPassNuevo();
      result = result * 59 + ($passNuevo == null ? 43 : $passNuevo.hashCode());
      Object $passConfirmar = this.getPassConfirmar();
      result = result * 59 + ($passConfirmar == null ? 43 : $passConfirmar.hashCode());
      return result;
   }

   public String toString() {
      return "CredencialPojo(passActual=" + this.getPassActual() + ", passNuevo=" + this.getPassNuevo() + ", passConfirmar=" + this.getPassConfirmar() + ")";
   }
}
