// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.error;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.ContextImage;

public class ErrorTokenNoValido extends WebPage {
   public ErrorTokenNoValido() {
   }

   protected void onInitialize() {
      super.onInitialize();
      this.checkStaless();
      ContextImage logocia = new ContextImage("logocia", "images/logo_bbva.png");
      this.queue(new Component[]{logocia});
   }

   private void checkStaless() {
      this.visitChildren((component, visit) -> {
         if (!component.isStateless()) {
         }

      });
   }
}
