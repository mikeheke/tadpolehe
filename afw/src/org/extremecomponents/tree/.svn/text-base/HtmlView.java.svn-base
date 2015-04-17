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
package org.extremecomponents.tree;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.bean.Row;
import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.calc.CalcResult;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.View;
import org.extremecomponents.table.view.html.BuilderConstants;
import org.extremecomponents.table.view.html.BuilderUtils;
import org.extremecomponents.util.ExtremeUtils;
import org.extremecomponents.util.HtmlBuilder;

/**
 * The default implementation of the table. The notion is that you can easily
 * plug in different parts of the view as needed or desired.
 * 
 * @author Jeff Johnston
 */
public class HtmlView extends HtmlBuilder implements View {
    public final static String TOOLBAR = "toolbar";
    public final static String SEPARATOR = "separator";
    public final static String STATUS_BAR = "statusBar";
    public final static String FILTER_BUTTONS = "filterButtons";
    public final static String FORM_BUTTONS = "formButtons";
    public final static String FILTER = "filter";
    public final static String TITLE = "title";
    public final static String TABLE_TOTAL_TITLE = "tableTotalTitle";
    public final static String TABLE_TOTALS = "tableTotal";
    public final static String TABLE_TOTALS_EMPTY = "tableTotalEmpty";
    public final static String TABLE_BODY = "tableBody";

    public final static String LAST_PAGE = "lastPage";
    public final static String LAST_PAGE_DISABLED = "lastPageDisabled";
    public final static String FIRST_PAGE = "firstPage";
    public final static String FIRST_PAGE_DISABLED = "firstPageDisabled";
    public final static String PREV_PAGE = "prevPage";
    public final static String PREV_PAGE_DISABLED = "prevPageDisabled";
    public final static String NEXT_PAGE = "nextPage";
    public final static String NEXT_PAGE_DISABLED = "nextPageDisabled";

    public final static String SEARCH_ARROW_IMAGE = "searchArrow";
    public final static String SEARCH_IMAGE = "search";
    public final static String CLEAR_IMAGE = "clear";

    public void beforeBody(TableModel model) {
        div().styleClass("eXtremeTable").close();
        toolbarPlacement(model);
        tableStart(model);
        statusBar(model);
        filter(model);
        header(model);
        tbody(1).styleClass(TABLE_BODY).close();
    }

    public void body(TableModel model, Column column) {
        if (column.isFirstColumn()) {
            tr(1);
            if (model.getRowHandler().isRowEven()) {
                rowStyleAndJavascript(model, BuilderConstants.ROW_EVEN_CSS);
            } else {
                rowStyleAndJavascript(model, BuilderConstants.ROW_ODD_CSS);
            }
            close();
        }

        append(column.getCellDisplay());

        if (column.isLastColumn()) {
            trEnd(1);
        }
    }

    public Object afterBody(TableModel model) {
        totals(model);
        tbodyEnd(1);
        tableEnd(model);
        newline().divEnd();

        return toString();
    }

    private void rowStyleAndJavascript(TableModel model, String defaultStyleClass) {
        Row row = model.getRowHandler().getRow();

        String styleClass = row.getStyleClass();
        String style = row.getStyle();

        if (StringUtils.isBlank(styleClass)) {
            styleClass = defaultStyleClass;
        }

        styleClass(styleClass);
        style(style);
        onclick(row.getOnclick());

        boolean highlightRow = row.isHighlightRow();
        if (highlightRow) {
            String highlightClass = row.getHighlightClass();
            if (StringUtils.isNotBlank(row.getOnmouseover())) {
                onmouseover("this.className='" + highlightClass + "'; " + row.getOnmouseover());
            } else {
                onmouseover("this.className='" + highlightClass + "'");
            }

            if (StringUtils.isNotBlank(row.getOnmouseout())) {
                onmouseout("this.className='" + styleClass + "'; " + row.getOnmouseout());
            } else {
                onmouseout("this.className='" + styleClass + "'");
            }
        } else {
            onmouseover(row.getOnmouseover());
            onmouseout(row.getOnmouseout());
        }
    }

