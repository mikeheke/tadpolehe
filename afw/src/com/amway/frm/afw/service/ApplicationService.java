package com.amway.frm.afw.service;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;

/**
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：应用服务接口
 */
public interface ApplicationService extends BaseService {
	
	ReturnMessage<Application> getApplicationsJDBC(UserProfile userProfile);
	
	ReturnMessage<Application> getApplicationsJDBC();
	
	ReturnMessage<Application> addCom(Application application);
	
	ReturnMessage<Application> update(Application application);
	
	ReturnMessage<Application> getApplications(Application application);
}
