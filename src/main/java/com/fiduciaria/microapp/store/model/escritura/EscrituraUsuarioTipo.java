// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.escritura;

import java.io.Serializable;

public class EscrituraUsuarioTipo implements Serializable {
   private String loginUsuario;
   private String tipo;

   public EscrituraUsuarioTipo() {
   }

   public String getLoginUsuario() {
      return this.loginUsuario;
   }

   public String getTipo() {
      return this.tipo;
   }

   public void setLoginUsuario(String loginUsuario) {
      this.loginUsuario = loginUsuario;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EscrituraUsuarioTipo)) {
         return false;
      } else {
         EscrituraUsuarioTipo other = (EscrituraUsuarioTipo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$loginUsuario = this.getLoginUsuario();
            Object other$loginUsuario = other.getLoginUsuario();
            if (this$loginUsuario == null) {
               if (other$loginUsuario != null) {
                  return false;
               }
            } else if (!this$loginUsuario.equals(other$loginUsuario)) {
               return false;
            }

            Object this$tipo = this.getTipo();
            Object other$tipo = other.getTipo();
            if (this$tipo == null) {
               if (other$tipo != null) {
                  return false;
               }
            } else if (!this$tipo.equals(other$tipo)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EscrituraUsuarioTipo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $loginUsuario = this.getLoginUsuario();
      result = result * 59 + ($loginUsuario == null ? 43 : $loginUsuario.hashCode());
      Object $tipo = this.getTipo();
      result = result * 59 + ($tipo == null ? 43 : $tipo.hashCode());
      return result;
   }

   public String toString() {
      return "EscrituraUsuarioTipo(loginUsuario=" + this.getLoginUsuario() + ", tipo=" + this.getTipo() + ")";
   }
}
