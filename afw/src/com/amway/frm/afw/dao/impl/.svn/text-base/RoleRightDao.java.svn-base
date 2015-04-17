package com.amway.frm.afw.dao.impl;


import java.sql.SQLException;

import com.amway.frm.afw.dao.IRoleRightDao;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleRight;
import com.amway.frm.base.dao.impl.BaseDao;

/**
 * 
 * @author lenovo
 *
 */
public class RoleRightDao extends BaseDao<RoleRight, Long> implements IRoleRightDao{

	/* (non-Javadoc)
	 * @see com.amway.frm.afw.dao.IRoleRightDao#deleteRightsByRole(com.amway.frm.afw.entity.Role)
	 */
	@Override
	public void deleteRightsByRole(Role role) throws SQLException {

		StringBuffer jql = new StringBuffer();
		final String jql1 = "DELETE FROM ";
		jql.append(jql1).append(RoleRight.class.getSimpleName());
		final String jql2 = " WHERE roleId=";
		jql.append(jql2).append(role.getRoleId());
		
		this.executeJpl(jql.toString());
	}
}
