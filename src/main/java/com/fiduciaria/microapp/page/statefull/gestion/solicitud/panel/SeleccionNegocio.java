package com.fiduciaria.microapp.page.statefull.gestion.solicitud.panel;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import com.fiduciaria.microapp.store.model.fideicomiso.FideicomisoPojo;
import com.fiduciaria.microapp.store.model.negocio.EnumTipoNegocio;
import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import com.fiduciaria.microapp.store.model.negocio.NegocioVM;
import com.fiduciaria.microapp.store.model.referunica.ReferenciaUnicaPojo;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxFallbackLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.Strings;

public abstract class SeleccionNegocio extends BasePanel {
  @SpringBean
  private GenericHttpClient gtwayHttp;
  
  private final WebMarkupContainer seccionAccion;
  
  private final NegocioPojo negocioModel;
  
  private final List<NegocioPojo> listadoNegFid = new ArrayList<>();
  
  private final List<NegocioPojo> listadoNegRefUnicas = new ArrayList<>();
  
  private String filtroCodigo;
  
  private String filtroNombre;
  
  public SeleccionNegocio(String id) {
    super(id);
    this.seccionAccion = new WebMarkupContainer("seccionAccion");
    this.negocioModel = new NegocioPojo();
    this.negocioModel.setReferenciaUnica(new ReferenciaUnicaPojo());
    this.negocioModel.setFideicomiso(new FideicomisoPojo());
  }
  
