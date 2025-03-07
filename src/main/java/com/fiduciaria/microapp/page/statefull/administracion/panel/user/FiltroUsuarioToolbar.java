package com.fiduciaria.microapp.page.statefull.administracion.panel.user;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.decorator.behavior.FieldDecorator;
import com.fiduciaria.microapp.store.model.usuario.UsuarioColumnEnum;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojoVM;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.string.Strings;

public class FiltroUsuarioToolbar extends BasePanel {
  final IModel<UsuarioPojoVM> viewModel;
  
  private UsuarioColumnEnum campoFiltro;
  
  private String valorFiltro;
  
  public FiltroUsuarioToolbar(String id, IModel<UsuarioPojoVM> viewModel) {
    super(id);
    this.viewModel = viewModel;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    LoadableDetachableModel<List<UsuarioColumnEnum>> listaTiposSol = new LoadableDetachableModel<List<UsuarioColumnEnum>>() {
        protected List<UsuarioColumnEnum> load() {
          List<UsuarioColumnEnum> response = new ArrayList<>();
          response.add(UsuarioColumnEnum.LOGIN_USUARIO);
          response.add(UsuarioColumnEnum.IDENTIFICACION);
          response.add(UsuarioColumnEnum.DESCRIPCION_USUARIO);
          return response;
        }
      };
    Form filtroForm = new Form("filtro");
    queue(new Component[] { (Component)filtroForm });
    TextField valorColumnaFiltro = new TextField("valorColumnaFiltro", LambdaModel.of(this::getValorFiltro, this::setValorFiltro));
    queue(new Component[] { (Component)valorColumnaFiltro });
    valorColumnaFiltro.add(new Behavior[] { (Behavior)new FieldDecorator() });
    valorColumnaFiltro.setOutputMarkupPlaceholderTag(true);
    valorColumnaFiltro.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
          } });
    ChoiceRenderer renderer = new ChoiceRenderer("titulo", "nombre");
    DropDownChoice selectTipoSolicitud = new DropDownChoice("selectTipoSolicitud", LambdaModel.of(this::getCampoFiltro, this::setCampoFiltro), (IModel)listaTiposSol, (IChoiceRenderer)renderer);
    queue(new Component[] { (Component)selectTipoSolicitud });
    selectTipoSolicitud.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {}
          } });
    AjaxSubmitLink fitrarUsuarios = new AjaxSubmitLink("fitrarUsuarios", filtroForm) {
        protected void onSubmit(AjaxRequestTarget target) {
          super.onSubmit(target);
          if (Objects.isNull(((UsuarioPojoVM)FiltroUsuarioToolbar.this.viewModel.getObject()).getParametros().getCondiciones()))
            ((UsuarioPojoVM)FiltroUsuarioToolbar.this.viewModel.getObject()).getParametros().setCondiciones(new HashMap<>()); 
          ((UsuarioPojoVM)FiltroUsuarioToolbar.this.viewModel.getObject()).getParametros().getCondiciones().clear();
          if (Objects.nonNull(FiltroUsuarioToolbar.this.getCampoFiltro()) && 
            Objects.nonNull(FiltroUsuarioToolbar.this.getValorFiltro()) && 
            !Strings.isEmpty(FiltroUsuarioToolbar.this.getValorFiltro()))
            ((UsuarioPojoVM)FiltroUsuarioToolbar.this.viewModel.getObject()).getParametros().getCondiciones().put(FiltroUsuarioToolbar.this.getCampoFiltro(), FiltroUsuarioToolbar.this.getValorFiltro()); 
          FiltroUsuarioToolbar.this.registrarComportamiento(UpdTablaUsuariosExternosBehavior.class);
        }
      };
    queue(new Component[] { (Component)fitrarUsuarios });
  }
  
  public void onEventoActualizar(AjaxRequestTarget target) {}
  
  public UsuarioColumnEnum getCampoFiltro() {
    return this.campoFiltro;
  }
  
  public void setCampoFiltro(UsuarioColumnEnum campoFiltro) {
    this.campoFiltro = campoFiltro;
  }
  
  public String getValorFiltro() {
    return this.valorFiltro;
  }
  
  public void setValorFiltro(String valorFiltro) {
    this.valorFiltro = valorFiltro;
  }
}
