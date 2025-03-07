// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.dane;

import java.io.Serializable;

public class TipoDireccionPojo implements Serializable {
   private String codigo;
   private String descripcion;

   public String getCodigo() {
      return this.codigo;
   }

   public String getDescripcion() {
      return this.descripcion;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TipoDireccionPojo)) {
         return false;
      } else {
         TipoDireccionPojo other = (TipoDireccionPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$codigo = this.getCodigo();
            Object other$codigo = other.getCodigo();
            if (this$codigo == null) {
               if (other$codigo != null) {
                  return false;
               }
            } else if (!this$codigo.equals(other$codigo)) {
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

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TipoDireccionPojo;
   }

   public int hashCode() {
      int PRIME = 0;
      int result = 1;
      Object $codigo = this.getCodigo();
      result = result * 59 + ($codigo == null ? 43 : $codigo.hashCode());
      Object $descripcion = this.getDescripcion();
      result = result * 59 + ($descripcion == null ? 43 : $descripcion.hashCode());
      return result;
   }

   public String toString() {
      return "TipoDireccionPojo(codigo=" + this.getCodigo() + ", descripcion=" + this.getDescripcion() + ")";
   }

   public TipoDireccionPojo() {
   }

   public TipoDireccionPojo(String codigo, String descripcion) {
      this.codigo = codigo;
      this.descripcion = descripcion;
   }
}
