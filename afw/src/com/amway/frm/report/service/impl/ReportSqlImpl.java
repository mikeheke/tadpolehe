/**
 * 
 */
package com.amway.frm.report.service.impl;

import java.util.List;

import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.report.dao.IReportSqlDao;
import com.amway.frm.report.entity.ReportInfo;
import com.amway.frm.report.entity.ReportSql;
import com.amway.frm.report.service.ReportSqlService;

/**
 * 
 *
 * 2011-9-6 下午05:28:26
 */
public class ReportSqlImpl extends BaseImpl implements ReportSqlService {

	private IReportSqlDao reportSqlDao;

	public void setReportSqlDao(IReportSqlDao reportSqlDao) {
		this.reportSqlDao = reportSqlDao;
	}

	public IReportSqlDao getReportSqlDao() {
		return reportSqlDao;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.report.service.ReportSqlService#queryReportSqls(com.amway.frm.report.entity.ReportInfo)
	 */
	@Override
	public ReturnMessage<ReportSql> queryReportSqls(ReportInfo reportInfo) {
		
		ReturnMessage<ReportSql> returnMessage = new ReturnMessage<ReportSql>();
		
		ReportSql reportSql = new ReportSql();
		reportSql.setReportInfo(reportInfo);
		
		List<ReportSql> reportSqls = queryList(reportSql);
		
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnObjects(reportSqls);
		
		return returnMessage;
	}

}
