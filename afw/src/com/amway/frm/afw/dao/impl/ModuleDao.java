package com.amway.frm.afw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.amway.frm.afw.dao.IModuleDao;
import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleRight;
import com.amway.frm.base.dao.impl.BaseDao;
import com.amway.frm.base.util.AppConstant;

/**
 * 
 * @author lenovo
 *
 */
public class ModuleDao extends BaseDao<Module,Long> implements IModuleDao{

	/* (non-Javadoc)
	 * @see com.amway.frm.afw.dao.IRoleRightDao#getRightsByRole(com.amway.frm.afw.entity.Role)
	 */
	@Override
	public List<Module> getRights(Role role) throws SQLException {
		
		StringBuffer jql = new StringBuffer();
		final String jql1 = "SELECT m FROM ";
		jql.append(jql1).append(Module.class.getSimpleName());
		final String jql2 = " m,";
		jql.append(jql2).append(RoleRight.class.getSimpleName());
		final String jql3 = " r";
		jql.append(jql3);
		final String jql4 = " WHERE m.moduleId=r.moduleId AND r.roleId=";
		jql.append(jql4).append(role.getRoleId());
		final String jql5 = " AND m.recordState=";
		jql.append(jql5).append(AppConstant.START);
		final String jql6 = " AND r.recordState=";
		jql.append(jql6).append(AppConstant.START);
		final String jql7 = " AND m.state=";
		jql.append(jql7).append(AppConstant.START);

		List<Module> modules = this.getResultList(jql.toString());
		
		return modules;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.afw.dao.IModuleDao#getRights(com.amway.frm.afw.entity.Application)
	 */
	@Override
	public List<Module> getRights(Application application) throws SQLException {
		
		Module module = new Module();
		module.setState(AppConstant.START);
		module.setApplication(application);
		
		List<Module> modules = getResultList(module);
		
		return modules;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.afw.dao.IModuleDao#getCModules(com.amway.frm.afw.entity.Module)
	 */
	@Override
	public List<Module> getCModules(Module module) throws SQLException {

		StringBuffer jql = new StringBuffer();
		final String jql1 = "from ";
		jql.append(jql1).append(module.getClass().getSimpleName());
		final String jql2 = " where parentModule=";
		jql.append(jql2).append(module.getModuleId());
		final String jql3 = " and parentModule!=moduleId";
		jql.append(jql3);
		
		return this.getResultList(jql.toString());
	}
}
