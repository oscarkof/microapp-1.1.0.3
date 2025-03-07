// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.statefull.dane;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.wicket.util.io.IClusterable;
import org.apache.wicket.util.string.Strings;

public class DireccionDanePojo implements IClusterable {
   private TipoDireccionPojo selectTipo;
   private String direccionFinal;
   private TipoViaPojo tipoVia;
   private String numeroViaPrincipal;
   private LetrasPojo letraViaPrincipal;
   private SufijoPojo bisViaPrincipal;
   private LetrasPojo letraSufijoPrincipal;
   private CuadrantePojo cuadranteViaPrincipal;
   private String numeroViaGeneradora;
   private LetrasPojo letraViaGeneradora;
   private SufijoPojo bisViaGeneradora;
   private LetrasPojo letraSufijoGeneradora;
   private String placaComplemento;
   private CuadrantePojo cuadranteViaGeneradora;
   private ComplementoPojo complemento;
   private String numeroComplemento;
   private ComplementoPojo tiponoestandar;
   private String nombretiponoestandar;
   private boolean openModalDireccionDane;

   public String getDireccionFinal() {
      StringBuilder response = new StringBuilder();
      if (Objects.nonNull(this.selectTipo) && Objects.nonNull(this.selectTipo.getClass()) && this.selectTipo.getCodigo().equalsIgnoreCase("0")) {
         response.append(Objects.nonNull(this.tipoVia) && !Strings.isEmpty(this.tipoVia.getCodigo()) ? this.tipoVia.getCodigo() + " " : "");
         response.append(Objects.nonNull(this.numeroViaPrincipal) && !Strings.isEmpty(this.numeroViaPrincipal) ? this.numeroViaPrincipal : "");
         response.append(Objects.nonNull(this.letraViaPrincipal) && !Strings.isEmpty(this.letraViaPrincipal.getCodigo()) ? this.letraViaPrincipal.getCodigo() + " " : "");
         response.append(Objects.nonNull(this.bisViaPrincipal) && !Strings.isEmpty(this.bisViaPrincipal.getCodigo()) ? this.bisViaPrincipal.getCodigo() + " " : "");
         response.append(Objects.nonNull(this.letraSufijoPrincipal) && !Strings.isEmpty(this.letraSufijoPrincipal.getCodigo()) ? this.letraSufijoPrincipal.getCodigo() + " " : "");
         response.append(Objects.nonNull(this.cuadranteViaPrincipal) && !Strings.isEmpty(this.cuadranteViaPrincipal.getCodigo()) ? this.cuadranteViaPrincipal.getCodigo() + " " : "");
         response.append(Objects.nonNull(this.numeroViaGeneradora) && !Strings.isEmpty(this.numeroViaGeneradora) ? " " + this.numeroViaGeneradora : "");
         response.append(Objects.nonNull(this.letraViaGeneradora) && !Strings.isEmpty(this.letraViaGeneradora.getCodigo()) ? this.letraViaGeneradora.getCodigo() : "");
         response.append(Objects.nonNull(this.bisViaGeneradora) && !Strings.isEmpty(this.bisViaGeneradora.getCodigo()) ? " " + this.bisViaGeneradora.getCodigo() : "");
         response.append(Objects.nonNull(this.letraSufijoGeneradora) && !Strings.isEmpty(this.letraSufijoGeneradora.getCodigo()) ? " " + this.letraSufijoGeneradora.getCodigo() : "");
         response.append(Objects.nonNull(this.placaComplemento) && !Strings.isEmpty(this.placaComplemento) ? " " + this.placaComplemento : "");
         response.append(Objects.nonNull(this.cuadranteViaGeneradora) && !Strings.isEmpty(this.cuadranteViaGeneradora.getCodigo()) ? " " + this.cuadranteViaGeneradora.getCodigo() : "");
      } else if (Objects.nonNull(this.selectTipo) && Objects.nonNull(this.selectTipo.getClass()) && this.selectTipo.getCodigo().equalsIgnoreCase("1")) {
         response.append(Objects.nonNull(this.tiponoestandar) && !Strings.isEmpty(this.tiponoestandar.getCodigo()) ? " " + this.tiponoestandar.getCodigo() : "");
         response.append(Objects.nonNull(this.nombretiponoestandar) && !Strings.isEmpty(this.nombretiponoestandar) ? " " + this.nombretiponoestandar : "");
      }

      response.append(Objects.nonNull(this.complemento) && !Strings.isEmpty(this.complemento.getCodigo()) ? " " + this.complemento.getCodigo() : "");
      response.append(Objects.nonNull(this.numeroComplemento) && !Strings.isEmpty(this.numeroComplemento) ? " " + this.numeroComplemento : "");
      this.direccionFinal = response.toString();
      return this.direccionFinal.length() > 100 ? this.direccionFinal.substring(0, 100) : this.direccionFinal;
   }

