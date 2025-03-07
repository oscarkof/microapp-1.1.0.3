// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.dominio.historial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistorialDominioParam implements Serializable {
   private long cuenta;
   private long inicio;
   private long totalRegistros;
   private String filtro;
   private boolean soloTotales;
   private List<HistorialDominioPojo> listaHistorial;
   private List<HistorialDominioColumnEnum> camposRequeridos;
   private HistorialDominioColumnEnum ordenarPor;
   private HistorialDominioColumnEnum columnaFiltro;
   private String valorFiltro;
   private Map<HistorialDominioColumnEnum, Object> condiciones;

   @JsonIgnore
   public long getMostrandoHasta() {
      return this.totalRegistros < this.inicio + this.cuenta ? this.totalRegistros : this.inicio + this.cuenta;
   }

   public List<HistorialDominioPojo> listadoHistorial() {
      List<HistorialDominioPojo> response = new ArrayList();
      return response;
   }

   public HistorialDominioParam() {
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

   public List<HistorialDominioPojo> getListaHistorial() {
      return this.listaHistorial;
   }

   public List<HistorialDominioColumnEnum> getCamposRequeridos() {
      return this.camposRequeridos;
   }

   public HistorialDominioColumnEnum getOrdenarPor() {
      return this.ordenarPor;
   }

   public HistorialDominioColumnEnum getColumnaFiltro() {
      return this.columnaFiltro;
   }

   public String getValorFiltro() {
      return this.valorFiltro;
   }

   public Map<HistorialDominioColumnEnum, Object> getCondiciones() {
      return this.condiciones;
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

   public void setListaHistorial(List<HistorialDominioPojo> listaHistorial) {
      this.listaHistorial = listaHistorial;
   }

   public void setCamposRequeridos(List<HistorialDominioColumnEnum> camposRequeridos) {
      this.camposRequeridos = camposRequeridos;
   }

   public void setOrdenarPor(HistorialDominioColumnEnum ordenarPor) {
      this.ordenarPor = ordenarPor;
   }

   public void setColumnaFiltro(HistorialDominioColumnEnum columnaFiltro) {
      this.columnaFiltro = columnaFiltro;
   }

   public void setValorFiltro(String valorFiltro) {
      this.valorFiltro = valorFiltro;
   }

   public void setCondiciones(Map<HistorialDominioColumnEnum, Object> condiciones) {
      this.condiciones = condiciones;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HistorialDominioParam)) {
         return false;
      } else {
         HistorialDominioParam other = (HistorialDominioParam)o;
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
            label105: {
               Object this$filtro = this.getFiltro();
               Object other$filtro = other.getFiltro();
               if (this$filtro == null) {
                  if (other$filtro == null) {
                     break label105;
                  }
               } else if (this$filtro.equals(other$filtro)) {
                  break label105;
               }

               return false;
            }

            Object this$listaHistorial = this.getListaHistorial();
            Object other$listaHistorial = other.getListaHistorial();
            if (this$listaHistorial == null) {
               if (other$listaHistorial != null) {
                  return false;
               }
            } else if (!this$listaHistorial.equals(other$listaHistorial)) {
               return false;
            }

            label91: {
               Object this$camposRequeridos = this.getCamposRequeridos();
               Object other$camposRequeridos = other.getCamposRequeridos();
               if (this$camposRequeridos == null) {
                  if (other$camposRequeridos == null) {
                     break label91;
                  }
               } else if (this$camposRequeridos.equals(other$camposRequeridos)) {
                  break label91;
               }

               return false;
            }

            Object this$ordenarPor = this.getOrdenarPor();
            Object other$ordenarPor = other.getOrdenarPor();
            if (this$ordenarPor == null) {
               if (other$ordenarPor != null) {
                  return false;
               }
            } else if (!this$ordenarPor.equals(other$ordenarPor)) {
               return false;
            }

            label77: {
               Object this$columnaFiltro = this.getColumnaFiltro();
               Object other$columnaFiltro = other.getColumnaFiltro();
               if (this$columnaFiltro == null) {
                  if (other$columnaFiltro == null) {
                     break label77;
                  }
               } else if (this$columnaFiltro.equals(other$columnaFiltro)) {
                  break label77;
               }

               return false;
            }

            label70: {
               Object this$valorFiltro = this.getValorFiltro();
               Object other$valorFiltro = other.getValorFiltro();
               if (this$valorFiltro == null) {
                  if (other$valorFiltro == null) {
                     break label70;
                  }
               } else if (this$valorFiltro.equals(other$valorFiltro)) {
                  break label70;
               }

               return false;
            }

            Object this$condiciones = this.getCondiciones();
            Object other$condiciones = other.getCondiciones();
            if (this$condiciones == null) {
               if (other$condiciones != null) {
                  return false;
               }
            } else if (!this$condiciones.equals(other$condiciones)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof HistorialDominioParam;
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
      Object $listaHistorial = this.getListaHistorial();
      result = result * 59 + ($listaHistorial == null ? 43 : $listaHistorial.hashCode());
      Object $camposRequeridos = this.getCamposRequeridos();
      result = result * 59 + ($camposRequeridos == null ? 43 : $camposRequeridos.hashCode());
      Object $ordenarPor = this.getOrdenarPor();
      result = result * 59 + ($ordenarPor == null ? 43 : $ordenarPor.hashCode());
      Object $columnaFiltro = this.getColumnaFiltro();
      result = result * 59 + ($columnaFiltro == null ? 43 : $columnaFiltro.hashCode());
      Object $valorFiltro = this.getValorFiltro();
      result = result * 59 + ($valorFiltro == null ? 43 : $valorFiltro.hashCode());
      Object $condiciones = this.getCondiciones();
      result = result * 59 + ($condiciones == null ? 43 : $condiciones.hashCode());
      return result;
   }

   public String toString() {
      return "HistorialDominioParam(cuenta=" + this.getCuenta() + ", inicio=" + this.getInicio() + ", totalRegistros=" + this.getTotalRegistros() + ", filtro=" + this.getFiltro() + ", soloTotales=" + this.isSoloTotales() + ", listaHistorial=" + this.getListaHistorial() + ", camposRequeridos=" + this.getCamposRequeridos() + ", ordenarPor=" + this.getOrdenarPor() + ", columnaFiltro=" + this.getColumnaFiltro() + ", valorFiltro=" + this.getValorFiltro() + ", condiciones=" + this.getCondiciones() + ")";
   }
}
