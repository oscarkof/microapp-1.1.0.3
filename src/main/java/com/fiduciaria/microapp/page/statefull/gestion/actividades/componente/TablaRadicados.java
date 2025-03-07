package com.fiduciaria.microapp.page.statefull.gestion.actividades.componente;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.componentes.datatable.AjaxFallbackSimpleDataTable;
import com.fiduciaria.microapp.componentes.datatable.AjaxPFNavigationToolbar;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.EnumCampoAdjuntoSolicitud;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.EnumCampoSolicitud;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojoVM;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.export.CSVDataExporter;
import org.apache.wicket.extensions.markup.html.repeater.data.table.export.ExportToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.export.IDataExporter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.MapModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.time.Duration;

public class TablaRadicados extends BasePanel {
  @SpringBean
  private GenericHttpClient gtwayHttp;
  
  private List<IColumn<SolicitudPojo, String>> columns = new ArrayList<>();
  
  final SolicitudPojoVM viewModel = new SolicitudPojoVM((IGenericHttpClient)this.gtwayHttp);
  
  public TablaRadicados(String id) {
    super(id);
    camposMostraTabla();
  }
  
  public TablaRadicados(String id, IModel<?> model) {
    super(id, model);
    camposMostraTabla();
  }
  
  private class PagingToolbarBehavior extends Behavior {
    private PagingToolbarBehavior() {}
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initDataTable();
  }
  
