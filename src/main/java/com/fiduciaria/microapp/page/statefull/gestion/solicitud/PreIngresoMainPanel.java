package com.fiduciaria.microapp.page.statefull.gestion.solicitud;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.GestionSolicitudesVM;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.panel.SeleccionNegocio;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.preingreso.FormPreingresoDatosUsuario;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.preingreso.FormPreingresoDatosUsuarioVM;
import com.fiduciaria.microapp.store.model.gui.solicitud.TipoFormulario;
import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PreIngresoMainPanel extends BasePanel {
  @SpringBean
  private GenericHttpClient gtwayHttp;
  
  private final IModel<FormPreingresoDatosUsuarioVM> modelo = (IModel<FormPreingresoDatosUsuarioVM>)new Model();
  
  private boolean negocioSeleccionado;
  
  private WebMarkupContainer formularioContenedor;
  
  private WebMarkupContainer seleccionNegocioContenedor;
  
  private class FiltroUpdate extends Behavior {}
  
  public PreIngresoMainPanel(String id, IModel<GestionSolicitudesVM> model) {
    super(id, model);
    this.modelo.setObject(new FormPreingresoDatosUsuarioVM(this.gtwayHttp, usuarioSesion()));
    ((FormPreingresoDatosUsuarioVM)this.modelo.getObject()).getModelo().setTipoFormulario(TipoFormulario.PREINGRESODATOSUSUARIO);
    ((FormPreingresoDatosUsuarioVM)this.modelo.getObject()).setStep(1);
  }
  
  protected void onInitialize() {
    super.onInitialize();
    this.seleccionNegocioContenedor = new WebMarkupContainer("seleccionNegocioContenedor") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!PreIngresoMainPanel.this.negocioSeleccionado);
        }
      };
    queue(new Component[] { (Component)this.seleccionNegocioContenedor });
    this.seleccionNegocioContenedor.setOutputMarkupPlaceholderTag(true);
    this.formularioContenedor = new WebMarkupContainer("formularioContenedor") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(PreIngresoMainPanel.this.negocioSeleccionado);
        }
      };
    queue(new Component[] { (Component)this.formularioContenedor });
    this.formularioContenedor.setOutputMarkupPlaceholderTag(true);
    FormPreingresoDatosUsuario formularioPreIngresoDatosUsuario = new FormPreingresoDatosUsuario("formularioPreIngresoDatosUsuario", this.modelo);
    queue(new Component[] { (Component)formularioPreIngresoDatosUsuario });
    iniSeleccionNegocio();
  }
  
  private void iniSeleccionNegocio() {
    queue(new Component[] { (Component)new SeleccionNegocio("panelSeleccionNegocio") {
            public void callbackSeleccion(AjaxRequestTarget target, NegocioPojo negocioSelecionado) {
              PreIngresoMainPanel.this.negocioSeleccionado = true;
              ((FormPreingresoDatosUsuarioVM)PreIngresoMainPanel.this.modelo.getObject()).getModelo().setNegocio(negocioSelecionado);
              ((FormPreingresoDatosUsuarioVM)PreIngresoMainPanel.this.modelo.getObject()).getModelo().getPreingresodatosusuario().setCodNegocioLink((
                  (FormPreingresoDatosUsuarioVM)PreIngresoMainPanel.this.modelo.getObject()).getModelo().getNegocio().getCodigoNegocio());
              ((FormPreingresoDatosUsuarioVM)PreIngresoMainPanel.this.modelo.getObject()).getModelo().getPreingresodatosusuario().setNombreNegocioLink((
                  (FormPreingresoDatosUsuarioVM)PreIngresoMainPanel.this.modelo.getObject()).getModelo().getNegocio().getNombreNegocio());
             // target.add(new Component[] { (Component)PreIngresoMainPanel.access$200(this.this$0), (Component)PreIngresoMainPanel.access$300(this.this$0) });
            }
          } });
  }
  
  public static final PreIngresoMainPanel getInstance(String id, IModel<GestionSolicitudesVM> model) {
    return new PreIngresoMainPanel(id, model);
  }
}
