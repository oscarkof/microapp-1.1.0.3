package com.fiduciaria.microapp.page.statefull.gestion.usuario;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import com.fiduciaria.microapp.page.statefull.gestion.usuario.model.GestionUsuariosVM;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojo;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojoVM;
import com.fiduciaria.microapp.store.model.usuario.nextgen.CredencialNextGen;
import com.fiduciaria.microapp.store.model.usuario.nextgen.UsuarioExternoNextGen;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxPreventSubmitBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.form.validation.IFormValidator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.PatternValidator;

public class CredencialesMainPanel extends BasePanel {
  @SpringBean
  IGenericHttpClient httpGtwy;
  
  private final IModel<GestionUsuariosVM> modelo;
  
  private final UsuarioExternoNextGen usuarioNextGen;
  
  private String confirmarPassword;
  
  private final List<String> mensajesError;
  
  private final List<String> mensajesExito;
  
  private class UpdateAlertMesgs extends Behavior {
    private UpdateAlertMesgs() {}
  }
  
  private final String INPUT_PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
  
  public CredencialesMainPanel(String id, IModel<GestionUsuariosVM> model) {
    super(id, model);
    this.modelo = model;
    this.usuarioNextGen = new UsuarioExternoNextGen();
    this.usuarioNextGen.setOldPassword(new CredencialNextGen());
    this.usuarioNextGen.setPassword(new CredencialNextGen());
    this.usuarioNextGen.setUser(usuarioSesion());
    this.mensajesError = new ArrayList<>();
    this.mensajesExito = new ArrayList<>();
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initFormularioCambioPass();
  }
  
