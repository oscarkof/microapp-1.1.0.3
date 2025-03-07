// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud.preingreso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojoVM;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoPojo;
import com.fiduciaria.microapp.store.dispositivo.CelularPojo;
import com.fiduciaria.microapp.store.model.gui.solicitud.FormularioSoportePojo;
import com.fiduciaria.microapp.store.model.gui.solicitud.PreIngresoDatosUsuarioPojo;
import com.fiduciaria.microapp.store.model.gui.solicitud.TipoFormulario;
import com.fiduciaria.microapp.store.model.persona.PersonaPojo;
import com.fiduciaria.microapp.store.sitio.DireccionPojo;
import org.apache.wicket.util.io.IClusterable;

public class FormPreingresoDatosUsuarioVM implements IClusterable {
   @JsonIgnore
   private GenericHttpClient gtwayHttp;
   @JsonIgnore
   private final SolicitudPojoVM solPojovm;
   private FormularioSoportePojo modelo;
   private int step;
   private boolean estado;
   private final String usuario;

   public FormPreingresoDatosUsuarioVM(GenericHttpClient gtwayHttp, String usuario) {
      this.initModelo();
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

   public void initModelo() {
      FormularioSoportePojo modelObj = new FormularioSoportePojo();
      modelObj.setTipoFormulario(TipoFormulario.PREINGRESODATOSUSUARIO);
      modelObj.setPreingresodatosusuario(new PreIngresoDatosUsuarioPojo());
      this.modelo = modelObj;
      this.modelo.getPreingresodatosusuario().setPersona(new PersonaPojo());
      this.modelo.getPreingresodatosusuario().getPersona().setTipoDocumento(new TipoDocumentoPojo());
      this.modelo.getPreingresodatosusuario().getPersona().setCelular(new CelularPojo());
      this.modelo.getPreingresodatosusuario().getPersona().setDireccionOficina(new DireccionPojo());
      this.modelo.getPreingresodatosusuario().getPersona().setDireccionPersonal(new DireccionPojo());
   }

   public String guardarFormularioPreingresoDatos(boolean usuarioUCA, boolean tareaCrearUsuarioAutomatica) {
      String response = this.solPojovm.guardarFormularioSolicitud(this.modelo, this.gtwayHttp, this.usuario, usuarioUCA, tareaCrearUsuarioAutomatica);
      return response;
   }

   public GenericHttpClient getGtwayHttp() {
      return this.gtwayHttp;
   }

   public SolicitudPojoVM getSolPojovm() {
      return this.solPojovm;
   }

   public String getUsuario() {
      return this.usuario;
   }

   @JsonIgnore
   public void setGtwayHttp(GenericHttpClient gtwayHttp) {
      this.gtwayHttp = gtwayHttp;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FormPreingresoDatosUsuarioVM)) {
         return false;
      } else {
         FormPreingresoDatosUsuarioVM other = (FormPreingresoDatosUsuarioVM)o;
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
               Object this$solPojovm = this.getSolPojovm();
               Object other$solPojovm = other.getSolPojovm();
               if (this$solPojovm == null) {
                  if (other$solPojovm == null) {
                     break label57;
                  }
               } else if (this$solPojovm.equals(other$solPojovm)) {
                  break label57;
               }

               return false;
            }

            Object this$modelo = this.getModelo();
            Object other$modelo = other.getModelo();
            if (this$modelo == null) {
               if (other$modelo != null) {
                  return false;
               }
            } else if (!this$modelo.equals(other$modelo)) {
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

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof FormPreingresoDatosUsuarioVM;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      result = result * 59 + this.getStep();
      result = result * 59 + (this.isEstado() ? 79 : 97);
      Object $gtwayHttp = this.getGtwayHttp();
      result = result * 59 + ($gtwayHttp == null ? 43 : $gtwayHttp.hashCode());
      Object $solPojovm = this.getSolPojovm();
      result = result * 59 + ($solPojovm == null ? 43 : $solPojovm.hashCode());
      Object $modelo = this.getModelo();
      result = result * 59 + ($modelo == null ? 43 : $modelo.hashCode());
      Object $usuario = this.getUsuario();
      result = result * 59 + ($usuario == null ? 43 : $usuario.hashCode());
      return result;
   }

   public String toString() {
      return "FormPreingresoDatosUsuarioVM(gtwayHttp=" + this.getGtwayHttp() + ", solPojovm=" + this.getSolPojovm() + ", modelo=" + this.getModelo() + ", step=" + this.getStep() + ", estado=" + this.isEstado() + ", usuario=" + this.getUsuario() + ")";
   }
}
