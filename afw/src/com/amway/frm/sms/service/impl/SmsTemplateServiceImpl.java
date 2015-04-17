package com.amway.frm.sms.service.impl;

import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.sms.dao.ISmsTemplateDao;
import com.amway.frm.sms.service.SmsTemplateService;


/**
 * Created by IntelliJ IDEA.
 * @author huangweijin
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：短信模板Service，适用于实现业务逻辑，包括获得新模板编码，新增，修改，删除短信模板信息
 */

public class SmsTemplateServiceImpl  extends BaseImpl implements SmsTemplateService {
    
    private ISmsTemplateDao smsTemplateDao;

	public void setSmsTemplateDao(ISmsTemplateDao smsTemplateDao) {
		this.smsTemplateDao = smsTemplateDao;
	}

	public ISmsTemplateDao getSmsTemplateDao() {
		return smsTemplateDao;
	}
}
