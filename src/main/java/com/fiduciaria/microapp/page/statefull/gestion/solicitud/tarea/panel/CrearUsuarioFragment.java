package com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.panel;

import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojo;
import com.fiduciaria.microapp.page.statefull.gestion.usuario.CrearUsuarioForm;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoPojo;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojo;
import java.io.Serializable;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public abstract class CrearUsuarioFragment extends Fragment {
  private final UsuarioPojo usuarioModel;
  
  private final TareaPojo tareaModel;
  
  public CrearUsuarioFragment(String id, String markupId, MarkupContainer markupProvider, IModel<TareaPojo> model) {
    super(id, markupId, markupProvider, model);
    this.tareaModel = (TareaPojo)model.getObject();
    this.usuarioModel = new UsuarioPojo();
    initUsuarioModel();
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initAccionCerrar();
    initFormularioCreaUsaurio();
  }
  
  private void initUsuarioModel() {
    this.usuarioModel.setCorreoElectronico(this.tareaModel.getSolicitudRelacionada().getCorreoSolicita());
    this.usuarioModel.setDireccionDomicilio(this.tareaModel.getSolicitudRelacionada().getDireccion());
    this.usuarioModel.setEstado(this.tareaModel.getSolicitudRelacionada().getEstado());
    this.usuarioModel.setIdentificacion(this.tareaModel.getSolicitudRelacionada().getIdentificacionSolicita());
    this.usuarioModel.setNumeroCelular(this.tareaModel.getSolicitudRelacionada().getCelularSolicita());
    this.usuarioModel.setNumeroTelefono(this.tareaModel.getSolicitudRelacionada().getTelefonoSolicita());
    this.usuarioModel.setPrimerApellido(this.tareaModel.getSolicitudRelacionada().getApellidoSolicita());
    this.usuarioModel.setPrimerNombre(this.tareaModel.getSolicitudRelacionada().getNombreSolicita());
    this.usuarioModel.setSegundoApellido(this.tareaModel.getSolicitudRelacionada().getSegundoApellido());
    this.usuarioModel.setSegundoNombre(this.tareaModel.getSolicitudRelacionada().getSegundoNombre());
    this.usuarioModel.setTipoIdentificacion(this.tareaModel.getSolicitudRelacionada().getTipoDocSolicita());
    this.usuarioModel.setTipoDocumento(new TipoDocumentoPojo());
    this.usuarioModel.getTipoDocumento().setClasedocumento(this.tareaModel.getSolicitudRelacionada().getTipoDocSolicita());
  }
  
  private void initFormularioCreaUsaurio() {
    CrearUsuarioForm formularioUsuario = new CrearUsuarioForm("formularioUsuario", (IModel)new Model((Serializable)this.usuarioModel)) {
        public void accionCierreModalAlerta(AjaxRequestTarget target, boolean exito, String nombreUsuarioCreado) {
          CrearUsuarioFragment.this.cerrar(target);
        }
        
        public void accionCreacionOKUsuario(AjaxRequestTarget target, boolean exito, String nombreUsuarioCreado) {
          CrearUsuarioFragment.this.cerrarTareaEnCreacionUsuarioOK(target, exito, nombreUsuarioCreado);
        }
      };
    queue(new Component[] { (Component)formularioUsuario });
  }
  
  private void initAccionCerrar() {
    queue(new Component[] { (Component)new AjaxLink("cerrar") {
            public void onClick(AjaxRequestTarget target) {
              CrearUsuarioFragment.this.cerrar(target);
            }
          } });
  }
  
  public abstract void cerrar(AjaxRequestTarget paramAjaxRequestTarget);
  
  public abstract void cerrarTareaEnCreacionUsuarioOK(AjaxRequestTarget paramAjaxRequestTarget, boolean paramBoolean, String paramString);
}
