// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.negocio;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.store.model.fideicomiso.FideicomisoPojo;
import com.fiduciaria.microapp.store.model.referunica.ReferenciaUnicaPojo;
import com.fiduciaria.microapp.store.service.EnumNegocioServiceDefi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.util.io.IClusterable;

public class NegocioVM implements IClusterable {
   private final IGenericHttpClient gtwayHttp;
   private final NegocioParam parametros;

   public NegocioVM(IGenericHttpClient gtwy) {
      this.gtwayHttp = gtwy;
      this.parametros = new NegocioParam();
      this.parametros.setInicio(0L);
      this.parametros.setCuenta(0L);
   }

   public List<NegocioPojo> fideicomisosUsuario(String usuario) {
      List<NegocioPojo> response = new ArrayList();
      StringBuilder urlServFidei = new StringBuilder();
      urlServFidei.append(AppConfParamConstant.getBaseServiceDomain());
      urlServFidei.append(EnumNegocioServiceDefi.GET_FIDEICOMISO_X_USUARIO.getUrl().replace("${usuario}", usuario));
      String fideicomisosRta = this.gtwayHttp.getRESTGralService(AppConfParamConstant.getUrltorestserver(), urlServFidei.toString());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);
      new ArrayList();

      try {
         List<FideicomisoPojo> fideicomisos = Arrays.asList((FideicomisoPojo[])mapper.readValue(fideicomisosRta, FideicomisoPojo[].class));
         fideicomisos.stream().forEach((fideicomiso) -> {
            NegocioPojo obj = new NegocioPojo();
            obj.setTipoNegocio(EnumTipoNegocio.FIDEICOMISO);
            obj.setReferenciaUnica(new ReferenciaUnicaPojo());
            obj.setFideicomiso(fideicomiso);
            response.add(obj);
         });
      } catch (JsonProcessingException var8) {
         Logger.getLogger(NegocioVM.class.getName()).log(Level.SEVERE, (String)null, var8);
      }

