
package mikeheke.tadpole.frm.tag.web.action;

import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.base.web.action.BaseAction;
import mikeheke.tadpole.frm.tag.service.AutoCompleteTagService;
import mikeheke.tadpole.frm.tag.vo.AutoComplete;

import com.opensymphony.xwork2.ActionContext;

/**
 * Tag的Ajax请求，返回josn数据
 * 
 */
public class AutoCompleteTagAction extends BaseAction{

	private static final long serialVersionUID = -7541165784955640507L;
	
	//数据源类型
	private String dsType;
	
	//基础数据服务编码
	private String source;
	
	//过滤列名
	private String filterColNames;
	
	//过滤值
	private String filterColValue;
	
	private AutoCompleteTagService autoCompleteTagService; 
	
	private String filterClass;
	
	private String filterPattern;
	
	private String limit;
	/**
	 * 根据请求参数，使用过滤列名和列值，调用基础数据服务接口获得AutoComplete的下拉提示列表
	 * @return
	 */
	public String findDatas(){
		
		ReturnMessage<String> returnMessage = null;
		AutoComplete autoComplete = getEntity();
		returnMessage = autoCompleteTagService.getAutoCompleteDatas(autoComplete);
		if(returnMessage.isSuccess()){
			setJsonValue(returnMessage.getReturnObject());
		}
		
		return JSON;
	}

	@Override
	protected AutoComplete getEntity() {

		AutoComplete autoComplete = new AutoComplete(ActionContext.getContext()
				.getValueStack());
		autoComplete.setDsType(dsType);
		autoComplete.setSource(source);
		autoComplete.setFilterColNames(filterColNames);
		autoComplete.setFilterColValue(filterColValue);
		autoComplete.setFilterClass(filterClass);
		autoComplete.setFilterPattern(filterPattern);
		autoComplete.setAutoCompleteLimit(limit);
		
		return autoComplete;
	}

	public String getDsType() {
		return dsType;
	}

	public void setDsType(String dsType) {
		this.dsType = dsType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFilterColNames() {
		return filterColNames;
	}

	public void setFilterColNames(String filterColNames) {
		this.filterColNames = filterColNames;
	}

	public String getFilterColValue() {
		return filterColValue;
	}

	public void setFilterColValue(String filterColValue) {
		this.filterColValue = filterColValue;
	}

	public void setQ(String qValue){
		setFilterColValue(qValue);
	}
	
	@Override
	public String getJsonValue() {

		return super.getJsonValue();
	}

	public void setAutoCompleteTagService(
			AutoCompleteTagService autoCompleteTagService) {
		this.autoCompleteTagService = autoCompleteTagService;
	}

	public String getFilterClass() {
		return filterClass;
	}

	public void setFilterClass(String filterClass) {
		this.filterClass = filterClass;
	}

	public String getFilterPattern() {
		return filterPattern;
	}

	public void setFilterPattern(String filterPattern) {
		this.filterPattern = filterPattern;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

}
