/**
 * 
 */
package com.amway.frm.query.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.bds.vo.BaseDataSourceVo;
import com.amway.frm.query.entity.Button;
import com.amway.frm.query.entity.DataCoding;
import com.amway.frm.query.entity.From;
import com.amway.frm.query.entity.GroupBy;
import com.amway.frm.query.entity.OrderBy;
import com.amway.frm.query.entity.Query;
import com.amway.frm.query.entity.Select;
import com.amway.frm.query.entity.Where;
import com.amway.frm.query.exception.QueryInfoException;
import com.amway.frm.query.exception.QuerySysException;
import com.amway.frm.query.service.ConfigService;
import com.amway.frm.query.util.QueryConstant;

/**
 * 
 *
 * 2011-2-15 下午06:09:18
 */
public class ConfigImpl extends QueryImpl implements ConfigService {
	
	/* (non-Javadoc)
	 * @see com.amway.frm.query.service.QueryService#saveQuery(com.amway.frm.query.entity.Query)
	 */
	@Override
	@Transactional
	public ReturnMessage<Query> saveQuery(Query query) throws QuerySysException {
		
		ReturnMessage<Query> returnMessage = new ReturnMessage<Query>();
		
		if(null == query){
			return returnMessage;
		}
		List<Select> selects = new ArrayList<Select>(query.getSelects());
		List<From> froms = new ArrayList<From>(query.getFroms());
		List<Where> wheres = new ArrayList<Where>(query.getWheres());
		List<OrderBy> orderBys = new ArrayList<OrderBy>(query.getOrderBys());
		List<GroupBy> groupBys = new ArrayList<GroupBy>(query.getGroupBys());
		List<Button> buttons = new ArrayList<Button>(query.getButtons());
		
		Query base = query.getBase();
		Query queryRet = saveBase(base);
		
		List<Select> selectRets = this.saveSelects(queryRet, selects);
		queryRet.getSelects().addAll(selectRets);
		
		List<From> fromRets = this.saveFroms(queryRet, froms);
		queryRet.getFroms().addAll(fromRets);
		
		List<Where> whereRets = this.saveWheres(queryRet, wheres);
		queryRet.getWheres().addAll(whereRets);
		
		List<OrderBy> orderByRets = this.saveOrderBys(queryRet, orderBys);
		queryRet.getOrderBys().addAll(orderByRets);
		
		List<GroupBy> groupByRets = this.saveGroupBys(queryRet, groupBys);
		queryRet.getGroupBys().addAll(groupByRets);
		
		List<Button> buttonRets = this.saveButtons(queryRet, buttons);
		queryRet.getButtons().addAll(buttonRets);
		
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnObject(queryRet);
		
		return returnMessage;
	}
	
	private Query saveBase(Query query) {
		
		Query queryRet = null;
		ReturnMessage<Query> returnMessage = null;
		if(QueryConstant.ADD_OPRT.equals(query.getOprtFlag())){
			//query.setQueryId(super.getQueryDao().generateSequence("MSTB_QUERY_INDEX"));
			returnMessage = this.add(query);
		}else if(QueryConstant.MDF_OPRT.equals(query.getOprtFlag())){
			returnMessage = this.update(query);
		}
		
		if(!returnMessage.isSuccess()){
			throw new QueryInfoException(returnMessage.getReturnMsg());
		}
		queryRet = returnMessage.getReturnObject();
		
		return queryRet;
	}
	
	private List<Select> saveSelects(Query query, List<Select> selects){
		
		List<Select> selectRets = new ArrayList<Select>();
		
		for(Select select: selects){
			
			ReturnMessage<Select> returnMessage = null;
			if(QueryConstant.ADD_OPRT.equals(select.getOprtFlag())){
				//select.setSelectId(super.getQueryDao().generateSequence("MSTB_QUERY_SELECT"));
				select.setQuery(query);
				returnMessage = this.addOrUpdate(select);
			}else if(QueryConstant.MDF_OPRT.equals(select.getOprtFlag())){
				select.setQuery(query);
				returnMessage = this.update(select);
			}else if(QueryConstant.DEL_OPRT.equals(select.getOprtFlag())){
				returnMessage = this.delete(select);
			}else{
				continue;
			}
			
			if(!returnMessage.isSuccess()){
				throw new QueryInfoException(returnMessage.getReturnMsg());
			}
			
			selectRets.add(returnMessage.getReturnObject());
		}
		
		return selectRets;
	}
	
