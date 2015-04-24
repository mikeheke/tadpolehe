package com.amway.frm.afw.web.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleRight;
import com.amway.frm.afw.service.RoleRightService;
import com.amway.frm.afw.service.RoleService;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.RoleRightVo;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：角色权限Action
 */
public class RoleRightAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -163506281399408097L;
	
	private static LogService logger = LogFactory.getLogger(RoleRightAction.class);
	
	private RoleRightService roleRightService;
	
	private RoleService roleService;

	private RoleRightVo roleRightVo;
	
	@Override
	public String init() {
		Application application = (Application) ServletActionContext.getRequest().getSession()
				.getAttribute(AppConstant.APPLICATION_NAME);
		String appid=application.getApplicationId();
		//if(appid==1){
		if("1".equals(appid)){
			this.setRoleRightVo(null);
		}
		else{
			roleRightVo=new RoleRightVo();
			roleRightVo.setApplicationId(appid+"");
			return super.query();
		}
		return super.init();
	}

	@Override
	public String query() {
		//if(!DataValidater.isStrLong(roleRightVo.getApplicationId())){
		if (StringUtils.isBlank(roleRightVo.getApplicationId())) { //modify by Mike He 20150424
			final String msg = "请选择应用";
			this.setMessage(AfwConstant.ID_MSG, msg);
		}
		return super.query();
	}

	@Override
	public String popup() {
		
		if (StringUtils.isBlank(roleRightVo.getApplicationId())) { //modify by Mike He 20150424
			final String msg = "请选择应用";
			this.setMessage(AfwConstant.ID_MSG, msg);
		}
		return super.popup();
	}

	@Override
	public String save() {
		String roleId = roleRightVo.getRoleId();
		if(!validateId(roleId)){
			this.setMessage(AfwConstant.ROLE_ID_KEY, AfwConstant.ROLE_ID_MSG);
			return SAVE_INPUT;
		}
		Role role = getEntity(roleId);
		List<Module> modules = new ArrayList<Module>();
		if (!AfwConstant._0_STR.equals(roleRightVo.getModuleId())) {
			modules = getEntities(roleRightVo.getModuleIds());
		}
		roleRightVo.setModuleId(null);
		
		ReturnMessage<RoleRight> returnMessage = roleRightService.disRights(role, modules);
		
		String result = this.setReturnMessage(returnMessage, SAVE_SUCCESS, SAVE_INPUT);
		
		final String key = "save";
		final String msg = "角色授权";
		logger.writeOperLog(key, msg);
		
		return result;
	}

	/**
     * Declare：点击复制权限按钮
     *
	 * @param  void
     * @return String
     */
	public String copy(){
		
		final String COPY_SUCCESS = "copySuccess";
		final String COPY_INPUT = "copyInput";
		
		String srcRoleId = roleRightVo.getSrcRoleId();
		if(!validateId(srcRoleId)){
			this.setMessage(AfwConstant.SRC_ROLE_ID_KEY, AfwConstant.SRC_ROLE_ID_MSG);
			return COPY_INPUT;
		}
		
		String desRoleId = roleRightVo.getDesRoleId();
		if(!validateId(desRoleId)){
			this.setMessage(AfwConstant.DES_ROLE_ID_KEY, AfwConstant.DES_ROLE_ID_MSG);
			return COPY_INPUT;
		}
		if(srcRoleId.equals(desRoleId)){
			this.setMessage(AfwConstant.DES_ROLE_ID_KEY, AfwConstant.DES_ROLE_ID_MSG2);
			return COPY_INPUT;
		}
		Role srcRole = this.getEntity(srcRoleId);
		Role desRole = this.getEntity(desRoleId);
		
		ReturnMessage<RoleRight> returnMessage = roleRightService.copyRight(desRole, srcRole);
		
		String result = setReturnMessage(returnMessage, COPY_SUCCESS, COPY_INPUT);
		
		final String key = "copy";
		final String msg = "角色授权复制";
		logger.writeOperLog(key, msg);
	
		return result;
	}
	
	/**
	 * 取应用角色
	 * @return
	 */
	public List<Role> getRoles(){
		
		String applicationIdStr = roleRightVo.getApplicationId();
		if (StringUtils.isBlank(roleRightVo.getApplicationId())) { //modify by Mike He 20150424
			return new ArrayList<Role>();
		}
		Application application = new Application();
		application.setApplicationId((applicationIdStr));
		List<Role> roles = roleService.getRoleList(application).getReturnObjects();
		
		return roles;
	}
	
	
	@Override
	protected Role getEntity(String roleId) {
		
		Role role = new Role();
		role.setRoleId((roleId));
		
		return role;
	}

	@Override
	protected List<Module> getEntities(String[] moduleIds) {

		List<Module> modules = new ArrayList<Module>();
		if (moduleIds != null) {
			for (String moduleId : moduleIds) {
				Module module = new Module();
				module.setModuleId((moduleId));
				modules.add(module);
			}
		}
		return modules;
	}

	public void setRoleRightService(RoleRightService roleRightService) {
		this.roleRightService = roleRightService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RoleRightVo getRoleRightVo() {
		return roleRightVo;
	}

	public void setRoleRightVo(RoleRightVo roleRightVo) {
		this.roleRightVo = roleRightVo;
	}
}
