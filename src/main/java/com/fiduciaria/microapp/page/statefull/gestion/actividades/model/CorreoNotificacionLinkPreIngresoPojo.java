// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.actividades.model;

import org.apache.wicket.util.io.IClusterable;

public class CorreoNotificacionLinkPreIngresoPojo implements IClusterable {
   private String correoDestino;
   private String asuntoTpl;
   private String cuerpoCorreoTpl;
   private String tokenId;
   private String jwtToken1;
   private String jwtToken2;
   private String jwtToken3;
   private String jwtToken4;
   private long vigenciaLink;
   private String usuario;
   private String codigoNegocio;
   private String tipoNegocio;
   private String descripcionNegocio;
   private String proceso;
   private String tokenK;
   private String uuid;
   private String tokenkey;
   private String tokenFormulario;
   private int diasValido = 7;

   public CorreoNotificacionLinkPreIngresoPojo() {
   }

   public String getCorreoDestino() {
      return this.correoDestino;
   }

   public String getAsuntoTpl() {
      return this.asuntoTpl;
   }

   public String getCuerpoCorreoTpl() {
      return this.cuerpoCorreoTpl;
   }

   public String getTokenId() {
      return this.tokenId;
   }

   public String getJwtToken1() {
      return this.jwtToken1;
   }

   public String getJwtToken2() {
      return this.jwtToken2;
   }

   public String getJwtToken3() {
      return this.jwtToken3;
   }

   public String getJwtToken4() {
      return this.jwtToken4;
   }

   public long getVigenciaLink() {
      return this.vigenciaLink;
   }

   public String getUsuario() {
      return this.usuario;
   }

   public String getCodigoNegocio() {
      return this.codigoNegocio;
   }

   public String getTipoNegocio() {
      return this.tipoNegocio;
   }

   public String getDescripcionNegocio() {
      return this.descripcionNegocio;
   }

   public String getProceso() {
      return this.proceso;
   }

   public String getTokenK() {
      return this.tokenK;
   }

   public String getUuid() {
      return this.uuid;
   }

   public String getTokenkey() {
      return this.tokenkey;
   }

   public String getTokenFormulario() {
      return this.tokenFormulario;
   }

   public int getDiasValido() {
      return this.diasValido;
   }

   public void setCorreoDestino(String correoDestino) {
      this.correoDestino = correoDestino;
   }

   public void setAsuntoTpl(String asuntoTpl) {
      this.asuntoTpl = asuntoTpl;
   }

   public void setCuerpoCorreoTpl(String cuerpoCorreoTpl) {
      this.cuerpoCorreoTpl = cuerpoCorreoTpl;
   }

   public void setTokenId(String tokenId) {
      this.tokenId = tokenId;
   }

   public void setJwtToken1(String jwtToken1) {
      this.jwtToken1 = jwtToken1;
   }

   public void setJwtToken2(String jwtToken2) {
      this.jwtToken2 = jwtToken2;
   }

   public void setJwtToken3(String jwtToken3) {
      this.jwtToken3 = jwtToken3;
   }

   public void setJwtToken4(String jwtToken4) {
      this.jwtToken4 = jwtToken4;
   }

