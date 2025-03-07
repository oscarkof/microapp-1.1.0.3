package com.fiduciaria.microapp.page.statefull.gestion.usuario.permisos;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import com.fiduciaria.microapp.store.model.negocio.NegocioVM;
import com.fiduciaria.microapp.store.model.rastro.RastroAccionPojo;
import com.fiduciaria.microapp.store.model.rastro.TraceVM;
import com.fiduciaria.microapp.store.model.referunica.ReferenciaUnicaPojo;
import com.fiduciaria.microapp.store.seguridad.PrincipalPojo;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PermisoUsuarioReferenciaUnica extends BasePanel {
  @SpringBean
  IGenericHttpClient httpGtwy;
  
  private final NegocioVM negocioModel;
  
  private final PrincipalPojo usuarioModel;
  
  private PerfilUsuarioAlfaEnum perfilUsuarioSel;
  
  private final List<NegocioPojo> referenciasPermisosNuevos = new ArrayList<>();
  
  public PermisoUsuarioReferenciaUnica(String id, IModel<PrincipalPojo> model) {
    super(id, model);
    this.usuarioModel = (PrincipalPojo)model.getObject();
    this.negocioModel = new NegocioVM(this.httpGtwy);
    if (Objects.nonNull(this.usuarioModel.getRolFondosAlfa()) && 
      !this.usuarioModel.getRolFondosAlfa().isEmpty())
      this.perfilUsuarioSel = PerfilUsuarioAlfaEnum.valueOf(this.usuarioModel.getRolFondosAlfa().split(";")[0]); 
  }
  
  protected void onInitialize() {
    super.onInitialize();
    final WebMarkupContainer seccionPermisoReferencias = new WebMarkupContainer("seccionPermisoReferencias");
    seccionPermisoReferencias.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)seccionPermisoReferencias });
    WebMarkupContainer instruccionesPermiso = new WebMarkupContainer("instruccionesPermiso") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!PermisoUsuarioReferenciaUnica.this.referenciasPermisosNuevos.isEmpty());
        }
      };
    queue(new Component[] { (Component)instruccionesPermiso });
    ListView<NegocioPojo> referenciasNuevasDataList = new ListView<NegocioPojo>("referenciasNuevasDataList", (IModel)new LoadableDetachableModel<List<NegocioPojo>>() {
          protected List<NegocioPojo> load() {
            return PermisoUsuarioReferenciaUnica.this.referenciasPermisosNuevos;
          }
        }) {
        protected void populateItem(final ListItem<NegocioPojo> item) {
          item.add(new Component[] { (Component)new Label("descripcionReferencia", (IModel)Model.of(((NegocioPojo)item.getModelObject()).getReferenciaUnica().getDescripcionReferencia())) });
          item.add(new Component[] { (Component)new AjaxLink("remove") {
                  public void onClick(AjaxRequestTarget target) {
                    PermisoUsuarioReferenciaUnica.this.referenciasPermisosNuevos.remove(item.getModelObject());
                    target.add(new Component[] { (Component)seccionPermisoReferencias });
                  }
                } });
        }
      };
    queue(new Component[] { (Component)referenciasNuevasDataList });
    AjaxLink agregarFerenciaUnica = new AjaxLink("agregarFerenciaUnica") {
        public void onClick(AjaxRequestTarget target) {
          PermisoUsuarioReferenciaUnica.this.addModal((Component)new ModalReferenciasUnica(PermisoUsuarioReferenciaUnica.this.getModalContentId(), PermisoUsuarioReferenciaUnica.this
                .negocioModel.referenciaUnicaUsuarioCliente(PermisoUsuarioReferenciaUnica.this.usuarioModel.getUsuario())) {
                public void cerrarModal(AjaxRequestTarget target) {
                  showModal(false, target);
                  removeModal();
                }
                
                public void aceptar(AjaxRequestTarget target, List<NegocioPojo> referenciasSeleccionadas) {
                  showModal(false, target);
                  removeModal();
                  PermisoUsuarioReferenciaUnica.this.referenciasPermisosNuevos.clear();
                  PermisoUsuarioReferenciaUnica.this.referenciasPermisosNuevos.addAll(referenciasSeleccionadas);
                  target.add(new Component[] { (Component)seccionPermisoReferencias });
                }
              });
          PermisoUsuarioReferenciaUnica.this.showModal(true, target);
        }
      };
    queue(new Component[] { (Component)agregarFerenciaUnica });
    ChoiceRenderer renderer = new ChoiceRenderer("nombre");
    DropDownChoice perfilUsuario = new DropDownChoice("perfilUsuario", LambdaModel.of(this::getPerfilUsuarioSel, this::setPerfilUsuarioSel), Arrays.asList(PerfilUsuarioAlfaEnum.values()), (IChoiceRenderer)renderer);
    queue(new Component[] { (Component)perfilUsuario });
    perfilUsuario.setRequired(true);
    perfilUsuario.setLabel((IModel)Model.of("Perfil Usuario en Fondos"));
    perfilUsuario.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", "Asigna el perfil del usuario para las operaciones Fondos Alfa") });
    LoadableDetachableModel<List<NegocioPojo>> refrenciasUnicas = new LoadableDetachableModel<List<NegocioPojo>>() {
        protected List<NegocioPojo> load() {
          return PermisoUsuarioReferenciaUnica.this.negocioModel.referenciaUnicaUsuarioCliente(PermisoUsuarioReferenciaUnica.this.usuarioModel.getUsuario());
        }
      };
    ListView<NegocioPojo> listaReferenciasUnicasPermitidas = new ListView<NegocioPojo>("listaReferenciasUnicasPermitidas", (IModel)refrenciasUnicas) {
        protected void populateItem(ListItem<NegocioPojo> item) {
          item.add(new Component[] { (Component)new Label("numeroReferencia", LambdaModel.of(((NegocioPojo)item.getModelObject()).getReferenciaUnica()::getNumeroReferencia)) });
          item.add(new Component[] { (Component)new Label("descripcionReferencia", LambdaModel.of(((NegocioPojo)item.getModelObject()).getReferenciaUnica()::getDescripcionReferencia)) });
          item.add(new Component[] { (Component)new Label("tipoIdentificacion", LambdaModel.of(((NegocioPojo)item.getModelObject()).getReferenciaUnica()::getTipoIdentificacion)) });
          item.add(new Component[] { (Component)new Label("identificacion", LambdaModel.of(((NegocioPojo)item.getModelObject()).getReferenciaUnica()::getIdentificacion)) });
          item.add(new Component[] { (Component)new Label("tipoReferencia", LambdaModel.of(((NegocioPojo)item.getModelObject()).getReferenciaUnica()::getTipoReferencia)) });
        }
      };
    queue(new Component[] { (Component)listaReferenciasUnicasPermitidas });
    final WebMarkupContainer referenciasDataTable = new WebMarkupContainer("referenciasDataTable");
    queue(new Component[] { (Component)referenciasDataTable });
    referenciasDataTable.setOutputMarkupPlaceholderTag(true);
    Form actaulizarUsuarioRefForm = new Form("actaulizarUsuarioRefForm");
    queue(new Component[] { (Component)actaulizarUsuarioRefForm });
    AjaxSubmitLink actualizar = new AjaxSubmitLink("actualizar", actaulizarUsuarioRefForm) {
        public void onSubmit(AjaxRequestTarget target) {
          if (PermisoUsuarioReferenciaUnica.this.referenciasPermisosNuevos.isEmpty()) {
            PermisoUsuarioReferenciaUnica.this.negocioModel.crearAlertaCliente("na", PermisoUsuarioReferenciaUnica.this
                .getPerfilUsuarioSel().toString(), PermisoUsuarioReferenciaUnica.this.usuarioModel.getUsuario());
            PermisoUsuarioReferenciaUnica.this.guardarTraza("Usuario", PermisoUsuarioReferenciaUnica.this
                
                .getPerfilUsuarioSel(), PermisoUsuarioReferenciaUnica.this
                .usuarioModel.getUsuario(), "Perfil de usuario actualizado para fondos alfa ", "");
          } else {
            PermisoUsuarioReferenciaUnica.this.referenciasPermisosNuevos.forEach(referencia -> {
                  PermisoUsuarioReferenciaUnica.this.negocioModel.crearAlertaCliente(referencia.getReferenciaUnica().getNumeroReferencia(), PermisoUsuarioReferenciaUnica.this.getPerfilUsuarioSel().toString(), PermisoUsuarioReferenciaUnica.this.usuarioModel.getUsuario());
                  PermisoUsuarioReferenciaUnica.this.guardarTraza("Usuario", PermisoUsuarioReferenciaUnica.this.getPerfilUsuarioSel(), PermisoUsuarioReferenciaUnica.this.usuarioModel.getUsuario(), "Creacipermisos a usuario sobre referencia " + referencia.getReferenciaUnica().getNumeroReferencia(), referencia.getReferenciaUnica().getNumeroReferencia());
                });
          } 
          PermisoUsuarioReferenciaUnica.this.referenciasPermisosNuevos.clear();
          target.add(new Component[] { (Component)referenciasDataTable, (Component)seccionPermisoReferencias });
        }
      };
    queue(new Component[] { (Component)actualizar });
  }
  
  public PerfilUsuarioAlfaEnum getPerfilUsuarioSel() {
    return this.perfilUsuarioSel;
  }
  
  public void setPerfilUsuarioSel(PerfilUsuarioAlfaEnum perfilUsuarioSel) {
    this.perfilUsuarioSel = perfilUsuarioSel;
  }
  
  private void guardarTraza(String accion, PerfilUsuarioAlfaEnum tipaccion, String usuarioAfectado, String mensaje, String idRelacionado) {
    RastroAccionPojo rastro = new RastroAccionPojo();
    rastro.setAccion(accion);
    rastro.setTipoAccion("Perfil " + tipaccion.getNombre());
    rastro.setAccionUuid(UUID.randomUUID().toString());
    rastro.setUsuarioSess(usuarioSesion());
    rastro.setIdRelacionado(idRelacionado);
    rastro.setIdAccion(usuarioAfectado);
    rastro.setMsg(mensaje);
    TraceVM.instance(this.httpGtwy, AuthenticatedWebSession.get().getRoles().hasRole("UCA"), usuarioSesion()).registraRastro(rastro);
  }
}
