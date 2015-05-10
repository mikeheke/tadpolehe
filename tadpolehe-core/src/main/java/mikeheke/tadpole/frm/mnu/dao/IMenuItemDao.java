package mikeheke.tadpole.frm.mnu.dao;

import java.util.List;

import mikeheke.tadpole.frm.afw.dao.IModuleDao;
import mikeheke.tadpole.frm.afw.entity.Module;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：菜单Dao接口
 */
public interface IMenuItemDao extends IModuleDao{
	
	List<Module> getBaseModules();
	
	List<Module> getMidModules();
	
	List<Module> getLeafModules();
	
	List<Module> getAllModules();
}
