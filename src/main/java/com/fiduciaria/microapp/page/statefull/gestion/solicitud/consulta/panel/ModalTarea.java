package com.fiduciaria.microapp.page.statefull.gestion.solicitud.consulta.panel;

import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.decorator.behavior.FieldDecorator;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojoVM;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.panel.modales.ModalUsuarios;
import com.fiduciaria.microapp.store.seguridad.PrincipalPojo;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public abstract class ModalTarea extends Panel {
  @SpringBean
  GenericHttpClient httpGtwy;
  
  private final TareaPojo tareamodel;
  
  private final IModel<SolicitudPojo> modeloSol;
  
  private final WebMarkupContainer tareaPanel;
  
  private final WebMarkupContainer seleccionUsuarioPanel;
  
  private boolean seleccionadoUsuario;
  
  public ModalTarea(String id, IModel<SolicitudPojo> model) {
    super(id, model);
    this.tareamodel = new TareaPojo();
    this.modeloSol = model;
    this.tareamodel.setNumRadicado(((SolicitudPojo)this.modeloSol.getObject()).getRadicadoId().toString());
    this.tareaPanel = new WebMarkupContainer("tareaPanel") {
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (!ModalTarea.this.seleccionadoUsuario) {
            tag.put("style", "");
            tag.remove("hidden");
          } else {
            tag.put("style", "display:none;");
            tag.put("hidden", "true");
          } 
        }
      };
    this.seleccionUsuarioPanel = new WebMarkupContainer("seleccionUsuarioPanel") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(ModalTarea.this.seleccionadoUsuario);
        }
      };
  }
  
  protected void onInitialize() {
    super.onInitialize();
    this.tareaPanel.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)this.tareaPanel });
    this.seleccionUsuarioPanel.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)this.seleccionUsuarioPanel });
    this.seleccionUsuarioPanel.add(new Component[] { (Component)new WebMarkupContainer("content") });
    final Form<Void> form = new Form("form");
    queue(new Component[] { (Component)form });
    final TextField loginUsuario = new TextField("loginUsuario", LambdaModel.of(this.tareamodel::getUsuarioAsignado));
    form.add(new Component[] { (Component)loginUsuario });
    loginUsuario.setOutputMarkupPlaceholderTag(true);
    loginUsuario.setRequired(true);
    loginUsuario.add(new Behavior[] { (Behavior)new FieldDecorator() });
    AjaxLink showSelecUsuarioPanel = new AjaxLink("showSelecUsuarioPanel") {
        public void onClick(AjaxRequestTarget target) {
          ModalTarea.this.seleccionadoUsuario = true;
          ModalUsuarios modalUsr = new ModalUsuarios("content", "TODOS") {
              public void cerrar(AjaxRequestTarget target) {
                ModalTarea.this.seleccionadoUsuario = false;
                ModalTarea.this.seleccionUsuarioPanel.addOrReplace(new Component[] { (Component)new WebMarkupContainer("content") });
                //target.add(new Component[] { (Component)ModalTarea.access$100(this.this$1.this$0), (Component)ModalTarea.access$200(this.this$1.this$0), (Component)this.this$1.val$loginUsuario });
              }
              
              public void seleccionarUsuario(AjaxRequestTarget target, PrincipalPojo usuario) {
                ModalTarea.this.seleccionadoUsuario = false;
                ModalTarea.this.tareamodel.setUsuarioAsignado(usuario.getUsuario());
                ModalTarea.this.seleccionUsuarioPanel.addOrReplace(new Component[] { (Component)new WebMarkupContainer("content") });
               // target.add(new Component[] { (Component)ModalTarea.access$100(this.this$1.this$0), (Component)ModalTarea.access$200(this.this$1.this$0), (Component)this.this$1.val$loginUsuario });
              }
            };
          ModalTarea.this.seleccionUsuarioPanel.addOrReplace(new Component[] { (Component)modalUsr });
          //target.add(new Component[] { (Component)ModalTarea.access$100(this.this$0), (Component)ModalTarea.access$200(this.this$0), (Component)this.val$loginUsuario });
        }
      };
    queue(new Component[] { (Component)showSelecUsuarioPanel });
    TextArea descripcion = new TextArea("descripcion", LambdaModel.of(this.tareamodel::getDescripcion, this.tareamodel::setDescripcion));
    queue(new Component[] { (Component)descripcion });
    descripcion.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
          } });
    DropDownChoice tipoTarea = new DropDownChoice("tipoTarea", LambdaModel.of(this.tareamodel::getTipoTarea, this.tareamodel::setTipoTarea), getTipoTareas());
    queue(new Component[] { (Component)tipoTarea });
    tipoTarea.setOutputMarkupPlaceholderTag(true);
    tipoTarea.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
          } });
    TextField tituloTarea = new TextField("tituloTarea", LambdaModel.of(this.tareamodel::getTitulo, this.tareamodel::setTitulo));
    queue(new Component[] { (Component)tituloTarea });
    tituloTarea.add(new Behavior[] { (Behavior)new FieldDecorator() });
    tituloTarea.setRequired(true);
    tituloTarea.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
          } });
    AjaxLink cerrarModal = new AjaxLink("cerrarModal") {
        public void onClick(AjaxRequestTarget target) {
          ModalTarea.this.cerrarModalCallback(target);
        }
      };
    queue(new Component[] { (Component)cerrarModal });
    AjaxSubmitLink guardarBtn = new AjaxSubmitLink("guardarBtn", form) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          ModalTarea.this.tareamodel.setUsuarioCrea(getSession().getAttribute("username").toString());
          TareaPojoVM.instance(ModalTarea.this.httpGtwy).guardarTarea(ModalTarea.this.tareamodel);
          ModalTarea.this.cerrarModalCallback(target);
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)form });
        }
      };
    queue(new Component[] { (Component)guardarBtn });
    AjaxLink cancelarBtn = new AjaxLink("cancelarBtn") {
        public void onClick(AjaxRequestTarget target) {
          ModalTarea.this.cerrarModalCallback(target);
        }
      };
    queue(new Component[] { (Component)cancelarBtn });
    form.add(new Behavior[] { (Behavior)new AjaxFormSubmitBehavior(form, "change") {
            protected void onSubmit(AjaxRequestTarget target) {}
            
            protected void onError(AjaxRequestTarget target) {}
          } });
  }
  
  private List<String> getTipoTareas() {
    List<String> response = new ArrayList<>();
    response.add("Crear usuario ALPHA");
    response.add("Crear usuario Fidusap");
    response.add("Crear usuario Fidusap /Sala de ventas");
    response.add("Enviar notificacion al cliente");
    response.add("Enrolar usuario en softoken");
    response.add("Revisar solicitud");
    return response;
  }
  
  protected abstract void cerrarModalCallback(AjaxRequestTarget paramAjaxRequestTarget);
}
