package com.fiduciaria.microapp.page.statefull.gestion.usuario;

import com.fiduciaria.microapp.page.statefull.gestion.usuario.model.GestionUsuariosVM;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class GestionUsuarioTabPanel extends Panel {
  private final IModel<GestionUsuariosVM> modelo;
  
  public GestionUsuarioTabPanel(String id, IModel<GestionUsuariosVM> model) {
    super(id, model);
    this.modelo = model;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    AjaxLink permisos = new AjaxLink("permisos") {
        public void onClick(AjaxRequestTarget target) {
          send((IEventSink)
              getPage(), Broadcast.BREADTH, new EventoNavegacionGestionUsuario(target, EnumOperacionNavegacionPanel.PERMISOS));
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (((GestionUsuariosVM)GestionUsuarioTabPanel.this.modelo.getObject()).getCurrenTabOption() == EnumOperacionNavegacionPanel.PERMISOS) {
            tag.put("class", "pf-c-tabs__item  pf-m-current");
          } else {
            tag.put("class", "pf-c-tabs__item");
          } 
        }
        
        protected void onConfigure() {
          super.onConfigure();
          setVisible(AuthenticatedWebSession.get().getRoles().hasRole("UCA"));
        }
      };
    queue(new Component[] { (Component)permisos });
    AjaxLink historial = new AjaxLink("historial") {
        public void onClick(AjaxRequestTarget target) {
          send((IEventSink)
              getPage(), Broadcast.BREADTH, new EventoNavegacionGestionUsuario(target, EnumOperacionNavegacionPanel.HISTORIAL));
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (((GestionUsuariosVM)GestionUsuarioTabPanel.this.modelo.getObject()).getCurrenTabOption() == EnumOperacionNavegacionPanel.HISTORIAL) {
            tag.put("class", "pf-c-tabs__item  pf-m-current");
          } else {
            tag.put("class", "pf-c-tabs__item");
          } 
        }
      };
    queue(new Component[] { (Component)historial });
    AjaxLink credenciales = new AjaxLink("credenciales") {
        public void onClick(AjaxRequestTarget target) {
          send((IEventSink)
              getPage(), Broadcast.BREADTH, new EventoNavegacionGestionUsuario(target, EnumOperacionNavegacionPanel.CREDENCIALES));
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (((GestionUsuariosVM)GestionUsuarioTabPanel.this.modelo.getObject()).getCurrenTabOption() == EnumOperacionNavegacionPanel.CREDENCIALES) {
            tag.put("class", "pf-c-tabs__item  pf-m-current");
          } else {
            tag.put("class", "pf-c-tabs__item");
          } 
        }
      };
    queue(new Component[] { (Component)credenciales });
    AjaxLink usuariosDelegados = new AjaxLink("usuariosDelegados") {
        public void onClick(AjaxRequestTarget target) {
          send((IEventSink)
              getPage(), Broadcast.BREADTH, new EventoNavegacionGestionUsuario(target, EnumOperacionNavegacionPanel.USUARIO_DELEGADO));
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (((GestionUsuariosVM)GestionUsuarioTabPanel.this.modelo.getObject()).getCurrenTabOption() == EnumOperacionNavegacionPanel.USUARIO_DELEGADO) {
            tag.put("class", "pf-c-tabs__item  pf-m-current");
          } else {
            tag.put("class", "pf-c-tabs__item");
          } 
        }
      };
    queue(new Component[] { (Component)usuariosDelegados });
    usuariosDelegados.setVisible(false);
    AjaxLink usuarios = new AjaxLink("usuarios") {
        public void onClick(AjaxRequestTarget target) {
          send((IEventSink)
              getPage(), Broadcast.BREADTH, new EventoNavegacionGestionUsuario(target, EnumOperacionNavegacionPanel.USUARIO));
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (((GestionUsuariosVM)GestionUsuarioTabPanel.this.modelo.getObject()).getCurrenTabOption() == EnumOperacionNavegacionPanel.USUARIO) {
            tag.put("class", "pf-c-tabs__item  pf-m-current");
          } else {
            tag.put("class", "pf-c-tabs__item");
          } 
        }
      };
    queue(new Component[] { (Component)usuarios });
  }
  
  public static final GestionUsuarioTabPanel getInstance(String id, IModel<GestionUsuariosVM> model) {
    return new GestionUsuarioTabPanel(id, model);
  }
}
