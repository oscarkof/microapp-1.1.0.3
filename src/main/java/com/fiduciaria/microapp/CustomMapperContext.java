// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp;

import org.apache.wicket.DefaultMapperContext;
import org.apache.wicket.request.resource.ResourceReferenceRegistry;

public class CustomMapperContext extends DefaultMapperContext {
   public CustomMapperContext() {
   }

   public ResourceReferenceRegistry getResourceReferenceRegistry() {
      return super.getResourceReferenceRegistry();
   }

   public String getBookmarkableIdentifier() {
      return "static";
   }

   public String getPageIdentifier() {
      return "index";
   }

   public String getNamespace() {
      return "microapp";
   }
}
