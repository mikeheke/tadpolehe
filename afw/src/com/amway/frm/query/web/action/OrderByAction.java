/**
 * 
 */
package com.amway.frm.query.web.action;


import java.util.List;

import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.query.entity.OrderBy;
import com.amway.frm.query.entity.Query;
import com.amway.frm.query.util.DataServiceUtil;
import com.amway.frm.query.util.QueryConstant;
import com.amway.frm.query.vo.OrderByVo;



/**
 * Created by MyElipse
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：排序Action类
 */
public class OrderByAction extends ConfigAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5664232631030785214L;

	private OrderByVo orderByVo;

	@Override
	public String init() {
	
		return super.init();
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
		
		OrderBy orderByObj = this.getEntity();
		orderByObj.setOrderById(System.currentTimeMillis()+"");
		orderByObj.setOprtFlag(QueryConstant.ADD_OPRT);		//增加操作
		
		if(validateAddRepeat(orderByObj)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		query.getOrderBys().add(orderByObj);
		this.setQueryToContext(query);
		
		this.setOrderByVo(null);
		
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
		
		String[] orderByIds = orderByVo.getIds();
		if(!validateIds(orderByIds)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		
		for(String orderBy: orderByIds){

			String[] orderByArr = orderBy.split(QueryConstant.OPRT_SEP);
			
			OrderBy orderByObj = new OrderBy();
			orderByObj.setOrderById((orderByArr[0]));
			orderByObj.setOprtFlag(orderByArr[1]);
			query.getOrderBys().remove(orderByObj);
			if(!QueryConstant.ADD_OPRT.equals(orderByObj.getOprtFlag())){
				orderByObj.setOprtFlag(QueryConstant.DEL_OPRT);
				query.getOrderBys().add(orderByObj);
			}
		}
			
		this.setQueryToContext(query);
		
		this.setOrderByVo(null);
		
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
		
		String[] orderByIds = orderByVo.getIds();
		if(!validateIds(orderByIds)){
			return INPUT;
		}
		
		if(!validateData()){
			return INPUT;
		}
		
		String[] orderByArr = orderByIds[0].split(QueryConstant.OPRT_SEP);
		
		OrderBy orderByObj = this.getEntity();
		orderByObj.setOrderById((orderByArr[0]));
		orderByObj.setOprtFlag(orderByArr[1]);
		
		if(validateMdfRepeat(orderByObj)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		query.getOrderBys().remove(orderByObj);
		
		if(QueryConstant.QRY_OPRT.equals(orderByObj.getOprtFlag())){
			orderByObj.setOprtFlag(QueryConstant.MDF_OPRT);
		}
		
		query.getOrderBys().add(orderByObj);
		
		this.setQueryToContext(query);
		
		this.setOrderByVo(null);
		
		return SUCCESS;
	}
	
	@Override
	public String findOrderNo() {
		
		Integer orderNo = 10;
		int maxOrderNo = 0;
		
		List<OrderBy> orderBys = getQueryOrderBys();
		for(OrderBy orderBy: orderBys){
			if(orderBy.getOrderNo()>maxOrderNo){
				maxOrderNo = orderBy.getOrderNo();
			}
		}
		
		orderNo += maxOrderNo;
	
		
		setJsonValue(DataConverter.IntegerToString(orderNo));
		
		return JSON;
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
		
		if(DataValidater.isStrEmpty(orderByVo.getTableName())){
			this.setMessage(QueryConstant.TABLE_NAME_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}
		if(DataValidater.isStrEmpty(orderByVo.getFieldName())){
			this.setMessage(QueryConstant.FIELD_NAME_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}
		if(!DataValidater.isStrInteger(orderByVo.getOrderBy())){
			this.setMessage(QueryConstant.ORDER_BY_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		if(!DataValidater.isStrInteger(orderByVo.getOrderNo())){
			this.setMessage(QueryConstant.ORDER_NO_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		
		return result;
	}
	
	@Override
	protected boolean validateAddRepeat(Object obj) {
		
		boolean result = false;
		OrderBy orderByIn = (OrderBy)obj;
		
		List<OrderBy> orderBys = this.getQueryFromContext().getOrderBys();
		for(OrderBy orderBy: orderBys){
			if(orderByIn.getOprtFlag().equals(orderBy.getOprtFlag())){
				result = validateRepeat(orderByIn, orderBy);
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
		OrderBy orderByIn = (OrderBy)obj;
		List<OrderBy> orderBys = this.getQueryFromContext().getOrderBys();
		for(OrderBy orderBy: orderBys){
			if(!QueryConstant.DEL_OPRT.equals(orderBy.getOprtFlag())
					&& !orderByIn.getOrderById().equals(orderBy.getOrderById())){
				result = validateRepeat(orderByIn, orderBy);
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
		
		OrderBy orderByIn = (OrderBy)objIn;
		OrderBy orderBy = (OrderBy)obj;
		
		String tableNameIn = orderByIn.getTableName();
		String fieldNameIn = orderByIn.getFieldName();
		
		String tableName = orderBy.getTableName();
		String fieldName = orderBy.getFieldName();
		
		if((tableNameIn+fieldNameIn).equals(tableName+fieldName)){
			final String msg = "表名+字段名已存在";
			this.setMessage(QueryConstant.FIELD_NAME_KEY, msg);
			result = true;
		}
		
		return result;
	}

	/**
     * Declare：从上下文取得排序对象
     *
	 * @param  void
     * @return OrderBy
     */
	@Override
	protected OrderBy getEntity() {
		
		OrderBy orderByObj = new OrderBy();
		String tableName = findTableAlias(orderByVo.getTableName());
		orderByObj.setTableName(tableName);
		orderByObj.setFieldName(orderByVo.getFieldName());
		orderByObj.setOrderBy(DataConverter.stringToInteger(orderByVo.getOrderBy()));
		orderByObj.setOrderByName(DataServiceUtil.getOrderByName(orderByObj.getOrderBy()));
		orderByObj.setOrderNo(DataConverter.stringToInteger(orderByVo.getOrderNo()));
		
		return orderByObj;
	}
	
	public OrderByVo getOrderByVo() {
		return orderByVo;
	}

	public void setOrderByVo(OrderByVo orderByVo) {
		this.orderByVo = orderByVo;
	}

	@Override
	public String getJsonValue() {
		return super.getJsonValue();
	}
}
