package com.fiduciaria.microapp.page.stateless;

import com.fiduciaria.microapp.base.BasePage;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.devutils.stateless.StatelessComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.visit.IVisit;

@StatelessComponent
public class BaseStatelessPage extends BasePage {
  private final WebMarkupContainer modalRoot;
  
  private static final String modalContentId = "modalContent";
  
  public BaseStatelessPage(PageParameters parameters) {
    super(parameters);
    this.modalRoot = new WebMarkupContainer("modalRoot");
    this.modalRoot.setVisible(false);
  }
  
  protected void onInitialize() {
    super.onInitialize();
    setStatelessHint(true);
    checkStaless();
    ContextImage logo = new ContextImage("logo", "images/BBVA_WHITE.png");
    queue(new Component[] { (Component)logo });
    this.modalRoot.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)this.modalRoot });
    queue(new Component[] { (Component)new WebMarkupContainer("modalContent") });
  }
  
  private void checkStaless() {
    visitChildren((component, visit) -> {
          if (!component.isStateless());
        });
  }
  
  public void addModal(Component modal) {
    this.modalRoot.addOrReplace(new Component[] { modal });
  }
  
  public void showModal(boolean visible, AjaxRequestTarget target) {
    this.modalRoot.setVisible(visible);
    if (Objects.nonNull(target))
      target.add(new Component[] { (Component)this.modalRoot }); 
  }
  
  public void removeModal() {
    this.modalRoot.addOrReplace(new Component[] { (Component)new WebMarkupContainer("modalContent") });
  }
  
  public static String getModalContentId() {
    return "modalContent";
  }
}
