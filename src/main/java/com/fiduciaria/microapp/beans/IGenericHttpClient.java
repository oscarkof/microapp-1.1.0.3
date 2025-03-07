// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.beans;

import java.util.Map;

public interface IGenericHttpClient {
   String getRESTService(String var1, String var2);

   String postRESTService(String var1, String var2, String var3);

   String getRESTGralService(String var1, String var2);

   String getRESTGralService(String var1, String var2, Map<String, String> var3);

   String postRESTgService(String var1, String var2, String var3);

   String postRESTgService(String var1, String var2, String var3, Map<String, String> var4);
}
