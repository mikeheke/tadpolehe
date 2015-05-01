package mikeheke.tadpole.frm.afw.dao;

import java.sql.SQLException;
import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.afw.entity.Module;
import mikeheke.tadpole.frm.afw.entity.Role;
import mikeheke.tadpole.frm.base.dao.IBaseDao;

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
