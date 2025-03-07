// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.base;

public enum NombreAccionesEnum {
   DOMINIO("Dominio", ""),
   CREAR_DOMINIO("Dominio", "Crear dominio delegado nuevo"),
   ADD_ADMON_DOMINIO("Dominio", "Agrega administrador a dominio delegado"),
   ADD_NEGOCIO_DOMINIO("Dominio", "Agrega Negocio a dominio dominio delegado"),
   DOMINIO_ADD_ADMON("Dominio", "Agregar usuario administrador"),
   USUARIO("Usuario", ""),
   CAMBIAR_PASS("Usuario", "Cambiar Contrase\u00f1a"),
   RESTABLECER_PASSS("Usuario", "Restrablecer Constrase\u00f1a"),
   CREAR_USUARIO("Usuario", "Crear Usuario"),
   BLOQUEAR_USUARIO("Usuario", "Bloquear Usuario"),
   DESBLOQUEAR_USUARIO("Usuario", "Desbloquear Usuario"),
   ENROLAR_SOFTOKEN("Usuario", "Enrolar Usuario OTP"),
   NUEVA_TAREA("Tarea", "Crear Tarea"),
   SOLICITUD_PREINGRESO("Solicitud", "Nueva solicitud preingreso datos"),
   CREAR_USUARIO_X_TAREA("Usuario", "Crear usuario por tarea");

   private final String accion;
   private final String tipoAccion;

   private NombreAccionesEnum(String accion, String tipoAccion) {
      this.accion = accion;
      this.tipoAccion = tipoAccion;
   }

   public String getAccion() {
      return this.accion;
   }

   public String getTipoAccion() {
      return this.tipoAccion;
   }
}
