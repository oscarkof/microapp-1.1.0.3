// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.stateless.usuarios.preingreso;

import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.upload.FileDescription;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

public class FileDescriptionListView extends ListView<FileDescription> {
   public FileDescriptionListView(String name, IModel<List<FileDescription>> descripcion) {
      super(name, descripcion);
   }

   protected void populateItem(ListItem<FileDescription> listItem) {
      FileDescription fileDescrip = (FileDescription)listItem.getModelObject();
      listItem.add(new Component[]{new Label("fileDesc", fileDescrip.getFileName())});
   }
}