    public void toolbarPlacement(TableModel model) {
        boolean showPagination = model.getTableHandler().getTable().isShowPagination();
        boolean showExports = model.getExportHandler().showExports();

        if (!showPagination && !showExports && StringUtils.isBlank(model.getTableHandler().getTable().getTitle())) {
            return;
        }

        table(0).border("0").cellPadding("0").cellSpacing("0");
        width(model.getTableHandler().getTable().getWidth()).close();

        tr(1).close();

        td(2).styleClass(TITLE).close();

        title(model);

        tdEnd();

        td(2).align("right").close();

        toolbar(model);

        tdEnd();

        trEnd(1);

        tableEnd(0);
        newline();
    }

    public void title(TableModel model) {
        String title = model.getTableHandler().getTable().getTitle();
        if (StringUtils.isNotBlank(title)) {
            span().close().append(title).spanEnd();
        }
    }

    /**
     * The pagination and export will display together.
     */
    public void toolbar(TableModel model) {
        boolean showPagination = model.getTableHandler().getTable().isShowPagination();
        boolean showExports = model.getExportHandler().showExports();

        if (!showPagination && !showExports) {
            return;
        }

        table(2).border("0").cellPadding("0").cellSpacing("1").styleClass(TOOLBAR).close();

        tr(3).close();

        toolbarFormStart(model);

        if (showPagination) {
            toolbarPaginationIcons(model);
            String separator = BuilderUtils.getImage(model, "separator");
            td(4).rowSpan("2").styleClass(SEPARATOR).close().img(separator).tdEnd();

            rowsDisplayedDroplist(model);

            if (showExports) {
                td(4).rowSpan("2").styleClass(SEPARATOR).close().img(separator).tdEnd();
            }
        }

        if (showExports) {
            toolbarExportIcons(model);
        }

        trEnd(3);

        tr(3).close();

        formEnd();

        trEnd(3);

        tableEnd(2);
        newline();
        tabs(2);
    }

    /**
     * Get the HTML for the start of the form <form>tag. The filter will need to
     * be wrapped in a form tag so that it can be submitted.
     */
    private void toolbarFormStart(TableModel model) {
        form();
        name(model.getTableHandler().prefixWithTableId() + "toolbar");

        String action = model.getTableHandler().getTable().getAction();

        if (StringUtils.isNotEmpty(action)) {
            action(action);
        }

        close();

        String hiddenFields = TreeRegistryUtils.getFormHiddenFields(model, true, true, false, false);

        if (StringUtils.isNotEmpty(hiddenFields)) {
            append(hiddenFields);
        }
    }

