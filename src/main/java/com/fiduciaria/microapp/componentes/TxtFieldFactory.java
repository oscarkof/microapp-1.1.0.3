package com.fiduciaria.microapp.componentes;

import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import java.io.Serializable;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

public class TxtFieldFactory implements Serializable {
  public static TxtFieldFactory instancia() {
    return new TxtFieldFactory();
  }
  
  public TextField crearCampoTextStandarizado(String id, boolean required, boolean popover, String popoverMsg, IModel fieldLabel) {
    TextField textField = new TextField(id);
    textField.setOutputMarkupPlaceholderTag(true);
    textField.setRequired(required);
    textField.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(popover, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", popoverMsg) });
    textField.setLabel(fieldLabel);
    return textField;
  }
  
  public TextField crearAjaxCampoTextStandarizado(String id, boolean required, boolean popover, String popoverMsg, IModel<?> model, IModel fieldLabel, Class clazz) {
    final TextField textField = new TextField(id, model, clazz);
    textField.setOutputMarkupPlaceholderTag(true);
    textField.setRequired(required);
    textField.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(popover, FormGroupControlBehavior.PosicionPopover.ARRIBA, "", "", popoverMsg) });
    textField.setLabel(fieldLabel);
    textField.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              target.add(new Component[] { (Component)this.getFormComponent() });
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)this.getFormComponent() });
            }
          } });
    return textField;
  }
}
