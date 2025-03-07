// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class EventoNavegacionGestionSolicitudes {
   private final AjaxRequestTarget request;
   private final EnumOperacionNavegacionSolicitudesPanel payload;

   public EventoNavegacionGestionSolicitudes(AjaxRequestTarget request, EnumOperacionNavegacionSolicitudesPanel payload) {
      this.request = request;
      this.payload = payload;
   }

   public AjaxRequestTarget getRequest() {
      return this.request;
   }

   public EnumOperacionNavegacionSolicitudesPanel getPayload() {
      return this.payload;
   }
}
