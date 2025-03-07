package com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.panel.modales;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
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
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;

public abstract class ModalUsuarios extends BasePanel {
  @SpringBean
  IGenericHttpClient httpGtwy;
  
  private PrincipalPojo usuarioSeleccionado;
  
  private String tipoFiltroUsuario;
  
  private String valorFiltroUsuario;
  
  private final String tipoUsuario;
  
  public ModalUsuarios(String id, String tipoUsrs) {
    super(id);
    this.tipoUsuario = tipoUsrs;
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
  
  protected void onInitialize() {
    super.onInitialize();
    PageableListView<PrincipalPojo> vistaUsuarios = initListaUsuario("listaUsuarios", usuarioSesion());
    queue(new Component[] { (Component)vistaUsuarios });
    initFiltroToolbar();
    AjaxLink cerrarModal = new AjaxLink("cerrarModal") {
        public void onClick(AjaxRequestTarget target) {
          ModalUsuarios.this.cerrar(target);
        }
      };
    queue(new Component[] { (Component)cerrarModal });
    WebMarkupContainer usuariosDataTable = new WebMarkupContainer("usuariosDataTable") {
        public void onEvent(IEvent<?> event) {
          super.onEvent(event);
          if (event.getPayload() instanceof ModalUsuarios.UpdUsrListEvent)
            ((ModalUsuarios.UpdUsrListEvent)event.getPayload()).getPayload().add(new Component[] { (Component)this }); 
        }
      };
    usuariosDataTable.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)usuariosDataTable });
  }
  
  private PageableListView<PrincipalPojo> initListaUsuario(String id, String usuarioActual) {
    LoadableDetachableModel<List<PrincipalPojo>> listaUsuarios = new LoadableDetachableModel<List<PrincipalPojo>>() {
        protected List<PrincipalPojo> load() {
          List<PrincipalPojo> response = new ArrayList<>();
          UsuarioPojoVM obj = new UsuarioPojoVM(ModalUsuarios.this.httpGtwy);
          List<Map<UsuarioColumnEnum, String>> listusrs = obj.listadoUsuario("allofthem", ModalUsuarios.this.tipoUsuario);
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
                if (!Strings.isEmpty(ModalUsuarios.this.tipoFiltroUsuario) && !Strings.isEmpty(ModalUsuarios.this.valorFiltroUsuario)) {
                  if (ModalUsuarios.this.tipoFiltroUsuario.equalsIgnoreCase("Login usuario".toLowerCase()) && !Strings.isEmpty(user5.getUsuario())) {
                    if (user5.getUsuario().toLowerCase().contains(ModalUsuarios.this.valorFiltroUsuario.toLowerCase()))
                      response.add(user5); 
                  } else if (ModalUsuarios.this.tipoFiltroUsuario.equalsIgnoreCase("Descripción".toLowerCase()) && !Strings.isEmpty(user5.getPrimerNombre())) {
                    if (user5.getPrimerNombre().toLowerCase().contains(ModalUsuarios.this.valorFiltroUsuario.toLowerCase()))
                      response.add(user5); 
                  } else if (ModalUsuarios.this.tipoFiltroUsuario.equalsIgnoreCase("Email".toLowerCase()) && !Strings.isEmpty(user5.getEmail()) && user5.getEmail().toLowerCase().contains(ModalUsuarios.this.valorFiltroUsuario.toLowerCase())) {
                    response.add(user5);
                  } 
                } else {
                  response.add(user5);
                } 
              });
          return response;
        }
      };
    PageableListView<PrincipalPojo> vistaUsuarios = new PageableListView<PrincipalPojo>(id, (IModel)listaUsuarios, 50L) {
        protected void populateItem(final ListItem<PrincipalPojo> item) {
          /*item.add(new Component[] { (Component)new Label("usuario", LambdaModel.of((PrincipalPojo)item
                    .getModelObject()::getUsuario, (PrincipalPojo)item
                    .getModelObject()::setUsuario)) });*/
          /*item.add(new Component[] { (Component)new Label("primerNombre", LambdaModel.of((PrincipalPojo)item
                    .getModelObject()::getPrimerNombre, (PrincipalPojo)item
                    .getModelObject()::setPrimerNombre)) });*/
          item.add(new Behavior[] { (Behavior)new AjaxEventBehavior("click") {
                  protected void onEvent(AjaxRequestTarget target) {
                    ModalUsuarios.this.seleccionarUsuario(target, (PrincipalPojo)item.getModelObject());
                  }
                } });
        }
      };
    AjaxPagingNavigator navigation = new AjaxPagingNavigator("navigation", (IPageable)vistaUsuarios);
    queue(new Component[] { (Component)navigation });
    return vistaUsuarios;
  }
  
  private void initFiltroToolbar() {
    Form formFiltro = new Form("formFiltro");
    queue(new Component[] { (Component)formFiltro });
    WebMarkupContainer filtroToolbar = new WebMarkupContainer("filtroToolbar");
    filtroToolbar.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)filtroToolbar });
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
          send((IEventSink)ModalUsuarios.this, Broadcast.DEPTH, new ModalUsuarios.UpdUsrListEvent(target));
        }
      };
    queue(new Component[] { (Component)filtrarUsuarios });
  }
  
  public abstract void cerrar(AjaxRequestTarget paramAjaxRequestTarget);
  
  public abstract void seleccionarUsuario(AjaxRequestTarget paramAjaxRequestTarget, PrincipalPojo paramPrincipalPojo);
  
  private class UpdUsrListEvent {
    private final AjaxRequestTarget payload;
    
    public UpdUsrListEvent(AjaxRequestTarget payload) {
      this.payload = payload;
    }
    
    public AjaxRequestTarget getPayload() {
      return this.payload;
    }
  }
}
