
package com.amway.frm.tag.web.action;

import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.tag.service.SelectTagService;
import com.amway.frm.tag.vo.Select;
import com.opensymphony.xwork2.ActionContext;

/**
 * Tag的Ajax请求，返回josn数据
 * @author huangweijin
 */
public class SelectTagAction extends BaseAction{

	private static final long serialVersionUID = -7541165784955640507L;
	//数据字典
	private String dictCode;
	
	private String parentKey;
	
	private String parentValue;
	
	//select标签业务类
	private SelectTagService selectTagService;
	
	/**
	 * 获得页面迭代数据
	 * @return
	 */
	public String findDatas(){
		
		Select select = getEntity();
		
		ReturnMessage<String> returnMessage = null;
		returnMessage = selectTagService.getSelectTagDatas(select);
		if(returnMessage.isSuccess()){
			setJsonValue(returnMessage.getReturnObject());
		}
	
		return JSON;
	}
	
	@Override
	protected Select getEntity() {
		
		Select select = new Select(ActionContext.getContext().getValueStack());
		select.setDictCode(dictCode);
		select.setParentKey(parentKey);
		select.setParentValue(parentValue);
		
		return select;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
  
	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getParentValue() {
		return parentValue;
	}

	public void setParentValue(String parentValue) {
		this.parentValue = parentValue;
	}

	public void setSelectTagService(SelectTagService selectTagService) {
		this.selectTagService = selectTagService;
	}

	@Override
	public String getJsonValue() {
		
		return super.getJsonValue();
	}

}
