/**
 * 
 */
package mikeheke.tadpole.frm.query.web.action;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import mikeheke.tadpole.frm.query.entity.Query;

import org.extremecomponents.table.limit.Limit;

/**
 * 
 *
 * 2011-6-15 下午03:42:14
 */
public class DefShowAction extends ExportShowAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1582107685126619592L;
	
	//范围"查询展示"对象
	public final static String QUERY_DEF_SHOW_NAME = "_query_def_show_";
	
	@Override
	public String execute() {
		
		String result = super.execute();
		
		//保存查询参数
		this.saveParamValues(this.getParamValues());
		
		return result;
	}
	
	private void saveParamValues(Map<String, String[]> parNamesValues){
		
		if(null == parNamesValues){
			return;
		}
		Set<Entry<String, String[]>> pnvSet = parNamesValues.entrySet();
		for(Entry<String, String[]> pnvEntry : pnvSet){
			String name = pnvEntry.getKey();
			String[] value = pnvEntry.getValue();
			if(value.length > 1){
				this.setMessage(name, value);
			}else{
				this.setMessage(name, value[0]);
			}
		}
	}

	@Override
	protected Query getQuery(Limit limit) {
		
		Query query = this.getQueryFromContext(); 
		if(null == query){
			query = new Query();
		}
		
		setPageAttributes(query, limit.getRowStart(), 
				limit.getRowEnd(), limit.getCurrentRowsDisplayed());
		
		return query;
	}

	@Override
	protected void setPageAttributes(Query query, int rowStart, int rowEnd,
			int pageNum) {
		query.getTable().setRowStart(rowStart);
		query.getTable().setRowEnd(rowEnd);
		query.getTable().setPageNum(pageNum);
	}

	@Override
	protected Query getQueryFromContext() {
		Query query = (Query) this.getQueryFromContext(QUERY_DEF_SHOW_NAME);
		
		return query;
	}

	@Override
	protected void setQueryToContext(Query query) {
		this.setQueryToContext(QUERY_DEF_SHOW_NAME, query);
	}
	
}
