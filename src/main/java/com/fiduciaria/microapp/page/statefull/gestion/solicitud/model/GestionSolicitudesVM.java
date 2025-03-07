// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud.model;

import com.fiduciaria.microapp.page.statefull.gestion.solicitud.EnumOperacionNavegacionSolicitudesPanel;
import org.apache.wicket.util.io.IClusterable;

public class GestionSolicitudesVM implements IClusterable {
   private SolicitudPojo modelo;
   private EnumOperacionNavegacionSolicitudesPanel currenTabOption;

   public GestionSolicitudesVM() {
   }

   public EnumOperacionNavegacionSolicitudesPanel getCurrenTabOption() {
      return this.currenTabOption;
   }

   public void setCurrenTabOption(EnumOperacionNavegacionSolicitudesPanel currenTabOption) {
      this.currenTabOption = currenTabOption;
   }
}
