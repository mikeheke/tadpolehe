package mikeheke.tadpole.frm.email.service.impl;

import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.email.dao.IEmailTemplateDao;
import mikeheke.tadpole.frm.email.service.EmailTemplateService;

/**
 * Created by IntelliJ IDEA. 
 * 
 * Declare：邮件模板Service，适用于实现业务逻辑，包括获得新模板编码，新增，修改，删除短信模板信息
 */
public class EmailTemplateServiceImpl extends BaseImpl implements
		EmailTemplateService {

	private IEmailTemplateDao emailTemplateDao;

	public void setEmailTemplateDao(IEmailTemplateDao emailTemplateDao) {
		this.emailTemplateDao = emailTemplateDao;
	}

	public IEmailTemplateDao getEmailTemplateDao() {
		return emailTemplateDao;
	}
	
}