      return response;
   }

   public List<NegocioPojo> referenciaUnicaUsuarioCliente(String usuario) {
      List<NegocioPojo> response = new ArrayList();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumNegocioServiceDefi.GET_REFERENCIA_UNICA_X_USAURIO_CLIENTE.getUrl().replace("${usuario}", usuario));
      String configuraciones = this.gtwayHttp.getRESTGralService(AppConfParamConstant.getUrltorestserver(), urlServ.toString());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);
      new ArrayList();

      try {
         List<Map<String, String>> dataServicio = (List)mapper.readValue(configuraciones, List.class);
         dataServicio.stream().forEach((fila) -> {
            NegocioPojo obj = new NegocioPojo();
            obj.setTipoNegocio(EnumTipoNegocio.REFERENCIA_UNICA_ALPHA);
            obj.setReferenciaUnica(new ReferenciaUnicaPojo());
            obj.getReferenciaUnica().setNumeroReferencia((String)fila.get("NUMERO_REFERENCIA".toLowerCase()));
            obj.getReferenciaUnica().setDescripcionReferencia((String)fila.get("DESCRIPCION_REFERENCIA".toLowerCase()));
            obj.getReferenciaUnica().setTipoIdentificacion((String)fila.get("TIPO_IDENTIFICACION".toLowerCase()));
            obj.getReferenciaUnica().setIdentificacion((String)fila.get("IDENTIFICACION".toLowerCase()));
            obj.getReferenciaUnica().setDigitosVerificacion((String)fila.get("DIGITOS_VERIFICACION".toLowerCase()));
            obj.getReferenciaUnica().setNombresRazonSocial((String)fila.get("NOMBRES_RAZON_SOCIAL".toLowerCase()));
            obj.getReferenciaUnica().setCorreoElectronico((String)fila.get("CORREO_ELECTRONICO".toLowerCase()));
            obj.getReferenciaUnica().setUsuarioCrea((String)fila.get("USUARIO_CREA".toLowerCase()));
            obj.getReferenciaUnica().setDatetimeCreacion((String)fila.get("DATETIME_CREATION".toLowerCase()));
            obj.getReferenciaUnica().setDatetimeUpdate((String)fila.get("DATETIME_UPDATE".toLowerCase()));
            obj.getReferenciaUnica().setEstado((String)fila.get("ESTADO".toLowerCase()));
            obj.getReferenciaUnica().setTipoReferencia((String)fila.get("TIPOREFERENCIA".toLowerCase()));
            obj.getReferenciaUnica().setReferenciaPadre((String)fila.get("REFERENCIAPADRE".toLowerCase()));
            obj.getReferenciaUnica().setNumeroFirmasAutorizar((String)fila.get("NUMEROFIRMASAUTIRZAR".toLowerCase()));
            response.add(obj);
         });
      } catch (JsonProcessingException var8) {
         Logger.getLogger(NegocioVM.class.getName()).log(Level.SEVERE, (String)null, var8);
      }

      return response;
   }

   public List<NegocioPojo> referenciasUnica() {
      List<NegocioPojo> response = new ArrayList();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumNegocioServiceDefi.GET_REFERENCIA_UNICA.getUrl());
      String configuraciones = this.gtwayHttp.getRESTGralService(AppConfParamConstant.getUrltorestserver(), urlServ.toString());
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);
      new ArrayList();

      try {
         List<Map<String, String>> parametros = (List)mapper.readValue(configuraciones, List.class);
         parametros.stream().forEach((fila) -> {
            NegocioPojo obj = new NegocioPojo();
            obj.setTipoNegocio(EnumTipoNegocio.REFERENCIA_UNICA_ALPHA);
            obj.setReferenciaUnica(new ReferenciaUnicaPojo());
            obj.getReferenciaUnica().setNumeroReferencia((String)fila.get("NUMERO_REFERENCIA".toLowerCase()));
            obj.getReferenciaUnica().setDescripcionReferencia((String)fila.get("DESCRIPCION_REFERENCIA".toLowerCase()));
            obj.getReferenciaUnica().setTipoIdentificacion((String)fila.get("TIPO_IDENTIFICACION".toLowerCase()));
            obj.getReferenciaUnica().setIdentificacion((String)fila.get("IDENTIFICACION".toLowerCase()));
            obj.getReferenciaUnica().setDigitosVerificacion((String)fila.get("DIGITOS_VERIFICACION".toLowerCase()));
            obj.getReferenciaUnica().setNombresRazonSocial((String)fila.get("NOMBRES_RAZON_SOCIAL".toLowerCase()));
            obj.getReferenciaUnica().setCorreoElectronico((String)fila.get("CORREO_ELECTRONICO".toLowerCase()));
            obj.getReferenciaUnica().setUsuarioCrea((String)fila.get("USUARIO_CREA".toLowerCase()));
            obj.getReferenciaUnica().setDatetimeCreacion((String)fila.get("DATETIME_CREATION".toLowerCase()));
            obj.getReferenciaUnica().setDatetimeUpdate((String)fila.get("DATETIME_UPDATE".toLowerCase()));
            obj.getReferenciaUnica().setEstado((String)fila.get("ESTADO".toLowerCase()));
            obj.getReferenciaUnica().setTipoReferencia((String)fila.get("TIPOREFERENCIA".toLowerCase()));
            obj.getReferenciaUnica().setReferenciaPadre((String)fila.get("REFERENCIAPADRE".toLowerCase()));
            obj.getReferenciaUnica().setNumeroFirmasAutorizar((String)fila.get("NUMEROFIRMASAUTIRZAR".toLowerCase()));
            response.add(obj);
         });
      } catch (JsonProcessingException var7) {
         Logger.getLogger(NegocioVM.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      return response;
   }

   public long totalRegistrosNegocioGeneral() {
      return (long)this.consultaNegociosGeneralXUsuario(this.parametros).getTotalRegistros();
   }

   public List<NegocioPojo> negociosGeneralXUsuario() {
      List<NegocioPojo> response = new ArrayList();
      NegocioParam negociosRta = this.consultaNegociosGeneralXUsuario(this.parametros);
      negociosRta.getRowsquery().stream().forEach((fila) -> {
         NegocioPojo obj = new NegocioPojo();
         obj.setTipoNegocio(EnumTipoNegocio.REFERENCIA_UNICA_ALPHA);
         obj.setReferenciaUnica(new ReferenciaUnicaPojo());
         obj.setCodigoNegocio((String)fila.get(NegocioColumnEnum.CODIGO_NEGOCIO.toString().toLowerCase()));
         obj.setDescripcionNegocio((String)fila.get(NegocioColumnEnum.DESCRIPCION_NEGOCIO.toString().toLowerCase()));
         obj.setDigitosVerificacion((String)fila.get(NegocioColumnEnum.DIGITOS_VERIFICACION.toString().toLowerCase()));
         obj.setEstado((String)fila.get(NegocioColumnEnum.ESTADO.toString().toLowerCase()));
         obj.setIdentificacion((String)fila.get(NegocioColumnEnum.IDENTIFICACION.toString().toLowerCase()));
         obj.setNombresRazonSocial((String)fila.get(NegocioColumnEnum.NOMBRES_RAZON_SOCIAL.toString().toLowerCase()));
         obj.setTipoIdentificacion((String)fila.get(NegocioColumnEnum.TIPO_IDENTIFICACION.toString().toLowerCase()));
         obj.setTipoNegocio(EnumTipoNegocio.valueOf(((String)fila.get(NegocioColumnEnum.TIPO_NEGOCIO.toString().toLowerCase())).toUpperCase()));
         obj.setTiporeferencia((String)fila.get(NegocioColumnEnum.TIPOREFERENCIA.toString().toLowerCase()));
         response.add(obj);
      });
      return response;
   }

   private NegocioParam consultaNegociosGeneralXUsuario(NegocioParam param) {
      NegocioParam response = new NegocioParam();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumNegocioServiceDefi.POST_NEGOCIO_GENERAL.getUrl());

      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.setDefaultPropertyInclusion(Include.NON_NULL);
         String entity = mapper.writeValueAsString(param);
         String entidadout = this.gtwayHttp.postRESTgService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), entity);
         response = (NegocioParam)mapper.readValue(entidadout, NegocioParam.class);
      } catch (JsonProcessingException var7) {
         Logger.getLogger(NegocioVM.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      return response;
   }

   public List<NegocioPojo> negociosGeneralXDominio() {
      List<NegocioPojo> response = new ArrayList();
      NegocioParam negociosRta = this.ConsultaNegociosGeneralXDominio(this.parametros);
      negociosRta.getRowsquery().stream().forEach((fila) -> {
         NegocioPojo obj = new NegocioPojo();
         obj.setTipoNegocio(EnumTipoNegocio.REFERENCIA_UNICA_ALPHA);
         obj.setReferenciaUnica(new ReferenciaUnicaPojo());
         obj.setCodigoNegocio((String)fila.get(NegocioColumnEnum.CODIGO_NEGOCIO.toString().toLowerCase()));
         obj.setDescripcionNegocio((String)fila.get(NegocioColumnEnum.DESCRIPCION_NEGOCIO.toString().toLowerCase()));
         obj.setDigitosVerificacion((String)fila.get(NegocioColumnEnum.DIGITOS_VERIFICACION.toString().toLowerCase()));
         obj.setEstado((String)fila.get(NegocioColumnEnum.ESTADO.toString().toLowerCase()));
         obj.setIdentificacion((String)fila.get(NegocioColumnEnum.IDENTIFICACION.toString().toLowerCase()));
         obj.setNombresRazonSocial((String)fila.get(NegocioColumnEnum.NOMBRES_RAZON_SOCIAL.toString().toLowerCase()));
         obj.setTipoIdentificacion((String)fila.get(NegocioColumnEnum.TIPO_IDENTIFICACION.toString().toLowerCase()));
         obj.setTipoNegocio(EnumTipoNegocio.desdeNombre(((String)fila.get(NegocioColumnEnum.TIPO_NEGOCIO.toString().toLowerCase())).toUpperCase()));
         obj.setTiporeferencia((String)fila.get(NegocioColumnEnum.TIPOREFERENCIA.toString().toLowerCase()));
         response.add(obj);
      });
      return response;
   }

   private NegocioParam ConsultaNegociosGeneralXDominio(NegocioParam param) {
      NegocioParam response = new NegocioParam();
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumNegocioServiceDefi.GET_NEGOCIO_DOMINIO.getUrl().replace("${dominioid}", param.getDominioid()));

      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.setDefaultPropertyInclusion(Include.NON_NULL);
         mapper.writeValueAsString(param);
         String entidadout = this.gtwayHttp.getRESTGralService(AppConfParamConstant.getUrltorestserver(), urlServ.toString());
         response = (NegocioParam)mapper.readValue(entidadout, NegocioParam.class);
      } catch (JsonProcessingException var7) {
         Logger.getLogger(NegocioVM.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      return response;
   }

   public void crearAlertaCliente(String numeroReferencia, String perfilusuario, String loginusuario) {
      StringBuilder urlServ = new StringBuilder();
      urlServ.append(AppConfParamConstant.getBaseServiceDomain());
      urlServ.append(EnumNegocioServiceDefi.POST_ALERTA_CLIENTE.getUrl().replace("${numeroreferencia}", numeroReferencia).replace("${perfilusuario}", Base64.getUrlEncoder().encodeToString(perfilusuario.getBytes())));
      this.gtwayHttp.postRESTService(AppConfParamConstant.getUrltorestserver(), urlServ.toString(), loginusuario);
   }

   public IGenericHttpClient getGtwayHttp() {
      return this.gtwayHttp;
   }

   public NegocioParam getParametros() {
      return this.parametros;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof NegocioVM)) {
         return false;
      } else {
         NegocioVM other = (NegocioVM)o;
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
      return other instanceof NegocioVM;
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
      return "NegocioVM(gtwayHttp=" + this.getGtwayHttp() + ", parametros=" + this.getParametros() + ")";
   }
}
