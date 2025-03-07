// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.auth.panel;

import com.fiduciaria.microapp.auth.EnumAuthConstant;
import com.fiduciaria.microapp.auth.PropagatedCredential;
import com.fiduciaria.microapp.page.MainPage;
import com.fiduciaria.microapp.page.error.Error403;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class SignInPanel extends Panel {
   private static final long serialVersionUID = 1L;
   private boolean includeRememberMe;
   private final PropagatedCredential propagatedCredential;
   private final PageParameters parameters;

   public SignInPanel(String id, PageParameters parameters) {
      this(id, false, parameters);
   }

   public SignInPanel(String id, boolean includeRememberMe, PageParameters parameters) {
      super(id);
      this.parameters = parameters;
      this.propagatedCredential = new PropagatedCredential();
      this.includeRememberMe = includeRememberMe;
      this.getCredentialPropagated();
      this.add(new Component[]{new FeedbackPanel("feedback")});
   }

   protected void onInitialize() {
      super.onInitialize();
   }

   private void getCredentialPropagated() {
      StringValue userId = this.parameters.get(EnumAuthConstant.UUID.getName());
      StringValue password = this.parameters.get(EnumAuthConstant.PROPAGATEDPASS.getName());
      this.propagatedCredential.setUuid(userId.toString("anonymous"));
      this.propagatedCredential.setPropagatedPass(password.toString("na"));
   }

   protected void onConfigure() {
      if (this.isSignedIn()) {
         String currentUuid = Objects.nonNull(AuthenticatedWebSession.get().getAttribute("UUID")) ? AuthenticatedWebSession.get().getAttribute("UUID").toString() : "";
         if (!currentUuid.equalsIgnoreCase(this.propagatedCredential.getUuid())) {
            AuthenticatedWebSession.get().invalidateNow();
            this.setResponsePage(MainPage.class);
         }
      }

      if (!this.isSignedIn()) {
         if (Objects.nonNull(this.propagatedCredential)) {
            if (this.signIn(this.propagatedCredential.getUuid(), this.propagatedCredential.getPropagatedPass())) {
               this.onSignInPropagated();
            } else {
               this.setResponsePage(Error403.class);
            }
         }
      } else {
         this.onSignInPropagated();
      }

      super.onConfigure();
   }

   private boolean signIn(String username, String password) {
      return AuthenticatedWebSession.get().signIn(username, password);
   }

   private boolean isSignedIn() {
      return AuthenticatedWebSession.get().isSignedIn();
   }

   protected void onSignInFailed() {
      this.error(this.getLocalizer().getString("signInFailed", this, "Fall\u00f3 el inicio de sesion"));
   }

   protected void onSignInSucceeded() {
      this.continueToOriginalDestination();
      this.setResponsePage(this.getApplication().getHomePage());
   }

   protected void onSignInPropagated() {
      throw new RestartResponseException(MainPage.class);
   }
}
