// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.administracion.panel.negocio;

import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import com.fiduciaria.microapp.store.model.negocio.NegocioVM;
import java.util.Iterator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class SortableNegocioDataProvider extends SortableDataProvider<NegocioPojo, String> implements IFilterStateLocator<NegocioFilter> {
   private NegocioFilter filtro;
   private final NegocioVM negocioVM;

   SortableNegocioDataProvider(NegocioVM negociomodel) {
      this.negocioVM = negociomodel;
   }

   public Iterator<? extends NegocioPojo> iterator(long first, long count) {
      this.negocioVM.getParametros().setInicio(first);
      this.negocioVM.getParametros().setCuenta(count);
      this.negocioVM.getParametros().setSoloTotales(false);
      return this.negocioVM.negociosGeneralXUsuario().iterator();
   }

   public long size() {
      this.negocioVM.getParametros().setSoloTotales(true);
      return this.negocioVM.totalRegistrosNegocioGeneral();
   }

   public IModel<NegocioPojo> model(NegocioPojo object) {
      return new DetachableNegocioModel(object);
   }

   public NegocioFilter getFilterState() {
      return this.filtro;
   }

   public void setFilterState(NegocioFilter state) {
      this.filtro = state;
   }
}
