// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.resources;

import org.apache.wicket.request.resource.CssResourceReference;

public class CSSPageResource extends CssResourceReference {
   public static final CSSPageResource getInstance() {
      return new CSSPageResource();
   }

   CSSPageResource() {
      super(CSSPageResource.class, "/css/components/Page/page.css");
   }
}
