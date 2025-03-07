package com.fiduciaria.microapp.store.model.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.beans.IGenericHttpClient;
import com.fiduciaria.microapp.store.model.usuario.nextgen.CredencialNextGen;
import com.fiduciaria.microapp.store.model.usuario.nextgen.UsuarioExternoNextGen;
import com.fiduciaria.microapp.store.model.usuario.nextgen.UsuariosExternosGridNextGen;
import com.fiduciaria.microapp.store.service.EnumSeguridadServiceDefi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.string.Strings;

public class UsuarioPojoVM implements IClusterable {
  private final IGenericHttpClient gtwayHttp;
  
  private final UsuarioParam parametros;
  
  public UsuarioPojoVM(IGenericHttpClient gtwy) {
    this.gtwayHttp = gtwy;
    this.parametros = new UsuarioParam();
    this.parametros.setInicio(0L);
    this.parametros.setCuenta(0L);
  }
  
  public UsuarioParam getParametros() {
    return this.parametros;
  }
  
  public long totalRegistros() {
    return listarUsuariosRegistrados(this.parametros).getTotalRegistros();
  }
  
  public List<Map<UsuarioColumnEnum, String>> listadoUsuario(String filtro, String tipo) {
    List<Map<UsuarioColumnEnum, String>> response = new ArrayList<>();
    StringBuilder urlServFidei = new StringBuilder();
    urlServFidei.append(AppConfParamConstant.getBaseServiceDomain());
    urlServFidei.append(EnumSeguridadServiceDefi.GET_USUARIO_FILTRADOS.getUrl()
        .replace("${filtrousuario}", filtro)
        .replace("${tipo}", tipo));
    String rsuarios = this.gtwayHttp.getRESTGralService(
        AppConfParamConstant.getUrltorestserver(), urlServFidei
        .toString());
    ObjectMapper mapper = new ObjectMapper();
    mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    try {
      response = (List<Map<UsuarioColumnEnum, String>>)mapper.readValue(rsuarios, List.class);
    } catch (JsonProcessingException ex) {
      Logger.getLogger(UsuarioPojoVM.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
    } 
    return response;
  }
  
  public List<Map<UsuarioColumnEnum, String>> listadoUsuarioDominio(String filtro) {
    List<Map<UsuarioColumnEnum, String>> response = new ArrayList<>();
    StringBuilder urlServFidei = new StringBuilder();
    urlServFidei.append(AppConfParamConstant.getBaseServiceDomain());
    urlServFidei.append(EnumSeguridadServiceDefi.GET_USUARIO_DOMINIO_FILTRADOS.getUrl()
        .replace("${dominio}", filtro));
    String rsuarios = this.gtwayHttp.getRESTGralService(
        AppConfParamConstant.getUrltorestserver(), urlServFidei
        .toString());
    ObjectMapper mapper = new ObjectMapper();
    mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    try {
      response = (List<Map<UsuarioColumnEnum, String>>)mapper.readValue(rsuarios, List.class);
    } catch (JsonProcessingException ex) {
      Logger.getLogger(UsuarioPojoVM.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
    } 
    return response;
  }
  
  public List<UsuarioPojo> recuperarListaUsuarios() {
    return listarUsuariosRegistrados(this.parametros).listaUsuarios();
  }
  
  private UsuarioParam listarUsuariosRegistrados(UsuarioParam param) {
    UsuarioParam response = new UsuarioParam();
    StringBuilder urlServFidei = new StringBuilder();
    urlServFidei.append(AppConfParamConstant.getBaseServiceDomain());
    urlServFidei.append(EnumSeguridadServiceDefi.POST_USUARIO_FILTRADOS.getUrl());
    ObjectMapper mapper = new ObjectMapper();
    mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    try {
      String rsuarios = this.gtwayHttp.postRESTService(
          AppConfParamConstant.getUrltorestserver(), urlServFidei
          .toString(), mapper.writeValueAsString(param));
      response = (UsuarioParam)mapper.readValue(rsuarios, UsuarioParam.class);
    } catch (JsonProcessingException ex) {
      Logger.getLogger(UsuarioPojoVM.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
    } 
    return response;
  }
  
  public String crearUsuario(UsuarioPojo usuario, String usuarioSesion, String subject, String correoOrigen, boolean ucausuariocreador, String ucausuario) {
    String response = "";
    UsuariosExternosGridNextGen entity = new UsuariosExternosGridNextGen();
    entity.setClaveactual(usuario.getPassword());
    entity.setClavenueva(usuario.getNewPassword());
    entity.setCodigousuario(usuario.getIdPrincipal());
    entity.setConfirmarclave(usuario.getNewPassword());
    entity.setEmail(usuario.getCorreoElectronico());
    entity.setEstado(usuario.getEstado());
    entity.setEsusuariosalaventas(usuario.isUsuarioSalaVentas() ? "S" : "N");
    entity.setIdentificacion(usuario.getIdentificacion());
    entity.setIsselected(Boolean.valueOf(true));
    entity.setNombre(usuario.getPrimerNombre());
    entity.setPassWordValue(usuario.getPassword());
    entity.setPrimerapellido(usuario.getPrimerApellido());
    entity.setRolsalaventas((!Strings.isEmpty(usuario.getTipoUsuarioSalaventas()) && usuario
        .getTipoUsuarioSalaventas()
        .equalsIgnoreCase("Administrador")) ? "A" : "P");
    entity.setSegundonombre(usuario.getSegundoNombre());
    entity.setTelefono(usuario.getNumeroTelefono());
    entity.setTipoidentificacion(usuario.getTipoDocumento().getClasedocumento());
    entity.setFidutoken(usuario.getFidutoken());
    entity.setOrigen(correoOrigen);
    entity.setSubject(subject);
    RandomPassword ranpass = new RandomPassword();
    entity.setPassWordValue(ranpass.getSaltString());
    entity.setUcaUsuarioCreador(ucausuariocreador);
    entity.setUsuarioAdmondominio(usuarioSesion);
    StringBuilder urlServFidei = new StringBuilder();
    urlServFidei.append(AppConfParamConstant.getBaseServiceDomain());
    urlServFidei.append(EnumSeguridadServiceDefi.POST_USUARIO_CREAR.getUrl());
    ObjectMapper mapper = new ObjectMapper();
    mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    try {
      Map<String, String> requestheaders = new HashMap<>();
      requestheaders.put("uuid", usuario.getFidutoken());
      requestheaders.put("userid", usuarioSesion);
      response = this.gtwayHttp.postRESTgService(
          AppConfParamConstant.getUrltorestserver(), urlServFidei
          .toString(), mapper.writeValueAsString(entity), requestheaders);
    } catch (JsonProcessingException ex) {
      Logger.getLogger(UsuarioPojoVM.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
    } 
    return response;
  }
  
  public String enrolarOTP(UsuarioPojo usuario, String usuarioSesion, String subject, String correoOrigen) {
    UsuarioExternoNextGen usernextgen = new UsuarioExternoNextGen();
    usernextgen.setUser(usuario.getIdPrincipal());
    usernextgen.setFidutoken(usuario.getFidutoken());
    String response = postLlamado(EnumSeguridadServiceDefi.POST_USUARIO_ENROLAR_OTP, usernextgen, usuario
        .getFidutoken(), usuarioSesion);
    return response;
  }
  
  public String bloquearUsuario(UsuarioPojo usuario, String usuarioSesion, String subject, String correoOrigen) {
    UsuarioExternoNextGen usernextgen = new UsuarioExternoNextGen();
    usernextgen.setUser(usuario.getIdPrincipal());
    usernextgen.setFidutoken(usuario.getFidutoken());
    String response = postLlamado(EnumSeguridadServiceDefi.POST_USUARIO_BLOQUEAR, usernextgen, usuario
        .getFidutoken(), usuarioSesion);
    return response;
  }
  
  public String desbloquearUsuario(UsuarioPojo usuario, String usuarioSesion, String subject, String correoOrigen) {
    UsuarioExternoNextGen usernextgen = new UsuarioExternoNextGen();
    usernextgen.setUser(usuario.getIdPrincipal());
    usernextgen.setFidutoken(usuario.getFidutoken());
    String response = postLlamado(EnumSeguridadServiceDefi.POST_USUARIO_DESBLOQUEAR, usernextgen, usuario
        .getFidutoken(), usuarioSesion);
    return response;
  }
  
  public String restablecerPassword(UsuarioPojo usuario, String usuarioSesion, String subject, String correoOrigen) {
    UsuarioExternoNextGen usernextgen = new UsuarioExternoNextGen();
    usernextgen.setUser(usuario.getIdPrincipal());
    usernextgen.setPassword(new CredencialNextGen());
    usernextgen.getPassword().setValue(usuario.getPassword());
    usernextgen.setIsDefault(Boolean.FALSE);
    usernextgen.setFidutoken(usuario.getFidutoken());
    usernextgen.setEmail(usuario.getCorreoElectronico());
    usernextgen.setOrigen(correoOrigen);
    usernextgen.setSubject(subject);
    RandomPassword ranpass = new RandomPassword();
    usernextgen.getPassword().setValue(ranpass.getSaltString());
    String response = postLlamado(EnumSeguridadServiceDefi.POST_USUARIO_RESTABLECER_PASS, usernextgen, usuario
        .getFidutoken(), usuarioSesion);
    return response;
  }
  
  public String cambiarPassword(UsuarioPojo usuario, String usuarioSesion, String subject, String correoOrigen) {
    UsuarioExternoNextGen usernextgen = new UsuarioExternoNextGen();
    usernextgen.setUser(usuario.getIdPrincipal());
    usernextgen.setPassword(new CredencialNextGen());
    usernextgen.getPassword().setValue(usuario.getNewPassword());
    usernextgen.setOldPassword(new CredencialNextGen());
    usernextgen.getOldPassword().setValue(usuario.getPassword());
    usernextgen.setFidutoken(usuario.getFidutoken());
    String response = postLlamado(EnumSeguridadServiceDefi.POST_USUARIO_RESTABLECER_PASS, usernextgen, usuario
        .getFidutoken(), usuarioSesion);
    return response;
  }
  
  private String postLlamado(EnumSeguridadServiceDefi endpoint, UsuarioExternoNextGen entity, String fidutoken, String usuarioSesion) {
    String response = "";
    StringBuilder urlServFidei = new StringBuilder();
    urlServFidei.append(AppConfParamConstant.getBaseServiceDomain());
    urlServFidei.append(endpoint.getUrl());
    ObjectMapper mapper = new ObjectMapper();
    mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    try {
      Map<String, String> requestheaders = new HashMap<>();
      requestheaders.put("uuid", fidutoken);
      requestheaders.put("userid", usuarioSesion);
      response = this.gtwayHttp.postRESTgService(
          AppConfParamConstant.getUrltorestserver(), urlServFidei
          .toString(), mapper.writeValueAsString(entity), requestheaders);
    } catch (JsonProcessingException ex) {
      Logger.getLogger(UsuarioPojoVM.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
    } 
    return response;
  }
  
  public String validarSarlaf(String tipoDocumento, String numeroDocumento, String digitoVerificacion, String fidutoken) {
    String response;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    try {
      Map<String, String> headers = new HashMap<>();
      headers.put("fidutoken", fidutoken);
      String entityresponse = this.gtwayHttp.getRESTGralService(AppConfParamConstant.getUrltorestserver(), EnumSeguridadServiceDefi.GET_SARLAF
          .getUrl()
          .replace("${doctype}", tipoDocumento)
          .replace("${docid}", numeroDocumento)
          .replace("${dv}", digitoVerificacion), headers);
      HashMap<String, Object> svrResponse = (HashMap<String, Object>)mapper.readValue(entityresponse, HashMap.class);
      if (!Strings.isEmpty(Strings.toString(svrResponse.get("severity")))) {
        response = "severity";
      } else if ("N".equalsIgnoreCase(Strings.toString(svrResponse.get("bloqueo"))) || "N"
        .equalsIgnoreCase(Strings.toString(svrResponse.get("alerta"))) || "N"
        .equalsIgnoreCase(Strings.toString(svrResponse.get("exento")))) {
        response = "OK";
      } else {
        response = "SARLAF";
      } 
    } catch (IOException ex) {
      response = ex.getLocalizedMessage();
      Logger.getLogger(UsuarioPojoVM.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (Exception ex) {
      response = ex.getLocalizedMessage();
      Logger.getLogger(UsuarioPojoVM.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    return response;
  }
  
  private class RandomPassword {
    private RandomPassword() {}
    
    protected String getSaltString() {
      String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
      StringBuilder salt = new StringBuilder();
      Random rnd = new Random();
      Random r = new Random();
      int randomlength = r.ints(1L, 8, 16).findFirst().getAsInt();
      while (salt.length() < randomlength) {
        int index = (int)(rnd.nextFloat() * SALTCHARS.length());
        salt.append(SALTCHARS.charAt(index));
      } 
      String saltStr = salt.toString();
      return saltStr;
    }
  }
}
