/**
 * 
 */
package com.amway.frm.query.override;

import java.util.List;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.bean.Export;
import org.extremecomponents.table.core.TableModel;
import org.extremecomponents.table.interceptor.ExportInterceptor;

import com.amway.frm.base.util.DataValidater;
import com.amway.frm.query.entity.Query;
import com.amway.frm.query.entity.Select;
import com.amway.frm.query.util.QueryConstant;
import com.amway.frm.query.web.action.DefShowAction;

/**
 * @author huangweijin
 *
 * 2011-6-18 下午04:47:38
 */
public class DefExportInterceptor implements ExportInterceptor{

	/* (non-Javadoc)
	 * @see org.extremecomponents.table.interceptor.ExportInterceptor#addExportAttributes(org.extremecomponents.table.core.TableModel, org.extremecomponents.table.bean.Export)
	 */
	@Override
	public void addExportAttributes(TableModel tableModel, Export export) {
		Query query = (Query) tableModel.getContext().getSessionAttribute(DefShowAction.QUERY_DEF_SHOW_NAME);
		if(DataValidater.isCollectionEmpty(query.getSelects())){
			List columns = tableModel.getColumnHandler().getColumns();
			for(Object colObj: columns){
				Column column = (Column)colObj;
				Select select = new Select();
				select.setDes(column.getTitle());
				select.setTableName(QueryConstant.EMPTY_STR);
				select.setFieldName(column.getProperty());
				select.setFieldAlias(QueryConstant.EMPTY_STR);
				query.getSelects().add(select);
				query.setQueryName(tableModel.getTableHandler().getTable().getTitle());
			}
			tableModel.getContext().setSessionAttribute(DefShowAction.QUERY_DEF_SHOW_NAME, query);
		}
	}

	
}
