package com.fiduciaria.microapp.page.statefull.gestion.usuario;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.base.NombreAccionesEnum;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.componentes.alerta.ModalAlert;
import com.fiduciaria.microapp.page.statefull.gestion.usuario.model.GestionUsuariosVM;
import com.fiduciaria.microapp.store.model.rastro.RastroAccionPojo;
import com.fiduciaria.microapp.store.model.rastro.TraceVM;
import com.fiduciaria.microapp.store.model.usuario.UsuarioColumnEnum;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojo;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojoVM;
import com.fiduciaria.microapp.store.seguridad.EstadoUsuario;
import com.fiduciaria.microapp.store.seguridad.PrincipalPojo;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.time.Duration;

public class UsuarioMainPanel extends BasePanel {
  @SpringBean
  IGenericHttpClient httpGtwy;
  
  private final IModel<GestionUsuariosVM> modelo;
  
  private class ActualizarComportamiento extends Behavior {
    private ActualizarComportamiento() {}
  }
  
   private final List<PrincipalPojo> listaUsuariosSeleccionados = new ArrayList<>();
  
  private boolean nuevoUsuarioActivado;
  
  private boolean actionPerforming;
  
  private String tipoFiltroUsuario;
  
  private String valorFiltroUsuario;
  
  public UsuarioMainPanel(String id, IModel<GestionUsuariosVM> model) {
     super(id, model);
     this.modelo = model;
     this.nuevoUsuarioActivado = false;
     initSecciones();
     initFormNuevoUsuario();
     initUsuarios();
     initFiltroToolbar();
  }
  
  private void initUsuarios() {
     AjaxLink nuevoUsuarioBtn = new AjaxLink("nuevoUsuarioBtn") {
        public void onClick(AjaxRequestTarget target) {
           UsuarioMainPanel.this.nuevoUsuarioActivado = !UsuarioMainPanel.this.nuevoUsuarioActivado;
           UsuarioMainPanel.this.registrarComportamiento(UsuarioMainPanel.ActualizarComportamiento.class);
        }
      };
     queue(new Component[] { (Component)nuevoUsuarioBtn });
     PageableListView<PrincipalPojo> vistaUsuarios = initListaUsuario("listaUsuarios", "sessionUser");
     queue(new Component[] { (Component)vistaUsuarios });
     AjaxPagingNavigator navigation = new AjaxPagingNavigator("navigation", (IPageable)vistaUsuarios);
     queue(new Component[] { (Component)navigation });
     IModel nombreArch = new IModel() {
        public Object getObject() {
           return "Listado usuarios " + Instant.now().toString() + ".csv";
        }
      };
     queue(new Component[] { (Component)(new DownloadLink("downloadLink", new IModel<File>() {
              private static final long serialVersionUID = 1L;
              
              public File getObject() {
                File tempFile;
                try {
                   tempFile = File.createTempFile("usrsvc-" + UsuarioMainPanel.this.usuarioSesion() + Instant.now().getNano(), ".tmp");
                   CSVPrinter printer = CSVFormat.EXCEL.builder().setHeader(new String[] { "ID", "LOGIN USUARIO", "DESCRIPCIÓN", "CORREO", "ESTADO" }).build().print(tempFile, Charset.defaultCharset());
                   List<PrincipalPojo> listadoUsuarios = UsuarioMainPanel.this.obtenerListadoUsuarios();
                   int idx = 1;
                   for (PrincipalPojo ppal : listadoUsuarios) {
                     printer.printRecord(new Object[] { Integer.valueOf(idx), ppal
                           .getUsuario(), ppal
                           .getPrimerNombre(), ppal
                           .getEmail(), ppal
                           .getEstado() });
                     idx++;
                  } 
                   printer.flush();
                 } catch (IOException e) {
                   throw new RuntimeException(e);
                } 
                 return tempFile;
              }
             })).setCacheDuration(Duration.NONE).setDeleteAfterDownload(true) });
  }
  
