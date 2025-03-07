// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.ayuda.panel;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.util.io.IClusterable;

public class ArchivoPojoVM implements IClusterable {
   private final IGenericHttpClient gtwayHttp;

   public static ArchivoPojoVM instance(IGenericHttpClient gtwy) {
      return new ArchivoPojoVM(gtwy);
   }

   public ArchivoPojoVM(IGenericHttpClient gtwy) {
      this.gtwayHttp = gtwy;
   }

   public List<ArchivoPojo> listaArchivosAyuda() {
      List<ArchivoPojo> response = new ArrayList();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append("/basicas/manuales/ayuda");
      String configuraciones = this.gtwayHttp.getRESTGralService(AppConfParamConstant.getUrltorestserver(), urlServ.toString());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         response = Arrays.asList((ArchivoPojo[])mapper.readValue(configuraciones, ArchivoPojo[].class));
      } catch (JsonProcessingException var6) {
         Logger.getLogger(ArchivoPojoVM.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      return (List)response;
   }

   public List<String> listaDirectorioConfArchivos() {
      List<String> response = new ArrayList();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append("/basicas/manuales/ayuda/directorio");
      String configuraciones = this.gtwayHttp.getRESTGralService(AppConfParamConstant.getUrltorestserver(), urlServ.toString());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         response = Arrays.asList((String[])mapper.readValue(configuraciones, String[].class));
      } catch (JsonProcessingException var6) {
         Logger.getLogger(ArchivoPojoVM.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      return (List)response;
   }

   public String agregarArchivoAyuda(ArchivoPojo entidad) {
      String response = "";
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append("/basicas/manuales/ayuda/nuevo");
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         String enitityStr = mapper.writeValueAsString(entidad);
         String var6 = this.gtwayHttp.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), enitityStr);
      } catch (JsonProcessingException var7) {
         Logger.getLogger(ArchivoPojoVM.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      return response;
   }

   public String deleteArchivoAyuda(ArchivoPojo entidad) {
      String response = "";
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append("/basicas/manuales/ayuda/delete");
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         String enitityStr = mapper.writeValueAsString(entidad);
         String var6 = this.gtwayHttp.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), enitityStr);
      } catch (JsonProcessingException var7) {
         Logger.getLogger(ArchivoPojoVM.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      return response;
   }
}
