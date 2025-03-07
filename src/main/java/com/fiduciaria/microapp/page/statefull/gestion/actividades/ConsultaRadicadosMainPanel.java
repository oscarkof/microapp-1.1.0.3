// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.actividades;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.page.statefull.gestion.actividades.model.GestionActividadesVM;
import org.apache.wicket.model.IModel;

public class ConsultaRadicadosMainPanel extends BasePanel {
   public ConsultaRadicadosMainPanel(String id, IModel<?> model) {
      super(id, model);
   }

   public static final ConsultaRadicadosMainPanel getInstance(String id, IModel<GestionActividadesVM> model) {
      return new ConsultaRadicadosMainPanel(id, model);
   }
}
