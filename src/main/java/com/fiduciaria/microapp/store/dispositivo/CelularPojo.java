// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.dispositivo;

import java.io.Serializable;

public class CelularPojo implements Serializable {
   private String tipoCelular;
   private String sistemaOperativo;
   private String version;
   private String modelo;
   private String fabricante;
   private String numeroCelular;
   private String operador;

   public CelularPojo() {
   }

   public String getTipoCelular() {
      return this.tipoCelular;
   }

   public String getSistemaOperativo() {
      return this.sistemaOperativo;
   }

   public String getVersion() {
      return this.version;
   }

   public String getModelo() {
      return this.modelo;
   }

   public String getFabricante() {
      return this.fabricante;
   }

   public String getNumeroCelular() {
      return this.numeroCelular;
   }

   public String getOperador() {
      return this.operador;
   }

   public void setTipoCelular(String tipoCelular) {
      this.tipoCelular = tipoCelular;
   }

   public void setSistemaOperativo(String sistemaOperativo) {
      this.sistemaOperativo = sistemaOperativo;
   }

   public void setVersion(String version) {
      this.version = version;
   }

   public void setModelo(String modelo) {
      this.modelo = modelo;
   }

   public void setFabricante(String fabricante) {
      this.fabricante = fabricante;
   }

   public void setNumeroCelular(String numeroCelular) {
      this.numeroCelular = numeroCelular;
   }

   public void setOperador(String operador) {
      this.operador = operador;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CelularPojo)) {
         return false;
      } else {
         CelularPojo other = (CelularPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label95: {
               Object this$tipoCelular = this.getTipoCelular();
               Object other$tipoCelular = other.getTipoCelular();
               if (this$tipoCelular == null) {
                  if (other$tipoCelular == null) {
                     break label95;
                  }
               } else if (this$tipoCelular.equals(other$tipoCelular)) {
                  break label95;
               }

               return false;
            }

            Object this$sistemaOperativo = this.getSistemaOperativo();
            Object other$sistemaOperativo = other.getSistemaOperativo();
            if (this$sistemaOperativo == null) {
               if (other$sistemaOperativo != null) {
                  return false;
               }
            } else if (!this$sistemaOperativo.equals(other$sistemaOperativo)) {
               return false;
            }

            Object this$version = this.getVersion();
            Object other$version = other.getVersion();
            if (this$version == null) {
               if (other$version != null) {
                  return false;
               }
            } else if (!this$version.equals(other$version)) {
               return false;
            }

            label74: {
               Object this$modelo = this.getModelo();
               Object other$modelo = other.getModelo();
               if (this$modelo == null) {
                  if (other$modelo == null) {
                     break label74;
                  }
               } else if (this$modelo.equals(other$modelo)) {
                  break label74;
               }

               return false;
            }

            label67: {
               Object this$fabricante = this.getFabricante();
               Object other$fabricante = other.getFabricante();
               if (this$fabricante == null) {
                  if (other$fabricante == null) {
                     break label67;
                  }
               } else if (this$fabricante.equals(other$fabricante)) {
                  break label67;
               }

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

            Object this$operador = this.getOperador();
            Object other$operador = other.getOperador();
            if (this$operador == null) {
               if (other$operador != null) {
                  return false;
               }
            } else if (!this$operador.equals(other$operador)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CelularPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $tipoCelular = this.getTipoCelular();
      result = result * 59 + ($tipoCelular == null ? 43 : $tipoCelular.hashCode());
      Object $sistemaOperativo = this.getSistemaOperativo();
      result = result * 59 + ($sistemaOperativo == null ? 43 : $sistemaOperativo.hashCode());
      Object $version = this.getVersion();
      result = result * 59 + ($version == null ? 43 : $version.hashCode());
      Object $modelo = this.getModelo();
      result = result * 59 + ($modelo == null ? 43 : $modelo.hashCode());
      Object $fabricante = this.getFabricante();
      result = result * 59 + ($fabricante == null ? 43 : $fabricante.hashCode());
      Object $numeroCelular = this.getNumeroCelular();
      result = result * 59 + ($numeroCelular == null ? 43 : $numeroCelular.hashCode());
      Object $operador = this.getOperador();
      result = result * 59 + ($operador == null ? 43 : $operador.hashCode());
      return result;
   }

   public String toString() {
      return "CelularPojo(tipoCelular=" + this.getTipoCelular() + ", sistemaOperativo=" + this.getSistemaOperativo() + ", version=" + this.getVersion() + ", modelo=" + this.getModelo() + ", fabricante=" + this.getFabricante() + ", numeroCelular=" + this.getNumeroCelular() + ", operador=" + this.getOperador() + ")";
   }
}
