package com.amway.frm.tag.vo;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 选择标签
 * @author huangweijin
 */
public class Select extends Component {

	/**
	 * @param stack
	 */
	public Select(ValueStack stack) {
		super(stack);
	}

	// 数据字典编码
	protected String dictCode;
	
	// 供筛选的数据集中的父对象key值
	protected String parentKey;
	
	// 供筛选的数据集中的子对象ID值
	protected String parentValue;

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
}
