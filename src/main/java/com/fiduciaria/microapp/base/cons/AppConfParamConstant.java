// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.base.cons;

public class AppConfParamConstant {
   private static final String baseServiceDomain = "/services";
   private static byte[] keyEncry;
   private static String conftoken;
   private static String urltorestserver;
   private static String uritotrustkeystore;
   private static String trustkeystorepass;
   private static String trustkeystoretype;

   public AppConfParamConstant() {
   }

   public static synchronized String getConftoken() {
      return conftoken;
   }

   public static synchronized void setConftoken(String conftoken) {
      AppConfParamConstant.conftoken = conftoken;
   }

   public static synchronized String getUrltorestserver() {
      return urltorestserver;
   }

   public static synchronized void setUrltorestserver(String urltorestserver) {
      AppConfParamConstant.urltorestserver = urltorestserver;
   }

   public static synchronized String getUritotrustkeystore() {
      return uritotrustkeystore;
   }

   public static synchronized void setUritotrustkeystore(String uritotrustkeystore) {
      AppConfParamConstant.uritotrustkeystore = uritotrustkeystore;
   }

   public static synchronized String getTrustkeystorepass() {
      return trustkeystorepass;
   }

   public static synchronized void setTrustkeystorepass(String trustkeystorepass) {
      AppConfParamConstant.trustkeystorepass = trustkeystorepass;
   }

   public static synchronized String getTrustkeystoretype() {
      return trustkeystoretype;
   }

   public static synchronized void setTrustkeystoretype(String trustkeystoretype) {
      AppConfParamConstant.trustkeystoretype = trustkeystoretype;
   }

   public static synchronized String getBaseServiceDomain() {
      return "/services";
   }

   public static synchronized byte[] getKeyEncry() {
      return keyEncry;
   }

   public static synchronized void setKeyEncry(byte[] keyEncry) {
      AppConfParamConstant.keyEncry = keyEncry;
   }
}
