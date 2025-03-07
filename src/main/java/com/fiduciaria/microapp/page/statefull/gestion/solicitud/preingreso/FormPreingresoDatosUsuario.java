package com.fiduciaria.microapp.page.statefull.gestion.solicitud.preingreso;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.componentes.MaskInputFormatAppender;
import com.fiduciaria.microapp.decorator.behavior.FieldDecorator;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import com.fiduciaria.microapp.page.statefull.dane.DireccionDane;
import com.fiduciaria.microapp.page.statefull.dane.DireccionDanePojo;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoPojo;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoVM;
import com.fiduciaria.microapp.store.dispositivo.CelularPojo;
import com.fiduciaria.microapp.store.model.gui.solicitud.FormularioSoportePojo;
import com.fiduciaria.microapp.store.model.gui.solicitud.PreIngresoDatosUsuarioPojo;
import com.fiduciaria.microapp.store.model.persona.PersonaPojo;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileDescription;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.form.upload.FilesSelectedBehavior;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
//import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.StringValidator;

public class FormPreingresoDatosUsuario extends BasePanel {
  private final FormPreingresoDatosUsuarioVM viewModel;
  
  private final Component feedback = (Component)new FeedbackPanel("feedback");
  
  private final WebMarkupContainer dataview = new WebMarkupContainer("dataview");
  
  private final WebMarkupContainer dataviewSelected = new WebMarkupContainer("dataviewSelected");
  
  private boolean guardado;
  
  private boolean onError;
  
  private String mensajeFeedback;
  
  private final List<File> listaArchivos = new ArrayList<>();
  
  private final List<FileDescription> listadescArchivos = new ArrayList<>();
  
  private UploadFilesPanel adjuntarArchivosUsuario;
  
  private final DireccionDanePojo direccionEstandar;
  
  @SpringBean
  private GenericHttpClient gtwayHttp;
  
  private class UpdSeccOutofFormBehavior extends Behavior {
    private UpdSeccOutofFormBehavior() {}
  }
  
  private class UpdateDireccionEstandarizada extends Behavior {
    private UpdateDireccionEstandarizada() {}
  }
  
  public FormPreingresoDatosUsuario(String id, IModel<FormPreingresoDatosUsuarioVM> model) {
    super(id, model);
    this.viewModel = (FormPreingresoDatosUsuarioVM)model.getObject();
    this.viewModel.setGtwayHttp(this.gtwayHttp);
    this.direccionEstandar = new DireccionDanePojo();
    setOutputMarkupPlaceholderTag(true);
  }
  
