// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.administracion.model;

import com.fiduciaria.microapp.page.statefull.administracion.EnumOperacionNavegacionPanel;
import org.apache.wicket.util.io.IClusterable;

public class AdministracionTenantVM implements IClusterable {
   private EnumOperacionNavegacionPanel currenTabOption;

   public AdministracionTenantVM() {
   }

   public EnumOperacionNavegacionPanel getCurrenTabOption() {
      return this.currenTabOption;
   }

   public void setCurrenTabOption(EnumOperacionNavegacionPanel currenTabOption) {
      this.currenTabOption = currenTabOption;
   }
}
