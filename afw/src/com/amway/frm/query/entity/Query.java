/**
 * 
 */
package com.amway.frm.query.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.query.util.QueryConstant;
import com.amway.frm.query.vo.Table;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：查询
 */
@Entity(name="query_Index")
@javax.persistence.Table(name="MSTB_QUERY_INDEX", schema=QueryConstant.APP_DEAULT_SCHEMA)
public class Query implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2488772794743598268L;
	
	//主键
	@Id
	//@GenericGenerator(name="systemUUID",strategy="uuid")
	//@GeneratedValue(generator="systemUUID")
	@Column(name="QUERY_ID")
	private String queryId;
	
	//查询代码
	@Column(name="QUERY_CODE", unique=true)
	private String queryCode;
	
	//查询名称
	@Column(name="QUERY_NAME")
	private String queryName;

	//应用ID
	@ManyToOne()
	@JoinColumn(name="APPLICATION_ID")
	private Application application;
	
	//数据源JNDI
	@Column(name="DS_JNDI")
	private String dsJndi;
	
	//是否列头过滤
	@Column(name="IS_COL_HEA_FIL")
	private Integer isColHeaFil;
	
	//外部JS引用
	@Column(name="EXTERNAL_JS")
	private String externalJs;
	
	//是否默认查询
	@Column(name="IS_DEFAULT_QRY")
	private Integer isDefaultQry;
	
	//是否自动刷新
	@Column(name="IS_REFRESH")
	private Integer isRefresh;
	
	//全局条件
	@Column(name="GLOBAL_WHERE")
	private String globalWhere;
	
	//使用状态
	@Column(name="USE_STATE")
	private Integer useState;
	
	//备注
	@Column(name="REMARE")
	private String remark;
	
	@Column(name="RECORD_STATE")
	private Integer recordState = AppConstant.START;
	
	@Column(name="UPDATED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	@Column(name="UPDATED_USER_ID")
	private String updatedUserId;
	
	@Column(name="CREATED_TIME", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name="CREATED_USER_ID", updatable=false)
	private String createdUserId;
	
	//查询表
	@Transient
	private Table table = new Table();
	
	//选择结果集
	@OneToMany(mappedBy="query", cascade={CascadeType.REMOVE})
	private List<Select> selects = new ArrayList<Select>();
	
	//来源结果集
	@OneToMany(mappedBy="query", cascade={CascadeType.REMOVE})
	private List<From> froms = new ArrayList<From>();
	
	//条件结果集
	@OneToMany(mappedBy="query", cascade={CascadeType.REMOVE})
	private List<Where> wheres = new ArrayList<Where>();
	
	//排序结果集
	@OneToMany(mappedBy="query", cascade={CascadeType.REMOVE})
	private List<OrderBy> orderBys = new ArrayList<OrderBy>();
	
	//分组结果集
	@OneToMany(mappedBy="query", cascade={CascadeType.REMOVE})
	private List<GroupBy> groupBys = new ArrayList<GroupBy>();
	
	//按钮结果集
	@OneToMany(mappedBy="query", cascade={CascadeType.REMOVE})
	private List<Button> buttons = new ArrayList<Button>();

	//操作标志
	@Transient
	private String oprtFlag = QueryConstant.QRY_OPRT;

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getDsJndi() {
		return dsJndi;
	}

	public void setDsJndi(String dsJndi) {
		this.dsJndi = dsJndi;
	}

	public Integer getIsColHeaFil() {
		return isColHeaFil;
	}

	public void setIsColHeaFil(Integer isColHeaFil) {
		this.isColHeaFil = isColHeaFil;
	}

	public String getExternalJs() {
		return externalJs;
	}

	public void setExternalJs(String externalJs) {
		this.externalJs = externalJs;
	}

	public Integer getIsDefaultQry() {
		return isDefaultQry;
	}

	public void setIsDefaultQry(Integer isDefaultQry) {
		this.isDefaultQry = isDefaultQry;
	}

	public Integer getIsRefresh() {
		return isRefresh;
	}

	public void setIsRefresh(Integer isRefresh) {
		this.isRefresh = isRefresh;
	}

	public String getGlobalWhere() {
		return globalWhere;
	}

	public void setGlobalWhere(String globalWhere) {
		this.globalWhere = globalWhere;
	}

	public Integer getUseState() {
		return useState;
	}

	public void setUseState(Integer useState) {
		this.useState = useState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRecordState() {
		return recordState;
	}

	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(String updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public List<Select> getSelects() {
		return selects;
	}

	public void setSelects(List<Select> selects) {
		this.selects = selects;
	}

	public List<From> getFroms() {
		return froms;
	}

	public void setFroms(List<From> froms) {
		this.froms = froms;
	}

	public List<Where> getWheres() {
		return wheres;
	}

	public void setWheres(List<Where> wheres) {
		this.wheres = wheres;
	}

	public List<OrderBy> getOrderBys() {
		return orderBys;
	}

	public void setOrderBys(List<OrderBy> orderBys) {
		this.orderBys = orderBys;
	}

	public List<GroupBy> getGroupBys() {
		return groupBys;
	}

	public void setGroupBys(List<GroupBy> groupBys) {
		this.groupBys = groupBys;
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	public String getOprtFlag() {
		return oprtFlag;
	}

	public void setOprtFlag(String oprtFlag) {
		this.oprtFlag = oprtFlag;
	}

	public Query getBase(){
		this.getSelects().clear();
		this.getFroms().clear();
		this.getWheres().clear();
		this.getOrderBys().clear();
		this.getGroupBys().clear();
		this.getButtons().clear();
		return this;
	}

	public String getTableWidth(){
		
		int tableWidth = 0;
		for(Select select: selects){

			String colWidth = select.getColWidth();
			if(DataValidater.isStrEmpty(colWidth)){
				return QueryConstant._100PERCENT;
			}
			if(colWidth.contains(QueryConstant.PERCENT_SIGN)){
				return QueryConstant._100PERCENT;
			}
			colWidth = colWidth.toLowerCase().replace(QueryConstant.PX_UNIT, QueryConstant.EMPTY_STR);
			if(!DataValidater.isStrInteger(colWidth)){
				return QueryConstant._100PERCENT;
			}
			tableWidth += DataConverter.stringToInteger(colWidth);
		}
	
		return tableWidth+QueryConstant.PX_UNIT;
	}
	
	/* 
	 * 取得"查询列"，
	 * 
	 * @param void
	 * @return List<>
	 */
	public List<com.amway.frm.query.vo.Column> getShowColumns() {
		
		List<com.amway.frm.query.vo.Column> columns = new ArrayList<com.amway.frm.query.vo.Column>();
		
		for(Select select: selects){

			com.amway.frm.query.vo.Column column = new com.amway.frm.query.vo.Column(select);
			columns.add(column);
		}
		
		return columns;
		
	}
	
	/**
	 * Declare：取得用户输入条件集
	 * 
	 * @param query 查询
	 * @return List<Where>
	 */
	public List<Where> getQueryUIWheres(){
		
		List<Where> userInWheres = new ArrayList<Where>();
		for(Where where: wheres){
			Integer isUserIn = where.getIsUserIn();
			if(null != isUserIn &&
					QueryConstant.WHERE_TYPE_UI.intValue() == isUserIn.intValue()){
				userInWheres.add(where);
			}
		}
		return userInWheres;
	}
	
	/**
	 * Declare：取得隐藏条件集
	 * 
	 * @param query 查询
	 * @return List<Where>
	 */
	public List<Where> getQueryHiddenWheres(){
		
		List<Where> userInWheres = new ArrayList<Where>();
		for(Where where: wheres){
			Integer isUserIn = where.getIsUserIn();
			if(null != isUserIn
					&& QueryConstant.WHERE_TYPE_HIDDEN.intValue() == isUserIn.intValue()){
				userInWheres.add(where);
			}
		}
		return userInWheres;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((oprtFlag == null) ? 0 : oprtFlag.hashCode());
		result = prime * result + ((queryId == null) ? 0 : queryId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (DataValidater.isFalse(getClass().equals(obj.getClass()))){
			return false;
		}
		Query other = (Query) obj;
		if (oprtFlag == null) {
			if (other.oprtFlag != null){
				return false;
			}
		} else if (!oprtFlag.equals(other.oprtFlag)){
			return false;
		}
		if (queryId == null) {
			if (other.queryId != null){
				return false;
			}
		} else if (!queryId.equals(other.queryId)){
			return false;
		}
		return true;
	}
}
