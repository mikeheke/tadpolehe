/**
 * 
 */
package com.amway.frm.afw.web.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.afw.service.RoleRightService;
import com.amway.frm.afw.service.RoleService;
import com.amway.frm.afw.service.RoleUserService;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.SysInfoBean;
import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 权限按钮标签
 * 
 * 
 *
 * 2011-5-11 下午02:44:18
 */
public class RoleRightTreeTag extends ComponentTagSupport {
	
	public static final String RIHGT_ROOT = "0";
	public static final String RIHGT_ROOT_P = "-1";
	public static final String RIHGT_ROOT_N = "";
	public static final String RIHGT_TYPE_M = "0";
	public static final String RIHGT_TYPE_B = "1";
	public static final String RIHGT_M_N = "（模块）";
	public static final String RIHGT_B_N = "（按钮）";

	private RoleRightService roleRightService;
	
	private RoleService roleService;
	
	private RoleUserService roleUserService;
	
	private HttpServletRequest request;
	
	private String imgPath;
	
	private boolean check;
	
	private String treeName;
	
	//private HttpServletResponse response;
	
	public RoleRightTreeTag(){
		
		final String beanName = "RoleRightService";
		RoleRightService roleRightService = (RoleRightService) ContextFactory.getBean(beanName);
		this.roleRightService = roleRightService;
		
		final String beanName2 = "RoleService";
		RoleService roleService = (RoleService)ContextFactory.getBean(beanName2);
		this.roleService = roleService;
		
		final String beanName3 = "RoleUserService";
		RoleUserService roleUserService = (RoleUserService) ContextFactory.getBean(beanName3);
		this.roleUserService = roleUserService;

	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 3922798959661550137L;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		this.request = req;
		//this.response = res;
		return new Component(stack);
	}
	
	/**
	 * 取标签体
	 * @param void
	 * @return String
	 */
	@Override
	protected String getBody() {
		return generateHtmlControl().toString();
	}
	
	private StringBuffer generateHtmlControl() {
		
		StringBuffer tree = new StringBuffer();
		
		this.appendTreePre(tree);
		
		this.initTree(tree);
		
		this.addNodesToTree(tree);
		
		this.appendTreeAft(tree);
		
		return tree;
	}
	
	protected void initTree(StringBuffer tree){
		final String VAR = "var ";
		tree.append(VAR).append(treeName);
		final String DTREE = "=new dTree('";
		tree.append(DTREE).append(treeName);
		tree.append(AfwConstant.QUO_S_SIGN).append(AfwConstant.DOU_SIGN);
		tree.append(check);
		tree.append(AfwConstant.DOU_SIGN).append(AfwConstant.QUO_S_SIGN);
		tree.append(imgPath).append(AfwConstant.QUO_S_SIGN);
		tree.append(AfwConstant.RIGHT_X_KUO).append(AfwConstant.FEN_SIGN);
		tree.append(AfwConstant.ENTER_SIGN);
		
	}
	
	
	protected void appendTreePre(StringBuffer tree){
		
		final String DIV_BEGIN = "<div id='";
		tree.append(DIV_BEGIN).append(treeName);
		final String CLASS = "' class=\"dtree\">\n";
		tree.append(CLASS);
		tree.append(AfwConstant.JS_BEGIN).append(AfwConstant.ENTER_SIGN);

	}
	
	protected void appendTreeAft(StringBuffer tree){
		
		final String js1 = "document.write(";
		tree.append(js1).append(treeName).append(AfwConstant.RIGHT_X_KUO);
		tree.append(AfwConstant.FEN_SIGN).append(AfwConstant.ENTER_SIGN);
		final String js2 = "try{setBroTreeV();}catch(e){}\n";
		tree.append(js2);
		tree.append(AfwConstant.JS_END).append(AfwConstant.ENTER_SIGN);
		tree.append(AfwConstant.TAG_DIV_END).append(AfwConstant.ENTER_SIGN);
	}
	
	protected void addNodesToTree(StringBuffer tree){
		
		List<Module> curUserRights = this.getCurUserRightList();
		List<Module> selRoleRights = this.getSelRoleRights();
		recursiveNodesToTree(tree, curUserRights, selRoleRights);
	}
	
