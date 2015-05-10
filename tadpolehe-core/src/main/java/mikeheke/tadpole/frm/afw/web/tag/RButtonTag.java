
package mikeheke.tadpole.frm.afw.web.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mikeheke.tadpole.frm.afw.entity.Module;
import mikeheke.tadpole.frm.afw.vo.SysInfoBean;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;


public class RButtonTag extends ComponentTagSupport {

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

//	private String id;
//	
//	private String name;
//
//	private String type = "submit";
//	
//	private String label = "提交";
//	
//	private String onclick;
//	
//	*//**
//	 * 
//	 *//*
//	private static final long serialVersionUID = -3097209437399331814L;
//
//	@Override
//	public Component getBean(ValueStack stack, HttpServletRequest req,
//			HttpServletResponse res) {
//		return new Component(stack);
//	}
//	
//	*//**
//	 * 取标签体
//	 * @param void
//	 * @return String
//	 *//*
//	@Override
//	protected String getBody() {
//		return generateHtmlControl().toString();
//	}
//	
//	*//**
//	 * 生成标签控件<input type='button' display:isHavingRight />
//	 * @param void
//	 * @return String
//	 *//*
//	private StringBuffer generateHtmlControl() {
//	
//		StringBuffer htmlControl = new StringBuffer();
//		
//		htmlControl.append("<input ");
//		htmlControl.append(" type='").append(getType()).append("'");
//		htmlControl.append(" id='").append(getId()).append("'");
//		htmlControl.append(" name='").append(getName()).append("'");
//		htmlControl.append(" value='").append(getLabel()).append("'");
//		htmlControl.append(" onclick='").append(getOnclick()).append("'");
//		htmlControl.append(" style='display:").append(isHavingRight()).append("'");
//		
//		return htmlControl;
//	}
//	
//	*//**
//	 * 是否拥有权限
//	 * 
//	 * @param btnName 按钮name
//	 * @return
//	 *//*
//	protected String isHavingRight() {
//		
//		String result = "none";
//		
//		List<Module> modules = SysInfoBean.getSysInfoBean().getModules();
//		for(Module module: modules){
//			String link = module.getLink();
//			if(link != null && name != null
//					&& link.equals(name)){
//				result = "";
//				break;
//			}
//		}	
//		
//		return result;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public String getLabel() {
//		return label;
//	}
//
//	public void setLabel(String label) {
//		this.label = label;
//	}
//
//	public String getOnclick() {
//		return onclick;
//	}
//
//	public void setOnclick(String onclick) {
//		this.onclick = onclick;
//	}

}
