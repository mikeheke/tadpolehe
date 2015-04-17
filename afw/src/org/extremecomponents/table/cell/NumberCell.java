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
package org.extremecomponents.table.cell;

import java.util.Locale;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.util.ExtremeUtils;

/**
 * Visually represents a column that will be displayed with a custom number
 * format.
 * 
 * @author Jeff Johnston
 * @author Todd Fredrich - 03 Jan 2008 changed to extend FormattedCell.
 */
public class NumberCell
extends FormattedCell
{
	protected String formatColumnValue(TableModel model, Column column)
	{
        Locale locale = model.getLocale();
        String value = column.getPropertyValueAsString();
        return ExtremeUtils.formatNumber(column.getFormat(), value, locale);
	}
}