   @JsonIgnore
   public static List<TipoViaPojo> getTipoVias() {
      List<TipoViaPojo> response = new ArrayList();
      response.add(new TipoViaPojo("", "Tipo Via"));
      response.add(new TipoViaPojo("CL", "Calle"));
      response.add(new TipoViaPojo("KR", "Carrera"));
      response.add(new TipoViaPojo("DG", "Diagonal"));
      response.add(new TipoViaPojo("AU", "Autopista"));
      response.add(new TipoViaPojo("AV", "Avenida"));
      response.add(new TipoViaPojo("AC", "Av. Calle"));
      response.add(new TipoViaPojo("AK", "Av. Carrera"));
      response.add(new TipoViaPojo("CM", "Camino"));
      response.add(new TipoViaPojo("CT", "Carretera"));
      response.add(new TipoViaPojo("CI", "Circular"));
      response.add(new TipoViaPojo("CV", "Circunvalar"));
      response.add(new TipoViaPojo("PRJ", "Paraje"));
      response.add(new TipoViaPojo("PAS", "Paseo"));
      response.add(new TipoViaPojo("TV", "Transversal"));
      response.add(new TipoViaPojo("TC", "Troncal"));
      response.add(new TipoViaPojo("VT", "Variante"));
      return response;
   }

   @JsonIgnore
   public static List<CuadrantePojo> getCuadrantes() {
      List<CuadrantePojo> response = new ArrayList();
      response.add(new CuadrantePojo("", "Ninguno"));
      response.add(new CuadrantePojo("E", "ESTE"));
      response.add(new CuadrantePojo("S", "SUR"));
      response.add(new CuadrantePojo("N", "NORTE"));
      response.add(new CuadrantePojo("O", "OESTE"));
      return response;
   }

   @JsonIgnore
   public static List<LetrasPojo> getLetras() {
      List<LetrasPojo> response = new ArrayList();
      response.add(new LetrasPojo("", "Sin Letra"));
      response.add(new LetrasPojo("A", "A"));
      response.add(new LetrasPojo("B", "B"));
      response.add(new LetrasPojo("C", "C"));
      response.add(new LetrasPojo("D", "D"));
      response.add(new LetrasPojo("F", "F"));
      response.add(new LetrasPojo("G", "G"));
      response.add(new LetrasPojo("H", "H"));
      response.add(new LetrasPojo("I", "I"));
      response.add(new LetrasPojo("J", "J"));
      response.add(new LetrasPojo("K", "K"));
      response.add(new LetrasPojo("L", "L"));
      response.add(new LetrasPojo("M", "M"));
      response.add(new LetrasPojo("N", "N"));
      response.add(new LetrasPojo("O", "O"));
      response.add(new LetrasPojo("P", "P"));
      response.add(new LetrasPojo("Q", "Q"));
      response.add(new LetrasPojo("R", "R"));
      response.add(new LetrasPojo("T", "T"));
      response.add(new LetrasPojo("U", "U"));
      response.add(new LetrasPojo("V", "V"));
      response.add(new LetrasPojo("W", "W"));
      response.add(new LetrasPojo("X", "X"));
      response.add(new LetrasPojo("Y", "Y"));
      response.add(new LetrasPojo("Z", "Z"));
      return response;
   }

