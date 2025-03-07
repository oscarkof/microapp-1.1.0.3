// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.resources;

import org.apache.wicket.request.resource.CssResourceReference;

public class CSSGridLayoutResource extends CssResourceReference {
   public static final CSSGridLayoutResource getInstance() {
      return new CSSGridLayoutResource();
   }

   CSSGridLayoutResource() {
      super(CSSGridLayoutResource.class, "/css/layouts/Grid/grid.css");
   }
}
