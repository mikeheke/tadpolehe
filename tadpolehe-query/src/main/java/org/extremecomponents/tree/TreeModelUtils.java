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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.extremecomponents.table.core.TableModel;

/**
 * org.extremecomponents.tree.model.BaseModelUtils.java -
 * 
 * @author phorn
 */
public final class TreeModelUtils {
    private static Log logger = LogFactory.getLog(TreeModelUtils.class);

    private TreeModelUtils() {
    }

    /**
     * Recursively loads the tree structure into the treeList attribute and sets
     * the treeList as the collection to use.
     */
    public static List loadTreeStructure(TableModel model, Collection inputList) throws Exception {
        List result = new ArrayList();

        List searchList = new ArrayList();
        searchList.addAll(inputList);

        for (Iterator iter = searchList.iterator(); iter.hasNext();) {
            Object bean = iter.next();

            String parentAttribute = model.getTableHandler().getTable().getAttributeAsString(TreeConstants.PARENT_ATTRIBUTE);
            Object parentId = BeanUtils.getProperty(bean, parentAttribute);
            if (parentId == null || StringUtils.isBlank(parentId + "")) {
                // Load up the top level parents
                TreeNode node = new TreeNode(bean, getBeanId(model, bean), 0);
                result.add(node);
                iter.remove();
                loadChildren(model, result, searchList, node, 0);
            }
        }

        return result;
    }

    public static void loadChildren(TableModel model, List displayList, List searchList, TreeNode node, int currentDepth) throws Exception {
        currentDepth++;

        List subList = new ArrayList();
        subList.addAll(searchList);

        Object id = node.getIdentifier();
        String key = getNodeKey(model, id);

        Map openNodes = (Map) model.getTableHandler().getTable().getAttribute(TreeConstants.OPEN_NODES);
        if (openNodes.get(key) != null) {
            node.setOpen(true);
        } else {
            node.setOpen(false);
        }

        for (Iterator iter = subList.iterator(); iter.hasNext();) {
            Object bean = iter.next();

            if (nodeIsBeanParent(model, node, bean)) {
                TreeNode childNode = new TreeNode(bean, getBeanId(model, bean), currentDepth);
                node.addChild(childNode);
                childNode.setParent(node);
                iter.remove();

                if (isOpen(model, node, true)) {
                    displayList.add(childNode);
                }

                loadChildren(model, displayList, subList, childNode, currentDepth); // Recurse
            }
        }
    }

    /**
     * Used by the filter function to add back the filtered out parents.
     */
    public static Collection findParents(TableModel model, Collection searchList) throws Exception {
        logger.debug("TableModel.findParents()");

        List result = new ArrayList();

        for (Iterator iter = searchList.iterator(); iter.hasNext();) {
            Object bean = iter.next();

            if (!result.contains(bean)) {
                result.add(bean);
            }

            findBeanParents(model, searchList, result, bean);
        }

        return result;
    }

    /**
     * Recursive call used by the filter function to add back the filtered out
     * parents.
     */
    public static void findBeanParents(TableModel model, Collection searchList, Collection parents, Object bean) throws Exception {
        Object parent = null;

        String parentAttribute = model.getTableHandler().getTable().getAttributeAsString(TreeConstants.PARENT_ATTRIBUTE);

        if (bean instanceof Map) {
            parent = ((Map) bean).get(parentAttribute);
        } else {
            parent = PropertyUtils.getProperty(bean, parentAttribute);
        }

        if (parent == null) {
            return;
        }

        Object parentBean = findByIdentifierOrReference(model, (List) searchList, parent);

        if (parentBean == null) {
            // Not found - we shouldn't ever get here
            return;
        }

        if (!parents.contains(parentBean)) {
            // Already added so do nothing
            parents.add(parentBean);
        }

        findBeanParents(model, searchList, parents, parentBean);
    }

