package com.fiduciaria.microapp.componentes;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.ajax.AjaxFileDropBehavior;
import org.apache.wicket.feedback.FeedbackMessages;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.time.Duration;

public abstract class StatelessSubirDocumento extends Panel {
    private final FileUploadField file;

    private long maximoMegaBytesCarga = 1L;

    private long maximoMegaBytesArchivo = 1L;

    private ArrayList<String> tiposArchivosValidos;

    private String fileLabelValue;

    private final boolean isDropEnabled;

    private transient List<FileUpload> uploads;

    private boolean indicatingEnabled = true;

    public StatelessSubirDocumento(String id, String fileLabel, boolean isDropEnabled) {
        super(id);
        this.tiposArchivosValidos = new ArrayList<>();
        this.file = new FileUploadField("file", (IModel) new PropertyModel(this, "uploads"));
        this.fileLabelValue = fileLabel;
        this.isDropEnabled = isDropEnabled;
    }

    protected void onInitialize() {
        super.onInitialize();
        final FeedbackPanel feedback = new FeedbackPanel("feedback") {
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("hidden", "true");
            }
        };
        feedback.setOutputMarkupId(true);
        queue(new Component[] { (Component) feedback });
        WebMarkupContainer uploadPanel = new WebMarkupContainer("uploadPanel");
        uploadPanel.setOutputMarkupPlaceholderTag(true);
        queue(new Component[] { (Component) uploadPanel });
        WebMarkupContainer inputlabel = new WebMarkupContainer("inputlabel") {
            public boolean isVisible() {
                return !StatelessSubirDocumento.this.isDropEnabled;
            }
        };
        queue(new Component[] { (Component) inputlabel });
        Label fileLabelInput = new Label("fileLabel", (IModel) new PropertyModel(this, "fileLabelValue"));
        fileLabelInput.setOutputMarkupPlaceholderTag(true);
        fileLabelInput.setEscapeModelStrings(false);
        queue(new Component[] { (Component) fileLabelInput });
        final Form<?> form = new Form("form");
        form.setOutputMarkupPlaceholderTag(true);
        form.setFileMaxSize(Bytes.megabytes(this.maximoMegaBytesArchivo));
        form.setMaxSize(Bytes.megabytes(this.maximoMegaBytesCarga));
        form.setMultiPart(true);
        queue(new Component[] { (Component) form });
        form.add(new Component[] { (Component) this.file });
        form.add(new Behavior[] { (Behavior) new AjaxFormValidatingBehavior("input", Duration.milliseconds(500L)) {
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                StatelessSubirDocumento.this.uploads = StatelessSubirDocumento.this.file.getFileUploads();
                if (StatelessSubirDocumento.this.uploads == null)
                    StatelessSubirDocumento.this.info("No file uploaded");
                StatelessSubirDocumento.this.callbackFilesUpload(target, StatelessSubirDocumento.this.uploads);
            }

            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
                target.add(new Component[] { feedback });
                FeedbackMessages messages = form.getFeedbackMessages();
                StatelessSubirDocumento.this.errorCallbackFilesUpload(target, messages.toString());
            }

            public boolean getStatelessHint(Component component) {
                return StatelessSubirDocumento.this.getStatelessHint();
            }
        } });
        OneClickAjaxButton ajaxSubmit = new OneClickAjaxButton("ajaxSubmit") {
            private static final long serialVersionUID = 1L;

            protected void onSubmit(AjaxRequestTarget target) {
                target.add(new Component[] { feedback });
                StatelessSubirDocumento.this.callbackFilesUpload(target, StatelessSubirDocumento.this.uploads);
            }

            protected void onError(AjaxRequestTarget target) {
                target.add(new Component[] { feedback });
                FeedbackMessages messages = form.getFeedbackMessages();
                StatelessSubirDocumento.this.errorCallbackFilesUpload(target, messages.toString());
            }

            protected boolean getStatelessHint() {
                return StatelessSubirDocumento.this.getStatelessHint();
            }
        };
        ajaxSubmit.setOutputMarkupPlaceholderTag(true);
        ajaxSubmit.setIndicatingEnabled(true);
        ajaxSubmit.setSmall(true);
        ajaxSubmit.setButtontype(ButtonType.LINK);
        ajaxSubmit.setVisible(false);
        form.add(new Component[] { (Component) ajaxSubmit });
        final WebMarkupContainer drop = new WebMarkupContainer("drop") {
            public boolean isVisible() {
                return StatelessSubirDocumento.this.isDropEnabled;
            }
        };
        drop.add(new Behavior[] { (Behavior) new AjaxFileDropBehavior() {
            protected void onFileUpload(AjaxRequestTarget target, List<FileUpload> files) {
                StatelessSubirDocumento.this.callbackFilesUpload(target, files);
            }

            protected void onError(AjaxRequestTarget target, FileUploadException fux) {
                StatelessSubirDocumento.this.errorCallbackFilesUpload(target, fux.getMessage());
                StatelessSubirDocumento.this.info(fux.getMessage());
            }

            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);
                AjaxCallListener listener = new AjaxCallListener() {

                };
                String indicatingclass = "";
                if (StatelessSubirDocumento.this.indicatingEnabled)
                    indicatingclass = "$('#" + form.getMarkupId() + "').addClass('pf-m-in-progress');$('#"
                            + form.getMarkupId()
                            + "').prepend('<span id=\"spinner\" class=\"pf-c-button__progress\" > <span class=\"pf-c-spinner pf-m-md\" role=\"progressbar\" aria-valuetext=\"Loading...\"> <span class=\"pf-c-spinner__clipper\"></span> <span class=\"pf-c-spinner__lead-ball\"></span> <span class=\"pf-c-spinner__tail-ball\"></span></span></span>');";
                String indicatingclasscomplete = "";
                if (StatelessSubirDocumento.this.indicatingEnabled)
                    indicatingclasscomplete = "$('#" + form.getMarkupId()
                            + "').children('span.pf-c-button__progress').remove();$('#" + form.getMarkupId()
                            + "').removeClass('pf-m-progress');$('#" + form.getMarkupId()
                            + "').removeClass('pf-m-in-progress');";
                listener.onBeforeSend("$('#" + form
                        .getMarkupId() + "').addClass('pf-m-progress');" + indicatingclass + "$('#"
                        + drop

                                .getMarkupId()
                        + "').prop('disabled', true);");
                listener.onComplete("if(jqXHR !=null){ if(jqXHR.status!=200){ window.location.reload(); }};$('#" + drop
                        .getMarkupId() + "').prop('disabled', false);" + indicatingclasscomplete);
                listener.onFailure(
                        "$('#" + drop.getMarkupId() + "').prop('disabled', false);" + indicatingclasscomplete);
                attributes.getAjaxCallListeners().add(listener);
            }

            public boolean getStatelessHint(Component component) {
                return StatelessSubirDocumento.this.getStatelessHint();
            }
        } });
        queue(new Component[] { (Component) drop });
        form.add(new Behavior[] { (Behavior) new AjaxFormValidatingBehavior("change", Duration.milliseconds(500L)) {
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                StatelessSubirDocumento.this.uploads = StatelessSubirDocumento.this.file.getFileUploads();
                if (StatelessSubirDocumento.this.uploads == null)
                    StatelessSubirDocumento.this.info("No file uploaded");
                StatelessSubirDocumento.this.callbackFilesUpload(target, StatelessSubirDocumento.this.uploads);
            }

            protected void onError(AjaxRequestTarget target) {
                super.onError(target);
                target.add(new Component[] { feedback });
                FeedbackMessages messages = form.getFeedbackMessages();
                StatelessSubirDocumento.this.errorCallbackFilesUpload(target, messages.toString());
            }

            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                String spinerSize;
                super.updateAjaxAttributes(attributes);
                if (StatelessSubirDocumento.this.isDropEnabled) {
                    spinerSize = "pf-m-xl";
                } else {
                    spinerSize = "pf-m-md";
                }
                AjaxCallListener listener = new AjaxCallListener() {

                };
                String indicatingclass = "";
                if (StatelessSubirDocumento.this.indicatingEnabled)
                    indicatingclass = "$('#" + form.getMarkupId() + "').prepend('<span class=\"pf-c-spinner "
                            + spinerSize
                            + " \" role=\"progressbar\" aria-valuetext=\"Loading...\"> <span class=\"pf-c-spinner__clipper\"></span> <span class=\"pf-c-spinner__lead-ball\"></span> <span class=\"pf-c-spinner__tail-ball\"></span></span>');";
                String indicatingclasscomplete = "";
                if (StatelessSubirDocumento.this.indicatingEnabled)
                    indicatingclasscomplete = "$('#" + form.getMarkupId()
                            + "').children('span.pf-c-spinner').remove();";
                listener.onBeforeSend(indicatingclass + "$('#" + drop

                        .getMarkupId() + "').hide();");
                listener.onComplete("if(jqXHR !=null){ if(jqXHR.status!=200){ window.location.reload(); }};$('#" + drop

                        .getMarkupId() + "').show();" + indicatingclasscomplete);
                listener.onFailure("$('#" + drop

                        .getMarkupId() + "').show();" + indicatingclasscomplete);
                attributes.getAjaxCallListeners().add(listener);
            }

            public boolean getStatelessHint(Component component) {
                return StatelessSubirDocumento.this.getStatelessHint();
            }
        } });
        if (!this.tiposArchivosValidos.isEmpty()) {
            StringBuilder bld = new StringBuilder();
            for (String flmime : this.tiposArchivosValidos) {
                if (bld.length() > 0)
                    bld.append(", ");
                bld.append(flmime);
            }
            this.file.add(new Behavior[] { (Behavior) new AttributeModifier("accept", bld.toString()) });
        }
    }

    public boolean validateMimeType(String filetype) {
        boolean isFileTypeValid = false;
        if (this.tiposArchivosValidos == null || this.tiposArchivosValidos.isEmpty()) {
            isFileTypeValid = true;
        } else {
            for (String type : this.tiposArchivosValidos) {
                if (type.equalsIgnoreCase(filetype))
                    isFileTypeValid = true;
            }
        }
        return isFileTypeValid;
    }

    public long getMaximoMegaBytesCarga() {
        return this.maximoMegaBytesCarga;
    }

    public void setMaximoMegaBytesCarga(long maximoMegaBytesCarga) {
        this.maximoMegaBytesCarga = maximoMegaBytesCarga;
    }

    public long getMaximoMegaBytesArchivo() {
        return this.maximoMegaBytesArchivo;
    }

    public void setMaximoMegaBytesArchivo(long maximoMegaBytesArchivo) {
        this.maximoMegaBytesArchivo = maximoMegaBytesArchivo;
    }

    public List<String> getTiposArchivosValidos() {
        return this.tiposArchivosValidos;
    }

    public void setTiposArchivosValidos(ArrayList<String> tiposArchivosValidos) {
        this.tiposArchivosValidos = tiposArchivosValidos;
    }

    protected boolean getStatelessHint() {
        return true;
    }

    public abstract void callbackFilesUpload(AjaxRequestTarget paramAjaxRequestTarget, List<FileUpload> paramList);

    public abstract void errorCallbackFilesUpload(AjaxRequestTarget paramAjaxRequestTarget, String paramString);
}
