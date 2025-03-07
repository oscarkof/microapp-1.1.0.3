// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.base;

import org.apache.wicket.util.io.IClusterable;

public class BaseParameters implements IClusterable {
   public static String APP_URL_BASE;

   private BaseParameters() {
   }

   public static synchronized void setAPP_URL_BASE(String APP_URL_BASE) {
      BaseParameters.APP_URL_BASE = APP_URL_BASE;
   }

   public static synchronized String getAPP_URL_BASE() {
      return APP_URL_BASE;
   }
}
