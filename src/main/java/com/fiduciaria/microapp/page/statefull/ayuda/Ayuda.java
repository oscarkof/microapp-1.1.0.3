// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.ayuda;

import com.fiduciaria.microapp.page.MainPage;
import com.fiduciaria.microapp.page.statefull.ayuda.panel.RecursosAprendizaje;
import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class Ayuda extends MainPage {
   public Ayuda(PageParameters parameters) {
      super(parameters);
   }

   protected void onInitialize() {
      super.onInitialize();
      RecursosAprendizaje mainPanel = new RecursosAprendizaje("mainPanel");
      this.queue(new Component[]{mainPanel});
   }
}
