package com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.EnumCampoSolicitud;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.GestionSolicitudesVM;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojoVM;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojoVM;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.panel.SeguimientoTarea;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class TareaSolicitudMainPanel extends BasePanel {
  @SpringBean
  private GenericHttpClient gtwayHttp;
  
  private final IModel<GestionSolicitudesVM> modelo;
  
  final SolicitudPojoVM viewModel = new SolicitudPojoVM((IGenericHttpClient)this.gtwayHttp);
  
  private final WebMarkupContainer cierreTareaSeccion;
  
  private boolean accionCierreTareaActiva;
  
  private class UpdAccionTarea extends Behavior {
    private UpdAccionTarea() {}
  }
  
  public static final TareaSolicitudMainPanel getInstance(String id, IModel<GestionSolicitudesVM> model) {
    return new TareaSolicitudMainPanel(id, model);
  }
  
  public TareaSolicitudMainPanel(String id, IModel<GestionSolicitudesVM> model) {
    super(id, model);
    this.cierreTareaSeccion = new WebMarkupContainer("cierreTareaSeccion");
    this.modelo = (IModel<GestionSolicitudesVM>)new Model((Serializable)new GestionSolicitudesVM());
    camposMostraTabla();
  }
  
  protected void onInitialize() {
    super.onInitialize();
    this.cierreTareaSeccion.setOutputMarkupPlaceholderTag(true);
    this.cierreTareaSeccion.add(new Component[] { (Component)new WebMarkupContainer("content") });
    queue(new Component[] { (Component)this.cierreTareaSeccion });
    initVistaTareas();
  }
  
  private void initVistaTareas() {
    WebMarkupContainer seccionVistaListaSolicitudes = new WebMarkupContainer("seccionVistaListaSolicitudes", this.modelo) {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!TareaSolicitudMainPanel.this.accionCierreTareaActiva);
        }
      };
    queue(new Component[] { (Component)seccionVistaListaSolicitudes });
    seccionVistaListaSolicitudes.setOutputMarkupPlaceholderTag(true);
    seccionVistaListaSolicitudes.add(new Behavior[] { new UpdAccionTarea() });
    LoadableDetachableModel<List<TareaPojo>> solicitudLoadable = new LoadableDetachableModel<List<TareaPojo>>() {
        protected List<TareaPojo> load() {
          List<TareaPojo> response = new ArrayList<>();
          response = TareaSolicitudMainPanel.this.getListaTareasAsignadas();
          return response;
        }
      };
    ListView<TareaPojo> vistaListaSolicitudes = new ListView<TareaPojo>("solicitudDataList", (IModel)solicitudLoadable) {
        protected void populateItem(ListItem<TareaPojo> item) {
          item.add(new Component[] { (Component)new SeguimientoTarea("item", item.getModel()) {
                  public void irDetalleTarea(AjaxRequestTarget target, IModel<TareaPojo> model) {
                    TareaSolicitudMainPanel.this.abrirSeccionCierreTareas(target, model);
                  }
                } });
        }
      };
    queue(new Component[] { (Component)vistaListaSolicitudes });
  }
  
  private void abrirSeccionCierreTareas(AjaxRequestTarget target, IModel<TareaPojo> model) {
    String idmkupfragmento = "content";
    if (Objects.nonNull(model.getObject())) {
      if (((TareaPojo)model.getObject()).getSolicitudRelacionada()
        .getTipoSolicitud().equalsIgnoreCase("Registro usuario".toLowerCase()) || ((TareaPojo)model
        .getObject()).getSolicitudRelacionada()
        .getTipoSolicitud().equalsIgnoreCase("Registro cliente".toLowerCase()) || ((TareaPojo)model
        .getObject()).getSolicitudRelacionada()
        .getTipoSolicitud().equalsIgnoreCase("Registro fiduciaria".toLowerCase())) {
        this.cierreTareaSeccion.addOrReplace(new Component[] { (Component)new CreacionUsuariosFragment("content", "creacionUsuariosFragment", (MarkupContainer)this, model) {
                public void cerrar(AjaxRequestTarget target) {
                  TareaSolicitudMainPanel.this.cierreTareaSeccion.addOrReplace(new Component[] { (Component)new WebMarkupContainer("content") });
                  TareaSolicitudMainPanel.this.accionCierreTareaActiva = false;
                  TareaSolicitudMainPanel.this.registrarComportamiento(TareaSolicitudMainPanel.UpdAccionTarea.class);
                  //target.add(new Component[] { (Component)TareaSolicitudMainPanel.access$400(this.this$0) });
                }
              } });
      } else {
        this.cierreTareaSeccion.addOrReplace(new Component[] { (Component)new CreacionUsuariosFragment("content", "creacionUsuariosFragment", (MarkupContainer)this, model) {
                public void cerrar(AjaxRequestTarget target) {
                  TareaSolicitudMainPanel.this.cierreTareaSeccion.addOrReplace(new Component[] { (Component)new WebMarkupContainer("content") });
                  TareaSolicitudMainPanel.this.accionCierreTareaActiva = false;
                  TareaSolicitudMainPanel.this.registrarComportamiento(TareaSolicitudMainPanel.UpdAccionTarea.class);
                  //target.add(new Component[] { (Component)TareaSolicitudMainPanel.access$400(this.this$0) });
                }
              } });
      } 
    } else {
      this.cierreTareaSeccion.addOrReplace(new Component[] { (Component)new WebMarkupContainer("content") });
    } 
    this.accionCierreTareaActiva = true;
    registrarComportamiento(UpdAccionTarea.class);
    target.add(new Component[] { (Component)this.cierreTareaSeccion });
  }
  
  private List<TareaPojo> getListaTareasAsignadas() {
    List<TareaPojo> response = new ArrayList<>();
    response = TareaPojoVM.instance(this.gtwayHttp).listaTareaXUsuario(getSession().getAttribute("username").toString());
    response.forEach(action -> {
          this.viewModel.getParametros().getCondiciones().put(EnumCampoSolicitud.RADICADO_ID, action.getNumRadicado());
          for (SolicitudPojo sol : this.viewModel.listSolicitud())
            action.setSolicitudRelacionada(sol); 
        });
    return response;
  }
  
  private void camposMostraTabla() {
    if (Objects.isNull(this.viewModel.getParametros().getCondiciones()))
      this.viewModel.getParametros().setCondiciones(new HashMap<>()); 
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
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.ACEPTACION_TR_DATOS);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.ROL);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.ROL_FIDEICOMISO);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.ROL_FIDEICOMISO_OTRO);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.DIRECCION);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.CONDI_MANEJO);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.CONDI_MANEJO_ROLCARGA_OTRA);
    this.viewModel.getParametros().getCamposRequeridos().add(EnumCampoSolicitud.CON_USR_AUTHZ);
  }
}
