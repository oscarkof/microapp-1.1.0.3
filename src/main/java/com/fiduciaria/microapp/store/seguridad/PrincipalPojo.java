// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.seguridad;

import com.fiduciaria.microapp.store.model.EnumRolePrincipal;
import com.fiduciaria.microapp.store.model.EnumRoleUsuario;
import java.util.Arrays;
import org.apache.wicket.util.io.IClusterable;

public class PrincipalPojo implements IClusterable {
   private final CredencialPojo credencial = new CredencialPojo();
   private EstadoUsuario estado;
   private String loginUsuario;
   private String usuario;
   private String primerNombre;
   private String segundoNombre;
   private String primerApellido;
   private String segundoApellido;
   private String email;
   private String descripcion;
   private EnumTipoUsuario tipoUsuario;
   private String permisoWeb;
   private transient byte[] firma;
   private Integer tipo;
   private String firmaUrl;
   private EnumRoleUsuario rol;
   private EnumRolePrincipal rolPrincipal;
   private String codigo;
   private String nombre;
   private boolean activo;
   private boolean requiereCambioCredencial;
   private boolean primerIngreso;
   private boolean externo;
   private boolean funcionario;
   private boolean sistema;
   private String rolePrincipal;
   private String rolFondosAlfa;

   public PrincipalPojo() {
   }

   public CredencialPojo getCredencial() {
      return this.credencial;
   }

   public EstadoUsuario getEstado() {
      return this.estado;
   }

   public String getLoginUsuario() {
      return this.loginUsuario;
   }

