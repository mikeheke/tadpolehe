/**
 * 
 */
package mikeheke.tadpole.frm.tag.web.tag;

import java.util.List;
import java.util.Map;

import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.tag.util.TagConstant;


/**
 * 单选标签
 * 
 */
public class RadioTag extends SelectTag {

	private static final long serialVersionUID = -5196480943592588920L;

	@Override
	protected void addHtmlControl(StringBuffer buf, List<Map> datas) {
		
		buf.append(TagConstant.TAG_DIV_BEGIN).append(TagConstant.EMPTY_ONE_STR);
		buf.append(generateControlHeader());
		buf.append(TagConstant.RIGHT_J_KUO).append(TagConstant.ENTER_SIGN);
		buf.append(generateControlBody(datas));
		buf.append(TagConstant.TAG_DIV_END).append(TagConstant.ENTER_SIGN);
	}
	
	@Override
	protected StringBuffer generateControlHeader() {
		
		StringBuffer buf = new StringBuffer();
		buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.ID);
		buf.append(TagConstant.EQUAL_SIGN).append(TagConstant.QUO_D_SIGN);
		buf.append(getId()).append(TagConstant.QUO_D_SIGN);
		if(!DataValidater.isStrEmpty(getParentItem())){
			buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TITLE);
			buf.append(TagConstant.EQUAL_SIGN).append(TagConstant.QUO_S_SIGN);
			buf.append(generateThisAndSuperProps(this.getClass(),
					new String[]{TagConstant.ID, TagConstant.VALUE}));
			buf.append(TagConstant.QUO_S_SIGN);
			buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.VALUE);
			buf.append(TagConstant.EQUAL_SIGN).append(TagConstant.QUO_D_SIGN);
			buf.append(getValue()).append(TagConstant.QUO_D_SIGN);
		}
		return buf;
	}

	@Override
	protected StringBuffer generateControlBody(List<Map> datas) {
		
		StringBuffer buf = new StringBuffer();
	
		int i = 0;
		for(Map<String, String> data : datas){
			String key = data.get(getListKey());
			String value = data.get(getListValue());
			buf.append(TagConstant.LEFT_J_KUO).append(TagConstant.TAG_INPUT);
			buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TYPE);
			buf.append(TagConstant.EQUAL_SIGN).append(TagConstant.QUO_D_SIGN);
			buf.append(getControlType()).append(TagConstant.QUO_D_SIGN);
			buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.ID);
			buf.append(TagConstant.EQUAL_SIGN).append(getId());
			buf.append(TagConstant.UNDER_SEP).append(i++);
			buf.append(generateThisAndSuperProps(this.getClass(),
					new String[] {TagConstant.ID, TagConstant.VALUE}));
			if(isDefaultValue(key) || isDefaultValue(value)){
				buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.CHECKED);
				buf.append(TagConstant.EQUAL_SIGN).append(TagConstant.QUO_D_SIGN);
				buf.append(TagConstant.CHECKED).append(TagConstant.QUO_D_SIGN).append(TagConstant.EMPTY_ONE_STR);
			}
			buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.VALUE).append(TagConstant.EQUAL_SIGN);
			buf.append(TagConstant.QUO_D_SIGN).append(key).append(TagConstant.QUO_D_SIGN);
			buf.append(TagConstant.TAG_END);
			buf.append(value);
			buf.append(TagConstant.ENTER_SIGN);
		}
		
		return buf;
	}

	protected String getControlType(){
		return TagConstant.RADIO;
	}
}
