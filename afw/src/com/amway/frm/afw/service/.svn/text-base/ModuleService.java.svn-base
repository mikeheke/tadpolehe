package com.amway.frm.afw.service;

import java.util.List;

import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.vo.ModuleVo;
import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;

/**
 *模块信息业务Service接口类
 *@author huangweijin
 */
public interface ModuleService extends BaseService {

	/**
	 * 封装传递到页面，显示数据的entity
	 */
	ReturnMessage<Module> initAddLevel(ModuleVo moduleVo);
	
	ReturnMessage<Module> deleteModuleList(List<Module> modules);
	
	ReturnMessage<Module> updateModule(Module module);
	
	ReturnMessage<Module> addModule(Module module);
}
