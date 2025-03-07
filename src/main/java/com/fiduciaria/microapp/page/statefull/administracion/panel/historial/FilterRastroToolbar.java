package com.fiduciaria.microapp.page.statefull.administracion.panel.historial;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.store.model.rastro.EnumCampoTraceColumn;
import com.fiduciaria.microapp.store.model.rastro.TraceVM;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.string.Strings;

public class FilterRastroToolbar extends BasePanel {
  final IModel<TraceVM> viewModel;
  
  private EnumCampoTraceColumn tipoSolicitud;
  
  private String valorFiltrarPor;
  
  public FilterRastroToolbar(String id, IModel<TraceVM> viewModel) {
    super(id);
    this.viewModel = viewModel;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    LoadableDetachableModel<List<EnumCampoTraceColumn>> listaTiposSol = new LoadableDetachableModel<List<EnumCampoTraceColumn>>() {
        protected List<EnumCampoTraceColumn> load() {
          List<EnumCampoTraceColumn> response = new ArrayList<>();
          response.add(EnumCampoTraceColumn.ID_ACCION);
          response.add(EnumCampoTraceColumn.TIPO_ACCION);
          response.add(EnumCampoTraceColumn.USUARIO_SESS);
          return response;
        }
      };
    Form filtro = new Form("filtro");
    queue(new Component[] { (Component)filtro });
    final TextField valorFiltro = new TextField("valorFiltro", LambdaModel.of(this::getValorFiltrarPor, this::setValorFiltrarPor));
    valorFiltro.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)valorFiltro });
              if (!Strings.isEmpty(FilterRastroToolbar.this.getValorFiltrarPor())) {
                if (Objects.isNull(((TraceVM)FilterRastroToolbar.this.viewModel.getObject()).getParametros().getCondiciones()))
                  ((TraceVM)FilterRastroToolbar.this.viewModel.getObject()).getParametros().setCondiciones(new HashMap<>()); 
                ((TraceVM)FilterRastroToolbar.this.viewModel.getObject()).getParametros()
                  .getCondiciones().put(FilterRastroToolbar.this.getTipoSolicitud(), FilterRastroToolbar.this.getValorFiltrarPor());
              } else if (Objects.isNull(((TraceVM)FilterRastroToolbar.this.viewModel.getObject()).getParametros().getCondiciones())) {
                ((TraceVM)FilterRastroToolbar.this.viewModel.getObject()).getParametros().setCondiciones(new HashMap<>());
              } 
              FilterRastroToolbar.this.registrarComportamiento(ActualizarTablaRastroBehavior.class);
            }
          } });
    valorFiltro.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)valorFiltro });
    ChoiceRenderer renderer = new ChoiceRenderer("tituloTabla", "nombreCampoBean");
    final DropDownChoice selectTipoSolicitud = new DropDownChoice("selectCampoFiltro", LambdaModel.of(this::getTipoSolicitud, this::setTipoSolicitud), (IModel)listaTiposSol, (IChoiceRenderer)renderer);
    queue(new Component[] { (Component)selectTipoSolicitud });
    selectTipoSolicitud.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              FilterRastroToolbar.this.setValorFiltrarPor((String)null);
              ((TraceVM)FilterRastroToolbar.this.viewModel.getObject()).getParametros().setCondiciones(new HashMap<>());
              target.add(new Component[] { (Component)selectTipoSolicitud, (Component)valorFiltro });
              FilterRastroToolbar.this.registrarComportamiento(ActualizarTablaRastroBehavior.class);
            }
          } });
    AjaxLink reiniciar = new AjaxLink("reiniciar") {
        public void onClick(AjaxRequestTarget target) {
          FilterRastroToolbar.this.setValorFiltrarPor((String)null);
          FilterRastroToolbar.this.setTipoSolicitud((EnumCampoTraceColumn)null);
          ((TraceVM)FilterRastroToolbar.this.viewModel.getObject()).getParametros().setCondiciones(new HashMap<>());
          target.add(new Component[] { (Component)selectTipoSolicitud, (Component)valorFiltro });
          FilterRastroToolbar.this.registrarComportamiento(ActualizarTablaRastroBehavior.class);
        }
      };
    queue(new Component[] { (Component)reiniciar });
  }
  
  public void onEventoActualizar(AjaxRequestTarget target) {}
  
  public EnumCampoTraceColumn getTipoSolicitud() {
    return this.tipoSolicitud;
  }
  
  public void setTipoSolicitud(EnumCampoTraceColumn tipoSolicitud) {
    this.tipoSolicitud = tipoSolicitud;
  }
  
  public String getValorFiltrarPor() {
    return this.valorFiltrarPor;
  }
  
  public void setValorFiltrarPor(String valorFiltrarPor) {
    this.valorFiltrarPor = valorFiltrarPor;
  }
}
