// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.auth;

import org.apache.wicket.util.io.IClusterable;

public class PropagatedCredential implements IClusterable {
   private String uuid;
   private String userLogin;
   private String nombreUsuario;
   private String roles;
   private String propagatedPass;
   private String permisos;

   public PropagatedCredential() {
   }

   public String getUuid() {
      return this.uuid;
   }

   public String getUserLogin() {
      return this.userLogin;
   }

   public String getNombreUsuario() {
      return this.nombreUsuario;
   }

   public String getRoles() {
      return this.roles;
   }

   public String getPropagatedPass() {
      return this.propagatedPass;
   }

   public String getPermisos() {
      return this.permisos;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public void setUserLogin(String userLogin) {
      this.userLogin = userLogin;
   }

   public void setNombreUsuario(String nombreUsuario) {
      this.nombreUsuario = nombreUsuario;
   }

   public void setRoles(String roles) {
      this.roles = roles;
   }

   public void setPropagatedPass(String propagatedPass) {
      this.propagatedPass = propagatedPass;
   }

   public void setPermisos(String permisos) {
      this.permisos = permisos;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PropagatedCredential)) {
         return false;
      } else {
         PropagatedCredential other = (PropagatedCredential)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$uuid = this.getUuid();
            Object other$uuid = other.getUuid();
            if (this$uuid == null) {
               if (other$uuid != null) {
                  return false;
               }
            } else if (!this$uuid.equals(other$uuid)) {
               return false;
            }

            Object this$userLogin = this.getUserLogin();
            Object other$userLogin = other.getUserLogin();
            if (this$userLogin == null) {
               if (other$userLogin != null) {
                  return false;
               }
            } else if (!this$userLogin.equals(other$userLogin)) {
               return false;
            }

            Object this$nombreUsuario = this.getNombreUsuario();
            Object other$nombreUsuario = other.getNombreUsuario();
            if (this$nombreUsuario == null) {
               if (other$nombreUsuario != null) {
                  return false;
               }
            } else if (!this$nombreUsuario.equals(other$nombreUsuario)) {
               return false;
            }

            label62: {
               Object this$roles = this.getRoles();
               Object other$roles = other.getRoles();
               if (this$roles == null) {
                  if (other$roles == null) {
                     break label62;
                  }
               } else if (this$roles.equals(other$roles)) {
                  break label62;
               }

               return false;
            }

            label55: {
               Object this$propagatedPass = this.getPropagatedPass();
               Object other$propagatedPass = other.getPropagatedPass();
               if (this$propagatedPass == null) {
                  if (other$propagatedPass == null) {
                     break label55;
                  }
               } else if (this$propagatedPass.equals(other$propagatedPass)) {
                  break label55;
               }

               return false;
            }

            Object this$permisos = this.getPermisos();
            Object other$permisos = other.getPermisos();
            if (this$permisos == null) {
               if (other$permisos != null) {
                  return false;
               }
            } else if (!this$permisos.equals(other$permisos)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PropagatedCredential;
   }

   public int hashCode() {
      int PRIME = 0; //TODO check this attribute
      int result = 1;
      Object $uuid = this.getUuid();
      result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
      Object $userLogin = this.getUserLogin();
      result = result * 59 + ($userLogin == null ? 43 : $userLogin.hashCode());
      Object $nombreUsuario = this.getNombreUsuario();
      result = result * 59 + ($nombreUsuario == null ? 43 : $nombreUsuario.hashCode());
      Object $roles = this.getRoles();
      result = result * 59 + ($roles == null ? 43 : $roles.hashCode());
      Object $propagatedPass = this.getPropagatedPass();
      result = result * 59 + ($propagatedPass == null ? 43 : $propagatedPass.hashCode());
      Object $permisos = this.getPermisos();
      result = result * 59 + ($permisos == null ? 43 : $permisos.hashCode());
      return result;
   }

   public String toString() {
      return "PropagatedCredential(uuid=" + this.getUuid() + ", userLogin=" + this.getUserLogin() + ", nombreUsuario=" + this.getNombreUsuario() + ", roles=" + this.getRoles() + ", propagatedPass=" + this.getPropagatedPass() + ", permisos=" + this.getPermisos() + ")";
   }
}
