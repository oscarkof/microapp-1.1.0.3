// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.gui.solicitud;

import java.io.Serializable;

public enum TipoFormulario implements Serializable {
   PREINGRESODATOSUSUARIO("Preingreso Datos de Usuario"),
   SOPORTETECNICO("Soporte T\u00e9cnico");

   private final String nombre;

   private TipoFormulario(String nombre) {
      this.nombre = nombre;
   }

   public String getNombre() {
      return this.nombre;
   }
}
