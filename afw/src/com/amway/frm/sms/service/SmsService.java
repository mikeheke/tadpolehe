package com.amway.frm.sms.service;

import java.util.Map;

import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.sms.entity.SmsBasicPara;

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-3-17
 * Time: 18:31:31
 * Declare：短信Service，适用于实现业务逻辑，包括获得,保存短信记录
 */
public interface SmsService extends BaseService {

    //发送邮件，不使用模板
    ReturnMessage<SmsBasicPara> sendSms(SmsBasicPara smsBasicPara);
    //发送邮件，使用模板，参数需包含模板编号，及模板里面的变量
	ReturnMessage<SmsBasicPara> sendSms(SmsBasicPara smsBasicPara,
			Map<String, Object> variableMap);
}