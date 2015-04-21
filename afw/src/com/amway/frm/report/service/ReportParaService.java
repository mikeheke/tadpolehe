/**
 * 
 */
package com.amway.frm.report.service;

import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.report.entity.ReportInfo;
import com.amway.frm.report.entity.ReportPara;

/**
 * 
 *
 * 2011-9-6 下午05:27:43
 */
public interface ReportParaService extends BaseService {

	ReturnMessage<ReportPara> queryReportParas(ReportInfo reportInfo);
}
