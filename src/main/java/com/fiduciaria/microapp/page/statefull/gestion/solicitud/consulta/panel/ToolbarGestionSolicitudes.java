package com.fiduciaria.microapp.page.statefull.gestion.solicitud.consulta.panel;

import com.fiduciaria.microapp.base.BasePanel;
import java.lang.invoke.SerializedLambda;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;

public class ToolbarGestionSolicitudes extends BasePanel {
  private final IModel<ToolbarPojo> toolbarModel;
  
  public ToolbarGestionSolicitudes(String id, IModel<ToolbarPojo> model) {
    super(id, model);
    setOutputMarkupPlaceholderTag(true);
    this.toolbarModel = model;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    Form toolbarForm = new Form("toolbarForm");
    queue(new Component[] { (Component)toolbarForm });
    Label totalRegistros = new Label("totalRegistros", () -> {
        //LambdaModel.of((ToolbarPojo)this.toolbarModel.getObject()::getTotalRegistros)
        String retval = this.toolbarModel.getObject().getTotalRegistros();
        return retval;
    });
    totalRegistros.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)totalRegistros });
    Label mostrandoDeHasta = new Label("mostrandoDeHasta", () -> {
        //LambdaModel.of((ToolbarPojo)this.toolbarModel.getObject()::getTotalRegistros)
        String retval = this.toolbarModel.getObject().getTotalRegistros();
        return retval;
    });
    mostrandoDeHasta.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)mostrandoDeHasta });
    DropDownChoice selectTipoSolicitud = new DropDownChoice("selectTipoSolicitud", () -> {
        //LambdaModel.of((ToolbarPojo)this.toolbarModel.getObject()::getTipoSolicitudSeleccionda, (ToolbarPojo)this.toolbarModel.getObject()::setTipoSolicitudSeleccionda)
        String retval = this.toolbarModel.getObject().getTipoSolicitudSeleccionda();
        return retval;
    }, ((ToolbarPojo)this.toolbarModel.getObject()).getListaTipoSolicitud());
    queue(new Component[] { (Component)selectTipoSolicitud });
    selectTipoSolicitud.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)getParent() });
            }
          } });
    /*DropDownChoice selectColumnaFiltro = new DropDownChoice("selectColumnaFiltro", LambdaModel.of((ToolbarPojo)this.toolbarModel.getObject()::getColumnaFiltroSeleccionada, (ToolbarPojo)this.toolbarModel.getObject()::setColumnaFiltroSeleccionada), ((ToolbarPojo)this.toolbarModel.getObject()).getListaColumnas());
    queue(new Component[] { (Component)selectColumnaFiltro });
    selectColumnaFiltro.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)this.this$0.getParent() });
            }
          } });*/
    /*DropDownChoice selectEstado = new DropDownChoice("selectEstado", LambdaModel.of((ToolbarPojo)this.toolbarModel.getObject()::getEstadoSeleccionado, (ToolbarPojo)this.toolbarModel.getObject()::setEstadoSeleccionado), ((ToolbarPojo)this.toolbarModel.getObject()).getListaEstados());
    queue(new Component[] { (Component)selectEstado });
    selectEstado.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)this.this$0.getParent() });
            }
          } });*/
  }
}
