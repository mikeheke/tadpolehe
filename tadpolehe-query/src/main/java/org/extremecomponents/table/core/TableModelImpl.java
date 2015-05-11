/*
 * Copyright 2004 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.extremecomponents.table.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.handler.ColumnHandler;
import org.extremecomponents.table.handler.ExportHandler;
import org.extremecomponents.table.handler.RowHandler;
import org.extremecomponents.table.handler.TableHandler;
import org.extremecomponents.table.handler.ViewHandler;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.ModelLimitFactory;
import org.extremecomponents.table.limit.TableLimit;

/**
 * @author Jeff Johnston
 */
public final class TableModelImpl implements TableModel {
    private static Log logger = LogFactory.getLog(TableModel.class);

    // model interfaces
    private Context context;
    private Preferences preferences;
    private Messages messages;
    private Registry registry;

    // model handlers
    private TableHandler tableHandler = new TableHandler(this);
    private RowHandler rowHandler = new RowHandler(this);
    private ColumnHandler columnHandler = new ColumnHandler(this);
    private ViewHandler viewHandler = new ViewHandler(this);
    private ExportHandler exportHandler = new ExportHandler(this);

    // model objects
    private Object currentRowBean;
    private Collection collectionOfBeans;
    private Collection collectionOfFilteredBeans;
    private Collection collectionOfPageBeans;
    private Limit limit;
    private Locale locale;

    public TableModelImpl(Context context) {
        this(context, null);
    }

    public TableModelImpl(Context context, String locale) {
        this.context = context;

        Preferences preferences = new TableProperties();
        preferences.init(context, TableModelUtils.getPreferencesLocation(context));
        this.preferences = preferences;

        this.locale = TableModelUtils.getLocale(context, preferences, locale);

        Messages messages = TableModelUtils.getMessages(this);
        messages.init(context, this.locale);
        this.messages = messages;
    }

    public Context getContext() {
        return context;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public Messages getMessages() {
        return messages;
    }

    public Registry getRegistry() {
        return registry;
    }

    public Table getTableInstance() {
        return new Table(this);
    }

    public Export getExportInstance() {
        return new Export(this);
    }

    public Row getRowInstance() {
        return new Row(this);
    }

    public Column getColumnInstance() {
        return new Column(this);
    }

    public void addTable(Table table) {
        tableHandler.addTable(table);

        // now set the registry
        this.registry = new TableRegistry(this);
        
        //then set the limit
        LimitFactory limitFactory = new ModelLimitFactory(this);
        this.limit = new TableLimit(limitFactory);
    }

    public void addExport(Export export) {
        exportHandler.addExport(export);
    }

    public void addRow(Row row) {
        rowHandler.addRow(row);
    }

    public void addColumn(Column column) {
        columnHandler.addAutoGenerateColumn(column);
    }

    public void addColumns(String autoGenerateColumns) {
        autoGenerateColumns = TableModelUtils.getAutoGenerateColumnsPreference(this, autoGenerateColumns);
        TableCache.getInstance().getAutoGenerateColumns(autoGenerateColumns).addColumns(this);
    }

    /**
     * The parameter value can be null, String, String[], or a List. The
     * parameter value will be converted to a String[] internally.
     * 
     * @param name The parameter name
     * @param value The parameter value
     */
    public void addParameter(String name, Object value) {
        registry.addParameter(name, value);
    }

    public TableHandler getTableHandler() {
        return tableHandler;
    }

    public RowHandler getRowHandler() {
        return rowHandler;
    }

    public ColumnHandler getColumnHandler() {
        return columnHandler;
    }

    public ViewHandler getViewHandler() {
        return viewHandler;
    }

    public ExportHandler getExportHandler() {
        return exportHandler;
    }

    public Object getCurrentRowBean() {
        return currentRowBean;
    }

    public void setCurrentRowBean(Object bean) {
        int rowcount = rowHandler.increaseRowCount();
        this.currentRowBean = bean;
        context.setPageAttribute(TableConstants.ROWCOUNT, String.valueOf(rowcount));
        context.setPageAttribute(tableHandler.getTable().getVar(), bean);
    }

    public Collection getCollectionOfBeans() {
        return collectionOfBeans;
    }

    public void setCollectionOfBeans(Collection collectionOfBeans) {
        this.collectionOfBeans = collectionOfBeans;
    }

    public Collection getCollectionOfFilteredBeans() {
        return collectionOfFilteredBeans;
    }

    public void setCollectionOfFilteredBeans(Collection collectionOfFilteredBeans) {
        this.collectionOfFilteredBeans = collectionOfFilteredBeans;
    }

    public Collection getCollectionOfPageBeans() {
        return collectionOfPageBeans;
    }

    public void setCollectionOfPageBeans(Collection collectionOfPageBeans) {
        this.collectionOfPageBeans = collectionOfPageBeans;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    public Locale getLocale() {
        return locale;
    }

    public Collection execute() throws Exception {
        Collection rows = TableModelUtils.retrieveRows(this);

        rows = new ArrayList(rows); // copy for thread safety

        this.collectionOfBeans = rows;

        rows = TableModelUtils.filterRows(this, rows);

        rows = TableModelUtils.sortRows(this, rows);

        this.collectionOfFilteredBeans = rows;

        Integer totalRows = getTableHandler().getTotalRows();
        int defaultRowsDisplayed = getTableHandler().getTable().getRowsDisplayed();
        if (totalRows != null) {
            limit.setRowAttributes(totalRows.intValue(), defaultRowsDisplayed);
        } else {
            limit.setRowAttributes(rows.size(), defaultRowsDisplayed);
        }

        if (logger.isDebugEnabled()) {
            logger.debug(limit.toString());
        }

        rows = TableModelUtils.getCurrentRows(this, rows);

        this.collectionOfPageBeans = rows;

        viewHandler.setView();

        return rows;
    }

    public void setColumnValues() throws Exception {
        List columns = columnHandler.getColumns();
        Iterator iter = columns.iterator();
        while (iter.hasNext()) {
            Column column = (Column) iter.next();
            if ("true".equals(column.getAttribute(TableConstants.IS_AUTO_GENERATE_COLUMN))) {
                String property = column.getProperty();
                Object propertyValue = TableModelUtils.getColumnPropertyValue(currentRowBean, property);
                column.setValue(propertyValue);
                column.setPropertyValue(propertyValue);
                columnHandler.modifyColumnAttributes(column);
                viewHandler.addColumnValueToView(column);
            }
        }
    }

    public Object getViewData() throws Exception {
        Object viewData = viewHandler.getView().afterBody(this);

        if (limit.isExported()) {
            context.setRequestAttribute(TableConstants.VIEW_DATA, viewData);
            context.setRequestAttribute(TableConstants.VIEW_RESOLVER, exportHandler.getCurrentExport().getViewResolver());
            context.setRequestAttribute(TableConstants.EXPORT_FILE_NAME, exportHandler.getCurrentExport().getFileName());
            return "";
        }

        return viewData;
    }

    /**
     * Will execute the model and interate over the rows. Very useful for
     * assembling a table using java code.
     */
    public Object assemble() throws Exception {
        Iterator iterator = execute().iterator();
        for (Iterator iter = iterator; iter.hasNext();) {
            Object bean = iterator.next();
            setCurrentRowBean(bean);

            // call to modify attributes
            getRowHandler().modifyRowAttributes();

            // call columns to set values
            setColumnValues();
        }

        return getViewData();
    }
}
