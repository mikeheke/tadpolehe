/**
 * 
 */
package mikeheke.tadpole.frm.query.web.action;

import java.util.ArrayList;
import java.util.List;

import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.query.entity.From;
import mikeheke.tadpole.frm.query.entity.Query;
import mikeheke.tadpole.frm.query.entity.Where;
import mikeheke.tadpole.frm.query.util.QueryConstant;
import mikeheke.tadpole.frm.query.vo.WhereVo;



/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：条件Action类
 */
public class WhereAction extends ConfigAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5664232631030785214L;

	private WhereVo whereVo;
	
	@Override
	public String init() {
		
		super.init();
		
		List<From> froms = this.getQueryFroms();
		List<From> allFroms = new ArrayList<From>();
		allFroms.addAll(froms);
		allFroms.addAll(getSysTableFroms());
		this.setMessage(QueryConstant.ALL_FROMS, allFroms);
		
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
		
		Where where = this.getEntity();
		where.setWhereId(System.currentTimeMillis()+"");
		where.setOprtFlag(QueryConstant.ADD_OPRT);		//增加操作
		
		if(validateAddRepeat(where)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		query.getWheres().add(where);
		query.setGlobalWhere(whereVo.getGlobalWhere());
		this.setQueryToContext(query);
		
		this.setWhereVo(null);
		
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
		
		String[] whereIds = whereVo.getIds();
		if(!validateIds(whereIds)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		
		for(String whereId: whereIds){
			String[] whereIdArr = whereId.split(QueryConstant.OPRT_SEP);
			
			Where where = new Where();
			where.setWhereId((whereIdArr[0]));
			where.setOprtFlag(whereIdArr[1]);
			query.getWheres().remove(where);
			if(!QueryConstant.ADD_OPRT.equals(where.getOprtFlag())){
				where.setOprtFlag(QueryConstant.DEL_OPRT);
				query.getWheres().add(where);
			}
		}
			
		this.setQueryToContext(query);
		
		this.setWhereVo(null);
		
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
		
		String[] whereIds = whereVo.getIds();
		if(!validateIds(whereIds)){
			return INPUT;
		}
		
		if(!validateData()){
			return INPUT;
		}
		
		String[] whereIdArr = whereIds[0].split(QueryConstant.OPRT_SEP);
		Where where = this.getEntity();
		where.setWhereId((whereIdArr[0]));
		where.setOprtFlag(whereIdArr[1]);
		
		if(validateMdfRepeat(where)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		query.getWheres().remove(where);
		
		if(QueryConstant.QRY_OPRT.equals(where.getOprtFlag())){
			where.setOprtFlag(QueryConstant.MDF_OPRT);
		}
		
		query.setGlobalWhere(whereVo.getGlobalWhere());
		query.getWheres().add(where);
		
		this.setQueryToContext(query);
		
		this.setWhereVo(null);
		
		return SUCCESS;
	}
	
	@Override
	public String findOrderNo() {
		
		Integer orderNo = 10;
		int maxOrderNo = 0;
		
		List<Where> wheres = getQueryWheres();
		for(Where where: wheres){
			if(where.getOrderNo()>maxOrderNo){
				maxOrderNo = where.getOrderNo();
			}
		}
		
		orderNo += maxOrderNo;

		setJsonValue(DataConverter.IntegerToString(orderNo));
		
		return JSON;
	}
	
	protected List<From> getSysTableFroms(){
		
		List<From> froms = new ArrayList<From>();
		Query query = this.getQueryFromContext();
		if(!getConfigService().checkDsJndi(query.getDsJndi())){
			return froms;
		}
		String sysTableDictCode = getConfigService().getSysTables(query);
		List<String> sysTables = getConfigService().getSysTables(sysTableDictCode);
		for(String sysTable: sysTables){
			From from = new From();
			from.setTableName(sysTable);
			from.setTableAlias(sysTable);
			froms.add(from);
		}
		
		return froms;
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
		
		String whereCode = whereVo.getWhereCode();
		if(DataValidater.isStrEmpty(whereCode)){
			this.setMessage(QueryConstant.WHERE_CODE_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}else if(DataValidater.isStrLenGt(whereCode, AppConstant._256)){
			this.setMessage(QueryConstant.WHERE_CODE_KEY, QueryConstant.WHERE_CODE_MSG);
			result = false;
		}
		if(DataValidater.isStrEmpty(whereVo.getTableName())){
			this.setMessage(QueryConstant.TABLE_NAME_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}
		if(DataValidater.isStrEmpty(whereVo.getFieldName())){
			this.setMessage(QueryConstant.FIELD_NAME_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}
		String whereResult = whereVo.getWhereResult();
		if(DataValidater.isStrEmpty(whereResult)){
			this.setMessage(QueryConstant.WHERE_RESULT_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}else if(DataValidater.isStrLenGt(whereResult, AppConstant._256)){
			this.setMessage(QueryConstant.WHERE_RESULT_KEY, QueryConstant.WHERE_RESULT_MSG);
			result = false;
		}
		String des = whereVo.getDes();
		if(DataValidater.isStrEmpty(des)){
			this.setMessage(QueryConstant.DES_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}else if(DataValidater.isStrLenGt(des, AppConstant._256)){
			this.setMessage(QueryConstant.DES_KEY, QueryConstant.DES_MSG);
			result = false;
		}
		if(!DataValidater.isStrInteger(whereVo.getOrderNo())){
			this.setMessage(QueryConstant.ORDER_NO_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		if(!DataValidater.isStrInteger(whereVo.getIsUserIn())){
			this.setMessage(QueryConstant.IS_USER_IN_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		if(!DataValidater.isStrInteger(whereVo.getOperator())){
			this.setMessage(QueryConstant.OPERATOR_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		
		return result;
	}
	
	@Override
	protected boolean validateAddRepeat(Object obj) {
		
		boolean result = false;
		Where whereIn = (Where)obj;
		
		List<Where> wheres = this.getQueryFromContext().getWheres();
		for(Where where: wheres){
			if(whereIn.getOprtFlag().equals(where.getOprtFlag())){
				result = validateRepeat(whereIn, where);
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
		Where whereIn = (Where)obj;
		List<Where> wheres = this.getQueryFromContext().getWheres();
		for(Where where: wheres){
			if(!QueryConstant.DEL_OPRT.equals(where.getOprtFlag())
					&& !whereIn.getWhereId().equals(where.getWhereId())){
				result = validateRepeat(whereIn, where);
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
		
		Where whereIn = (Where)objIn;
		Where where = (Where)obj;
		
		String whereCodeIn = whereIn.getWhereCode();
		//String whereResultIn = whereIn.getWhereResult();
		
		String whereCode = where.getWhereCode();
		//String whereResult = where.getWhereResult();
		
		if(whereCodeIn.equals(whereCode)){
			final String msg = "条件名已存在";
			this.setMessage(QueryConstant.WHERE_CODE_KEY, msg);
			result = true;
		}
		/*if(whereResultIn.equals(whereResult)){
			this.setMessage("whereResultMsg", "组合结果已存在");
			result = true;
		}*/
		
		return result;
	}

	/**
     * Declare：从上下文取得条件对象
     *
	 * @param  void
     * @return Where
     */
	@Override
	protected Where getEntity() {
		
		Where where = new Where();
		where.setWhereCode(whereVo.getWhereCode());
		String tableName = whereVo.getTableName();
		if(!DataValidater.isStrEmpty(tableName)){
			tableName = findTableAlias(tableName);
		}
		where.setTableName(tableName);
		where.setFieldName(whereVo.getFieldName());
		where.setWhereResult(whereVo.getWhereResult());
		where.setDes(whereVo.getDes());
		where.setDataType(whereVo.getDataType());
		where.setControlType(DataConverter.stringToInteger(whereVo.getControlType()));
		where.setDataCoding(whereVo.getDataCoding());
		where.setDefaultValue(whereVo.getDefaultValue());
		where.setFilterField(whereVo.getFilterField());
		where.setIsIgnoreCase(DataConverter.stringToInteger(whereVo.getIsIgnoreCase()));
		where.setIsRequired(DataConverter.stringToInteger(whereVo.getIsRequired()));
		where.setIsUserIn(DataConverter.stringToInteger(whereVo.getIsUserIn()));
		where.setLinkTable(whereVo.getLinkTable());
		where.setLinkField(whereVo.getLinkField());
		where.setIsInnerLink(DataConverter.stringToInteger(whereVo.getIsInnerLink()));
		where.setOperator(DataConverter.stringToInteger(whereVo.getOperator()));
		where.setOrderNo(DataConverter.stringToInteger(whereVo.getOrderNo()));
		where.setParTagId(whereVo.getParTagId());
		where.setRegExp(whereVo.getRegExp());

		return where;
	}

	public WhereVo getWhereVo() {
		return whereVo;
	}

	public void setWhereVo(WhereVo whereVo) {
		this.whereVo = whereVo;
	}
	
	@Override
	public String getJsonValue() {

		return super.getJsonValue();
	}
}
