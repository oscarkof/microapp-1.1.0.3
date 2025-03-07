// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.beans;

import com.fiduciaria.microapp.base.cons.AppConfParamConstant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

@ManagedBean
public class GenericHttpClient implements IGenericHttpClient {
   public GenericHttpClient() {
   }

   public String getRESTService(String urtoserver, String pathtoservice) {
      String result = "";
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
      String variablepart = timestamp.toString();
      HttpGet get = new HttpGet(urtoserver + pathtoservice);
      get.addHeader("Accept", "application/json");
      get.addHeader("conftoken", AppConfParamConstant.getConftoken());
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();

      try {
         CloseableHttpResponse response = httpClient.execute(get);
         result = EntityUtils.toString(response.getEntity());
      } catch (IOException var12) {
         Logger.getLogger(GenericHttpClient.class.getName()).log(Level.SEVERE, (String)null, var12);
      } finally {
         get.releaseConnection();
      }

      return result;
   }

   public String postRESTService(String urtoserver, String pathtoservice, String entitynin) {
      String result = "";
      HttpPost post = new HttpPost(urtoserver + pathtoservice);
      post.addHeader("Accept", "application/json");
      post.addHeader("conftoken", AppConfParamConstant.getConftoken());

      try {
         post.setEntity(new StringEntity(entitynin, ContentType.APPLICATION_JSON));
         CloseableHttpClient httpClient = HttpClientBuilder.create().build();
         CloseableHttpResponse response = httpClient.execute(post);
         result = EntityUtils.toString(response.getEntity());
      } catch (IOException var12) {
         Logger.getLogger(GenericHttpClient.class.getName()).log(Level.SEVERE, (String)null, var12);
      } finally {
         post.releaseConnection();
      }

      return result;
   }

   public static String read(InputStream input) throws IOException {
      BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
      Throwable var2 = null;

      String var3;
      try {
         var3 = (String)buffer.lines().collect(Collectors.joining("\n"));
      } catch (Throwable var12) {
         var2 = var12;
         throw var12;
      } finally {
         if (buffer != null) {
            if (var2 != null) {
               try {
                  buffer.close();
               } catch (Throwable var11) {
                  var2.addSuppressed(var11);
               }
            } else {
               buffer.close();
            }
         }

      }

      return var3;
   }

   public String getRESTGralService(String urtoserver, String pathtoservice) {
      String result = "";
      HttpGet get = new HttpGet(urtoserver + pathtoservice);
      get.addHeader("Accept", "application/json");
      get.addHeader("conftoken", AppConfParamConstant.getConftoken());
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();

      try {
         CloseableHttpResponse response = httpClient.execute(get);
         result = EntityUtils.toString(response.getEntity());
      } catch (IOException var11) {
         Logger.getLogger(GenericHttpClient.class.getName()).log(Level.SEVERE, (String)null, var11);
      } finally {
         get.releaseConnection();
      }

      return result;
   }

   public String getRESTGralService(String urtoserver, String pathtoservice, Map<String, String> headers) {
      String result = "";
      HttpGet get = new HttpGet(urtoserver + pathtoservice);
      get.addHeader("Accept", "application/json");
      get.addHeader("conftoken", AppConfParamConstant.getConftoken());
      Iterator var6 = headers.entrySet().iterator();

      while(var6.hasNext()) {
         Map.Entry<String, String> entry = (Map.Entry)var6.next();
         get.addHeader((String)entry.getKey(), (String)entry.getValue());
      }

      CloseableHttpClient httpClient = HttpClientBuilder.create().build();

      try {
         CloseableHttpResponse response = httpClient.execute(get);
         Header[] var9 = response.getAllHeaders();
         int var10 = var9.length;

         for(int var11 = 0; var11 < var10; ++var11) {
            Header header = var9[var11];
            headers.put(header.getName(), header.getValue());
         }

         result = EntityUtils.toString(response.getEntity());
      } catch (IOException var16) {
         Logger.getLogger(GenericHttpClient.class.getName()).log(Level.SEVERE, (String)null, var16);
      } finally {
         get.releaseConnection();
      }

      return result;
   }

   public String postRESTgService(String urtoserver, String pathtoservice, String entitynin, Map<String, String> requestheaders) {
      String result = "";
      HttpPost post = new HttpPost(urtoserver + pathtoservice);
      post.addHeader("conftoken", AppConfParamConstant.getConftoken());
      post.addHeader("Accept", "application/json");
      Iterator var7 = requestheaders.entrySet().iterator();

      while(var7.hasNext()) {
         Map.Entry<String, String> entry = (Map.Entry)var7.next();
         post.addHeader((String)entry.getKey(), (String)entry.getValue());
      }

      try {
         post.setEntity(new StringEntity(entitynin, ContentType.APPLICATION_JSON));
         CloseableHttpClient httpClient = HttpClientBuilder.create().build();
         CloseableHttpResponse response = httpClient.execute(post);
         result = EntityUtils.toString(response.getEntity());
      } catch (IOException var12) {
         Logger.getLogger(GenericHttpClient.class.getName()).log(Level.SEVERE, (String)null, var12);
      } finally {
         post.releaseConnection();
      }

      return result;
   }

   public String postRESTgService(String urtoserver, String pathtoservice, String entitynin) {
      String result = "";
      HttpPost post = new HttpPost(urtoserver + pathtoservice);
      post.addHeader("conftoken", AppConfParamConstant.getConftoken());
      post.addHeader("Accept", "application/json");

      try {
         post.setEntity(new StringEntity(entitynin, ContentType.APPLICATION_JSON));
         CloseableHttpClient httpClient = HttpClientBuilder.create().build();
         CloseableHttpResponse response = httpClient.execute(post);
         result = EntityUtils.toString(response.getEntity());
      } catch (IOException var11) {
         Logger.getLogger(GenericHttpClient.class.getName()).log(Level.SEVERE, (String)null, var11);
      } finally {
         post.releaseConnection();
      }

      return result;
   }
}
