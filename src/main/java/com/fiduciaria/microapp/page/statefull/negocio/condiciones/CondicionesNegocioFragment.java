package com.fiduciaria.microapp.page.statefull.negocio.condiciones;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.componentes.MaskInputFormatAppender;
import com.fiduciaria.microapp.componentes.TxtFieldFactory;
import com.fiduciaria.microapp.decorator.behavior.FieldDecorator;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.tarea.panel.modales.ModalUsuarios;
import com.fiduciaria.microapp.store.model.escritura.ConceptoUsuarioTipo;
import com.fiduciaria.microapp.store.model.escritura.CondicionesManejoPojo;
import com.fiduciaria.microapp.store.model.escritura.EscrituraCondicionTipo;
import com.fiduciaria.microapp.store.model.escritura.EscrituraFirmasCondiciones;
import com.fiduciaria.microapp.store.model.escritura.EscrituraUsuarioTipo;
import com.fiduciaria.microapp.store.seguridad.PrincipalPojo;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.RangeValidator;
import org.apache.wicket.validation.validator.StringValidator;

public abstract class CondicionesNegocioFragment extends Fragment {

    private final CondicionesManejoPojo condicionesManejo;

    private final EscrituraUsuarioTipo usuarioTipoSelec;

    private final EscrituraFirmasCondiciones condicionFirmasSel;

    private final BasePanel panelParent;

    private final TmpCondicionesRelacionadasFrm tmpCondicionesRelacionadas = new TmpCondicionesRelacionadasFrm();

    private final TmpCondicionesRelacionadasFrm tmpCondicionesRelacionadasFijoVariable = new TmpCondicionesRelacionadasFrm();

    public CondicionesNegocioFragment(String id, String markupId, MarkupContainer markupProvider, IModel<CondicionesManejoPojo> model, BasePanel panelParent) {
        super(id, markupId, markupProvider, model);
        this.condicionesManejo = (CondicionesManejoPojo) model.getObject();
        this.usuarioTipoSelec = new EscrituraUsuarioTipo();
        this.condicionFirmasSel = new EscrituraFirmasCondiciones();
        setParamIniciales();
        this.panelParent = panelParent;
        this.condicionFirmasSel.setModo("EXT");
    }

    private void setParamIniciales() {
        this.condicionesManejo.setEscrituraUsuarioTipo(new HashMap<>());
        this.condicionesManejo.setEscrituraFirmasCondiciones(new HashMap<>());
    }

