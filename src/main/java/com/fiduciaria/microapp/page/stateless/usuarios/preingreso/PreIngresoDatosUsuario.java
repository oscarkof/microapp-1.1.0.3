// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.stateless.usuarios.preingreso;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.jwt.AESenc;
import com.fiduciaria.microapp.page.error.ErrorTokenNoValido;
import com.fiduciaria.microapp.page.stateless.BaseStatelessPage;
import com.fiduciaria.microapp.store.model.gui.solicitud.FormularioSoportePojo;
import com.fiduciaria.microapp.store.model.gui.solicitud.TipoFormulario;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.string.Strings;

public class PreIngresoDatosUsuario extends BaseStatelessPage {
   @SpringBean
   private IGenericHttpClient gtwayHttp;
   private final IModel<FormularioPreingresoDatosUsuarioVM> modeloUrlToken = new Model();
   private final IModel<FormularioPreingresoDatosUsuarioVM> modelo = new Model();

   public PreIngresoDatosUsuario(PageParameters parameters) {
      super(parameters);
      String payload = "";
      StringValue jwt = parameters.get("bbvaform");
      if (!jwt.isEmpty()) {
         byte[] aeskey = AppConfParamConstant.getKeyEncry();
         payload = AESenc.decrypt(jwt.toString(), aeskey);
      }

      this.setOutputMarkupPlaceholderTag(true);
      this.modeloUrlToken.setObject(this.deserializarModel(payload));
      if (((FormularioPreingresoDatosUsuarioVM)this.modeloUrlToken.getObject()).getValidoHasta() <= LocalDate.now().toEpochDay()) {
         this.setResponsePage(ErrorTokenNoValido.class);
      } else {
         AppConfParamConstant.setUrltorestserver(((FormularioPreingresoDatosUsuarioVM)this.modeloUrlToken.getObject()).getDefaultServerURI());
         if (Objects.isNull(AppConfParamConstant.getConftoken())) {
            AppConfParamConstant.setConftoken(((FormularioPreingresoDatosUsuarioVM)this.modeloUrlToken.getObject()).getConftoken());
            AppConfParamConstant.setTrustkeystorepass(((FormularioPreingresoDatosUsuarioVM)this.modeloUrlToken.getObject()).getTrustkeystorepass());
            AppConfParamConstant.setTrustkeystoretype(((FormularioPreingresoDatosUsuarioVM)this.modeloUrlToken.getObject()).getTrustkeystoretype());
            AppConfParamConstant.setUritotrustkeystore(((FormularioPreingresoDatosUsuarioVM)this.modeloUrlToken.getObject()).getUritotrustkeystore());
         }

         FormularioPreingresoDatosUsuarioVM dmdeserial = this.deserializarMainModel(((FormularioPreingresoDatosUsuarioVM)this.modeloUrlToken.getObject()).getModelo().getPreingresodatosusuario().getUuid());
         if (Objects.isNull(dmdeserial)) {
            throw new RestartResponseException(ErrorTokenNoValido.class);
         }

         this.modelo.setObject(dmdeserial);
         ((FormularioPreingresoDatosUsuarioVM)this.modelo.getObject()).setGtwayHttp(this.gtwayHttp);
      }

   }

   protected void onInitialize() {
      super.onInitialize();
      FormularioPreingresoDatosUsuario formularioPreIngresoDatosUsuario = new FormularioPreingresoDatosUsuario("formularioPreIngresoDatosUsuario", this.modelo);
      this.queue(new Component[]{formularioPreIngresoDatosUsuario});
   }

   private FormularioPreingresoDatosUsuarioVM deserializarMainModel(String uuid) {
      new FormularioPreingresoDatosUsuarioVM();
      Map<String, String> headers = new HashMap();
      headers.put("uuid", uuid);
      headers.put("conftoken", AppConfParamConstant.getConftoken());
      String rtaCliente = this.gtwayHttp.postRESTgService(AppConfParamConstant.getUrltorestserver(), "services/payment/rest/autogestion/preingreso/recuperar", "", headers);
      if (!Strings.isEmpty(rtaCliente)) {
         ObjectMapper mapper = new ObjectMapper();

         FormularioPreingresoDatosUsuarioVM response;
         try {
            response = (FormularioPreingresoDatosUsuarioVM)mapper.readValue(rtaCliente, FormularioPreingresoDatosUsuarioVM.class);
            response.setStep(this.getStep());
         } catch (JsonProcessingException var7) {
            response = null;
            Logger.getLogger(PreIngresoDatosUsuario.class.getName()).log(Level.SEVERE, (String)null, var7);
         }

         return response;
      } else {
         return null;
      }
   }

   private FormularioPreingresoDatosUsuarioVM deserializarModel(String payload) {
      FormularioPreingresoDatosUsuarioVM response = new FormularioPreingresoDatosUsuarioVM();
      if (!Strings.isEmpty(payload)) {
         response = this.deserializarViewModel(payload);
         response.setStep(this.getStep());
      }

      if (response.getModelo() == null) {
         FormularioSoportePojo obj = new FormularioSoportePojo();
         obj.setTipoFormulario(TipoFormulario.PREINGRESODATOSUSUARIO);
         response.setModelo(obj);
      }

      return response;
   }

   private FormularioPreingresoDatosUsuarioVM deserializarViewModel(String rtaCliente) {
      FormularioPreingresoDatosUsuarioVM response = new FormularioPreingresoDatosUsuarioVM();
      ObjectMapper mapper = new ObjectMapper();
      mapper.setDefaultPropertyInclusion(Include.NON_NULL);

      try {
         response = (FormularioPreingresoDatosUsuarioVM)mapper.readValue(rtaCliente, FormularioPreingresoDatosUsuarioVM.class);
      } catch (IOException var5) {
         Logger.getLogger(FormularioPreingresoDatosUsuario.class.getName()).log(Level.SEVERE, (String)null, var5);
      }

      return response;
   }

   private Integer getStep() {
      StringValue value = this.getPage().getPageParameters().get("step");
      return !value.isNull() && !value.isEmpty() ? this.getPage().getPageParameters().get("step").toInt(1) : 1;
   }
}
