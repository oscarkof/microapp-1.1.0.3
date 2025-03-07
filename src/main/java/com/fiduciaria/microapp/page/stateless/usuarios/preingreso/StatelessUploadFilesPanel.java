package com.fiduciaria.microapp.page.stateless.usuarios.preingreso;

import com.fiduciaria.microapp.base.BasePanel;
import com.fiduciaria.microapp.componentes.StatelessAjaxSubmitLink;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.upload.FileDescription;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.form.upload.FilesSelectedBehavior;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;

public class StatelessUploadFilesPanel extends BasePanel {
  private final Component feedback = (Component)new FeedbackPanel("feedback");
  
  private final WebMarkupContainer dataview = new WebMarkupContainer("dataview");
  
  private final WebMarkupContainer dataviewSelected = new WebMarkupContainer("dataviewSelected");
  
  private final List<File> listaArchivos = new ArrayList<>();
  
  private final List<FileDescription> listadescArchivos = new ArrayList<>();
  
  private final String prefijo;
  
  private final FormularioPreingresoDatosUsuarioVM viewModel;
  
  public StatelessUploadFilesPanel(String id, IModel<String> model, FormularioPreingresoDatosUsuarioVM viewModel) {
    super(id, model);
    this.prefijo = (String)model.getObject();
    this.viewModel = viewModel;
  }
  
  protected void onInitialize() {
    super.onInitialize();
    initAdjuntos();
  }
  
