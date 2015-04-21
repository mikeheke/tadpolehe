package com.amway.frm.tag.web.tag;

import java.util.Date;

import com.amway.frm.base.util.DataConverter;
import com.amway.frm.tag.util.TagConstant;


/**
 * 日期标签
 * 
 */
public class DateTag extends TextFieldTag {

	private static final long serialVersionUID = -5196480943592588920L;
	
	private String oFormat;

	@Override
	public Object getValue() {
		
		Object value = super.getValue();
		
		return getFmtValue(value);
	}
	
	protected String getFmtValue(Object value){
		
		String format = getFormat();
		String valueStr = null;
		
		if(null == value || null == format){
			//valueStr = null;
		}else if(value instanceof Date){
			valueStr = DataConverter.fmtDateToDateStr((Date)value, format);
		}else if(value instanceof String){
			valueStr = DataConverter.fmtDateStrToDateStr((String)value, getoFormat(), format);
		}
		
		return valueStr;
	}

	@Override
	protected void addControlBody(StringBuffer buf) {
		
		super.addControlBody(buf);
		
		buf.append(getAttachHiddenInput());
	}
	
	private String getAttachHiddenInput(){
		
		StringBuffer buf = new StringBuffer(TagConstant.EMPTY_STR);
		
		buf.append(TagConstant.LEFT_J_KUO).append(TagConstant.TAG_INPUT).append(TagConstant.EMPTY_ONE_STR);
		buf.append(TagConstant.TYPE).append(TagConstant.EQUAL_SIGN);
		buf.append(TagConstant.QUO_D_SIGN).append(TagConstant.HIDDEN).append(TagConstant.QUO_D_SIGN);
		buf.append(TagConstant.EMPTY_ONE_STR);
		
		buf.append(TagConstant.EMPTY_ONE_STR);
		buf.append(TagConstant.ID).append(TagConstant.EQUAL_SIGN);
		buf.append(TagConstant.QUO_D_SIGN).append(TagConstant.UNDER_SEP);
		buf.append(getId()).append(TagConstant.QUO_D_SIGN);
		Object value = super.getValue();
		if(value != null){
			buf.append(TagConstant.EMPTY_ONE_STR);
			buf.append(TagConstant.VALUE).append(TagConstant.EQUAL_SIGN);
			buf.append(TagConstant.QUO_D_SIGN).append(value.toString()).append(TagConstant.QUO_D_SIGN);
		}
		buf.append(TagConstant.TAG_END).append(TagConstant.ENTER_SIGN);
		
		return buf.toString();
	}

	@Override
	protected Object getHiddenLabel(Object value) {
		return getFmtValue(value)+getAttachHiddenInput();
	}

	public String getoFormat() {
		if(oFormat == null){
			return getFormat();
		}
		return oFormat;
	}

	public void setoFormat(String oFormat) {
		this.oFormat = oFormat;
	}
	
}
