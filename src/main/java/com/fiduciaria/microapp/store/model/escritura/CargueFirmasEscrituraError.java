// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.escritura;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

public class CargueFirmasEscrituraError implements Serializable {
   private String codigoTipoFideicomiso;
   private String codigoSubtipoFideicomiso;
   private String codigoFideicomiso;
   private String numeroEscrit;
   private String codTipoCondicion;
   private String secCondicion;
   private String secuencialError;
   private String mensajeError;
   private String loginUsuario;
   @JsonIgnore
   private boolean alertaExitosa;

   public CargueFirmasEscrituraError() {
   }

   public String getCodigoTipoFideicomiso() {
      return this.codigoTipoFideicomiso;
   }

   public String getCodigoSubtipoFideicomiso() {
      return this.codigoSubtipoFideicomiso;
   }

   public String getCodigoFideicomiso() {
      return this.codigoFideicomiso;
   }

   public String getNumeroEscrit() {
      return this.numeroEscrit;
   }

   public String getCodTipoCondicion() {
      return this.codTipoCondicion;
   }

   public String getSecCondicion() {
      return this.secCondicion;
   }

   public String getSecuencialError() {
      return this.secuencialError;
   }

   public String getMensajeError() {
      return this.mensajeError;
   }

   public String getLoginUsuario() {
      return this.loginUsuario;
   }

   public boolean isAlertaExitosa() {
      return this.alertaExitosa;
   }

   public void setCodigoTipoFideicomiso(String codigoTipoFideicomiso) {
      this.codigoTipoFideicomiso = codigoTipoFideicomiso;
   }

   public void setCodigoSubtipoFideicomiso(String codigoSubtipoFideicomiso) {
      this.codigoSubtipoFideicomiso = codigoSubtipoFideicomiso;
   }

   public void setCodigoFideicomiso(String codigoFideicomiso) {
      this.codigoFideicomiso = codigoFideicomiso;
   }

   public void setNumeroEscrit(String numeroEscrit) {
      this.numeroEscrit = numeroEscrit;
   }

   public void setCodTipoCondicion(String codTipoCondicion) {
      this.codTipoCondicion = codTipoCondicion;
   }

   public void setSecCondicion(String secCondicion) {
      this.secCondicion = secCondicion;
   }

   public void setSecuencialError(String secuencialError) {
      this.secuencialError = secuencialError;
   }

   public void setMensajeError(String mensajeError) {
      this.mensajeError = mensajeError;
   }

   public void setLoginUsuario(String loginUsuario) {
      this.loginUsuario = loginUsuario;
   }

