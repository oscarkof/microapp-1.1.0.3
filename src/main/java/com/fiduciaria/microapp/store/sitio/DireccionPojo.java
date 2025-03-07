// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.sitio;

import java.io.Serializable;

public class DireccionPojo implements Serializable {
   private CiudadPojo ciudad;
   private DepartamentoPojo departamento;
   private PaisPojo pais;
   private String numero;
   private String barrio;
   private String conjunto;
   private String codigoZip;
   private String tipoDireccion;

   public DireccionPojo() {
   }

   public CiudadPojo getCiudad() {
      return this.ciudad;
   }

   public DepartamentoPojo getDepartamento() {
      return this.departamento;
   }

   public PaisPojo getPais() {
      return this.pais;
   }

   public String getNumero() {
      return this.numero;
   }

   public String getBarrio() {
      return this.barrio;
   }

   public String getConjunto() {
      return this.conjunto;
   }

   public String getCodigoZip() {
      return this.codigoZip;
   }

   public String getTipoDireccion() {
      return this.tipoDireccion;
   }

   public void setCiudad(CiudadPojo ciudad) {
      this.ciudad = ciudad;
   }

   public void setDepartamento(DepartamentoPojo departamento) {
      this.departamento = departamento;
   }

   public void setPais(PaisPojo pais) {
      this.pais = pais;
   }

   public void setNumero(String numero) {
      this.numero = numero;
   }

   public void setBarrio(String barrio) {
      this.barrio = barrio;
   }

   public void setConjunto(String conjunto) {
      this.conjunto = conjunto;
   }

   public void setCodigoZip(String codigoZip) {
      this.codigoZip = codigoZip;
   }

   public void setTipoDireccion(String tipoDireccion) {
      this.tipoDireccion = tipoDireccion;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DireccionPojo)) {
         return false;
      } else {
         DireccionPojo other = (DireccionPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label107: {
               Object this$ciudad = this.getCiudad();
               Object other$ciudad = other.getCiudad();
               if (this$ciudad == null) {
                  if (other$ciudad == null) {
                     break label107;
                  }
               } else if (this$ciudad.equals(other$ciudad)) {
                  break label107;
               }

               return false;
            }

            Object this$departamento = this.getDepartamento();
            Object other$departamento = other.getDepartamento();
            if (this$departamento == null) {
               if (other$departamento != null) {
                  return false;
               }
            } else if (!this$departamento.equals(other$departamento)) {
               return false;
            }

            Object this$pais = this.getPais();
            Object other$pais = other.getPais();
            if (this$pais == null) {
               if (other$pais != null) {
                  return false;
               }
            } else if (!this$pais.equals(other$pais)) {
               return false;
            }

            label86: {
               Object this$numero = this.getNumero();
               Object other$numero = other.getNumero();
               if (this$numero == null) {
                  if (other$numero == null) {
                     break label86;
                  }
               } else if (this$numero.equals(other$numero)) {
                  break label86;
               }

               return false;
            }

            label79: {
               Object this$barrio = this.getBarrio();
               Object other$barrio = other.getBarrio();
               if (this$barrio == null) {
                  if (other$barrio == null) {
                     break label79;
                  }
               } else if (this$barrio.equals(other$barrio)) {
                  break label79;
               }

               return false;
            }

            label72: {
               Object this$conjunto = this.getConjunto();
               Object other$conjunto = other.getConjunto();
               if (this$conjunto == null) {
                  if (other$conjunto == null) {
                     break label72;
                  }
               } else if (this$conjunto.equals(other$conjunto)) {
                  break label72;
               }

               return false;
            }

            Object this$codigoZip = this.getCodigoZip();
            Object other$codigoZip = other.getCodigoZip();
            if (this$codigoZip == null) {
               if (other$codigoZip != null) {
                  return false;
               }
            } else if (!this$codigoZip.equals(other$codigoZip)) {
               return false;
            }

            Object this$tipoDireccion = this.getTipoDireccion();
            Object other$tipoDireccion = other.getTipoDireccion();
            if (this$tipoDireccion == null) {
               if (other$tipoDireccion != null) {
                  return false;
               }
            } else if (!this$tipoDireccion.equals(other$tipoDireccion)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof DireccionPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $ciudad = this.getCiudad();
      result = result * 59 + ($ciudad == null ? 43 : $ciudad.hashCode());
      Object $departamento = this.getDepartamento();
      result = result * 59 + ($departamento == null ? 43 : $departamento.hashCode());
      Object $pais = this.getPais();
      result = result * 59 + ($pais == null ? 43 : $pais.hashCode());
      Object $numero = this.getNumero();
      result = result * 59 + ($numero == null ? 43 : $numero.hashCode());
      Object $barrio = this.getBarrio();
      result = result * 59 + ($barrio == null ? 43 : $barrio.hashCode());
      Object $conjunto = this.getConjunto();
      result = result * 59 + ($conjunto == null ? 43 : $conjunto.hashCode());
      Object $codigoZip = this.getCodigoZip();
      result = result * 59 + ($codigoZip == null ? 43 : $codigoZip.hashCode());
      Object $tipoDireccion = this.getTipoDireccion();
      result = result * 59 + ($tipoDireccion == null ? 43 : $tipoDireccion.hashCode());
      return result;
   }

   public String toString() {
      return "DireccionPojo(ciudad=" + this.getCiudad() + ", departamento=" + this.getDepartamento() + ", pais=" + this.getPais() + ", numero=" + this.getNumero() + ", barrio=" + this.getBarrio() + ", conjunto=" + this.getConjunto() + ", codigoZip=" + this.getCodigoZip() + ", tipoDireccion=" + this.getTipoDireccion() + ")";
   }
}
