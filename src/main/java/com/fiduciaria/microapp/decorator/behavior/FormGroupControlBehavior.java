package com.fiduciaria.microapp.decorator.behavior;

import com.fiduciaria.microapp.decorator.popover.PopoverJSResourceReference;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.IAjaxRegionMarkupIdProvider;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.string.StringValue;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.value.ValueMap;

public class FormGroupControlBehavior extends Behavior implements IAjaxRegionMarkupIdProvider {
  private static final long serialVersionUID = 1L;
  
  private static final String ARIA_INVALID_TAG = "aria-invalid";
  
  private static final String CSS_CLASS_TAG = "class";
  
  private static final String PF_CLASS_SUCCESS = "pf-m-success";
  
  private static final String PF_CLASS_FORM_CONTROL = "pf-c-form-control";
  
  private PosicionPopover posicion;
  
  private boolean ayudaPopover;
  
  private String headerPopover;
  
  private String footerPopover;
  
  private String bodyPopover;
  
  private boolean mostrarSignoRequerido;
  
  public static final FormGroupControlBehavior INSTANCE = new FormGroupControlBehavior(false, PosicionPopover.ARRIBA, "", "", "");
  
  public FormGroupControlBehavior(boolean ayudaPopover, PosicionPopover posicion, String headerPopover, String footerPopover, String bodyPopover) {
    this.posicion = posicion;
    this.ayudaPopover = ayudaPopover;
    this.headerPopover = headerPopover;
    this.footerPopover = footerPopover;
    this.bodyPopover = bodyPopover;
    this.mostrarSignoRequerido = true;
  }
  
  private void addInvalidInputTags(ComponentTag tag, StringBuilder strbld) {
    tag.put("class", strbld.toString());
    tag.put("aria-invalid", "true");
  }
  
  private void addValidInputTags(ComponentTag tag, StringBuilder strbld) {
    tag.put("class", strbld.toString());
    tag.put("aria-invalid", "false");
  }
  
  public void onComponentTag(Component component, ComponentTag tag) {
    FormComponent<?> fcomp = (FormComponent)component;
    ValueMap attrbts = fcomp.getMarkupAttributes();
    StringValue baseClassess = attrbts.getStringValue("class");
    String baseClassessval = "";
    if (baseClassess != null) {
      baseClassessval = baseClassess.toString();
      if (baseClassessval != null && !Strings.isEmpty(baseClassessval)) {
        baseClassessval = baseClassessval.replace("pf-c-form-control", "");
        baseClassessval = baseClassessval.replace("pf-m-success", "");
      } else {
        baseClassessval = "";
      } 
    } 
    tag.put("class", "" + baseClassessval);
    tag.put("aria-invalid", "false");
    StringBuilder strbld = new StringBuilder();
    strbld.append("pf-c-form-control");
    strbld.append(" ");
    if ((!fcomp.isValid() && fcomp.isRequired()) || !fcomp.isValid()) {
      strbld.append(baseClassessval);
      addInvalidInputTags(tag, strbld);
    } else if ((fcomp.isRequired() && fcomp.getInput() == null) || (
      !fcomp.isRequired() && fcomp
      .isValid() && 
      !fcomp.hasRawInput() && fcomp
      .getInput() == null)) {
      strbld.append(baseClassessval);
      addValidInputTags(tag, strbld);
    } else if ((fcomp.hasRawInput() && fcomp
      .isValid()) || fcomp
      .isValid()) {
      strbld.append("pf-m-success");
      strbld.append(" ");
      strbld.append(baseClassessval);
      addValidInputTags(tag, strbld);
    } 
    if (!fcomp.isEnabled()) {
      strbld.append(baseClassessval);
      tag.put("class", strbld.toString());
      tag.put("aria-invalid", "");
    } 
    super.onComponentTag(component, tag);
  }
  
  public void bind(Component component) {
    if (!(component instanceof FormComponent))
      throw new IllegalArgumentException(); 
  }
  
  public String getAjaxRegionMarkupId(Component component) {
    return component.getMarkupId() + "_fd";
  }
  
