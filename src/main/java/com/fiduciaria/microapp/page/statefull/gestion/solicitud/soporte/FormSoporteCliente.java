package com.fiduciaria.microapp.page.statefull.gestion.solicitud.soporte;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.componentes.MaskInputFormatAppender;
import com.fiduciaria.microapp.componentes.StatelessAjaxSubmitLink;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoPojo;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoVM;
import com.fiduciaria.microapp.store.dispositivo.CelularPojo;
import com.fiduciaria.microapp.store.model.gui.solicitud.FormularioSoportePojo;
import com.fiduciaria.microapp.store.model.gui.solicitud.SoporteTecnico;
import com.fiduciaria.microapp.store.model.gui.solicitud.TipoFormulario;
import com.fiduciaria.microapp.store.model.persona.PersonaPojo;
import java.io.File;
import java.io.IOException;
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
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
//import org.apache.wicket.util.file.File;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.IValidator;

public class FormSoporteCliente extends BasePanel {
  @SpringBean
  IGenericHttpClient gtwayHttp;
  
  private final FormSoporteClienteVM viewModel;
  
  private WebMarkupContainer dataview = new WebMarkupContainer("dataview");
  
  private WebMarkupContainer dataviewSelected = new WebMarkupContainer("dataviewSelected");
  
  private final List<File> listaArchivos = new ArrayList<>();
  
  private final List<FileDescription> listadescArchivos = new ArrayList<>();
  
  private final Component feedback = (Component)new FeedbackPanel("feedback");
  
  private String mensajeFeedback;
  
  private boolean guardado;
  
  private boolean onError;
  
  private class UpdateFormBehavior extends Behavior {
    private UpdateFormBehavior() {}
  }
  
  private class UpdSeccOutofFormBehavior extends Behavior {
    private UpdSeccOutofFormBehavior() {}
  }
  
  public FormSoporteCliente(String id, IModel<FormSoporteClienteVM> model) {
    super(id, model);
    this.viewModel = (FormSoporteClienteVM)model.getObject();
    this.viewModel.setStep(1);
    initModelo();
  }
  
  private void initModelo() {
    this.viewModel.setModelo(new FormularioSoportePojo());
    this.viewModel.getModelo().setSoporteTecnico(new SoporteTecnico());
    this.viewModel.getModelo().setTipoFormulario(TipoFormulario.SOPORTETECNICO);
    this.viewModel.getModelo().getSoporteTecnico().setPersona(new PersonaPojo());
    this.viewModel.getModelo().getSoporteTecnico().getPersona().setTipoDocumento(new TipoDocumentoPojo());
    this.viewModel.getModelo().getSoporteTecnico().getPersona().setCelular(new CelularPojo());
  }
  