   @JsonIgnore
   public static List<SufijoPojo> getSufijos() {
      List<SufijoPojo> response = new ArrayList();
      response.add(new SufijoPojo("", "No sufijo"));
      response.add(new SufijoPojo("BIS", "Bis"));
      return response;
   }

   @JsonIgnore
   public static List<ComplementoPojo> getComplementos() {
      List<ComplementoPojo> response = new ArrayList();
      response.add(new ComplementoPojo("", "Complemento"));
      response.add(new ComplementoPojo("AD", "Administraci\u00f3n"));
      response.add(new ComplementoPojo("AGN", "Agencia"));
      response.add(new ComplementoPojo("AG", "Agrupaci\u00f3n"));
      response.add(new ComplementoPojo("ALM", "Almac\u00e9n"));
      response.add(new ComplementoPojo("AL", "Altillo"));
      response.add(new ComplementoPojo("APD", "Apartado"));
      response.add(new ComplementoPojo("AP", "Apartamento"));
      response.add(new ComplementoPojo("AST", "Asentamiento"));
      response.add(new ComplementoPojo("BRR", "Barrio"));
      response.add(new ComplementoPojo("BL", "Bloque"));
      response.add(new ComplementoPojo("BD", "Bodega"));
      response.add(new ComplementoPojo("CS", "Casa"));
      response.add(new ComplementoPojo("CAS", "Caserio"));
      response.add(new ComplementoPojo("CE", "C\u00e9lula"));
      response.add(new ComplementoPojo("CC", "Centro Comercial"));
      response.add(new ComplementoPojo("CD", "Ciudadela"));
      response.add(new ComplementoPojo("CDM", "Condominio"));
      response.add(new ComplementoPojo("CJ", "Conjunto"));
      response.add(new ComplementoPojo("CJR", "Conjunto Residencial"));
      response.add(new ComplementoPojo("CN", "Consultorio"));
      response.add(new ComplementoPojo("CRG", "Corregimiento"));
      response.add(new ComplementoPojo("CTO", "Cuarto"));
      response.add(new ComplementoPojo("DPTO", "Departamento"));
      response.add(new ComplementoPojo("DP", "Deposito"));
      response.add(new ComplementoPojo("DS", "Deposito S\u00f3tano"));
      response.add(new ComplementoPojo("ED", "Edificio "));
      response.add(new ComplementoPojo("EN", "Entrada"));
      response.add(new ComplementoPojo("ESQ", "Esquina"));
      response.add(new ComplementoPojo("ES", "Estaci\u00f3n"));
      response.add(new ComplementoPojo("ET", "Etapa"));
      response.add(new ComplementoPojo("EX", "Exterior"));
      response.add(new ComplementoPojo("FRR", "Ferrocarril"));
      response.add(new ComplementoPojo("FI", "Finca"));
      response.add(new ComplementoPojo("GJ", "Garaje"));
      response.add(new ComplementoPojo("GS", "Garaje S\u00f3tano"));
      response.add(new ComplementoPojo("HB", "Habitaci\u00f3n"));
      response.add(new ComplementoPojo("HC", "Hacienda"));
      response.add(new ComplementoPojo("IN", "Interior"));
      response.add(new ComplementoPojo("KM", "Kil\u00f3metro"));
      response.add(new ComplementoPojo("LC", "Local"));
      response.add(new ComplementoPojo("LM", "Local Mezzanine"));
      response.add(new ComplementoPojo("LT", "Lote"));
      response.add(new ComplementoPojo("MZ", "Manzana"));
      response.add(new ComplementoPojo("MN", "Mezzanine"));
      response.add(new ComplementoPojo("MD", "M\u00f3dulo"));
      response.add(new ComplementoPojo("MPIO", "Municipio"));
      response.add(new ComplementoPojo("NV", "Nivel"));
      response.add(new ComplementoPojo("OF", "Oficina"));
      response.add(new ComplementoPojo("PA", "Parcela"));
      response.add(new ComplementoPojo("PAR", "Parque"));
      response.add(new ComplementoPojo("PQ", "Parqueadero"));
      response.add(new ComplementoPojo("PTN", "Peatonal"));
      response.add(new ComplementoPojo("PH", "Penthouse"));
      response.add(new ComplementoPojo("PS", "Piso"));
      response.add(new ComplementoPojo("PN", "Plan"));
      response.add(new ComplementoPojo("PL", "Planta"));
      response.add(new ComplementoPojo("PZ", "Plaza"));
      response.add(new ComplementoPojo("PO", "Porter\u00eda"));
      response.add(new ComplementoPojo("PD", "Predio"));
      response.add(new ComplementoPojo("PT", "Puente"));
      response.add(new ComplementoPojo("PU", "Puesto"));
      response.add(new ComplementoPojo("QT", "Quinta"));
      response.add(new ComplementoPojo("RS", "Residencia"));
      response.add(new ComplementoPojo("RP", "Round Point"));
      response.add(new ComplementoPojo("SA", "Sal\u00f3n"));
      response.add(new ComplementoPojo("SC", "Sal\u00f3n Comunal"));
      response.add(new ComplementoPojo("SCT", "Sector"));
      response.add(new ComplementoPojo("SS", "Semis\u00f3tano"));
      response.add(new ComplementoPojo("SL", "Solar"));
      response.add(new ComplementoPojo("SO", "S\u00f3tano"));
      response.add(new ComplementoPojo("SU", "Suite"));
      response.add(new ComplementoPojo("SM", "Supermanzana"));
      response.add(new ComplementoPojo("TM", "Terminal"));
      response.add(new ComplementoPojo("TZ", "Terraza"));
      response.add(new ComplementoPojo("TO", "Torre"));
      response.add(new ComplementoPojo("UN", "Unidad"));
      response.add(new ComplementoPojo("UR", "Unidad Residencial"));
      response.add(new ComplementoPojo("UB", "Urbanizaci\u00f3n"));
      response.add(new ComplementoPojo("VD", "Vereda"));
      response.add(new ComplementoPojo("VIA", "V\u00eda"));
      response.add(new ComplementoPojo("VDU", "V\u00edaducto"));
      response.add(new ComplementoPojo("ZN", "Zona"));
      response.add(new ComplementoPojo("ZF", "Zona Franca"));
      return response;
   }