  public void beforeRender(Component component) {
    FormComponent<?> fc = (FormComponent)component;
    Response r = component.getResponse();
    r.write("<div id=\"");
    r.write(getAjaxRegionMarkupId(component));
    r.write("\" class=\"pf-c-form__group\">");
    r.write("<div class=\"pf-c-form__group-label\">\n  <label class=\"pf-c-form__label\" for=\"" + component
        .getMarkupId() + "\">\n     <span class=\"pf-c-form__label-text\">");
    if (fc.getLabel() != null) {
      r.write((CharSequence)fc.getLabel().getObject());
    } else {
      r.write("");
    } 
    r.write("         </span>\n");
    if (fc.isRequired() && this.mostrarSignoRequerido)
      r.write("&nbsp;<span class=\"pf-c-form__label-required\" aria-hidden=\"true\">&#42;</span>\n"); 
    if (this.ayudaPopover)
      r.write(" <button class=\"pf-c-form__group-label-help\"\n   aria-label=\"\"\n   aria-describedby=\"form-vertical-name\"\n   role=\"button\" type=\"button\" \n  ><i id=\"" + component
          
          .getMarkupId() + "help\" data-popover-target=\"my-popover\" \n                class=\"pficon pf-icon-help\" aria-hidden=\"true\"></i></button>"); 
    r.write("</label>\n  </div>\n  <div class=\"pf-c-form__group-control\">");
    r.write("");
  }
  
  public void afterRender(Component component) {
    FormComponent fc = (FormComponent)component;
    Response r = component.getResponse();
    if (fc.hasFeedbackMessage()) {
      String error = fc.getFeedbackMessages().first().getMessage().toString();
      r.write("<p  class=\"pf-c-form__helper-text pf-m-error \" aria-live=\"\">" + error + "</p>");
      fc.clearInput();
      fc.getFeedbackMessages().clear();
    } else {
      r.write("<p class=\"pf-c-form__helper-text\" \n  aria-live=\"\"></p>");
    } 
    r.write("\n </div>\n</div>");
  }
  
  public void renderHead(Component component, IHeaderResponse response) {
    super.renderHead(component, response);
    StringBuilder objConf = new StringBuilder();
    objConf.append("{ position: '")
      .append(getPosicion().getNombre())
      .append("', bodyPopover: '")
      .append(getBodyPopover())
      .append("', headerPopover:'")
      .append(getHeaderPopover())
      .append("', footerPopover :'")
      .append(getFooterPopover())
      .append("' }");
    String compMarkupId = component.getMarkupId() + "help";
    StringBuilder scripOnload = new StringBuilder();
    scripOnload.append("const trigger");
    scripOnload.append(compMarkupId);
    scripOnload.append(" = document.getElementById('");
    scripOnload.append(compMarkupId);
    scripOnload.append("');");
    scripOnload.append("let popover");
    scripOnload.append(compMarkupId);
    scripOnload.append(" = new Popover(trigger");
    scripOnload.append(compMarkupId);
    scripOnload.append(", ");
    scripOnload.append(objConf.toString());
    scripOnload.append(");");
    scripOnload.append("trigger");
    scripOnload.append(compMarkupId);
    scripOnload.append(".addEventListener('click', () =>  popover");
    scripOnload.append(compMarkupId);
    scripOnload.append(".toggle()); ");
    if (this.ayudaPopover) {
      response.render(
          (HeaderItem)JavaScriptReferenceHeaderItem.forReference((ResourceReference)new PopoverJSResourceReference()));
      response.render(
          (HeaderItem)OnDomReadyHeaderItem.forScript(scripOnload.toString()));
    } 
  }
  
  public void detach(Component component) {
    super.detach(component);
  }
  
  public PosicionPopover getPosicion() {
    return this.posicion;
  }
  
  public void setPosicion(PosicionPopover posicion) {
    this.posicion = posicion;
  }
  
  public boolean isAyudaPopover() {
    return this.ayudaPopover;
  }
  
  public void setAyudaPopover(boolean ayudaPopover) {
    this.ayudaPopover = ayudaPopover;
  }
  
  public String getHeaderPopover() {
    return this.headerPopover;
  }
  
  public void setHeaderPopover(String headerPopover) {
    this.headerPopover = headerPopover;
  }
  
  public String getFooterPopover() {
    return this.footerPopover;
  }
  
  public void setFooterPopover(String footerPopover) {
    this.footerPopover = footerPopover;
  }
  
  public String getBodyPopover() {
    return this.bodyPopover;
  }
  
  public void setBodyPopover(String bodyPopover) {
    this.bodyPopover = bodyPopover;
  }
  
  public enum PosicionPopover {
    ARRIBA("top"),
    ABAJO("bottom"),
    IZQUIERDA("left"),
    DERECHA("right");
    
    private final String nombre;
    
    PosicionPopover(String nombre) {
      this.nombre = nombre;
    }
    
    public String getNombre() {
      return this.nombre;
    }
  }
  
  public boolean isMostrarSignoRequerido() {
    return this.mostrarSignoRequerido;
  }
  
  public void setMostrarSignoRequerido(boolean mostrarSignoRequerido) {
    this.mostrarSignoRequerido = mostrarSignoRequerido;
  }
}
