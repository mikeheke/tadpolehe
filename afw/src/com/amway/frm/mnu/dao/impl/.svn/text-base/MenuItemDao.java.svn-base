package com.amway.frm.mnu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.amway.frm.afw.dao.impl.ModuleDao;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.SysInfoBean;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.mnu.dao.IMenuItemDao;

/**
 * 
 * @author lenovo
 *
 */
public class MenuItemDao extends ModuleDao implements IMenuItemDao {

	private boolean isCurOrParModuleStop(Module module){
		
		if(null == module){
			return true;
		}
		if(null != module.getState()
				&& module.getState().intValue() == AppConstant.STOP.intValue()){
			return true;
		}
		Module pModule = module.getParentModule();
		if(pModule.getModuleId().longValue() == module.getModuleId().longValue()){
			return false;
		}
		
		return isCurOrParModuleStop(pModule);
	}
	
	@Override
	public List<Module> getBaseModules(){
		List<Module> allModules = SysInfoBean.getSysInfoBean().getModules();
		List<Module> modules = new ArrayList<Module>();
		for(Module module: allModules){
			if(null == module.getIsModuleOrButton()
					|| AfwConstant.MODULE_TYPE_M.intValue() != module.getIsModuleOrButton().intValue()){
				continue;
			}
			if(isCurOrParModuleStop(module)){
				continue;
			}
			if(module.getModuleId().equals(module.getParentModule().getModuleId())){
				modules.add(module);
			}
		}
		return modules;
	}
	
	@Override
	public List<Module> getMidModules(){
		List<Module> allModules = SysInfoBean.getSysInfoBean().getModules();
		List<Module> modules = new ArrayList<Module>();
		for(Module module: allModules){
			if(null == module.getIsModuleOrButton()
					|| AfwConstant.MODULE_TYPE_M.intValue() != module.getIsModuleOrButton().intValue()){
				continue;
			}
			if(isCurOrParModuleStop(module)){
				continue;
			}
			if(!module.getModuleId().equals(module.getParentModule().getModuleId())){
				modules.add(module);
			}
		}
		return modules;
	}
	
	@Override
	public List<Module> getLeafModules(){
		List<Module> allModules = SysInfoBean.getSysInfoBean().getModules();
		List<Module> modules = new ArrayList<Module>();
		for(Module module: allModules){
			if(null == module.getIsModuleOrButton()
					|| AfwConstant.MODULE_TYPE_M.intValue() != module.getIsModuleOrButton().intValue()){
				continue;
			}
			if(isCurOrParModuleStop(module)){
				continue;
			}
			if(null != module.getIsSeed()
					&& module.getIsSeed().intValue() == AppConstant.YES.intValue()){
				modules.add(module);
			}
		}
		return modules;
	}
	
	@Override
	public List<Module> getAllModules(){
		List<Module> allModules = SysInfoBean.getSysInfoBean().getModules();
		List<Module> modules = new ArrayList<Module>();
		for(Module module: allModules){
			if(null == module.getIsModuleOrButton()
					|| AfwConstant.MODULE_TYPE_M.intValue() != module.getIsModuleOrButton().intValue()){
				continue;
			}
			if(isCurOrParModuleStop(module)){
				continue;
			}
			modules.add(module);
		}
		return modules;
	}
}
