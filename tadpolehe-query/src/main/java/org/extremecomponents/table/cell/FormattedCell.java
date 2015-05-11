/**
 * 
 */
package org.extremecomponents.table.cell;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;

/**
 * A FormattedCell is a cell of a specific type that is formatted by eXtremeTable (e.g. Date, Number).
 * FormattedCell, however, allows for the case where the user puts content between the column begin
 * and end elements.  Thus, if the column value (between the begin/end elements) contains a String,
 * it is returned.  Otherwise, the property value is formatted and returned.
 * 
 * @author Todd Fredrich
 * @since 1.0.4
 * @see DateCell, NumberCell
 */
public abstract class FormattedCell
extends AbstractCell
{
	/* (non-Javadoc)
	 * @see org.extremecomponents.table.cell.AbstractCell#getCellValue(org.extremecomponents.table.core.TableModel, org.extremecomponents.table.bean.Column)
	 */
	protected String getCellValue(TableModel model, Column column)
	{
        Object value = column.getValue();

        if (value == null) return "";

        String result = null;

// hwj delete 20110616
//        if (value instanceof String)
//        {
//        	result = value.toString();
//        }
//        else
//        {
        	result = formatColumnValue(model, column);
//        }

        return result;
	}

	protected abstract String formatColumnValue(TableModel model, Column column);
}
