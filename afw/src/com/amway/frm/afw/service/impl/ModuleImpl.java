package com.amway.frm.afw.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.amway.frm.afw.dao.IModuleDao;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleRight;
import com.amway.frm.afw.service.ModuleService;
import com.amway.frm.afw.service.RoleRightService;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.afw.vo.ModuleVo;
import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.exception.exception.AmwaySysException;

/**
 * 模块信息业务Service实现类
 * 
 */
public class ModuleImpl extends BaseImpl implements ModuleService{

	private IModuleDao moduleDao;
	
	private RoleRightService roleRightService;
	
	/* (non-Javadoc)
	 * @see com.amway.frm.afw.service.ModuleService#initAdd(com.amway.frm.afw.vo.ModuleVo)
	 */
	@Override
	public ReturnMessage<Module> initAddLevel(ModuleVo moduleVo) {
		
		ReturnMessage<Module> returnMessage = new ReturnMessage<Module>();
		
		//取出当前模块
		String[] moduleIds = moduleVo.getModuleIds();
		Module module = null;
		if(!DataValidater.isArrEmpty(moduleIds)){
			Module qModule = new Module();
			qModule.setModuleId(moduleIds[0]);
			//module = (Module) querySingle(new Module((moduleIds[0])));
			module = (Module) querySingle(qModule);
		}
		
		//根据不同级别按钮，设置不同初值
		String addLevel = moduleVo.getAddLevel();
		Module moduleRet = new Module();
		if(AfwConstant.ADD_LEVEL_CURRENT.equals(addLevel)){
			if(module != null 
					&& !module.getModuleId().equals(module.getParentModule().getModuleId())){
				moduleRet.setParentModule(module.getParentModule());
				moduleRet.setApplication(module.getParentModule().getApplication());
			}
		}else if(AfwConstant.ADD_LEVEL_NEXT.equals(addLevel)){
			if(module != null){
				moduleRet.setParentModule(module);
				moduleRet.setApplication(module.getApplication());
			}
		}
		
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnObject(moduleRet);
		
		return returnMessage;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.afw.service.ModuleService#deleteModuleList(java.util.List)
	 */
	@Override
	@Transactional
	public ReturnMessage<Module> deleteModuleList(List<Module> modules) {
		
		ReturnMessage<Module> returnMessage = new ReturnMessage<Module>();
		
		try {
			deleteModuleList(returnMessage, modules);
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		}
		
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnMsg(ReturnMessage.DEL_SUCCESS_MSG);
		
		return returnMessage;
	}
	
	@Transactional
	private void deleteModuleList(ReturnMessage<Module> returnMessage,
			List<Module> modules) throws SQLException{
		
		for(Module module: modules){
			List<Module> cModules = moduleDao.getCModules(module);
			if(!DataValidater.isCollectionEmpty(cModules)){
				deleteModuleList(returnMessage, cModules);
			}
			
			RoleRight roleRight = new RoleRight();
			roleRight.setModuleId(module.getModuleId());
			List<RoleRight> roleRights = roleRightService.queryList(roleRight);
			roleRightService.logicDeleteList(roleRights);
			
			returnMessage.setReturnObject((Module) logicDelete(module).getReturnObject());
		}
	}
	
	@Override
	@Transactional
	public ReturnMessage<Module> updateModule(Module module) {
		
		if(null != module.getState()
				&& AfwConstant.STOP.intValue() == module.getState().intValue()){
			ReturnMessage<Module> returnMessage = new ReturnMessage<Module>();
			try {
				updateModuleList(returnMessage, module);
				return returnMessage;
			} catch (SQLException e) {
				throw new AmwaySysException(e);
			}
		}else{
			return update(module);
		}
	}
	
	@Transactional
	private void updateModuleList(ReturnMessage<Module> returnMessage,
			Module module) throws SQLException{
		
		List<Module> cModules = moduleDao.getCModules(module);
		for(Module cModule: cModules){
			updateModuleList(returnMessage, cModule);							//更新子
		}
		
		module.setState(AfwConstant.STOP);
		returnMessage.setReturnObject((Module)(update(module).getReturnObject()));	//更新自己
	}
	
	@Override
	@Transactional
	public ReturnMessage<Module> addModule(Module module){
		
//		if(module.getModuleId()==null){
//			module.setModuleId(moduleDao.generateSequence("MSTB_MODULE"));
//		}
		
		ReturnMessage<Module> returnMessage = addCom(module);
		
		return returnMessage;
		
	}

	public void setModuleDao(IModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	public RoleRightService getRoleRightService() {
		return roleRightService;
	}

	public void setRoleRightService(RoleRightService roleRightService) {
		this.roleRightService = roleRightService;
	}

}
