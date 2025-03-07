// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.administracion.panel.negocio;

import java.io.Serializable;

public enum EnumCampoNegocio implements Serializable {
   TIPO_NEGOCIO("Tipo Negocio", "tipoNegocio"),
   IDENTIFICADOR("C\u00f3d. Negocio", "idRefNegocio"),
   DESCRIPCION("Descripci\u00f3n", "descripcion"),
   ESTADO("Estado", "estado");

   private final String titleTitle;
   private final String fieldName;

   private EnumCampoNegocio(String fieldTitle, String fielName) {
      this.titleTitle = fieldTitle;
      this.fieldName = fielName;
   }

   public String getFieldTitle() {
      return this.titleTitle;
   }

   public String getFieldName() {
      return this.fieldName;
   }
}
