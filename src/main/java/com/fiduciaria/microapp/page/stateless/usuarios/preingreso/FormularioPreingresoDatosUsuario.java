package com.fiduciaria.microapp.page.stateless.usuarios.preingreso;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.componentes.MaskInputFormatAppender;
import com.fiduciaria.microapp.componentes.StatelessAjaxFormComponentUpdatingBehavior;
import com.fiduciaria.microapp.componentes.StatelessAjaxSubmitLink;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import com.fiduciaria.microapp.page.statefull.dane.DireccionDanePojo;
import com.fiduciaria.microapp.page.stateless.StatelessBasePanel;
import com.fiduciaria.microapp.page.stateless.dane.StatelessDireccionDane;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoPojo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.devutils.stateless.StatelessComponent;
import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileDescription;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.form.upload.FilesSelectedBehavior;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.StringValidator;

@StatelessComponent
public class FormularioPreingresoDatosUsuario extends StatelessBasePanel {
  private final FormularioPreingresoDatosUsuarioVM viewModel;
  
  private final Component feedback = (Component)new FeedbackPanel("feedback");
  
  private WebMarkupContainer dataview = new WebMarkupContainer("dataview");
  
  private WebMarkupContainer dataviewSelected = new WebMarkupContainer("dataviewSelected");
  
  private StatelessUploadFilesPanel adjuntarArchivosUsuario;
  
  private final List<File> listaArchivos;
  
  private final List<FileDescription> listadescArchivos;
  
  public FormularioPreingresoDatosUsuario(String id, IModel<FormularioPreingresoDatosUsuarioVM> model) {
    super(id, model);
    this.listaArchivos = new ArrayList<>();
    this.listadescArchivos = new ArrayList<>();
    this.viewModel = (FormularioPreingresoDatosUsuarioVM)model.getObject();
    if (Objects.isNull(this.viewModel.getDireccionEstandar()))
      this.viewModel.setDireccionEstandar(new DireccionDanePojo()); 
  }
  
