// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.wicket.util.io.IClusterable;

public class SolicitudParam implements IClusterable {
   private boolean soloTotales;
   private List<EnumCampoSolicitud> camposRequeridos;
   private EnumCampoSolicitud ordenarPor;
   private EnumCampoSolicitud columnaFiltro;
   private String valorFiltro;
   private Map<EnumCampoSolicitud, Object> condiciones;
   private long totalPaginas;
   private long tamanioPagina;
   private long inicio;
   private boolean usuarioUCA;
   private boolean tareaAtomaticaCreacionUsuario;
   private String ordenarTipo;
   private int totalRegistros;
   private List<Map<String, Object>> rowsquery;

   @JsonIgnore
   public List<SolicitudPojo> listadoSolicitudes() {
      List<SolicitudPojo> response = new LinkedList();
      if (Objects.nonNull(this.rowsquery)) {
         this.rowsquery.forEach((action) -> {
            SolicitudPojo obj = new SolicitudPojo();
            obj.setAceptacionTrDatos(this.stringValueFormater(action.get(EnumCampoSolicitud.ACEPTACION_TR_DATOS.toString().toLowerCase().toLowerCase())));
            obj.setApellidoSolicita(this.stringValueFormater(action.get(EnumCampoSolicitud.APELLIDO_SOLICITA.toString().toLowerCase())));
            obj.setCelularSolicita(this.stringValueFormater(action.get(EnumCampoSolicitud.CELULAR_SOLICITA.toString().toLowerCase())));
            obj.setConUsrAuthz(this.stringValueFormater(action.get(EnumCampoSolicitud.CON_USR_AUTHZ.toString().toLowerCase())));
            obj.setCondiManejo(this.stringValueFormater(action.get(EnumCampoSolicitud.CONDI_MANEJO.toString().toLowerCase())));
            obj.setCorreoForm(this.stringValueFormater(action.get(EnumCampoSolicitud.CORREO_FORM.toString().toLowerCase())));
            obj.setCorreoSolicita(this.stringValueFormater(action.get(EnumCampoSolicitud.CORREO_SOLICITA.toString().toLowerCase())));
            obj.setDescripcion(this.stringValueFormater(action.get(EnumCampoSolicitud.DESCRIPCION.toString().toLowerCase())));
            obj.setDireccion(this.stringValueFormater(action.get(EnumCampoSolicitud.DIRECCION.toString().toLowerCase())));
            if (Objects.nonNull(action.get(EnumCampoSolicitud.FECHA_HORA.toString().toLowerCase()))) {
               Long fechalong = Long.valueOf(this.stringValueFormater(action.get(EnumCampoSolicitud.FECHA_HORA.toString().toLowerCase())));
               Date fecha = new Date(fechalong);
               SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
               obj.setFechaHora(formater.format(fecha));
            } else {
               obj.setFechaHora("");
            }

            obj.setIdAdjunto(this.stringValueFormater(action.get(EnumCampoSolicitud.ID_ADJUNTO.toString().toLowerCase())));
            obj.setIdRefNegocio(this.stringValueFormater(action.get(EnumCampoSolicitud.ID_REF_NEGOCIO.toString().toLowerCase())));
            obj.setIdentificacionSolicita(this.stringValueFormater(action.get(EnumCampoSolicitud.IDENTIFICACION_SOLICITA.toString().toLowerCase())));
            obj.setNombreRefNegocio(this.stringValueFormater(action.get(EnumCampoSolicitud.NOMBRE_REF_NEGOCIO.toString().toLowerCase())));
            obj.setNombreSolicita(this.stringValueFormater(action.get(EnumCampoSolicitud.NOMBRE_SOLICITA.toString().toLowerCase())));
            obj.setRadicadoId(new BigDecimal(this.stringValueFormater(action.get(EnumCampoSolicitud.RADICADO_ID.toString().toLowerCase()))));
            obj.setRol(this.stringValueFormater(action.get(EnumCampoSolicitud.ROL.toString().toLowerCase())));
            obj.setRolFideicomiso(this.stringValueFormater(action.get(EnumCampoSolicitud.ROL_FIDEICOMISO.toString().toLowerCase())));
            obj.setRolFideicomisoOtro(this.stringValueFormater(action.get(EnumCampoSolicitud.ROL_FIDEICOMISO_OTRO.toString().toLowerCase())));
            obj.setSegundoApellido(this.stringValueFormater(action.get(EnumCampoSolicitud.SEGUNDO_APELLIDO.toString().toLowerCase())));
            obj.setSegundoNombre(this.stringValueFormater(action.get(EnumCampoSolicitud.SEGUNDO_NOMBRE.toString().toLowerCase())));
            obj.setTelefonoSolicita(this.stringValueFormater(action.get(EnumCampoSolicitud.TELEFONO_SOLICITA.toString().toLowerCase())));
            obj.setTipoDocSolicita(this.stringValueFormater(action.get(EnumCampoSolicitud.TIPO_DOC_SOLICITA.toString().toLowerCase())));
            obj.setTipoSolicitud(this.stringValueFormater(action.get(EnumCampoSolicitud.TIPO_SOLICITUD.toString().toLowerCase())));
            obj.setToken(this.stringValueFormater(action.get(EnumCampoSolicitud.TOKEN.toString().toLowerCase())));
            obj.setEstado(this.stringValueFormater(action.get(EnumCampoSolicitud.ESTADO.toString().toLowerCase())));
            obj.setUsuarioCrea(this.stringValueFormater(action.get(EnumCampoSolicitud.USUARIO_CREA.toString().toLowerCase())));
            obj.setNumUsuarios(this.stringValueFormater(action.get(EnumCampoSolicitud.NUM_USUARIOS.toString().toLowerCase())));
            response.add(obj);
         });
      }

      return response;
   }

