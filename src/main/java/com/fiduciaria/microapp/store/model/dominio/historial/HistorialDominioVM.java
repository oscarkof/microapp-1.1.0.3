// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.dominio.historial;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.store.service.EnumDominioServiceDefi;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistorialDominioVM implements Serializable {
   private final IGenericHttpClient gtwayHttp;
   private final HistorialDominioParam parametros;

   public static HistorialDominioVM instancia(IGenericHttpClient gtwayHttp) {
      return new HistorialDominioVM(gtwayHttp);
   }

   public HistorialDominioVM(IGenericHttpClient gtwayHttp) {
      this.gtwayHttp = gtwayHttp;
      this.parametros = new HistorialDominioParam();
   }

   public long totalSolicitudes() {
      HistorialDominioParam rta = this.listaSolicitudes(this.parametros);
      return rta.getTotalRegistros();
   }

   public List<HistorialDominioPojo> listaHistorialDominio() {
      HistorialDominioParam rta = this.listaSolicitudes(this.parametros);
      return rta.listadoHistorial();
   }

   private HistorialDominioParam listaSolicitudes(HistorialDominioParam param) {
      HistorialDominioParam response = new HistorialDominioParam();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumDominioServiceDefi.POST_HISTORIAL_DOMINIO_LISTA.getUrl());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         String entity = mapper.writeValueAsString(param);
         String paramTpl = this.gtwayHttp.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), entity);
         response = (HistorialDominioParam)mapper.readValue(paramTpl, HistorialDominioParam.class);
      } catch (JsonProcessingException var7) {
         Logger.getLogger(HistorialDominioVM.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      return response;
   }

   public IGenericHttpClient getGtwayHttp() {
      return this.gtwayHttp;
   }

   public HistorialDominioParam getParametros() {
      return this.parametros;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HistorialDominioVM)) {
         return false;
      } else {
         HistorialDominioVM other = (HistorialDominioVM)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$gtwayHttp = this.getGtwayHttp();
            Object other$gtwayHttp = other.getGtwayHttp();
            if (this$gtwayHttp == null) {
               if (other$gtwayHttp != null) {
                  return false;
               }
            } else if (!this$gtwayHttp.equals(other$gtwayHttp)) {
               return false;
            }

            Object this$parametros = this.getParametros();
            Object other$parametros = other.getParametros();
            if (this$parametros == null) {
               if (other$parametros != null) {
                  return false;
               }
            } else if (!this$parametros.equals(other$parametros)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof HistorialDominioVM;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $gtwayHttp = this.getGtwayHttp();
      result = result * 59 + ($gtwayHttp == null ? 43 : $gtwayHttp.hashCode());
      Object $parametros = this.getParametros();
      result = result * 59 + ($parametros == null ? 43 : $parametros.hashCode());
      return result;
   }

   public String toString() {
      return "HistorialDominioVM(gtwayHttp=" + this.getGtwayHttp() + ", parametros=" + this.getParametros() + ")";
   }
}
