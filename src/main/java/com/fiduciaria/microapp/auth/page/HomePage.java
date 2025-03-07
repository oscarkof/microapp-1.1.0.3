// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.auth.page;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;

public class HomePage extends WebPage {
   public HomePage(PageParameters parameters) {
      super(parameters);
   }

   protected void onInitialize() {
      super.onInitialize();
      ContextImage image = new ContextImage("logoCia", "images/BBVA-Asset-Managemet-SF-RGB.png");
      this.queue(new Component[]{image});
   }

   public void renderHead(IHeaderResponse response) {
      super.renderHead(response);
      response.render(CssHeaderItem.forReference(new CssResourceReference(HomePage.class, "loading.css")));
   }
}
