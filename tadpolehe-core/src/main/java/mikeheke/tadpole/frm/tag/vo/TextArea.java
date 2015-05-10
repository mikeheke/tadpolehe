
package mikeheke.tadpole.frm.tag.vo;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * 多行文本输入框
 * 
 */
public class TextArea extends TextField {
	
	//输入框行数
	private String rows;
	//输入框列数
	private String cols;
	
	public TextArea(ValueStack stack) {
		super(stack);
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
