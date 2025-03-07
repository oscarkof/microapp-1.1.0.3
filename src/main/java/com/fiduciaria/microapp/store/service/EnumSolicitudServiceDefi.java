// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.service;

public enum EnumSolicitudServiceDefi {
   POST_NOTIF_LINK_PREINGRESO_DTO_USUARIO("/autogestion/notificacion/preingreso/link"),
   GET_TPL_DEFAULTS("/autogestion/tpl/preingreso/link"),
   POST_LISTA_SOLICITUDES("/autogestion/solicitud/soporte/lista"),
   POST_CREAR_SOLICITUD("/autogestion/solicitud/soporte"),
   GET_TAREAS_X_SOLICITUD("/autogestion/solicitud/${idradicado}/tarea"),
   GET_TAREAS_X_USUARIO("/autogestion/solicitud/usuario/${username}/tarea"),
   POST_GUARDAR_TAREA("/autogestion/solicitud/tarea"),
   GET_ADJUNTOS_SOLICITUD("/autogestion/solicitud/soporte/${idsolicitud}/${consecutivo}/${detalle}");

   private final String url;

   private EnumSolicitudServiceDefi(String url) {
      this.url = url;
   }

   public String getUrl() {
      return this.url;
   }
}
