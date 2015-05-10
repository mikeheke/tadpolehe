package mikeheke.tadpole.frm.afw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import mikeheke.tadpole.frm.afw.dao.IRoleDao;
import mikeheke.tadpole.frm.afw.entity.Role;
import mikeheke.tadpole.frm.afw.entity.RoleUser;
import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.base.dao.impl.BaseDao;

/**
 * 
 * @author lenovo
 *
 */
public class RoleDao extends BaseDao<Role, String> implements IRoleDao {

	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.afw.dao.IRoleDao#getRoleList(mikeheke.tadpole.frm.afw.entity.UserProfile)
	 */
	@Override
	public List<Role> getRoleList(UserProfile userProfile) throws SQLException {
		
		StringBuffer jql = new StringBuffer();
		final String jql1 = "SELECT r FROM ";
		jql.append(jql1).append(Role.class.getSimpleName());
		final String jql2 = " r,";
		jql.append(jql2).append(RoleUser.class.getSimpleName());
		final String jql3 = " u";
		jql.append(jql3);
		final String jql4 = " WHERE r.recordState=1 AND r.state=1 AND r.roleId=u.roleId AND u.recordState=1 ";
		jql.append(jql4);
		final String jql5 = " AND u.userProfileId=";
		jql.append(jql5).append("'"+userProfile.getUserProfileId()+"'"); //modify by Mike He 20150424
		
		List<Role> roles = this.getResultList(jql.toString());
	
		return roles;
	}
	
}