  protected void onInitialize() {
    super.onInitialize();
    queue(new Component[] { this.feedback });
    this.feedback.setOutputMarkupPlaceholderTag(true);
    ContextImage imgQuedateEncasa = new ContextImage("imgQuedateEncasa", "images/quedateencasa.png");
    queue(new Component[] { (Component)imgQuedateEncasa });
    final Form formulario = new Form("form");
    WebMarkupContainer alertaProcesamiento = new WebMarkupContainer("alertaProcesamiento") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormPreingresoDatosUsuario.this.guardado);
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          String htlTagCls = "class";
          if (FormPreingresoDatosUsuario.this.onError) {
            tag.put(htlTagCls, "pf-c-alert pf-m-danger");
          } else {
            tag.put(htlTagCls, "pf-c-alert pf-m-success");
          } 
        }
      };
    LoadableDetachableModel<String> mss = new LoadableDetachableModel<String>() {
        protected String load() {
          return FormPreingresoDatosUsuario.this.mensajeFeedback;
        }
      };
    alertaProcesamiento.add(new Component[] { (Component)new Label("mensajeFeedback", (IModel)mss) });
    alertaProcesamiento.add(new Behavior[] { new UpdSeccOutofFormBehavior() });
    alertaProcesamiento.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)alertaProcesamiento });
    WebMarkupContainer seccionesFormulario = new WebMarkupContainer("seccionesFormulario") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!FormPreingresoDatosUsuario.this.guardado);
        }
      };
    seccionesFormulario.add(new Behavior[] { new UpdSeccOutofFormBehavior() });
    seccionesFormulario.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)seccionesFormulario });
    formulario.setMultiPart(true);
    formulario.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)formulario });
    seccionPrimera();
    initPrerrequisitos();
    initTipoSolicitud();
    iniDatosNegocio();
    iniDatosUsuario();
    initCaracteristicasUsuario();
    condicionesManejo();
    initProgressBar();
    initCondicionesManejo();
    initAdjuntos();
    AjaxButton siguienteBtn = new AjaxButton("siguienteBtn", formulario) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          int nextStep = FormPreingresoDatosUsuario.this.getNextStep();
          if ((nextStep > 2 && FormPreingresoDatosUsuario.this
            .viewModel.getModelo()
            .isAceptacionPrerrequisitos()) || FormPreingresoDatosUsuario.this
            .viewModel.getStep() == 1) {
            if (FormPreingresoDatosUsuario.this.viewModel.getStep() == 5 && 
              FormPreingresoDatosUsuario.this.adjuntarArchivosUsuario.totalArchivosUsuarios() <= 0) {
              error("Pregunta obligatoria para poder continuar.");
              //target.add(new Component[] { FormPreingresoDatosUsuario.access$700(this.this$0) });
              return;
            } 
            if (FormPreingresoDatosUsuario.this.viewModel.getStep() == 8) {
              FormPreingresoDatosUsuario.this.listaArchivos.clear();
              FormPreingresoDatosUsuario.this.listaArchivos.addAll(Arrays.asList(FormPreingresoDatosUsuario.this.getUploadFolder().listFiles()));
              if (FormPreingresoDatosUsuario.this.listaArchivos.isEmpty()) {
                error("Pregunta obligatoria para poder continuar.");
                //target.add(new Component[] { FormPreingresoDatosUsuario.access$700(this.this$0) });
                return;
              } 
              FormPreingresoDatosUsuario.this.viewModel.setStep(nextStep);
            } else {
              FormPreingresoDatosUsuario.this.viewModel.setStep(nextStep);
            } 
          } 
          FormPreingresoDatosUsuario.this.registrarComportamiento(FormPreingresoDatosUsuario.UpdSeccOutofFormBehavior.class);
          target.add(new Component[] { (Component)formulario });
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)formulario });
        }
      };
    queue(new Component[] { (Component)siguienteBtn });
    AjaxSubmitLink regresarBtn = new AjaxSubmitLink("regresarBtn", formulario) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          int backStep = FormPreingresoDatosUsuario.this.getBackStep();
          FormPreingresoDatosUsuario.this.viewModel.setStep(backStep);
          FormPreingresoDatosUsuario.this.registrarComportamiento(FormPreingresoDatosUsuario.UpdSeccOutofFormBehavior.class);
          target.add(new Component[] { (Component)formulario });
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)formulario });
        }
      };
    queue(new Component[] { (Component)regresarBtn });
    AjaxSubmitLink submitBtn = new AjaxSubmitLink("submitBtn", formulario) {
        protected void onConfigure() {
          super.onConfigure();
          setVisible((FormPreingresoDatosUsuario.this.viewModel.getStep() >= 9));
        }
        
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          if (Objects.isNull(FormPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario()
              .getAdjuntos()))
            FormPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario()
              .setAdjuntos(new ArrayList()); 
          for (File file : FormPreingresoDatosUsuario.this.getUploadFolder().getFiles())
            FormPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario()
              .getAdjuntos().add(file.getAbsolutePath()); 
          if (AuthenticatedWebSession.get().getRoles().hasRole("UCA")) {
            FormPreingresoDatosUsuario.this.viewModel.guardarFormularioPreingresoDatos(true, true);
          } else {
            FormPreingresoDatosUsuario.this.viewModel.guardarFormularioPreingresoDatos(false, false);
          } 
          FormPreingresoDatosUsuario.this.getUploadFolder().remove();
          FormPreingresoDatosUsuario.this.guardado = true;
          FormPreingresoDatosUsuario.this.onError = false;
          FormPreingresoDatosUsuario.this.mensajeFeedback = "Hemos guardado con exito tu solicitud.";
          target.add(new Component[] { (Component)getPage() });
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)formulario });
        }
      };
    queue(new Component[] { (Component)submitBtn });
    submitBtn.setOutputMarkupPlaceholderTag(true);
    submitBtn.add(new Behavior[] { new UpdSeccOutofFormBehavior() });
    AjaxSubmitLink resetBtn = new AjaxSubmitLink("resetBtn", formulario) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          FormPreingresoDatosUsuario.this.viewModel.setStep(1);
          FormPreingresoDatosUsuario.this.viewModel.initModelo();
          FormPreingresoDatosUsuario.this.registrarComportamiento(FormPreingresoDatosUsuario.UpdSeccOutofFormBehavior.class);
          target.add(new Component[] { (Component)formulario });
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)formulario });
        }
      };
    queue(new Component[] { (Component)resetBtn });
    initControlSecciones();
  }
  
  private int getNextStep() {
    int nextStep = (this.viewModel.getStep() + 1 > 9) ? 9 : (this.viewModel.getStep() + 1);
    if ((nextStep == 7 || nextStep == 8) && 
      !isRolFideicomisoRepresentanteLegal())
      nextStep = 9; 
    return nextStep;
  }
  
  private int getBackStep() {
    int backStep = (this.viewModel.getStep() - 1 < 1) ? 1 : (this.viewModel.getStep() - 1);
    if ((backStep == 8 || backStep == 7) && 
      !isRolFideicomisoRepresentanteLegal())
      backStep = 6; 
    return backStep;
  }
  
  private void initControlSecciones() {
    WebMarkupContainer seccionUno = new WebMarkupContainer("seccionUno") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(1)));
        }
      };
    queue(new Component[] { (Component)seccionUno });
    WebMarkupContainer seccionDos = new WebMarkupContainer("seccionDos") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(2)));
        }
      };
    queue(new Component[] { (Component)seccionDos });
    WebMarkupContainer seccionTres = new WebMarkupContainer("seccionTres") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(3)));
        }
      };
    queue(new Component[] { (Component)seccionTres });
    WebMarkupContainer seccionCuatro = new WebMarkupContainer("seccionCuatro") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(4)));
        }
      };
    queue(new Component[] { (Component)seccionCuatro });
    WebMarkupContainer seccionCinco = new WebMarkupContainer("seccionCinco") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(5)));
        }
      };
    queue(new Component[] { (Component)seccionCinco });
    WebMarkupContainer seccionSeis = new WebMarkupContainer("seccionSeis") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(6)));
        }
      };
    queue(new Component[] { (Component)seccionSeis });
    WebMarkupContainer seccionSiete = new WebMarkupContainer("seccionSiete") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible((FormPreingresoDatosUsuario.this
              .getStep().equals(Integer.valueOf(7)) && FormPreingresoDatosUsuario.this
              .isRolFideicomisoRepresentanteLegal()));
        }
      };
    queue(new Component[] { (Component)seccionSiete });
    WebMarkupContainer seccionOcho = new WebMarkupContainer("seccionOcho") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible((FormPreingresoDatosUsuario.this
              .getStep().equals(Integer.valueOf(8)) && FormPreingresoDatosUsuario.this
              .isRolFideicomisoRepresentanteLegal()));
        }
      };
    queue(new Component[] { (Component)seccionOcho });
    seccionOcho.setOutputMarkupPlaceholderTag(true);
    seccionOcho.add(new Behavior[] { new UpdSeccOutofFormBehavior() });
    WebMarkupContainer seccionNueve = new WebMarkupContainer("seccionNueve") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(9)));
        }
      };
    seccionNueve.setOutputMarkupPlaceholderTag(true);
    seccionNueve.add(new Behavior[] { new UpdSeccOutofFormBehavior() });
    queue(new Component[] { (Component)seccionNueve });
  }
  
  private boolean isRolFideicomisoRepresentanteLegal() {
    if (Objects.isNull(this.viewModel.getModelo().getPreingresodatosusuario()) || 
      Objects.isNull(this.viewModel.getModelo().getPreingresodatosusuario().getRolFideicomiso()))
      return false; 
    return (this.viewModel.getModelo()
      .getPreingresodatosusuario()
      .getRole().equalsIgnoreCase("Entrega condiciones de manejo Fideicomisos") || this.viewModel
      .getModelo()
      .getPreingresodatosusuario()
      .getRole().equalsIgnoreCase("Entrega condiciones de manejo Alpha"));
  }
  
  private void seccionPrimera() {
    final TextField correoFormulario = new TextField("correoFormulario", LambdaModel.of(this.viewModel
          
          .getModelo()::getCorreoFormulario, this.viewModel
          
          .getModelo()::setCorreoFormulario));
    correoFormulario.setOutputMarkupPlaceholderTag(true);
    correoFormulario.setRequired(true);
    correoFormulario.add((IValidator)RfcCompliantEmailAddressValidator.getInstance());
    correoFormulario.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "") {
          
          } });
    correoFormulario
      .setLabel((IModel)Model.of("Email"));
    correoFormulario.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)correoFormulario });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)correoFormulario });
            }
          } });
    queue(new Component[] { (Component)correoFormulario });
  }
  
  private void initPrerrequisitos() {
    CheckBox aceptacionPrerrequisitos = new CheckBox("aceptacionPrerrequisitos", LambdaModel.of(this.viewModel
          
          .getModelo()::isAceptacionPrerrequisitos, this.viewModel
          
          .getModelo()::setAceptacionPrerrequisitos));
    queue(new Component[] { (Component)aceptacionPrerrequisitos });
  }
  
  private void condicionesManejo() {
    TextField condicionesUsuariosAutorizadores = new TextField("condicionesUsuariosAutorizadores", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::getCondicionesUsuariosAutorizadores, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::setCondicionesUsuariosAutorizadores));
    condicionesUsuariosAutorizadores.setOutputMarkupPlaceholderTag(true);
    condicionesUsuariosAutorizadores.setRequired(true);
    condicionesUsuariosAutorizadores.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "") });
    condicionesUsuariosAutorizadores
      .setLabel((IModel)Model.of("Condiciones de los usuarios autorizadores de las operaciones en la plataforma "));
    queue(new Component[] { (Component)condicionesUsuariosAutorizadores });
  }
  
  private void initCaracteristicasUsuario() {
    LoadableDetachableModel<List<String>> listaRoles = new LoadableDetachableModel<List<String>>() {
        protected List<String> load() {
          List<String> response = new ArrayList<>();
          response.add("Administrador de la plataforma");
          response.add("Operadores de la plataforma");
          response.add("Entrega condiciones de manejo Fideicomisos");
          response.add("Entrega condiciones de manejo Alpha");
          return response;
        }
      };
    DropDownChoice rolEnLaPlataforma = new DropDownChoice("rolEnLaPlataforma", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::getRole, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::setRole), (IModel)listaRoles);
    rolEnLaPlataforma.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
          } });
    queue(new Component[] { (Component)rolEnLaPlataforma });
    rolEnLaPlataforma.setOutputMarkupPlaceholderTag(true);
    rolEnLaPlataforma.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", " ", "Seleccione el rol del usuario en la plataforma. <br>") });
    rolEnLaPlataforma.setLabel((IModel)new Model("Rol en la plataforma"));
    rolEnLaPlataforma.setRequired(true);
    LoadableDetachableModel<List<String>> listaTiposRelacionNegocio = new LoadableDetachableModel<List<String>>() {
        protected List<String> load() {
          List<String> response = new ArrayList<>();
          response.add("Representante Legal Fideicomisos");
          response.add("Interventor Fideicomisos");
          response.add("Tesorero Fideicomisos");
          response.add("Secretario Fideicomisos");
          response.add("Revisor Fideicomisos");
          response.add("Representante Legal Fondos Alfa");
          response.add("Operador de sistema");
          response.add("No opera el sistema");
          return response;
        }
      };
    final TextField textoOtroRolFideicomiso = new TextField("textoOtroRolFideicomiso", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::getTextoOtroRolFideicomiso, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::setTextoOtroRolFideicomiso)) {
        protected void onConfigure() {
          super.onConfigure();
          if (Objects.nonNull(FormPreingresoDatosUsuario.this.viewModel
              .getModelo()
              .getPreingresodatosusuario()
              .getRolFideicomiso()) && FormPreingresoDatosUsuario.this
            .viewModel
            .getModelo()
            .getPreingresodatosusuario()
            .getRolFideicomiso().equalsIgnoreCase("otro")) {
            setEnabled(true);
          } else {
            if (Objects.nonNull(FormPreingresoDatosUsuario.this.viewModel
                .getModelo()
                .getPreingresodatosusuario()
                .getTextoOtroRolFideicomiso()))
              FormPreingresoDatosUsuario.this.viewModel
                .getModelo()
                .getPreingresodatosusuario()
                .setTextoOtroRolFideicomiso(null); 
            setEnabled(false);
          } 
          setVisible(false);
        }
      };
    textoOtroRolFideicomiso.setLabel((IModel)Model.of("otro"));
    FormGroupControlBehavior formGBhv = new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "Respuesta obligatoria", "", "Provee minformacisobre tu respuesta");
    formGBhv.setMostrarSignoRequerido(false);
    textoOtroRolFideicomiso.add(new Behavior[] { (Behavior)formGBhv });
    textoOtroRolFideicomiso.setLabel((IModel)Model.of(""));
    queue(new Component[] { (Component)textoOtroRolFideicomiso });
    textoOtroRolFideicomiso.setRequired(true);
    textoOtroRolFideicomiso.setOutputMarkupPlaceholderTag(true);
    final DropDownChoice tipoRelacionConNegocio = new DropDownChoice("tipoRelacionConNegocio", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::getRolFideicomiso, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::setRolFideicomiso), (IModel)listaTiposRelacionNegocio);
    queue(new Component[] { (Component)tipoRelacionConNegocio });
    tipoRelacionConNegocio.setOutputMarkupPlaceholderTag(true);
    tipoRelacionConNegocio.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", " ", "Seleccione el rol del usuario en la plataforma. <br>") });
    tipoRelacionConNegocio.setLabel((IModel)new Model("Tipo relacion con negocio"));
    tipoRelacionConNegocio.setRequired(true);
    tipoRelacionConNegocio.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)textoOtroRolFideicomiso, (Component)tipoRelacionConNegocio });
            }
          } });
  }
  
  private void iniDatosUsuario() {
    LoadableDetachableModel<List<TipoDocumentoPojo>> tiposDocumentos = new LoadableDetachableModel<List<TipoDocumentoPojo>>() {
        protected List<TipoDocumentoPojo> load() {
          TipoDocumentoVM dataObj = new TipoDocumentoVM((IGenericHttpClient)FormPreingresoDatosUsuario.this.gtwayHttp);
          return dataObj.listaTipoDocumentos();
        }
      };
    ChoiceRenderer rendererTipDoc = new ChoiceRenderer("descripciondocumento", "clasedocumento");
    final DropDownChoice<TipoDocumentoPojo> tipoDocumento = new DropDownChoice("tipoDocumento", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::getTipoDocumento, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::setTipoDocumento), (IModel)tiposDocumentos, (IChoiceRenderer)rendererTipDoc);
    tipoDocumento.setOutputMarkupPlaceholderTag(true);
    tipoDocumento.setRequired(true);
    tipoDocumento.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)tipoDocumento });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)tipoDocumento });
            }
          } });
    tipoDocumento.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Indicar el Tipo Documento del usuario") });
    tipoDocumento.setLabel((IModel)Model.of("Tipo Documento"));
    queue(new Component[] { (Component)tipoDocumento });
    final TextField idDocumento = new TextField("idDocumento", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::getNumeroDocumento, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::setNumeroDocumento));
    idDocumento.setOutputMarkupPlaceholderTag(true);
    idDocumento.setRequired(true);
    idDocumento.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)idDocumento });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)idDocumento });
            }
          } });
    idDocumento.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Indicar el Nde documento") });
    idDocumento.setLabel((IModel)Model.of("NDocumento"));
    idDocumento.add(new Behavior[] { (Behavior)MaskInputFormatAppender.general("[20]", null, "", false, false, true) });
    queue(new Component[] { (Component)idDocumento });
    final TextField celular = new TextField("celular", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()
          .getCelular()::getNumeroCelular, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()
          .getCelular()::setNumeroCelular));
    celular.add(new Behavior[] { (Behavior)MaskInputFormatAppender.general("[3,3,4]", "-", null, false, false, true) });
    celular.setOutputMarkupPlaceholderTag(true);
    celular.setRequired(true);
    celular.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)celular });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)celular });
            }
          } });
    celular.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", " ") });
    celular.setLabel((IModel)Model.of("Celular"));
    queue(new Component[] { (Component)celular });
    final TextField direccion = new TextField("direccion", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::getDireccion, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::setDireccion));
    direccion.setOutputMarkupPlaceholderTag(true);
    direccion.setRequired(true);
    direccion.add(new Behavior[] { new UpdateDireccionEstandarizada() });
    direccion.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)direccion });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)direccion });
            }
          } });
    direccion.add(new Behavior[] { (Behavior)new FieldDecorator() });
    direccion.setLabel((IModel)Model.of("Dirección"));
    direccion.add((IValidator)StringValidator.maximumLength(100));
    queue(new Component[] { (Component)direccion });
    queue(new Component[] { (Component)new AjaxLink("editarFormatoDane") {
            public void onClick(AjaxRequestTarget target) {
              DireccionDane dirDane = new DireccionDane(FormPreingresoDatosUsuario.this.getModalContentId(), (IModel)new Model((Serializable)FormPreingresoDatosUsuario.this.direccionEstandar)) {
                  public void cerrarCallback(AjaxRequestTarget target) {
                    showModal(false, target);
                    removeModal();
                  }
                  
                  public void aceptarCallback(AjaxRequestTarget target) {
                    FormPreingresoDatosUsuario.this.viewModel
                      .getModelo()
                      .getPreingresodatosusuario()
                      .getPersona().setDireccion(FormPreingresoDatosUsuario.this.direccionEstandar.getDireccionFinal());
                    showModal(false, target);
                    removeModal();
                    registrarComportamiento(FormPreingresoDatosUsuario.UpdateDireccionEstandarizada.class);
                  }
                };
              FormPreingresoDatosUsuario.this.addModal((Component)dirDane);
              FormPreingresoDatosUsuario.this.showModal(true, target);
            }
          } });
    final TextField primerNombre = new TextField("primerNombre", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::getPrimerNombre, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::setPrimerNombre));
    primerNombre.setOutputMarkupPlaceholderTag(true);
    primerNombre.setRequired(true);
    primerNombre.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)primerNombre });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)primerNombre });
            }
          } });
    primerNombre.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", " ") });
    primerNombre.setLabel((IModel)Model.of("Primer Nombre"));
    queue(new Component[] { (Component)primerNombre });
    primerNombre.add((IValidator)StringValidator.maximumLength(30));
    final TextField segundoNombre = new TextField("segundoNombre", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::getSegundoNombre, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::setSegundoNombre));
    segundoNombre.setOutputMarkupPlaceholderTag(true);
    segundoNombre.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", " ") });
    segundoNombre.setLabel((IModel)Model.of("Segundo Nombre"));
    segundoNombre.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)segundoNombre });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)segundoNombre });
            }
          } });
    segundoNombre.add((IValidator)StringValidator.maximumLength(30));
    queue(new Component[] { (Component)segundoNombre });
    final TextField primerApellido = new TextField("primerApellido", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::getPrimerApellido, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::setPrimerApellido));
    primerApellido.setOutputMarkupPlaceholderTag(true);
    primerApellido.setRequired(true);
    primerApellido.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)primerApellido });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)primerApellido });
            }
          } });
    primerApellido.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", " ") });
    primerApellido.setLabel((IModel)Model.of("Primer Apellido"));
    primerApellido.add((IValidator)StringValidator.maximumLength(30));
    queue(new Component[] { (Component)primerApellido });
    final TextField segundoApellido = new TextField("segundoApellido", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::getSegundoApellido, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::setSegundoApellido));
    segundoApellido.setOutputMarkupPlaceholderTag(true);
    segundoApellido.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)segundoApellido });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)segundoApellido });
            }
          } });
    segundoApellido.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", " ") });
    segundoApellido.setLabel((IModel)Model.of("Segundo Apellido"));
    segundoApellido.add((IValidator)StringValidator.maximumLength(30));
    queue(new Component[] { (Component)segundoApellido });
    final TextField correoElectronico = new TextField("correoElectronico", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::getCorreoElectronico, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()
          .getPersona()::setCorreoElectronico));
    correoElectronico.setOutputMarkupPlaceholderTag(true);
    correoElectronico.setRequired(true);
    correoElectronico.add((IValidator)RfcCompliantEmailAddressValidator.getInstance());
    correoElectronico.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)correoElectronico });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)correoElectronico });
            }
          } });
    correoElectronico.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", " ") });
    correoElectronico.setLabel((IModel)Model.of("Email"));
    correoElectronico.add((IValidator)StringValidator.maximumLength(60));
    queue(new Component[] { (Component)correoElectronico });
    this.adjuntarArchivosUsuario = new UploadFilesPanel("adjuntarArchivosUsuario", (IModel<String>)Model.of("frmausr"));
    queue(new Component[] { (Component)this.adjuntarArchivosUsuario });
  }
  
  private void iniDatosNegocio() {
    final TextField codigoNegocio = new TextField("codigoNegocio", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::getCodNegocioLink, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::setCodNegocioLink));
    codigoNegocio.setOutputMarkupPlaceholderTag(true);
    codigoNegocio.setRequired(true);
    codigoNegocio.setEnabled(false);
    codigoNegocio.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)codigoNegocio });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)codigoNegocio });
            }
          } });
    codigoNegocio.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Indicar el cdel Negocio (Enunciado en el asunto del email)") });
    codigoNegocio.setLabel((IModel)Model.of("CNegocio"));
    queue(new Component[] { (Component)codigoNegocio });
    final TextField nombreNegocio = new TextField("nombreNegocio", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::getNombreNegocioLink, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::setNombreNegocioLink));
    nombreNegocio.setOutputMarkupPlaceholderTag(true);
    nombreNegocio.setRequired(true);
    nombreNegocio.setEnabled(false);
    nombreNegocio.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Indicar el Nombre del Negocio (Enunciado en el asunto del email)") });
    nombreNegocio.setLabel((IModel)Model.of("Nombre Fideicomiso"));
    nombreNegocio.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)nombreNegocio });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)nombreNegocio });
            }
          } });
    queue(new Component[] { (Component)nombreNegocio });
  }
  
  private Integer getStep() {
    int value = this.viewModel.getStep();
    return Integer.valueOf(value);
  }
  
  private void initProgressBar() {
    WebMarkupContainer progressSection = new WebMarkupContainer("progressSection");
    progressSection.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)progressSection });
    progressSection.add(new Behavior[] { new UpdSeccOutofFormBehavior() });
    IModel<String> labelProgress = new IModel<String>() {
        public String getObject() {
          String labelProgres = FormPreingresoDatosUsuario.this.viewModel.getStep() + " de " + '\t';
          return labelProgres;
        }
      };
    Label progressPercentage = new Label("progressPercentage", labelProgress);
    queue(new Component[] { (Component)progressPercentage });
    WebMarkupContainer progressPercentageBar = new WebMarkupContainer("progressPercentageBar") {
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          int porcentaje = FormPreingresoDatosUsuario.this.viewModel.getStep() * 100 / 9;
          tag.put("style", "width: " + porcentaje + "%;");
        }
      };
    queue(new Component[] { (Component)progressPercentageBar });
  }
  
  private void initTipoSolicitud() {
    List<String> listTipoSolicitudes = new ArrayList<>();
    listTipoSolicitudes.add("Registro cliente");
    if (!AuthenticatedWebSession.get().getRoles().hasRole("UCA"))
      listTipoSolicitudes.add("Registro fiduciaria"); 
    final DropDownChoice tipoSolicitud = new DropDownChoice("tipoSolicitud", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::getTipoSolicitud, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::setTipoSolicitud), listTipoSolicitudes);
    tipoSolicitud.setRequired(true);
    tipoSolicitud.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Seleccione el tipo de solicitud. <br> Para continuar, es requerido responder a esta pregunta. ") });
    tipoSolicitud.setLabel((IModel)Model.of("Registro FIDUSAP WEB"));
    queue(new Component[] { (Component)tipoSolicitud });
    tipoSolicitud.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)tipoSolicitud });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)tipoSolicitud });
            }
          } });
  }
  
  private void initCondicionesManejo() {
    final TextField textoOtroCondicionesManejo = new TextField("textoOtroCondicionesManejo", LambdaModel.of(this.viewModel
          .getModelo()
          .getPreingresodatosusuario()::getTextoOtroCondicionesManejo, this.viewModel
          .getModelo()
          .getPreingresodatosusuario()::setTextoOtroCondicionesManejo)) {
        protected void onConfigure() {
          super.onConfigure();
          if (Objects.nonNull(FormPreingresoDatosUsuario.this.viewModel.getModelo()
              .getPreingresodatosusuario()
              .getCondicionesManejo()) && FormPreingresoDatosUsuario.this
            .viewModel.getModelo()
            .getPreingresodatosusuario()
            .getCondicionesManejo()
            .equalsIgnoreCase("Otro tipo de condicion")) {
            setEnabled(true);
          } else {
            FormPreingresoDatosUsuario.this.viewModel.getModelo()
              .getPreingresodatosusuario()
              .setTextoOtroCondicionesManejo(null);
            setEnabled(false);
          } 
        }
      };
    queue(new Component[] { (Component)textoOtroCondicionesManejo });
    textoOtroCondicionesManejo.setRequired(true);
    textoOtroCondicionesManejo.setOutputMarkupPlaceholderTag(true);
    FormGroupControlBehavior formGBhv = new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "Respuesta obligatoria", "", "Provee minformacisobre tu respuesta");
    formGBhv.setMostrarSignoRequerido(false);
    textoOtroCondicionesManejo.add(new Behavior[] { (Behavior)formGBhv });
    textoOtroCondicionesManejo.setLabel((IModel)Model.of(""));
    LoadableDetachableModel listaNumeroUsuarios = new LoadableDetachableModel() {
        protected List<String> load() {
          List<String> response = new ArrayList<>();
          for (int k = 1; k < 21; k++)
            response.add("" + k); 
          return response;
        }
      };
    DropDownChoice numeroDeUsuariosObligatorios = new DropDownChoice("numeroDeUsuariosObligatorios", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::getNumeroUsuariosCondiciones, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::setNumeroUsuariosCondiciones), (IModel)listaNumeroUsuarios);
    numeroDeUsuariosObligatorios.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Numero de usuarios requeridos para condiciones de negocio<br>Para Alfa, corresponde al nde aprobadores requeridos para las operaciones.<br>Para fideicomisos, son el mde autorizadores requeridos para aprobar pagos. ") });
    queue(new Component[] { (Component)numeroDeUsuariosObligatorios });
    numeroDeUsuariosObligatorios.setLabel((IModel)new Model("Numero de usuarios Obligatorios"));
    numeroDeUsuariosObligatorios.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
          } });
    LoadableDetachableModel listaCondicionesManejo = new LoadableDetachableModel() {
        protected List<String> load() {
          List<String> response = new ArrayList<>();
          response.add("Una firma obligatoria - *Una Firma obligatoria de las registradas - *No se requiere indicar que usuario es el obligatorio");
          response.add("Dos firmas autorizadas - *Dos Firmas obligatorias de las registradas - *No se requiere indicar que usuario es el obligatorio");
          response.add("Tres o mautorizadas - *Tres o m Firmas obligatorias de las registradas - *No se requiere indicar que usuario es el obligatorio");
          response.add("Con Interventor - *Firma del interventor siempre es obligatoria y Otros Autorizadores si se requieren - *Se requiere indicar usuario interventor");
          response.add("Otro tipo de condicion");
          return response;
        }
      };
    DropDownChoice condicionesManejo = new DropDownChoice("condicionesManejo", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::getCondicionesManejo, this.viewModel
          
          .getModelo()
          .getPreingresodatosusuario()::setCondicionesManejo), (IModel)listaCondicionesManejo);
    queue(new Component[] { (Component)condicionesManejo });
    condicionesManejo.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)textoOtroCondicionesManejo });
            }
          } });
    WebMarkupContainer condicionesManejoFideicomiso = new WebMarkupContainer("condicionesManejoFideicomiso") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormPreingresoDatosUsuario.this.viewModel.getModelo()
              .getPreingresodatosusuario()
              .getRole().equalsIgnoreCase("Entrega condiciones de manejo Fideicomisos"));
        }
      };
    queue(new Component[] { (Component)condicionesManejoFideicomiso });
  }
  
  private void initAdjuntos() {
    queue(new Component[] { (Component)this.dataview });
    queue(new Component[] { (Component)this.dataviewSelected });
    this.dataview.setOutputMarkupPlaceholderTag(true);
    this.dataviewSelected.setOutputMarkupPlaceholderTag(true);
    FileUploadForm simpleUploadForm = new FileUploadForm("simpleUpload");
    simpleUploadForm.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)simpleUploadForm });
    simpleUploadForm.setMaxSize(Bytes.megabytes(1500L));
    simpleUploadForm.setFileMaxSize(Bytes.megabytes(100L));
    FileListView fileListView = new FileListView("fileList", (IModel<List<File>>)new LoadableDetachableModel<List<File>>() {
          protected List<File> load() {
            FormPreingresoDatosUsuario.this.listaArchivos.clear();
            FormPreingresoDatosUsuario.this.listaArchivos.addAll(Arrays.asList(FormPreingresoDatosUsuario.this.getUploadFolder().listFiles()));
            return FormPreingresoDatosUsuario.this.listaArchivos;
          }
        });
    queue(new Component[] { (Component)fileListView });
    FileDescriptionListView fileDescListView = new FileDescriptionListView("fileDescList", (IModel<List<FileDescription>>)new LoadableDetachableModel<List<FileDescription>>() {
          protected List<FileDescription> load() {
            return FormPreingresoDatosUsuario.this.listadescArchivos;
          }
        });
    queue(new Component[] { (Component)fileDescListView });
  }
  
  private class FileUploadForm extends Form<Void> {
    public FileUploadForm(String id) {
      super(id);
      setOutputMarkupPlaceholderTag(true);
      setMultiPart(true);
      final FileUploadField fileInput = new FileUploadField("fileInput");
      add(new Component[] { (Component)fileInput });
      fileInput.add(new Behavior[] { (Behavior)new FilesSelectedBehavior() {
              protected void onSelected(AjaxRequestTarget target, List<FileDescription> fileDescriptions) {
                FormPreingresoDatosUsuario.this.listadescArchivos.clear();
                FormPreingresoDatosUsuario.this.listadescArchivos.addAll(fileDescriptions);
                Bytes bytes = Bytes.bytes(fileDescriptions.stream().mapToLong(FileDescription::getFileSize).sum());
                if (bytes.greaterThan(FormPreingresoDatosUsuario.FileUploadForm.this.getFileMaxSize())) {
                  FormPreingresoDatosUsuario.FileUploadForm.this.error(" Supera el valor mpermito para carga de un archivo  " + FormPreingresoDatosUsuario.FileUploadForm.this.getFileMaxSize());
                } else {
                  FormPreingresoDatosUsuario.FileUploadForm.this.info("Permite cargar archivos" + FormPreingresoDatosUsuario.FileUploadForm.this.getMaxSize());
                } 
               // target.add(new Component[] { (Component)FormPreingresoDatosUsuario.access$1700(this.this$1.this$0), FormPreingresoDatosUsuario.access$700(this.this$1.this$0) });
              }
            } });
      add(new Component[] { (Component)new AjaxButton("submitUpload") {
              protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                List<FileUpload> uploadfls = fileInput.getFileUploads();
                for (FileUpload upload : uploadfls) {
                  File newFile = new File((File)FormPreingresoDatosUsuario.this.getUploadFolder(), upload.getClientFileName());
                  FormPreingresoDatosUsuario.this.checkFileExists(newFile);
                  try {
                    newFile.createNewFile();
                    upload.writeTo(newFile);
                    FormPreingresoDatosUsuario.this.listadescArchivos.clear();
                    FormPreingresoDatosUsuario.this.listaArchivos.clear();
                    FormPreingresoDatosUsuario.this.listaArchivos.addAll(Arrays.asList(FormPreingresoDatosUsuario.this.getUploadFolder().listFiles()));
                  } catch (Exception e) {
                    throw new IllegalStateException("Unable to write file");
                  } 
                } 
               // target.add(new Component[] { (Component)FormPreingresoDatosUsuario.access$1700(this.this$1.this$0), (Component)FormPreingresoDatosUsuario.access$1900(this.this$1.this$0), (Component)this.val$fileInput, (Component)this.this$1 });
              }
            } });
    }
  }
  
  private void checkFileExists(File newFile) {
    if (newFile.exists())
      if (!Files.remove(newFile))
        throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());  
  }
  
  private Folder getUploadFolder() {
    Folder folder = new Folder(System.getProperty("java.io.tmpdir") + "/hsperfdata_psbswn/sesstmp/" + usuarioSesion() + "/preingreso");
    try {
      folder.ensureExists();
    } catch (IOException ex) {
      Logger.getLogger(FormPreingresoDatosUsuario.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    return folder;
  }
  
  private class FileDescriptionListView extends ListView<FileDescription> {
    public FileDescriptionListView(String name, IModel<List<FileDescription>> descripcion) {
      super(name, descripcion);
    }
    
    protected void populateItem(ListItem<FileDescription> listItem) {
      FileDescription fileDescrip = (FileDescription)listItem.getModelObject();
      Label fileDes = new Label("fileDesc", fileDescrip.getFileName());
      listItem.add(new Component[] { (Component)fileDes });
    }
  }
  
  private class FileListView extends ListView<File> {
    public FileListView(String name, IModel<List<File>> files) {
      super(name, files);
    }
    
    protected void populateItem(ListItem<File> listItem) {
      final File file = (File)listItem.getModelObject();
      listItem.setVisible(!file.getName().startsWith("frmausr_"));
      listItem.add(new Component[] { (Component)new Label("file", file.getName()) });
      listItem.add(new Component[] { (Component)new AjaxLink("delete") {
              private static final long serialVersionUID = 1L;
              
              protected void onConfigure() {
                super.onConfigure();
                setOutputMarkupPlaceholderTag(true);
              }
              
              public String getMarkupId() {
                return file.getName();
              }
              
              public void onClick(AjaxRequestTarget target) {
                Files.remove(file);
                info("Deleted " + file);
                FormPreingresoDatosUsuario.this.listaArchivos.clear();
                FormPreingresoDatosUsuario.this.listaArchivos.addAll(Arrays.asList(FormPreingresoDatosUsuario.this.getUploadFolder().listFiles()));
                //target.add(new Component[] { (Component)FormPreingresoDatosUsuario.access$1900(this.this$1.this$0) });
              }
            } });
    }
  }
}
