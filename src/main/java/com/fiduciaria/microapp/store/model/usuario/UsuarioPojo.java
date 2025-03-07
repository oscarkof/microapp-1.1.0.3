// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoPojo;
import java.math.BigDecimal;
import java.util.Map;
import org.apache.wicket.util.io.IClusterable;

public class UsuarioPojo implements IClusterable {
   private Map<String, String> atributos;
   private String idPrincipal;
   private String usuario;
   private String tipoPrincipal;
   private String nombrePrincipal;
   private String password;
   private String newPassword;
   private boolean mapped;
   private boolean soloLectura;
   private boolean borrable;
   private long fechaCreacion;
   private long fechaModificacion;
   private String idDominio;
   private String estado;
   private String nombreUsuario;
   private String primerNombre;
   private String segundoNombre;
   private String primerApellido;
   private String segundoApellido;
   private String tipoIdentificacion;
   private String identificacion;
   private String digitoVerificacion;
   private String correoElectronico;
   private String numeroCelular;
   private String numeroTelefono;
   private String lugarFechaNacimiento;
   private String direccionDomicilio;
   private String tieneDiscapacidad;
   private String actividadEconomica;
   private String actividadEconomicaPpla;
   private String pep;
   private BigDecimal ingresos;
   private BigDecimal egresos;
   private BigDecimal pasivos;
   private BigDecimal activos;
   private BigDecimal patrimonio;
   private String sistemaOperativoCelular;
   private TipoDocumentoPojo tipoDocumento;
   private boolean usuarioSalaVentas;
   private String tipoUsuarioSalaventas;
   private String fidutoken;
   private String usuarioSession;

   @JsonIgnore
   public void clear() {
      this.idPrincipal = null;
      this.usuario = null;
      this.tipoPrincipal = null;
      this.nombrePrincipal = null;
      this.password = null;
      this.newPassword = null;
      this.mapped = false;
      this.soloLectura = false;
      this.borrable = false;
      this.fechaCreacion = -1L;
      this.fechaModificacion = -1L;
      this.idDominio = null;
      this.estado = null;
      this.nombreUsuario = null;
      this.primerNombre = null;
      this.segundoNombre = null;
      this.primerApellido = null;
      this.segundoApellido = null;
      this.tipoIdentificacion = null;
      this.identificacion = null;
      this.digitoVerificacion = null;
      this.correoElectronico = null;
      this.numeroCelular = null;
      this.numeroTelefono = null;
      this.lugarFechaNacimiento = null;
      this.direccionDomicilio = null;
      this.tieneDiscapacidad = null;
      this.actividadEconomica = null;
      this.actividadEconomicaPpla = null;
      this.pep = null;
      this.ingresos = null;
      this.egresos = null;
      this.pasivos = null;
      this.activos = null;
      this.patrimonio = null;
      this.sistemaOperativoCelular = null;
      this.tipoDocumento = null;
      this.usuarioSalaVentas = false;
      this.tipoUsuarioSalaventas = null;
   }

   public UsuarioPojo() {
   }

   public Map<String, String> getAtributos() {
      return this.atributos;
   }

   public String getIdPrincipal() {
      return this.idPrincipal;
   }

   public String getUsuario() {
      return this.usuario;
   }

   public String getTipoPrincipal() {
      return this.tipoPrincipal;
   }

   public String getNombrePrincipal() {
      return this.nombrePrincipal;
   }

   public String getPassword() {
      return this.password;
   }

   public String getNewPassword() {
      return this.newPassword;
   }

   public boolean isMapped() {
      return this.mapped;
   }

   public boolean isSoloLectura() {
      return this.soloLectura;
   }

   public boolean isBorrable() {
      return this.borrable;
   }

   public long getFechaCreacion() {
      return this.fechaCreacion;
   }

   public long getFechaModificacion() {
      return this.fechaModificacion;
   }

   public String getIdDominio() {
      return this.idDominio;
   }

   public String getEstado() {
      return this.estado;
   }

