// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.administracion.panel.historial;

import com.fiduciaria.microapp.store.model.rastro.RastroAccionPojo;
import org.apache.wicket.model.LoadableDetachableModel;

public class DetachableHistorialRastroModel extends LoadableDetachableModel<RastroAccionPojo> {
   private final String id;
   private final RastroAccionPojo cobj;

   public DetachableHistorialRastroModel(RastroAccionPojo c) {
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
      } else if (obj instanceof DetachableHistorialRastroModel) {
         DetachableHistorialRastroModel other = (DetachableHistorialRastroModel)obj;
         return other.id == null ? this.id == null : other.id.equals(this.id);
      } else {
         return false;
      }
   }

   protected RastroAccionPojo load() {
      return this.cobj;
   }
}
