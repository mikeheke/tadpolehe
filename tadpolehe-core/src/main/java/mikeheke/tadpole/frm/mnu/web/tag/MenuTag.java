/**
 * 
 */
package mikeheke.tadpole.frm.mnu.web.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mikeheke.tadpole.frm.afw.util.AfwConstant;
import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.mnu.service.MenuItemService;
import mikeheke.tadpole.frm.mnu.vo.MenuItem;
import mikeheke.tadpole.frm.mnu.vo.TreeNode;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 菜单父标签，子类实现generateMenuControl方法
 * 
 *
 * 2011-4-25 下午04:42:48
 */
public abstract class MenuTag extends ComponentTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7266224679706905441L;
	
	//业务Service
	protected MenuItemService menuItemService;
	
	//用户代码
	private String user;
	
	//应用代码
	private String app;
	
	//模块代码
	private String module;
	
	//类型
	private String type;
	
	//图片路径
	private String imgPath;
	
	//js路径
	private String jsPath;
	
	//类名
	private String className;
	
	public static final String MAIN_FRAME = "main";
	public static final String LEFT_FRAME = "left";
	public static final String TOP_FRAME = "top";
	public static final String BOTTOM_FRAME = "bottom";
	
	
	public MenuTag(){
		final String beanName = "MenuItemService";
		menuItemService = (MenuItemService) ContextFactory.getBean(beanName);
	}
	
	 /**
     * Declare：生成菜单Bean
     *
	 * @param  stack  stack
	 * @param  req  请求
	 * @param  res 应答
     * @return Component 菜单组件
     */
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new MenuItem(stack);
	}

	 /**
     * Declare：设置菜单参数
     *
	 * @param  void
     * @return void
     */
	@Override
	protected void populateParams() {
		super.populateParams();
		
		MenuItem tree = (MenuItem)component;
		tree.setUser(user);
		tree.setApp(app);
		tree.setModule(module);
		tree.setType(type);
	}
	
	/**
     * Declare：生成菜单体
     *
	 * @param  void
     * @return String 菜单体
     */
	@Override
	protected String getBody() {
		
		String bodyRet = AfwConstant.EMPTY_STR;
		
		List<TreeNode> treeNodes = menuItemService.getMenusFromDB((MenuItem)component);
	
		StringBuffer tree = this.generateMenuControl(treeNodes);
		
		bodyRet = tree.toString();
		
		return bodyRet;
	}
	
	/**
     * Declare：生成菜单
     *
	 * @param  treeNodes 菜单节点结果集
     * @return String 菜单
     */
	public abstract StringBuffer generateMenuControl(List<TreeNode> treeNodes);

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getJsPath() {
		return jsPath;
	}

	public void setJsPath(String jsPath) {
		this.jsPath = jsPath;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
