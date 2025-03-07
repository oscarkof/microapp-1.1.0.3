package com.fiduciaria.microapp.page.statefull.gestion.usuario;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.base.NombreAccionesEnum;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.componentes.MaskInputFormatAppender;
import com.fiduciaria.microapp.componentes.TxtFieldFactory;
import com.fiduciaria.microapp.componentes.alerta.ModalAlert;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoPojo;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoVM;
import com.fiduciaria.microapp.store.model.rastro.RastroAccionPojo;
import com.fiduciaria.microapp.store.model.rastro.TraceVM;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojo;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojoVM;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.StringValidator;

public class CrearUsuarioForm extends BasePanel {
  @SpringBean
  IGenericHttpClient httpGtwy;
  
  private final UsuarioPojo usuarioModel;
  
  public CrearUsuarioForm(String id) {
    super(id);
    this.usuarioModel = new UsuarioPojo();
    this.usuarioModel.setIdPrincipal("automatico");
  }
  
  public CrearUsuarioForm(String id, IModel<UsuarioPojo> model) {
    super(id, model);
    this.usuarioModel = Objects.nonNull(model.getObject()) ? (UsuarioPojo)model.getObject() : new UsuarioPojo();
    this.usuarioModel.setIdPrincipal("automatico");
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initFormularioCreacion();
  }
  
