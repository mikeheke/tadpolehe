/**
 * 
 */
package mikeheke.tadpole.frm.report.service;

import java.util.List;

import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.report.entity.ReportInfo;
import mikeheke.tadpole.frm.tag.entity.TagFileUpload;

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
