// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.store.service.EnumSolicitudServiceDefi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TareaPojoVM {
   private final IGenericHttpClient gtwayHttp;

   public TareaPojoVM(IGenericHttpClient gtwayHttp) {
      this.gtwayHttp = gtwayHttp;
   }

   public static TareaPojoVM instance(GenericHttpClient gtwayHttp) {
      return new TareaPojoVM(gtwayHttp);
   }

   public List<TareaPojo> listaTareaXSolicitud(String numeroRadicado) {
      List<TareaPojo> response = new ArrayList();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumSolicitudServiceDefi.GET_TAREAS_X_SOLICITUD.getUrl().replace("${idradicado}", numeroRadicado));
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         String paramTpl = this.gtwayHttp.getRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString());
         response = Arrays.asList((TareaPojo[])mapper.readValue(paramTpl, TareaPojo[].class));
      } catch (JsonProcessingException var6) {
         Logger.getLogger(TareaPojoVM.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      return (List)response;
   }

   public List<TareaPojo> listaTareaXUsuario(String numeroRadicado) {
      List<TareaPojo> response = new ArrayList();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumSolicitudServiceDefi.GET_TAREAS_X_USUARIO.getUrl().replace("${username}", numeroRadicado));
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         String paramTpl = this.gtwayHttp.getRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString());
         response = Arrays.asList((TareaPojo[])mapper.readValue(paramTpl, TareaPojo[].class));
      } catch (JsonProcessingException var6) {
         Logger.getLogger(TareaPojoVM.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      return (List)response;
   }

   public String guardarTarea(TareaPojo tarea) {
      String response = "";
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumSolicitudServiceDefi.POST_GUARDAR_TAREA.getUrl());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         String entitytarea = mapper.writeValueAsString(tarea);
         String rtasrvport = this.gtwayHttp.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), entitytarea);
         response = "";
      } catch (JsonProcessingException var7) {
         response = "error";
         Logger.getLogger(TareaPojoVM.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      return response;
   }
}
