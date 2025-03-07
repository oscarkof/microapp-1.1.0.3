package com.fiduciaria.microapp.page.statefull.gestion.solicitud.seguimiento;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.consulta.panel.SeguimientoSolicitud;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.consulta.panel.ToolbarGestionSolicitudes;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.consulta.panel.ToolbarPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.EnumCampoSolicitud;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.EnumEstadoSolicitud;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.EnumTipoSolicitud;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.GestionSolicitudesVM;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojoVM;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;

public class SeguimientoSolicitudMainPanel extends BasePanel {
  @SpringBean
  private GenericHttpClient gtwayHttp;
  
  private final IModel<GestionSolicitudesVM> modelo;
  
  private final SolicitudPojoVM viewSolPoj;
  
  final IModel<ToolbarPojo> toolbarModel = (IModel<ToolbarPojo>)new Model((Serializable)new ToolbarPojo());
  
  public SeguimientoSolicitudMainPanel(String id, IModel<GestionSolicitudesVM> model) {
    super(id, model);
    setOutputMarkupPlaceholderTag(true);
    this.modelo = (IModel<GestionSolicitudesVM>)new Model((Serializable)new GestionSolicitudesVM());
    this.viewSolPoj = new SolicitudPojoVM((IGenericHttpClient)this.gtwayHttp);
    iniCamposSol();
    initToolbarModel();
  }
  
