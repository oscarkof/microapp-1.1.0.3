// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.negocio;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.wicket.util.io.IClusterable;

public class NegocioParam implements IClusterable {
   private String usuario;
   private String dominioid;
   private boolean soloTotales;
   private List<NegocioColumnEnum> camposRequeridos;
   private NegocioColumnEnum ordenarPor;
   private NegocioColumnEnum columnaFiltro;
   private String valorFiltro;
   private Map<NegocioColumnEnum, Object> condiciones;
   private long totalPaginas;
   private long cuenta;
   private long inicio;
   private String ordenarTipo;
   private int totalRegistros;
   private List<Map<String, String>> rowsquery;

   public List<NegocioPojo> listaNegocios() {
      List<NegocioPojo> response = new LinkedList();
      if (Objects.nonNull(this.rowsquery) && !this.rowsquery.isEmpty()) {
      }

      return response;
   }

   public NegocioParam() {
   }

   public String getUsuario() {
      return this.usuario;
   }

   public String getDominioid() {
      return this.dominioid;
   }

   public boolean isSoloTotales() {
      return this.soloTotales;
   }

   public List<NegocioColumnEnum> getCamposRequeridos() {
      return this.camposRequeridos;
   }

   public NegocioColumnEnum getOrdenarPor() {
      return this.ordenarPor;
   }

   public NegocioColumnEnum getColumnaFiltro() {
      return this.columnaFiltro;
   }

   public String getValorFiltro() {
      return this.valorFiltro;
   }

   public Map<NegocioColumnEnum, Object> getCondiciones() {
      return this.condiciones;
   }

   public long getTotalPaginas() {
      return this.totalPaginas;
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

   public List<Map<String, String>> getRowsquery() {
      return this.rowsquery;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public void setDominioid(String dominioid) {
      this.dominioid = dominioid;
   }

   public void setSoloTotales(boolean soloTotales) {
      this.soloTotales = soloTotales;
   }

   public void setCamposRequeridos(List<NegocioColumnEnum> camposRequeridos) {
      this.camposRequeridos = camposRequeridos;
   }

   public void setOrdenarPor(NegocioColumnEnum ordenarPor) {
      this.ordenarPor = ordenarPor;
   }

   public void setColumnaFiltro(NegocioColumnEnum columnaFiltro) {
      this.columnaFiltro = columnaFiltro;
   }

   public void setValorFiltro(String valorFiltro) {
      this.valorFiltro = valorFiltro;
   }

   public void setCondiciones(Map<NegocioColumnEnum, Object> condiciones) {
      this.condiciones = condiciones;
   }

   public void setTotalPaginas(long totalPaginas) {
      this.totalPaginas = totalPaginas;
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

   public void setRowsquery(List<Map<String, String>> rowsquery) {
      this.rowsquery = rowsquery;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NegocioParam)) {
         return false;
      } else {
         NegocioParam other = (NegocioParam)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isSoloTotales() != other.isSoloTotales()) {
            return false;
         } else if (this.getTotalPaginas() != other.getTotalPaginas()) {
            return false;
         } else if (this.getCuenta() != other.getCuenta()) {
            return false;
         } else if (this.getInicio() != other.getInicio()) {
            return false;
         } else if (this.getTotalRegistros() != other.getTotalRegistros()) {
            return false;
         } else {
            Object this$usuario = this.getUsuario();
            Object other$usuario = other.getUsuario();
            if (this$usuario == null) {
               if (other$usuario != null) {
                  return false;
               }
            } else if (!this$usuario.equals(other$usuario)) {
               return false;
            }

            label124: {
               Object this$dominioid = this.getDominioid();
               Object other$dominioid = other.getDominioid();
               if (this$dominioid == null) {
                  if (other$dominioid == null) {
                     break label124;
                  }
               } else if (this$dominioid.equals(other$dominioid)) {
                  break label124;
               }

               return false;
            }

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

            label103: {
               Object this$columnaFiltro = this.getColumnaFiltro();
               Object other$columnaFiltro = other.getColumnaFiltro();
               if (this$columnaFiltro == null) {
                  if (other$columnaFiltro == null) {
                     break label103;
                  }
               } else if (this$columnaFiltro.equals(other$columnaFiltro)) {
                  break label103;
               }

               return false;
            }

            label96: {
               Object this$valorFiltro = this.getValorFiltro();
               Object other$valorFiltro = other.getValorFiltro();
               if (this$valorFiltro == null) {
                  if (other$valorFiltro == null) {
                     break label96;
                  }
               } else if (this$valorFiltro.equals(other$valorFiltro)) {
                  break label96;
               }

               return false;
            }

            label89: {
               Object this$condiciones = this.getCondiciones();
               Object other$condiciones = other.getCondiciones();
               if (this$condiciones == null) {
                  if (other$condiciones == null) {
                     break label89;
                  }
               } else if (this$condiciones.equals(other$condiciones)) {
                  break label89;
               }

               return false;
            }

            Object this$ordenarTipo = this.getOrdenarTipo();
            Object other$ordenarTipo = other.getOrdenarTipo();
            if (this$ordenarTipo == null) {
               if (other$ordenarTipo != null) {
                  return false;
               }
            } else if (!this$ordenarTipo.equals(other$ordenarTipo)) {
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
      return other instanceof NegocioParam;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + (this.isSoloTotales() ? 79 : 97);
      long $totalPaginas = this.getTotalPaginas();
      result = result * 59 + (int)($totalPaginas >>> 32 ^ $totalPaginas);
      long $cuenta = this.getCuenta();
      result = result * 59 + (int)($cuenta >>> 32 ^ $cuenta);
      long $inicio = this.getInicio();
      result = result * 59 + (int)($inicio >>> 32 ^ $inicio);
      result = result * 59 + this.getTotalRegistros();
      Object $usuario = this.getUsuario();
      result = result * 59 + ($usuario == null ? 43 : $usuario.hashCode());
      Object $dominioid = this.getDominioid();
      result = result * 59 + ($dominioid == null ? 43 : $dominioid.hashCode());
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
      return "NegocioParam(usuario=" + this.getUsuario() + ", dominioid=" + this.getDominioid() + ", soloTotales=" + this.isSoloTotales() + ", camposRequeridos=" + this.getCamposRequeridos() + ", ordenarPor=" + this.getOrdenarPor() + ", columnaFiltro=" + this.getColumnaFiltro() + ", valorFiltro=" + this.getValorFiltro() + ", condiciones=" + this.getCondiciones() + ", totalPaginas=" + this.getTotalPaginas() + ", cuenta=" + this.getCuenta() + ", inicio=" + this.getInicio() + ", ordenarTipo=" + this.getOrdenarTipo() + ", totalRegistros=" + this.getTotalRegistros() + ", rowsquery=" + this.getRowsquery() + ")";
   }
}
