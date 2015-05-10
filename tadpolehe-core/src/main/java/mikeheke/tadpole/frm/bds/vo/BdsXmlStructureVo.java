/**
 * 
 */
package mikeheke.tadpole.frm.bds.vo;

import mikeheke.tadpole.frm.bds.util.BdsConstant;

/**
 * xml数据结构，每一项的固定属性
 * 
 */
public class BdsXmlStructureVo {
	
	private String id;
	//字段名
	private String name;
	//英文名称
	private String enName;
	//中文名称
	private String cnName;
	//字段类型
	private String colType;
	//是否必填
	private String required;
	//最大长度
	private String maxLen;
	//是否唯一
	private String unique;
	//关联编码
	private String refCode;
	//关联字段
	private String refCol;
	//正则表达式
	private String regex;

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

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getColType() {
		return colType;
	}

	public void setColType(String colType) {
		this.colType = colType;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getMaxLen() {
		return maxLen;
	}

	public void setMaxLen(String maxLen) {
		this.maxLen = maxLen;
	}

	public String getUnique() {
		return unique;
	}

	public void setUnique(String unique) {
		this.unique = unique;
	}

	public String getRefCol() {
		return refCol;
	}

	public void setRefCol(String refCol) {
		this.refCol = refCol;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}

	public boolean getDeleteFlag() {
		if(BdsConstant.FIXED_COL_NAME_CODE.equals(name)
				|| BdsConstant.FIXED_COL_NAME_DN.equals(name)
				|| BdsConstant.FIXED_COL_NAME_DN_EN.equals(name)
				|| BdsConstant.FIXED_COL_NAME_DN_TC.equals(name)){
			return false;
		}
		return true;
	}
}
