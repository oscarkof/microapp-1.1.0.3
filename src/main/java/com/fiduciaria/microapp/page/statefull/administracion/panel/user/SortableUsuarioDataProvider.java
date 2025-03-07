// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.administracion.panel.user;

import com.fiduciaria.microapp.store.model.usuario.UsuarioColumnEnum;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojo;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojoVM;
import java.util.Iterator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class SortableUsuarioDataProvider extends SortableDataProvider<UsuarioPojo, String> implements IFilterStateLocator<UsuarioFilter> {
   private UsuarioFilter filtro;
   private final UsuarioPojoVM usrViewModel;

   public SortableUsuarioDataProvider(UsuarioPojoVM usrViewModel) {
      this.usrViewModel = usrViewModel;
      this.filtro = new UsuarioFilter();
   }

   public Iterator<? extends UsuarioPojo> iterator(long primero, long cuenta) {
      this.initStrictFilterValues();
      this.usrViewModel.getParametros().setInicio(primero);
      this.usrViewModel.getParametros().setCuenta(cuenta);
      this.usrViewModel.getParametros().setSoloTotales(false);
      return this.usrViewModel.recuperarListaUsuarios().iterator();
   }

   public long size() {
      this.initStrictFilterValues();
      this.usrViewModel.getParametros().setSoloTotales(true);
      return this.usrViewModel.totalRegistros();
   }

   public IModel<UsuarioPojo> model(UsuarioPojo object) {
      return new DetachableUsuarioModel(object);
   }

   public UsuarioFilter getFilterState() {
      return this.filtro;
   }

   public void setFilterState(UsuarioFilter state) {
      this.filtro = state;
   }

   private void initStrictFilterValues() {
      this.usrViewModel.getParametros().getCondiciones().put(UsuarioColumnEnum.TIPO_USUARIO, "EXT");
      this.usrViewModel.getParametros().getCondiciones().put(UsuarioColumnEnum.ESTADO_USUARIO, "A");
   }
}
