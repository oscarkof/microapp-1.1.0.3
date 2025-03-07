// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud;

import com.fiduciaria.microapp.page.MainPage;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.GestionSolicitudesVM;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class GestionSolicitudes extends MainPage {
   private final IModel<GestionSolicitudesVM> modelo;

   public GestionSolicitudes(PageParameters parameters) {
      super(parameters);
      this.setOutputMarkupPlaceholderTag(true);
      GestionSolicitudesVM modelObj = new GestionSolicitudesVM();
      this.modelo = new Model(modelObj);
      if (((GestionSolicitudesVM)this.modelo.getObject()).getCurrenTabOption() == null) {
         ((GestionSolicitudesVM)this.modelo.getObject()).setCurrenTabOption(EnumOperacionNavegacionSolicitudesPanel.PREINGRESO);
      }

   }

   protected void onInitialize() {
      super.onInitialize();
      this.queue(new Component[]{SolicitudesTabPanel.getInstance("tabPanel", this.modelo)});
      this.initMainPanel();
   }

   private void initMainPanel() {
      try {
         this.addOrReplace(new Component[]{((GestionSolicitudesVM)this.modelo.getObject()).getCurrenTabOption().operacionNavegacion("mainPanel", this.modelo)});
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException var2) {
         this.addOrReplace(new Component[]{PreIngresoMainPanel.getInstance("mainPanel", this.modelo)});
         Logger.getLogger(GestionSolicitudes.class.getName()).log(Level.SEVERE, (String)null, var2);
      }

   }

   public void onEvent(IEvent<?> event) {
      super.onEvent(event);
      if (event.getPayload() instanceof EventoNavegacionGestionSolicitudes) {
         EventoNavegacionGestionSolicitudes msg = (EventoNavegacionGestionSolicitudes)event.getPayload();
         ((GestionSolicitudesVM)this.modelo.getObject()).setCurrenTabOption(msg.getPayload());
         this.initMainPanel();
         msg.getRequest().add(new Component[]{this.getPage()});
      }

   }
}
