// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.usuario.model;

import com.fiduciaria.microapp.page.statefull.gestion.usuario.EnumOperacionNavegacionPanel;
import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import com.fiduciaria.microapp.store.model.persona.PersonaPojo;
import java.util.List;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.io.IClusterable;

public class GestionUsuariosVM implements IClusterable {
   private boolean nuevoUsuarioActivado;
   private EnumOperacionNavegacionPanel currenTabOption;
   private final IModel<PersonaPojo> personaModel = new Model(new PersonaPojo());
   private final IModel<List<NegocioPojo>> negocios = new ListModel();

   public GestionUsuariosVM() {
   }

   public EnumOperacionNavegacionPanel getCurrenTabOption() {
      return this.currenTabOption;
   }

   public void setCurrenTabOption(EnumOperacionNavegacionPanel currenTabOption) {
      this.currenTabOption = currenTabOption;
   }

   public IModel<PersonaPojo> getPersonaModel() {
      return this.personaModel;
   }

   public boolean isNuevoUsuarioActivado() {
      return this.nuevoUsuarioActivado;
   }

   public void setNuevoUsuarioActivado(boolean nuevoUsuarioActivado) {
      this.nuevoUsuarioActivado = nuevoUsuarioActivado;
   }
}
