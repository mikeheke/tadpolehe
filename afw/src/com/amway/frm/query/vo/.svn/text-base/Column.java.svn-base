/**
 * 
 */
package com.amway.frm.query.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.bds.service.BaseDataSourceService;
import com.amway.frm.bds.util.BdsConstant;
import com.amway.frm.bds.vo.BaseDataSourceVo;
import com.amway.frm.query.entity.Select;
import com.amway.frm.query.util.DataServiceUtil;
import com.amway.frm.query.util.QueryConstant;

/**
 * Created by MyElipse
 * @author huangweijin
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：查询列
 */
public class Column extends Select {
	
	//别名
	private String alias;
	
	//统计
    private String calc[];
    
    //统计标题
    private String calcTitle[];
    
    //单元格
    private String cell;
    
    //单元格标题
    private String cellDisplay;
    
    //过滤选项
    private transient Object filterOptions;
    
    //是否自动格式化
    private Boolean escapeAutoFormat;
    
    //是否可过滤
    private Boolean filterable;
    
    //过滤单元格
    private String filterCell;
    
    //过滤样式
    private String filterClass;
    
    //过滤样式
    private String filterStyle;
    
    //格式
    private String format;
    
    //列头单元格
    private String headerCell;
    
    //列头样式
    private String headerClass;
    
    //列头样式
    private String headerStyle;
    
    //拦截器
    private String interceptor;
    
    //分析器
    private String parse;
    
    //属性
    private String property;
    
    //属性值
    private transient Object propertyValue;
    
    //排序
    private Boolean sortable;
    
    //样式
    private String style;
    
    //样式
    private String styleClass;
    
    //标题
    private String title;
    
    //值
    private transient Object value;
    
    //允许显示集合
    private String viewsAllowed[];
    
    //不允许显示集合
    private String viewsDenied;
    
    //列宽
    private String width;
    
    //选择对象
    private Select select;
    
    public Column(){}

	public Column(Select select){
    	this.select = select;
    	this.init();
    }
    
    private void init(){
    	this.setProperty();
    	this.setCell();
    	this.setTitle();
    	//this.setFormat();
    	this.setViewsDenied();
    	this.setWidth();
    	this.setSortable();
    	this.setBtnType(select.getBtnType());
    	this.setDataInput(select.getDataInput());
    	this.setTableName(select.getTableName());
    	this.setFieldName(select.getFieldName());
    	this.setFieldAlias(select.getFieldAlias());
    	this.setLinkUrl(select.getLinkUrl());
    	this.setOpenType(select.getOpenType());
    	this.setDataCoding(select.getDataCoding());
    	this.setDataType(select.getDataType());
    	this.setOutFormat(select.getOutFormat());
    	
    	this.setFilter();
    }
    
    private static BaseDataSourceService baseDataSourceService;
    private BaseDataSourceService getBaseDataSourceService(){
    	if(baseDataSourceService != null){
    		return baseDataSourceService;
    	}
    	String beanName = BaseDataSourceService.class.getSimpleName();
		BaseDataSourceService baseDataSourceService = (BaseDataSourceService) ContextFactory.getBean(beanName);
		return baseDataSourceService;
    }
    
    private Map<Object, Object> getDataCodingKeyValues(){
    	
    	BaseDataSourceService baseDataSourceService = getBaseDataSourceService();
		BaseDataSourceVo baseDataSourceVo = baseDataSourceService.getBdsVoData(
				this.getDataCoding(), new HashMap<String, String[]>(), null).getReturnObject();
		Map<Object, Object> keyValues = baseDataSourceVo.codingColumnKeyValue(
				BdsConstant.FIXED_COL_NAME_CODE, BdsConstant.FIXED_COL_NAME_DN);
		
		return keyValues;
    }
    
