// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.gui.solicitud;

import com.fiduciaria.microapp.store.model.persona.PersonaPojo;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SoporteTecnico implements Serializable {
   private Long id;
   private String codigoNegocioForm;
   private String nombreNegocioForm;
   private String tipoSolicitud;
   private String descripcionCasoSoporte;
   private String tipoCasoSoporte;
   private PersonaPojo persona;
   private List<String> adjuntos;

   public PersonaPojo getPersona() {
      if (Objects.isNull(this.persona)) {
         this.persona = new PersonaPojo();
      }

      return this.persona;
   }

   public SoporteTecnico() {
   }

   public Long getId() {
      return this.id;
   }

   public String getCodigoNegocioForm() {
      return this.codigoNegocioForm;
   }

   public String getNombreNegocioForm() {
      return this.nombreNegocioForm;
   }

   public String getTipoSolicitud() {
      return this.tipoSolicitud;
   }

   public String getDescripcionCasoSoporte() {
      return this.descripcionCasoSoporte;
   }

   public String getTipoCasoSoporte() {
      return this.tipoCasoSoporte;
   }

   public List<String> getAdjuntos() {
      return this.adjuntos;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setCodigoNegocioForm(String codigoNegocioForm) {
      this.codigoNegocioForm = codigoNegocioForm;
   }

   public void setNombreNegocioForm(String nombreNegocioForm) {
      this.nombreNegocioForm = nombreNegocioForm;
   }

   public void setTipoSolicitud(String tipoSolicitud) {
      this.tipoSolicitud = tipoSolicitud;
   }

   public void setDescripcionCasoSoporte(String descripcionCasoSoporte) {
      this.descripcionCasoSoporte = descripcionCasoSoporte;
   }

   public void setTipoCasoSoporte(String tipoCasoSoporte) {
      this.tipoCasoSoporte = tipoCasoSoporte;
   }

   public void setPersona(PersonaPojo persona) {
      this.persona = persona;
   }

   public void setAdjuntos(List<String> adjuntos) {
      this.adjuntos = adjuntos;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SoporteTecnico)) {
         return false;
      } else {
         SoporteTecnico other = (SoporteTecnico)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label107: {
               Object this$id = this.getId();
               Object other$id = other.getId();
               if (this$id == null) {
                  if (other$id == null) {
                     break label107;
                  }
               } else if (this$id.equals(other$id)) {
                  break label107;
               }

               return false;
            }

            Object this$codigoNegocioForm = this.getCodigoNegocioForm();
            Object other$codigoNegocioForm = other.getCodigoNegocioForm();
            if (this$codigoNegocioForm == null) {
               if (other$codigoNegocioForm != null) {
                  return false;
               }
            } else if (!this$codigoNegocioForm.equals(other$codigoNegocioForm)) {
               return false;
            }

            Object this$nombreNegocioForm = this.getNombreNegocioForm();
            Object other$nombreNegocioForm = other.getNombreNegocioForm();
            if (this$nombreNegocioForm == null) {
               if (other$nombreNegocioForm != null) {
                  return false;
               }
            } else if (!this$nombreNegocioForm.equals(other$nombreNegocioForm)) {
               return false;
            }

            label86: {
               Object this$tipoSolicitud = this.getTipoSolicitud();
               Object other$tipoSolicitud = other.getTipoSolicitud();
               if (this$tipoSolicitud == null) {
                  if (other$tipoSolicitud == null) {
                     break label86;
                  }
               } else if (this$tipoSolicitud.equals(other$tipoSolicitud)) {
                  break label86;
               }

               return false;
            }

            label79: {
               Object this$descripcionCasoSoporte = this.getDescripcionCasoSoporte();
               Object other$descripcionCasoSoporte = other.getDescripcionCasoSoporte();
               if (this$descripcionCasoSoporte == null) {
                  if (other$descripcionCasoSoporte == null) {
                     break label79;
                  }
               } else if (this$descripcionCasoSoporte.equals(other$descripcionCasoSoporte)) {
                  break label79;
               }

               return false;
            }

            label72: {
               Object this$tipoCasoSoporte = this.getTipoCasoSoporte();
               Object other$tipoCasoSoporte = other.getTipoCasoSoporte();
               if (this$tipoCasoSoporte == null) {
                  if (other$tipoCasoSoporte == null) {
                     break label72;
                  }
               } else if (this$tipoCasoSoporte.equals(other$tipoCasoSoporte)) {
                  break label72;
               }

               return false;
            }

            Object this$persona = this.getPersona();
            Object other$persona = other.getPersona();
            if (this$persona == null) {
               if (other$persona != null) {
                  return false;
               }
            } else if (!this$persona.equals(other$persona)) {
               return false;
            }

            Object this$adjuntos = this.getAdjuntos();
            Object other$adjuntos = other.getAdjuntos();
            if (this$adjuntos == null) {
               if (other$adjuntos != null) {
                  return false;
               }
            } else if (!this$adjuntos.equals(other$adjuntos)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SoporteTecnico;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $codigoNegocioForm = this.getCodigoNegocioForm();
      result = result * 59 + ($codigoNegocioForm == null ? 43 : $codigoNegocioForm.hashCode());
      Object $nombreNegocioForm = this.getNombreNegocioForm();
      result = result * 59 + ($nombreNegocioForm == null ? 43 : $nombreNegocioForm.hashCode());
      Object $tipoSolicitud = this.getTipoSolicitud();
      result = result * 59 + ($tipoSolicitud == null ? 43 : $tipoSolicitud.hashCode());
      Object $descripcionCasoSoporte = this.getDescripcionCasoSoporte();
      result = result * 59 + ($descripcionCasoSoporte == null ? 43 : $descripcionCasoSoporte.hashCode());
      Object $tipoCasoSoporte = this.getTipoCasoSoporte();
      result = result * 59 + ($tipoCasoSoporte == null ? 43 : $tipoCasoSoporte.hashCode());
      Object $persona = this.getPersona();
      result = result * 59 + ($persona == null ? 43 : $persona.hashCode());
      Object $adjuntos = this.getAdjuntos();
      result = result * 59 + ($adjuntos == null ? 43 : $adjuntos.hashCode());
      return result;
   }

   public String toString() {
      return "SoporteTecnico(id=" + this.getId() + ", codigoNegocioForm=" + this.getCodigoNegocioForm() + ", nombreNegocioForm=" + this.getNombreNegocioForm() + ", tipoSolicitud=" + this.getTipoSolicitud() + ", descripcionCasoSoporte=" + this.getDescripcionCasoSoporte() + ", tipoCasoSoporte=" + this.getTipoCasoSoporte() + ", persona=" + this.getPersona() + ", adjuntos=" + this.getAdjuntos() + ")";
   }
}
