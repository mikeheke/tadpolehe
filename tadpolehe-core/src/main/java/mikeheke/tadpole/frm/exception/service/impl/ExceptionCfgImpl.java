/**
 * 
 */
package mikeheke.tadpole.frm.exception.service.impl;

import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.email.entity.EmailBasicPara;
import mikeheke.tadpole.frm.email.service.EmailService;
import mikeheke.tadpole.frm.email.util.EmailConstant;
import mikeheke.tadpole.frm.exception.dao.IExceptionCfgDao;
import mikeheke.tadpole.frm.exception.service.ExceptionCfgService;
import mikeheke.tadpole.frm.exception.vo.ExceptionCfgVo;

/**
 * 
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
		//if(!DataValidater.isStrLong(userNo)){
		//	return;
		//}
		UserProfile userProfile = new UserProfile();
		userProfile.setUserProfileId((userNo));
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
