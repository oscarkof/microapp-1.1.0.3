package com.fiduciaria.microapp.componentes.alerta;

import com.fiduciaria.microapp.base.BasePanel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public abstract class ModalAlert extends BasePanel {
  private final List<String> mensajesError;
  
  private final List<String> mensajesInfo;
  
  private final List<String> mensajesSuccess;
  
  private final List<String> mensajesWarning;
  
  public ModalAlert(String id) {
    super(id);
    this.mensajesError = new ArrayList<>();
    this.mensajesInfo = new ArrayList<>();
    this.mensajesSuccess = new ArrayList<>();
    this.mensajesWarning = new ArrayList<>();
  }
  
  protected void onInitialize() {
    super.onInitialize();
    AjaxLink cerrarBtn = new AjaxLink("cerrarBtn") {
        public void onClick(AjaxRequestTarget target) {
          ModalAlert.this.cerrarModal(target);
        }
      };
    queue(new Component[] { (Component)cerrarBtn });
    initInfoAlert();
    initSuccesAlert();
    initErrorAlert();
  }
  
  private void initInfoAlert() {
    WebMarkupContainer infoAlert = new WebMarkupContainer("infoAlert") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!ModalAlert.this.mensajesInfo.isEmpty());
        }
      };
    queue(new Component[] { (Component)infoAlert });
    ListView<String> infolistview = new ListView<String>("infolistview", this.mensajesInfo) {
        protected void populateItem(ListItem<String> item) {
          item.add(new Component[] { (Component)new Label("msg", (IModel)Model.of((Serializable)item.getModelObject())) });
        }
      };
    queue(new Component[] { (Component)infolistview });
  }
  
  private void initSuccesAlert() {
    WebMarkupContainer successAlert = new WebMarkupContainer("successAlert") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!ModalAlert.this.mensajesSuccess.isEmpty());
        }
      };
    queue(new Component[] { (Component)successAlert });
    ListView<String> successlistview = new ListView<String>("successlistview", this.mensajesSuccess) {
        protected void populateItem(ListItem<String> item) {
          item.add(new Component[] { (Component)new Label("msg", (IModel)Model.of((Serializable)item.getModelObject())) });
        }
      };
    queue(new Component[] { (Component)successlistview });
  }
  
  private void initErrorAlert() {
    WebMarkupContainer dangerAlert = new WebMarkupContainer("dangerAlert") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(!ModalAlert.this.mensajesError.isEmpty());
        }
      };
    queue(new Component[] { (Component)dangerAlert });
    ListView<String> dangerlistview = new ListView<String>("dangerlistview", this.mensajesError) {
        protected void populateItem(ListItem<String> item) {
          item.add(new Component[] { (Component)new Label("msg", (IModel)Model.of((Serializable)item.getModelObject())) });
        }
      };
    queue(new Component[] { (Component)dangerlistview });
  }
  
  public List<String> getMensajesError() {
    return this.mensajesError;
  }
  
  public List<String> getMensajesInfo() {
    return this.mensajesInfo;
  }
  
  public List<String> getMensajesSuccess() {
    return this.mensajesSuccess;
  }
  
  public abstract void cerrarModal(AjaxRequestTarget paramAjaxRequestTarget);
}
