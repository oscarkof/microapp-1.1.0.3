package com.fiduciaria.microapp.page.statefull.administracion.panel;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.base.NombreAccionesEnum;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.componentes.MaskInputFormatAppender;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import com.fiduciaria.microapp.page.statefull.administracion.panel.negocio.TablaNegociosPanel;
import com.fiduciaria.microapp.page.statefull.administracion.panel.user.TablaUsuariosExternosPanel;
import com.fiduciaria.microapp.page.statefull.gestion.actividades.componente.ActualizarTablaBehavior;
import com.fiduciaria.microapp.store.model.dominio.DominioPojo;
import com.fiduciaria.microapp.store.model.dominio.DominioVM;
import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import com.fiduciaria.microapp.store.model.rastro.RastroAccionPojo;
import com.fiduciaria.microapp.store.model.rastro.TraceVM;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojo;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxPreventSubmitBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.ReuseIfModelsEqualStrategy;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

public abstract class DelegadoForm extends BasePanel {
  @SpringBean
  IGenericHttpClient gtwy;

  private final Map<String, NegocioPojo> negocioDominioDelegadoSele;

  private final Map<String, UsuarioPojo> admonDelegados = new HashMap<>();

  private boolean expandedAdmonDelegadosSecc;

  private boolean expandedNegociosDelegadosSecc;

  private final DominioPojo dominioModel;

  private final List<String> errores = new ArrayList<>();

  private final List<String> success = new ArrayList<>();

  private class ActualizarNegocioBehavior extends Behavior {
    private ActualizarNegocioBehavior() {
    }
  }

  public DelegadoForm(String id) {
    super(id);
    this.negocioDominioDelegadoSele = new HashMap<>();
    this.dominioModel = new DominioPojo();
  }