  private PageableListView<PrincipalPojo> initListaUsuario(String id, String usuarioActual) {
     final CheckGroup chekUsrGroup = new CheckGroup("chekUsrGroup", this.listaUsuariosSeleccionados);
     queue(new Component[] { (Component)chekUsrGroup });
     chekUsrGroup.setOutputMarkupPlaceholderTag(true);
     chekUsrGroup.add(new Behavior[] { (Behavior)new AjaxFormChoiceComponentUpdatingBehavior() {
            protected void onUpdate(AjaxRequestTarget target) {}
          } });
     LoadableDetachableModel<List<PrincipalPojo>> listaUsuarios = new LoadableDetachableModel<List<PrincipalPojo>>() {
        protected List<PrincipalPojo> load() {
           return UsuarioMainPanel.this.obtenerListadoUsuarios();
        }
      };
     final PageableListView<PrincipalPojo> vistaUsuarios = new PageableListView<PrincipalPojo>(id, (IModel)listaUsuarios, 15L) {
        protected void populateItem(final ListItem<PrincipalPojo> item) {
           item.add(new Component[] { (Component)new Check("checkUser", (IModel)new Model((Serializable)item.getModelObject()), chekUsrGroup) });
           //LambdaModel.of((PrincipalPojo)item.getModelObject()::getUsuario, (PrincipalPojo)item.getModelObject()::setUsuario)
           item.add(new Component[] { (Component)new Label("usuario", () -> {
               String retval = item.getModelObject().getUsuario();
               return retval;
           }) });
           //LambdaModel.of((PrincipalPojo)item.getModelObject()::getPrimerNombre, (PrincipalPojo)item.getModelObject()::setPrimerNombre)
           item.add(new Component[] { (Component)new Label("primerNombre", () -> {
               return item.getModelObject().getPrimerNombre();
           }) });
           
           //LambdaModel.of((PrincipalPojo)item.getModelObject()::getEmail, (PrincipalPojo)item.getModelObject()::setEmail)
           item.add(new Component[] { (Component)new Label("email", () -> {
               return item.getModelObject().getEmail();
           }) });
           WebMarkupContainer estadoLabel = new WebMarkupContainer("estadoLabel") {
              protected void onComponentTag(ComponentTag tag) {
                 super.onComponentTag(tag);
                 String cssClass = (((PrincipalPojo)item.getModelObject()).getEstado() == EstadoUsuario.ACTIVO) ? "pf-c-label pf-m-green pf-m-outline" : "pf-c-label pf-m-red pf-m- outline";
                 tag.put("class", cssClass);
              }
            };
           item.add(new Component[] { (Component)estadoLabel });
           
           //LambdaModel.of((PrincipalPojo)item.getModelObject()::getEstado, (PrincipalPojo)item.getModelObject()::setEstado)
           estadoLabel.add(new Component[] { (Component)new Label("estado", () -> {
               return item.getModelObject().getEstado();
           }) {
                  protected void onComponentTag(ComponentTag tag) {
                     super.onComponentTag(tag);
                     String color = (((PrincipalPojo)item.getModelObject()).getEstado() == EstadoUsuario.ACTIVO) ? "green;" : "red;";
                     tag.put("style", "color:" + color);
                  }
                } });
           item.add(new Component[] { (Component)new AjaxLink("bloquear") {
                  public void onClick(AjaxRequestTarget target) {
                     ModalAlert modalAlerta = new ModalAlert(UsuarioMainPanel.this.getModalContentId()) {
                        public void cerrarModal(AjaxRequestTarget target) {
                           UsuarioMainPanel.this.listaUsuariosSeleccionados.clear();
                           UsuarioMainPanel.this.actionPerforming = false;
                           showModal(false, target);
                           removeModal();
                        }
                      };
                     String rta = UsuarioMainPanel.this.bloquearUsuario((PrincipalPojo)item.getModelObject());
                     RastroAccionPojo rastro = new RastroAccionPojo();
                     rastro.setAccion(NombreAccionesEnum.BLOQUEAR_USUARIO.getAccion());
                     rastro.setTipoAccion(NombreAccionesEnum.BLOQUEAR_USUARIO.getTipoAccion());
                     rastro.setAccionUuid(UUID.randomUUID().toString());
                     rastro.setUsuarioSess(UsuarioMainPanel.this.usuarioSesion());
                     rastro.setIdAccion(((PrincipalPojo)item.getModelObject()).getUsuario());
                     if (rta.equalsIgnoreCase("200")) {
                       modalAlerta.getMensajesSuccess().add("Usuario " + ((PrincipalPojo)item
                           .getModelObject()).getUsuario() + " bloqueado");
                       rastro.setMsg("Bloqueado");
                    } else {
                       modalAlerta.getMensajesError().add("Usuario " + ((PrincipalPojo)item
                           .getModelObject()).getUsuario() + " no bloqueado. Fallo en el servicio");
                       rastro.setMsg("Falló bloqueo");
                    } 
                     TraceVM.instance(UsuarioMainPanel.this.httpGtwy, AuthenticatedWebSession.get().getRoles().hasRole("UCA"), UsuarioMainPanel.this.usuarioSesion()).registraRastro(rastro);
                     UsuarioMainPanel.this.addModal((Component)modalAlerta);
                     UsuarioMainPanel.this.showModal(true, target);
                  }
                } });
           item.add(new Component[] { (Component)new AjaxLink("desbloquear") {
                  public void onClick(AjaxRequestTarget target) {
                     ModalAlert modalAlerta = new ModalAlert(UsuarioMainPanel.this.getModalContentId()) {
                        public void cerrarModal(AjaxRequestTarget target) {
                           UsuarioMainPanel.this.listaUsuariosSeleccionados.clear();
                           UsuarioMainPanel.this.actionPerforming = false;
                           showModal(false, target);
                           removeModal();
                        }
                      };
                     String rta = UsuarioMainPanel.this.desbloquearUsuario((PrincipalPojo)item.getModelObject());
                     RastroAccionPojo rastro = new RastroAccionPojo();
                     rastro.setAccion(NombreAccionesEnum.DESBLOQUEAR_USUARIO.getAccion());
                     rastro.setTipoAccion(NombreAccionesEnum.DESBLOQUEAR_USUARIO.getTipoAccion());
                     rastro.setAccionUuid(UUID.randomUUID().toString());
                     rastro.setUsuarioSess(UsuarioMainPanel.this.usuarioSesion());
                     rastro.setIdAccion(((PrincipalPojo)item.getModelObject()).getUsuario());
                     if (rta.equalsIgnoreCase("200")) {
                       modalAlerta.getMensajesSuccess().add("Usuario " + ((PrincipalPojo)item
                           .getModelObject()).getUsuario() + " desbloqueado");
                       rastro.setMsg("Desbloqueado");
                    } else {
                       modalAlerta.getMensajesError().add("Usuario " + ((PrincipalPojo)item
                           .getModelObject()).getUsuario() + " no desbloqueado. Fallo en el servicio");
                       rastro.setMsg("Falló desbloqueo");
                    } 
                     TraceVM.instance(UsuarioMainPanel.this.httpGtwy, AuthenticatedWebSession.get().getRoles().hasRole("UCA"), UsuarioMainPanel.this.usuarioSesion()).registraRastro(rastro);
                     UsuarioMainPanel.this.addModal((Component)modalAlerta);
                     UsuarioMainPanel.this.showModal(true, target);
                  }
                } });
           item.add(new Component[] { (Component)new AjaxLink("enrolarOTP") {
                  public void onClick(AjaxRequestTarget target) {
                     ModalAlert modalAlerta = new ModalAlert(UsuarioMainPanel.this.getModalContentId()) {
                        public void cerrarModal(AjaxRequestTarget target) {
                           UsuarioMainPanel.this.listaUsuariosSeleccionados.clear();
                           UsuarioMainPanel.this.actionPerforming = false;
                           showModal(false, target);
                           removeModal();
                        }
                      };
                     String rta = UsuarioMainPanel.this.desbloquearUsuario((PrincipalPojo)item.getModelObject());
                     RastroAccionPojo rastro = new RastroAccionPojo();
                     rastro.setAccion(NombreAccionesEnum.ENROLAR_SOFTOKEN.getAccion());
                     rastro.setTipoAccion(NombreAccionesEnum.ENROLAR_SOFTOKEN.getTipoAccion());
                     rastro.setAccionUuid(UUID.randomUUID().toString());
                     rastro.setUsuarioSess(UsuarioMainPanel.this.usuarioSesion());
                     rastro.setIdAccion(((PrincipalPojo)item.getModelObject()).getUsuario());
                     if (rta.equalsIgnoreCase("200")) {
                       modalAlerta.getMensajesSuccess().add("Usuario " + ((PrincipalPojo)item
                           .getModelObject()).getUsuario() + " enrolado");
                       rastro.setMsg("Enrolado en OTP");
                    } else {
                       modalAlerta.getMensajesError().add("Usuario " + ((PrincipalPojo)item
                           .getModelObject()).getUsuario() + " no enrolado. Fallo en el servicio");
                       rastro.setMsg("Falló enrolamiento");
                    } 
                     TraceVM.instance(UsuarioMainPanel.this.httpGtwy, AuthenticatedWebSession.get().getRoles().hasRole("UCA"), UsuarioMainPanel.this.usuarioSesion()).registraRastro(rastro);
                     UsuarioMainPanel.this.addModal((Component)modalAlerta);
                     UsuarioMainPanel.this.showModal(true, target);
                  }
                } });
           item.add(new Component[] { (Component)new AjaxLink("restablecerPass") {
                  public void onClick(AjaxRequestTarget target) {
                     ModalAlert modalAlerta = new ModalAlert(UsuarioMainPanel.this.getModalContentId()) {
                        public void cerrarModal(AjaxRequestTarget target) {
                           UsuarioMainPanel.this.listaUsuariosSeleccionados.clear();
                           UsuarioMainPanel.this.actionPerforming = false;
                           showModal(false, target);
                           removeModal();
                        }
                      };
                     String rta = UsuarioMainPanel.this.restablecerPassword((PrincipalPojo)item.getModelObject());
                     RastroAccionPojo rastro = new RastroAccionPojo();
                     rastro.setAccion(NombreAccionesEnum.RESTABLECER_PASSS.getAccion());
                     rastro.setTipoAccion(NombreAccionesEnum.RESTABLECER_PASSS.getTipoAccion());
                     rastro.setAccionUuid(UUID.randomUUID().toString());
                     rastro.setUsuarioSess(UsuarioMainPanel.this.usuarioSesion());
                     rastro.setIdAccion(((PrincipalPojo)item.getModelObject()).getUsuario());
                     if (rta.equalsIgnoreCase("200")) {
                       modalAlerta.getMensajesSuccess().add("Contraseña restablecida para usuario " + ((PrincipalPojo)item
                           .getModelObject()).getUsuario());
                       rastro.setMsg("Contraseña restablecida");
                    } else {
                       modalAlerta.getMensajesError().add("No pudimos restablecer contraseña a usuario " + ((PrincipalPojo)item
                           .getModelObject()).getUsuario() + ". Fallo en el servicio");
                       rastro.setMsg("Falló acción");
                    } 
                     TraceVM.instance(UsuarioMainPanel.this.httpGtwy, AuthenticatedWebSession.get().getRoles().hasRole("UCA"), UsuarioMainPanel.this.usuarioSesion()).registraRastro(rastro);
                     UsuarioMainPanel.this.addModal((Component)modalAlerta);
                     UsuarioMainPanel.this.showModal(true, target);
                  }
                } });
        }
      };
     vistaUsuarios.setReuseItems(true);
     Form formUsuarios = new Form("formUsuarios") {
        protected void onConfigure() {
           super.onConfigure();
           setVisible(!UsuarioMainPanel.this.actionPerforming);
           vistaUsuarios.removeAll();
        }
      };
     queue(new Component[] { (Component)formUsuarios });
     formUsuarios.add(new Behavior[] { new ActualizarComportamiento() });
     return vistaUsuarios;
  }
  
