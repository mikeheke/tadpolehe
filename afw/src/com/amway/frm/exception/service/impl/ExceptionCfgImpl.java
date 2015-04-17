/**
 * 
 */
package com.amway.frm.exception.service.impl;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.email.entity.EmailBasicPara;
import com.amway.frm.email.service.EmailService;
import com.amway.frm.email.util.EmailConstant;
import com.amway.frm.exception.dao.IExceptionCfgDao;
import com.amway.frm.exception.service.ExceptionCfgService;
import com.amway.frm.exception.vo.ExceptionCfgVo;

/**
 * @author huangweijin
 *
 * 2011-4-14 下午06:36:30
 */
public class ExceptionCfgImpl extends BaseImpl implements ExceptionCfgService {

	private IExceptionCfgDao exceptionCfgDao;

	private EmailService emailService;

	@Override
	public void send(ExceptionCfgVo exceptionVo) {

		Application application = exceptionVo.getApplication();
		if(null == application){
			return;
		}
		String userNo = application.getFaultHandlerEmpNumber();
		if(!DataValidater.isStrLong(userNo)){
			return;
		}
		UserProfile userProfile = new UserProfile();
		userProfile.setUserProfileId(DataConverter.stringToLong(userNo));
		UserProfile userProfileRet = (UserProfile) querySingle(userProfile);
		if(null == userProfileRet){
			return;
		}
		String emailAddr = userProfileRet.getEmailAddr();
		if(DataValidater.isStrEmpty(emailAddr)){
			return;
		}
		EmailBasicPara emailBasicPara = new EmailBasicPara();
		emailBasicPara.setMailSubject(exceptionVo.getExceptionName());
		emailBasicPara.setMailContent(exceptionVo.getRemark());
		emailBasicPara.setMailTo(new String[]{emailAddr});
		emailBasicPara.setSendMode(EmailConstant.SYN_SEND);
		
		emailService.sendEmail(emailBasicPara);
	}
	
	public void setExceptionCfgDao(IExceptionCfgDao exceptionCfgDao) {
		this.exceptionCfgDao = exceptionCfgDao;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public IExceptionCfgDao getExceptionCfgDao() {
		return exceptionCfgDao;
	}

	public EmailService getEmailService() {
		return emailService;
	}
	
}
