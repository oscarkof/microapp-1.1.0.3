// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.actividades.model;

import org.apache.wicket.util.io.IClusterable;

public class GestionActividadesEstados implements IClusterable {
   public GestionActividadesEstados() {
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GestionActividadesEstados)) {
         return false;
      } else {
         GestionActividadesEstados other = (GestionActividadesEstados)o;
         return other.canEqual(this);
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof GestionActividadesEstados;
   }

   public int hashCode() {
      int result = 1;
      return 1;
   }

   public String toString() {
      return "GestionActividadesEstados()";
   }
}
