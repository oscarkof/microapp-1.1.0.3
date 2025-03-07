package com.fiduciaria.microapp.page.stateless.dane;

import com.fiduciaria.microapp.componentes.StatelessAjaxFormComponentUpdatingBehavior;
import com.fiduciaria.microapp.componentes.StatelessAjaxSubmitLink;
import com.fiduciaria.microapp.page.statefull.dane.ComplementoPojo;
import com.fiduciaria.microapp.page.statefull.dane.CuadrantePojo;
import com.fiduciaria.microapp.page.statefull.dane.DireccionDanePojo;
import com.fiduciaria.microapp.page.statefull.dane.SufijoPojo;
import com.fiduciaria.microapp.page.statefull.dane.TipoDireccionPojo;
import com.fiduciaria.microapp.page.stateless.StatelessBasePanel;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.basic.EnclosureContainer;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.StringValidator;

public abstract class StatelessDireccionDane extends StatelessBasePanel {
  private final DireccionDanePojo direccionEstandar;
  
  public StatelessDireccionDane(String id, IModel<DireccionDanePojo> model) {
    super(id, model);
    this.direccionEstandar = (DireccionDanePojo)model.getObject();
    if (Objects.isNull(this.direccionEstandar.getSelectTipo()))
      this.direccionEstandar.setSelectTipo(new TipoDireccionPojo("0", "Direccion Estandar")); 
    initDireccionEstandar();
  }
  
