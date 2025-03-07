// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.actividades.model;

import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.page.statefull.gestion.actividades.EnumOperacionNavegacionActividadesPanel;
import com.fiduciaria.microapp.store.model.fideicomiso.FideicomisoPojo;
import com.fiduciaria.microapp.store.model.referunica.ReferenciaUnicaPojo;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.io.IClusterable;

public class GestionActividadesVM implements IClusterable {
   private boolean seleccionadoNegocio;
   private GenericHttpClient gtwayHttp;
   private EnumOperacionNavegacionActividadesPanel currenTabOption;
   private IModel<GestionActividadesM> modelo;
   private IModel<GestionActividadesEstados> estadosComponente;

   public GestionActividadesVM() {
      this.initModelo();
   }

   public EnumOperacionNavegacionActividadesPanel getCurrenTabOption() {
      return this.currenTabOption;
   }

   public void setCurrenTabOption(EnumOperacionNavegacionActividadesPanel currenTabOption) {
      this.currenTabOption = currenTabOption;
   }

   private void initModelo() {
      this.modelo = new Model(new GestionActividadesM());
      ((GestionActividadesM)this.modelo.getObject()).getNegocio().setFideicomiso(new FideicomisoPojo());
      ((GestionActividadesM)this.modelo.getObject()).getNegocio().setReferenciaUnica(new ReferenciaUnicaPojo());
      this.estadosComponente = new Model(new GestionActividadesEstados());
   }

   public boolean isSeleccionadoNegocio() {
      return this.seleccionadoNegocio;
   }

   public void setSeleccionadoNegocio(boolean seleccionadoNegocio) {
      this.seleccionadoNegocio = seleccionadoNegocio;
   }

   public GenericHttpClient getGtwayHttp() {
      return this.gtwayHttp;
   }

   public void setGtwayHttp(GenericHttpClient gtwayHttp) {
      this.gtwayHttp = gtwayHttp;
   }

   public IModel<GestionActividadesM> getModelo() {
      return this.modelo;
   }

   public void setModelo(IModel<GestionActividadesM> modelo) {
      this.modelo = modelo;
   }

   public IModel<GestionActividadesEstados> getEstadosComponente() {
      return this.estadosComponente;
   }

   public void setEstadosComponente(IModel<GestionActividadesEstados> estadosComponente) {
      this.estadosComponente = estadosComponente;
   }
}