  private List<PrincipalPojo> obtenerListadoUsuarios() {
     List<PrincipalPojo> response = new ArrayList<>();
     UsuarioPojoVM obj = new UsuarioPojoVM(this.httpGtwy);
     List<Map<UsuarioColumnEnum, String>> listusrs = new ArrayList<>();
     if (AuthenticatedWebSession.get().getRoles().hasRole("UCA")) {
       listusrs = obj.listadoUsuario("allofthem", "UCAUCA" + usuarioSesion());
    } else {
       listusrs = obj.listadoUsuario("allofthem", "TODOS");
    } 
     listusrs.forEach(action -> {
          PrincipalPojo user5 = new PrincipalPojo();
          user5.setUsuario((String)action.get(UsuarioColumnEnum.LOGIN_USUARIO.toString()));
          user5.setPrimerNombre((String)action.get(UsuarioColumnEnum.DESCRIPCION_USUARIO.toString()));
          user5.setEmail((String)action.get(UsuarioColumnEnum.CORREO.toString()));
          user5.setPrimerApellido("");
          if (Objects.isNull(action.get(UsuarioColumnEnum.ESTADO_USUARIO.toString()))) {
            user5.setEstado(EstadoUsuario.NO_DEFINIDO);
          } else if (((String)action.get(UsuarioColumnEnum.ESTADO_USUARIO.toString())).equalsIgnoreCase("I")) {
            user5.setEstado(EstadoUsuario.INACTIVO);
          } else if (((String)action.get(UsuarioColumnEnum.ESTADO_USUARIO.toString())).equalsIgnoreCase("A")) {
            user5.setEstado(EstadoUsuario.ACTIVO);
          } 
          if (!Strings.isEmpty(this.tipoFiltroUsuario) && !Strings.isEmpty(this.valorFiltroUsuario)) {
            if (this.tipoFiltroUsuario.equalsIgnoreCase("Login usuario".toLowerCase()) && !Strings.isEmpty(user5.getUsuario())) {
              if (user5.getUsuario().toLowerCase().contains(this.valorFiltroUsuario.toLowerCase()))
                response.add(user5); 
            } else if (this.tipoFiltroUsuario.equalsIgnoreCase("Descripción".toLowerCase()) && !Strings.isEmpty(user5.getPrimerNombre())) {
              if (user5.getPrimerNombre().toLowerCase().contains(this.valorFiltroUsuario.toLowerCase()))
                response.add(user5); 
            } else if (this.tipoFiltroUsuario.equalsIgnoreCase("Email".toLowerCase()) && !Strings.isEmpty(user5.getEmail()) && user5.getEmail().toLowerCase().contains(this.valorFiltroUsuario.toLowerCase())) {
              response.add(user5);
            } 
          } else {
            response.add(user5);
          } 
        });
     return response;
  }
  
