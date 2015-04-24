/**
 * 
 */
package com.amway.frm.query.web.action;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;

import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;
import com.amway.frm.query.entity.Query;
import com.amway.frm.query.exception.QueryInfoException;
import com.amway.frm.query.service.ShowService;
import com.amway.frm.query.util.QueryConstant;
import com.amway.frm.query.vo.Row;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：查询展示Action类
 */
public class ShowAction extends QueryAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7403391402791826326L;
	
	private static LogService logger = LogFactory.getLogger(ShowAction.class);
	
	//范围"查询展示"对象
	public final static String QUERY_SHOW_NAME = "_query_show_";
	
	//查询总记录数
	public final static String TOTAL_ROWS_NAME = "totalRows";
	
	//默认每页记录数
	public final static Integer TABLE_PAGE_SIZE = 15;
	
	//业务Service
	private ShowService showService;
	
	//查询代码
	private String queryCode;

	public String init(){
		return SUCCESS;
	}
	
	/**
     * Declare：前后台交互方法
     *
	 * @param  void
     * @return String
     */
	public String execute() {
		
		// 取得请求交互Limit(过滤和排序)
		Limit limit = getLimit(ServletActionContext.getRequest()); 
		Query query = this.getQuery(limit);
		if (null == query) {
			return ERROR;
		}
		
		// 查询数据
		ReturnMessage<Row> returnMessage = null;
		if(null != query.getIsDefaultQry()
				&& QueryConstant.NO.intValue() == query.getIsDefaultQry().intValue()){	//是否查询
			returnMessage = new ReturnMessage<Row>();
		}else{
			returnMessage = showService.getQueryShowDatas(query,
				limit, getParamValues(), getUserInfo()); 	
		}

		// 设置返回值
		String result = setReturnMessage(returnMessage); 
		
		this.setQueryToContext(query);
		
		// 设置结果集行数
		setTotalRows(query.getTable().getTotalRow());
		
		final String key = "execute";
		final String msg = "通用查询，查询编号";
		logger.writeOperLog(key, msg + query.getQueryCode());

		return result;
	}
	
	/**
     * Declare：设置返回记录数
     *
	 * @param  totalRow 总行数
     * @return void
     */
	protected void setTotalRows(int totalRow){
		
		setMessage(TOTAL_ROWS_NAME, totalRow);
		
	}
	
	protected Query getQuery(Limit limit){
		
		Query query = this.getQueryFromContext(); 
		if(null == query || !queryCode.equals(query.getQueryCode())){
			query = this.getQueryByContext();
		}
		
		if (null != query 
				&& query.getUseState().intValue() != QueryConstant.YES.intValue()) {
			throw new QueryInfoException(AppConstant.RECORD_STOP);
		}
		
		if(null != query){
			setPageAttributes(query, limit.getRowStart(), 
					limit.getRowEnd(), limit.getCurrentRowsDisplayed());
		}
		
		if(null != query){
			String action = limit.getFilterSet().getAction();
			if(null != action){
				query.setIsDefaultQry(QueryConstant.YES);	//默认查询)
			}
		}
		
		return query;
	}
	
	/**
     * Declare：从上下文取得查询对象,并放入Session
     *
	 * @param  void
     * @return Query
     */
	protected Query getQueryByContext() {
		
		Query queryCon = new Query();
		queryCon.setQueryCode(queryCode);
		//queryCon.setUseState(AppConstant.START);
		//取得"查询"，包含有Query,Selects,Froms,Wheres,OrderByS,GroupBys
		Query query = showService.getQueryByQuery(queryCon).getReturnObject();
		
		return query;
	}
	
	/**
     * Declare：从Session取得查询对象
     *
	 * @param  void
     * @return Query
     */
	protected Query getQueryFromContext(){
		
		Query query = (Query) this.getQueryFromContext(QUERY_SHOW_NAME);
		
		return query;
	}
	
	protected void setPageAttributes(Query query, int rowStart, int rowEnd, int 
			pageNum){
		
		query.getTable().setRowStart(rowStart);
		query.getTable().setRowEnd(rowEnd);
		query.getTable().setPageNum(pageNum);
		
	}
	
	protected void removeQueryFromContext(){
		this.removeQueryFromContext(QUERY_SHOW_NAME);
	}
	
	protected void setQueryToContext(Query query){
		this.removeQueryFromContext();
		this.setQueryToContext(QUERY_SHOW_NAME, query);
	}
	
	/**
     * Declare：从request取得Limit对象
     *
	 * @param  request
     * @return Limit
     */
	protected Limit getLimit(HttpServletRequest request) {
		
		Context context = new HttpServletRequestContext(request);
		LimitFactory limitFactory = new TableLimitFactory(context);
		Limit limit = new TableLimit(limitFactory);
		limit.setRowAttributes(Integer.MAX_VALUE, ShowAction.TABLE_PAGE_SIZE);

		return limit;
	}
	
	protected Map<String, String[]> getParamValues(){
		Map<String, String[]> parNamesValues = ServletActionContext
				.getRequest().getParameterMap(); 	
		
		UserProfile userProfile = (UserProfile)ServletActionContext.getRequest().getSession().
			getAttribute(AppConstant.USER_NAME);
		
		return parNamesValues;
	}
	
	/**
     * Declare：注入业务ShowService
     *
	 * @param  showService
     * @return void
     */
	public void setShowService(ShowService showService) {
		this.showService = showService;
	}

	public ShowService getShowService() {
		return showService;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

}
