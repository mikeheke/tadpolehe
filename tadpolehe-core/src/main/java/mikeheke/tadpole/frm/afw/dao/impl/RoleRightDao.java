package mikeheke.tadpole.frm.afw.dao.impl;


import java.sql.SQLException;

import mikeheke.tadpole.frm.afw.dao.IRoleRightDao;
import mikeheke.tadpole.frm.afw.entity.Role;
import mikeheke.tadpole.frm.afw.entity.RoleRight;
import mikeheke.tadpole.frm.base.dao.impl.BaseDao;

/**
 * 
 * @author lenovo
 *
 */
public class RoleRightDao extends BaseDao<RoleRight, String> implements IRoleRightDao{

	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.afw.dao.IRoleRightDao#deleteRightsByRole(mikeheke.tadpole.frm.afw.entity.Role)
	 */
	@Override
	public void deleteRightsByRole(Role role) throws SQLException {

		StringBuffer jql = new StringBuffer();
		final String jql1 = "DELETE FROM ";
		jql.append(jql1).append(RoleRight.class.getSimpleName());
		final String jql2 = " WHERE roleId=";
		jql.append(jql2).append("'"+role.getRoleId()+"'");//modify by Mike He 20150424
		
		this.executeJpl(jql.toString());
	}
}
