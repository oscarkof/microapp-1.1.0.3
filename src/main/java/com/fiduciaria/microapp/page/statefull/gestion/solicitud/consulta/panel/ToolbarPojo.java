// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.gestion.solicitud.consulta.panel;

import java.util.List;
import org.apache.wicket.util.io.IClusterable;

public class ToolbarPojo implements IClusterable {
   private String totalRegistros;
   private String mostrandoDeHasta;
   private List<String> listaEstados;
   private List<String> listaTipoSolicitud;
   private List<String> listaColumnas;
   private String tipoSolicitudSeleccionda;
   private String estadoSeleccionado;
   private String columnaFiltroSeleccionada;
   private String filtroNombreCliente;

   public ToolbarPojo() {
   }

   public String getTotalRegistros() {
      return this.totalRegistros;
   }

   public String getMostrandoDeHasta() {
      return this.mostrandoDeHasta;
   }

   public List<String> getListaEstados() {
      return this.listaEstados;
   }

   public List<String> getListaTipoSolicitud() {
      return this.listaTipoSolicitud;
   }

   public List<String> getListaColumnas() {
      return this.listaColumnas;
   }

   public String getTipoSolicitudSeleccionda() {
      return this.tipoSolicitudSeleccionda;
   }

   public String getEstadoSeleccionado() {
      return this.estadoSeleccionado;
   }

   public String getColumnaFiltroSeleccionada() {
      return this.columnaFiltroSeleccionada;
   }

   public String getFiltroNombreCliente() {
      return this.filtroNombreCliente;
   }

   public void setTotalRegistros(String totalRegistros) {
      this.totalRegistros = totalRegistros;
   }

   public void setMostrandoDeHasta(String mostrandoDeHasta) {
      this.mostrandoDeHasta = mostrandoDeHasta;
   }

   public void setListaEstados(List<String> listaEstados) {
      this.listaEstados = listaEstados;
   }

   public void setListaTipoSolicitud(List<String> listaTipoSolicitud) {
      this.listaTipoSolicitud = listaTipoSolicitud;
   }

   public void setListaColumnas(List<String> listaColumnas) {
      this.listaColumnas = listaColumnas;
   }

   public void setTipoSolicitudSeleccionda(String tipoSolicitudSeleccionda) {
      this.tipoSolicitudSeleccionda = tipoSolicitudSeleccionda;
   }

   public void setEstadoSeleccionado(String estadoSeleccionado) {
      this.estadoSeleccionado = estadoSeleccionado;
   }

   public void setColumnaFiltroSeleccionada(String columnaFiltroSeleccionada) {
      this.columnaFiltroSeleccionada = columnaFiltroSeleccionada;
   }