   @JsonIgnore
   public static List<TipoDireccionPojo> getTiposDirecciones() {
      List<TipoDireccionPojo> response = new ArrayList();
      response.add(new TipoDireccionPojo("0", "Direcci\u00f3n est\u00e1ndar"));
      response.add(new TipoDireccionPojo("1", "Direcci\u00f3n no est\u00e1ndar"));
      return response;
   }

   public DireccionDanePojo() {
   }

   public TipoDireccionPojo getSelectTipo() {
      return this.selectTipo;
   }

   public TipoViaPojo getTipoVia() {
      return this.tipoVia;
   }

   public String getNumeroViaPrincipal() {
      return this.numeroViaPrincipal;
   }

   public LetrasPojo getLetraViaPrincipal() {
      return this.letraViaPrincipal;
   }

   public SufijoPojo getBisViaPrincipal() {
      return this.bisViaPrincipal;
   }

   public LetrasPojo getLetraSufijoPrincipal() {
      return this.letraSufijoPrincipal;
   }

   public CuadrantePojo getCuadranteViaPrincipal() {
      return this.cuadranteViaPrincipal;
   }

   public String getNumeroViaGeneradora() {
      return this.numeroViaGeneradora;
   }

   public LetrasPojo getLetraViaGeneradora() {
      return this.letraViaGeneradora;
   }

