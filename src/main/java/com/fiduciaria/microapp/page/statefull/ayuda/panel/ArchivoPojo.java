// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.ayuda.panel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.wicket.util.io.IClusterable;

public class ArchivoPojo implements IClusterable {
   private String identificador;
   private String idx;
   private String nombre;
   private String resumen;
   private String localizacion;

   @JsonIgnore
   public void clear() {
      this.identificador = null;
      this.idx = null;
      this.nombre = null;
      this.resumen = null;
      this.localizacion = null;
   }

   public ArchivoPojo() {
   }

   public String getIdentificador() {
      return this.identificador;
   }

   public String getIdx() {
      return this.idx;
   }

   public String getNombre() {
      return this.nombre;
   }

   public String getResumen() {
      return this.resumen;
   }

   public String getLocalizacion() {
      return this.localizacion;
   }

   public void setIdentificador(String identificador) {
      this.identificador = identificador;
   }

   public void setIdx(String idx) {
      this.idx = idx;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public void setResumen(String resumen) {
      this.resumen = resumen;
   }

   public void setLocalizacion(String localizacion) {
      this.localizacion = localizacion;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ArchivoPojo)) {
         return false;
      } else {
         ArchivoPojo other = (ArchivoPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label71: {
               Object this$identificador = this.getIdentificador();
               Object other$identificador = other.getIdentificador();
               if (this$identificador == null) {
                  if (other$identificador == null) {
                     break label71;
                  }
               } else if (this$identificador.equals(other$identificador)) {
                  break label71;
               }

               return false;
            }

            Object this$idx = this.getIdx();
            Object other$idx = other.getIdx();
            if (this$idx == null) {
               if (other$idx != null) {
                  return false;
               }
            } else if (!this$idx.equals(other$idx)) {
               return false;
            }

            label57: {
               Object this$nombre = this.getNombre();
               Object other$nombre = other.getNombre();
               if (this$nombre == null) {
                  if (other$nombre == null) {
                     break label57;
                  }
               } else if (this$nombre.equals(other$nombre)) {
                  break label57;
               }

               return false;
            }

            Object this$resumen = this.getResumen();
            Object other$resumen = other.getResumen();
            if (this$resumen == null) {
               if (other$resumen != null) {
                  return false;
               }
            } else if (!this$resumen.equals(other$resumen)) {
               return false;
            }

            Object this$localizacion = this.getLocalizacion();
            Object other$localizacion = other.getLocalizacion();
            if (this$localizacion == null) {
               if (other$localizacion == null) {
                  return true;
               }
            } else if (this$localizacion.equals(other$localizacion)) {
               return true;
            }

            return false;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ArchivoPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $identificador = this.getIdentificador();
      result = result * 59 + ($identificador == null ? 43 : $identificador.hashCode());
      Object $idx = this.getIdx();
      result = result * 59 + ($idx == null ? 43 : $idx.hashCode());
      Object $nombre = this.getNombre();
      result = result * 59 + ($nombre == null ? 43 : $nombre.hashCode());
      Object $resumen = this.getResumen();
      result = result * 59 + ($resumen == null ? 43 : $resumen.hashCode());
      Object $localizacion = this.getLocalizacion();
      result = result * 59 + ($localizacion == null ? 43 : $localizacion.hashCode());
      return result;
   }

   public String toString() {
      return "ArchivoPojo(identificador=" + this.getIdentificador() + ", idx=" + this.getIdx() + ", nombre=" + this.getNombre() + ", resumen=" + this.getResumen() + ", localizacion=" + this.getLocalizacion() + ")";
   }
}
