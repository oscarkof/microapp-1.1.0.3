package com.fiduciaria.microapp.page.statefull.dane;

import com.fiduciaria.microapp.base.BasePanel;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.basic.EnclosureContainer;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.StringValidator;

public abstract class DireccionDane extends BasePanel {
  private final DireccionDanePojo direccionEstandar;
  
  private final class UpdateTipoDireccion extends Behavior {
    private UpdateTipoDireccion() {}
  }
  
  private final class UpdateDireccionBehavior extends Behavior {
    private UpdateDireccionBehavior() {}
  }
  
  public DireccionDane(String id, IModel<DireccionDanePojo> model) {
    super(id, model);
    this.direccionEstandar = (DireccionDanePojo)model.getObject();
    this.direccionEstandar.setSelectTipo(new TipoDireccionPojo("0", "Direccion Estandar"));
    initDireccionEstandar();
  }
  
  protected void onInitialize() {
    super.onInitialize();
    Form direccionDaneForm = new Form("direccionDaneForm");
    queue(new Component[] { (Component)direccionDaneForm });
    TextField direccionnoEstandar = new TextField("direccionnoEstandar", LambdaModel.of(this.direccionEstandar::getDireccionFinal));
    direccionnoEstandar.setOutputMarkupPlaceholderTag(true);
    direccionnoEstandar.add(new Behavior[] { new UpdateDireccionBehavior() });
    queue(new Component[] { (Component)direccionnoEstandar });
    direccionnoEstandar.setEnabled(false);
    queue(new Component[] { (Component)new AjaxLink("cerrar") {
            public void onClick(AjaxRequestTarget target) {
              DireccionDane.this.cerrarCallback(target);
            }
          } });
    queue(new Component[] { (Component)new AjaxLink("cancelarBtn") {
            public void onClick(AjaxRequestTarget target) {
              DireccionDane.this.cerrarCallback(target);
            }
          } });
    queue(new Component[] { (Component)new AjaxSubmitLink("aceptar", direccionDaneForm) {
            protected void onSubmit(AjaxRequestTarget target) {
              super.onSubmit(target);
              DireccionDane.this.aceptarCallback(target);
            }
          } });
    direccionDaneForm.add(new Behavior[] { new UpdateTipoDireccion() });
    ChoiceRenderer renderedStandar = new ChoiceRenderer("descripcion", "codigo");
    DropDownChoice tipovia = new DropDownChoice("tipovia", LambdaModel.of(this.direccionEstandar::getTipoVia, this.direccionEstandar::setTipoVia), DireccionDanePojo.getTipoVias(), (IChoiceRenderer)renderedStandar) {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(DireccionDane.this.direccionEstandar.getSelectTipo().getCodigo().equalsIgnoreCase("0"));
        }
      };
    tipovia.setOutputMarkupPlaceholderTag(true);
    tipovia.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    tipovia.add(new Behavior[] { new UpdateTipoDireccion() });
    queue(new Component[] { (Component)tipovia });
    DropDownChoice selectTipo = new DropDownChoice("selectTipo", LambdaModel.of(this.direccionEstandar::getSelectTipo, this.direccionEstandar::setSelectTipo), DireccionDanePojo.getTiposDirecciones(), (IChoiceRenderer)renderedStandar);
    selectTipo.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateTipoDireccion.class);
            }
          } });
    queue(new Component[] { (Component)selectTipo });
    selectTipo.setOutputMarkupPlaceholderTag(true);
    EnclosureContainer seccionDireccionEstandar = new EnclosureContainer("seccionDireccionEstandar", (Component)tipovia);
    queue(new Component[] { (Component)seccionDireccionEstandar });
    TextField numeroViaPrincipal = new TextField("numeroViaPrincipal", LambdaModel.of(this.direccionEstandar::getNumeroViaPrincipal, this.direccionEstandar::setNumeroViaPrincipal));
    queue(new Component[] { (Component)numeroViaPrincipal });
    numeroViaPrincipal.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    numeroViaPrincipal.add((IValidator)StringValidator.maximumLength(4));
    DropDownChoice letraViaPrincipal = new DropDownChoice("letraViaPrincipal", LambdaModel.of(this.direccionEstandar::getLetraViaPrincipal, this.direccionEstandar::setLetraViaPrincipal), DireccionDanePojo.getLetras(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)letraViaPrincipal });
    letraViaPrincipal.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    DropDownChoice bisViaPrincipal = new DropDownChoice("bisViaPrincipal", LambdaModel.of(this.direccionEstandar::getBisViaPrincipal, this.direccionEstandar::setBisViaPrincipal), DireccionDanePojo.getSufijos(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)bisViaPrincipal });
    bisViaPrincipal.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    DropDownChoice letraSufijoPrincipal = new DropDownChoice("letraSufijoPrincipal", LambdaModel.of(this.direccionEstandar::getLetraSufijoPrincipal, this.direccionEstandar::setLetraSufijoPrincipal), DireccionDanePojo.getLetras(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)letraSufijoPrincipal });
    letraSufijoPrincipal.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    DropDownChoice cuadranteViaPrincipal = new DropDownChoice("cuadranteViaPrincipal", LambdaModel.of(this.direccionEstandar::getCuadranteViaPrincipal, this.direccionEstandar::setCuadranteViaPrincipal), DireccionDanePojo.getCuadrantes(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)cuadranteViaPrincipal });
    cuadranteViaPrincipal.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    TextField numeroViaGeneradora = new TextField("numeroViaGeneradora", LambdaModel.of(this.direccionEstandar::getNumeroViaGeneradora, this.direccionEstandar::setNumeroViaGeneradora));
    queue(new Component[] { (Component)numeroViaGeneradora });
    numeroViaGeneradora.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    numeroViaGeneradora.add((IValidator)StringValidator.maximumLength(4));
    DropDownChoice letraViaGeneradora = new DropDownChoice("letraViaGeneradora", LambdaModel.of(this.direccionEstandar::getLetraViaGeneradora, this.direccionEstandar::setLetraViaGeneradora), DireccionDanePojo.getLetras(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)letraViaGeneradora });
    letraViaGeneradora.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    DropDownChoice bisViaGeneradora = new DropDownChoice("bisViaGeneradora", LambdaModel.of(this.direccionEstandar::getBisViaGeneradora, this.direccionEstandar::setBisViaGeneradora), DireccionDanePojo.getSufijos(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)bisViaGeneradora });
    bisViaGeneradora.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    DropDownChoice letraSufijoGeneradora = new DropDownChoice("letraSufijoGeneradora", LambdaModel.of(this.direccionEstandar::getLetraSufijoGeneradora, this.direccionEstandar::setLetraSufijoGeneradora), DireccionDanePojo.getLetras(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)letraSufijoGeneradora });
    letraSufijoGeneradora.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    TextField placaComplemento = new TextField("placaComplemento", LambdaModel.of(this.direccionEstandar::getPlacaComplemento, this.direccionEstandar::setPlacaComplemento));
    queue(new Component[] { (Component)placaComplemento });
    placaComplemento.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    placaComplemento.add((IValidator)StringValidator.maximumLength(4));
    DropDownChoice cuadranteViaGeneradora = new DropDownChoice("cuadranteViaGeneradora", LambdaModel.of(this.direccionEstandar::getCuadranteViaGeneradora, this.direccionEstandar::setCuadranteViaGeneradora), DireccionDanePojo.getCuadrantes(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)cuadranteViaGeneradora });
    cuadranteViaGeneradora.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    DropDownChoice complemento = new DropDownChoice("complemento", LambdaModel.of(this.direccionEstandar::getComplemento, this.direccionEstandar::setComplemento), DireccionDanePojo.getComplementos(), (IChoiceRenderer)renderedStandar);
    queue(new Component[] { (Component)complemento });
    complemento.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    TextField numeroComplemento = new TextField("numeroComplemento", LambdaModel.of(this.direccionEstandar::getNumeroComplemento, this.direccionEstandar::setNumeroComplemento));
    queue(new Component[] { (Component)numeroComplemento });
    numeroComplemento.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    DropDownChoice tiponoestandar = new DropDownChoice("tiponoestandar", LambdaModel.of(this.direccionEstandar::getTiponoestandar, this.direccionEstandar::setTiponoestandar), DireccionDanePojo.getComplementos(), (IChoiceRenderer)renderedStandar) {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(DireccionDane.this.direccionEstandar.getSelectTipo().getCodigo().equalsIgnoreCase("1"));
        }
      };
    queue(new Component[] { (Component)tiponoestandar });
    tiponoestandar.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
    EnclosureContainer seccionDireccionNoEstandar = new EnclosureContainer("seccionDireccionNoEstandar", (Component)tiponoestandar);
    queue(new Component[] { (Component)seccionDireccionNoEstandar });
    TextField nombretiponoestandar = new TextField("nombretiponoestandar", LambdaModel.of(this.direccionEstandar::getNombretiponoestandar, this.direccionEstandar::setNombretiponoestandar));
    queue(new Component[] { (Component)nombretiponoestandar });
    nombretiponoestandar.add(new Behavior[] { (Behavior)new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
              DireccionDane.this.registrarComportamiento(DireccionDane.UpdateDireccionBehavior.class);
            }
          } });
  }
  
  private void initDireccionEstandar() {
    this.direccionEstandar.setBisViaGeneradora(new SufijoPojo("", ""));
    this.direccionEstandar.setBisViaPrincipal(new SufijoPojo("", ""));
    this.direccionEstandar.setComplemento(new ComplementoPojo("", ""));
    this.direccionEstandar.setCuadranteViaGeneradora(new CuadrantePojo("", ""));
  }
  
  public abstract void cerrarCallback(AjaxRequestTarget paramAjaxRequestTarget);
  
  public abstract void aceptarCallback(AjaxRequestTarget paramAjaxRequestTarget);
}