    public static Object findByIdentifierOrReference(TableModel model, List searchList, Object bean) {
        if (searchList.contains(bean)) {
            return bean; // Not the identifier but the actual bean reference
        }

        String identifier = model.getTableHandler().getTable().getAttributeAsString(TreeConstants.IDENTIFIER);

        for (int i = 0; i < searchList.size(); i++) {
            Object row = searchList.get(i);
            try {
                if (bean.equals(BeanUtils.getProperty(row, identifier))) {
                    return row;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Used by the filter function to add back the filtered out children.
     */
    public static void addClosedChildren(TableModel model, Collection displayList) throws Exception {
        logger.debug("TableModel.addClosedChildren()");

        List parents = new ArrayList();
        parents.addAll(displayList);

        for (Iterator iter = parents.iterator(); iter.hasNext();) {
            TreeNode node = (TreeNode) iter.next();

            if ((node.getChildren() == null) || (node.getChildren().size() == 0)) {
                addChildren(model, displayList, node);
            }
        }
    }

    /**
     * Recursive call used by the filter function to add back the filtered out
     * children but keeps them collapsed unless they have been openned manually
     * be the open parameter.
     */
    public static void addChildren(TableModel model, Collection displayList, TreeNode node) throws Exception {
        for (Iterator iter = model.getCollectionOfBeans().iterator(); iter.hasNext();) {
            Object bean = iter.next();

            if (nodeIsBeanParent(model, node, bean)) {
                TreeNode childNode = new TreeNode(bean, getBeanId(model, bean), node.getDepth() + 1);
                node.addChild(childNode);

                Object id = node.getIdentifier();
                Map openNodes = (Map) model.getTableHandler().getTable().getAttribute(TreeConstants.OPEN_NODES);
                String nodeKey = getNodeKey(model, id);
                Object openParam = openNodes.get(nodeKey);
                
                if (openParam != null && openParam instanceof String[]) {
                    openParam = ((String[])openParam)[0];
                }
                
                if (isOpen(model, node, false) || "true".equals(openParam)) {
                    node.setOpen(true);

                    int parentPosition = ((List) displayList).indexOf(node);
                    ((List) displayList).add(parentPosition + 1, childNode);
                }

                addChildren(model, displayList, childNode);
            }
        }
    }

    public static boolean nodeIsBeanParent(TableModel model, TreeNode node, Object bean) throws Exception {
        Object parent = null;

        String parentAttribute = model.getTableHandler().getTable().getAttributeAsString(TreeConstants.PARENT_ATTRIBUTE);

        if (bean instanceof Map) {
            parent = ((Map) bean).get(parentAttribute);
        } else {
            parent = PropertyUtils.getProperty(bean, parentAttribute);
        }

        if (parent != null)
            logger.debug("parent instanceof " + parent.getClass().getName());

        if (parent == null || StringUtils.isBlank(parent + "")) {
            return false;
        }

        Object nodeId = node.getIdentifier();
        Object parentId = getBeanId(model, parent);

        if (node.getBean().equals(parent))
            return true;
        if (nodeId.equals(parentId))
            return true;

        return false;
    }

    public static Object getBeanId(TableModel model, Object bean) throws Exception {
        try {
            String identifier = model.getTableHandler().getTable().getAttributeAsString(TreeConstants.IDENTIFIER);

            if (bean instanceof Map) {
                return ((Map) bean).get(identifier);
            }

            return PropertyUtils.getProperty(bean, identifier);

        } catch (NoSuchMethodException e) {

            return bean;
        }
    }

    /**
     * Find out if the node and all parents are open.
     */
    public static boolean isOpen(TableModel model, TreeNode node, boolean filterControlled) {
        boolean filtered = model.getLimit().isFiltered();
        boolean cleared = model.getLimit().isCleared();

        if (filterControlled && filtered && !cleared) {
            node.setOpen(true);

            return true;
        }

        if (!node.isOpen()) {
            return false;
        }

        if (node.getParent() == null) {
            return true;
        }

        return isOpen(model, node.getParent(), filterControlled);
    }

    /**
     * @return Returns the parameter key used to indicate this node is open.
     */
    public static String getNodeKey(TableModel model, Object id) {
        return model.getTableHandler().prefixWithTableId() + TreeConstants.OPEN + id;
    }
}
