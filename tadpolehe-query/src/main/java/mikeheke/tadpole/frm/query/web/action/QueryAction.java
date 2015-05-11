/**
 * 
 */
package mikeheke.tadpole.frm.query.web.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.base.web.action.BaseAction;
import mikeheke.tadpole.frm.query.entity.Query;
import mikeheke.tadpole.frm.query.service.QueryService;
import mikeheke.tadpole.frm.query.vo.QueryVo;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：查询Action类
 */
public class QueryAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7770808879042149945L;
	
	//查询业务Service
	private QueryService queryService;
	
	private QueryVo queryVo;
	
	/**
     * Declare：点击删除按钮
     *
	 * @param  void
     * @return String
     */
	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] queryIds = null;
		if(null != queryVo){
			queryIds = queryVo.getIds();
		}
		if(validateIds(queryIds)){

			List<Query> queries = this.getEntities(queryIds);
			ReturnMessage<Query> returnMessage = queryService.deleteQuerys(queries);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		
		return JSON;
	}
	
	protected void removeQueryFromContext(String name){
		ContextFactory.removeFromSession(name);
	}
	
	protected void setQueryToContext(String name, Query query){
		ContextFactory.setToSession(name, query);
	}
	
	protected Query getQueryFromContext(String name) {
		return (Query) ContextFactory.getFromSession(name);
	}
	
	/**
     * Declare：从上下文取得查询对象数组
     *
	 * @param  queryIds 查询ids
     * @return List<Query>
     */
	@Override
	protected List<Query> getEntities(String[] queryIds) {
		
		List<Query> queries = new ArrayList<Query>();
		for(String queryId: queryIds){
			Query query = new Query();
			query.setQueryId((queryId));
			queries.add(query);
		}
		
		return queries;
	}

	@JSON(serialize=false)
	public QueryService getQueryService() {
		return queryService;
	}

	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}

	public QueryVo getQueryVo() {
		return queryVo;
	}

	public void setQueryVo(QueryVo queryVo) {
		this.queryVo = queryVo;
	}

}
