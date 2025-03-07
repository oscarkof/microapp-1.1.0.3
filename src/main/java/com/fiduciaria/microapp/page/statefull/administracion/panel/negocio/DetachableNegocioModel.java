// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.administracion.panel.negocio;

import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import org.apache.wicket.model.LoadableDetachableModel;

public class DetachableNegocioModel extends LoadableDetachableModel<NegocioPojo> {
   private final NegocioPojo negocio;
   private final String id;

   public DetachableNegocioModel(NegocioPojo negocio) {
      this.negocio = negocio;
      this.id = negocio.getCodigoNegocio();
   }

   protected NegocioPojo load() {
      return this.negocio;
   }

   public int hashCode() {
      return this.id.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (obj instanceof DetachableNegocioModel) {
         DetachableNegocioModel other = (DetachableNegocioModel)obj;
         return other.id == null ? this.id == null : other.id.equals(this.id);
      } else {
         return false;
      }
   }
}
