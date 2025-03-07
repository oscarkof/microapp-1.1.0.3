// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.service;

public enum EnumRastroServiceDef {
   POST_RASTRO("/autogestion/rastro"),
   POST_RASTRO_LISTADO("/autogestion/rastro/lista");

   private final String url;

   private EnumRastroServiceDef(String url) {
      this.url = url;
   }

   public String getUrl() {
      return this.url;
   }
}
