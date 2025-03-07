package com.fiduciaria.microapp.page.statefull.administracion.panel;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.store.model.dominio.DominioParam;
import com.fiduciaria.microapp.store.model.dominio.DominioVM;
import java.lang.invoke.SerializedLambda;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxPreventSubmitBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;

public abstract class NavToolbarDominios extends BasePanel {
  private final IModel<DominioVM> modeloParametros;
  
  public NavToolbarDominios(String id, IModel<DominioVM> model) {
    super(id, model);
    this.modeloParametros = model;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initFiltroForm();
  }
  
  private void initFiltroForm() {
    Form filtroForm = new Form("filtroForm");
    queue(new Component[] { (Component)filtroForm });
    filtroForm.add(new Behavior[] { (Behavior)new AjaxPreventSubmitBehavior() });
    TextField filtroNombre = new TextField("filtroNombre", LambdaModel.of(((DominioVM)this.modeloParametros
          .getObject()).getParametros()::getFiltro, ((DominioVM)this.modeloParametros
          .getObject()).getParametros()::setFiltro));
    queue(new Component[] { (Component)filtroNombre });
    AjaxSubmitLink submitFiltroForm = new AjaxSubmitLink("submitFiltroForm", filtroForm) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          NavToolbarDominios.this.callOnActionEvent(target);
        }
      };
    queue(new Component[] { (Component)submitFiltroForm });
    Label navMostrandoDeHasta = new Label("navMostrandoDeHasta", new IModel() {
          public String getObject() {
            StringBuilder response = new StringBuilder();
            response.append(((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getInicio() + 1L);
            response.append(" al ");
            if (((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).totalRegistrosDominios() < ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getInicio() + (
              (DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getCuenta()) {
              response.append(((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).totalRegistrosDominios());
            } else {
              response.append((
                  (DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getInicio() + (
                  (DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getCuenta());
            } 
            return response.toString();
          }
        });
    queue(new Component[] { (Component)navMostrandoDeHasta });
    Label navTotal = new Label("navTotal", (IModel)Model.of(
          Long.valueOf(((DominioVM)this.modeloParametros.getObject()).totalRegistrosDominios())));
    queue(new Component[] { (Component)navTotal });
    WebMarkupContainer navSeccion = new WebMarkupContainer("navSeccion");
    queue(new Component[] { (Component)navSeccion });
    navSeccion.setOutputMarkupPlaceholderTag(true);
    AjaxLink total5Xpag = new AjaxLink("total5Xpag") {
        public void onClick(AjaxRequestTarget target) {
          ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().setCuenta(5L);
          NavToolbarDominios.this.callOnActionEvent(target);
        }
      };
    queue(new Component[] { (Component)total5Xpag });
    WebMarkupContainer total5XpagCheck = new WebMarkupContainer("total5XpagCheck") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible((((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getCuenta() == 5L));
        }
      };
    queue(new Component[] { (Component)total5XpagCheck });
    AjaxLink total10Xpag = new AjaxLink("total10Xpag") {
        public void onClick(AjaxRequestTarget target) {
          ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().setCuenta(10L);
          NavToolbarDominios.this.callOnActionEvent(target);
        }
      };
    queue(new Component[] { (Component)total10Xpag });
    WebMarkupContainer total10XpagCheck = new WebMarkupContainer("total10XpagCheck") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible((((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getCuenta() == 10L));
        }
      };
    queue(new Component[] { (Component)total10XpagCheck });
    total10XpagCheck.setOutputMarkupPlaceholderTag(true);
    AjaxLink total20Xpag = new AjaxLink("total20Xpag") {
        public void onClick(AjaxRequestTarget target) {
          ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().setCuenta(20L);
          NavToolbarDominios.this.callOnActionEvent(target);
        }
      };
    queue(new Component[] { (Component)total20Xpag });
    WebMarkupContainer total20XpagCheck = new WebMarkupContainer("total20XpagCheck") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible((((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getCuenta() == 20L));
        }
      };
    queue(new Component[] { (Component)total20XpagCheck });
    total20XpagCheck.setOutputMarkupPlaceholderTag(true);
    AjaxLink paginaAnterior = new AjaxLink("paginaAnterior") {
        public void onClick(AjaxRequestTarget target) {
          if (((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getInicio() - ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getCuenta() > 0L) {
            ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().setInicio(((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getInicio() - ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getCuenta());
          } else {
            ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().setInicio(0L);
          } 
          NavToolbarDominios.this.callOnActionEvent(target);
        }
        
        protected void onConfigure() {
          super.onConfigure();
          setEnabled((((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getInicio() > 0L));
        }
      };
    queue(new Component[] { (Component)paginaAnterior });
    AjaxLink paginaSiguiente = new AjaxLink("paginaSiguiente") {
        public void onClick(AjaxRequestTarget target) {
          if (((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).totalRegistrosDominios() >= (
            (DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getInicio() + ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getCuenta() + 1L)
            ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().setInicio(((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getInicio() + ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getCuenta()); 
          NavToolbarDominios.this.callOnActionEvent(target);
        }
        
        protected void onConfigure() {
          super.onConfigure();
          setEnabled(
              (((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).totalRegistrosDominios() >= ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getInicio() + ((DominioVM)NavToolbarDominios.this.modeloParametros.getObject()).getParametros().getCuenta() + 1L));
        }
      };
    queue(new Component[] { (Component)paginaSiguiente });
    queue(new Component[] { (Component)new AjaxLink("nuevoTenant") {
            public void onClick(AjaxRequestTarget target) {
              NavToolbarDominios.this.onClickNuevoTenant(target);
            }
          } });
  }
  
  public abstract void callOnActionEvent(AjaxRequestTarget paramAjaxRequestTarget);
  
  public abstract void onClickNuevoTenant(AjaxRequestTarget paramAjaxRequestTarget);
}
