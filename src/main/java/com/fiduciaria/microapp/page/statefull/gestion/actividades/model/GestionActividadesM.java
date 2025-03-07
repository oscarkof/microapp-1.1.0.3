// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.actividades.model;

import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import com.fiduciaria.microapp.store.model.persona.PersonaPojo;
import com.fiduciaria.microapp.store.seguridad.PrincipalPojo;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.util.io.IClusterable;

public class GestionActividadesM implements IClusterable {
   private final NegocioPojo negocio;
   private final List<PrincipalPojo> usuarios;
   private final List<PersonaPojo> personas;

   public GestionActividadesM() {
      this.negocio = new NegocioPojo();
      this.personas = new ArrayList();
      this.usuarios = new ArrayList();
   }

   public NegocioPojo getNegocio() {
      return this.negocio;
   }

   public List<PrincipalPojo> getUsuarios() {
      return this.usuarios;
   }

   public List<PersonaPojo> getPersonas() {
      return this.personas;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof GestionActividadesM)) {
         return false;
      } else {
         GestionActividadesM other = (GestionActividadesM)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label47: {
               Object this$negocio = this.getNegocio();
               Object other$negocio = other.getNegocio();
               if (this$negocio == null) {
                  if (other$negocio == null) {
                     break label47;
                  }
               } else if (this$negocio.equals(other$negocio)) {
                  break label47;
               }

               return false;
            }

            Object this$usuarios = this.getUsuarios();
            Object other$usuarios = other.getUsuarios();
            if (this$usuarios == null) {
               if (other$usuarios != null) {
                  return false;
               }
            } else if (!this$usuarios.equals(other$usuarios)) {
               return false;
            }

            Object this$personas = this.getPersonas();
            Object other$personas = other.getPersonas();
            if (this$personas == null) {
               if (other$personas != null) {
                  return false;
               }
            } else if (!this$personas.equals(other$personas)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof GestionActividadesM;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $negocio = this.getNegocio();
      result = result * 59 + ($negocio == null ? 43 : $negocio.hashCode());
      Object $usuarios = this.getUsuarios();
      result = result * 59 + ($usuarios == null ? 43 : $usuarios.hashCode());
      Object $personas = this.getPersonas();
      result = result * 59 + ($personas == null ? 43 : $personas.hashCode());
      return result;
   }

   public String toString() {
      return "GestionActividadesM(negocio=" + this.getNegocio() + ", usuarios=" + this.getUsuarios() + ", personas=" + this.getPersonas() + ")";
   }

   public GestionActividadesM(NegocioPojo negocio, List<PrincipalPojo> usuarios, List<PersonaPojo> personas) {
      this.negocio = negocio;
      this.usuarios = usuarios;
      this.personas = personas;
   }
}
