// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.seguridad;

import java.io.Serializable;
import java.util.List;

public class RolePojo implements Serializable {
   private String codigo;
   private String descripcion;
   private boolean activo;
   private List<PermisoPojo> permiso;

   public RolePojo() {
   }

   public String getCodigo() {
      return this.codigo;
   }

   public String getDescripcion() {
      return this.descripcion;
   }

   public boolean isActivo() {
      return this.activo;
   }

   public List<PermisoPojo> getPermiso() {
      return this.permiso;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public void setActivo(boolean activo) {
      this.activo = activo;
   }

   public void setPermiso(List<PermisoPojo> permiso) {
      this.permiso = permiso;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RolePojo)) {
         return false;
      } else {
         RolePojo other = (RolePojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isActivo() != other.isActivo()) {
            return false;
         } else {
            label49: {
               Object this$codigo = this.getCodigo();
               Object other$codigo = other.getCodigo();
               if (this$codigo == null) {
                  if (other$codigo == null) {
                     break label49;
                  }
               } else if (this$codigo.equals(other$codigo)) {
                  break label49;
               }

               return false;
            }

            Object this$descripcion = this.getDescripcion();
            Object other$descripcion = other.getDescripcion();
            if (this$descripcion == null) {
               if (other$descripcion != null) {
                  return false;
               }
            } else if (!this$descripcion.equals(other$descripcion)) {
               return false;
            }

            Object this$permiso = this.getPermiso();
            Object other$permiso = other.getPermiso();
            if (this$permiso == null) {
               if (other$permiso != null) {
                  return false;
               }
            } else if (!this$permiso.equals(other$permiso)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RolePojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + (this.isActivo() ? 79 : 97);
      Object $codigo = this.getCodigo();
      result = result * 59 + ($codigo == null ? 43 : $codigo.hashCode());
      Object $descripcion = this.getDescripcion();
      result = result * 59 + ($descripcion == null ? 43 : $descripcion.hashCode());
      Object $permiso = this.getPermiso();
      result = result * 59 + ($permiso == null ? 43 : $permiso.hashCode());
      return result;
   }

   public String toString() {
      return "RolePojo(codigo=" + this.getCodigo() + ", descripcion=" + this.getDescripcion() + ", activo=" + this.isActivo() + ", permiso=" + this.getPermiso() + ")";
   }
}
