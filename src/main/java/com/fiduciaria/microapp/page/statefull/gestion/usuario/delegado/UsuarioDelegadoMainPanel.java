package com.fiduciaria.microapp.page.statefull.gestion.usuario.delegado;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.page.statefull.gestion.usuario.model.GestionUsuariosVM;
import com.fiduciaria.microapp.store.model.usuario.UsuarioColumnEnum;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojoVM;
import com.fiduciaria.microapp.store.seguridad.EstadoUsuario;
import com.fiduciaria.microapp.store.seguridad.PrincipalPojo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.danekja.java.util.function.serializable.SerializableConsumer;
import org.danekja.java.util.function.serializable.SerializableSupplier;

public class UsuarioDelegadoMainPanel extends BasePanel {
  @SpringBean
  IGenericHttpClient httpGtwy;
  
  private final IModel<GestionUsuariosVM> modelo;
  
  public UsuarioDelegadoMainPanel(String id, IModel<GestionUsuariosVM> model) {
    super(id, model);
    this.modelo = model;
    ((GestionUsuariosVM)this.modelo.getObject()).setNuevoUsuarioActivado(false);
    initSecciones();
    initUsuarios();
  }
  
  private void initUsuarios() {
    AjaxLink nuevoUsuarioBtn = new AjaxLink("nuevoUsuarioBtn") {
        public void onClick(AjaxRequestTarget target) {
          ((GestionUsuariosVM)UsuarioDelegadoMainPanel.this.modelo.getObject()).setNuevoUsuarioActivado(!((GestionUsuariosVM)UsuarioDelegadoMainPanel.this.modelo.getObject()).isNuevoUsuarioActivado());
          UsuarioDelegadoMainPanel.this.registrarComportamiento(ActualizarComportamiento.class);
        }
      };
    queue(new Component[] { (Component)nuevoUsuarioBtn });
    PageableListView<PrincipalPojo> vistaUsuarios = initListaUsuario("listaUsuarios", usuarioSesion());
    queue(new Component[] { (Component)vistaUsuarios });
    AjaxPagingNavigator navigation = new AjaxPagingNavigator("navigation", (IPageable)vistaUsuarios);
    queue(new Component[] { (Component)navigation });
  }
  
