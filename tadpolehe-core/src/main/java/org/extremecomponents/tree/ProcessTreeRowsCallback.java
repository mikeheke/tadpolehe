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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.extremecomponents.table.callback.FilterPredicate;
import org.extremecomponents.table.callback.FilterRowsCallback;
import org.extremecomponents.table.callback.SortRowsCallback;
import org.extremecomponents.table.core.TableConstants;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.limit.Sort;

/**
 * @author phorn
 */
public final class ProcessTreeRowsCallback implements FilterRowsCallback, SortRowsCallback {
    public Collection filterRows(TableModel model, Collection rows) throws Exception {
        model.getTableHandler().getTable().addAttribute(TreeConstants.OPEN_NODES, getParameters(model.getRegistry().getParameterMap(), TreeConstants.OPEN, model.getTableHandler().prefixWithTableId()));

        boolean filtered = model.getLimit().isFiltered();
        boolean cleared = model.getLimit().isCleared();

        if (filtered && !cleared) {
            rows = filter(model, rows);
            rows = TreeModelUtils.findParents(model, rows);
            rows = TreeModelUtils.loadTreeStructure(model, rows);
            TreeModelUtils.addClosedChildren(model, rows);
        } else {
            rows = TreeModelUtils.loadTreeStructure(model, rows);
        }

        setFilteredCount(model, rows);

        return rows;
    }
    
    /**
     * Get a Map of specific parameters
     */
    private Map getParameters(Map parameterMap, String parameter, String prefixWithTableId) {
        Map subset = new HashMap();

        String find = prefixWithTableId + parameter;

        Set set = parameterMap.keySet();
        for (Iterator iter = set.iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            if (name.startsWith(find)) {
                String[] values = (String[]) parameterMap.get(name);
                subset.put(name, values);
            }
        }

        return subset;
    }

    private Collection filter(TableModel model, Collection rows) throws Exception {
        List results = new ArrayList();
        FilterPredicate filterPredicate = new FilterPredicate(model);
        CollectionUtils.select(rows, filterPredicate, results);

        // Add back the parents
        for (int i = 0; i < results.size(); i++) {
            Object bean = results.get(i);
            TreeModelUtils.findBeanParents(model, rows, results, bean);
        }

        return results;
    }

    private void setFilteredCount(TableModel model, Collection rows) {
        if (rows == null) {
            model.getTableHandler().getTable().addAttribute(TreeConstants.FILTERED_COUNT, "0");
            return;
        }

        model.getTableHandler().getTable().addAttribute(TreeConstants.FILTERED_COUNT, rows.size() + "");
    }

    public Collection sortRows(TableModel model, Collection rows) throws Exception {
        boolean sorted = model.getLimit().isSorted();

        if (!sorted) {
            return rows;
        }

        List parents = new ArrayList();
        for (Iterator iter = rows.iterator(); iter.hasNext();) {
            TreeNode node = (TreeNode) iter.next();
            if (node.getParent() == null)
                parents.add(node);
        }

        List output = new ArrayList();
        Sort sort = model.getLimit().getSort();
        String property = sort.getProperty();
        String sortOrder = sort.getSortOrder();
        subSort(parents, property, sortOrder); // First sort the parents
        recursiveSort(output, parents, property, sortOrder);

        output.retainAll(rows); // Only keep the original nodes
        rows.clear();
        rows.addAll(output);

        return rows;
    }

    private void recursiveSort(List output, List rows, String property, String sortOrder) {
        for (Iterator iter = rows.iterator(); iter.hasNext();) {
            TreeNode node = (TreeNode) iter.next();

            output.add(node);

            if (node.getChildren() != null && node.getChildren().size() > 0) {
                subSort(node.getChildren(), property, sortOrder);
                recursiveSort(output, node.getChildren(), property, sortOrder);
            }
        }
    }

    private void subSort(List rows, String property, String sortOrder) {
        if (sortOrder.equals(TableConstants.SORT_ASC)) {
            BeanComparator comparator = new BeanComparator(property, new NullComparator());
            Collections.sort(rows, comparator);
        } else if (sortOrder.equals(TableConstants.SORT_DESC)) {
            BeanComparator reversedNaturalOrderBeanComparator = new BeanComparator(property, new ReverseComparator(new NullComparator()));
            Collections.sort(rows, reversedNaturalOrderBeanComparator);
        }
    }
}
