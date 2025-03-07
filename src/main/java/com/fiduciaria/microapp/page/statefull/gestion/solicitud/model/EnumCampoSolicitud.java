// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud.model;

import java.io.Serializable;

public enum EnumCampoSolicitud implements Serializable {
   RADICADO_ID("# Rad.", "radicadoId"),
   TIPO_SOLICITUD("Tipo de Solicitud", "tipoSolicitud"),
   ID_REF_NEGOCIO("C\u00f3d. Negocio", "idRefNegocio"),
   FECHA_HORA(" Fecha ", "fechaHora"),
   CORREO_SOLICITA("Email", "correoSolicita"),
   DESCRIPCION("Descripci\u00f3n", "descripcion"),
   NOMBRE_SOLICITA("Primer Nombre", "nombreSolicita"),
   APELLIDO_SOLICITA("Primer Apellido", "apellidoSolicita"),
   TELEFONO_SOLICITA("Tel\u00e9fono", "telefonoSolicita"),
   NOMBRE_REF_NEGOCIO("Nombre Negocio", "nombreRefNegocio"),
   TIPO_DOC_SOLICITA("Tipo Doc.", "tipoDocSolicita"),
   IDENTIFICACION_SOLICITA("Identificaci\u00f3n", "identificacionSolicita"),
   CELULAR_SOLICITA("Celular", "celularSolicita"),
   ID_ADJUNTO("Id Adjunto", "idAdjunto"),
   ADJUNTO("Nombre Adjunto", "adjunto"),
   ACEPTACION_TR_DATOS("Acept\u00f3 TDP ?", "aceptacionTrDatos"),
   SEGUNDO_NOMBRE("Segundo Nombre", "segundoNombre"),
   SEGUNDO_APELLIDO("Segundo Apellido", "segundoApellido"),
   CORREO_FORM("Correo dirigido a", "correoForm"),
   DIRECCION("Direcci\u00f3n", "direccion"),
   ROL("Rol", "rol"),
   ROL_FIDEICOMISO("Rol Fideicomiso", "rolFideicomiso"),
   ROL_FIDEICOMISO_OTRO("Rol Fid. Otro", "rolFideicomisoOtro"),
   CONDI_MANEJO_ROLCARGA_OTRA("Condiciones Manejo otro", "condiManejoRolcargaOtra"),
   CONDI_MANEJO("Condiciones Manejo", "condiManejo"),
   CON_USR_AUTHZ("Condiciones Usuario Autoriza", "conUsrAuthz"),
   TOKEN("Token", "token"),
   ESTADO("Estado", "estado"),
   NUM_USUARIOS("N\u00famero usuarios", "numUsuarios"),
   USUARIO_CREA("Usuario", "usuarioCrea");

   private final String titleTitle;
   private final String fieldName;

   private EnumCampoSolicitud(String fieldTitle, String fielName) {
      this.titleTitle = fieldTitle;
      this.fieldName = fielName;
   }

   public String getFieldTitle() {
      return this.titleTitle;
   }

   public String getFieldName() {
      return this.fieldName;
   }
}
