/**
 * 
 */
package com.amway.frm.report.service.impl;

import java.util.List;

import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.report.dao.IReportParaDao;
import com.amway.frm.report.entity.ReportInfo;
import com.amway.frm.report.entity.ReportPara;
import com.amway.frm.report.service.ReportParaService;

/**
 * @author huangweijin
 *
 * 2011-9-6 下午05:28:26
 */
public class ReportParaImpl extends BaseImpl implements ReportParaService {

	private IReportParaDao reportParaDao;

	public void setReportParaDao(IReportParaDao reportParaDao) {
		this.reportParaDao = reportParaDao;
	}

	public IReportParaDao getReportParaDao() {
		return reportParaDao;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.report.service.ReportParaService#queryReportParas(com.amway.frm.report.entity.ReportInfo)
	 */
	@Override
	public ReturnMessage<ReportPara> queryReportParas(ReportInfo reportInfo) {
		
		ReturnMessage<ReportPara> returnMessage = new ReturnMessage<ReportPara>();
		
		ReportPara reportPara = new ReportPara();
		reportPara.setReportInfo(reportInfo);
		
		List<ReportPara> reportParas = queryList(reportPara);
		
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnObjects(reportParas);
		
		return returnMessage;
	}

}
