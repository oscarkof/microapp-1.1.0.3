package com.fiduciaria.microapp.componentes.datatable;

import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByBorder;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackHeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NoRecordsToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

public abstract class AjaxFallbackSimpleDataTable<T, S> extends DataTable<T, S> {
  private static final long serialVersionUID = 1L;
  
  public AjaxFallbackSimpleDataTable(String id, List<? extends IColumn<T, S>> columns, ISortableDataProvider<T, S> dataProvider, int rowsPerPage) {
    super(id, columns, (IDataProvider)dataProvider, rowsPerPage);
    setOutputMarkupId(true);
    setVersioned(false);
    addTopToolbar((AbstractToolbar)new AjaxFallbackHeadersToolbar(this, (ISortStateLocator)dataProvider));
    addBottomToolbar((AbstractToolbar)new NoRecordsToolbar(this));
  }
  
  public AjaxFallbackSimpleDataTable(String id, List<? extends IColumn<T, S>> columns, ISortableDataProvider<T, S> dataProvider, int rowsPerPage, FilterToolbar filterToolbar) {
    super(id, columns, (IDataProvider)dataProvider, rowsPerPage);
    setOutputMarkupId(true);
    setVersioned(false);
    if (filterToolbar != null)
      addTopToolbar((AbstractToolbar)filterToolbar); 
    addTopToolbar((AbstractToolbar)new AjaxFallbackHeadersToolbar(this, (ISortStateLocator)dataProvider));
    addBottomToolbar((AbstractToolbar)new NoRecordsToolbar(this));
  }
  
  public AjaxFallbackSimpleDataTable(String id, List<? extends IColumn<T, S>> columns, ISortableDataProvider<T, S> dataProvider, int rowsPerPage, FilterForm<?> filterForm, String message, String fileName) {
    super(id, columns, (IDataProvider)dataProvider, rowsPerPage);
    setOutputMarkupId(true);
    setVersioned(false);
    AjaxFallbackHeadersToolbar thb = new AjaxFallbackHeadersToolbar(this, (ISortStateLocator)dataProvider) {
        protected WebMarkupContainer newSortableHeader(String borderId, Object property, ISortStateLocator locator) {
          return (WebMarkupContainer)new AjaxFallbackOrderByBorder(borderId, property, locator) {
              private static final long serialVersionUID = 1L;
              
              protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {}
              
              protected void onAjaxClick(AjaxRequestTarget target) {
                //target.add(new Component[] { (Component)AjaxFallbackSimpleDataTable.null.access$000(this.this$1) });
                AjaxFallbackSimpleDataTable.this.hookPfToolbar(target);
              }
              
              protected void onSortChanged() {
                super.onSortChanged();
               // AjaxFallbackSimpleDataTable.null.this.getTable().setCurrentPage(0L);
              }
            };
        }
      };
    addTopToolbar((AbstractToolbar)thb);
    addBottomToolbar((AbstractToolbar)new NoRecordsToolbar(this));
  }
  
  protected Item<T> newRowItem(String id, int index, final IModel<T> model) {
    OddEvenItem row = new OddEvenItem(id, index, model);
    row.add(new Behavior[] { (Behavior)new AjaxEventBehavior("click") {
            protected void onEvent(AjaxRequestTarget target) {
              AjaxFallbackSimpleDataTable.this.clickOnRow(target, model);
            }
          } });
    return (Item<T>)row;
  }
  
  public abstract void clickOnRow(AjaxRequestTarget paramAjaxRequestTarget, IModel<T> paramIModel);
  
  public abstract void hookPfToolbar(AjaxRequestTarget paramAjaxRequestTarget);
}
