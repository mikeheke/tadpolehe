package com.amway.frm.afw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.amway.frm.afw.dao.IRoleDao;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleUser;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.base.dao.impl.BaseDao;

/**
 * 
 * @author lenovo
 *
 */
public class RoleDao extends BaseDao<Role, String> implements IRoleDao {

	/* (non-Javadoc)
	 * @see com.amway.frm.afw.dao.IRoleDao#getRoleList(com.amway.frm.afw.entity.UserProfile)
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
		jql.append(jql5).append(userProfile.getUserProfileId());
		
		List<Role> roles = this.getResultList(jql.toString());
	
		return roles;
	}
	
}
