/**
 * 
 */
package com.amway.frm.tag.web.tag;

import javax.persistence.Column;

import com.amway.frm.tag.util.TagConstant;


/**
 * 多行文本标签
 * 
 *
 * 2011-2-18 下午02:48:01
 */
public class TextAreaTag extends TextFieldTag {

	private static final long serialVersionUID = -5196480943592588920L;
	
	//输入框行数
	@Column(name="rows")
	private String rows;
	
	//输入框列数
	@Column(name="cols")
	private String cols;

	protected void addControlBody(StringBuffer buf){
		
		buf.append(TagConstant.TAG_TEXTAREA_BEGIN);
		buf.append(TagConstant.EMPTY_ONE_STR);
		buf.append(generateThisAndSuperProps(this.getClass(), 
				new String[]{TagConstant.VALUE}));
		buf.append(TagConstant.RIGHT_J_KUO);
		Object value = getValue();
		if(value != null){
			buf.append(value.toString());
		}
		buf.append(TagConstant.TAG_TEXTAREA_END);
		buf.append(TagConstant.ENTER_SIGN);
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

}
