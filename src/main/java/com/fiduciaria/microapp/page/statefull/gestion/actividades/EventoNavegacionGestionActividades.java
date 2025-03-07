// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.actividades;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class EventoNavegacionGestionActividades {
   private final AjaxRequestTarget request;
   private final EnumOperacionNavegacionActividadesPanel payload;

   public EventoNavegacionGestionActividades(AjaxRequestTarget request, EnumOperacionNavegacionActividadesPanel payload) {
      this.request = request;
      this.payload = payload;
   }

   public AjaxRequestTarget getRequest() {
      return this.request;
   }

   public EnumOperacionNavegacionActividadesPanel getPayload() {
      return this.payload;
   }
}
