// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.dominio.historial;

public enum HistorialDominioColumnEnum {
   IDENTIFICADOR("identificador", "Id."),
   ACCION("accion", "Acci\u00f3n"),
   DOMINIO("dominio", "Nombre dominio"),
   ATRIBUTO("atributo", "Atributo"),
   VALOR_ATRIBUTO("valorAtributo", "Valor Atributo"),
   VALOR_ATRIBUTO_ANT("valorAtributoAnt", "Valor Anterior"),
   ESTAMPA_TIEMPO("estampaTiempo", "Fecha Hora"),
   USUARIO_CREA("usuarioCrea", "Usuario ");

   private final String nombre;
   private final String titulo;

   private HistorialDominioColumnEnum(String nombre, String titulo) {
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
