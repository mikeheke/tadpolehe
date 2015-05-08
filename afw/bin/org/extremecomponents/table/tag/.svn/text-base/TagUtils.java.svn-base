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
package org.extremecomponents.table.tag;

import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.ExceptionUtils;

/**
 * @author Jeff Johnston
 */
public final class TagUtils {
    private static Log logger = LogFactory.getLog(ColumnTag.class);

    private TagUtils() {
    }

    public final static String evaluateExpressionAsString(String attributeName, String attribute, Tag tag, PageContext pageContext) {
        try {
            if (attribute != null) {
                attribute = (String) ExpressionEvaluatorManager.evaluate(attributeName, attribute, String.class, tag, pageContext);
            }
        } catch (JspException e) {
            logger.error("Could not resolve EL for [" + attributeName + "] - " + ExceptionUtils.formatStackTrace(e));
        }

        return attribute;
    }

    public final static Object evaluateExpressionAsObject(String attributeName, Object attribute, Tag tag, PageContext pageContext) {
        try {
            if (attribute != null) {
                attribute = ExpressionEvaluatorManager.evaluate(attributeName, attribute.toString(), Object.class, tag, pageContext);
            }
        } catch (JspException e) {
            logger.error("Could not resolve EL for [" + attributeName + "] - " + ExceptionUtils.formatStackTrace(e));
        }

        return attribute;
    }

    public final static Collection evaluateExpressionAsCollection(String attributeName, Object attribute, Tag tag, PageContext pageContext) {
        attribute = evaluateExpressionAsObject(attributeName, attribute, tag, pageContext);

        if (attribute == null || !(attribute instanceof Collection)) {
            if (logger.isDebugEnabled()) {
                logger.debug("The attribute [" + attributeName  + "] is null or not a Collection.");
            }
            return null;
        }

        return (Collection)attribute;
    }

    public final static Boolean evaluateExpressionAsBoolean(String attributeName, String attribute, Tag tag, PageContext pageContext) {
        attribute = evaluateExpressionAsString(attributeName, attribute, tag, pageContext);

        if (attribute == null) {
            return null;
        }

        return Boolean.valueOf(attribute);
    }

    public final static int evaluateExpressionAsInt(String attributeName, String attribute, Tag tag, PageContext pageContext) {
        attribute = evaluateExpressionAsString(attributeName, attribute, tag, pageContext);

        if (attribute == null) {
            return 0;
        }

        return new Integer(attribute).intValue();
    }

    public static TableModel getModel(Tag tag) {
        TableTag tableTag = (TableTag) TagSupport.findAncestorWithClass(tag, TableTag.class);
        return tableTag.getModel();
    }

    public static boolean isIteratingBody(Tag tag) {
        return getModel(tag).getCurrentRowBean() != null;
    }
}
