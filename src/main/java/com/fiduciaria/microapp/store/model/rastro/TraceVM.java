// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.rastro;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.store.service.EnumRastroServiceDef;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.util.io.IClusterable;

public class TraceVM implements IClusterable {
   private final IGenericHttpClient gtwayHttp;
   private final TraceParam parametros;
   private final boolean rolUCA;
   private final String usuarioUCA;

   public static TraceVM instance(IGenericHttpClient gtwy, boolean roluca, String usuariouca) {
      return new TraceVM(gtwy, roluca, usuariouca);
   }

   public TraceVM(IGenericHttpClient gtwy, boolean roluca, String usuariouca) {
      this.gtwayHttp = gtwy;
      this.parametros = new TraceParam();
      this.parametros.setInicio(0L);
      this.parametros.setCuenta(0L);
      this.rolUCA = roluca;
      this.usuarioUCA = usuariouca;
   }

   public void registraRastro(RastroAccionPojo rastro) {
      StringBuilder urlServFidei = new StringBuilder();
      urlServFidei.append(AppConfParamConstant.getBaseServiceDomain());
      urlServFidei.append(EnumRastroServiceDef.POST_RASTRO.getUrl());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         String entityout = mapper.writeValueAsString(rastro);
         this.gtwayHttp.postRESTgService(AppConfParamConstant.getUrltorestserver(), urlServFidei.toString(), entityout);
      } catch (JsonProcessingException var6) {
         Logger.getLogger(TraceVM.class.getName()).log(Level.SEVERE, (String)null, var6);
      }

   }

   public long totalRegistros() {
      this.iniConUCA();
      return (long)this.consultaRastro(this.parametros).getTotalRegistros();
   }

   private void iniConUCA() {
      if (this.rolUCA) {
         if (Objects.isNull(this.parametros.getCondiciones())) {
            this.parametros.setCondiciones(new HashMap());
         }

         this.parametros.getCondiciones().put(EnumCampoTraceColumn.USUARIO_SESS, this.usuarioUCA);
      }

   }

   public List<RastroAccionPojo> rastrosActividadEnUsuario() {
      List<RastroAccionPojo> response = new ArrayList();
      this.iniConUCA();
      TraceParam rastroRta = this.consultaRastro(this.parametros);
      rastroRta.getRowsquery().stream().forEach((fila) -> {
         RastroAccionPojo obj = new RastroAccionPojo();
         obj.setAccion(String.valueOf(fila.get(EnumCampoTraceColumn.ACCION.toString().toLowerCase())));
         obj.setAccionUuid(String.valueOf(fila.get(EnumCampoTraceColumn.ACCION_UUID.toString().toLowerCase())));
         obj.setActual(String.valueOf(fila.get(EnumCampoTraceColumn.ACTUAL.toString().toLowerCase())));
         obj.setAnterior(String.valueOf(fila.get(EnumCampoTraceColumn.ANTERIOR.toString().toLowerCase())));
         obj.setEstampaTiempo(String.valueOf(fila.get(EnumCampoTraceColumn.ESTAMPA_TIEMPO.toString().toLowerCase())));
         obj.setId(String.valueOf(fila.get(EnumCampoTraceColumn.ID.toString().toLowerCase())));
         obj.setIdAccion(String.valueOf(fila.get(EnumCampoTraceColumn.ID_ACCION.toString().toLowerCase())));
         obj.setIdRelacionado(String.valueOf(fila.get(EnumCampoTraceColumn.ID_RELACIONADO.toString().toLowerCase())));
         obj.setMsg(String.valueOf(fila.get(EnumCampoTraceColumn.MSG.toString().toLowerCase())));
         obj.setSecuencialAcc(String.valueOf(fila.get(EnumCampoTraceColumn.SECUENCIAL_ACC.toString().toLowerCase())));
         obj.setTipoAccion(String.valueOf(fila.get(EnumCampoTraceColumn.TIPO_ACCION.toString().toLowerCase())));
         obj.setTipoRelacionado(String.valueOf(fila.get(EnumCampoTraceColumn.TIPO_RELACIONADO.toString().toLowerCase())));
         obj.setUsuarioSess(String.valueOf(fila.get(EnumCampoTraceColumn.USUARIO_SESS.toString().toLowerCase())));
         response.add(obj);
      });
      return response;
   }

   private TraceParam consultaRastro(TraceParam param) {
      TraceParam response = new TraceParam();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumRastroServiceDef.POST_RASTRO_LISTADO.getUrl());

      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.setDefaultPropertyInclusion(Include.NON_NULL);
         String entity = mapper.writeValueAsString(param);
         String entidadout = this.gtwayHttp.postRESTgService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), entity);
         response = (TraceParam)mapper.readValue(entidadout, TraceParam.class);
      } catch (JsonProcessingException var7) {
         Logger.getLogger(TraceVM.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      return response;
   }

   public IGenericHttpClient getGtwayHttp() {
      return this.gtwayHttp;
   }

   public TraceParam getParametros() {
      return this.parametros;
   }

   public boolean isRolUCA() {
      return this.rolUCA;
   }

   public String getUsuarioUCA() {
      return this.usuarioUCA;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TraceVM)) {
         return false;
      } else {
         TraceVM other = (TraceVM)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isRolUCA() != other.isRolUCA()) {
            return false;
         } else {
            label49: {
               Object this$gtwayHttp = this.getGtwayHttp();
               Object other$gtwayHttp = other.getGtwayHttp();
               if (this$gtwayHttp == null) {
                  if (other$gtwayHttp == null) {
                     break label49;
                  }
               } else if (this$gtwayHttp.equals(other$gtwayHttp)) {
                  break label49;
               }

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

            Object this$usuarioUCA = this.getUsuarioUCA();
            Object other$usuarioUCA = other.getUsuarioUCA();
            if (this$usuarioUCA == null) {
               if (other$usuarioUCA != null) {
                  return false;
               }
            } else if (!this$usuarioUCA.equals(other$usuarioUCA)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TraceVM;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + (this.isRolUCA() ? 79 : 97);
      Object $gtwayHttp = this.getGtwayHttp();
      result = result * 59 + ($gtwayHttp == null ? 43 : $gtwayHttp.hashCode());
      Object $parametros = this.getParametros();
      result = result * 59 + ($parametros == null ? 43 : $parametros.hashCode());
      Object $usuarioUCA = this.getUsuarioUCA();
      result = result * 59 + ($usuarioUCA == null ? 43 : $usuarioUCA.hashCode());
      return result;
   }

   public String toString() {
      return "TraceVM(gtwayHttp=" + this.getGtwayHttp() + ", parametros=" + this.getParametros() + ", rolUCA=" + this.isRolUCA() + ", usuarioUCA=" + this.getUsuarioUCA() + ")";
   }
}
