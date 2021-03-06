package mikeheke.tadpole.frm.email.service;

import java.util.Map;

import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.email.entity.EmailBasicPara;

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-3-17
 * Time: 18:31:31
 * To change this template use File | Settings | File Templates.
 */
public interface EmailService extends BaseService {

    //发送邮件，不使用模板
    ReturnMessage<EmailBasicPara> sendEmail(EmailBasicPara emailBasicPara) ;

    //发送邮件，使用模板，参数需包含模板编号，及模板里面的变量
    ReturnMessage<EmailBasicPara> sendEmail(EmailBasicPara emailBasicPara, 
    		Map<String, Object> variableMap);
    
    void sendEmailByAsynMode();
}