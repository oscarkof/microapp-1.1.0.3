// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.usuario.permisos;

public enum PerfilUsuarioAlfaEnum {
   SOLICITAR("Solicitar"),
   AUTORIZAR("Autorizar"),
   AMBOS("Autorizar y Solicitar");

   private final String nombre;

   private PerfilUsuarioAlfaEnum(String nombre) {
      this.nombre = nombre;
   }

   public String getNombre() {
      return this.nombre;
   }
}
