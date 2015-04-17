/**
 * 
 */
package com.amway.frm.query.web.action;

import org.apache.struts2.ServletActionContext;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.query.entity.Query;
import com.amway.frm.query.service.ConfigService;
import com.amway.frm.query.util.QueryConstant;
import com.amway.frm.query.vo.QueryVo;

/**
 * @author huangweijin
 *
 * 2011-4-29 下午02:15:53
 */
public class QBaseAction extends ConfigAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1131129115390992846L;
	
	@Override
	public String init() {
		
		this.removeQueryFromContext();
		
		return INIT_SUCCESS;
	}

	/**
     * Declare：初始化页面(增加)
     *
	 * @param  void
     * @return String
     */
	@Override
	public String initAdd() {
		
		String result = SUCCESS;
		
		this.setOprt(AppConstant.ADD_OPRT);
		
		Query query = this.getQueryFromContext();
		if(null == query){
			query = new Query();
			query.setQueryId(System.currentTimeMillis());
			query.setOprtFlag(QueryConstant.ADD_OPRT);
		}
		//从session取得当前应用名称
		query.setApplication((Application) ServletActionContext.getRequest().getSession()
						.getAttribute(AppConstant.APPLICATION_NAME));
		this.setQueryToContext(query);
		
		return result;
	}

	/**
     * Declare：初始化页面(修改)
     *
	 * @param  void
     * @return String
     */
	@Override
	public String initModify() {

		String result = SUCCESS;
		
		this.setOprt(AppConstant.MDF_OPRT);
		
		String[] ids = this.getQueryVo().getIds();
		if(!validateIds0(ids)){
			return INPUT;
		}
		
		Query queryCxt = this.getQueryFromContext();
		if(queryCxt!=null 
				&& ids[0].equals(DataConverter.LongToString(queryCxt.getQueryId()))
				&& QueryConstant.MDF_OPRT.equals(queryCxt.getOprtFlag())){
			return result;
		}
		
		Query query = getEntity(ids[0]);
		
		Query queryRet = getConfigService().getQueryByQuery(query).getReturnObject();
		queryRet.setOprtFlag(QueryConstant.MDF_OPRT);
		
		this.setQueryToContext(queryRet);
		
		return result;
	}

	 /**
     * Declare：点击增加按钮
     *
	 * @param  void
     * @return String
     */
	@Override
	public String add(){
		
		if(!validateData()){
			return INPUT;
		}
		
		Query query = this.getEntity();
		query.setQueryId(System.currentTimeMillis());
		query.setOprtFlag(QueryConstant.ADD_OPRT);	//增加操作
		
		this.setQueryToContext(query);
		
		return SUCCESS;
	}
	
	/**
     * Declare：点击修改按钮
     *
	 * @param  void
     * @return String
     */
	@Override
	public String modify() {
		
		String queryId = this.getQueryVo().getId();
		if(!validateId(queryId)){
			return INPUT;
		}
		
		if(!validateData()){
			return INPUT;
		}
		
		Query query = this.getEntity();
		query.setQueryId(DataConverter.stringToLong(queryId));
		query.setOprtFlag(QueryConstant.MDF_OPRT);	//修改操作
		
		this.setQueryToContext(query);
	
		return SUCCESS;

	}
	
	public String checkDsJndi(){
		
		String dsJndi = this.getQueryVo().getDsJndi();
		boolean result = getConfigService().checkDsJndi(dsJndi);
		this.setJsonValue(result+QueryConstant.EMPTY_STR);
		
		return JSON;
	}
	
	/**
     * Declare：从上下文取得查询对象
     *
	 * @param  queryId 查询id
     * @return Query
     */
	@Override
	protected Query getEntity(String queryId) {
		
		Query query = new Query();
		query.setQueryId(DataConverter.stringToLong(queryId));

		return query;
		
	}

	/**
     * Declare：从上下文取得查询对象
     *
	 * @param  void
     * @return Query
     */
	@Override
	protected Query getEntity() {
		
		Query query = null;
		query = this.getQueryFromContext();
		if(null == query){
			query = new Query();
		}
		QueryVo queryVo = this.getQueryVo();
		query.setQueryCode(queryVo.getQueryCode());
		query.setQueryName(queryVo.getQueryName());
		Application app = new Application();
		app.setApplicationId(DataConverter.stringToLong(queryVo.getApplicationId()));
		app = (Application) getConfigService().querySingle(app);
		query.setApplication(app);
		query.setDsJndi(queryVo.getDsJndi());
		query.setIsColHeaFil(DataConverter.stringToInteger(queryVo.getIsColHeaFil()));
		query.setExternalJs(queryVo.getExternalJs());
		query.setIsDefaultQry(DataConverter.stringToInteger(queryVo.getIsDefaultQry()));
		query.setIsRefresh(DataConverter.stringToInteger(queryVo.getIsRefresh()));
		query.setUseState(DataConverter.stringToInteger(queryVo.getUseState()));
		query.setRemark(queryVo.getRemark());
		
		return query;
	}

	/**
     * Declare：验证对象属性
     *
	 * @param  void
     * @return boolean
     */
	@Override
	protected boolean validateData() {
		
		boolean result = true;
		
		QueryVo queryVo = this.getQueryVo();
		
		String queryCode = queryVo.getQueryCode();
		if(DataValidater.isStrEmpty(queryCode)){
			this.setMessage(QueryConstant.QUERY_CODE_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}else if(DataValidater.isStrLenGt(queryCode, AppConstant._128)){
			this.setMessage(QueryConstant.QUERY_CODE_KEY, QueryConstant.QUERY_CODE_MSG);
			result = false;
		}
		
		String queryName = queryVo.getQueryName();
		if(DataValidater.isStrEmpty(queryName)){
			this.setMessage(QueryConstant.QUERY_NAME_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}else if(DataValidater.isStrLenGt(queryName, AppConstant._256)){
			this.setMessage(QueryConstant.QUERY_NAME_KEY, QueryConstant.QUERY_NAME_MSG);
			result = false;
		}
		
		if(DataValidater.isFalse(DataValidater.isStrLong(queryVo.getApplicationId()))){
			this.setMessage(QueryConstant.APPLICATION_ID_KEY, getText(QueryConstant.YES_LONG_KEY));
			result = false;
		}
		
		if(DataValidater.isStrEmpty(queryVo.getDsJndi())){
			this.setMessage(QueryConstant.DS_JNDI_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}
		
		if(DataValidater.isFalse(DataValidater.isStrInteger(queryVo.getIsColHeaFil()))){
			this.setMessage(QueryConstant.IS_COL_HEA_FIL_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		
		if(DataValidater.isStrLenGt(queryVo.getDsJndi(), AppConstant._256)){
			this.setMessage(QueryConstant.DS_JNDI_KEY, QueryConstant.DS_JNDI_MSG);
			result = false;
		}
		
		if(DataValidater.isFalse(DataValidater.isStrInteger(queryVo.getIsDefaultQry()))){
			this.setMessage(QueryConstant.IS_DEFAULT_QRY_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		
		if(DataValidater.isFalse(DataValidater.isStrInteger(queryVo.getIsRefresh()))){
			this.setMessage(QueryConstant.IS_REFRESH_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		if(DataValidater.isFalse(DataValidater.isStrInteger(queryVo.getUseState()))){
			this.setMessage(QueryConstant.USE_STATE_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		
		if(DataValidater.isStrLenGt(queryVo.getRemark(), AppConstant._2048)){
			this.setMessage(QueryConstant.REMARK_KEY, QueryConstant.REMARK_MSG);
			result = false;
		}
		
		return result;
	}

	@Override
	public String getJsonValue() {
		return super.getJsonValue();
	}
	
	/**
	 * 打开复制Query页面
	 * @return
	 */
	public String copyQueryInput() {
		
		String[] ids = getIds();
		Query query = new Query();
		query.setQueryId(DataConverter.stringToLong(ids[0]));
		Query queryRet = getConfigService().getQueryByQuery(query).getReturnObject();
		
		//设置值到request中
		setMessage("oldQueryCode", queryRet.getQueryCode());
		
		return "copyQueryInput";
	}
	
	/**
	 * 复制Query
	 * @return
	 */
	public String copyQuery() {
		
		String oldQueryCode = ContextFactory.getParamFromRequest("oldQueryCode");
		String newQueryCode = ContextFactory.getParamFromRequest("newQueryCode");
		
		ReturnMessage<Query> returnMessage = getConfigService().copyQuery(oldQueryCode, newQueryCode);
		setReturnMessage(returnMessage);
		
		
		setMessage("oldQueryCode", oldQueryCode);
		setMessage("newQueryCode", newQueryCode);
		
		return "copyQuerySuccess";
	}
	
}