   public String getNombreUsuario() {
      return this.nombreUsuario;
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

   public String getTipoIdentificacion() {
      return this.tipoIdentificacion;
   }

   public String getIdentificacion() {
      return this.identificacion;
   }

   public String getDigitoVerificacion() {
      return this.digitoVerificacion;
   }

   public String getCorreoElectronico() {
      return this.correoElectronico;
   }

   public String getNumeroCelular() {
      return this.numeroCelular;
   }

   public String getNumeroTelefono() {
      return this.numeroTelefono;
   }

   public String getLugarFechaNacimiento() {
      return this.lugarFechaNacimiento;
   }

   public String getDireccionDomicilio() {
      return this.direccionDomicilio;
   }

   public String getTieneDiscapacidad() {
      return this.tieneDiscapacidad;
   }

   public String getActividadEconomica() {
      return this.actividadEconomica;
   }

   public String getActividadEconomicaPpla() {
      return this.actividadEconomicaPpla;
   }

   public String getPep() {
      return this.pep;
   }

   public BigDecimal getIngresos() {
      return this.ingresos;
   }

   public BigDecimal getEgresos() {
      return this.egresos;
   }

   public BigDecimal getPasivos() {
      return this.pasivos;
   }

   public BigDecimal getActivos() {
      return this.activos;
   }

   public BigDecimal getPatrimonio() {
      return this.patrimonio;
   }

   public String getSistemaOperativoCelular() {
      return this.sistemaOperativoCelular;
   }

   public TipoDocumentoPojo getTipoDocumento() {
      return this.tipoDocumento;
   }

   public boolean isUsuarioSalaVentas() {
      return this.usuarioSalaVentas;
   }

   public String getTipoUsuarioSalaventas() {
      return this.tipoUsuarioSalaventas;
   }

   public String getFidutoken() {
      return this.fidutoken;
   }

   public String getUsuarioSession() {
      return this.usuarioSession;
   }

   public void setAtributos(Map<String, String> atributos) {
      this.atributos = atributos;
   }

   public void setIdPrincipal(String idPrincipal) {
      this.idPrincipal = idPrincipal;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public void setTipoPrincipal(String tipoPrincipal) {
      this.tipoPrincipal = tipoPrincipal;
   }

   public void setNombrePrincipal(String nombrePrincipal) {
      this.nombrePrincipal = nombrePrincipal;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public void setNewPassword(String newPassword) {
      this.newPassword = newPassword;
   }

   public void setMapped(boolean mapped) {
      this.mapped = mapped;
   }

   public void setSoloLectura(boolean soloLectura) {
      this.soloLectura = soloLectura;
   }

   public void setBorrable(boolean borrable) {
      this.borrable = borrable;
   }

   public void setFechaCreacion(long fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   public void setFechaModificacion(long fechaModificacion) {
      this.fechaModificacion = fechaModificacion;
   }

   public void setIdDominio(String idDominio) {
      this.idDominio = idDominio;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   public void setNombreUsuario(String nombreUsuario) {
      this.nombreUsuario = nombreUsuario;
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

   public void setTipoIdentificacion(String tipoIdentificacion) {
      this.tipoIdentificacion = tipoIdentificacion;
   }

   public void setIdentificacion(String identificacion) {
      this.identificacion = identificacion;
   }

   public void setDigitoVerificacion(String digitoVerificacion) {
      this.digitoVerificacion = digitoVerificacion;
   }

   public void setCorreoElectronico(String correoElectronico) {
      this.correoElectronico = correoElectronico;
   }

   public void setNumeroCelular(String numeroCelular) {
      this.numeroCelular = numeroCelular;
   }

   public void setNumeroTelefono(String numeroTelefono) {
      this.numeroTelefono = numeroTelefono;
   }

   public void setLugarFechaNacimiento(String lugarFechaNacimiento) {
      this.lugarFechaNacimiento = lugarFechaNacimiento;
   }

   public void setDireccionDomicilio(String direccionDomicilio) {
      this.direccionDomicilio = direccionDomicilio;
   }

   public void setTieneDiscapacidad(String tieneDiscapacidad) {
      this.tieneDiscapacidad = tieneDiscapacidad;
   }

   public void setActividadEconomica(String actividadEconomica) {
      this.actividadEconomica = actividadEconomica;
   }

   public void setActividadEconomicaPpla(String actividadEconomicaPpla) {
      this.actividadEconomicaPpla = actividadEconomicaPpla;
   }

   public void setPep(String pep) {
      this.pep = pep;
   }

   public void setIngresos(BigDecimal ingresos) {
      this.ingresos = ingresos;
   }

   public void setEgresos(BigDecimal egresos) {
      this.egresos = egresos;
   }

   public void setPasivos(BigDecimal pasivos) {
      this.pasivos = pasivos;
   }

   public void setActivos(BigDecimal activos) {
      this.activos = activos;
   }

   public void setPatrimonio(BigDecimal patrimonio) {
      this.patrimonio = patrimonio;
   }

   public void setSistemaOperativoCelular(String sistemaOperativoCelular) {
      this.sistemaOperativoCelular = sistemaOperativoCelular;
   }

   public void setTipoDocumento(TipoDocumentoPojo tipoDocumento) {
      this.tipoDocumento = tipoDocumento;
   }

   public void setUsuarioSalaVentas(boolean usuarioSalaVentas) {
      this.usuarioSalaVentas = usuarioSalaVentas;
   }

   public void setTipoUsuarioSalaventas(String tipoUsuarioSalaventas) {
      this.tipoUsuarioSalaventas = tipoUsuarioSalaventas;
   }

   public void setFidutoken(String fidutoken) {
      this.fidutoken = fidutoken;
   }

   public void setUsuarioSession(String usuarioSession) {
      this.usuarioSession = usuarioSession;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UsuarioPojo)) {
         return false;
      } else {
         UsuarioPojo other = (UsuarioPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isMapped() != other.isMapped()) {
            return false;
         } else if (this.isSoloLectura() != other.isSoloLectura()) {
            return false;
         } else if (this.isBorrable() != other.isBorrable()) {
            return false;
         } else if (this.getFechaCreacion() != other.getFechaCreacion()) {
            return false;
         } else if (this.getFechaModificacion() != other.getFechaModificacion()) {
            return false;
         } else if (this.isUsuarioSalaVentas() != other.isUsuarioSalaVentas()) {
            return false;
         } else {
            label458: {
               Object this$atributos = this.getAtributos();
               Object other$atributos = other.getAtributos();
               if (this$atributos == null) {
                  if (other$atributos == null) {
                     break label458;
                  }
               } else if (this$atributos.equals(other$atributos)) {
                  break label458;
               }

               return false;
            }

            Object this$idPrincipal = this.getIdPrincipal();
            Object other$idPrincipal = other.getIdPrincipal();
            if (this$idPrincipal == null) {
               if (other$idPrincipal != null) {
                  return false;
               }
            } else if (!this$idPrincipal.equals(other$idPrincipal)) {
               return false;
            }

            Object this$usuario = this.getUsuario();
            Object other$usuario = other.getUsuario();
            if (this$usuario == null) {
               if (other$usuario != null) {
                  return false;
               }
            } else if (!this$usuario.equals(other$usuario)) {
               return false;
            }

            Object this$tipoPrincipal = this.getTipoPrincipal();
            Object other$tipoPrincipal = other.getTipoPrincipal();
            if (this$tipoPrincipal == null) {
               if (other$tipoPrincipal != null) {
                  return false;
               }
            } else if (!this$tipoPrincipal.equals(other$tipoPrincipal)) {
               return false;
            }

            label430: {
               Object this$nombrePrincipal = this.getNombrePrincipal();
               Object other$nombrePrincipal = other.getNombrePrincipal();
               if (this$nombrePrincipal == null) {
                  if (other$nombrePrincipal == null) {
                     break label430;
                  }
               } else if (this$nombrePrincipal.equals(other$nombrePrincipal)) {
                  break label430;
               }

               return false;
            }

            Object this$password = this.getPassword();
            Object other$password = other.getPassword();
            if (this$password == null) {
               if (other$password != null) {
                  return false;
               }
            } else if (!this$password.equals(other$password)) {
               return false;
            }

            Object this$newPassword = this.getNewPassword();
            Object other$newPassword = other.getNewPassword();
            if (this$newPassword == null) {
               if (other$newPassword != null) {
                  return false;
               }
            } else if (!this$newPassword.equals(other$newPassword)) {
               return false;
            }

            label409: {
               Object this$idDominio = this.getIdDominio();
               Object other$idDominio = other.getIdDominio();
               if (this$idDominio == null) {
                  if (other$idDominio == null) {
                     break label409;
                  }
               } else if (this$idDominio.equals(other$idDominio)) {
                  break label409;
               }

               return false;
            }

            label402: {
               Object this$estado = this.getEstado();
               Object other$estado = other.getEstado();
               if (this$estado == null) {
                  if (other$estado == null) {
                     break label402;
                  }
               } else if (this$estado.equals(other$estado)) {
                  break label402;
               }

               return false;
            }

            Object this$nombreUsuario = this.getNombreUsuario();
            Object other$nombreUsuario = other.getNombreUsuario();
            if (this$nombreUsuario == null) {
               if (other$nombreUsuario != null) {
                  return false;
               }
            } else if (!this$nombreUsuario.equals(other$nombreUsuario)) {
               return false;
            }

            Object this$primerNombre = this.getPrimerNombre();
            Object other$primerNombre = other.getPrimerNombre();
            if (this$primerNombre == null) {
               if (other$primerNombre != null) {
                  return false;
               }
            } else if (!this$primerNombre.equals(other$primerNombre)) {
               return false;
            }

            label381: {
               Object this$segundoNombre = this.getSegundoNombre();
               Object other$segundoNombre = other.getSegundoNombre();
               if (this$segundoNombre == null) {
                  if (other$segundoNombre == null) {
                     break label381;
                  }
               } else if (this$segundoNombre.equals(other$segundoNombre)) {
                  break label381;
               }

               return false;
            }

            label374: {
               Object this$primerApellido = this.getPrimerApellido();
               Object other$primerApellido = other.getPrimerApellido();
               if (this$primerApellido == null) {
                  if (other$primerApellido == null) {
                     break label374;
                  }
               } else if (this$primerApellido.equals(other$primerApellido)) {
                  break label374;
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

            label360: {
               Object this$tipoIdentificacion = this.getTipoIdentificacion();
               Object other$tipoIdentificacion = other.getTipoIdentificacion();
               if (this$tipoIdentificacion == null) {
                  if (other$tipoIdentificacion == null) {
                     break label360;
                  }
               } else if (this$tipoIdentificacion.equals(other$tipoIdentificacion)) {
                  break label360;
               }

               return false;
            }

            Object this$identificacion = this.getIdentificacion();
            Object other$identificacion = other.getIdentificacion();
            if (this$identificacion == null) {
               if (other$identificacion != null) {
                  return false;
               }
            } else if (!this$identificacion.equals(other$identificacion)) {
               return false;
            }

            label346: {
               Object this$digitoVerificacion = this.getDigitoVerificacion();
               Object other$digitoVerificacion = other.getDigitoVerificacion();
               if (this$digitoVerificacion == null) {
                  if (other$digitoVerificacion == null) {
                     break label346;
                  }
               } else if (this$digitoVerificacion.equals(other$digitoVerificacion)) {
                  break label346;
               }

               return false;
            }

            Object this$correoElectronico = this.getCorreoElectronico();
            Object other$correoElectronico = other.getCorreoElectronico();
            if (this$correoElectronico == null) {
               if (other$correoElectronico != null) {
                  return false;
               }
            } else if (!this$correoElectronico.equals(other$correoElectronico)) {
               return false;
            }

            Object this$numeroCelular = this.getNumeroCelular();
            Object other$numeroCelular = other.getNumeroCelular();
            if (this$numeroCelular == null) {
               if (other$numeroCelular != null) {
                  return false;
               }
            } else if (!this$numeroCelular.equals(other$numeroCelular)) {
               return false;
            }

            Object this$numeroTelefono = this.getNumeroTelefono();
            Object other$numeroTelefono = other.getNumeroTelefono();
            if (this$numeroTelefono == null) {
               if (other$numeroTelefono != null) {
                  return false;
               }
            } else if (!this$numeroTelefono.equals(other$numeroTelefono)) {
               return false;
            }

            label318: {
               Object this$lugarFechaNacimiento = this.getLugarFechaNacimiento();
               Object other$lugarFechaNacimiento = other.getLugarFechaNacimiento();
               if (this$lugarFechaNacimiento == null) {
                  if (other$lugarFechaNacimiento == null) {
                     break label318;
                  }
               } else if (this$lugarFechaNacimiento.equals(other$lugarFechaNacimiento)) {
                  break label318;
               }

               return false;
            }

            Object this$direccionDomicilio = this.getDireccionDomicilio();
            Object other$direccionDomicilio = other.getDireccionDomicilio();
            if (this$direccionDomicilio == null) {
               if (other$direccionDomicilio != null) {
                  return false;
               }
            } else if (!this$direccionDomicilio.equals(other$direccionDomicilio)) {
               return false;
            }

            Object this$tieneDiscapacidad = this.getTieneDiscapacidad();
            Object other$tieneDiscapacidad = other.getTieneDiscapacidad();
            if (this$tieneDiscapacidad == null) {
               if (other$tieneDiscapacidad != null) {
                  return false;
               }
            } else if (!this$tieneDiscapacidad.equals(other$tieneDiscapacidad)) {
               return false;
            }

            label297: {
               Object this$actividadEconomica = this.getActividadEconomica();
               Object other$actividadEconomica = other.getActividadEconomica();
               if (this$actividadEconomica == null) {
                  if (other$actividadEconomica == null) {
                     break label297;
                  }
               } else if (this$actividadEconomica.equals(other$actividadEconomica)) {
                  break label297;
               }

               return false;
            }

            label290: {
               Object this$actividadEconomicaPpla = this.getActividadEconomicaPpla();
               Object other$actividadEconomicaPpla = other.getActividadEconomicaPpla();
               if (this$actividadEconomicaPpla == null) {
                  if (other$actividadEconomicaPpla == null) {
                     break label290;
                  }
               } else if (this$actividadEconomicaPpla.equals(other$actividadEconomicaPpla)) {
                  break label290;
               }

               return false;
            }

            Object this$pep = this.getPep();
            Object other$pep = other.getPep();
            if (this$pep == null) {
               if (other$pep != null) {
                  return false;
               }
            } else if (!this$pep.equals(other$pep)) {
               return false;
            }

            Object this$ingresos = this.getIngresos();
            Object other$ingresos = other.getIngresos();
            if (this$ingresos == null) {
               if (other$ingresos != null) {
                  return false;
               }
            } else if (!this$ingresos.equals(other$ingresos)) {
               return false;
            }

            label269: {
               Object this$egresos = this.getEgresos();
               Object other$egresos = other.getEgresos();
               if (this$egresos == null) {
                  if (other$egresos == null) {
                     break label269;
                  }
               } else if (this$egresos.equals(other$egresos)) {
                  break label269;
               }

               return false;
            }

            label262: {
               Object this$pasivos = this.getPasivos();
               Object other$pasivos = other.getPasivos();
               if (this$pasivos == null) {
                  if (other$pasivos == null) {
                     break label262;
                  }
               } else if (this$pasivos.equals(other$pasivos)) {
                  break label262;
               }

               return false;
            }

            Object this$activos = this.getActivos();
            Object other$activos = other.getActivos();
            if (this$activos == null) {
               if (other$activos != null) {
                  return false;
               }
            } else if (!this$activos.equals(other$activos)) {
               return false;
            }

            label248: {
               Object this$patrimonio = this.getPatrimonio();
               Object other$patrimonio = other.getPatrimonio();
               if (this$patrimonio == null) {
                  if (other$patrimonio == null) {
                     break label248;
                  }
               } else if (this$patrimonio.equals(other$patrimonio)) {
                  break label248;
               }

               return false;
            }

            Object this$sistemaOperativoCelular = this.getSistemaOperativoCelular();
            Object other$sistemaOperativoCelular = other.getSistemaOperativoCelular();
            if (this$sistemaOperativoCelular == null) {
               if (other$sistemaOperativoCelular != null) {
                  return false;
               }
            } else if (!this$sistemaOperativoCelular.equals(other$sistemaOperativoCelular)) {
               return false;
            }

            label234: {
               Object this$tipoDocumento = this.getTipoDocumento();
               Object other$tipoDocumento = other.getTipoDocumento();
               if (this$tipoDocumento == null) {
                  if (other$tipoDocumento == null) {
                     break label234;
                  }
               } else if (this$tipoDocumento.equals(other$tipoDocumento)) {
                  break label234;
               }

               return false;
            }

            Object this$tipoUsuarioSalaventas = this.getTipoUsuarioSalaventas();
            Object other$tipoUsuarioSalaventas = other.getTipoUsuarioSalaventas();
            if (this$tipoUsuarioSalaventas == null) {
               if (other$tipoUsuarioSalaventas != null) {
                  return false;
               }
            } else if (!this$tipoUsuarioSalaventas.equals(other$tipoUsuarioSalaventas)) {
               return false;
            }

            Object this$fidutoken = this.getFidutoken();
            Object other$fidutoken = other.getFidutoken();
            if (this$fidutoken == null) {
               if (other$fidutoken != null) {
                  return false;
               }
            } else if (!this$fidutoken.equals(other$fidutoken)) {
               return false;
            }

            Object this$usuarioSession = this.getUsuarioSession();
            Object other$usuarioSession = other.getUsuarioSession();
            if (this$usuarioSession == null) {
               if (other$usuarioSession != null) {
                  return false;
               }
            } else if (!this$usuarioSession.equals(other$usuarioSession)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UsuarioPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + (this.isMapped() ? 79 : 97);
      result = result * 59 + (this.isSoloLectura() ? 79 : 97);
      result = result * 59 + (this.isBorrable() ? 79 : 97);
      long $fechaCreacion = this.getFechaCreacion();
      result = result * 59 + (int)($fechaCreacion >>> 32 ^ $fechaCreacion);
      long $fechaModificacion = this.getFechaModificacion();
      result = result * 59 + (int)($fechaModificacion >>> 32 ^ $fechaModificacion);
      result = result * 59 + (this.isUsuarioSalaVentas() ? 79 : 97);
      Object $atributos = this.getAtributos();
      result = result * 59 + ($atributos == null ? 43 : $atributos.hashCode());
      Object $idPrincipal = this.getIdPrincipal();
      result = result * 59 + ($idPrincipal == null ? 43 : $idPrincipal.hashCode());
      Object $usuario = this.getUsuario();
      result = result * 59 + ($usuario == null ? 43 : $usuario.hashCode());
      Object $tipoPrincipal = this.getTipoPrincipal();
      result = result * 59 + ($tipoPrincipal == null ? 43 : $tipoPrincipal.hashCode());
      Object $nombrePrincipal = this.getNombrePrincipal();
      result = result * 59 + ($nombrePrincipal == null ? 43 : $nombrePrincipal.hashCode());
      Object $password = this.getPassword();
      result = result * 59 + ($password == null ? 43 : $password.hashCode());
      Object $newPassword = this.getNewPassword();
      result = result * 59 + ($newPassword == null ? 43 : $newPassword.hashCode());
      Object $idDominio = this.getIdDominio();
      result = result * 59 + ($idDominio == null ? 43 : $idDominio.hashCode());
      Object $estado = this.getEstado();
      result = result * 59 + ($estado == null ? 43 : $estado.hashCode());
      Object $nombreUsuario = this.getNombreUsuario();
      result = result * 59 + ($nombreUsuario == null ? 43 : $nombreUsuario.hashCode());
      Object $primerNombre = this.getPrimerNombre();
      result = result * 59 + ($primerNombre == null ? 43 : $primerNombre.hashCode());
      Object $segundoNombre = this.getSegundoNombre();
      result = result * 59 + ($segundoNombre == null ? 43 : $segundoNombre.hashCode());
      Object $primerApellido = this.getPrimerApellido();
      result = result * 59 + ($primerApellido == null ? 43 : $primerApellido.hashCode());
      Object $segundoApellido = this.getSegundoApellido();
      result = result * 59 + ($segundoApellido == null ? 43 : $segundoApellido.hashCode());
      Object $tipoIdentificacion = this.getTipoIdentificacion();
      result = result * 59 + ($tipoIdentificacion == null ? 43 : $tipoIdentificacion.hashCode());
      Object $identificacion = this.getIdentificacion();
      result = result * 59 + ($identificacion == null ? 43 : $identificacion.hashCode());
      Object $digitoVerificacion = this.getDigitoVerificacion();
      result = result * 59 + ($digitoVerificacion == null ? 43 : $digitoVerificacion.hashCode());
      Object $correoElectronico = this.getCorreoElectronico();
      result = result * 59 + ($correoElectronico == null ? 43 : $correoElectronico.hashCode());
      Object $numeroCelular = this.getNumeroCelular();
      result = result * 59 + ($numeroCelular == null ? 43 : $numeroCelular.hashCode());
      Object $numeroTelefono = this.getNumeroTelefono();
      result = result * 59 + ($numeroTelefono == null ? 43 : $numeroTelefono.hashCode());
      Object $lugarFechaNacimiento = this.getLugarFechaNacimiento();
      result = result * 59 + ($lugarFechaNacimiento == null ? 43 : $lugarFechaNacimiento.hashCode());
      Object $direccionDomicilio = this.getDireccionDomicilio();
      result = result * 59 + ($direccionDomicilio == null ? 43 : $direccionDomicilio.hashCode());
      Object $tieneDiscapacidad = this.getTieneDiscapacidad();
      result = result * 59 + ($tieneDiscapacidad == null ? 43 : $tieneDiscapacidad.hashCode());
      Object $actividadEconomica = this.getActividadEconomica();
      result = result * 59 + ($actividadEconomica == null ? 43 : $actividadEconomica.hashCode());
      Object $actividadEconomicaPpla = this.getActividadEconomicaPpla();
      result = result * 59 + ($actividadEconomicaPpla == null ? 43 : $actividadEconomicaPpla.hashCode());
      Object $pep = this.getPep();
      result = result * 59 + ($pep == null ? 43 : $pep.hashCode());
      Object $ingresos = this.getIngresos();
      result = result * 59 + ($ingresos == null ? 43 : $ingresos.hashCode());
      Object $egresos = this.getEgresos();
      result = result * 59 + ($egresos == null ? 43 : $egresos.hashCode());
      Object $pasivos = this.getPasivos();
      result = result * 59 + ($pasivos == null ? 43 : $pasivos.hashCode());
      Object $activos = this.getActivos();
      result = result * 59 + ($activos == null ? 43 : $activos.hashCode());
      Object $patrimonio = this.getPatrimonio();
      result = result * 59 + ($patrimonio == null ? 43 : $patrimonio.hashCode());
      Object $sistemaOperativoCelular = this.getSistemaOperativoCelular();
      result = result * 59 + ($sistemaOperativoCelular == null ? 43 : $sistemaOperativoCelular.hashCode());
      Object $tipoDocumento = this.getTipoDocumento();
      result = result * 59 + ($tipoDocumento == null ? 43 : $tipoDocumento.hashCode());
      Object $tipoUsuarioSalaventas = this.getTipoUsuarioSalaventas();
      result = result * 59 + ($tipoUsuarioSalaventas == null ? 43 : $tipoUsuarioSalaventas.hashCode());
      Object $fidutoken = this.getFidutoken();
      result = result * 59 + ($fidutoken == null ? 43 : $fidutoken.hashCode());
      Object $usuarioSession = this.getUsuarioSession();
      result = result * 59 + ($usuarioSession == null ? 43 : $usuarioSession.hashCode());
      return result;
   }

   public String toString() {
      return "UsuarioPojo(atributos=" + this.getAtributos() + ", idPrincipal=" + this.getIdPrincipal() + ", usuario=" + this.getUsuario() + ", tipoPrincipal=" + this.getTipoPrincipal() + ", nombrePrincipal=" + this.getNombrePrincipal() + ", password=" + this.getPassword() + ", newPassword=" + this.getNewPassword() + ", mapped=" + this.isMapped() + ", soloLectura=" + this.isSoloLectura() + ", borrable=" + this.isBorrable() + ", fechaCreacion=" + this.getFechaCreacion() + ", fechaModificacion=" + this.getFechaModificacion() + ", idDominio=" + this.getIdDominio() + ", estado=" + this.getEstado() + ", nombreUsuario=" + this.getNombreUsuario() + ", primerNombre=" + this.getPrimerNombre() + ", segundoNombre=" + this.getSegundoNombre() + ", primerApellido=" + this.getPrimerApellido() + ", segundoApellido=" + this.getSegundoApellido() + ", tipoIdentificacion=" + this.getTipoIdentificacion() + ", identificacion=" + this.getIdentificacion() + ", digitoVerificacion=" + this.getDigitoVerificacion() + ", correoElectronico=" + this.getCorreoElectronico() + ", numeroCelular=" + this.getNumeroCelular() + ", numeroTelefono=" + this.getNumeroTelefono() + ", lugarFechaNacimiento=" + this.getLugarFechaNacimiento() + ", direccionDomicilio=" + this.getDireccionDomicilio() + ", tieneDiscapacidad=" + this.getTieneDiscapacidad() + ", actividadEconomica=" + this.getActividadEconomica() + ", actividadEconomicaPpla=" + this.getActividadEconomicaPpla() + ", pep=" + this.getPep() + ", ingresos=" + this.getIngresos() + ", egresos=" + this.getEgresos() + ", pasivos=" + this.getPasivos() + ", activos=" + this.getActivos() + ", patrimonio=" + this.getPatrimonio() + ", sistemaOperativoCelular=" + this.getSistemaOperativoCelular() + ", tipoDocumento=" + this.getTipoDocumento() + ", usuarioSalaVentas=" + this.isUsuarioSalaVentas() + ", tipoUsuarioSalaventas=" + this.getTipoUsuarioSalaventas() + ", fidutoken=" + this.getFidutoken() + ", usuarioSession=" + this.getUsuarioSession() + ")";
   }
}
