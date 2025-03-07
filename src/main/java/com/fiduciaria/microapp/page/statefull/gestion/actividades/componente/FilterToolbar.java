package com.fiduciaria.microapp.page.statefull.gestion.actividades.componente;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.EnumCampoSolicitud;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.EnumTipoSolicitud;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojoVM;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class FilterToolbar extends BasePanel {
  final IModel<SolicitudPojoVM> viewModel;
  
  private String tipoSolicitud;
  
  public FilterToolbar(String id, IModel<SolicitudPojoVM> viewModel) {
    super(id);
    this.viewModel = viewModel;
    ((SolicitudPojoVM)viewModel.getObject()).getParametros().setCondiciones(new HashMap<>());
    if (AuthenticatedWebSession.get().getRoles().hasRole("UCA"))
      ((SolicitudPojoVM)this.viewModel.getObject()).getParametros().getCondiciones().put(EnumCampoSolicitud.USUARIO_CREA, usuarioSesion()); 
  }
  
  protected void onInitialize() {
    super.onInitialize();
    LoadableDetachableModel<List<String>> listaTiposSol = new LoadableDetachableModel<List<String>>() {
        protected List<String> load() {
          List<String> response = new ArrayList<>();
          Arrays.<EnumTipoSolicitud>asList(EnumTipoSolicitud.values()).forEach(action -> response.add(action.getDescripcion()));
          return response;
        }
      };
    Form filtro = new Form("filtro");
    queue(new Component[] { (Component)filtro });
    DropDownChoice selectTipoSolicitud = new DropDownChoice("selectTipoSolicitud", LambdaModel.of(this::getTipoSolicitud, this::setTipoSolicitud), (IModel)listaTiposSol);
    queue(new Component[] { (Component)selectTipoSolicitud });
    selectTipoSolicitud.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              ((SolicitudPojoVM)FilterToolbar.this.viewModel.getObject()).getParametros().getCondiciones().put(EnumCampoSolicitud.TIPO_SOLICITUD, FilterToolbar.this.getTipoSolicitud());
              FilterToolbar.this.registrarComportamiento(ActualizarTablaBehavior.class);
            }
          } });
    AjaxLink manejarColumnas = new AjaxLink("manejarColumnas") {
        public void onClick(AjaxRequestTarget target) {
          FilterToolbar.this.addModal((Component)new SeleccionarColumnasModal(FilterToolbar.this.getModalContentId(), FilterToolbar.this.viewModel) {
                public void callbackCerrarModal(AjaxRequestTarget target) {
                  showModal(false, target);
                  removeModal();
                  registrarComportamiento(ActualizarTablaBehavior.class);
                  FilterToolbar.this.onEventoActualizar(target);
                }
                
                public void callbackGuardarModal(AjaxRequestTarget target) {
                  showModal(false, target);
                  removeModal();
                  registrarComportamiento(ActualizarTablaBehavior.class);
                  FilterToolbar.this.onEventoActualizar(target);
                }
              });
          FilterToolbar.this.showModal(true, target);
        }
      };
    queue(new Component[] { (Component)manejarColumnas });
  }
  
  public void onEventoActualizar(AjaxRequestTarget target) {}
  
  public String getTipoSolicitud() {
    return this.tipoSolicitud;
  }
  
  public void setTipoSolicitud(String tipoSolicitud) {
    this.tipoSolicitud = tipoSolicitud;
  }
}