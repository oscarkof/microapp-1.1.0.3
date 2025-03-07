package com.fiduciaria.microapp.page.statefull.gestion.usuario.permisos;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import com.fiduciaria.microapp.store.model.negocio.NegocioVM;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public abstract class ModalReferenciasUnica extends BasePanel {
  @SpringBean
  IGenericHttpClient httpGtwy;
  
  private final NegocioVM negocioModel;
  
  private final List<NegocioPojo> referenciasSeleccionadas;
  
  private final List<NegocioPojo> referenciasAsignadas;
  
  public ModalReferenciasUnica(String id, List<NegocioPojo> referencias) {
    super(id);
    this.negocioModel = new NegocioVM(this.httpGtwy);
    this.referenciasSeleccionadas = new ArrayList<>();
    this.referenciasAsignadas = referencias;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    Form formCheckGroup = new Form("formCheckGroup");
    queue(new Component[] { (Component)formCheckGroup });
    CheckGroup groupCheckReferencia = new CheckGroup("groupCheckReferencia", this.referenciasSeleccionadas);
    queue(new Component[] { (Component)groupCheckReferencia });
    WebMarkupContainer referenciasDataTable = new WebMarkupContainer("referenciasDataTable");
    queue(new Component[] { (Component)referenciasDataTable });
    referenciasDataTable.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)inicializaVistaListaReferencias() });
    AjaxLink cerrarModal = new AjaxLink("cerrarModal") {
        public void onClick(AjaxRequestTarget target) {
          ModalReferenciasUnica.this.cerrarModal(target);
        }
      };
    queue(new Component[] { (Component)cerrarModal });
    AjaxLink cancelar = new AjaxLink("cancelar") {
        public void onClick(AjaxRequestTarget target) {
          ModalReferenciasUnica.this.cerrarModal(target);
        }
      };
    queue(new Component[] { (Component)cancelar });
    AjaxSubmitLink aceptar = new AjaxSubmitLink("aceptar", formCheckGroup) {
        public void onSubmit(AjaxRequestTarget target) {
          ModalReferenciasUnica.this.aceptar(target, ModalReferenciasUnica.this.referenciasSeleccionadas);
        }
      };
    queue(new Component[] { (Component)aceptar });
  }
  
  private ListView inicializaVistaListaReferencias() {
    LoadableDetachableModel<List<NegocioPojo>> listaReferecnias = new LoadableDetachableModel<List<NegocioPojo>>() {
        protected List<NegocioPojo> load() {
          List<NegocioPojo> respone = new ArrayList<>();
          ModalReferenciasUnica.this.negocioModel.referenciaUnicaUsuarioCliente(ModalReferenciasUnica.this.usuarioSesion()).forEach(action -> {
                if (!ModalReferenciasUnica.this.referenciasAsignadas.contains(action))
                  respone.add(action); 
              });
          return respone;
        }
      };
    ListView<NegocioPojo> response = new ListView<NegocioPojo>("listaReferencias", (IModel)listaReferecnias) {
        protected void populateItem(ListItem<NegocioPojo> item) {
          item.add(new Component[] { (Component)new Check("seleccion", item.getModel()) });
          item.add(new Component[] { (Component)new Label("numeroReferencia", (IModel)Model.of(((NegocioPojo)item.getModelObject()).getReferenciaUnica().getNumeroReferencia())) });
          item.add(new Component[] { (Component)new Label("descripcion", (IModel)Model.of(((NegocioPojo)item.getModelObject()).getReferenciaUnica().getDescripcionReferencia())) });
          item.add(new Component[] { (Component)new Label("tipoDocumento", (IModel)Model.of(((NegocioPojo)item.getModelObject()).getReferenciaUnica().getTipoIdentificacion())) });
          item.add(new Component[] { (Component)new Label("identificacion", (IModel)Model.of(((NegocioPojo)item.getModelObject()).getReferenciaUnica().getIdentificacion())) });
          item.add(new Component[] { (Component)new Label("tipo", (IModel)Model.of(((NegocioPojo)item.getModelObject()).getReferenciaUnica().getTipoReferencia())) });
        }
      };
    return response;
  }
  
  public abstract void cerrarModal(AjaxRequestTarget paramAjaxRequestTarget);
  
  public abstract void aceptar(AjaxRequestTarget paramAjaxRequestTarget, List<NegocioPojo> paramList);
}