  void iniCamposSol() {
    this.viewSolPoj.getParametros().setTamanioPagina(10L);
    if (Objects.isNull(this.viewSolPoj.getParametros().getCondiciones()))
      this.viewSolPoj.getParametros().setCondiciones(new HashMap<>()); 
    this.viewSolPoj.getParametros().getCamposRequeridos().clear();
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.RADICADO_ID);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.ID_REF_NEGOCIO);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.CELULAR_SOLICITA);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.CORREO_FORM);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.FECHA_HORA);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.CORREO_SOLICITA);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.ESTADO);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.IDENTIFICACION_SOLICITA);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.NOMBRE_REF_NEGOCIO);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.NOMBRE_SOLICITA);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.APELLIDO_SOLICITA);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.SEGUNDO_APELLIDO);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.SEGUNDO_NOMBRE);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.TIPO_DOC_SOLICITA);
    this.viewSolPoj.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.TIPO_SOLICITUD);
  }
  
  public static final SeguimientoSolicitudMainPanel getInstance(String id, IModel<GestionSolicitudesVM> model) {
    return new SeguimientoSolicitudMainPanel(id, model);
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initVistaSolicitudes();
  }
  
  private void setFiltros() {
    this.viewSolPoj.getParametros().getCondiciones().clear();
    if (!Strings.isEmpty(((ToolbarPojo)this.toolbarModel.getObject()).getEstadoSeleccionado()) && 
      !((ToolbarPojo)this.toolbarModel.getObject()).getEstadoSeleccionado().equalsIgnoreCase("TODOS LOS ESTADOS"))
      this.viewSolPoj.getParametros().getCondiciones().put(EnumCampoSolicitud.ESTADO, ((ToolbarPojo)this.toolbarModel
          .getObject()).getEstadoSeleccionado()); 
    if (!Strings.isEmpty(((ToolbarPojo)this.toolbarModel.getObject()).getTipoSolicitudSeleccionda()) && 
      !((ToolbarPojo)this.toolbarModel.getObject()).getTipoSolicitudSeleccionda().equalsIgnoreCase("TODOS LOS TIPOS DE SOLICITUD"))
      this.viewSolPoj.getParametros().getCondiciones().put(EnumCampoSolicitud.TIPO_SOLICITUD, ((ToolbarPojo)this.toolbarModel
          .getObject()).getTipoSolicitudSeleccionda()); 
    if (!Strings.isEmpty(((ToolbarPojo)this.toolbarModel.getObject()).getColumnaFiltroSeleccionada()) && 
      !((ToolbarPojo)this.toolbarModel.getObject()).getColumnaFiltroSeleccionada().equalsIgnoreCase("NO SELECCION"))
      this.viewSolPoj.getParametros().getCamposRequeridos().forEach(enumColumn -> {
            if (enumColumn.getFieldTitle().equalsIgnoreCase(((ToolbarPojo)this.toolbarModel.getObject()).getColumnaFiltroSeleccionada()))
              this.viewSolPoj.getParametros().getCondiciones().put(enumColumn, ((ToolbarPojo)this.toolbarModel.getObject()).getColumnaFiltroSeleccionada()); 
          }); 
    ((ToolbarPojo)this.toolbarModel.getObject()).setTotalRegistros("" + this.viewSolPoj.totalSolicitudes());
  }
  
  class LoadableToolbarModel extends LoadableDetachableModel<ToolbarPojo> {
    protected ToolbarPojo load() {
      SeguimientoSolicitudMainPanel.this.setFiltros();
      ((ToolbarPojo)SeguimientoSolicitudMainPanel.this.toolbarModel.getObject()).setTotalRegistros("" + SeguimientoSolicitudMainPanel.this.viewSolPoj.totalSolicitudes());
      return (ToolbarPojo)SeguimientoSolicitudMainPanel.this.toolbarModel.getObject();
    }
  }
  
  void initToolbarModel() {
    ((ToolbarPojo)this.toolbarModel.getObject()).setListaColumnas(new ArrayList());
    ((ToolbarPojo)this.toolbarModel.getObject()).getListaColumnas().add("NO SELECCION");
    this.viewSolPoj.getParametros().getCamposRequeridos().forEach(column -> ((ToolbarPojo)this.toolbarModel.getObject()).getListaColumnas().add(column.getFieldTitle()));
    ((ToolbarPojo)this.toolbarModel.getObject()).setListaEstados(new ArrayList());
    ((ToolbarPojo)this.toolbarModel.getObject()).getListaEstados().add("TODOS LOS ESTADOS");
    for (EnumEstadoSolicitud enumEstado : EnumEstadoSolicitud.values())
      ((ToolbarPojo)this.toolbarModel.getObject()).getListaEstados().add(enumEstado.getDescripcion()); 
    ((ToolbarPojo)this.toolbarModel.getObject()).setListaTipoSolicitud(new ArrayList());
    ((ToolbarPojo)this.toolbarModel.getObject()).getListaTipoSolicitud().add("TODOS LOS TIPOS DE SOLICITUD");
    for (EnumTipoSolicitud tipsol : EnumTipoSolicitud.values())
      ((ToolbarPojo)this.toolbarModel.getObject()).getListaTipoSolicitud().add(tipsol.getDescripcion()); 
  }
  
  private void initVistaSolicitudes() {
    WebMarkupContainer solicitudesDataView = new WebMarkupContainer("solicitudesDataView");
    queue(new Component[] { (Component)solicitudesDataView });
    solicitudesDataView.setOutputMarkupId(true);
    ToolbarGestionSolicitudes toolbar = new ToolbarGestionSolicitudes("toolbar", (IModel)new LoadableToolbarModel());
    solicitudesDataView.queue(new Component[] { (Component)toolbar });
    toolbar.setOutputMarkupPlaceholderTag(true);
    LoadableDetachableModel<List<SolicitudPojo>> solicitudLoadable = new LoadableDetachableModel<List<SolicitudPojo>>() {
        protected List<SolicitudPojo> load() {
          SeguimientoSolicitudMainPanel.this.setFiltros();
          List<SolicitudPojo> response = new ArrayList<>();
          response = SeguimientoSolicitudMainPanel.this.getListaSolicitudesRadicadas();
          return response;
        }
      };
    ListView<SolicitudPojo> vistaListaSolicitudes = new ListView<SolicitudPojo>("solicitudDataList", (IModel)solicitudLoadable) {
        protected void populateItem(ListItem<SolicitudPojo> item) {
          item.add(new Component[] { (Component)new SeguimientoSolicitud("item", item.getModel()) });
        }
      };
    queue(new Component[] { (Component)vistaListaSolicitudes });
  }
  
  private List<SolicitudPojo> getListaSolicitudesRadicadas() {
    if (AuthenticatedWebSession.get().getRoles().hasRole("UCA"))
      this.viewSolPoj.getParametros().getCondiciones().put(EnumCampoSolicitud.USUARIO_CREA, usuarioSesion()); 
    return this.viewSolPoj.listSolicitud();
  }
}
