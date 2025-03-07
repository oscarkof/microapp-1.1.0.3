// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.actividades;

import com.fiduciaria.microapp.page.statefull.negocio.condiciones.CondicionesFirmas;
import java.lang.reflect.InvocationTargetException;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public enum EnumOperacionNavegacionActividadesPanel {
   LINK_PREINGRESO(LinkPreIngresoMainPanel.class),
   RADICADOS(RadicadosMainPanel.class),
   CONSULTA(ConsultaRadicadosMainPanel.class),
   CONDICIONES_FIRMAS_NEGOCIO(CondicionesFirmas.class);

   private final Class<? extends Panel> operacionPanel;

   private EnumOperacionNavegacionActividadesPanel(Class operacion) {
      this.operacionPanel = operacion;
   }

   public Panel operacionNavegacion(String id, IModel<?> modelo) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      return (Panel)this.operacionPanel.getConstructor(String.class, IModel.class).newInstance(id, modelo);
   }
}
