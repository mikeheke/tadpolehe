/**
 * 
 */
package com.amway.frm.afw.web.action;

import java.util.ArrayList;
import java.util.List;

import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleUser;
import com.amway.frm.afw.service.RoleUserService;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.RoleUserVo;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;

/**
 * 角色用户Action
 * @author huangweijin
 *
 * 2011-5-5 下午04:17:39
 */
public class RoleUserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2317731098915342917L;
	
	private static LogService logger = LogFactory.getLogger(RoleUserAction.class);

	private RoleUserService roleUserService;
	
	private RoleUserVo roleUserVo;
	
	//本地增加用户角色权限
	public static final int LOCAL_ROLE_USER = 1;

	@Override
	public String initAdd() {
		
		String roleId = roleUserVo.getRoleId();
		if(!validateId(roleId)){
			return INIT_ADD_INPUT;
		}
		Role role = this.getEntity(roleId);
		Role roleRet = (Role) roleUserService.querySingle(role);
		
		this.setMessage(AfwConstant.ROLE, roleRet);
		
		return super.initAdd();
	}
	
	@Override
	public String save() {
		
		String result = SAVE_INPUT;
		if(!validateData()){
			return SAVE_INPUT;
		}
		RoleUser roleUser = this.getEntity();
		ReturnMessage<RoleUser> returnMessage = roleUserService.addCom(roleUser);
		result = setReturnMessage(returnMessage, SAVE_SUCCESS, SAVE_INPUT);
		
		initAdd();		//初始化
		
		final String key = "save";
		final String msg = "角色分配";
		logger.writeOperLog(key, msg);
		
		return result;
	}


	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] roleUserIds = null;
		if(null != roleUserVo){
			roleUserIds = roleUserVo.getIds();
		}
		if(validateIds(roleUserIds)){

			List<RoleUser> roleUsers = this.getEntities(roleUserIds);
			ReturnMessage<RoleUser> returnMessage = roleUserService.logicDeleteList(roleUsers);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		
		return JSON;
	}

	public String addBySQL(){
		
		return SUCCESS;
	}
	
//	public List<UserProfile> getUserProfiles(){
//		UserProfile userProfile = new UserProfile();
//		return roleUserService.queryList(userProfile);
//	}
	
	@Override
	protected Role getEntity(String roleId) {
		
		Role role = new Role();
		role.setRoleId(DataConverter.stringToLong(roleId));
		
		return role;
	}

	@Override
	protected RoleUser getEntity() {
		
		RoleUser roleUser = new RoleUser();
		roleUser.setRoleId(DataConverter.stringToLong(roleUserVo.getRoleId()));
		roleUser.setUserProfileId(DataConverter.stringToLong(roleUserVo.getUserProfileId()[1]));
		roleUser.setDisplayName(roleUserVo.getUserProfileId()[0]);
		roleUser.setIsLocalRight(LOCAL_ROLE_USER);
		return roleUser;
	}

	@Override
	protected List<RoleUser> getEntities(String[] roleUserIds) {
		
		List<RoleUser> roleUsers = new ArrayList<RoleUser>();
		for(String roleUserId: roleUserIds){
			RoleUser roleUser = new RoleUser();
			roleUser.setRoleUserId(DataConverter.stringToLong(roleUserId));
			roleUsers.add(roleUser);
		}
		
		return roleUsers;
	}

	@Override
	protected boolean validateData() {
		boolean result = true;
		if(!validateId(roleUserVo.getRoleId())){
			result = this.setInputMessage(AfwConstant.ROLE_ID_KEY, AfwConstant.ROLE_ID_MSG);
		}
		if(!validateId(roleUserVo.getUserProfileId()[1])){
			result = this.setInputMessage(AfwConstant.USER_PROFILE_ID_KEY, AfwConstant.USER_PROFILE_ID_MSG2);
		}
		return result;
	}

	public RoleUserVo getRoleUserVo() {
		return roleUserVo;
	}

	public void setRoleUserVo(RoleUserVo roleUserVo) {
		this.roleUserVo = roleUserVo;
	}

	public void setRoleUserService(RoleUserService roleUserService) {
		this.roleUserService = roleUserService;
	}
	
	@Override
	public String getJsonValue() {

		return super.getJsonValue();
	}
}
