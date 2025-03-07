// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.wicket.util.io.IClusterable;

public class UsuarioParam implements IClusterable {
   private boolean soloTotales;
   @JsonIgnore
   private boolean mostrarAdmin = false;
   private List<UsuarioColumnEnum> camposRequeridos;
   private UsuarioColumnEnum ordenarPor;
   private UsuarioColumnEnum columnaFiltro;
   private String valorFiltro;
   private Map<UsuarioColumnEnum, Object> condiciones;
   private long totalPaginas;
   private long cuenta;
   private long inicio;
   private String ordenarTipo;
   private int totalRegistros;
   private List<Map<String, String>> rowsquery;

   public List<UsuarioPojo> listaUsuarios() {
      List<UsuarioPojo> response = new LinkedList();
      if (Objects.nonNull(this.rowsquery) && !this.rowsquery.isEmpty()) {
         this.rowsquery.stream().map((detalleUsuario) -> {
            UsuarioPojo usr = new UsuarioPojo();
            usr.setIdPrincipal((String)detalleUsuario.get(UsuarioColumnEnum.LOGIN_USUARIO.toString().toLowerCase()));
            usr.setUsuario((String)detalleUsuario.get(UsuarioColumnEnum.USUARIO.toString().toLowerCase()));
            usr.setEstado((String)detalleUsuario.get(UsuarioColumnEnum.ESTADO_USUARIO.toString().toLowerCase()));
            usr.setNombrePrincipal((String)detalleUsuario.get(UsuarioColumnEnum.DESCRIPCION_USUARIO.toString().toLowerCase()));
            if (Objects.isNull(usr.getAtributos())) {
               usr.setAtributos(new HashMap());
            }

            usr.getAtributos().put(UsuarioColumnEnum.CLASE_DOCUMENTO.toString().toLowerCase(), detalleUsuario.get(UsuarioColumnEnum.CLASE_DOCUMENTO.toString().toLowerCase()));
            usr.getAtributos().put(UsuarioColumnEnum.CODIGO_GRUPO_USUARIO.toString().toLowerCase(), detalleUsuario.get(UsuarioColumnEnum.CODIGO_GRUPO_USUARIO.toString().toLowerCase()));
            usr.getAtributos().put(UsuarioColumnEnum.IDENTIFICACION.toString().toLowerCase(), detalleUsuario.get(UsuarioColumnEnum.IDENTIFICACION.toString().toLowerCase()));
            usr.getAtributos().put(UsuarioColumnEnum.CORREO.toString().toLowerCase(), detalleUsuario.get(UsuarioColumnEnum.CORREO.toString().toLowerCase()));
            return usr;
         }).forEachOrdered((usr) -> {
            response.add(usr);
         });
      }

      return response;
   }

   public UsuarioParam() {
   }

   public boolean isSoloTotales() {
      return this.soloTotales;
   }

   public boolean isMostrarAdmin() {
      return this.mostrarAdmin;
   }

   public List<UsuarioColumnEnum> getCamposRequeridos() {
      return this.camposRequeridos;
   }

   public UsuarioColumnEnum getOrdenarPor() {
      return this.ordenarPor;
   }

   public UsuarioColumnEnum getColumnaFiltro() {
      return this.columnaFiltro;
   }

   public String getValorFiltro() {
      return this.valorFiltro;
   }

   public Map<UsuarioColumnEnum, Object> getCondiciones() {
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

   public void setSoloTotales(boolean soloTotales) {
      this.soloTotales = soloTotales;
   }

   @JsonIgnore
   public void setMostrarAdmin(boolean mostrarAdmin) {
      this.mostrarAdmin = mostrarAdmin;
   }

   public void setCamposRequeridos(List<UsuarioColumnEnum> camposRequeridos) {
      this.camposRequeridos = camposRequeridos;
   }

   public void setOrdenarPor(UsuarioColumnEnum ordenarPor) {
      this.ordenarPor = ordenarPor;
   }

   public void setColumnaFiltro(UsuarioColumnEnum columnaFiltro) {
      this.columnaFiltro = columnaFiltro;
   }

   public void setValorFiltro(String valorFiltro) {
      this.valorFiltro = valorFiltro;
   }

   public void setCondiciones(Map<UsuarioColumnEnum, Object> condiciones) {
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
      } else if (!(o instanceof UsuarioParam)) {
         return false;
      } else {
         UsuarioParam other = (UsuarioParam)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isSoloTotales() != other.isSoloTotales()) {
            return false;
         } else if (this.isMostrarAdmin() != other.isMostrarAdmin()) {
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
            Object this$camposRequeridos = this.getCamposRequeridos();
            Object other$camposRequeridos = other.getCamposRequeridos();
            if (this$camposRequeridos == null) {
               if (other$camposRequeridos != null) {
                  return false;
               }
            } else if (!this$camposRequeridos.equals(other$camposRequeridos)) {
               return false;
            }

            label103: {
               Object this$ordenarPor = this.getOrdenarPor();
               Object other$ordenarPor = other.getOrdenarPor();
               if (this$ordenarPor == null) {
                  if (other$ordenarPor == null) {
                     break label103;
                  }
               } else if (this$ordenarPor.equals(other$ordenarPor)) {
                  break label103;
               }

               return false;
            }

            Object this$columnaFiltro = this.getColumnaFiltro();
            Object other$columnaFiltro = other.getColumnaFiltro();
            if (this$columnaFiltro == null) {
               if (other$columnaFiltro != null) {
                  return false;
               }
            } else if (!this$columnaFiltro.equals(other$columnaFiltro)) {
               return false;
            }

            label89: {
               Object this$valorFiltro = this.getValorFiltro();
               Object other$valorFiltro = other.getValorFiltro();
               if (this$valorFiltro == null) {
                  if (other$valorFiltro == null) {
                     break label89;
                  }
               } else if (this$valorFiltro.equals(other$valorFiltro)) {
                  break label89;
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
      return other instanceof UsuarioParam;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + (this.isSoloTotales() ? 79 : 97);
      result = result * 59 + (this.isMostrarAdmin() ? 79 : 97);
      long $totalPaginas = this.getTotalPaginas();
      result = result * 59 + (int)($totalPaginas >>> 32 ^ $totalPaginas);
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
      return "UsuarioParam(soloTotales=" + this.isSoloTotales() + ", mostrarAdmin=" + this.isMostrarAdmin() + ", camposRequeridos=" + this.getCamposRequeridos() + ", ordenarPor=" + this.getOrdenarPor() + ", columnaFiltro=" + this.getColumnaFiltro() + ", valorFiltro=" + this.getValorFiltro() + ", condiciones=" + this.getCondiciones() + ", totalPaginas=" + this.getTotalPaginas() + ", cuenta=" + this.getCuenta() + ", inicio=" + this.getInicio() + ", ordenarTipo=" + this.getOrdenarTipo() + ", totalRegistros=" + this.getTotalRegistros() + ", rowsquery=" + this.getRowsquery() + ")";
   }
}
