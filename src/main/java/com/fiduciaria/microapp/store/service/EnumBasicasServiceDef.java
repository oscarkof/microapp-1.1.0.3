// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.service;

public enum EnumBasicasServiceDef {
   GET_TIPO_DOCUMENTOS("/basicas/documento/tipo");

   private final String url;

   private EnumBasicasServiceDef(String url) {
      this.url = url;
   }

   public String getUrl() {
      return this.url;
   }
}
