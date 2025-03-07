// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.negocio;

public enum NegocioColumnEnum {
   TIPO_NEGOCIO("tipoNegocio", "Tipo Negocio"),
   CODIGO_NEGOCIO("codigoNegocio", "C\u00f3digo Negocio"),
   DESCRIPCION_NEGOCIO("descripcionNegocio", "Descripcion"),
   TIPO_IDENTIFICACION("tipoIdentificacion", "Tipo Id."),
   IDENTIFICACION("identificacion", "Identificaci\u00f3n"),
   DIGITOS_VERIFICACION("digitosVerificacion", "Dig. Verif."),
   NOMBRES_RAZON_SOCIAL("nombresRazonSocial", "Nombre/ Raz\u00f3n Social"),
   ESTADO("estado", "Estado"),
   TIPOREFERENCIA("tiporeferencia", "Tipo Referencia");

   private final String nombre;
   private final String titulo;

   private NegocioColumnEnum(String nombre, String titulo) {
      this.nombre = nombre;
      this.titulo = titulo;
   }

   public String getNombre() {
      return this.nombre;
   }

   public String getTitulo() {
      return this.titulo;
   }
}
