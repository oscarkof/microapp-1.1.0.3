package com.fiduciaria.microapp.page.statefull.negocio.condiciones;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.store.model.escritura.CargueFirmasEscrituraError;
import com.fiduciaria.microapp.store.model.escritura.CondicionesManejoPojo;
import com.fiduciaria.microapp.store.model.escritura.EscrituraCondicionTipo;
import com.fiduciaria.microapp.store.model.escritura.EscrituraFirmasCondiciones;
import com.fiduciaria.microapp.store.model.escritura.EscrituraVM;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class CondicionesFirmas extends BasePanel {
  @SpringBean
  IGenericHttpClient gtwayhttp;
  
  private final IModel<CondicionesManejoPojo> modelo;
  
  private final WebMarkupContainer fragmentPanel = new WebMarkupContainer("fragmentPanel");
  
  private final String fragmentContentId = "content";
  
  private final List<CargueFirmasEscrituraError> listaAlertas = new ArrayList<>();
  
  private final class UpdOnFragmentAction extends Behavior {
    private UpdOnFragmentAction() {}
  }
  
  public CondicionesFirmas(String id, IModel<?> model) {
    super(id, model);
    this.modelo = (IModel<CondicionesManejoPojo>)new Model((Serializable)new CondicionesManejoPojo());
    this.fragmentPanel.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)this.fragmentPanel });
    this.fragmentPanel.add(new Behavior[] { new UpdOnFragmentAction() });
    this.fragmentPanel.addOrReplace(new Component[] { (Component)new WebMarkupContainer("content") });
    initCondicionesManejoFragment();
  }
  
  protected void onInitialize() {
    super.onInitialize();
    final WebMarkupContainer alertasGrupo = initGrupoAlertas();
    queue(new Component[] { (Component)new AjaxLink("guardarCondicionesManejo") {
            public void onClick(AjaxRequestTarget target) {
              CondicionesFirmas.this.listaAlertas.clear();
              EscrituraVM condicionesNegocioVM = new EscrituraVM(CondicionesFirmas.this.gtwayhttp);
              ((CondicionesManejoPojo)CondicionesFirmas.this.modelo.getObject()).setUsuarioCargue(CondicionesFirmas.this.usuarioSesion());
              if (CondicionesFirmas.this.validarCondicionesNegocioParaGuardar()) {
                CondicionesFirmas.this.listaAlertas.addAll(condicionesNegocioVM.guardarCondicionesManejo((CondicionesManejoPojo)CondicionesFirmas.this.modelo.getObject()));
                if (CondicionesFirmas.this.listaAlertas.isEmpty()) {
                  CondicionesFirmas.this.listaAlertas.add(new CargueFirmasEscrituraError());
                  ((CargueFirmasEscrituraError)CondicionesFirmas.this.listaAlertas.get(0)).setMensajeError("Condiciones aplicadas de forma exitosa");
                  ((CargueFirmasEscrituraError)CondicionesFirmas.this.listaAlertas.get(0)).setAlertaExitosa(true);
                } 
              } else {
                CondicionesFirmas.this.listaAlertas.add(new CargueFirmasEscrituraError());
                ((CargueFirmasEscrituraError)CondicionesFirmas.this.listaAlertas.get(0)).setMensajeError("No se cumplen con las condiciones para ser aplicadas. Favor revisar e intentar nuevamente. ");
                ((CargueFirmasEscrituraError)CondicionesFirmas.this.listaAlertas.get(0)).setAlertaExitosa(false);
              } 
              target.add(new Component[] { (Component)alertasGrupo });
            }
          } });
  }
  
  private boolean validarCondicionesNegocioParaGuardar() {
    boolean response = false;
    if (Objects.nonNull(((CondicionesManejoPojo)this.modelo.getObject()).getNumeroEscritura()) && 
      !((CondicionesManejoPojo)this.modelo.getObject()).getNumeroEscritura().trim().isEmpty())
      if (((CondicionesManejoPojo)this.modelo.getObject()).getNumeroFirmas().intValue() == ((CondicionesManejoPojo)this.modelo.getObject()).getEscrituraUsuarioTipo().size() && (
        (CondicionesManejoPojo)this.modelo.getObject()).getEscrituraFirmasCondiciones().size() > 0) {
        if (!enableEscrituraCondicionTipo((CondicionesManejoPojo)this.modelo.getObject()) || 
          !obtenerEscriturasFirmasCondicionesVariableS((CondicionesManejoPojo)this.modelo.getObject()).isEmpty())
          return response; 
        if (!enableConceptoUsuarioTipo((CondicionesManejoPojo)this.modelo.getObject()) || enableFormularioConceptoUsuarioTipo((CondicionesManejoPojo)this.modelo.getObject()))
          return response; 
        response = true;
      }  
    return response;
  }
  
  private boolean enableFormularioConceptoUsuarioTipo(CondicionesManejoPojo condicionesManejo) {
    List<Boolean> listaCondicionVariable = new ArrayList<>();
    if (Objects.isNull(condicionesManejo.getEscrituraFirmasCondiciones()) || 
      !condicionesManejo.getEscrituraFirmasCondiciones().isEmpty())
      condicionesManejo.getEscrituraFirmasCondiciones().forEach((k, v) -> {
            if (v.getFijoVariable().equalsIgnoreCase("f") && condicionesManejo.getEscrituraUsuarioTipo().size() > v.getConceptoUsuarioTipo().size())
              listaCondicionVariable.add(Boolean.valueOf(true)); 
          }); 
    return !listaCondicionVariable.isEmpty();
  }
  
  private boolean enableConceptoUsuarioTipo(CondicionesManejoPojo condicionesManejo) {
    List<Boolean> listaCondicionVariable = new ArrayList<>();
    if (Objects.isNull(condicionesManejo.getEscrituraFirmasCondiciones()) || 
      !condicionesManejo.getEscrituraFirmasCondiciones().isEmpty())
      condicionesManejo.getEscrituraFirmasCondiciones().forEach((k, v) -> {
            if (v.getFijoVariable().equalsIgnoreCase("f"))
              listaCondicionVariable.add(Boolean.valueOf(true)); 
          }); 
    return !listaCondicionVariable.isEmpty();
  }
  
  private List<EscrituraFirmasCondiciones> obtenerEscriturasFirmasCondicionesVariableS(CondicionesManejoPojo condicionesManejo) {
    List<EscrituraFirmasCondiciones> response = new ArrayList<>();
    condicionesManejo.getEscrituraFirmasCondiciones().forEach((llave, valor) -> {
          if (valor.getVariableSn().equalsIgnoreCase("S")) {
            int totalNumFirmasIngresadas = 0;
            for (Map.Entry<String, EscrituraCondicionTipo> entryset : (Iterable<Map.Entry<String, EscrituraCondicionTipo>>)valor.getEscrituraCondicionTipo().entrySet())
              totalNumFirmasIngresadas += ((EscrituraCondicionTipo)entryset.getValue()).getNumFirmas().intValue(); 
            if (totalNumFirmasIngresadas < valor.getNumFirmas().intValue())
              response.add(valor); 
          } 
        });
    return response;
  }
  
  private boolean enableEscrituraCondicionTipo(CondicionesManejoPojo condicionesManejo) {
    List<Boolean> listaCondicionVariable = new ArrayList<>();
    if (Objects.isNull(condicionesManejo.getEscrituraFirmasCondiciones()) || 
      !condicionesManejo.getEscrituraFirmasCondiciones().isEmpty())
      condicionesManejo.getEscrituraFirmasCondiciones().forEach((k, v) -> {
            if (v.getVariableSn().equalsIgnoreCase("s"))
              listaCondicionVariable.add(Boolean.valueOf(true)); 
          }); 
    return !listaCondicionVariable.isEmpty();
  }
  
  private WebMarkupContainer initGrupoAlertas() {
    WebMarkupContainer alertasGrupo = new WebMarkupContainer("alertasGrupo") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!CondicionesFirmas.this.listaAlertas.isEmpty());
        }
      };
    queue(new Component[] { (Component)alertasGrupo });
    alertasGrupo.setOutputMarkupPlaceholderTag(true);
    ListView<CargueFirmasEscrituraError> vistaListaAlertas = new ListView<CargueFirmasEscrituraError>("vistaListaAlertas", this.listaAlertas) {
        protected void populateItem(final ListItem<CargueFirmasEscrituraError> item) {
          WebMarkupContainer alerta = new WebMarkupContainer("alerta") {
              private final String claseCss = "class";
              
              protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                if (((CargueFirmasEscrituraError)item.getModelObject()).isAlertaExitosa()) {
                  tag.put("class", "pf-c-alert pf-m-inline pf-m-success");
                } else {
                  tag.put("class", "pf-c-alert pf-m-inline pf-m-danger");
                } 
              }
            };
          item.add(new Component[] { (Component)alerta });
          alerta.add(new Component[] { (Component)new WebMarkupContainer("alertaicono") {
                  private final String claseCss = "class";
                  
                  protected void onComponentTag(ComponentTag tag) {
                    super.onComponentTag(tag);
                    if (((CargueFirmasEscrituraError)item.getModelObject()).isAlertaExitosa()) {
                      tag.put("class", "fas fa-fw fa-check-circle");
                    } else {
                      tag.put("class", "fas fa-fw fa-exclamation-circle");
                    } 
                  }
                } });
          alerta.add(new Component[] { (Component)new Label("tituloalerta", () -> {
                    String response = "";
                    CargueFirmasEscrituraError obj = (CargueFirmasEscrituraError)item.getModelObject();
                    if (!obj.isAlertaExitosa()) {
                      response = Objects.nonNull(obj.getSecCondicion()) ? ("  Sec.Condicion  :  " + obj.getSecCondicion()) : " ";
                      response = response + (Objects.nonNull(obj.getSecuencialError()) ? (" <br>  Sec Error  : " + obj.getSecuencialError() + "<br>") : "  ");
                      response = response + (Objects.nonNull(obj.getMensajeError()) ? ("  Error  : " + obj.getMensajeError()) : "");
                    } else {
                      response = obj.getMensajeError();
                    } 
                    return response;
                  }) {
                  protected void onConfigure() {
                    super.onConfigure();
                    setEscapeModelStrings(false);
                  }
                } });
        }
      };
    queue(new Component[] { (Component)vistaListaAlertas });
    return alertasGrupo;
  }
  
  private void setFramentPanelDefault(AjaxRequestTarget target) {
    this.fragmentPanel.addOrReplace(new Component[] { (Component)new WebMarkupContainer("content") });
    target.add(new Component[] { (Component)this.fragmentPanel });
  }
  
  private void initCondicionesManejoFragment() {
    this.fragmentPanel.addOrReplace(new Component[] { (Component)new CondicionesNegocioFragment("content", "condicionesNegocioFragment", (MarkupContainer)this, this.modelo, this) {
            public void cerrar(AjaxRequestTarget target) {
              CondicionesFirmas.this.setFramentPanelDefault(target);
              CondicionesFirmas.this.registrarComportamiento(CondicionesFirmas.UpdOnFragmentAction.class);
            }
          } });
  }
}
