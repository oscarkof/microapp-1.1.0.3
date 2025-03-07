// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.usuario;

import com.fiduciaria.microapp.page.MainPage;
import com.fiduciaria.microapp.page.statefull.gestion.usuario.model.GestionUsuariosVM;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class GestionUsuario extends MainPage {
   private final IModel<GestionUsuariosVM> modelo;

   public GestionUsuario(PageParameters parameters) {
      super(parameters);
      this.setOutputMarkupPlaceholderTag(true);
      this.modelo = new Model(new GestionUsuariosVM());
   }

   protected void onInitialize() {
      super.onInitialize();
      if (((GestionUsuariosVM)this.modelo.getObject()).getCurrenTabOption() == null) {
         ((GestionUsuariosVM)this.modelo.getObject()).setCurrenTabOption(EnumOperacionNavegacionPanel.USUARIO);
      }

      this.queue(new Component[]{GestionUsuarioTabPanel.getInstance("tabPanel", this.modelo)});
      this.initMainPanel();
   }

   private void initMainPanel() {
      try {
         this.addOrReplace(new Component[]{((GestionUsuariosVM)this.modelo.getObject()).getCurrenTabOption().operacionNavegacion("mainPanel", this.modelo)});
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException var2) {
         this.addOrReplace(new Component[]{EnrolamientoMainPanel.getInstance("mainPanel", this.modelo)});
         Logger.getLogger(GestionUsuario.class.getName()).log(Level.SEVERE, (String)null, var2);
      }

   }

   public void onEvent(IEvent<?> event) {
      super.onEvent(event);
      if (event.getPayload() instanceof EventoNavegacionGestionUsuario) {
         EventoNavegacionGestionUsuario msg = (EventoNavegacionGestionUsuario)event.getPayload();
         ((GestionUsuariosVM)this.modelo.getObject()).setCurrenTabOption(msg.getPayload());
         this.initMainPanel();
         msg.getRequest().add(new Component[]{this.getPage()});
      }

   }
}