   public void setFiltroNombreCliente(String filtroNombreCliente) {
      this.filtroNombreCliente = filtroNombreCliente;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof ToolbarPojo)) {
         return false;
      } else {
         ToolbarPojo other = (ToolbarPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label119: {
               Object this$totalRegistros = this.getTotalRegistros();
               Object other$totalRegistros = other.getTotalRegistros();
               if (this$totalRegistros == null) {
                  if (other$totalRegistros == null) {
                     break label119;
                  }
               } else if (this$totalRegistros.equals(other$totalRegistros)) {
                  break label119;
               }

               return false;
            }

            Object this$mostrandoDeHasta = this.getMostrandoDeHasta();
            Object other$mostrandoDeHasta = other.getMostrandoDeHasta();
            if (this$mostrandoDeHasta == null) {
               if (other$mostrandoDeHasta != null) {
                  return false;
               }
            } else if (!this$mostrandoDeHasta.equals(other$mostrandoDeHasta)) {
               return false;
            }

            label105: {
               Object this$listaEstados = this.getListaEstados();
               Object other$listaEstados = other.getListaEstados();
               if (this$listaEstados == null) {
                  if (other$listaEstados == null) {
                     break label105;
                  }
               } else if (this$listaEstados.equals(other$listaEstados)) {
                  break label105;
               }

               return false;
            }

            Object this$listaTipoSolicitud = this.getListaTipoSolicitud();
            Object other$listaTipoSolicitud = other.getListaTipoSolicitud();
            if (this$listaTipoSolicitud == null) {
               if (other$listaTipoSolicitud != null) {
                  return false;
               }
            } else if (!this$listaTipoSolicitud.equals(other$listaTipoSolicitud)) {
               return false;
            }

            label91: {
               Object this$listaColumnas = this.getListaColumnas();
               Object other$listaColumnas = other.getListaColumnas();
               if (this$listaColumnas == null) {
                  if (other$listaColumnas == null) {
                     break label91;
                  }
               } else if (this$listaColumnas.equals(other$listaColumnas)) {
                  break label91;
               }

               return false;
            }

            Object this$tipoSolicitudSeleccionda = this.getTipoSolicitudSeleccionda();
            Object other$tipoSolicitudSeleccionda = other.getTipoSolicitudSeleccionda();
            if (this$tipoSolicitudSeleccionda == null) {
               if (other$tipoSolicitudSeleccionda != null) {
                  return false;
               }
            } else if (!this$tipoSolicitudSeleccionda.equals(other$tipoSolicitudSeleccionda)) {
               return false;
            }

            label77: {
               Object this$estadoSeleccionado = this.getEstadoSeleccionado();
               Object other$estadoSeleccionado = other.getEstadoSeleccionado();
               if (this$estadoSeleccionado == null) {
                  if (other$estadoSeleccionado == null) {
                     break label77;
                  }
               } else if (this$estadoSeleccionado.equals(other$estadoSeleccionado)) {
                  break label77;
               }

               return false;
            }

            label70: {
               Object this$columnaFiltroSeleccionada = this.getColumnaFiltroSeleccionada();
               Object other$columnaFiltroSeleccionada = other.getColumnaFiltroSeleccionada();
               if (this$columnaFiltroSeleccionada == null) {
                  if (other$columnaFiltroSeleccionada == null) {
                     break label70;
                  }
               } else if (this$columnaFiltroSeleccionada.equals(other$columnaFiltroSeleccionada)) {
                  break label70;
               }

               return false;
            }

            Object this$filtroNombreCliente = this.getFiltroNombreCliente();
            Object other$filtroNombreCliente = other.getFiltroNombreCliente();
            if (this$filtroNombreCliente == null) {
               if (other$filtroNombreCliente != null) {
                  return false;
               }
            } else if (!this$filtroNombreCliente.equals(other$filtroNombreCliente)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof ToolbarPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $totalRegistros = this.getTotalRegistros();
      result = result * 59 + ($totalRegistros == null ? 43 : $totalRegistros.hashCode());
      Object $mostrandoDeHasta = this.getMostrandoDeHasta();
      result = result * 59 + ($mostrandoDeHasta == null ? 43 : $mostrandoDeHasta.hashCode());
      Object $listaEstados = this.getListaEstados();
      result = result * 59 + ($listaEstados == null ? 43 : $listaEstados.hashCode());
      Object $listaTipoSolicitud = this.getListaTipoSolicitud();
      result = result * 59 + ($listaTipoSolicitud == null ? 43 : $listaTipoSolicitud.hashCode());
      Object $listaColumnas = this.getListaColumnas();
      result = result * 59 + ($listaColumnas == null ? 43 : $listaColumnas.hashCode());
      Object $tipoSolicitudSeleccionda = this.getTipoSolicitudSeleccionda();
      result = result * 59 + ($tipoSolicitudSeleccionda == null ? 43 : $tipoSolicitudSeleccionda.hashCode());
      Object $estadoSeleccionado = this.getEstadoSeleccionado();
      result = result * 59 + ($estadoSeleccionado == null ? 43 : $estadoSeleccionado.hashCode());
      Object $columnaFiltroSeleccionada = this.getColumnaFiltroSeleccionada();
      result = result * 59 + ($columnaFiltroSeleccionada == null ? 43 : $columnaFiltroSeleccionada.hashCode());
      Object $filtroNombreCliente = this.getFiltroNombreCliente();
      result = result * 59 + ($filtroNombreCliente == null ? 43 : $filtroNombreCliente.hashCode());
      return result;
   }

   public String toString() {
      return "ToolbarPojo(totalRegistros=" + this.getTotalRegistros() + ", mostrandoDeHasta=" + this.getMostrandoDeHasta() + ", listaEstados=" + this.getListaEstados() + ", listaTipoSolicitud=" + this.getListaTipoSolicitud() + ", listaColumnas=" + this.getListaColumnas() + ", tipoSolicitudSeleccionda=" + this.getTipoSolicitudSeleccionda() + ", estadoSeleccionado=" + this.getEstadoSeleccionado() + ", columnaFiltroSeleccionada=" + this.getColumnaFiltroSeleccionada() + ", filtroNombreCliente=" + this.getFiltroNombreCliente() + ")";
   }
}
