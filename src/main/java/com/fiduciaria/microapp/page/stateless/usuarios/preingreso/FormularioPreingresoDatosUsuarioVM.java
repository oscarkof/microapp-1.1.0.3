// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.stateless.usuarios.preingreso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.page.statefull.dane.DireccionDanePojo;
import com.fiduciaria.microapp.page.statefull.gestion.solicitud.model.SolicitudPojoVM;
import com.fiduciaria.microapp.store.basicas.TipoDocumentoPojo;
import com.fiduciaria.microapp.store.dispositivo.CelularPojo;
import com.fiduciaria.microapp.store.model.gui.solicitud.FormularioSoportePojo;
import com.fiduciaria.microapp.store.model.gui.solicitud.PreIngresoDatosUsuarioPojo;
import com.fiduciaria.microapp.store.model.gui.solicitud.TipoFormulario;
import com.fiduciaria.microapp.store.model.persona.PersonaPojo;
import com.fiduciaria.microapp.store.sitio.DireccionPojo;
import java.util.List;
import org.apache.wicket.util.io.IClusterable;

public class FormularioPreingresoDatosUsuarioVM implements IClusterable {
   @JsonIgnore
   private IGenericHttpClient gtwayHttp;
   @JsonIgnore
   private final SolicitudPojoVM solPojovm;
   private FormularioSoportePojo modelo;
   private String defaultServerURI;
   private long validoHasta;
   private List<TipoDocumentoPojo> listaTipoDocumentos;
   private int step;
   private boolean estado;
   private String conftoken;
   private String trustkeystoretype;
   private String uritotrustkeystore;
   private String trustkeystorepass;
   private String fidutoken;
   private boolean usuarioUCA;
   private String nombreUsuarioCrea;
   private DireccionDanePojo direccionEstandar;

   public FormularioPreingresoDatosUsuarioVM() {
      this.initModelo();
      this.solPojovm = new SolicitudPojoVM(this.gtwayHttp);
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
      this.modelo = new FormularioSoportePojo();
      this.modelo.setTipoFormulario(TipoFormulario.PREINGRESODATOSUSUARIO);
      this.modelo.setPreingresodatosusuario(new PreIngresoDatosUsuarioPojo());
      this.modelo.getPreingresodatosusuario().setPersona(new PersonaPojo());
      this.modelo.getPreingresodatosusuario().getPersona().setTipoDocumento(new TipoDocumentoPojo());
      this.modelo.getPreingresodatosusuario().getPersona().setCelular(new CelularPojo());
      this.modelo.getPreingresodatosusuario().getPersona().setDireccionOficina(new DireccionPojo());
      this.modelo.getPreingresodatosusuario().getPersona().setDireccionPersonal(new DireccionPojo());
      this.modelo.getPreingresodatosusuario().getPersona().setDireccionCorrespondencia(new DireccionPojo());
   }

   public String guardarFormularioPreingresoDatos() {
      return this.solPojovm.guardarFormularioSolicitud(this.modelo, this.gtwayHttp, this.nombreUsuarioCrea, this.isUsuarioUCA(), this.isUsuarioUCA());
   }

   public IGenericHttpClient getGtwayHttp() {
      return this.gtwayHttp;
   }

   public SolicitudPojoVM getSolPojovm() {
      return this.solPojovm;
   }

   public String getDefaultServerURI() {
      return this.defaultServerURI;
   }

   public long getValidoHasta() {
      return this.validoHasta;
   }

   public List<TipoDocumentoPojo> getListaTipoDocumentos() {
      return this.listaTipoDocumentos;
   }

   public String getConftoken() {
      return this.conftoken;
   }

   public String getTrustkeystoretype() {
      return this.trustkeystoretype;
   }

   public String getUritotrustkeystore() {
      return this.uritotrustkeystore;
   }

   public String getTrustkeystorepass() {
      return this.trustkeystorepass;
   }

   public String getFidutoken() {
      return this.fidutoken;
   }

   public boolean isUsuarioUCA() {
      return this.usuarioUCA;
   }

   public String getNombreUsuarioCrea() {
      return this.nombreUsuarioCrea;
   }

   public DireccionDanePojo getDireccionEstandar() {
      return this.direccionEstandar;
   }

   @JsonIgnore
   public void setGtwayHttp(IGenericHttpClient gtwayHttp) {
      this.gtwayHttp = gtwayHttp;
   }

   public void setDefaultServerURI(String defaultServerURI) {
      this.defaultServerURI = defaultServerURI;
   }

