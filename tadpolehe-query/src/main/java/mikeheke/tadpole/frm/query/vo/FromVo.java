/**
 * 
 */
package mikeheke.tadpole.frm.query.vo;

/**
 * 
 *
 * 2011-4-29 下午02:22:44
 */
public class FromVo {

	//主键ID
	private String id;
	
	//主键ID数组
	private String[] ids;
	
	//表名
	private String tableName;
	
	//表别名
	private String tableAlias;
	
	//排序
	private String orderNo;
	
	//组合结果
	private String fromResult;

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

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableAlias() {
		return tableAlias;
	}

	public void setTableAlias(String tableAlias) {
		this.tableAlias = tableAlias;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getFromResult() {
		return fromResult;
	}

	public void setFromResult(String fromResult) {
		this.fromResult = fromResult;
	}
	
}