   public SufijoPojo getBisViaGeneradora() {
      return this.bisViaGeneradora;
   }

   public LetrasPojo getLetraSufijoGeneradora() {
      return this.letraSufijoGeneradora;
   }

   public String getPlacaComplemento() {
      return this.placaComplemento;
   }

   public CuadrantePojo getCuadranteViaGeneradora() {
      return this.cuadranteViaGeneradora;
   }

   public ComplementoPojo getComplemento() {
      return this.complemento;
   }

   public String getNumeroComplemento() {
      return this.numeroComplemento;
   }

   public ComplementoPojo getTiponoestandar() {
      return this.tiponoestandar;
   }

   public String getNombretiponoestandar() {
      return this.nombretiponoestandar;
   }

   public boolean isOpenModalDireccionDane() {
      return this.openModalDireccionDane;
   }

   public void setSelectTipo(TipoDireccionPojo selectTipo) {
      this.selectTipo = selectTipo;
   }

   public void setDireccionFinal(String direccionFinal) {
      this.direccionFinal = direccionFinal;
   }

   public void setTipoVia(TipoViaPojo tipoVia) {
      this.tipoVia = tipoVia;
   }

   public void setNumeroViaPrincipal(String numeroViaPrincipal) {
      this.numeroViaPrincipal = numeroViaPrincipal;
   }

   public void setLetraViaPrincipal(LetrasPojo letraViaPrincipal) {
      this.letraViaPrincipal = letraViaPrincipal;
   }

   public void setBisViaPrincipal(SufijoPojo bisViaPrincipal) {
      this.bisViaPrincipal = bisViaPrincipal;
   }

   public void setLetraSufijoPrincipal(LetrasPojo letraSufijoPrincipal) {
      this.letraSufijoPrincipal = letraSufijoPrincipal;
   }

   public void setCuadranteViaPrincipal(CuadrantePojo cuadranteViaPrincipal) {
      this.cuadranteViaPrincipal = cuadranteViaPrincipal;
   }

   public void setNumeroViaGeneradora(String numeroViaGeneradora) {
      this.numeroViaGeneradora = numeroViaGeneradora;
   }

   public void setLetraViaGeneradora(LetrasPojo letraViaGeneradora) {
      this.letraViaGeneradora = letraViaGeneradora;
   }

   public void setBisViaGeneradora(SufijoPojo bisViaGeneradora) {
      this.bisViaGeneradora = bisViaGeneradora;
   }

   public void setLetraSufijoGeneradora(LetrasPojo letraSufijoGeneradora) {
      this.letraSufijoGeneradora = letraSufijoGeneradora;
   }

   public void setPlacaComplemento(String placaComplemento) {
      this.placaComplemento = placaComplemento;
   }

   public void setCuadranteViaGeneradora(CuadrantePojo cuadranteViaGeneradora) {
      this.cuadranteViaGeneradora = cuadranteViaGeneradora;
   }

   public void setComplemento(ComplementoPojo complemento) {
      this.complemento = complemento;
   }

   public void setNumeroComplemento(String numeroComplemento) {
      this.numeroComplemento = numeroComplemento;
   }

   public void setTiponoestandar(ComplementoPojo tiponoestandar) {
      this.tiponoestandar = tiponoestandar;
   }

   public void setNombretiponoestandar(String nombretiponoestandar) {
      this.nombretiponoestandar = nombretiponoestandar;
   }

