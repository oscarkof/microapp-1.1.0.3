// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.actividades;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.page.statefull.gestion.actividades.componente.TablaRadicados;
import com.fiduciaria.microapp.page.statefull.gestion.actividades.model.GestionActividadesVM;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

public class RadicadosMainPanel extends BasePanel {
   public RadicadosMainPanel(String id, IModel<?> model) {
      super(id, model);
   }

   protected void onInitialize() {
      super.onInitialize();
      this.queue(new Component[]{new TablaRadicados("tablaRadicadosActividades")});
   }

   public static final RadicadosMainPanel getInstance(String id, IModel<GestionActividadesVM> model) {
      return new RadicadosMainPanel(id, model);
   }
}