  protected void onInitialize() {
    super.onInitialize();
    StatelessForm direccionDaneForm = new StatelessForm("direccionDaneForm");
    queue(new Component[] { (Component)direccionDaneForm });
    TextField direccionnoEstandar = new TextField("direccionnoEstandar", LambdaModel.of(this.direccionEstandar::getDireccionFinal));
    direccionnoEstandar.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)direccionnoEstandar });
    direccionnoEstandar.setEnabled(false);
    queue(new Component[] { (Component)new AjaxLink("cerrar") {
            public void onClick(AjaxRequestTarget target) {
              StatelessDireccionDane.this.cerrarCallback(target);
            }
            
            protected boolean getStatelessHint() {
              return true;
            }
          } });
    queue(new Component[] { (Component)new AjaxLink("cancelarBtn") {
            public void onClick(AjaxRequestTarget target) {
              StatelessDireccionDane.this.cerrarCallback(target);
            }
            
            protected boolean getStatelessHint() {
              return true;
            }
          } });
    queue(new Component[] { (Component)new StatelessAjaxSubmitLink("aceptar", (Form)direccionDaneForm) {
            protected void onSubmit(AjaxRequestTarget target) {
              super.onSubmit(target);
              StatelessDireccionDane.this.aceptarCallback(target);
            }
          } });
    ChoiceRenderer renderedStandar = new ChoiceRenderer("descripcion", "codigo");
    DropDownChoice tipovia = new DropDownChoice("tipovia", LambdaModel.of(this.direccionEstandar::getTipoVia, this.direccionEstandar::setTipoVia), DireccionDanePojo.getTipoVias(), (IChoiceRenderer)renderedStandar) {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(StatelessDireccionDane.this.direccionEstandar.getSelectTipo().getCodigo().equalsIgnoreCase("0"));
        }
      };
    tipovia.setOutputMarkupPlaceholderTag(true);
    tipovia.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    queue(new Component[] { (Component)tipovia });
    final DropDownChoice selectTipo = new DropDownChoice("selectTipo", LambdaModel.of(this.direccionEstandar::getSelectTipo, this.direccionEstandar::setSelectTipo), DireccionDanePojo.getTiposDirecciones(), (IChoiceRenderer)renderedStandar);
    selectTipo.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
            
            protected void onError(AjaxRequestTarget target, RuntimeException e) {
              super.onError(target, e);
              target.add(new Component[] { (Component)selectTipo });
            }
          } });
    queue(new Component[] { (Component)selectTipo });
    selectTipo.setOutputMarkupPlaceholderTag(true);
    EnclosureContainer seccionDireccionEstandar = new EnclosureContainer("seccionDireccionEstandar", (Component)tipovia);
    queue(new Component[] { (Component)seccionDireccionEstandar });
    TextField numeroViaPrincipal = new TextField("numeroViaPrincipal", LambdaModel.of(this.direccionEstandar::getNumeroViaPrincipal, this.direccionEstandar::setNumeroViaPrincipal));
    queue(new Component[] { (Component)numeroViaPrincipal });
    numeroViaPrincipal.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    numeroViaPrincipal.add((IValidator)StringValidator.maximumLength(4));
    DropDownChoice letraViaPrincipal = new DropDownChoice("letraViaPrincipal", LambdaModel.of(this.direccionEstandar::getLetraViaPrincipal, this.direccionEstandar::setLetraViaPrincipal), DireccionDanePojo.getLetras(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)letraViaPrincipal });
    letraViaPrincipal.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    DropDownChoice bisViaPrincipal = new DropDownChoice("bisViaPrincipal", LambdaModel.of(this.direccionEstandar::getBisViaPrincipal, this.direccionEstandar::setBisViaPrincipal), DireccionDanePojo.getSufijos(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)bisViaPrincipal });
    bisViaPrincipal.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    DropDownChoice letraSufijoPrincipal = new DropDownChoice("letraSufijoPrincipal", LambdaModel.of(this.direccionEstandar::getLetraSufijoPrincipal, this.direccionEstandar::setLetraSufijoPrincipal), DireccionDanePojo.getLetras(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)letraSufijoPrincipal });
    letraSufijoPrincipal.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    DropDownChoice cuadranteViaPrincipal = new DropDownChoice("cuadranteViaPrincipal", LambdaModel.of(this.direccionEstandar::getCuadranteViaPrincipal, this.direccionEstandar::setCuadranteViaPrincipal), DireccionDanePojo.getCuadrantes(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)cuadranteViaPrincipal });
    cuadranteViaPrincipal.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    TextField numeroViaGeneradora = new TextField("numeroViaGeneradora", LambdaModel.of(this.direccionEstandar::getNumeroViaGeneradora, this.direccionEstandar::setNumeroViaGeneradora));
    queue(new Component[] { (Component)numeroViaGeneradora });
    numeroViaGeneradora.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    numeroViaGeneradora.add((IValidator)StringValidator.maximumLength(4));
    DropDownChoice letraViaGeneradora = new DropDownChoice("letraViaGeneradora", LambdaModel.of(this.direccionEstandar::getLetraViaGeneradora, this.direccionEstandar::setLetraViaGeneradora), DireccionDanePojo.getLetras(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)letraViaGeneradora });
    letraViaGeneradora.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    DropDownChoice bisViaGeneradora = new DropDownChoice("bisViaGeneradora", LambdaModel.of(this.direccionEstandar::getBisViaGeneradora, this.direccionEstandar::setBisViaGeneradora), DireccionDanePojo.getSufijos(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)bisViaGeneradora });
    bisViaGeneradora.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    DropDownChoice letraSufijoGeneradora = new DropDownChoice("letraSufijoGeneradora", LambdaModel.of(this.direccionEstandar::getLetraSufijoGeneradora, this.direccionEstandar::setLetraSufijoGeneradora), DireccionDanePojo.getLetras(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)letraSufijoGeneradora });
    letraSufijoGeneradora.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    TextField placaComplemento = new TextField("placaComplemento", LambdaModel.of(this.direccionEstandar::getPlacaComplemento, this.direccionEstandar::setPlacaComplemento));
    queue(new Component[] { (Component)placaComplemento });
    placaComplemento.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    placaComplemento.add((IValidator)StringValidator.maximumLength(4));
    DropDownChoice cuadranteViaGeneradora = new DropDownChoice("cuadranteViaGeneradora", LambdaModel.of(this.direccionEstandar::getCuadranteViaGeneradora, this.direccionEstandar::setCuadranteViaGeneradora), DireccionDanePojo.getCuadrantes(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)cuadranteViaGeneradora });
    cuadranteViaGeneradora.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    DropDownChoice complemento = new DropDownChoice("complemento", LambdaModel.of(this.direccionEstandar::getComplemento, this.direccionEstandar::setComplemento), DireccionDanePojo.getComplementos(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)complemento });
    complemento.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    TextField numeroComplemento = new TextField("numeroComplemento", LambdaModel.of(this.direccionEstandar::getNumeroComplemento, this.direccionEstandar::setNumeroComplemento));
    queue(new Component[] { (Component)numeroComplemento });
    numeroComplemento.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    DropDownChoice tiponoestandar = new DropDownChoice("tiponoestandar", LambdaModel.of(this.direccionEstandar::getTiponoestandar, this.direccionEstandar::setTiponoestandar), DireccionDanePojo.getComplementos(), (IChoiceRenderer)renderedStandar) {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(StatelessDireccionDane.this.direccionEstandar.getSelectTipo().getCodigo().equalsIgnoreCase("1"));
        }
      };
    queue(new Component[] { (Component)tiponoestandar });
    tiponoestandar.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
    EnclosureContainer seccionDireccionNoEstandar = new EnclosureContainer("seccionDireccionNoEstandar", (Component)tiponoestandar);
    queue(new Component[] { (Component)seccionDireccionNoEstandar });
    TextField nombretiponoestandar = new TextField("nombretiponoestandar", LambdaModel.of(this.direccionEstandar::getNombretiponoestandar, this.direccionEstandar::setNombretiponoestandar));
    queue(new Component[] { (Component)nombretiponoestandar });
    nombretiponoestandar.add(new Behavior[] { (Behavior)new StatelessAjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              StatelessDireccionDane.this.hookSyncModel(target);
            }
          } });
  }
  
  private void initDireccionEstandar() {
    if (Objects.isNull(this.direccionEstandar.getBisViaGeneradora()))
      this.direccionEstandar.setBisViaGeneradora(new SufijoPojo("", "")); 
    if (Objects.isNull(this.direccionEstandar.getBisViaPrincipal()))
      this.direccionEstandar.setBisViaPrincipal(new SufijoPojo("", "")); 
    if (Objects.isNull(this.direccionEstandar.getComplemento()))
      this.direccionEstandar.setComplemento(new ComplementoPojo("", "")); 
    if (Objects.isNull(this.direccionEstandar.getCuadranteViaGeneradora()))
      this.direccionEstandar.setCuadranteViaGeneradora(new CuadrantePojo("", "")); 
  }
  
  protected boolean getStatelessHint() {
    return true;
  }
  
  public abstract void cerrarCallback(AjaxRequestTarget paramAjaxRequestTarget);
  
  public abstract void aceptarCallback(AjaxRequestTarget paramAjaxRequestTarget);
  
  public abstract void hookSyncModel(AjaxRequestTarget paramAjaxRequestTarget);
}
