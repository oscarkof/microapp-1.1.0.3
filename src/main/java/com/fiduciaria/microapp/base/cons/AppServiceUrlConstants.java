// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.base.cons;

import org.apache.wicket.util.io.IClusterable;

public enum AppServiceUrlConstants implements IClusterable {
   CONF_SRV("/autogestion/${uuid}/configuracion");

   private final String desc;

   private AppServiceUrlConstants(String desc) {
      this.desc = desc;
   }

   public String getDesc() {
      return this.desc;
   }
}
