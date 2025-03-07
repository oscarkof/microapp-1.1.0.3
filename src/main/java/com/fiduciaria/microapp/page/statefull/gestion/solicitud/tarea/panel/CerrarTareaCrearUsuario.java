package com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.panel;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.EnumCampoAdjuntoSolicitud;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojoVM;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.model.TareaPojoVM;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.util.MapModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.time.Duration;

public abstract class CerrarTareaCrearUsuario extends BasePanel {
  @SpringBean
  IGenericHttpClient httpgtwy;
  
  private final IModel<TareaPojo> modelo;
  
  private final TareaPojo modeloObj;
  
  private final WebMarkupContainer fragmentPanel = new WebMarkupContainer("fragmentPanel");
  
  private final String fragmentContentId = "content";
  
  private boolean accionFragmentActivo;
  
  private final class UpdOnFragmentAction extends Behavior {
    private UpdOnFragmentAction() {}
  }
  
  public CerrarTareaCrearUsuario(String id, IModel<TareaPojo> model) {
    super(id, model);
    this.modelo = model;
    this.modeloObj = (TareaPojo)this.modelo.getObject();
  }
  
  protected void onInitialize() {
    super.onInitialize();
    this.fragmentPanel.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)this.fragmentPanel });
    this.fragmentPanel.add(new Behavior[] { new UpdOnFragmentAction() });
    this.fragmentPanel.addOrReplace(new Component[] { (Component)new WebMarkupContainer("content") });
    WebMarkupContainer detalleTareaPanel = new WebMarkupContainer("detalleTareaPanel") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!CerrarTareaCrearUsuario.this.accionFragmentActivo);
        }
      };
    queue(new Component[] { (Component)detalleTareaPanel });
    detalleTareaPanel.setOutputMarkupPlaceholderTag(true);
    detalleTareaPanel.add(new Behavior[] { new UpdOnFragmentAction() });
    Label tituloTarea = new Label("tituloTarea", LambdaModel.of(this.modeloObj::getTitulo));
    queue(new Component[] { (Component)tituloTarea });
    final Form actualizaTareaForm = new Form("actualizaTareaForm") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!CerrarTareaCrearUsuario.this.modeloObj.getEstado().trim().equalsIgnoreCase("cerrada"));
        }
      };
    queue(new Component[] { (Component)actualizaTareaForm });
    actualizaTareaForm.setOutputMarkupPlaceholderTag(true);
    AjaxLink cerrarModal = new AjaxLink("cerrarModal") {
        public void onClick(AjaxRequestTarget target) {
          CerrarTareaCrearUsuario.this.cerrarModalCallback(target);
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
    estadoTarea.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "Estados de Tarea", "", "Seleccione el estado de la tarea para su grabado en el sistemas.") });
    estadoTarea.setLabel((IModel)new ResourceModel("formulario.campo.estado.label", ""));
    final TextArea observacionTarea = new TextArea("observacionTarea", LambdaModel.of(this.modeloObj::getComentario, this.modeloObj::setComentario));
    observacionTarea.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)observacionTarea });
    observacionTarea.setRequired(true);
    observacionTarea.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)observacionTarea });
            }
          } });
    observacionTarea.setLabel((IModel)new ResourceModel("formulario.campo.observaciones.label", ""));
    observacionTarea.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "Observaciones adicionales", "", "Ingrese notas complementarias o descripcide las tareas.") });
    AjaxLink cancelarBtn = new AjaxLink("cancelarBtn") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!CerrarTareaCrearUsuario.this.modeloObj.getEstado().trim().equalsIgnoreCase("cerrada"));
        }
        
        public void onClick(AjaxRequestTarget target) {
          CerrarTareaCrearUsuario.this.cerrarModalCallback(target);
        }
      };
    queue(new Component[] { (Component)cancelarBtn });
    AjaxSubmitLink guardarTarea = new AjaxSubmitLink("guardarTarea", actualizaTareaForm) {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!CerrarTareaCrearUsuario.this.modeloObj.getEstado().trim().equalsIgnoreCase("cerrada"));
        }
        
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          TareaPojoVM trObj = new TareaPojoVM(CerrarTareaCrearUsuario.this.httpgtwy);
          trObj.guardarTarea(CerrarTareaCrearUsuario.this.modeloObj);
          CerrarTareaCrearUsuario.this.cerrarModalCallback(target);
        }
        
        protected void onError(AjaxRequestTarget target) {
          super.onError(target);
          target.add(new Component[] { (Component)actualizaTareaForm });
        }
      };
    queue(new Component[] { (Component)guardarTarea });
    initDetalleSolicitud();
    initAccionesTarea();
  }
  
  private void initAccionesTarea() {
    queue(new Component[] { (Component)new AjaxLink("crearUsuario") {
            protected void onConfigure() {
              super.onConfigure();
              setVisible((CerrarTareaCrearUsuario.this
                  .modeloObj.getSolicitudRelacionada().getTipoSolicitud().equalsIgnoreCase("Registro usuario") || CerrarTareaCrearUsuario.this
                  .modeloObj.getSolicitudRelacionada().getTipoSolicitud().equalsIgnoreCase("Registro cliente") || CerrarTareaCrearUsuario.this
                  .modeloObj.getSolicitudRelacionada().getTipoSolicitud().equalsIgnoreCase("Registro fiduciaria")));
            }
            
            public void onClick(AjaxRequestTarget target) {
              CerrarTareaCrearUsuario.this.accionFragmentActivo = true;
              /*CerrarTareaCrearUsuario.this.fragmentPanel.addOrReplace(new Component[] { (Component)new CrearUsuarioFragment("content", "fragmentoCrearUsuario", (MarkupContainer)this.this$0, 
                      CerrarTareaCrearUsuario.access$300(this.this$0)) {
                      public void cerrar(AjaxRequestTarget target) {
                        CerrarTareaCrearUsuario.this.accionFragmentActivo = false;
                        CerrarTareaCrearUsuario.this.setFramentPanelDefault(target);
                        CerrarTareaCrearUsuario.this.registrarComportamiento(CerrarTareaCrearUsuario.UpdOnFragmentAction.class);
                        CerrarTareaCrearUsuario.this.cerrarModalCallback(target);
                      }
                      
                      public void cerrarTareaEnCreacionUsuarioOK(AjaxRequestTarget target, boolean creado, String nombreUsuario) {
                        TareaPojoVM trObj = new TareaPojoVM(CerrarTareaCrearUsuario.this.httpgtwy);
                        CerrarTareaCrearUsuario.this.modeloObj.setComentario("AUTO :: Usuario creado exitosamente");
                        CerrarTareaCrearUsuario.this.modeloObj.setConcepto("Usuario creado");
                        CerrarTareaCrearUsuario.this.modeloObj.setResumen(nombreUsuario);
                        CerrarTareaCrearUsuario.this.modeloObj.setEstado("Cerrada");
                        trObj.guardarTarea(CerrarTareaCrearUsuario.this.modeloObj);
                      }
                    } });
              CerrarTareaCrearUsuario.this.registrarComportamiento(CerrarTareaCrearUsuario.UpdOnFragmentAction.class);*/
            }
          } });
  }
  
  private void setFramentPanelDefault(AjaxRequestTarget target) {
    this.fragmentPanel.addOrReplace(new Component[] { (Component)new WebMarkupContainer("content") });
    target.add(new Component[] { (Component)this.fragmentPanel });
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
    Label primerNombre = new Label("primerNombre", (IModel)Model.of(getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getNombreSolicita())));
    queue(new Component[] { (Component)primerNombre });
    Label segundoNombre = new Label("segundoNombre", (IModel)Model.of(
          getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getSegundoNombre())));
    queue(new Component[] { (Component)segundoNombre });
    Label primerApellido = new Label("primerApellido", (IModel)Model.of(
          getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getApellidoSolicita())));
    queue(new Component[] { (Component)primerApellido });
    Label segundoApellido = new Label("segundoApellido", (IModel)Model.of(
          getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getSegundoApellido())));
    queue(new Component[] { (Component)segundoApellido });
    Label numeroCelular = new Label("numeroCelular", (IModel)Model.of(
          getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getCelularSolicita())));
    queue(new Component[] { (Component)numeroCelular });
    Label direccionSolicita = new Label("direccionSolicita", (IModel)Model.of(
          getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getDireccion())));
    queue(new Component[] { (Component)direccionSolicita });
    Label correoSolicita = new Label("correoSolicita", (IModel)Model.of(
          getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getCorreoSolicita())));
    queue(new Component[] { (Component)correoSolicita });
    Label rolFideicomiso = new Label("rolFideicomiso", (IModel)Model.of(
          getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getRolFideicomiso())));
    queue(new Component[] { (Component)rolFideicomiso });
    Label rol = new Label("rol", (IModel)Model.of(
          getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getRol())));
    queue(new Component[] { (Component)rol });
    Label rolFideicomisoOtro = new Label("rolFideicomisoOtro", (IModel)Model.of(
          getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getRolFideicomisoOtro())));
    queue(new Component[] { (Component)rolFideicomisoOtro });
    Label condiManejo = new Label("condiManejo", (IModel)Model.of(
          getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getCondiManejo())));
    queue(new Component[] { (Component)condiManejo });
    Label condiManejoRolcargaOtra = new Label("condiManejoRolcargaOtra", (IModel)Model.of(
          getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getCondiManejoRolcargaOtra())));
    queue(new Component[] { (Component)condiManejoRolcargaOtra });
    Label conUsrAuthz = new Label("conUsrAuthz", (IModel)Model.of(
          getStringNoNull(this.modeloObj.getSolicitudRelacionada()
            .getConUsrAuthz())));
    queue(new Component[] { (Component)conUsrAuthz });
    initVistaDescargaArchivo();
  }
  
  private void initVistaDescargaArchivo() {
    RefreshingView<Map<String, String>> listaArchivosDescarga = new RefreshingView<Map<String, String>>("adjuntos") {
        protected Iterator<IModel<Map<String, String>>> getItemModels() {
          SolicitudPojo selected = CerrarTareaCrearUsuario.this.modeloObj.getSolicitudRelacionada();
          SolicitudPojoVM viewModel = new SolicitudPojoVM(CerrarTareaCrearUsuario.this.httpgtwy);
          List<Map<String, String>> adjuntos = viewModel.listaAjuntosSolicitude(selected.getRadicadoId().toString(), "all", true);
          List<IModel<Map<String, String>>> response = new ArrayList<>();
          adjuntos.forEach(action -> response.add(new MapModel(action)));
          return response.iterator();
        }
        
        protected void populateItem(final Item<Map<String, String>> item) {
          Label nombre = new Label("nombre", (IModel)Model.of(
                String.valueOf(((Map)item.getModelObject()).get(EnumCampoAdjuntoSolicitud.NOMBRE.toString()))));
          /*DownloadLink descarga = (new DownloadLink("descarga", new IModel<File>() {
                private static final long serialVersionUID = 1L;
                
                public File getObject() {
                  File tempFile;
                  try {
                    tempFile = File.createTempFile(String.valueOf(((Map)item.getModelObject()).get(EnumCampoAdjuntoSolicitud.NOMBRE.toString())), "");
                    SolicitudPojoVM viewModel = new SolicitudPojoVM(CerrarTareaCrearUsuario.this.httpgtwy);
                    List<Map<String, String>> adjuntos = viewModel.listaAjuntosSolicitude(CerrarTareaCrearUsuario.this.modeloObj.getSolicitudRelacionada().getRadicadoId().toString(), String.valueOf(((Map)item.getModelObject()).get(EnumCampoAdjuntoSolicitud.CONSECUTIVO_ADJUNTO.toString())), false);
                    byte[] adjuntobin = new byte[0];
                    if (!adjuntos.isEmpty())
                      adjuntobin = Base64.getDecoder().decode((String)((Map)adjuntos.get(0)).get(EnumCampoAdjuntoSolicitud.ADJUNTO.toString())); 
                    InputStream data = new ByteArrayInputStream(adjuntobin);
                    Files.writeTo(tempFile, data);
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  } 
                  return tempFile;
                }
              }
                  String.valueOf(((Map)item.getModelObject()).get(EnumCampoAdjuntoSolicitud.NOMBRE.toString())))).setCacheDuration(Duration.NONE).setDeleteAfterDownload(true);*/
          //item.add(new Component[] { (Component)descarga });
          //descarga.add(new Component[] { (Component)nombre });
        }
      };
    queue(new Component[] { (Component)listaArchivosDescarga });
  }
  
  private String getStringNoNull(String input) {
    String response = Strings.isEmpty(input) ? "" : input;
    if (response.equalsIgnoreCase("null"))
      response = ""; 
    return response;
  }
  
  protected abstract void cerrarModalCallback(AjaxRequestTarget paramAjaxRequestTarget);
}
