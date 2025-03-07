// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.store.model.rastro;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class RastroAccionPojo implements IRastroAccion {
   private String accionUuid;
   public String id;
   public String accion;
   public String tipoAccion;
   public String idAccion;
   public String idRelacionado;
   public String tipoRelacionado;
   public String usuarioSess;
   public String estampaTiempo;
   public String anterior;
   public String actual;
   public String msg;
   public String secuencialAcc;

   public String getEstampaTiempo() {
      if (Objects.nonNull(this.estampaTiempo)) {
         LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(this.estampaTiempo)), ZoneId.systemDefault());
         return date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      } else {
         return this.estampaTiempo;
      }
   }

   public RastroAccionPojo() {
   }

   public String getAccionUuid() {
      return this.accionUuid;
   }

   public String getId() {
      return this.id;
   }

   public String getAccion() {
      return this.accion;
   }

   public String getTipoAccion() {
      return this.tipoAccion;
   }

   public String getIdAccion() {
      return this.idAccion;
   }

   public String getIdRelacionado() {
      return this.idRelacionado;
   }

   public String getTipoRelacionado() {
      return this.tipoRelacionado;
   }

   public String getUsuarioSess() {
      return this.usuarioSess;
   }

   public String getAnterior() {
      return this.anterior;
   }

   public String getActual() {
      return this.actual;
   }

   public String getMsg() {
      return this.msg;
   }

   public String getSecuencialAcc() {
      return this.secuencialAcc;
   }

   public void setAccionUuid(String accionUuid) {
      this.accionUuid = accionUuid;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setAccion(String accion) {
      this.accion = accion;
   }

   public void setTipoAccion(String tipoAccion) {
      this.tipoAccion = tipoAccion;
   }

   public void setIdAccion(String idAccion) {
      this.idAccion = idAccion;
   }

   public void setIdRelacionado(String idRelacionado) {
      this.idRelacionado = idRelacionado;
   }

   public void setTipoRelacionado(String tipoRelacionado) {
      this.tipoRelacionado = tipoRelacionado;
   }

   public void setUsuarioSess(String usuarioSess) {
      this.usuarioSess = usuarioSess;
   }

   public void setEstampaTiempo(String estampaTiempo) {
      this.estampaTiempo = estampaTiempo;
   }

   public void setAnterior(String anterior) {
      this.anterior = anterior;
   }

   public void setActual(String actual) {
      this.actual = actual;
   }

   public void setMsg(String msg) {
      this.msg = msg;
   }

   public void setSecuencialAcc(String secuencialAcc) {
      this.secuencialAcc = secuencialAcc;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RastroAccionPojo)) {
         return false;
      } else {
         RastroAccionPojo other = (RastroAccionPojo)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label167: {
               Object this$accionUuid = this.getAccionUuid();
               Object other$accionUuid = other.getAccionUuid();
               if (this$accionUuid == null) {
                  if (other$accionUuid == null) {
                     break label167;
                  }
               } else if (this$accionUuid.equals(other$accionUuid)) {
                  break label167;
               }

               return false;
            }

            Object this$id = this.getId();
            Object other$id = other.getId();
            if (this$id == null) {
               if (other$id != null) {
                  return false;
               }
            } else if (!this$id.equals(other$id)) {
               return false;
            }

            label153: {
               Object this$accion = this.getAccion();
               Object other$accion = other.getAccion();
               if (this$accion == null) {
                  if (other$accion == null) {
                     break label153;
                  }
               } else if (this$accion.equals(other$accion)) {
                  break label153;
               }

               return false;
            }

            Object this$tipoAccion = this.getTipoAccion();
            Object other$tipoAccion = other.getTipoAccion();
            if (this$tipoAccion == null) {
               if (other$tipoAccion != null) {
                  return false;
               }
            } else if (!this$tipoAccion.equals(other$tipoAccion)) {
               return false;
            }

            label139: {
               Object this$idAccion = this.getIdAccion();
               Object other$idAccion = other.getIdAccion();
               if (this$idAccion == null) {
                  if (other$idAccion == null) {
                     break label139;
                  }
               } else if (this$idAccion.equals(other$idAccion)) {
                  break label139;
               }

               return false;
            }

            Object this$idRelacionado = this.getIdRelacionado();
            Object other$idRelacionado = other.getIdRelacionado();
            if (this$idRelacionado == null) {
               if (other$idRelacionado != null) {
                  return false;
               }
            } else if (!this$idRelacionado.equals(other$idRelacionado)) {
               return false;
            }

            label125: {
               Object this$tipoRelacionado = this.getTipoRelacionado();
               Object other$tipoRelacionado = other.getTipoRelacionado();
               if (this$tipoRelacionado == null) {
                  if (other$tipoRelacionado == null) {
                     break label125;
                  }
               } else if (this$tipoRelacionado.equals(other$tipoRelacionado)) {
                  break label125;
               }

               return false;
            }

            label118: {
               Object this$usuarioSess = this.getUsuarioSess();
               Object other$usuarioSess = other.getUsuarioSess();
               if (this$usuarioSess == null) {
                  if (other$usuarioSess == null) {
                     break label118;
                  }
               } else if (this$usuarioSess.equals(other$usuarioSess)) {
                  break label118;
               }

               return false;
            }

            Object this$estampaTiempo = this.getEstampaTiempo();
            Object other$estampaTiempo = other.getEstampaTiempo();
            if (this$estampaTiempo == null) {
               if (other$estampaTiempo != null) {
                  return false;
               }
            } else if (!this$estampaTiempo.equals(other$estampaTiempo)) {
               return false;
            }

            label104: {
               Object this$anterior = this.getAnterior();
               Object other$anterior = other.getAnterior();
               if (this$anterior == null) {
                  if (other$anterior == null) {
                     break label104;
                  }
               } else if (this$anterior.equals(other$anterior)) {
                  break label104;
               }

               return false;
            }

            label97: {
               Object this$actual = this.getActual();
               Object other$actual = other.getActual();
               if (this$actual == null) {
                  if (other$actual == null) {
                     break label97;
                  }
               } else if (this$actual.equals(other$actual)) {
                  break label97;
               }

               return false;
            }

            Object this$msg = this.getMsg();
            Object other$msg = other.getMsg();
            if (this$msg == null) {
               if (other$msg != null) {
                  return false;
               }
            } else if (!this$msg.equals(other$msg)) {
               return false;
            }

            Object this$secuencialAcc = this.getSecuencialAcc();
            Object other$secuencialAcc = other.getSecuencialAcc();
            if (this$secuencialAcc == null) {
               if (other$secuencialAcc != null) {
                  return false;
               }
            } else if (!this$secuencialAcc.equals(other$secuencialAcc)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RastroAccionPojo;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $accionUuid = this.getAccionUuid();
      result = result * 59 + ($accionUuid == null ? 43 : $accionUuid.hashCode());
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $accion = this.getAccion();
      result = result * 59 + ($accion == null ? 43 : $accion.hashCode());
      Object $tipoAccion = this.getTipoAccion();
      result = result * 59 + ($tipoAccion == null ? 43 : $tipoAccion.hashCode());
      Object $idAccion = this.getIdAccion();
      result = result * 59 + ($idAccion == null ? 43 : $idAccion.hashCode());
      Object $idRelacionado = this.getIdRelacionado();
      result = result * 59 + ($idRelacionado == null ? 43 : $idRelacionado.hashCode());
      Object $tipoRelacionado = this.getTipoRelacionado();
      result = result * 59 + ($tipoRelacionado == null ? 43 : $tipoRelacionado.hashCode());
      Object $usuarioSess = this.getUsuarioSess();
      result = result * 59 + ($usuarioSess == null ? 43 : $usuarioSess.hashCode());
      Object $estampaTiempo = this.getEstampaTiempo();
      result = result * 59 + ($estampaTiempo == null ? 43 : $estampaTiempo.hashCode());
      Object $anterior = this.getAnterior();
      result = result * 59 + ($anterior == null ? 43 : $anterior.hashCode());
      Object $actual = this.getActual();
      result = result * 59 + ($actual == null ? 43 : $actual.hashCode());
      Object $msg = this.getMsg();
      result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
      Object $secuencialAcc = this.getSecuencialAcc();
      result = result * 59 + ($secuencialAcc == null ? 43 : $secuencialAcc.hashCode());
      return result;
   }

   public String toString() {
      return "RastroAccionPojo(accionUuid=" + this.getAccionUuid() + ", id=" + this.getId() + ", accion=" + this.getAccion() + ", tipoAccion=" + this.getTipoAccion() + ", idAccion=" + this.getIdAccion() + ", idRelacionado=" + this.getIdRelacionado() + ", tipoRelacionado=" + this.getTipoRelacionado() + ", usuarioSess=" + this.getUsuarioSess() + ", estampaTiempo=" + this.getEstampaTiempo() + ", anterior=" + this.getAnterior() + ", actual=" + this.getActual() + ", msg=" + this.getMsg() + ", secuencialAcc=" + this.getSecuencialAcc() + ")";
   }
}
