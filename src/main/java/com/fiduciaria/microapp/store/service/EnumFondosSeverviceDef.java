// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.service;

public enum EnumFondosSeverviceDef {
   GET_REFERENCIAS_X_USUARIO("/fondos/referenciaunica/user/{userid}/get");

   private final String url;

   private EnumFondosSeverviceDef(String url) {
      this.url = url;
   }

   public String getUrl() {
      return this.url;
   }
}
