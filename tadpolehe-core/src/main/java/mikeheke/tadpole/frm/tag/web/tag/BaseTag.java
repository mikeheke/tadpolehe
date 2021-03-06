package mikeheke.tadpole.frm.tag.web.tag;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.tag.exception.TagSysException;
import mikeheke.tadpole.frm.tag.util.TagConstant;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 标签基础类
 * 
 */

public abstract class BaseTag extends ComponentTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5527869989568985593L;

	//标识
	@Column(name="id")
	private String id = TagConstant.TAG_ID_BEF+System.nanoTime();

	//元素名
	@Column(name="name")
	private String name;
	
	//class样式表
	@Column(name="class")
	private String cssClass;
	
	//页面元素长度
	@Column(name="size")
	private String size;
	
	//元素是否可编辑
	@Column(name="disabled")
	private String disabled;
	
	//元素水平位置
	@Column(name="align")
	private String align;
	
	//元素样式
	@Column(name="style")
	private String style;
	
	//元素是否只可读
	@Column(name="readonly")
	private String readonly;
	
	//焦点离开是触发的事件
	@Column(name="onblur")
	private String onblur;
	
	//元素值改变时触发的事件
	@Column(name="onchange")
	private String onchange;
	
	//元素被双击时触发的事件
	@Column(name="ondblclick")
	private String ondblclick;
	
	//元素被点击时触发的事件
	@Column(name="onclick")
	private String onclick;
	
	//元素获得焦点是触发
	@Column(name="onfocus")
	private String onfocus;
	
	//键盘键弹起
	@Column(name="onkeyup")
	private String onkeyup;
	
	//键盘键压下
	@Column(name="onkeypress")
	private String onkeypress;
	
	//鼠标在元素上方停留时，触发的事件
	@Column(name="onmouseover")
	private String onmouseover;
	
	//鼠标在元素上方移走时，触发的事件
	@Column(name="onmouseout")
	private String onmouseout;

	// 保存于valuestack/request/session/application中的默认值KEY
	@Column(name="value")
	private Object value;

	// 返回保存于范围对象的json字符串
	@Column(name="jsonValue")
	private String jsonValue;

	// 用于生成tag的页面代码，生成服务器的URL前缀路径
	private String basePath;

	// 判断本元素是否可用的页面标识元素名拼接字符集
	private String group;
	
	//validate验证框架指定的dataType类型
	@Column(name="dataType")
	private String dataType;
	
	//正则表达式验证错误时，提示消息
	@Column(name="msg")
	private String msg;
	
	//validate验证框架指定的是否必须字段
	@Column(name="require")
	private String require;
	
	//validate验证框架指定的重复输入字段
	@Column(name="to")
	private String to;
	
	//validate验证框架指定的最小输入长度
	@Column(name="min")
	private String min;
	
	//validate验证框架指定的最大输入长度
	@Column(name="max")
	private String max;
	
	//validate验证框架指定的格式化字符
	@Column(name="format")
	private String format;
	
	//validate验证框架指定的正则表达式
	@Column(name="regexp")
	private String regexp;
	
	//执行前的javascript代码
	private String beforeSend = TagConstant.EMPTY_STR;
	
	//执行后的javascript代码
	private String complete = TagConstant.EMPTY_STR;
	
	private String filterClass = TagConstant.FILTER_CLASS;
	
	private String filterPattern = TagConstant.EMPTY_STR;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request,
			HttpServletResponse respone) {
		this.setBasePath(stack, request);
		Component com = getBean(stack);
		if(null == com){
			com = new Component(stack);
		}
		return com;
	}

	public Component getBean(ValueStack stack){
		return null;
	}
	
	@Override
	protected String getBody() {

		StringBuffer buf = null;

		if (isInGroup()) {
			buf = generateHtmlControl();
		} else {
			buf = generateHiddenInput();
		}

		return buf.toString();
	}

	/**
	 * 在stackValue范围内设置应用路径，并且获取对象默认值
	 * 
	 * @param stack
	 * @param request
	 */
	protected void setBasePath(ValueStack stack,
			HttpServletRequest request) {
		String path = request.getContextPath();
		basePath = request.getScheme()+TagConstant.MAO_SIGN+TagConstant.UNIX_SEP2
				+ request.getServerName() + TagConstant.MAO_SIGN
				+ request.getServerPort() + path;
		stack.getContext().put(TagConstant.CXT_BASE_PATH_NAME, basePath);
	}

	/**
	 * 通过保存在request范围内的group属性，判断当前用户是否具有编辑权限
	 * 
	 * @return
	 */
	protected boolean isInGroup() {
		
		if(this.group == null){
			return true;
		}
		if(TagConstant.EMPTY_STR.equals(this.group)){
			return false;
		}
		String[] groups = this.group.split(TagConstant.GROUP_SEP);
		for (String groupStr : groups) {
			String groupValue = (String)getContextScopeObjectVal(groupStr);
			if (groupValue != null && TagConstant.GROUP_VALUE.equals(groupValue)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 生成形如 id='personId' name='personName' 形式的HTML字符串，用于常用属性的生成
	 * 
	 * @param clazz
	 *            指定使用其中Filed属性的类
	 * @param excludeProps
	 *            例外属性，该集合中的字符串代表的属性名，将不加入到生成属性列表中
	 * @return HTML的元素属性字符串
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	protected String generateSelfProps(Class clazz,
			Object[] excludeProps) {
		
		StringBuffer buffer = new StringBuffer(TagConstant.EMPTY_ONE_STR);
		Field[] fields = clazz.getDeclaredFields();

		List<Object> excludePropList = DataConverter.arrayToList(excludeProps);
		for (Field field : fields) {
			String name = field.getName();
			if(null == name){
				continue;
			}
			if (excludePropList.contains(name)){
				continue;
			}
			Column column = field.getAnnotation(Column.class);
			if(null == column){
				continue;
			}
			String columnName = column.name();
			if(null == columnName){
				continue;
			}
			field.setAccessible(true);
			Object value = null;
			try {
				value = field.get(this);
				value = getI18nValue(value);
			} catch (Exception e) {
				throw new TagSysException(e);
			}
			if (value != null) {
				buffer.append(TagConstant.EMPTY_ONE_STR).append(columnName);
				buffer.append(TagConstant.EQUAL_SIGN).append(TagConstant.QUO_D_SIGN);
				buffer.append(value);
				buffer.append(TagConstant.QUO_D_SIGN).append(TagConstant.EMPTY_ONE_STR);
			}
		}
		return buffer.toString();
	}
	
	protected String generateThisAndSuperProps(Class clazz) {
		
		return generateThisAndSuperProps(clazz, null);
	}
	
	protected String generateThisAndSuperProps(Class clazz,
			Object[] excludeProps) {

		StringBuffer buffer = new StringBuffer();
		addParentClassProps(clazz, buffer, excludeProps);
		
		return buffer.toString();
	}
	
	private void addParentClassProps(Class clazz, StringBuffer buffer,
			Object[] excludeProps) {

		if (clazz.equals(ComponentTagSupport.class)) {
			return;
		}
		addParentClassProps(clazz.getSuperclass(), buffer, excludeProps);
		buffer.append(generateSelfProps(clazz, excludeProps));
		
	}
	
	protected Object getI18nValue(Object value){
		
		if(!isNeedI18n(value)){
			return value;
		}
		String valueStr = (String)value;
		valueStr = valueStr.substring(TagConstant.I18N_KEY.length());
		
		return ContextFactory.getI18nValue(valueStr);
	}
	
	protected boolean isNeedI18n(Object obj){
		
		if(null == obj){
			return false;
		}
		if(!(obj instanceof String)){
			return false;
		}
		
		return ((String)obj).startsWith(TagConstant.I18N_KEY);
	}

	protected Object getContextScopeObjectVal(String name) {
		
		Object value = null;
		if (DataValidater.isStrEmpty(name)) {
			return name;
		}
		
		value = pageContext.getAttribute(name);
		if(value != null){
			return value;
		}
		value = this.findValue(name);
		if(value != null) {
			return value;
		}
		value = pageContext.getRequest().getAttribute(name);
		if(value != null) {
			return value;
		}
		value = pageContext.getSession().getAttribute(name);
		if(value != null) {
			return value;
		}
		value = pageContext.getServletContext().getAttribute(name);
		if(value != null){
			return value;
		}

		return name;

	}

	/**
	 * 当group属性中的其中一项为true是，表单只显示标签值
	 * 
	 * @param inputName
	 * @return
	 */
	protected StringBuffer generateHiddenInput() {
		
		StringBuffer buf = new StringBuffer(TagConstant.EMPTY_ONE_STR);
		
		Object value = getValue();
		if(null == value){
			addHiddenInput(buf, value, value);
		}else if(value instanceof Object[]){
			Object[] objArr = (Object[])value;
			for(Object obj: objArr){
				addHiddenInput(buf, obj, obj);
			}
		}else if(value instanceof Collection){
			Collection objCol = (Collection)value;
			for(Object obj: objCol){
				addHiddenInput(buf, obj, obj);
			}
		}else if(value instanceof Map){
			Map objMap = (Map)value;
			Set<Entry> objSet = objMap.entrySet();
			for(Entry objEntry: objSet){
				addHiddenInput(buf, objEntry.getKey(), objEntry.getValue());
			}
		}else{
			addHiddenInput(buf, value, value);
		}
		buf.delete(buf.length()-1, buf.length());
		
		return buf;
	}
	
	protected boolean isDefaultValue(Object defaultValue){
		
		Object value = getValue();
		if(null == value || null == defaultValue){
			return false;
		}if(value instanceof Object[]){
			Object[] objArr = (Object[])value;
			for(Object obj: objArr){
				if(defaultValue.equals(obj)){
					return true;
				}
			}
		}else if(value instanceof Collection){
			Collection objCol = (Collection)value;
			return objCol.contains(defaultValue);
		}else if(value instanceof Map){
			Map objMap = (Map)value;
			return (objMap.keySet().contains(defaultValue) 
					|| objMap.values().contains(defaultValue));
			
		}else{
			if (value.toString().equals(defaultValue.toString())) {
				return true;
			}
		}
		return false;
	}
	
	private void addHiddenInput(StringBuffer buf, Object value,
			Object label){
		
		buf.append(TagConstant.LEFT_J_KUO).append(TagConstant.TAG_INPUT).append(TagConstant.EMPTY_ONE_STR);
		buf.append(TagConstant.TYPE).append(TagConstant.EQUAL_SIGN);
		buf.append(TagConstant.QUO_D_SIGN).append(TagConstant.HIDDEN).append(TagConstant.QUO_D_SIGN);
		if(null != id){
			buf.append(TagConstant.EMPTY_ONE_STR);
			buf.append(TagConstant.ID).append(TagConstant.EQUAL_SIGN);
			buf.append(TagConstant.QUO_D_SIGN).append(id).append(TagConstant.QUO_D_SIGN);
		}
		if(null != name){
			buf.append(TagConstant.EMPTY_ONE_STR);
			buf.append(TagConstant.NAME).append(TagConstant.EQUAL_SIGN);
			buf.append(TagConstant.QUO_D_SIGN).append(name).append(TagConstant.QUO_D_SIGN);
		}
		if(null != value){
			buf.append(TagConstant.EMPTY_ONE_STR);
			buf.append(TagConstant.VALUE).append(TagConstant.EQUAL_SIGN);
			buf.append(TagConstant.QUO_D_SIGN).append(value).append(TagConstant.QUO_D_SIGN);
		}
		buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TAG_END);
		if(null != label){
			buf.append(getHiddenLabel(label));
			buf.append(TagConstant.DOU_SIGN);
		}else{
			buf.append(TagConstant.EMPTY_ONE_STR);
		}
	}
	
	protected Object getHiddenLabel(Object label){
		
		return label;
	}

	/**
	 * 生成页面HTML代码
	 * 
	 * @return StringBuffer字符对象
	 */
	public abstract StringBuffer generateHtmlControl();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getOnblur() {
		return onblur;
	}

	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getOndblclick() {
		return ondblclick;
	}

	public void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}

	public String getOnfocus() {
		return onfocus;
	}

	public void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
	}

	public String getOnmouseover() {
		return onmouseover;
	}

	public void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}

	public String getOnmouseout() {
		return onmouseout;
	}

	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getJsonValue() {
		return jsonValue;
	}

	public void setJsonValue(String jsonValue) {
		this.jsonValue = jsonValue;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRegexp() {
		return regexp;
	}

	public void setRegexp(String regexp) {
		this.regexp = regexp;
	}

	public String getOnkeyup() {
		return onkeyup;
	}

	public void setOnkeyup(String onkeyup) {
		this.onkeyup = onkeyup;
	}

	public String getOnkeypress() {
		return onkeypress;
	}

	public void setOnkeypress(String onkeypress) {
		this.onkeypress = onkeypress;
	}
	
	public String getBeforeSend() {
		return beforeSend;
	}

	public void setBeforeSend(String beforeSend) {
		this.beforeSend = beforeSend;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
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
}
