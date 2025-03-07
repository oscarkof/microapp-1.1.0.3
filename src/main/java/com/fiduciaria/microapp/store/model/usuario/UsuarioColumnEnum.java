// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.usuario;

import java.io.Serializable;

public enum UsuarioColumnEnum implements Serializable {
   LOGIN_USUARIO("idPrincipal", "Login Usuario"),
   CODIGO_GRUPO_USUARIO("codigoGrupoUsuario", "C\u00f3digo Grupo Usuario"),
   DESCRIPCION_USUARIO("nombrePrincipal", "Descripci\u00f3n "),
   ESTADO_USUARIO("estado", "Estado"),
   CORREO("correo", "Correo"),
   USUARIO("usuario", "Usuario"),
   TIPO("tipo", "Tipo"),
   TIPO_USUARIO("tipoUsuario", "tipoPrincipal"),
   PERMISO_WEB("permisoWeb", ""),
   FIRMA_URL("firmaUrl", ""),
   IDENTIFICACION("identificacion", "N\u00fam. Identificacion"),
   CLASE_DOCUMENTO("claseDocumento", "Tipo Documento"),
   ROL_FONDOS("rolFondoss", "Rol Fondos");

   private final String nombre;
   private final String titulo;

   private UsuarioColumnEnum(String nombre, String titulo) {
      this.nombre = nombre;
      this.titulo = titulo;
   }

   public String getNombre() {
      return this.nombre;
   }

   public String getTitulo() {
      return this.titulo;
   }
}
