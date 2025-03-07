package com.fiduciaria.microapp.page.statefull.gestion.usuario;

import com.fiduciaria.microapp.store.seguridad.PrincipalPojo;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public abstract class EnrolarUsuariosOTPFragment extends Fragment {
  private final IModel<List<PrincipalPojo>> usuariosModel;
  
  public EnrolarUsuariosOTPFragment(String id, String markupId, MarkupContainer markupProvider, IModel<List<PrincipalPojo>> model) {
    super(id, markupId, markupProvider, model);
    this.usuariosModel = model;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    ListView<PrincipalPojo> usuarioToOTPView = new ListView<PrincipalPojo>("usuarioToOTPView", this.usuariosModel) {
        protected void populateItem(ListItem<PrincipalPojo> item) {
          item.add(new Component[] { (Component)new Label("loginUsuario", (IModel)Model.of(((PrincipalPojo)item.getModelObject()).getUsuario())) });
          item.add(new Component[] { (Component)new Label("descripcionUsuario", (IModel)Model.of(((PrincipalPojo)item.getModelObject()).getPrimerNombre())) });
        }
      };
    queue(new Component[] { (Component)usuarioToOTPView });
    queue(new Component[] { (Component)new IndicatingAjaxLink("confirmar") {
            public void onClick(AjaxRequestTarget target) {
              EnrolarUsuariosOTPFragment.this.confirmarEnrolar(target);
            }
          } });
    queue(new Component[] { (Component)new IndicatingAjaxLink("cancelar") {
            public void onClick(AjaxRequestTarget target) {
              EnrolarUsuariosOTPFragment.this.cancelarEnrolar(target);
            }
          } });
  }
  
  public abstract void confirmarEnrolar(AjaxRequestTarget paramAjaxRequestTarget);
  
  public abstract void cancelarEnrolar(AjaxRequestTarget paramAjaxRequestTarget);
}
