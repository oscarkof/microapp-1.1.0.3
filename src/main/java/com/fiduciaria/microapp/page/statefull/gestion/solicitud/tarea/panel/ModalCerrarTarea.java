package com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.panel;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojoVM;
import com.fiduciaria.microapp.store.model.usuario.UsuarioColumnEnum;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojoVM;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public abstract class ModalCerrarTarea extends BasePanel {
  @SpringBean
  IGenericHttpClient httpgtwy;
  
  private final IModel<TareaPojo> modelo;
  
  private final String nombreUsuarioAsigna;
  
  private final TareaPojo modeloObj;
  
  public ModalCerrarTarea(String id, IModel<TareaPojo> model) {
    super(id, model);
    this.modelo = model;
    this.modeloObj = (TareaPojo)this.modelo.getObject();
    this.nombreUsuarioAsigna = getNombreUsuario(getSession().getAttribute("username").toString());
  }
  
  private String getNombreUsuario(String loginName) {
    StringBuilder response = new StringBuilder();
    UsuarioPojoVM usrObj = new UsuarioPojoVM(this.httpgtwy);
    List<Map<UsuarioColumnEnum, String>> usrs = usrObj.listadoUsuario(loginName, "TODOS");
    if (!usrs.isEmpty())
      usrs.forEach(action -> response.append((String)action.get(UsuarioColumnEnum.DESCRIPCION_USUARIO.toString()))); 
    return response.toString();
  }
  
  protected void onInitialize() {
    super.onInitialize();
    Label tituloTarea = new Label("tituloTarea", LambdaModel.of(this.modeloObj::getTitulo));
    queue(new Component[] { (Component)tituloTarea });
    final Form actualizaTareaForm = new Form("actualizaTareForm") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!ModalCerrarTarea.this.modeloObj.getEstado().equalsIgnoreCase("Cerrada"));
        }
      };
    queue(new Component[] { (Component)actualizaTareaForm });
    actualizaTareaForm.setOutputMarkupPlaceholderTag(true);
    Label nombreUsuarioAsignaLbl = new Label("nombreUsuarioAsigna", (IModel)Model.of(this.nombreUsuarioAsigna));
    queue(new Component[] { (Component)nombreUsuarioAsignaLbl });
    AjaxLink cerrarModal = new AjaxLink("cerrarModal") {
        public void onClick(AjaxRequestTarget target) {
          ModalCerrarTarea.this.cerrarModalCallback(target);
        }
      };
    queue(new Component[] { (Component)cerrarModal });
    List<String> listaEstados = new ArrayList<>();
    listaEstados.add("Cerrada");
    listaEstados.add("Asignada");
    DropDownChoice<String> estadoTarea = new DropDownChoice("listaEstadoTarea", LambdaModel.of(this.modeloObj::getEstado, this.modeloObj::setEstado), listaEstados);
    queue(new Component[] { (Component)estadoTarea });
    estadoTarea.setOutputMarkupPlaceholderTag(true);
    estadoTarea.setRequired(true);
    estadoTarea.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
          } });
    final TextArea observacionTarea = new TextArea("observacionTarea", LambdaModel.of(this.modeloObj::getComentario, this.modeloObj::setComentario));
    observacionTarea.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)observacionTarea });
    observacionTarea.setRequired(true);
    observacionTarea.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)observacionTarea });
            }
          } });
    AjaxLink cancelarBtn = new AjaxLink("cancelarBtn") {
        public void onClick(AjaxRequestTarget target) {
          ModalCerrarTarea.this.cerrarModalCallback(target);
        }
      };
    queue(new Component[] { (Component)cancelarBtn });
    AjaxSubmitLink guardarTarea = new AjaxSubmitLink("guardarTarea", actualizaTareaForm) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          TareaPojoVM trObj = new TareaPojoVM(ModalCerrarTarea.this.httpgtwy);
          trObj.guardarTarea(ModalCerrarTarea.this.modeloObj);
          ModalCerrarTarea.this.cerrarModalCallback(target);
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)actualizaTareaForm });
        }
      };
    queue(new Component[] { (Component)guardarTarea });
    initDetalleSolicitud();
  }
  
  private void initDetalleSolicitud() {
    Label codigoNegocio = new Label("codigoNegocio", (IModel)Model.of(this.modeloObj.getSolicitudRelacionada()
          .getIdRefNegocio()));
    queue(new Component[] { (Component)codigoNegocio });
    Label nombreNegocio = new Label("nombreNegocio", (IModel)Model.of(this.modeloObj.getSolicitudRelacionada()
          .getNombreRefNegocio()));
    queue(new Component[] { (Component)nombreNegocio });
    Label tipoSolicitud = new Label("tipoSolicitud", (IModel)Model.of(this.modeloObj.getSolicitudRelacionada()
          .getTipoSolicitud()));
    queue(new Component[] { (Component)tipoSolicitud });
    String nombresVal = this.modeloObj.getSolicitudRelacionada().getNombreSolicita() + " " + this.modeloObj.getSolicitudRelacionada().getSegundoNombre();
    Label nombres = new Label("nombres", (IModel)Model.of(nombresVal));
    queue(new Component[] { (Component)nombres });
    String apellidosVal = this.modeloObj.getSolicitudRelacionada().getApellidoSolicita() + " " + this.modeloObj.getSolicitudRelacionada().getSegundoApellido();
    Label apellidos = new Label("apellidos", (IModel)Model.of(apellidosVal));
    queue(new Component[] { (Component)apellidos });
    Label numeroCelular = new Label("numeroCelular", (IModel)Model.of(this.modeloObj
          .getSolicitudRelacionada()
          .getCelularSolicita()));
    queue(new Component[] { (Component)numeroCelular });
    Label direccionSolicita = new Label("direccionSolicita", (IModel)Model.of(this.modeloObj
          .getSolicitudRelacionada()
          .getDireccion()));
    queue(new Component[] { (Component)direccionSolicita });
    Label correoSolicita = new Label("correoSolicita", (IModel)Model.of(this.modeloObj
          .getSolicitudRelacionada()
          .getCorreoSolicita()));
    queue(new Component[] { (Component)correoSolicita });
    Label rolFideicomiso = new Label("rolFideicomiso", (IModel)Model.of(this.modeloObj
          .getSolicitudRelacionada()
          .getRolFideicomiso()));
    queue(new Component[] { (Component)rolFideicomiso });
    Label rol = new Label("rol", (IModel)Model.of(this.modeloObj
          .getSolicitudRelacionada()
          .getRol()));
    queue(new Component[] { (Component)rol });
    Label rolFideicomisoOtro = new Label("rolFideicomisoOtro", (IModel)Model.of(this.modeloObj
          .getSolicitudRelacionada()
          .getRolFideicomisoOtro()));
    queue(new Component[] { (Component)rolFideicomisoOtro });
    Label condiManejo = new Label("condiManejo", (IModel)Model.of(this.modeloObj
          .getSolicitudRelacionada()
          .getCondiManejo()));
    queue(new Component[] { (Component)condiManejo });
    Label condiManejoRolcargaOtra = new Label("condiManejoRolcargaOtra", (IModel)Model.of(this.modeloObj
          .getSolicitudRelacionada()
          .getCondiManejoRolcargaOtra()));
    queue(new Component[] { (Component)condiManejoRolcargaOtra });
    Label conUsrAuthz = new Label("conUsrAuthz", (IModel)Model.of(this.modeloObj
          .getSolicitudRelacionada()
          .getConUsrAuthz()));
    queue(new Component[] { (Component)conUsrAuthz });
  }
  
  protected abstract void cerrarModalCallback(AjaxRequestTarget paramAjaxRequestTarget);
}
