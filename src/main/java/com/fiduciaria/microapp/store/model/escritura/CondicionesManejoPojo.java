// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.escritura;

import java.io.Serializable;
import java.util.Map;

public class CondicionesManejoPojo implements Serializable {
   private String numeroInternoNegocio;
   private Integer numeroFirmas;
   private String numeroEscritura;
   private String usuarioCargue;
   private Map<String, EscrituraUsuarioTipo> escrituraUsuarioTipo;
   private Map<Integer, EscrituraFirmasCondiciones> escrituraFirmasCondiciones;

   public CondicionesManejoPojo() {
   }

   public String getNumeroInternoNegocio() {
      return this.numeroInternoNegocio;
   }

   public Integer getNumeroFirmas() {
      return this.numeroFirmas;
   }

   public String getNumeroEscritura() {
      return this.numeroEscritura;
   }

   public String getUsuarioCargue() {
      return this.usuarioCargue;
   }

   public Map<String, EscrituraUsuarioTipo> getEscrituraUsuarioTipo() {
      return this.escrituraUsuarioTipo;
   }

   public Map<Integer, EscrituraFirmasCondiciones> getEscrituraFirmasCondiciones() {
      return this.escrituraFirmasCondiciones;
   }

   public void setNumeroInternoNegocio(String numeroInternoNegocio) {
      this.numeroInternoNegocio = numeroInternoNegocio;
   }

   public void setNumeroFirmas(Integer numeroFirmas) {
      this.numeroFirmas = numeroFirmas;
   }

   public void setNumeroEscritura(String numeroEscritura) {
      this.numeroEscritura = numeroEscritura;
   }

   public void setUsuarioCargue(String usuarioCargue) {
      this.usuarioCargue = usuarioCargue;
   }

   public void setEscrituraUsuarioTipo(Map<String, EscrituraUsuarioTipo> escrituraUsuarioTipo) {
      this.escrituraUsuarioTipo = escrituraUsuarioTipo;
   }

   public void setEscrituraFirmasCondiciones(Map<Integer, EscrituraFirmasCondiciones> escrituraFirmasCondiciones) {
      this.escrituraFirmasCondiciones = escrituraFirmasCondiciones;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CondicionesManejoPojo)) {
         return false;
      } else {
         CondicionesManejoPojo other = (CondicionesManejoPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$numeroFirmas = this.getNumeroFirmas();
            Object other$numeroFirmas = other.getNumeroFirmas();
            if (this$numeroFirmas == null) {
               if (other$numeroFirmas != null) {
                  return false;
               }
            } else if (!this$numeroFirmas.equals(other$numeroFirmas)) {
               return false;
            }

            Object this$numeroInternoNegocio = this.getNumeroInternoNegocio();
            Object other$numeroInternoNegocio = other.getNumeroInternoNegocio();
            if (this$numeroInternoNegocio == null) {
               if (other$numeroInternoNegocio != null) {
                  return false;
               }
            } else if (!this$numeroInternoNegocio.equals(other$numeroInternoNegocio)) {
               return false;
            }

            Object this$numeroEscritura = this.getNumeroEscritura();
            Object other$numeroEscritura = other.getNumeroEscritura();
            if (this$numeroEscritura == null) {
               if (other$numeroEscritura != null) {
                  return false;
               }
            } else if (!this$numeroEscritura.equals(other$numeroEscritura)) {
               return false;
            }

            label62: {
               Object this$usuarioCargue = this.getUsuarioCargue();
               Object other$usuarioCargue = other.getUsuarioCargue();
               if (this$usuarioCargue == null) {
                  if (other$usuarioCargue == null) {
                     break label62;
                  }
               } else if (this$usuarioCargue.equals(other$usuarioCargue)) {
                  break label62;
               }

               return false;
            }

            label55: {
               Object this$escrituraUsuarioTipo = this.getEscrituraUsuarioTipo();
               Object other$escrituraUsuarioTipo = other.getEscrituraUsuarioTipo();
               if (this$escrituraUsuarioTipo == null) {
                  if (other$escrituraUsuarioTipo == null) {
                     break label55;
                  }
               } else if (this$escrituraUsuarioTipo.equals(other$escrituraUsuarioTipo)) {
                  break label55;
               }

               return false;
            }

            Object this$escrituraFirmasCondiciones = this.getEscrituraFirmasCondiciones();
            Object other$escrituraFirmasCondiciones = other.getEscrituraFirmasCondiciones();
            if (this$escrituraFirmasCondiciones == null) {
               if (other$escrituraFirmasCondiciones != null) {
                  return false;
               }
            } else if (!this$escrituraFirmasCondiciones.equals(other$escrituraFirmasCondiciones)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CondicionesManejoPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $numeroFirmas = this.getNumeroFirmas();
      result = result * 59 + ($numeroFirmas == null ? 43 : $numeroFirmas.hashCode());
      Object $numeroInternoNegocio = this.getNumeroInternoNegocio();
      result = result * 59 + ($numeroInternoNegocio == null ? 43 : $numeroInternoNegocio.hashCode());
      Object $numeroEscritura = this.getNumeroEscritura();
      result = result * 59 + ($numeroEscritura == null ? 43 : $numeroEscritura.hashCode());
      Object $usuarioCargue = this.getUsuarioCargue();
      result = result * 59 + ($usuarioCargue == null ? 43 : $usuarioCargue.hashCode());
      Object $escrituraUsuarioTipo = this.getEscrituraUsuarioTipo();
      result = result * 59 + ($escrituraUsuarioTipo == null ? 43 : $escrituraUsuarioTipo.hashCode());
      Object $escrituraFirmasCondiciones = this.getEscrituraFirmasCondiciones();
      result = result * 59 + ($escrituraFirmasCondiciones == null ? 43 : $escrituraFirmasCondiciones.hashCode());
      return result;
   }

   public String toString() {
      return "CondicionesManejoPojo(numeroInternoNegocio=" + this.getNumeroInternoNegocio() + ", numeroFirmas=" + this.getNumeroFirmas() + ", numeroEscritura=" + this.getNumeroEscritura() + ", usuarioCargue=" + this.getUsuarioCargue() + ", escrituraUsuarioTipo=" + this.getEscrituraUsuarioTipo() + ", escrituraFirmasCondiciones=" + this.getEscrituraFirmasCondiciones() + ")";
   }
}
