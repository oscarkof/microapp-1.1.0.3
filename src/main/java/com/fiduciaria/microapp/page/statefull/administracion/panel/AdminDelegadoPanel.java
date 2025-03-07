package com.fiduciaria.microapp.page.statefull.administracion.panel;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.page.statefull.administracion.model.AdministracionTenantVM;
import com.fiduciaria.microapp.store.model.dominio.DominioPojo;
import com.fiduciaria.microapp.store.model.dominio.DominioVM;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class AdminDelegadoPanel extends BasePanel {
  @SpringBean
  IGenericHttpClient httpgwy;
  
  private final DominioVM dominioVM;
  
  private final WebMarkupContainer contenedorDerecho;
  
  private final WebMarkupContainer contenedorIzquierdo;
  
  private class UpdVistaDominios extends Behavior {
    private UpdVistaDominios() {}
  }
  
  public static AdminDelegadoPanel instance(String mainPanel, IModel<AdministracionTenantVM> modelo) {
    return new AdminDelegadoPanel(mainPanel, modelo);
  }
  
  private class UpdContenedorDerBehavior extends Behavior {
    private UpdContenedorDerBehavior() {}
  }
  
  private boolean visibleIzquierdo = true;
  
  public AdminDelegadoPanel(String id, IModel<?> model) {
    super(id, model);
    this.contenedorDerecho = new WebMarkupContainer("contenedorDerecho");
    this.contenedorIzquierdo = new WebMarkupContainer("contenedorIzquierdo") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(AdminDelegadoPanel.this.visibleIzquierdo);
        }
      };
    this.dominioVM = new DominioVM(this.httpgwy);
    initDomParam();
  }
  
  private void initDomParam() {
    this.dominioVM.getParametros().setCuenta(5L);
    this.dominioVM.getParametros().setInicio(0L);
  }
  
  protected void onInitialize() {
    super.onInitialize();
    queue(new Component[] { (Component)this.contenedorIzquierdo });
    this.contenedorIzquierdo.setOutputMarkupPlaceholderTag(true);
    this.contenedorIzquierdo.add(new Behavior[] { new UpdContenedorIzqBehavior() });
    queue(new Component[] { (Component)this.contenedorDerecho });
    this.contenedorDerecho.setOutputMarkupPlaceholderTag(true);
    this.contenedorDerecho.add(new Behavior[] { new UpdContenedorDerBehavior() });
    this.contenedorDerecho.addOrReplace(new Component[] { (Component)new WebMarkupContainer("detalleSeccion") });
    initVistaDominios();
    iniNavToolbarDominios();
  }
  
  private void initVistaDominios() {
    WebMarkupContainer dominioDataView = new WebMarkupContainer("dominioDataView");
    queue(new Component[] { (Component)dominioDataView });
    dominioDataView.add(new Behavior[] { new UpdContenedorDerBehavior() });
    dominioDataView.add(new Behavior[] { new UpdVistaDominios() });
    dominioDataView.setOutputMarkupPlaceholderTag(true);
    DominioRefreshingView dominioListView = new DominioRefreshingView("dominioListView", (IModel<DominioVM>)new Model((Serializable)this.dominioVM));
    queue(new Component[] { (Component)dominioListView });
  }
  
  public class FormularioDelegadoFragment extends Fragment {
    public FormularioDelegadoFragment(String id, String markupId) {
      super(id, markupId, (MarkupContainer)AdminDelegadoPanel.this);
      queue(new Component[] { (Component)new DelegadoForm("formDelegadoPanel") {
              public void regresar(AjaxRequestTarget target) {
                AdminDelegadoPanel.this.visibleIzquierdo = true;
                AdminDelegadoPanel.this.contenedorDerecho.addOrReplace(new Component[] { (Component)new WebMarkupContainer("detalleSeccion") });
                registrarComportamiento(UpdContenedorIzqBehavior.class);
                registrarComportamiento(AdminDelegadoPanel.UpdContenedorDerBehavior.class);
              }
            } });
    }
  }
  
  public class DetalleDelegadoFragment extends Fragment {
    public DetalleDelegadoFragment(String id, String markupId, IModel<DominioPojo> model) {
      super(id, markupId, (MarkupContainer)AdminDelegadoPanel.this);
      add(new Component[] { (Component)new DetalleDeledagoPanelFragment("mainPanel", model) });
    }
  }
  
  private class DominioRefreshingView extends RefreshingView<DominioPojo> {
    private final IModel<DominioVM> modeloVM;
    
    public DominioRefreshingView(String id, IModel<DominioVM> model) {
      super(id, model);
      this.modeloVM = model;
    }
    
    protected Iterator<IModel<DominioPojo>> getItemModels() {
      List<IModel<DominioPojo>> response = new ArrayList<>();
      ((DominioVM)this.modeloVM.getObject()).listarDominios().getListaDominios().forEach(domi -> response.add(new Model((Serializable)domi)));
      return response.iterator();
    }
    
    protected void populateItem(final Item<DominioPojo> item) {
      item.add(new Component[] { (Component)new Label("nombreDominio", (IModel)Model.of(((DominioPojo)item.getModelObject()).getNombreDominio().toUpperCase())) });
      item.add(new Component[] { (Component)new Label("descripcionDominio", (IModel)Model.of(((DominioPojo)item.getModelObject()).getDescripcionDominio())) });
      item.add(new Component[] { (Component)new AjaxLink("detalleTenat") {
              public void onClick(AjaxRequestTarget target) {
                AdminDelegadoPanel.this.contenedorDerecho.addOrReplace(new Component[] { (Component)new AdminDelegadoPanel.DetalleDelegadoFragment("detalleSeccion", "detalleDelegado", (IModel<DominioPojo>)new Model((Serializable)item
                          .getModelObject())) });
                AdminDelegadoPanel.this.registrarComportamiento(AdminDelegadoPanel.UpdContenedorDerBehavior.class);
              }
            } });
    }
  }
  
  private void iniNavToolbarDominios() {
    final WebMarkupContainer toolbarDominios = new WebMarkupContainer("toolbarDominios");
    queue(new Component[] { (Component)toolbarDominios });
    toolbarDominios.setOutputMarkupPlaceholderTag(true);
    NavToolbarDominios toolbarDominioContent = new NavToolbarDominios("toolbarDominioContent", (IModel)new Model((Serializable)this.dominioVM)) {
        public void callOnActionEvent(AjaxRequestTarget target) {
          target.add(new Component[] { (Component)toolbarDominios });
          registrarComportamiento(AdminDelegadoPanel.UpdVistaDominios.class);
        }
        
        public void onClickNuevoTenant(AjaxRequestTarget target) {
          AdminDelegadoPanel.this.contenedorDerecho.addOrReplace(new Component[] { (Component)new AdminDelegadoPanel.FormularioDelegadoFragment("detalleSeccion", "formularioNuevoDelegado") });
          AdminDelegadoPanel.this.visibleIzquierdo = !AdminDelegadoPanel.this.visibleIzquierdo;
          registrarComportamiento(UpdContenedorIzqBehavior.class);
          registrarComportamiento(AdminDelegadoPanel.UpdContenedorDerBehavior.class);
        }
      };
    queue(new Component[] { (Component)toolbarDominioContent });
  }
}