  protected void onInitialize() {
    super.onInitialize();
    this.seccionAccion.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)this.seccionAccion });
    initFragmentPanel();
    initSeleccionNegocio();
    initToolbar();
  }
  
  private void initFragmentPanel() {
    WebMarkupContainer fragmentPanelWeb = new WebMarkupContainer("fragmentPanel");
    queue(new Component[] { (Component)fragmentPanelWeb });
    fragmentPanelWeb.setOutputMarkupPlaceholderTag(true);
  }
  
  private void initToolbar() {
    WebMarkupContainer toolbarFiltro = new WebMarkupContainer("toolbarFiltro");
    toolbarFiltro.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)toolbarFiltro });
    Form filtroForm = new Form("filtroForm");
    queue(new Component[] { (Component)filtroForm });
    final TextField filtroNombreInput = new TextField("filtroNombre", (IModel)new PropertyModel(this, "filtroNombre"));
    queue(new Component[] { (Component)filtroNombreInput });
    filtroNombreInput.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              SeleccionNegocio.this.filtroNombre = filtroNombreInput.getValue();
            }
          } });
    final TextField filtroCodigoInput = new TextField("filtroCodigo", (IModel)new PropertyModel(this, "filtroCodigo"));
    queue(new Component[] { (Component)filtroCodigoInput });
    filtroCodigoInput.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              SeleccionNegocio.this.filtroCodigo = filtroCodigoInput.getValue();
            }
          } });
    IndicatingAjaxFallbackLink<Void> filtroLink = new IndicatingAjaxFallbackLink<Void>("filtroLink") {
        public void onClick(Optional<AjaxRequestTarget> optional) {
          optional.ifPresent(target -> {
                SeleccionNegocio.this.cargaListaNegocios();
                //SeleccionNegocio.this.seccionAccion.addOrReplace(new Component[] { (Component)new SeleccionNegocio.VistaNegocioFragment("fragmentPanel", "seleccionNegocioFragment", (MarkupContainer)this.this$0) });
                //target.add(new Component[] { (Component)SeleccionNegocio.access$300(this.this$0) });
              });
        }
      };
    queue(new Component[] { (Component)filtroLink });
  }
  
  private void initSeleccionNegocio() {
    final WebMarkupContainer filterInputsPane = new WebMarkupContainer("filterInputsPane");
    filterInputsPane.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)filterInputsPane });
    Form<Void> form = new Form("form");
    queue(new Component[] { (Component)form });
    ChoiceRenderer renderer = new ChoiceRenderer("nombre");
    DropDownChoice<EnumTipoNegocio> tipoNegocio = new DropDownChoice("tipoNegocio", LambdaModel.of(this.negocioModel::getTipoNegocio, this.negocioModel::setTipoNegocio), Arrays.asList(EnumTipoNegocio.values()), (IChoiceRenderer)renderer);
    tipoNegocio.setOutputMarkupPlaceholderTag(true);
    tipoNegocio.setRequired(true);
    tipoNegocio.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(false, FormGroupControlBehavior.PosicionPopover.DERECHA, "Filtro Negocio", "", "Ingrese nombre o parte del nombre del negocio para su búsqueda") });
    queue(new Component[] { (Component)tipoNegocio });
    tipoNegocio.setLabel((IModel)Model.of("Tipo Negocio"));
    tipoNegocio.add(new Behavior[] { (Behavior)new AjaxFormSubmitBehavior(form, "change") {
            protected void onSubmit(AjaxRequestTarget target) {
              SeleccionNegocio.this.cargaListaNegocios();
              //SeleccionNegocio.this.seccionAccion.addOrReplace(new Component[] { (Component)new SeleccionNegocio.VistaNegocioFragment("fragmentPanel", "seleccionNegocioFragment", (MarkupContainer)this.this$0) });
              SeleccionNegocio.this.negocioModel.limpiar();
              //target.add(new Component[] { (Component)this.val$filterInputsPane, (Component)SeleccionNegocio.access$300(this.this$0) });
            }
            
            protected void onError(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)filterInputsPane });
            }
          } });
  }
  
  private void cargaListaNegocios() {
    NegocioVM negocioVM = new NegocioVM((IGenericHttpClient)this.gtwayHttp);
    if (this.negocioModel.getTipoNegocio() == EnumTipoNegocio.FIDEICOMISO) {
      if (this.listadoNegFid.isEmpty())
        this.listadoNegFid.addAll(negocioVM.fideicomisosUsuario(usuarioSesion())); 
    } else if (this.negocioModel.getTipoNegocio() == EnumTipoNegocio.REFERENCIA_UNICA_ALPHA) {
      if (this.listadoNegRefUnicas.isEmpty())
        if (AuthenticatedWebSession.get().getRoles().hasRole("UCA")) {
          this.listadoNegRefUnicas.addAll(negocioVM.referenciaUnicaUsuarioCliente(usuarioSesion()));
        } else {
          this.listadoNegRefUnicas.addAll(negocioVM.referenciasUnica());
        }  
    } 
  }
  
  public abstract void callbackSeleccion(AjaxRequestTarget paramAjaxRequestTarget, NegocioPojo paramNegocioPojo);
  
  private class VistaNegocioFragment extends Fragment {
    public VistaNegocioFragment(String id, String markupId, MarkupContainer markupProvider) {
      super(id, markupId, markupProvider);
      LoadableDetachableModel<List<NegocioPojo>> listaNegocios = new LoadableDetachableModel<List<NegocioPojo>>() {
          protected List<NegocioPojo> load() {
            List<NegocioPojo> listaReferenciasUnicas = new ArrayList<>();
            if (SeleccionNegocio.this.negocioModel.getTipoNegocio() == EnumTipoNegocio.FIDEICOMISO)
              if (!Strings.isEmpty(SeleccionNegocio.this.filtroCodigo) || 
                !Strings.isEmpty(SeleccionNegocio.this.filtroNombre)) {
                listaReferenciasUnicas.addAll(
                    Arrays.asList(
                      (NegocioPojo[])SeleccionNegocio.this.listadoNegFid.stream().filter(negocio -> 
                        ((!Strings.isEmpty(SeleccionNegocio.this.filtroCodigo) && negocio.getFideicomiso().getCodigofideicomiso().toLowerCase().contains(SeleccionNegocio.this.filtroCodigo)) || (!Strings.isEmpty(SeleccionNegocio.this.filtroNombre) && negocio.getFideicomiso().getNombrefideicomiso().toLowerCase().contains(SeleccionNegocio.this.filtroNombre))))
                      
                      .toArray(x$0 -> new NegocioPojo[x$0])));
              } else {
                listaReferenciasUnicas.addAll(SeleccionNegocio.this.listadoNegFid);
              }  
            if (SeleccionNegocio.this.negocioModel.getTipoNegocio() == EnumTipoNegocio.REFERENCIA_UNICA_ALPHA)
              if (!Strings.isEmpty(SeleccionNegocio.this.filtroCodigo) || 
                !Strings.isEmpty(SeleccionNegocio.this.filtroNombre)) {
                listaReferenciasUnicas.addAll(
                    Arrays.asList(
                      (NegocioPojo[])SeleccionNegocio.this.listadoNegRefUnicas.stream().filter(negocio -> 
                        ((!Strings.isEmpty(SeleccionNegocio.this.filtroCodigo) && negocio.getReferenciaUnica().getNumeroReferencia().toLowerCase().contains(SeleccionNegocio.this.filtroCodigo)) || (!Strings.isEmpty(SeleccionNegocio.this.filtroNombre) && negocio.getReferenciaUnica().getNombresRazonSocial().toLowerCase().contains(SeleccionNegocio.this.filtroNombre))))
                      
                      .toArray(x$0 -> new NegocioPojo[x$0])));
              } else {
                listaReferenciasUnicas.addAll(SeleccionNegocio.this.listadoNegRefUnicas);
              }  
            return listaReferenciasUnicas;
          }
        };
      SoporteNegocioSelectPanel vistaSeleccionNegocio = new SoporteNegocioSelectPanel("vistaNegocio", (IModel)listaNegocios, (IModel)new Model((Serializable)SeleccionNegocio.this.negocioModel)) {
          public void callbackOnEvent(AjaxRequestTarget target) {
            SeleccionNegocio.this.callbackSeleccion(target, SeleccionNegocio.this.negocioModel);
          }
        };
      vistaSeleccionNegocio.setTipoNegocio(SeleccionNegocio.this.negocioModel.getTipoNegocio());
      queue(new Component[] { (Component)vistaSeleccionNegocio });
    }
  }
}
