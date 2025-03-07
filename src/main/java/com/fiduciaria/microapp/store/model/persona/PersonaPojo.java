// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.persona;

import com.fiduciaria.microapp.store.basicas.TipoDocumentoPojo;
import com.fiduciaria.microapp.store.dispositivo.CelularPojo;
import com.fiduciaria.microapp.store.seguridad.PrincipalPojo;
import com.fiduciaria.microapp.store.sitio.DireccionPojo;
import org.apache.wicket.util.io.IClusterable;

public class PersonaPojo implements IClusterable {
   private String primerNombre;
   private String segundoNombre;
   private String primerApellido;
   private String segundoApellido;
   private String email;
   private String telefono;
   private String extension;
   private String numeroDocumento;
   private String direccion;
   private String correoElectronico;
   private PrincipalPojo usuario;
   private String razonSocial;
   private TipoDocumentoPojo tipoDocumento;
   private CelularPojo celular;
   private DireccionPojo direccionPersonal;
   private DireccionPojo direccionOficina;
   private DireccionPojo direccionCorrespondencia;

   public PersonaPojo() {
   }

   public String getPrimerNombre() {
      return this.primerNombre;
   }

   public String getSegundoNombre() {
      return this.segundoNombre;
   }

   public String getPrimerApellido() {
      return this.primerApellido;
   }

   public String getSegundoApellido() {
      return this.segundoApellido;
   }

   public String getEmail() {
      return this.email;
   }

   public String getTelefono() {
      return this.telefono;
   }

   public String getExtension() {
      return this.extension;
   }

   public String getNumeroDocumento() {
      return this.numeroDocumento;
   }

   public String getDireccion() {
      return this.direccion;
   }

   public String getCorreoElectronico() {
      return this.correoElectronico;
   }

   public PrincipalPojo getUsuario() {
      return this.usuario;
   }

   public String getRazonSocial() {
      return this.razonSocial;
   }

   public TipoDocumentoPojo getTipoDocumento() {
      return this.tipoDocumento;
   }

   public CelularPojo getCelular() {
      return this.celular;
   }

   public DireccionPojo getDireccionPersonal() {
      return this.direccionPersonal;
   }

   public DireccionPojo getDireccionOficina() {
      return this.direccionOficina;
   }

   public DireccionPojo getDireccionCorrespondencia() {
      return this.direccionCorrespondencia;
   }

   public void setPrimerNombre(String primerNombre) {
      this.primerNombre = primerNombre;
   }

   public void setSegundoNombre(String segundoNombre) {
      this.segundoNombre = segundoNombre;
   }

   public void setPrimerApellido(String primerApellido) {
      this.primerApellido = primerApellido;
   }

   public void setSegundoApellido(String segundoApellido) {
      this.segundoApellido = segundoApellido;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }

   public void setExtension(String extension) {
      this.extension = extension;
   }

   public void setNumeroDocumento(String numeroDocumento) {
      this.numeroDocumento = numeroDocumento;
   }

   public void setDireccion(String direccion) {
      this.direccion = direccion;
   }

   public void setCorreoElectronico(String correoElectronico) {
      this.correoElectronico = correoElectronico;
   }

   public void setUsuario(PrincipalPojo usuario) {
      this.usuario = usuario;
   }

   public void setRazonSocial(String razonSocial) {
      this.razonSocial = razonSocial;
   }

   public void setTipoDocumento(TipoDocumentoPojo tipoDocumento) {
      this.tipoDocumento = tipoDocumento;
   }

   public void setCelular(CelularPojo celular) {
      this.celular = celular;
   }

   public void setDireccionPersonal(DireccionPojo direccionPersonal) {
      this.direccionPersonal = direccionPersonal;
   }

   public void setDireccionOficina(DireccionPojo direccionOficina) {
      this.direccionOficina = direccionOficina;
   }

