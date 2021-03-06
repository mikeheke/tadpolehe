/**
 * 
 */
package mikeheke.tadpole.frm.query.web.action;


import java.util.List;

import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.query.entity.GroupBy;
import mikeheke.tadpole.frm.query.entity.Query;
import mikeheke.tadpole.frm.query.util.QueryConstant;
import mikeheke.tadpole.frm.query.vo.GroupByVo;



/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：分组Action类
 */
public class GroupByAction extends ConfigAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5664232631030785214L;

	private GroupByVo groupByVo;
	
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
		
		GroupBy groupBy = this.getEntity();
		groupBy.setGroupById(System.currentTimeMillis()+"");
		groupBy.setOprtFlag(QueryConstant.ADD_OPRT);		//增加操作
		
		if(validateAddRepeat(groupBy)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		query.getGroupBys().add(groupBy);
		this.setQueryToContext(query);
		
		this.setGroupByVo(null);
		
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
		
		String[] groupByIds = groupByVo.getIds();
		if(!validateIds(groupByIds)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		
		for(String groupById: groupByIds){

			String[] groupByIdArr = groupById.split(QueryConstant.OPRT_SEP);
			
			GroupBy groupBy = new GroupBy();
			groupBy.setGroupById((groupByIdArr[0]));
			groupBy.setOprtFlag(groupByIdArr[1]);
			query.getGroupBys().remove(groupBy);
			if(!QueryConstant.ADD_OPRT.equals(groupBy.getOprtFlag())){
				groupBy.setOprtFlag(QueryConstant.DEL_OPRT);
				query.getGroupBys().add(groupBy);
			}
		}
			
		this.setQueryToContext(query);
		
		this.setGroupByVo(null);
		
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
		
		String[] groupByIds = groupByVo.getIds();
		if(!validateIds(groupByIds)){
			return INPUT;
		}
		
		if(!validateData()){
			return INPUT;
		}
		
		String[] groupByIdArr = groupByIds[0].split(QueryConstant.OPRT_SEP);
		GroupBy groupBy = this.getEntity();
		groupBy.setGroupById((groupByIdArr[0]));
		groupBy.setOprtFlag(groupByIdArr[1]);
		
		if(validateMdfRepeat(groupBy)){
			return INPUT;
		}
		
		Query query = this.getQueryFromContext();
		query.getGroupBys().remove(groupBy);
		
		if(QueryConstant.QRY_OPRT.equals(groupBy.getOprtFlag())){
			groupBy.setOprtFlag(QueryConstant.MDF_OPRT);
		}
		
		query.getGroupBys().add(groupBy);
		
		this.setQueryToContext(query);
		
		this.setGroupByVo(null);
		
		return SUCCESS;
	}

	@Override
	public String findOrderNo() {
		
		Integer orderNo = 10;
		int maxOrderNo = 0;
		
		List<GroupBy> groupBys = getQueryGroupBys();
		for(GroupBy groupBy: groupBys){
			if(groupBy.getOrderNo()>maxOrderNo){
				maxOrderNo = groupBy.getOrderNo();
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
		
		if(DataValidater.isStrEmpty(groupByVo.getTableName())){
			this.setMessage(QueryConstant.TABLE_NAME_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}
		if(DataValidater.isStrEmpty(groupByVo.getFieldName())){
			this.setMessage(QueryConstant.FIELD_NAME_KEY, getText(QueryConstant.NO_EMPTY_KEY));
			result = false;
		}
		if(!DataValidater.isStrInteger(groupByVo.getOrderNo())){
			this.setMessage(QueryConstant.ORDER_NO_KEY, getText(QueryConstant.YES_INT_KEY));
			result = false;
		}
		
		return result;
	}
	
	@Override
	protected boolean validateAddRepeat(Object obj) {
		
		boolean result = false;
		GroupBy groupByIn = (GroupBy)obj;
		
		List<GroupBy> groupBys = this.getQueryFromContext().getGroupBys();
		for(GroupBy groupBy: groupBys){
			if(groupByIn.getOprtFlag().equals(groupBy.getOprtFlag())){
				result = validateRepeat(groupByIn, groupBy);
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
		GroupBy groupByIn = (GroupBy)obj;
		List<GroupBy> groupBys = this.getQueryFromContext().getGroupBys();
		for(GroupBy groupBy: groupBys){
			if(!QueryConstant.DEL_OPRT.equals(groupBy.getOprtFlag())
					&& !groupByIn.getGroupById().equals(groupBy.getGroupById())){
				result = validateRepeat(groupByIn, groupBy);
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
		
		GroupBy groupByIn = (GroupBy)objIn;
		GroupBy groupBy = (GroupBy)obj;
		
		String tableNameIn = groupByIn.getTableName();
		String fieldNameIn = groupByIn.getFieldName();
		
		String tableName = groupBy.getTableName();
		String fieldName = groupBy.getFieldName();
		
		if((tableNameIn+fieldNameIn).equals(tableName+fieldName)){
			final String msg = "表名+字段名已存在";
			this.setMessage(QueryConstant.FIELD_NAME_KEY, msg);
			result = true;
		}
		
		return result;
	}

	/**
     * Declare：从上下文取得分组对象
     *
	 * @param  void
     * @return GroupBy
     */
	@Override
	protected GroupBy getEntity() {
		
		GroupBy groupBy = new GroupBy();
		String tableName = findTableAlias(groupByVo.getTableName());
		groupBy.setTableName(tableName);
		groupBy.setFieldName(groupByVo.getFieldName());
		groupBy.setOrderNo(DataConverter.stringToInteger(groupByVo.getOrderNo()));
		
		return groupBy;
	}

	public GroupByVo getGroupByVo() {
		return groupByVo;
	}

	public void setGroupByVo(GroupByVo groupByVo) {
		this.groupByVo = groupByVo;
	}

	@Override
	public String getJsonValue() {
		return super.getJsonValue();
	}
}
