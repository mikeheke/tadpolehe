/**
 * 
 */
package mikeheke.tadpole.frm.query.web.action;

import java.util.ArrayList;
import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.query.entity.Button;
import mikeheke.tadpole.frm.query.entity.DataCoding;
import mikeheke.tadpole.frm.query.entity.From;
import mikeheke.tadpole.frm.query.entity.GroupBy;
import mikeheke.tadpole.frm.query.entity.OrderBy;
import mikeheke.tadpole.frm.query.entity.Query;
import mikeheke.tadpole.frm.query.entity.Select;
import mikeheke.tadpole.frm.query.entity.Where;
import mikeheke.tadpole.frm.query.service.ConfigService;
import mikeheke.tadpole.frm.query.util.QueryConstant;

/**
 * 
 *
 * 2011-4-29 下午02:11:23
 */
public class ConfigAction extends QueryAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5122632761251748071L;
	
	//范围"查询"对象名
	public final static String QUERY_NAME = "_query_";
	
	private ConfigService configService;
	
	@Override
	public String init() {
		
		List<From> froms = this.getQueryFroms();
		this.setMessage(QueryConstant.FROMS, froms);
		Query query = this.getQueryFromContext();
		String sysTableFields = configService.getSysTableFields(query);
		this.setMessage(QueryConstant.SYS_TABLE_FIELDS, sysTableFields);
		
		return SUCCESS;
	}
	
	/**
     * Declare：点击保存按钮
     *
	 * @param  void
     * @return String
     */
	@Override
	public String save() {
		
		Query query = this.getQueryFromContext();
		
		ReturnMessage<Query> returnMessage = configService.saveQuery(
				query);
		
		this.setReturnMessage(returnMessage);
		
		this.removeQueryFromContext();

		return NONE;
	}
	
	/**
     * Declare：点击放弃保存按钮
     *
	 * @param  void
     * @return String
     */
	public String leave() {
		
		this.removeQueryFromContext();
		
		return NONE;
	}
	
	protected boolean validateAddRepeat(Object obj) {
		
		return false;
	}
	
	protected boolean validateMdfRepeat(Object obj) {
		
		return false;
	}
	
	protected boolean validateRepeat(Object objIn, Object obj) {
		
		return false;
	}
	
	public String findTableAlias(String tableName){
		
		if(DataValidater.isStrEmpty(tableName)){
			return QueryConstant.EMPTY_STR;
		}
		String tableAlias = QueryConstant.EMPTY_STR;
		List<From> froms = this.getQueryFroms();
		for(From from: froms){
			String tableName2 = from.getTableName();
			if(tableName2.equals(tableName)){
				tableAlias = from.getTableAlias();
				break;
			}
		}
		
		return tableAlias;
	}
	
	public List<From> getQueryFroms() {
		
		List<From> fromsRet = new ArrayList<From>();
		List<From> froms = this.getQueryFromContext().getFroms();
		for(From from: froms){
			if(QueryConstant.DEL_OPRT.equals(from.getOprtFlag())){
				continue;
			}
			fromsRet.add(from);
		}
		
		return fromsRet;
	}
	
	public List<Select> getQuerySelects() {
		
		List<Select> selectsRet = new ArrayList<Select>();
		List<Select> selects = this.getQueryFromContext().getSelects();
		for(Select select: selects){
			if(QueryConstant.DEL_OPRT.equals(select.getOprtFlag())){
				continue;
			}
			selectsRet.add(select);
		}
		
		return selectsRet;
	}	
	
	public List<Where> getQueryWheres() {
		
		List<Where> wheresRet = new ArrayList<Where>();
		List<Where> wheres = this.getQueryFromContext().getWheres();
		for(Where where: wheres){
			if(QueryConstant.DEL_OPRT.equals(where.getOprtFlag())){
				continue;
			}
			wheresRet.add(where);
		}
		
		return wheresRet;
	}	
	
	public List<OrderBy> getQueryOrderBys() {
		
		List<OrderBy> orderBysRet = new ArrayList<OrderBy>();
		List<OrderBy> orderBys = this.getQueryFromContext().getOrderBys();
		for(OrderBy orderBy: orderBys){
			if(QueryConstant.DEL_OPRT.equals(orderBy.getOprtFlag())){
				continue;
			}
			orderBysRet.add(orderBy);
		}
		
		return orderBysRet;
	}	
	
	public List<GroupBy> getQueryGroupBys() {
		
		List<GroupBy> groupBysRet = new ArrayList<GroupBy>();
		List<GroupBy> groupBys = this.getQueryFromContext().getGroupBys();
		for(GroupBy groupBy: groupBys){
			if(QueryConstant.DEL_OPRT.equals(groupBy.getOprtFlag())){
				continue;
			}
			groupBysRet.add(groupBy);
		}
		
		return groupBysRet;
	}	
	
	public List<Button> getQueryButtons() {
		
		List<Button> buttonsRet = new ArrayList<Button>();
		List<Button> buttons = this.getQueryFromContext().getButtons();
		for(Button button: buttons){
			if(QueryConstant.DEL_OPRT.equals(button.getOprtFlag())){
				continue;
			}
			buttonsRet.add(button);
		}
		
		return buttonsRet;
	}	
	
	public List<Application> getApplications(){
		
		return configService.getApplications();
	}
	
	public List<DataCoding> getDataCodings(){
		
		return configService.getDataCodings();
	}
	
	protected void removeQueryFromContext(){
		this.removeQueryFromContext(QUERY_NAME);
	}
	
	protected void setQueryToContext(Query query){
		this.removeQueryFromContext();
		this.setQueryToContext(QUERY_NAME, query);
	}
	
	protected Query getQueryFromContext() {
		Query query = this.getQueryFromContext(QUERY_NAME);
		return query;
	}
	
	public ConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}
	
}
