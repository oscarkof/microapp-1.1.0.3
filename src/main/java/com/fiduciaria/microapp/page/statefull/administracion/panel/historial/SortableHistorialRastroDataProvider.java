// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.administracion.panel.historial;

import com.fiduciaria.microapp.base.NombreAccionesEnum;
import com.fiduciaria.microapp.store.model.rastro.EnumCampoTraceColumn;
import com.fiduciaria.microapp.store.model.rastro.RastroAccionPojo;
import com.fiduciaria.microapp.store.model.rastro.TraceVM;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class SortableHistorialRastroDataProvider extends SortableDataProvider<RastroAccionPojo, String> implements IFilterStateLocator<HistorialRastroDominioFilter> {
   private HistorialRastroDominioFilter contactFilter = new HistorialRastroDominioFilter();
   private final TraceVM traceVm;

   public SortableHistorialRastroDataProvider(TraceVM tracedb) {
      this.setSort("primerNombre", SortOrder.ASCENDING);
      this.traceVm = tracedb;
   }

   public Iterator<RastroAccionPojo> iterator(long inicio, long cuenta) {
      this.traceVm.getParametros().setInicio(inicio);
      this.traceVm.getParametros().setCuenta(cuenta);
      if (Objects.isNull(this.traceVm.getParametros().getCondiciones())) {
         this.traceVm.getParametros().setCondiciones(new HashMap());
      }

      this.traceVm.getParametros().getCondiciones().put(EnumCampoTraceColumn.ACCION, NombreAccionesEnum.DOMINIO.getAccion());
      this.traceVm.getParametros().setSoloTotales(false);
      return this.traceVm.rastrosActividadEnUsuario().iterator();
   }

   public long size() {
      this.traceVm.getParametros().setSoloTotales(true);
      return this.traceVm.totalRegistros();
   }

   public IModel<RastroAccionPojo> model(RastroAccionPojo object) {
      return new DetachableHistorialRastroModel(object);
   }

   public HistorialRastroDominioFilter getFilterState() {
      return this.contactFilter;
   }

   public void setFilterState(HistorialRastroDominioFilter state) {
      this.contactFilter = state;
   }
}
