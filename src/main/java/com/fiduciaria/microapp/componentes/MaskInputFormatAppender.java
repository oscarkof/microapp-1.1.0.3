// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.componentes;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;

public class MaskInputFormatAppender extends Behavior {
   private static final PackageResourceReference autoInputMask = new PackageResourceReference(MaskInputFormatAppender.class, "cleave.js");
   private String options;

   public MaskInputFormatAppender(String options) {
      if (options != null && !options.isEmpty()) {
         this.options = options;
      } else {
         throw new NullPointerException("No options");
      }
   }

   public void renderHead(Component component, IHeaderResponse response) {
      response.render(JavaScriptHeaderItem.forReference(autoInputMask));
      String initScript = "MaskInputFormatListener = {  maskInputFormat: function (elementId) { try { elementId = \"#\" + elementId; new Cleave(elementId, { " + this.options + "}); } catch(err){ console.log(' MaskInputFormatAppender ERROR  '+err); }finally{} }}; ;MaskInputFormatListener.maskInputFormat('" + component.getMarkupId() + "');";
      response.render(OnLoadHeaderItem.forScript(initScript));
   }

   public static MaskInputFormatAppender numeral(int numeralIntegerScale, int numeralDecimalScale, String decimalMark, boolean numeralPositiveOnly) {
      String delimiter;
      if (decimalMark.equalsIgnoreCase(",")) {
         delimiter = ".";
      } else {
         delimiter = ",";
      }

      return new MaskInputFormatAppender("numeral: true,numeralThousandsGroupStyle:'thousand',numeralIntegerScale: " + numeralIntegerScale + ",numeralDecimalMark:'" + decimalMark + "',numeralDecimalScale: " + numeralDecimalScale + ",delimiter: '" + delimiter + "',numeralPositiveOnly:" + numeralPositiveOnly);
   }

   public static MaskInputFormatAppender numeral() {
      return numeral(22, 2, ",", false);
   }

   public static MaskInputFormatAppender fecha(String patronFecha, String separador) {
      if (separador == null || separador.isEmpty()) {
         separador = "/";
      }

      if (patronFecha == null || patronFecha.isEmpty()) {
         patronFecha = "['Y', 'm', 'd']";
      }

      return new MaskInputFormatAppender("date:true,datePattern:" + patronFecha + ",delimiter: '" + separador + "'");
   }

   public static MaskInputFormatAppender fecha(String patronFecha, String separador, String dateMin, String dateMax) {
      if (separador == null || separador.isEmpty()) {
         separador = "/";
      }

      if (patronFecha == null || patronFecha.isEmpty()) {
         patronFecha = "['Y', 'm', 'd']";
      }

      return new MaskInputFormatAppender("date:true,datePattern:" + patronFecha + ",delimiter: '" + separador + "',dateMin: '" + dateMin + "',dateMax: '" + dateMax + "'");
   }

   public static MaskInputFormatAppender fecha() {
      return fecha("['Y', 'm', 'd']", "/");
   }

   public static MaskInputFormatAppender general(String bloques, String delimitador, String delimitadores, boolean mayusculas, boolean minusculas, boolean soloNumeros) {
      if (delimitadores != null && !delimitadores.isEmpty()) {
         delimitadores = ",delimiters:" + delimitadores;
      } else {
         delimitadores = "";
      }

      return new MaskInputFormatAppender("blocks:" + bloques + ",delimiter:'" + delimitador + "'" + delimitadores + ",numericOnly:" + soloNumeros + ",uppercase:" + mayusculas + ",lowercase:" + minusculas);
   }
}
