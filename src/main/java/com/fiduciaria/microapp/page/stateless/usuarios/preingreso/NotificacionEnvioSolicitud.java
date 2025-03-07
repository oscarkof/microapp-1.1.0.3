// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.stateless.usuarios.preingreso;

import com.fiduciaria.microapp.page.stateless.BaseStatelessPage;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class NotificacionEnvioSolicitud extends BaseStatelessPage {
   final PageParameters parametros;

   public NotificacionEnvioSolicitud(PageParameters parameters) {
      super(parameters);
      this.parametros = parameters;
   }

   protected void onInitialize() {
      super.onInitialize();
      WebMarkupContainer notificacion = new WebMarkupContainer("notificacion");
      notificacion.setOutputMarkupPlaceholderTag(true);
      notificacion.setVisible(this.parametros.get("tipomensaje").toString().toLowerCase().equals("notificacion"));
      this.queue(new Component[]{notificacion});
      WebMarkupContainer error = new WebMarkupContainer("error");
      error.setOutputMarkupPlaceholderTag(true);
      error.setVisible(this.parametros.get("tipomensaje").toString().toLowerCase().equals("error"));
      this.queue(new Component[]{error});
      Label respuesta = new Label("respuesta", Model.of(this.parametros.get("numerosolicitud")));
      this.queue(new Component[]{respuesta});
   }
}
