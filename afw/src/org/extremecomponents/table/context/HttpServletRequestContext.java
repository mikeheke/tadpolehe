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
package org.extremecomponents.table.context;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.extremecomponents.table.core.TableModelUtils;

/**
 * @author Jeff Johnston
 */
public class HttpServletRequestContext implements Context {
    private HttpServletRequest request;
    private Map parameterMap;

    public HttpServletRequestContext(HttpServletRequest request) {
        this.request = request;
    }
    
    /**
     * Send in and use a custom map of parameters along with the normal
     * HttpServletRequest.
     * 
     * @param request An instance of a HttpServletRequest.
     * @param parameterMap A map of parameters to use. 
     * 
     * tODO: we may need this... Note: the parameter values need to be an Array String[]
     */
    public HttpServletRequestContext(HttpServletRequest request, Map parameterMap) {
        this(request);
        this.parameterMap = parameterMap;
    }
    
    public Object getApplicationInitParameter(String name) {
        return request.getSession().getServletContext().getInitParameter(name);
    }

    public Object getApplicationAttribute(String name) {
        return request.getSession().getServletContext().getAttribute(name);
    }

    public void setApplicationAttribute(String name, Object value) {
        request.getSession().getServletContext().setAttribute(name, value);
    }

    public void removeApplicationAttribute(String name) {
        request.getSession().getServletContext().removeAttribute(name);
    }

    public Object getPageAttribute(String name) {
        return request.getAttribute(name);
    }

    public void setPageAttribute(String name, Object value) {
        request.setAttribute(name, value);
    }

    public void removePageAttribute(String name) {
        request.removeAttribute(name);
    }

    public String getParameter(String name) {
        if (parameterMap != null) {
            String[] values = TableModelUtils.getValueAsArray(parameterMap.get(name));
            if (values != null && values.length > 0) {
                return values[0];
            }
        }
        
        return request.getParameter(name);
    }

    public Map getParameterMap() {
        if (parameterMap != null) {
            return parameterMap;
        }

        return request.getParameterMap();
    }

    public Object getRequestAttribute(String name) {
        return request.getAttribute(name);
    }

    public void setRequestAttribute(String name, Object value) {
        request.setAttribute(name, value);
    }

    public void removeRequestAttribute(String name) {
        request.removeAttribute(name);
    }

    public Object getSessionAttribute(String name) {
        return request.getSession().getAttribute(name);
    }

    public void setSessionAttribute(String name, Object value) {
        request.getSession().setAttribute(name, value);
    }

    public void removeSessionAttribute(String name) {
        request.getSession().removeAttribute(name);
    }

    public Writer getWriter() {
        return new StringWriter();
    }

    public Locale getLocale() {
        return request.getLocale();
    }

    public String getContextPath() {
        return request.getContextPath();
    }

    public Object getContextObject() {
        return request;
    }
}
