// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.base.cons;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContexts;

public class ApplicationGlobalUtil {
   public ApplicationGlobalUtil() {
   }

   public static synchronized SSLConnectionSocketFactory getSSLContext(String trustkeystoretype, String uritotrustkeystore, String trustkeystorepass) {
      SSLConnectionSocketFactory sf = null;

      try {
         KeyStore keyStore = KeyStore.getInstance(trustkeystoretype);
         keyStore.load(new FileInputStream(uritotrustkeystore), trustkeystorepass.toCharArray());
         SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(keyStore, new TrustAllStrategy()).loadKeyMaterial(keyStore, trustkeystorepass.toCharArray()).build();
         HostnameVerifier allowAllHosts = new NoopHostnameVerifier();
         sf = new SSLConnectionSocketFactory(sslcontext, allowAllHosts);
      } catch (NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException | IOException | CertificateException | KeyStoreException var7) {
         Logger.getLogger(ApplicationGlobalUtil.class.getName()).log(Level.SEVERE, (String)null, var7);
      }

      return sf;
   }
}
