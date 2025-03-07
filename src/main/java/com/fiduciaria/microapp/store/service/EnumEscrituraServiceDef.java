// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.service;

public enum EnumEscrituraServiceDef {
   POST_ESCRITURA_CONDICIONES_MANEJO("/autogestion/escritura/condiciones/firma");

   private final String url;

   private EnumEscrituraServiceDef(String url) {
      this.url = url;
   }

   public String getUrl() {
      return this.url;
   }
}