   public void setOpenModalDireccionDane(boolean openModalDireccionDane) {
      this.openModalDireccionDane = openModalDireccionDane;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof DireccionDanePojo)) {
         return false;
      } else {
         DireccionDanePojo other = (DireccionDanePojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isOpenModalDireccionDane() != other.isOpenModalDireccionDane()) {
            return false;
         } else {
            Object this$selectTipo = this.getSelectTipo();
            Object other$selectTipo = other.getSelectTipo();
            if (this$selectTipo == null) {
               if (other$selectTipo != null) {
                  return false;
               }
            } else if (!this$selectTipo.equals(other$selectTipo)) {
               return false;
            }

            Object this$direccionFinal = this.getDireccionFinal();
            Object other$direccionFinal = other.getDireccionFinal();
            if (this$direccionFinal == null) {
               if (other$direccionFinal != null) {
                  return false;
               }
            } else if (!this$direccionFinal.equals(other$direccionFinal)) {
               return false;
            }

            label215: {
               Object this$tipoVia = this.getTipoVia();
               Object other$tipoVia = other.getTipoVia();
               if (this$tipoVia == null) {
                  if (other$tipoVia == null) {
                     break label215;
                  }
               } else if (this$tipoVia.equals(other$tipoVia)) {
                  break label215;
               }

               return false;
            }

            label208: {
               Object this$numeroViaPrincipal = this.getNumeroViaPrincipal();
               Object other$numeroViaPrincipal = other.getNumeroViaPrincipal();
               if (this$numeroViaPrincipal == null) {
                  if (other$numeroViaPrincipal == null) {
                     break label208;
                  }
               } else if (this$numeroViaPrincipal.equals(other$numeroViaPrincipal)) {
                  break label208;
               }

               return false;
            }

            Object this$letraViaPrincipal = this.getLetraViaPrincipal();
            Object other$letraViaPrincipal = other.getLetraViaPrincipal();
            if (this$letraViaPrincipal == null) {
               if (other$letraViaPrincipal != null) {
                  return false;
               }
            } else if (!this$letraViaPrincipal.equals(other$letraViaPrincipal)) {
               return false;
            }

            Object this$bisViaPrincipal = this.getBisViaPrincipal();
            Object other$bisViaPrincipal = other.getBisViaPrincipal();
            if (this$bisViaPrincipal == null) {
               if (other$bisViaPrincipal != null) {
                  return false;
               }
            } else if (!this$bisViaPrincipal.equals(other$bisViaPrincipal)) {
               return false;
            }

            label187: {
               Object this$letraSufijoPrincipal = this.getLetraSufijoPrincipal();
               Object other$letraSufijoPrincipal = other.getLetraSufijoPrincipal();
               if (this$letraSufijoPrincipal == null) {
                  if (other$letraSufijoPrincipal == null) {
                     break label187;
                  }
               } else if (this$letraSufijoPrincipal.equals(other$letraSufijoPrincipal)) {
                  break label187;
               }

               return false;
            }

            Object this$cuadranteViaPrincipal = this.getCuadranteViaPrincipal();
            Object other$cuadranteViaPrincipal = other.getCuadranteViaPrincipal();
            if (this$cuadranteViaPrincipal == null) {
               if (other$cuadranteViaPrincipal != null) {
                  return false;
               }
            } else if (!this$cuadranteViaPrincipal.equals(other$cuadranteViaPrincipal)) {
               return false;
            }

            Object this$numeroViaGeneradora = this.getNumeroViaGeneradora();
            Object other$numeroViaGeneradora = other.getNumeroViaGeneradora();
            if (this$numeroViaGeneradora == null) {
               if (other$numeroViaGeneradora != null) {
                  return false;
               }
            } else if (!this$numeroViaGeneradora.equals(other$numeroViaGeneradora)) {
               return false;
            }

            label166: {
               Object this$letraViaGeneradora = this.getLetraViaGeneradora();
               Object other$letraViaGeneradora = other.getLetraViaGeneradora();
               if (this$letraViaGeneradora == null) {
                  if (other$letraViaGeneradora == null) {
                     break label166;
                  }
               } else if (this$letraViaGeneradora.equals(other$letraViaGeneradora)) {
                  break label166;
               }

               return false;
            }

            label159: {
               Object this$bisViaGeneradora = this.getBisViaGeneradora();
               Object other$bisViaGeneradora = other.getBisViaGeneradora();
               if (this$bisViaGeneradora == null) {
                  if (other$bisViaGeneradora == null) {
                     break label159;
                  }
               } else if (this$bisViaGeneradora.equals(other$bisViaGeneradora)) {
                  break label159;
               }

               return false;
            }

            label152: {
               Object this$letraSufijoGeneradora = this.getLetraSufijoGeneradora();
               Object other$letraSufijoGeneradora = other.getLetraSufijoGeneradora();
               if (this$letraSufijoGeneradora == null) {
                  if (other$letraSufijoGeneradora == null) {
                     break label152;
                  }
               } else if (this$letraSufijoGeneradora.equals(other$letraSufijoGeneradora)) {
                  break label152;
               }

               return false;
            }

            Object this$placaComplemento = this.getPlacaComplemento();
            Object other$placaComplemento = other.getPlacaComplemento();
            if (this$placaComplemento == null) {
               if (other$placaComplemento != null) {
                  return false;
               }
            } else if (!this$placaComplemento.equals(other$placaComplemento)) {
               return false;
            }

            label138: {
               Object this$cuadranteViaGeneradora = this.getCuadranteViaGeneradora();
               Object other$cuadranteViaGeneradora = other.getCuadranteViaGeneradora();
               if (this$cuadranteViaGeneradora == null) {
                  if (other$cuadranteViaGeneradora == null) {
                     break label138;
                  }
               } else if (this$cuadranteViaGeneradora.equals(other$cuadranteViaGeneradora)) {
                  break label138;
               }

               return false;
            }

            Object this$complemento = this.getComplemento();
            Object other$complemento = other.getComplemento();
            if (this$complemento == null) {
               if (other$complemento != null) {
                  return false;
               }
            } else if (!this$complemento.equals(other$complemento)) {
               return false;
            }

            label124: {
               Object this$numeroComplemento = this.getNumeroComplemento();
               Object other$numeroComplemento = other.getNumeroComplemento();
               if (this$numeroComplemento == null) {
                  if (other$numeroComplemento == null) {
                     break label124;
                  }
               } else if (this$numeroComplemento.equals(other$numeroComplemento)) {
                  break label124;
               }

               return false;
            }

            Object this$tiponoestandar = this.getTiponoestandar();
            Object other$tiponoestandar = other.getTiponoestandar();
            if (this$tiponoestandar == null) {
               if (other$tiponoestandar != null) {
                  return false;
               }
            } else if (!this$tiponoestandar.equals(other$tiponoestandar)) {
               return false;
            }

            Object this$nombretiponoestandar = this.getNombretiponoestandar();
            Object other$nombretiponoestandar = other.getNombretiponoestandar();
            if (this$nombretiponoestandar == null) {
               if (other$nombretiponoestandar != null) {
                  return false;
               }
            } else if (!this$nombretiponoestandar.equals(other$nombretiponoestandar)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof DireccionDanePojo;
   }

   public int hashCode() {
      int PRIME = 0;
      int result = 1;
      result = result * 59 + (this.isOpenModalDireccionDane() ? 79 : 97);
      Object $selectTipo = this.getSelectTipo();
      result = result * 59 + ($selectTipo == null ? 43 : $selectTipo.hashCode());
      Object $direccionFinal = this.getDireccionFinal();
      result = result * 59 + ($direccionFinal == null ? 43 : $direccionFinal.hashCode());
      Object $tipoVia = this.getTipoVia();
      result = result * 59 + ($tipoVia == null ? 43 : $tipoVia.hashCode());
      Object $numeroViaPrincipal = this.getNumeroViaPrincipal();
      result = result * 59 + ($numeroViaPrincipal == null ? 43 : $numeroViaPrincipal.hashCode());
      Object $letraViaPrincipal = this.getLetraViaPrincipal();
      result = result * 59 + ($letraViaPrincipal == null ? 43 : $letraViaPrincipal.hashCode());
      Object $bisViaPrincipal = this.getBisViaPrincipal();
      result = result * 59 + ($bisViaPrincipal == null ? 43 : $bisViaPrincipal.hashCode());
      Object $letraSufijoPrincipal = this.getLetraSufijoPrincipal();
      result = result * 59 + ($letraSufijoPrincipal == null ? 43 : $letraSufijoPrincipal.hashCode());
      Object $cuadranteViaPrincipal = this.getCuadranteViaPrincipal();
      result = result * 59 + ($cuadranteViaPrincipal == null ? 43 : $cuadranteViaPrincipal.hashCode());
      Object $numeroViaGeneradora = this.getNumeroViaGeneradora();
      result = result * 59 + ($numeroViaGeneradora == null ? 43 : $numeroViaGeneradora.hashCode());
      Object $letraViaGeneradora = this.getLetraViaGeneradora();
      result = result * 59 + ($letraViaGeneradora == null ? 43 : $letraViaGeneradora.hashCode());
      Object $bisViaGeneradora = this.getBisViaGeneradora();
      result = result * 59 + ($bisViaGeneradora == null ? 43 : $bisViaGeneradora.hashCode());
      Object $letraSufijoGeneradora = this.getLetraSufijoGeneradora();
      result = result * 59 + ($letraSufijoGeneradora == null ? 43 : $letraSufijoGeneradora.hashCode());
      Object $placaComplemento = this.getPlacaComplemento();
      result = result * 59 + ($placaComplemento == null ? 43 : $placaComplemento.hashCode());
      Object $cuadranteViaGeneradora = this.getCuadranteViaGeneradora();
      result = result * 59 + ($cuadranteViaGeneradora == null ? 43 : $cuadranteViaGeneradora.hashCode());
      Object $complemento = this.getComplemento();
      result = result * 59 + ($complemento == null ? 43 : $complemento.hashCode());
      Object $numeroComplemento = this.getNumeroComplemento();
      result = result * 59 + ($numeroComplemento == null ? 43 : $numeroComplemento.hashCode());
      Object $tiponoestandar = this.getTiponoestandar();
      result = result * 59 + ($tiponoestandar == null ? 43 : $tiponoestandar.hashCode());
      Object $nombretiponoestandar = this.getNombretiponoestandar();
      result = result * 59 + ($nombretiponoestandar == null ? 43 : $nombretiponoestandar.hashCode());
      return result;
   }

   public String toString() {
      return "DireccionDanePojo(selectTipo=" + this.getSelectTipo() + ", direccionFinal=" + this.getDireccionFinal() + ", tipoVia=" + this.getTipoVia() + ", numeroViaPrincipal=" + this.getNumeroViaPrincipal() + ", letraViaPrincipal=" + this.getLetraViaPrincipal() + ", bisViaPrincipal=" + this.getBisViaPrincipal() + ", letraSufijoPrincipal=" + this.getLetraSufijoPrincipal() + ", cuadranteViaPrincipal=" + this.getCuadranteViaPrincipal() + ", numeroViaGeneradora=" + this.getNumeroViaGeneradora() + ", letraViaGeneradora=" + this.getLetraViaGeneradora() + ", bisViaGeneradora=" + this.getBisViaGeneradora() + ", letraSufijoGeneradora=" + this.getLetraSufijoGeneradora() + ", placaComplemento=" + this.getPlacaComplemento() + ", cuadranteViaGeneradora=" + this.getCuadranteViaGeneradora() + ", complemento=" + this.getComplemento() + ", numeroComplemento=" + this.getNumeroComplemento() + ", tiponoestandar=" + this.getTiponoestandar() + ", nombretiponoestandar=" + this.getNombretiponoestandar() + ", openModalDireccionDane=" + this.isOpenModalDireccionDane() + ")";
   }
}
