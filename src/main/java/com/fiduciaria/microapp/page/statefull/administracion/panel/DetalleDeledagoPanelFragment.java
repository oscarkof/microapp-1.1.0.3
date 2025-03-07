package com.fiduciaria.microapp.page.statefull.administracion.panel;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.base.NombreAccionesEnum;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.panel.modales.ModalUsuarios;
import com.fiduciaria.microapp.store.model.dominio.DominioPojo;
import com.fiduciaria.microapp.store.model.dominio.DominioVM;
import com.fiduciaria.microapp.store.model.negocio.NegocioColumnEnum;
import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import com.fiduciaria.microapp.store.model.negocio.NegocioVM;
import com.fiduciaria.microapp.store.model.rastro.RastroAccionPojo;
import com.fiduciaria.microapp.store.model.rastro.TraceVM;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojo;
import com.fiduciaria.microapp.store.seguridad.PrincipalPojo;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class DetalleDeledagoPanelFragment extends BasePanel {
  @SpringBean
  IGenericHttpClient gtwayhttp;
  
  private final IModel<DominioPojo> dominioModel;
  
  public DetalleDeledagoPanelFragment(String id, IModel<DominioPojo> model) {
    super(id);
    DominioVM dominioVM = new DominioVM(this.gtwayhttp);
    this.dominioModel = (IModel<DominioPojo>)new Model((Serializable)dominioVM.obtenerDominio(((DominioPojo)model.getObject()).getIdentificador()));
  }
  
  protected void onInitialize() {
    super.onInitialize();
    Label nombreModelo = new Label("nombreDominio", new IModel() {
          public Object getObject() {
            return Objects.nonNull(((DominioPojo)DetalleDeledagoPanelFragment.this.dominioModel.getObject()).getNombreDominio()) ? (
              (DominioPojo)DetalleDeledagoPanelFragment.this.dominioModel.getObject()).getNombreDominio().toUpperCase() : "";
          }
        });
    queue(new Component[] { (Component)nombreModelo });
    //Label descripcion = new Label("descripcion", LambdaModel.of((DominioPojo)this.dominioModel.getObject()::getDescripcionDominio));
    Label descripcion = new Label("descripcion", () -> {
        String retval = this.dominioModel.getObject().getDescripcionDominio();
        return retval;
    });
    queue(new Component[] { (Component)descripcion });
    initVistaListaAdministradores();
    initVistaListaUsuarios();
    initVistaListaNegocios();
  }
  
  private void initVistaListaAdministradores() {
    final WebMarkupContainer actionAdmin = new WebMarkupContainer("actionAdmin");
    queue(new Component[] { (Component)actionAdmin });
    actionAdmin.setOutputMarkupPlaceholderTag(true);
    final WebMarkupContainer adminDataView = new WebMarkupContainer("adminDataView");
    queue(new Component[] { (Component)adminDataView });
    adminDataView.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)new AjaxLink("agregarAdministrador") {
            protected void onConfigure() {
              super.onConfigure();
              Integer numAdminActivos = Integer.valueOf(0);
              DominioVM dominioVM = new DominioVM(DetalleDeledagoPanelFragment.this.gtwayhttp);
              numAdminActivos = dominioVM.obtenerDominio(((DominioPojo)DetalleDeledagoPanelFragment.this.dominioModel.getObject()).getIdentificador()).getAdministradoresDominio().entrySet().stream().filter(keyset -> ((UsuarioPojo)keyset.getValue()).getEstado().equalsIgnoreCase("A")).map(_item -> Integer.valueOf(1)).reduce(numAdminActivos, Integer::sum);
              setVisible((numAdminActivos.intValue() < 2));
            }
            
            public void onClick(AjaxRequestTarget target) {
              ModalUsuarios modalUsr = new ModalUsuarios(DetalleDeledagoPanelFragment.this.getModalContentId(), "EXTERNO") {
                  public void cerrar(AjaxRequestTarget target) {
                    showModal(false, target);
                    removeModal();
                  }
                  
                  public void seleccionarUsuario(AjaxRequestTarget target, PrincipalPojo usuario) {
                    DetalleDeledagoPanelFragment.this.agregarAdministradorADominio(target, usuario, "admin");
                    RastroAccionPojo rastro = new RastroAccionPojo();
                    rastro.setAccion(NombreAccionesEnum.ADD_ADMON_DOMINIO.getAccion());
                    rastro.setTipoAccion(NombreAccionesEnum.ADD_ADMON_DOMINIO.getTipoAccion());
                    rastro.setAccionUuid(UUID.randomUUID().toString());
                    rastro.setIdAccion(((DominioPojo)DetalleDeledagoPanelFragment.this.dominioModel.getObject()).getNombreDominio());
                    rastro.setUsuarioSess(usuarioSesion());
                    rastro.setMsg("Usuario " + usuario.getUsuario() + " agregado como administrador del dominio " + (
                        (DominioPojo)DetalleDeledagoPanelFragment.this.dominioModel.getObject()).getNombreDominio());
                    TraceVM.instance(DetalleDeledagoPanelFragment.this.gtwayhttp, AuthenticatedWebSession.get().getRoles().hasRole("UCA"), usuarioSesion()).registraRastro(rastro);
                    target.add(new Component[] { (Component)adminDataView, (Component)actionAdmin });
                    showModal(false, target);
                    removeModal();
                  }
                };
              DetalleDeledagoPanelFragment.this.addModal((Component)modalUsr);
              DetalleDeledagoPanelFragment.this.showModal(true, target);
            }
          } });
    LoadableDetachableModel<List<UsuarioPojo>> listUser = new LoadableDetachableModel<List<UsuarioPojo>>() {
        protected List<UsuarioPojo> load() {
          List<UsuarioPojo> response = new ArrayList<>();
          DominioVM dominioVM = new DominioVM(DetalleDeledagoPanelFragment.this.gtwayhttp);
          dominioVM.obtenerDominio(((DominioPojo)DetalleDeledagoPanelFragment.this.dominioModel.getObject()).getIdentificador()).getAdministradoresDominio()
            .forEach((k, v) -> response.add(v));
          return response;
        }
      };
    ListView<UsuarioPojo> vistaListaAdministradores = new ListView<UsuarioPojo>("vistaListaAdministradores", (IModel)listUser) {
        protected void populateItem(final ListItem<UsuarioPojo> item) {
          //item.add(new Component[] { (Component)new Label("loginUsuario", 
          //        LambdaModel.of((UsuarioPojo)item.getModelObject()::getIdPrincipal)) });
          item.add(new Component[] { (Component)new Label("loginUsuario", () -> {
              String retval = item.getModelObject().getIdPrincipal();
              return retval;
          }) });
          //item.add(new Component[] { (Component)new Label("descripcionUsuario", 
          //        LambdaModel.of((UsuarioPojo)item.getModelObject()::getNombrePrincipal)) });
          item.add(new Component[] { (Component)new Label("descripcionUsuario", () -> {
              String retval = item.getModelObject().getNombrePrincipal();
              return retval;
          }) });
          
          item.add(new Component[] { (Component)new Label("fechaCreacion", new IModel() {
                    public Object getObject() {
                      String response = "";
                      if (Objects.nonNull(Long.valueOf(((UsuarioPojo)item.getModelObject()).getFechaCreacion()))) {
                        Date date = new Date(((UsuarioPojo)item.getModelObject()).getFechaCreacion());
                        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        response = formater.format(date);
                      } 
                      return response;
                    }
                  }) });
          item.add(new Component[] { (Component)new Label("estado", new IModel() {
                    public Object getObject() {
                      if (Objects.nonNull(((UsuarioPojo)item.getModelObject()).getEstado())) {
                        String response = "";
                        if (((UsuarioPojo)item.getModelObject()).getEstado().equalsIgnoreCase("A")) {
                          response = "Activo";
                        } else if (((UsuarioPojo)item.getModelObject()).getEstado().equalsIgnoreCase("I")) {
                          response = "Inactivo";
                        } else {
                          response = "";
                        } 
                        return response;
                      } 
                      return "";
                    }
                  }) });
        }
      };
    queue(new Component[] { (Component)vistaListaAdministradores });
  }
  
  private void agregarAdministradorADominio(AjaxRequestTarget target, PrincipalPojo principal, String tipo) {
    UsuarioPojo usuario = new UsuarioPojo();
    usuario.setIdPrincipal(principal.getUsuario());
    usuario.setUsuario(principal.getUsuario());
    DominioVM.instance(this.gtwayhttp).agregarUsuarioDominio((DominioPojo)this.dominioModel.getObject(), usuario, tipo);
  }
  
  private void initVistaListaUsuarios() {
    RefreshingView<UsuarioPojo> vistaListaUsuarios = new RefreshingView<UsuarioPojo>("vistaListaUsuarios") {
        protected Iterator<IModel<UsuarioPojo>> getItemModels() {
          List<IModel<UsuarioPojo>> response = new ArrayList<>();
          ((DominioPojo)DetalleDeledagoPanelFragment.this.dominioModel.getObject()).getUsuarioDominio().forEach((k, v) -> response.add(new Model((Serializable)v)));
          return response.iterator();
        }
        
        protected void populateItem(Item<UsuarioPojo> item) {
          //item.add(new Component[] { (Component)new Label("loginUsuario", 
          //        LambdaModel.of((UsuarioPojo)item.getModelObject()::getIdPrincipal)) });
          //item.add(new Component[] { (Component)new Label("descripcionUsuario", 
          //        LambdaModel.of((UsuarioPojo)item.getModelObject()::getNombrePrincipal)) });
            item.add(new Component[]{(Component) new Label("loginUsuario", () -> {
                String retval = item.getModelObject().getIdPrincipal();
                return retval;
            })});
            item.add(new Component[]{(Component) new Label("descripcionUsuario", () -> {
                String retval = item.getModelObject().getNombrePrincipal();
                return retval;
            })});
          item.add(new Component[] { (Component)new Label("fechaCreacion", () -> {
                    String response = "";
                    if (Objects.nonNull(Long.valueOf(((UsuarioPojo)item.getModelObject()).getFechaCreacion()))) {
                      Date date = new Date(((UsuarioPojo)item.getModelObject()).getFechaCreacion());
                      SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                      response = formater.format(date);
                    } 
                    return response;
                  }) });
          item.add(new Component[] { (Component)new Label("estado", () -> {
                    if (Objects.nonNull(((UsuarioPojo)item.getModelObject()).getEstado())) {
                      String response = "";
                      if (((UsuarioPojo)item.getModelObject()).getEstado().equalsIgnoreCase("A")) {
                        response = "Activo";
                      } else if (((UsuarioPojo)item.getModelObject()).getEstado().equalsIgnoreCase("I")) {
                        response = "Inactivo";
                      } else {
                        response = "";
                      } 
                      return response;
                    } 
                    return "";
                  }) });
        }
      };
    queue(new Component[] { (Component)vistaListaUsuarios });
  }
  
  private void initVistaListaNegocios() {
    WebMarkupContainer seccionNegocios = new WebMarkupContainer("seccionNegocios");
    seccionNegocios.setVisible(false);
    queue(new Component[] { (Component)seccionNegocios });
    LoadableDetachableModel<List<NegocioPojo>> listaNegocios = new LoadableDetachableModel<List<NegocioPojo>>() {
        protected List<NegocioPojo> load() {
          List<NegocioPojo> response = new ArrayList<>();
          return response;
        }
      };
    PageableListView<NegocioPojo> vistaListaAdministradores = new PageableListView<NegocioPojo>("vistaListaNegocios", (IModel)listaNegocios, 20L) {
        protected void populateItem(ListItem<NegocioPojo> item) {
          //item.add(new Component[] { (Component)new Label("codigoNegocio", 
          //        LambdaModel.of((NegocioPojo)item.getModelObject()::getCodigoNegocio)) });
          //item.add(new Component[] { (Component)new Label("nombreNegocio", 
          //        LambdaModel.of((NegocioPojo)item.getModelObject()::getDescripcionNegocio)) });
          item.add(new Component[] { (Component)new Label("codigoNegocio", () -> {
              String retval = item.getModelObject().getCodigoNegocio();
              return retval;
          }) });
          item.add(new Component[] { (Component)new Label("nombreNegocio", () -> {
              String retval = item.getModelObject().getDescripcionNegocio();
              return retval;
          }) });
        }
      };
    vistaListaAdministradores.setVisible(false);
    queue(new Component[] { (Component)vistaListaAdministradores });
    queue(new Component[] { (Component)new PagingNavigator("navegador", (IPageable)vistaListaAdministradores) });
  }
  
  private void recuperarNegociosAdministrados() {
    ((DominioPojo)this.dominioModel.getObject()).setNegociosDominio(new HashMap<>());
    getNegociosXDominio(((DominioPojo)this.dominioModel.getObject()).getIdentificador()).forEach(negocio -> ((DominioPojo)this.dominioModel.getObject()).getNegociosDominio().put(negocio.getCodigoNegocio(), negocio));
  }
  
  private List<NegocioPojo> getNegociosXDominio(String dominioid) {
    NegocioVM negocioviewModel = new NegocioVM(this.gtwayhttp);
    negocioviewModel.getParametros().setDominioid(dominioid);
    if (Objects.isNull(negocioviewModel.getParametros().getCamposRequeridos()))
      negocioviewModel.getParametros().setCamposRequeridos(new ArrayList()); 
    negocioviewModel.getParametros().getCamposRequeridos().clear();
    negocioviewModel.getParametros().getCamposRequeridos().add(NegocioColumnEnum.TIPO_NEGOCIO);
    negocioviewModel.getParametros().getCamposRequeridos().add(NegocioColumnEnum.CODIGO_NEGOCIO);
    negocioviewModel.getParametros().getCamposRequeridos().add(NegocioColumnEnum.DESCRIPCION_NEGOCIO);
    negocioviewModel.getParametros().getCamposRequeridos().add(NegocioColumnEnum.IDENTIFICACION);
    negocioviewModel.getParametros().getCamposRequeridos().add(NegocioColumnEnum.TIPOREFERENCIA);
    negocioviewModel.getParametros().setSoloTotales(true);
    negocioviewModel.getParametros().setCuenta(negocioviewModel.totalRegistrosNegocioGeneral());
    negocioviewModel.getParametros().setInicio(0L);
    negocioviewModel.getParametros().setSoloTotales(false);
    List<NegocioPojo> rtsNegocio = negocioviewModel.negociosGeneralXDominio();
    return rtsNegocio;
  }
}
