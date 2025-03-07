package com.fiduciaria.microapp.page.statefull.gestion.actividades;

import com.fiduciaria.microapp.page.statefull.gestion.actividades.model.GestionActividadesVM;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class GestionActividadesTabPanel extends Panel {
  private final IModel<GestionActividadesVM> modelo;
  
  public GestionActividadesTabPanel(String id, IModel<GestionActividadesVM> model) {
    super(id, model);
    this.modelo = model;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    queue(new Component[] { (Component)crearAjaxLinkOpcion("linkPreIngresoDatosUsuario", EnumOperacionNavegacionActividadesPanel.LINK_PREINGRESO) });
    queue(new Component[] { (Component)crearAjaxLinkOpcion("radicados", EnumOperacionNavegacionActividadesPanel.RADICADOS) });
    AjaxLink consulta = crearAjaxLinkOpcion("consulta", EnumOperacionNavegacionActividadesPanel.CONSULTA);
    consulta.setVisible(false);
    queue(new Component[] { (Component)consulta });
    AjaxLink condicionesNegocioFirma = crearAjaxLinkOpcion("condicionesNegocioFirma", EnumOperacionNavegacionActividadesPanel.CONDICIONES_FIRMAS_NEGOCIO);
    queue(new Component[] { (Component)condicionesNegocioFirma });
    if (AuthenticatedWebSession.get().getRoles().hasRole("UIFA")) {
      condicionesNegocioFirma.setVisible(true);
    } else {
      condicionesNegocioFirma.setVisible(false);
    } 
  }
  
  private AjaxLink crearAjaxLinkOpcion(String id, final EnumOperacionNavegacionActividadesPanel operacion) {
    AjaxLink ajaxLinkResponse = new AjaxLink(id) {
        public void onClick(AjaxRequestTarget target) {
          send((IEventSink)
              getPage(), Broadcast.BREADTH, new EventoNavegacionGestionActividades(target, operacion));
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (((GestionActividadesVM)GestionActividadesTabPanel.this.modelo.getObject()).getCurrenTabOption() == operacion) {
            tag.put("class", "pf-c-tabs__item  pf-m-current");
          } else {
            tag.put("class", "pf-c-tabs__item");
          } 
        }
      };
    return ajaxLinkResponse;
  }
  
  public static final GestionActividadesTabPanel getInstance(String id, IModel<GestionActividadesVM> model) {
    return new GestionActividadesTabPanel(id, model);
  }
}
