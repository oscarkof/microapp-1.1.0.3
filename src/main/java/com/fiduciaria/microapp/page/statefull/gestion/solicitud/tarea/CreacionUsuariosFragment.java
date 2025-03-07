package com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea;

import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.panel.CerrarTareaCrearUsuario;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;

public abstract class CreacionUsuariosFragment extends Fragment {
  private final IModel<TareaPojo> model;
  
  public CreacionUsuariosFragment(String id, String markupId, MarkupContainer markupProvider, IModel<TareaPojo> model) {
    super(id, markupId, markupProvider, model);
    this.model = model;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    CerrarTareaCrearUsuario mainPanel = new CerrarTareaCrearUsuario("mainPanel", this.model) {
        protected void cerrarModalCallback(AjaxRequestTarget target) {
          CreacionUsuariosFragment.this.cerrar(target);
        }
      };
    queue(new Component[] { (Component)mainPanel });
  }
  
  public abstract void cerrar(AjaxRequestTarget paramAjaxRequestTarget);
}
