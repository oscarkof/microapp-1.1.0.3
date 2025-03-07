// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.componentes;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.IAutoCompleteRenderer;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.resource.CssResourceReference;

public abstract class CustomPfAutocompleteTextField<T> extends AutoCompleteTextField<T> {
   private static final long serialVersionUID = 1L;

   public CustomPfAutocompleteTextField(String id, Class<T> type) {
      super(id, type);
   }

   public CustomPfAutocompleteTextField(String id, IModel<T> model, Class<T> type, AutoCompleteSettings settings) {
      super(id, model, type, settings);
   }

   public CustomPfAutocompleteTextField(String id, IModel<T> model, AutoCompleteSettings settings) {
      super(id, model, settings);
   }

   public CustomPfAutocompleteTextField(String id, IModel<T> model) {
      super(id, model);
   }

   public CustomPfAutocompleteTextField(String id, AutoCompleteSettings settings) {
      super(id, settings);
   }

   public CustomPfAutocompleteTextField(String id) {
      super(id);
   }

   public CustomPfAutocompleteTextField(String id, IAutoCompleteRenderer<T> renderer) {
      super(id, renderer);
   }

   public CustomPfAutocompleteTextField(String id, Class<T> type, IAutoCompleteRenderer<T> renderer) {
      super(id, type, renderer);
   }

   public CustomPfAutocompleteTextField(String id, IModel<T> model, IAutoCompleteRenderer<T> renderer) {
      super(id, model, renderer);
   }

   public CustomPfAutocompleteTextField(String id, IModel<T> model, Class<T> type, IAutoCompleteRenderer<T> renderer, AutoCompleteSettings settings) {
      super(id, model, type, renderer, settings);
   }

   public void renderHead(IHeaderResponse response) {
      super.renderHead(response);
      response.render(CssHeaderItem.forReference(new CssResourceReference(CustomPfAutocompleteTextField.class, "CustomPfAutocompleteTextField.css")));
   }
}
