// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.usuario.componente;

import com.fiduciaria.microapp.store.model.rastro.RastroAccionPojo;
import org.apache.wicket.model.LoadableDetachableModel;

public class DetachableHistorialUsuarioModel extends LoadableDetachableModel<RastroAccionPojo> {
   private final String id;
   private final RastroAccionPojo cobj;

   public DetachableHistorialUsuarioModel(RastroAccionPojo c) {
      this.cobj = c;
      this.id = c.getId();
   }

   public int hashCode() {
      return Long.valueOf(this.id).hashCode();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (obj instanceof DetachableHistorialUsuarioModel) {
         DetachableHistorialUsuarioModel other = (DetachableHistorialUsuarioModel)obj;
         return other.id == null ? this.id == null : other.id.equals(this.id);
      } else {
         return false;
      }
   }

   protected RastroAccionPojo load() {
      return this.cobj;
   }
}
