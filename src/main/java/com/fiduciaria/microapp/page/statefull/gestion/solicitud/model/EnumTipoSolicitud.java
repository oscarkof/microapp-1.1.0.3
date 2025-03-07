// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud.model;

public enum EnumTipoSolicitud {
   REGISTRO_CLIENTE("Registro cliente"),
   REGISTRO_FIDUCIARIA("Registro fiduciaria"),
   REGISTRO_USUARIO("Registro usuario"),
   NO_USUARIO_CLAVE("No he recibido mi Usuario / clave"),
   NO_RECIBIDO_MANUAL_FIDUSAP("No he recibido manual FIDUSAP"),
   PROBLEMAS_EN_INGRESO("Problemas con Ingreso"),
   PROBLEMAS_CON_TOKEN("Problemas con Token digital"),
   BLOQUEO_DESBLOQEUO_CLAVE("Bloqueo/desbloqueo Clave"),
   PROBLEMAS_CON_CARGUE("Problemas con el cargue"),
   PROBLEMAS_CON_AUTORIZACION("Problemas con la autorizaci\u00f3n"),
   SOPORTE_ESPECIALIZADO("Soporte Especializado"),
   CAMBIO_CLAVE_USUARIO("Cambio de Clave Usuario");

   private final String descripcion;

   private EnumTipoSolicitud(String descripcion) {
      this.descripcion = descripcion;
   }

   public String getDescripcion() {
      return this.descripcion;
   }
}
