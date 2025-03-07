package com.fiduciaria.microapp.page.statefull.administracion.panel.historial;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.componentes.datatable.AjaxFallbackSimpleDataTable;
import com.fiduciaria.microapp.componentes.datatable.AjaxPFNavigationToolbar;
import com.fiduciaria.microapp.store.model.rastro.EnumCampoTraceColumn;
import com.fiduciaria.microapp.store.model.rastro.RastroAccionPojo;
import com.fiduciaria.microapp.store.model.rastro.TraceVM;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.export.CSVDataExporter;
import org.apache.wicket.extensions.markup.html.repeater.data.table.export.ExportToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.export.IDataExporter;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class TablaHistorialRastroDominio extends BasePanel {
  @SpringBean
  IGenericHttpClient httgwy;
  
  private final TraceVM tracedb;
  
  private final List<IColumn<RastroAccionPojo, String>> columns;
  
  public TablaHistorialRastroDominio(String id) {
    this(id, (IModel<?>)null);
  }
  
  public TablaHistorialRastroDominio(String id, IModel<?> model) {
    super(id, model);
    this.tracedb = new TraceVM(this.httgwy, AuthenticatedWebSession.get().getRoles().hasRole("UCA"), usuarioSesion());
    this.columns = new ArrayList<>();
    initColumnasXDefecto();
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initDataTable();
  }
  
  private void initColumns() {
    this.columns.clear();
    this.tracedb.getParametros().getCamposRequeridos().forEach(campo -> {
          if (campo.getNombreCampoBean().equalsIgnoreCase(EnumCampoTraceColumn.ACCION_UUID.getNombreCampoBean().toLowerCase())) {
            agregaColumnaFija(campo, this.columns);
          } else {
            agregaColumnaBase(campo, this.columns);
          } 
        });
  }
  
  private void initDataTable() {
    initColumns();
    AjaxFallbackSimpleDataTable tablaHistorial = new AjaxFallbackSimpleDataTable("table", this.columns, (ISortableDataProvider)new SortableHistorialRastroDataProvider(this.tracedb), 10) {
        public void clickOnRow(AjaxRequestTarget target, IModel model) {}
        
        public void hookPfToolbar(AjaxRequestTarget target) {}
      };
    queue(new Component[] { (Component)tablaHistorial });
    tablaHistorial.add(new Behavior[] { new ActualizarTablaRastroBehavior() });
    AjaxPFNavigationToolbar toolbar = new AjaxPFNavigationToolbar("datatableToolbar", tablaHistorial);
    IModel nombreArchivoExportado = () -> "Historial Acciones Usuarios " + Instant.now().toString();
    tablaHistorial.addBottomToolbar((AbstractToolbar)(new ExportToolbar((DataTable)tablaHistorial, (IModel)Model.of("Exportar Historial Dominios a "), nombreArchivoExportado)).addDataExporter((IDataExporter)new CSVDataExporter() {
            protected IConverterLocator getConverterLocator() {
              return (IConverterLocator)TablaHistorialRastroDominio.this;
            }
            
            protected <T> IModel<T> wrapModel(IModel<T> model) {
              return TablaHistorialRastroDominio.this.wrap(model);
            }
          }));
    toolbar.setOutputMarkupPlaceholderTag(true);
    toolbar.addFilterComponents(initSeccionFiltros());
    toolbar.add(new Behavior[] { new PagingToolbarBehavior() });
    queue(new Component[] { (Component)toolbar });
  }
  
  private Component initSeccionFiltros() {
    FilterRastroToolbar opcionesFiltroBar = new FilterRastroToolbar("filterSeccion", (IModel)new Model((Serializable)this.tracedb)) {
        public void onEventoActualizar(AjaxRequestTarget target) {
          super.onEventoActualizar(target);
          TablaHistorialRastroDominio.this.initColumns();
        }
      };
    return (Component)opcionesFiltroBar;
  }
  
  private void initColumnasXDefecto() {
    this.tracedb.getParametros().setCamposRequeridos(new ArrayList());
    this.tracedb.getParametros().getCamposRequeridos().add(EnumCampoTraceColumn.ACCION_UUID);
    this.tracedb.getParametros().getCamposRequeridos().add(EnumCampoTraceColumn.ID);
    this.tracedb.getParametros().getCamposRequeridos().add(EnumCampoTraceColumn.TIPO_ACCION);
    this.tracedb.getParametros().getCamposRequeridos().add(EnumCampoTraceColumn.USUARIO_SESS);
    this.tracedb.getParametros().getCamposRequeridos().add(EnumCampoTraceColumn.ESTAMPA_TIEMPO);
    this.tracedb.getParametros().getCamposRequeridos().add(EnumCampoTraceColumn.ID_ACCION);
    this.tracedb.getParametros().getCamposRequeridos().add(EnumCampoTraceColumn.MSG);
    this.tracedb.getParametros().setOrdenarPor(EnumCampoTraceColumn.ID);
    this.tracedb.getParametros().setOrdenarTipo("DESC");
  }
  
  private void agregaColumnaFija(final EnumCampoTraceColumn campo, List<IColumn<RastroAccionPojo, String>> columns) {
    columns.add(new PropertyColumn<RastroAccionPojo, String>((IModel)Model.of(campo.getTituloTabla()), campo.getNombreCampoBean()) {
          public void populateItem(Item<ICellPopulator<RastroAccionPojo>> item, String componentId, IModel<RastroAccionPojo> rowModel) {
            super.populateItem(item, componentId, rowModel);
            item.add(new Behavior[] { (Behavior)new AttributeModifier("data-label", campo.getTituloTabla()) });
            item.add(new Behavior[] { (Behavior)new AttributeModifier("style", "--pf-c-table__sticky-column--MinWidth: 20px;") });
          }
          
          public String getCssClass() {
            return " pf-m-truncate pf-m-border-right pf-c-table__sticky-column";
          }
        });
  }
  
  private void agregaColumnaBase(final EnumCampoTraceColumn campo, List<IColumn<RastroAccionPojo, String>> columns) {
    columns.add(new PropertyColumn<RastroAccionPojo, String>((IModel)Model.of(campo.getTituloTabla()), campo.getNombreCampoBean()) {
          public void populateItem(Item<ICellPopulator<RastroAccionPojo>> item, String componentId, IModel<RastroAccionPojo> rowModel) {
            super.populateItem(item, componentId, rowModel);
            item.add(new Behavior[] { (Behavior)new AttributeModifier("data-label", campo.getTituloTabla()) });
          }
          
          public String getCssClass() {
            return " pf-m-fit-content ";
          }
        });
  }
  
  private class PagingToolbarBehavior extends Behavior {
    private PagingToolbarBehavior() {}
  }
}
