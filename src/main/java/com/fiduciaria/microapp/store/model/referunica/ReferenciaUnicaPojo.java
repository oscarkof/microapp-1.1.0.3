// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.referunica;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.wicket.util.io.IClusterable;

public class ReferenciaUnicaPojo implements IClusterable {
   private String numeroReferencia;
   private String descripcionReferencia;
   private String tipoIdentificacion;
   private String identificacion;
   private String digitosVerificacion;
   private String nombresRazonSocial;
   private String correoElectronico;
   private String usuarioCrea;
   private String datetimeCreacion;
   private String datetimeUpdate;
   private String estado;
   private String tipoReferencia;
   private String referenciaPadre;
   private String numeroFirmasAutorizar;

   @JsonIgnore
   public void copiarDesde(ReferenciaUnicaPojo referencia) {
      this.numeroReferencia = referencia.getNumeroReferencia();
      this.descripcionReferencia = referencia.getDescripcionReferencia();
      this.tipoIdentificacion = referencia.getTipoIdentificacion();
      this.identificacion = referencia.getIdentificacion();
      this.digitosVerificacion = referencia.getDigitosVerificacion();
      this.nombresRazonSocial = referencia.getNombresRazonSocial();
      this.correoElectronico = referencia.getCorreoElectronico();
      this.usuarioCrea = referencia.getUsuarioCrea();
      this.datetimeCreacion = referencia.getDatetimeCreacion();
      this.datetimeUpdate = referencia.getDatetimeUpdate();
      this.estado = referencia.getEstado();
      this.tipoReferencia = referencia.getTipoReferencia();
      this.referenciaPadre = referencia.getReferenciaPadre();
      this.numeroFirmasAutorizar = referencia.getNumeroFirmasAutorizar();
   }

   @JsonIgnore
   public void limpiar() {
      this.numeroReferencia = null;
      this.descripcionReferencia = null;
      this.tipoIdentificacion = null;
      this.identificacion = null;
      this.digitosVerificacion = null;
      this.nombresRazonSocial = null;
      this.correoElectronico = null;
      this.usuarioCrea = null;
      this.datetimeCreacion = null;
      this.datetimeUpdate = null;
      this.estado = null;
      this.tipoReferencia = null;
      this.referenciaPadre = null;
      this.numeroFirmasAutorizar = null;
   }

   public ReferenciaUnicaPojo() {
   }

   public String getNumeroReferencia() {
      return this.numeroReferencia;
   }

   public String getDescripcionReferencia() {
      return this.descripcionReferencia;
   }

   public String getTipoIdentificacion() {
      return this.tipoIdentificacion;
   }

   public String getIdentificacion() {
      return this.identificacion;
   }

   public String getDigitosVerificacion() {
      return this.digitosVerificacion;
   }

   public String getNombresRazonSocial() {
      return this.nombresRazonSocial;
   }

   public String getCorreoElectronico() {
      return this.correoElectronico;
   }

   public String getUsuarioCrea() {
      return this.usuarioCrea;
   }

   public String getDatetimeCreacion() {
      return this.datetimeCreacion;
   }

   public String getDatetimeUpdate() {
      return this.datetimeUpdate;
   }

   public String getEstado() {
      return this.estado;
   }

   public String getTipoReferencia() {
      return this.tipoReferencia;
   }

   public String getReferenciaPadre() {
      return this.referenciaPadre;
   }

   public String getNumeroFirmasAutorizar() {
      return this.numeroFirmasAutorizar;
   }

   public void setNumeroReferencia(String numeroReferencia) {
      this.numeroReferencia = numeroReferencia;
   }

   public void setDescripcionReferencia(String descripcionReferencia) {
      this.descripcionReferencia = descripcionReferencia;
   }

   public void setTipoIdentificacion(String tipoIdentificacion) {
      this.tipoIdentificacion = tipoIdentificacion;
   }

   public void setIdentificacion(String identificacion) {
      this.identificacion = identificacion;
   }

   public void setDigitosVerificacion(String digitosVerificacion) {
      this.digitosVerificacion = digitosVerificacion;
   }

   public void setNombresRazonSocial(String nombresRazonSocial) {
      this.nombresRazonSocial = nombresRazonSocial;
   }

   public void setCorreoElectronico(String correoElectronico) {
      this.correoElectronico = correoElectronico;
   }

   public void setUsuarioCrea(String usuarioCrea) {
      this.usuarioCrea = usuarioCrea;
   }

   public void setDatetimeCreacion(String datetimeCreacion) {
      this.datetimeCreacion = datetimeCreacion;
   }

