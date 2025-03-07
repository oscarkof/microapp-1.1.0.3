// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.gui.solicitud;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import org.apache.wicket.util.io.IClusterable;

public class FormularioSoportePojo implements IClusterable {
   private boolean aceptacionPrerrequisitos;
   private boolean aceptacionCreacionUsuarioAutomatica;
   @JsonIgnore
   private NegocioPojo negocio;
   private TipoFormulario tipoFormulario;
   private String correoFormulario;
   private PreIngresoDatosUsuarioPojo preingresodatosusuario;
   private SoporteTecnico soporteTecnico;

   public FormularioSoportePojo() {
   }

   public boolean isAceptacionPrerrequisitos() {
      return this.aceptacionPrerrequisitos;
   }

   public boolean isAceptacionCreacionUsuarioAutomatica() {
      return this.aceptacionCreacionUsuarioAutomatica;
   }

   public NegocioPojo getNegocio() {
      return this.negocio;
   }

   public TipoFormulario getTipoFormulario() {
      return this.tipoFormulario;
   }

   public String getCorreoFormulario() {
      return this.correoFormulario;
   }

   public PreIngresoDatosUsuarioPojo getPreingresodatosusuario() {
      return this.preingresodatosusuario;
   }

   public SoporteTecnico getSoporteTecnico() {
      return this.soporteTecnico;
   }

   public void setAceptacionPrerrequisitos(boolean aceptacionPrerrequisitos) {
      this.aceptacionPrerrequisitos = aceptacionPrerrequisitos;
   }

   public void setAceptacionCreacionUsuarioAutomatica(boolean aceptacionCreacionUsuarioAutomatica) {
      this.aceptacionCreacionUsuarioAutomatica = aceptacionCreacionUsuarioAutomatica;
   }

   @JsonIgnore
   public void setNegocio(NegocioPojo negocio) {
      this.negocio = negocio;
   }

   public void setTipoFormulario(TipoFormulario tipoFormulario) {
      this.tipoFormulario = tipoFormulario;
   }

   public void setCorreoFormulario(String correoFormulario) {
      this.correoFormulario = correoFormulario;
   }

   public void setPreingresodatosusuario(PreIngresoDatosUsuarioPojo preingresodatosusuario) {
      this.preingresodatosusuario = preingresodatosusuario;
   }

   public void setSoporteTecnico(SoporteTecnico soporteTecnico) {
      this.soporteTecnico = soporteTecnico;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FormularioSoportePojo)) {
         return false;
      } else {
         FormularioSoportePojo other = (FormularioSoportePojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isAceptacionPrerrequisitos() != other.isAceptacionPrerrequisitos()) {
            return false;
         } else if (this.isAceptacionCreacionUsuarioAutomatica() != other.isAceptacionCreacionUsuarioAutomatica()) {
            return false;
         } else {
            label76: {
               Object this$negocio = this.getNegocio();
               Object other$negocio = other.getNegocio();
               if (this$negocio == null) {
                  if (other$negocio == null) {
                     break label76;
                  }
               } else if (this$negocio.equals(other$negocio)) {
                  break label76;
               }

               return false;
            }

            Object this$tipoFormulario = this.getTipoFormulario();
            Object other$tipoFormulario = other.getTipoFormulario();
            if (this$tipoFormulario == null) {
               if (other$tipoFormulario != null) {
                  return false;
               }
            } else if (!this$tipoFormulario.equals(other$tipoFormulario)) {
               return false;
            }

            label62: {
               Object this$correoFormulario = this.getCorreoFormulario();
               Object other$correoFormulario = other.getCorreoFormulario();
               if (this$correoFormulario == null) {
                  if (other$correoFormulario == null) {
                     break label62;
                  }
               } else if (this$correoFormulario.equals(other$correoFormulario)) {
                  break label62;
               }

               return false;
            }

            label55: {
               Object this$preingresodatosusuario = this.getPreingresodatosusuario();
               Object other$preingresodatosusuario = other.getPreingresodatosusuario();
               if (this$preingresodatosusuario == null) {
                  if (other$preingresodatosusuario == null) {
                     break label55;
                  }
               } else if (this$preingresodatosusuario.equals(other$preingresodatosusuario)) {
                  break label55;
               }

               return false;
            }

            Object this$soporteTecnico = this.getSoporteTecnico();
            Object other$soporteTecnico = other.getSoporteTecnico();
            if (this$soporteTecnico == null) {
               if (other$soporteTecnico != null) {
                  return false;
               }
            } else if (!this$soporteTecnico.equals(other$soporteTecnico)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof FormularioSoportePojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + (this.isAceptacionPrerrequisitos() ? 79 : 97);
      result = result * 59 + (this.isAceptacionCreacionUsuarioAutomatica() ? 79 : 97);
      Object $negocio = this.getNegocio();
      result = result * 59 + ($negocio == null ? 43 : $negocio.hashCode());
      Object $tipoFormulario = this.getTipoFormulario();
      result = result * 59 + ($tipoFormulario == null ? 43 : $tipoFormulario.hashCode());
      Object $correoFormulario = this.getCorreoFormulario();
      result = result * 59 + ($correoFormulario == null ? 43 : $correoFormulario.hashCode());
      Object $preingresodatosusuario = this.getPreingresodatosusuario();
      result = result * 59 + ($preingresodatosusuario == null ? 43 : $preingresodatosusuario.hashCode());
      Object $soporteTecnico = this.getSoporteTecnico();
      result = result * 59 + ($soporteTecnico == null ? 43 : $soporteTecnico.hashCode());
      return result;
   }

   public String toString() {
      return "FormularioSoportePojo(aceptacionPrerrequisitos=" + this.isAceptacionPrerrequisitos() + ", aceptacionCreacionUsuarioAutomatica=" + this.isAceptacionCreacionUsuarioAutomatica() + ", negocio=" + this.getNegocio() + ", tipoFormulario=" + this.getTipoFormulario() + ", correoFormulario=" + this.getCorreoFormulario() + ", preingresodatosusuario=" + this.getPreingresodatosusuario() + ", soporteTecnico=" + this.getSoporteTecnico() + ")";
   }
}
