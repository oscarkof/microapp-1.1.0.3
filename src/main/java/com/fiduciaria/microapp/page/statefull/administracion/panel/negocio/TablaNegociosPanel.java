package com.fiduciaria.microapp.page.statefull.administracion.panel.negocio;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.componentes.datatable.AjaxFallbackSimpleDataTable;
import com.fiduciaria.microapp.componentes.datatable.AjaxPFNavigationToolbar;
import com.fiduciaria.microapp.page.statefull.gestion.actividades.componente.ActualizarTablaBehavior;
import com.fiduciaria.microapp.store.model.negocio.NegocioColumnEnum;
import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import com.fiduciaria.microapp.store.model.negocio.NegocioVM;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class TablaNegociosPanel extends BasePanel {
  @SpringBean
  private IGenericHttpClient gtwayHttp;
  
  private final NegocioVM negocioviewModel;
  
  private final List<IColumn<NegocioPojo, String>> columns = new ArrayList<>();
  
  private NegocioPojo selected;
  
  private final Map<String, UsuarioPojo> admonDelegados;
  
  public TablaNegociosPanel(String id, Map<String, UsuarioPojo> admonDelegados) {
    super(id);
    this.negocioviewModel = new NegocioVM(this.gtwayHttp);
    this.admonDelegados = admonDelegados;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initBaseParam();
    initNegociosDataTable();
  }
  
  private void initBaseParam() {
    if (!this.admonDelegados.isEmpty()) {
      if (this.admonDelegados.keySet().iterator().hasNext()) {
        this.negocioviewModel.getParametros()
          .setUsuario(this.admonDelegados.keySet().iterator().next());
      } else {
        this.negocioviewModel.getParametros().setUsuario("");
      } 
    } else {
      this.negocioviewModel.getParametros().setUsuario("");
    } 
    if (Objects.isNull(this.negocioviewModel.getParametros().getCamposRequeridos()))
      this.negocioviewModel.getParametros().setCamposRequeridos(new ArrayList()); 
    this.negocioviewModel.getParametros().getCamposRequeridos().clear();
    this.negocioviewModel.getParametros().getCamposRequeridos().add(NegocioColumnEnum.TIPO_NEGOCIO);
    this.negocioviewModel.getParametros().getCamposRequeridos().add(NegocioColumnEnum.CODIGO_NEGOCIO);
    this.negocioviewModel.getParametros().getCamposRequeridos().add(NegocioColumnEnum.DESCRIPCION_NEGOCIO);
    this.negocioviewModel.getParametros().getCamposRequeridos().add(NegocioColumnEnum.IDENTIFICACION);
    this.negocioviewModel.getParametros().getCamposRequeridos().add(NegocioColumnEnum.TIPOREFERENCIA);
  }
  
  private void initColumns() {
    this.columns.clear();
    this.negocioviewModel.getParametros().getCamposRequeridos().forEach(campo -> agregaColumnaBase(campo, this.columns));
    this.columns.add(new AbstractColumn<NegocioPojo, String>((IModel)Model.of("Acción")) {
          public void populateItem(Item<ICellPopulator<NegocioPojo>> cellItem, String componentId, IModel<NegocioPojo> model) {
            cellItem.add(new Component[] { (Component)new TablaNegociosPanel.SeleccionPanel(componentId, model) });
          }
        });
  }
  
  private void initNegociosDataTable() {
    initColumns();
    SortableNegocioDataProvider dataProvider = new SortableNegocioDataProvider(this.negocioviewModel);
    AjaxFallbackSimpleDataTable table = new AjaxFallbackSimpleDataTable("tablaNegocios", this.columns, (ISortableDataProvider)dataProvider, 10) {
        public void clickOnRow(AjaxRequestTarget target, IModel model) {}
        
        public void hookPfToolbar(AjaxRequestTarget target) {}
      };
    table.setOutputMarkupPlaceholderTag(true);
    table.add(new Behavior[] { (Behavior)new ActualizarTablaBehavior() });
    queue(new Component[] { (Component)table });
    AjaxPFNavigationToolbar toolbar = new AjaxPFNavigationToolbar("datatableToolbar", table);
    toolbar.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)toolbar });
  }
  
  private void agregaColumnaBase(final NegocioColumnEnum campo, List<IColumn<NegocioPojo, String>> columns) {
    columns.add(new PropertyColumn<NegocioPojo, String>((IModel)Model.of(campo.getTitulo()), campo.getNombre()) {
          public void populateItem(Item<ICellPopulator<NegocioPojo>> item, String componentId, IModel<NegocioPojo> rowModel) {
            super.populateItem(item, componentId, rowModel);
            item.add(new Behavior[] { (Behavior)new AttributeModifier("data-label", campo.getTitulo()) });
          }
          
          public String getCssClass() {
            return " pf-m-fit-content ";
          }
        });
  }
  
  private class SeleccionPanel extends BasePanel {
    public SeleccionPanel(String id, IModel<NegocioPojo> model) {
      super(id, model);
      add(new Component[] { (Component)new Link<Void>("seleccionar") {
              public void onClick() {
                TablaNegociosPanel.this.setSelected((NegocioPojo)getParent().getDefaultModelObject());
              }
            } });
    }
  }
  
  public NegocioPojo getSelected() {
    return this.selected;
  }
  
  public void setSelected(NegocioPojo selected) {
    addStateChange();
    this.selected = selected;
  }
}
