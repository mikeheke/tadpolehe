/**
 * 
 */
package com.amway.frm.query.web.action;

import java.util.List;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.query.entity.Query;
import com.amway.frm.query.entity.Select;
import com.amway.frm.query.util.QueryConstant;
import com.amway.frm.query.vo.SelectVo;



/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：选择Action类
 */
public class SelectAction extends ConfigAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5664232631030785214L;
	
	private SelectVo selectVo;
	
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
		
		Select select = this.getEntity();
		select.setSelectId(System.currentTimeMillis()+"");
		select.setOprtFlag(QueryConstant.ADD_OPRT);		//增加操作
		
		if(validateAddRepeat(select)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		query.getSelects().add(select);
		this.setQueryToContext(query);
		
		this.setSelectVo(null);
		
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
		
		String[] selectIds = selectVo.getIds();
		if(!validateIds(selectIds)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		for(String selectId: selectIds){

			if(DataValidater.isStrEmpty(selectId)){
				continue;
			}
			String[] selectIdArr = selectId.split(QueryConstant.OPRT_SEP);
			
			Select select = new Select();
			select.setSelectId((selectIdArr[0]));
			select.setOprtFlag(selectIdArr[1]);
			query.getSelects().remove(select);
			if(!QueryConstant.ADD_OPRT.equals(select.getOprtFlag())){
				select.setOprtFlag(QueryConstant.DEL_OPRT);
				query.getSelects().add(select);
			}
		}
			
		this.setQueryToContext(query);
		
		this.setSelectVo(null);
		
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
		
		String[] selectIds = selectVo.getIds();
		if(!validateIds(selectIds)){
			return INPUT;
		}
		
		if(!validateData()){
			return INPUT;
		}
		
		String[] selectIdArr = selectIds[0].split(QueryConstant.OPRT_SEP);
		Select select = this.getEntity();
		select.setSelectId((selectIdArr[0]));
		select.setOprtFlag(selectIdArr[1]);
		
		if(validateMdfRepeat(select)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		query.getSelects().remove(select);
		
		if(QueryConstant.QRY_OPRT.equals(select.getOprtFlag())){
			select.setOprtFlag(QueryConstant.MDF_OPRT);
		}
		
		query.getSelects().add(select);
		
		this.setQueryToContext(query);
		
		this.setSelectVo(null);
		
		return SUCCESS;
	}
	
	@Override
	public String findOrderNo() {
		
		Integer orderNo = 10;
		int maxOrderNo = 0;
		
		List<Select> selects = getQuerySelects();
		for(Select select: selects){
			if(select.getOrderNo()>maxOrderNo){
				maxOrderNo = select.getOrderNo();
			}
		}
		
		orderNo += maxOrderNo;

		setJsonValue(DataConverter.IntegerToString(orderNo));
		
		return JSON;
	}
	
	public String findTableFieldType(){
		
		String fieldDictCode = this.getJsonValue();
		String tableName = selectVo.getTableName();
		String fieldName = selectVo.getFieldName();
		String fieldType = getConfigService().getSysTableFieldType(
				fieldDictCode, tableName, fieldName);
		
		setJsonValue(fieldType);
		
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
		
		String tableName = selectVo.getTableName();
		if(!DataValidater.isStrEmpty(tableName)){
			if(DataValidater.isStrEmpty(selectVo.getFieldName())){
				this.setMessage(QueryConstant.FIELD_NAME_KEY, getText(QueryConstant.NO_EMPTY_KEY));
				result = false;
			}
		}
		String des = selectVo.getDes();
		if(DataValidater.isStrEmpty(des)){
			this.setMessage(QueryConstant.DES_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}else if(DataValidater.isStrLenGt(des, AppConstant._256)){
			this.setMessage(QueryConstant.DES_KEY, QueryConstant.DES_MSG);
			result = false;
		}
		
		if(DataValidater.isStrEmpty(selectVo.getSelectResult())){
			this.setMessage(QueryConstant.SELECT_RESULT_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}
		if(DataValidater.isStrEmpty(selectVo.getDataType())){
			this.setMessage(QueryConstant.DATA_TYPE_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}
		if(!DataValidater.isStrInteger(selectVo.getOrderNo())){
			this.setMessage(QueryConstant.ORDER_NO_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		if(!DataValidater.isStrInteger(selectVo.getIsHidden())){
			this.setMessage(QueryConstant.IS_HIDDEN_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		
		return result;
	}
	
	@Override
	protected boolean validateAddRepeat(Object obj) {
		
		boolean result = false;
		Select selectIn = (Select)obj;
		
		List<Select> selects = this.getQueryFromContext().getSelects();
		for(Select select: selects){
			if(selectIn.getOprtFlag().equals(select.getOprtFlag())){
				result = validateRepeat(selectIn, select);
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
		Select selectIn = (Select)obj;
		List<Select> selects = this.getQueryFromContext().getSelects();
		for(Select select: selects){
			if(!QueryConstant.DEL_OPRT.equals(select.getOprtFlag())
					&& !selectIn.getSelectId().equals(select.getSelectId())){
				result = validateRepeat(selectIn, select);
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
		
		Select selectIn = (Select)objIn;
		Select select = (Select)obj;
		
		String fieldAliasIn = selectIn.getFieldAlias();
		String selectReslutIn = selectIn.getSelectResult();
		
		String fieldAlias = select.getFieldAlias();
		String selectResult = select.getSelectResult();
		
		if(!DataValidater.isStrEmpty(fieldAliasIn)
				&& !DataValidater.isStrEmpty(fieldAlias)
				&& fieldAliasIn.equals(fieldAlias)){
			this.setMessage(QueryConstant.FIELD_ALIAS_KEY, QueryConstant.FILED_ALIAS_MSG);
			result = true;
		}
		if(selectReslutIn.equals(selectResult)
				&& fieldAliasIn.equals(fieldAlias)){
			this.setMessage(QueryConstant.SELECT_RESULT_KEY, QueryConstant.SELECT_RESULT_MSG);
			result = true;
		}
		
		return result;
	}

	/**
     * Declare：从上下文取得选择对象
     *
	 * @param  void
     * @return Select
     */
	@Override
	protected Select getEntity() {
		
		Select select = new Select();
		String tableName = selectVo.getTableName();
		if(!DataValidater.isStrEmpty(tableName)){
			tableName = findTableAlias(tableName);
		}
		select.setTableName(tableName);
		if(!DataValidater.isStrEmpty(tableName)){
			select.setFieldName(selectVo.getFieldName());
		}else{
			select.setFieldName(selectVo.getFieldName2());
		}
		select.setFieldAlias(selectVo.getFieldAlias());
		select.setSelectResult(selectVo.getSelectResult());
		select.setDes(selectVo.getDes());
		select.setBtnType(DataConverter.stringToInteger(selectVo.getBtnType()));
		select.setDataInput(selectVo.getDataInput());
		select.setDataCoding(selectVo.getDataCoding());
		select.setDataType(selectVo.getDataType());
		select.setIsHidden(DataConverter.stringToInteger(selectVo.getIsHidden()));
		select.setLinkUrl(selectVo.getLinkUrl());
		select.setOpenType(DataConverter.stringToInteger(selectVo.getOpenType()));
		select.setOrderNo(DataConverter.stringToInteger(selectVo.getOrderNo()));
		select.setOutFormat(DataConverter.stringToInteger(selectVo.getOutFormat()));
		select.setColWidth(selectVo.getColWidth());
		
		return select;
	}

	public SelectVo getSelectVo() {
		return selectVo;
	}

	public void setSelectVo(SelectVo selectVo) {
		this.selectVo = selectVo;
	}
	
	@Override
	public String getJsonValue() {

		return super.getJsonValue();
	}
}
