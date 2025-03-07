// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.escritura;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.store.service.EnumEscrituraServiceDef;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EscrituraVM implements Serializable {
   private final IGenericHttpClient gtwayHttp;

   public static EscrituraVM instance(IGenericHttpClient gtwy) {
      return new EscrituraVM(gtwy);
   }

   public EscrituraVM(IGenericHttpClient gtwy) {
      this.gtwayHttp = gtwy;
   }

   public List<CargueFirmasEscrituraError> guardarCondicionesManejo(CondicionesManejoPojo condiciones) {
      List<CargueFirmasEscrituraError> respuesta = new ArrayList();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumEscrituraServiceDef.POST_ESCRITURA_CONDICIONES_MANEJO.getUrl());

      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.setDefaultPropertyInclusion(Include.NON_NULL);
         String entity = mapper.writeValueAsString(condiciones);
         String entidadout = this.gtwayHttp.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), entity);
         CargueFirmasEscrituraError[] rstservice = (CargueFirmasEscrituraError[])mapper.readValue(entidadout, CargueFirmasEscrituraError[].class);
         respuesta = Arrays.asList(rstservice);
      } catch (JsonProcessingException var8) {
         Logger.getLogger(EscrituraVM.class.getName()).log(Level.SEVERE, (String)null, var8);
      }

      return (List)respuesta;
   }
}