   public String getUsuario() {
      return this.usuario;
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

   public String getDescripcion() {
      return this.descripcion;
   }

   public EnumTipoUsuario getTipoUsuario() {
      return this.tipoUsuario;
   }

   public String getPermisoWeb() {
      return this.permisoWeb;
   }

   public byte[] getFirma() {
      return this.firma;
   }

   public Integer getTipo() {
      return this.tipo;
   }

   public String getFirmaUrl() {
      return this.firmaUrl;
   }

   public EnumRoleUsuario getRol() {
      return this.rol;
   }

   public EnumRolePrincipal getRolPrincipal() {
      return this.rolPrincipal;
   }

   public String getCodigo() {
      return this.codigo;
   }

   public String getNombre() {
      return this.nombre;
   }

   public boolean isActivo() {
      return this.activo;
   }

   public boolean isRequiereCambioCredencial() {
      return this.requiereCambioCredencial;
   }

   public boolean isPrimerIngreso() {
      return this.primerIngreso;
   }

   public boolean isExterno() {
      return this.externo;
   }

   public boolean isFuncionario() {
      return this.funcionario;
   }

   public boolean isSistema() {
      return this.sistema;
   }

   public String getRolePrincipal() {
      return this.rolePrincipal;
   }

   public String getRolFondosAlfa() {
      return this.rolFondosAlfa;
   }

   public void setEstado(EstadoUsuario estado) {
      this.estado = estado;
   }

   public void setLoginUsuario(String loginUsuario) {
      this.loginUsuario = loginUsuario;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
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

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public void setTipoUsuario(EnumTipoUsuario tipoUsuario) {
      this.tipoUsuario = tipoUsuario;
   }

   public void setPermisoWeb(String permisoWeb) {
      this.permisoWeb = permisoWeb;
   }

   public void setFirma(byte[] firma) {
      this.firma = firma;
   }

   public void setTipo(Integer tipo) {
      this.tipo = tipo;
   }

   public void setFirmaUrl(String firmaUrl) {
      this.firmaUrl = firmaUrl;
   }

   public void setRol(EnumRoleUsuario rol) {
      this.rol = rol;
   }

   public void setRolPrincipal(EnumRolePrincipal rolPrincipal) {
      this.rolPrincipal = rolPrincipal;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public void setActivo(boolean activo) {
      this.activo = activo;
   }

   public void setRequiereCambioCredencial(boolean requiereCambioCredencial) {
      this.requiereCambioCredencial = requiereCambioCredencial;
   }

   public void setPrimerIngreso(boolean primerIngreso) {
      this.primerIngreso = primerIngreso;
   }

   public void setExterno(boolean externo) {
      this.externo = externo;
   }

   public void setFuncionario(boolean funcionario) {
      this.funcionario = funcionario;
   }

   public void setSistema(boolean sistema) {
      this.sistema = sistema;
   }

   public void setRolePrincipal(String rolePrincipal) {
      this.rolePrincipal = rolePrincipal;
   }

   public void setRolFondosAlfa(String rolFondosAlfa) {
      this.rolFondosAlfa = rolFondosAlfa;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof PrincipalPojo)) {
         return false;
      } else {
         PrincipalPojo other = (PrincipalPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isActivo() != other.isActivo()) {
            return false;
         } else if (this.isRequiereCambioCredencial() != other.isRequiereCambioCredencial()) {
            return false;
         } else if (this.isPrimerIngreso() != other.isPrimerIngreso()) {
            return false;
         } else if (this.isExterno() != other.isExterno()) {
            return false;
         } else if (this.isFuncionario() != other.isFuncionario()) {
            return false;
         } else if (this.isSistema() != other.isSistema()) {
            return false;
         } else {
            label266: {
               Object this$tipo = this.getTipo();
               Object other$tipo = other.getTipo();
               if (this$tipo == null) {
                  if (other$tipo == null) {
                     break label266;
                  }
               } else if (this$tipo.equals(other$tipo)) {
                  break label266;
               }

               return false;
            }

            Object this$credencial = this.getCredencial();
            Object other$credencial = other.getCredencial();
            if (this$credencial == null) {
               if (other$credencial != null) {
                  return false;
               }
            } else if (!this$credencial.equals(other$credencial)) {
               return false;
            }

            Object this$estado = this.getEstado();
            Object other$estado = other.getEstado();
            if (this$estado == null) {
               if (other$estado != null) {
                  return false;
               }
            } else if (!this$estado.equals(other$estado)) {
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

            label238: {
               Object this$usuario = this.getUsuario();
               Object other$usuario = other.getUsuario();
               if (this$usuario == null) {
                  if (other$usuario == null) {
                     break label238;
                  }
               } else if (this$usuario.equals(other$usuario)) {
                  break label238;
               }

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

            Object this$segundoNombre = this.getSegundoNombre();
            Object other$segundoNombre = other.getSegundoNombre();
            if (this$segundoNombre == null) {
               if (other$segundoNombre != null) {
                  return false;
               }
            } else if (!this$segundoNombre.equals(other$segundoNombre)) {
               return false;
            }

            label217: {
               Object this$primerApellido = this.getPrimerApellido();
               Object other$primerApellido = other.getPrimerApellido();
               if (this$primerApellido == null) {
                  if (other$primerApellido == null) {
                     break label217;
                  }
               } else if (this$primerApellido.equals(other$primerApellido)) {
                  break label217;
               }

               return false;
            }

            label210: {
               Object this$segundoApellido = this.getSegundoApellido();
               Object other$segundoApellido = other.getSegundoApellido();
               if (this$segundoApellido == null) {
                  if (other$segundoApellido == null) {
                     break label210;
                  }
               } else if (this$segundoApellido.equals(other$segundoApellido)) {
                  break label210;
               }

               return false;
            }

            Object this$email = this.getEmail();
            Object other$email = other.getEmail();
            if (this$email == null) {
               if (other$email != null) {
                  return false;
               }
            } else if (!this$email.equals(other$email)) {
               return false;
            }

            Object this$descripcion = this.getDescripcion();
            Object other$descripcion = other.getDescripcion();
            if (this$descripcion == null) {
               if (other$descripcion != null) {
                  return false;
               }
            } else if (!this$descripcion.equals(other$descripcion)) {
               return false;
            }

            label189: {
               Object this$tipoUsuario = this.getTipoUsuario();
               Object other$tipoUsuario = other.getTipoUsuario();
               if (this$tipoUsuario == null) {
                  if (other$tipoUsuario == null) {
                     break label189;
                  }
               } else if (this$tipoUsuario.equals(other$tipoUsuario)) {
                  break label189;
               }

               return false;
            }

            label182: {
               Object this$permisoWeb = this.getPermisoWeb();
               Object other$permisoWeb = other.getPermisoWeb();
               if (this$permisoWeb == null) {
                  if (other$permisoWeb == null) {
                     break label182;
                  }
               } else if (this$permisoWeb.equals(other$permisoWeb)) {
                  break label182;
               }

               return false;
            }

            Object this$firmaUrl = this.getFirmaUrl();
            Object other$firmaUrl = other.getFirmaUrl();
            if (this$firmaUrl == null) {
               if (other$firmaUrl != null) {
                  return false;
               }
            } else if (!this$firmaUrl.equals(other$firmaUrl)) {
               return false;
            }

            label168: {
               Object this$rol = this.getRol();
               Object other$rol = other.getRol();
               if (this$rol == null) {
                  if (other$rol == null) {
                     break label168;
                  }
               } else if (this$rol.equals(other$rol)) {
                  break label168;
               }

               return false;
            }

            Object this$rolPrincipal = this.getRolPrincipal();
            Object other$rolPrincipal = other.getRolPrincipal();
            if (this$rolPrincipal == null) {
               if (other$rolPrincipal != null) {
                  return false;
               }
            } else if (!this$rolPrincipal.equals(other$rolPrincipal)) {
               return false;
            }

            label154: {
               Object this$codigo = this.getCodigo();
               Object other$codigo = other.getCodigo();
               if (this$codigo == null) {
                  if (other$codigo == null) {
                     break label154;
                  }
               } else if (this$codigo.equals(other$codigo)) {
                  break label154;
               }

               return false;
            }

            Object this$nombre = this.getNombre();
            Object other$nombre = other.getNombre();
            if (this$nombre == null) {
               if (other$nombre != null) {
                  return false;
               }
            } else if (!this$nombre.equals(other$nombre)) {
               return false;
            }

            Object this$rolePrincipal = this.getRolePrincipal();
            Object other$rolePrincipal = other.getRolePrincipal();
            if (this$rolePrincipal == null) {
               if (other$rolePrincipal != null) {
                  return false;
               }
            } else if (!this$rolePrincipal.equals(other$rolePrincipal)) {
               return false;
            }

            Object this$rolFondosAlfa = this.getRolFondosAlfa();
            Object other$rolFondosAlfa = other.getRolFondosAlfa();
            if (this$rolFondosAlfa == null) {
               if (other$rolFondosAlfa != null) {
                  return false;
               }
            } else if (!this$rolFondosAlfa.equals(other$rolFondosAlfa)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof PrincipalPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + (this.isActivo() ? 79 : 97);
      result = result * 59 + (this.isRequiereCambioCredencial() ? 79 : 97);
      result = result * 59 + (this.isPrimerIngreso() ? 79 : 97);
      result = result * 59 + (this.isExterno() ? 79 : 97);
      result = result * 59 + (this.isFuncionario() ? 79 : 97);
      result = result * 59 + (this.isSistema() ? 79 : 97);
      Object $tipo = this.getTipo();
      result = result * 59 + ($tipo == null ? 43 : $tipo.hashCode());
      Object $credencial = this.getCredencial();
      result = result * 59 + ($credencial == null ? 43 : $credencial.hashCode());
      Object $estado = this.getEstado();
      result = result * 59 + ($estado == null ? 43 : $estado.hashCode());
      Object $loginUsuario = this.getLoginUsuario();
      result = result * 59 + ($loginUsuario == null ? 43 : $loginUsuario.hashCode());
      Object $usuario = this.getUsuario();
      result = result * 59 + ($usuario == null ? 43 : $usuario.hashCode());
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
      Object $descripcion = this.getDescripcion();
      result = result * 59 + ($descripcion == null ? 43 : $descripcion.hashCode());
      Object $tipoUsuario = this.getTipoUsuario();
      result = result * 59 + ($tipoUsuario == null ? 43 : $tipoUsuario.hashCode());
      Object $permisoWeb = this.getPermisoWeb();
      result = result * 59 + ($permisoWeb == null ? 43 : $permisoWeb.hashCode());
      Object $firmaUrl = this.getFirmaUrl();
      result = result * 59 + ($firmaUrl == null ? 43 : $firmaUrl.hashCode());
      Object $rol = this.getRol();
      result = result * 59 + ($rol == null ? 43 : $rol.hashCode());
      Object $rolPrincipal = this.getRolPrincipal();
      result = result * 59 + ($rolPrincipal == null ? 43 : $rolPrincipal.hashCode());
      Object $codigo = this.getCodigo();
      result = result * 59 + ($codigo == null ? 43 : $codigo.hashCode());
      Object $nombre = this.getNombre();
      result = result * 59 + ($nombre == null ? 43 : $nombre.hashCode());
      Object $rolePrincipal = this.getRolePrincipal();
      result = result * 59 + ($rolePrincipal == null ? 43 : $rolePrincipal.hashCode());
      Object $rolFondosAlfa = this.getRolFondosAlfa();
      result = result * 59 + ($rolFondosAlfa == null ? 43 : $rolFondosAlfa.hashCode());
      return result;
   }

   public String toString() {
      return "PrincipalPojo(credencial=" + this.getCredencial() + ", estado=" + this.getEstado() + ", loginUsuario=" + this.getLoginUsuario() + ", usuario=" + this.getUsuario() + ", primerNombre=" + this.getPrimerNombre() + ", segundoNombre=" + this.getSegundoNombre() + ", primerApellido=" + this.getPrimerApellido() + ", segundoApellido=" + this.getSegundoApellido() + ", email=" + this.getEmail() + ", descripcion=" + this.getDescripcion() + ", tipoUsuario=" + this.getTipoUsuario() + ", permisoWeb=" + this.getPermisoWeb() + ", firma=" + Arrays.toString(this.getFirma()) + ", tipo=" + this.getTipo() + ", firmaUrl=" + this.getFirmaUrl() + ", rol=" + this.getRol() + ", rolPrincipal=" + this.getRolPrincipal() + ", codigo=" + this.getCodigo() + ", nombre=" + this.getNombre() + ", activo=" + this.isActivo() + ", requiereCambioCredencial=" + this.isRequiereCambioCredencial() + ", primerIngreso=" + this.isPrimerIngreso() + ", externo=" + this.isExterno() + ", funcionario=" + this.isFuncionario() + ", sistema=" + this.isSistema() + ", rolePrincipal=" + this.getRolePrincipal() + ", rolFondosAlfa=" + this.getRolFondosAlfa() + ")";
   }
}