  private void initAdjuntos() {
    queue(new Component[] { this.feedback });
    this.feedback.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)this.dataview });
    queue(new Component[] { (Component)this.dataviewSelected });
    this.dataview.setOutputMarkupPlaceholderTag(true);
    this.dataviewSelected.setOutputMarkupPlaceholderTag(true);
    FileUploadForm simpleUploadForm = new FileUploadForm("simpleUpload");
    simpleUploadForm.setOutputMarkupPlaceholderTag(true);
    queue(new Component[] { (Component)simpleUploadForm });
    simpleUploadForm.setMaxSize(Bytes.megabytes(1500L));
    simpleUploadForm.setFileMaxSize(Bytes.megabytes(100L));
    FileListView fileListView = new FileListView("fileList", (IModel<List<File>>)new LoadableDetachableModel<List<File>>() {
          protected List<File> load() {
            StatelessUploadFilesPanel.this.listaArchivos.clear();
            StatelessUploadFilesPanel.this.listaArchivos.addAll(Arrays.asList(StatelessUploadFilesPanel.this.getUploadFolder().listFiles()));
            return StatelessUploadFilesPanel.this.listaArchivos;
          }
        });
    queue(new Component[] { (Component)fileListView });
    FileDescriptionListView fileDescListView = new FileDescriptionListView("fileDescList", (IModel<List<FileDescription>>)new LoadableDetachableModel<List<FileDescription>>() {
          protected List<FileDescription> load() {
            return StatelessUploadFilesPanel.this.listadescArchivos;
          }
        });
    queue(new Component[] { (Component)fileDescListView });
  }
  
  public int totalArchivosUsuarios() {
    int totalArchivoUsuario = 0;
    for (File file : Arrays.<File>asList(getUploadFolder().listFiles())) {
      if (file.getName().startsWith(this.prefijo))
        totalArchivoUsuario++; 
    } 
    return totalArchivoUsuario;
  }
  
  private class FileUploadForm extends StatelessForm<Void> {
    private transient List<FileUpload> fileinputTmp;
    
    public FileUploadForm(String id) {
      super(id);
      setOutputMarkupPlaceholderTag(true);
      setMultiPart(true);
      final FileUploadField fileInput = new FileUploadField("fileInput", LambdaModel.of(this::getFileinputTmp, this::setFileinputTmp));
      add(new Component[] { (Component)fileInput });
      fileInput.add(new Behavior[] { (Behavior)new FilesSelectedBehavior() {
              protected void onSelected(AjaxRequestTarget target, List<FileDescription> fileDescriptions) {
                StatelessUploadFilesPanel.this.listadescArchivos.clear();
                StatelessUploadFilesPanel.this.listadescArchivos.addAll(fileDescriptions);
                Bytes bytes = Bytes.bytes(fileDescriptions.stream().mapToLong(FileDescription::getFileSize).sum());
                if (bytes.greaterThan(StatelessUploadFilesPanel.FileUploadForm.this.getFileMaxSize())) {
                  StatelessUploadFilesPanel.FileUploadForm.this.error(" Supera el valor mpermito para carga de un archivo  " + StatelessUploadFilesPanel.FileUploadForm.this.getFileMaxSize());
                } else {
                  StatelessUploadFilesPanel.FileUploadForm.this.info("Permite cargar archivos" + StatelessUploadFilesPanel.FileUploadForm.this.getMaxSize());
                } 
                //target.add(new Component[] { (Component)StatelessUploadFilesPanel.access$300(this.this$1.this$0), StatelessUploadFilesPanel.access$400(this.this$1.this$0) });
              }
              
              public boolean getStatelessHint(Component component) {
                return true;
              }
            } });
      add(new Component[] { (Component)new StatelessAjaxSubmitLink("submitUpload") {
              protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                List<FileUpload> uploadfls = fileInput.getFileUploads();
                uploadfls.forEach(upload -> {
                      File newFile = new File((File)StatelessUploadFilesPanel.this.getUploadFolder(), StatelessUploadFilesPanel.this.prefijo + "_" + upload.getClientFileName());
                      StatelessUploadFilesPanel.this.checkFileExists(newFile);
                      try {
                        newFile.createNewFile();
                        upload.writeTo(newFile);
                        StatelessUploadFilesPanel.this.listadescArchivos.clear();
                        StatelessUploadFilesPanel.this.listaArchivos.clear();
                        StatelessUploadFilesPanel.this.listaArchivos.addAll(Arrays.asList(StatelessUploadFilesPanel.this.getUploadFolder().listFiles()));
                      } catch (Exception e) {
                        throw new IllegalStateException("No se pudo guardar el archivo");
                      } 
                    });
                //target.add(new Component[] { (Component)StatelessUploadFilesPanel.access$300(this.this$1.this$0), (Component)StatelessUploadFilesPanel.access$500(this.this$1.this$0), (Component)this.val$fileInput, (Component)this.this$1 });
              }
            } });
    }
    
    public List<FileUpload> getFileinputTmp() {
      return this.fileinputTmp;
    }
    
    public void setFileinputTmp(List<FileUpload> fileinputTmp) {
      this.fileinputTmp = fileinputTmp;
    }
  }
  
  private class FileListView extends ListView<File> {
    public FileListView(String name, IModel<List<File>> files) {
      super(name, files);
    }
    
    protected void populateItem(ListItem<File> listItem) {
      final File file = (File)listItem.getModelObject();
      listItem.setVisible(file.getName().startsWith("frmausr_"));
      listItem.add(new Component[] { (Component)new Label("file", file.getName().replace("frmausr_", "")) });
      listItem.add(new Component[] { (Component)new AjaxLink("delete") {
              private static final long serialVersionUID = 1L;
              
              protected void onConfigure() {
                super.onConfigure();
                setOutputMarkupPlaceholderTag(true);
              }
              
              public String getMarkupId() {
                return file.getName();
              }
              
              public void onClick(AjaxRequestTarget target) {
                Files.remove(file);
                info("Deleted " + file);
                StatelessUploadFilesPanel.this.listaArchivos.clear();
                for (File adjunto : Arrays.<File>asList(StatelessUploadFilesPanel.this.getUploadFolder().listFiles())) {
                  if (adjunto.isFile() && adjunto.getName().startsWith(StatelessUploadFilesPanel.this.prefijo))
                    StatelessUploadFilesPanel.this.listaArchivos.add(adjunto); 
                } 
                //target.add(new Component[] { (Component)StatelessUploadFilesPanel.access$500(this.this$1.this$0) });
              }
              
              protected boolean getStatelessHint() {
                return true;
              }
            } });
    }
  }
  
  private void checkFileExists(File newFile) {
    if (newFile.exists())
      if (!Files.remove(newFile))
        throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());  
  }
  
  private Folder getUploadFolder() {
    Folder folder = new Folder(System.getProperty("java.io.tmpdir") + "/hsperfdata_psbswn/sesstmp/" + this.viewModel.getModelo().getPreingresodatosusuario().getUuid());
    try {
      folder.ensureExists();
    } catch (IOException ex) {
      Logger.getLogger(StatelessUploadFilesPanel.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    return folder;
  }
  
  private class FileDescriptionListView extends ListView<FileDescription> {
    public FileDescriptionListView(String name, IModel<List<FileDescription>> descripcion) {
      super(name, descripcion);
    }
    
    protected void populateItem(ListItem<FileDescription> listItem) {
      FileDescription fileDescrip = (FileDescription)listItem.getModelObject();
      listItem.add(new Component[] { (Component)new Label("fileDesc", fileDescrip.getFileName()) });
    }
  }
  
  protected boolean getStatelessHint() {
    return true;
  }
}
