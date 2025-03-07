package com.fiduciaria.microapp.page.statefull.gestion.usuario;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.page.statefull.gestion.usuario.model.GestionUsuariosVM;
import com.fiduciaria.microapp.page.statefull.gestion.usuario.permisos.PermisoUsuarioReferenciaUnica;
import com.fiduciaria.microapp.store.model.usuario.UsuarioColumnEnum;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojoVM;
import com.fiduciaria.microapp.store.seguridad.EstadoUsuario;
import com.fiduciaria.microapp.store.seguridad.PrincipalPojo;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class PermisosUsuarioDominio extends BasePanel {
  @SpringBean
  IGenericHttpClient httpGtwy;
  
  private final IModel<GestionUsuariosVM> modelo;
  
  public PermisosUsuarioDominio(String id, IModel<GestionUsuariosVM> model) {
    super(id, model);
    this.modelo = model;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    PageableListView<PrincipalPojo> vistaUsuarios = initListaUsuario("listaUsuarios");
    queue(new Component[] { (Component)vistaUsuarios });
  }
  
  private PageableListView<PrincipalPojo> initListaUsuario(String id) {
    LoadableDetachableModel<List<PrincipalPojo>> listaUsuarios = new LoadableDetachableModel<List<PrincipalPojo>>() {
        protected List<PrincipalPojo> load() {
          List<PrincipalPojo> response = new ArrayList<>();
          UsuarioPojoVM obj = new UsuarioPojoVM(PermisosUsuarioDominio.this.httpGtwy);
          List<Map<UsuarioColumnEnum, String>> listusrs = new ArrayList<>();
          if (AuthenticatedWebSession.get().getRoles().hasRole("UCA")) {
            listusrs = obj.listadoUsuario("allofthem", "UCAUCA" + PermisosUsuarioDominio.this.usuarioSesion());
          } else {
            listusrs = obj.listadoUsuario("allofthem", "TODOS");
          } 
          listusrs.forEach(action -> {
                PrincipalPojo user5 = new PrincipalPojo();
                user5.setUsuario((String)action.get(UsuarioColumnEnum.LOGIN_USUARIO.toString()));
                user5.setPrimerNombre((String)action.get(UsuarioColumnEnum.DESCRIPCION_USUARIO.toString()));
                user5.setEmail((String)action.get(UsuarioColumnEnum.CORREO.toString()));
                user5.setPrimerApellido("");
                user5.setRolFondosAlfa((String)action.get(UsuarioColumnEnum.ROL_FONDOS.toString()));
                if (Objects.isNull(action.get(UsuarioColumnEnum.ESTADO_USUARIO.toString()))) {
                  user5.setEstado(EstadoUsuario.NO_DEFINIDO);
                } else if (((String)action.get(UsuarioColumnEnum.ESTADO_USUARIO.toString())).equalsIgnoreCase("I")) {
                  user5.setEstado(EstadoUsuario.INACTIVO);
                } else if (((String)action.get(UsuarioColumnEnum.ESTADO_USUARIO.toString())).equalsIgnoreCase("A")) {
                  user5.setEstado(EstadoUsuario.ACTIVO);
                } 
                response.add(user5);
              });
          return response;
        }
      };
    PageableListView<PrincipalPojo> vistaUsuarios = new PageableListView<PrincipalPojo>(id, (IModel)listaUsuarios, 15L) {
        protected void populateItem(final ListItem<PrincipalPojo> item) {
          item.queue(new Component[] { (Component)new Label("usuario", () -> {
              //LambdaModel.of((PrincipalPojo)item.getModelObject()::getUsuario
              String retval = item.getModelObject().getUsuario();
              return retval;
          }) });
          item.queue(new Component[] { (Component)new Label("descripcion", () -> {
              //LambdaModel.of((PrincipalPojo)item.getModelObject()::getDescripcion)
              String retval = item.getModelObject().getDescripcion();
              return retval;
          }) });
          
          //LambdaModel.of((PrincipalPojo)item.getModelObject()::getEmail, (PrincipalPojo)item.getModelObject()::setEmail)
          
          item.queue(new Component[] { (Component)new Label("email", () -> {
            String retval = item.getModelObject().getEmail();
            return retval;
          }) });
          WebMarkupContainer estadoLabel = new WebMarkupContainer("estadoLabel") {
              protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                String cssClass = (((PrincipalPojo)item.getModelObject()).getEstado() == EstadoUsuario.ACTIVO) ? "pf-c-label pf-m-green pf-m-outline" : "pf-c-label pf-m-red pf-m- outline";
                tag.put("class", cssClass);
              }
            };
          item.queue(new Component[] { (Component)estadoLabel });
          //LambdaModel.of((PrincipalPojo)item.getModelObject()::getEstado, (PrincipalPojo)item.getModelObject()::setEstado)
          estadoLabel.add(new Component[] { (Component)new Label("estado", () -> {
              EstadoUsuario retval = item.getModelObject().getEstado();
              return retval;
          }) {
                  protected void onComponentTag(ComponentTag tag) {
                    super.onComponentTag(tag);
                    String color = (((PrincipalPojo)item.getModelObject()).getEstado() == EstadoUsuario.ACTIVO) ? "green;" : "red;";
                    tag.put("style", "color:" + color);
                  }
                } });
          final WebMarkupContainer detalleSeccion = new WebMarkupContainer("detallePermisosUsuario") {
              protected void onConfigure() {
                super.onConfigure();
              }
              
              protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                if (isVisible())
                  tag.put("class", "pf-c-table__expandable-row pf-m-expanded"); 
              }
            };
          detalleSeccion.setOutputMarkupPlaceholderTag(true);
          item.queue(new Component[] { (Component)detalleSeccion });
          detalleSeccion.setVisible(false);
          detalleSeccion.add(new Component[] { (Component)new PermisoUsuarioReferenciaUnica("contenidoDetalle", item.getModel()) });
          final WebMarkupContainer rowExpandable = new WebMarkupContainer("rowExpandable") {
              protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                if (detalleSeccion.isVisible()) {
                  tag.put("class", "pf-m-expanded");
                } else {
                  tag.put("class", "");
                } 
              }
            };
          item.add(new Component[] { (Component)rowExpandable });
          rowExpandable.setOutputMarkupPlaceholderTag(true);
          AjaxLink toggleDetalles = new AjaxLink("toggleDetalles") {
              public void onClick(AjaxRequestTarget target) {
                detalleSeccion.setVisible(!detalleSeccion.isVisible());
                target.add(new Component[] { (Component)rowExpandable });
              }
              
              protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                if (detalleSeccion.isVisible()) {
                  tag.put("class", "pf-c-button pf-m-plain pf-m-expanded");
                } else {
                  tag.put("class", "pf-c-button pf-m-plain");
                } 
              }
            };
          item.queue(new Component[] { (Component)toggleDetalles });
        }
      };
    vistaUsuarios.setReuseItems(true);
    return vistaUsuarios;
  }
}
