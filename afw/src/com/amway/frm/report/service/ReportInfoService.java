/**
 * 
 */
package com.amway.frm.report.service;

import java.util.List;

import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.report.entity.ReportInfo;
import com.amway.frm.tag.entity.TagFileUpload;

/**
 * 
 *
 * 2011-9-6 下午05:27:43
 */
public interface ReportInfoService extends BaseService {

	String getReportFilePath();
	
	ReturnMessage<ReportInfo> add(ReportInfo reportInfo,
			List<TagFileUpload> fileUploads);
	
	ReturnMessage<ReportInfo> update(ReportInfo reportInfo,
			List<TagFileUpload> fileUploads);
}
