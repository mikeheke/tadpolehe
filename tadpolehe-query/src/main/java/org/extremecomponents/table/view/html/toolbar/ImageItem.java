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
package org.extremecomponents.table.view.html.toolbar;

import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.HtmlBuilder;

/**
 * @author Jeff Johnston
 */
public class ImageItem extends AbstractItem implements ToolbarItem {
    String image;
    String disabledImage;
    String alt;

    public String getDisabledImage() {
        return disabledImage;
    }

    public void setDisabledImage(String disabledImage) {
        this.disabledImage = disabledImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImage() {

    }

    public void disabled(HtmlBuilder html) {
        html.img().src(getDisabledImage()).style(getStyle()).alt(getAlt()).xclose();
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void enabled(HtmlBuilder html, TableModel model) {
        html.a();
        html.quote();
        html.append(getAction());
        html.quote().close();

        boolean showTooltips = model.getTableHandler().getTable().isShowTooltips();
        if (showTooltips) {
            html.img().src(getImage()).style(getStyle()).title(getTooltip()).onmouseover(getOnmouseover()).onmouseout(getOnmouseout()).alt(getAlt()).xclose();
        } else {
            html.img().src(getImage()).style(getStyle()).onmouseover(getOnmouseover()).onmouseout(getOnmouseout()).alt(getAlt()).xclose();
        }

        html.aEnd();
    }
}
