package com.fiduciaria.microapp.page.statefull.ayuda.panel;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.decorator.behavior.FormGroupControlBehavior;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
//import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.util.time.Duration;

public class RecursosAprendizaje extends BasePanel {
  @SpringBean
  IGenericHttpClient gtwy;
  
  private boolean seccionFormularioExpanded;
  
  public RecursosAprendizaje(String id) {
    super(id);
  }
  
  public RecursosAprendizaje(String id, IModel<?> model) {
    super(id, model);
  }
  
  protected void onInitialize() {
    super.onInitialize();
    setOutputMarkupPlaceholderTag(true);
    WebMarkupContainer expandedSeccion = new WebMarkupContainer("expandedSeccion") {
        protected void onConfigure() {
          super.onConfigure();
          setVisible(RecursosAprendizaje.this.seccionFormularioExpanded);
        }
      };
    queue(new Component[] { (Component)expandedSeccion });
    final WebMarkupContainer seccionFormulario = new WebMarkupContainer("seccionFormulario") {
        protected void onConfigure() {
          super.onConfigure();
          if (AuthenticatedWebSession.get().getRoles().hasRole("UIFA") || 
            AuthenticatedWebSession.get().getRoles().hasRole("UIFS"))
            if (String.valueOf(
                AuthenticatedWebSession.get().getAttribute("usertype")).equalsIgnoreCase("INT")) {
              setVisible(true);
              return;
            }  
          setVisible(false);
        }
        
        protected void onComponentTag(ComponentTag tag) {
          super.onComponentTag(tag);
          if (RecursosAprendizaje.this.seccionFormularioExpanded) {
            tag.put("class", "pf-c-expandable-section pf-m-expanded");
          } else {
            tag.put("class", "pf-c-expandable-section");
          } 
        }
      };
    seccionFormulario.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)seccionFormulario });
    AjaxLink expandirContraerForm = new AjaxLink("expandirContraerForm") {
        public void onClick(AjaxRequestTarget target) {
          RecursosAprendizaje.this.seccionFormularioExpanded = !RecursosAprendizaje.this.seccionFormularioExpanded;
          target.add(new Component[] { (Component)seccionFormulario });
        }
      };
    queue(new Component[] { (Component)expandirContraerForm });
    FormularioArchivosAyuda agregarArchivoForm = new FormularioArchivosAyuda("agregarArchivoForm") {
        protected void onConfigure() {
          super.onConfigure();
        }
      };
    queue(new Component[] { (Component)agregarArchivoForm });
    final WebMarkupContainer manualesDataView = new WebMarkupContainer("manualesDataView");
    queue(new Component[] { (Component)manualesDataView });
    manualesDataView.setOutputMarkupPlaceholderTag(true);
    LoadableDetachableModel<List<ArchivoPojo>> listaManuales = new LoadableDetachableModel<List<ArchivoPojo>>() {
        protected List<ArchivoPojo> load() {
          return ArchivoPojoVM.instance(RecursosAprendizaje.this.gtwy).listaArchivosAyuda();
        }
      };
    RefreshingView<ArchivoPojo> manualesListView = new RefreshingView<ArchivoPojo>("manualesListView") {
        protected void populateItem(final Item<ArchivoPojo> item) {
          item.add(new Component[] { (Component)new Label("nombreArchivo", ((ArchivoPojo)item.getModelObject()).getNombre()) });
          item.add(new Component[] { (Component)new Label("resumenArchivo", ((ArchivoPojo)item.getModelObject()).getResumen()) });
          item.add(new Component[] { (Component)new IndicatingAjaxLink("eliminarArchivo") {
                  protected void onConfigure() {
                    super.onConfigure();
                    if (AuthenticatedWebSession.get().getRoles().hasRole("UIFA") || 
                      AuthenticatedWebSession.get().getRoles().hasRole("UIFS"))
                      if (String.valueOf(
                          AuthenticatedWebSession.get().getAttribute("usertype")).equalsIgnoreCase("INT")) {
                        setVisible(true);
                        return;
                      }  
                    setVisible(false);
                  }
                  
                  public void onClick(AjaxRequestTarget target) {
                    try {
                      ArchivoPojoVM.instance(RecursosAprendizaje.this.gtwy).deleteArchivoAyuda((ArchivoPojo)item.getModelObject());
                      java.nio.file.Files.deleteIfExists(
                          Paths.get(((ArchivoPojo)item.getModelObject()).getLocalizacion(), new String[0]));
                    } catch (IOException ex) {
                      Logger.getLogger(RecursosAprendizaje.class.getName()).log(Level.SEVERE, (String)null, ex);
                    } 
                    target.add(new Component[] { (Component)manualesDataView });
                  }
                } });
          String nombre = ((ArchivoPojo)item.getModelObject()).getLocalizacion().split("/")[(((ArchivoPojo)item.getModelObject()).getLocalizacion().split("/")).length - 1];
          item.add(new Component[] { (Component)(new DownloadLink("downloadLink", new IModel<File>() {
                    private static final long serialVersionUID = 1L;
                    
                    public File getObject() {
                      File tempFile = new File(((ArchivoPojo)item.getModelObject()).getLocalizacion());
                      return tempFile;
                    }
                  },  nombre)).setCacheDuration(Duration.NONE) });
        }
        
        protected Iterator<IModel<ArchivoPojo>> getItemModels() {
          List<IModel<ArchivoPojo>> lista = new ArrayList<>();
          ArchivoPojoVM.instance(RecursosAprendizaje.this.gtwy).listaArchivosAyuda().forEach(archivo -> lista.add(new Model((Serializable)archivo)));
          return lista.iterator();
        }
      };
    manualesDataView.queue(new Component[] { (Component)manualesListView });
  }
  
  private class FormularioArchivosAyuda extends Form {
    private final ArchivoPojo modelo;
    
    private FileUploadField fileUploadField;
    
    public FormularioArchivosAyuda(String id) {
      super(id);
      this.modelo = new ArchivoPojo();
      setMultiPart(true);
      setMaxSize(Bytes.kilobytes(100000L));
      setFileMaxSize(Bytes.kilobytes(90000L));
    }
    
    protected void onInitialize() {
      super.onInitialize();
      TextField rutaArchivo = new TextField("rutaArchivo", LambdaModel.of(this.modelo::getLocalizacion, this.modelo::setLocalizacion));
      rutaArchivo.setOutputMarkupPlaceholderTag(true);
      queue(new Component[] { (Component)rutaArchivo });
      rutaArchivo.setRequired(true);
      rutaArchivo.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "Sitio archivo", "", "Agregue el directorio donde estaralmacenado el archivo") });
      rutaArchivo.setLabel((IModel)Model.of("Directorio de archivo"));
      TextField nombreDescripcion = new TextField("nombreDescripcion", LambdaModel.of(this.modelo::getResumen, this.modelo::setResumen));
      nombreDescripcion.setOutputMarkupPlaceholderTag(true);
      queue(new Component[] { (Component)nombreDescripcion });
      nombreDescripcion.setLabel((IModel)Model.of("Resumen del archivo"));
      nombreDescripcion.setRequired(true);
      nombreDescripcion.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "Nombre archivo", "", "Agregue resumen del contenido del archivo de ayuda para el usuario.") });
      TextField nombreArchivo = new TextField("nombreArchivo", LambdaModel.of(this.modelo::getNombre, this.modelo::setNombre));
      nombreArchivo.setOutputMarkupPlaceholderTag(true);
      queue(new Component[] { (Component)nombreArchivo });
      nombreArchivo.setLabel((IModel)Model.of("Nombre archivo"));
      nombreArchivo.setRequired(true);
      nombreArchivo.add(new Behavior[] { (Behavior)new FormGroupControlBehavior(true, FormGroupControlBehavior.PosicionPopover.ARRIBA, "Nombre archivo", "", "Agregue le nombre como quiere sea visto en la pantalla") });
      queue(new Component[] { (Component)(this.fileUploadField = new FileUploadField("fileInput")) });
      this.fileUploadField.setRequired(true);
    }
    
    protected void onSubmit() {
      List<FileUpload> uploads = this.fileUploadField.getFileUploads();
      if (uploads != null)
        for (FileUpload upload : uploads) {
          if (!this.modelo.getLocalizacion().endsWith("/"))
            this.modelo.setLocalizacion(this.modelo.getLocalizacion() + "/"); 
          File newFile = new File((File)getUploadFolder(this.modelo.getLocalizacion()), upload.getClientFileName());
          checkFileExists(newFile);
          try {
            newFile.createNewFile();
            upload.writeTo(newFile);
            this.modelo.setLocalizacion(this.modelo
                .getLocalizacion() + upload
                .getClientFileName());
            ArchivoPojoVM.instance(RecursosAprendizaje.this.gtwy).agregarArchivoAyuda(this.modelo);
            RecursosAprendizaje.this.info("Archivo guardado: " + upload.getClientFileName());
          } catch (Exception e) {
            throw new IllegalStateException("No pudo escribir el archivo", e);
          } 
        }  
      this.modelo.clear();
      RecursosAprendizaje.this.seccionFormularioExpanded = false;
    }
    
    protected void onError() {
      super.onError();
      RecursosAprendizaje.this.info("No se pudo guardar el archivo. ");
    }
    
    private void checkFileExists(File newFile) {
      if (newFile.exists() && 
        !Files.remove(newFile))
        throw new IllegalStateException("No puede sobreescribir " + newFile.getAbsolutePath()); 
    }
    
    private Folder getUploadFolder(String localizacion) {
      if (Objects.nonNull(ArchivoPojoVM.instance(RecursosAprendizaje.this.gtwy).listaDirectorioConfArchivos()) && 
        !ArchivoPojoVM.instance(RecursosAprendizaje.this.gtwy).listaDirectorioConfArchivos().isEmpty()) {
        String rutaBase = ArchivoPojoVM.instance(RecursosAprendizaje.this.gtwy).listaDirectorioConfArchivos().get(0);
        Folder uploadFolder = new Folder(rutaBase, localizacion);
        uploadFolder.mkdirs();
        return uploadFolder;
      } 
      return null;
    }
  }
}
