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
package org.extremecomponents.table.callback;

import java.util.Collection;

import org.extremecomponents.table.bean.Table;
import org.extremecomponents.table.core.RetrievalUtils;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;

/**
 * Handles the pagination feature.
 * 
 * @author Jeff Johnston
 */
public final class LimitCallback implements RetrieveRowsCallback, FilterRowsCallback, SortRowsCallback {
    public final static String TOTAL_ROWS = "totalRows";

    /**
     * Retrieve the collection from the given scope. Requires that a variable be
     * passed in from any of the web scopes (page, request, session,
     * application) that is called "totalRows". The totalRows is a Integer
     * value.
     */
    public Collection retrieveRows(TableModel model) throws Exception {
        Table table = model.getTableHandler().getTable();
        Collection rows = RetrievalUtils.retrieveCollection(model.getContext(), table.getItems(), table.getScope());

        Object totalRows = RetrievalUtils.retrieve(model.getContext(), TableConstants.TOTAL_ROWS);
        if (totalRows == null) {
            totalRows = (Integer) RetrievalUtils.retrieve(model.getContext(), model.getTableHandler().prefixWithTableId() + TableConstants.TOTAL_ROWS);
        }

        if (totalRows instanceof Integer) {
            model.getTableHandler().setTotalRows((Integer)totalRows);
        } else {
            String message = "You need to specify the " + TableConstants.TOTAL_ROWS + " (as an Integer) to use the " + this.getClass().getName() + ".";
            throw new Exception(message);
        }

        return rows;
    }

    public Collection filterRows(TableModel model, Collection rows) throws Exception {
        return rows;
    }

    public Collection sortRows(TableModel model, Collection rows) throws Exception {
        return rows;
    }
}
