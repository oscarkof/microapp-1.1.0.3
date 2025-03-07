// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud.model;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.store.model.gui.solicitud.FormularioSoportePojo;
import com.fiduciaria.microapp.store.service.EnumSolicitudServiceDefi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.util.io.IClusterable;

public class SolicitudPojoVM implements IClusterable {
   private final IGenericHttpClient gtwayHttp;
   private final List<EnumCampoSolicitud> camposXDefault;
   private final SolicitudParam parametros = new SolicitudParam();

   public SolicitudPojoVM(IGenericHttpClient gtwayHttp) {
      this.gtwayHttp = gtwayHttp;
      this.camposXDefault = new LinkedList();
      this.camposXDefault.add(EnumCampoSolicitud.TIPO_SOLICITUD);
      this.parametros.setCamposRequeridos(this.camposXDefault);
      this.parametros.setInicio(0L);
      this.parametros.setTamanioPagina(0L);
      this.parametros.setOrdenarPor(EnumCampoSolicitud.RADICADO_ID);
      this.parametros.setOrdenarTipo("DESC");
   }

   public int totalSolicitudes() {
      SolicitudParam rta = this.listaSolicitudes(this.parametros);
      return rta.getTotalRegistros();
   }

   public List<SolicitudPojo> listSolicitud() {
      SolicitudParam rta = this.listaSolicitudes(this.parametros);
      return rta.listadoSolicitudes();
   }

