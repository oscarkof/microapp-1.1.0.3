// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.negocio;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.wicket.util.io.IClusterable;

public enum EnumTipoNegocio implements IClusterable {
   FIDEICOMISO("FIDEICOMISO"),
   REFERENCIA_UNICA_ALPHA("REFERENCIA UNICA");

   private final String nombre;

   private EnumTipoNegocio(String nombre) {
      this.nombre = nombre;
   }

   public String getNombre() {
      return this.nombre;
   }

   public static EnumTipoNegocio desdeNombre(String nombre) {
      Map<String, EnumTipoNegocio> mapVals = new HashMap();
      Arrays.asList(values()).forEach((action) -> {
         EnumTipoNegocio var10000 = (EnumTipoNegocio)mapVals.put(action.getNombre(), action);
      });
      return (EnumTipoNegocio)mapVals.get(nombre);
   }
}
