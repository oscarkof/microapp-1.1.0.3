// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.rastro;

public enum EnumCampoTraceColumn {
   ACCION_UUID("Identificador \u00fanico", "accionUuid"),
   ID("Identificador", "id"),
   ACCION("Actividad", "accion"),
   TIPO_ACCION("Tipo Actividad", "tipoAccion"),
   ID_ACCION("Afecta a", "idAccion"),
   ID_RELACIONADO("", "idRelacionado"),
   TIPO_RELACIONADO("", "tipoRelacionado"),
   USUARIO_SESS("Usuario ejecuta", "usuarioSess"),
   ESTAMPA_TIEMPO("Fecha Hora", "estampaTiempo"),
   ANTERIOR("Vlr Anterior", "anterior"),
   ACTUAL("Vlr Actual", "actual"),
   MSG("Resultado", "msg"),
   SECUENCIAL_ACC("Secuencial", "secuencialAcc");

   private final String tituloTabla;
   private final String nombreCampoBean;

   private EnumCampoTraceColumn(String tituloTabla, String nombreCampoBean) {
      this.tituloTabla = tituloTabla;
      this.nombreCampoBean = nombreCampoBean;
   }

   public String getTituloTabla() {
      return this.tituloTabla;
   }

   public String getNombreCampoBean() {
      return this.nombreCampoBean;
   }
}
