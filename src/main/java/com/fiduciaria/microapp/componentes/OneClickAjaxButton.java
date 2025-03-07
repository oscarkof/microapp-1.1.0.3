package com.fiduciaria.microapp.componentes;

import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

public class OneClickAjaxButton extends AjaxButton {
  private boolean block = false;
  
  private boolean callToAction = false;
  
  private boolean small = false;
  
  private ButtonType buttontype = ButtonType.PRIMARY;
  
  private boolean indicatingEnabled = false;
  
  public OneClickAjaxButton(String id) {
    super(id);
    setOutputMarkupPlaceholderTag(true);
  }
  
  public OneClickAjaxButton(String id, IModel<String> model) {
    super(id, model);
    setOutputMarkupPlaceholderTag(true);
  }
  
  public OneClickAjaxButton(String id, Form<?> form) {
    super(id, form);
    setOutputMarkupPlaceholderTag(true);
  }
  
  public OneClickAjaxButton(String id, IModel<String> model, Form<?> form) {
    super(id, model, form);
    setOutputMarkupPlaceholderTag(true);
  }
  
  public boolean isCallToAction() {
    return this.callToAction;
  }
  
  public void setCallToAction(boolean callToAction) {
    this.callToAction = callToAction;
  }
  
  public boolean isBlock() {
    return this.block;
  }
  
  public void setBlock(boolean block) {
    this.block = block;
  }
  
  public boolean isSmall() {
    return this.small;
  }
  
  public void setSmall(boolean small) {
    this.small = small;
  }
  
  public boolean isIndicatingEnabled() {
    return this.indicatingEnabled;
  }
  
  public void setIndicatingEnabled(boolean indicatingEnabled) {
    this.indicatingEnabled = indicatingEnabled;
  }
  
  public ButtonType getButtontype() {
    return this.buttontype;
  }
  
  public void setButtontype(ButtonType buttontype) {
    this.buttontype = buttontype;
  }
  
  protected void onComponentTag(ComponentTag tag) {
    String blockOrCallToAction;
    super.onComponentTag(tag);
    if (this.block) {
      blockOrCallToAction = " pf-m-block ";
    } else if (this.callToAction) {
      blockOrCallToAction = " pf-m-display-lg ";
    } else {
      blockOrCallToAction = "";
    } 
    String buttonclass = "";
    switch (this.buttontype) {
      case PRIMARY:
        buttonclass = "pf-c-button pf-m-primary ";
        break;
      case SECONDARY:
        buttonclass = "pf-c-button pf-m-secondary ";
        break;
      case TERTIARY:
        buttonclass = "pf-c-button pf-m-tertiary ";
        break;
      case DANGER:
        buttonclass = "pf-c-button pf-m-danger ";
        break;
      case WARNING:
        buttonclass = "pf-c-button pf-m-warning ";
        break;
      case CONTROL:
        buttonclass = "pf-c-button pf-m-control ";
        break;
      case LINK:
        buttonclass = "pf-c-button pf-m-link ";
        break;
      case INLINELINK:
        buttonclass = "pf-c-button pf-m-inline pf-m-link ";
        break;
      case PLAIN:
        buttonclass = "pf-c-button pf-m-plain ";
        break;
      default:
        buttonclass = "pf-c-button pf-m-primary ";
        break;
    } 
    String smallclass = "";
    if (this.small)
      smallclass = " pf-m-small "; 
    tag.put("class", buttonclass + blockOrCallToAction + smallclass);
  }
  
  protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
    super.updateAjaxAttributes(attributes);
    AjaxCallListener listener = new AjaxCallListener() {
      
      };
    String indicatingclass = "";
    if (this.indicatingEnabled)
      indicatingclass = "$('#" + getMarkupId() + "').addClass('pf-m-in-progress');$('#" + getMarkupId() + "').prepend('<span id=\"spinner\" class=\"pf-c-button__progress\" > <span class=\"pf-c-spinner pf-m-md\" role=\"progressbar\" aria-valuetext=\"Loading...\"> <span class=\"pf-c-spinner__clipper\"></span> <span class=\"pf-c-spinner__lead-ball\"></span> <span class=\"pf-c-spinner__tail-ball\"></span></span></span>');"; 
    String indicatingclasscomplete = "";
    if (this.indicatingEnabled)
      indicatingclasscomplete = "$('#" + getMarkupId() + "').children('span.pf-c-button__progress').remove();$('#" + getMarkupId() + "').removeClass('pf-m-progress');$('#" + getMarkupId() + "').removeClass('pf-m-in-progress');"; 
    listener.onBeforeSend("$('#" + 
        getMarkupId() + "').addClass('pf-m-progress');" + indicatingclass + "$('#" + 
        
        getMarkupId() + "').prop('disabled', true);");
    listener.onComplete("if(jqXHR !=null){ if(jqXHR.status!=200){ window.location.reload(); }};$('#" + 
        getMarkupId() + "').prop('disabled', false);" + indicatingclasscomplete);
    listener.onFailure("$('#" + getMarkupId() + "').prop('disabled', false);" + indicatingclasscomplete);
    attributes.getAjaxCallListeners().add(listener);
  }
}
