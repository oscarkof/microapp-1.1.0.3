// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.seguridad;

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
@JsonPropertyOrder({"loginusuario", "codigogrupousuario", "descripcionusuario", "oficinaingresoweb", "estadousuario", "correo", "usuario", "ip", "nombremaquina", "tipo", "tipousuario", "autenticado", "clave"})
public class UsuarioFidusap implements Serializable {
   @JsonProperty("loginusuario")
   private String loginusuario;
   @JsonProperty("codigogrupousuario")
   private String codigogrupousuario;
   @JsonProperty("descripcionusuario")
   private String descripcionusuario;
   @JsonProperty("oficinaingresoweb")
   private String oficinaingresoweb;
   @JsonProperty("estadousuario")
   private String estadousuario;
   @JsonProperty("correo")
   private String correo;
   @JsonProperty("usuario")
   private String usuario;
   @JsonProperty("ip")
   private String ip;
   @JsonProperty("nombremaquina")
   private String nombremaquina;
   @JsonProperty("tipo")
   private String tipo;
   @JsonProperty("tipousuario")
   private String tipousuario;
   @JsonProperty("autenticado")
   private boolean autenticado;
   @JsonProperty("clave")
   private String clave;
   @JsonIgnore
   private Map<String, Object> additionalProperties = new HashMap();

   public UsuarioFidusap() {
   }

   @JsonProperty("autenticado")
   public boolean isAutenticado() {
      return this.autenticado;
   }

   @JsonProperty("autenticado")
   public void setAutenticado(boolean autenticado) {
      this.autenticado = autenticado;
   }

   @JsonProperty("nombremaquina")
   public String getNombremaquina() {
      return this.nombremaquina;
   }

   @JsonProperty("nombremaquina")
   public void setNombremaquina(String nombremaquina) {
      this.nombremaquina = nombremaquina;
   }

   @JsonProperty("clave")
   public String getClave() {
      return this.clave;
   }

   @JsonProperty("clave")
   public void setClave(String clave) {
      this.clave = clave;
   }

   @JsonProperty("loginusuario")
   public String getLoginusuario() {
      return this.loginusuario;
   }

   @JsonProperty("loginusuario")
   public void setLoginusuario(String loginusuario) {
      this.loginusuario = loginusuario;
   }

   @JsonProperty("codigogrupousuario")
   public String getCodigogrupousuario() {
      return this.codigogrupousuario;
   }

   @JsonProperty("codigogrupousuario")
   public void setCodigogrupousuario(String codigogrupousuario) {
      this.codigogrupousuario = codigogrupousuario;
   }

   @JsonProperty("descripcionusuario")
   public String getDescripcionusuario() {
      return this.descripcionusuario;
   }

   @JsonProperty("descripcionusuario")
   public void setDescripcionusuario(String descripcionusuario) {
      this.descripcionusuario = descripcionusuario;
   }

   @JsonProperty("oficinaingresoweb")
   public String getOficinaingresoweb() {
      return this.oficinaingresoweb;
   }

   @JsonProperty("oficinaingresoweb")
   public void setOficinaingresoweb(String oficinaingresoweb) {
      this.oficinaingresoweb = oficinaingresoweb;
   }

   @JsonProperty("estadousuario")
   public String getEstadousuario() {
      return this.estadousuario;
   }

   @JsonProperty("estadousuario")
   public void setEstadousuario(String estadousuario) {
      this.estadousuario = estadousuario;
   }

   @JsonProperty("correo")
   public String getCorreo() {
      return this.correo;
   }

   @JsonProperty("correo")
   public void setCorreo(String correo) {
      this.correo = correo;
   }

   @JsonProperty("usuario")
   public String getUsuario() {
      return this.usuario;
   }

   @JsonProperty("usuario")
   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   @JsonProperty("ip")
   public String getIp() {
      return this.ip;
   }

   @JsonProperty("ip")
   public void setIp(String ip) {
      this.ip = ip;
   }

   @JsonProperty("tipo")
   public String getTipo() {
      return this.tipo;
   }

   @JsonProperty("tipo")
   public void setTipo(String tipo) {
      this.tipo = tipo;
   }

   @JsonProperty("tipousuario")
   public String getTipousuario() {
      return this.tipousuario;
   }

   @JsonProperty("tipousuario")
   public void setTipousuario(String tipousuario) {
      this.tipousuario = tipousuario;
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
