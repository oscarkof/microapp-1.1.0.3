package com.fiduciaria.microapp.page.statefull.gestion.solicitud.panel;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.store.model.fideicomiso.FideicomisoPojo;
import com.fiduciaria.microapp.store.model.negocio.EnumTipoNegocio;
import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import com.fiduciaria.microapp.store.model.referunica.ReferenciaUnicaPojo;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;

public abstract class SoporteNegocioSelectPanel extends BasePanel {
  private final IModel<List<NegocioPojo>> modeloListaNegocios;
  
  private final IModel<NegocioPojo> modeloGestion;
  
  private EnumTipoNegocio tipoNegocio;
  
  public SoporteNegocioSelectPanel(String id, IModel<List<NegocioPojo>> model, IModel<NegocioPojo> modelNegocio) {
    super(id, model);
    this.modeloListaNegocios = model;
    this.modeloGestion = modelNegocio;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initVistaListaNegocios();
  }
  
  public EnumTipoNegocio getTipoNegocio() {
    return this.tipoNegocio;
  }
  
  public void setTipoNegocio(EnumTipoNegocio tipoNegocio) {
    this.tipoNegocio = tipoNegocio;
  }
  
  private void initVistaListaNegocios() {
    if (Objects.nonNull(this.tipoNegocio) && this.tipoNegocio == EnumTipoNegocio.FIDEICOMISO) {
      addOrReplace(new Component[] { (Component)new FideicomisosFragment("seleccionNegocio", "vistaFideicomisos", (MarkupContainer)this, this.modeloListaNegocios) });
    } else {
      addOrReplace(new Component[] { (Component)new ReferenciaUnicaFragment("seleccionNegocio", "vistaReferenciaUnicas", (MarkupContainer)this, this.modeloListaNegocios) });
    } 
  }
  
  public abstract void callbackOnEvent(AjaxRequestTarget paramAjaxRequestTarget);
  
  private class FideicomisosFragment extends Fragment {
    private final IModel<? extends List<NegocioPojo>> modelo;
    
    public FideicomisosFragment(String id, String markupId, MarkupContainer markupProvider, IModel<? extends List<NegocioPojo>> model) {
      super(id, markupId, markupProvider, model);
      this.modelo = model;
    }
    
    protected void onInitialize() {
      super.onInitialize();
      WebMarkupContainer dataView = new WebMarkupContainer("dataView");
      queue(new Component[] { (Component)dataView });
      dataView.setOutputMarkupPlaceholderTag(true);
      VistaListaFideicomiso vistaFideicomisos = new VistaListaFideicomiso("vistaFideicomisos", this.modelo, 15L);
      queue(new Component[] { (Component)vistaFideicomisos });
      queue(new Component[] { (Component)new AjaxPagingNavigator("navigator", (IPageable)vistaFideicomisos) });
    }
    
    private class VistaListaFideicomiso extends PageableListView<NegocioPojo> {
      public VistaListaFideicomiso(String id, IModel<? extends List<NegocioPojo>> model, long itemsPerPage) {
        super(id, model, itemsPerPage);
      }
      
      protected void populateItem(final ListItem<NegocioPojo> item) {
        item.add(new Component[] { (Component)new Label("codigoTipo", LambdaModel.of(((NegocioPojo)item.getModelObject())
                  .getFideicomiso()::getCodigotipofideicomiso)) });
        item.add(new Component[] { (Component)new Label("codigoSubtipo", LambdaModel.of(((NegocioPojo)item.getModelObject())
                  .getFideicomiso()::getCodigosubtipofideicomiso)) });
        item.add(new Component[] { (Component)new Label("codigo", LambdaModel.of(((NegocioPojo)item.getModelObject())
                  .getFideicomiso()::getCodigofideicomiso)) });
        item.add(new Component[] { (Component)new Label("nombreFideicomiso", LambdaModel.of(((NegocioPojo)item.getModelObject())
                  .getFideicomiso()::getNombrefideicomiso)) });
        item.add(new Component[] { (Component)new Label("etapa", LambdaModel.of(((NegocioPojo)item.getModelObject())
                  .getFideicomiso()::getNumerofideicomiso)) });
        item.add(new Behavior[] { (Behavior)new AjaxEventBehavior("click") {
                protected void onEvent(AjaxRequestTarget target) {
                  ((NegocioPojo)SoporteNegocioSelectPanel.this.modeloGestion.getObject())
                    .getFideicomiso()
                    .copiarDesde(((NegocioPojo)item.getModelObject()).getFideicomiso());
                  SoporteNegocioSelectPanel.this.callbackOnEvent(target);
                }
              } });
      }
    }
  }
  
  private class ReferenciaUnicaFragment extends Fragment {
    private final IModel<? extends List<NegocioPojo>> modelo;
    
    public ReferenciaUnicaFragment(String id, String markupId, MarkupContainer markupProvider, IModel<? extends List<NegocioPojo>> model) {
      super(id, markupId, markupProvider, model);
      this.modelo = model;
    }
    
    protected void onInitialize() {
      super.onInitialize();
      WebMarkupContainer dataViewRefUnicas = new WebMarkupContainer("dataViewRefUnicas");
      queue(new Component[] { (Component)dataViewRefUnicas });
      dataViewRefUnicas.setOutputMarkupPlaceholderTag(true);
      VistaListaReferenciaUnica vistaListaReferenciaUnicas = new VistaListaReferenciaUnica("vistaListaReferenciaUnicas", this.modelo, 15L);
      queue(new Component[] { (Component)vistaListaReferenciaUnicas });
      queue(new Component[] { (Component)new AjaxPagingNavigator("navigator", (IPageable)vistaListaReferenciaUnicas) });
    }
    
    private class VistaListaReferenciaUnica extends PageableListView<NegocioPojo> {
      public VistaListaReferenciaUnica(String id, IModel<? extends List<NegocioPojo>> model, long itemsPerPage) {
        super(id, model, itemsPerPage);
      }
      
      protected void populateItem(final ListItem<NegocioPojo> item) {
        item.add(new Component[] { (Component)new Label("numeroReferencia", 
                
                LambdaModel.of(((NegocioPojo)item.getModelObject())
                  .getReferenciaUnica()::getNumeroReferencia)) });
        item.add(new Component[] { (Component)new Label("descripcion", LambdaModel.of(((NegocioPojo)item.getModelObject())
                  .getReferenciaUnica()::getDescripcionReferencia)) });
        item.add(new Component[] { (Component)new Label("tipoIdentificacion", LambdaModel.of(((NegocioPojo)item
                  .getModelObject())
                  .getReferenciaUnica()::getTipoIdentificacion)) });
        item.add(new Component[] { (Component)new Label("numeroIdentificacion", LambdaModel.of(((NegocioPojo)item
                  .getModelObject())
                  .getReferenciaUnica()::getNumeroReferencia)) });
        item.add(new Behavior[] { (Behavior)new AjaxEventBehavior("click") {
                protected void onEvent(AjaxRequestTarget target) {
                  ((NegocioPojo)SoporteNegocioSelectPanel.this.modeloGestion
                    .getObject())
                    .getReferenciaUnica().copiarDesde(((NegocioPojo)item.getModelObject()).getReferenciaUnica());
                  SoporteNegocioSelectPanel.this.callbackOnEvent(target);
                }
              } });
      }
    }
  }
}
