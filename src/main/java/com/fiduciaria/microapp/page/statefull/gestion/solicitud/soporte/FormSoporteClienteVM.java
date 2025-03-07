// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud.soporte;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojoVM;
import com.fiduciaria.microapp.store.model.gui.solicitud.FormularioSoportePojo;
import org.apache.wicket.util.io.IClusterable;

public class FormSoporteClienteVM implements IClusterable {
   @JsonIgnore
   private final IGenericHttpClient gtwayHttp;
   private FormularioSoportePojo modelo;
   private int step;
   private boolean estado;
   private final String usuario;
   @JsonIgnore
   private final SolicitudPojoVM solPojovm;

   public FormSoporteClienteVM(IGenericHttpClient gtwayHttp, String usuario) {
      this.gtwayHttp = gtwayHttp;
      this.solPojovm = new SolicitudPojoVM(gtwayHttp);
      this.usuario = usuario;
   }

   public FormularioSoportePojo getModelo() {
      return this.modelo;
   }

   public void setModelo(FormularioSoportePojo modelo) {
      this.modelo = modelo;
   }

   public int getStep() {
      return this.step;
   }

   public void setStep(int step) {
      this.step = step;
   }

   public boolean isEstado() {
      return this.estado;
   }

   public void setEstado(boolean estado) {
      this.estado = estado;
   }

   public String guardarFormularioSoporte() {
      return this.solPojovm.guardarFormularioSoporte(this.modelo, this.gtwayHttp, this.usuario);
   }

   public IGenericHttpClient getGtwayHttp() {
      return this.gtwayHttp;
   }

   public String getUsuario() {
      return this.usuario;
   }

   public SolicitudPojoVM getSolPojovm() {
      return this.solPojovm;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FormSoporteClienteVM)) {
         return false;
      } else {
         FormSoporteClienteVM other = (FormSoporteClienteVM)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getStep() != other.getStep()) {
            return false;
         } else if (this.isEstado() != other.isEstado()) {
            return false;
         } else {
            label64: {
               Object this$gtwayHttp = this.getGtwayHttp();
               Object other$gtwayHttp = other.getGtwayHttp();
               if (this$gtwayHttp == null) {
                  if (other$gtwayHttp == null) {
                     break label64;
                  }
               } else if (this$gtwayHttp.equals(other$gtwayHttp)) {
                  break label64;
               }

               return false;
            }

            label57: {
               Object this$modelo = this.getModelo();
               Object other$modelo = other.getModelo();
               if (this$modelo == null) {
                  if (other$modelo == null) {
                     break label57;
                  }
               } else if (this$modelo.equals(other$modelo)) {
                  break label57;
               }

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

            Object this$solPojovm = this.getSolPojovm();
            Object other$solPojovm = other.getSolPojovm();
            if (this$solPojovm == null) {
               if (other$solPojovm != null) {
                  return false;
               }
            } else if (!this$solPojovm.equals(other$solPojovm)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof FormSoporteClienteVM;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + this.getStep();
      result = result * 59 + (this.isEstado() ? 79 : 97);
      Object $gtwayHttp = this.getGtwayHttp();
      result = result * 59 + ($gtwayHttp == null ? 43 : $gtwayHttp.hashCode());
      Object $modelo = this.getModelo();
      result = result * 59 + ($modelo == null ? 43 : $modelo.hashCode());
      Object $usuario = this.getUsuario();
      result = result * 59 + ($usuario == null ? 43 : $usuario.hashCode());
      Object $solPojovm = this.getSolPojovm();
      result = result * 59 + ($solPojovm == null ? 43 : $solPojovm.hashCode());
      return result;
   }

   public String toString() {
      return "FormSoporteClienteVM(gtwayHttp=" + this.getGtwayHttp() + ", modelo=" + this.getModelo() + ", step=" + this.getStep() + ", estado=" + this.isEstado() + ", usuario=" + this.getUsuario() + ", solPojovm=" + this.getSolPojovm() + ")";
   }
}
