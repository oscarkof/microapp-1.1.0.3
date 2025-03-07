// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud.model;

import java.math.BigDecimal;
import org.apache.wicket.util.io.IClusterable;

public class SolicitudPojo implements IClusterable {
   private BigDecimal radicadoId;
   private String tipoSolicitud;
   private String idRefNegocio;
   private String nombreRefNegocio;
   private String fechaHora;
   private String correoSolicita;
   private String descripcion;
   private String nombreSolicita;
   private String apellidoSolicita;
   private String segundoNombre;
   private String segundoApellido;
   private String telefonoSolicita;
   private String tipoDocSolicita;
   private String identificacionSolicita;
   private String celularSolicita;
   private String idAdjunto;
   private String adjunto;
   private String aceptacionTrDatos;
   private String correoForm;
   private String direccion;
   private String rol;
   private String rolFideicomiso;
   private String rolFideicomisoOtro;
   private String condiManejoRolcargaOtra;
   private String condiManejo;
   private String conUsrAuthz;
   private String token;
   private String estadoSolicitud;
   private String descripcionDelCaso;
   private String estado;
   private String usuarioCrea;
   private String numUsuarios;

   public SolicitudPojo() {
   }

   public String toString() {
      return "[Contact id=" + this.radicadoId + " firstName=" + this.nombreSolicita + " lastName=" + this.apellidoSolicita + " celularSolicita=" + this.celularSolicita + " direccion=" + this.direccion + "]";
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof SolicitudPojo)) {
         return false;
      } else {
         SolicitudPojo other = (SolicitudPojo)obj;
         return other.getRadicadoId().equals(this.getRadicadoId()) && other.getNombreSolicita().equals(this.getNombreSolicita()) && other.getSegundoNombre().equals(this.getSegundoNombre()) && other.getCelularSolicita().equals(this.getCelularSolicita()) && other.getApellidoSolicita().equals(this.getApellidoSolicita()) && other.getSegundoApellido().equals(this.getSegundoApellido());
      }
   }

   public int hashCode() {
      int result = this.getNombreSolicita().hashCode();
      result = 31 * result + this.getApellidoSolicita().hashCode();
      result = 31 * result + this.getCelularSolicita().hashCode();
      result = 31 * result + this.getSegundoNombre().hashCode();
      result = 31 * result + this.getSegundoApellido().hashCode();
      return result;
   }

   public BigDecimal getRadicadoId() {
      return this.radicadoId;
   }

   public String getTipoSolicitud() {
      return this.tipoSolicitud;
   }

   public String getIdRefNegocio() {
      return this.idRefNegocio;
   }

   public String getNombreRefNegocio() {
      return this.nombreRefNegocio;
   }

   public String getFechaHora() {
      return this.fechaHora;
   }

   public String getCorreoSolicita() {
      return this.correoSolicita;
   }

   public String getDescripcion() {
      return this.descripcion;
   }

   public String getNombreSolicita() {
      return this.nombreSolicita;
   }

   public String getApellidoSolicita() {
      return this.apellidoSolicita;
   }

   public String getSegundoNombre() {
      return this.segundoNombre;
   }

   public String getSegundoApellido() {
      return this.segundoApellido;
   }

   public String getTelefonoSolicita() {
      return this.telefonoSolicita;
   }

   public String getTipoDocSolicita() {
      return this.tipoDocSolicita;
   }

   public String getIdentificacionSolicita() {
      return this.identificacionSolicita;
   }

   public String getCelularSolicita() {
      return this.celularSolicita;
   }

   public String getIdAdjunto() {
      return this.idAdjunto;
   }

   public String getAdjunto() {
      return this.adjunto;
   }

   public String getAceptacionTrDatos() {
      return this.aceptacionTrDatos;
   }

   public String getCorreoForm() {
      return this.correoForm;
   }

   public String getDireccion() {
      return this.direccion;
   }

   public String getRol() {
      return this.rol;
   }

   public String getRolFideicomiso() {
      return this.rolFideicomiso;
   }

   public String getRolFideicomisoOtro() {
      return this.rolFideicomisoOtro;
   }

   public String getCondiManejoRolcargaOtra() {
      return this.condiManejoRolcargaOtra;
   }

   public String getCondiManejo() {
      return this.condiManejo;
   }

   public String getConUsrAuthz() {
      return this.conUsrAuthz;
   }

   public String getToken() {
      return this.token;
   }

   public String getEstadoSolicitud() {
      return this.estadoSolicitud;
   }

   public String getDescripcionDelCaso() {
      return this.descripcionDelCaso;
   }

   public String getEstado() {
      return this.estado;
   }

   public String getUsuarioCrea() {
      return this.usuarioCrea;
   }

   public String getNumUsuarios() {
      return this.numUsuarios;
   }

   public void setRadicadoId(BigDecimal radicadoId) {
      this.radicadoId = radicadoId;
   }

   public void setTipoSolicitud(String tipoSolicitud) {
      this.tipoSolicitud = tipoSolicitud;
   }

   public void setIdRefNegocio(String idRefNegocio) {
      this.idRefNegocio = idRefNegocio;
   }

   public void setNombreRefNegocio(String nombreRefNegocio) {
      this.nombreRefNegocio = nombreRefNegocio;
   }

   public void setFechaHora(String fechaHora) {
      this.fechaHora = fechaHora;
   }

   public void setCorreoSolicita(String correoSolicita) {
      this.correoSolicita = correoSolicita;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

   public void setNombreSolicita(String nombreSolicita) {
      this.nombreSolicita = nombreSolicita;
   }

   public void setApellidoSolicita(String apellidoSolicita) {
      this.apellidoSolicita = apellidoSolicita;
   }

   public void setSegundoNombre(String segundoNombre) {
      this.segundoNombre = segundoNombre;
   }

   public void setSegundoApellido(String segundoApellido) {
      this.segundoApellido = segundoApellido;
   }

   public void setTelefonoSolicita(String telefonoSolicita) {
      this.telefonoSolicita = telefonoSolicita;
   }

   public void setTipoDocSolicita(String tipoDocSolicita) {
      this.tipoDocSolicita = tipoDocSolicita;
   }

   public void setIdentificacionSolicita(String identificacionSolicita) {
      this.identificacionSolicita = identificacionSolicita;
   }

   public void setCelularSolicita(String celularSolicita) {
      this.celularSolicita = celularSolicita;
   }

   public void setIdAdjunto(String idAdjunto) {
      this.idAdjunto = idAdjunto;
   }

   public void setAdjunto(String adjunto) {
      this.adjunto = adjunto;
   }

   public void setAceptacionTrDatos(String aceptacionTrDatos) {
      this.aceptacionTrDatos = aceptacionTrDatos;
   }

   public void setCorreoForm(String correoForm) {
      this.correoForm = correoForm;
   }

   public void setDireccion(String direccion) {
      this.direccion = direccion;
   }

   public void setRol(String rol) {
      this.rol = rol;
   }

   public void setRolFideicomiso(String rolFideicomiso) {
      this.rolFideicomiso = rolFideicomiso;
   }

   public void setRolFideicomisoOtro(String rolFideicomisoOtro) {
      this.rolFideicomisoOtro = rolFideicomisoOtro;
   }

   public void setCondiManejoRolcargaOtra(String condiManejoRolcargaOtra) {
      this.condiManejoRolcargaOtra = condiManejoRolcargaOtra;
   }

   public void setCondiManejo(String condiManejo) {
      this.condiManejo = condiManejo;
   }

   public void setConUsrAuthz(String conUsrAuthz) {
      this.conUsrAuthz = conUsrAuthz;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public void setEstadoSolicitud(String estadoSolicitud) {
      this.estadoSolicitud = estadoSolicitud;
   }

   public void setDescripcionDelCaso(String descripcionDelCaso) {
      this.descripcionDelCaso = descripcionDelCaso;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   public void setUsuarioCrea(String usuarioCrea) {
      this.usuarioCrea = usuarioCrea;
   }

   public void setNumUsuarios(String numUsuarios) {
      this.numUsuarios = numUsuarios;
   }
}
