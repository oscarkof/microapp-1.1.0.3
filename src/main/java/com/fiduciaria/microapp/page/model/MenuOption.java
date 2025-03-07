// Source code is decompiled from a .class file using FernFlower decompiler.
package com.fiduciaria.microapp.page.model;

import com.fiduciaria.microapp.page.PageType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.io.IClusterable;

public class MenuOption implements IClusterable {
   private String id;
   private String label;
   private PageType pageType;
   private Class<? extends WebPage> pageClass;

   public MenuOption() {
   }

   public String getId() {
      return this.id;
   }

   public String getLabel() {
      return this.label;
   }

   public PageType getPageType() {
      return this.pageType;
   }

   public Class<? extends WebPage> getPageClass() {
      return this.pageClass;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setLabel(String label) {
      this.label = label;
   }

   public void setPageType(PageType pageType) {
      this.pageType = pageType;
   }

   public void setPageClass(Class<? extends WebPage> pageClass) {
      this.pageClass = pageClass;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MenuOption)) {
         return false;
      } else {
         MenuOption other = (MenuOption)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            label59: {
               Object this$id = this.getId();
               Object other$id = other.getId();
               if (this$id == null) {
                  if (other$id == null) {
                     break label59;
                  }
               } else if (this$id.equals(other$id)) {
                  break label59;
               }

               return false;
            }

            Object this$label = this.getLabel();
            Object other$label = other.getLabel();
            if (this$label == null) {
               if (other$label != null) {
                  return false;
               }
            } else if (!this$label.equals(other$label)) {
               return false;
            }

            Object this$pageType = this.getPageType();
            Object other$pageType = other.getPageType();
            if (this$pageType == null) {
               if (other$pageType != null) {
                  return false;
               }
            } else if (!this$pageType.equals(other$pageType)) {
               return false;
            }

            Object this$pageClass = this.getPageClass();
            Object other$pageClass = other.getPageClass();
            if (this$pageClass == null) {
               if (other$pageClass != null) {
                  return false;
               }
            } else if (!this$pageClass.equals(other$pageClass)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MenuOption;
   }

   public int hashCode() {
      int PRIME = 1;
      int result = 1;
      Object $id = this.getId();
      result = result * 59 + ($id == null ? 43 : $id.hashCode());
      Object $label = this.getLabel();
      result = result * 59 + ($label == null ? 43 : $label.hashCode());
      Object $pageType = this.getPageType();
      result = result * 59 + ($pageType == null ? 43 : $pageType.hashCode());
      Object $pageClass = this.getPageClass();
      result = result * 59 + ($pageClass == null ? 43 : $pageClass.hashCode());
      return result;
   }

   public String toString() {
      return "MenuOption(id=" + this.getId() + ", label=" + this.getLabel() + ", pageType=" + this.getPageType() + ", pageClass=" + this.getPageClass() + ")";
   }
}