	private void recursiveNodesToTree(StringBuffer tree, 
			List<Module> curUserRights, List<Module> selRoleRights){
		
		final String js_add = ".add('";
		for(Module curUserRight: curUserRights){
			tree.append(treeName).append(js_add);
			tree.append(curUserRight.getModuleId());
			tree.append(AfwConstant.QUO_S_SIGN).append(AfwConstant.DOU_SIGN).append(AfwConstant.QUO_S_SIGN);
			tree.append(getPModuleCode(curUserRight));
			tree.append(AfwConstant.QUO_S_SIGN).append(AfwConstant.DOU_SIGN).append(AfwConstant.QUO_S_SIGN);
			tree.append(curUserRight.getModuleName());
			if(check){
				if(isSelRoleRight(curUserRight, selRoleRights)){
					tree.append(AfwConstant.QUO_S_SIGN).append(AfwConstant.DOU_SIGN).append(AfwConstant.QUO_S_SIGN);
					tree.append(AfwConstant.CHECKED);
				}else{
					tree.append(AfwConstant.QUO_S_SIGN).append(AfwConstant.DOU_SIGN).append(AfwConstant.QUO_S_SIGN);
				}
			}
			tree.append(AfwConstant.QUO_S_SIGN).append(AfwConstant.RIGHT_X_KUO).append(AfwConstant.FEN_SIGN);
			tree.append(AfwConstant.ENTER_SIGN);
		}
	}
	
	private boolean isSelRoleRight(Module curUserRight, List<Module> selRoleRights){
		
		boolean result = false;
		
		for(Module selRoleRight: selRoleRights){
			if(curUserRight.getModuleCode().equals(selRoleRight.getModuleCode())){
				result = true;
				break;
			}
		}
		
		return result;
	}
	
	private String getPModuleCode(Module curUserRight){
		
		String pModuleCode = AfwConstant.EMPTY_STR;
		if(curUserRight.getModuleCode().equals(curUserRight.getParentModule().getModuleCode())){
			pModuleCode = RIHGT_ROOT;
		}else{
			pModuleCode = curUserRight.getParentModule().getModuleId()+AfwConstant.EMPTY_STR;
		}
		
		return pModuleCode;
	}
	
	private void appendTopNode(List<Module> modules){
		
		Module topNode = new Module();
		topNode.setModuleId((RIHGT_ROOT));
		topNode.setModuleCode(RIHGT_ROOT);
		Module topPNode = new Module();
		topPNode.setModuleId((RIHGT_ROOT_P));
		topPNode.setModuleCode(RIHGT_ROOT_P);
		topNode.setParentModule(topPNode);
		topNode.setModuleName(RIHGT_ROOT_N);
		modules.add(0, topNode);
	
	}
	
	private List<Module> getCurUserRightList(){
	
		Application application = getApplication();
		if(null == application){
			return new ArrayList<Module>();
		}
		
		UserProfile userProfile = SysInfoBean.getSysInfoBean().getUserProfile();
		List<Role> curRoles = SysInfoBean.getSysInfoBean().getRoles();
		ReturnMessage<Module> returnMessage = null;
		if(SysInfoBean.getSysInfoBean().isSuperRole(curRoles)){
			returnMessage = roleRightService.getRightList(application);
		}else{
			List<Role> roles = roleUserService.getRoleList(userProfile, application).getReturnObjects();
			returnMessage = roleRightService.getRightList(roles);
		}
		
		List<Module> modules = returnMessage.getReturnObjects();
		
		this.appendTopNode(modules);
		
		return modules;
	}
	
	private Application getApplication(){
		
		Application application = null;
		final String applicationIdKey = "roleRightVo.applicationId";
		String applicationId = request.getParameter(applicationIdKey);
		//if(DataValidater.isStrLong(applicationId)){
		if (!StringUtils.isBlank(applicationId)) { //modify by Mike He 20150424
			application = new Application();
			application.setApplicationId((applicationId));
		}
		
		return application;
	}
	
	private List<Module> getSelRoleRights(){
		
		Role role = getRole();
		ReturnMessage<Module> returnMessage = roleRightService.getRightList(role);
		List<Module> modules = returnMessage.getReturnObjects();
		
		return modules;
	}
	
	private Role getRole(){
		
		Role role = new Role();
		final String key = "roleRightVo.roleId";
		String roldId = request.getParameter(key);
		role.setRoleId((roldId));
		
		return role;
	}

	public void setRoleRightService(RoleRightService roleRightService) {
		this.roleRightService = roleRightService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setRoleUserService(RoleUserService roleUserService) {
		this.roleUserService = roleUserService;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}
	
}
