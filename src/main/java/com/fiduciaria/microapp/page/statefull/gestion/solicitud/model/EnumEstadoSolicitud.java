// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud.model;

public enum EnumEstadoSolicitud {
   CERRADO_OK("Cerrada"),
   EN_PROCESO("En proceso"),
   EN_VALIDACION("En validacion"),
   PENDIENTE("Pendiente");

   private final String descripcion;

   private EnumEstadoSolicitud(String descripcion) {
      this.descripcion = descripcion;
   }

   public String getDescripcion() {
      return this.descripcion;
   }
}
