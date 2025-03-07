// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.decorator.behavior;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.IAjaxRegionMarkupIdProvider;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.value.ValueMap;

public class FieldDecorator extends Behavior implements IAjaxRegionMarkupIdProvider {
   private String baseClassessval = "";

   public FieldDecorator() {
   }

   public void onConfigure(Component component) {
      FormComponent<?> fc = (FormComponent)component;
      ValueMap attrbts = fc.getMarkupAttributes();
      StringValue baseClassess = attrbts.getStringValue("class");
      if (baseClassess != null) {
         this.baseClassessval = baseClassess.toString();
         if (this.baseClassessval != null) {
            this.baseClassessval = this.baseClassessval.replace("pf-c-form-control", "");
            this.baseClassessval = this.baseClassessval.replace("pf-m-success", "");
         }
      } else {
         baseClassess = StringValue.valueOf("");
      }

      if (!((FormComponent)component).isValid() && fc.isRequired()) {
         fc.add(new Behavior[]{new AttributeModifier("class", "pf-c-form-control " + this.baseClassessval)});
         fc.add(new Behavior[]{new AttributeModifier("aria-invalid", "true")});
      } else if (fc.isRequired() && ((FormComponent)component).getInput() == null) {
         fc.add(new Behavior[]{new AttributeModifier("class", "pf-c-form-control " + this.baseClassessval)});
         fc.add(new Behavior[]{new AttributeModifier("aria-invalid", "false")});
      } else if (!((FormComponent)component).isValid()) {
         fc.add(new Behavior[]{new AttributeModifier("class", "pf-c-form-control " + this.baseClassessval)});
         fc.add(new Behavior[]{new AttributeModifier("aria-invalid", "true")});
      } else if (((FormComponent)component).hasRawInput() && ((FormComponent)component).isValid()) {
         fc.add(new Behavior[]{new AttributeModifier("class", "pf-c-form-control pf-m-success " + this.baseClassessval)});
         fc.add(new Behavior[]{new AttributeModifier("aria-invalid", "false")});
      } else {
         fc.add(new Behavior[]{new AttributeModifier("class", "pf-c-form-control pf-m-success " + this.baseClassessval)});
         fc.add(new Behavior[]{new AttributeModifier("aria-invalid", "false")});
      }

      if (!fc.isEnabled()) {
         fc.add(new Behavior[]{new AttributeModifier("class", "pf-c-form-control " + this.baseClassessval)});
         fc.add(new Behavior[]{new AttributeModifier("aria-invalid", "")});
      }

   }

   public void onComponentTag(Component component, ComponentTag tag) {
      if (!((FormComponent)component).isValid()) {
         FormComponent fc = (FormComponent)component;
         Response r = component.getResponse();
         tag.put("aria-invalid", "true");
      } else {
         tag.put("aria-invalid", "false");
      }

   }

   public void bind(Component component) {
      if (!(component instanceof FormComponent)) {
         throw new IllegalArgumentException();
      }
   }

   public String getAjaxRegionMarkupId(Component component) {
      return component.getMarkupId() + "_fd";
   }

   public void beforeRender(Component component) {
      Response r = component.getResponse();
      r.write("<div id=\"");
      r.write(this.getAjaxRegionMarkupId(component));
      r.write("\" class=\"nukak-pf-input-decorator " + this.baseClassessval + "\">");
   }

   public void afterRender(Component component) {
      FormComponent fc = (FormComponent)component;
      Response r = component.getResponse();
      if (fc.hasFeedbackMessage()) {
         String error = fc.getFeedbackMessages().first().getMessage().toString();
         r.write("<p id=\"hideMe\" class=\"pf-c-form__helper-text pf-m-error\" aria-live=\"polite\">" + error + "</p>");
         fc.clearInput();
         fc.getFeedbackMessages().clear();
      }

      r.write("</div>");
   }
}
