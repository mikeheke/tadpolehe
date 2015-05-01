package mikeheke.tadpole.frm.afw.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.afw.entity.Role;
import mikeheke.tadpole.frm.afw.service.RoleService;
import mikeheke.tadpole.frm.afw.util.AfwConstant;
import mikeheke.tadpole.frm.afw.vo.RoleVo;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.base.web.action.BaseAction;
import mikeheke.tadpole.frm.logging.service.LogService;
import mikeheke.tadpole.frm.logging.util.LogFactory;

import org.apache.commons.lang.StringUtils;

/**
 * Declare：角色Action
 * @author Feng Hanhao
 * Created date: 2011-5-28
 */
public class RoleAction extends BaseAction{

	private static final long serialVersionUID = 3303542819013987002L;
	
	private static LogService logger = LogFactory.getLogger(RoleAction.class);
	
	//业务Service
	private RoleService roleService;
	
	private RoleVo roleVo;
	
	
	@Override
	public String initAdd(){
		
		String result = INIT_ADD_SUCCESS;
		this.setMessage(AppConstant.OPRT, AppConstant.ADD_OPRT);
		
		return result;
	}
	
	@Override
	public String initModify() {
		
		String result = INIT_MODIFY_SUCCESS;
		this.setMessage(AppConstant.OPRT, AppConstant.MDF_OPRT);
		
		String [] roleIds = null;
		if(null != roleVo){
			roleIds = roleVo.getRoleIds();
		}
		if(!validateIds0(roleIds)){
			return INIT_MODIFY_INPUT;
		}
		
		
		Role role = this.getEntity(roleIds[0]);
		ReturnMessage<Role> returnMessage = roleService.query(role);
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
		
		return result;
	}
	

	@Override
	public String add() {

		String result = ADD_SUCCESS;
		
		Role role = this.getEntity();
		ReturnMessage<Role> returnMessage = new ReturnMessage<Role>();
		
		if(!validateData()){
			returnMessage.setReturnObject(role);
			setReturnMessage(returnMessage);
			return ADD_INPUT;
		}
		
//		if (StringUtils.isBlank(role.getRoleId())) {
//			role.setRoleId(ContextFactory.getUUID());
//		}
		
		returnMessage = roleService.addRole(role);
		if(returnMessage.isSuccess()){
			returnMessage.clearReturnObjects();
		}
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
		
		final String msg = "创建角色";
		logger.writeOperLog(AppConstant.ADD_OPRT, msg + role.getRoleCode());
		
		return result;
	}
	

	
	@Override
	public String modify(){
		
		String result = MDF_SUCCESS;

		Role role = this.getEntity();
		ReturnMessage<Role> returnMessage = new ReturnMessage<Role>();
		
		if(!validateData()){
			returnMessage.setReturnObject(role);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		String roleId = roleVo.getRoleId();
		if(!validateId(roleId)){
			returnMessage.setReturnObject(role);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		
		returnMessage = roleService.updateRole(role);
		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		final String msg = "修改角色";
		logger.writeOperLog(AppConstant.MDF_OPRT, msg + role.getRoleCode());
		
		return result;
	}
	
	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] roleIds = null;
		if(null != roleVo){
			roleIds = roleVo.getRoleIds();
		}
		if(validateIds(roleIds)){

			List<Role> roles = this.getEntities(roleIds);
			ReturnMessage<Role> returnMessage = roleService.logicDeleteList(roles);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		
		return JSON;
	}
	
	@Override
	protected Role getEntity() {
		
		Role role = new Role();
		role.setRoleId( (roleVo.getRoleId()));
		role.setRoleCode(roleVo.getRoleCode());
		role.setRoleName(roleVo.getRoleName());
		Application application = new Application();
		application.setApplicationId((roleVo.getApplicationId()));
		role.setApplication(application);
		role.setState(DataConverter.stringToInteger(roleVo.getState()));
		role.setUserSql(roleVo.getBdsCode());
		role.setRemark(roleVo.getRemark());
		role.setCreatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		role.setCreatedTime(new Date());
		role.setUpdatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		role.setUpdatedTime(new Date());
		
		return role;
		
	}

	@Override
	protected List<Role> getEntities(String[] roleIds){
		
		List<Role> roles = new ArrayList<Role>(); 
		
		for(String roleId: roleIds){
			Role role = new Role();
			role.setRoleId((roleId));
			roles.add(role);
		}
		
		return roles;
	}

	@Override
	protected Role getEntity(String roleId) {
		
		Role role = new Role();
		role.setRoleId((roleId));
		
		return role;
	}

	/**
	 * 表单输入校验
	 * @return
	 */
	protected boolean validateData() {
		
		boolean result = true;
		if(DataValidater.isStrEmpty(roleVo.getRoleCode())){
			result = setInputMessage(AfwConstant.ROLE_CODE_KEY, AfwConstant.ROLE_CODE_MSG);
		}
		if(DataValidater.isStrEmpty(roleVo.getRoleName())){
			result = setInputMessage(AfwConstant.ROLE_NAME_KEY, AfwConstant.ROLE_NAME_MSG);
		}
		if(DataValidater.isStrEmpty(roleVo.getApplicationId())){
			result = setInputMessage(AfwConstant.APPLICATION_ID_KEY, AfwConstant.APPLICATION_ID_MSG);
		}
		if(DataValidater.isStrEmpty(roleVo.getState())){
			result = setInputMessage(AfwConstant.STATE_KEY, AfwConstant.STATE_MSG);
		}
		
		return result;
	}

	@Override
	public String getJsonValue() {

		return super.getJsonValue();
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public RoleVo getRoleVo() {
		return roleVo;
	}

	public void setRoleVo(RoleVo roleVo) {
		this.roleVo = roleVo;
	}
}
