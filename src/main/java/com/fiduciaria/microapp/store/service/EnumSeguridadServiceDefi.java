// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.service;

public enum EnumSeguridadServiceDefi {
   GET_USUARIO_FILTRADOS("/autogestion/usuario/${filtrousuario}/${tipo}/list"),
   GET_USUARIO_DOMINIO_FILTRADOS("/autogestion/usuario/${dominio}/list"),
   POST_USUARIO_FILTRADOS("/autogestion/usuario/list"),
   POST_USUARIO_GUARDAR("/autogestion/usuario/${filtrousuario}/list"),
   POST_USUARIO_CREAR("/gatewayaso/credentials-life-cycle/v0/company-password/enrollments"),
   POST_USUARIO_DESBLOQUEAR("/gatewayaso/credentials-life-cycle/v0/company-password/unlock"),
   POST_USUARIO_BLOQUEAR("/gatewayaso/credentials-life-cycle/v0/company-password/lock"),
   POST_USUARIO_RESTABLECER_PASS("/gatewayaso/credentials-life-cycle/v0/company-password/resets"),
   POST_USUARIO_ENROLAR_OTP("/gatewayaso/nextgen/softwareTokens/v0/softwareTokens"),
   POST_USUARIO_CAMBIAR_PASS("/gatewayaso/credentials-life-cycle/v0/company-password/changes"),
   GET_SARLAF("/services/gatewayaso/sarlaft/V01/sarlaft?$filter=(documentType==${doctype};documentId==${docid};checkDigit==${dv})");

   private final String url;

   private EnumSeguridadServiceDefi(String url) {
      this.url = url;
   }

   public String getUrl() {
      return this.url;
   }
}
