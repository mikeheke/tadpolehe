/**
 * 
 */
package mikeheke.tadpole.frm.tag.vo;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 自动充填标签VO
 * 
 */
public class AutoComplete extends TextField {

	// 数据来源类型（1.范围对象List<Entity>、2.范围对象List<Map>、3.范围对象JSON、4.基础数据服务
	private String dsType;
	// 指定数据来源，根据ds_type取
	private String source;
	// 过滤的列名，多个列名使用“|”分隔
	private String filterColNames;
	// 指定充填到标签体的过滤列名中的某一列列值
	private String filterColValue;
	
	private String filterClass;
	
	private String filterPattern;
	
	//黄波 2013/10/22
	public final static String LIMIT_NAME = "autoCompleteLimit";
	private String autoCompleteLimit;

	public AutoComplete(ValueStack stack) {
		super(stack);
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

	public String getAutoCompleteLimit() {
		return autoCompleteLimit;
	}

	public void setAutoCompleteLimit(String autoCompleteLimit) {
		this.autoCompleteLimit = autoCompleteLimit;
	}


}
