/**
 * 
 */
package mikeheke.tadpole.frm.query.vo;

import java.sql.SQLException;
import java.util.List;

import mikeheke.tadpole.frm.query.entity.Query;
import mikeheke.tadpole.frm.query.util.QueryConstant;
import mikeheke.tadpole.frm.query.web.action.ShowAction;

import org.extremecomponents.table.bean.Column;
import org.extremecomponents.table.core.TableModel;


/**
 * 
 * 
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
		List<mikeheke.tadpole.frm.query.vo.Column> columns = query.getShowColumns();
		for(mikeheke.tadpole.frm.query.vo.Column col: columns){
			
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
