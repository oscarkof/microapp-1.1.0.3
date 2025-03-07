// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.basicas;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.store.model.negocio.NegocioVM;
import com.fiduciaria.microapp.store.service.EnumBasicasServiceDef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.util.io.IClusterable;

public class TipoDocumentoVM implements IClusterable {
   private final IGenericHttpClient gtwayHttp;

   public TipoDocumentoVM(IGenericHttpClient gtwy) {
      this.gtwayHttp = gtwy;
   }

   public List<TipoDocumentoPojo> listaTipoDocumentos() {
      List<TipoDocumentoPojo> response = new ArrayList();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumBasicasServiceDef.GET_TIPO_DOCUMENTOS.getUrl());
      String configuraciones = this.gtwayHttp.getRESTGralService(AppConfParamConstant.getUrltorestserver(), urlServ.toString());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         response = Arrays.asList((TipoDocumentoPojo[])mapper.readValue(configuraciones, TipoDocumentoPojo[].class));
      } catch (JsonProcessingException var6) {
         Logger.getLogger(NegocioVM.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

      return (List)response;
   }
}
