package mikeheke.tadpole.frm.afw.service.impl;

import java.util.List;

import mikeheke.tadpole.frm.afw.dao.IRoleDao;
import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.afw.entity.Role;
import mikeheke.tadpole.frm.afw.entity.RoleRight;
import mikeheke.tadpole.frm.afw.entity.RoleUser;
import mikeheke.tadpole.frm.afw.service.RoleRightService;
import mikeheke.tadpole.frm.afw.service.RoleService;
import mikeheke.tadpole.frm.afw.service.RoleUserService;
import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;

import org.springframework.transaction.annotation.Transactional;

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
	 * @see mikeheke.tadpole.frm.afw.service.RoleService#addRole(mikeheke.tadpole.frm.afw.entity.Role)
	 */
	@Override
	@Transactional
	public ReturnMessage<Role> addRole(Role role) {
		
//		role.setRoleId(roleDao.generateSequence("MSTB_ROLE"));
		
		ReturnMessage<Role> returnMessage = addCom(role);
		if(!returnMessage.isSuccess()){
			return returnMessage;
		}
	
		Role roleRet = returnMessage.getReturnObject();
		roleUserService.addRoleUserBySQL(roleRet);
	
		return returnMessage;
	}
	

	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.afw.service.RoleService#updateRole(mikeheke.tadpole.frm.afw.entity.Role)
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
