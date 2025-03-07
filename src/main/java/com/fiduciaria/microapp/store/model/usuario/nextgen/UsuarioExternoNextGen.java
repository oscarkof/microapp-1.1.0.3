// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.usuario.nextgen;

import java.io.Serializable;

public class UsuarioExternoNextGen implements Serializable {
   private String email;
   private String origen;
   private String subject;
   private String fidutoken;
   private String user;
   private CredencialNextGen password;
   private CredencialNextGen oldPassword;
   private Boolean isDefault;

   public UsuarioExternoNextGen() {
   }

   public String getEmail() {
      return this.email;
   }

   public String getOrigen() {
      return this.origen;
   }

   public String getSubject() {
      return this.subject;
   }

   public String getFidutoken() {
      return this.fidutoken;
   }

   public String getUser() {
      return this.user;
   }

   public CredencialNextGen getPassword() {
      return this.password;
   }

   public CredencialNextGen getOldPassword() {
      return this.oldPassword;
   }

   public Boolean getIsDefault() {
      return this.isDefault;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setOrigen(String origen) {
      this.origen = origen;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public void setFidutoken(String fidutoken) {
      this.fidutoken = fidutoken;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public void setPassword(CredencialNextGen password) {
      this.password = password;
   }

   public void setOldPassword(CredencialNextGen oldPassword) {
      this.oldPassword = oldPassword;
   }

   public void setIsDefault(Boolean isDefault) {
      this.isDefault = isDefault;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UsuarioExternoNextGen)) {
         return false;
      } else {
         UsuarioExternoNextGen other = (UsuarioExternoNextGen)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label107: {
               Object this$isDefault = this.getIsDefault();
               Object other$isDefault = other.getIsDefault();
               if (this$isDefault == null) {
                  if (other$isDefault == null) {
                     break label107;
                  }
               } else if (this$isDefault.equals(other$isDefault)) {
                  break label107;
               }

               return false;
            }

            Object this$email = this.getEmail();
            Object other$email = other.getEmail();
            if (this$email == null) {
               if (other$email != null) {
                  return false;
               }
            } else if (!this$email.equals(other$email)) {
               return false;
            }

            Object this$origen = this.getOrigen();
            Object other$origen = other.getOrigen();
            if (this$origen == null) {
               if (other$origen != null) {
                  return false;
               }
            } else if (!this$origen.equals(other$origen)) {
               return false;
            }

            label86: {
               Object this$subject = this.getSubject();
               Object other$subject = other.getSubject();
               if (this$subject == null) {
                  if (other$subject == null) {
                     break label86;
                  }
               } else if (this$subject.equals(other$subject)) {
                  break label86;
               }

               return false;
            }

            label79: {
               Object this$fidutoken = this.getFidutoken();
               Object other$fidutoken = other.getFidutoken();
               if (this$fidutoken == null) {
                  if (other$fidutoken == null) {
                     break label79;
                  }
               } else if (this$fidutoken.equals(other$fidutoken)) {
                  break label79;
               }

               return false;
            }

            label72: {
               Object this$user = this.getUser();
               Object other$user = other.getUser();
               if (this$user == null) {
                  if (other$user == null) {
                     break label72;
                  }
               } else if (this$user.equals(other$user)) {
                  break label72;
               }

               return false;
            }

            Object this$password = this.getPassword();
            Object other$password = other.getPassword();
            if (this$password == null) {
               if (other$password != null) {
                  return false;
               }
            } else if (!this$password.equals(other$password)) {
               return false;
            }

            Object this$oldPassword = this.getOldPassword();
            Object other$oldPassword = other.getOldPassword();
            if (this$oldPassword == null) {
               if (other$oldPassword != null) {
                  return false;
               }
            } else if (!this$oldPassword.equals(other$oldPassword)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UsuarioExternoNextGen;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $isDefault = this.getIsDefault();
      result = result * 59 + ($isDefault == null ? 43 : $isDefault.hashCode());
      Object $email = this.getEmail();
      result = result * 59 + ($email == null ? 43 : $email.hashCode());
      Object $origen = this.getOrigen();
      result = result * 59 + ($origen == null ? 43 : $origen.hashCode());
      Object $subject = this.getSubject();
      result = result * 59 + ($subject == null ? 43 : $subject.hashCode());
      Object $fidutoken = this.getFidutoken();
      result = result * 59 + ($fidutoken == null ? 43 : $fidutoken.hashCode());
      Object $user = this.getUser();
      result = result * 59 + ($user == null ? 43 : $user.hashCode());
      Object $password = this.getPassword();
      result = result * 59 + ($password == null ? 43 : $password.hashCode());
      Object $oldPassword = this.getOldPassword();
      result = result * 59 + ($oldPassword == null ? 43 : $oldPassword.hashCode());
      return result;
   }

   public String toString() {
      return "UsuarioExternoNextGen(email=" + this.getEmail() + ", origen=" + this.getOrigen() + ", subject=" + this.getSubject() + ", fidutoken=" + this.getFidutoken() + ", user=" + this.getUser() + ", password=" + this.getPassword() + ", oldPassword=" + this.getOldPassword() + ", isDefault=" + this.getIsDefault() + ")";
   }
}
