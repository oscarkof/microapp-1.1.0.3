// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.gui.solicitud;

import com.fiduciaria.microapp.store.model.persona.PersonaPojo;
import java.io.Serializable;
import java.util.List;

public class PreIngresoDatosUsuarioPojo implements Serializable {
   private Long id;
   private String uuid;
   private String codNegocioLink;
   private String nombreNegocioLink;
   private String tipoSolicitud;
   private String condicionesUsuariosAutorizadores;
   private PersonaPojo persona;
   private String rolFideicomiso;
   private String role;
   private String textoOtroRolFideicomiso;
   private String textoOtroCondicionesManejo;
   private String condicionesManejo;
   private String numeroUsuariosCondiciones;
   private List<String> adjuntos;

   public PreIngresoDatosUsuarioPojo() {
   }

   public Long getId() {
      return this.id;
   }

   public String getUuid() {
      return this.uuid;
   }

   public String getCodNegocioLink() {
      return this.codNegocioLink;
   }

   public String getNombreNegocioLink() {
      return this.nombreNegocioLink;
   }

   public String getTipoSolicitud() {
      return this.tipoSolicitud;
   }

   public String getCondicionesUsuariosAutorizadores() {
      return this.condicionesUsuariosAutorizadores;
   }

   public PersonaPojo getPersona() {
      return this.persona;
   }

   public String getRolFideicomiso() {
      return this.rolFideicomiso;
   }

   public String getRole() {
      return this.role;
   }

   public String getTextoOtroRolFideicomiso() {
      return this.textoOtroRolFideicomiso;
   }

   public String getTextoOtroCondicionesManejo() {
      return this.textoOtroCondicionesManejo;
   }

   public String getCondicionesManejo() {
      return this.condicionesManejo;
   }

   public String getNumeroUsuariosCondiciones() {
      return this.numeroUsuariosCondiciones;
   }

