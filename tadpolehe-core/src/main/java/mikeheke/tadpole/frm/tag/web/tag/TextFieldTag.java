
package mikeheke.tadpole.frm.tag.web.tag;

import javax.persistence.Column;

import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.tag.util.TagConstant;





/**
 * 单行文本输入标签
 * 
 */
public class TextFieldTag extends BaseTag {

	private static final long serialVersionUID = -5196480943592588920L;
	
	//输入字符串长度限制,当输入超过这个字符长度时，将会有js提示，并且按照这个长度的maxline/3截断
	@Column(name="maxlength")
	private String maxLine;

	/**
	 * 生成页面HTML代码
	 * @return  StringBuffer字符对象
	 */
	@Override
	public StringBuffer generateHtmlControl(){
		
		StringBuffer buf = new StringBuffer(TagConstant.EMPTY_STR);
		addControlBody(buf);
		addControlJs(buf);
		
		return buf;
	}

	protected void addControlBody(StringBuffer buf){
		
		buf.append(TagConstant.TAG_INPUT_BEGIN);
		buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TYPE);
		buf.append(TagConstant.EQUAL_SIGN).append(TagConstant.TEXT);
		buf.append(TagConstant.EMPTY_ONE_STR);
		buf.append(generateThisAndSuperProps(this.getClass(),
				new String[]{TagConstant.VALUE}));
		Object value = getValue();
		if(value != null){
			buf.append(TagConstant.EMPTY_ONE_STR);
			buf.append(TagConstant.VALUE).append(TagConstant.EQUAL_SIGN);
			buf.append(TagConstant.QUO_D_SIGN).append(value.toString());
			buf.append(TagConstant.QUO_D_SIGN);
		}
		buf.append(TagConstant.TAG_END).append(TagConstant.ENTER_SIGN);
		
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
