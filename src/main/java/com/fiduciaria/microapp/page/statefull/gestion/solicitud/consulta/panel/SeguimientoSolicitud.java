package com.fiduciaria.microapp.page.statefull.gestion.solicitud.consulta.panel;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojoVM;
import java.lang.invoke.SerializedLambda;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class SeguimientoSolicitud extends BasePanel {
  @SpringBean
  private GenericHttpClient gtwayHttp;
  
  private boolean expandedContent;
  
  private IModel<SolicitudPojo> modelo;
  
  private class UpdateBehavior extends Behavior {
    private UpdateBehavior() {}
  }
  
  public SeguimientoSolicitud(String id, IModel<SolicitudPojo> model) {
    super(id, model);
    this.modelo = model;
    setOutputMarkupPlaceholderTag(true);
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initListaSolicitudes();
    initDatosSolicitud();
    initComponentesTarea();
  }
  
  private void initListaSolicitudes() {
    AjaxLink toogle = new AjaxLink("toogle") {
        public void onClick(AjaxRequestTarget target) {
          SeguimientoSolicitud.this.setExpandedContent(!SeguimientoSolicitud.this.isExpandedContent());
          SeguimientoSolicitud.this.registrarComportamiento(SeguimientoSolicitud.UpdateBehavior.class);
        }
      };
    queue(new Component[] { (Component)toogle });
    WebMarkupContainer solicitud = new WebMarkupContainer("solicitud") {
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          String cssAttrName = "class";
          if (SeguimientoSolicitud.this.isExpandedContent()) {
            tag.put(cssAttrName, "pf-c-data-list__item pf-m-expanded");
          } else {
            tag.put(cssAttrName, "pf-c-data-list__item");
          } 
        }
      };
    solicitud.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)solicitud });
    solicitud.add(new Behavior[] { new UpdateBehavior() });
    WebMarkupContainer contenidoSeguimientoSolicitud = new WebMarkupContainer("contenidoSeguimientoSolicitud") {
        protected void onConfigure() {
          super.onConfigure();
          if (SeguimientoSolicitud.this.isExpandedContent()) {
            setVisible(true);
          } else {
            setVisible(false);
          } 
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
        }
      };
    contenidoSeguimientoSolicitud.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)contenidoSeguimientoSolicitud });
    LoadableDetachableModel<List<TareaPojo>> tareasAsociadas = new LoadableDetachableModel<List<TareaPojo>>() {
        protected List<TareaPojo> load() {
          TareaPojoVM objtar = new TareaPojoVM((IGenericHttpClient)SeguimientoSolicitud.this.gtwayHttp);
          return objtar.listaTareaXSolicitud(((SolicitudPojo)SeguimientoSolicitud.this.modelo.getObject()).getRadicadoId().toString());
        }
      };
    contenidoSeguimientoSolicitud.queue(new Component[] { (Component)new VistaTarea("tareaRelacionada", (IModel<? extends List<TareaPojo>>)tareasAsociadas) });
  }
  
  private class VistaTarea extends ListView<TareaPojo> {
    public VistaTarea(String id, IModel<? extends List<TareaPojo>> model) {
      super(id, model);
    }
    
    protected void populateItem(ListItem<TareaPojo> item) {
      item.add(new Component[] { (Component)new Label("titulo", ((TareaPojo)item.getModelObject()).getTitulo()) });
      item.add(new Component[] { (Component)new Label("descripcion", ((TareaPojo)item.getModelObject()).getDescripcion()) });
      item.add(new Component[] { (Component)new Label("estado", ((TareaPojo)item.getModelObject()).getEstado()) });
      item.add(new Component[] { (Component)new Label("usuarioCrea", ((TareaPojo)item.getModelObject()).getUsuarioCrea()) });
      item.add(new Component[] { (Component)new Label("usuarioAsignado", ((TareaPojo)item.getModelObject()).getUsuarioAsignado()) });
      if (Objects.nonNull(((TareaPojo)item.getModelObject()).getFechaAsignacion())) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        item.add(new Component[] { (Component)new Label("fechaCreacion", formatter
                .format(new Date(((TareaPojo)item.getModelObject()).getFechaAsignacion().longValue()))) });
      } else {
        item.add(new Component[] { (Component)new Label("fechaCreacion", ((TareaPojo)item.getModelObject()).getFechaAsignacion()) });
      } 
      item.add(new Component[] { (Component)new Label("tipoTarea", ((TareaPojo)item.getModelObject()).getTipoTarea()) });
    }
  }
  
  private void initDatosSolicitud() {
    Label tipoSolicitud = new Label("tipoSolicitud", () -> {        
        String retval = this.modelo.getObject().getTipoSolicitud();
        return retval;        
    });    
    queue(new Component[] { (Component)tipoSolicitud });
    
    Label numeroRadicado = new Label("numeroRadicado", () -> {
        //LambdaModel.of((SolicitudPojo)this.modelo.getObject()::getRadicadoId)
        BigDecimal retval = this.modelo.getObject().getRadicadoId();
        return retval;
    });
    queue(new Component[] { (Component)numeroRadicado });
    
    Label negocio = new Label("negocio", () -> {
        //LambdaModel.of((SolicitudPojo)this.modelo.getObject()::getNombreRefNegocio)
        String retval = this.modelo.getObject().getNombreRefNegocio();
        return retval;
    });
    queue(new Component[] { (Component)negocio });
    
    Label fechaRadicado = new Label("fechaRadicado", () -> {
        //LambdaModel.of((SolicitudPojo)this.modelo.getObject()::getFechaHora)
        String retval = this.modelo.getObject().getFechaHora();
        return retval;
    });    
    queue(new Component[] { (Component)fechaRadicado });
    
    Label codigoNegocio = new Label("codigoNegocio", () -> {
        //LambdaModel.of((SolicitudPojo)this.modelo.getObject()::getIdRefNegocio)
        String retval = this.modelo.getObject().getIdRefNegocio();
        return retval;
    });    
    queue(new Component[] { (Component)codigoNegocio });
    
    WebMarkupContainer estadoSolicitud = new WebMarkupContainer("estadoSolicitud") {
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (Objects.isNull(((SolicitudPojo)SeguimientoSolicitud.this.modelo.getObject()).getEstado())) {
            tag.put("class", "pf-c-label pf-m-blue");
          } else if (((SolicitudPojo)SeguimientoSolicitud.this.modelo.getObject()).getEstado().equalsIgnoreCase("Cerrada")) {
            tag.put("class", "pf-c-label pf-m-green");
          } else if (((SolicitudPojo)SeguimientoSolicitud.this.modelo.getObject()).getEstado().equalsIgnoreCase("En proceso")) {
            tag.put("class", "pf-c-label pf-m-gold");
          } else if (((SolicitudPojo)SeguimientoSolicitud.this.modelo.getObject()).getEstado().equalsIgnoreCase("En validacion")) {
            tag.put("class", "pf-c-label pf-m-gold");
          } else if (((SolicitudPojo)SeguimientoSolicitud.this.modelo.getObject()).getEstado().equalsIgnoreCase("Cerrado por rechazo")) {
            tag.put("class", "pf-c-label pf-m-red");
          } else {
            tag.put("class", "pf-c-label");
          } 
        }
      };
    queue(new Component[] { (Component)estadoSolicitud });
    Label estadoSolicitudLabel = new Label("estadoSolicitudLabel", () -> {
        //LambdaModel.of((SolicitudPojo)this.modelo.getObject()::getEstado)
        String retval = this.modelo.getObject().getEstado();
        return retval;
    });
    queue(new Component[] { (Component)estadoSolicitudLabel });
  }
  
  private void initComponentesTarea() {
    AjaxLink agregarTarea = new AjaxLink("agregarTarea") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible((
              Objects.isNull(((SolicitudPojo)SeguimientoSolicitud.this.modelo.getObject()).getEstado()) || (
              Objects.nonNull(((SolicitudPojo)SeguimientoSolicitud.this.modelo.getObject()).getEstado()) && 
              !((SolicitudPojo)SeguimientoSolicitud.this.modelo.getObject()).getEstado().equalsIgnoreCase("Cerrada"))));
        }
        
        public void onClick(AjaxRequestTarget target) {
          String idModal = SeguimientoSolicitud.this.getModalContentId();
          SeguimientoSolicitud.this.addModal((Component)new ModalTarea(idModal, SeguimientoSolicitud.this.modelo) {
                protected void cerrarModalCallback(AjaxRequestTarget target) {
                  SeguimientoSolicitud.this.showModal(false, target);
                  SeguimientoSolicitud.this.removeModal();
                  //target.add(new Component[] { (Component)this.this$1.this$0 });
                }
              });
          SeguimientoSolicitud.this.showModal(true, target);
        }
      };
    queue(new Component[] { (Component)agregarTarea });
  }
  
  private boolean isExpandedContent() {
    return this.expandedContent;
  }
  
  private void setExpandedContent(boolean expanded) {
    this.expandedContent = expanded;
  }
}
