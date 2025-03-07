// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.basicas;

import org.apache.wicket.util.io.IClusterable;

public class TipoDocumentoPojo implements IClusterable {
   private String clasedocumento;
   private String descripciondocumento;

   public TipoDocumentoPojo() {
   }

   public String getClasedocumento() {
      return this.clasedocumento;
   }

   public String getDescripciondocumento() {
      return this.descripciondocumento;
   }

   public void setClasedocumento(String clasedocumento) {
      this.clasedocumento = clasedocumento;
   }

   public void setDescripciondocumento(String descripciondocumento) {
      this.descripciondocumento = descripciondocumento;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TipoDocumentoPojo)) {
         return false;
      } else {
         TipoDocumentoPojo other = (TipoDocumentoPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$clasedocumento = this.getClasedocumento();
            Object other$clasedocumento = other.getClasedocumento();
            if (this$clasedocumento == null) {
               if (other$clasedocumento != null) {
                  return false;
               }
            } else if (!this$clasedocumento.equals(other$clasedocumento)) {
               return false;
            }

            Object this$descripciondocumento = this.getDescripciondocumento();
            Object other$descripciondocumento = other.getDescripciondocumento();
            if (this$descripciondocumento == null) {
               if (other$descripciondocumento != null) {
                  return false;
               }
            } else if (!this$descripciondocumento.equals(other$descripciondocumento)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof TipoDocumentoPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $clasedocumento = this.getClasedocumento();
      result = result * 59 + ($clasedocumento == null ? 43 : $clasedocumento.hashCode());
      Object $descripciondocumento = this.getDescripciondocumento();
      result = result * 59 + ($descripciondocumento == null ? 43 : $descripciondocumento.hashCode());
      return result;
   }

   public String toString() {
      return "TipoDocumentoPojo(clasedocumento=" + this.getClasedocumento() + ", descripciondocumento=" + this.getDescripciondocumento() + ")";
   }
}
