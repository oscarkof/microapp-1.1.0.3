// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.administracion;

import com.fiduciaria.microapp.page.MainPage;
import com.fiduciaria.microapp.page.statefull.administracion.model.AdministracionTenantVM;
import com.fiduciaria.microapp.page.statefull.administracion.panel.AdminDelegadoPanel;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AdimistracionTenant extends MainPage {
   private final IModel<AdministracionTenantVM> modelo = new Model(new AdministracionTenantVM());

   public AdimistracionTenant(PageParameters parameters) {
      super(parameters);
   }

   protected void onInitialize() {
      super.onInitialize();
      if (((AdministracionTenantVM)this.modelo.getObject()).getCurrenTabOption() == null) {
         ((AdministracionTenantVM)this.modelo.getObject()).setCurrenTabOption(EnumOperacionNavegacionPanel.ADMON_DELEGADO);
      }

      this.queue(new Component[]{AdministracionTabPanel.getInstance("tabPanel", this.modelo)});
      this.initMainPanel();
   }

   private void initMainPanel() {
      try {
         this.addOrReplace(new Component[]{((AdministracionTenantVM)this.modelo.getObject()).getCurrenTabOption().operacionNavegacion("mainPanel", this.modelo)});
      } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException var2) {
         this.addOrReplace(new Component[]{AdminDelegadoPanel.instance("mainPanel", this.modelo)});
         Logger.getLogger(AdimistracionTenant.class.getName()).log(Level.SEVERE, (String)null, var2);
      }

   }

   public void onEvent(IEvent<?> event) {
      super.onEvent(event);
      if (event.getPayload() instanceof EventoNavegacionAdministracion) {
         EventoNavegacionAdministracion msg = (EventoNavegacionAdministracion)event.getPayload();
         ((AdministracionTenantVM)this.modelo.getObject()).setCurrenTabOption(msg.getPayload());
         this.initMainPanel();
         msg.getRequest().add(new Component[]{this.getPage()});
      }

   }
}
