// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.actividades.model;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.jwt.JwtUtil;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoPojo;
import com.fiduciaria.microapp.store.service.EnumSolicitudServiceDefi;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.util.io.IClusterable;

public class CorreoNotificacionLinkPreIngresoPojoVM implements IClusterable {
   private final CorreoNotificacionLinkPreIngresoPojo modeloNotificacion;
   private final List<TipoDocumentoPojo> listaTipoDoc;
   private String tokenLink;
   private String msgFeedback;
   private String tipoAlerta = "info";
   private final Map<String, String> tplDefaults;
   private final IGenericHttpClient gwty;

   public CorreoNotificacionLinkPreIngresoPojoVM(IGenericHttpClient gwty) {
      this.gwty = gwty;
      this.modeloNotificacion = new CorreoNotificacionLinkPreIngresoPojo();
      this.tplDefaults = this.paramXdefectoPlantilla();
      this.listaTipoDoc = new ArrayList();
      this.initDefault();
   }

   public void enviarNotificacion() {
      StringBuilder urlServFidei = new StringBuilder();
      urlServFidei.append(AppConfParamConstant.getBaseServiceDomain());
      urlServFidei.append(EnumSolicitudServiceDefi.POST_NOTIF_LINK_PREINGRESO_DTO_USUARIO.getUrl());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);
      byte[] tokenk = JwtUtil.instance().crearKAleatoria();
      this.modeloNotificacion.setTokenK(Base64.getEncoder().encodeToString(tokenk));

      try {
         String entity = mapper.writeValueAsString(this.modeloNotificacion);
         String rsta = this.gwty.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServFidei.toString(), entity);
         this.msgFeedback = "Procesamos con \u00e9xito tu solicitud.";
         this.tipoAlerta = "info";
      } catch (JsonProcessingException var6) {
         this.msgFeedback = "EX: Sucedi\u00f3 un error que no nos permiti\u00f3 procesar tu solicitud.";
         this.tipoAlerta = "danger";
         Logger.getLogger(CorreoNotificacionLinkPreIngresoPojoVM.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

   }

   public Map<String, String> paramXdefectoPlantilla() {
      Map<String, String> response = new HashMap();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumSolicitudServiceDefi.GET_TPL_DEFAULTS.getUrl());
      String paramTpl = this.gwty.getRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         response = (Map)mapper.readValue(paramTpl, Map.class);
      } catch (JsonProcessingException var6) {
         Logger.getLogger(CorreoNotificacionLinkPreIngresoPojoVM.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      return (Map)response;
   }

   public void initDefault() {
      this.modeloNotificacion.setAsuntoTpl((String)this.tplDefaults.get("tpl_preing_asunto"));
      this.modeloNotificacion.setCuerpoCorreoTpl((String)this.tplDefaults.get("tpl_preing_msg"));
      this.modeloNotificacion.setProceso("Crear Link seguro a formulario Preingreso Datos usuario");
   }

   public CorreoNotificacionLinkPreIngresoPojo getModeloNotificacion() {
      return this.modeloNotificacion;
   }

   public String uriExternosJunction() {
      return (String)this.tplDefaults.get("self_svr_uri_externos");
   }

   public String getMsgFeedback() {
      return this.msgFeedback;
   }

   public String getTipoAlerta() {
      return this.tipoAlerta;
   }

   public void setTipoAlerta(String tipoAlerta) {
      this.tipoAlerta = tipoAlerta;
   }

   public void setMsgFeedback(String msgFeedback) {
      this.msgFeedback = msgFeedback;
   }

   public void setListaTipoDoc(List<TipoDocumentoPojo> tipos) {
      this.listaTipoDoc.clear();
      this.listaTipoDoc.addAll(tipos);
   }

   public List<TipoDocumentoPojo> getListaTipoDoc() {
      return this.listaTipoDoc;
   }
}
