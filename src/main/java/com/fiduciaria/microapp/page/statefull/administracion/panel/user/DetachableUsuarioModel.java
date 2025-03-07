// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.administracion.panel.user;

import com.fiduciaria.microapp.store.model.usuario.UsuarioPojo;
import org.apache.wicket.model.LoadableDetachableModel;

public class DetachableUsuarioModel extends LoadableDetachableModel<UsuarioPojo> {
   private final UsuarioPojo usuario;
   private final String id;

   public DetachableUsuarioModel(UsuarioPojo usr) {
      this.usuario = usr;
      this.id = usr.getIdPrincipal();
   }

   protected UsuarioPojo load() {
      return this.usuario;
   }

   public int hashCode() {
      return this.id.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (obj instanceof DetachableUsuarioModel) {
         DetachableUsuarioModel other = (DetachableUsuarioModel)obj;
         return other.id == null ? this.id == null : other.id.equals(this.id);
      } else {
         return false;
      }
   }
}
