package com.fiduciaria.microapp.store.model.dominio;

import com.fiduciaria.microapp.store.model.negocio.NegocioPojo;
import com.fiduciaria.microapp.store.model.usuario.UsuarioPojo;
import java.util.Map;
import org.apache.wicket.util.io.IClusterable;

public class DominioPojo implements IClusterable {
  private Integer codigoGrupo;
  
  private String nombreDominio;
  
  private String descripcionDominio;
  
  private String identificador;
  
  private String estadoDominio;
  
  private Long fechaCreacion;
  
  private String borrable;
  
  private Map<String, NegocioPojo> negociosDominio;
  
  private Map<String, UsuarioPojo> administradoresDominio;
  
  private Map<String, UsuarioPojo> usuarioDominio;
  
  public void setCodigoGrupo(Integer codigoGrupo) {
    this.codigoGrupo = codigoGrupo;
  }
  
  public void setNombreDominio(String nombreDominio) {
    this.nombreDominio = nombreDominio;
  }
  
  public void setDescripcionDominio(String descripcionDominio) {
    this.descripcionDominio = descripcionDominio;
  }
  
  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }
  
  public void setEstadoDominio(String estadoDominio) {
    this.estadoDominio = estadoDominio;
  }
  
  public void setFechaCreacion(Long fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }
  
  public void setBorrable(String borrable) {
    this.borrable = borrable;
  }
  
  public void setNegociosDominio(Map<String, NegocioPojo> negociosDominio) {
    this.negociosDominio = negociosDominio;
  }
  
  public void setAdministradoresDominio(Map<String, UsuarioPojo> administradoresDominio) {
    this.administradoresDominio = administradoresDominio;
  }
  
  public void setUsuarioDominio(Map<String, UsuarioPojo> usuarioDominio) {
    this.usuarioDominio = usuarioDominio;
  }
  
  /*public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof DominioPojo))
      return false; 
    DominioPojo other = (DominioPojo)o;
    if (!other.canEqual(this))
      return false; 
    Object this$codigoGrupo = getCodigoGrupo(), other$codigoGrupo = other.getCodigoGrupo();
    if ((this$codigoGrupo == null) ? (other$codigoGrupo != null) : !this$codigoGrupo.equals(other$codigoGrupo))
      return false; 
    Object this$fechaCreacion = getFechaCreacion(), other$fechaCreacion = other.getFechaCreacion();
    if ((this$fechaCreacion == null) ? (other$fechaCreacion != null) : !this$fechaCreacion.equals(other$fechaCreacion))
      return false; 
    Object this$nombreDominio = getNombreDominio(), other$nombreDominio = other.getNombreDominio();
    if ((this$nombreDominio == null) ? (other$nombreDominio != null) : !this$nombreDominio.equals(other$nombreDominio))
      return false; 
    Object this$descripcionDominio = getDescripcionDominio(), other$descripcionDominio = other.getDescripcionDominio();
    if ((this$descripcionDominio == null) ? (other$descripcionDominio != null) : !this$descripcionDominio.equals(other$descripcionDominio))
      return false; 
    Object this$identificador = getIdentificador(), other$identificador = other.getIdentificador();
    if ((this$identificador == null) ? (other$identificador != null) : !this$identificador.equals(other$identificador))
      return false; 
    Object this$estadoDominio = getEstadoDominio(), other$estadoDominio = other.getEstadoDominio();
    if ((this$estadoDominio == null) ? (other$estadoDominio != null) : !this$estadoDominio.equals(other$estadoDominio))
      return false; 
    Object this$borrable = getBorrable(), other$borrable = other.getBorrable();
    if ((this$borrable == null) ? (other$borrable != null) : !this$borrable.equals(other$borrable))
      return false; 
    Object<String, NegocioPojo> this$negociosDominio = (Object<String, NegocioPojo>)getNegociosDominio(), other$negociosDominio = (Object<String, NegocioPojo>)other.getNegociosDominio();
    if ((this$negociosDominio == null) ? (other$negociosDominio != null) : !this$negociosDominio.equals(other$negociosDominio))
      return false; 
    Object<String, UsuarioPojo> this$administradoresDominio = (Object<String, UsuarioPojo>)getAdministradoresDominio(), other$administradoresDominio = (Object<String, UsuarioPojo>)other.getAdministradoresDominio();
    if ((this$administradoresDominio == null) ? (other$administradoresDominio != null) : !this$administradoresDominio.equals(other$administradoresDominio))
      return false; 
    Object<String, UsuarioPojo> this$usuarioDominio = (Object<String, UsuarioPojo>)getUsuarioDominio(), other$usuarioDominio = (Object<String, UsuarioPojo>)other.getUsuarioDominio();
    return !((this$usuarioDominio == null) ? (other$usuarioDominio != null) : !this$usuarioDominio.equals(other$usuarioDominio));
  }*/
  
  protected boolean canEqual(Object other) {
    return other instanceof DominioPojo;
  }
  
  public int hashCode() {
    int PRIME = 59;
    int result = 1;
    Object $codigoGrupo = getCodigoGrupo();
    result = result * 59 + (($codigoGrupo == null) ? 43 : $codigoGrupo.hashCode());
    Object $fechaCreacion = getFechaCreacion();
    result = result * 59 + (($fechaCreacion == null) ? 43 : $fechaCreacion.hashCode());
    Object $nombreDominio = getNombreDominio();
    result = result * 59 + (($nombreDominio == null) ? 43 : $nombreDominio.hashCode());
    Object $descripcionDominio = getDescripcionDominio();
    result = result * 59 + (($descripcionDominio == null) ? 43 : $descripcionDominio.hashCode());
    Object $identificador = getIdentificador();
    result = result * 59 + (($identificador == null) ? 43 : $identificador.hashCode());
    Object $estadoDominio = getEstadoDominio();
    result = result * 59 + (($estadoDominio == null) ? 43 : $estadoDominio.hashCode());
    Object $borrable = getBorrable();
    result = result * 59 + (($borrable == null) ? 43 : $borrable.hashCode());
    /*Object<String, NegocioPojo> $negociosDominio = (Object<String, NegocioPojo>)getNegociosDominio();
    result = result * 59 + (($negociosDominio == null) ? 43 : $negociosDominio.hashCode());
    Object<String, UsuarioPojo> $administradoresDominio = (Object<String, UsuarioPojo>)getAdministradoresDominio();
    result = result * 59 + (($administradoresDominio == null) ? 43 : $administradoresDominio.hashCode());
    Object<String, UsuarioPojo> $usuarioDominio = (Object<String, UsuarioPojo>)getUsuarioDominio();*/
    return result;
  }
  
  public String toString() {
    return "DominioPojo(codigoGrupo=" + getCodigoGrupo() + ", nombreDominio=" + getNombreDominio() + ", descripcionDominio=" + getDescripcionDominio() + ", identificador=" + getIdentificador() + ", estadoDominio=" + getEstadoDominio() + ", fechaCreacion=" + getFechaCreacion() + ", borrable=" + getBorrable() + ", negociosDominio=" + getNegociosDominio() + ", administradoresDominio=" + getAdministradoresDominio() + ", usuarioDominio=" + getUsuarioDominio() + ")";
  }
  
  public Integer getCodigoGrupo() {
    return this.codigoGrupo;
  }
  
  public String getNombreDominio() {
    return this.nombreDominio;
  }
  
  public String getDescripcionDominio() {
    return this.descripcionDominio;
  }
  
  public String getIdentificador() {
    return this.identificador;
  }
  
  public String getEstadoDominio() {
    return this.estadoDominio;
  }
  
  public Long getFechaCreacion() {
    return this.fechaCreacion;
  }
  
  public String getBorrable() {
    return this.borrable;
  }
  
  public Map<String, NegocioPojo> getNegociosDominio() {
    return this.negociosDominio;
  }
  
  public Map<String, UsuarioPojo> getAdministradoresDominio() {
    return this.administradoresDominio;
  }
  
  public Map<String, UsuarioPojo> getUsuarioDominio() {
    return this.usuarioDominio;
  }
}