  private void initFormularioCreacion() {
    final Form formularioNuevoUsuario = new Form("formularioNuevoUsuario");
    queue(new Component[] { (Component)formularioNuevoUsuario });
    LoadableDetachableModel<List<String>> listadoAplicativo = new LoadableDetachableModel<List<String>>() {
        protected List<String> load() {
          List<String> response = new ArrayList<>();
          response.add("ALPHA");
          response.add("FIDUSAP APPEON");
          response.add("SALAVENTAS");
          response.add("FIRMAS");
          response.add("PAGOS WEB");
          return response;
        }
      };
    DropDownChoice aplicativo = new DropDownChoice("aplicativo", (IModel)listadoAplicativo);
    aplicativo.setOutputMarkupPlaceholderTag(true);
    aplicativo.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Seleccione la aplicacia la cual se creara el usuario.") });
    aplicativo.setLabel((IModel)Model.of("Nombre Aplicación"));
    aplicativo.setRequired(true);
    queue(new Component[] { (Component)aplicativo });
    aplicativo.setVisible(false);
    DropDownChoice negocio = new DropDownChoice("negocio", (IModel)listadoAplicativo);
    negocio.setOutputMarkupPlaceholderTag(true);
    negocio.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "") });
    negocio.setLabel((IModel)Model.of("Negocio"));
    negocio.setRequired(true);
    queue(new Component[] { (Component)negocio });
    negocio.setVisible(false);
    LoadableDetachableModel<List<TipoDocumentoPojo>> tiposDocumentos = new LoadableDetachableModel<List<TipoDocumentoPojo>>() {
        protected List<TipoDocumentoPojo> load() {
          TipoDocumentoVM dataObj = new TipoDocumentoVM(CrearUsuarioForm.this.httpGtwy);
          return dataObj.listaTipoDocumentos();
        }
      };
    ChoiceRenderer rendererTipDoc = new ChoiceRenderer("descripciondocumento", "clasedocumento");
    final DropDownChoice<TipoDocumentoPojo> tipoIdentificacion = new DropDownChoice("tipoIdentificacion", LambdaModel.of(this.usuarioModel::getTipoDocumento, this.usuarioModel::setTipoDocumento), (IModel)tiposDocumentos, (IChoiceRenderer)rendererTipDoc);
    tipoIdentificacion.setOutputMarkupPlaceholderTag(true);
    tipoIdentificacion.setRequired(true);
    tipoIdentificacion.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)tipoIdentificacion });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)tipoIdentificacion });
            }
          } });
    tipoIdentificacion.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Indicar el Tipo Documento del usuario") });
    tipoIdentificacion.setLabel((IModel)Model.of("Tipo Identificación"));
    queue(new Component[] { (Component)tipoIdentificacion });
    queue(new Component[] { (Component)TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("codigoUsuario", false, true, "El nombre de usuario (Nombre login), con el cual se identificará para realizar ingreso a la aplicacion, se genera de forma automática.", 
            
            LambdaModel.of(this.usuarioModel::getIdPrincipal, this.usuarioModel::setIdPrincipal), 
            (IModel)Model.of("Nombre de Usuario"), String.class) });
    TextField numeroIdentificacion = TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("numeroIdentificacion", true, false, "", 
        
        LambdaModel.of(this.usuarioModel::getIdentificacion, this.usuarioModel::setIdentificacion), 
        (IModel)Model.of("Nde identificación"), String.class);
    queue(new Component[] { (Component)numeroIdentificacion });
    numeroIdentificacion.add((IValidator)StringValidator.maximumLength(30));
    TextField dv = TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("digitoVerificacion", false, false, "", 
        
        LambdaModel.of(this.usuarioModel::getDigitoVerificacion, this.usuarioModel::setDigitoVerificacion), 
        (IModel)Model.of("D.V."), String.class);
    queue(new Component[] { (Component)dv });
    dv.add((IValidator)StringValidator.exactLength(1));
    dv.add(new Behavior[] { (Behavior)MaskInputFormatAppender.general("[1]", "", null, false, false, true) });
    TextField primerNombre = TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("primerNombre", true, false, "", 
        
        LambdaModel.of(this.usuarioModel::getPrimerNombre, this.usuarioModel::setPrimerNombre), 
        (IModel)Model.of("Primer Nombre"), String.class);
    queue(new Component[] { (Component)primerNombre });
    primerNombre.add((IValidator)StringValidator.maximumLength(30));
    TextField segundoNombre = TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("segundoNombre", false, false, "", 
        
        LambdaModel.of(this.usuarioModel::getSegundoNombre, this.usuarioModel::setSegundoNombre), 
        (IModel)Model.of("Segundo Nombre"), String.class);
    queue(new Component[] { (Component)segundoNombre });
    segundoNombre.add((IValidator)StringValidator.maximumLength(30));
    TextField primerApellido = TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("primerApellido", true, false, "", 
        
        LambdaModel.of(this.usuarioModel::getPrimerApellido, this.usuarioModel::setPrimerApellido), 
        (IModel)Model.of("Primer Apellido"), String.class);
    queue(new Component[] { (Component)primerApellido });
    primerApellido.add((IValidator)StringValidator.maximumLength(30));
    TextField segundoApellido = TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("segundoApellido", false, false, "", 
        
        LambdaModel.of(this.usuarioModel::getSegundoApellido, this.usuarioModel::setSegundoApellido), 
        (IModel)Model.of("Segundo Apellido"), String.class);
    queue(new Component[] { (Component)segundoApellido });
    segundoApellido.add((IValidator)StringValidator.maximumLength(30));
    TextField correoElectronico = TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("correoElectronico", true, false, "", 
        
        LambdaModel.of(this.usuarioModel::getCorreoElectronico, this.usuarioModel::setCorreoElectronico), 
        (IModel)Model.of("Email"), String.class);
    queue(new Component[] { (Component)correoElectronico });
    correoElectronico.add((IValidator)RfcCompliantEmailAddressValidator.getInstance());
    TextField telefono = TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("telefono", false, false, "", 
        
        LambdaModel.of(this.usuarioModel::getNumeroTelefono, this.usuarioModel::setNumeroTelefono), 
        (IModel)Model.of("telefono"), String.class);
    queue(new Component[] { (Component)telefono });
    telefono.add((IValidator)StringValidator.maximumLength(12));
    telefono.add(new Behavior[] { (Behavior)MaskInputFormatAppender.general("[12]", "", null, false, false, true) });
    TextField celular = TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("celular", true, false, "", 
        
        LambdaModel.of(this.usuarioModel::getNumeroCelular, this.usuarioModel::setNumeroCelular), 
        (IModel)Model.of("celular"), String.class);
    queue(new Component[] { (Component)celular });
    celular.add((IValidator)StringValidator.maximumLength(12));
    celular.add(new Behavior[] { (Behavior)MaskInputFormatAppender.general("[12]", "", null, false, false, true) });
    List<String> tiposUsrSalaVenta = new ArrayList<>();
    tiposUsrSalaVenta.add("Promotor");
    tiposUsrSalaVenta.add("Administrador");
    final DropDownChoice<String> tipoUsuarioSalaventas = new DropDownChoice<String>("tipoUsuarioSalaventas", LambdaModel.of(this.usuarioModel::getTipoUsuarioSalaventas, this.usuarioModel::setTipoUsuarioSalaventas), tiposUsrSalaVenta) {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(CrearUsuarioForm.this.usuarioModel.isUsuarioSalaVentas());
        }
      };
    tipoUsuarioSalaventas.setOutputMarkupPlaceholderTag(true);
    tipoUsuarioSalaventas.setRequired(true);
    tipoUsuarioSalaventas.setLabel((IModel)Model.of("Tipo Usuario Salaventas"));
    tipoUsuarioSalaventas.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)tipoUsuarioSalaventas });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)tipoUsuarioSalaventas });
            }
          } });
    tipoUsuarioSalaventas.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Seleccione el tipo de usuario asignado para sala de ventas") });
    queue(new Component[] { (Component)tipoUsuarioSalaventas });
    CheckBox usarioSalaventas = new CheckBox("usarioSalaventas", LambdaModel.of(this.usuarioModel::isUsuarioSalaVentas, this.usuarioModel::setUsuarioSalaVentas));
    queue(new Component[] { (Component)usarioSalaventas });
    usarioSalaventas.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              CrearUsuarioForm.this.usuarioModel.setTipoUsuarioSalaventas(null);
              target.add(new Component[] { (Component)tipoUsuarioSalaventas });
            }
          } });
    AjaxSubmitLink submitBtn = new AjaxSubmitLink("submitBtn", formularioNuevoUsuario) {
        String nombreUsuarioCreado = "";
        
        boolean exito = false;
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)formularioNuevoUsuario });
        }
        
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          ModalAlert modalAlerta = new ModalAlert(CrearUsuarioForm.this.getModalContentId()) {
              public void cerrarModal(AjaxRequestTarget target) {
                showModal(false, target);
                removeModal();
                //CrearUsuarioForm.this.accionCierreModalAlerta(target, CrearUsuarioForm.this.exito, CrearUsuarioForm.null.this.nombreUsuarioCreado);
              }
            };
          RastroAccionPojo rastro = new RastroAccionPojo();
          rastro.setAccion(NombreAccionesEnum.CREAR_USUARIO.getAccion());
          rastro.setTipoAccion(NombreAccionesEnum.CREAR_USUARIO.getTipoAccion());
          rastro.setAccionUuid(UUID.randomUUID().toString());
          rastro.setUsuarioSess(CrearUsuarioForm.this.usuarioSesion());
          UsuarioPojoVM usrvm = new UsuarioPojoVM(CrearUsuarioForm.this.httpGtwy);
          String tokenSeguridad = AuthenticatedWebSession.get().getAttribute("fidutoken").toString();
          String valSarlf = usrvm.validarSarlaf(CrearUsuarioForm.this.usuarioModel.getTipoDocumento().getClasedocumento(), CrearUsuarioForm.this
              .usuarioModel.getIdentificacion(), 
              Objects.nonNull(CrearUsuarioForm.this.usuarioModel.getDigitoVerificacion()) ? CrearUsuarioForm.this.usuarioModel.getDigitoVerificacion() : "0", tokenSeguridad);
          if (valSarlf.equalsIgnoreCase("OK")) {
            String rta = CrearUsuarioForm.this.crearUsuario(target, (IModel<UsuarioPojo>)new Model((Serializable)CrearUsuarioForm.this.usuarioModel));
            if (!rta.equalsIgnoreCase("{}") && 
              !rta.equalsIgnoreCase("{null}") && 
              !Strings.isEmpty(rta)) {
              this.nombreUsuarioCreado = rta;
              this.exito = true;
              CrearUsuarioForm.this.accionCreacionOKUsuario(target, this.exito, this.nombreUsuarioCreado);
              modalAlerta.getMensajesSuccess().add("Usuario creado con exito con login de usuario " + rta);
              rastro.setIdAccion(rta);
              rastro.setMsg("Usuario creado con login de usuario " + rta);
            } else {
              modalAlerta.getMensajesError().add("Fallla creacide usuarios." + rta);
              rastro.setMsg("Fallcreaciusuario" + rta);
            } 
            TraceVM.instance(CrearUsuarioForm.this.httpGtwy, AuthenticatedWebSession.get().getRoles().hasRole("UCA"), CrearUsuarioForm.this.usuarioSesion()).registraRastro(rastro);
          } else {
            modalAlerta.getMensajesError().add("Fallvalidacisarlaf.");
            rastro.setMsg("Fallcreaciusuario por validacion sarlaf");
            TraceVM.instance(CrearUsuarioForm.this.httpGtwy, AuthenticatedWebSession.get().getRoles().hasRole("UCA"), CrearUsuarioForm.this.usuarioSesion()).registraRastro(rastro);
          } 
          CrearUsuarioForm.this.addModal((Component)modalAlerta);
          CrearUsuarioForm.this.showModal(true, target);
        }
      };
    queue(new Component[] { (Component)submitBtn });
    AjaxSubmitLink resetBtn = new AjaxSubmitLink("resetBtn", formularioNuevoUsuario) {
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          CrearUsuarioForm.this.usuarioModel.clear();
          if (formularioNuevoUsuario.hasFeedbackMessage())
            formularioNuevoUsuario.getFeedbackMessages().clear(); 
          formularioNuevoUsuario.clearInput();
          target.add(new Component[] { (Component)formularioNuevoUsuario });
        }
        
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          CrearUsuarioForm.this.usuarioModel.clear();
          if (formularioNuevoUsuario.hasFeedbackMessage())
            formularioNuevoUsuario.getFeedbackMessages(); 
          formularioNuevoUsuario.clearInput();
          target.add(new Component[] { (Component)formularioNuevoUsuario });
        }
      };
    queue(new Component[] { (Component)resetBtn });
  }
  
  public void accionCierreModalAlerta(AjaxRequestTarget target, boolean exito, String nombreUsuarioCreado) {}
  
  public void accionCreacionOKUsuario(AjaxRequestTarget target, boolean exito, String nombreUsuarioCreado) {}
  
  private String crearUsuario(AjaxRequestTarget target, IModel<UsuarioPojo> model) {
    UsuarioPojoVM userVM = new UsuarioPojoVM(this.httpGtwy);
    ((UsuarioPojo)model.getObject()).setFidutoken((String)AuthenticatedWebSession.get().getAttribute("fidutoken"));
    ((UsuarioPojo)model.getObject()).setUsuarioSession(usuarioSesion());
    String origen = getString("app.email.correo.origen");
    String subject = getString("app.email.correo.reset.subject");
    return userVM.crearUsuario((UsuarioPojo)model.getObject(), usuarioSesion(), subject, origen, 
        AuthenticatedWebSession.get().getRoles().hasRole("UCA"), 
        usuarioSesion());
  }
}
