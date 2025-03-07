package com.fiduciaria.microapp.store.model.negocio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiduciaria.microapp.store.model.fideicomiso.FideicomisoPojo;
import com.fiduciaria.microapp.store.model.referunica.ReferenciaUnicaPojo;
import java.util.Objects;
import org.apache.wicket.util.io.IClusterable;

public class NegocioPojo implements IClusterable {
  private String codigoNegocio;
  
  private String descripcionNegocio;
  
  private String tipoIdentificacion;
  
  private String identificacion;
  
  private String digitosVerificacion;
  
  private String nombresRazonSocial;
  
  private String estado;
  
  private String tiporeferencia;
  
  private EnumTipoNegocio tipoNegocio;
  
  private ReferenciaUnicaPojo referenciaUnica;
  
  private FideicomisoPojo fideicomiso;
  
  public void setCodigoNegocio(String codigoNegocio) {
    this.codigoNegocio = codigoNegocio;
  }
  
  public void setDescripcionNegocio(String descripcionNegocio) {
    this.descripcionNegocio = descripcionNegocio;
  }
  
  public void setTipoIdentificacion(String tipoIdentificacion) {
    this.tipoIdentificacion = tipoIdentificacion;
  }
  
  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }
  
  public void setDigitosVerificacion(String digitosVerificacion) {
    this.digitosVerificacion = digitosVerificacion;
  }
  
  public void setNombresRazonSocial(String nombresRazonSocial) {
    this.nombresRazonSocial = nombresRazonSocial;
  }
  
  public void setEstado(String estado) {
    this.estado = estado;
  }
  
  public void setTiporeferencia(String tiporeferencia) {
    this.tiporeferencia = tiporeferencia;
  }
  
  public void setTipoNegocio(EnumTipoNegocio tipoNegocio) {
    this.tipoNegocio = tipoNegocio;
  }
  
  public void setReferenciaUnica(ReferenciaUnicaPojo referenciaUnica) {
    this.referenciaUnica = referenciaUnica;
  }
  
  public void setFideicomiso(FideicomisoPojo fideicomiso) {
    this.fideicomiso = fideicomiso;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof NegocioPojo))
      return false; 
    NegocioPojo other = (NegocioPojo)o;
    if (!other.canEqual(this))
      return false; 
    Object this$codigoNegocio = getCodigoNegocio(), other$codigoNegocio = other.getCodigoNegocio();
    if ((this$codigoNegocio == null) ? (other$codigoNegocio != null) : !this$codigoNegocio.equals(other$codigoNegocio))
      return false; 
    Object this$descripcionNegocio = getDescripcionNegocio(), other$descripcionNegocio = other.getDescripcionNegocio();
    if ((this$descripcionNegocio == null) ? (other$descripcionNegocio != null) : !this$descripcionNegocio.equals(other$descripcionNegocio))
      return false; 
    Object this$tipoIdentificacion = getTipoIdentificacion(), other$tipoIdentificacion = other.getTipoIdentificacion();
    if ((this$tipoIdentificacion == null) ? (other$tipoIdentificacion != null) : !this$tipoIdentificacion.equals(other$tipoIdentificacion))
      return false; 
    Object this$identificacion = getIdentificacion(), other$identificacion = other.getIdentificacion();
    if ((this$identificacion == null) ? (other$identificacion != null) : !this$identificacion.equals(other$identificacion))
      return false; 
    Object this$digitosVerificacion = getDigitosVerificacion(), other$digitosVerificacion = other.getDigitosVerificacion();
    if ((this$digitosVerificacion == null) ? (other$digitosVerificacion != null) : !this$digitosVerificacion.equals(other$digitosVerificacion))
      return false; 
    Object this$nombresRazonSocial = getNombresRazonSocial(), other$nombresRazonSocial = other.getNombresRazonSocial();
    if ((this$nombresRazonSocial == null) ? (other$nombresRazonSocial != null) : !this$nombresRazonSocial.equals(other$nombresRazonSocial))
      return false; 
    Object this$estado = getEstado(), other$estado = other.getEstado();
    if ((this$estado == null) ? (other$estado != null) : !this$estado.equals(other$estado))
      return false; 
    Object this$tiporeferencia = getTiporeferencia(), other$tiporeferencia = other.getTiporeferencia();
    if ((this$tiporeferencia == null) ? (other$tiporeferencia != null) : !this$tiporeferencia.equals(other$tiporeferencia))
      return false; 
    Object this$tipoNegocio = getTipoNegocio(), other$tipoNegocio = other.getTipoNegocio();
    if ((this$tipoNegocio == null) ? (other$tipoNegocio != null) : !this$tipoNegocio.equals(other$tipoNegocio))
      return false; 
    Object this$referenciaUnica = getReferenciaUnica(), other$referenciaUnica = other.getReferenciaUnica();
    if ((this$referenciaUnica == null) ? (other$referenciaUnica != null) : !this$referenciaUnica.equals(other$referenciaUnica))
      return false; 
    Object this$fideicomiso = getFideicomiso(), other$fideicomiso = other.getFideicomiso();
    return !((this$fideicomiso == null) ? (other$fideicomiso != null) : !this$fideicomiso.equals(other$fideicomiso));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof NegocioPojo;
  }
  
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $codigoNegocio = getCodigoNegocio();
    result = result * 59 + (($codigoNegocio == null) ? 43 : $codigoNegocio.hashCode());
    Object $descripcionNegocio = getDescripcionNegocio();
    result = result * 59 + (($descripcionNegocio == null) ? 43 : $descripcionNegocio.hashCode());
    Object $tipoIdentificacion = getTipoIdentificacion();
    result = result * 59 + (($tipoIdentificacion == null) ? 43 : $tipoIdentificacion.hashCode());
    Object $identificacion = getIdentificacion();
    result = result * 59 + (($identificacion == null) ? 43 : $identificacion.hashCode());
    Object $digitosVerificacion = getDigitosVerificacion();
    result = result * 59 + (($digitosVerificacion == null) ? 43 : $digitosVerificacion.hashCode());
    Object $nombresRazonSocial = getNombresRazonSocial();
    result = result * 59 + (($nombresRazonSocial == null) ? 43 : $nombresRazonSocial.hashCode());
    Object $estado = getEstado();
    result = result * 59 + (($estado == null) ? 43 : $estado.hashCode());
    Object $tiporeferencia = getTiporeferencia();
    result = result * 59 + (($tiporeferencia == null) ? 43 : $tiporeferencia.hashCode());
    Object $tipoNegocio = getTipoNegocio();
    result = result * 59 + (($tipoNegocio == null) ? 43 : $tipoNegocio.hashCode());
    Object $referenciaUnica = getReferenciaUnica();
    result = result * 59 + (($referenciaUnica == null) ? 43 : $referenciaUnica.hashCode());
    Object $fideicomiso = getFideicomiso();
    return result * 59 + (($fideicomiso == null) ? 43 : $fideicomiso.hashCode());
  }
  
  public String toString() {
    return "NegocioPojo(codigoNegocio=" + getCodigoNegocio() + ", descripcionNegocio=" + getDescripcionNegocio() + ", tipoIdentificacion=" + getTipoIdentificacion() + ", identificacion=" + getIdentificacion() + ", digitosVerificacion=" + getDigitosVerificacion() + ", nombresRazonSocial=" + getNombresRazonSocial() + ", estado=" + getEstado() + ", tiporeferencia=" + getTiporeferencia() + ", tipoNegocio=" + getTipoNegocio() + ", referenciaUnica=" + getReferenciaUnica() + ", fideicomiso=" + getFideicomiso() + ")";
  }
  
  public String getDescripcionNegocio() {
    return this.descripcionNegocio;
  }
  
  public String getTipoIdentificacion() {
    return this.tipoIdentificacion;
  }
  
  public String getIdentificacion() {
    return this.identificacion;
  }
  
  public String getDigitosVerificacion() {
    return this.digitosVerificacion;
  }
  
  public String getNombresRazonSocial() {
    return this.nombresRazonSocial;
  }
  
  public String getEstado() {
    return this.estado;
  }
  
  public String getTiporeferencia() {
    return this.tiporeferencia;
  }
  
  public EnumTipoNegocio getTipoNegocio() {
    return this.tipoNegocio;
  }
  
  public ReferenciaUnicaPojo getReferenciaUnica() {
    return this.referenciaUnica;
  }
  
  public FideicomisoPojo getFideicomiso() {
    return this.fideicomiso;
  }
  
  @JsonIgnore
  public void limpiar() {
    this.referenciaUnica.limpiar();
    this.fideicomiso.limpiar();
  }
  
  @JsonIgnore
  public String getNombreNegocio() {
    String response = "";
    switch (this.tipoNegocio) {
      case FIDEICOMISO:
        if (Objects.nonNull(this.fideicomiso)) {
          response = this.fideicomiso.getNombrefideicomiso();
          break;
        } 
        response = this.descripcionNegocio;
        break;
      case REFERENCIA_UNICA_ALPHA:
        if (Objects.nonNull(this.referenciaUnica)) {
          response = this.referenciaUnica.getDescripcionReferencia();
          break;
        } 
        response = this.descripcionNegocio;
        break;
    } 
    return response;
  }
  
  public String getCodigoNegocio() {
    String response = "";
    switch (this.tipoNegocio) {
      case FIDEICOMISO:
        if (Objects.nonNull(this.fideicomiso)) {
          response = this.fideicomiso.getCodigotipofideicomiso() + "-" + this.fideicomiso.getCodigosubtipofideicomiso() + "-" + this.fideicomiso.getCodigofideicomiso();
          break;
        } 
        response = this.codigoNegocio;
        break;
      case REFERENCIA_UNICA_ALPHA:
        if (Objects.nonNull(this.referenciaUnica) && 
          Objects.nonNull(this.referenciaUnica.getNumeroReferencia()) && 
          !this.referenciaUnica.getNumeroReferencia().isEmpty()) {
          response = this.referenciaUnica.getNumeroReferencia();
          break;
        } 
        response = this.codigoNegocio;
        break;
    } 
    return response;
  }
}
