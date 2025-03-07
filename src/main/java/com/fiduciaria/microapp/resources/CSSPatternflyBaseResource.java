// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.resources;

import org.apache.wicket.request.resource.CssResourceReference;

public class CSSPatternflyBaseResource extends CssResourceReference {
   public static final CSSPatternflyBaseResource getInstance() {
      return new CSSPatternflyBaseResource();
   }

   CSSPatternflyBaseResource() {
      super(CSSPatternflyBaseResource.class, "/css/patternfly-base.css");
   }
}
