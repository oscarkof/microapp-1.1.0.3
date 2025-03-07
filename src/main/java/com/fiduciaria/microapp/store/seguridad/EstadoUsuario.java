// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.seguridad;

import java.io.Serializable;

public enum EstadoUsuario implements Serializable {
   ACTIVO("A"),
   INACTIVO("I"),
   NO_DEFINIDO("NA");

   private final String nombre;

   private EstadoUsuario(String nombre) {
      this.nombre = nombre;
   }

   public String getNombre() {
      return this.nombre;
   }
}
