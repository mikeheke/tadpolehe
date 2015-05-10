/**
 * 
 */
package mikeheke.tadpole.frm.report.service.impl;

import java.util.List;

import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.report.dao.IReportParaDao;
import mikeheke.tadpole.frm.report.entity.ReportInfo;
import mikeheke.tadpole.frm.report.entity.ReportPara;
import mikeheke.tadpole.frm.report.service.ReportParaService;

/**
 * 
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
	 * @see mikeheke.tadpole.frm.report.service.ReportParaService#queryReportParas(mikeheke.tadpole.frm.report.entity.ReportInfo)
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
