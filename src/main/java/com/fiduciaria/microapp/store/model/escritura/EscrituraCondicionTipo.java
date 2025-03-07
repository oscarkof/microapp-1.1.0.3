// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.escritura;

import java.io.Serializable;

public class EscrituraCondicionTipo implements Serializable {
   private String codTipoCondicion;
   private String secCondicion;
   private String tipo;
   private Integer numFirmas;
   private String usuarioCargue;

   public EscrituraCondicionTipo() {
   }

   public String getCodTipoCondicion() {
      return this.codTipoCondicion;
   }

   public String getSecCondicion() {
      return this.secCondicion;
   }

   public String getTipo() {
      return this.tipo;
   }

   public Integer getNumFirmas() {
      return this.numFirmas;
   }

   public String getUsuarioCargue() {
      return this.usuarioCargue;
   }

   public void setCodTipoCondicion(String codTipoCondicion) {
      this.codTipoCondicion = codTipoCondicion;
   }

   public void setSecCondicion(String secCondicion) {
      this.secCondicion = secCondicion;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
   }

   public void setNumFirmas(Integer numFirmas) {
      this.numFirmas = numFirmas;
   }

   public void setUsuarioCargue(String usuarioCargue) {
      this.usuarioCargue = usuarioCargue;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EscrituraCondicionTipo)) {
         return false;
      } else {
         EscrituraCondicionTipo other = (EscrituraCondicionTipo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label71: {
               Object this$numFirmas = this.getNumFirmas();
               Object other$numFirmas = other.getNumFirmas();
               if (this$numFirmas == null) {
                  if (other$numFirmas == null) {
                     break label71;
                  }
               } else if (this$numFirmas.equals(other$numFirmas)) {
                  break label71;
               }

               return false;
            }

            Object this$codTipoCondicion = this.getCodTipoCondicion();
            Object other$codTipoCondicion = other.getCodTipoCondicion();
            if (this$codTipoCondicion == null) {
               if (other$codTipoCondicion != null) {
                  return false;
               }
            } else if (!this$codTipoCondicion.equals(other$codTipoCondicion)) {
               return false;
            }

            label57: {
               Object this$secCondicion = this.getSecCondicion();
               Object other$secCondicion = other.getSecCondicion();
               if (this$secCondicion == null) {
                  if (other$secCondicion == null) {
                     break label57;
                  }
               } else if (this$secCondicion.equals(other$secCondicion)) {
                  break label57;
               }

               return false;
            }

            Object this$tipo = this.getTipo();
            Object other$tipo = other.getTipo();
            if (this$tipo == null) {
               if (other$tipo != null) {
                  return false;
               }
            } else if (!this$tipo.equals(other$tipo)) {
               return false;
            }

            Object this$usuarioCargue = this.getUsuarioCargue();
            Object other$usuarioCargue = other.getUsuarioCargue();
            if (this$usuarioCargue == null) {
               if (other$usuarioCargue == null) {
                  return true;
               }
            } else if (this$usuarioCargue.equals(other$usuarioCargue)) {
               return true;
            }

            return false;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EscrituraCondicionTipo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $numFirmas = this.getNumFirmas();
      result = result * 59 + ($numFirmas == null ? 43 : $numFirmas.hashCode());
      Object $codTipoCondicion = this.getCodTipoCondicion();
      result = result * 59 + ($codTipoCondicion == null ? 43 : $codTipoCondicion.hashCode());
      Object $secCondicion = this.getSecCondicion();
      result = result * 59 + ($secCondicion == null ? 43 : $secCondicion.hashCode());
      Object $tipo = this.getTipo();
      result = result * 59 + ($tipo == null ? 43 : $tipo.hashCode());
      Object $usuarioCargue = this.getUsuarioCargue();
      result = result * 59 + ($usuarioCargue == null ? 43 : $usuarioCargue.hashCode());
      return result;
   }

   public String toString() {
      return "EscrituraCondicionTipo(codTipoCondicion=" + this.getCodTipoCondicion() + ", secCondicion=" + this.getSecCondicion() + ", tipo=" + this.getTipo() + ", numFirmas=" + this.getNumFirmas() + ", usuarioCargue=" + this.getUsuarioCargue() + ")";
   }
}
