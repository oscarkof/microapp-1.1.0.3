package com.fiduciaria.microapp.page.statefull.administracion.panel.user;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.componentes.datatable.AjaxFallbackSimpleDataTable;
import com.fiduciaria.microapp.componentes.datatable.AjaxPFNavigationToolbar;
import com.fiduciaria.microapp.store.model.usuario.UsuarioColumnEnum;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojo;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojoVM;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class TablaUsuariosExternosPanel extends BasePanel {
  @SpringBean
  private IGenericHttpClient gtwayHttp;
  
  private final List<IColumn<UsuarioPojo, String>> columns = new ArrayList<>();
  
  private final UsuarioPojoVM usrPjview;
  
  private UsuarioPojo selected;
  
  public TablaUsuariosExternosPanel(String id) {
    super(id);
    this.usrPjview = new UsuarioPojoVM(this.gtwayHttp);
    initParametros();
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initTablaUsuario();
  }
  
  private void initParametros() {
    if (Objects.isNull(this.usrPjview.getParametros().getCamposRequeridos()))
      this.usrPjview.getParametros().setCamposRequeridos(new ArrayList()); 
    if (Objects.isNull(this.usrPjview.getParametros().getCondiciones()))
      this.usrPjview.getParametros().setCondiciones(new HashMap<>()); 
    this.usrPjview.getParametros().getCamposRequeridos().clear();
    this.usrPjview.getParametros().getCamposRequeridos().add(UsuarioColumnEnum.LOGIN_USUARIO);
    this.usrPjview.getParametros().getCamposRequeridos().add(UsuarioColumnEnum.USUARIO);
    this.usrPjview.getParametros().getCamposRequeridos().add(UsuarioColumnEnum.DESCRIPCION_USUARIO);
    this.usrPjview.getParametros().getCamposRequeridos().add(UsuarioColumnEnum.ESTADO_USUARIO);
  }
  
  private void initTablaUsuario() {
    initColumns();
    this.columns.add(new AbstractColumn<UsuarioPojo, String>((IModel)Model.of("Acción")) {
          public void populateItem(Item<ICellPopulator<UsuarioPojo>> cellItem, String componentId, IModel<UsuarioPojo> model) {
            cellItem.add(new Component[] { (Component)new TablaUsuariosExternosPanel.SeleccionPanel(componentId, model) });
          }
        });
    this.usrPjview.getParametros().setMostrarAdmin(false);
    SortableUsuarioDataProvider dataProvider = new SortableUsuarioDataProvider(this.usrPjview);
    AjaxFallbackSimpleDataTable table = new AjaxFallbackSimpleDataTable("tableUsuarios", this.columns, (ISortableDataProvider)dataProvider, 10) {
        public void clickOnRow(AjaxRequestTarget target, IModel model) {}
        
        public void hookPfToolbar(AjaxRequestTarget target) {}
      };
    table.setOutputMarkupPlaceholderTag(true);
    table.add(new Behavior[] { new UpdTablaUsuariosExternosBehavior() });
    queue(new Component[] { (Component)table });
    AjaxPFNavigationToolbar toolbar = new AjaxPFNavigationToolbar("datatableToolbar", table);
    toolbar.setOutputMarkupPlaceholderTag(true);
    toolbar.addFilterComponents(initSeccionFiltros());
    queue(new Component[] { (Component)toolbar });
  }
  
  private Component initSeccionFiltros() {
    FiltroUsuarioToolbar opcionesFiltroBar = new FiltroUsuarioToolbar("filterSeccion", (IModel)new Model((Serializable)this.usrPjview)) {
        public void onEventoActualizar(AjaxRequestTarget target) {
          super.onEventoActualizar(target);
          TablaUsuariosExternosPanel.this.initColumns();
        }
      };
    return (Component)opcionesFiltroBar;
  }
  
  private void initColumns() {
    this.columns.clear();
    this.usrPjview.getParametros().getCamposRequeridos().forEach(campo -> {
          if (UsuarioColumnEnum.USUARIO != campo)
            agregaColumnaBase(campo, this.columns); 
        });
  }
  
  private void agregaColumnaBase(final UsuarioColumnEnum campo, List<IColumn<UsuarioPojo, String>> columns) {
    columns.add(new PropertyColumn<UsuarioPojo, String>((IModel)Model.of(campo.getTitulo()), campo.getNombre()) {
          public void populateItem(Item<ICellPopulator<UsuarioPojo>> item, String componentId, IModel<UsuarioPojo> rowModel) {
            super.populateItem(item, componentId, rowModel);
            item.add(new Behavior[] { (Behavior)new AttributeModifier("data-label", campo.getTitulo()) });
          }
          
          public String getCssClass() {
            return " pf-m-fit-content ";
          }
        });
  }
  
  private class SeleccionPanel extends BasePanel {
    public SeleccionPanel(String id, final IModel<UsuarioPojo> modelo) {
      super(id, modelo);
      Link<Void> linkAccion = new Link<Void>("seleccionar") {
          public void onClick() {
            TablaUsuariosExternosPanel.this.setSelected((UsuarioPojo)getParent().getDefaultModelObject());
          }
          
          protected void onConfigure() {
            super.onConfigure();
            UsuarioPojo usr = (UsuarioPojo)getParent().getDefaultModelObject();
            if (usr.getUsuario().split(";;")[(usr.getUsuario().split(";;")).length - 1].trim().equalsIgnoreCase("admin") || usr
              .getUsuario().split(";;")[(usr.getUsuario().split(";;")).length - 1].trim().equalsIgnoreCase("user"))
              setEnabled(false); 
          }
        };
      add(new Component[] { (Component)linkAccion });
      IModel modeloAccion = new IModel() {
          public Object getObject() {
            UsuarioPojo usr = (UsuarioPojo)modelo.getObject();
            if (usr.getUsuario().split(";;")[(usr.getUsuario().split(";;")).length - 1].trim().equalsIgnoreCase("admin"))
              return "Es administrador"; 
            if (usr.getUsuario().split(";;")[(usr.getUsuario().split(";;")).length - 1].trim().equalsIgnoreCase("user"))
              return "Usuario administrado"; 
            return "Asignar como administrador";
          }
        };
      linkAccion.add(new Component[] { (Component)new Label("labelAccion", modeloAccion) });
    }
  }
  
  public UsuarioPojo getSelected() {
    return this.selected;
  }
  
  public void setSelected(UsuarioPojo selected) {
    addStateChange();
    this.selected = selected;
  }
}