  private PageableListView<PrincipalPojo> initListaUsuario(String id, final String usuarioActual) {
    LoadableDetachableModel<List<PrincipalPojo>> listaUsuarios = new LoadableDetachableModel<List<PrincipalPojo>>() {
        protected List<PrincipalPojo> load() {
          List<PrincipalPojo> response = new ArrayList<>();
          UsuarioPojoVM obj = new UsuarioPojoVM(UsuarioDelegadoMainPanel.this.httpGtwy);
          List<Map<UsuarioColumnEnum, String>> listusrs = obj.listadoUsuarioDominio(usuarioActual);
          listusrs.forEach(action -> {
                PrincipalPojo user5 = new PrincipalPojo();
                user5.setUsuario((String)action.get(UsuarioColumnEnum.LOGIN_USUARIO.toString()));
                user5.setPrimerNombre((String)action.get(UsuarioColumnEnum.DESCRIPCION_USUARIO.toString()));
                user5.setPrimerApellido("");
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
          item.add(new Component[] { (Component)new Label("usuario", () -> {
              //LambdaModel.of((PrincipalPojo)item.getModelObject()::getUsuario, (PrincipalPojo)item.getModelObject()::setUsuario)
              return item.getModelObject().getUsuario();
          }) });
          item.add(new Component[] { (Component)new Label("primerNombre", () -> {
              //LambdaModel.of((PrincipalPojo)item.getModelObject()::getPrimerNombre, (PrincipalPojo)item.getModelObject()::setPrimerNombre)
              return item.getModelObject().getPrimerNombre();
          }) });
          item.add(new Component[] { (Component)new Label("segundoNombre", () -> {
              //LambdaModel.of((PrincipalPojo)item.getModelObject()::getSegundoNombre, (PrincipalPojo)item.getModelObject()::setSegundoNombre)
              return item.getModelObject().getSegundoNombre();
          }) });
          item.add(new Component[] { (Component)new Label("primerApellido", () -> {
              //LambdaModel.of((PrincipalPojo)item.getModelObject()::getPrimerApellido, (PrincipalPojo)item.getModelObject()::setPrimerApellido)
              return item.getModelObject().getPrimerApellido();
          }) });
          
          item.add(new Component[] { (Component)new Label("segundoApellido", LambdaModel.of(          
            (SerializableSupplier<String>) () -> item.getModelObject().getSegundoApellido(),
            (SerializableConsumer<String>) segundoApellido -> item.getModelObject().setSegundoApellido(segundoApellido)
          )) });
          
          
          //(PrincipalPojo)item.getModelObject()::getEmail, (PrincipalPojo)item.getModelObject()::setEmail
          item.add(new Component[] { (Component)new Label("email", LambdaModel.of(
            (SerializableSupplier<String>) () -> item.getModelObject().getEmail(),
            (SerializableConsumer<String>) email -> item.getModelObject().setEmail(email)
          )) });
          WebMarkupContainer estadoLabel = new WebMarkupContainer("estadoLabel") {
              protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                String cssClass = (((PrincipalPojo)item.getModelObject()).getEstado() == EstadoUsuario.ACTIVO) ? "pf-c-label pf-m-green pf-m-outline" : "pf-c-label pf-m-red pf-m- outline";
                tag.put("class", cssClass);
              }
            };
          item.add(new Component[] { (Component)estadoLabel });
          //(PrincipalPojo)item.getModelObject()::getEstado, (PrincipalPojo)item.getModelObject()::setEstado
          estadoLabel.add(new Component[] { (Component)new Label("estado", LambdaModel.of(
            (SerializableSupplier<EstadoUsuario>) () -> item.getModelObject().getEstado(),
            (SerializableConsumer<EstadoUsuario>) estadoUsuario -> item.getModelObject().setEstado(estadoUsuario)   
          )) {
                  protected void onComponentTag(ComponentTag tag) {
                    super.onComponentTag(tag);
                    String color = (((PrincipalPojo)item.getModelObject()).getEstado() == EstadoUsuario.ACTIVO) ? "green;" : "red;";
                    tag.put("style", "color:" + color);
                  }
                } });
          final WebMarkupContainer opcionesUsuario = new WebMarkupContainer("opcionesUsuario");
          opcionesUsuario.setOutputMarkupPlaceholderTag(true);
          opcionesUsuario.setVisible(false);
          AjaxLink openOpcionesUsuario = new AjaxLink("openOpcionesUsuario") {
              public void onClick(AjaxRequestTarget target) {
                opcionesUsuario.setVisible(!opcionesUsuario.isVisible());
                target.add(new Component[] { (Component)opcionesUsuario });
              }
            };
          item.add(new Component[] { (Component)opcionesUsuario });
          item.add(new Component[] { (Component)openOpcionesUsuario });
        }
      };
    return vistaUsuarios;
  }
  
  private void initSecciones() {
    NuevoUsuarioWizard seccionNuevoUsuario = new NuevoUsuarioWizard("seccionNuevoUsuario", this.modelo) {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(((GestionUsuariosVM)UsuarioDelegadoMainPanel.this.modelo.getObject()).isNuevoUsuarioActivado());
        }
      };
    queue(new Component[] { (Component)seccionNuevoUsuario });
    seccionNuevoUsuario.setOutputMarkupPlaceholderTag(true);
    seccionNuevoUsuario.add(new Behavior[] { new ActualizarComportamiento() });
    WebMarkupContainer seccionUsuarios = new WebMarkupContainer("seccionUsuarios") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!((GestionUsuariosVM)UsuarioDelegadoMainPanel.this.modelo.getObject()).isNuevoUsuarioActivado());
        }
      };
    seccionUsuarios.setOutputMarkupPlaceholderTag(true);
    seccionUsuarios.add(new Behavior[] { new ActualizarComportamiento() });
    queue(new Component[] { (Component)seccionUsuarios });
  }
}