  protected void onInitialize() {
    super.onInitialize();
    queue(new Component[] { this.feedback });
    this.feedback.setOutputMarkupPlaceholderTag(true);
    ContextImage imgQuedateEncasa = new ContextImage("imgQuedateEncasa", "images/quedateencasa.png");
    queue(new Component[] { (Component)imgQuedateEncasa });
    WebMarkupContainer alertaProcesamiento = new WebMarkupContainer("alertaProcesamiento") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormSoporteCliente.this.guardado);
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          String htlTagCls = "class";
          if (FormSoporteCliente.this.onError) {
            tag.put(htlTagCls, "pf-c-alert pf-m-danger");
          } else {
            tag.put(htlTagCls, "pf-c-alert pf-m-success");
          } 
        }
      };
    LoadableDetachableModel<String> mss = new LoadableDetachableModel<String>() {
        protected String load() {
          return FormSoporteCliente.this.mensajeFeedback;
        }
      };
    alertaProcesamiento.add(new Component[] { (Component)new Label("mensajeFeedback", (IModel)mss) });
    alertaProcesamiento.add(new Behavior[] { new UpdSeccOutofFormBehavior() });
    alertaProcesamiento.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)alertaProcesamiento });
    WebMarkupContainer seccionesFormulario = new WebMarkupContainer("seccionesFormulario") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!FormSoporteCliente.this.guardado);
        }
      };
    seccionesFormulario.add(new Behavior[] { new UpdSeccOutofFormBehavior() });
    seccionesFormulario.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)seccionesFormulario });
    final Form formulario = new Form("form");
    formulario
      .setDefaultModel((IModel)new CompoundPropertyModel(this.viewModel.getModelo()));
    formulario.setOutputMarkupPlaceholderTag(true);
    formulario.add(new Behavior[] { new UpdateFormBehavior() });
    queue(new Component[] { (Component)formulario });
    initPrerrequisitos();
    initTipoSolicitud();
    iniDatosNegocio();
    iniDatosUsuario();
    initProgressBar();
    AjaxSubmitLink siguienteBtn = new AjaxSubmitLink("siguienteBtn", formulario) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          int nextStep = (FormSoporteCliente.this.viewModel.getStep() + 1 > 6) ? 6 : (FormSoporteCliente.this.viewModel.getStep() + 1);
          if (FormSoporteCliente.this.viewModel.getModelo().isAceptacionPrerrequisitos())
            FormSoporteCliente.this.viewModel.setStep(nextStep); 
          FormSoporteCliente.this.registrarComportamiento(FormSoporteCliente.UpdateFormBehavior.class);
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
          int backStep = (FormSoporteCliente.this.viewModel.getStep() - 1 < 1) ? 1 : (FormSoporteCliente.this.viewModel.getStep() - 1);
          FormSoporteCliente.this.viewModel.setStep(backStep);
          FormSoporteCliente.this.registrarComportamiento(FormSoporteCliente.UpdateFormBehavior.class);
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)formulario });
        }
      };
    queue(new Component[] { (Component)regresarBtn });
    StatelessAjaxSubmitLink statelessAjaxSubmitLink = new StatelessAjaxSubmitLink("submitBtn", formulario) {
        protected void onConfigure() {
          super.onConfigure();
          setVisible((FormSoporteCliente.this.viewModel.getStep() >= 6));
        }
        
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          if (Objects.isNull(FormSoporteCliente.this.viewModel.getModelo().getSoporteTecnico()
              .getAdjuntos()))
            FormSoporteCliente.this.viewModel.getModelo().getSoporteTecnico()
              .setAdjuntos(new ArrayList()); 
          for (File file : FormSoporteCliente.this.getUploadFolder().getFiles())
            FormSoporteCliente.this.viewModel.getModelo().getSoporteTecnico()
              .getAdjuntos().add(file.getAbsolutePath()); 
          String radicadoSol = FormSoporteCliente.this.viewModel.guardarFormularioSoporte();
          if (radicadoSol.toLowerCase().contains("error")) {
            FormSoporteCliente.this.guardado = false;
            FormSoporteCliente.this.onError = true;
            FormSoporteCliente.this.mensajeFeedback = "Surgiun inconveniente guardando tu solicitud.  " + radicadoSol;
          } else {
            FormSoporteCliente.this.getUploadFolder().remove();
            FormSoporteCliente.this.guardado = true;
            FormSoporteCliente.this.onError = false;
            FormSoporteCliente.this.mensajeFeedback = "Hemos guardado con exito tu solicitud.  " + radicadoSol;
          } 
          target.add(new Component[] { (Component)getPage() });
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)formulario });
        }
      };
    queue(new Component[] { (Component)statelessAjaxSubmitLink });
    initControlSecciones();
    initAdjunto();
  }
  
  private void initControlSecciones() {
    WebMarkupContainer seccionDos = new WebMarkupContainer("seccionDos") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormSoporteCliente.this.getStep().equals(Integer.valueOf(1)));
        }
      };
    queue(new Component[] { (Component)seccionDos });
    WebMarkupContainer seccionTres = new WebMarkupContainer("seccionTres") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormSoporteCliente.this.getStep().equals(Integer.valueOf(2)));
        }
      };
    queue(new Component[] { (Component)seccionTres });
    WebMarkupContainer seccionCuatro = new WebMarkupContainer("seccionCuatro") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormSoporteCliente.this.getStep().equals(Integer.valueOf(3)));
        }
      };
    queue(new Component[] { (Component)seccionCuatro });
    WebMarkupContainer seccionCinco = new WebMarkupContainer("seccionCinco") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormSoporteCliente.this.getStep().equals(Integer.valueOf(4)));
        }
      };
    queue(new Component[] { (Component)seccionCinco });
    WebMarkupContainer seccionOcho = new WebMarkupContainer("seccionOcho") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormSoporteCliente.this.getStep().equals(Integer.valueOf(5)));
        }
      };
    queue(new Component[] { (Component)seccionOcho });
    WebMarkupContainer seccionNueve = new WebMarkupContainer("seccionNueve") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(FormSoporteCliente.this.getStep().equals(Integer.valueOf(6)));
        }
      };
    queue(new Component[] { (Component)seccionNueve });
  }
  
  private void initPrerrequisitos() {
    CheckBox aceptacionPrerrequisitos = new CheckBox("aceptacionPrerrequisitos", LambdaModel.of(this.viewModel
          .getModelo()::isAceptacionPrerrequisitos, this.viewModel
          .getModelo()::setAceptacionPrerrequisitos));
    queue(new Component[] { (Component)aceptacionPrerrequisitos });
  }
  
  private void iniDatosUsuario() {
    LoadableDetachableModel<List<TipoDocumentoPojo>> listTipoDocumentos = new LoadableDetachableModel<List<TipoDocumentoPojo>>() {
        protected List<TipoDocumentoPojo> load() {
          TipoDocumentoVM dataObj = new TipoDocumentoVM(FormSoporteCliente.this.gtwayHttp);
          return dataObj.listaTipoDocumentos();
        }
      };
    ChoiceRenderer renderer = new ChoiceRenderer("descripciondocumento", "clasedocumento");
    final DropDownChoice<TipoDocumentoPojo> tipoDocumento = new DropDownChoice("tipoDocumento", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()::getTipoDocumento, this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()::setTipoDocumento), (IModel)listTipoDocumentos, (IChoiceRenderer)renderer);
    tipoDocumento.setOutputMarkupPlaceholderTag(true);
    tipoDocumento.setRequired(true);
    tipoDocumento.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Indicar el Tipo Documento del usuario") });
    tipoDocumento.setLabel((IModel)Model.of("Tipo Documento"));
    tipoDocumento.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)tipoDocumento });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)tipoDocumento });
            }
          } });
    queue(new Component[] { (Component)tipoDocumento });
    final TextField idDocumento = new TextField("idDocumento", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()::getNumeroDocumento, this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()::setNumeroDocumento));
    idDocumento.setOutputMarkupPlaceholderTag(true);
    idDocumento.setRequired(true);
    idDocumento.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Indicar el Nde documento") });
    idDocumento.setLabel((IModel)Model.of("NDocumento"));
    idDocumento.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)idDocumento });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)idDocumento });
            }
          } });
    queue(new Component[] { (Component)idDocumento });
    final TextField celular = new TextField("celular", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()
          .getCelular()::getNumeroCelular, this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()
          .getCelular()::setNumeroCelular));
    celular.setOutputMarkupPlaceholderTag(true);
    celular.setRequired(true);
    celular.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Celular de Contacto") });
    celular.setLabel((IModel)Model.of("Celular"));
    celular.add(new Behavior[] { (Behavior)MaskInputFormatAppender.general("[3,3,4]", "-", null, false, false, true) });
    celular.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)celular });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)celular });
            }
          } });
    queue(new Component[] { (Component)celular });
    final TextField primerNombre = new TextField("primerNombre", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()::getPrimerNombre, this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()::setPrimerNombre));
    primerNombre.setOutputMarkupPlaceholderTag(true);
    primerNombre.setRequired(true);
    primerNombre.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", " ") });
    primerNombre.setLabel((IModel)Model.of("Primer Nombre"));
    primerNombre.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)primerNombre });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)primerNombre });
            }
          } });
    queue(new Component[] { (Component)primerNombre });
    final TextField segundoNombre = new TextField("segundoNombre", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()::getSegundoNombre, this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
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
    queue(new Component[] { (Component)segundoNombre });
    final TextField primerApellido = new TextField("primerApellido", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()::getPrimerApellido, this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()::setPrimerApellido));
    primerApellido.setOutputMarkupPlaceholderTag(true);
    primerApellido.setRequired(true);
    primerApellido.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", " ") });
    primerApellido.setLabel((IModel)Model.of("Primer Apellido"));
    primerApellido.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)primerApellido });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)primerApellido });
            }
          } });
    queue(new Component[] { (Component)primerApellido });
    final TextField segundoApellido = new TextField("segundoApellido", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()::getSegundoApellido, this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
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
    queue(new Component[] { (Component)segundoApellido });
    final TextField correoElectronico = new TextField("correoElectronico", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()::getCorreoElectronico, this.viewModel
          
          .getModelo()
          .getSoporteTecnico()
          .getPersona()::setCorreoElectronico));
    correoElectronico.setOutputMarkupPlaceholderTag(true);
    correoElectronico.setRequired(true);
    correoElectronico.add((IValidator)RfcCompliantEmailAddressValidator.getInstance());
    correoElectronico.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", " ") });
    correoElectronico.setLabel((IModel)Model.of("Email"));
    correoElectronico.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)correoElectronico });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)correoElectronico });
            }
          } });
    queue(new Component[] { (Component)correoElectronico });
  }
  
  private void iniDatosNegocio() {
    final TextField codigoNegocio = new TextField("codigoNegocio", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getSoporteTecnico()::getCodigoNegocioForm, this.viewModel
          
          .getModelo()
          .getSoporteTecnico()::setCodigoNegocioForm));
    codigoNegocio.setOutputMarkupPlaceholderTag(true);
    codigoNegocio.setRequired(true);
    codigoNegocio.setEnabled(false);
    codigoNegocio.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Indicar el cdel Negocio junto con el nombre (Enunciado en el asunto del email)") });
    codigoNegocio.setLabel((IModel)Model.of("CNegocio / Fondo de Inversi"));
    codigoNegocio.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)codigoNegocio });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)codigoNegocio });
            }
          } });
    queue(new Component[] { (Component)codigoNegocio });
    final TextField nombreNegocio = new TextField("nombreNegocio", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getSoporteTecnico()::getNombreNegocioForm, this.viewModel
          .getModelo()
          .getSoporteTecnico()::setNombreNegocioForm));
    nombreNegocio.setOutputMarkupPlaceholderTag(true);
    nombreNegocio.setRequired(true);
    nombreNegocio.setEnabled(false);
    nombreNegocio.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Indicar el Nombre del Negocio (Enunciado en el asunto del email)") });
    nombreNegocio.setLabel((IModel)Model.of("Nombre Fideicomiso / Fondo de Inversi"));
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
  
  private void initAdjunto() {
    final TextField descripcionCasoSoporte = new TextField("descripcionCasoSoporte", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getSoporteTecnico()::getDescripcionCasoSoporte, this.viewModel
          
          .getModelo()
          .getSoporteTecnico()::setDescripcionCasoSoporte));
    descripcionCasoSoporte.setOutputMarkupPlaceholderTag(true);
    descripcionCasoSoporte.setRequired(true);
    descripcionCasoSoporte.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "De ser necesario ingrese una descripción") });
    descripcionCasoSoporte.setLabel((IModel)Model.of("Descripción del caso"));
    queue(new Component[] { (Component)descripcionCasoSoporte });
    descripcionCasoSoporte.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)descripcionCasoSoporte });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)descripcionCasoSoporte });
            }
          } });
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
            FormSoporteCliente.this.listaArchivos.clear();
            FormSoporteCliente.this.listaArchivos.addAll(Arrays.asList(FormSoporteCliente.this.getUploadFolder().listFiles()));
            return FormSoporteCliente.this.listaArchivos;
          }
        });
    queue(new Component[] { (Component)fileListView });
    FileDescriptionListView fileDescListView = new FileDescriptionListView("fileDescList", (IModel<List<FileDescription>>)new LoadableDetachableModel<List<FileDescription>>() {
          protected List<FileDescription> load() {
            return FormSoporteCliente.this.listadescArchivos;
          }
        });
    queue(new Component[] { (Component)fileDescListView });
  }
  
  private Integer getStep() {
    if (this.viewModel.getStep() < 1)
      return Integer.valueOf(1); 
    return Integer.valueOf(this.viewModel.getStep());
  }
  
  private void initProgressBar() {
    WebMarkupContainer progressPanel = new WebMarkupContainer("progressPanel");
    queue(new Component[] { (Component)progressPanel });
    progressPanel.setOutputMarkupPlaceholderTag(true);
    progressPanel.add(new Behavior[] { new UpdateFormBehavior() });
    LoadableDetachableModel<String> percentage = new LoadableDetachableModel<String>() {
        protected String load() {
          String labelProgres = FormSoporteCliente.this.viewModel.getStep() + " de " + '\006';
          return labelProgres;
        }
      };
    Label progressPercentage = new Label("progressPercentage", (IModel)percentage);
    queue(new Component[] { (Component)progressPercentage });
    WebMarkupContainer progressPercentageBar = new WebMarkupContainer("progressPercentageBar") {
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          int porcentaje = FormSoporteCliente.this.viewModel.getStep() * 100 / 6;
          tag.put("style", "width: " + porcentaje + "%;");
        }
      };
    progressPercentageBar.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)progressPercentageBar });
  }
  
  private void initTipoSolicitud() {
    List<String> listTipoSolicitudes = getTipoSolicitud();
    DropDownChoice tipoSolicitud = new DropDownChoice("tipoSolicitud", LambdaModel.of(this.viewModel
          
          .getModelo()
          .getSoporteTecnico()::getTipoSolicitud, this.viewModel
          
          .getModelo()
          .getSoporteTecnico()::setTipoSolicitud), listTipoSolicitudes);
    tipoSolicitud.setRequired(true);
    tipoSolicitud.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Seleccione el tipo de solicitud. <br> Para continuar, es requerido responder a esta pregunta. ") });
    tipoSolicitud.setLabel((IModel)Model.of("Registro FIDUSAP WEB"));
    queue(new Component[] { (Component)tipoSolicitud });
  }
  
  private List<String> getTipoSolicitud() {
    List<String> response = new ArrayList<>();
    response.add("No he recibido mi Usuario / clave");
    response.add("No he recibido manual FIDUSAP");
    response.add("Problemas con Ingreso");
    response.add("Problemas con Token digital");
    response.add("Bloqueo/desbloqueo Clave");
    response.add("Problemas con el cargue");
    response.add("Problemas con la autorización");
    response.add("Soporte Especializado");
    response.add("Cambio de Clave Usuario");
    return response;
  }
  
  private class FileUploadForm extends Form<Void> {
    private List<FileUpload> listaAdjuntos = new ArrayList<>();
    
    public FileUploadForm(String id) {
      super(id);
      setOutputMarkupPlaceholderTag(true);
      setMultiPart(true);
      final FileUploadField fileInput = new FileUploadField("fileInput", (IModel)new ListModel(this.listaAdjuntos));
      add(new Component[] { (Component)fileInput });
      fileInput.add(new Behavior[] { (Behavior)new FilesSelectedBehavior() {
              protected void onSelected(AjaxRequestTarget target, List<FileDescription> fileDescriptions) {
                FormSoporteCliente.this.listadescArchivos.clear();
                FormSoporteCliente.this.listadescArchivos.addAll(fileDescriptions);
                Bytes bytes = Bytes.bytes(fileDescriptions.stream().mapToLong(FileDescription::getFileSize).sum());
                if (bytes.greaterThan(FormSoporteCliente.FileUploadForm.this.getFileMaxSize())) {
                  FormSoporteCliente.FileUploadForm.this.error(" Supera el valor mpermito para carga de un archivo  " + FormSoporteCliente.FileUploadForm.this.getFileMaxSize());
                } else {
                  FormSoporteCliente.FileUploadForm.this.info("Permite cargar archivos" + FormSoporteCliente.FileUploadForm.this.getMaxSize());
                } 
                //target.add(new Component[] { (Component)FormSoporteCliente.access$1000(this.this$1.this$0), FormSoporteCliente.access$1100(this.this$1.this$0) });
              }
            } });
      add(new Component[] { (Component)new AjaxButton("submitUpload") {
              protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                List<FileUpload> uploadfls = fileInput.getFileUploads();
                for (FileUpload upload : uploadfls) {
                  File newFile = new File((File)FormSoporteCliente.this.getUploadFolder(), upload.getClientFileName());
                  FormSoporteCliente.this.checkFileExists(newFile);
                  try {
                    newFile.createNewFile();
                    upload.writeTo(newFile);
                    FormSoporteCliente.this.listaArchivos.clear();
                    FormSoporteCliente.this.listaArchivos.addAll(Arrays.asList(FormSoporteCliente.this.getUploadFolder().listFiles()));
                  } catch (Exception e) {
                    throw new IllegalStateException("Unable to write file");
                  } 
                } 
                //target.add(new Component[] { (Component)FormSoporteCliente.access$1300(this.this$1.this$0), (Component)this.val$fileInput, (Component)this.this$1 });
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
    Folder folder = new Folder(System.getProperty("java.io.tmpdir") + "/hsperfdata_psbswn/sesstmp/" + usuarioSesion() + "/formsuport");
    try {
      folder.ensureExists();
    } catch (IOException ex) {
      Logger.getLogger(FormSoporteCliente.class.getName()).log(Level.SEVERE, (String)null, ex);
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
                FormSoporteCliente.this.listaArchivos.clear();
                FormSoporteCliente.this.listaArchivos.addAll(Arrays.asList(FormSoporteCliente.this.getUploadFolder().listFiles()));
                //target.add(new Component[] { (Component)FormSoporteCliente.access$1300(this.this$1.this$0) });
              }
            } });
    }
  }
}
