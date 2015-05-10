package mikeheke.tadpole.frm.query.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.logging.service.LogService;
import mikeheke.tadpole.frm.logging.util.LogFactory;
import mikeheke.tadpole.frm.query.entity.Query;
import mikeheke.tadpole.frm.query.exception.QueryInfoException;
import mikeheke.tadpole.frm.query.service.ShowService;
import mikeheke.tadpole.frm.query.util.QueryConstant;
import mikeheke.tadpole.frm.query.vo.Row;

import org.apache.struts2.ServletActionContext;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;

/**
 * @author Mike.He
 * Date: 2013-1-16
 * Declare：查询带条件的分两页展示Action类
 */
public class ShowTwoPageAction extends QueryAction {
	
	private static final long serialVersionUID = -3628468491670414560L;
	
	private static LogService logger = LogFactory.getLogger(ShowTwoPageAction.class);
	
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
	
	//condition:显示查询条件页面；result:显示查询结果页面
	private String showType;

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
		
		String result = "";
		
		// 取得请求交互Limit(过滤和排序)
		Limit limit = getLimit(ServletActionContext.getRequest()); 
		Query query = this.getQuery(limit);
		if (null == query) {
			return ERROR;
		}
		
		// 查询数据
		ReturnMessage<Row> returnMessage = null;
		
		/* old
		if(null != query.getIsDefaultQry()
				&& QueryConstant.NO.intValue() == query.getIsDefaultQry().intValue()){	//是否查询
			returnMessage = new ReturnMessage<Row>();
		}else{
			returnMessage = showService.getQueryShowDatas(query,
				limit, getParamValues(), getUserInfo()); 	
		}
		*/
		
		if ("result".equals(showType)) {//查询数据
			result = "showResult";
			
			returnMessage = showService.getQueryShowDatas(query,limit, getParamValues(), getUserInfo()); 	

			//记录日志
			final String key = "execute";
			final String msg = "通用查询，查询编号";
			logger.writeOperLog(key, msg + query.getQueryCode());	
			
		} else {//显示查询条件	
			returnMessage = new ReturnMessage<Row>();
			result = "showCondition";
		}
		
		

		// 设置返回值
		//String result = setReturnMessage(returnMessage);
		setReturnMessage(returnMessage); 
		
		this.setQueryToContext(query);
		
		// 设置结果集行数
		setTotalRows(query.getTable().getTotalRow());
		
		/*
		final String key = "execute";
		final String msg = "通用查询，查询编号";
		logger.writeOperLog(key, msg + query.getQueryCode());
		*/

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

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

}
