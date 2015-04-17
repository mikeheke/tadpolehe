/**
 * 
 */
package com.amway.frm.tag.web.tag;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.apache.struts2.components.Component;

import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.bds.util.BdsConstant;
import com.amway.frm.tag.service.SelectTagService;
import com.amway.frm.tag.util.TagConstant;
import com.amway.frm.tag.vo.Select;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 下拉标签
 * @author huangweijin
 *
 * 2011-2-18 下午02:48:01
 */
public class SelectTag extends BaseTag {
	
	private static final long serialVersionUID = -5196480943592588920L;

	//数据字典编码
	private Object dictCode;
	
	//父子联动的父对象元素ID
	private String parentItem;
	
	//供筛选的数据集中的父对象key值
	private String parentKey;
	
	//供筛选的数据集中的子对象ID值
	private String listKey = BdsConstant.FIXED_COL_NAME_CODE;
	
	//供筛选的数据集中的子对象显示值
	private String listValue = BdsConstant.FIXED_COL_NAME_DN;
	
	//选择标签的第一项选页名是否使用默认值
	protected boolean defaultHK = true;
	
	//选择标签的第一项选页名
	private String headerKey;
	
	//选择标签的第一项选页值
	private String headerValue;
	
	//是否多选
	@Column(name="multiple")
	private String multiple;
	
	//父子联动时，在子菜单联动前执行的javascript代码
	//private String beforeSend = "";
	
	//父子联动时，在子菜单联动后执行的javascript代码
	//private String complete = "";
	
	private SelectTagService selectTagService;
	
	@Override 
	public Component getBean(ValueStack stack) {
		
		String beanName = SelectTagService.class.getSimpleName();
		SelectTagService selectTagService = (SelectTagService) ContextFactory.getBean(beanName);
		setSelectTagService(selectTagService);
		
		return new Select(stack);
	}

	@Override
	protected void populateParams() {

		super.populateParams();
		
		Select select = (Select)component;
		select.setDictCode(DataConverter.valueOfWN(dictCode));
	}
	
	@Override
	public StringBuffer generateHtmlControl() {
		
		StringBuffer buf = new StringBuffer(TagConstant.EMPTY_STR);
		List<Map> datas = getControlDatas();
		addHtmlControl(buf, datas);
		addControlLinkJs(buf);
	
		return buf;
	}
	