    public void toolbarPaginationIcons(TableModel model) {
        int page = model.getLimit().getPage();
        int totalPages = BuilderUtils.getTotalPages(model);

        td(4).close();
        if (!BuilderUtils.isFirstPageEnabled(page)) {
            String firstPageImage = BuilderUtils.getImage(model, FIRST_PAGE_DISABLED);
            img(firstPageImage);
        } else {
            String firstPageImage = BuilderUtils.getImage(model, FIRST_PAGE);
            String firstPageTooltip = model.getMessages().getMessage(BuilderConstants.TOOLBAR_FIRST_PAGE_TOOLTIP);
            paginationImage(model, 1, firstPageImage, firstPageTooltip);
        }
        tdEnd();

        // prev page
        td(4).close();
        if (!BuilderUtils.isPrevPageEnabled(page)) {
            String prevPageImage = BuilderUtils.getImage(model, PREV_PAGE_DISABLED);
            img(prevPageImage);
        } else {
            String prevPageImage = BuilderUtils.getImage(model, PREV_PAGE);
            String prevPageTooltip = model.getMessages().getMessage(BuilderConstants.TOOLBAR_PREV_PAGE_TOOLTIP);
            paginationImage(model, page - 1, prevPageImage, prevPageTooltip);
        }
        tdEnd();

        // next page
        td(4).close();
        if (!BuilderUtils.isNextPageEnabled(page, totalPages)) {
            String nextPageImage = BuilderUtils.getImage(model, NEXT_PAGE_DISABLED);
            img(nextPageImage);
        } else {
            String nextPageImage = BuilderUtils.getImage(model, NEXT_PAGE);
            String nextPageTooltip = model.getMessages().getMessage(BuilderConstants.TOOLBAR_NEXT_PAGE_TOOLTIP);
            paginationImage(model, page + 1, nextPageImage, nextPageTooltip);
        }
        tdEnd();

        td(4).close();
        if (!BuilderUtils.isLastPageEnabled(page, totalPages)) {
            String lastPageImage = BuilderUtils.getImage(model, LAST_PAGE_DISABLED);
            img(lastPageImage);
        } else {
            String lastPageImage = BuilderUtils.getImage(model, LAST_PAGE);
            String lastPageTooltip = model.getMessages().getMessage(BuilderConstants.TOOLBAR_LAST_PAGE_TOOLTIP);
            paginationImage(model, totalPages, lastPageImage, lastPageTooltip);
        }
        tdEnd();
    }

    public void paginationImage(TableModel model, int page, String image, String tooltip) {
        a();
        quote();

        String action = model.getTableHandler().getTable().getAction();

        if (StringUtils.isNotEmpty(action)) {
            append(action);
        }

        question().append(model.getTableHandler().prefixWithTableId()).append(TableConstants.PAGE).equals().append("" + page);
        append(TreeRegistryUtils.getURLParameterString(model, true, true, false, true));
        quote().close();
        img(image, tooltip);
        aEnd();
    }

    public void toolbarExportIcons(TableModel model) {
        for (Iterator iter = model.getExportHandler().getExports().iterator(); iter.hasNext();) {
            td(4).close();
            Export export = (Export) iter.next();
            exportImage(model, export);
            tdEnd();
        }
    }

    public void rowsDisplayedDroplist(TableModel model) {
        int rowsDisplayed = model.getTableHandler().getTable().getRowsDisplayed();
        int medianRowsDisplayed = model.getTableHandler().getTable().getMedianRowsDisplayed();
        int maxRowsDisplayed = model.getTableHandler().getTable().getMaxRowsDisplayed();
        int currentRowsDisplayed = model.getLimit().getCurrentRowsDisplayed();

        td(4).width("20").close();
        newline();
        tabs(4);
        select().name(model.getTableHandler().prefixWithTableId() + TableConstants.CURRENT_ROWS_DISPLAYED);

        StringBuffer onchange = new StringBuffer();
        onchange.append(HtmlViewUtils.paginationJavaScript(model));
        onchange(onchange.toString());

        close();

        newline();
        tabs(4);

        // default rows
        option().value(String.valueOf(rowsDisplayed));
        if (currentRowsDisplayed == rowsDisplayed) {
            append(" selected");
        }
        close();
        append(String.valueOf(rowsDisplayed));
        optionEnd();

        // median rows
        option().value(String.valueOf(medianRowsDisplayed));
        if (currentRowsDisplayed == medianRowsDisplayed) {
            append(" selected");
        }
        close();
        append(String.valueOf(medianRowsDisplayed));
        optionEnd();

        // max rows
        option().value(String.valueOf(maxRowsDisplayed));
        if (currentRowsDisplayed == maxRowsDisplayed) {
            append(" selected");
        }
        close();
        append(String.valueOf(maxRowsDisplayed));
        optionEnd();

        newline();
        tabs(4);
        selectEnd();

        img(BuilderUtils.getImage(model, "rowsDisplayed"));

        tdEnd();
    }