  protected void onInitialize() {
    super.onInitialize();
    final WebMarkupContainer dangerAlert = new WebMarkupContainer("dangerAlert") {
      protected void onConfigure() {
        super.onConfigure();
        setVisible(!DelegadoForm.this.errores.isEmpty());
      }
    };
    dangerAlert.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component) dangerAlert });
    final WebMarkupContainer successAlert = new WebMarkupContainer("successAlert") {
      protected void onConfigure() {
        super.onConfigure();
        setVisible(!DelegadoForm.this.success.isEmpty());
      }
    };
    successAlert.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component) successAlert });
    Label mensajeSuccess = new Label("mensajeSuccess", () -> {
      StringBuilder response = new StringBuilder();
      this.success.forEach((String name) -> {
        response.append(name);
      });
      return response.toString();
    });
    queue(new Component[] { (Component) mensajeSuccess });
    mensajeSuccess.setEscapeModelStrings(false);
    Label mensajeError = new Label("mensajeError", () -> {
      StringBuilder response = new StringBuilder();
      this.errores.forEach((String name) -> {
        response.append(name);
      });
      return response.toString();
    });
    queue(new Component[] { (Component) mensajeError });
    mensajeError.setEscapeModelStrings(false);
    AjaxLink regresar = new AjaxLink("regresar") {
      public void onClick(AjaxRequestTarget target) {
        DelegadoForm.this.regresar(target);
      }
    };
    queue(new Component[] { (Component) regresar });
    AjaxLink cancelar = new AjaxLink("cancelar") {
      public void onClick(AjaxRequestTarget target) {
        DelegadoForm.this.regresar(target);
      }
    };
    queue(new Component[] { (Component) cancelar });
    final TextField<Integer> codigoGrupoUsuarioXDefecto = new TextField("codigoGrupoUsuarioXDefecto",
        LambdaModel.of(this.dominioModel::getCodigoGrupo, this.dominioModel::setCodigoGrupo), Integer.class);
    codigoGrupoUsuarioXDefecto.add(new Behavior[] { (Behavior) new FormGroupControlBehavior(true,
        FormGroupControlBehavior.PosicionPopover.ARRIBA, "CGrupo Usuario", "",
        "El codigo de grupo que se usa por defecto en la creacide los usurios para este dominio.<br><br>Cuando el administrador delegado cree usuarios, a cada uno de ellos se le asigna de forma automel cde grupo ingresado en este campo.") });
    queue(new Component[] { (Component) codigoGrupoUsuarioXDefecto });
    codigoGrupoUsuarioXDefecto.add(new Behavior[] { (Behavior) new AjaxFormComponentUpdatingBehavior("change") {
      protected void onUpdate(AjaxRequestTarget target) {
        target.add(new Component[] { (Component) codigoGrupoUsuarioXDefecto });
      }
    } });
    codigoGrupoUsuarioXDefecto.setLabel((IModel) Model.of("Grupo"));
    codigoGrupoUsuarioXDefecto.setOutputMarkupPlaceholderTag(true);
    codigoGrupoUsuarioXDefecto.setRequired(true);
    codigoGrupoUsuarioXDefecto
        .add(new Behavior[] { (Behavior) MaskInputFormatAppender.general("[5]", null, "", false, false, true) });
    codigoGrupoUsuarioXDefecto.add((IValidator) RangeValidator.maximum(Integer.valueOf(99999)));
    final TextField dominio = new TextField("dominio",
        LambdaModel.of(this.dominioModel::getNombreDominio, this.dominioModel::setNombreDominio));
    dominio.add(
        new Behavior[] { (Behavior) new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA,
            "Dominio de seguridad delagada", "", "Nombre del dominio de administracidelegada a ser creado") });
    queue(new Component[] { (Component) dominio });
    dominio.add(new Behavior[] { (Behavior) new AjaxFormComponentUpdatingBehavior("change") {
      protected void onUpdate(AjaxRequestTarget target) {
        target.add(new Component[] { (Component) dominio });
      }
    } });
    dominio.setLabel((IModel) Model.of("Nombre Dominio"));
    dominio.setOutputMarkupPlaceholderTag(true);
    dominio.setRequired(true);
    dominio.add((IValidator) StringValidator.maximumLength(50));
    final TextField dominioDescripcion = new TextField("dominioDescripcion",
        LambdaModel.of(this.dominioModel::getDescripcionDominio, this.dominioModel::setDescripcionDominio));
    dominioDescripcion.add(
        new Behavior[] { (Behavior) new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA,
            "DescripciDominio", "", "Breve descripcidel dominio de administracidelegada a ser creado") });
    queue(new Component[] { (Component) dominioDescripcion });
    dominioDescripcion.setLabel((IModel) Model.of("Descripcidel dominio"));
    dominioDescripcion.setRequired(true);
    dominioDescripcion.add(new Behavior[] { (Behavior) new AjaxFormComponentUpdatingBehavior("change") {
      protected void onUpdate(AjaxRequestTarget target) {
        target.add(new Component[] { (Component) dominioDescripcion });
      }
    } });
    dominioDescripcion.setOutputMarkupPlaceholderTag(true);
    final TablaUsuariosExternosPanel seleccionarAdminDelegado = new TablaUsuariosExternosPanel(
        "seleccionarAdminDelegado") {
      public void setSelected(UsuarioPojo selected) {
        super.setSelected(selected);
        if (DelegadoForm.this.admonDelegados.size() < 2) {
          DelegadoForm.this.negocioDominioDelegadoSele.clear();
          DelegadoForm.this.admonDelegados.put(selected.getIdPrincipal(), selected);
          registrarComportamiento(ActualizarTablaBehavior.class);
        }
      }

      public UsuarioPojo getSelected() {
        return super.getSelected();
      }

      public boolean isVisible() {
        return DelegadoForm.this.expandedAdmonDelegadosSecc;
      }
    };
    seleccionarAdminDelegado.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component) seleccionarAdminDelegado });
    initAdmonListView();
    final Form formNuevoDominio = new Form("formNuevoDominio");
    queue(new Component[] { (Component) formNuevoDominio });
    formNuevoDominio.add(new Behavior[] { (Behavior) new AjaxPreventSubmitBehavior() });
    AjaxSubmitLink submitDominio = new AjaxSubmitLink("submitDominio", formNuevoDominio) {
      protected void onSubmit(AjaxRequestTarget target) {
        super.onSubmit(target);
        DelegadoForm.this.errores.clear();
        if (!DelegadoForm.this.admonDelegados.isEmpty()) {
          DelegadoForm.this.errores.clear();
          DelegadoForm.this.success.clear();
          RastroAccionPojo rastro = new RastroAccionPojo();
          rastro.setAccion(NombreAccionesEnum.CREAR_DOMINIO.getAccion());
          rastro.setTipoAccion(NombreAccionesEnum.CREAR_DOMINIO.getTipoAccion());
          rastro.setAccionUuid(UUID.randomUUID().toString());
          rastro.setUsuarioSess(DelegadoForm.this.usuarioSesion());
          rastro.setIdAccion(DelegadoForm.this.dominioModel.getNombreDominio());
          DelegadoForm.this.dominioModel.setNegociosDominio(DelegadoForm.this.negocioDominioDelegadoSele);
          DelegadoForm.this.dominioModel.setUsuarioDominio(DelegadoForm.this.admonDelegados);
          StringBuilder msgbldr = new StringBuilder();
          msgbldr.append("Administradores delegados :: ");
          DelegadoForm.this.admonDelegados.forEach((k, v) -> msgbldr.append("  ").append(v.getIdPrincipal()));
          rastro.setMsg(msgbldr.toString());
          DominioVM.instance(DelegadoForm.this.gtwy).guardardominio(DelegadoForm.this.dominioModel);
          TraceVM.instance(DelegadoForm.this.gtwy, AuthenticatedWebSession.get().getRoles().hasRole("UCA"),
              DelegadoForm.this.usuarioSesion()).registraRastro(rastro);
          DelegadoForm.this.success.add("Hemos guardado el dominio administracidelegada.");
        } else {
          if (DelegadoForm.this.admonDelegados.isEmpty())
            DelegadoForm.this.errores.add("El dominio no tiene usuarios administradores. Agregue al menos uno.");
          DelegadoForm.this.success.clear();
        }
        target.add(new Component[] { (Component) dangerAlert, (Component) successAlert });
      }

      protected void onError(AjaxRequestTarget target) {
        super.onError(target);
        target.add(new Component[] { (Component) formNuevoDominio });
      }
    };
    queue(new Component[] { (Component) submitDominio });
    final WebMarkupContainer adminDelegadoExpandibleSeccion = new WebMarkupContainer("adminDelegadoExpandibleSeccion") {
      protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        String classCssName = "class";
        if (DelegadoForm.this.expandedAdmonDelegadosSecc) {
          tag.put("class",
              "pf-u-mt-md pf-c-expandable-section pf-m-expanded pf-m-display-lg pf-m-limit-width pf-u-mb-md");
        } else {
          tag.put("class", "pf-u-mt-md pf-c-expandable-section pf-m-display-lg pf-m-limit-width pf-u-mb-md");
        }
      }
    };
    adminDelegadoExpandibleSeccion.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component) adminDelegadoExpandibleSeccion });
    AjaxLink expandirAdministradoresSeccion = new AjaxLink("expandirAdministradoresSeccion") {
      public void onClick(AjaxRequestTarget target) {
        DelegadoForm.this.expandedAdmonDelegadosSecc = !DelegadoForm.this.expandedAdmonDelegadosSecc;
        DelegadoForm.this.expandedNegociosDelegadosSecc = false;
        DelegadoForm.this.registrarComportamiento(DelegadoForm.ActualizarNegocioBehavior.class);
        target.add(new Component[] { (Component) seleccionarAdminDelegado,
            (Component) adminDelegadoExpandibleSeccion });
      }
    };
    queue(new Component[] { (Component) expandirAdministradoresSeccion });
    initVistaNegocio();
  }

  private void initAdmonListView() {
    WebMarkupContainer administradorDelegadoDataView = new WebMarkupContainer("administradorDelegadoDataView");
    administradorDelegadoDataView.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component) administradorDelegadoDataView });
    RefreshingView<UsuarioPojo> administradorDelegadodataLisView = new RefreshingView<UsuarioPojo>(
        "administradorDelegadodataLisView") {
      protected Iterator<IModel<UsuarioPojo>> getItemModels() {
        List<IModel<UsuarioPojo>> response = new ArrayList<>();
        DelegadoForm.this.admonDelegados.forEach((k, usr) -> response.add(new Model((Serializable) usr)));
        return response.iterator();
      }

      protected void populateItem(final Item<UsuarioPojo> item) {
        item.add(new Component[] {
            (Component) new Label("administradorDelegadoLoginUsuario", (IModel) Model.of(((UsuarioPojo) item
                .getModelObject()).getIdPrincipal())) });
        item.add(new Component[] { (Component) new Link("borrarAdminDelegado") {
          public void onClick() {
            DelegadoForm.this.admonDelegados.remove(((UsuarioPojo) item.getModelObject()).getIdPrincipal());
            DelegadoForm.this.registrarComportamiento(DelegadoForm.ActualizarNegocioBehavior.class);
          }
        } });
      }
    };
    administradorDelegadodataLisView.setItemReuseStrategy(ReuseIfModelsEqualStrategy.getInstance());
    queue(new Component[] { (Component) administradorDelegadodataLisView });
  }

  private void initVistaNegocio() {
    final WebMarkupContainer negocioSeleccionSeccion = new WebMarkupContainer("negocioSeleccionSeccion") {
      protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        String cssTagName = "class";
        if (DelegadoForm.this.expandedNegociosDelegadosSecc) {
          tag.put("class", "pf-c-expandable-section pf-m-expanded pf-m-display-lg pf-m-limit-width pf-u-mb-md");
        } else {
          tag.put("class", "pf-c-expandable-section pf-m-display-lg pf-m-limit-width pf-u-mb-md");
        }
      }
    };
    negocioSeleccionSeccion.setVisible(false);
    queue(new Component[] { negocioSeleccionSeccion.setOutputMarkupPlaceholderTag(true) });
    negocioSeleccionSeccion.add(new Behavior[] { new ActualizarNegocioBehavior() });
    queue(new Component[] { (Component) new WebMarkupContainer("seleccionarNegocio") });
    AjaxLink expandirNegociosDominio = new AjaxLink("expandirNegociosDominio") {
      public void onClick(AjaxRequestTarget target) {
        DelegadoForm.this.expandedNegociosDelegadosSecc = !DelegadoForm.this.expandedNegociosDelegadosSecc;
        TablaNegociosPanel negocioPanel = DelegadoForm.this.crearTablaNegocio();
        negocioSeleccionSeccion.addOrReplace(new Component[] { (Component) negocioPanel });
        target.add(new Component[] { (Component) negocioSeleccionSeccion });
        DelegadoForm.this.registrarComportamiento(DelegadoForm.ActualizarNegocioBehavior.class);
      }
    };
    queue(new Component[] { (Component) expandirNegociosDominio });
    initNegocioListView();
  }

  private TablaNegociosPanel crearTablaNegocio() {
    TablaNegociosPanel seleccionarNegocio = new TablaNegociosPanel("seleccionarNegocio", this.admonDelegados) {
      public void setSelected(NegocioPojo selected) {
        super.setSelected(selected);
        selected.setReferenciaUnica(null);
        selected.setFideicomiso(null);
        DelegadoForm.this.negocioDominioDelegadoSele.put(selected.getCodigoNegocio(), selected);
      }

      protected void onConfigure() {
        super.onConfigure();
        setVisible(DelegadoForm.this.expandedNegociosDelegadosSecc);
      }
    };
    seleccionarNegocio.add(new Behavior[] { new ActualizarNegocioBehavior() });
    seleccionarNegocio.setOutputMarkupPlaceholderTag(true);
    return seleccionarNegocio;
  }

  private void initNegocioListView() {
    WebMarkupContainer negociosDelegadosSeleccionados = new WebMarkupContainer("negociosDelegadosSeleccionados");
    negociosDelegadosSeleccionados.setVisible(false);
    queue(new Component[] { (Component) negociosDelegadosSeleccionados });
    WebMarkupContainer negocioDataView = new WebMarkupContainer("negocioDataView");
    queue(new Component[] { (Component) negocioDataView });
    negocioDataView.setOutputMarkupPlaceholderTag(true);
    RefreshingView<NegocioPojo> negocioListView = new RefreshingView<NegocioPojo>("negocioListView") {
      protected Iterator<IModel<NegocioPojo>> getItemModels() {
        List<IModel<NegocioPojo>> response = new ArrayList<>();
        DelegadoForm.this.negocioDominioDelegadoSele.forEach((t, u) -> response.add(new Model((Serializable) u)));
        return response.iterator();
      }

      protected void populateItem(final Item<NegocioPojo> item) {
        item.add(new Component[] { (Component) new Label("negocioNombre", (IModel) Model.of(((NegocioPojo) item
            .getModelObject()).getDescripcionNegocio())) });
        item.add(new Component[] { (Component) new Link("borrarNegocioDominio") {
          public void onClick() {
            DelegadoForm.this.negocioDominioDelegadoSele
                .remove(((NegocioPojo) item.getModelObject()).getCodigoNegocio());
          }
        } });
      }
    };
    negocioListView.setItemReuseStrategy(ReuseIfModelsEqualStrategy.getInstance());
    queue(new Component[] { (Component) negocioListView });
  }

  public abstract void regresar(AjaxRequestTarget paramAjaxRequestTarget);
}
