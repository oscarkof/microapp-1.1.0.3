package com.fiduciaria.microapp.page.statefull.gestion.actividades;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import com.fiduciaria.microapp.jwt.AESenc;
import com.fiduciaria.microapp.jwt.JwtUtil;
import com.fiduciaria.microapp.page.statefull.gestion.actividades.model.CorreoNotificacionLinkPreIngresoPojoVM;
import com.fiduciaria.microapp.page.statefull.gestion.actividades.model.GestionActividadesM;
import com.fiduciaria.microapp.page.statefull.gestion.actividades.model.GestionActividadesVM;
import com.fiduciaria.microapp.page.statefull.gestion.panel.NegocioSelectPanel;
import com.fiduciaria.microapp.page.stateless.usuarios.preingreso.FormularioPreingresoDatosUsuarioVM;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoVM;
import com.fiduciaria.microapp.store.model.fideicomiso.FideicomisoPojo;
import com.fiduciaria.microapp.store.model.negocio.EnumTipoNegocio;
import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import com.fiduciaria.microapp.store.model.negocio.NegocioVM;
import com.fiduciaria.microapp.store.model.referunica.ReferenciaUnicaPojo;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxFallbackLink;
import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.validation.IValidator;

public class LinkPreIngresoMainPanel extends BasePanel {
  @SpringBean
  private GenericHttpClient gtwayHttp;
  
  private final IModel<GestionActividadesVM> modelo;
  
  private final WebMarkupContainer seccionAccion;
  
  private final List<NegocioPojo> listadoNegFid = new ArrayList<>();
  
  private final List<NegocioPojo> listadoNegRefUnicas = new ArrayList<>();
  
  private String filtroCodigo;
  
  private String filtroNombre;
  
  private boolean creandoCorreo;
  
  private final IModel<CorreoNotificacionLinkPreIngresoPojoVM> notifiModel;
  
  private class FiltroUpdate extends Behavior {}
  
  public LinkPreIngresoMainPanel(String id, IModel<GestionActividadesVM> model) {
    super(id, model);
    this.modelo = model;
    setOutputMarkupPlaceholderTag(true);
    this.seccionAccion = new WebMarkupContainer("seccionAccion");
    this.notifiModel = (IModel<CorreoNotificacionLinkPreIngresoPojoVM>)new Model((Serializable)new CorreoNotificacionLinkPreIngresoPojoVM((IGenericHttpClient)this.gtwayHttp));
  }
  
  protected void onInitialize() {
    super.onInitialize();
    queue(new Component[] { (Component)this.seccionAccion });
    this.seccionAccion.setOutputMarkupPlaceholderTag(true);
    initFiltro();
    initFragmentPanel();
    initToolbar();
  }
  
