// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.error;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.ContextImage;

public class Error404 extends WebPage {
   public Error404() {
   }

   protected void onInitialize() {
      super.onInitialize();
      this.checkStaless();
      ContextImage logocia = new ContextImage("logocia", "images/logo_bbva.png");
      this.queue(new Component[]{logocia});
      ContextImage logoerror = new ContextImage("logoerror", "images/404.png");
      this.queue(new Component[]{logoerror});
   }

   private void checkStaless() {
      this.visitChildren((component, visit) -> {
         if (!component.isStateless()) {
         }

      });
   }
}