  private void initFiltroToolbar() {
     WebMarkupContainer filtroToolbar = new WebMarkupContainer("filtroToolbar") {
        protected void onConfigure() {
           super.onConfigure();
           setVisible(!UsuarioMainPanel.this.actionPerforming);
        }
      };
     filtroToolbar.setOutputMarkupPlaceholderTag(true);
     queue(new Component[] { (Component)filtroToolbar });
     filtroToolbar.add(new Behavior[] { new ActualizarComportamiento() });
     List<String> tipoDeFiltro = new ArrayList<>();
     tipoDeFiltro.add("Login usuario");
     tipoDeFiltro.add("Descripción");
     tipoDeFiltro.add("Email");
     TextField valorFiltroUsuario = new TextField("valorFiltroUsuario", LambdaModel.of(this::getValorFiltroUsuario, this::setValorFiltroUsuario));
     queue(new Component[] { (Component)valorFiltroUsuario });
     valorFiltroUsuario.setOutputMarkupPlaceholderTag(true);
     valorFiltroUsuario.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
          } });
     DropDownChoice opcionesFiltro = new DropDownChoice("opcionesFiltro", LambdaModel.of(this::getTipoFiltroUsuario, this::setTipoFiltroUsuario), tipoDeFiltro);
     queue(new Component[] { (Component)opcionesFiltro });
     opcionesFiltro.setOutputMarkupPlaceholderTag(true);
     opcionesFiltro.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
          } });
     AjaxLink filtrarUsuarios = new AjaxLink("filtrarUsuarios") {
        public void onClick(AjaxRequestTarget target) {
           UsuarioMainPanel.this.registrarComportamiento(UsuarioMainPanel.ActualizarComportamiento.class);
        }
      };
     queue(new Component[] { (Component)filtrarUsuarios });
  }
  
  private void initSecciones() {
     WebMarkupContainer toolbarSeccion = new WebMarkupContainer("toolbarSeccion") {
        protected void onConfigure() {
           super.onConfigure();
           setVisible(!UsuarioMainPanel.this.actionPerforming);
        }
      };
     queue(new Component[] { (Component)toolbarSeccion });
     toolbarSeccion.setOutputMarkupPlaceholderTag(true);
     toolbarSeccion.add(new Behavior[] { new ActualizarComportamiento() });
     WebMarkupContainer seccionNuevoUsuario = new WebMarkupContainer("seccionNuevoUsuario") {
        protected void onConfigure() {
           super.onConfigure();
           setVisible(UsuarioMainPanel.this.nuevoUsuarioActivado);
        }
      };
     seccionNuevoUsuario.setOutputMarkupPlaceholderTag(true);
     seccionNuevoUsuario.add(new Behavior[] { new ActualizarComportamiento() });
     queue(new Component[] { (Component)seccionNuevoUsuario });
     final WebMarkupContainer seccionUsuarios = new WebMarkupContainer("seccionUsuarios") {
        protected void onConfigure() {
           super.onConfigure();
           setVisible(!UsuarioMainPanel.this.nuevoUsuarioActivado);
        }
      };
     seccionUsuarios.setOutputMarkupPlaceholderTag(true);
     seccionUsuarios.add(new Behavior[] { new ActualizarComportamiento() });
     queue(new Component[] { (Component)seccionUsuarios });
     WebMarkupContainer fragmentActionSection = new WebMarkupContainer("fragmentActionSection");
     queue(new Component[] { (Component)fragmentActionSection });
     AjaxLink enrolarUsuariosOtp = new AjaxLink("enrolarUsuariosOtp") {
        public void onClick(AjaxRequestTarget target) {
           if (!UsuarioMainPanel.this.listaUsuariosSeleccionados.isEmpty()) {
             /*seccionUsuarios.addOrReplace(new Component[] { (Component)new EnrolarUsuariosOTPFragment("fragmentActionSection", "enrolarUsuariosOtpFragment", (MarkupContainer)this.this$0, (IModel)new ListModel(
                      
                       UsuarioMainPanel.access$200(this.this$0))) {
                    public void confirmarEnrolar(AjaxRequestTarget target) {
                       ModalAlert modalAlerta = new ModalAlert(UsuarioMainPanel.this.getModalContentId()) {
                          public void cerrarModal(AjaxRequestTarget target) {
                             UsuarioMainPanel.this.listaUsuariosSeleccionados.clear();
                             UsuarioMainPanel.this.actionPerforming = false;
                             showModal(false, target);
                             removeModal();
                             seccionUsuarios.addOrReplace(new Component[] { (Component)new WebMarkupContainer("fragmentActionSection") });
                             registrarComportamiento(UsuarioMainPanel.ActualizarComportamiento.class);
                             target.add(new Component[] { (Component)this.this$2.this$1.val$seccionUsuarios });
                          }
                        };
                       UsuarioMainPanel.this.listaUsuariosSeleccionados.forEach(ppl -> {
                            RastroAccionPojo rastro = new RastroAccionPojo();
                            rastro.setAccion(NombreAccionesEnum.ENROLAR_SOFTOKEN.getAccion());
                            rastro.setTipoAccion(NombreAccionesEnum.ENROLAR_SOFTOKEN.getTipoAccion());
                            rastro.setAccionUuid(UUID.randomUUID().toString());
                            rastro.setUsuarioSess(UsuarioMainPanel.this.usuarioSesion());
                            rastro.setIdAccion(ppl.getUsuario());
                            if (UsuarioMainPanel.this.enrolarUsuarioOTP(ppl).equalsIgnoreCase("200")) {
                              modalAlerta.getMensajesSuccess().add("Usuario " + ppl.getUsuario() + " registrado en OTP");
                              rastro.setMsg("Enrolado en OTP");
                            } else {
                              modalAlerta.getMensajesError().add("Usuario " + ppl.getUsuario() + " no registrado en OTP. Fallo en el servicio");
                              rastro.setMsg("Falló enrolamiento");
                            } 
                            TraceVM.instance(UsuarioMainPanel.this.httpGtwy, AuthenticatedWebSession.get().getRoles().hasRole("UCA"), UsuarioMainPanel.this.usuarioSesion()).registraRastro(rastro);
                          });
                       UsuarioMainPanel.this.addModal((Component)modalAlerta);
                       UsuarioMainPanel.this.showModal(true, target);
                    }
                    
                    public void cancelarEnrolar(AjaxRequestTarget target) {
                       UsuarioMainPanel.this.listaUsuariosSeleccionados.clear();
                       UsuarioMainPanel.this.actionPerforming = false;
                       seccionUsuarios.addOrReplace(new Component[] { (Component)new WebMarkupContainer("fragmentActionSection") });
                       UsuarioMainPanel.this.registrarComportamiento(UsuarioMainPanel.ActualizarComportamiento.class);
                       target.add(new Component[] { (Component)this.this$1.val$seccionUsuarios });
                    }
                  } });*/
             UsuarioMainPanel.this.actionPerforming = true;
             UsuarioMainPanel.this.registrarComportamiento(UsuarioMainPanel.ActualizarComportamiento.class);
             target.add(new Component[] { (Component)seccionUsuarios });
          } 
        }
      };
     queue(new Component[] { (Component)enrolarUsuariosOtp });
     AjaxLink desbloquearUsuarios = new AjaxLink("desbloquearUsuarios") {
        public void onClick(AjaxRequestTarget target) {
           if (!UsuarioMainPanel.this.listaUsuariosSeleccionados.isEmpty()) {
             /*seccionUsuarios.addOrReplace(new Component[] { (Component)new DesbloquearUsuariosFragment("fragmentActionSection", "desbloquearUsuariosFragment", (MarkupContainer)this.this$0, (IModel)new ListModel(
                      
                       UsuarioMainPanel.access$200(this.this$0))) {
                    public void confirmarDesbloquear(AjaxRequestTarget target) {
                       ModalAlert modalAlerta = new ModalAlert(UsuarioMainPanel.this.getModalContentId()) {
                          public void cerrarModal(AjaxRequestTarget target) {
                             UsuarioMainPanel.this.listaUsuariosSeleccionados.clear();
                             UsuarioMainPanel.this.actionPerforming = false;
                             showModal(false, target);
                             removeModal();
                             seccionUsuarios.addOrReplace(new Component[] { (Component)new WebMarkupContainer("fragmentActionSection") });
                             registrarComportamiento(UsuarioMainPanel.ActualizarComportamiento.class);
                             target.add(new Component[] { (Component)seccionUsuarios });
                          }
                        };
                       UsuarioMainPanel.this.listaUsuariosSeleccionados.forEach(ppl -> {
                            RastroAccionPojo rastro = new RastroAccionPojo();
                            rastro.setAccion(NombreAccionesEnum.DESBLOQUEAR_USUARIO.getAccion());
                            rastro.setTipoAccion(NombreAccionesEnum.DESBLOQUEAR_USUARIO.getTipoAccion());
                            rastro.setAccionUuid(UUID.randomUUID().toString());
                            rastro.setUsuarioSess(UsuarioMainPanel.this.usuarioSesion());
                            rastro.setIdAccion(ppl.getUsuario());
                            if (UsuarioMainPanel.this.desbloquearUsuario(ppl).equalsIgnoreCase("200")) {
                              modalAlerta.getMensajesSuccess().add("Usuario " + ppl.getUsuario() + " desbloqueado");
                              rastro.setMsg("Desbloqueado");
                            } else {
                              modalAlerta.getMensajesError().add("Usuario " + ppl.getUsuario() + " no desbloqueado. Fallo en el servicio");
                              rastro.setMsg("Falló desbloqueo");
                            } 
                            TraceVM.instance(UsuarioMainPanel.this.httpGtwy, AuthenticatedWebSession.get().getRoles().hasRole("UCA"), UsuarioMainPanel.this.usuarioSesion()).registraRastro(rastro);
                          });
                       UsuarioMainPanel.this.addModal((Component)modalAlerta);
                       UsuarioMainPanel.this.showModal(true, target);
                    }
                    
                    public void cancelarDesbloquear(AjaxRequestTarget target) {
                       UsuarioMainPanel.this.listaUsuariosSeleccionados.clear();
                       UsuarioMainPanel.this.actionPerforming = false;
                       seccionUsuarios.addOrReplace(new Component[] { (Component)new WebMarkupContainer("fragmentActionSection") });
                       UsuarioMainPanel.this.registrarComportamiento(UsuarioMainPanel.ActualizarComportamiento.class);
                       target.add(new Component[] { (Component)this.this$1.val$seccionUsuarios });
                    }
                  } });*/
             UsuarioMainPanel.this.actionPerforming = true;
             UsuarioMainPanel.this.registrarComportamiento(UsuarioMainPanel.ActualizarComportamiento.class);
             target.add(new Component[] { (Component)seccionUsuarios });
          } 
        }
      };
     queue(new Component[] { (Component)desbloquearUsuarios });
     AjaxLink bloquearUsuarios = new AjaxLink("bloquearUsuarios") {
        public void onClick(AjaxRequestTarget target) {
           if (!UsuarioMainPanel.this.listaUsuariosSeleccionados.isEmpty()) {
             /*seccionUsuarios.addOrReplace(new Component[] { (Component)new BloquearUsuariosFragment("fragmentActionSection", "bloquearUsuariosFragment", (MarkupContainer)this.this$0, (IModel)new ListModel(
                      
                       UsuarioMainPanel.access$200(this.this$0))) {
                    public void confirmarBloquear(AjaxRequestTarget target) {
                       ModalAlert modalAlerta = new ModalAlert(UsuarioMainPanel.this.getModalContentId()) {
                          public void cerrarModal(AjaxRequestTarget target) {
                             UsuarioMainPanel.this.listaUsuariosSeleccionados.clear();
                             UsuarioMainPanel.this.actionPerforming = false;
                             showModal(false, target);
                             removeModal();
                             seccionUsuarios.addOrReplace(new Component[] { (Component)new WebMarkupContainer("fragmentActionSection") });
                             registrarComportamiento(UsuarioMainPanel.ActualizarComportamiento.class);
                             target.add(new Component[] { (Component)this.this$2.this$1.val$seccionUsuarios });
                          }
                        };
                       UsuarioMainPanel.this.listaUsuariosSeleccionados.forEach(ppl -> {
                            RastroAccionPojo rastro = new RastroAccionPojo();
                            rastro.setAccion(NombreAccionesEnum.BLOQUEAR_USUARIO.getAccion());
                            rastro.setTipoAccion(NombreAccionesEnum.BLOQUEAR_USUARIO.getTipoAccion());
                            rastro.setAccionUuid(UUID.randomUUID().toString());
                            rastro.setUsuarioSess(UsuarioMainPanel.this.usuarioSesion());
                            rastro.setIdAccion(ppl.getUsuario());
                            if (UsuarioMainPanel.this.bloquearUsuario(ppl).equalsIgnoreCase("200")) {
                              modalAlerta.getMensajesSuccess().add("Usuario " + ppl.getUsuario() + " bloqueado");
                              rastro.setMsg("Bloqueado");
                            } else {
                              modalAlerta.getMensajesError().add("Usuario " + ppl.getUsuario() + " no bloqueado. Fallo en el servicio");
                              rastro.setMsg("Falló bloqueo");
                            } 
                            TraceVM.instance(UsuarioMainPanel.this.httpGtwy, AuthenticatedWebSession.get().getRoles().hasRole("UCA"), UsuarioMainPanel.this.usuarioSesion()).registraRastro(rastro);
                          });
                       UsuarioMainPanel.this.addModal((Component)modalAlerta);
                       UsuarioMainPanel.this.showModal(true, target);
                    }
                    
                    public void cancelarBloquear(AjaxRequestTarget target) {
                       UsuarioMainPanel.this.listaUsuariosSeleccionados.clear();
                       UsuarioMainPanel.this.actionPerforming = false;
                       seccionUsuarios.addOrReplace(new Component[] { (Component)new WebMarkupContainer("fragmentActionSection") });
                       UsuarioMainPanel.this.registrarComportamiento(UsuarioMainPanel.ActualizarComportamiento.class);
                       target.add(new Component[] { (Component)this.this$1.val$seccionUsuarios });
                    }
                  } });*/
             UsuarioMainPanel.this.actionPerforming = true;
             UsuarioMainPanel.this.registrarComportamiento(UsuarioMainPanel.ActualizarComportamiento.class);
             target.add(new Component[] { (Component)seccionUsuarios });
          } 
        }
      };
     queue(new Component[] { (Component)bloquearUsuarios });
  }
  
  private void initFormNuevoUsuario() {
     queue(new Component[] { (Component)new AjaxLink("backtoUsuarios") {
            public void onClick(AjaxRequestTarget target) {
               UsuarioMainPanel.this.nuevoUsuarioActivado = !UsuarioMainPanel.this.nuevoUsuarioActivado;
               UsuarioMainPanel.this.registrarComportamiento(UsuarioMainPanel.ActualizarComportamiento.class);
            }
          } });
     CrearUsuarioForm nuevoUsuario = new CrearUsuarioForm("nuevoUsuario");
     queue(new Component[] { (Component)nuevoUsuario });
  }
  
  public String getTipoFiltroUsuario() {
     return this.tipoFiltroUsuario;
  }
  
  public void setTipoFiltroUsuario(String tipoFiltroUsuario) {
     this.tipoFiltroUsuario = tipoFiltroUsuario;
  }
  
  public String getValorFiltroUsuario() {
     return this.valorFiltroUsuario;
  }
  
  public void setValorFiltroUsuario(String valorFiltroUsuario) {
     this.valorFiltroUsuario = valorFiltroUsuario;
  }
  
  private String bloquearUsuario(PrincipalPojo ppl) {
     UsuarioPojoVM userVM = new UsuarioPojoVM(this.httpGtwy);
     UsuarioPojo usuario = new UsuarioPojo();
     usuario.setIdPrincipal(ppl.getUsuario());
     usuario.setUsuario(ppl.getUsuario());
     usuario.setFidutoken((String)AuthenticatedWebSession.get().getAttribute("fidutoken"));
     String origen = getString("app.email.correo.origen");
     String subject = getString("app.email.correo.reset.subject");
     return userVM.bloquearUsuario(usuario, usuarioSesion(), subject, origen);
  }
  
  private String desbloquearUsuario(PrincipalPojo ppl) {
     UsuarioPojoVM userVM = new UsuarioPojoVM(this.httpGtwy);
     UsuarioPojo usuario = new UsuarioPojo();
     usuario.setIdPrincipal(ppl.getUsuario());
     usuario.setUsuario(ppl.getUsuario());
     usuario.setFidutoken((String)AuthenticatedWebSession.get().getAttribute("fidutoken"));
     String origen = getString("app.email.correo.origen");
     String subject = getString("app.email.correo.reset.subject");
     return userVM.desbloquearUsuario(usuario, usuarioSesion(), subject, origen);
  }
  
  private String enrolarUsuarioOTP(PrincipalPojo ppl) {
     UsuarioPojoVM userVM = new UsuarioPojoVM(this.httpGtwy);
     UsuarioPojo usuario = new UsuarioPojo();
     usuario.setIdPrincipal(ppl.getUsuario());
     usuario.setUsuario(ppl.getUsuario());
     usuario.setFidutoken((String)AuthenticatedWebSession.get().getAttribute("fidutoken"));
     String origen = getString("app.email.correo.origen");
     String subject = getString("app.email.correo.reset.subject");
     return userVM.enrolarOTP(usuario, usuarioSesion(), subject, origen);
  }
  
  private String restablecerPassword(PrincipalPojo ppl) {
     UsuarioPojoVM userVM = new UsuarioPojoVM(this.httpGtwy);
     UsuarioPojo usuario = new UsuarioPojo();
     usuario.setIdPrincipal(ppl.getUsuario());
     usuario.setUsuario(ppl.getUsuario());
     usuario.setCorreoElectronico(ppl.getEmail());
     usuario.setFidutoken((String)AuthenticatedWebSession.get().getAttribute("fidutoken"));
     String origen = getString("app.email.correo.origen");
     String subject = getString("app.email.correo.reset.subject");
     return userVM.restablecerPassword(usuario, usuarioSesion(), subject, origen);
  }
}