/**
 * 
 */
package mikeheke.tadpole.frm.report.service;

import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.report.entity.ReportInfo;
import mikeheke.tadpole.frm.report.entity.ReportPara;

/**
 * 
 *
 * 2011-9-6 下午05:27:43
 */
public interface ReportParaService extends BaseService {

	ReturnMessage<ReportPara> queryReportParas(ReportInfo reportInfo);
}
