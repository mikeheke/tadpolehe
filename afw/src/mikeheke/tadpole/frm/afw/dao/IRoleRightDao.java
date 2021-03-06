package mikeheke.tadpole.frm.afw.dao;

import java.sql.SQLException;

import mikeheke.tadpole.frm.afw.entity.Role;
import mikeheke.tadpole.frm.afw.entity.RoleRight;
import mikeheke.tadpole.frm.base.dao.IBaseDao;


/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：角色权限Dao接口
 */
public interface IRoleRightDao extends IBaseDao<RoleRight, String> {
	
	/**
	 * Declare：删除角色所有权限
	 * 
	 * @param role 角色
	 * @return List<RoleRight> 权限结果集
	 */
	void deleteRightsByRole(Role role) throws SQLException;
	
}
