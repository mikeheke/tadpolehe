/**
 * 
 */
package com.amway.frm.query.web.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

//import org.apache.commons.lang.StringUtils;

import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.bds.service.BaseDataSourceService;
import com.amway.frm.bds.util.BdsConstant;
import com.amway.frm.bds.vo.BaseDataSourceVo;
import com.amway.frm.query.entity.From;
import com.amway.frm.query.entity.Query;
import com.amway.frm.query.util.QueryConstant;
import com.amway.frm.query.vo.FromVo;



/**
 * Created by MyElipse
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：来源Action类
 */
public class FromAction extends ConfigAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5664232631030785214L;

	private FromVo fromVo;
	
	// 基础数据服务业务逻辑接口
	private BaseDataSourceService baseDataSourceService;

	@Override
	public String init() {
		
		Query query = this.getQueryFromContext();
		String sysTables = getConfigService().getSysTables(query);
		this.setMessage(QueryConstant.SYS_TABLES, sysTables);
		
		return SUCCESS;
	}
	
	 /**
     * Declare：点击增加按钮
     *
	 * @param  void
     * @return String
     */
	@Override
	public String add() {

		this.init();
		
		if(!validateData()){
			return INPUT;
		}
		
		From from = this.getEntity();
		from.setFromId(System.currentTimeMillis()+"");
		from.setOprtFlag(QueryConstant.ADD_OPRT);		//增加操作
		
		if(validateAddRepeat(from)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		
		query.getFroms().add(from);
		
		this.setQueryToContext(query);
		
		this.setFromVo(null);
		
		return SUCCESS;
	}

	/**
     * Declare：点击删除按钮
     *
	 * @param  void
     * @return String
     */
	@Override
	public String delete() {
		
		this.init();
		
		String[]  fromIds = fromVo.getIds();
		if(!validateIds(fromIds)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		
		for(String fromId: fromIds){
			
			String[] fromIdArr = fromId.split(QueryConstant.OPRT_SEP);
			From from = new From();
			from.setFromId((fromIdArr[0]));
			from.setOprtFlag(fromIdArr[1]);
			query.getFroms().remove(from);
			if(!QueryConstant.ADD_OPRT.equals(from.getOprtFlag())){
				from.setOprtFlag(QueryConstant.DEL_OPRT);
				query.getFroms().add(from);
			}
		}
			
		this.setQueryToContext(query);
		
		this.setFromVo(null);
		
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
		
		this.init();
		
		String[] fromIds = fromVo.getIds();
		if(!validateIds(fromIds)){
			return INPUT;
		}
		
		if(!validateData()){
			return INPUT;
		}
		
		From from = this.getEntity();
		
		String[] fromIdArr = fromIds[0].split(QueryConstant.OPRT_SEP);
		from.setFromId((fromIdArr[0]));
		from.setOprtFlag(fromIdArr[1]);
		
		if(validateMdfRepeat(from)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		query.getFroms().remove(from);
		
		if(QueryConstant.QRY_OPRT.equals(from.getOprtFlag())){
			from.setOprtFlag(QueryConstant.MDF_OPRT);
		}
		
		query.getFroms().add(from);
		
		this.setQueryToContext(query);
		
		this.setFromVo(null);
		
		return SUCCESS;
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
		
		if(DataValidater.isStrEmpty(fromVo.getTableName())){
			this.setMessage(QueryConstant.TABLE_NAME_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}
		String tableAlias = fromVo.getTableAlias();
		final Integer tableAliasLen = 32;
		if(DataValidater.isStrEmpty(tableAlias)){
			this.setMessage(QueryConstant.TABLE_ALIAS_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}else if(DataValidater.isStrLenGt(tableAlias, tableAliasLen)){
			this.setMessage(QueryConstant.TABLE_ALIAS_KEY, QueryConstant.TABLE_ALIAS_MSG);
			result = false;
		}
		if(!DataValidater.isStrInteger(fromVo.getOrderNo())){
			this.setMessage(QueryConstant.ORDER_NO_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		if(DataValidater.isStrEmpty(fromVo.getFromResult())){
			this.setMessage(QueryConstant.FROM_RESULT_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}
		
		return result;
	}
	
	@Override
	protected boolean validateAddRepeat(Object obj) {
		
		boolean result = false;
		From fromIn = (From)obj;
		List<From> froms = this.getQueryFromContext().getFroms();
		for(From from: froms){
			if(!QueryConstant.DEL_OPRT.equals(from.getOprtFlag())){
				result = validateRepeat(fromIn, from);
			}
			if(result){
				break;
			}
		}
	
		return result;
	}
	
	@Override
	protected boolean validateMdfRepeat(Object obj) {
		
		boolean result = false;
		From fromIn = (From)obj;
		List<From> froms = this.getQueryFromContext().getFroms();
		for(From from: froms){
			if(!fromIn.getFromId().equals(from.getFromId())
					&& !fromIn.getFromId().equals(from.getFromId())){
				result = validateRepeat(fromIn, from);
			}
			if(result){
				break;
			}
		}
	
		return result;
	}
	
	@Override
	protected boolean validateRepeat(Object objIn, Object obj) {
		
		boolean result = false;
		
		From fromIn = (From)objIn;
		From from = (From)obj;
		
		String tableAliasIn = fromIn.getTableAlias();
		String fromResultIn = fromIn.getFromResult();
		
		String tableAlias = from.getTableAlias();
		String fromResult = from.getFromResult();
		
		if(tableAliasIn.equals(tableAlias)){
			final String msg = "别名已存在";
			this.setMessage(QueryConstant.TABLE_ALIAS_KEY, msg);
			result = true;
		}
		if(fromResultIn.equals(fromResult)){
			final String msg = "组合结果已存在";
			this.setMessage(QueryConstant.FROM_RESULT_KEY, msg);
			result = true;
		}
		
		return result;
	}

	/**
     * Declare：从上下文取得查询对象
     *
	 * @param  void
     * @return From
     */
	@Override
	protected From getEntity() {
		
		From from = new From();
		from.setTableName(fromVo.getTableName());
		from.setTableAlias(fromVo.getTableAlias());
		from.setOrderNo(DataConverter.stringToInteger(fromVo.getOrderNo()));
		from.setFromResult(fromVo.getFromResult());
		
		return from;
	}
	
	@Override
	public String findOrderNo() {
		
		Integer orderNo = 10;
		int maxOrderNo = 0;
		
		List<From> froms = getQueryFroms();
		for(From from: froms){
			if(from.getOrderNo()>maxOrderNo){
				maxOrderNo = from.getOrderNo();
			}
		}
		orderNo += maxOrderNo;
		
		setJsonValue(DataConverter.IntegerToString(orderNo));
		
		return JSON;
	}

	/**
	 * 弹出选择表名页面
	 * @return
	 */
	public String popupSelTbName() {
		
		List<String> tableNameList = new ArrayList<String>();
		
		Query query = this.getQueryFromContext();
		String sysTables = getConfigService().getSysTables(query);
		String queryFromName = ContextFactory.getParamFromRequest("queryFromName");
		
		final String AND = "and";
		BaseDataSourceVo baseDataSourceVo = baseDataSourceService.getBdsVoData(
				sysTables, new HashMap<String, String[]>(), AND).getReturnObject();
		if (!DataValidater.isObjNull(baseDataSourceVo)) {
			List<Object> tableNameObjList = baseDataSourceVo.getColumn(BdsConstant.FIXED_COL_NAME_CODE);
			
			for (int i=0; i<tableNameObjList.size(); i++) {
				String tn = (String)tableNameObjList.get(i);
				
				if (queryFromName!= null && !"".equals(queryFromName)) {//存在过滤条件
					if (StringUtils.containsIgnoreCase(tn, queryFromName)) {
						tableNameList.add(tn);
					}
				} else {
					tableNameList.add(tn);
				}
			}
		}
		
		//设置到Request中
		this.setMessage("queryFromName", queryFromName);
		this.setMessage("tableNameList", tableNameList);
		
		return "popupSelTbName";
	}
	
	/**
	 * 弹出选择表字段页面
	 * @return
	 */
	public String popupSelFieldName() {

		List<String> fieldNameList = new ArrayList<String>();
		
		String sysTableFields = ContextFactory.getParamFromRequest("sysTableFields");
		String tableName = ContextFactory.getParamFromRequest("tableName");
		String queryFieldName = ContextFactory.getParamFromRequest("queryFieldName");
		
		if (sysTableFields != null && !"".equals(sysTableFields) 
				&& tableName != null && !"".equals(tableName)) {
			
			String AND = "and";
			Map<String, String[]> filterMap = new HashMap<String, String[]>();
			filterMap.put("tableName", new String[]{tableName});
			
			BaseDataSourceVo baseDataSourceVo = baseDataSourceService.getBdsVoData(
					sysTableFields, filterMap, AND).getReturnObject();
			if (!DataValidater.isObjNull(baseDataSourceVo)) {
				List<Object> fieldNameObjList = baseDataSourceVo.getColumn(BdsConstant.FIXED_COL_NAME_CODE);
				
				for (int i=0; i<fieldNameObjList.size(); i++) {
					String fn = (String)fieldNameObjList.get(i);
					
					if (queryFieldName != null && !"".equals(queryFieldName)) {//存在过滤条件
						if (StringUtils.containsIgnoreCase(fn, queryFieldName)) {
							fieldNameList.add(fn);
						}
					} else {
						fieldNameList.add(fn);
					}
					
				}
			}
		}
		
		//设置到Request中
		this.setMessage("sysTableFields", sysTableFields);
		this.setMessage("tableName", tableName);
		this.setMessage("queryFieldName", queryFieldName);
		this.setMessage("fieldNameList", fieldNameList);
		
		return "popupSelFieldName";
	}
	
	public FromVo getFromVo() {
		return fromVo;
	}

	public void setFromVo(FromVo fromVo) {
		this.fromVo = fromVo;
	}

	@Override
	public String getJsonValue() {
		return super.getJsonValue();
	}

	public void setBaseDataSourceService(BaseDataSourceService baseDataSourceService) {
		this.baseDataSourceService = baseDataSourceService;
	}
}
