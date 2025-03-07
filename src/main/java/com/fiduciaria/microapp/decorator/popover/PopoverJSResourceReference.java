// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.decorator.popover;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class PopoverJSResourceReference extends JavaScriptResourceReference {
   public static final PopoverJSResourceReference getInstance() {
      return new PopoverJSResourceReference();
   }

   public PopoverJSResourceReference() {
      super(PopoverJSResourceReference.class, "popover.js");
   }
}
