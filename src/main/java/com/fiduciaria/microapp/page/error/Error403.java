// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.error;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;

public class Error403 extends WebPage {
   public Error403() {
   }

   public void renderHead(IHeaderResponse response) {
      super.renderHead(response);
      response.render(CssHeaderItem.forUrl("css/error403.css"));
   }
}
