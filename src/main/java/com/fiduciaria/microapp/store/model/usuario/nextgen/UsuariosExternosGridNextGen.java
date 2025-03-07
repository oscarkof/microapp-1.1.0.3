// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.usuario.nextgen;

public class UsuariosExternosGridNextGen {
   private Integer codigoGrupo;
   private String codigousuario;
   private String nombre;
   private String segundonombre;
   private String primerapellido;
   private String segundoapellido;
   private String email;
   private String telefono;
   private String tipoidentificacion;
   private String identificacion;
   private String estado;
   private Boolean isselected;
   private String resultadooperacion;
   private String claveactual;
   private String clavenueva;
   private String confirmarclave;
   private String descripcionusuario;
   private String passWordValue;
   private String fidutoken;
   private String origen;
   private String subject;
   private String esusuariosalaventas;
   private String rolsalaventas;
   private String usuarioAdmondominio;
   private boolean ucaUsuarioCreador;

   public UsuariosExternosGridNextGen() {
   }

   public Integer getCodigoGrupo() {
      return this.codigoGrupo;
   }

   public String getCodigousuario() {
      return this.codigousuario;
   }

   public String getNombre() {
      return this.nombre;
   }

   public String getSegundonombre() {
      return this.segundonombre;
   }

   public String getPrimerapellido() {
      return this.primerapellido;
   }

   public String getSegundoapellido() {
      return this.segundoapellido;
   }

   public String getEmail() {
      return this.email;
   }

   public String getTelefono() {
      return this.telefono;
   }

   public String getTipoidentificacion() {
      return this.tipoidentificacion;
   }

   public String getIdentificacion() {
      return this.identificacion;
   }

   public String getEstado() {
      return this.estado;
   }

   public Boolean getIsselected() {
      return this.isselected;
   }

   public String getResultadooperacion() {
      return this.resultadooperacion;
   }

   public String getClaveactual() {
      return this.claveactual;
   }

   public String getClavenueva() {
      return this.clavenueva;
   }

   public String getConfirmarclave() {
      return this.confirmarclave;
   }

   public String getDescripcionusuario() {
      return this.descripcionusuario;
   }

   public String getPassWordValue() {
      return this.passWordValue;
   }

   public String getFidutoken() {
      return this.fidutoken;
   }

   public String getOrigen() {
      return this.origen;
   }

   public String getSubject() {
      return this.subject;
   }

   public String getEsusuariosalaventas() {
      return this.esusuariosalaventas;
   }

   public String getRolsalaventas() {
      return this.rolsalaventas;
   }

   public String getUsuarioAdmondominio() {
      return this.usuarioAdmondominio;
   }

   public boolean isUcaUsuarioCreador() {
      return this.ucaUsuarioCreador;
   }

   public void setCodigoGrupo(Integer codigoGrupo) {
      this.codigoGrupo = codigoGrupo;
   }