	private void setFilter() {
	
		if(DataValidater.isStrEmpty(this.getDataCoding())){
			return;
		}
		Map<Object, Object> keyValues = getDataCodingKeyValues();
		this.setFilterCell(QueryConstant.FILTER_DROPLIST_CELL);
		this.setFilterOptions(keyValues);
	}
	
	
	private void setFilterOptions(Map<Object, Object> keyValues){
		
		Collection cols = new ArrayList();
		
		if(!DataValidater.isMapEmpty(keyValues)){
			Set<Entry<Object, Object>> set = keyValues.entrySet();
			for(Entry<Object, Object> entry: set){
				Object key = entry.getKey();
				Object value = entry.getValue();
				FilterOption filterOption = new FilterOption();
				filterOption.setLabel(value);
				filterOption.setValue(key);
				cols.add(filterOption);
			}
		}
		
		this.setFilterOptions(cols);
	}
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String[] getCalc() {
		return calc;
	}
	public void setCalc(String[] calc) {
		this.calc = calc;
	}
	public String[] getCalcTitle() {
		return calcTitle;
	}
	public void setCalcTitle(String[] calcTitle) {
		this.calcTitle = calcTitle;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	private void setCell() {
		String dataType = select.getDataType();
		setCell(DataServiceUtil.getDataValue(QueryConstant.DATA_TYPE, dataType));
	}
	public String getCellDisplay() {
		return cellDisplay;
	}
	public void setCellDisplay(String cellDisplay) {
		this.cellDisplay = cellDisplay;
	}
	public Object getFilterOptions() {
		return filterOptions;
	}
	public void setFilterOptions(Object filterOptions) {
		this.filterOptions = filterOptions;
	}
	public Boolean getEscapeAutoFormat() {
		return escapeAutoFormat;
	}
	public void setEscapeAutoFormat(Boolean escapeAutoFormat) {
		this.escapeAutoFormat = escapeAutoFormat;
	}
	public Boolean getFilterable() {
		return filterable;
	}
	public void setFilterable(Boolean filterable) {
		this.filterable = filterable;
	}
	public String getFilterCell() {
		return filterCell;
	}
	public void setFilterCell(String filterCell) {
		this.filterCell = filterCell;
	}
	public String getFilterClass() {
		return filterClass;
	}
	public void setFilterClass(String filterClass) {
		this.filterClass = filterClass;
	}
	public String getFilterStyle() {
		return filterStyle;
	}
	public void setFilterStyle(String filterStyle) {
		this.filterStyle = filterStyle;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
/*	private void setFormat() {
		Integer outFormat = select.getOutFormat();
		String outFormatStr = DataConverter.IntegerToString(outFormat);
		String tmpFormat = DataServiceUtil.getDataValue(QueryConstant.DATA_FORMAT, outFormatStr);
		setFormat(tmpFormat);
	}*/
	public String getHeaderCell() {
		return headerCell;
	}
	public void setHeaderCell(String headerCell) {
		this.headerCell = headerCell;
	}
	public String getHeaderClass() {
		return headerClass;
	}
	public void setHeaderClass(String headerClass) {
		this.headerClass = headerClass;
	}
	public String getHeaderStyle() {
		return headerStyle;
	}
	public void setHeaderStyle(String headerStyle) {
		this.headerStyle = headerStyle;
	}
	public String getInterceptor() {
		return interceptor;
	}
	public void setInterceptor(String interceptor) {
		this.interceptor = interceptor;
	}
	public String getParse() {
		return parse;
	}
	public void setParse(String parse) {
		this.parse = parse;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	private void setProperty() {
		String tab = select.getTableName();
		String field = select.getFieldName();
		String alias = select.getFieldAlias();
		setProperty(tab + field + alias);
	}
	public Object getPropertyValue() {
		return propertyValue;
	}
	public void setPropertyValue(Object propertyValue) {
		this.propertyValue = propertyValue;
	}
	public Boolean getSortable() {
		return sortable;
	}
	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}
	private void setSortable() {
		Integer btnType = select.getBtnType();
		if(null == btnType){
			return;
		}
		if(QueryConstant.BTN_TYPE_TEXT.intValue() != btnType.intValue()){
			this.setSortable(false);
		}
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private void setTitle() {
		
		String des = null;
		Integer btnType = select.getBtnType();
		if(btnType != null 
				&& QueryConstant.BTN_TYPE_CHECKBOX.intValue() == btnType.intValue()){
			des = QueryConstant.HEAD_CHECKBOX;
			setSortable(false);
			setFilterable(false);
		}else{
			des = select.getDes();
		}
		setTitle(des);
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String[] getViewsAllowed() {
		return viewsAllowed;
	}
	public void setViewsAllowed(String[] viewsAllowed) {
		this.viewsAllowed = viewsAllowed;
	}
	public String getViewsDenied() {
		return viewsDenied;
	}
	public void setViewsDenied(String viewsDenied) {
		this.viewsDenied = viewsDenied;
	}
	
	private void setViewsDenied() {
		Integer isHidden = select.getIsHidden();
		if(null != isHidden 
				&& QueryConstant.YES.intValue() == isHidden.intValue()){
			setViewsDenied(QueryConstant.HTML);
		}
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	private void setWidth() {
		String width = select.getColWidth();
		if(null != width && !QueryConstant.EMPTY_STR.equals(width)){
			setWidth(width);
		}
	}
   
}