    private void exportImage(TableModel model, Export export) {
        a();
        quote();

        String action = model.getTableHandler().getTable().getAction();

        if (StringUtils.isNotEmpty(action)) {
            append(action);
        }

        question().append(TableConstants.EXPORT_TABLE_ID).equals().append(model.getTableHandler().getTable().getTableId()).ampersand().append(
                model.getTableHandler().prefixWithTableId() + TableConstants.EXPORT_VIEW).equals().append(export.getView()).ampersand().append(
                model.getTableHandler().prefixWithTableId() + TableConstants.EXPORT_FILE_NAME).equals().append(export.getFileName());
        append(TreeRegistryUtils.getURLParameterString(model, true, true, true, true));
        quote();
        close();

        String imageName = BuilderUtils.getImage(model, export.getImageName());

        if (StringUtils.isNotEmpty(imageName)) {
            String tooltip = export.getTooltip();
            if (StringUtils.isEmpty(tooltip)) {
                img(imageName);
            } else {
                img(imageName, tooltip);
            }
        } else {
            nbsp().append(export.getView()).nbsp();
        }

        aEnd();
    }

    /**
     * The HTML for the filter search and clear buttons.
     */
    public void statusBar(TableModel model) {
        if (!model.getTableHandler().getTable().isShowStatusBar() && !model.getTableHandler().getTable().isFilterable()) {
            return;
        }

        tr(1).close();

        td(2).colSpan(String.valueOf(model.getColumnHandler().columnCount())).close();

        table(2).border("0").cellPadding("0").cellSpacing("0").width("100%").close();
        tr(3).close();

        if (model.getTableHandler().getTable().isShowStatusBar()) {
            td(4).styleClass(STATUS_BAR).close();

            if (model.getLimit().getTotalRows() == 0) {
                append(model.getMessages().getMessage(BuilderConstants.STATUSBAR_NO_RESULTS_FOUND));
            } else {
                Integer total = new Integer(model.getLimit().getTotalRows());
                Integer from = new Integer(model.getLimit().getRowStart() + 1);
                Integer to = new Integer(model.getLimit().getRowEnd());
                Object[] messageArguments = { total, from, to };
                append(model.getMessages().getMessage(BuilderConstants.STATUSBAR_RESULTS_FOUND, messageArguments));
            }

            tdEnd();
        }

        if (model.getTableHandler().getTable().isFilterable()) {
            td(4).styleClass(FILTER_BUTTONS).close();

            String imageSearchArrow = BuilderUtils.getImage(model, SEARCH_ARROW_IMAGE);

            img(imageSearchArrow);

            nbsp();

            a().quote().append(HtmlViewUtils.filterJavaScript(model)).quote().close();

            String imageSearch = BuilderUtils.getImage(model, SEARCH_IMAGE);

            if (StringUtils.isNotEmpty(imageSearch)) {
                String searchTooltip = model.getMessages().getMessage(BuilderConstants.TOOLBAR_FILTER_TOOLTIP);
                img(imageSearch, searchTooltip);
            } else {
                append("&nbsp;Filter&nbsp;");
            }

            aEnd();

            nbsp();

            a().quote().append(
                    "javascript:document.forms." + model.getTableHandler().prefixWithTableId() + "filter." + model.getTableHandler().prefixWithTableId() + TableConstants.FILTER
                            + TableConstants.ACTION + ".value='" + TableConstants.CLEAR_ACTION + "';document.forms." + model.getTableHandler().prefixWithTableId() + "filter.submit()").quote().close();

            String imageClear = BuilderUtils.getImage(model, CLEAR_IMAGE);

            if (StringUtils.isNotEmpty(imageClear)) {
                String clearTooltip = model.getMessages().getMessage(BuilderConstants.TOOLBAR_CLEAR_TOOLTIP);
                img(imageClear, clearTooltip);
            } else {
                append("&nbsp;Clear&nbsp;");
            }

            aEnd();

            tdEnd();
        }

        trEnd(3);
        tableEnd(2);
        newline();
        tabs(2);

        tdEnd();
        trEnd(1);
        tabs(2);
        newline();
    }

