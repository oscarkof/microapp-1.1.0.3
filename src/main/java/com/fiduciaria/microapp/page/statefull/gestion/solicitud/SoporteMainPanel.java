package com.fiduciaria.microapp.page.statefull.gestion.solicitud;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.GestionSolicitudesVM;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.panel.SeleccionNegocio;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.soporte.FormSoporteCliente;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.soporte.FormSoporteClienteVM;
import com.fiduciaria.microapp.store.model.gui.solicitud.FormularioSoportePojo;
import com.fiduciaria.microapp.store.model.gui.solicitud.TipoFormulario;
import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import java.io.Serializable;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class SoporteMainPanel extends BasePanel {
  @SpringBean
  IGenericHttpClient gtwayHttp;
  
  private final IModel<FormSoporteClienteVM> modelo;
  
  private WebMarkupContainer formularioContenedor;
  
  private WebMarkupContainer seleccionNegocioContenedor;
  
  private boolean negocioSeleccionado;
  
  public SoporteMainPanel(String id, IModel<GestionSolicitudesVM> model) {
    super(id, model);
    this.modelo = (IModel<FormSoporteClienteVM>)new Model((Serializable)new FormSoporteClienteVM(this.gtwayHttp, usuarioSesion()));
    ((FormSoporteClienteVM)this.modelo.getObject())
      .setModelo(new FormularioSoportePojo());
    ((FormSoporteClienteVM)this.modelo.getObject()).getModelo().setTipoFormulario(TipoFormulario.SOPORTETECNICO);
  }
  
  public static final SoporteMainPanel getInstance(String id, IModel<GestionSolicitudesVM> model) {
    return new SoporteMainPanel(id, model);
  }
  
  protected void onInitialize() {
    super.onInitialize();
    this.seleccionNegocioContenedor = new WebMarkupContainer("seleccionNegocioContenedor") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!SoporteMainPanel.this.negocioSeleccionado);
        }
      };
    queue(new Component[] { (Component)this.seleccionNegocioContenedor });
    this.seleccionNegocioContenedor.setOutputMarkupPlaceholderTag(true);
    this.formularioContenedor = new WebMarkupContainer("formularioContenedor") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(SoporteMainPanel.this.negocioSeleccionado);
        }
      };
    queue(new Component[] { (Component)this.formularioContenedor });
    this.formularioContenedor.setOutputMarkupPlaceholderTag(true);
    initFormularioSoporte();
    iniSeleccionNegocio();
  }
  
  private void iniSeleccionNegocio() {
    queue(new Component[] { (Component)new SeleccionNegocio("panelSeleccionNegocio") {
            public void callbackSeleccion(AjaxRequestTarget target, NegocioPojo negocioSelecionado) {
              SoporteMainPanel.this.negocioSeleccionado = true;
              ((FormSoporteClienteVM)SoporteMainPanel.this.modelo.getObject()).getModelo().setNegocio(negocioSelecionado);
              ((FormSoporteClienteVM)SoporteMainPanel.this.modelo.getObject()).getModelo().getSoporteTecnico().setCodigoNegocioForm((
                  (FormSoporteClienteVM)SoporteMainPanel.this.modelo.getObject()).getModelo().getNegocio().getCodigoNegocio());
              ((FormSoporteClienteVM)SoporteMainPanel.this.modelo.getObject()).getModelo().getSoporteTecnico().setNombreNegocioForm((
                  (FormSoporteClienteVM)SoporteMainPanel.this.modelo.getObject()).getModelo().getNegocio().getNombreNegocio());
            //  target.add(new Component[] { (Component)SoporteMainPanel.access$200(this.this$0), (Component)SoporteMainPanel.access$300(this.this$0) });
            }
          } });
  }
  
  private void initFormularioSoporte() {
    FormSoporteCliente formularioSoporteCliente = new FormSoporteCliente("formularioSoporteCliente", this.modelo);
    queue(new Component[] { (Component)formularioSoporteCliente });
  }
}