   public void setDireccionCorrespondencia(DireccionPojo direccionCorrespondencia) {
      this.direccionCorrespondencia = direccionCorrespondencia;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PersonaPojo)) {
         return false;
      } else {
         PersonaPojo other = (PersonaPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label215: {
               Object this$primerNombre = this.getPrimerNombre();
               Object other$primerNombre = other.getPrimerNombre();
               if (this$primerNombre == null) {
                  if (other$primerNombre == null) {
                     break label215;
                  }
               } else if (this$primerNombre.equals(other$primerNombre)) {
                  break label215;
               }

               return false;
            }

            Object this$segundoNombre = this.getSegundoNombre();
            Object other$segundoNombre = other.getSegundoNombre();
            if (this$segundoNombre == null) {
               if (other$segundoNombre != null) {
                  return false;
               }
            } else if (!this$segundoNombre.equals(other$segundoNombre)) {
               return false;
            }

            label201: {
               Object this$primerApellido = this.getPrimerApellido();
               Object other$primerApellido = other.getPrimerApellido();
               if (this$primerApellido == null) {
                  if (other$primerApellido == null) {
                     break label201;
                  }
               } else if (this$primerApellido.equals(other$primerApellido)) {
                  break label201;
               }

               return false;
            }

            Object this$segundoApellido = this.getSegundoApellido();
            Object other$segundoApellido = other.getSegundoApellido();
            if (this$segundoApellido == null) {
               if (other$segundoApellido != null) {
                  return false;
               }
            } else if (!this$segundoApellido.equals(other$segundoApellido)) {
               return false;
            }

            label187: {
               Object this$email = this.getEmail();
               Object other$email = other.getEmail();
               if (this$email == null) {
                  if (other$email == null) {
                     break label187;
                  }
               } else if (this$email.equals(other$email)) {
                  break label187;
               }

               return false;
            }

            Object this$telefono = this.getTelefono();
            Object other$telefono = other.getTelefono();
            if (this$telefono == null) {
               if (other$telefono != null) {
                  return false;
               }
            } else if (!this$telefono.equals(other$telefono)) {
               return false;
            }

            label173: {
               Object this$extension = this.getExtension();
               Object other$extension = other.getExtension();
               if (this$extension == null) {
                  if (other$extension == null) {
                     break label173;
                  }
               } else if (this$extension.equals(other$extension)) {
                  break label173;
               }

               return false;
            }

            label166: {
               Object this$numeroDocumento = this.getNumeroDocumento();
               Object other$numeroDocumento = other.getNumeroDocumento();
               if (this$numeroDocumento == null) {
                  if (other$numeroDocumento == null) {
                     break label166;
                  }
               } else if (this$numeroDocumento.equals(other$numeroDocumento)) {
                  break label166;
               }

               return false;
            }

            Object this$direccion = this.getDireccion();
            Object other$direccion = other.getDireccion();
            if (this$direccion == null) {
               if (other$direccion != null) {
                  return false;
               }
            } else if (!this$direccion.equals(other$direccion)) {
               return false;
            }

            label152: {
               Object this$correoElectronico = this.getCorreoElectronico();
               Object other$correoElectronico = other.getCorreoElectronico();
               if (this$correoElectronico == null) {
                  if (other$correoElectronico == null) {
                     break label152;
                  }
               } else if (this$correoElectronico.equals(other$correoElectronico)) {
                  break label152;
               }

               return false;
            }

            label145: {
               Object this$usuario = this.getUsuario();
               Object other$usuario = other.getUsuario();
               if (this$usuario == null) {
                  if (other$usuario == null) {
                     break label145;
                  }
               } else if (this$usuario.equals(other$usuario)) {
                  break label145;
               }

               return false;
            }

            Object this$razonSocial = this.getRazonSocial();
            Object other$razonSocial = other.getRazonSocial();
            if (this$razonSocial == null) {
               if (other$razonSocial != null) {
                  return false;
               }
            } else if (!this$razonSocial.equals(other$razonSocial)) {
               return false;
            }

            Object this$tipoDocumento = this.getTipoDocumento();
            Object other$tipoDocumento = other.getTipoDocumento();
            if (this$tipoDocumento == null) {
               if (other$tipoDocumento != null) {
                  return false;
               }
            } else if (!this$tipoDocumento.equals(other$tipoDocumento)) {
               return false;
            }

            label124: {
               Object this$celular = this.getCelular();
               Object other$celular = other.getCelular();
               if (this$celular == null) {
                  if (other$celular == null) {
                     break label124;
                  }
               } else if (this$celular.equals(other$celular)) {
                  break label124;
               }

               return false;
            }

            Object this$direccionPersonal = this.getDireccionPersonal();
            Object other$direccionPersonal = other.getDireccionPersonal();
            if (this$direccionPersonal == null) {
               if (other$direccionPersonal != null) {
                  return false;
               }
            } else if (!this$direccionPersonal.equals(other$direccionPersonal)) {
               return false;
            }

            Object this$direccionOficina = this.getDireccionOficina();
            Object other$direccionOficina = other.getDireccionOficina();
            if (this$direccionOficina == null) {
               if (other$direccionOficina != null) {
                  return false;
               }
            } else if (!this$direccionOficina.equals(other$direccionOficina)) {
               return false;
            }

            Object this$direccionCorrespondencia = this.getDireccionCorrespondencia();
            Object other$direccionCorrespondencia = other.getDireccionCorrespondencia();
            if (this$direccionCorrespondencia == null) {
               if (other$direccionCorrespondencia != null) {
                  return false;
               }
            } else if (!this$direccionCorrespondencia.equals(other$direccionCorrespondencia)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PersonaPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $primerNombre = this.getPrimerNombre();
      result = result * 59 + ($primerNombre == null ? 43 : $primerNombre.hashCode());
      Object $segundoNombre = this.getSegundoNombre();
      result = result * 59 + ($segundoNombre == null ? 43 : $segundoNombre.hashCode());
      Object $primerApellido = this.getPrimerApellido();
      result = result * 59 + ($primerApellido == null ? 43 : $primerApellido.hashCode());
      Object $segundoApellido = this.getSegundoApellido();
      result = result * 59 + ($segundoApellido == null ? 43 : $segundoApellido.hashCode());
      Object $email = this.getEmail();
      result = result * 59 + ($email == null ? 43 : $email.hashCode());
      Object $telefono = this.getTelefono();
      result = result * 59 + ($telefono == null ? 43 : $telefono.hashCode());
      Object $extension = this.getExtension();
      result = result * 59 + ($extension == null ? 43 : $extension.hashCode());
      Object $numeroDocumento = this.getNumeroDocumento();
      result = result * 59 + ($numeroDocumento == null ? 43 : $numeroDocumento.hashCode());
      Object $direccion = this.getDireccion();
      result = result * 59 + ($direccion == null ? 43 : $direccion.hashCode());
      Object $correoElectronico = this.getCorreoElectronico();
      result = result * 59 + ($correoElectronico == null ? 43 : $correoElectronico.hashCode());
      Object $usuario = this.getUsuario();
      result = result * 59 + ($usuario == null ? 43 : $usuario.hashCode());
      Object $razonSocial = this.getRazonSocial();
      result = result * 59 + ($razonSocial == null ? 43 : $razonSocial.hashCode());
      Object $tipoDocumento = this.getTipoDocumento();
      result = result * 59 + ($tipoDocumento == null ? 43 : $tipoDocumento.hashCode());
      Object $celular = this.getCelular();
      result = result * 59 + ($celular == null ? 43 : $celular.hashCode());
      Object $direccionPersonal = this.getDireccionPersonal();
      result = result * 59 + ($direccionPersonal == null ? 43 : $direccionPersonal.hashCode());
      Object $direccionOficina = this.getDireccionOficina();
      result = result * 59 + ($direccionOficina == null ? 43 : $direccionOficina.hashCode());
      Object $direccionCorrespondencia = this.getDireccionCorrespondencia();
      result = result * 59 + ($direccionCorrespondencia == null ? 43 : $direccionCorrespondencia.hashCode());
      return result;
   }

   public String toString() {
      return "PersonaPojo(primerNombre=" + this.getPrimerNombre() + ", segundoNombre=" + this.getSegundoNombre() + ", primerApellido=" + this.getPrimerApellido() + ", segundoApellido=" + this.getSegundoApellido() + ", email=" + this.getEmail() + ", telefono=" + this.getTelefono() + ", extension=" + this.getExtension() + ", numeroDocumento=" + this.getNumeroDocumento() + ", direccion=" + this.getDireccion() + ", correoElectronico=" + this.getCorreoElectronico() + ", usuario=" + this.getUsuario() + ", razonSocial=" + this.getRazonSocial() + ", tipoDocumento=" + this.getTipoDocumento() + ", celular=" + this.getCelular() + ", direccionPersonal=" + this.getDireccionPersonal() + ", direccionOficina=" + this.getDireccionOficina() + ", direccionCorrespondencia=" + this.getDireccionCorrespondencia() + ")";
   }
}