   private SolicitudParam listaSolicitudes(SolicitudParam param) {
      SolicitudParam response = new SolicitudParam();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumSolicitudServiceDefi.POST_LISTA_SOLICITUDES.getUrl());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         String entity = mapper.writeValueAsString(param);
         String paramTpl = this.gtwayHttp.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), entity);
         response = (SolicitudParam)mapper.readValue(paramTpl, SolicitudParam.class);
      } catch (JsonProcessingException var7) {
         Logger.getLogger(SolicitudPojoVM.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      return response;
   }

   public SolicitudParam getParametros() {
      return this.parametros;
   }

   public String guardarFormularioSolicitud(FormularioSoportePojo formulario, IGenericHttpClient gtwayHttp, String usuario, boolean usuarioUCA, boolean tareaAtomaticaCreacionUsuario) {
      SolicitudParam param = new SolicitudParam();
      param.setUsuarioUCA(usuarioUCA);
      param.setTareaAtomaticaCreacionUsuario(tareaAtomaticaCreacionUsuario);
      List<Map<String, Object>> sols = new LinkedList();
      Map<String, Object> solPreIngreso = new HashMap();
      solPreIngreso.put(EnumCampoSolicitud.ACEPTACION_TR_DATOS.toString(), formulario.isAceptacionPrerrequisitos());
      solPreIngreso.put(EnumCampoSolicitud.ADJUNTO.toString(), formulario.getPreingresodatosusuario().getAdjuntos());
      solPreIngreso.put(EnumCampoSolicitud.APELLIDO_SOLICITA.toString(), formulario.getPreingresodatosusuario().getPersona().getPrimerApellido());
      solPreIngreso.put(EnumCampoSolicitud.CELULAR_SOLICITA.toString(), formulario.getPreingresodatosusuario().getPersona().getCelular().getNumeroCelular());
      solPreIngreso.put(EnumCampoSolicitud.CONDI_MANEJO.toString(), formulario.getPreingresodatosusuario().getCondicionesManejo());
      solPreIngreso.put(EnumCampoSolicitud.CONDI_MANEJO_ROLCARGA_OTRA.toString(), formulario.getPreingresodatosusuario().getTextoOtroRolFideicomiso());
      solPreIngreso.put(EnumCampoSolicitud.CON_USR_AUTHZ.toString(), formulario.getPreingresodatosusuario().getCondicionesUsuariosAutorizadores());
      solPreIngreso.put(EnumCampoSolicitud.CORREO_FORM.toString(), formulario.getCorreoFormulario());
      solPreIngreso.put(EnumCampoSolicitud.CORREO_SOLICITA.toString(), formulario.getPreingresodatosusuario().getPersona().getCorreoElectronico());
      solPreIngreso.put(EnumCampoSolicitud.DIRECCION.toString(), formulario.getPreingresodatosusuario().getPersona().getDireccion());
      solPreIngreso.put(EnumCampoSolicitud.IDENTIFICACION_SOLICITA.toString(), formulario.getPreingresodatosusuario().getPersona().getNumeroDocumento());
      solPreIngreso.put(EnumCampoSolicitud.ID_REF_NEGOCIO.toString(), formulario.getPreingresodatosusuario().getCodNegocioLink());
      solPreIngreso.put(EnumCampoSolicitud.NOMBRE_REF_NEGOCIO.toString(), formulario.getPreingresodatosusuario().getNombreNegocioLink());
      solPreIngreso.put(EnumCampoSolicitud.NOMBRE_SOLICITA.toString(), formulario.getPreingresodatosusuario().getPersona().getPrimerNombre());
      solPreIngreso.put(EnumCampoSolicitud.ROL.toString(), formulario.getPreingresodatosusuario().getRole());
      solPreIngreso.put(EnumCampoSolicitud.ROL_FIDEICOMISO.toString(), formulario.getPreingresodatosusuario().getRolFideicomiso());
      solPreIngreso.put(EnumCampoSolicitud.ROL_FIDEICOMISO_OTRO.toString(), formulario.getPreingresodatosusuario().getTextoOtroRolFideicomiso());
      solPreIngreso.put(EnumCampoSolicitud.SEGUNDO_APELLIDO.toString(), formulario.getPreingresodatosusuario().getPersona().getSegundoApellido());
      solPreIngreso.put(EnumCampoSolicitud.SEGUNDO_NOMBRE.toString(), formulario.getPreingresodatosusuario().getPersona().getSegundoNombre());
      solPreIngreso.put(EnumCampoSolicitud.TELEFONO_SOLICITA.toString(), formulario.getPreingresodatosusuario().getPersona().getTelefono());
      solPreIngreso.put(EnumCampoSolicitud.TIPO_DOC_SOLICITA.toString(), formulario.getPreingresodatosusuario().getPersona().getTipoDocumento().getClasedocumento());
      solPreIngreso.put(EnumCampoSolicitud.TIPO_SOLICITUD.toString(), formulario.getPreingresodatosusuario().getTipoSolicitud());
      solPreIngreso.put(EnumCampoSolicitud.NUM_USUARIOS.toString(), formulario.getPreingresodatosusuario().getNumeroUsuariosCondiciones());
      solPreIngreso.put(EnumCampoSolicitud.USUARIO_CREA.toString(), usuario);
      sols.add(solPreIngreso);
      param.setRowsquery(sols);
      String rspta = this.crearSolicitud(param, gtwayHttp);
      return rspta;
   }

   public String guardarFormularioSoporte(FormularioSoportePojo formulario, IGenericHttpClient gtwayHttp, String usuario) {
      SolicitudParam param = new SolicitudParam();
      List<Map<String, Object>> sols = new LinkedList();
      Map<String, Object> solPreIngreso = new HashMap();
      solPreIngreso.put(EnumCampoSolicitud.ACEPTACION_TR_DATOS.toString(), formulario.isAceptacionPrerrequisitos());
      solPreIngreso.put(EnumCampoSolicitud.ADJUNTO.toString(), formulario.getSoporteTecnico().getAdjuntos());
      solPreIngreso.put(EnumCampoSolicitud.APELLIDO_SOLICITA.toString(), formulario.getSoporteTecnico().getPersona().getPrimerApellido());
      solPreIngreso.put(EnumCampoSolicitud.CELULAR_SOLICITA.toString(), formulario.getSoporteTecnico().getPersona().getCelular().getNumeroCelular());
      solPreIngreso.put(EnumCampoSolicitud.CONDI_MANEJO.toString(), "");
      solPreIngreso.put(EnumCampoSolicitud.CONDI_MANEJO_ROLCARGA_OTRA.toString(), "");
      solPreIngreso.put(EnumCampoSolicitud.CON_USR_AUTHZ.toString(), "");
      solPreIngreso.put(EnumCampoSolicitud.CORREO_FORM.toString(), formulario.getCorreoFormulario());
      solPreIngreso.put(EnumCampoSolicitud.CORREO_SOLICITA.toString(), formulario.getSoporteTecnico().getPersona().getCorreoElectronico());
      solPreIngreso.put(EnumCampoSolicitud.DESCRIPCION.toString(), formulario.getSoporteTecnico().getDescripcionCasoSoporte());
      solPreIngreso.put(EnumCampoSolicitud.DIRECCION.toString(), formulario.getSoporteTecnico().getPersona().getDireccion());
      solPreIngreso.put(EnumCampoSolicitud.IDENTIFICACION_SOLICITA.toString(), formulario.getSoporteTecnico().getPersona().getNumeroDocumento());
      solPreIngreso.put(EnumCampoSolicitud.ID_REF_NEGOCIO.toString(), formulario.getSoporteTecnico().getCodigoNegocioForm());
      solPreIngreso.put(EnumCampoSolicitud.NOMBRE_REF_NEGOCIO.toString(), formulario.getSoporteTecnico().getNombreNegocioForm());
      solPreIngreso.put(EnumCampoSolicitud.NOMBRE_SOLICITA.toString(), formulario.getSoporteTecnico().getPersona().getPrimerNombre());
      solPreIngreso.put(EnumCampoSolicitud.ROL.toString(), "");
      solPreIngreso.put(EnumCampoSolicitud.ROL_FIDEICOMISO.toString(), "");
      solPreIngreso.put(EnumCampoSolicitud.ROL_FIDEICOMISO_OTRO.toString(), "");
      solPreIngreso.put(EnumCampoSolicitud.SEGUNDO_APELLIDO.toString(), formulario.getSoporteTecnico().getPersona().getSegundoApellido());
      solPreIngreso.put(EnumCampoSolicitud.SEGUNDO_NOMBRE.toString(), formulario.getSoporteTecnico().getPersona().getSegundoNombre());
      solPreIngreso.put(EnumCampoSolicitud.TELEFONO_SOLICITA.toString(), formulario.getSoporteTecnico().getPersona().getTelefono());
      solPreIngreso.put(EnumCampoSolicitud.TIPO_DOC_SOLICITA.toString(), formulario.getSoporteTecnico().getPersona().getTipoDocumento().getClasedocumento());
      solPreIngreso.put(EnumCampoSolicitud.TIPO_SOLICITUD.toString(), formulario.getSoporteTecnico().getTipoSolicitud());
      solPreIngreso.put(EnumCampoSolicitud.USUARIO_CREA.toString(), usuario);
      sols.add(solPreIngreso);
      param.setRowsquery(sols);
      String rspta = this.crearSolicitud(param, gtwayHttp);
      return rspta;
   }

   private String crearSolicitud(SolicitudParam param, IGenericHttpClient gtwayHttp) {
      String response = "";
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumSolicitudServiceDefi.POST_CREAR_SOLICITUD.getUrl());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         String entity = mapper.writeValueAsString(param);
         String paramTpl = gtwayHttp.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), entity);
         Logger.getLogger(SolicitudPojoVM.class.getName()).log(Level.SEVERE, paramTpl);
         response = paramTpl;
      } catch (JsonProcessingException var8) {
         Logger.getLogger(SolicitudPojoVM.class.getName()).log(Level.SEVERE, (String)null, var8);
      } catch (Exception var9) {
         Logger.getLogger(SolicitudPojoVM.class.getName()).log(Level.SEVERE, (String)null, var9);
      }

      return response;
   }

   public List<Map<String, String>> listaAjuntosSolicitude(String idsolicitud, String consecutivo, boolean detalle) {
      List<Map<String, String>> response = new ArrayList();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumSolicitudServiceDefi.GET_ADJUNTOS_SOLICITUD.getUrl().replace("${idsolicitud}", idsolicitud).replace("${detalle}", String.valueOf(detalle)).replace("${consecutivo}", consecutivo));
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         String paramTpl = this.gtwayHttp.getRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString());
         response = (List)mapper.readValue(paramTpl, List.class);
      } catch (JsonProcessingException var8) {
         Logger.getLogger(SolicitudPojoVM.class.getName()).log(Level.SEVERE, (String)null, var8);
      }

      return (List)response;
   }
}