  private void initFormularioCambioPass() {
    WebMarkupContainer alertaError = new WebMarkupContainer("alertaError") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!CredencialesMainPanel.this.mensajesError.isEmpty());
        }
      };
    alertaError.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)alertaError });
    LoadableDetachableModel<String> loadError = new LoadableDetachableModel<String>() {
        protected String load() {
          StringBuilder response = new StringBuilder();
          CredencialesMainPanel.this.mensajesError.forEach(mensaje -> response.append(mensaje).append("<br>"));
          return response.toString();
        }
      };
    alertaError.add(new Behavior[] { new UpdateAlertMesgs() });
    Label msgErr = new Label("mensaje", (IModel)loadError);
    alertaError.add(new Component[] { (Component)msgErr });
    msgErr.setEscapeModelStrings(false);
    WebMarkupContainer alertaExito = new WebMarkupContainer("alertaExito") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!CredencialesMainPanel.this.mensajesExito.isEmpty());
        }
      };
    alertaExito.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)alertaExito });
    alertaExito.add(new Behavior[] { new UpdateAlertMesgs() });
    LoadableDetachableModel<String> loadSuccess = new LoadableDetachableModel<String>() {
        protected String load() {
          StringBuilder response = new StringBuilder();
          CredencialesMainPanel.this.mensajesExito.forEach(mensaje -> response.append(mensaje).append("<br>"));
          return response.toString();
        }
      };
    Label msgExito = new Label("mensaje", (IModel)loadSuccess);
    alertaExito.add(new Component[] { (Component)msgExito });
    msgExito.setEscapeModelStrings(false);
    Form formCambioPassword = new Form("formCambioPassword");
    queue(new Component[] { (Component)formCambioPassword });
    formCambioPassword.add(new Behavior[] { (Behavior)new AjaxPreventSubmitBehavior() });
    Label usuario = new Label("usuario", (IModel)Model.of(usuarioSesion()));
    usuario.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)usuario });
    final PasswordTextField passActual = new PasswordTextField("passActual", LambdaModel.of(this.usuarioNextGen
          .getOldPassword()::getValue, this.usuarioNextGen
          .getOldPassword()::setValue));
    passActual.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
            }
          } });
    passActual.setOutputMarkupPlaceholderTag(true);
    passActual.setRequired(true);
    passActual.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "Contraseantigua", "", "Ingrese la contraseactual con la que ingresa al sitio. ") });
    passActual.setLabel((IModel)Model.of("ContraseActual"));
    queue(new Component[] { (Component)passActual });
    final PasswordTextField passNuevo = new PasswordTextField("passNuevo", LambdaModel.of(this.usuarioNextGen
          .getPassword()::getValue, this.usuarioNextGen
          .getPassword()::setValue));
    passNuevo.setOutputMarkupPlaceholderTag(true);
    passNuevo.setRequired(true);
    passNuevo.add((IValidator)new PatternValidator("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
    passNuevo.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
            }
          } });
    passNuevo.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "Nueva Contraseña", "", "Ingrese la nueva contrapara ingresar al sitio. ") });
    passNuevo.setLabel((IModel)Model.of("Nueva Constraseña"));
    queue(new Component[] { (Component)passNuevo });
    final PasswordTextField passConfirmar = new PasswordTextField("passConfirmar", LambdaModel.of(this::getConfirmarPassword, this::setConfirmarPassword));
    passConfirmar.setOutputMarkupPlaceholderTag(true);
    passConfirmar.setRequired(true);
    passConfirmar.add((IValidator)new PatternValidator("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
    passConfirmar.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "ConfirmaciContrasenueva", "", "Ingrese la contrasea confirmar. <br> Debe ser igual al valor ingresado como Nueva contrase") });
    passConfirmar.setLabel((IModel)Model.of("Confirmar Contrasenueva"));
    passConfirmar.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
            }
          } });
    queue(new Component[] { (Component)passConfirmar });
    formCambioPassword.add((IFormValidator)new EqualPasswordInputValidator((FormComponent)passNuevo, (FormComponent)passConfirmar));
    AjaxSubmitLink submitForm = new AjaxSubmitLink("submitForm", formCambioPassword) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          CredencialesMainPanel.this.mensajesExito.add("");
          CredencialesMainPanel.this.mensajesError.clear();
          CredencialesMainPanel.this.registrarComportamiento(CredencialesMainPanel.UpdateAlertMesgs.class);
          CredencialesMainPanel.this.cambiarPasword(target);
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          CredencialesMainPanel.this.mensajesError.clear();
          CredencialesMainPanel.this.mensajesExito.clear();
          if (passActual.hasErrorMessage())
            passActual.getFeedbackMessages().toList().forEach(action -> CredencialesMainPanel.this.mensajesError.add(action.getMessage().toString())); 
          if (passNuevo.hasErrorMessage())
            passNuevo.getFeedbackMessages().toList().forEach(action -> CredencialesMainPanel.this.mensajesError.add(action.getMessage().toString())); 
          if (passConfirmar.hasErrorMessage())
            passConfirmar.getFeedbackMessages().toList().forEach(action -> CredencialesMainPanel.this.mensajesError.add(action.getMessage().toString())); 
          CredencialesMainPanel.this.registrarComportamiento(CredencialesMainPanel.UpdateAlertMesgs.class);
        }
      };
    queue(new Component[] { (Component)submitForm });
  }
  
  public String getConfirmarPassword() {
    return this.confirmarPassword;
  }
  
  public void setConfirmarPassword(String confirmarPassword) {
    this.confirmarPassword = confirmarPassword;
  }
  
  private boolean cambiarPasword(AjaxRequestTarget target) {
    UsuarioPojoVM userVM = new UsuarioPojoVM(this.httpGtwy);
    UsuarioPojo usuario = new UsuarioPojo();
    usuario.setIdPrincipal(this.usuarioNextGen.getUser());
    usuario.setNewPassword(this.usuarioNextGen.getPassword().getValue());
    usuario.setPassword(this.usuarioNextGen.getOldPassword().getValue());
    usuario.setFidutoken((String)AuthenticatedWebSession.get().getAttribute("fidutoken"));
    String origen = getString("app.email.correo.origen");
    String subject = getString("app.email.correo.reset.subject");
    userVM.cambiarPassword(usuario, usuarioSesion(), subject, origen);
    return true;
  }
}
