// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.administracion;

import com.fiduciaria.microapp.page.statefull.administracion.panel.AdminDelegadoPanel;
import com.fiduciaria.microapp.page.statefull.administracion.panel.historial.TablaHistorialRastroDominio;
import java.lang.reflect.InvocationTargetException;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public enum EnumOperacionNavegacionPanel {
   ADMON_DELEGADO(AdminDelegadoPanel.class),
   HISTORIAL(TablaHistorialRastroDominio.class);

   private final Class<? extends Panel> operacionPanel;

   private EnumOperacionNavegacionPanel(Class operacion) {
      this.operacionPanel = operacion;
   }

   public Panel operacionNavegacion(String id, IModel<?> modelo) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      return (Panel)this.operacionPanel.getConstructor(String.class, IModel.class).newInstance(id, modelo);
   }
}