   private String stringValueFormater(Object valor) {
      String response = "";
      if (Objects.nonNull(valor)) {
         response = String.valueOf(valor);
      }

      return response;
   }

   public SolicitudParam() {
   }

   public boolean isSoloTotales() {
      return this.soloTotales;
   }

   public List<EnumCampoSolicitud> getCamposRequeridos() {
      return this.camposRequeridos;
   }

   public EnumCampoSolicitud getOrdenarPor() {
      return this.ordenarPor;
   }

   public EnumCampoSolicitud getColumnaFiltro() {
      return this.columnaFiltro;
   }

   public String getValorFiltro() {
      return this.valorFiltro;
   }

   public Map<EnumCampoSolicitud, Object> getCondiciones() {
      return this.condiciones;
   }

   public long getTotalPaginas() {
      return this.totalPaginas;
   }

   public long getTamanioPagina() {
      return this.tamanioPagina;
   }

   public long getInicio() {
      return this.inicio;
   }

   public boolean isUsuarioUCA() {
      return this.usuarioUCA;
   }

   public boolean isTareaAtomaticaCreacionUsuario() {
      return this.tareaAtomaticaCreacionUsuario;
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

   public void setCamposRequeridos(List<EnumCampoSolicitud> camposRequeridos) {
      this.camposRequeridos = camposRequeridos;
   }

   public void setOrdenarPor(EnumCampoSolicitud ordenarPor) {
      this.ordenarPor = ordenarPor;
   }

   public void setColumnaFiltro(EnumCampoSolicitud columnaFiltro) {
      this.columnaFiltro = columnaFiltro;
   }

   public void setValorFiltro(String valorFiltro) {
      this.valorFiltro = valorFiltro;
   }

   public void setCondiciones(Map<EnumCampoSolicitud, Object> condiciones) {
      this.condiciones = condiciones;
   }

   public void setTotalPaginas(long totalPaginas) {
      this.totalPaginas = totalPaginas;
   }

   public void setTamanioPagina(long tamanioPagina) {
      this.tamanioPagina = tamanioPagina;
   }

   public void setInicio(long inicio) {
      this.inicio = inicio;
   }

   public void setUsuarioUCA(boolean usuarioUCA) {
      this.usuarioUCA = usuarioUCA;
   }

   public void setTareaAtomaticaCreacionUsuario(boolean tareaAtomaticaCreacionUsuario) {
      this.tareaAtomaticaCreacionUsuario = tareaAtomaticaCreacionUsuario;
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
      } else if (!(o instanceof SolicitudParam)) {
         return false;
      } else {
         SolicitudParam other = (SolicitudParam)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isSoloTotales() != other.isSoloTotales()) {
            return false;
         } else if (this.getTotalPaginas() != other.getTotalPaginas()) {
            return false;
         } else if (this.getTamanioPagina() != other.getTamanioPagina()) {
            return false;
         } else if (this.getInicio() != other.getInicio()) {
            return false;
         } else if (this.isUsuarioUCA() != other.isUsuarioUCA()) {
            return false;
         } else if (this.isTareaAtomaticaCreacionUsuario() != other.isTareaAtomaticaCreacionUsuario()) {
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

            label105: {
               Object this$ordenarPor = this.getOrdenarPor();
               Object other$ordenarPor = other.getOrdenarPor();
               if (this$ordenarPor == null) {
                  if (other$ordenarPor == null) {
                     break label105;
                  }
               } else if (this$ordenarPor.equals(other$ordenarPor)) {
                  break label105;
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

            label91: {
               Object this$valorFiltro = this.getValorFiltro();
               Object other$valorFiltro = other.getValorFiltro();
               if (this$valorFiltro == null) {
                  if (other$valorFiltro == null) {
                     break label91;
                  }
               } else if (this$valorFiltro.equals(other$valorFiltro)) {
                  break label91;
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
      return other instanceof SolicitudParam;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + (this.isSoloTotales() ? 79 : 97);
      long $totalPaginas = this.getTotalPaginas();
      result = result * 59 + (int)($totalPaginas >>> 32 ^ $totalPaginas);
      long $tamanioPagina = this.getTamanioPagina();
      result = result * 59 + (int)($tamanioPagina >>> 32 ^ $tamanioPagina);
      long $inicio = this.getInicio();
      result = result * 59 + (int)($inicio >>> 32 ^ $inicio);
      result = result * 59 + (this.isUsuarioUCA() ? 79 : 97);
      result = result * 59 + (this.isTareaAtomaticaCreacionUsuario() ? 79 : 97);
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
      return "SolicitudParam(soloTotales=" + this.isSoloTotales() + ", camposRequeridos=" + this.getCamposRequeridos() + ", ordenarPor=" + this.getOrdenarPor() + ", columnaFiltro=" + this.getColumnaFiltro() + ", valorFiltro=" + this.getValorFiltro() + ", condiciones=" + this.getCondiciones() + ", totalPaginas=" + this.getTotalPaginas() + ", tamanioPagina=" + this.getTamanioPagina() + ", inicio=" + this.getInicio() + ", usuarioUCA=" + this.isUsuarioUCA() + ", tareaAtomaticaCreacionUsuario=" + this.isTareaAtomaticaCreacionUsuario() + ", ordenarTipo=" + this.getOrdenarTipo() + ", totalRegistros=" + this.getTotalRegistros() + ", rowsquery=" + this.getRowsquery() + ")";
   }
}