   public void setDatetimeUpdate(String datetimeUpdate) {
      this.datetimeUpdate = datetimeUpdate;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   public void setTipoReferencia(String tipoReferencia) {
      this.tipoReferencia = tipoReferencia;
   }

   public void setReferenciaPadre(String referenciaPadre) {
      this.referenciaPadre = referenciaPadre;
   }

   public void setNumeroFirmasAutorizar(String numeroFirmasAutorizar) {
      this.numeroFirmasAutorizar = numeroFirmasAutorizar;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ReferenciaUnicaPojo)) {
         return false;
      } else {
         ReferenciaUnicaPojo other = (ReferenciaUnicaPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$numeroReferencia = this.getNumeroReferencia();
            Object other$numeroReferencia = other.getNumeroReferencia();
            if (this$numeroReferencia == null) {
               if (other$numeroReferencia != null) {
                  return false;
               }
            } else if (!this$numeroReferencia.equals(other$numeroReferencia)) {
               return false;
            }

            Object this$descripcionReferencia = this.getDescripcionReferencia();
            Object other$descripcionReferencia = other.getDescripcionReferencia();
            if (this$descripcionReferencia == null) {
               if (other$descripcionReferencia != null) {
                  return false;
               }
            } else if (!this$descripcionReferencia.equals(other$descripcionReferencia)) {
               return false;
            }

            Object this$tipoIdentificacion = this.getTipoIdentificacion();
            Object other$tipoIdentificacion = other.getTipoIdentificacion();
            if (this$tipoIdentificacion == null) {
               if (other$tipoIdentificacion != null) {
                  return false;
               }
            } else if (!this$tipoIdentificacion.equals(other$tipoIdentificacion)) {
               return false;
            }

            label158: {
               Object this$identificacion = this.getIdentificacion();
               Object other$identificacion = other.getIdentificacion();
               if (this$identificacion == null) {
                  if (other$identificacion == null) {
                     break label158;
                  }
               } else if (this$identificacion.equals(other$identificacion)) {
                  break label158;
               }

               return false;
            }

            label151: {
               Object this$digitosVerificacion = this.getDigitosVerificacion();
               Object other$digitosVerificacion = other.getDigitosVerificacion();
               if (this$digitosVerificacion == null) {
                  if (other$digitosVerificacion == null) {
                     break label151;
                  }
               } else if (this$digitosVerificacion.equals(other$digitosVerificacion)) {
                  break label151;
               }

               return false;
            }

            Object this$nombresRazonSocial = this.getNombresRazonSocial();
            Object other$nombresRazonSocial = other.getNombresRazonSocial();
            if (this$nombresRazonSocial == null) {
               if (other$nombresRazonSocial != null) {
                  return false;
               }
            } else if (!this$nombresRazonSocial.equals(other$nombresRazonSocial)) {
               return false;
            }

            label137: {
               Object this$correoElectronico = this.getCorreoElectronico();
               Object other$correoElectronico = other.getCorreoElectronico();
               if (this$correoElectronico == null) {
                  if (other$correoElectronico == null) {
                     break label137;
                  }
               } else if (this$correoElectronico.equals(other$correoElectronico)) {
                  break label137;
               }

               return false;
            }

            label130: {
               Object this$usuarioCrea = this.getUsuarioCrea();
               Object other$usuarioCrea = other.getUsuarioCrea();
               if (this$usuarioCrea == null) {
                  if (other$usuarioCrea == null) {
                     break label130;
                  }
               } else if (this$usuarioCrea.equals(other$usuarioCrea)) {
                  break label130;
               }

               return false;
            }

            Object this$datetimeCreacion = this.getDatetimeCreacion();
            Object other$datetimeCreacion = other.getDatetimeCreacion();
            if (this$datetimeCreacion == null) {
               if (other$datetimeCreacion != null) {
                  return false;
               }
            } else if (!this$datetimeCreacion.equals(other$datetimeCreacion)) {
               return false;
            }

            Object this$datetimeUpdate = this.getDatetimeUpdate();
            Object other$datetimeUpdate = other.getDatetimeUpdate();
            if (this$datetimeUpdate == null) {
               if (other$datetimeUpdate != null) {
                  return false;
               }
            } else if (!this$datetimeUpdate.equals(other$datetimeUpdate)) {
               return false;
            }

            label109: {
               Object this$estado = this.getEstado();
               Object other$estado = other.getEstado();
               if (this$estado == null) {
                  if (other$estado == null) {
                     break label109;
                  }
               } else if (this$estado.equals(other$estado)) {
                  break label109;
               }

               return false;
            }

            label102: {
               Object this$tipoReferencia = this.getTipoReferencia();
               Object other$tipoReferencia = other.getTipoReferencia();
               if (this$tipoReferencia == null) {
                  if (other$tipoReferencia == null) {
                     break label102;
                  }
               } else if (this$tipoReferencia.equals(other$tipoReferencia)) {
                  break label102;
               }

               return false;
            }

            Object this$referenciaPadre = this.getReferenciaPadre();
            Object other$referenciaPadre = other.getReferenciaPadre();
            if (this$referenciaPadre == null) {
               if (other$referenciaPadre != null) {
                  return false;
               }
            } else if (!this$referenciaPadre.equals(other$referenciaPadre)) {
               return false;
            }

            Object this$numeroFirmasAutorizar = this.getNumeroFirmasAutorizar();
            Object other$numeroFirmasAutorizar = other.getNumeroFirmasAutorizar();
            if (this$numeroFirmasAutorizar == null) {
               if (other$numeroFirmasAutorizar != null) {
                  return false;
               }
            } else if (!this$numeroFirmasAutorizar.equals(other$numeroFirmasAutorizar)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ReferenciaUnicaPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $numeroReferencia = this.getNumeroReferencia();
      result = result * 59 + ($numeroReferencia == null ? 43 : $numeroReferencia.hashCode());
      Object $descripcionReferencia = this.getDescripcionReferencia();
      result = result * 59 + ($descripcionReferencia == null ? 43 : $descripcionReferencia.hashCode());
      Object $tipoIdentificacion = this.getTipoIdentificacion();
      result = result * 59 + ($tipoIdentificacion == null ? 43 : $tipoIdentificacion.hashCode());
      Object $identificacion = this.getIdentificacion();
      result = result * 59 + ($identificacion == null ? 43 : $identificacion.hashCode());
      Object $digitosVerificacion = this.getDigitosVerificacion();
      result = result * 59 + ($digitosVerificacion == null ? 43 : $digitosVerificacion.hashCode());
      Object $nombresRazonSocial = this.getNombresRazonSocial();
      result = result * 59 + ($nombresRazonSocial == null ? 43 : $nombresRazonSocial.hashCode());
      Object $correoElectronico = this.getCorreoElectronico();
      result = result * 59 + ($correoElectronico == null ? 43 : $correoElectronico.hashCode());
      Object $usuarioCrea = this.getUsuarioCrea();
      result = result * 59 + ($usuarioCrea == null ? 43 : $usuarioCrea.hashCode());
      Object $datetimeCreacion = this.getDatetimeCreacion();
      result = result * 59 + ($datetimeCreacion == null ? 43 : $datetimeCreacion.hashCode());
      Object $datetimeUpdate = this.getDatetimeUpdate();
      result = result * 59 + ($datetimeUpdate == null ? 43 : $datetimeUpdate.hashCode());
      Object $estado = this.getEstado();
      result = result * 59 + ($estado == null ? 43 : $estado.hashCode());
      Object $tipoReferencia = this.getTipoReferencia();
      result = result * 59 + ($tipoReferencia == null ? 43 : $tipoReferencia.hashCode());
      Object $referenciaPadre = this.getReferenciaPadre();
      result = result * 59 + ($referenciaPadre == null ? 43 : $referenciaPadre.hashCode());
      Object $numeroFirmasAutorizar = this.getNumeroFirmasAutorizar();
      result = result * 59 + ($numeroFirmasAutorizar == null ? 43 : $numeroFirmasAutorizar.hashCode());
      return result;
   }

   public String toString() {
      return "ReferenciaUnicaPojo(numeroReferencia=" + this.getNumeroReferencia() + ", descripcionReferencia=" + this.getDescripcionReferencia() + ", tipoIdentificacion=" + this.getTipoIdentificacion() + ", identificacion=" + this.getIdentificacion() + ", digitosVerificacion=" + this.getDigitosVerificacion() + ", nombresRazonSocial=" + this.getNombresRazonSocial() + ", correoElectronico=" + this.getCorreoElectronico() + ", usuarioCrea=" + this.getUsuarioCrea() + ", datetimeCreacion=" + this.getDatetimeCreacion() + ", datetimeUpdate=" + this.getDatetimeUpdate() + ", estado=" + this.getEstado() + ", tipoReferencia=" + this.getTipoReferencia() + ", referenciaPadre=" + this.getReferenciaPadre() + ", numeroFirmasAutorizar=" + this.getNumeroFirmasAutorizar() + ")";
   }
}
