package com.amway.frm.afw.service.impl;

import com.amway.frm.afw.dao.IApplicationPlusDao;
import com.amway.frm.afw.service.ApplicationPlusService;
import com.amway.frm.afw.service.ApplicationService;
import com.amway.frm.base.service.impl.BaseImpl;

/**
 * 应用系统扩展信息service实现类
 * @author Feng Hanhao
 * Created date: 2011-5-30
 */
public class ApplicationPlusImpl extends BaseImpl implements ApplicationPlusService{
	
	private IApplicationPlusDao applicationPlusDao;
	
	private ApplicationService applicationService;

	
	public void setApplicationPlusDao(IApplicationPlusDao applicationPlusDao) {
		this.applicationPlusDao = applicationPlusDao;
	}

	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	public IApplicationPlusDao getApplicationPlusDao() {
		return applicationPlusDao;
	}

	public ApplicationService getApplicationService() {
		return applicationService;
	}

}
