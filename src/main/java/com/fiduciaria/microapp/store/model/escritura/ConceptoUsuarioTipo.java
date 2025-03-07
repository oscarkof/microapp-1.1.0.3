// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.escritura;

import java.io.Serializable;

public class ConceptoUsuarioTipo implements Serializable {
   private String numeroEscritura;
   private String loginUsuario;
   private String codTipoCondicion;
   private Integer secCondicion;
   private String tipo;
   private String aplicaSN;
   private String usuarioCargue;

   public ConceptoUsuarioTipo() {
   }

   public String getNumeroEscritura() {
      return this.numeroEscritura;
   }

   public String getLoginUsuario() {
      return this.loginUsuario;
   }

   public String getCodTipoCondicion() {
      return this.codTipoCondicion;
   }

   public Integer getSecCondicion() {
      return this.secCondicion;
   }

   public String getTipo() {
      return this.tipo;
   }

   public String getAplicaSN() {
      return this.aplicaSN;
   }

   public String getUsuarioCargue() {
      return this.usuarioCargue;
   }

   public void setNumeroEscritura(String numeroEscritura) {
      this.numeroEscritura = numeroEscritura;
   }

   public void setLoginUsuario(String loginUsuario) {
      this.loginUsuario = loginUsuario;
   }

   public void setCodTipoCondicion(String codTipoCondicion) {
      this.codTipoCondicion = codTipoCondicion;
   }

   public void setSecCondicion(Integer secCondicion) {
      this.secCondicion = secCondicion;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
   }

   public void setAplicaSN(String aplicaSN) {
      this.aplicaSN = aplicaSN;
   }

   public void setUsuarioCargue(String usuarioCargue) {
      this.usuarioCargue = usuarioCargue;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ConceptoUsuarioTipo)) {
         return false;
      } else {
         ConceptoUsuarioTipo other = (ConceptoUsuarioTipo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label95: {
               Object this$secCondicion = this.getSecCondicion();
               Object other$secCondicion = other.getSecCondicion();
               if (this$secCondicion == null) {
                  if (other$secCondicion == null) {
                     break label95;
                  }
               } else if (this$secCondicion.equals(other$secCondicion)) {
                  break label95;
               }

               return false;
            }

            Object this$numeroEscritura = this.getNumeroEscritura();
            Object other$numeroEscritura = other.getNumeroEscritura();
            if (this$numeroEscritura == null) {
               if (other$numeroEscritura != null) {
                  return false;
               }
            } else if (!this$numeroEscritura.equals(other$numeroEscritura)) {
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

            label74: {
               Object this$codTipoCondicion = this.getCodTipoCondicion();
               Object other$codTipoCondicion = other.getCodTipoCondicion();
               if (this$codTipoCondicion == null) {
                  if (other$codTipoCondicion == null) {
                     break label74;
                  }
               } else if (this$codTipoCondicion.equals(other$codTipoCondicion)) {
                  break label74;
               }

               return false;
            }

            label67: {
               Object this$tipo = this.getTipo();
               Object other$tipo = other.getTipo();
               if (this$tipo == null) {
                  if (other$tipo == null) {
                     break label67;
                  }
               } else if (this$tipo.equals(other$tipo)) {
                  break label67;
               }

               return false;
            }

            Object this$aplicaSN = this.getAplicaSN();
            Object other$aplicaSN = other.getAplicaSN();
            if (this$aplicaSN == null) {
               if (other$aplicaSN != null) {
                  return false;
               }
            } else if (!this$aplicaSN.equals(other$aplicaSN)) {
               return false;
            }

            Object this$usuarioCargue = this.getUsuarioCargue();
            Object other$usuarioCargue = other.getUsuarioCargue();
            if (this$usuarioCargue == null) {
               if (other$usuarioCargue != null) {
                  return false;
               }
            } else if (!this$usuarioCargue.equals(other$usuarioCargue)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ConceptoUsuarioTipo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $secCondicion = this.getSecCondicion();
      result = result * 59 + ($secCondicion == null ? 43 : $secCondicion.hashCode());
      Object $numeroEscritura = this.getNumeroEscritura();
      result = result * 59 + ($numeroEscritura == null ? 43 : $numeroEscritura.hashCode());
      Object $loginUsuario = this.getLoginUsuario();
      result = result * 59 + ($loginUsuario == null ? 43 : $loginUsuario.hashCode());
      Object $codTipoCondicion = this.getCodTipoCondicion();
      result = result * 59 + ($codTipoCondicion == null ? 43 : $codTipoCondicion.hashCode());
      Object $tipo = this.getTipo();
      result = result * 59 + ($tipo == null ? 43 : $tipo.hashCode());
      Object $aplicaSN = this.getAplicaSN();
      result = result * 59 + ($aplicaSN == null ? 43 : $aplicaSN.hashCode());
      Object $usuarioCargue = this.getUsuarioCargue();
      result = result * 59 + ($usuarioCargue == null ? 43 : $usuarioCargue.hashCode());
      return result;
   }

   public String toString() {
      return "ConceptoUsuarioTipo(numeroEscritura=" + this.getNumeroEscritura() + ", loginUsuario=" + this.getLoginUsuario() + ", codTipoCondicion=" + this.getCodTipoCondicion() + ", secCondicion=" + this.getSecCondicion() + ", tipo=" + this.getTipo() + ", aplicaSN=" + this.getAplicaSN() + ", usuarioCargue=" + this.getUsuarioCargue() + ")";
   }
}