    protected void onInitialize() {
        super.onInitialize();
        Form formCondicionesNegocio = new Form("formCondicionesNegocio") {
            public void onEvent(IEvent event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }

            protected void onConfigure() {
                super.onConfigure();
                setVisible(CondicionesNegocioFragment.this.enableformCondicionesNegocio());
            }
        };
        queue(new Component[]{(Component) formCondicionesNegocio});
        formCondicionesNegocio.setOutputMarkupPlaceholderTag(true);
        initGenerales(formCondicionesNegocio);
        initUsuarioTipo(formCondicionesNegocio);
        initCondiciones(formCondicionesNegocio);
        iniCerrarFragment();
        WebMarkupContainer seccionDataGlobal = new WebMarkupContainer("seccionDataGlobal") {
            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }
        };
        seccionDataGlobal.setOutputMarkupPlaceholderTag(true);
        queue(new Component[]{(Component) seccionDataGlobal});
        Label numeroEscrituraGeneral = new Label("numeroEscrituraGeneral", LambdaModel.of(this.condicionesManejo::getNumeroEscritura));
        queue(new Component[]{(Component) numeroEscrituraGeneral});
        Label numeroFirmasGeneral = new Label("numeroFirmasGeneral", LambdaModel.of(this.condicionesManejo::getNumeroFirmas));
        queue(new Component[]{(Component) numeroFirmasGeneral});
        initViewEscrituraUsuarioTipo();
        initVistaEscrituraFirmaCondiciones();
        initViewEscrituraCondicionTipo();
        initViewConceptoUsuarioTipo();
        initFormularioEscrituraCondicionTipo();
        initFormularioConceptoUsuarioTipo();
    }

    private void initGenerales(Form formCondicionesNegocio) {
        WebMarkupContainer generales = new WebMarkupContainer("generales") {
            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }

            protected void onConfigure() {
                super.onConfigure();
                setVisible(CondicionesNegocioFragment.this.enableGenerales());
            }
        };
        queue(new Component[]{(Component) generales});
        generales.setOutputMarkupId(true);
        TextField numeroFirmas = TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("numeroFirmas", true, true, "Ntotal de firmas requeridas",
                LambdaModel.of(this.condicionesManejo::getNumeroFirmas, this.condicionesManejo::setNumeroFirmas), (IModel) new ResourceModel("condiciones.negocio.fragment.form.generales.campo.numerofirmas.label", ""), Integer.class);
        numeroFirmas.add(new Behavior[]{(Behavior) new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
            }
        }});
        numeroFirmas.add((IValidator) RangeValidator.maximum(Integer.valueOf(9999)));
        numeroFirmas.add(new Behavior[]{(Behavior) MaskInputFormatAppender.general("[4]", null, "", false, false, true)});
        generales.queue(new Component[]{(Component) numeroFirmas});
        TextField numeroEscritura = TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("numeroEscritura", true, true, "Nde escritura interno del negocio",
                LambdaModel.of(this.condicionesManejo::getNumeroEscritura, this.condicionesManejo::setNumeroEscritura), (IModel) new ResourceModel("condiciones.negocio.fragment.form.generales.campo.numeroescritura.label", ""), String.class);
        generales.queue(new Component[]{(Component) numeroEscritura});
        numeroEscritura.add(new Behavior[]{(Behavior) new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
                CondicionesNegocioFragment.this.sendEvent(target);
            }
        }});
        numeroEscritura.add((IValidator) StringValidator.maximumLength(10));
        numeroEscritura.add(new Behavior[]{(Behavior) MaskInputFormatAppender.general("[10]", null, "", false, false, true)});
    }

    private void sendEvent(AjaxRequestTarget target) {
        send((IEventSink) this, Broadcast.BREADTH, new EventUpdateMessag(target));
    }

    private void initUsuarioTipo(Form formCondicionesNegocio) {
        WebMarkupContainer usuarioTipo = new WebMarkupContainer("usuarioTipo") {
            protected void onConfigure() {
                super.onConfigure();
                setVisible(CondicionesNegocioFragment.this.enableUsuarioTipo());
            }

            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }
        };
        queue(new Component[]{(Component) usuarioTipo});
        usuarioTipo.setOutputMarkupPlaceholderTag(true);
        final TextField loginUsuario = new TextField("loginUsuario", LambdaModel.of(this.usuarioTipoSelec::getLoginUsuario, this.usuarioTipoSelec::setLoginUsuario), String.class);
        loginUsuario.setOutputMarkupPlaceholderTag(true);
        loginUsuario.add(new Behavior[]{(Behavior) new FieldDecorator()});
        usuarioTipo.queue(new Component[]{(Component) loginUsuario});
        loginUsuario.setRequired(true);
        AjaxLink abrirModalUsuarios = new AjaxLink("abrirModalUsuarios") {
            public void onClick(AjaxRequestTarget target) {
                ModalUsuarios modalUsr = new ModalUsuarios(CondicionesNegocioFragment.this.panelParent.getModalContentId(), "TODOS") {
                    public void cerrar(AjaxRequestTarget target) {
                        CondicionesNegocioFragment.this.panelParent.showModal(false, target);
                        CondicionesNegocioFragment.this.panelParent.removeModal();
                    }

                    public void seleccionarUsuario(AjaxRequestTarget target, PrincipalPojo usuario) {
                        CondicionesNegocioFragment.this.panelParent.showModal(false, target);
                        CondicionesNegocioFragment.this.panelParent.removeModal();
                        CondicionesNegocioFragment.this.usuarioTipoSelec.setLoginUsuario(usuario.getUsuario());
                        target.add(new Component[]{(Component) loginUsuario});
                    }
                };
                CondicionesNegocioFragment.this.panelParent.addModal((Component) modalUsr);
                CondicionesNegocioFragment.this.panelParent.showModal(true, target);
            }
        };
        queue(new Component[]{(Component) abrirModalUsuarios});
        List<String> listaTipoFirma = new ArrayList<>();
        listaTipoFirma.add("A");
        listaTipoFirma.add("B");
        listaTipoFirma.add("C");
        listaTipoFirma.add("D");
        DropDownChoice tipoFirma = crearDropDown("tipoFirma",
                LambdaModel.of(this.usuarioTipoSelec::getTipo, this.usuarioTipoSelec::setTipo), (IModel) new ListModel(listaTipoFirma), " ", "Seleccione el tipo de firma para usuario");
        tipoFirma.setLabel((IModel) new ResourceModel("condiciones.negocio.fragment.form.usuariotipo.campo.tipofirma.label", ""));
        tipoFirma.setRequired(true);
        usuarioTipo.queue(new Component[]{(Component) tipoFirma});
        usuarioTipo.queue(new Component[]{(Component) new AjaxLink("agregarUsuarioTipo") {
            public void onClick(AjaxRequestTarget target) {
                if (!Strings.isEmpty(CondicionesNegocioFragment.this.usuarioTipoSelec.getLoginUsuario())
                        && !Strings.isEmpty(CondicionesNegocioFragment.this.usuarioTipoSelec.getTipo())) {
                    CondicionesNegocioFragment.this.condicionesManejo.getEscrituraUsuarioTipo().put(CondicionesNegocioFragment.this.usuarioTipoSelec.getLoginUsuario(), new EscrituraUsuarioTipo());
                    ((EscrituraUsuarioTipo) CondicionesNegocioFragment.this.condicionesManejo.getEscrituraUsuarioTipo().get(CondicionesNegocioFragment.this.usuarioTipoSelec.getLoginUsuario())).setLoginUsuario(CondicionesNegocioFragment.this.usuarioTipoSelec.getLoginUsuario());
                    ((EscrituraUsuarioTipo) CondicionesNegocioFragment.this.condicionesManejo.getEscrituraUsuarioTipo().get(CondicionesNegocioFragment.this.usuarioTipoSelec.getLoginUsuario())).setTipo(CondicionesNegocioFragment.this.usuarioTipoSelec.getTipo());
                    CondicionesNegocioFragment.this.usuarioTipoSelec.setLoginUsuario(null);
                    CondicionesNegocioFragment.this.usuarioTipoSelec.setTipo(null);
                }
                CondicionesNegocioFragment.this.sendEvent(target);
            }
        }});
    }

    private void initCondiciones(final Form formCondicionesNegocio) {
        WebMarkupContainer condiciones = new WebMarkupContainer("condiciones") {
            protected void onConfigure() {
                super.onConfigure();
                setVisible(CondicionesNegocioFragment.this.enableCondiciones());
            }

            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }
        };
        condiciones.setOutputMarkupPlaceholderTag(true);
        queue(new Component[]{(Component) condiciones});
        List<String> listaTipoCondicion = new ArrayList<>();
        listaTipoCondicion.add("G");
        listaTipoCondicion.add("M");
        DropDownChoice codTipoCondicion = crearDropDown("codTipoCondicion",
                LambdaModel.of(this.condicionFirmasSel::getCodTipoCondicion, this.condicionFirmasSel::setCodTipoCondicion), (IModel) new ListModel(listaTipoCondicion), "Tipo Condición", "Seleccione el tipo de condiciM = MONTO o G = GENERAL");
        condiciones.queue(new Component[]{(Component) codTipoCondicion});
        codTipoCondicion.setRequired(true);
        codTipoCondicion.setLabel((IModel) new ResourceModel("condiciones.negocio.fragment.form.condiciones.codtipocondicion.label", ""));
        List<String> listaFijoVariable = new ArrayList<>();
        listaFijoVariable.add("F");
        listaFijoVariable.add("V");
        DropDownChoice fijoVariable = crearDropDown("fijoVariable",
                LambdaModel.of(this.condicionFirmasSel::getFijoVariable, this.condicionFirmasSel::setFijoVariable), (IModel) new ListModel(listaFijoVariable), " ", "Seleccione F = Fijo o V = Variable");
        condiciones.queue(new Component[]{(Component) fijoVariable});
        fijoVariable.setLabel((IModel) new ResourceModel("condiciones.negocio.fragment.form.condiciones.fijovariable.label", ""));
        fijoVariable.setRequired(true);
        List<String> listavariableSN = new ArrayList<>();
        listavariableSN.add("S");
        listavariableSN.add("N");
        DropDownChoice variableSN = crearDropDown("variableSN",
                LambdaModel.of(this.condicionFirmasSel::getVariableSn, this.condicionFirmasSel::setVariableSn), (IModel) new ListModel(listavariableSN), " ", "Seleccione S = SI o N = NO");
        condiciones.queue(new Component[]{(Component) variableSN});
        variableSN.setLabel((IModel) new ResourceModel("condiciones.negocio.fragment.form.condiciones.variableSN.label", ""));
        variableSN.setRequired(true);
        List<String> listaSavedSN = new ArrayList<>();
        listaSavedSN.add("S");
        listaSavedSN.add("N");
        DropDownChoice savedSN = crearDropDown("savedSN",
                LambdaModel.of(this.condicionFirmasSel::getSavedSn, this.condicionFirmasSel::setSavedSn), (IModel) new ListModel(listaSavedSN), " ", "Seleccione S = SI o N = NO");
        condiciones.queue(new Component[]{(Component) savedSN});
        savedSN.setLabel((IModel) new ResourceModel("condiciones.negocio.fragment.form.condiciones.savedsn.label", ""));
        savedSN.setRequired(true);
        condiciones.queue(new Component[]{(Component) TxtFieldFactory.instancia()
            .crearAjaxCampoTextStandarizado("numeroFirmasRequeridas", true, true, "",
            LambdaModel.of(this.condicionFirmasSel::getNumFirmas, this.condicionFirmasSel::setNumFirmas), (IModel) new ResourceModel("condiciones.negocio.fragment.form.condiciones.numerofirmasrequeridas.label", ""), Integer.class)});
        condiciones.queue(new Component[]{(Component) TxtFieldFactory.instancia()
            .crearAjaxCampoTextStandarizado("valorInicialAprobar", true, true, "",
            LambdaModel.of(this.condicionFirmasSel::getValorInicial, this.condicionFirmasSel::setValorInicial), (IModel) new ResourceModel("condiciones.negocio.fragment.form.condiciones.valorInicialAprobar.label", ""), BigDecimal.class)});
        condiciones.queue(new Component[]{(Component) TxtFieldFactory.instancia()
            .crearAjaxCampoTextStandarizado("valorFinalAprobar", true, true, "",
            LambdaModel.of(this.condicionFirmasSel::getValorFinal, this.condicionFirmasSel::setValorFinal), (IModel) new ResourceModel("condiciones.negocio.fragment.form.condiciones.valorFinalAprobar.label", ""), BigDecimal.class)});
        List<String> listaModos = new ArrayList<>();
        listaModos.add("EXT");
        listaModos.add("INT");
        DropDownChoice modo = crearDropDown("modo",
                LambdaModel.of(this.condicionFirmasSel::getModo, this.condicionFirmasSel::setModo), (IModel) new ListModel(listaModos), " ", "Seleccione EXT = Externo o  INT = Interno. <br> Valida el tipo de condicion si es INT =interno o EXT= externo");
        condiciones.queue(new Component[]{(Component) modo});
        modo.setRequired(true);
        modo.setLabel((IModel) new ResourceModel("condiciones.negocio.fragment.form.condiciones.modo.label", ""));
        condiciones.queue(new Component[]{(Component) new AjaxSubmitLink("agregarEscrituraFirmaCondicion", formCondicionesNegocio) {
            public void onSubmit(AjaxRequestTarget target) {
                int consecutivo = CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().size();
                CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().put(Integer.valueOf(consecutivo), new EscrituraFirmasCondiciones());
                ((EscrituraFirmasCondiciones) CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().get(Integer.valueOf(consecutivo)))
                        .setCodTipoCondicion(CondicionesNegocioFragment.this.condicionFirmasSel.getCodTipoCondicion());
                ((EscrituraFirmasCondiciones) CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().get(Integer.valueOf(consecutivo)))
                        .setCurrentSecuencial(Integer.valueOf(consecutivo));
                ((EscrituraFirmasCondiciones) CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().get(Integer.valueOf(consecutivo)))
                        .setFijoVariable(CondicionesNegocioFragment.this.condicionFirmasSel.getFijoVariable());
                ((EscrituraFirmasCondiciones) CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().get(Integer.valueOf(consecutivo)))
                        .setModo(CondicionesNegocioFragment.this.condicionFirmasSel.getModo());
                ((EscrituraFirmasCondiciones) CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().get(Integer.valueOf(consecutivo)))
                        .setNumFirmas(CondicionesNegocioFragment.this.condicionFirmasSel.getNumFirmas());
                ((EscrituraFirmasCondiciones) CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().get(Integer.valueOf(consecutivo)))
                        .setSavedSn(CondicionesNegocioFragment.this.condicionFirmasSel.getSavedSn());
                ((EscrituraFirmasCondiciones) CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().get(Integer.valueOf(consecutivo)))
                        .setSecCondicion(Integer.valueOf(consecutivo));
                ((EscrituraFirmasCondiciones) CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().get(Integer.valueOf(consecutivo)))
                        .setValorFinal(CondicionesNegocioFragment.this.condicionFirmasSel.getValorFinal());
                ((EscrituraFirmasCondiciones) CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().get(Integer.valueOf(consecutivo)))
                        .setValorInicial(CondicionesNegocioFragment.this.condicionFirmasSel.getValorInicial());
                ((EscrituraFirmasCondiciones) CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().get(Integer.valueOf(consecutivo)))
                        .setVariableSn(CondicionesNegocioFragment.this.condicionFirmasSel.getVariableSn());
                CondicionesNegocioFragment.this.condicionFirmasSel.setCodTipoCondicion(null);
                CondicionesNegocioFragment.this.condicionFirmasSel.setCurrentSecuencial(null);
                CondicionesNegocioFragment.this.condicionFirmasSel.setFechaProceso(null);
                CondicionesNegocioFragment.this.condicionFirmasSel.setFijoVariable(null);
                CondicionesNegocioFragment.this.condicionFirmasSel.setNumFirmas(null);
                CondicionesNegocioFragment.this.condicionFirmasSel.setSavedSn(null);
                CondicionesNegocioFragment.this.condicionFirmasSel.setSecCondicion(null);
                CondicionesNegocioFragment.this.condicionFirmasSel.setUsuarioProceso(null);
                CondicionesNegocioFragment.this.condicionFirmasSel.setValorFinal(null);
                CondicionesNegocioFragment.this.condicionFirmasSel.setValorInicial(null);
                CondicionesNegocioFragment.this.condicionFirmasSel.setVariableSn(null);
                CondicionesNegocioFragment.this.sendEvent(target);
            }

            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
                target.add(new Component[]{(Component) formCondicionesNegocio});
            }
        }});
    }

    private DropDownChoice crearDropDown(String id, IModel modelo, IModel modelChoices, String popovrHead, String popovrmsg) {
        final DropDownChoice dropDownInput = new DropDownChoice(id, modelo, modelChoices);
        dropDownInput.add(new Behavior[]{(Behavior) new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(new Component[]{(Component) dropDownInput});
            }

            protected void onError(AjaxRequestTarget target, RuntimeException e) {
                super.onError(target, e);
                target.add(new Component[]{(Component) dropDownInput});
            }
        }});
        dropDownInput.add(new Behavior[]{(Behavior) new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, popovrHead, "", popovrmsg)});
        return dropDownInput;
    }

    private void iniCerrarFragment() {
    }

    private boolean enableGenerales() {
        return (Objects.nonNull(this.condicionesManejo.getEscrituraUsuarioTipo()) && this.condicionesManejo
                .getEscrituraUsuarioTipo().isEmpty());
    }

    private boolean enableUsuarioTipo() {
        return (Objects.nonNull(this.condicionesManejo.getNumeroFirmas()) && this.condicionesManejo
                .getNumeroFirmas().intValue() > 0
                && !Strings.isEmpty(this.condicionesManejo.getNumeroEscritura())
                && Objects.nonNull(this.condicionesManejo.getEscrituraUsuarioTipo()) && this.condicionesManejo
                .getEscrituraUsuarioTipo().size() < this.condicionesManejo.getNumeroFirmas().intValue());
    }

    private boolean enableformCondicionesNegocio() {
        List<Boolean> listaEjecutados = new ArrayList<>();
        if (Objects.nonNull(this.condicionesManejo.getEscrituraFirmasCondiciones())
                && !this.condicionesManejo.getEscrituraFirmasCondiciones().isEmpty()) {
            this.condicionesManejo.getEscrituraFirmasCondiciones().forEach((k, v) -> {
                if (Objects.nonNull(v.getEscrituraCondicionTipo()) && v.getEscrituraCondicionTipo().size() > 0) {
                    listaEjecutados.add(Boolean.valueOf(true));
                }
                if (Objects.nonNull(v.getConceptoUsuarioTipo()) && v.getConceptoUsuarioTipo().size() > 0) {
                    listaEjecutados.add(Boolean.valueOf(true));
                }
            });
        }
        return listaEjecutados.isEmpty();
    }

    private boolean enableCondiciones() {
        return (Objects.nonNull(this.condicionesManejo.getEscrituraUsuarioTipo())
                && !this.condicionesManejo.getEscrituraUsuarioTipo().isEmpty() && this.condicionesManejo
                .getEscrituraUsuarioTipo().size() == this.condicionesManejo.getNumeroFirmas().intValue());
    }

    private boolean enableEscrituraCondicionTipo() {
        List<Boolean> listaCondicionVariable = new ArrayList<>();
        if (Objects.isNull(this.condicionesManejo.getEscrituraFirmasCondiciones())
                || !this.condicionesManejo.getEscrituraFirmasCondiciones().isEmpty()) {
            this.condicionesManejo.getEscrituraFirmasCondiciones().forEach((k, v) -> {
                if (v.getVariableSn().equalsIgnoreCase("s")) {
                    listaCondicionVariable.add(Boolean.valueOf(true));
                }
            });
        }
        return !listaCondicionVariable.isEmpty();
    }

    private boolean enableConceptoUsuarioTipo() {
        List<Boolean> listaCondicionVariable = new ArrayList<>();
        if (Objects.isNull(this.condicionesManejo.getEscrituraFirmasCondiciones())
                || !this.condicionesManejo.getEscrituraFirmasCondiciones().isEmpty()) {
            this.condicionesManejo.getEscrituraFirmasCondiciones().forEach((k, v) -> {
                if (v.getFijoVariable().equalsIgnoreCase("f")) {
                    listaCondicionVariable.add(Boolean.valueOf(true));
                }
            });
        }
        return !listaCondicionVariable.isEmpty();
    }

    private boolean enableFormularioConceptoUsuarioTipo() {
        List<Boolean> listaCondicionVariable = new ArrayList<>();
        if (Objects.isNull(this.condicionesManejo.getEscrituraFirmasCondiciones())
                || !this.condicionesManejo.getEscrituraFirmasCondiciones().isEmpty()) {
            this.condicionesManejo.getEscrituraFirmasCondiciones().forEach((k, v) -> {
                if (v.getFijoVariable().equalsIgnoreCase("f") && this.condicionesManejo.getEscrituraUsuarioTipo().size() > v.getConceptoUsuarioTipo().size()) {
                    listaCondicionVariable.add(Boolean.valueOf(true));
                }
            });
        }
        return !listaCondicionVariable.isEmpty();
    }

    private class EventUpdateMessag {

        private final AjaxRequestTarget target;

        public EventUpdateMessag(AjaxRequestTarget target) {
            this.target = target;
        }

        public AjaxRequestTarget getTarget() {
            return this.target;
        }
    }

    private void initViewEscrituraUsuarioTipo() {
        WebMarkupContainer escrituraUsuarioTipoDataView = new WebMarkupContainer("escrituraUsuarioTipoDataView") {
            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }
        };
        queue(new Component[]{(Component) escrituraUsuarioTipoDataView});
        escrituraUsuarioTipoDataView.setOutputMarkupPlaceholderTag(true);
        RefreshingView<EscrituraUsuarioTipo> listViewEscrituraUsuarioTipo = new RefreshingView<EscrituraUsuarioTipo>("listViewEscrituraUsuarioTipo") {
            protected Iterator<IModel<EscrituraUsuarioTipo>> getItemModels() {
                List<IModel<EscrituraUsuarioTipo>> response = new ArrayList<>();
                CondicionesNegocioFragment.this.condicionesManejo.getEscrituraUsuarioTipo().forEach((k, v) -> response.add(new Model((Serializable) v)));
                return response.iterator();
            }

            protected void populateItem(Item<EscrituraUsuarioTipo> item) {
                item.add(new Component[]{(Component) new Label("loginUsuario", (IModel) Model.of(((EscrituraUsuarioTipo) item.getModelObject()).getLoginUsuario()))});
                item.add(new Component[]{(Component) new Label("tipo", (IModel) Model.of(((EscrituraUsuarioTipo) item.getModelObject()).getTipo()))});
            }
        };
        queue(new Component[]{(Component) listViewEscrituraUsuarioTipo});
    }

    private void initVistaEscrituraFirmaCondiciones() {
        WebMarkupContainer escrituraFirmaCondicionesDataView = new WebMarkupContainer("escrituraFirmaCondicionesDataView") {
            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }
        };
        queue(new Component[]{(Component) escrituraFirmaCondicionesDataView});
        escrituraFirmaCondicionesDataView.setOutputMarkupPlaceholderTag(true);
        RefreshingView<EscrituraFirmasCondiciones> listViewEscrituraFirmaCondiciones = new RefreshingView<EscrituraFirmasCondiciones>("listViewEscrituraFirmaCondiciones") {
            protected Iterator<IModel<EscrituraFirmasCondiciones>> getItemModels() {
                List<IModel<EscrituraFirmasCondiciones>> response = new ArrayList<>();
                CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().forEach((k, v) -> response.add(new Model((Serializable) v)));
                return response.iterator();
            }

            protected void populateItem(Item<EscrituraFirmasCondiciones> item) {
                item.add(new Component[]{(Component) new Label("codTipoCondicion", (IModel) Model.of(((EscrituraFirmasCondiciones) item.getModelObject()).getCodTipoCondicion()))});
                item.add(new Component[]{(Component) new Label("secuencial", (IModel) Model.of(((EscrituraFirmasCondiciones) item.getModelObject()).getSecCondicion()))});
                item.add(new Component[]{(Component) new Label("fijoVariable", (IModel) Model.of(((EscrituraFirmasCondiciones) item.getModelObject()).getFijoVariable()))});
                item.add(new Component[]{(Component) new Label("numFirmas", (IModel) Model.of(((EscrituraFirmasCondiciones) item.getModelObject()).getNumFirmas()))});
                item.add(new Component[]{(Component) new Label("valorInicial", (IModel) Model.of(((EscrituraFirmasCondiciones) item.getModelObject()).getValorInicial()))});
                item.add(new Component[]{(Component) new Label("valorFinal", (IModel) Model.of(((EscrituraFirmasCondiciones) item.getModelObject()).getValorFinal()))});
                item.add(new Component[]{(Component) new Label("variableSN", (IModel) Model.of(((EscrituraFirmasCondiciones) item.getModelObject()).getVariableSn()))});
                item.add(new Component[]{(Component) new Label("savedSN", (IModel) Model.of(((EscrituraFirmasCondiciones) item.getModelObject()).getSavedSn()))});
                item.add(new Component[]{(Component) new Label("modo", (IModel) Model.of(((EscrituraFirmasCondiciones) item.getModelObject()).getModo()))});
            }
        };
        queue(new Component[]{(Component) listViewEscrituraFirmaCondiciones});
    }

    private void initViewEscrituraCondicionTipo() {
        WebMarkupContainer seccionCondicionTipo = new WebMarkupContainer("seccionCondicionTipo") {
            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }

            protected void onConfigure() {
                super.onConfigure();
                setVisible(CondicionesNegocioFragment.this.enableEscrituraCondicionTipo());
            }
        };
        seccionCondicionTipo.setOutputMarkupPlaceholderTag(true);
        queue(new Component[]{(Component) seccionCondicionTipo});
        WebMarkupContainer escrituraCondicionTipoDataView = new WebMarkupContainer("escrituraCondicionTipoDataView") {
            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }
        };
        queue(new Component[]{(Component) escrituraCondicionTipoDataView});
        escrituraCondicionTipoDataView.setOutputMarkupPlaceholderTag(true);
        RefreshingView<EscrituraCondicionTipo> listViewEscrituraCondicionTipo = new RefreshingView<EscrituraCondicionTipo>("listViewEscrituraCondicionTipo") {
            protected Iterator<IModel<EscrituraCondicionTipo>> getItemModels() {
                List<IModel<EscrituraCondicionTipo>> response = new ArrayList<>();
                CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().forEach((ksfc, vsfc) -> {
                    if (Objects.isNull(vsfc.getEscrituraCondicionTipo())) {
                        vsfc.setEscrituraCondicionTipo(new HashMap<>());
                    }
                    vsfc.getEscrituraCondicionTipo().forEach((k, v) -> {
                        // Aquí puedes realizar operaciones con las claves (k) y valores (v) del mapa
                    });
                });
                return response.iterator();
            }

            protected void populateItem(Item<EscrituraCondicionTipo> item) {
                item.add(new Component[]{(Component) new Label("codTipoCondicion", (IModel) Model.of(((EscrituraCondicionTipo) item.getModelObject()).getCodTipoCondicion()))});
                item.add(new Component[]{(Component) new Label("secuencialCondicion", (IModel) Model.of(((EscrituraCondicionTipo) item.getModelObject()).getSecCondicion()))});
                item.add(new Component[]{(Component) new Label("tipo", (IModel) Model.of(((EscrituraCondicionTipo) item.getModelObject()).getTipo()))});
                item.add(new Component[]{(Component) new Label("numeroFirmas", (IModel) Model.of(((EscrituraCondicionTipo) item.getModelObject()).getNumFirmas()))});
            }
        };
        queue(new Component[]{(Component) listViewEscrituraCondicionTipo});
    }

    private void initViewConceptoUsuarioTipo() {
        WebMarkupContainer seccionConceptoUsuarioTipo = new WebMarkupContainer("seccionConceptoUsuarioTipo") {
            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }

            protected void onConfigure() {
                super.onConfigure();
                setVisible(CondicionesNegocioFragment.this.enableConceptoUsuarioTipo());
            }
        };
        seccionConceptoUsuarioTipo.setOutputMarkupPlaceholderTag(true);
        queue(new Component[]{(Component) seccionConceptoUsuarioTipo});
        WebMarkupContainer conceptoUsuarioTipoDataView = new WebMarkupContainer("conceptoUsuarioTipoDataView") {
            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }
        };
        queue(new Component[]{(Component) conceptoUsuarioTipoDataView});
        conceptoUsuarioTipoDataView.setOutputMarkupPlaceholderTag(true);
        RefreshingView<ConceptoUsuarioTipo> listViewConceptoUsuarioTipo = new RefreshingView<ConceptoUsuarioTipo>("listViewConceptoUsuarioTipo") {
            protected Iterator<IModel<ConceptoUsuarioTipo>> getItemModels() {
                List<IModel<ConceptoUsuarioTipo>> response = new ArrayList<>();
                CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().forEach((j, vj) -> {
                    if (Objects.isNull(vj.getConceptoUsuarioTipo())) {
                        vj.setConceptoUsuarioTipo(new HashMap<>());
                    }
                   // vj.getConceptoUsuarioTipo().forEach(());
                });
                return response.iterator();
            }

            protected void populateItem(Item<ConceptoUsuarioTipo> item) {
                item.add(new Component[]{(Component) new Label("loginUsuario", (IModel) Model.of(((ConceptoUsuarioTipo) item.getModelObject()).getLoginUsuario()))});
                item.add(new Component[]{(Component) new Label("tipo", (IModel) Model.of(((ConceptoUsuarioTipo) item.getModelObject()).getTipo()))});
                item.add(new Component[]{(Component) new Label("codTipoCondicion", (IModel) Model.of(((ConceptoUsuarioTipo) item.getModelObject()).getCodTipoCondicion()))});
                item.add(new Component[]{(Component) new Label("secCondicion", (IModel) Model.of(((ConceptoUsuarioTipo) item.getModelObject()).getSecCondicion()))});
                item.add(new Component[]{(Component) new Label("aplicaSN", (IModel) Model.of(((ConceptoUsuarioTipo) item.getModelObject()).getAplicaSN()))});
            }
        };
        queue(new Component[]{(Component) listViewConceptoUsuarioTipo});
    }

    private void initFormularioEscrituraCondicionTipo() {
        WebMarkupContainer seccionDataCondicionTipo = new WebMarkupContainer("seccionDataCondicionTipo") {
            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }

            protected void onConfigure() {
                super.onConfigure();
                setVisible((CondicionesNegocioFragment.this.enableEscrituraCondicionTipo()
                        && !CondicionesNegocioFragment.this.obtenerEscriturasFirmasCondicionesVariableS().isEmpty()));
            }
        };
        queue(new Component[]{(Component) seccionDataCondicionTipo});
        seccionDataCondicionTipo.setOutputMarkupPlaceholderTag(true);
        IModel descripcionModel = new IModel() {
            private String response = "";

            public Object getObject() {
                List<EscrituraFirmasCondiciones> condicionesVariableS = CondicionesNegocioFragment.this.obtenerEscriturasFirmasCondicionesVariableS();
                if (condicionesVariableS.isEmpty()) {
                    this.response = "";
                } else {
                    String secuencialesXtrabajo = "";
                    for (EscrituraFirmasCondiciones codntmp : condicionesVariableS) {
                        secuencialesXtrabajo = secuencialesXtrabajo + codntmp.getSecCondicion() + " ";
                        if (codntmp.getSecCondicion().intValue() == 0) {
                            CondicionesNegocioFragment.this.tmpCondicionesRelacionadas.setEscrituraFirmasCondiciones(codntmp);
                        }
                    }
                    this.response = "Se requiere ingresar detalle para los secuenciales " + secuencialesXtrabajo;
                }
                return this.response;
            }
        };
        Label descripcionValidacionCondiciones = new Label("descripcionValidacionCondiciones", descripcionModel);
        queue(new Component[]{(Component) descripcionValidacionCondiciones});
        Form formCondicionTipo = new Form("formCondicionTipo");
        queue(new Component[]{(Component) formCondicionTipo});
        formCondicionTipo.setOutputMarkupPlaceholderTag(true);
        IModel codCondSel = new IModel() {
            public Object getObject() {
                return Objects.isNull(CondicionesNegocioFragment.this.tmpCondicionesRelacionadas.getEscrituraFirmasCondiciones()) ? "" : CondicionesNegocioFragment.this.tmpCondicionesRelacionadas.getEscrituraFirmasCondiciones().getCodTipoCondicion();
            }
        };
        final Label codigoTipoCondicion = new Label("codigoTipoCondicion", codCondSel);
        queue(new Component[]{(Component) codigoTipoCondicion});
        codigoTipoCondicion.setOutputMarkupPlaceholderTag(true);
        LoadableDetachableModel listaSecuenciales = new LoadableDetachableModel() {
            protected List<EscrituraFirmasCondiciones> load() {
                List<EscrituraFirmasCondiciones> response = CondicionesNegocioFragment.this.obtenerEscriturasFirmasCondicionesVariableS();
                return response;
            }
        };
        ChoiceRenderer renderer = new ChoiceRenderer("secCondicion");
        final DropDownChoice secuencialCondicion = crearDropDown("secuencialCondicion",
                LambdaModel.of(this.tmpCondicionesRelacionadas::getEscrituraFirmasCondiciones, this.tmpCondicionesRelacionadas::setEscrituraFirmasCondiciones), (IModel) listaSecuenciales, " ", "Seleccione el secuencia condicia llenar.");
        secuencialCondicion.setChoiceRenderer((IChoiceRenderer) renderer);
        queue(new Component[]{(Component) secuencialCondicion});
        secuencialCondicion.setRequired(true);
        secuencialCondicion.setLabel((IModel) Model.of("Secuencial Condicion"));
        secuencialCondicion.add(new Behavior[]{(Behavior) new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
                target.add(new Component[]{(Component) secuencialCondicion, (Component) codigoTipoCondicion});
            }
        }});
        List<String> listTipo = Arrays.asList(new String[]{"A", "B", "C", "D"});
        DropDownChoice tipo = crearDropDown("tipo",
                LambdaModel.of(this.tmpCondicionesRelacionadas
                        .getEscrituraCondicionTipo()::getTipo, this.tmpCondicionesRelacionadas
                                .getEscrituraCondicionTipo()::setTipo), (IModel) new ListModel(listTipo), "Tipo ", "Tipo de firma a ingresar debe ser: A,B,C,D");
        queue(new Component[]{(Component) tipo});
        tipo.setRequired(true);
        tipo.setLabel((IModel) Model.of("Tipo"));
        TextField numeroFirmas = TxtFieldFactory.instancia().crearAjaxCampoTextStandarizado("numeroFirmas", true, true, "Nde Firmas",
                LambdaModel.of(this.tmpCondicionesRelacionadas
                        .getEscrituraCondicionTipo()::getNumFirmas, this.tmpCondicionesRelacionadas
                                .getEscrituraCondicionTipo()::setNumFirmas), (IModel) new ResourceModel("condiciones.negocio.fragment.form.generales.campo.numerofirmas.label", ""), Integer.class);
        numeroFirmas.add(new Behavior[]{(Behavior) new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
            }
        }});
        numeroFirmas.add((IValidator) RangeValidator.maximum(Integer.valueOf(99)));
        numeroFirmas.add(new Behavior[]{(Behavior) MaskInputFormatAppender.general("[2]", null, "", false, false, true)});
        queue(new Component[]{(Component) numeroFirmas});
        final Label numeroFirmasError = new Label("numeroFirmasError", (IModel) Model.of("No suficiente nde firmas disponible.")) {
            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }
        };
        queue(new Component[]{(Component) numeroFirmasError});
        numeroFirmasError.setVisible(false);
        numeroFirmasError.setOutputMarkupPlaceholderTag(true);
        AjaxSubmitLink agregarEscrituraCondicionTipo = new AjaxSubmitLink("agregarEscrituraCondicionTipo") {
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().forEach((i, v) -> {
                    if (v.getSecCondicion() == CondicionesNegocioFragment.this.tmpCondicionesRelacionadas.getEscrituraFirmasCondiciones().getSecCondicion()) {
                        int numTolFirmas = v.getNumFirmas().intValue();
                        int numFirmAgregadas = 0;
                        for (Map.Entry<String, EscrituraCondicionTipo> entryset : (Iterable<Map.Entry<String, EscrituraCondicionTipo>>) v.getEscrituraCondicionTipo().entrySet()) {
                            numFirmAgregadas += ((EscrituraCondicionTipo) entryset.getValue()).getNumFirmas().intValue();
                        }
                        if (numFirmAgregadas + CondicionesNegocioFragment.this.tmpCondicionesRelacionadas.getEscrituraCondicionTipo().getNumFirmas().intValue() <= numTolFirmas) {
                            EscrituraCondicionTipo valor = new EscrituraCondicionTipo();
                            valor.setCodTipoCondicion(v.getCodTipoCondicion());
                            valor.setNumFirmas(CondicionesNegocioFragment.this.tmpCondicionesRelacionadas.getEscrituraCondicionTipo().getNumFirmas());
                            valor.setSecCondicion("" + v.getSecCondicion());
                            valor.setTipo(CondicionesNegocioFragment.this.tmpCondicionesRelacionadas.getEscrituraCondicionTipo().getTipo());
                            v.getEscrituraCondicionTipo().put(CondicionesNegocioFragment.this.tmpCondicionesRelacionadas.getEscrituraCondicionTipo().getTipo(), valor);
                            numeroFirmasError.setVisible(false);
                        } else {
                            numeroFirmasError.setVisible(true);
                            CondicionesNegocioFragment.this.tmpCondicionesRelacionadas.getEscrituraCondicionTipo().setNumFirmas(Integer.valueOf(numTolFirmas - numFirmAgregadas));
                        }
                        CondicionesNegocioFragment.this.sendEvent(target);
                    }
                });
            }

            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
            }
        };
        queue(new Component[]{(Component) agregarEscrituraCondicionTipo});
    }

    private void initFormularioConceptoUsuarioTipo() {
        WebMarkupContainer seccionDataConceptoUsuarioTipo = new WebMarkupContainer("seccionDataConceptoUsuarioTipo") {
            public void onEvent(IEvent<?> event) {
                super.onEvent(event);
                if (event.getPayload() instanceof CondicionesNegocioFragment.EventUpdateMessag) {
                    CondicionesNegocioFragment.EventUpdateMessag update = (CondicionesNegocioFragment.EventUpdateMessag) event.getPayload();
                    update.getTarget().add(new Component[]{(Component) this});
                }
            }

            protected void onConfigure() {
                super.onConfigure();
                setVisible(CondicionesNegocioFragment.this.enableFormularioConceptoUsuarioTipo());
            }
        };
        queue(new Component[]{(Component) seccionDataConceptoUsuarioTipo});
        seccionDataConceptoUsuarioTipo.setOutputMarkupPlaceholderTag(true);
        IModel descripcionModel = new IModel() {
            private String response = "";

            public Object getObject() {
                List<EscrituraFirmasCondiciones> condicionesFijoVariableF = CondicionesNegocioFragment.this.obtenerEscriturasFirmasCondicionesFijoVariableF();
                if (condicionesFijoVariableF.isEmpty()) {
                    this.response = "";
                } else {
                    this.response = "Se requiere ingresar detalle para " + condicionesFijoVariableF.size() + " secuenciales";
                }
                return this.response;
            }
        };
        Label descripcionValidacionConceptoUsuarioTipo = new Label("descripcionValidacionConceptoUsuarioTipo", descripcionModel);
        queue(new Component[]{(Component) descripcionValidacionConceptoUsuarioTipo});
        Form formConceptoUsuarioTipo = new Form("formConceptoUsuarioTipo");
        queue(new Component[]{(Component) formConceptoUsuarioTipo});
        formConceptoUsuarioTipo.setOutputMarkupPlaceholderTag(true);
        LoadableDetachableModel listaEscrituraUsuarioTipo = new LoadableDetachableModel() {
            protected List<EscrituraUsuarioTipo> load() {
                List<EscrituraUsuarioTipo> response = new ArrayList<>();
                CondicionesNegocioFragment.this.condicionesManejo.getEscrituraUsuarioTipo().values().forEach(val -> response.add(val));
                return response;
            }
        };
        ChoiceRenderer rendererEscrituraUsuarioTipo = new ChoiceRenderer("loginUsuario");
        final DropDownChoice escrituraUsuarioTipo = crearDropDown("escrituraUsuarioTipo",
                LambdaModel.of(this.tmpCondicionesRelacionadasFijoVariable::getEscrituraUsuarioTipo, this.tmpCondicionesRelacionadasFijoVariable::setEscrituraUsuarioTipo), (IModel) listaEscrituraUsuarioTipo, " ", "Seleccione el login de usuario.");
        escrituraUsuarioTipo.setChoiceRenderer((IChoiceRenderer) rendererEscrituraUsuarioTipo);
        queue(new Component[]{(Component) escrituraUsuarioTipo});
        escrituraUsuarioTipo.setRequired(true);
        escrituraUsuarioTipo.setLabel((IModel) Model.of("Login de Usuario"));
        List<String> listaAplicaSiNo = new ArrayList<>();
        listaAplicaSiNo.add("N");
        listaAplicaSiNo.add("S");
        DropDownChoice aplicaSN = crearDropDown("aplicaSN",
                LambdaModel.of(this.tmpCondicionesRelacionadasFijoVariable
                        .getConceptoUsuarioTipo()::getAplicaSN, this.tmpCondicionesRelacionadasFijoVariable
                                .getConceptoUsuarioTipo()::setAplicaSN), (IModel) new ListModel(listaAplicaSiNo), " ", "Valor si aplica S = SI  y N = NO.");
        queue(new Component[]{(Component) aplicaSN});
        aplicaSN.setRequired(true);
        aplicaSN.setLabel((IModel) Model.of("Aplica (S/N)"));
        LoadableDetachableModel listaSecuenciales = new LoadableDetachableModel() {
            protected List<EscrituraFirmasCondiciones> load() {
                List<EscrituraFirmasCondiciones> response = CondicionesNegocioFragment.this.obtenerEscriturasFirmasCondicionesFijoVariableF();
                return response;
            }
        };
        ChoiceRenderer renderer = new ChoiceRenderer("secCondicion");
        DropDownChoice secuencialXConceptoUsuarioTipo = crearDropDown("secuencialXConceptoUsuarioTipo",
                LambdaModel.of(this.tmpCondicionesRelacionadasFijoVariable::getEscrituraFirmasCondiciones, this.tmpCondicionesRelacionadasFijoVariable::setEscrituraFirmasCondiciones), (IModel) listaSecuenciales, " ", "Seleccione el secuencia condicia llenar.");
        secuencialXConceptoUsuarioTipo.setChoiceRenderer((IChoiceRenderer) renderer);
        queue(new Component[]{(Component) secuencialXConceptoUsuarioTipo});
        secuencialXConceptoUsuarioTipo.setRequired(true);
        secuencialXConceptoUsuarioTipo.setLabel((IModel) Model.of("Secuencial Condicion Fijo Variable"));
        secuencialXConceptoUsuarioTipo.add(new Behavior[]{(Behavior) new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget target) {
                CondicionesNegocioFragment.this.tmpCondicionesRelacionadasFijoVariable.setEscrituraUsuarioTipo(null);
                target.add(new Component[]{(Component) escrituraUsuarioTipo});
            }
        }});
        AjaxSubmitLink agregarConceptoUsuarioTipo = new AjaxSubmitLink("agregarConceptoUsuarioTipo") {
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                CondicionesNegocioFragment.this.condicionesManejo.getEscrituraFirmasCondiciones().forEach((i, v) -> {
                    if (v.getFijoVariable().equalsIgnoreCase("f") && v.getSecCondicion() == CondicionesNegocioFragment.this.tmpCondicionesRelacionadasFijoVariable.getEscrituraFirmasCondiciones().getSecCondicion()) {
                        if (CondicionesNegocioFragment.this.condicionesManejo.getEscrituraUsuarioTipo().size() > v.getConceptoUsuarioTipo().size()) {
                            ConceptoUsuarioTipo tmpobj = new ConceptoUsuarioTipo();
                            tmpobj.setAplicaSN(CondicionesNegocioFragment.this.tmpCondicionesRelacionadasFijoVariable.getConceptoUsuarioTipo().getAplicaSN());
                            tmpobj.setCodTipoCondicion(v.getCodTipoCondicion());
                            tmpobj.setSecCondicion(v.getSecCondicion());
                            tmpobj.setLoginUsuario(CondicionesNegocioFragment.this.tmpCondicionesRelacionadasFijoVariable.getEscrituraUsuarioTipo().getLoginUsuario());
                            tmpobj.setTipo(CondicionesNegocioFragment.this.tmpCondicionesRelacionadasFijoVariable.getEscrituraUsuarioTipo().getTipo());
                            v.getConceptoUsuarioTipo().put(tmpobj.getLoginUsuario(), tmpobj);
                        }
                        CondicionesNegocioFragment.this.sendEvent(target);
                    }
                });
            }

            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
            }
        };
        queue(new Component[]{(Component) agregarConceptoUsuarioTipo});
    }

    private class TmpCondicionesRelacionadasFrm implements Serializable {

        private EscrituraFirmasCondiciones escrituraFirmasCondiciones;

        private EscrituraCondicionTipo escrituraCondicionTipo = new EscrituraCondicionTipo();

        private ConceptoUsuarioTipo conceptoUsuarioTipo = new ConceptoUsuarioTipo();

        private EscrituraUsuarioTipo escrituraUsuarioTipo;

        public EscrituraFirmasCondiciones getEscrituraFirmasCondiciones() {
            return this.escrituraFirmasCondiciones;
        }

        public void setEscrituraFirmasCondiciones(EscrituraFirmasCondiciones escrituraFirmasCondiciones) {
            this.escrituraFirmasCondiciones = escrituraFirmasCondiciones;
        }

        public EscrituraCondicionTipo getEscrituraCondicionTipo() {
            return this.escrituraCondicionTipo;
        }

        public void setEscrituraCondicionTipo(EscrituraCondicionTipo escrituraCondicionTipo) {
            this.escrituraCondicionTipo = escrituraCondicionTipo;
        }

        public ConceptoUsuarioTipo getConceptoUsuarioTipo() {
            return this.conceptoUsuarioTipo;
        }

        public void setConceptoUsuarioTipo(ConceptoUsuarioTipo conceptoUsuarioTipo) {
            this.conceptoUsuarioTipo = conceptoUsuarioTipo;
        }

        public EscrituraUsuarioTipo getEscrituraUsuarioTipo() {
            return this.escrituraUsuarioTipo;
        }

        public void setEscrituraUsuarioTipo(EscrituraUsuarioTipo escrituraUsuarioTipo) {
            this.escrituraUsuarioTipo = escrituraUsuarioTipo;
        }
    }

    private List<EscrituraFirmasCondiciones> obtenerEscriturasFirmasCondicionesVariableS() {
        List<EscrituraFirmasCondiciones> response = new ArrayList<>();
        this.condicionesManejo.getEscrituraFirmasCondiciones().forEach((llave, valor) -> {
            if (valor.getVariableSn().equalsIgnoreCase("S")) {
                int totalNumFirmasIngresadas = 0;
                for (Map.Entry<String, EscrituraCondicionTipo> entryset : (Iterable<Map.Entry<String, EscrituraCondicionTipo>>) valor.getEscrituraCondicionTipo().entrySet()) {
                    totalNumFirmasIngresadas += ((EscrituraCondicionTipo) entryset.getValue()).getNumFirmas().intValue();
                }
                if (totalNumFirmasIngresadas < valor.getNumFirmas().intValue()) {
                    response.add(valor);
                }
            }
        });
        return response;
    }

    private List<EscrituraFirmasCondiciones> obtenerEscriturasFirmasCondicionesFijoVariableF() {
        List<EscrituraFirmasCondiciones> response = new ArrayList<>();
        this.condicionesManejo.getEscrituraFirmasCondiciones().forEach((llave, valor) -> {
            if (valor.getFijoVariable().equalsIgnoreCase("F")) {
                if (this.condicionesManejo.getEscrituraUsuarioTipo().size() > valor.getConceptoUsuarioTipo().size()) {
                    response.add(valor);
                }
            }
        });
        return response;
    }

    public abstract void cerrar(AjaxRequestTarget paramAjaxRequestTarget);
}
