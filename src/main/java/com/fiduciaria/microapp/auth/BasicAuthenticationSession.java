// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.auth;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import com.fiduciaria.microapp.base.cons.AppServiceUrlConstants;
import com.fiduciaria.microapp.beans.GenericHttpClient;
import com.fiduciaria.microapp.jwt.JwtUtil;
import com.fiduciaria.microapp.store.config.SecParam;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.apache.wicket.util.string.Strings;
import org.jose4j.lang.JoseException;

public class BasicAuthenticationSession extends AuthenticatedWebSession {
   private String role;

   public BasicAuthenticationSession(Request request) {
      super(request);
   }

   protected boolean authenticate(String username, String token) {
      return this.validarCredenciales(username, token);
   }

   public Roles getRoles() {
      Roles resultRoles = new Roles();
      if (this.isSignedIn()) {
         resultRoles.add("SIGNED_IN");
      }

      if (this.role != null) {
         String[] roles = this.role.split(";");
         String[] var3 = roles;
         int var4 = roles.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String role1 = var3[var5];
            if (role1 != null && !role1.equals("")) {
               resultRoles.add(role1.toUpperCase());
            }
         }
      }

      return resultRoles;
   }

   private boolean validarCredenciales(String uuid, String token) {
      boolean response = false;
      String jwt = new String(Base64.decodeBase64(token));
      String[] tockenConf = jwt.split("&&");
      if (tockenConf.length > 1) {
         GenericHttpClient gtwayHttp = new GenericHttpClient();
         String srvurl = AppServiceUrlConstants.CONF_SRV.getDesc().replace("${uuid}", Base64.encodeBase64URLSafeString(uuid.getBytes()));
         Map<String, String> responseHeaders = new HashMap();
         String configuraciones = gtwayHttp.getRESTGralService(AppConfParamConstant.getUrltorestserver(), "services/payment/rest" + srvurl, responseHeaders);
         ObjectMapper mapper = new ObjectMapper();
         mapper.setDefaultPropertyInclusion(Include.NON_NULL);

         try {
            AuthenticatedWebSession.get().setAttribute("UUID", uuid);
            AuthenticatedWebSession.get().setAttribute("conftoken", (Serializable)responseHeaders.get("conftoken"));
            AppConfParamConstant.setConftoken((String)responseHeaders.get("conftoken"));
            SecParam[] parametros = (SecParam[])mapper.readValue(configuraciones, SecParam[].class);
            SecParam[] var12 = parametros;
            int var13 = parametros.length;

            for(int var14 = 0; var14 < var13; ++var14) {
               SecParam param = var12[var14];
               if (param.getNombrevariable().equalsIgnoreCase("username")) {
                  String[] values = param.getValorvariable().split(";");
                  AuthenticatedWebSession.get().setAttribute(param.getNombrevariable(), values[0]);
                  AuthenticatedWebSession.get().setAttribute("usertype", values[1]);
               } else {
                  AuthenticatedWebSession.get().setAttribute(param.getNombrevariable(), param.getValorvariable());
               }
            }
         } catch (JsonProcessingException var18) {
            Logger.getLogger(BasicAuthenticationSession.class.getName()).log(Level.SEVERE, (String)null, var18);
         }

         String jwtToken = tockenConf[1];
         String sesskey = Objects.nonNull(AuthenticatedWebSession.get().getAttribute("jwtkey")) ? AuthenticatedWebSession.get().getAttribute("jwtkey").toString() : "";
         if (!Strings.isEmpty(sesskey)) {
            try {
               byte[] aeskey = Base64.decodeBase64(sesskey);
               JwtUtil jwtobj = new JwtUtil();
               String payload = jwtobj.simpleJWEDecript(aeskey, jwtToken);
               HashMap<String, String> comConf = (HashMap)mapper.readValue(payload, HashMap.class);
               AppConfParamConstant.setUritotrustkeystore((String)comConf.get("uritotrustkeystore"));
               AppConfParamConstant.setTrustkeystorepass((String)comConf.get("trustkeystorepass"));
               AppConfParamConstant.setTrustkeystoretype((String)comConf.get("trustkeystoretype"));
               AuthenticatedWebSession.get().setAttribute("fidutoken", (Serializable)comConf.get("fidutoken"));
               this.role = (String)comConf.get("rolesusuario");
               if (Objects.nonNull(AuthenticatedWebSession.get().getAttribute("username")) && ((String)comConf.get("username")).equalsIgnoreCase(AuthenticatedWebSession.get().getAttribute("username").toString())) {
                  response = true;
               } else {
                  response = false;
               }
            } catch (JsonProcessingException | JoseException var17) {
               response = false;
               Logger.getLogger(BasicAuthenticationSession.class.getName()).log(Level.SEVERE, (String)null, var17);
            }
         } else {
            response = false;
         }
      }

      return response;
   }

   public void signOut() {
      super.signOut();
   }

   public void onInvalidate() {
      super.onInvalidate();
   }
}
