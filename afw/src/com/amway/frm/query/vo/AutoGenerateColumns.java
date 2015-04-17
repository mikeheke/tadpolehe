/**
 * 
 */
package com.amway.frm.query.vo;

import java.sql.SQLException;
import java.util.List;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;

import com.amway.frm.query.entity.Query;
import com.amway.frm.query.util.QueryConstant;
import com.amway.frm.query.web.action.ShowAction;


/**
 * Created by MyElipse
 * @author huangweijin
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：生成查询列接类
 */
public class AutoGenerateColumns implements 
	org.extremecomponents.table.core.AutoGenerateColumns {

	/**
	 * @param model tableModel
	 * @return void
	 * @throws SQLException
	 */
	public void addColumns(TableModel model) {
		
		Query query = (Query) model.getContext().getSessionAttribute(ShowAction.QUERY_SHOW_NAME);
		List<com.amway.frm.query.vo.Column> columns = query.getShowColumns();
		for(com.amway.frm.query.vo.Column col: columns){
			
			Column column = new Column(model);
			if(null != query.getIsColHeaFil()
					&& QueryConstant.NO.intValue() == query.getIsColHeaFil().intValue()){
				column.setFilterable(false);
			}else{
				column.setFilterable(col.getFilterable());
			}
			column.setSortable(col.getSortable());
            column.setProperty(col.getProperty());
            column.setCell(col.getCell());
            column.setTitle(col.getTitle());
            column.setWidth(col.getWidth());
            column.setViewsDenied(col.getViewsDenied());
            column.setFormat(col.getFormat());
            column.setParse(col.getFormat());
            
            column.setFilterCell(col.getFilterCell());
            column.setFilterOptions(col.getFilterOptions());
            
            model.getColumnHandler().addAutoGenerateColumn(column);
		}
	}
}
