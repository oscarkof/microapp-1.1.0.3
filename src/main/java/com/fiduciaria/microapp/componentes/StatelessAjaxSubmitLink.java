// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.componentes;

import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;

public class StatelessAjaxSubmitLink extends AjaxSubmitLink {
   public StatelessAjaxSubmitLink(String id) {
      super(id);
   }

   public StatelessAjaxSubmitLink(String id, Form<?> form) {
      super(id, form);
   }

   protected boolean getStatelessHint() {
      return true;
   }
}
