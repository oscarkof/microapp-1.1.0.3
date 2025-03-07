// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.service;

public enum EnumDominioServiceDefi {
   POST_DOMINIO_DELEGADO("/autogestion/dominio/delegado"),
   POST_DOMINIO_DELEGADO_LISTA("/autogestion/dominio/delegados"),
   POST_HISTORIAL_DOMINIO_LISTA("/autogestion/dominio/historial"),
   GET_DOMINIO_DELEGADO("/autogestion/dominio/delegado/${iddominio}"),
   POST_ADD_USUARIO_DOMINIO_DELEGADO("/autogestion/dominio/delegado/${iddominio}/${tipousuario}");

   private final String url;

   private EnumDominioServiceDefi(String url) {
      this.url = url;
   }

   public String getUrl() {
      return this.url;
   }
}
