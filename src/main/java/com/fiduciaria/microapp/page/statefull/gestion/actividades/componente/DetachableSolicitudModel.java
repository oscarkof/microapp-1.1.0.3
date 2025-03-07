// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.actividades.componente;

import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojo;
import java.math.BigDecimal;
import org.apache.wicket.model.LoadableDetachableModel;

public class DetachableSolicitudModel extends LoadableDetachableModel<SolicitudPojo> {
   private final BigDecimal id;
   private final SolicitudPojo solici;

   public DetachableSolicitudModel(SolicitudPojo c) {
      this.solici = c;
      this.id = c.getRadicadoId();
   }

   public int hashCode() {
      return this.id.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (obj instanceof DetachableSolicitudModel) {
         DetachableSolicitudModel other = (DetachableSolicitudModel)obj;
         return other.id == this.id;
      } else {
         return false;
      }
   }

   protected SolicitudPojo load() {
      return this.solici;
   }
}
