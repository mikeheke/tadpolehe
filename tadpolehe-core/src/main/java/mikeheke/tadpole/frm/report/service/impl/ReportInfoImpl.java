/**
 * 
 */
package mikeheke.tadpole.frm.report.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.bds.service.BaseDataSourceService;
import mikeheke.tadpole.frm.bds.util.BdsConstant;
import mikeheke.tadpole.frm.bds.vo.BaseDataSourceVo;
import mikeheke.tadpole.frm.report.dao.IReportInfoDao;
import mikeheke.tadpole.frm.report.entity.ReportInfo;
import mikeheke.tadpole.frm.report.entity.ReportPara;
import mikeheke.tadpole.frm.report.entity.ReportSql;
import mikeheke.tadpole.frm.report.exception.ReportInfoException;
import mikeheke.tadpole.frm.report.service.ReportInfoService;
import mikeheke.tadpole.frm.report.util.ReportConstant;
import mikeheke.tadpole.frm.tag.entity.TagFileUpload;

import org.springframework.transaction.annotation.Transactional;

/**
 * 
 *
 * 2011-9-6 下午05:28:26
 */
public class ReportInfoImpl extends BaseImpl implements ReportInfoService {

	private BaseDataSourceService baseDataSourceService;
	
	private IReportInfoDao reportInfoDao;

	public void setReportInfoDao(IReportInfoDao reportInfoDao) {
		this.reportInfoDao = reportInfoDao;
	}

	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.report.service.ReportInfoService#addReportInfo(mikeheke.tadpole.frm.report.entity.ReportInfo, java.lang.String[])
	 */
	@Override
	@Transactional
	public ReturnMessage<ReportInfo> add(ReportInfo reportInfo,
			List<TagFileUpload> fileUploads) {
		
		if (!DataValidater.isCollectionEmpty(fileUploads)){
			ReturnMessage<TagFileUpload> returnMessage = updateList(fileUploads);
			if(!returnMessage.isSuccess()){
				throw new ReportInfoException(ReportConstant.REPORT_INFO_EXP_0001);
			}
		}

		return this.addCom(reportInfo);
	}
	
	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.report.service.ReportInfoService#addReportInfo(mikeheke.tadpole.frm.report.entity.ReportInfo, java.lang.String[])
	 */
	@Override
	@Transactional
	public ReturnMessage<ReportInfo> update(ReportInfo reportInfo,
			List<TagFileUpload> fileUploads) {
		
		TagFileUpload tagFileUploadQuery = new TagFileUpload();
		tagFileUploadQuery.setApplicationId(getSysInfo().getApplication().getApplicationId());
		tagFileUploadQuery.setModuleId(ReportConstant.REPORT_FILE_MODULE+reportInfo.getReportCode());
		ReturnMessage<TagFileUpload> returnMessage = deleteList(queryList(tagFileUploadQuery));
		
		if(!returnMessage.isSuccess()){
			throw new ReportInfoException(ReportConstant.REPORT_INFO_EXP_0002);
		}
		
		if (!DataValidater.isCollectionEmpty(fileUploads)){
			returnMessage = updateList(fileUploads);
			if(!returnMessage.isSuccess()){
				throw new ReportInfoException(ReportConstant.REPORT_INFO_EXP_0003);
			}
		}

		return this.update(reportInfo);
	}
	
	@Override
	@Transactional
	public ReturnMessage logicDeleteList(List objs) {
		
		if(null == objs){
			return new ReturnMessage();
		}
		
		for(Object obj: objs){
			ReportInfo reportInfo = (ReportInfo)obj;
			
			ReportPara reportPara = new ReportPara();
			reportPara.setReportInfo(reportInfo);
			this.logicDelete(reportPara);
			
			ReportSql reportSql = new ReportSql();
			reportSql.setReportInfo(reportInfo);
			this.logicDelete(reportSql);
		}
		
		return super.logicDeleteList(objs);
	}
	
	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.report.service.ReportShowService#getReportFilePath()
	 */
	@Override
	public String getReportFilePath() {
		
		BaseDataSourceVo baseDataVo = baseDataSourceService.getBdsVoData(
				ReportConstant.REPORT_SAVE_PATH,
				new HashMap<String, String[]>(), ReportConstant.SQL_AND).getReturnObject();
		String filePath = (String) baseDataVo.getColumnSingle(BdsConstant.FIXED_COL_NAME_DN);
		if(!new File(filePath).exists()){
			throw new ReportInfoException(ReportConstant.REPORT_INFO_EXP_0004);
		}
		
		return filePath;
		
	}
	
	public void setBaseDataSourceService(BaseDataSourceService baseDataSourceService) {
		this.baseDataSourceService = baseDataSourceService;
	}
}