	protected void addHtmlControl(StringBuffer buf, List<Map> datas) {
		
		buf.append(TagConstant.TAG_SELECT_BEGIN).append(TagConstant.EMPTY_ONE_STR);
		buf.append(generateThisAndSuperProps(this.getClass(),
				new String[] {TagConstant.VALUE}));
		buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.RIGHT_J_KUO).append(TagConstant.EMPTY_ONE_STR);
		buf.append(TagConstant.ENTER_SIGN);
		buf.append(generateControlHeader());
		buf.append(generateControlBody(datas));
		buf.append(TagConstant.TAG_SELECT_END).append(TagConstant.ENTER_SIGN);

	}
	
	protected StringBuffer generateControlHeader(){
		
		StringBuffer buf = new StringBuffer();
		if(defaultHK){
			setHeaderKey(TagConstant.DEFAULT_HEADER_KEY);
			setHeaderValue(TagConstant.DEFAULT_HEADER_TEXT);
		}
		if(null != headerKey || null != headerValue){
			buf.append(TagConstant.TAG_OPTION_BEGIN).append(TagConstant.EMPTY_ONE_STR);
			buf.append(TagConstant.VALUE).append(TagConstant.EQUAL_SIGN).append(TagConstant.QUO_D_SIGN);
			buf.append(headerKey).append(TagConstant.QUO_D_SIGN).append(TagConstant.RIGHT_J_KUO);
			buf.append(headerValue).append(TagConstant.TAG_OPTION_END).append(TagConstant.ENTER_SIGN);
		}
		
		return buf;
	}
	
	protected StringBuffer generateControlBody(List<Map> datas) {
		
		StringBuffer buf = new StringBuffer();
		
		for(Map data : datas){
			Object key = data.get(listKey);
			Object value = data.get(listValue);
			buf.append(TagConstant.TAG_OPTION_BEGIN).append(TagConstant.EMPTY_ONE_STR);
			buf.append(TagConstant.VALUE).append(TagConstant.EQUAL_SIGN).append(TagConstant.QUO_D_SIGN);
			buf.append(key).append(TagConstant.QUO_D_SIGN);
			if(isDefaultValue(key) || isDefaultValue(value)){
				buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.SELECTED);//默认选中
			}
			buf.append(TagConstant.RIGHT_J_KUO).append(value).append(TagConstant.TAG_OPTION_END);
			buf.append(TagConstant.ENTER_SIGN);
		}
		
		return buf;
	}
	
	protected void addControlLinkJs(StringBuffer buf){
		
		if(parentItem == null) {
			return;
		}
		if(!isInGroup()){
			return;
		}
		buf.append(TagConstant.JS_BEGIN).append(TagConstant.ENTER_SIGN);
		final String params = TagConstant.QUO_D_SIGN + getParentItem() 
			+ TagConstant.QUO_D_SIGN + TagConstant.DOU_SIGN + TagConstant.QUO_D_SIGN + getId() 
			+ TagConstant.QUO_D_SIGN + TagConstant.DOU_SIGN + TagConstant.QUO_D_SIGN + getBasePath()
			+ TagConstant.QUO_D_SIGN + TagConstant.DOU_SIGN + TagConstant.QUO_D_SIGN + getParentKey()
			+ TagConstant.QUO_D_SIGN + TagConstant.DOU_SIGN + TagConstant.QUO_D_SIGN + getDictCode()
			+ TagConstant.QUO_D_SIGN + TagConstant.DOU_SIGN + TagConstant.QUO_D_SIGN + getListKey() 
			+ TagConstant.QUO_D_SIGN + TagConstant.DOU_SIGN + TagConstant.QUO_D_SIGN + getListValue() 
			+ TagConstant.QUO_D_SIGN + TagConstant.DOU_SIGN + TagConstant.QUO_D_SIGN + getHeaderKey()
			+ TagConstant.QUO_D_SIGN + TagConstant.DOU_SIGN + TagConstant.QUO_D_SIGN + getHeaderValue()
			+ TagConstant.QUO_D_SIGN + TagConstant.DOU_SIGN + TagConstant.QUO_D_SIGN + getValue()
			+ TagConstant.QUO_D_SIGN + TagConstant.DOU_SIGN + TagConstant.QUO_D_SIGN + getBeforeSend()
			+ TagConstant.QUO_D_SIGN + TagConstant.DOU_SIGN + TagConstant.QUO_D_SIGN + getComplete()
			+ TagConstant.QUO_D_SIGN;
		final String FUNC1_BEGIN = "getDataByParent(";
		final String FUNC_END = ");\n";
		buf.append(FUNC1_BEGIN).append(params).append(FUNC_END);
		final String FUNC2_BEGIN = "attacheParentEvent(";
		buf.append(FUNC2_BEGIN).append(params).append(FUNC_END);
		buf.append(TagConstant.JS_END);
	}
	
	protected List<Map> getControlDatas(){
		
		if(parentItem != null){
			return new ArrayList<Map>();
		}
	
		return getControlDatas2();
	}
	
	protected List<Map> getControlDatas2(){
		
		List<Map> datas = getDatas(dictCode);
		if(!DataValidater.isCollectionEmpty(datas)){
			return datas;
		}
		if(dictCode instanceof String){
			datas = getDatas(getContextScopeObjectVal((String)dictCode));
		}
				
		return datas;
	}
	
	private List<Map> getDatas(Object dictCode){
		
		List<Map> datas = null;
		
		if(null == dictCode){
			return new ArrayList<Map>();
		}else if(dictCode instanceof Collection){
			datas = selectTagService
					.getDatasFromScope((Collection) dictCode);
		}else{
			datas = selectTagService
					.getDatasFromBDS((Select) component);
		}
		
		return datas;
	}
	
	@Override
	protected Object getHiddenLabel(Object label) {
		
		List<Map> datas = getControlDatas2();
		for(Map data : datas){
			Object key = data.get(listKey);
			Object value = data.get(listValue);
			if(isDefaultValue(key) || isDefaultValue(value)){
				return value;
			}
		}
		
		return label;
	}

	public Object getDictCode() {
		return dictCode;
	}

	public void setDictCode(Object dictCode) {
		this.dictCode = dictCode;
	}

	public String getParentItem() {
		return parentItem;
	}

	public void setParentItem(String parentItem) {
		this.parentItem = parentItem;
	}

	public String getParentKey() {
		if(DataValidater.isStrEmpty(parentKey)){
			return getParentItem();
		}
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getListKey() {
		return listKey;
	}

	public void setListKey(String listKey) {
		this.listKey = listKey;
	}

	public String getListValue() {
		return listValue;
	}

	public void setListValue(String listValue) {
		this.listValue = listValue;
	}

	public boolean isDefaultHK() {
		return defaultHK;
	}

	public void setDefaultHK(boolean defaultHK) {
		this.defaultHK = defaultHK;
	}

	public String getHeaderKey() {
		return headerKey;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public void setSelectTagService(SelectTagService selectTagService) {
		this.selectTagService = selectTagService;
	}
	
}