   @JsonIgnore
   public void setAlertaExitosa(boolean alertaExitosa) {
      this.alertaExitosa = alertaExitosa;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CargueFirmasEscrituraError)) {
         return false;
      } else {
         CargueFirmasEscrituraError other = (CargueFirmasEscrituraError)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isAlertaExitosa() != other.isAlertaExitosa()) {
            return false;
         } else {
            label121: {
               Object this$codigoTipoFideicomiso = this.getCodigoTipoFideicomiso();
               Object other$codigoTipoFideicomiso = other.getCodigoTipoFideicomiso();
               if (this$codigoTipoFideicomiso == null) {
                  if (other$codigoTipoFideicomiso == null) {
                     break label121;
                  }
               } else if (this$codigoTipoFideicomiso.equals(other$codigoTipoFideicomiso)) {
                  break label121;
               }

               return false;
            }

            Object this$codigoSubtipoFideicomiso = this.getCodigoSubtipoFideicomiso();
            Object other$codigoSubtipoFideicomiso = other.getCodigoSubtipoFideicomiso();
            if (this$codigoSubtipoFideicomiso == null) {
               if (other$codigoSubtipoFideicomiso != null) {
                  return false;
               }
            } else if (!this$codigoSubtipoFideicomiso.equals(other$codigoSubtipoFideicomiso)) {
               return false;
            }

            label107: {
               Object this$codigoFideicomiso = this.getCodigoFideicomiso();
               Object other$codigoFideicomiso = other.getCodigoFideicomiso();
               if (this$codigoFideicomiso == null) {
                  if (other$codigoFideicomiso == null) {
                     break label107;
                  }
               } else if (this$codigoFideicomiso.equals(other$codigoFideicomiso)) {
                  break label107;
               }

               return false;
            }

            Object this$numeroEscrit = this.getNumeroEscrit();
            Object other$numeroEscrit = other.getNumeroEscrit();
            if (this$numeroEscrit == null) {
               if (other$numeroEscrit != null) {
                  return false;
               }
            } else if (!this$numeroEscrit.equals(other$numeroEscrit)) {
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

            label86: {
               Object this$secCondicion = this.getSecCondicion();
               Object other$secCondicion = other.getSecCondicion();
               if (this$secCondicion == null) {
                  if (other$secCondicion == null) {
                     break label86;
                  }
               } else if (this$secCondicion.equals(other$secCondicion)) {
                  break label86;
               }

               return false;
            }

            label79: {
               Object this$secuencialError = this.getSecuencialError();
               Object other$secuencialError = other.getSecuencialError();
               if (this$secuencialError == null) {
                  if (other$secuencialError == null) {
                     break label79;
                  }
               } else if (this$secuencialError.equals(other$secuencialError)) {
                  break label79;
               }

               return false;
            }

            Object this$mensajeError = this.getMensajeError();
            Object other$mensajeError = other.getMensajeError();
            if (this$mensajeError == null) {
               if (other$mensajeError != null) {
                  return false;
               }
            } else if (!this$mensajeError.equals(other$mensajeError)) {
               return false;
            }

            Object this$loginUsuario = this.getLoginUsuario();
            Object other$loginUsuario = other.getLoginUsuario();
            if (this$loginUsuario == null) {
               if (other$loginUsuario != null) {
                  return false;
               }
            } else if (!this$loginUsuario.equals(other$loginUsuario)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CargueFirmasEscrituraError;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + (this.isAlertaExitosa() ? 79 : 97);
      Object $codigoTipoFideicomiso = this.getCodigoTipoFideicomiso();
      result = result * 59 + ($codigoTipoFideicomiso == null ? 43 : $codigoTipoFideicomiso.hashCode());
      Object $codigoSubtipoFideicomiso = this.getCodigoSubtipoFideicomiso();
      result = result * 59 + ($codigoSubtipoFideicomiso == null ? 43 : $codigoSubtipoFideicomiso.hashCode());
      Object $codigoFideicomiso = this.getCodigoFideicomiso();
      result = result * 59 + ($codigoFideicomiso == null ? 43 : $codigoFideicomiso.hashCode());
      Object $numeroEscrit = this.getNumeroEscrit();
      result = result * 59 + ($numeroEscrit == null ? 43 : $numeroEscrit.hashCode());
      Object $codTipoCondicion = this.getCodTipoCondicion();
      result = result * 59 + ($codTipoCondicion == null ? 43 : $codTipoCondicion.hashCode());
      Object $secCondicion = this.getSecCondicion();
      result = result * 59 + ($secCondicion == null ? 43 : $secCondicion.hashCode());
      Object $secuencialError = this.getSecuencialError();
      result = result * 59 + ($secuencialError == null ? 43 : $secuencialError.hashCode());
      Object $mensajeError = this.getMensajeError();
      result = result * 59 + ($mensajeError == null ? 43 : $mensajeError.hashCode());
      Object $loginUsuario = this.getLoginUsuario();
      result = result * 59 + ($loginUsuario == null ? 43 : $loginUsuario.hashCode());
      return result;
   }

   public String toString() {
      return "CargueFirmasEscrituraError(codigoTipoFideicomiso=" + this.getCodigoTipoFideicomiso() + ", codigoSubtipoFideicomiso=" + this.getCodigoSubtipoFideicomiso() + ", codigoFideicomiso=" + this.getCodigoFideicomiso() + ", numeroEscrit=" + this.getNumeroEscrit() + ", codTipoCondicion=" + this.getCodTipoCondicion() + ", secCondicion=" + this.getSecCondicion() + ", secuencialError=" + this.getSecuencialError() + ", mensajeError=" + this.getMensajeError() + ", loginUsuario=" + this.getLoginUsuario() + ", alertaExitosa=" + this.isAlertaExitosa() + ")";
   }
}
