// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.dominio;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojo;
import com.fiduciaria.microapp.store.service.EnumDominioServiceDefi;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DominioVM implements Serializable {
   private final IGenericHttpClient gtwayHttp;
   private final DominioParam parametros;

   public static DominioVM instance(IGenericHttpClient gtwy) {
      return new DominioVM(gtwy);
   }

   public DominioVM(IGenericHttpClient gtwy) {
      this.gtwayHttp = gtwy;
      this.parametros = new DominioParam();
   }

   public void guardardominio(DominioPojo dominio) {
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumDominioServiceDefi.POST_DOMINIO_DELEGADO.getUrl());

      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.setDefaultPropertyInclusion(Include.NON_NULL);
         String entity = mapper.writeValueAsString(dominio);
         String var5 = this.gtwayHttp.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), entity);
      } catch (JsonProcessingException var6) {
         Logger.getLogger(DominioVM.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

   }

   public DominioParam listarDominios() {
      StringBuilder urlServ = new StringBuilder();
      DominioParam response = new DominioParam();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumDominioServiceDefi.POST_DOMINIO_DELEGADO_LISTA.getUrl());

      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.setDefaultPropertyInclusion(Include.NON_NULL);
         this.parametros.setSoloTotales(false);
         String entity = mapper.writeValueAsString(this.parametros);
         String entidadout = this.gtwayHttp.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), entity);
         response = (DominioParam)mapper.readValue(entidadout, DominioParam.class);
      } catch (JsonProcessingException var6) {
         Logger.getLogger(DominioVM.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      return response;
   }

   public long totalRegistrosDominios() {
      StringBuilder urlServ = new StringBuilder();
      DominioParam response = new DominioParam();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumDominioServiceDefi.POST_DOMINIO_DELEGADO_LISTA.getUrl());

      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.setDefaultPropertyInclusion(Include.NON_NULL);
         this.parametros.setSoloTotales(true);
         String entity = mapper.writeValueAsString(this.parametros);
         String entidadout = this.gtwayHttp.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), entity);
         response = (DominioParam)mapper.readValue(entidadout, DominioParam.class);
      } catch (JsonProcessingException var6) {
         Logger.getLogger(DominioVM.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      return response.getTotalRegistros();
   }

   public DominioParam getParametros() {
      return this.parametros;
   }

   public DominioPojo obtenerDominio(String dominioid) {
      StringBuilder urlServ = new StringBuilder();
      DominioPojo response = new DominioPojo();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumDominioServiceDefi.GET_DOMINIO_DELEGADO.getUrl().replace("${iddominio}", dominioid));

      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.setDefaultPropertyInclusion(Include.NON_NULL);
         String entidadout = this.gtwayHttp.getRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString());
         response = (DominioPojo)mapper.readValue(entidadout, DominioPojo.class);
      } catch (JsonProcessingException var6) {
         Logger.getLogger(DominioVM.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      return response;
   }

   public void agregarUsuarioDominio(DominioPojo dominio, UsuarioPojo usuario, String tipo) {
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumDominioServiceDefi.POST_ADD_USUARIO_DOMINIO_DELEGADO.getUrl().replace("${iddominio}", dominio.getIdentificador()).replace("${tipousuario}", tipo));

      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.setDefaultPropertyInclusion(Include.NON_NULL);
         String entity = mapper.writeValueAsString(usuario);
         this.gtwayHttp.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), entity);
      } catch (JsonProcessingException var7) {
         Logger.getLogger(DominioVM.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

   }
}
