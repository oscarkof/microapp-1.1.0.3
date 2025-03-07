package com.fiduciaria.microapp.page.statefull.gestion.solicitud;

import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.GestionSolicitudesVM;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class SolicitudesTabPanel extends Panel {
  private final IModel<GestionSolicitudesVM> modelo;
  
  public SolicitudesTabPanel(String id, IModel<GestionSolicitudesVM> model) {
    super(id, model);
    this.modelo = model;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    queue(new Component[] { (Component)crearAjaxLinkOpcion("preIngresoDatoUsuario", EnumOperacionNavegacionSolicitudesPanel.PREINGRESO) });
    queue(new Component[] { (Component)crearAjaxLinkOpcion("soporte", EnumOperacionNavegacionSolicitudesPanel.SOPORTE) });
    AjaxLink consulta = crearAjaxLinkOpcion("consulta", EnumOperacionNavegacionSolicitudesPanel.CONSULTA);
    queue(new Component[] { (Component)consulta });
    if (AuthenticatedWebSession.get().getRoles().hasRole("UIFA")) {
      consulta.setVisible(true);
    } else {
      consulta.setVisible(false);
    } 
    AjaxLink tarea = crearAjaxLinkOpcion("tarea", EnumOperacionNavegacionSolicitudesPanel.TAREA);
    queue(new Component[] { (Component)tarea });
    if (AuthenticatedWebSession.get().getRoles().hasRole("UIFA") || 
      AuthenticatedWebSession.get().getRoles().hasRole("UIFS") || 
      AuthenticatedWebSession.get().getRoles().hasRole("UCA")) {
      tarea.setVisible(true);
    } else {
      tarea.setVisible(false);
    } 
  }
  
  private AjaxLink crearAjaxLinkOpcion(String id, final EnumOperacionNavegacionSolicitudesPanel operacion) {
    AjaxLink ajaxLinkResponse = new AjaxLink(id) {
        public void onClick(AjaxRequestTarget target) {
          send((IEventSink)
              getPage(), Broadcast.BREADTH, new EventoNavegacionGestionSolicitudes(target, operacion));
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (((GestionSolicitudesVM)SolicitudesTabPanel.this.modelo.getObject()).getCurrenTabOption() == operacion) {
            tag.put("class", "pf-c-tabs__item  pf-m-current");
          } else {
            tag.put("class", "pf-c-tabs__item");
          } 
        }
      };
    return ajaxLinkResponse;
  }
  
  public static final SolicitudesTabPanel getInstance(String id, IModel<GestionSolicitudesVM> model) {
    return new SolicitudesTabPanel(id, model);
  }
}
