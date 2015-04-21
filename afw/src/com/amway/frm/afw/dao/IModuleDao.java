package com.amway.frm.afw.dao;

import java.sql.SQLException;
import java.util.List;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.base.dao.IBaseDao;

/**
 * 
 * @author lenovo
 *
 */
public interface IModuleDao extends IBaseDao<Module, String> {

	/**
	 * Declare：取角色权限结果集
	 * 
	 * @param role
	 *            角色
	 * @return List<Module> 权限结果集
	 */
	List<Module> getRights(Role role) throws SQLException;

	List<Module> getRights(Application application) throws SQLException;
	
	List<Module> getCModules(Module module) throws SQLException;
}
