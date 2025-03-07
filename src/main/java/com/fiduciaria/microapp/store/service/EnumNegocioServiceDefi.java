// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.service;

public enum EnumNegocioServiceDefi {
   GET_USUARIOS_REFERENCIA_UNICA("/autogestion/alfa/referenciaunica/${idref}/usuario/${tipousuario}"),
   GET_REFERENCIA_UNICA("/autogestion/alfa/referenciaunica"),
   GET_REFERENCIA_UNICA_X_USAURIO_CLIENTE("/autogestion/alfa/referenciaunica/${usuario}/cliente"),
   GET_FIDEICOMISO_X_USUARIO("/autogestion/fidusap/usuario/${usuario}/fideicomiso"),
   POST_NEGOCIO_GENERAL("/autogestion/negocio/general"),
   POST_ALERTA_CLIENTE("/autogestion/alfa/referencia/${numeroreferencia}/${perfilusuario}/alerta/cliente"),
   GET_NEGOCIO_DOMINIO("/autogestion/negocio/${dominioid}/dominio");

   private final String url;

   private EnumNegocioServiceDefi(String url) {
      this.url = url;
   }

   public String getUrl() {
      return this.url;
   }
}
