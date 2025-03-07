// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model;

import org.apache.wicket.util.io.IClusterable;

public enum EnumRoleUsuario implements IClusterable {
   CARGA("Cargar"),
   AUTORIZAR("Autorizar"),
   AMBOS("Cargar y Autorizar");

   private final String nombre;

   private EnumRoleUsuario(String nombre) {
      this.nombre = nombre;
   }

   public String getNombre() {
      return this.nombre;
   }
}
