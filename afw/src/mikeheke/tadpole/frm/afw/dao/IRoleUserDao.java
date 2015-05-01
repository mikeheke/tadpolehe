package mikeheke.tadpole.frm.afw.dao;

import java.sql.SQLException;

import mikeheke.tadpole.frm.afw.entity.Role;
import mikeheke.tadpole.frm.afw.entity.RoleUser;
import mikeheke.tadpole.frm.base.dao.IBaseDao;

/**
 * 
 * @author lenovo
 *
 */
public interface IRoleUserDao extends IBaseDao<RoleUser, String> {

	int deleteRoleUsers(Role role);
	
	String findUserProfileIdByEmpNumber(String empNumber);
}
