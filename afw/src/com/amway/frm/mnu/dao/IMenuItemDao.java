package com.amway.frm.mnu.dao;

import java.util.List;

import com.amway.frm.afw.dao.IModuleDao;
import com.amway.frm.afw.entity.Module;

/**
 * Created by MyElipse
 * @author huangweijin
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
