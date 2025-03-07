package com.fiduciaria.microapp.page.statefull.gestion.usuario;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.page.statefull.gestion.usuario.model.GestionUsuariosVM;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.model.IModel;

public class EnrolamientoMainPanel extends BasePanel {
  private StringBuilder values = new StringBuilder();
  
  private IModel<GestionUsuariosVM> modelo;
  
  private class FiltroUpdate extends Behavior {}
  
  public EnrolamientoMainPanel(String id, IModel<GestionUsuariosVM> model) {
    super(id, model);
    this.modelo = model;
  }
  
  protected void onInitialize() {
    super.onInitialize();
  }
  
  public static final EnrolamientoMainPanel getInstance(String id, IModel<GestionUsuariosVM> model) {
    return new EnrolamientoMainPanel(id, model);
  }
}
