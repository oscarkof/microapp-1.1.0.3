// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.seguridad;

import org.apache.wicket.util.io.IClusterable;

public enum EnumTipoUsuario implements IClusterable {
   FUNCIONARIO("INT"),
   EXTERNOS("EXT"),
   NODEFINIDO((String)null);

   private final String nombre;

   private EnumTipoUsuario(String descripcion) {
      this.nombre = descripcion;
   }

   public String getNombre() {
      return this.nombre;
   }
}