   public void setValidoHasta(long validoHasta) {
      this.validoHasta = validoHasta;
   }

   public void setListaTipoDocumentos(List<TipoDocumentoPojo> listaTipoDocumentos) {
      this.listaTipoDocumentos = listaTipoDocumentos;
   }

   public void setConftoken(String conftoken) {
      this.conftoken = conftoken;
   }

   public void setTrustkeystoretype(String trustkeystoretype) {
      this.trustkeystoretype = trustkeystoretype;
   }

   public void setUritotrustkeystore(String uritotrustkeystore) {
      this.uritotrustkeystore = uritotrustkeystore;
   }

   public void setTrustkeystorepass(String trustkeystorepass) {
      this.trustkeystorepass = trustkeystorepass;
   }

   public void setFidutoken(String fidutoken) {
      this.fidutoken = fidutoken;
   }

   public void setUsuarioUCA(boolean usuarioUCA) {
      this.usuarioUCA = usuarioUCA;
   }

   public void setNombreUsuarioCrea(String nombreUsuarioCrea) {
      this.nombreUsuarioCrea = nombreUsuarioCrea;
   }

   public void setDireccionEstandar(DireccionDanePojo direccionEstandar) {
      this.direccionEstandar = direccionEstandar;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FormularioPreingresoDatosUsuarioVM)) {
         return false;
      } else {
         FormularioPreingresoDatosUsuarioVM other = (FormularioPreingresoDatosUsuarioVM)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.getValidoHasta() != other.getValidoHasta()) {
            return false;
         } else if (this.getStep() != other.getStep()) {
            return false;
         } else if (this.isEstado() != other.isEstado()) {
            return false;
         } else if (this.isUsuarioUCA() != other.isUsuarioUCA()) {
            return false;
         } else {
            Object this$gtwayHttp = this.getGtwayHttp();
            Object other$gtwayHttp = other.getGtwayHttp();
            if (this$gtwayHttp == null) {
               if (other$gtwayHttp != null) {
                  return false;
               }
            } else if (!this$gtwayHttp.equals(other$gtwayHttp)) {
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

            label151: {
               Object this$modelo = this.getModelo();
               Object other$modelo = other.getModelo();
               if (this$modelo == null) {
                  if (other$modelo == null) {
                     break label151;
                  }
               } else if (this$modelo.equals(other$modelo)) {
                  break label151;
               }

               return false;
            }

            Object this$defaultServerURI = this.getDefaultServerURI();
            Object other$defaultServerURI = other.getDefaultServerURI();
            if (this$defaultServerURI == null) {
               if (other$defaultServerURI != null) {
                  return false;
               }
            } else if (!this$defaultServerURI.equals(other$defaultServerURI)) {
               return false;
            }

            label137: {
               Object this$listaTipoDocumentos = this.getListaTipoDocumentos();
               Object other$listaTipoDocumentos = other.getListaTipoDocumentos();
               if (this$listaTipoDocumentos == null) {
                  if (other$listaTipoDocumentos == null) {
                     break label137;
                  }
               } else if (this$listaTipoDocumentos.equals(other$listaTipoDocumentos)) {
                  break label137;
               }

               return false;
            }

            Object this$conftoken = this.getConftoken();
            Object other$conftoken = other.getConftoken();
            if (this$conftoken == null) {
               if (other$conftoken != null) {
                  return false;
               }
            } else if (!this$conftoken.equals(other$conftoken)) {
               return false;
            }

            Object this$trustkeystoretype = this.getTrustkeystoretype();
            Object other$trustkeystoretype = other.getTrustkeystoretype();
            if (this$trustkeystoretype == null) {
               if (other$trustkeystoretype != null) {
                  return false;
               }
            } else if (!this$trustkeystoretype.equals(other$trustkeystoretype)) {
               return false;
            }

            Object this$uritotrustkeystore = this.getUritotrustkeystore();
            Object other$uritotrustkeystore = other.getUritotrustkeystore();
            if (this$uritotrustkeystore == null) {
               if (other$uritotrustkeystore != null) {
                  return false;
               }
            } else if (!this$uritotrustkeystore.equals(other$uritotrustkeystore)) {
               return false;
            }

            label109: {
               Object this$trustkeystorepass = this.getTrustkeystorepass();
               Object other$trustkeystorepass = other.getTrustkeystorepass();
               if (this$trustkeystorepass == null) {
                  if (other$trustkeystorepass == null) {
                     break label109;
                  }
               } else if (this$trustkeystorepass.equals(other$trustkeystorepass)) {
                  break label109;
               }

               return false;
            }

            label102: {
               Object this$fidutoken = this.getFidutoken();
               Object other$fidutoken = other.getFidutoken();
               if (this$fidutoken == null) {
                  if (other$fidutoken == null) {
                     break label102;
                  }
               } else if (this$fidutoken.equals(other$fidutoken)) {
                  break label102;
               }

               return false;
            }

            Object this$nombreUsuarioCrea = this.getNombreUsuarioCrea();
            Object other$nombreUsuarioCrea = other.getNombreUsuarioCrea();
            if (this$nombreUsuarioCrea == null) {
               if (other$nombreUsuarioCrea != null) {
                  return false;
               }
            } else if (!this$nombreUsuarioCrea.equals(other$nombreUsuarioCrea)) {
               return false;
            }

            Object this$direccionEstandar = this.getDireccionEstandar();
            Object other$direccionEstandar = other.getDireccionEstandar();
            if (this$direccionEstandar == null) {
               if (other$direccionEstandar != null) {
                  return false;
               }
            } else if (!this$direccionEstandar.equals(other$direccionEstandar)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof FormularioPreingresoDatosUsuarioVM;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      long $validoHasta = this.getValidoHasta();
      result = result * 59 + (int)($validoHasta >>> 32 ^ $validoHasta);
      result = result * 59 + this.getStep();
      result = result * 59 + (this.isEstado() ? 79 : 97);
      result = result * 59 + (this.isUsuarioUCA() ? 79 : 97);
      Object $gtwayHttp = this.getGtwayHttp();
      result = result * 59 + ($gtwayHttp == null ? 43 : $gtwayHttp.hashCode());
      Object $solPojovm = this.getSolPojovm();
      result = result * 59 + ($solPojovm == null ? 43 : $solPojovm.hashCode());
      Object $modelo = this.getModelo();
      result = result * 59 + ($modelo == null ? 43 : $modelo.hashCode());
      Object $defaultServerURI = this.getDefaultServerURI();
      result = result * 59 + ($defaultServerURI == null ? 43 : $defaultServerURI.hashCode());
      Object $listaTipoDocumentos = this.getListaTipoDocumentos();
      result = result * 59 + ($listaTipoDocumentos == null ? 43 : $listaTipoDocumentos.hashCode());
      Object $conftoken = this.getConftoken();
      result = result * 59 + ($conftoken == null ? 43 : $conftoken.hashCode());
      Object $trustkeystoretype = this.getTrustkeystoretype();
      result = result * 59 + ($trustkeystoretype == null ? 43 : $trustkeystoretype.hashCode());
      Object $uritotrustkeystore = this.getUritotrustkeystore();
      result = result * 59 + ($uritotrustkeystore == null ? 43 : $uritotrustkeystore.hashCode());
      Object $trustkeystorepass = this.getTrustkeystorepass();
      result = result * 59 + ($trustkeystorepass == null ? 43 : $trustkeystorepass.hashCode());
      Object $fidutoken = this.getFidutoken();
      result = result * 59 + ($fidutoken == null ? 43 : $fidutoken.hashCode());
      Object $nombreUsuarioCrea = this.getNombreUsuarioCrea();
      result = result * 59 + ($nombreUsuarioCrea == null ? 43 : $nombreUsuarioCrea.hashCode());
      Object $direccionEstandar = this.getDireccionEstandar();
      result = result * 59 + ($direccionEstandar == null ? 43 : $direccionEstandar.hashCode());
      return result;
   }

   public String toString() {
      return "FormularioPreingresoDatosUsuarioVM(gtwayHttp=" + this.getGtwayHttp() + ", solPojovm=" + this.getSolPojovm() + ", modelo=" + this.getModelo() + ", defaultServerURI=" + this.getDefaultServerURI() + ", validoHasta=" + this.getValidoHasta() + ", listaTipoDocumentos=" + this.getListaTipoDocumentos() + ", step=" + this.getStep() + ", estado=" + this.isEstado() + ", conftoken=" + this.getConftoken() + ", trustkeystoretype=" + this.getTrustkeystoretype() + ", uritotrustkeystore=" + this.getUritotrustkeystore() + ", trustkeystorepass=" + this.getTrustkeystorepass() + ", fidutoken=" + this.getFidutoken() + ", usuarioUCA=" + this.isUsuarioUCA() + ", nombreUsuarioCrea=" + this.getNombreUsuarioCrea() + ", direccionEstandar=" + this.getDireccionEstandar() + ")";
   }
}