   public List<String> getAdjuntos() {
      return this.adjuntos;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public void setCodNegocioLink(String codNegocioLink) {
      this.codNegocioLink = codNegocioLink;
   }

   public void setNombreNegocioLink(String nombreNegocioLink) {
      this.nombreNegocioLink = nombreNegocioLink;
   }

   public void setTipoSolicitud(String tipoSolicitud) {
      this.tipoSolicitud = tipoSolicitud;
   }

   public void setCondicionesUsuariosAutorizadores(String condicionesUsuariosAutorizadores) {
      this.condicionesUsuariosAutorizadores = condicionesUsuariosAutorizadores;
   }

   public void setPersona(PersonaPojo persona) {
      this.persona = persona;
   }

   public void setRolFideicomiso(String rolFideicomiso) {
      this.rolFideicomiso = rolFideicomiso;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public void setTextoOtroRolFideicomiso(String textoOtroRolFideicomiso) {
      this.textoOtroRolFideicomiso = textoOtroRolFideicomiso;
   }

   public void setTextoOtroCondicionesManejo(String textoOtroCondicionesManejo) {
      this.textoOtroCondicionesManejo = textoOtroCondicionesManejo;
   }

   public void setCondicionesManejo(String condicionesManejo) {
      this.condicionesManejo = condicionesManejo;
   }

   public void setNumeroUsuariosCondiciones(String numeroUsuariosCondiciones) {
      this.numeroUsuariosCondiciones = numeroUsuariosCondiciones;
   }

   public void setAdjuntos(List<String> adjuntos) {
      this.adjuntos = adjuntos;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PreIngresoDatosUsuarioPojo)) {
         return false;
      } else {
         PreIngresoDatosUsuarioPojo other = (PreIngresoDatosUsuarioPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$id = this.getId();
            Object other$id = other.getId();
            if (this$id == null) {
               if (other$id != null) {
                  return false;
               }
            } else if (!this$id.equals(other$id)) {
               return false;
            }

            Object this$uuid = this.getUuid();
            Object other$uuid = other.getUuid();
            if (this$uuid == null) {
               if (other$uuid != null) {
                  return false;
               }
            } else if (!this$uuid.equals(other$uuid)) {
               return false;
            }

            Object this$codNegocioLink = this.getCodNegocioLink();
            Object other$codNegocioLink = other.getCodNegocioLink();
            if (this$codNegocioLink == null) {
               if (other$codNegocioLink != null) {
                  return false;
               }
            } else if (!this$codNegocioLink.equals(other$codNegocioLink)) {
               return false;
            }

            label158: {
               Object this$nombreNegocioLink = this.getNombreNegocioLink();
               Object other$nombreNegocioLink = other.getNombreNegocioLink();
               if (this$nombreNegocioLink == null) {
                  if (other$nombreNegocioLink == null) {
                     break label158;
                  }
               } else if (this$nombreNegocioLink.equals(other$nombreNegocioLink)) {
                  break label158;
               }

               return false;
            }

            label151: {
               Object this$tipoSolicitud = this.getTipoSolicitud();
               Object other$tipoSolicitud = other.getTipoSolicitud();
               if (this$tipoSolicitud == null) {
                  if (other$tipoSolicitud == null) {
                     break label151;
                  }
               } else if (this$tipoSolicitud.equals(other$tipoSolicitud)) {
                  break label151;
               }

               return false;
            }

            Object this$condicionesUsuariosAutorizadores = this.getCondicionesUsuariosAutorizadores();
            Object other$condicionesUsuariosAutorizadores = other.getCondicionesUsuariosAutorizadores();
            if (this$condicionesUsuariosAutorizadores == null) {
               if (other$condicionesUsuariosAutorizadores != null) {
                  return false;
               }
            } else if (!this$condicionesUsuariosAutorizadores.equals(other$condicionesUsuariosAutorizadores)) {
               return false;
            }

            label137: {
               Object this$persona = this.getPersona();
               Object other$persona = other.getPersona();
               if (this$persona == null) {
                  if (other$persona == null) {
                     break label137;
                  }
               } else if (this$persona.equals(other$persona)) {
                  break label137;
               }

               return false;
            }

            label130: {
               Object this$rolFideicomiso = this.getRolFideicomiso();
               Object other$rolFideicomiso = other.getRolFideicomiso();
               if (this$rolFideicomiso == null) {
                  if (other$rolFideicomiso == null) {
                     break label130;
                  }
               } else if (this$rolFideicomiso.equals(other$rolFideicomiso)) {
                  break label130;
               }

               return false;
            }

            Object this$role = this.getRole();
            Object other$role = other.getRole();
            if (this$role == null) {
               if (other$role != null) {
                  return false;
               }
            } else if (!this$role.equals(other$role)) {
               return false;
            }

            Object this$textoOtroRolFideicomiso = this.getTextoOtroRolFideicomiso();
            Object other$textoOtroRolFideicomiso = other.getTextoOtroRolFideicomiso();
            if (this$textoOtroRolFideicomiso == null) {
               if (other$textoOtroRolFideicomiso != null) {
                  return false;
               }
            } else if (!this$textoOtroRolFideicomiso.equals(other$textoOtroRolFideicomiso)) {
               return false;
            }

            label109: {
               Object this$textoOtroCondicionesManejo = this.getTextoOtroCondicionesManejo();
               Object other$textoOtroCondicionesManejo = other.getTextoOtroCondicionesManejo();
               if (this$textoOtroCondicionesManejo == null) {
                  if (other$textoOtroCondicionesManejo == null) {
                     break label109;
                  }
               } else if (this$textoOtroCondicionesManejo.equals(other$textoOtroCondicionesManejo)) {
                  break label109;
               }

               return false;
            }

            label102: {
               Object this$condicionesManejo = this.getCondicionesManejo();
               Object other$condicionesManejo = other.getCondicionesManejo();
               if (this$condicionesManejo == null) {
                  if (other$condicionesManejo == null) {
                     break label102;
                  }
               } else if (this$condicionesManejo.equals(other$condicionesManejo)) {
                  break label102;
               }

               return false;
            }

            Object this$numeroUsuariosCondiciones = this.getNumeroUsuariosCondiciones();
            Object other$numeroUsuariosCondiciones = other.getNumeroUsuariosCondiciones();
            if (this$numeroUsuariosCondiciones == null) {
               if (other$numeroUsuariosCondiciones != null) {
                  return false;
               }
            } else if (!this$numeroUsuariosCondiciones.equals(other$numeroUsuariosCondiciones)) {
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
      return other instanceof PreIngresoDatosUsuarioPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $uuid = this.getUuid();
      result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
      Object $codNegocioLink = this.getCodNegocioLink();
      result = result * 59 + ($codNegocioLink == null ? 43 : $codNegocioLink.hashCode());
      Object $nombreNegocioLink = this.getNombreNegocioLink();
      result = result * 59 + ($nombreNegocioLink == null ? 43 : $nombreNegocioLink.hashCode());
      Object $tipoSolicitud = this.getTipoSolicitud();
      result = result * 59 + ($tipoSolicitud == null ? 43 : $tipoSolicitud.hashCode());
      Object $condicionesUsuariosAutorizadores = this.getCondicionesUsuariosAutorizadores();
      result = result * 59 + ($condicionesUsuariosAutorizadores == null ? 43 : $condicionesUsuariosAutorizadores.hashCode());
      Object $persona = this.getPersona();
      result = result * 59 + ($persona == null ? 43 : $persona.hashCode());
      Object $rolFideicomiso = this.getRolFideicomiso();
      result = result * 59 + ($rolFideicomiso == null ? 43 : $rolFideicomiso.hashCode());
      Object $role = this.getRole();
      result = result * 59 + ($role == null ? 43 : $role.hashCode());
      Object $textoOtroRolFideicomiso = this.getTextoOtroRolFideicomiso();
      result = result * 59 + ($textoOtroRolFideicomiso == null ? 43 : $textoOtroRolFideicomiso.hashCode());
      Object $textoOtroCondicionesManejo = this.getTextoOtroCondicionesManejo();
      result = result * 59 + ($textoOtroCondicionesManejo == null ? 43 : $textoOtroCondicionesManejo.hashCode());
      Object $condicionesManejo = this.getCondicionesManejo();
      result = result * 59 + ($condicionesManejo == null ? 43 : $condicionesManejo.hashCode());
      Object $numeroUsuariosCondiciones = this.getNumeroUsuariosCondiciones();
      result = result * 59 + ($numeroUsuariosCondiciones == null ? 43 : $numeroUsuariosCondiciones.hashCode());
      Object $adjuntos = this.getAdjuntos();
      result = result * 59 + ($adjuntos == null ? 43 : $adjuntos.hashCode());
      return result;
   }

   public String toString() {
      return "PreIngresoDatosUsuarioPojo(id=" + this.getId() + ", uuid=" + this.getUuid() + ", codNegocioLink=" + this.getCodNegocioLink() + ", nombreNegocioLink=" + this.getNombreNegocioLink() + ", tipoSolicitud=" + this.getTipoSolicitud() + ", condicionesUsuariosAutorizadores=" + this.getCondicionesUsuariosAutorizadores() + ", persona=" + this.getPersona() + ", rolFideicomiso=" + this.getRolFideicomiso() + ", role=" + this.getRole() + ", textoOtroRolFideicomiso=" + this.getTextoOtroRolFideicomiso() + ", textoOtroCondicionesManejo=" + this.getTextoOtroCondicionesManejo() + ", condicionesManejo=" + this.getCondicionesManejo() + ", numeroUsuariosCondiciones=" + this.getNumeroUsuariosCondiciones() + ", adjuntos=" + this.getAdjuntos() + ")";
   }
}
