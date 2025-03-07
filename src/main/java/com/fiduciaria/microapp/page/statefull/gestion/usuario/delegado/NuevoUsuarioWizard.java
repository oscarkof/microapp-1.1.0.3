package com.fiduciaria.microapp.page.statefull.gestion.usuario.delegado;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import com.fiduciaria.microapp.page.statefull.gestion.usuario.model.GestionUsuariosVM;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoPojo;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoVM;
import com.fiduciaria.microapp.store.model.persona.PersonaPojo;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
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

public class NuevoUsuarioWizard extends BasePanel {
  @SpringBean
  IGenericHttpClient gtwayHttp;
  
  private IModel<GestionUsuariosVM> modelo;
  
  public NuevoUsuarioWizard(String id, IModel<GestionUsuariosVM> modelo) {
    super(id, modelo);
    this.modelo = modelo;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initFormNuevoUsuario();
  }
  
  private void initFormNuevoUsuario() {
    queue(new Component[] { (Component)new AjaxLink("backtoUsuarios") {
            public void onClick(AjaxRequestTarget target) {
              ((GestionUsuariosVM)NuevoUsuarioWizard.this.modelo.getObject()).setNuevoUsuarioActivado(!((GestionUsuariosVM)NuevoUsuarioWizard.this.modelo.getObject()).isNuevoUsuarioActivado());
              NuevoUsuarioWizard.this.registrarComportamiento(ActualizarComportamiento.class);
            }
          } });
    Form formularioNuevoUsuario = new Form("formularioNuevoUsuario");
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
          TipoDocumentoVM dataObj = new TipoDocumentoVM(NuevoUsuarioWizard.this.gtwayHttp);
          return dataObj.listaTipoDocumentos();
        }
      };
    ChoiceRenderer rendererTipDoc = new ChoiceRenderer("descripciondocumento", "clasedocumento");
    final DropDownChoice<TipoDocumentoPojo> tipoIdentificacion = new DropDownChoice("tipoIdentificacion", () -> { 
        //LambdaModel.of((PersonaPojo)((GestionUsuariosVM)this.modelo.getObject()).getPersonaModel().getObject()::getTipoDocumento, (PersonaPojo)((GestionUsuariosVM)this.modelo.getObject()).getPersonaModel().getObject()::setTipoDocumento)
        TipoDocumentoPojo retval = this.modelo.getObject().getPersonaModel().getObject().getTipoDocumento();
        return retval;}, (IModel)tiposDocumentos, (IChoiceRenderer)rendererTipDoc);
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
    queue(new Component[] { (Component)textFieldHelper("codigoUsuario", true, true, "ingrese el nombre de usuario(Nombre login) con el cual se identificarpara realizar ingreso a la aplicacion.", "Nombre de Usuario") });
    queue(new Component[] { (Component)textFieldHelper("numeroIdentificacion", true, false, "", "Nde identificación") });
    queue(new Component[] { (Component)textFieldHelper("primerNombre", true, false, "", "Primer Nombre") });
    queue(new Component[] { (Component)textFieldHelper("segundoNombre", true, false, "", "Segundo Nombre") });
    queue(new Component[] { (Component)textFieldHelper("primerApellido", true, false, "", "Primer Apellido") });
    queue(new Component[] { (Component)textFieldHelper("segundoApellido", true, false, "", "Segundo Apellido") });
    queue(new Component[] { (Component)textFieldHelper("correoElectronico", true, false, "", "Email") });
    queue(new Component[] { (Component)textFieldHelper("telefono", true, false, "", "telefono") });
    queue(new Component[] { (Component)textFieldHelper("celular", true, false, "", "celular") });
    CheckBox usarioSalaventas = new CheckBox("usarioSalaventas");
    queue(new Component[] { (Component)usarioSalaventas });
  }
  
  private TextField textFieldHelper(String id, boolean required, boolean popover, String popoverMsg, String fieldLabel) {
    TextField textField = new TextField(id);
    textField.setOutputMarkupPlaceholderTag(true);
    textField.setRequired(required);
    textField.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(popover, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", popoverMsg) });
    textField.setLabel((IModel)Model.of(fieldLabel));
    return textField;
  }
}
