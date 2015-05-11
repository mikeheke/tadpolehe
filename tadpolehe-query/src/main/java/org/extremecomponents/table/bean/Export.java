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
package org.extremecomponents.table.bean;

import org.extremecomponents.table.core.TableModel;

/**
 * @author jeff johnston
 */
public class Export extends Attributes {
    private TableModel model;
    
    private String encoding;
    private String fileName;
    private String imageName;
    private String interceptor;
    private String view;
    private String viewResolver;
    private String text;
    private String tooltip;
    private String action;	//hwj add 20100618

    public Export(TableModel model) {
        this.model = model;
    }

    public void defaults() {
        this.encoding = ExportDefaults.getEncoding(model, encoding);
        this.text = ExportDefaults.getText(model, text);
        this.tooltip = ExportDefaults.getTooltip(model, tooltip);
        this.viewResolver = ExportDefaults.getviewResolver(model, viewResolver);
    }

    public String getEncoding(){
        return encoding;
    }
    
    public void setEncoding(String encoding){
        this.encoding = encoding;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(String interceptor) {
        this.interceptor = interceptor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getViewResolver() {
        return viewResolver;
    }

    public void setViewResolver(String viewResolver) {
        this.viewResolver = viewResolver;
    }

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
    
}
