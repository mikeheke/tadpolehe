package mikeheke.tadpole.frm.email.dao;

import mikeheke.tadpole.frm.base.dao.IBaseDao;
import mikeheke.tadpole.frm.email.entity.EmailTemplate;

/**
 * Created by IntelliJ IDEA. 
 * 
 * Declare：EmailTemplates表对应的DAO
 * ，适用于操作EmailTemplates表，包括SELECT,CREATE,UPDATE,DELETE各种操作
 */
public interface IEmailTemplateDao extends IBaseDao<EmailTemplate, String> {

}
