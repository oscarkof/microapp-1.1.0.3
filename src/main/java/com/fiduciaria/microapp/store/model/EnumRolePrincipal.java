// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model;

import org.apache.wicket.util.io.IClusterable;

public enum EnumRolePrincipal implements IClusterable {
   ADMINISTRADORDELEGADO("Administrador Negocio"),
   USUARIO_NEGOCIO("Usuario Negocio");

   private final String nombre;

   private EnumRolePrincipal(String nombre) {
      this.nombre = nombre;
   }

   public String getNombre() {
      return this.nombre;
   }
}
