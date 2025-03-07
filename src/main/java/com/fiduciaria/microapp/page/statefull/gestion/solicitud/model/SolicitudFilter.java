// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud.model;

import java.io.Serializable;

public class SolicitudFilter implements Serializable {
   private String codigoNegocio;
   private String nombreNegocio;

   public SolicitudFilter() {
   }

   public String getCodigoNegocio() {
      return this.codigoNegocio;
   }

   public String getNombreNegocio() {
      return this.nombreNegocio;
   }

   public void setCodigoNegocio(String codigoNegocio) {
      this.codigoNegocio = codigoNegocio;
   }

   public void setNombreNegocio(String nombreNegocio) {
      this.nombreNegocio = nombreNegocio;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SolicitudFilter)) {
         return false;
      } else {
         SolicitudFilter other = (SolicitudFilter)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$codigoNegocio = this.getCodigoNegocio();
            Object other$codigoNegocio = other.getCodigoNegocio();
            if (this$codigoNegocio == null) {
               if (other$codigoNegocio != null) {
                  return false;
               }
            } else if (!this$codigoNegocio.equals(other$codigoNegocio)) {
               return false;
            }

            Object this$nombreNegocio = this.getNombreNegocio();
            Object other$nombreNegocio = other.getNombreNegocio();
            if (this$nombreNegocio == null) {
               if (other$nombreNegocio != null) {
                  return false;
               }
            } else if (!this$nombreNegocio.equals(other$nombreNegocio)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SolicitudFilter;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $codigoNegocio = this.getCodigoNegocio();
      result = result * 59 + ($codigoNegocio == null ? 43 : $codigoNegocio.hashCode());
      Object $nombreNegocio = this.getNombreNegocio();
      result = result * 59 + ($nombreNegocio == null ? 43 : $nombreNegocio.hashCode());
      return result;
   }

   public String toString() {
      return "SolicitudFilter(codigoNegocio=" + this.getCodigoNegocio() + ", nombreNegocio=" + this.getNombreNegocio() + ")";
   }
}
