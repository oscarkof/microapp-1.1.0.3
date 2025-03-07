// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.usuario.componente;

import java.io.Serializable;
import java.util.Date;

public class HistorialRastroUsuarioFilter implements Serializable {
   private Date dateFrom;
   private Date dateTo;

   public HistorialRastroUsuarioFilter() {
   }

   public Date getDateFrom() {
      return this.dateFrom;
   }

   public void setDateFrom(Date dateFrom) {
      this.dateFrom = dateFrom;
   }

   public Date getDateTo() {
      return this.dateTo;
   }

   public void setDateTo(Date dateTo) {
      this.dateTo = dateTo;
   }
}
