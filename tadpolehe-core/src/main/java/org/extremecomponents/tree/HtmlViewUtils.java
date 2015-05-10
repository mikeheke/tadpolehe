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

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.core.TableConstants;

/**
 * @author Jeff Johnston
 */
public final class HtmlViewUtils {

    private HtmlViewUtils() {
    }

    /**
     * generate the script to submit the filter
     * 
     * @deprecated Is used by now deprecated HtmlView
     */
    public static String filterJavaScript(TableModel model) {
        return "javascript:document.forms." + model.getTableHandler().prefixWithTableId() + "filter." + model.getTableHandler().prefixWithTableId() + TableConstants.FILTER + TableConstants.ACTION
                + ".value='" + TableConstants.FILTER_ACTION + "';document.forms." + model.getTableHandler().prefixWithTableId() + "filter.submit()";
    }

    /**
     * generate the script to submit the pagination
     * 
     * @deprecated Is used by now deprecated HtmlView
     */
    public static String paginationJavaScript(TableModel model) {
        return "javascript:document.forms." + model.getTableHandler().prefixWithTableId() + "toolbar.submit()";
    }
}
