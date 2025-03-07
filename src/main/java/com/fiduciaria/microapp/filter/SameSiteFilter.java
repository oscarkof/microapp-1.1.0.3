// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.apache.wicket.protocol.http.WicketFilter;

public class SameSiteFilter extends WicketFilter {
   public SameSiteFilter() {
   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      super.doFilter(request, response, chain);
   }

   private void addSameSiteAttribute(HttpServletResponse response) {
      Collection<String> headers = response.getHeaders("Set-Cookie");
      boolean firstHeader = true;
      Iterator var4 = headers.iterator();

      while(var4.hasNext()) {
         String header = (String)var4.next();
         if (firstHeader) {
            response.setHeader("Set-Cookie", String.format("%s; %s", header, "SameSite=Strict"));
            firstHeader = false;
         } else {
            response.addHeader("Set-Cookie", String.format("%s; %s", header, "SameSite=Strict"));
         }
      }

   }

   public void destroy() {
   }
}
