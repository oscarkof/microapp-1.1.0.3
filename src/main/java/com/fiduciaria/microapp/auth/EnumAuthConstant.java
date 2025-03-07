// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.auth;

public enum EnumAuthConstant {
   UUID("tiket"),
   PROPAGATEDPASS("token");

   private final String name;

   private EnumAuthConstant(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }
}
