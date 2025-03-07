// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.config;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.HashMap;
import java.util.Map;
import org.apache.wicket.util.io.IClusterable;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"nombrevariable", "valorvariable"})
public class SecParam implements IClusterable {
   @JsonProperty("nombrevariable")
   private String nombrevariable;
   @JsonProperty("valorvariable")
   private String valorvariable;
   @JsonIgnore
   private Map<String, Object> additionalProperties = new HashMap();

   public SecParam() {
   }

   @JsonProperty("nombrevariable")
   public String getNombrevariable() {
      return this.nombrevariable;
   }

   @JsonProperty("nombrevariable")
   public void setNombrevariable(String nombrevariable) {
      this.nombrevariable = nombrevariable;
   }

   @JsonProperty("valorvariable")
   public String getValorvariable() {
      return this.valorvariable;
   }

   @JsonProperty("valorvariable")
   public void setValorvariable(String valorvariable) {
      this.valorvariable = valorvariable;
   }

   @JsonAnyGetter
   public Map<String, Object> getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }
}
