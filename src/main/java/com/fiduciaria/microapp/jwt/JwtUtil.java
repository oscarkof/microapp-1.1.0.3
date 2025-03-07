// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.jwt;

import java.security.Key;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.JoseException;

public class JwtUtil {
   public JwtUtil() {
   }

   public static JwtUtil instance() {
      return new JwtUtil();
   }

   public byte[] crearKAleatoria() {
      return ByteUtil.randomBytes(16);
   }

   public String simpleJWEEncript(byte[] aeskey, String payload) throws JoseException {
      Key key = new AesKey(aeskey);
      JsonWebEncryption jwe = new JsonWebEncryption();
      jwe.setPayload(payload);
      jwe.setAlgorithmHeaderValue("A128KW");
      jwe.setEncryptionMethodHeaderParameter("A128CBC-HS256");
      jwe.setKey(key);
      String serializedJwe = jwe.getCompactSerialization();
      return serializedJwe;
   }

   public String simpleJWEDecript(byte[] aeskey, String serializedJwe) throws JoseException {
      Key key = new AesKey(aeskey);
      new JsonWebEncryption();
      JsonWebEncryption jwe = new JsonWebEncryption();
      jwe.setAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.PERMIT, new String[]{"A128KW"}));
      jwe.setContentEncryptionAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.PERMIT, new String[]{"A128CBC-HS256"}));
      jwe.setKey(key);
      jwe.setCompactSerialization(serializedJwe);
      return jwe.getPayload();
   }
}
