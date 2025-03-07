// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.escritura;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

public class EscrituraFirmasCondiciones implements Serializable {
   private String codTipoCondicion;
   private Integer secCondicion;
   private String fijoVariable;
   private Integer numFirmas;
   private BigDecimal valorInicial;
   private BigDecimal valorFinal;
   private String variableSn;
   private String savedSn;
   private String usuarioProceso;
   private String fechaProceso;
   private String modo;
   private Integer currentSecuencial;
   private Map<String, EscrituraCondicionTipo> escrituraCondicionTipo;
   private Map<String, ConceptoUsuarioTipo> conceptoUsuarioTipo;

   public EscrituraFirmasCondiciones() {
   }

   public String getCodTipoCondicion() {
      return this.codTipoCondicion;
   }

   public Integer getSecCondicion() {
      return this.secCondicion;
   }

   public String getFijoVariable() {
      return this.fijoVariable;
   }

   public Integer getNumFirmas() {
      return this.numFirmas;
   }

   public BigDecimal getValorInicial() {
      return this.valorInicial;
   }

   public BigDecimal getValorFinal() {
      return this.valorFinal;
   }

   public String getVariableSn() {
      return this.variableSn;
   }

   public String getSavedSn() {
      return this.savedSn;
   }

   public String getUsuarioProceso() {
      return this.usuarioProceso;
   }

   public String getFechaProceso() {
      return this.fechaProceso;
   }

   public String getModo() {
      return this.modo;
   }

   public Integer getCurrentSecuencial() {
      return this.currentSecuencial;
   }

   public Map<String, EscrituraCondicionTipo> getEscrituraCondicionTipo() {
      return this.escrituraCondicionTipo;
   }

   public Map<String, ConceptoUsuarioTipo> getConceptoUsuarioTipo() {
      return this.conceptoUsuarioTipo;
   }

   public void setCodTipoCondicion(String codTipoCondicion) {
      this.codTipoCondicion = codTipoCondicion;
   }

   public void setSecCondicion(Integer secCondicion) {
      this.secCondicion = secCondicion;
   }

   public void setFijoVariable(String fijoVariable) {
      this.fijoVariable = fijoVariable;
   }

   public void setNumFirmas(Integer numFirmas) {
      this.numFirmas = numFirmas;
   }

   public void setValorInicial(BigDecimal valorInicial) {
      this.valorInicial = valorInicial;
   }

   public void setValorFinal(BigDecimal valorFinal) {
      this.valorFinal = valorFinal;
   }

   public void setVariableSn(String variableSn) {
      this.variableSn = variableSn;
   }

   public void setSavedSn(String savedSn) {
      this.savedSn = savedSn;
   }

   public void setUsuarioProceso(String usuarioProceso) {
      this.usuarioProceso = usuarioProceso;
   }

   public void setFechaProceso(String fechaProceso) {
      this.fechaProceso = fechaProceso;
   }

   public void setModo(String modo) {
      this.modo = modo;
   }

   public void setCurrentSecuencial(Integer currentSecuencial) {
      this.currentSecuencial = currentSecuencial;
   }

   public void setEscrituraCondicionTipo(Map<String, EscrituraCondicionTipo> escrituraCondicionTipo) {
      this.escrituraCondicionTipo = escrituraCondicionTipo;
   }

