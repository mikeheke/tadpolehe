package com.amway.frm.afw.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.amway.frm.afw.dao.IApplicationDao;
import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.ApplicationPlus;
import com.amway.frm.afw.entity.Department;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleRight;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.afw.service.ApplicationPlusService;
import com.amway.frm.afw.service.ApplicationService;
import com.amway.frm.afw.service.ModuleService;
import com.amway.frm.afw.service.RoleService;
import com.amway.frm.afw.util.AfwConstant;
import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.exception.exception.AmwaySysException;

/**
 * 
 * @author lenovo
 *
 */
public class ApplicationImpl extends BaseImpl implements ApplicationService{

	private IApplicationDao applicationDao;
	
	private ModuleService moduleService;
	
	private RoleService roleService;
	
	private ApplicationPlusService applicationPlusService;

	public void setApplicationPlusService(
			ApplicationPlusService applicationPlusService) {
		this.applicationPlusService = applicationPlusService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setApplicationDao(IApplicationDao applicationDao) {
		this.applicationDao = applicationDao;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.afw.service.ApplicationService#getApplications(com.amway.frm.afw.entity.UserProfile)
	 */
	@Override
	public ReturnMessage<Application> getApplicationsJDBC(UserProfile userProfile) {
		
		ReturnMessage<Application> returnMessage = new ReturnMessage<Application>();
		try {
			List<Application> applications = applicationDao.getApplicationListJDBC(userProfile);
			
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			returnMessage.setReturnObjects(applications);
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		}
		
		return returnMessage;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.afw.service.ApplicationService#getApplications()
	 */
	@Override
	public ReturnMessage<Application> getApplicationsJDBC() {
		
		ReturnMessage<Application> returnMessage = new ReturnMessage<Application>();
		try {
			List<Application> applications = applicationDao.getApplicationListJDBC();
			
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			returnMessage.setReturnObjects(applications);
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		}
		
		return returnMessage;
	}
	
	@Override
	@Transactional
	public ReturnMessage<Application> logicDeleteList(List applications) {
		
		for (Object app: applications) {
			Application application = (Application) app;
			
			Module module = new Module();
			module.setApplication(application);
			List<Module> modules = queryList(module);
			moduleService.deleteModuleList(modules);
			
			Role role = new Role();
			role.setApplication(application);
			List<Role> roles = queryList(role);
			roleService.logicDeleteList(roles);
			
			ApplicationPlus applicationPlus = new ApplicationPlus();
			applicationPlus.setApplication(application);
			List<ApplicationPlus> applicationPluses = queryList(applicationPlus);
			applicationPlusService.logicDeleteList(applicationPluses);
		}

		return super.logicDeleteList(applications);
	}
	
	@Override
	@Transactional
	public ReturnMessage<Application> query(Object app) {
		Application application = (Application) app;
		application = (Application) querySingle(application);
		ReturnMessage<Application> returnMessage = super.query(application);
		application = returnMessage.getReturnObject();
		
		setDeptName(application);
		setEmpNumber(application);
		
		returnMessage.setReturnObject(application);
		return returnMessage;
	}
	
	@Transactional
	public ReturnMessage<Application> addCom(Application applicationIn) {
		
		//applicationIn.setApplicationId(applicationDao.generateSequence("MSTB_APPLICATION"));
		
		ReturnMessage<Application> returnMessage = super.addCom(applicationIn);
		
		if (returnMessage.isSuccess()) {
			Application application = returnMessage.getReturnObject();
		
			setDeptName(application);
			setEmpNumber(application);
			
			List<Role> roleList=generateRoles(application);
			
//			for(Role r : roleList){
//				if(r.getRoleId()==null){
//					r.setRoleId(applicationDao.generateSequence("MSTB_ROLE"));
//				}
//			}
		
			addComList(roleList);
			
			List<Module> moduleList=generateDevlopeModules(application);
			
			addComList(moduleList);
			
			List<RoleRight> roleRightList=generateDevlopeModulesRoleRight(moduleList,roleList.get(2));
			
			addComList(roleRightList);
		
			returnMessage.setReturnObject(application);
		}
		return returnMessage;
	}
	
	@Transactional
	public ReturnMessage<Application> update(Application applicationIn) {
		ReturnMessage<Application> returnMessage = super.update(applicationIn);
		
		if (returnMessage.isSuccess()) {
			Application application = returnMessage.getReturnObject();
		
			setDeptName(application);
			setEmpNumber(application);
		
			returnMessage.setReturnObject(application);
		}
		return returnMessage;
	}
	
	private List<Role> generateRoles(Application application) {
		
		Role adminRole = new Role();
		adminRole.setRoleCode(application.getApplicationCode()
				+ AfwConstant.DEFAULT_ADMIN_ROLE_AFT);
		adminRole.setRoleName(application.getApplicationName()
				+ AfwConstant.DEFAULT_ADMIN_ROLE_NAME_AFT);
		adminRole.setApplication(application);
		adminRole.setState(AppConstant.START);
		adminRole.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		adminRole.setCreatedTime(new Date());
		adminRole.setUpdatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		adminRole.setUpdatedTime(new Date());
		
		Role defaultRole = new Role();
		defaultRole.setRoleCode(application.getApplicationCode()
				+ AfwConstant.DEFAULT_USER_ROLE_AFT);
		defaultRole.setRoleName(application.getApplicationName()
				+ AfwConstant.DEFAULT_USER_ROLE_NAME_AFT);
		defaultRole.setApplication(application);
		defaultRole.setState(AppConstant.START);
		defaultRole.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		defaultRole.setCreatedTime(new Date());
		defaultRole.setUpdatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		defaultRole.setUpdatedTime(new Date());
		
		//ad by hyc 增加应用开发员角色
		Role developeRole = new Role();
		developeRole.setRoleCode(application.getApplicationCode()
				+ AfwConstant.DEFAULT_DEV_ROLE_AFT);
		developeRole.setRoleName(application.getApplicationName()
				+ AfwConstant.DEFAULT_DEV_ROLE_NAME_AFT);
		developeRole.setApplication(application);
		developeRole.setState(AppConstant.START);
		developeRole.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		developeRole.setCreatedTime(new Date());
		developeRole.setUpdatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		developeRole.setUpdatedTime(new Date());
		
		List<Role> roleList = new ArrayList<Role>();
		roleList.add(adminRole);
		roleList.add(defaultRole);
		roleList.add(developeRole);
		return roleList;
	}
	
	private List<RoleRight> generateDevlopeModulesRoleRight(List<Module> moduleList,Role developeRole) {
		
		List<RoleRight> roleRightList = new ArrayList<RoleRight>();
		
		
		for(Module module:moduleList){
			RoleRight rr=new RoleRight();
			
			//rr.setRoleRightId(applicationDao.generateSequence("MSTB_ROLE_RIGHT"));
			
			rr.setRoleId(developeRole.getRoleId());
			
			rr.setModuleId(module.getModuleId());
			
			rr.setRecordState(1);
			
			roleRightList.add(rr);
		}
		
		

		return roleRightList;
		
	}
	
	private List<Module> generateDevlopeModules(Application application) {
		//root
		Module moduleRoot = new Module();
//		moduleRoot.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleRoot.setModuleCode(application.getApplicationCode()+"_root");
		moduleRoot.setModuleName(application.getApplicationName());
		moduleRoot.setIsModuleOrButton(0);
		moduleRoot.setParentModule(moduleRoot);
		moduleRoot.setApplication(application);
		moduleRoot.setIco("ind_top_ico.jpg");
		moduleRoot.setLink("#");
		moduleRoot.setOrderNo("1");
		moduleRoot.setIsSeed(1);
		moduleRoot.setTarget("main");
		moduleRoot.setState(1);
		moduleRoot.setRecordState(1);
		moduleRoot.setCreatedTime(new Date());
		moduleRoot.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//menuroot
		Module moduleMenuRoot = new Module();
//		moduleMenuRoot.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleMenuRoot.setModuleCode(application.getApplicationCode()+"_menuroot");
		moduleMenuRoot.setModuleName("应用管理");
		moduleMenuRoot.setIsModuleOrButton(0);
		moduleMenuRoot.setParentModule(moduleRoot);
		moduleMenuRoot.setApplication(application);
		moduleMenuRoot.setLink("#");
		moduleMenuRoot.setOrderNo("2");
		moduleMenuRoot.setIsSeed(1);
		moduleMenuRoot.setTarget("main");
		moduleMenuRoot.setState(1);
		moduleMenuRoot.setRecordState(1);
		moduleMenuRoot.setCreatedTime(new Date());
		moduleMenuRoot.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//应用设置维护
		Module moduleApp = new Module();
//		moduleApp.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleApp.setModuleCode(application.getApplicationCode()+"_app");
		moduleApp.setModuleName("应用设置维护");
		moduleApp.setIsModuleOrButton(0);
		moduleApp.setParentModule(moduleMenuRoot);
		moduleApp.setApplication(application);
		moduleApp.setLink("$/showAction.action?queryCode=appquery_ad");
		moduleApp.setOrderNo("3");
		moduleApp.setIsSeed(1);
		moduleApp.setTarget("main");
		moduleApp.setState(1);
		moduleApp.setRecordState(1);
		moduleApp.setCreatedTime(new Date());
		moduleApp.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//功能信息维护
		Module moduleFunction = new Module();
//		moduleFunction.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleFunction.setModuleCode(application.getApplicationCode()+"_module");
		moduleFunction.setModuleName("功能信息维护");
		moduleFunction.setIsModuleOrButton(0);
		moduleFunction.setParentModule(moduleMenuRoot);
		moduleFunction.setApplication(application);
		moduleFunction.setLink("$/showAction.action?queryCode=modulequery_ad");
		moduleFunction.setOrderNo("4");
		moduleFunction.setIsSeed(1);
		moduleFunction.setTarget("main");
		moduleFunction.setState(1);
		moduleFunction.setRecordState(1);
		moduleFunction.setCreatedTime(new Date());
		moduleFunction.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//角色信息维护
		Module moduleRole = new Module();
//		moduleRole.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleRole.setModuleCode(application.getApplicationCode()+"_role");
		moduleRole.setModuleName("角色信息维护");
		moduleRole.setIsModuleOrButton(0);
		moduleRole.setParentModule(moduleMenuRoot);
		moduleRole.setApplication(application);
		moduleRole.setLink("$/showAction.action?queryCode=rolequery_ad");
		moduleRole.setOrderNo("5");
		moduleRole.setIsSeed(1);
		moduleRole.setTarget("main");
		moduleRole.setState(1);
		moduleRole.setRecordState(1);
		moduleRole.setCreatedTime(new Date());
		moduleRole.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//角色分配用户
		Module moduleRoleUser = new Module();
//		moduleRoleUser.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleRoleUser.setModuleCode(application.getApplicationCode()+"_roleuser");
		moduleRoleUser.setModuleName("角色分配用户");
		moduleRoleUser.setIsModuleOrButton(0);
		moduleRoleUser.setParentModule(moduleMenuRoot);
		moduleRoleUser.setApplication(application);
		moduleRoleUser.setLink("$/showAction.action?queryCode=roleuserquery_ad");
		moduleRoleUser.setOrderNo("6");
		moduleRoleUser.setIsSeed(1);
		moduleRoleUser.setTarget("main");
		moduleRoleUser.setState(1);
		moduleRoleUser.setRecordState(1);
		moduleRoleUser.setCreatedTime(new Date());
		moduleRoleUser.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//角色授权维护
		Module moduleRoleRight = new Module();
//		moduleRoleRight.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleRoleRight.setModuleCode(application.getApplicationCode()+"_roleright");
		moduleRoleRight.setModuleName("角色授权维护");
		moduleRoleRight.setIsModuleOrButton(0);
		moduleRoleRight.setParentModule(moduleMenuRoot);
		moduleRoleRight.setApplication(application);
		moduleRoleRight.setLink("$/roleRightAction!init.action");
		moduleRoleRight.setOrderNo("7");
		moduleRoleRight.setIsSeed(1);
		moduleRoleRight.setTarget("main");
		moduleRoleRight.setState(1);
		moduleRoleRight.setRecordState(1);
		moduleRoleRight.setCreatedTime(new Date());
		moduleRoleRight.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//扩展应用配置
		Module moduleExtApp = new Module();
//		moduleExtApp.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleExtApp.setModuleCode(application.getApplicationCode()+"_extapp");
		moduleExtApp.setModuleName("扩展应用配置");
		moduleExtApp.setIsModuleOrButton(0);
		moduleExtApp.setParentModule(moduleMenuRoot);
		moduleExtApp.setApplication(application);
		moduleExtApp.setLink("$/showAction.action?queryCode=extappquery_ad");
		moduleExtApp.setOrderNo("8");
		moduleExtApp.setIsSeed(1);
		moduleExtApp.setTarget("main");
		moduleExtApp.setState(1);
		moduleExtApp.setRecordState(1);
		moduleExtApp.setCreatedTime(new Date());
		moduleExtApp.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//日志配置
		Module moduleLog = new Module();
//		moduleLog.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleLog.setModuleCode(application.getApplicationCode()+"_log");
		moduleLog.setModuleName("日志配置");
		moduleLog.setIsModuleOrButton(0);
		moduleLog.setParentModule(moduleMenuRoot);
		moduleLog.setApplication(application);
		moduleLog.setLink("$/logAction!init.action");
		moduleLog.setOrderNo("9");
		moduleLog.setIsSeed(1);
		moduleLog.setTarget("main");
		moduleLog.setState(1);
		moduleLog.setRecordState(1);
		moduleLog.setCreatedTime(new Date());
		moduleLog.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//邮件模板配置
		Module moduleMailTemplate = new Module();
//		moduleMailTemplate.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleMailTemplate.setModuleCode(application.getApplicationCode()+"_mailtemplate");
		moduleMailTemplate.setModuleName("邮件模板配置");
		moduleMailTemplate.setIsModuleOrButton(0);
		moduleMailTemplate.setParentModule(moduleMenuRoot);
		moduleMailTemplate.setApplication(application);
		moduleMailTemplate.setLink("$/showAction.action?queryCode=mailtemplatequery_ad");
		moduleMailTemplate.setOrderNo("10");
		moduleMailTemplate.setIsSeed(1);
		moduleMailTemplate.setTarget("main");
		moduleMailTemplate.setState(1);
		moduleMailTemplate.setRecordState(1);
		moduleMailTemplate.setCreatedTime(new Date());
		moduleMailTemplate.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//短信模板配置
		Module moduleShortMessageTemplate = new Module();
//		moduleShortMessageTemplate.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleShortMessageTemplate.setModuleCode(application.getApplicationCode()+"_shortmessage");
		moduleShortMessageTemplate.setModuleName("短信模板配置");
		moduleShortMessageTemplate.setIsModuleOrButton(0);
		moduleShortMessageTemplate.setParentModule(moduleMenuRoot);
		moduleShortMessageTemplate.setApplication(application);
		moduleShortMessageTemplate.setLink("$/showAction.action?queryCode=shortmessagequery_ad");
		moduleShortMessageTemplate.setOrderNo("11");
		moduleShortMessageTemplate.setIsSeed(1);
		moduleShortMessageTemplate.setTarget("main");
		moduleShortMessageTemplate.setState(1);
		moduleShortMessageTemplate.setRecordState(1);
		moduleShortMessageTemplate.setCreatedTime(new Date());
		moduleShortMessageTemplate.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//通用查询
		Module moduleCommonQuery = new Module();
//		moduleCommonQuery.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleCommonQuery.setModuleCode(application.getApplicationCode()+"_commonquery");
		moduleCommonQuery.setModuleName("通用查询");
		moduleCommonQuery.setIsModuleOrButton(0);
		moduleCommonQuery.setParentModule(moduleMenuRoot);
		moduleCommonQuery.setApplication(application);
		moduleCommonQuery.setLink("$/showAction.action?queryCode=commonquery_ad");
		moduleCommonQuery.setOrderNo("12");
		moduleCommonQuery.setIsSeed(1);
		moduleCommonQuery.setTarget("main");
		moduleCommonQuery.setState(1);
		moduleCommonQuery.setRecordState(1);
		moduleCommonQuery.setCreatedTime(new Date());
		moduleCommonQuery.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//基础数据服务
		Module moduleBDS = new Module();
//		moduleBDS.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleBDS.setModuleCode(application.getApplicationCode()+"_bds");
		moduleBDS.setModuleName("基础数据服务");
		moduleBDS.setIsModuleOrButton(0);
		moduleBDS.setParentModule(moduleMenuRoot);
		moduleBDS.setApplication(application);
		moduleBDS.setLink("$/showAction.action?queryCode=bdsquery_ad");
		moduleBDS.setOrderNo("13");
		moduleBDS.setIsSeed(1);
		moduleBDS.setTarget("main");
		moduleBDS.setState(1);
		moduleBDS.setRecordState(1);
		moduleBDS.setCreatedTime(new Date());
		moduleBDS.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//操作日志查询
		Module moduleOpLog = new Module();
//		moduleOpLog.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleOpLog.setModuleCode(application.getApplicationCode()+"_oplog");
		moduleOpLog.setModuleName("操作日志查询");
		moduleOpLog.setIsModuleOrButton(0);
		moduleOpLog.setParentModule(moduleMenuRoot);
		moduleOpLog.setApplication(application);
		moduleOpLog.setLink("$/showAction.action?queryCode=oplogquery_ad");
		moduleOpLog.setOrderNo("14");
		moduleOpLog.setIsSeed(1);
		moduleOpLog.setTarget("main");
		moduleOpLog.setState(1);
		moduleOpLog.setRecordState(1);
		moduleOpLog.setCreatedTime(new Date());
		moduleOpLog.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//定时报表配置
		Module moduleTimeReport = new Module();
//		moduleTimeReport.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleTimeReport.setModuleCode(application.getApplicationCode()+"_timereport");
		moduleTimeReport.setModuleName("定时报表配置");
		moduleTimeReport.setIsModuleOrButton(0);
		moduleTimeReport.setParentModule(moduleMenuRoot);
		moduleTimeReport.setApplication(application);
		moduleTimeReport.setLink("$/showAction.action?queryCode=timereportquery_ad");
		moduleTimeReport.setOrderNo("15");
		moduleTimeReport.setIsSeed(1);
		moduleTimeReport.setTarget("main");
		moduleTimeReport.setState(1);
		moduleTimeReport.setRecordState(1);
		moduleTimeReport.setCreatedTime(new Date());
		moduleTimeReport.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		//定时任务配置
		Module moduleTimeTask = new Module();
//		moduleTimeTask.setModuleId(applicationDao.generateSequence("MSTB_MODULE"));		
		moduleTimeTask.setModuleCode(application.getApplicationCode()+"_timetask");
		moduleTimeTask.setModuleName("定时任务配置");
		moduleTimeTask.setIsModuleOrButton(0);
		moduleTimeTask.setParentModule(moduleMenuRoot);
		moduleTimeTask.setApplication(application);
		moduleTimeTask.setLink("$/showAction.action?queryCode=timetaskquery_ad");
		moduleTimeTask.setOrderNo("16");
		moduleTimeTask.setIsSeed(1);
		moduleTimeTask.setTarget("main");
		moduleTimeTask.setState(1);
		moduleTimeTask.setRecordState(1);
		moduleTimeTask.setCreatedTime(new Date());
		moduleTimeTask.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		List<Module> moduleList = new ArrayList<Module>();
		moduleList.add(moduleRoot);
		moduleList.add(moduleMenuRoot);
		moduleList.add(moduleApp);
		moduleList.add(moduleFunction);
		moduleList.add(moduleRole);
		moduleList.add(moduleRoleUser);
		moduleList.add(moduleRoleRight);
		moduleList.add(moduleExtApp);
		moduleList.add(moduleLog);
		moduleList.add(moduleMailTemplate);
		moduleList.add(moduleShortMessageTemplate);
		moduleList.add(moduleCommonQuery);
		moduleList.add(moduleBDS);
		moduleList.add(moduleOpLog);
		moduleList.add(moduleTimeReport);
		moduleList.add(moduleTimeTask);
		return moduleList;
		
	}
	
	private void setDeptName(Application application) {
		if (application.getDepartmentCode() != null) {
			Department department = new Department();
			department.setUnitCode(application.getDepartmentCode());
			department = (Department) querySingle(department);
			application.setDepartmentName(department.getDeptNameCn());
		}
	}
	
	private void setEmpNumber(Application application) {
		if (application.getFaultHandlerEmpNumber() != null) {
			UserProfile userProfile = new UserProfile();
			userProfile.setUserProfileId((application.getFaultHandlerEmpNumber()));
			userProfile = (UserProfile) querySingle(userProfile);
			application.setEmpNumber(userProfile.getEmpNumber());
		}
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.afw.service.ApplicationService#getApplication(com.amway.frm.afw.entity.Application)
	 */
	@Override
	public ReturnMessage<Application> getApplications(Application application) {
		
		ReturnMessage<Application> returnMessage = new ReturnMessage<Application>();
		
		try {
			List<Application> applications = applicationDao.getApplications(application);
			returnMessage.setReturnObjects(applications);
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			returnMessage.setQryReturnMsg();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new AmwaySysException(e);
		}
		
		return returnMessage;
	}
}