  private void initToolbar() {
    WebMarkupContainer toolbarFiltro = new WebMarkupContainer("toolbarFiltro") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!LinkPreIngresoMainPanel.this.creandoCorreo);
        }
      };
    toolbarFiltro.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)toolbarFiltro });
    Form filtroForm = new Form("filtroForm");
    queue(new Component[] { (Component)filtroForm });
    final TextField filtroNombreInput = new TextField("filtroNombre", (IModel)new PropertyModel(this, "filtroNombre"));
    queue(new Component[] { (Component)filtroNombreInput });
    filtroNombreInput.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              LinkPreIngresoMainPanel.this.filtroNombre = filtroNombreInput.getValue();
            }
          } });
    final TextField filtroCodigoInput = new TextField("filtroCodigo", (IModel)new PropertyModel(this, "filtroCodigo"));
    queue(new Component[] { (Component)filtroCodigoInput });
    filtroCodigoInput.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              LinkPreIngresoMainPanel.this.filtroCodigo = filtroCodigoInput.getValue();
            }
          } });
    IndicatingAjaxFallbackLink<Void> filtroLink = new IndicatingAjaxFallbackLink<Void>("filtroLink") {
        public void onClick(Optional<AjaxRequestTarget> optional) {
          optional.ifPresent(target -> {
                LinkPreIngresoMainPanel.this.cargaListaNegocios();
                //LinkPreIngresoMainPanel.this.seccionAccion.addOrReplace(new Component[] { (Component)new LinkPreIngresoMainPanel.VistaNegocioFragment("fragmentPanel", "seleccionNegocioFragment", (MarkupContainer)this.this$0, LinkPreIngresoMainPanel.access$400(this.this$0)) });
                //target.add(new Component[] { (Component)LinkPreIngresoMainPanel.access$500(this.this$0) });
              });
        }
      };
    queue(new Component[] { (Component)filtroLink });
  }
  
  private void initFragmentPanel() {
    WebMarkupContainer fragmentPanelWeb = new WebMarkupContainer("fragmentPanel");
    queue(new Component[] { (Component)fragmentPanelWeb });
    fragmentPanelWeb.setOutputMarkupPlaceholderTag(true);
  }
  
  private void initFiltro() {
    WebMarkupContainer seccionFiltro = new WebMarkupContainer("seccionFiltro");
    queue(new Component[] { (Component)seccionFiltro });
    seccionFiltro.setOutputMarkupPlaceholderTag(true);
    final WebMarkupContainer filterInputsPane = new WebMarkupContainer("filterInputsPane");
    filterInputsPane.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)filterInputsPane });
    final WebMarkupContainer detalleNegocio = new WebMarkupContainer("detalleNegocio");
    detalleNegocio.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)detalleNegocio });
    final IndicatingAjaxFallbackLink<Void> crearCorreo = new IndicatingAjaxFallbackLink<Void>("crearCorreo") {
        public void onClick(Optional<AjaxRequestTarget> optional) {
          ((CorreoNotificacionLinkPreIngresoPojoVM)LinkPreIngresoMainPanel.this.notifiModel.getObject()).setMsgFeedback(null);
          LinkPreIngresoMainPanel.this.fillnotificacion(LinkPreIngresoMainPanel.this.notifiModel);
          LinkPreIngresoMainPanel.CorreoLinkFragment correoLinkSeguroFragment = new LinkPreIngresoMainPanel.CorreoLinkFragment("fragmentPanel", "correoLinkSeguroFragment", (MarkupContainer)LinkPreIngresoMainPanel.this, LinkPreIngresoMainPanel.this.modelo);
          LinkPreIngresoMainPanel.this.creandoCorreo = true;
          LinkPreIngresoMainPanel.this.seccionAccion.addOrReplace(new Component[] { (Component)correoLinkSeguroFragment });
          correoLinkSeguroFragment.setOutputMarkupPlaceholderTag(true);
          //optional.ifPresent(target -> target.add(new Component[] { (Component)LinkPreIngresoMainPanel.access$500(this.this$0) }));
        }
        
        protected void onConfigure() {
          super.onConfigure();
          setVisible(((GestionActividadesVM)LinkPreIngresoMainPanel.this.modelo.getObject()).isSeleccionadoNegocio());
        }
      };
    crearCorreo.add(new Behavior[] { new LinkPreIngresoMainPanelUpdBehavior() });
    crearCorreo.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)crearCorreo });
    Form<Void> form = new Form("form");
    queue(new Component[] { (Component)form });
    ChoiceRenderer renderer = new ChoiceRenderer("nombre");
    DropDownChoice<EnumTipoNegocio> tipoNegocio = new DropDownChoice("tipoNegocio", LambdaModel.of(((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo().getObject()).getNegocio()::getTipoNegocio, ((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo().getObject()).getNegocio()::setTipoNegocio), Arrays.asList(EnumTipoNegocio.values()), (IChoiceRenderer)renderer);
    tipoNegocio.setOutputMarkupPlaceholderTag(true);
    tipoNegocio.setRequired(true);
    tipoNegocio.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.DERECHA, "Filtro Negocio", "", "Ingrese nombre o parte del nombre del negocio para su búsqueda") });
    queue(new Component[] { (Component)tipoNegocio });
    tipoNegocio.setLabel((IModel)Model.of("Tipo Negocio"));
    tipoNegocio.add(new Behavior[] { (Behavior)new AjaxFormSubmitBehavior(form, "change") {
            protected void onSubmit(AjaxRequestTarget target) {
              ((CorreoNotificacionLinkPreIngresoPojoVM)LinkPreIngresoMainPanel.this.notifiModel.getObject()).setMsgFeedback(null);
              LinkPreIngresoMainPanel.this.cargaListaNegocios();
              LinkPreIngresoMainPanel.this.creandoCorreo = false;
              //LinkPreIngresoMainPanel.this.seccionAccion.addOrReplace(new Component[] { (Component)new LinkPreIngresoMainPanel.VistaNegocioFragment("fragmentPanel", "seleccionNegocioFragment", (MarkupContainer)this.this$0, 
              //        LinkPreIngresoMainPanel.access$400(this.this$0)) });
              ((GestionActividadesVM)LinkPreIngresoMainPanel.this.modelo.getObject()).setSeleccionadoNegocio(false);
              ((GestionActividadesM)((GestionActividadesVM)LinkPreIngresoMainPanel.this.modelo.getObject()).getModelo().getObject()).getNegocio().limpiar();
              //target.add(new Component[] { (Component)filterInputsPane, (Component)detalleNegocio, (Component)crearCorreo, (Component)LinkPreIngresoMainPanel.access$500(this.this$0) });
            }
            
            protected void onError(AjaxRequestTarget target) {
              //target.add(new Component[] { (Component)this.val$filterInputsPane, (Component)this.val$detalleNegocio });
            }
          } });
    initDetalleNegocio();
  }
  
  private void initDetalleNegocio() {
    Label numeroReferencia = new Label("numeroReferencia", LambdaModel.of(((GestionActividadesM)((GestionActividadesVM)this.modelo
          .getObject()).getModelo()
          .getObject()).getNegocio()
          .getReferenciaUnica()::getNumeroReferencia));
    numeroReferencia.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)numeroReferencia });
    Label descripcionReferencia = new Label("descripcionReferencia", LambdaModel.of(((GestionActividadesM)((GestionActividadesVM)this.modelo
          .getObject()).getModelo()
          .getObject()).getNegocio()
          .getReferenciaUnica()::getDescripcionReferencia));
    descripcionReferencia.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)descripcionReferencia });
    Label tipoIdentificacion = new Label("tipoIdentificacion", LambdaModel.of(((GestionActividadesM)((GestionActividadesVM)this.modelo
          .getObject()).getModelo()
          .getObject()).getNegocio()
          .getReferenciaUnica()::getTipoIdentificacion));
    tipoIdentificacion.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)tipoIdentificacion });
    Label identificacion = new Label("identificacion", LambdaModel.of(((GestionActividadesM)((GestionActividadesVM)this.modelo
          .getObject()).getModelo()
          .getObject()).getNegocio()
          .getReferenciaUnica()::getIdentificacion));
    identificacion.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)identificacion });
    Label digitosVerificacion = new Label("digitosVerificacion", LambdaModel.of(((GestionActividadesM)((GestionActividadesVM)this.modelo
          .getObject()).getModelo()
          .getObject()).getNegocio()
          .getReferenciaUnica()::getDigitosVerificacion));
    digitosVerificacion.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)digitosVerificacion });
    WebMarkupContainer detalleReferenciaUnica = new WebMarkupContainer("detalleReferenciaUnica") {
        protected void onConfigure() {
          super.onConfigure();
          boolean mostrarcomponente = false;
          if (Objects.nonNull(((GestionActividadesVM)LinkPreIngresoMainPanel.this.modelo.getObject()).getModelo().getObject()) && 
            Objects.nonNull(((GestionActividadesM)((GestionActividadesVM)LinkPreIngresoMainPanel.this.modelo.getObject()).getModelo()
              .getObject()).getNegocio()) && (
            (GestionActividadesM)((GestionActividadesVM)LinkPreIngresoMainPanel.this.modelo.getObject()).getModelo().getObject()).getNegocio()
            .getTipoNegocio() == EnumTipoNegocio.REFERENCIA_UNICA_ALPHA)
            mostrarcomponente = true; 
          setVisible(mostrarcomponente);
        }
      };
    detalleReferenciaUnica.add(new Behavior[] { new LinkPreIngresoMainPanelUpdBehavior() });
    queue(new Component[] { (Component)detalleReferenciaUnica });
    detalleReferenciaUnica.setOutputMarkupPlaceholderTag(true);
    WebMarkupContainer detalleFideicomiso = new WebMarkupContainer("detalleFideicomiso") {
        protected void onConfigure() {
          super.onConfigure();
          boolean mostrarcomponente = false;
          if (Objects.nonNull(((GestionActividadesVM)LinkPreIngresoMainPanel.this.modelo.getObject()).getModelo().getObject()) && 
            Objects.nonNull(((GestionActividadesM)((GestionActividadesVM)LinkPreIngresoMainPanel.this.modelo.getObject()).getModelo()
              .getObject()).getNegocio()) && (
            (GestionActividadesM)((GestionActividadesVM)LinkPreIngresoMainPanel.this.modelo.getObject()).getModelo().getObject())
            .getNegocio().getTipoNegocio() == EnumTipoNegocio.FIDEICOMISO)
            mostrarcomponente = true; 
          setVisible(mostrarcomponente);
        }
      };
    detalleFideicomiso.add(new Behavior[] { new LinkPreIngresoMainPanelUpdBehavior() });
    queue(new Component[] { (Component)detalleFideicomiso });
    detalleFideicomiso.setOutputMarkupPlaceholderTag(true);
    Label tipo = new Label("tipo", LambdaModel.of(((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject())
          .getModelo().getObject())
          .getNegocio().getFideicomiso()::getCodigotipofideicomiso));
    tipo.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)tipo });
    Label subtipo = new Label("subtipo", LambdaModel.of(((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject())
          .getModelo().getObject())
          .getNegocio().getFideicomiso()::getCodigosubtipofideicomiso));
    subtipo.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)subtipo });
    Label codigo = new Label("codigo", LambdaModel.of(((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject())
          .getModelo().getObject())
          .getNegocio().getFideicomiso()::getCodigofideicomiso));
    codigo.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)codigo });
    Label nombreNegocio = new Label("nombreNegocio", LambdaModel.of(((GestionActividadesM)((GestionActividadesVM)this.modelo
          .getObject())
          .getModelo().getObject())
          .getNegocio().getFideicomiso()::getNombrefideicomiso));
    nombreNegocio.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)nombreNegocio });
  }
  
  public static final LinkPreIngresoMainPanel getInstance(String id, IModel<GestionActividadesVM> model) {
    return new LinkPreIngresoMainPanel(id, model);
  }
  
  private class CorreoLinkFragment extends Fragment {
    public CorreoLinkFragment(String id, String markupId, MarkupContainer markupProvider, IModel<?> model) {
      super(id, markupId, markupProvider, model);
    }
    
    protected void onInitialize() {
      super.onInitialize();
      initMensajeNotificacionUrlSegura();
    }
    
    private void initMensajeNotificacionUrlSegura() {
      final WebMarkupContainer alerta = new WebMarkupContainer("alerta") {
          protected void onConfigure() {
            super.onConfigure();
            setVisible(!Strings.isEmpty(((CorreoNotificacionLinkPreIngresoPojoVM)LinkPreIngresoMainPanel.this.notifiModel.getObject()).getMsgFeedback()));
          }
          
          protected void onComponentTag(ComponentTag tag) {
            super.onComponentTag(tag);
            tag.put("class", "pf-c-alert pf-m-" + ((CorreoNotificacionLinkPreIngresoPojoVM)LinkPreIngresoMainPanel.this.notifiModel.getObject()).getTipoAlerta());
          }
        };
      queue(new Component[] { (Component)alerta });
      alerta.setOutputMarkupPlaceholderTag(true);
      //alerta.add(new Component[] { (Component)new Label("mensajeAlerta", LambdaModel.of(
      //          (CorreoNotificacionLinkPreIngresoPojoVM)LinkPreIngresoMainPanel.access$600(this.this$0).getObject()::getMsgFeedback)) });
      final Form formCorreo = new Form("formCorreo");
      formCorreo.setDefaultModel((IModel)new CompoundPropertyModel(((CorreoNotificacionLinkPreIngresoPojoVM)LinkPreIngresoMainPanel.this.notifiModel.getObject()).getModeloNotificacion()));
      formCorreo.setOutputMarkupPlaceholderTag(true);
      queue(new Component[] { (Component)formCorreo });
      formCorreo.setOutputMarkupPlaceholderTag(true);
      final TextField to = new TextField("correoDestino");
      to.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
              protected void onUpdate(AjaxRequestTarget target) {
                target.add(new Component[] { (Component)to });
              }
              
              protected void onError(AjaxRequestTarget target, RuntimeException e) {
                super.onError(target, e);
                target.add(new Component[] { (Component)to });
              }
            } });
      to.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "Dirigido a", "", "Ingrese el correo a quien va dirigido el mensaje") });
      queue(new Component[] { (Component)to });
      to.setRequired(true);
      to.setLabel((IModel)Model.of("Email"));
      to.add((IValidator)RfcCompliantEmailAddressValidator.getInstance());
      TextField asunto = new TextField("asuntoTpl");
      asunto.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "Asunto", "", "Ingrese el asunto del correo") });
      queue(new Component[] { (Component)asunto });
      asunto.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
              protected void onUpdate(AjaxRequestTarget target) {}
            } });
      asunto.setRequired(true);
      asunto.setLabel((IModel)Model.of("Asunto :"));
      TextArea mensaje = new TextArea("cuerpoCorreoTpl");
      queue(new Component[] { (Component)mensaje });
      mensaje.setRequired(true);
      mensaje.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
              protected void onUpdate(AjaxRequestTarget target) {}
            } });
      mensaje.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "Mensaje", "", "Mensaje de notificacidel correo") });
      AjaxSubmitLink submitLink = new AjaxSubmitLink("submitLink", formCorreo) {
          protected void onSubmit(AjaxRequestTarget target) {
            super.onSubmit(target);
            LinkPreIngresoMainPanel.this.fillnotificacion(LinkPreIngresoMainPanel.this.notifiModel);
            ((CorreoNotificacionLinkPreIngresoPojoVM)LinkPreIngresoMainPanel.this.notifiModel.getObject()).enviarNotificacion();
            target.add(new Component[] { formCorreo.setVisible(false), (Component)alerta });
          }
          
          protected void onError(AjaxRequestTarget target) {
            super.onError(target);
            target.add(new Component[] { (Component)formCorreo });
          }
        };
      queue(new Component[] { (Component)submitLink });
    }
  }
  
  private void fillnotificacion(IModel<CorreoNotificacionLinkPreIngresoPojoVM> notifiModel) {
    ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject()).initDefault();
    ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
      .getModeloNotificacion()
      .setTipoNegocio(((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo().getObject()).getNegocio().getTipoNegocio().getNombre());
    ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
      .getModeloNotificacion()
      .setVigenciaLink(432000000L);
    if (((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo().getObject()).getNegocio().getTipoNegocio() == EnumTipoNegocio.FIDEICOMISO) {
      ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
        .getModeloNotificacion()
        .setCodigoNegocio(((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo().getObject()).getNegocio().getFideicomiso().getCodigofideicomiso());
      ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
        .getModeloNotificacion()
        .setDescripcionNegocio(((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo().getObject()).getNegocio().getFideicomiso().getNombrefideicomiso());
    } else if (((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo().getObject()).getNegocio().getTipoNegocio() == EnumTipoNegocio.REFERENCIA_UNICA_ALPHA) {
      ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
        .getModeloNotificacion()
        .setCodigoNegocio(((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo().getObject())
          .getNegocio().getReferenciaUnica().getNumeroReferencia());
      ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
        .getModeloNotificacion()
        .setDescripcionNegocio(((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo()
          .getObject()).getNegocio().getReferenciaUnica().getDescripcionReferencia());
    } 
    String asunto = ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject()).getModeloNotificacion().getAsuntoTpl().replace("${codigoNegocio}", ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject()).getModeloNotificacion().getCodigoNegocio()).replace("${nombreNegocio}", ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject()).getModeloNotificacion().getDescripcionNegocio());
    ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
      .getModeloNotificacion()
      .setAsuntoTpl(asunto);
    ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
      .getModeloNotificacion()
      .setUsuario(usuarioSesion());
    ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
      .getModeloNotificacion()
      .setUuid(UUID.randomUUID().toString());
    TipoDocumentoVM dataObj = new TipoDocumentoVM((IGenericHttpClient)this.gtwayHttp);
    ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject()).setListaTipoDoc(dataObj.listaTipoDocumentos());
    generarTokenSeguro(notifiModel);
    String linkToken = generarTokenLinkSeguro(notifiModel, AppConfParamConstant.getKeyEncry());
    String bdyMsg = ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject()).getModeloNotificacion().getCuerpoCorreoTpl().replace("${linkFormulario}", linkToken);
    ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
      .getModeloNotificacion().setCuerpoCorreoTpl(bdyMsg);
  }
  
  private String generarTokenLinkSeguro(IModel<CorreoNotificacionLinkPreIngresoPojoVM> notifiModel, byte[] keyEncry) {
    StringBuilder response = new StringBuilder();
    FormularioPreingresoDatosUsuarioVM formulario = new FormularioPreingresoDatosUsuarioVM();
    formulario.setUsuarioUCA(AuthenticatedWebSession.get().getRoles().hasRole("UCA"));
    formulario.setNombreUsuarioCrea(usuarioSesion());
    formulario.setDefaultServerURI(AppConfParamConstant.getUrltorestserver());
    formulario.setConftoken(AppConfParamConstant.getConftoken());
    formulario.setTrustkeystorepass(AppConfParamConstant.getTrustkeystorepass());
    formulario.setTrustkeystoretype(AppConfParamConstant.getTrustkeystoretype());
    formulario.setUritotrustkeystore(AppConfParamConstant.getUritotrustkeystore());
    formulario.setStep(1);
    formulario.setValidoHasta(LocalDate.now()
        .plusDays(((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
          .getModeloNotificacion().getDiasValido())
        .toEpochDay());
    formulario.getModelo().getPreingresodatosusuario().setUuid(((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
        .getModeloNotificacion()
        .getUuid());
    ObjectMapper mapper = new ObjectMapper();
    mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    response.append(" ");
    try {
      String payload = mapper.writeValueAsString(formulario);
      String urilink = AESenc.encrypt(payload, keyEncry);
      response.append(((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject()).uriExternosJunction());
      response.append("/fidusapwebbswn/rprxy/autogestion/usuarios/preingreso?bbvaform=");
      response.append(urilink);
    } catch (JsonProcessingException ex) {
      Logger.getLogger(LinkPreIngresoMainPanel.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
    } 
    return response.toString();
  }
  
  private void generarTokenSeguro(IModel<CorreoNotificacionLinkPreIngresoPojoVM> notifiModel) {
    FormularioPreingresoDatosUsuarioVM formulario = new FormularioPreingresoDatosUsuarioVM();
    formulario.setDefaultServerURI(AppConfParamConstant.getUrltorestserver());
    formulario.setUsuarioUCA(AuthenticatedWebSession.get().getRoles().hasRole("UCA"));
    formulario.setNombreUsuarioCrea(usuarioSesion());
    formulario.setConftoken(AppConfParamConstant.getConftoken());
    formulario.setTrustkeystorepass(AppConfParamConstant.getTrustkeystorepass());
    formulario.setTrustkeystoretype(AppConfParamConstant.getTrustkeystoretype());
    formulario.setUritotrustkeystore(AppConfParamConstant.getUritotrustkeystore());
    formulario.setListaTipoDocumentos(((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject()).getListaTipoDoc());
    formulario.setStep(1);
    formulario.setValidoHasta(LocalDate.now().plusDays(7L).toEpochDay());
    formulario.getModelo().setCorreoFormulario(((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject()).getModeloNotificacion().getCorreoDestino());
    formulario.getModelo().getPreingresodatosusuario().setUuid(((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject())
        .getModeloNotificacion()
        .getUuid());
    if (((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo().getObject()).getNegocio().getTipoNegocio() == EnumTipoNegocio.FIDEICOMISO) {
      formulario.getModelo().getPreingresodatosusuario().setCodNegocioLink(((GestionActividadesM)((GestionActividadesVM)this.modelo
          .getObject()).getModelo().getObject()).getNegocio().getFideicomiso().getCodigotipofideicomiso() + "-" + ((GestionActividadesM)((GestionActividadesVM)this.modelo
          
          .getObject()).getModelo()
          .getObject()).getNegocio().getFideicomiso()
          .getCodigosubtipofideicomiso() + "-" + ((GestionActividadesM)((GestionActividadesVM)this.modelo
          
          .getObject()).getModelo()
          .getObject()).getNegocio().getFideicomiso().getCodigofideicomiso());
      formulario.getModelo().getPreingresodatosusuario()
        .setNombreNegocioLink(((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo()
          .getObject()).getNegocio().getFideicomiso().getNombrefideicomiso());
    } else if (((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo().getObject()).getNegocio().getTipoNegocio() == EnumTipoNegocio.REFERENCIA_UNICA_ALPHA) {
      formulario.getModelo().getPreingresodatosusuario().setCodNegocioLink(((GestionActividadesM)((GestionActividadesVM)this.modelo
          .getObject()).getModelo()
          .getObject()).getNegocio()
          .getReferenciaUnica().getNumeroReferencia());
      formulario.getModelo().getPreingresodatosusuario()
        .setNombreNegocioLink(((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo()
          .getObject()).getNegocio().getReferenciaUnica().getDescripcionReferencia());
    } 
    formulario.getModelo().getPreingresodatosusuario().setTipoSolicitud("Registro cliente");
    ObjectMapper mapper = new ObjectMapper();
    mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    String urilink = "";
    try {
      String payload = mapper.writeValueAsString(formulario);
      byte[] keytoken = JwtUtil.instance().crearKAleatoria();
      ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject()).getModeloNotificacion().setTokenkey(Base64.getEncoder().encodeToString(keytoken));
      ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject()).getModeloNotificacion().setVigenciaLink(formulario.getValidoHasta());
      urilink = JwtUtil.instance().simpleJWEEncript(keytoken, payload);
      ((CorreoNotificacionLinkPreIngresoPojoVM)notifiModel.getObject()).getModeloNotificacion().setTokenFormulario(urilink);
    } catch (JsonProcessingException|org.jose4j.lang.JoseException ex) {
      Logger.getLogger(LinkPreIngresoMainPanel.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
  }
  
  private class VistaNegocioFragment extends Fragment {
    public VistaNegocioFragment(String id, String markupId, MarkupContainer markupProvider, IModel<GestionActividadesVM> model) {
      super(id, markupId, markupProvider, model);
      LoadableDetachableModel<List<NegocioPojo>> listaNegocios = new LoadableDetachableModel<List<NegocioPojo>>() {
          protected List<NegocioPojo> load() {
            List<NegocioPojo> listaReferenciasUnicas = new ArrayList<>();
            if (((GestionActividadesM)((GestionActividadesVM)LinkPreIngresoMainPanel.this.modelo.getObject()).getModelo().getObject()).getNegocio().getTipoNegocio() == EnumTipoNegocio.FIDEICOMISO)
              if (!Strings.isEmpty(LinkPreIngresoMainPanel.this.filtroCodigo) || 
                !Strings.isEmpty(LinkPreIngresoMainPanel.this.filtroNombre)) {
                listaReferenciasUnicas.addAll(
                    Arrays.asList(
                      (NegocioPojo[])LinkPreIngresoMainPanel.this.listadoNegFid.stream().filter(negocio -> 
                        ((!Strings.isEmpty(LinkPreIngresoMainPanel.this.filtroCodigo) && negocio.getFideicomiso().getCodigofideicomiso().toLowerCase().contains(LinkPreIngresoMainPanel.this.filtroCodigo)) || (!Strings.isEmpty(LinkPreIngresoMainPanel.this.filtroNombre) && negocio.getFideicomiso().getNombrefideicomiso().toLowerCase().contains(LinkPreIngresoMainPanel.this.filtroNombre))))
                      
                      .toArray(x$0 -> new NegocioPojo[x$0])));
              } else {
                listaReferenciasUnicas.addAll(LinkPreIngresoMainPanel.this.listadoNegFid);
              }  
            if (((GestionActividadesM)((GestionActividadesVM)LinkPreIngresoMainPanel.this.modelo.getObject()).getModelo().getObject()).getNegocio().getTipoNegocio() == EnumTipoNegocio.REFERENCIA_UNICA_ALPHA)
              if (!Strings.isEmpty(LinkPreIngresoMainPanel.this.filtroCodigo) || 
                !Strings.isEmpty(LinkPreIngresoMainPanel.this.filtroNombre)) {
                listaReferenciasUnicas.addAll(
                    Arrays.asList(
                      (NegocioPojo[])LinkPreIngresoMainPanel.this.listadoNegRefUnicas.stream().filter(negocio -> 
                        ((!Strings.isEmpty(LinkPreIngresoMainPanel.this.filtroCodigo) && negocio.getReferenciaUnica().getNumeroReferencia().toLowerCase().contains(LinkPreIngresoMainPanel.this.filtroCodigo)) || (!Strings.isEmpty(LinkPreIngresoMainPanel.this.filtroNombre) && negocio.getReferenciaUnica().getNombresRazonSocial().toLowerCase().contains(LinkPreIngresoMainPanel.this.filtroNombre))))
                      
                      .toArray(x$0 -> new NegocioPojo[x$0])));
              } else {
                listaReferenciasUnicas.addAll(LinkPreIngresoMainPanel.this.listadoNegRefUnicas);
              }  
            return listaReferenciasUnicas;
          }
        };
      NegocioSelectPanel vistaSeleccionNegocio = new NegocioSelectPanel("vistaNegocio", (IModel)listaNegocios, LinkPreIngresoMainPanel.this.modelo);
      vistaSeleccionNegocio.setTipoNegocio(((GestionActividadesM)((GestionActividadesVM)model.getObject()).getModelo()
          .getObject()).getNegocio().getTipoNegocio());
      queue(new Component[] { (Component)vistaSeleccionNegocio });
    }
  }
  
  private void cargaListaNegocios() {
    NegocioVM negocioVM = new NegocioVM((IGenericHttpClient)this.gtwayHttp);
    if (((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo().getObject()).getNegocio().getTipoNegocio() == EnumTipoNegocio.FIDEICOMISO) {
      if (this.listadoNegFid.isEmpty())
        this.listadoNegFid.addAll(negocioVM.fideicomisosUsuario(usuarioSesion())); 
    } else if (((GestionActividadesM)((GestionActividadesVM)this.modelo.getObject()).getModelo().getObject()).getNegocio().getTipoNegocio() == EnumTipoNegocio.REFERENCIA_UNICA_ALPHA) {
      if (this.listadoNegRefUnicas.isEmpty())
        if (AuthenticatedWebSession.get().getRoles().hasRole("UCA")) {
          this.listadoNegRefUnicas.addAll(negocioVM.referenciaUnicaUsuarioCliente(usuarioSesion()));
        } else {
          this.listadoNegRefUnicas.addAll(negocioVM.referenciasUnica());
        }  
    } 
    cargarTplDefaults();
  }
  
  private void cargarTplDefaults() {}
}
