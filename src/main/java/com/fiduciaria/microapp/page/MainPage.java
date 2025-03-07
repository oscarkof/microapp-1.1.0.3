package com.fiduciaria.microapp.page;

import com.fiduciaria.microapp.IAuthenticatedWebPage;
import com.fiduciaria.microapp.base.AuthenticatedBasePage;
import com.fiduciaria.microapp.page.error.Error403;
import com.fiduciaria.microapp.page.model.MenuOption;
import com.fiduciaria.microapp.page.statefull.administracion.AdimistracionTenant;
import com.fiduciaria.microapp.page.statefull.ayuda.Ayuda;
import com.fiduciaria.microapp.page.statefull.gestion.actividades.GestionActividades;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.GestionSolicitudes;
import com.fiduciaria.microapp.page.statefull.gestion.usuario.GestionUsuario;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.Strings;

public class MainPage extends AuthenticatedBasePage implements IAuthenticatedWebPage {
  private boolean sidebarExpanded = true;
  
  private final WebMarkupContainer modalRoot;
  
  private static final String modalContentId = "modalContent";
  
  public MainPage(PageParameters parameters) {
    super(parameters);
    this.modalRoot = new WebMarkupContainer("modalRoot");
    this.modalRoot.setVisible(false);
  }
  
  protected void onConfigure() {
    super.onConfigure();
    if (!AuthenticatedWebSession.get().isSignedIn())
      throw new RestartResponseAtInterceptPageException(Error403.class); 
  }
  
