package com.fiduciaria.microapp.componentes.datatable;

import com.fiduciaria.microapp.base.BasePanel;
import java.util.Objects;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;

public class AjaxPFNavigationToolbar extends BasePanel {
    public static final String FILTER_COMPONENT_ID = "filterSeccion";

    private static final String CSS_CLASS_PROPERTY = "class";

    private static final String CSS_CLASS_BUTTON_PLAIN = "pf-c-button pf-m-plain";

    private static final String CSS_CLASS_BUTTON_PLAIN_DISABLED = "pf-c-button pf-m-plain pf-m-disabled";

    private final AjaxFallbackSimpleDataTable pageable;

    private Component filterComponent;

    private Long currentPageValue;

    public AjaxPFNavigationToolbar(String id, AjaxFallbackSimpleDataTable pageable) {
        super(id);
        setOutputMarkupPlaceholderTag(true);
        this.pageable = pageable;
        this.currentPageValue = Long.valueOf(this.pageable.getCurrentPage() + 1L);
    }

    protected void onInitialize() {
        super.onInitialize();
        this.currentPageValue = Long.valueOf(this.pageable.getCurrentPage() + 1L);
        WebMarkupContainer navigationToolbar = new WebMarkupContainer("navigationToolbar") {
            protected void onConfigure() {
                super.onConfigure();
                setVisible((AjaxPFNavigationToolbar.this.pageable.getPageCount() > 0L));
            }
        };
        navigationToolbar.setOutputMarkupPlaceholderTag(true);
        queue(new Component[] { (Component) navigationToolbar });
        if (Objects.nonNull(this.filterComponent)) {
            navigationToolbar.addOrReplace(new Component[] { this.filterComponent });
        } else {
            WebMarkupContainer filterSeccion = new WebMarkupContainer("filterSeccion");
            queue(new Component[] { (Component) filterSeccion });
        }
        AjaxLink fistPage = new AjaxLink("fistPage") {
            public void onClick(AjaxRequestTarget art) {
                AjaxPFNavigationToolbar.this.pageable.setCurrentPage(0L);
                AjaxPFNavigationToolbar.this.currentPageValue = Long
                        .valueOf(AjaxPFNavigationToolbar.this.pageable.getCurrentPage() + 1L);
                //art.add(new Component[] { (Component) AjaxPFNavigationToolbar.access$000(this.this$0), (Component) this.this$0 });
            }

            protected void onConfigure() {
                super.onConfigure();
                setEnabled((AjaxPFNavigationToolbar.this.pageable.getCurrentPage() > 0L));
            }

            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                if (AjaxPFNavigationToolbar.this.pageable.getCurrentPage() > 0L) {
                    tag.put("class", "pf-c-button pf-m-plain");
                } else {
                    tag.put("class", "pf-c-button pf-m-plain pf-m-disabled");
                }
            }

            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);
                AjaxCallListener listener = new AjaxCallListener() {

                };
                String indicatingclass = "";
                indicatingclass = "$('#" + getMarkupId() + "').addClass('pf-m-in-progress');$('#" + getMarkupId()
                        + "').prepend('<span id=\"spinner\" class=\"pf-c-button__progress\" > <span class=\"pf-c-spinner pf-m-md\" role=\"progressbar\" aria-valuetext=\"Loading...\"> <span class=\"pf-c-spinner__clipper\"></span> <span class=\"pf-c-spinner__lead-ball\"></span> <span class=\"pf-c-spinner__tail-ball\"></span></span></span>');";
                String indicatingclasscomplete = "";
                indicatingclasscomplete = "$('#" + getMarkupId()
                        + "').children('span.pf-c-button__progress').remove();$('#" + getMarkupId()
                        + "').removeClass('pf-m-progress');$('#" + getMarkupId()
                        + "').removeClass('pf-m-in-progress');";
                listener.onBeforeSend("$('#" +
                        getMarkupId() + "').addClass('pf-m-progress');" + indicatingclass + "$('#" +

                        getMarkupId() + "').prop('disabled', true);");
                listener.onComplete("if(jqXHR !=null){ if(jqXHR.status!=200){ window.location.reload(); }};$('#" +
                        getMarkupId() + "').prop('disabled', false);" + indicatingclasscomplete);
                listener.onFailure("$('#" + getMarkupId() + "').prop('disabled', false);" + indicatingclasscomplete);
                attributes.getAjaxCallListeners().add(listener);
            }
        };
        queue(new Component[] { (Component) fistPage });
        AjaxLink previousPage = new AjaxLink("previousPage") {
            public void onClick(AjaxRequestTarget art) {
                if (AjaxPFNavigationToolbar.this.pageable.getCurrentPage() < 1L) {
                    AjaxPFNavigationToolbar.this.pageable.setCurrentPage(0L);
                } else {
                    long prev = AjaxPFNavigationToolbar.this.pageable.getCurrentPage();
                    AjaxPFNavigationToolbar.this.pageable.setCurrentPage(prev - 1L);
                }
                AjaxPFNavigationToolbar.this.currentPageValue = Long
                        .valueOf(AjaxPFNavigationToolbar.this.pageable.getCurrentPage() + 1L);
                //art.add(new Component[] { (Component) AjaxPFNavigationToolbar.access$000(this.this$0),(Component) this.this$0 });
            }

            protected void onConfigure() {
                super.onConfigure();
                setEnabled((AjaxPFNavigationToolbar.this.pageable.getCurrentPage() > 0L));
            }

            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                if (AjaxPFNavigationToolbar.this.pageable.getCurrentPage() > 0L) {
                    tag.put("class", "pf-c-button pf-m-plain");
                } else {
                    tag.put("class", "pf-c-button pf-m-plain pf-m-disabled");
                }
            }

            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);
                AjaxCallListener listener = new AjaxCallListener() {

                };
                String indicatingclass = "";
                indicatingclass = "$('#" + getMarkupId() + "').addClass('pf-m-in-progress');$('#" + getMarkupId()
                        + "').prepend('<span id=\"spinner\" class=\"pf-c-button__progress\" > <span class=\"pf-c-spinner pf-m-md\" role=\"progressbar\" aria-valuetext=\"Loading...\"> <span class=\"pf-c-spinner__clipper\"></span> <span class=\"pf-c-spinner__lead-ball\"></span> <span class=\"pf-c-spinner__tail-ball\"></span></span></span>');";
                String indicatingclasscomplete = "";
                indicatingclasscomplete = "$('#" + getMarkupId()
                        + "').children('span.pf-c-button__progress').remove();$('#" + getMarkupId()
                        + "').removeClass('pf-m-progress');$('#" + getMarkupId()
                        + "').removeClass('pf-m-in-progress');";
                listener.onBeforeSend("$('#" +
                        getMarkupId() + "').addClass('pf-m-progress');" + indicatingclass + "$('#" +

                        getMarkupId() + "').prop('disabled', true);");
                listener.onComplete("if(jqXHR !=null){ if(jqXHR.status!=200){ window.location.reload(); }};$('#" +
                        getMarkupId() + "').prop('disabled', false);" + indicatingclasscomplete);
                listener.onFailure("$('#" + getMarkupId() + "').prop('disabled', false);" + indicatingclasscomplete);
                attributes.getAjaxCallListeners().add(listener);
            }
        };
        queue(new Component[] { (Component) previousPage });
        AjaxLink lastPage = new AjaxLink("lastPage") {
            public void onClick(AjaxRequestTarget art) {
                long lastPage = AjaxPFNavigationToolbar.this.pageable.getPageCount() - 1L;
                AjaxPFNavigationToolbar.this.pageable.setCurrentPage(lastPage);
                AjaxPFNavigationToolbar.this.currentPageValue = Long
                        .valueOf(AjaxPFNavigationToolbar.this.pageable.getCurrentPage() + 1L);
                //art.add(new Component[] { (Component) AjaxPFNavigationToolbar.access$000(this.this$0),(Component) this.this$0 });
            }

            protected void onConfigure() {
                super.onConfigure();
                setEnabled(
                        (AjaxPFNavigationToolbar.this.pageable.getPageCount() > AjaxPFNavigationToolbar.this.pageable
                                .getCurrentPage()));
            }

            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                if (AjaxPFNavigationToolbar.this.pageable.getPageCount() - 1L > AjaxPFNavigationToolbar.this.pageable
                        .getCurrentPage()) {
                    tag.put("class", "pf-c-button pf-m-plain");
                } else {
                    tag.put("class", "pf-c-button pf-m-plain pf-m-disabled");
                }
            }

            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);
                AjaxCallListener listener = new AjaxCallListener() {

                };
                String indicatingclass = "";
                indicatingclass = "$('#" + getMarkupId() + "').addClass('pf-m-in-progress');$('#" + getMarkupId()
                        + "').prepend('<span id=\"spinner\" class=\"pf-c-button__progress\" > <span class=\"pf-c-spinner pf-m-md\" role=\"progressbar\" aria-valuetext=\"Loading...\"> <span class=\"pf-c-spinner__clipper\"></span> <span class=\"pf-c-spinner__lead-ball\"></span> <span class=\"pf-c-spinner__tail-ball\"></span></span></span>');";
                String indicatingclasscomplete = "";
                indicatingclasscomplete = "$('#" + getMarkupId()
                        + "').children('span.pf-c-button__progress').remove();$('#" + getMarkupId()
                        + "').removeClass('pf-m-progress');$('#" + getMarkupId()
                        + "').removeClass('pf-m-in-progress');";
                listener.onBeforeSend("$('#" +
                        getMarkupId() + "').addClass('pf-m-progress');" + indicatingclass + "$('#" +

                        getMarkupId() + "').prop('disabled', true);");
                listener.onComplete("if(jqXHR !=null){ if(jqXHR.status!=200){ window.location.reload(); }};$('#" +
                        getMarkupId() + "').prop('disabled', false);" + indicatingclasscomplete);
                listener.onFailure("$('#" + getMarkupId() + "').prop('disabled', false);" + indicatingclasscomplete);
                attributes.getAjaxCallListeners().add(listener);
            }
        };
        queue(new Component[] { (Component) lastPage });
        AjaxLink nextPage = new AjaxLink("nextPage") {
            public void onClick(AjaxRequestTarget art) {
                if (AjaxPFNavigationToolbar.this.pageable
                        .getCurrentPage() < AjaxPFNavigationToolbar.this.pageable.getPageCount() - 1L) {
                    long next = AjaxPFNavigationToolbar.this.pageable.getCurrentPage();
                    AjaxPFNavigationToolbar.this.pageable.setCurrentPage(next + 1L);
                    AjaxPFNavigationToolbar.this.currentPageValue = Long
                            .valueOf(AjaxPFNavigationToolbar.this.pageable.getCurrentPage() + 1L);
                    //art.add(new Component[] { (Component) AjaxPFNavigationToolbar.access$000(this.this$0),(Component) this.this$0 });
                }
            }

            protected void onConfigure() {
                super.onConfigure();
                setEnabled(
                        (AjaxPFNavigationToolbar.this.pageable.getPageCount()
                                - 1L > AjaxPFNavigationToolbar.this.pageable.getCurrentPage()));
            }

            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                if (AjaxPFNavigationToolbar.this.pageable.getPageCount() > AjaxPFNavigationToolbar.this.pageable
                        .getCurrentPage()) {
                    tag.put("class", "pf-c-button pf-m-plain");
                } else {
                    tag.put("class", "pf-c-button pf-m-plain pf-m-disabled");
                }
            }

            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);
                AjaxCallListener listener = new AjaxCallListener() {

                };
                String indicatingclass = "";
                indicatingclass = "$('#" + getMarkupId() + "').addClass('pf-m-in-progress');$('#" + getMarkupId()
                        + "').prepend('<span id=\"spinner\" class=\"pf-c-button__progress\" > <span class=\"pf-c-spinner pf-m-md\" role=\"progressbar\" aria-valuetext=\"Loading...\"> <span class=\"pf-c-spinner__clipper\"></span> <span class=\"pf-c-spinner__lead-ball\"></span> <span class=\"pf-c-spinner__tail-ball\"></span></span></span>');";
                String indicatingclasscomplete = "";
                indicatingclasscomplete = "$('#" + getMarkupId()
                        + "').children('span.pf-c-button__progress').remove();$('#" + getMarkupId()
                        + "').removeClass('pf-m-progress');$('#" + getMarkupId()
                        + "').removeClass('pf-m-in-progress');";
                listener.onBeforeSend("$('#" +
                        getMarkupId() + "').addClass('pf-m-progress');" + indicatingclass + "$('#" +

                        getMarkupId() + "').prop('disabled', true);");
                listener.onComplete("if(jqXHR !=null){ if(jqXHR.status!=200){ window.location.reload(); }};$('#" +
                        getMarkupId() + "').prop('disabled', false);" + indicatingclasscomplete);
                listener.onFailure("$('#" + getMarkupId() + "').prop('disabled', false);" + indicatingclasscomplete);
                attributes.getAjaxCallListeners().add(listener);
            }
        };
        queue(new Component[] { (Component) nextPage });
        Label totalPages = new Label("totalPages", (IModel) new LoadableDetachableModel() {
            protected Object load() {
                return Long.valueOf(AjaxPFNavigationToolbar.this.pageable.getPageCount());
            }
        });
        totalPages.setOutputMarkupPlaceholderTag(true);
        queue(new Component[] { (Component) totalPages });
        Label totalRegistros = new Label("totalRegistros", (IModel) new LoadableDetachableModel() {
            protected Object load() {
                return Long.valueOf(AjaxPFNavigationToolbar.this.pageable.getItemCount());
            }
        });
        totalRegistros.setOutputMarkupPlaceholderTag(true);
        queue(new Component[] { (Component) totalRegistros });
        Label mostrandoRango = new Label("mostrandoRango", (IModel) new LoadableDetachableModel() {
            protected Object load() {
                return AjaxPFNavigationToolbar.this.calculoMostrandoRango();
            }
        });
        mostrandoRango.setOutputMarkupPlaceholderTag(true);
        queue(new Component[] { (Component) mostrandoRango });
        NumberTextField currentPage = new NumberTextField("currentPage",
                (IModel) PropertyModel.of(this, "currentPageValue")) {
            protected void onBeforeRender() {
                super.onBeforeRender();
                AjaxPFNavigationToolbar.this.currentPageValue = Long
                        .valueOf(AjaxPFNavigationToolbar.this.pageable.getCurrentPage() + 1L);
            }

            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("min", "1");
                tag.put("max", "" + AjaxPFNavigationToolbar.this.pageable.getPageCount());
                tag.put("readonly", "true");
            }
        };
        currentPage.add(new Behavior[] { (Behavior) new AjaxFormComponentUpdatingBehavior("change") {
            protected void onUpdate(AjaxRequestTarget art) {
                AjaxPFNavigationToolbar.this.pageable
                        .setCurrentPage(AjaxPFNavigationToolbar.this.currentPageValue.longValue() - 1L);
                //art.add(new Component[] { (Component) AjaxPFNavigationToolbar.access$000(this.this$0),(Component) this.this$0 });
            }
        } });
        currentPage.setOutputMarkupPlaceholderTag(true);
        queue(new Component[] { (Component) currentPage });
    }

    private String calculoMostrandoRango() {
        Long hasta;
        if (this.pageable.getItemCount() > this.pageable
                .getItemsPerPage() * (this.pageable.getCurrentPage() + 1L)) {
            hasta = Long.valueOf(this.pageable.getItemsPerPage() * (this.pageable.getCurrentPage() + 1L));
        } else {
            hasta = Long.valueOf(this.pageable.getItemCount());
        }
        long desde = this.pageable.getCurrentPage() * this.pageable.getItemsPerPage() + 1L;
        return desde + " - " + hasta;
    }

    public void addFilterComponents(Component component) {
        if (component.getId().equals("filterSeccion"))
            this.filterComponent = component;
    }
}
