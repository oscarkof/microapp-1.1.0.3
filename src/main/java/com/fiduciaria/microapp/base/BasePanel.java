package com.fiduciaria.microapp.base;

import com.fiduciaria.microapp.page.MainPage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.cycle.IRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.visit.IVisit;

public class BasePanel extends Panel {

    private final String modalContentId;

    public BasePanel(String id) {
        super(id);
        this.modalContentId = MainPage.getModalContentId();
    }

    public BasePanel(String id, IModel<?> model) {
        super(id, model);
        this.modalContentId = MainPage.getModalContentId();
    }

    protected static final MetaDataKey<List<Class<? extends Behavior>>> BEHAVIOR_TYPE = new MetaDataKey<List<Class<? extends Behavior>>>() {

    };

    protected void onInitialize() {
        super.onInitialize();
        registerListeners();
    }

    public void registrarComportamiento(Class<? extends Behavior> behaviorClazz) {
        ((List<Class<? extends Behavior>>) getRequestCycle().getMetaData(BEHAVIOR_TYPE))
                .add(behaviorClazz);
    }

    private void registerListeners() {
        getApplication().getRequestCycleListeners().add(new IRequestCycleListener() {
            public void onBeginRequest(RequestCycle cycle) {
                RequestCycle.get().setMetaData(BasePanel.BEHAVIOR_TYPE, new ArrayList());
            }
        });
        ((WebApplication) getApplication()).getAjaxRequestTargetListeners()
                .add(new AjaxRequestTarget.IListener() {
                    public void onBeforeRespond(Map<String, Component> map, AjaxRequestTarget target) {
                        List<Class<? extends Behavior>> list = (List<Class<? extends Behavior>>) RequestCycle.get().getMetaData(BasePanel.BEHAVIOR_TYPE);
                        target.getPage().visitChildren((c, ivisit) -> {
                            for (Behavior b : c.getBehaviors()) {
                                if (list.contains(b.getClass())) {
                                    target.add(new Component[]{c});
                                }
                            }
                        });
                    }
                });
    }

    public void addModal(Component modal) {
        ((MainPage) getPage()).addModal(modal);
    }

    public void showModal(boolean visible, AjaxRequestTarget art) {
        ((MainPage) getPage()).showModal(visible, art);
    }

    public void removeModal() {
        ((MainPage) getPage()).removeModal();
    }

    public String getModalContentId() {
        return this.modalContentId;
    }

    public String usuarioSesion() {
        String usuario = "";
        if (Objects.nonNull(AuthenticatedWebSession.get().getAttribute("username"))) {
            usuario = AuthenticatedWebSession.get().getAttribute("username").toString();
        }
        return usuario;
    }
}