   public void setVigenciaLink(long vigenciaLink) {
      this.vigenciaLink = vigenciaLink;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public void setCodigoNegocio(String codigoNegocio) {
      this.codigoNegocio = codigoNegocio;
   }

   public void setTipoNegocio(String tipoNegocio) {
      this.tipoNegocio = tipoNegocio;
   }

   public void setDescripcionNegocio(String descripcionNegocio) {
      this.descripcionNegocio = descripcionNegocio;
   }

   public void setProceso(String proceso) {
      this.proceso = proceso;
   }

   public void setTokenK(String tokenK) {
      this.tokenK = tokenK;
   }

   public void setUuid(String uuid) {
      this.uuid = uuid;
   }

   public void setTokenkey(String tokenkey) {
      this.tokenkey = tokenkey;
   }

   public void setTokenFormulario(String tokenFormulario) {
      this.tokenFormulario = tokenFormulario;
   }

   public void setDiasValido(int diasValido) {
      this.diasValido = diasValido;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CorreoNotificacionLinkPreIngresoPojo)) {
         return false;
      } else {
         CorreoNotificacionLinkPreIngresoPojo other = (CorreoNotificacionLinkPreIngresoPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getVigenciaLink() != other.getVigenciaLink()) {
            return false;
         } else if (this.getDiasValido() != other.getDiasValido()) {
            return false;
         } else {
            label220: {
               Object this$correoDestino = this.getCorreoDestino();
               Object other$correoDestino = other.getCorreoDestino();
               if (this$correoDestino == null) {
                  if (other$correoDestino == null) {
                     break label220;
                  }
               } else if (this$correoDestino.equals(other$correoDestino)) {
                  break label220;
               }

               return false;
            }

            Object this$asuntoTpl = this.getAsuntoTpl();
            Object other$asuntoTpl = other.getAsuntoTpl();
            if (this$asuntoTpl == null) {
               if (other$asuntoTpl != null) {
                  return false;
               }
            } else if (!this$asuntoTpl.equals(other$asuntoTpl)) {
               return false;
            }

            label206: {
               Object this$cuerpoCorreoTpl = this.getCuerpoCorreoTpl();
               Object other$cuerpoCorreoTpl = other.getCuerpoCorreoTpl();
               if (this$cuerpoCorreoTpl == null) {
                  if (other$cuerpoCorreoTpl == null) {
                     break label206;
                  }
               } else if (this$cuerpoCorreoTpl.equals(other$cuerpoCorreoTpl)) {
                  break label206;
               }

               return false;
            }

            label199: {
               Object this$tokenId = this.getTokenId();
               Object other$tokenId = other.getTokenId();
               if (this$tokenId == null) {
                  if (other$tokenId == null) {
                     break label199;
                  }
               } else if (this$tokenId.equals(other$tokenId)) {
                  break label199;
               }

               return false;
            }

            Object this$jwtToken1 = this.getJwtToken1();
            Object other$jwtToken1 = other.getJwtToken1();
            if (this$jwtToken1 == null) {
               if (other$jwtToken1 != null) {
                  return false;
               }
            } else if (!this$jwtToken1.equals(other$jwtToken1)) {
               return false;
            }

            label185: {
               Object this$jwtToken2 = this.getJwtToken2();
               Object other$jwtToken2 = other.getJwtToken2();
               if (this$jwtToken2 == null) {
                  if (other$jwtToken2 == null) {
                     break label185;
                  }
               } else if (this$jwtToken2.equals(other$jwtToken2)) {
                  break label185;
               }

               return false;
            }

            label178: {
               Object this$jwtToken3 = this.getJwtToken3();
               Object other$jwtToken3 = other.getJwtToken3();
               if (this$jwtToken3 == null) {
                  if (other$jwtToken3 == null) {
                     break label178;
                  }
               } else if (this$jwtToken3.equals(other$jwtToken3)) {
                  break label178;
               }

               return false;
            }

            Object this$jwtToken4 = this.getJwtToken4();
            Object other$jwtToken4 = other.getJwtToken4();
            if (this$jwtToken4 == null) {
               if (other$jwtToken4 != null) {
                  return false;
               }
            } else if (!this$jwtToken4.equals(other$jwtToken4)) {
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

            label157: {
               Object this$codigoNegocio = this.getCodigoNegocio();
               Object other$codigoNegocio = other.getCodigoNegocio();
               if (this$codigoNegocio == null) {
                  if (other$codigoNegocio == null) {
                     break label157;
                  }
               } else if (this$codigoNegocio.equals(other$codigoNegocio)) {
                  break label157;
               }

               return false;
            }

            label150: {
               Object this$tipoNegocio = this.getTipoNegocio();
               Object other$tipoNegocio = other.getTipoNegocio();
               if (this$tipoNegocio == null) {
                  if (other$tipoNegocio == null) {
                     break label150;
                  }
               } else if (this$tipoNegocio.equals(other$tipoNegocio)) {
                  break label150;
               }

               return false;
            }

            Object this$descripcionNegocio = this.getDescripcionNegocio();
            Object other$descripcionNegocio = other.getDescripcionNegocio();
            if (this$descripcionNegocio == null) {
               if (other$descripcionNegocio != null) {
                  return false;
               }
            } else if (!this$descripcionNegocio.equals(other$descripcionNegocio)) {
               return false;
            }

            label136: {
               Object this$proceso = this.getProceso();
               Object other$proceso = other.getProceso();
               if (this$proceso == null) {
                  if (other$proceso == null) {
                     break label136;
                  }
               } else if (this$proceso.equals(other$proceso)) {
                  break label136;
               }

               return false;
            }

            Object this$tokenK = this.getTokenK();
            Object other$tokenK = other.getTokenK();
            if (this$tokenK == null) {
               if (other$tokenK != null) {
                  return false;
               }
            } else if (!this$tokenK.equals(other$tokenK)) {
               return false;
            }

            label122: {
               Object this$uuid = this.getUuid();
               Object other$uuid = other.getUuid();
               if (this$uuid == null) {
                  if (other$uuid == null) {
                     break label122;
                  }
               } else if (this$uuid.equals(other$uuid)) {
                  break label122;
               }

               return false;
            }

            Object this$tokenkey = this.getTokenkey();
            Object other$tokenkey = other.getTokenkey();
            if (this$tokenkey == null) {
               if (other$tokenkey != null) {
                  return false;
               }
            } else if (!this$tokenkey.equals(other$tokenkey)) {
               return false;
            }

            Object this$tokenFormulario = this.getTokenFormulario();
            Object other$tokenFormulario = other.getTokenFormulario();
            if (this$tokenFormulario == null) {
               if (other$tokenFormulario == null) {
                  return true;
               }
            } else if (this$tokenFormulario.equals(other$tokenFormulario)) {
               return true;
            }

            return false;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CorreoNotificacionLinkPreIngresoPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      long $vigenciaLink = this.getVigenciaLink();
      result = result * 59 + (int)($vigenciaLink >>> 32 ^ $vigenciaLink);
      result = result * 59 + this.getDiasValido();
      Object $correoDestino = this.getCorreoDestino();
      result = result * 59 + ($correoDestino == null ? 43 : $correoDestino.hashCode());
      Object $asuntoTpl = this.getAsuntoTpl();
      result = result * 59 + ($asuntoTpl == null ? 43 : $asuntoTpl.hashCode());
      Object $cuerpoCorreoTpl = this.getCuerpoCorreoTpl();
      result = result * 59 + ($cuerpoCorreoTpl == null ? 43 : $cuerpoCorreoTpl.hashCode());
      Object $tokenId = this.getTokenId();
      result = result * 59 + ($tokenId == null ? 43 : $tokenId.hashCode());
      Object $jwtToken1 = this.getJwtToken1();
      result = result * 59 + ($jwtToken1 == null ? 43 : $jwtToken1.hashCode());
      Object $jwtToken2 = this.getJwtToken2();
      result = result * 59 + ($jwtToken2 == null ? 43 : $jwtToken2.hashCode());
      Object $jwtToken3 = this.getJwtToken3();
      result = result * 59 + ($jwtToken3 == null ? 43 : $jwtToken3.hashCode());
      Object $jwtToken4 = this.getJwtToken4();
      result = result * 59 + ($jwtToken4 == null ? 43 : $jwtToken4.hashCode());
      Object $usuario = this.getUsuario();
      result = result * 59 + ($usuario == null ? 43 : $usuario.hashCode());
      Object $codigoNegocio = this.getCodigoNegocio();
      result = result * 59 + ($codigoNegocio == null ? 43 : $codigoNegocio.hashCode());
      Object $tipoNegocio = this.getTipoNegocio();
      result = result * 59 + ($tipoNegocio == null ? 43 : $tipoNegocio.hashCode());
      Object $descripcionNegocio = this.getDescripcionNegocio();
      result = result * 59 + ($descripcionNegocio == null ? 43 : $descripcionNegocio.hashCode());
      Object $proceso = this.getProceso();
      result = result * 59 + ($proceso == null ? 43 : $proceso.hashCode());
      Object $tokenK = this.getTokenK();
      result = result * 59 + ($tokenK == null ? 43 : $tokenK.hashCode());
      Object $uuid = this.getUuid();
      result = result * 59 + ($uuid == null ? 43 : $uuid.hashCode());
      Object $tokenkey = this.getTokenkey();
      result = result * 59 + ($tokenkey == null ? 43 : $tokenkey.hashCode());
      Object $tokenFormulario = this.getTokenFormulario();
      result = result * 59 + ($tokenFormulario == null ? 43 : $tokenFormulario.hashCode());
      return result;
   }

   public String toString() {
      return "CorreoNotificacionLinkPreIngresoPojo(correoDestino=" + this.getCorreoDestino() + ", asuntoTpl=" + this.getAsuntoTpl() + ", cuerpoCorreoTpl=" + this.getCuerpoCorreoTpl() + ", tokenId=" + this.getTokenId() + ", jwtToken1=" + this.getJwtToken1() + ", jwtToken2=" + this.getJwtToken2() + ", jwtToken3=" + this.getJwtToken3() + ", jwtToken4=" + this.getJwtToken4() + ", vigenciaLink=" + this.getVigenciaLink() + ", usuario=" + this.getUsuario() + ", codigoNegocio=" + this.getCodigoNegocio() + ", tipoNegocio=" + this.getTipoNegocio() + ", descripcionNegocio=" + this.getDescripcionNegocio() + ", proceso=" + this.getProceso() + ", tokenK=" + this.getTokenK() + ", uuid=" + this.getUuid() + ", tokenkey=" + this.getTokenkey() + ", tokenFormulario=" + this.getTokenFormulario() + ", diasValido=" + this.getDiasValido() + ")";
   }
}
