/**
 * 
 */
package com.amway.frm.report.service;

import java.io.OutputStream;
import java.util.Map;

import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.report.entity.ReportInfo;

/**
 * 
 *
 * 2011-9-16 上午11:40:50
 */
public interface ReportShowService extends BaseService {

	/**
	 * @param reportInfo
	 * @param params
	 * @return
	 */
	ReturnMessage<Map<String, Object>> getDataResult(ReportInfo reportInfo,
			Map<String, Object> params);
	
	void makeOnce(ReportInfo reportInfo, Map<String, Object> params,
			OutputStream outputStream);
	
	void makeCache();
	
	String getCacheReportFilePath(ReportInfo reportInfo, Map<String, Object> params);
}