   public void setConceptoUsuarioTipo(Map<String, ConceptoUsuarioTipo> conceptoUsuarioTipo) {
      this.conceptoUsuarioTipo = conceptoUsuarioTipo;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof EscrituraFirmasCondiciones)) {
         return false;
      } else {
         EscrituraFirmasCondiciones other = (EscrituraFirmasCondiciones)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$secCondicion = this.getSecCondicion();
            Object other$secCondicion = other.getSecCondicion();
            if (this$secCondicion == null) {
               if (other$secCondicion != null) {
                  return false;
               }
            } else if (!this$secCondicion.equals(other$secCondicion)) {
               return false;
            }

            Object this$numFirmas = this.getNumFirmas();
            Object other$numFirmas = other.getNumFirmas();
            if (this$numFirmas == null) {
               if (other$numFirmas != null) {
                  return false;
               }
            } else if (!this$numFirmas.equals(other$numFirmas)) {
               return false;
            }

            Object this$currentSecuencial = this.getCurrentSecuencial();
            Object other$currentSecuencial = other.getCurrentSecuencial();
            if (this$currentSecuencial == null) {
               if (other$currentSecuencial != null) {
                  return false;
               }
            } else if (!this$currentSecuencial.equals(other$currentSecuencial)) {
               return false;
            }

            label158: {
               Object this$codTipoCondicion = this.getCodTipoCondicion();
               Object other$codTipoCondicion = other.getCodTipoCondicion();
               if (this$codTipoCondicion == null) {
                  if (other$codTipoCondicion == null) {
                     break label158;
                  }
               } else if (this$codTipoCondicion.equals(other$codTipoCondicion)) {
                  break label158;
               }

               return false;
            }

            label151: {
               Object this$fijoVariable = this.getFijoVariable();
               Object other$fijoVariable = other.getFijoVariable();
               if (this$fijoVariable == null) {
                  if (other$fijoVariable == null) {
                     break label151;
                  }
               } else if (this$fijoVariable.equals(other$fijoVariable)) {
                  break label151;
               }

               return false;
            }

            Object this$valorInicial = this.getValorInicial();
            Object other$valorInicial = other.getValorInicial();
            if (this$valorInicial == null) {
               if (other$valorInicial != null) {
                  return false;
               }
            } else if (!this$valorInicial.equals(other$valorInicial)) {
               return false;
            }

            label137: {
               Object this$valorFinal = this.getValorFinal();
               Object other$valorFinal = other.getValorFinal();
               if (this$valorFinal == null) {
                  if (other$valorFinal == null) {
                     break label137;
                  }
               } else if (this$valorFinal.equals(other$valorFinal)) {
                  break label137;
               }

               return false;
            }

            label130: {
               Object this$variableSn = this.getVariableSn();
               Object other$variableSn = other.getVariableSn();
               if (this$variableSn == null) {
                  if (other$variableSn == null) {
                     break label130;
                  }
               } else if (this$variableSn.equals(other$variableSn)) {
                  break label130;
               }

               return false;
            }

            Object this$savedSn = this.getSavedSn();
            Object other$savedSn = other.getSavedSn();
            if (this$savedSn == null) {
               if (other$savedSn != null) {
                  return false;
               }
            } else if (!this$savedSn.equals(other$savedSn)) {
               return false;
            }

            Object this$usuarioProceso = this.getUsuarioProceso();
            Object other$usuarioProceso = other.getUsuarioProceso();
            if (this$usuarioProceso == null) {
               if (other$usuarioProceso != null) {
                  return false;
               }
            } else if (!this$usuarioProceso.equals(other$usuarioProceso)) {
               return false;
            }

            label109: {
               Object this$fechaProceso = this.getFechaProceso();
               Object other$fechaProceso = other.getFechaProceso();
               if (this$fechaProceso == null) {
                  if (other$fechaProceso == null) {
                     break label109;
                  }
               } else if (this$fechaProceso.equals(other$fechaProceso)) {
                  break label109;
               }

               return false;
            }

            label102: {
               Object this$modo = this.getModo();
               Object other$modo = other.getModo();
               if (this$modo == null) {
                  if (other$modo == null) {
                     break label102;
                  }
               } else if (this$modo.equals(other$modo)) {
                  break label102;
               }

               return false;
            }

            Object this$escrituraCondicionTipo = this.getEscrituraCondicionTipo();
            Object other$escrituraCondicionTipo = other.getEscrituraCondicionTipo();
            if (this$escrituraCondicionTipo == null) {
               if (other$escrituraCondicionTipo != null) {
                  return false;
               }
            } else if (!this$escrituraCondicionTipo.equals(other$escrituraCondicionTipo)) {
               return false;
            }

            Object this$conceptoUsuarioTipo = this.getConceptoUsuarioTipo();
            Object other$conceptoUsuarioTipo = other.getConceptoUsuarioTipo();
            if (this$conceptoUsuarioTipo == null) {
               if (other$conceptoUsuarioTipo != null) {
                  return false;
               }
            } else if (!this$conceptoUsuarioTipo.equals(other$conceptoUsuarioTipo)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof EscrituraFirmasCondiciones;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $secCondicion = this.getSecCondicion();
      result = result * 59 + ($secCondicion == null ? 43 : $secCondicion.hashCode());
      Object $numFirmas = this.getNumFirmas();
      result = result * 59 + ($numFirmas == null ? 43 : $numFirmas.hashCode());
      Object $currentSecuencial = this.getCurrentSecuencial();
      result = result * 59 + ($currentSecuencial == null ? 43 : $currentSecuencial.hashCode());
      Object $codTipoCondicion = this.getCodTipoCondicion();
      result = result * 59 + ($codTipoCondicion == null ? 43 : $codTipoCondicion.hashCode());
      Object $fijoVariable = this.getFijoVariable();
      result = result * 59 + ($fijoVariable == null ? 43 : $fijoVariable.hashCode());
      Object $valorInicial = this.getValorInicial();
      result = result * 59 + ($valorInicial == null ? 43 : $valorInicial.hashCode());
      Object $valorFinal = this.getValorFinal();
      result = result * 59 + ($valorFinal == null ? 43 : $valorFinal.hashCode());
      Object $variableSn = this.getVariableSn();
      result = result * 59 + ($variableSn == null ? 43 : $variableSn.hashCode());
      Object $savedSn = this.getSavedSn();
      result = result * 59 + ($savedSn == null ? 43 : $savedSn.hashCode());
      Object $usuarioProceso = this.getUsuarioProceso();
      result = result * 59 + ($usuarioProceso == null ? 43 : $usuarioProceso.hashCode());
      Object $fechaProceso = this.getFechaProceso();
      result = result * 59 + ($fechaProceso == null ? 43 : $fechaProceso.hashCode());
      Object $modo = this.getModo();
      result = result * 59 + ($modo == null ? 43 : $modo.hashCode());
      Object $escrituraCondicionTipo = this.getEscrituraCondicionTipo();
      result = result * 59 + ($escrituraCondicionTipo == null ? 43 : $escrituraCondicionTipo.hashCode());
      Object $conceptoUsuarioTipo = this.getConceptoUsuarioTipo();
      result = result * 59 + ($conceptoUsuarioTipo == null ? 43 : $conceptoUsuarioTipo.hashCode());
      return result;
   }

   public String toString() {
      return "EscrituraFirmasCondiciones(codTipoCondicion=" + this.getCodTipoCondicion() + ", secCondicion=" + this.getSecCondicion() + ", fijoVariable=" + this.getFijoVariable() + ", numFirmas=" + this.getNumFirmas() + ", valorInicial=" + this.getValorInicial() + ", valorFinal=" + this.getValorFinal() + ", variableSn=" + this.getVariableSn() + ", savedSn=" + this.getSavedSn() + ", usuarioProceso=" + this.getUsuarioProceso() + ", fechaProceso=" + this.getFechaProceso() + ", modo=" + this.getModo() + ", currentSecuencial=" + this.getCurrentSecuencial() + ", escrituraCondicionTipo=" + this.getEscrituraCondicionTipo() + ", conceptoUsuarioTipo=" + this.getConceptoUsuarioTipo() + ")";
   }
}
