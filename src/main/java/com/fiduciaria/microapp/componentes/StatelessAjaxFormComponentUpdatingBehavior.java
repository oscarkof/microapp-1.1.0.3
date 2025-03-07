// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.componentes;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;

public abstract class StatelessAjaxFormComponentUpdatingBehavior extends AjaxFormComponentUpdatingBehavior {
   public StatelessAjaxFormComponentUpdatingBehavior(String event) {
      super(event);
   }

   public boolean getStatelessHint(Component component) {
      return true;
   }
}
