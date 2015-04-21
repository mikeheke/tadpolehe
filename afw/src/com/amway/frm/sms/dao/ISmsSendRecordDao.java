package com.amway.frm.sms.dao;

import java.sql.SQLException;

import com.amway.frm.base.dao.IBaseDao;
import com.amway.frm.sms.entity.SmsBasicPara;

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-3-22
 * Time: 11:00:43
 * Declare：短信表对应的DAO，适用于操作短信表，包括SELECT,CREATE,UPDATE,DELETE各种操作
 */
public interface ISmsSendRecordDao extends IBaseDao{
	
    //保存记录信息
	public SmsBasicPara saveRecord(SmsBasicPara smsBasicPara)
			throws SQLException;
	public SmsBasicPara saveRemotRecord(SmsBasicPara smsBasicPara)
		throws SQLException;
	public SmsBasicPara saveLocalRecord(SmsBasicPara smsBasicPara)
		throws SQLException;
}
