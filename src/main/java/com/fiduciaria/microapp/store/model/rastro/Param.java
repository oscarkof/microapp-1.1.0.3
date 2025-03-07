// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.rastro;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Param<T> implements Serializable {
   private boolean soloTotales;
   private List<T> camposRequeridos;
   private T ordenarPor;
   private T columnaFiltro;
   private String valorFiltro;
   private Map<T, Object> condiciones;
   private long cuenta;
   private long inicio;
   private String ordenarTipo;
   private int totalRegistros;
   private List<Map<String, Object>> rowsquery;

   public Param() {
   }

   public boolean isSoloTotales() {
      return this.soloTotales;
   }

   public List<T> getCamposRequeridos() {
      return this.camposRequeridos;
   }

   public T getOrdenarPor() {
      return this.ordenarPor;
   }

   public T getColumnaFiltro() {
      return this.columnaFiltro;
   }

   public String getValorFiltro() {
      return this.valorFiltro;
   }

   public Map<T, Object> getCondiciones() {
      return this.condiciones;
   }

   public long getCuenta() {
      return this.cuenta;
   }

   public long getInicio() {
      return this.inicio;
   }

   public String getOrdenarTipo() {
      return this.ordenarTipo;
   }

   public int getTotalRegistros() {
      return this.totalRegistros;
   }

   public List<Map<String, Object>> getRowsquery() {
      return this.rowsquery;
   }

   public void setSoloTotales(boolean soloTotales) {
      this.soloTotales = soloTotales;
   }

   public void setCamposRequeridos(List<T> camposRequeridos) {
      this.camposRequeridos = camposRequeridos;
   }

   public void setOrdenarPor(T ordenarPor) {
      this.ordenarPor = ordenarPor;
   }

   public void setColumnaFiltro(T columnaFiltro) {
      this.columnaFiltro = columnaFiltro;
   }

   public void setValorFiltro(String valorFiltro) {
      this.valorFiltro = valorFiltro;
   }

   public void setCondiciones(Map<T, Object> condiciones) {
      this.condiciones = condiciones;
   }

   public void setCuenta(long cuenta) {
      this.cuenta = cuenta;
   }

   public void setInicio(long inicio) {
      this.inicio = inicio;
   }

   public void setOrdenarTipo(String ordenarTipo) {
      this.ordenarTipo = ordenarTipo;
   }

   public void setTotalRegistros(int totalRegistros) {
      this.totalRegistros = totalRegistros;
   }

   public void setRowsquery(List<Map<String, Object>> rowsquery) {
      this.rowsquery = rowsquery;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Param)) {
         return false;
      } else {
         Param<?> other = (Param)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isSoloTotales() != other.isSoloTotales()) {
            return false;
         } else if (this.getCuenta() != other.getCuenta()) {
            return false;
         } else if (this.getInicio() != other.getInicio()) {
            return false;
         } else if (this.getTotalRegistros() != other.getTotalRegistros()) {
            return false;
         } else {
            Object this$camposRequeridos = this.getCamposRequeridos();
            Object other$camposRequeridos = other.getCamposRequeridos();
            if (this$camposRequeridos == null) {
               if (other$camposRequeridos != null) {
                  return false;
               }
            } else if (!this$camposRequeridos.equals(other$camposRequeridos)) {
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

            label91: {
               Object this$columnaFiltro = this.getColumnaFiltro();
               Object other$columnaFiltro = other.getColumnaFiltro();
               if (this$columnaFiltro == null) {
                  if (other$columnaFiltro == null) {
                     break label91;
                  }
               } else if (this$columnaFiltro.equals(other$columnaFiltro)) {
                  break label91;
               }

               return false;
            }

            Object this$valorFiltro = this.getValorFiltro();
            Object other$valorFiltro = other.getValorFiltro();
            if (this$valorFiltro == null) {
               if (other$valorFiltro != null) {
                  return false;
               }
            } else if (!this$valorFiltro.equals(other$valorFiltro)) {
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

            label70: {
               Object this$ordenarTipo = this.getOrdenarTipo();
               Object other$ordenarTipo = other.getOrdenarTipo();
               if (this$ordenarTipo == null) {
                  if (other$ordenarTipo == null) {
                     break label70;
                  }
               } else if (this$ordenarTipo.equals(other$ordenarTipo)) {
                  break label70;
               }

               return false;
            }

            Object this$rowsquery = this.getRowsquery();
            Object other$rowsquery = other.getRowsquery();
            if (this$rowsquery == null) {
               if (other$rowsquery != null) {
                  return false;
               }
            } else if (!this$rowsquery.equals(other$rowsquery)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof Param;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + (this.isSoloTotales() ? 79 : 97);
      long $cuenta = this.getCuenta();
      result = result * 59 + (int)($cuenta >>> 32 ^ $cuenta);
      long $inicio = this.getInicio();
      result = result * 59 + (int)($inicio >>> 32 ^ $inicio);
      result = result * 59 + this.getTotalRegistros();
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
      Object $ordenarTipo = this.getOrdenarTipo();
      result = result * 59 + ($ordenarTipo == null ? 43 : $ordenarTipo.hashCode());
      Object $rowsquery = this.getRowsquery();
      result = result * 59 + ($rowsquery == null ? 43 : $rowsquery.hashCode());
      return result;
   }

   public String toString() {
      return "Param(soloTotales=" + this.isSoloTotales() + ", camposRequeridos=" + this.getCamposRequeridos() + ", ordenarPor=" + this.getOrdenarPor() + ", columnaFiltro=" + this.getColumnaFiltro() + ", valorFiltro=" + this.getValorFiltro() + ", condiciones=" + this.getCondiciones() + ", cuenta=" + this.getCuenta() + ", inicio=" + this.getInicio() + ", ordenarTipo=" + this.getOrdenarTipo() + ", totalRegistros=" + this.getTotalRegistros() + ", rowsquery=" + this.getRowsquery() + ")";
   }
}
