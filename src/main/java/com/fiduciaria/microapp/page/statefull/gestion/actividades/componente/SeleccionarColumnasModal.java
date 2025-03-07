package com.fiduciaria.microapp.page.statefull.gestion.actividades.componente;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.EnumCampoSolicitud;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojoVM;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.CheckGroupSelector;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

public abstract class SeleccionarColumnasModal extends BasePanel {
  private final IModel<SolicitudPojoVM> viewModel;
  
  private List<EnumCampoSolicitud> columnasSeleccionadas;
  
  public SeleccionarColumnasModal(String id, IModel<SolicitudPojoVM> model) {
    super(id, model);
    this.viewModel = model;
    this.columnasSeleccionadas = new ArrayList<>();
    this.columnasSeleccionadas.addAll(((SolicitudPojoVM)this.viewModel.getObject()).getParametros().getCamposRequeridos());
  }
  
  protected void onInitialize() {
    super.onInitialize();
    AjaxLink cerrarModal = new AjaxLink("cerrarModal") {
        public void onClick(AjaxRequestTarget target) {
          SeleccionarColumnasModal.this.callbackCerrarModal(target);
        }
      };
    queue(new Component[] { (Component)cerrarModal });
    AjaxLink cancelar = new AjaxLink("cancelar") {
        public void onClick(AjaxRequestTarget target) {
          SeleccionarColumnasModal.this.callbackCerrarModal(target);
        }
      };
    queue(new Component[] { (Component)cancelar });
    crearVistaColumnas();
  }
  
  private void crearVistaColumnas() {
    Form formularioColumnas = new Form("formularioColumnas");
    queue(new Component[] { (Component)formularioColumnas });
    WebMarkupContainer vistaDatos = new WebMarkupContainer("vistaDatos");
    queue(new Component[] { (Component)vistaDatos });
    vistaDatos.setOutputMarkupPlaceholderTag(true);
    CheckGroup seleccionColumnas = new CheckGroup("seleccionarTodosCheckGroup", this.columnasSeleccionadas);
    queue(new Component[] { (Component)seleccionColumnas });
    queue(new Component[] { (Component)new CheckGroupSelector("groupselector", seleccionColumnas) });
    ListView<EnumCampoSolicitud> fila = new ListView<EnumCampoSolicitud>("fila", () -> {
          List<EnumCampoSolicitud> response = new ArrayList<>();
          for (EnumCampoSolicitud column : EnumCampoSolicitud.values()) {
            if (column != EnumCampoSolicitud.ID_ADJUNTO && column != EnumCampoSolicitud.ADJUNTO)
              response.add(column); 
          } 
          return response;
        }) {
        protected void populateItem(ListItem<EnumCampoSolicitud> item) {
          item.add(new Component[] { (Component)new Check("checkbox", item.getModel()) });
          item.add(new Component[] { (Component)new Label("nombreColumna", ((EnumCampoSolicitud)item.getModelObject()).getFieldTitle()) });
        }
      };
    fila.setReuseItems(true);
    queue(new Component[] { (Component)fila });
    AjaxSubmitLink guardar = new AjaxSubmitLink("guardar", formularioColumnas) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          if (!SeleccionarColumnasModal.this.columnasSeleccionadas.contains(EnumCampoSolicitud.RADICADO_ID))
            SeleccionarColumnasModal.this.columnasSeleccionadas.add(0, EnumCampoSolicitud.RADICADO_ID); 
          ((SolicitudPojoVM)SeleccionarColumnasModal.this.viewModel.getObject()).getParametros().getCamposRequeridos().clear();
          ((SolicitudPojoVM)SeleccionarColumnasModal.this.viewModel.getObject()).getParametros().getCamposRequeridos().addAll(SeleccionarColumnasModal.this.columnasSeleccionadas);
          SeleccionarColumnasModal.this.callbackGuardarModal(target);
        }
      };
    queue(new Component[] { (Component)guardar });
  }
  
  public abstract void callbackCerrarModal(AjaxRequestTarget paramAjaxRequestTarget);
  
  public abstract void callbackGuardarModal(AjaxRequestTarget paramAjaxRequestTarget);
}