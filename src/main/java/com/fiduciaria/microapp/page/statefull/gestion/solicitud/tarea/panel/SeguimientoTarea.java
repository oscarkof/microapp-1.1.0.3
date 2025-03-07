package com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.panel;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojo;
import java.lang.invoke.SerializedLambda;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;

public abstract class SeguimientoTarea extends BasePanel {
  private final IModel<TareaPojo> modelo;
  
  private class UpdateTareaBehavior extends Behavior {
    private UpdateTareaBehavior() {}
  }
  
  public SeguimientoTarea(String id, IModel<TareaPojo> model) {
    super(id, model);
    this.modelo = model;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initListaSolicitudes();
    initDatosSolicitud();
  }
  
  private void initListaSolicitudes() {
    WebMarkupContainer tarea = new WebMarkupContainer("tarea") {
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          String cssAttrName = "class";
          tag.put(cssAttrName, "pf-c-data-list__item");
        }
      };
    tarea.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)tarea });
    tarea.add(new Behavior[] { new UpdateTareaBehavior() });
    tarea.add(new Behavior[] { (Behavior)new AjaxEventBehavior("click") {
            protected void onEvent(AjaxRequestTarget target) {
              SeguimientoTarea.this.irDetalleTarea(target, SeguimientoTarea.this.modelo);
            }
          } });
  }
  
  private void initDatosSolicitud() {
    Label nombreTarea = new Label("nombreTarea", () -> {
        //LambdaModel.of((TareaPojo)this.modelo.getObject()::getNombreTarea)
        String retval = this.modelo.getObject().getNombreTarea();
        return retval;
    });
    queue(new Component[] { (Component)nombreTarea });
    Label numeroRadicado = new Label("numeroRadicado", LambdaModel.of(((TareaPojo)this.modelo.getObject()).getSolicitudRelacionada()::getRadicadoId));
    queue(new Component[] { (Component)numeroRadicado });
    Label negocio = new Label("negocio", LambdaModel.of(((TareaPojo)this.modelo.getObject()).getSolicitudRelacionada()::getNombreRefNegocio));
    queue(new Component[] { (Component)negocio });
    Label codigoNegocio = new Label("codigoNegocio", LambdaModel.of(((TareaPojo)this.modelo.getObject()).getSolicitudRelacionada()::getIdRefNegocio));
    queue(new Component[] { (Component)codigoNegocio });
    WebMarkupContainer estadoTarea = new WebMarkupContainer("estadoTarea") {
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (Objects.isNull(((TareaPojo)SeguimientoTarea.this.modelo.getObject()).getEstado())) {
            tag.put("class", "pf-c-label");
          } else if (((TareaPojo)SeguimientoTarea.this.modelo.getObject()).getEstado().equalsIgnoreCase("Cancelada")) {
            tag.put("class", "pf-c-label pf-m-red");
          } else if (((TareaPojo)SeguimientoTarea.this.modelo.getObject()).getEstado().equalsIgnoreCase("Cerrada")) {
            tag.put("class", "pf-c-label pf-m-green");
          } else if (((TareaPojo)SeguimientoTarea.this.modelo.getObject()).getEstado().equalsIgnoreCase("Abierta")) {
            tag.put("class", "pf-c-label pf-m-gold");
          } else {
            tag.put("class", "pf-c-label");
          } 
        }
      };
    queue(new Component[] { (Component)estadoTarea });
    Label estadoTareaLabel = new Label("estadoTareaLabel", () -> {
        //LambdaModel.of((TareaPojo)this.modelo.getObject()::getEstado)
        String retval = this.modelo.getObject().getEstado();
        return retval;
    });
    queue(new Component[] { (Component)estadoTareaLabel });
    Label usuarioCreaTarea = new Label("usuarioCreaTarea", () -> {
        //LambdaModel.of((TareaPojo)this.modelo.getObject()::getUsuarioCrea)
        String retval = this.modelo.getObject().getUsuarioCrea();
        return retval;
    });
    queue(new Component[] { (Component)usuarioCreaTarea });
    LocalDateTime localDateTime = LocalDateTime.ofInstant((new Date(((TareaPojo)this.modelo.getObject()).getFechaAsignacion().longValue())).toInstant(), ZoneId.systemDefault());
    Label fechaCreacionTarea = new Label("fechaCreacionTarea", (IModel)Model.of(localDateTime.toString()));
    queue(new Component[] { (Component)fechaCreacionTarea });
    Label descripcionTarea = new Label("descripcionTarea", () -> {
        //LambdaModel.of((TareaPojo)this.modelo.getObject()::getDescripcion)
        String retval = this.modelo.getObject().getDescripcion();
        return retval;
    });
    queue(new Component[] { (Component)descripcionTarea });
  }
  
  public abstract void irDetalleTarea(AjaxRequestTarget paramAjaxRequestTarget, IModel<TareaPojo> paramIModel);
}
