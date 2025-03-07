// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.base;

import com.fiduciaria.microapp.IAuthenticatedWebPage;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@AuthorizeInstantiation({"UCA", "UIFS", "UIFA"})
public class AuthenticatedBasePage extends BasePage implements IAuthenticatedWebPage {
   public AuthenticatedBasePage(PageParameters parameters) {
      super(parameters);
   }

   protected void onConfigure() {
      super.onConfigure();
   }
}
