// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.fideicomiso;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"cccodigo", "tipocontabilidad", "codigotipofideicomiso", "codigosubtipofideicomiso", "codigofideicomiso", "nombrefideicomiso", "numerofideicomiso", "nombreetapafideicomiso"})
public class FideicomisoPojo implements Serializable {
   @JsonProperty("codigotipofideicomiso")
   private String codigotipofideicomiso;
   @JsonProperty("codigosubtipofideicomiso")
   private String codigosubtipofideicomiso;
   @JsonProperty("codigofideicomiso")
   private String codigofideicomiso;
   @JsonProperty("nombrefideicomiso")
   private String nombrefideicomiso;
   @JsonProperty("numerofideicomiso")
   private String numerofideicomiso;
   @JsonProperty("nombreetapafideicomiso")
   private String nombreetapafideicomiso;
   @JsonProperty("cccodigo")
   private String cccodigo;
   @JsonProperty("tipocontabilidad")
   private String tipocontabilidad;
   @JsonIgnore
   private Map<String, Object> additionalProperties = new HashMap();

   public FideicomisoPojo() {
   }

   @JsonProperty("codigotipofideicomiso")
   public String getCodigotipofideicomiso() {
      return this.codigotipofideicomiso;
   }

   @JsonProperty("numerofideicomiso")
   public String getNumerofideicomiso() {
      return this.numerofideicomiso;
   }

   @JsonProperty("numerofideicomiso")
   public void setNumerofideicomiso(String numerofideicomiso) {
      this.numerofideicomiso = numerofideicomiso;
   }

   @JsonProperty("codigotipofideicomiso")
   public void setCodigotipofideicomiso(String codigotipofideicomiso) {
      this.codigotipofideicomiso = codigotipofideicomiso;
   }

   @JsonProperty("codigosubtipofideicomiso")
   public String getCodigosubtipofideicomiso() {
      return this.codigosubtipofideicomiso;
   }

   @JsonProperty("codigosubtipofideicomiso")
   public void setCodigosubtipofideicomiso(String codigosubtipofideicomiso) {
      this.codigosubtipofideicomiso = codigosubtipofideicomiso;
   }

   @JsonProperty("codigofideicomiso")
   public String getCodigofideicomiso() {
      return this.codigofideicomiso;
   }

   @JsonProperty("codigofideicomiso")
   public void setCodigofideicomiso(String codigofideicomiso) {
      this.codigofideicomiso = codigofideicomiso;
   }

   @JsonProperty("nombrefideicomiso")
   public String getNombrefideicomiso() {
      return this.nombrefideicomiso;
   }

   @JsonProperty("nombrefideicomiso")
   public void setNombrefideicomiso(String nombrefideicomiso) {
      this.nombrefideicomiso = nombrefideicomiso;
   }

   @JsonProperty("nombreetapafideicomiso")
   public String getNombreetapafideicomiso() {
      return this.nombreetapafideicomiso;
   }

   @JsonProperty("nombreetapafideicomiso")
   public void setNombreetapafideicomiso(String nombreetapafideicomiso) {
      this.nombreetapafideicomiso = nombreetapafideicomiso;
   }

   @JsonProperty("cccodigo")
   public String getCccodigo() {
      return this.cccodigo;
   }

   @JsonProperty("cccodigo")
   public void setCccodigo(String cccodigo) {
      this.cccodigo = cccodigo;
   }

   @JsonProperty("tipocontabilidad")
   public String getTipocontabilidad() {
      return this.tipocontabilidad;
   }

   @JsonProperty("tipocontabilidad")
   public void setTipocontabilidad(String tipocontabilidad) {
      this.tipocontabilidad = tipocontabilidad;
   }

   @JsonAnyGetter
   public Map<String, Object> getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }

   @JsonIgnore
   public void copiarDesde(FideicomisoPojo desde) {
      this.cccodigo = desde.getCccodigo();
      this.tipocontabilidad = desde.getTipocontabilidad();
      this.codigotipofideicomiso = desde.getCodigotipofideicomiso();
      this.codigosubtipofideicomiso = desde.getCodigosubtipofideicomiso();
      this.codigofideicomiso = desde.getCodigofideicomiso();
      this.nombrefideicomiso = desde.getNombrefideicomiso();
      this.numerofideicomiso = desde.getNumerofideicomiso();
      this.nombreetapafideicomiso = desde.getNombreetapafideicomiso();
   }

   @JsonIgnore
   public void limpiar() {
      this.cccodigo = null;
      this.tipocontabilidad = null;
      this.codigotipofideicomiso = null;
      this.codigosubtipofideicomiso = null;
      this.codigofideicomiso = null;
      this.nombrefideicomiso = null;
      this.numerofideicomiso = null;
      this.nombreetapafideicomiso = null;
   }
}
