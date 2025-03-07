// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;

public class DominioParam implements Serializable {
   private long cuenta;
   private long inicio;
   private long totalRegistros;
   private String filtro;
   private boolean soloTotales;
   private List<DominioPojo> listaDominios;

   @JsonIgnore
   public long getMostrandoHasta() {
      return this.totalRegistros < this.inicio + this.cuenta ? this.totalRegistros : this.inicio + this.cuenta;
   }

   public DominioParam() {
   }

   public long getCuenta() {
      return this.cuenta;
   }

   public long getInicio() {
      return this.inicio;
   }

   public long getTotalRegistros() {
      return this.totalRegistros;
   }

   public String getFiltro() {
      return this.filtro;
   }

   public boolean isSoloTotales() {
      return this.soloTotales;
   }

   public List<DominioPojo> getListaDominios() {
      return this.listaDominios;
   }

   public void setCuenta(long cuenta) {
      this.cuenta = cuenta;
   }

   public void setInicio(long inicio) {
      this.inicio = inicio;
   }

   public void setTotalRegistros(long totalRegistros) {
      this.totalRegistros = totalRegistros;
   }

   public void setFiltro(String filtro) {
      this.filtro = filtro;
   }

   public void setSoloTotales(boolean soloTotales) {
      this.soloTotales = soloTotales;
   }

   public void setListaDominios(List<DominioPojo> listaDominios) {
      this.listaDominios = listaDominios;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DominioParam)) {
         return false;
      } else {
         DominioParam other = (DominioParam)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getCuenta() != other.getCuenta()) {
            return false;
         } else if (this.getInicio() != other.getInicio()) {
            return false;
         } else if (this.getTotalRegistros() != other.getTotalRegistros()) {
            return false;
         } else if (this.isSoloTotales() != other.isSoloTotales()) {
            return false;
         } else {
            Object this$filtro = this.getFiltro();
            Object other$filtro = other.getFiltro();
            if (this$filtro == null) {
               if (other$filtro != null) {
                  return false;
               }
            } else if (!this$filtro.equals(other$filtro)) {
               return false;
            }

            Object this$listaDominios = this.getListaDominios();
            Object other$listaDominios = other.getListaDominios();
            if (this$listaDominios == null) {
               if (other$listaDominios != null) {
                  return false;
               }
            } else if (!this$listaDominios.equals(other$listaDominios)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof DominioParam;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      long $cuenta = this.getCuenta();
      result = result * 59 + (int)($cuenta >>> 32 ^ $cuenta);
      long $inicio = this.getInicio();
      result = result * 59 + (int)($inicio >>> 32 ^ $inicio);
      long $totalRegistros = this.getTotalRegistros();
      result = result * 59 + (int)($totalRegistros >>> 32 ^ $totalRegistros);
      result = result * 59 + (this.isSoloTotales() ? 79 : 97);
      Object $filtro = this.getFiltro();
      result = result * 59 + ($filtro == null ? 43 : $filtro.hashCode());
      Object $listaDominios = this.getListaDominios();
      result = result * 59 + ($listaDominios == null ? 43 : $listaDominios.hashCode());
      return result;
   }

   public String toString() {
      return "DominioParam(cuenta=" + this.getCuenta() + ", inicio=" + this.getInicio() + ", totalRegistros=" + this.getTotalRegistros() + ", filtro=" + this.getFiltro() + ", soloTotales=" + this.isSoloTotales() + ", listaDominios=" + this.getListaDominios() + ")";
   }
}
