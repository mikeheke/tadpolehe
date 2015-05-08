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

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.view.html.BuilderUtils;

/**
 * org.extremecomponents.tree.view.HtmlView.java -
 * 
 * @author Paul Horn
 */
public final class TreeView extends HtmlView {
    public void beforeBody(TableModel model) {
        div().styleClass("eXtremeTable").close();
        titleRow(model);
        tableStart(model);
        statusBar(model);
        filter(model);
        header(model);
    }

    public Object afterBody(TableModel model) {
        totals(model);
        tableEnd(model);
        newline().divEnd();

        return toString();
    }

    public void titleRow(TableModel model) {
        if (StringUtils.isBlank(model.getTableHandler().getTable().getTitle())) {
            return;
        }

        table(0).border("0").cellPadding("0").cellSpacing("0");
        width(model.getTableHandler().getTable().getWidth()).close();

        tr(1).close();

        td(2).styleClass(TITLE).close();

        title(model);

        tdEnd();

        trEnd(1);

        tableEnd(0);
        newline();
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

            String count = model.getTableHandler().getTable().getAttributeAsString(TreeConstants.FILTERED_COUNT);

            if ("0".equalsIgnoreCase(count)) {
                append("There were no results found.");
            } else {
                append(count + " total results found");
            }

            tdEnd();
        }

        if (model.getTableHandler().getTable().isFilterable()) {
            td(4).styleClass(FILTER_BUTTONS).close();

            String imageSearchArrow = BuilderUtils.getImage(model, SEARCH_ARROW_IMAGE);

            img(imageSearchArrow, "Search");

            nbsp();

            a().quote().append(HtmlViewUtils.filterJavaScript(model)).quote().close();

            String imageSearch = BuilderUtils.getImage(model, SEARCH_IMAGE);

            if (StringUtils.isNotEmpty(imageSearch)) {
                img(imageSearch, "Search");
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
                img(imageClear, "Clear");
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
}