  private void camposMostraTabla() {
    this.viewModel.getParametros().getCamposRequeridos().clear();
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.RADICADO_ID);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.TIPO_SOLICITUD);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.CORREO_FORM);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.ID_REF_NEGOCIO);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.NOMBRE_REF_NEGOCIO);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.TIPO_DOC_SOLICITA);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.IDENTIFICACION_SOLICITA);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.NOMBRE_SOLICITA);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.SEGUNDO_NOMBRE);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.APELLIDO_SOLICITA);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.SEGUNDO_APELLIDO);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.CELULAR_SOLICITA);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.CORREO_SOLICITA);
  }
  
  private void initColumns() {
    this.columns.clear();
    this.viewModel.getParametros().getCamposRequeridos().forEach(campo -> {
          if (campo.getFieldName().equalsIgnoreCase("radicadoId".toLowerCase())) {
            agregaColumnaFija(campo, this.columns);
          } else {
            agregaColumnaBase(campo, this.columns);
          } 
        });
    this.columns.add(new AbstractColumn<SolicitudPojo, String>((IModel)Model.of("")) {
          public void populateItem(Item<ICellPopulator<SolicitudPojo>> cellItem, String componentId, IModel<SolicitudPojo> model) {
            cellItem.add(new Component[] { (Component)new TablaRadicados.ActionPanel(componentId, model) });
          }
        });
  }
  
  private void initDataTable() {
    initColumns();
    SortableSolicitudDataProvider dataProvider = new SortableSolicitudDataProvider(this.viewModel);
    AjaxFallbackSimpleDataTable table = new AjaxFallbackSimpleDataTable("table", this.columns, (ISortableDataProvider)dataProvider, 10) {
        public void clickOnRow(AjaxRequestTarget target, IModel model) {}
        
        public void hookPfToolbar(AjaxRequestTarget target) {
          TablaRadicados.this.registrarComportamiento(TablaRadicados.PagingToolbarBehavior.class);
        }
      };
    table.setOutputMarkupPlaceholderTag(true);
    table.add(new Behavior[] { new ActualizarTablaBehavior() });
    queue(new Component[] { (Component)table });
    AjaxPFNavigationToolbar toolbar = new AjaxPFNavigationToolbar("datatableToolbar", table);
    toolbar.setOutputMarkupPlaceholderTag(true);
    toolbar.addFilterComponents(initSeccionFiltros());
    toolbar.add(new Behavior[] { new PagingToolbarBehavior() });
    queue(new Component[] { (Component)toolbar });
    table.addBottomToolbar((AbstractToolbar)(new ExportToolbar((DataTable)table, (IModel)Model.of("Exportar datos a "), (IModel)Model.of("Radicados"))).addDataExporter((IDataExporter)new CSVDataExporter() {
            protected IConverterLocator getConverterLocator() {
              return (IConverterLocator)TablaRadicados.this;
            }
            
            protected <T> IModel<T> wrapModel(IModel<T> model) {
              return TablaRadicados.this.wrap(model);
            }
          }));
  }
  
  private Component initSeccionFiltros() {
    FilterToolbar opcionesFiltroBar = new FilterToolbar("filterSeccion", (IModel)new Model((Serializable)this.viewModel)) {
        public void onEventoActualizar(AjaxRequestTarget target) {
          super.onEventoActualizar(target);
          TablaRadicados.this.initColumns();
        }
      };
    return (Component)opcionesFiltroBar;
  }
  
  private void agregaColumnaFija(final EnumCampoSolicitud campo, List<IColumn<SolicitudPojo, String>> columns) {
    columns.add(new PropertyColumn<SolicitudPojo, String>((IModel)Model.of(campo.getFieldTitle()), campo.getFieldName()) {
          public void populateItem(Item<ICellPopulator<SolicitudPojo>> item, String componentId, IModel<SolicitudPojo> rowModel) {
            super.populateItem(item, componentId, rowModel);
            item.add(new Behavior[] { (Behavior)new AttributeModifier("data-label", campo.getFieldTitle()) });
            item.add(new Behavior[] { (Behavior)new AttributeModifier("style", "--pf-c-table__sticky-column--MinWidth: 20px;") });
          }
          
          public String getCssClass() {
            return " pf-m-truncate pf-m-border-right pf-c-table__sticky-column";
          }
        });
  }
  
  private void agregaColumnaBase(final EnumCampoSolicitud campo, List<IColumn<SolicitudPojo, String>> columns) {
    columns.add(new PropertyColumn<SolicitudPojo, String>((IModel)Model.of(campo.getFieldTitle()), campo.getFieldName()) {
          public void populateItem(Item<ICellPopulator<SolicitudPojo>> item, String componentId, IModel<SolicitudPojo> rowModel) {
            super.populateItem(item, componentId, rowModel);
            item.add(new Behavior[] { (Behavior)new AttributeModifier("data-label", campo.getFieldTitle()) });
          }
          
          public String getCssClass() {
            return " pf-m-fit-content ";
          }
        });
  }
  
  class ActionPanel extends Panel {
    private final SolicitudPojo actionObjModel;
    
    public ActionPanel(String id, IModel<SolicitudPojo> model) {
      super(id, model);
      this.actionObjModel = (SolicitudPojo)model.getObject();
    }
    
    protected void onInitialize() {
      super.onInitialize();
      initVistaDescargaArchivo();
    }
    
    private void initVistaDescargaArchivo() {
      RefreshingView<Map<String, String>> listaArchivosDescarga = new RefreshingView<Map<String, String>>("listaArchivosDescarga") {
          protected Iterator<IModel<Map<String, String>>> getItemModels() {
            SolicitudPojo selected = (SolicitudPojo)getParent().getDefaultModelObject();
            List<Map<String, String>> adjuntos = TablaRadicados.this.viewModel.listaAjuntosSolicitude(selected.getRadicadoId().toString(), "all", true);
            List<IModel<Map<String, String>>> response = new ArrayList<>();
            adjuntos.forEach(action -> response.add(new MapModel(action)));
            return response.iterator();
          }
          
          protected void populateItem(final Item<Map<String, String>> item) {
            Label nombreArchivo = new Label("nombreArchivo", (IModel)Model.of(
                  String.valueOf(((Map)item.getModelObject()).get(EnumCampoAdjuntoSolicitud.NOMBRE.toString()))));
            /*DownloadLink descargar = (new DownloadLink("descargar", new IModel<File>() {
                  private static final long serialVersionUID = 1L;
                  
                  public File getObject() {
                    File tempFile;
                    try {
                      tempFile = File.createTempFile(String.valueOf(((Map)item.getModelObject()).get(EnumCampoAdjuntoSolicitud.NOMBRE.toString())), "");
                      List<Map<String, String>> adjuntos = TablaRadicados.this.viewModel.listaAjuntosSolicitude(TablaRadicados.ActionPanel.this.actionObjModel.getRadicadoId().toString(), String.valueOf(((Map)item.getModelObject()).get(EnumCampoAdjuntoSolicitud.CONSECUTIVO_ADJUNTO.toString())), false);
                      byte[] adjuntobin = new byte[0];
                      if (!adjuntos.isEmpty())
                        adjuntobin = Base64.getDecoder().decode((String)((Map)adjuntos.get(0)).get(EnumCampoAdjuntoSolicitud.ADJUNTO.toString())); 
                      InputStream data = new ByteArrayInputStream(adjuntobin);
                      Files.writeTo(tempFile, data);
                    } catch (IOException e) {
                      throw new RuntimeException(e);
                    } 
                    return tempFile;
                  }
                }String.valueOf(((Map)item.getModelObject()).get(EnumCampoAdjuntoSolicitud.NOMBRE.toString())))).setCacheDuration(Duration.NONE).setDeleteAfterDownload(true);*/
            //item.add(new Component[] { (Component)descargar });
            //descargar.add(new Component[] { (Component)nombreArchivo });
          }
        };
      queue(new Component[] { (Component)listaArchivosDescarga });
    }
  }
}
