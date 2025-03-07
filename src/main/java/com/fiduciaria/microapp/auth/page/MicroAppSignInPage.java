// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.auth.page;

import com.fiduciaria.microapp.auth.EnumAuthConstant;
import com.fiduciaria.microapp.auth.panel.SignInPanel;
import com.fiduciaria.microapp.base.BaseParameters;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.page.error.Error403;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MicroAppSignInPage extends WebPage {
   private static final long serialVersionUID = 1L;
   @SpringBean
   private GenericHttpClient gtwayHttp;

   public MicroAppSignInPage() {
      this((PageParameters)null);
   }

   public MicroAppSignInPage(PageParameters parameters) {
      this.traceInit();
      this.validatePageParam(parameters);
      this.queue(new Component[]{new SignInPanel("signInPanel", parameters)});
   }

   private void traceInit() {
      WebRequest request = (WebRequest)RequestCycle.get().getRequest();
      List<String> cookieNames = new ArrayList();
      request.getCookies().forEach((cookie) -> {
         cookieNames.add(cookie.getName() + " " + cookie.getPath() + " " + cookie.getDomain());
      });
      Logger.getLogger(MicroAppSignInPage.class.getSimpleName()).log(Level.SEVERE, "ckies {0} ", new Object[]{Arrays.toString(cookieNames.toArray())});
   }

   private boolean validatePageParam(PageParameters parameters) {
      if (Objects.nonNull(parameters) && !parameters.get(EnumAuthConstant.PROPAGATEDPASS.getName()).isEmpty() && !parameters.get(EnumAuthConstant.UUID.getName()).isEmpty()) {
         String token = parameters.get(EnumAuthConstant.PROPAGATEDPASS.getName()).toString();
         String jwt = new String(Base64.decodeBase64(token));
         String[] tockenConf = jwt.split("&&");
         if (Objects.isNull(AppConfParamConstant.getUrltorestserver())) {
            AppConfParamConstant.setUrltorestserver(tockenConf[0]);
            BaseParameters.setAPP_URL_BASE(tockenConf[0]);
         }

         return true;
      } else {
         throw new RestartResponseException(Error403.class);
      }
   }
}