	private List<From> saveFroms(Query query, List<From> froms) {
		
		List<From> fromRets = new ArrayList<From>();
		
		for(From from: froms){
			
			ReturnMessage<From> returnMessage = null;
			if(QueryConstant.ADD_OPRT.equals(from.getOprtFlag())){
				//from.setFromId(super.getQueryDao().generateSequence("MSTB_QUERY_FROM"));
				from.setQuery(query);
				returnMessage = this.addOrUpdate(from);
			}else if(QueryConstant.MDF_OPRT.equals(from.getOprtFlag())){
				from.setQuery(query);
				returnMessage = this.update(from);
			}else if(QueryConstant.DEL_OPRT.equals(from.getOprtFlag())){
				returnMessage = this.delete(from);
			}else{
				continue;
			}
			
			if(!returnMessage.isSuccess()){
				throw new QueryInfoException(returnMessage.getReturnMsg());
			}
			
			fromRets.add(returnMessage.getReturnObject());
		}
		
		return fromRets;
	}	
	
	private List<Where> saveWheres(Query query, List<Where> wheres) {
		
		List<Where> whereRets = new ArrayList<Where>();
	
		for(Where where: wheres){

			ReturnMessage<Where> returnMessage = null;
			if(QueryConstant.ADD_OPRT.equals(where.getOprtFlag())){
				//where.setWhereId(super.getQueryDao().generateSequence("MSTB_QUERY_WHERE"));
				where.setQuery(query);
				returnMessage = this.addOrUpdate(where);
			}else if(QueryConstant.MDF_OPRT.equals(where.getOprtFlag())){
				where.setQuery(query);
				returnMessage = this.update(where);
			}else if(QueryConstant.DEL_OPRT.equals(where.getOprtFlag())){
				returnMessage = this.delete(where);
			}else{
				continue;
			}
			
			if(!returnMessage.isSuccess()){
				throw new QueryInfoException(returnMessage.getReturnMsg());
			}
			
			whereRets.add(returnMessage.getReturnObject());
		}
		
		return whereRets;
	}
	
	private List<OrderBy> saveOrderBys(Query query, List<OrderBy> orderBys){
		
		List<OrderBy> orderByRets = new ArrayList<OrderBy>();
		
		for(OrderBy orderBy: orderBys){
			
			ReturnMessage<OrderBy> returnMessage = null;
			if(QueryConstant.ADD_OPRT.equals(orderBy.getOprtFlag())){
				//orderBy.setOrderById(super.getQueryDao().generateSequence("MSTB_QUERY_ORDERBY"));
				orderBy.setQuery(query);
				returnMessage = this.addOrUpdate(orderBy);
			}else if(QueryConstant.MDF_OPRT.equals(orderBy.getOprtFlag())){
				orderBy.setQuery(query);
				returnMessage = this.update(orderBy);
			}else if(QueryConstant.DEL_OPRT.equals(orderBy.getOprtFlag())){
				returnMessage = this.delete(orderBy);
			}else{
				continue;
			}
			
			if(!returnMessage.isSuccess()){
				throw new QueryInfoException(returnMessage.getReturnMsg());
			}
			
			orderByRets.add(returnMessage.getReturnObject());
		}
		
		return orderByRets;
	}
	
	private List<GroupBy> saveGroupBys(Query query, List<GroupBy> groupBys){
	
		List<GroupBy> groupByRets = new ArrayList<GroupBy>();
		
		for(GroupBy groupBy: groupBys){
			
			ReturnMessage<GroupBy> returnMessage = null;
			if(QueryConstant.ADD_OPRT.equals(groupBy.getOprtFlag())){
				//groupBy.setGroupById(super.getQueryDao().generateSequence("MSTB_QUERY_GROUPBY"));
				groupBy.setQuery(query);
				returnMessage = this.addOrUpdate(groupBy);
			}else if(QueryConstant.MDF_OPRT.equals(groupBy.getOprtFlag())){
				groupBy.setQuery(query);
				returnMessage = this.update(groupBy);
			}else if(QueryConstant.DEL_OPRT.equals(groupBy.getOprtFlag())){
				returnMessage = this.delete(groupBy);
			}else{
				continue;
			}
			
			if(!returnMessage.isSuccess()){
				throw new QueryInfoException(returnMessage.getReturnMsg());
			}
			
			groupByRets.add(returnMessage.getReturnObject());
		}
		
		return groupByRets;
	}
	
