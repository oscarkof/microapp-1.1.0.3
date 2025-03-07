// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud;

import com.fiduciaria.microapp.page.statefull.gestion.solicitud.seguimiento.SeguimientoSolicitudMainPanel;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.TareaSolicitudMainPanel;
import java.lang.reflect.InvocationTargetException;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public enum EnumOperacionNavegacionSolicitudesPanel {
   PREINGRESO(PreIngresoMainPanel.class),
   SOPORTE(SoporteMainPanel.class),
   CONSULTA(SeguimientoSolicitudMainPanel.class),
   TAREA(TareaSolicitudMainPanel.class);

   private final Class<? extends Panel> operacionPanel;

   private EnumOperacionNavegacionSolicitudesPanel(Class operacion) {
      this.operacionPanel = operacion;
   }

   public Panel operacionNavegacion(String id, IModel<?> modelo) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      return (Panel)this.operacionPanel.getConstructor(String.class, IModel.class).newInstance(id, modelo);
   }
}
