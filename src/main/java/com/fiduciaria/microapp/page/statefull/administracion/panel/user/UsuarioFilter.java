// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.administracion.panel.user;

import org.apache.wicket.util.io.IClusterable;

public class UsuarioFilter implements IClusterable {
   private String usuario;
   private String loginUsuario;
   private String nombreUsuario;
   private String estado;

   public UsuarioFilter() {
   }

   public String getUsuario() {
      return this.usuario;
   }

   public String getLoginUsuario() {
      return this.loginUsuario;
   }

   public String getNombreUsuario() {
      return this.nombreUsuario;
   }

   public String getEstado() {
      return this.estado;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public void setLoginUsuario(String loginUsuario) {
      this.loginUsuario = loginUsuario;
   }

   public void setNombreUsuario(String nombreUsuario) {
      this.nombreUsuario = nombreUsuario;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UsuarioFilter)) {
         return false;
      } else {
         UsuarioFilter other = (UsuarioFilter)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label59: {
               Object this$usuario = this.getUsuario();
               Object other$usuario = other.getUsuario();
               if (this$usuario == null) {
                  if (other$usuario == null) {
                     break label59;
                  }
               } else if (this$usuario.equals(other$usuario)) {
                  break label59;
               }

               return false;
            }

            Object this$loginUsuario = this.getLoginUsuario();
            Object other$loginUsuario = other.getLoginUsuario();
            if (this$loginUsuario == null) {
               if (other$loginUsuario != null) {
                  return false;
               }
            } else if (!this$loginUsuario.equals(other$loginUsuario)) {
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

            Object this$estado = this.getEstado();
            Object other$estado = other.getEstado();
            if (this$estado == null) {
               if (other$estado != null) {
                  return false;
               }
            } else if (!this$estado.equals(other$estado)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UsuarioFilter;
   }

   public int hashCode() {
      int PRIME = 0;
      int result = 1;
      Object $usuario = this.getUsuario();
      result = result * 59 + ($usuario == null ? 43 : $usuario.hashCode());
      Object $loginUsuario = this.getLoginUsuario();
      result = result * 59 + ($loginUsuario == null ? 43 : $loginUsuario.hashCode());
      Object $nombreUsuario = this.getNombreUsuario();
      result = result * 59 + ($nombreUsuario == null ? 43 : $nombreUsuario.hashCode());
      Object $estado = this.getEstado();
      result = result * 59 + ($estado == null ? 43 : $estado.hashCode());
      return result;
   }

   public String toString() {
      return "UsuarioFilter(usuario=" + this.getUsuario() + ", loginUsuario=" + this.getLoginUsuario() + ", nombreUsuario=" + this.getNombreUsuario() + ", estado=" + this.getEstado() + ")";
   }
}
