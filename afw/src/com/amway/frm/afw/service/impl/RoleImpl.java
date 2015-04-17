package com.amway.frm.afw.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.amway.frm.afw.dao.IRoleDao;
import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleRight;
import com.amway.frm.afw.entity.RoleUser;
import com.amway.frm.afw.service.RoleRightService;
import com.amway.frm.afw.service.RoleService;
import com.amway.frm.afw.service.RoleUserService;
import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.base.vo.ReturnMessage;

/**
 * 
 * @author lenovo
 *
 */
public class RoleImpl extends BaseImpl<Role> implements RoleService {

	private IRoleDao roleDao;
	
	private RoleRightService roleRightService;
	
	private RoleUserService roleUserService;
	
	/**
	 * 取应et
	 * @param application
	 * @return
	 */
	@Override
	public ReturnMessage<Role> getRoleList(Application application) {
		
		Role role = new Role();
		role.setApplication(application);
		ReturnMessage<Role> returnMessage = this.query(role);
		
		return returnMessage;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.afw.service.RoleService#addRole(com.amway.frm.afw.entity.Role)
	 */
	@Override
	@Transactional
	public ReturnMessage<Role> addRole(Role role) {
		
		role.setRoleId(roleDao.generateSequence("MSTB_ROLE"));
		
		ReturnMessage<Role> returnMessage = addCom(role);
		if(!returnMessage.isSuccess()){
			return returnMessage;
		}
	
		Role roleRet = returnMessage.getReturnObject();
		roleUserService.addRoleUserBySQL(roleRet);
	
		return returnMessage;
	}
	

	/* (non-Javadoc)
	 * @see com.amway.frm.afw.service.RoleService#updateRole(com.amway.frm.afw.entity.Role)
	 */
	@Override
	@Transactional
	public ReturnMessage<Role> updateRole(Role role) {
		
		ReturnMessage<Role> returnMessage = update(role);
		if(!returnMessage.isSuccess()){
			return returnMessage;
		}
		
		Role roleRet = returnMessage.getReturnObject();
		roleUserService.addRoleUserBySQL(roleRet);
		
		return returnMessage;
	}
	
	@Override
	@Transactional
	public ReturnMessage<Role> logicDeleteList(List<Role> roles) {
		
		for (Role role: roles) {
			RoleRight roleRight = new RoleRight();
			roleRight.setRoleId(role.getRoleId());
			List<RoleRight> roleRights = roleRightService.queryList(roleRight);
			roleRightService.logicDeleteList(roleRights);
			
			RoleUser roleUser = new RoleUser();
			roleUser.setRoleId(role.getRoleId());
			List<RoleUser> roleUsers = roleUserService.queryList(roleUser);
			roleUserService.logicDeleteList(roleUsers);
		}

		return super.logicDeleteList(roles);
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public RoleRightService getRoleRightService() {
		return roleRightService;
	}

	public void setRoleRightService(RoleRightService roleRightService) {
		this.roleRightService = roleRightService;
	}

	public RoleUserService getRoleUserService() {
		return roleUserService;
	}

	public void setRoleUserService(RoleUserService roleUserService) {
		this.roleUserService = roleUserService;
	}
}