    /**
     * The opening table tag along with all the attributes of the table.
     */
    public void tableStart(TableModel model) {
        Table table = model.getTableHandler().getTable();

        table(0);
        id(table.getTableId());
        border(table.getBorder());
        cellSpacing(table.getCellspacing());
        cellPadding(table.getCellpadding());
        width(table.getWidth());
        styleClass(table.getStyleClass());
        style(table.getStyle());
        close();
    }

    /**
     * Closes the table.
     */
    public void tableEnd(TableModel model) {
        tableEnd(0);
    }

    /**
     * The header row of the table. This is typically the first row of the
     * table.
     */
    public void header(TableModel model) {
        tr(1).close();

        List columns = model.getColumnHandler().getHeaderColumns();
        for (Iterator iter = columns.iterator(); iter.hasNext();) {
            Column column = (Column) iter.next();
            append(column.getCellDisplay());
        }

        trEnd(1);
    }

    /**
     * The filter row of the table. Typically sits below the header (if
     * enabled). Will contain input or select type fields to allow the user to
     * filter the result set.
     */
    public void filter(TableModel model) {
        if (!model.getTableHandler().getTable().isFilterable()) {
            return;
        }

        filterFormStart(model);

        tr(1).styleClass(FILTER).id(FILTER).close();

        List columns = model.getColumnHandler().getFilterColumns();
        for (Iterator iter = columns.iterator(); iter.hasNext();) {
            Column column = (Column) iter.next();
            append(column.getCellDisplay());
        }

        trEnd(1);
        formEnd();
    }

    /**
     * Get the HTML for the start of the form <form>tag. The filter will need to
     * be wrapped in a form tag so that it can be submitted.
     */
    private void filterFormStart(TableModel model) {
        form();
        name(model.getTableHandler().prefixWithTableId() + "filter");

        String action = model.getTableHandler().getTable().getAction();

        if (StringUtils.isNotEmpty(action)) {
            action(action);
        }

        close();

        input("hidden").name(model.getTableHandler().prefixWithTableId() + TableConstants.FILTER + TableConstants.ACTION).close();

        String hiddenFields = TreeRegistryUtils.getFormHiddenFields(model, false, true, false, true);

        if (StringUtils.isNotEmpty(hiddenFields)) {
            append(hiddenFields);
        }
    }
    public void totals(TableModel model) {
        Column calcColumn = model.getColumnHandler().getFirstCalcColumn();
        if (calcColumn == null) {
            return;
        }

        String calcTitle[] = calcColumn.getCalcTitle();
        if (calcTitle != null && calcTitle.length > 0) {
            tr(1).close();
            td(2).styleClass(TABLE_TOTAL_TITLE).colSpan("" + model.getColumnHandler().columnCount()).close();
            for (int i = 0; i < calcTitle.length; i++) {
                String title = calcTitle[i];
                append(title);
            }
            tdEnd();
            trEnd(1);
        }

        tr(1).close();
        for (Iterator iter = model.getColumnHandler().getColumns().iterator(); iter.hasNext();) {
            Column column = (Column) iter.next();
            if (column.isCalculated()) {
                td(2).styleClass(TABLE_TOTALS).close();
                CalcResult calcValues[] = model.getColumnHandler().getCalcResults(column);
                for (int i = 0; i < calcValues.length; i++) {
                    CalcResult calcValue = calcValues[i];
                    Number value = calcValue.getValue();
                    append(ExtremeUtils.formatNumber(column.getFormat(), value, model.getLocale()));
                    if (calcValues.length > 0 && i + 1 != calcValues.length) {
                        append(" / ");
                    }
                }
            } else {
                td(2).styleClass(TABLE_TOTALS_EMPTY).close();
                nbsp();
            }

            tdEnd();
        }
        trEnd(1);
    }
}
