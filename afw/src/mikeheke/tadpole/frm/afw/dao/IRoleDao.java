package mikeheke.tadpole.frm.afw.dao;

import java.sql.SQLException;
import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Role;
import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.base.dao.IBaseDao;

/**
 * 
 * @author lenovo
 *
 */
public interface IRoleDao extends IBaseDao<Role, String> {

	/**
	 * @param userProfile
	 * @return
	 */
	List<Role> getRoleList(UserProfile userProfile) throws SQLException;
	
}
