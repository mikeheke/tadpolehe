package mikeheke.tadpole.frm.afw.service;

import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Module;
import mikeheke.tadpole.frm.afw.vo.ModuleVo;
import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;

/**
 *模块信息业务Service接口类
 *
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
