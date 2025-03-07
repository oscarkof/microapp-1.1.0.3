// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.actividades;

import com.fiduciaria.microapp.page.MainPage;
import com.fiduciaria.microapp.page.statefull.gestion.actividades.model.GestionActividadesVM;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class GestionActividades extends MainPage {
   private final IModel<GestionActividadesVM> modelo = new Model(new GestionActividadesVM());

   public GestionActividades(PageParameters parameters) {
      super(parameters);
   }

   protected void onInitialize() {
      super.onInitialize();
      this.queue(new Component[]{GestionActividadesTabPanel.getInstance("tabPanel", this.modelo)});
      if (((GestionActividadesVM)this.modelo.getObject()).getCurrenTabOption() == null) {
         ((GestionActividadesVM)this.modelo.getObject()).setCurrenTabOption(EnumOperacionNavegacionActividadesPanel.LINK_PREINGRESO);
      }

      this.initMainPanel();
   }

   private void initMainPanel() {
      try {
         this.addOrReplace(new Component[]{((GestionActividadesVM)this.modelo.getObject()).getCurrenTabOption().operacionNavegacion("mainPanel", this.modelo)});
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException var2) {
         this.addOrReplace(new Component[]{LinkPreIngresoMainPanel.getInstance("mainPanel", this.modelo)});
         Logger.getLogger(GestionActividades.class.getName()).log(Level.SEVERE, (String)null, var2);
      }

   }

   public void onEvent(IEvent<?> event) {
      super.onEvent(event);
      if (event.getPayload() instanceof EventoNavegacionGestionActividades) {
         EventoNavegacionGestionActividades msg = (EventoNavegacionGestionActividades)event.getPayload();
         ((GestionActividadesVM)this.modelo.getObject()).setCurrenTabOption(msg.getPayload());
         this.initMainPanel();
         msg.getRequest().add(new Component[]{this.getPage()});
      }

   }
}
