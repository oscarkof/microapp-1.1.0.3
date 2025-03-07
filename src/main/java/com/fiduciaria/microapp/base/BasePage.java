// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.base;

import com.fiduciaria.microapp.auth.page.MicroAppSignInPage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;

public class BasePage extends WebPage {
   private static final PackageResourceReference JSEventMng = new PackageResourceReference(BasePage.class,
         "globalJSEvent.js");
   protected static final MetaDataKey<List<Class<? extends Behavior>>> BEHAVIOR_TYPE = new MetaDataKey<List<Class<? extends Behavior>>>() {};

   public BasePage(PageParameters parameters) {
      super(parameters);
      this.traceInit();
      this.getSession().setLocale(new Locale("es", "CO"));
   }

   protected void onInitialize() {
      super.onInitialize();
   }

   private void traceInit() {
      WebRequest request = (WebRequest) RequestCycle.get().getRequest();
      List<String> cookieNames = new ArrayList();
      request.getCookies().forEach((cookie) -> {
         cookieNames.add(cookie.getName() + " " + cookie.getPath() + " " + cookie.getDomain());
      });
      Logger.getLogger(MicroAppSignInPage.class.getSimpleName()).log(Level.SEVERE, "ckies {0} ",
            new Object[] { Arrays.toString(cookieNames.toArray()) });
   }

   public void renderHead(IHeaderResponse response) {
      super.renderHead(response);
      response.render(CssHeaderItem.forUrl("css/patternfly.css", "screen", (String) null, "preload"));
      response.render(CssHeaderItem.forUrl("css/line-awesome.css"));
      response.render(CssHeaderItem.forUrl("css/patternfly-addons.css"));
      response.render(CssHeaderItem.forUrl("css/microfrontend.css"));
      response.render(CssHeaderItem.forUrl("css/bbvastl.css"));
      response.render(JavaScriptHeaderItem.forReference(JSEventMng));
   }

   public static void registrarComportamiento(Class<? extends Behavior> behaviorClazz) {
      ((List) RequestCycle.get().getMetaData(BEHAVIOR_TYPE)).add(behaviorClazz);
   }

   class BasePage_1 extends MetaDataKey<List<Class<? extends Behavior>>> {
      public BasePage_1() {
      }
   }
}
