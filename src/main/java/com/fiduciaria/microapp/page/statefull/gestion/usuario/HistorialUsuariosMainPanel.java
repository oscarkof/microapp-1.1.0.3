// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.usuario;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.page.statefull.gestion.usuario.componente.TablaHistorialUsuarios;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

public class HistorialUsuariosMainPanel extends BasePanel {
   public HistorialUsuariosMainPanel(String id, IModel<?> model) {
      super(id, model);
   }

   protected void onInitialize() {
      super.onInitialize();
      this.initTablaHistorialUsuarios();
   }

   private void initTablaHistorialUsuarios() {
      this.queue(new Component[]{new TablaHistorialUsuarios("tablaHistorialUsuarios")});
   }
}