  protected void onInitialize() {
    super.onInitialize();
    if (this.viewModel.getDireccionEstandar().isOpenModalDireccionDane()) {
      StatelessDireccionDane dirDane = abrirModalDireccionDane();
      addModal((Component)dirDane);
      showModal(true, null);
    } 
    visitChildren((component, visit) -> {
          if (!component.isStateless())
            System.out.println("Component " + component.getId() + " is not stateless"); 
        });
    queue(new Component[] { this.feedback });
    this.feedback.setOutputMarkupPlaceholderTag(true);
    ContextImage imgQuedateEncasa = new ContextImage("imgQuedateEncasa", "images/quedateencasa.png");
    queue(new Component[] { (Component)imgQuedateEncasa });
    final StatelessForm formulario = new StatelessForm("form");
    formulario.setDefaultModel((IModel)new CompoundPropertyModel(this.viewModel.getModelo()));
    formulario.setMultiPart(true);
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
    AjaxButton siguienteBtn = new AjaxButton("siguienteBtn", (Form)formulario) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          int nextStep = FormularioPreingresoDatosUsuario.this.getNextStep();
          if ((nextStep > 2 && FormularioPreingresoDatosUsuario.this.viewModel.getModelo().isAceptacionPrerrequisitos()) || FormularioPreingresoDatosUsuario.this.viewModel.getStep() == 1) {
            if (FormularioPreingresoDatosUsuario.this.viewModel.getStep() == 5 && FormularioPreingresoDatosUsuario.this.adjuntarArchivosUsuario.totalArchivosUsuarios() <= 0) {
              error("Pregunta obligatoria para poder continuar.");
              target.add(new Component[] { FormularioPreingresoDatosUsuario.this.adjuntarArchivosUsuario });
              return;
            } 
            if (FormularioPreingresoDatosUsuario.this.viewModel.getStep() == 8) {
              FormularioPreingresoDatosUsuario.this.listaArchivos.clear();
              FormularioPreingresoDatosUsuario.this.listaArchivos.addAll(Arrays.asList(FormularioPreingresoDatosUsuario.this.getUploadFolder().listFiles()));
              if (FormularioPreingresoDatosUsuario.this.listaArchivos.isEmpty()) {
                error("Pregunta obligatoria para poder continuar.");
                target.add(new Component[] { FormularioPreingresoDatosUsuario.this.adjuntarArchivosUsuario });
                return;
              } 
              FormularioPreingresoDatosUsuario.this.viewModel.setStep(nextStep);
              String entity = FormularioPreingresoDatosUsuario.this.serializarViewModel(FormularioPreingresoDatosUsuario.this.viewModel);
              FormularioPreingresoDatosUsuario.this.guardarModeloFormulario(FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getUuid(), entity);
              FormularioPreingresoDatosUsuario.this.updateParametrosPagina(getPage().getPageParameters(), Integer.toString(nextStep));
            } else {
              FormularioPreingresoDatosUsuario.this.viewModel.setStep(nextStep);
              String entity = FormularioPreingresoDatosUsuario.this.serializarViewModel(FormularioPreingresoDatosUsuario.this.viewModel);
              FormularioPreingresoDatosUsuario.this.guardarModeloFormulario(FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getUuid(), entity);
              FormularioPreingresoDatosUsuario.this.updateParametrosPagina(getPage().getPageParameters(), Integer.toString(nextStep));
            } 
          } 
          target.add(new Component[] { (Component)getPage() });
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)formulario });
        }
        
        protected boolean getStatelessHint() {
          return true;
        }
      };
    queue(new Component[] { (Component)siguienteBtn });
    StatelessAjaxSubmitLink statelessAjaxSubmitLink1 = new StatelessAjaxSubmitLink("regresarBtn", (Form)formulario) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          int backStep = FormularioPreingresoDatosUsuario.this.getBackStep();
          FormularioPreingresoDatosUsuario.this.viewModel.setStep(backStep);
          String entity = FormularioPreingresoDatosUsuario.this.serializarViewModel(FormularioPreingresoDatosUsuario.this.viewModel);
          FormularioPreingresoDatosUsuario.this.guardarModeloFormulario(FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getUuid(), entity);
          FormularioPreingresoDatosUsuario.this.updateParametrosPagina(getPage().getPageParameters(), Integer.toString(backStep));
          target.add(new Component[] { (Component)getPage() });
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)formulario });
        }
      };
    queue(new Component[] { (Component)statelessAjaxSubmitLink1 });
    StatelessAjaxSubmitLink statelessAjaxSubmitLink2 = new StatelessAjaxSubmitLink("submitBtn", (Form)formulario) {
        protected void onConfigure() {
          super.onConfigure();
          setVisible((FormularioPreingresoDatosUsuario.this.viewModel.getStep() >= 9));
        }
        
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          if (Objects.isNull(FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getAdjuntos()))
            FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().setAdjuntos(new ArrayList()); 
          for (File file : FormularioPreingresoDatosUsuario.this.getUploadFolder().getFiles())
            FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getAdjuntos().add(file.getAbsolutePath()); 
          String rta = FormularioPreingresoDatosUsuario.this.viewModel.guardarFormularioPreingresoDatos();
          if (!rta.toLowerCase().contains("error")) {
            String uuidToClose = FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getUuid();
            FormularioPreingresoDatosUsuario.this.viewModel.setStep(1);
            FormularioPreingresoDatosUsuario.this.viewModel.initModelo();
            String entity = FormularioPreingresoDatosUsuario.this.serializarViewModel(FormularioPreingresoDatosUsuario.this.viewModel);
            FormularioPreingresoDatosUsuario.this.cerrarModeloFormulario(uuidToClose, entity);
            FormularioPreingresoDatosUsuario.this.getUploadFolder().remove();
            target.add(new Component[] { (Component)getPage() });
            PageParameters paramRta = new PageParameters();
            paramRta.add("numerosolicitud", rta);
            paramRta.add("tipomensaje", "notificacion");
            setResponsePage(NotificacionEnvioSolicitud.class, paramRta);
          } else {
            String uuidToClose = FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getUuid();
            FormularioPreingresoDatosUsuario.this.viewModel.setStep(1);
            FormularioPreingresoDatosUsuario.this.viewModel.initModelo();
            String entity = FormularioPreingresoDatosUsuario.this.serializarViewModel(FormularioPreingresoDatosUsuario.this.viewModel);
            FormularioPreingresoDatosUsuario.this.cerrarModeloFormulario(uuidToClose, entity);
            FormularioPreingresoDatosUsuario.this.getUploadFolder().remove();
            target.add(new Component[] { (Component)getPage() });
            PageParameters paramRta = new PageParameters();
            paramRta.add("numerosolicitud", rta);
            paramRta.add("tipomensaje", "error");
            setResponsePage(NotificacionEnvioSolicitud.class, paramRta);
          } 
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)formulario });
        }
      };
    queue(new Component[] { (Component)statelessAjaxSubmitLink2 });
    StatelessAjaxSubmitLink statelessAjaxSubmitLink3 = new StatelessAjaxSubmitLink("resetBtn", (Form)formulario) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          FormularioPreingresoDatosUsuario.this.viewModel.setStep(1);
          FormularioPreingresoDatosUsuario.this.viewModel.initModelo();
          FormularioPreingresoDatosUsuario.this.updateParametrosPagina(getPage().getPageParameters(), Integer.toString(1));
          target.add(new Component[] { (Component)getPage() });
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)formulario });
        }
      };
    queue(new Component[] { (Component)statelessAjaxSubmitLink3 });
    initControlSecciones();
  }
  
  private void guardarModeloFormulario(String uuid, String modelo) {
    FormularioPreingresoDatosUsuarioVM response = new FormularioPreingresoDatosUsuarioVM();
    Map<String, String> headers = new HashMap<>();
    headers.put("uuid", uuid);
    headers.put("conftoken", AppConfParamConstant.getConftoken());
    String rtaCliente = this.viewModel.getGtwayHttp().postRESTgService(AppConfParamConstant.getUrltorestserver(), "services/payment/rest/autogestion/preingreso/guardar", modelo, headers);
  }
  
  private void cerrarModeloFormulario(String uuid, String modelo) {
    FormularioPreingresoDatosUsuarioVM response = new FormularioPreingresoDatosUsuarioVM();
    Map<String, String> headers = new HashMap<>();
    headers.put("uuid", uuid);
    headers.put("conftoken", AppConfParamConstant.getConftoken());
    String rtaCliente = this.viewModel.getGtwayHttp().postRESTgService(AppConfParamConstant.getUrltorestserver(), "services/payment/rest/autogestion/preingreso/cerrar", modelo, headers);
  }
  
  private int getNextStep() {
    int nextStep = (this.viewModel.getStep() + 1 > 9) ? 9 : (this.viewModel.getStep() + 1);
    if ((nextStep == 7 || nextStep == 8) && !isRolFideicomisoRepresentanteLegal())
      nextStep = 9; 
    return nextStep;
  }
  
  private int getBackStep() {
    int backStep = (this.viewModel.getStep() - 1 < 1) ? 1 : (this.viewModel.getStep() - 1);
    if ((backStep == 8 || backStep == 7) && !isRolFideicomisoRepresentanteLegal())
      backStep = 6; 
    return backStep;
  }
  
  private void initControlSecciones() {
    WebMarkupContainer seccionUno = new WebMarkupContainer("seccionUno") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormularioPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(1)));
        }
      };
    queue(new Component[] { (Component)seccionUno });
    WebMarkupContainer seccionDos = new WebMarkupContainer("seccionDos") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormularioPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(2)));
        }
      };
    queue(new Component[] { (Component)seccionDos });
    WebMarkupContainer seccionTres = new WebMarkupContainer("seccionTres") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormularioPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(3)));
        }
      };
    queue(new Component[] { (Component)seccionTres });
    WebMarkupContainer seccionCuatro = new WebMarkupContainer("seccionCuatro") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormularioPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(4)));
        }
      };
    queue(new Component[] { (Component)seccionCuatro });
    WebMarkupContainer seccionCinco = new WebMarkupContainer("seccionCinco") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormularioPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(5)));
        }
      };
    queue(new Component[] { (Component)seccionCinco });
    WebMarkupContainer seccionSeis = new WebMarkupContainer("seccionSeis") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormularioPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(6)));
        }
      };
    queue(new Component[] { (Component)seccionSeis });
    WebMarkupContainer seccionSiete = new WebMarkupContainer("seccionSiete") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible((FormularioPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(7)) && FormularioPreingresoDatosUsuario.this.isRolFideicomisoRepresentanteLegal()));
        }
      };
    queue(new Component[] { (Component)seccionSiete });
    WebMarkupContainer seccionOcho = new WebMarkupContainer("seccionOcho") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible((FormularioPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(8)) && FormularioPreingresoDatosUsuario.this.isRolFideicomisoRepresentanteLegal()));
        }
      };
    queue(new Component[] { (Component)seccionOcho });
    WebMarkupContainer seccionNueve = new WebMarkupContainer("seccionNueve") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormularioPreingresoDatosUsuario.this.getStep().equals(Integer.valueOf(9)));
        }
      };
    queue(new Component[] { (Component)seccionNueve });
  }
  
  private boolean isRolFideicomisoRepresentanteLegal() {
    if (Objects.isNull(this.viewModel.getModelo().getPreingresodatosusuario()) || Objects.isNull(this.viewModel.getModelo().getPreingresodatosusuario().getRolFideicomiso()))
      return false; 
    return (this.viewModel.getModelo().getPreingresodatosusuario().getRole().equalsIgnoreCase("Entrega condiciones de manejo Fideicomisos") || this.viewModel.getModelo().getPreingresodatosusuario().getRole().equalsIgnoreCase("Entrega condiciones de manejo Alpha"));
  }
  
  private void seccionPrimera() {
    final TextField correoFormulario = new TextField("correoFormulario", LambdaModel.of(this.viewModel.getModelo()::getCorreoFormulario, this.viewModel.getModelo()::setCorreoFormulario));
    correoFormulario.setOutputMarkupPlaceholderTag(true);
    correoFormulario.setRequired(true);
    correoFormulario.add((IValidator)RfcCompliantEmailAddressValidator.getInstance());
    correoFormulario.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "") {
            public boolean getStatelessHint(Component component) {
              return true;
            }
          } });
    correoFormulario.setLabel((IModel)Model.of("Email"));
    correoFormulario.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)correoFormulario });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)correoFormulario });
            }
            
            public boolean getStatelessHint(Component component) {
              return true;
            }
          } });
    queue(new Component[] { (Component)correoFormulario });
    correoFormulario.add((IValidator)StringValidator.maximumLength(60));
  }
  
  private void initPrerrequisitos() {
    CheckBox aceptacionPrerrequisitos = new CheckBox("aceptacionPrerrequisitos", LambdaModel.of(this.viewModel.getModelo()::isAceptacionPrerrequisitos, this.viewModel.getModelo()::setAceptacionPrerrequisitos));
    queue(new Component[] { (Component)aceptacionPrerrequisitos });
  }
  
  private void condicionesManejo() {
    TextField condicionesUsuariosAutorizadores = new TextField("condicionesUsuariosAutorizadores", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario()::getCondicionesUsuariosAutorizadores, this.viewModel.getModelo().getPreingresodatosusuario()::setCondicionesUsuariosAutorizadores));
    condicionesUsuariosAutorizadores.setOutputMarkupPlaceholderTag(true);
    condicionesUsuariosAutorizadores.setRequired(true);
    condicionesUsuariosAutorizadores.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "") });
    condicionesUsuariosAutorizadores.setLabel((IModel)Model.of("Condiciones de los usuarios autorizadores de las operaciones en la plataforma "));
    queue(new Component[] { (Component)condicionesUsuariosAutorizadores });
    condicionesUsuariosAutorizadores.add((IValidator)StringValidator.maximumLength(100));
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
    final DropDownChoice rolEnLaPlataforma = new DropDownChoice("rolEnLaPlataforma", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario()::getRole, this.viewModel.getModelo().getPreingresodatosusuario()::setRole), (IModel)listaRoles);
    rolEnLaPlataforma.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)rolEnLaPlataforma });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)rolEnLaPlataforma });
            }
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
    final TextField textoOtroRolFideicomiso = new TextField("textoOtroRolFideicomiso", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario()::getTextoOtroRolFideicomiso, this.viewModel.getModelo().getPreingresodatosusuario()::setTextoOtroRolFideicomiso)) {
        protected void onConfigure() {
          super.onConfigure();
          if (Objects.nonNull(FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getRolFideicomiso()) && FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getRolFideicomiso().equalsIgnoreCase("otro")) {
            setEnabled(true);
          } else {
            if (Objects.nonNull(FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getTextoOtroRolFideicomiso()))
              FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().setTextoOtroRolFideicomiso(null); 
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
    textoOtroRolFideicomiso.add((IValidator)StringValidator.maximumLength(1000));
    final DropDownChoice tipoRelacionConNegocio = new DropDownChoice("tipoRelacionConNegocio", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario()::getRolFideicomiso, this.viewModel.getModelo().getPreingresodatosusuario()::setRolFideicomiso), (IModel)listaTiposRelacionNegocio);
    queue(new Component[] { (Component)tipoRelacionConNegocio });
    tipoRelacionConNegocio.setOutputMarkupPlaceholderTag(true);
    tipoRelacionConNegocio.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", " ", "Seleccione el rol del usuario en la plataforma. <br>") });
    tipoRelacionConNegocio.setLabel((IModel)new Model("Tipo relacion con negocio"));
    tipoRelacionConNegocio.setRequired(true);
    tipoRelacionConNegocio.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)textoOtroRolFideicomiso, (Component)tipoRelacionConNegocio });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)textoOtroRolFideicomiso, (Component)tipoRelacionConNegocio });
            }
          } });
  }
  
  private void iniDatosUsuario() {
    LoadableDetachableModel<List<TipoDocumentoPojo>> tiposDocumentos = new LoadableDetachableModel<List<TipoDocumentoPojo>>() {
        protected List<TipoDocumentoPojo> load() {
          return FormularioPreingresoDatosUsuario.this.viewModel.getListaTipoDocumentos();
        }
      };
    ChoiceRenderer renderer = new ChoiceRenderer("descripciondocumento", "clasedocumento");
    final DropDownChoice<TipoDocumentoPojo> tipoDocumento = new DropDownChoice("tipoDocumento", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::getTipoDocumento, this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::setTipoDocumento), (IModel)tiposDocumentos, (IChoiceRenderer)renderer);
    tipoDocumento.setOutputMarkupPlaceholderTag(true);
    tipoDocumento.setRequired(true);
    tipoDocumento.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
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
    final TextField idDocumento = new TextField("idDocumento", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::getNumeroDocumento, this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::setNumeroDocumento));
    idDocumento.setOutputMarkupPlaceholderTag(true);
    idDocumento.setRequired(true);
    idDocumento.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
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
    queue(new Component[] { (Component)idDocumento });
    idDocumento.add(new Behavior[] { (Behavior)MaskInputFormatAppender.general("[20]", null, "", false, false, true) });
    final TextField celular = new TextField("celular", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario().getPersona().getCelular()::getNumeroCelular, this.viewModel.getModelo().getPreingresodatosusuario().getPersona().getCelular()::setNumeroCelular));
    celular.add(new Behavior[] { (Behavior)MaskInputFormatAppender.general("[2,1,3,3,4]", "-", null, false, false, true) });
    celular.setOutputMarkupPlaceholderTag(true);
    celular.setRequired(true);
    celular.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
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
    final TextField direccion = new TextField("direccion", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::getDireccion, this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::setDireccion));
    direccion.setOutputMarkupPlaceholderTag(true);
    direccion.setRequired(true);
    direccion.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)direccion });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)direccion });
            }
          } });
    direccion.setLabel((IModel)Model.of("Dirección"));
    queue(new Component[] { (Component)direccion });
    direccion.add((IValidator)StringValidator.maximumLength(100));
    queue(new Component[] { (Component)new AjaxLink("editarFormatoDane") {
            public void onClick(AjaxRequestTarget target) {
              if (!FormularioPreingresoDatosUsuario.this.viewModel.getDireccionEstandar().isOpenModalDireccionDane()) {
                FormularioPreingresoDatosUsuario.this.viewModel.getDireccionEstandar().setOpenModalDireccionDane(true);
                StatelessDireccionDane dirDane = FormularioPreingresoDatosUsuario.this.abrirModalDireccionDane();
                FormularioPreingresoDatosUsuario.this.acualizarModeloEnDb();
                FormularioPreingresoDatosUsuario.this.addModal((Component)dirDane);
                FormularioPreingresoDatosUsuario.this.showModal(true, null);
                target.add(new Component[] { (Component)getPage() });
              } 
            }
            
            protected boolean getStatelessHint() {
              return true;
            }
          } });
    final TextField primerNombre = new TextField("primerNombre", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::getPrimerNombre, this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::setPrimerNombre));
    primerNombre.setOutputMarkupPlaceholderTag(true);
    primerNombre.setRequired(true);
    primerNombre.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
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
    final TextField segundoNombre = new TextField("segundoNombre", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::getSegundoNombre, this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::setSegundoNombre));
    segundoNombre.setOutputMarkupPlaceholderTag(true);
    segundoNombre.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", " ") });
    segundoNombre.setLabel((IModel)Model.of("Segundo Nombre"));
    segundoNombre.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)segundoNombre });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)segundoNombre });
            }
          } });
    queue(new Component[] { (Component)segundoNombre });
    segundoNombre.add((IValidator)StringValidator.maximumLength(30));
    final TextField primerApellido = new TextField("primerApellido", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::getPrimerApellido, this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::setPrimerApellido));
    primerApellido.setOutputMarkupPlaceholderTag(true);
    primerApellido.setRequired(true);
    primerApellido.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
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
    queue(new Component[] { (Component)primerApellido });
    primerApellido.add((IValidator)StringValidator.maximumLength(30));
    final TextField segundoApellido = new TextField("segundoApellido", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::getSegundoApellido, this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::setSegundoApellido));
    segundoApellido.setOutputMarkupPlaceholderTag(true);
    segundoApellido.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
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
    queue(new Component[] { (Component)segundoApellido });
    segundoApellido.add((IValidator)StringValidator.maximumLength(30));
    final TextField correoElectronico = new TextField("correoElectronico", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::getCorreoElectronico, this.viewModel.getModelo().getPreingresodatosusuario().getPersona()::setCorreoElectronico));
    correoElectronico.setOutputMarkupPlaceholderTag(true);
    correoElectronico.setRequired(true);
    correoElectronico.add((IValidator)RfcCompliantEmailAddressValidator.getInstance());
    correoElectronico.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
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
    queue(new Component[] { (Component)correoElectronico });
    correoElectronico.add((IValidator)StringValidator.maximumLength(60));
    this.adjuntarArchivosUsuario = new StatelessUploadFilesPanel("adjuntarArchivosUsuario", (IModel<String>)Model.of("frmausr"), this.viewModel);
    queue(new Component[] { (Component)this.adjuntarArchivosUsuario });
  }
  
  private void iniDatosNegocio() {
    final TextField codigoNegocio = new TextField("codigoNegocio", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario()::getCodNegocioLink, this.viewModel.getModelo().getPreingresodatosusuario()::setCodNegocioLink));
    codigoNegocio.setOutputMarkupPlaceholderTag(true);
    codigoNegocio.setRequired(true);
    codigoNegocio.setEnabled(false);
    codigoNegocio.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
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
    codigoNegocio.add((IValidator)StringValidator.maximumLength(15));
    final TextField nombreNegocio = new TextField("nombreNegocio", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario()::getNombreNegocioLink, this.viewModel.getModelo().getPreingresodatosusuario()::setNombreNegocioLink));
    nombreNegocio.setOutputMarkupPlaceholderTag(true);
    nombreNegocio.setRequired(true);
    nombreNegocio.setEnabled(false);
    nombreNegocio.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Indicar el Nombre del Negocio (Enunciado en el asunto del email)") });
    nombreNegocio.setLabel((IModel)Model.of("Nombre Fideicomiso"));
    nombreNegocio.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)nombreNegocio });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)nombreNegocio });
            }
          } });
    queue(new Component[] { (Component)nombreNegocio });
    nombreNegocio.add((IValidator)StringValidator.maximumLength(100));
  }
  
  private Integer getStep() {
    StringValue value = getPage().getPageParameters().get("step");
    if (value.isNull() || value.isEmpty())
      return Integer.valueOf(1); 
    return Integer.valueOf(getPage().getPageParameters().get("step").toInt(1));
  }
  
  private StatelessDireccionDane abrirModalDireccionDane() {
    StatelessDireccionDane dirDane = new StatelessDireccionDane(getModalContentId(), (IModel)new Model((Serializable)this.viewModel.getDireccionEstandar())) {
        public void cerrarCallback(AjaxRequestTarget target) {
          showModal(false, null);
          removeModal();
          FormularioPreingresoDatosUsuario.this.viewModel.getDireccionEstandar().setOpenModalDireccionDane(false);
          FormularioPreingresoDatosUsuario.this.acualizarModeloEnDb();
          target.add(new Component[] { (Component)getPage() });
        }
        
        public void aceptarCallback(AjaxRequestTarget target) {
          showModal(false, target);
          removeModal();
          FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getPersona().setDireccion(FormularioPreingresoDatosUsuario.this.viewModel.getDireccionEstandar().getDireccionFinal());
          FormularioPreingresoDatosUsuario.this.viewModel.getDireccionEstandar().setOpenModalDireccionDane(false);
          FormularioPreingresoDatosUsuario.this.acualizarModeloEnDb();
          target.add(new Component[] { (Component)getPage() });
        }
        
        public void hookSyncModel(AjaxRequestTarget target) {
          FormularioPreingresoDatosUsuario.this.acualizarModeloEnDb();
          target.add(new Component[] { (Component)getPage() });
        }
      };
    return dirDane;
  }
  
  private void acualizarModeloEnDb() {
    String entity = serializarViewModel(this.viewModel);
    guardarModeloFormulario(this.viewModel.getModelo().getPreingresodatosusuario().getUuid(), entity);
    updateParametrosPagina(getPage().getPageParameters(), "" + this.viewModel.getStep());
  }
  
  private void updateParametrosPagina(PageParameters parametros, String step) {
    parametros.set("step", step);
  }
  
  private String serializarViewModel(FormularioPreingresoDatosUsuarioVM viewModel) {
    String response = "";
    ObjectMapper mapper = new ObjectMapper();
    mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    try {
      response = mapper.writeValueAsString(viewModel);
    } catch (IOException ex) {
      Logger.getLogger(FormularioPreingresoDatosUsuario.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    return response;
  }
  
  private void initProgressBar() {
    final int porcentaje = this.viewModel.getStep() * 100 / 9;
    String labelProgres = this.viewModel.getStep() + " de " + '\t';
    Label progressPercentage = new Label("progressPercentage", (IModel)Model.of(labelProgres));
    queue(new Component[] { (Component)progressPercentage });
    WebMarkupContainer progressPercentageBar = new WebMarkupContainer("progressPercentageBar") {
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          tag.put("style", "width: " + porcentaje + "%;");
        }
      };
    queue(new Component[] { (Component)progressPercentageBar });
  }
  
  private void initTipoSolicitud() {
    List<String> listTipoSolicitudes = new ArrayList<>();
    listTipoSolicitudes.add("Registro cliente");
    listTipoSolicitudes.add("Registro fiduciaria");
    if (this.viewModel.isUsuarioUCA())
      this.viewModel.getModelo().getPreingresodatosusuario().setTipoSolicitud("Registro cliente"); 
    final DropDownChoice tipoSolicitud = new DropDownChoice("tipoSolicitud", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario()::getTipoSolicitud, this.viewModel.getModelo().getPreingresodatosusuario()::setTipoSolicitud), listTipoSolicitudes);
    tipoSolicitud.setRequired(true);
    tipoSolicitud.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Seleccione el tipo de solicitud. <br> Para continuar, es requerido responder a esta pregunta. ") });
    tipoSolicitud.setLabel((IModel)Model.of("Registro FIDUSAP WEB"));
    tipoSolicitud.setEnabled(!this.viewModel.isUsuarioUCA());
    queue(new Component[] { (Component)tipoSolicitud });
    tipoSolicitud.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
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
    final TextField textoOtroCondicionesManejo = new TextField("textoOtroCondicionesManejo", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario()::getTextoOtroCondicionesManejo, this.viewModel.getModelo().getPreingresodatosusuario()::setTextoOtroCondicionesManejo)) {
        protected void onConfigure() {
          super.onConfigure();
          if (Objects.nonNull(FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getCondicionesManejo()) && FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getCondicionesManejo().equalsIgnoreCase("Otro tipo de condicion")) {
            setEnabled(true);
            setRequired(true);
          } else {
            FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().setTextoOtroCondicionesManejo(null);
            setRequired(false);
            setEnabled(false);
          } 
        }
      };
    queue(new Component[] { (Component)textoOtroCondicionesManejo });
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
    final DropDownChoice numeroDeUsuariosObligatorios = new DropDownChoice("numeroDeUsuariosObligatorios", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario()::getNumeroUsuariosCondiciones, this.viewModel.getModelo().getPreingresodatosusuario()::setNumeroUsuariosCondiciones), (IModel)listaNumeroUsuarios);
    numeroDeUsuariosObligatorios.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Numero de usuarios requeridos para condiciones de negocio<br>Para Alfa, corresponde al nde aprobadores requeridos para las operaciones.<br>Para fideicomisos, son el mde autorizadores requeridos para aprobar pagos. ") });
    queue(new Component[] { (Component)numeroDeUsuariosObligatorios });
    numeroDeUsuariosObligatorios.setLabel((IModel)new Model("Numero de usuarios Obligatorios"));
    numeroDeUsuariosObligatorios.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              String entity = FormularioPreingresoDatosUsuario.this.serializarViewModel(FormularioPreingresoDatosUsuario.this.viewModel);
              FormularioPreingresoDatosUsuario.this.guardarModeloFormulario(FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getUuid(), entity);
              FormularioPreingresoDatosUsuario.this.updateParametrosPagina(FormularioPreingresoDatosUsuario.this.getPage().getPageParameters(), "" + FormularioPreingresoDatosUsuario.this.viewModel.getStep());
              target.add(new Component[] { (Component)getPage() });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)numeroDeUsuariosObligatorios });
            }
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
    final DropDownChoice condicionesManejo = new DropDownChoice("condicionesManejo", LambdaModel.of(this.viewModel.getModelo().getPreingresodatosusuario()::getCondicionesManejo, this.viewModel.getModelo().getPreingresodatosusuario()::setCondicionesManejo), (IModel)listaCondicionesManejo);
    queue(new Component[] { (Component)condicionesManejo });
    condicionesManejo.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              if (Objects.nonNull(FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getCondicionesManejo()) && FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getCondicionesManejo().equalsIgnoreCase("Otro tipo de condicion")) {
                textoOtroCondicionesManejo.setEnabled(true);
              } else {
                textoOtroCondicionesManejo.setEnabled(false);
              } 
              String entity = FormularioPreingresoDatosUsuario.this.serializarViewModel(FormularioPreingresoDatosUsuario.this.viewModel);
              FormularioPreingresoDatosUsuario.this.guardarModeloFormulario(FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getUuid(), entity);
              FormularioPreingresoDatosUsuario.this.updateParametrosPagina(FormularioPreingresoDatosUsuario.this.getPage().getPageParameters(), "" + FormularioPreingresoDatosUsuario.this.viewModel.getStep());
              target.add(new Component[] { (Component)getPage() });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)textoOtroCondicionesManejo, (Component)condicionesManejo });
            }
          } });
    WebMarkupContainer condicionesManejoFideicomiso = new WebMarkupContainer("condicionesManejoFideicomiso") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormularioPreingresoDatosUsuario.this.viewModel.getModelo().getPreingresodatosusuario().getRole().equalsIgnoreCase("Entrega condiciones de manejo Fideicomisos"));
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
    queue(new Component[] { (Component)simpleUploadForm });
    simpleUploadForm.setMaxSize(Bytes.megabytes(1500L));
    simpleUploadForm.setFileMaxSize(Bytes.megabytes(100L));
    FileListView fileListView = new FileListView("fileList", (IModel<List<File>>)new LoadableDetachableModel<List<File>>() {
          protected List<File> load() {
            FormularioPreingresoDatosUsuario.this.listaArchivos.clear();
            FormularioPreingresoDatosUsuario.this.listaArchivos.addAll(Arrays.asList(FormularioPreingresoDatosUsuario.this.getUploadFolder().listFiles()));
            return FormularioPreingresoDatosUsuario.this.listaArchivos;
          }
        });
    queue(new Component[] { (Component)fileListView });
    FileDescriptionListView fileDescListView = new FileDescriptionListView("fileDescList", (IModel<List<FileDescription>>)new LoadableDetachableModel<List<FileDescription>>() {
          protected List<FileDescription> load() {
            return FormularioPreingresoDatosUsuario.this.listadescArchivos;
          }
        });
    queue(new Component[] { (Component)fileDescListView });
  }
  
  private class FileUploadForm extends StatelessForm<Void> {
    public FileUploadForm(String id) {
      super(id);
      setOutputMarkupPlaceholderTag(true);
      setMultiPart(true);
      final FileUploadField fileInput = new FileUploadField("fileInput");
      add(new Component[] { (Component)fileInput });
      fileInput.add(new Behavior[] { (Behavior)new FilesSelectedBehavior() {
              protected void onSelected(AjaxRequestTarget target, List<FileDescription> fileDescriptions) {
                FormularioPreingresoDatosUsuario.this.listadescArchivos.clear();
                FormularioPreingresoDatosUsuario.this.listadescArchivos.addAll(fileDescriptions);
                Bytes bytes = Bytes.bytes(fileDescriptions.stream().mapToLong(FileDescription::getFileSize).sum());
                if (bytes.greaterThan(FormularioPreingresoDatosUsuario.FileUploadForm.this.getFileMaxSize())) {
                  FormularioPreingresoDatosUsuario.FileUploadForm.this.error(" Supera el valor mpermito para carga de un archivo  " + FormularioPreingresoDatosUsuario.FileUploadForm.this.getFileMaxSize());
                } else {
                  FormularioPreingresoDatosUsuario.FileUploadForm.this.info("Permite cargar archivos" + FormularioPreingresoDatosUsuario.FileUploadForm.this.getMaxSize());
                } 
                target.add(new Component[] { (Component)FormularioPreingresoDatosUsuario.this.adjuntarArchivosUsuario, FormularioPreingresoDatosUsuario.this.adjuntarArchivosUsuario });
              }
              
              public boolean getStatelessHint(Component component) {
                return true;
              }
            } });
      add(new Component[] { (Component)new AjaxButton("submitUpload") {
              protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                List<FileUpload> uploadfls = fileInput.getFileUploads();
                for (FileUpload upload : uploadfls) {
                  File newFile = new File((File)FormularioPreingresoDatosUsuario.this.getUploadFolder(), "frmcneg_" + upload.getClientFileName());
                  FormularioPreingresoDatosUsuario.this.checkFileExists(newFile);
                  try {
                    newFile.createNewFile();
                    upload.writeTo(newFile);
                    FormularioPreingresoDatosUsuario.this.listadescArchivos.clear();
                    FormularioPreingresoDatosUsuario.this.listaArchivos.clear();
                    FormularioPreingresoDatosUsuario.this.listaArchivos.addAll(Arrays.asList(FormularioPreingresoDatosUsuario.this.getUploadFolder().listFiles()));
                  } catch (Exception e) {
                    throw new IllegalStateException("No se pudo guardar el archivo");
                  } 
                } 
                //target.add(new Component[] { (Component)FormularioPreingresoDatosUsuario.access$1600(this.this$1.this$0), (Component)FormularioPreingresoDatosUsuario.access$1800(this.this$1.this$0), (Component)this.this$1 });
              }
              
              protected boolean getStatelessHint() {
                return true;
              }
            } });
    }
  }
  
  private void checkFileExists(File newFile) {
    if (newFile.exists())
      if (!Files.remove(newFile))
        throw new IllegalStateException("No pudo escribir " + newFile.getAbsolutePath());  
  }
  
  private Folder getUploadFolder() {
    Folder folder = new Folder(System.getProperty("java.io.tmpdir") + "/hsperfdata_psbswn/sesstmp/" + this.viewModel.getModelo().getPreingresodatosusuario().getUuid());
    try {
      folder.ensureExists();
    } catch (IOException ex) {
      Logger.getLogger(FormularioPreingresoDatosUsuario.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    return folder;
  }
  
  private class FileDescriptionListView extends ListView<FileDescription> {
    public FileDescriptionListView(String name, IModel<List<FileDescription>> descripcion) {
      super(name, descripcion);
    }
    
    protected void populateItem(ListItem<FileDescription> listItem) {
      FileDescription fileDescrip = (FileDescription)listItem.getModelObject();
      listItem.add(new Component[] { (Component)new Label("fileDesc", fileDescrip.getFileName()) });
    }
  }
  
  private class FileListView extends ListView<File> {
    public FileListView(String name, IModel<List<File>> files) {
      super(name, files);
    }
    
    protected void populateItem(ListItem<File> listItem) {
      final File file = (File)listItem.getModelObject();
      listItem.setVisible(file.getName().startsWith("frmcneg_"));
      listItem.add(new Component[] { (Component)new Label("file", file.getName().replace("frmcneg_", "")) });
      listItem.add(new Component[] { (Component)new AjaxLink("delete") {
              private static final long serialVersionUID = 1L;
              
              protected boolean getStatelessHint() {
                return true;
              }
              
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
                FormularioPreingresoDatosUsuario.this.listaArchivos.clear();
                FormularioPreingresoDatosUsuario.this.listaArchivos.addAll(Arrays.asList(FormularioPreingresoDatosUsuario.this.getUploadFolder().listFiles()));
               // target.add(new Component[] { (Component)FormularioPreingresoDatosUsuario.access$1800(this.this$1.this$0) });
              }
            } });
    }
  }
}