   public void setCodigousuario(String codigousuario) {
      this.codigousuario = codigousuario;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public void setSegundonombre(String segundonombre) {
      this.segundonombre = segundonombre;
   }

   public void setPrimerapellido(String primerapellido) {
      this.primerapellido = primerapellido;
   }

   public void setSegundoapellido(String segundoapellido) {
      this.segundoapellido = segundoapellido;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setTelefono(String telefono) {
      this.telefono = telefono;
   }

   public void setTipoidentificacion(String tipoidentificacion) {
      this.tipoidentificacion = tipoidentificacion;
   }

   public void setIdentificacion(String identificacion) {
      this.identificacion = identificacion;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   public void setIsselected(Boolean isselected) {
      this.isselected = isselected;
   }

   public void setResultadooperacion(String resultadooperacion) {
      this.resultadooperacion = resultadooperacion;
   }

   public void setClaveactual(String claveactual) {
      this.claveactual = claveactual;
   }

   public void setClavenueva(String clavenueva) {
      this.clavenueva = clavenueva;
   }

   public void setConfirmarclave(String confirmarclave) {
      this.confirmarclave = confirmarclave;
   }

   public void setDescripcionusuario(String descripcionusuario) {
      this.descripcionusuario = descripcionusuario;
   }

   public void setPassWordValue(String passWordValue) {
      this.passWordValue = passWordValue;
   }

   public void setFidutoken(String fidutoken) {
      this.fidutoken = fidutoken;
   }

   public void setOrigen(String origen) {
      this.origen = origen;
   }

   public void setSubject(String subject) {
      this.subject = subject;
   }

   public void setEsusuariosalaventas(String esusuariosalaventas) {
      this.esusuariosalaventas = esusuariosalaventas;
   }

   public void setRolsalaventas(String rolsalaventas) {
      this.rolsalaventas = rolsalaventas;
   }

   public void setUsuarioAdmondominio(String usuarioAdmondominio) {
      this.usuarioAdmondominio = usuarioAdmondominio;
   }

   public void setUcaUsuarioCreador(boolean ucaUsuarioCreador) {
      this.ucaUsuarioCreador = ucaUsuarioCreador;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof UsuariosExternosGridNextGen)) {
         return false;
      } else {
         UsuariosExternosGridNextGen other = (UsuariosExternosGridNextGen)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isUcaUsuarioCreador() != other.isUcaUsuarioCreador()) {
            return false;
         } else {
            label301: {
               Object this$codigoGrupo = this.getCodigoGrupo();
               Object other$codigoGrupo = other.getCodigoGrupo();
               if (this$codigoGrupo == null) {
                  if (other$codigoGrupo == null) {
                     break label301;
                  }
               } else if (this$codigoGrupo.equals(other$codigoGrupo)) {
                  break label301;
               }

               return false;
            }

            label294: {
               Object this$isselected = this.getIsselected();
               Object other$isselected = other.getIsselected();
               if (this$isselected == null) {
                  if (other$isselected == null) {
                     break label294;
                  }
               } else if (this$isselected.equals(other$isselected)) {
                  break label294;
               }

               return false;
            }

            Object this$codigousuario = this.getCodigousuario();
            Object other$codigousuario = other.getCodigousuario();
            if (this$codigousuario == null) {
               if (other$codigousuario != null) {
                  return false;
               }
            } else if (!this$codigousuario.equals(other$codigousuario)) {
               return false;
            }

            label280: {
               Object this$nombre = this.getNombre();
               Object other$nombre = other.getNombre();
               if (this$nombre == null) {
                  if (other$nombre == null) {
                     break label280;
                  }
               } else if (this$nombre.equals(other$nombre)) {
                  break label280;
               }

               return false;
            }

            Object this$segundonombre = this.getSegundonombre();
            Object other$segundonombre = other.getSegundonombre();
            if (this$segundonombre == null) {
               if (other$segundonombre != null) {
                  return false;
               }
            } else if (!this$segundonombre.equals(other$segundonombre)) {
               return false;
            }

            label266: {
               Object this$primerapellido = this.getPrimerapellido();
               Object other$primerapellido = other.getPrimerapellido();
               if (this$primerapellido == null) {
                  if (other$primerapellido == null) {
                     break label266;
                  }
               } else if (this$primerapellido.equals(other$primerapellido)) {
                  break label266;
               }

               return false;
            }

            Object this$segundoapellido = this.getSegundoapellido();
            Object other$segundoapellido = other.getSegundoapellido();
            if (this$segundoapellido == null) {
               if (other$segundoapellido != null) {
                  return false;
               }
            } else if (!this$segundoapellido.equals(other$segundoapellido)) {
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

            Object this$telefono = this.getTelefono();
            Object other$telefono = other.getTelefono();
            if (this$telefono == null) {
               if (other$telefono != null) {
                  return false;
               }
            } else if (!this$telefono.equals(other$telefono)) {
               return false;
            }

            label238: {
               Object this$tipoidentificacion = this.getTipoidentificacion();
               Object other$tipoidentificacion = other.getTipoidentificacion();
               if (this$tipoidentificacion == null) {
                  if (other$tipoidentificacion == null) {
                     break label238;
                  }
               } else if (this$tipoidentificacion.equals(other$tipoidentificacion)) {
                  break label238;
               }

               return false;
            }

            label231: {
               Object this$identificacion = this.getIdentificacion();
               Object other$identificacion = other.getIdentificacion();
               if (this$identificacion == null) {
                  if (other$identificacion == null) {
                     break label231;
                  }
               } else if (this$identificacion.equals(other$identificacion)) {
                  break label231;
               }

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

            label217: {
               Object this$resultadooperacion = this.getResultadooperacion();
               Object other$resultadooperacion = other.getResultadooperacion();
               if (this$resultadooperacion == null) {
                  if (other$resultadooperacion == null) {
                     break label217;
                  }
               } else if (this$resultadooperacion.equals(other$resultadooperacion)) {
                  break label217;
               }

               return false;
            }

            label210: {
               Object this$claveactual = this.getClaveactual();
               Object other$claveactual = other.getClaveactual();
               if (this$claveactual == null) {
                  if (other$claveactual == null) {
                     break label210;
                  }
               } else if (this$claveactual.equals(other$claveactual)) {
                  break label210;
               }

               return false;
            }

            Object this$clavenueva = this.getClavenueva();
            Object other$clavenueva = other.getClavenueva();
            if (this$clavenueva == null) {
               if (other$clavenueva != null) {
                  return false;
               }
            } else if (!this$clavenueva.equals(other$clavenueva)) {
               return false;
            }

            Object this$confirmarclave = this.getConfirmarclave();
            Object other$confirmarclave = other.getConfirmarclave();
            if (this$confirmarclave == null) {
               if (other$confirmarclave != null) {
                  return false;
               }
            } else if (!this$confirmarclave.equals(other$confirmarclave)) {
               return false;
            }

            label189: {
               Object this$descripcionusuario = this.getDescripcionusuario();
               Object other$descripcionusuario = other.getDescripcionusuario();
               if (this$descripcionusuario == null) {
                  if (other$descripcionusuario == null) {
                     break label189;
                  }
               } else if (this$descripcionusuario.equals(other$descripcionusuario)) {
                  break label189;
               }

               return false;
            }

            label182: {
               Object this$passWordValue = this.getPassWordValue();
               Object other$passWordValue = other.getPassWordValue();
               if (this$passWordValue == null) {
                  if (other$passWordValue == null) {
                     break label182;
                  }
               } else if (this$passWordValue.equals(other$passWordValue)) {
                  break label182;
               }

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

            label168: {
               Object this$origen = this.getOrigen();
               Object other$origen = other.getOrigen();
               if (this$origen == null) {
                  if (other$origen == null) {
                     break label168;
                  }
               } else if (this$origen.equals(other$origen)) {
                  break label168;
               }

               return false;
            }

            Object this$subject = this.getSubject();
            Object other$subject = other.getSubject();
            if (this$subject == null) {
               if (other$subject != null) {
                  return false;
               }
            } else if (!this$subject.equals(other$subject)) {
               return false;
            }

            label154: {
               Object this$esusuariosalaventas = this.getEsusuariosalaventas();
               Object other$esusuariosalaventas = other.getEsusuariosalaventas();
               if (this$esusuariosalaventas == null) {
                  if (other$esusuariosalaventas == null) {
                     break label154;
                  }
               } else if (this$esusuariosalaventas.equals(other$esusuariosalaventas)) {
                  break label154;
               }

               return false;
            }

            Object this$rolsalaventas = this.getRolsalaventas();
            Object other$rolsalaventas = other.getRolsalaventas();
            if (this$rolsalaventas == null) {
               if (other$rolsalaventas != null) {
                  return false;
               }
            } else if (!this$rolsalaventas.equals(other$rolsalaventas)) {
               return false;
            }

            Object this$usuarioAdmondominio = this.getUsuarioAdmondominio();
            Object other$usuarioAdmondominio = other.getUsuarioAdmondominio();
            if (this$usuarioAdmondominio == null) {
               if (other$usuarioAdmondominio != null) {
                  return false;
               }
            } else if (!this$usuarioAdmondominio.equals(other$usuarioAdmondominio)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof UsuariosExternosGridNextGen;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + (this.isUcaUsuarioCreador() ? 79 : 97);
      Object $codigoGrupo = this.getCodigoGrupo();
      result = result * 59 + ($codigoGrupo == null ? 43 : $codigoGrupo.hashCode());
      Object $isselected = this.getIsselected();
      result = result * 59 + ($isselected == null ? 43 : $isselected.hashCode());
      Object $codigousuario = this.getCodigousuario();
      result = result * 59 + ($codigousuario == null ? 43 : $codigousuario.hashCode());
      Object $nombre = this.getNombre();
      result = result * 59 + ($nombre == null ? 43 : $nombre.hashCode());
      Object $segundonombre = this.getSegundonombre();
      result = result * 59 + ($segundonombre == null ? 43 : $segundonombre.hashCode());
      Object $primerapellido = this.getPrimerapellido();
      result = result * 59 + ($primerapellido == null ? 43 : $primerapellido.hashCode());
      Object $segundoapellido = this.getSegundoapellido();
      result = result * 59 + ($segundoapellido == null ? 43 : $segundoapellido.hashCode());
      Object $email = this.getEmail();
      result = result * 59 + ($email == null ? 43 : $email.hashCode());
      Object $telefono = this.getTelefono();
      result = result * 59 + ($telefono == null ? 43 : $telefono.hashCode());
      Object $tipoidentificacion = this.getTipoidentificacion();
      result = result * 59 + ($tipoidentificacion == null ? 43 : $tipoidentificacion.hashCode());
      Object $identificacion = this.getIdentificacion();
      result = result * 59 + ($identificacion == null ? 43 : $identificacion.hashCode());
      Object $estado = this.getEstado();
      result = result * 59 + ($estado == null ? 43 : $estado.hashCode());
      Object $resultadooperacion = this.getResultadooperacion();
      result = result * 59 + ($resultadooperacion == null ? 43 : $resultadooperacion.hashCode());
      Object $claveactual = this.getClaveactual();
      result = result * 59 + ($claveactual == null ? 43 : $claveactual.hashCode());
      Object $clavenueva = this.getClavenueva();
      result = result * 59 + ($clavenueva == null ? 43 : $clavenueva.hashCode());
      Object $confirmarclave = this.getConfirmarclave();
      result = result * 59 + ($confirmarclave == null ? 43 : $confirmarclave.hashCode());
      Object $descripcionusuario = this.getDescripcionusuario();
      result = result * 59 + ($descripcionusuario == null ? 43 : $descripcionusuario.hashCode());
      Object $passWordValue = this.getPassWordValue();
      result = result * 59 + ($passWordValue == null ? 43 : $passWordValue.hashCode());
      Object $fidutoken = this.getFidutoken();
      result = result * 59 + ($fidutoken == null ? 43 : $fidutoken.hashCode());
      Object $origen = this.getOrigen();
      result = result * 59 + ($origen == null ? 43 : $origen.hashCode());
      Object $subject = this.getSubject();
      result = result * 59 + ($subject == null ? 43 : $subject.hashCode());
      Object $esusuariosalaventas = this.getEsusuariosalaventas();
      result = result * 59 + ($esusuariosalaventas == null ? 43 : $esusuariosalaventas.hashCode());
      Object $rolsalaventas = this.getRolsalaventas();
      result = result * 59 + ($rolsalaventas == null ? 43 : $rolsalaventas.hashCode());
      Object $usuarioAdmondominio = this.getUsuarioAdmondominio();
      result = result * 59 + ($usuarioAdmondominio == null ? 43 : $usuarioAdmondominio.hashCode());
      return result;
   }

   public String toString() {
      return "UsuariosExternosGridNextGen(codigoGrupo=" + this.getCodigoGrupo() + ", codigousuario=" + this.getCodigousuario() + ", nombre=" + this.getNombre() + ", segundonombre=" + this.getSegundonombre() + ", primerapellido=" + this.getPrimerapellido() + ", segundoapellido=" + this.getSegundoapellido() + ", email=" + this.getEmail() + ", telefono=" + this.getTelefono() + ", tipoidentificacion=" + this.getTipoidentificacion() + ", identificacion=" + this.getIdentificacion() + ", estado=" + this.getEstado() + ", isselected=" + this.getIsselected() + ", resultadooperacion=" + this.getResultadooperacion() + ", claveactual=" + this.getClaveactual() + ", clavenueva=" + this.getClavenueva() + ", confirmarclave=" + this.getConfirmarclave() + ", descripcionusuario=" + this.getDescripcionusuario() + ", passWordValue=" + this.getPassWordValue() + ", fidutoken=" + this.getFidutoken() + ", origen=" + this.getOrigen() + ", subject=" + this.getSubject() + ", esusuariosalaventas=" + this.getEsusuariosalaventas() + ", rolsalaventas=" + this.getRolsalaventas() + ", usuarioAdmondominio=" + this.getUsuarioAdmondominio() + ", ucaUsuarioCreador=" + this.isUcaUsuarioCreador() + ")";
   }
}
