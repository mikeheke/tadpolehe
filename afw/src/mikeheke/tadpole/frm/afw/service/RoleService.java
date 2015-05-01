package mikeheke.tadpole.frm.afw.service;

import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.afw.entity.Role;
import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;

/**
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：角色服务接口
 */
public interface RoleService extends BaseService<Role> {
	
	ReturnMessage<Role> getRoleList(Application application);
	
	ReturnMessage<Role> addRole(Role role);
	
	ReturnMessage<Role> updateRole(Role role);
}
