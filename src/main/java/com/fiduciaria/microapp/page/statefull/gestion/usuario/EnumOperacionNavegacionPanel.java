// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.usuario;

import com.fiduciaria.microapp.page.statefull.gestion.usuario.delegado.UsuarioDelegadoMainPanel;
import java.lang.reflect.InvocationTargetException;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public enum EnumOperacionNavegacionPanel {
   USUARIO(UsuarioMainPanel.class),
   USUARIO_DELEGADO(UsuarioDelegadoMainPanel.class),
   HISTORIAL(HistorialUsuariosMainPanel.class),
   PERMISOS(PermisosUsuarioDominio.class),
   CREDENCIALES(CredencialesMainPanel.class);

   private final Class<? extends Panel> operacionPanel;

   private EnumOperacionNavegacionPanel(Class operacion) {
      this.operacionPanel = operacion;
   }

   public Panel operacionNavegacion(String id, IModel<?> modelo) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      return (Panel)this.operacionPanel.getConstructor(String.class, IModel.class).newInstance(id, modelo);
   }
}
