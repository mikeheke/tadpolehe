/**
 * 
 */
package mikeheke.tadpole.frm.query.vo;

/**
 * 
 *
 * 2011-4-29 下午02:22:33
 */
public class ButtonVo {

	//按钮主键ID
	private String id;
	
	//按钮主键ID数组
	private String[] ids;
	
	//按钮代码
	private String buttonNo;
	
	//按钮描述
	private String buttonName;
	
	//提交URL
	private String subUrl;
	
	//URL打开方式
	private String openType;
	
	//执行JS代码
	private String executeJs;
	
	//排序
	private String orderNo;

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

	public String getButtonNo() {
		return buttonNo;
	}

	public void setButtonNo(String buttonNo) {
		this.buttonNo = buttonNo;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getSubUrl() {
		return subUrl;
	}

	public void setSubUrl(String subUrl) {
		this.subUrl = subUrl;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getExecuteJs() {
		return executeJs;
	}

	public void setExecuteJs(String executeJs) {
		this.executeJs = executeJs;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}	
}