	private List<Button> saveButtons(Query query, List<Button> buttons){
	
		List<Button> buttonRets = new ArrayList<Button>();
		
		for(Button button: buttons){
	
			ReturnMessage<Button> returnMessage = null;
			if(QueryConstant.ADD_OPRT.equals(button.getOprtFlag())){
				//button.setButtonId(super.getQueryDao().generateSequence("MSTB_QUERY_BUTTON"));
				button.setQuery(query);
				returnMessage = this.addOrUpdate(button);
			}else if(QueryConstant.MDF_OPRT.equals(button.getOprtFlag())){
				button.setQuery(query);
				returnMessage = this.update(button);
			}else if(QueryConstant.DEL_OPRT.equals(button.getOprtFlag())){
				returnMessage = this.delete(button);
			}else{
				continue;
			}
			
			if(!returnMessage.isSuccess()){
				throw new QueryInfoException(returnMessage.getReturnMsg());
			}
			
			buttonRets.add(returnMessage.getReturnObject());
		}
		
		return buttonRets;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.query.service.QueryService#checkDsJndi(java.lang.String)
	 */
	@Override
	public boolean checkDsJndi(String dsJndi) {
		
		boolean result = true;
		if(DataValidater.isStrEmpty(dsJndi)){
			return false;
		}
		result = this.getQueryDao().checkDsJndi(dsJndi);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.query.service.ConfigService#getSysTables()
	 */
	@Override
	public String getSysTables(Query query) {
	
		Map<String, String[]> filterMap = new HashMap<String, String[]>();
		filterMap.put(QueryConstant.DS_JNDI_NAME, new String[]{query.getDsJndi()});
		BaseDataSourceVo baseDataSourceVo = getBaseDataSourceVo(
				QueryConstant.DS_TABLE_COL_NAME, filterMap);
		String sysTable = (String) baseDataSourceVo
				.getColumnSingle(QueryConstant.DS_TABLE_DICT_NAME);
		
		return sysTable;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.query.service.ConfigService#getSysTableFieldList(java.lang.String)
	 */
	@Override
	public String getSysTableFields(Query query) {
		
		Map<String, String[]> filterMap = new HashMap<String, String[]>();
		filterMap.put(QueryConstant.DS_JNDI_NAME, new String[]{query.getDsJndi()});
		BaseDataSourceVo baseDataSourceVo = getBaseDataSourceVo(
				QueryConstant.DS_TABLE_COL_NAME, filterMap);
		String sysTableField = (String) baseDataSourceVo
				.getColumnSingle(QueryConstant.DS_COL_DICT_NAME);
		
		return sysTableField;

	}
	
	@Override
	public String getSysTableFieldType(String fieldDictCode, String tableName,
			String fieldName){
		
		Map<String, String[]> filterMap = new HashMap<String, String[]>();
		filterMap.put(QueryConstant.DS_TABLE_NAME, new String[]{tableName});
		filterMap.put(QueryConstant.DS_COL_NAME, new String[]{fieldName});
		BaseDataSourceVo baseDataSourceVo = getBaseDataSourceVo(fieldDictCode,
				filterMap);
		String sysTableFieldType = (String) baseDataSourceVo
			.getColumnSingle(QueryConstant.DS_COL_TYPE_NAME);
		
		sysTableFieldType = getDisplayNameByCode(
				QueryConstant.DS_DATA_TYPE_NAME, sysTableFieldType);
		
		return sysTableFieldType;
	}
	
	public List getSysTables(String sysTableDictCode){
		
		Map<String, String[]> filterMap = new HashMap<String, String[]>();
		BaseDataSourceVo baseDataSourceVo = getBaseDataSourceVo(sysTableDictCode, filterMap);
		List sysTables = baseDataSourceVo.getColumn(QueryConstant.DS_TABLE_NAME);
		
		return sysTables;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.query.service.ConfigService#getDataCodings()
	 */
	@Override
	public List<DataCoding> getDataCodings() {
		return queryList(new DataCoding());
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.query.service.ConfigService#getApplications()
	 */
	@Override
	public List<Application> getApplications() {
		return queryList(new Application());
	}

	/*
	 * (non-Javadoc)
	 * @see com.amway.frm.query.service.ConfigService#copyQuery(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public ReturnMessage<Query> copyQuery(String oldQueryCode,
			String newQueryCode) {
		
		ReturnMessage<Query> returnMessage = new ReturnMessage<Query>();
		
		//查询条件query
		Query queryCondition = new Query();
		//根据queryCode查询
		queryCondition.setQueryCode(newQueryCode);
		
		if (null != this.query(queryCondition).getReturnObject()) {//判断新的queryCode是否存在
			returnMessage.setReturnMsg(AppConstant.RECORD_YEXISTS);
			return returnMessage;
		}
		
		//根据queryCode查询
		queryCondition.setQueryCode(oldQueryCode);
		
		Query oldQuery = this.getQueryByQuery(queryCondition).getReturnObject();
		Query newQuery=new Query();
		
		BeanUtils.copyProperties(oldQuery, newQuery);
		
		//操作标识
		newQuery.setOprtFlag(QueryConstant.ADD_OPRT);
		//设置对象id为null
		//newQuery.setQueryId(super.getQueryDao().generateSequence("MSTB_QUERY_INDEX"));
		newQuery.setQueryCode(newQueryCode);
		
		newQuery.setSelects(null);
		newQuery.setFroms(null);
		newQuery.setWheres(null);
		newQuery.setOrderBys(null);
		newQuery.setGroupBys(null);
		newQuery.setButtons(null);
		
		//保存query
		this.addOrUpdate(newQuery);
		
		for (Select select : oldQuery.getSelects()) {
			Select newSelect = new Select();
			BeanUtils.copyProperties(select, newSelect);
			//newSelect.setSelectId(super.getQueryDao().generateSequence("MSTB_QUERY_SELECT"));
			newSelect.setQuery(newQuery);
			this.addOrUpdate(newSelect);
		}
		for (From from : oldQuery.getFroms()) {
			From newFrom = new From();
			BeanUtils.copyProperties(from, newFrom);
			//newFrom.setFromId(super.getQueryDao().generateSequence("MSTB_QUERY_FROM"));
			newFrom.setQuery(newQuery);
			this.addOrUpdate(newFrom);
		}
		for (Where where : oldQuery.getWheres()) {
			Where newWhere = new Where();
			BeanUtils.copyProperties(where, newWhere);
			//newWhere.setWhereId(super.getQueryDao().generateSequence("MSTB_QUERY_WHERE"));
			newWhere.setQuery(newQuery);
			this.addOrUpdate(newWhere);
		}
		for (GroupBy groupBy : oldQuery.getGroupBys()) {
			GroupBy newGroupBy = new GroupBy();
			BeanUtils.copyProperties(groupBy, newGroupBy);
			//newGroupBy.setGroupById(super.getQueryDao().generateSequence("MSTB_QUERY_GROUPBY"));
			newGroupBy.setQuery(newQuery);
			this.addOrUpdate(newGroupBy);
		}
		for (OrderBy orderBy : oldQuery.getOrderBys()) {
			OrderBy newOrderBy = new OrderBy();
			BeanUtils.copyProperties(orderBy, newOrderBy);
			//newOrderBy.setOrderById(super.getQueryDao().generateSequence("MSTB_QUERY_ORDERBY"));
			newOrderBy.setQuery(newQuery);
			this.addOrUpdate(newOrderBy);
		}
		for (Button button : oldQuery.getButtons()) {
			Button newButton = new Button();
			BeanUtils.copyProperties(button, newButton);
			//newButton.setButtonId(super.getQueryDao().generateSequence("MSTB_QUERY_BUTTON"));
			newButton.setQuery(newQuery);
			this.addOrUpdate(newButton);
		}
		
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnMsg("复制成功");
		return returnMessage;
	}

	
}
