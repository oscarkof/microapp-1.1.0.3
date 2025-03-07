// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.actividades.componente;

import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudFilter;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojoVM;
import java.util.Iterator;
import java.util.List;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class SortableSolicitudDataProvider extends SortableDataProvider<SolicitudPojo, String> implements IFilterStateLocator<SolicitudFilter> {
   private SolicitudFilter solicitudFilter = new SolicitudFilter();
   private final SolicitudPojoVM viewModelSol;

   public SortableSolicitudDataProvider(SolicitudPojoVM modelo) {
      this.viewModelSol = modelo;
      this.setSort("firstName", SortOrder.ASCENDING);
   }

   public Iterator<SolicitudPojo> iterator(long inicio, long cantidad) {
      this.viewModelSol.getParametros().setInicio(inicio);
      this.viewModelSol.getParametros().setTamanioPagina(cantidad);
      List<SolicitudPojo> solicitudesEncontradas = this.viewModelSol.listSolicitud();
      return solicitudesEncontradas.iterator();
   }

   public long size() {
      return (long)this.viewModelSol.totalSolicitudes();
   }

   public IModel<SolicitudPojo> model(SolicitudPojo object) {
      return new DetachableSolicitudModel(object);
   }

   public SolicitudFilter getFilterState() {
      return this.solicitudFilter;
   }

   public void setFilterState(SolicitudFilter state) {
      this.solicitudFilter = state;
   }
}
