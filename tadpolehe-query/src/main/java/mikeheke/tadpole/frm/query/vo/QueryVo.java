/**
 * 
 */
package mikeheke.tadpole.frm.query.vo;

/**
 * 
 *
 * 2011-4-29 下午02:19:59
 */
public class QueryVo {

	//主键ID
	private String id;
	
	//主键ID数组
	private String[] ids;
	
	//查询代码
	private String queryCode;
	
	//查询名称
	private String queryName;
	
	//应用ID
	private String applicationId;
	
	//数据源JNDI
	private String dsJndi;
	
	//是否显示列头过滤
	private String isColHeaFil;
	
	//外部JS引用
	private String externalJs;
	
	//是否默认查询
	private String isDefaultQry;
	
	//是否自动刷新
	private String isRefresh;
	
	//使用状态
	private String useState;
	
	//备注
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
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

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getDsJndi() {
		return dsJndi;
	}

	public void setDsJndi(String dsJndi) {
		this.dsJndi = dsJndi;
	}

	public String getIsColHeaFil() {
		return isColHeaFil;
	}

	public void setIsColHeaFil(String isColHeaFil) {
		this.isColHeaFil = isColHeaFil;
	}

	public String getExternalJs() {
		return externalJs;
	}

	public void setExternalJs(String externalJs) {
		this.externalJs = externalJs;
	}

	public String getIsDefaultQry() {
		return isDefaultQry;
	}

	public void setIsDefaultQry(String isDefaultQry) {
		this.isDefaultQry = isDefaultQry;
	}

	public String getUseState() {
		return useState;
	}

	public void setUseState(String useState) {
		this.useState = useState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsRefresh() {
		return isRefresh;
	}

	public void setIsRefresh(String isRefresh) {
		this.isRefresh = isRefresh;
	}
}
