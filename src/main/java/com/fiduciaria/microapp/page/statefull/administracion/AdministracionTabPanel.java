package com.fiduciaria.microapp.page.statefull.administracion;

import com.fiduciaria.microapp.page.statefull.administracion.model.AdministracionTenantVM;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class AdministracionTabPanel extends Panel {
  private final IModel<AdministracionTenantVM> modelo;
  
  public AdministracionTabPanel(String id, IModel<AdministracionTenantVM> model) {
    super(id, model);
    this.modelo = model;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    AjaxLink dominiosDelegados = new AjaxLink("dominiosDelegados") {
        public void onClick(AjaxRequestTarget target) {
          send((IEventSink)
              getPage(), Broadcast.BREADTH, new EventoNavegacionAdministracion(target, EnumOperacionNavegacionPanel.ADMON_DELEGADO));
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (((AdministracionTenantVM)AdministracionTabPanel.this.modelo.getObject()).getCurrenTabOption() == EnumOperacionNavegacionPanel.ADMON_DELEGADO) {
            tag.put("class", "pf-c-tabs__item  pf-m-current");
          } else {
            tag.put("class", "pf-c-tabs__item");
          } 
        }
      };
    queue(new Component[] { (Component)dominiosDelegados });
    AjaxLink historial = new AjaxLink("historial") {
        public void onClick(AjaxRequestTarget target) {
          send((IEventSink)
              getPage(), Broadcast.BREADTH, new EventoNavegacionAdministracion(target, EnumOperacionNavegacionPanel.HISTORIAL));
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (((AdministracionTenantVM)AdministracionTabPanel.this.modelo.getObject()).getCurrenTabOption() == EnumOperacionNavegacionPanel.HISTORIAL) {
            tag.put("class", "pf-c-tabs__item  pf-m-current");
          } else {
            tag.put("class", "pf-c-tabs__item");
          } 
        }
      };
    queue(new Component[] { (Component)historial });
  }
  
  public static final AdministracionTabPanel getInstance(String id, IModel<AdministracionTenantVM> model) {
    return new AdministracionTabPanel(id, model);
  }
}