  protected void onInitialize() {
    super.onInitialize();
    final WebMarkupContainer sidebar = new WebMarkupContainer("sidebar") {
        protected void onConfigure() {
          super.onConfigure();
          int cantidadRoles = 0;
          if (AuthenticatedWebSession.get().getRoles().hasRole("UIFA"))
            cantidadRoles++; 
          if (AuthenticatedWebSession.get().getRoles().hasRole("UIFS"))
            cantidadRoles++; 
          if (AuthenticatedWebSession.get().getRoles().hasRole("UCA"))
            cantidadRoles++; 
          setVisible((cantidadRoles == 1));
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (MainPage.this.sidebarExpanded) {
            tag.put("class", "pf-c-page__sidebar pf-m-expanded");
          } else {
            tag.put("class", "pf-c-page__sidebar pf-m-collapsed");
          } 
        }
      };
    sidebar.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)sidebar });
    AjaxLink toggleNavbar = new AjaxLink("toggleNavbar") {
        protected void onConfigure() {
          super.onConfigure();
          int cantidadRoles = 0;
          if (AuthenticatedWebSession.get().getRoles().hasRole("UIFA"))
            cantidadRoles++; 
          if (AuthenticatedWebSession.get().getRoles().hasRole("UIFS"))
            cantidadRoles++; 
          if (AuthenticatedWebSession.get().getRoles().hasRole("UCA"))
            cantidadRoles++; 
          setVisible((cantidadRoles == 1));
        }
        
        public void onClick(AjaxRequestTarget target) {
          MainPage.this.sidebarExpanded = !MainPage.this.sidebarExpanded;
          target.add(new Component[] { (Component)sidebar, (Component)this });
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (MainPage.this.sidebarExpanded) {
            tag.put("class", "pf-u-display-inline-flex toggleNavBar toggleNavBar-expanded");
          } else {
            tag.put("class", "pf-u-display-inline-flex toggleNavBar");
          } 
        }
      };
    queue(new Component[] { (Component)toggleNavbar });
    queue(new Component[] { (Component)navMenu("navMenus") });
    this.modalRoot.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)this.modalRoot });
    queue(new Component[] { (Component)new WebMarkupContainer("modalContent") });
  }
  
  private ListView navMenu(String id) {
    WebMarkupContainer navContainer = new WebMarkupContainer("navContainer") {
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          tag.put("style", "--pf-c-nav__link--hover--BackgroundColor: #5bbeff;--pf-c-nav__link--m-current--Color: #5bbeff;--pf-c-nav__link--m-current--BackgroundColor: #042144ba;--pf-c-nav__link--active--BackgroundColor: #042144ba;--pf-c-nav__link--focus--BackgroundColor: #042144ba;");
        }
      };
    navContainer.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)navContainer });
    List<MenuOption> menus = new ArrayList<>();
    if (AuthenticatedWebSession.get().getRoles().hasRole("UIFA") && 
      String.valueOf(AuthenticatedWebSession.get().getAttribute("usertype")).equalsIgnoreCase("INT")) {
      MenuOption administracion = new MenuOption();
      administracion.setId("5");
      administracion.setLabel("Administración");
      administracion.setPageClass(AdimistracionTenant.class);
      menus.add(administracion);
    } 
    if (AuthenticatedWebSession.get().getRoles().hasRole("UIFA") || 
      AuthenticatedWebSession.get().getRoles().hasRole("UIFS"))
      if (String.valueOf(
          AuthenticatedWebSession.get().getAttribute("usertype")).equalsIgnoreCase("INT")) {
        MenuOption gestionTareas = new MenuOption();
        gestionTareas.setId("1");
        gestionTareas.setLabel("GestiTareas");
        gestionTareas.setPageClass(GestionActividades.class);
        menus.add(gestionTareas);
        MenuOption solicitudes = new MenuOption();
        solicitudes.setId("2");
        solicitudes.setLabel("Solicitudes");
        solicitudes.setPageClass(GestionSolicitudes.class);
        menus.add(solicitudes);
        MenuOption usuarios = new MenuOption();
        usuarios.setId("3");
        usuarios.setLabel("Usuarios");
        usuarios.setPageClass(GestionUsuario.class);
        menus.add(usuarios);
        MenuOption ayuda = new MenuOption();
        ayuda.setId("4");
        ayuda.setLabel("Ayuda");
        ayuda.setPageClass(Ayuda.class);
        menus.add(ayuda);
      }  
    if (AuthenticatedWebSession.get().getRoles().hasRole("UCA"))
      if (String.valueOf(
          AuthenticatedWebSession.get().getAttribute("usertype")).equalsIgnoreCase("EXT")) {
        MenuOption gestionTareas = new MenuOption();
        gestionTareas.setId("1");
        gestionTareas.setLabel("GestiTareas");
        gestionTareas.setPageClass(GestionActividades.class);
        menus.add(gestionTareas);
        MenuOption solicitudes = new MenuOption();
        solicitudes.setId("2");
        solicitudes.setLabel("Solicitudes");
        solicitudes.setPageClass(GestionSolicitudes.class);
        menus.add(solicitudes);
        MenuOption usuarios = new MenuOption();
        usuarios.setId("3");
        usuarios.setLabel("Usuarios");
        usuarios.setPageClass(GestionUsuario.class);
        menus.add(usuarios);
        MenuOption ayuda = new MenuOption();
        ayuda.setId("4");
        ayuda.setLabel("Ayuda");
        ayuda.setPageClass(Ayuda.class);
        menus.add(ayuda);
      }  
    ListView<MenuOption> response = new ListView<MenuOption>(id, menus) {
        protected void populateItem(final ListItem<MenuOption> item) {
          AjaxLink menu = new AjaxLink("navOption") {
              public void onClick(AjaxRequestTarget target) {
                PageParameters parameters = MainPage.this.getPageParameters();
                parameters.set("navopid", ((MenuOption)item.getModelObject()).getId());
                setResponsePage(((MenuOption)item.getModelObject()).getPageClass(), MainPage.this.getPageParameters());
              }
              
              protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("style", " --pf-c-nav__link--before--BorderColor: transparent;--pf-c-nav__link--after--BorderColor: transparent;--pf-c-nav__link--before--BorderBottomWidth: 0px;");
              }
            };
          String labelDes = "";
          if (((MenuOption)item.getModelObject()).getLabel().trim().equalsIgnoreCase("GestiTareas")) {
            labelDes = "<i class=\"fas fa-tasks\"></i>";
          } else if (((MenuOption)item.getModelObject()).getLabel().trim().equalsIgnoreCase("Usuarios")) {
            labelDes = "<i class=\"fas fa-users\"></i>";
          } else if (((MenuOption)item.getModelObject()).getLabel().trim().equalsIgnoreCase("Ayuda")) {
            labelDes = "<i class=\"fas fa-info\"></i>";
          } else if (((MenuOption)item.getModelObject()).getLabel().trim().equalsIgnoreCase("Administración")) {
            labelDes = "<i class=\"fas fa-tools\"></i>";
          } else if (((MenuOption)item.getModelObject()).getLabel().trim().equalsIgnoreCase("Solicitudes")) {
            labelDes = "<i class=\"fas fa-bookmark\"></i>";
          } 
          menu.add(new Component[] { (Component)new Label("label", (IModel)Model.of(labelDes + " &nbsp;&nbsp;&nbsp;" + ((MenuOption)item.getModelObject()).getLabel())) {
                  protected void onConfigure() {
                    super.onConfigure();
                    setEscapeModelStrings(false);
                  }
                } });
          String selected = MainPage.this.getPageParameters().get("navopid").toString();
          if (!Strings.isEmpty(selected) && selected
            .equalsIgnoreCase(((MenuOption)item.getModelObject()).getId())) {
            menu.add(new Behavior[] { (Behavior)AttributeModifier.replace("class", "pf-c-nav__link pf-m-current") });
          } else {
            menu.add(new Behavior[] { (Behavior)AttributeModifier.replace("class", "pf-c-nav__link") });
          } 
          item.add(new Component[] { (Component)menu });
        }
      };
    return response;
  }
  
  public void addModal(Component modal) {
    this.modalRoot.addOrReplace(new Component[] { modal });
  }
  
  public void showModal(boolean visible, AjaxRequestTarget target) {
    this.modalRoot.setVisible(visible);
    target.add(new Component[] { (Component)this.modalRoot });
  }
  
  public void removeModal() {
    this.modalRoot.addOrReplace(new Component[] { (Component)new WebMarkupContainer("modalContent") });
  }
  
  public static String getModalContentId() {
    return "modalContent";
  }
}
