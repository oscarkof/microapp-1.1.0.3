// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.dominio.historial;

import java.io.Serializable;

public class HistorialDominioPojo implements Serializable {
   private String identificador;
   private String accion;
   private String dominio;
   private String atributo;
   private String valorAtributo;
   private String valorAtributoAnt;
   private String estampaTiempo;
   private String usuarioCrea;

   public HistorialDominioPojo() {
   }

   public String getIdentificador() {
      return this.identificador;
   }

   public String getAccion() {
      return this.accion;
   }

   public String getDominio() {
      return this.dominio;
   }

   public String getAtributo() {
      return this.atributo;
   }

   public String getValorAtributo() {
      return this.valorAtributo;
   }

   public String getValorAtributoAnt() {
      return this.valorAtributoAnt;
   }

   public String getEstampaTiempo() {
      return this.estampaTiempo;
   }

   public String getUsuarioCrea() {
      return this.usuarioCrea;
   }

   public void setIdentificador(String identificador) {
      this.identificador = identificador;
   }

   public void setAccion(String accion) {
      this.accion = accion;
   }

   public void setDominio(String dominio) {
      this.dominio = dominio;
   }

   public void setAtributo(String atributo) {
      this.atributo = atributo;
   }

   public void setValorAtributo(String valorAtributo) {
      this.valorAtributo = valorAtributo;
   }

   public void setValorAtributoAnt(String valorAtributoAnt) {
      this.valorAtributoAnt = valorAtributoAnt;
   }

   public void setEstampaTiempo(String estampaTiempo) {
      this.estampaTiempo = estampaTiempo;
   }

   public void setUsuarioCrea(String usuarioCrea) {
      this.usuarioCrea = usuarioCrea;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HistorialDominioPojo)) {
         return false;
      } else {
         HistorialDominioPojo other = (HistorialDominioPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label107: {
               Object this$identificador = this.getIdentificador();
               Object other$identificador = other.getIdentificador();
               if (this$identificador == null) {
                  if (other$identificador == null) {
                     break label107;
                  }
               } else if (this$identificador.equals(other$identificador)) {
                  break label107;
               }

               return false;
            }

            Object this$accion = this.getAccion();
            Object other$accion = other.getAccion();
            if (this$accion == null) {
               if (other$accion != null) {
                  return false;
               }
            } else if (!this$accion.equals(other$accion)) {
               return false;
            }

            Object this$dominio = this.getDominio();
            Object other$dominio = other.getDominio();
            if (this$dominio == null) {
               if (other$dominio != null) {
                  return false;
               }
            } else if (!this$dominio.equals(other$dominio)) {
               return false;
            }

            label86: {
               Object this$atributo = this.getAtributo();
               Object other$atributo = other.getAtributo();
               if (this$atributo == null) {
                  if (other$atributo == null) {
                     break label86;
                  }
               } else if (this$atributo.equals(other$atributo)) {
                  break label86;
               }

               return false;
            }

            label79: {
               Object this$valorAtributo = this.getValorAtributo();
               Object other$valorAtributo = other.getValorAtributo();
               if (this$valorAtributo == null) {
                  if (other$valorAtributo == null) {
                     break label79;
                  }
               } else if (this$valorAtributo.equals(other$valorAtributo)) {
                  break label79;
               }

               return false;
            }

            label72: {
               Object this$valorAtributoAnt = this.getValorAtributoAnt();
               Object other$valorAtributoAnt = other.getValorAtributoAnt();
               if (this$valorAtributoAnt == null) {
                  if (other$valorAtributoAnt == null) {
                     break label72;
                  }
               } else if (this$valorAtributoAnt.equals(other$valorAtributoAnt)) {
                  break label72;
               }

               return false;
            }

            Object this$estampaTiempo = this.getEstampaTiempo();
            Object other$estampaTiempo = other.getEstampaTiempo();
            if (this$estampaTiempo == null) {
               if (other$estampaTiempo != null) {
                  return false;
               }
            } else if (!this$estampaTiempo.equals(other$estampaTiempo)) {
               return false;
            }

            Object this$usuarioCrea = this.getUsuarioCrea();
            Object other$usuarioCrea = other.getUsuarioCrea();
            if (this$usuarioCrea == null) {
               if (other$usuarioCrea != null) {
                  return false;
               }
            } else if (!this$usuarioCrea.equals(other$usuarioCrea)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof HistorialDominioPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $identificador = this.getIdentificador();
      result = result * 59 + ($identificador == null ? 43 : $identificador.hashCode());
      Object $accion = this.getAccion();
      result = result * 59 + ($accion == null ? 43 : $accion.hashCode());
      Object $dominio = this.getDominio();
      result = result * 59 + ($dominio == null ? 43 : $dominio.hashCode());
      Object $atributo = this.getAtributo();
      result = result * 59 + ($atributo == null ? 43 : $atributo.hashCode());
      Object $valorAtributo = this.getValorAtributo();
      result = result * 59 + ($valorAtributo == null ? 43 : $valorAtributo.hashCode());
      Object $valorAtributoAnt = this.getValorAtributoAnt();
      result = result * 59 + ($valorAtributoAnt == null ? 43 : $valorAtributoAnt.hashCode());
      Object $estampaTiempo = this.getEstampaTiempo();
      result = result * 59 + ($estampaTiempo == null ? 43 : $estampaTiempo.hashCode());
      Object $usuarioCrea = this.getUsuarioCrea();
      result = result * 59 + ($usuarioCrea == null ? 43 : $usuarioCrea.hashCode());
      return result;
   }

   public String toString() {
      return "HistorialDominioPojo(identificador=" + this.getIdentificador() + ", accion=" + this.getAccion() + ", dominio=" + this.getDominio() + ", atributo=" + this.getAtributo() + ", valorAtributo=" + this.getValorAtributo() + ", valorAtributoAnt=" + this.getValorAtributoAnt() + ", estampaTiempo=" + this.getEstampaTiempo() + ", usuarioCrea=" + this.getUsuarioCrea() + ")";
   }
}
