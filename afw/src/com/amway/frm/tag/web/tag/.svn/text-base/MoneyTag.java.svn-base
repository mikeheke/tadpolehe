package com.amway.frm.tag.web.tag;

import javax.persistence.Column;

import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.tag.util.TagConstant;


/**
 * 货币类型标签
 * @author huangweijin
 */
public class MoneyTag extends DateTag {

	private static final long serialVersionUID = -5196480943592588920L;
	
	@Column(name="maxlength")
	private String maxLine;

	@Override
	protected String getFmtValue(Object value) {
		
		String format = getFormat();
		
		String valueStr = null;
		if(null == value || null == format){
			//valueStr = null;
		}else if(value instanceof String){
			valueStr = value.toString();
			if(DataValidater.isStrDouble(valueStr)){
				valueStr = DataConverter.fmtToNumberStr(Double.parseDouble(valueStr), format);
			}
		}else{
			valueStr = DataConverter.fmtToNumberStr(value, format);
		}
		
		return valueStr;
	}
	
	protected void addControlJs(StringBuffer buf){
		
		if(DataValidater.isStrEmpty(maxLine)){
			return;
		}
		buf.append(TagConstant.JS_BEGIN).append(TagConstant.ENTER_SIGN);
		final String FUNC_BEGIN = " restrict('";
		buf.append(FUNC_BEGIN).append(getId());
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.DOU_SIGN).append(TagConstant.QUO_S_SIGN);
		buf.append(maxLine);
		final String FUNC_END = "'); \n";
		buf.append(FUNC_END);
		buf.append(TagConstant.JS_END);
	}
	
	public String getMaxLine() {
		return maxLine;
	}

	public void setMaxLine(String maxLine) {
		this.maxLine = maxLine;
	}
}
