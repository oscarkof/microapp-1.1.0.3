// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.usuario;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.io.IClusterable;

public class EventoNavegacionGestionUsuario implements IClusterable {
   private final AjaxRequestTarget request;
   private final EnumOperacionNavegacionPanel payload;

   public EventoNavegacionGestionUsuario(AjaxRequestTarget request, EnumOperacionNavegacionPanel payload) {
      this.request = request;
      this.payload = payload;
   }

   public AjaxRequestTarget getRequest() {
      return this.request;
   }

   public EnumOperacionNavegacionPanel getPayload() {
      return this.payload;
   }
}
