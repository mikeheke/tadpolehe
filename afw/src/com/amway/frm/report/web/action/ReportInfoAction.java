/**
 * 
 */
package com.amway.frm.report.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.report.entity.ReportInfo;
import com.amway.frm.report.service.ReportInfoService;
import com.amway.frm.report.util.ReportConstant;
import com.amway.frm.report.vo.ReportInfoVo;
import com.amway.frm.tag.entity.TagFileUpload;

/**
 * 
 *
 * 2011-9-6 下午05:14:20
 */
public class ReportInfoAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2172228604705322261L;

	private ReportInfoVo reportInfoVo;
	
	private ReportInfoService reportInfoService;
	
	private String[] fileList;
	
	@Override
	public String init() {
		
		String filePath = reportInfoService.getReportFilePath();
		setMessage(ReportConstant.REPORT_SAVE_PATH, filePath);
		
		return super.init();
	}

	@Override
	public String initAdd() {
		
		init();
		
		String result = INIT_ADD_SUCCESS;
		this.setOprt(AppConstant.ADD_OPRT);
		
		return result;
	}

	@Override
	public String initModify() {
		
		init();
		
		String result = INIT_MODIFY_SUCCESS;
		this.setOprt(AppConstant.MDF_OPRT);
		
		String[] reportInfoIds = null;
		if(null != reportInfoVo){
			reportInfoIds = reportInfoVo.getReportInfoIds();
		}
		if(!validateIds0(reportInfoIds)){
			return INIT_MODIFY_INPUT;
		}
		ReturnMessage<ReportInfo> returnMessage = null;
		ReportInfo reportInfo = this.getEntity(reportInfoIds[0]);
		returnMessage = reportInfoService.query(reportInfo);
		
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
		
		return result;
	}

	@Override
	public String add() {
		
		init();
		
		String result = ADD_SUCCESS;
		this.setOprt(AppConstant.ADD_OPRT);

		ReportInfo reportInfo = this.getEntity();
		ReturnMessage<ReportInfo> returnMessage = new ReturnMessage<ReportInfo>();
		
		if(!validateData()){
			returnMessage.setReturnObject(reportInfo);
			setReturnMessage(returnMessage);
			return ADD_INPUT;
		}
		
		returnMessage = reportInfoService.add(reportInfo, getFileUploads());
		if(returnMessage.isSuccess()){
			returnMessage.clearReturnObjects();
		}
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
		
		return result;
	}

	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] reportInfoIds = null;
		if(null != reportInfoVo){
			reportInfoIds = reportInfoVo.getReportInfoIds();
		}
		if(validateIds(reportInfoIds)){

			List<ReportInfo> reportInfos = this.getEntities(reportInfoIds);
			ReturnMessage<ReportInfo> returnMessage = reportInfoService.logicDeleteList(reportInfos);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		
		return JSON;
	}

	@Override
	public String modify() {
		
		init();
		
		String result = MDF_SUCCESS;
		this.setOprt(AppConstant.MDF_OPRT);
		
		ReportInfo reportInfo = this.getEntity();
		ReturnMessage<ReportInfo> returnMessage = new ReturnMessage<ReportInfo>();
		
		if(!validateData()){
			returnMessage.setReturnObject(reportInfo);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		String reportInfoId = reportInfoVo.getReportInfoId();
		if(!validateId(reportInfoId)){
			returnMessage.setReturnObject(reportInfo);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		
		returnMessage = reportInfoService.update(reportInfo, getFileUploads());
		
		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		return result;
	}
	
	

	@Override
	protected ReportInfo getEntity() {
		
		ReportInfo reportInfo = new ReportInfo();
		reportInfo.setReportInfoId((reportInfoVo.getReportInfoId()));
		reportInfo.setReportCode(reportInfoVo.getReportCode());
		reportInfo.setReportName(reportInfoVo.getReportName());
		reportInfo.setReportType(DataConverter.stringToInteger(reportInfoVo.getReportType()));
		reportInfo.setReportUrl(reportInfoVo.getReportUrl());
		reportInfo.setState(DataConverter.stringToInteger(reportInfoVo.getState()));
		Application application = new Application();
		application.setApplicationId((reportInfoVo.getApplicationId()));
		reportInfo.setApplication(application);
		reportInfo.setRemark(reportInfoVo.getRemark());
		reportInfo.setCreatedTime(new Date());
		reportInfo.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		reportInfo.setUpdatedTime(new Date());
		reportInfo.setUpdatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		return reportInfo;
	}

	@Override
	protected ReportInfo getEntity(String id) {
		
		ReportInfo reportInfo = new ReportInfo();
		reportInfo.setReportInfoId((id));
		
		return reportInfo;
	}

	@Override
	protected List<ReportInfo> getEntities(String[] ids) {
		
		List<ReportInfo> reportInfos = new ArrayList<ReportInfo>(); 
		
		for(String id: ids){
			ReportInfo reportInfo = getEntity(id);
			reportInfos.add(reportInfo);
		}
		
		return reportInfos;
	}
	
	private List<TagFileUpload> getFileUploads(){
		
		List<TagFileUpload> fileUploads = new ArrayList<TagFileUpload>();

		if(null == fileList){
			return fileUploads;
		}
		for (String fileStr : fileList) {
			String[] fileArray = fileStr.split(AppConstant.SPILT_OPER);
			TagFileUpload tagFileUpload = new TagFileUpload();
			if(!DataValidater.isStrEmpty(fileArray[0])){
				tagFileUpload.setUuid(fileArray[0]);
			}
			tagFileUpload.setCnName(fileArray[1]);
			tagFileUpload.setFileEncryptName(fileArray[2]);
			tagFileUpload.setFileType(fileArray[3]);
			tagFileUpload.setSavePath(fileArray[4]);
			tagFileUpload.setUploadUser(fileArray[5]);
			tagFileUpload.setApplicationId(getSysInfo().getApplication().getApplicationId());
			StringBuffer moduleId = new StringBuffer();
			moduleId.append(ReportConstant.REPORT_FILE_MODULE).append(reportInfoVo.getReportCode());
			tagFileUpload.setModuleId(moduleId.toString());
			tagFileUpload.setState(ReportConstant.START);
			tagFileUpload.setCreatedTime(new Date());
			tagFileUpload.setCreatedUserId((
					getSysInfo().getUserProfile().getUserProfileId()));
			tagFileUpload.setUpdatedTime(new Date());
			tagFileUpload.setUpdatedUserId((
					getSysInfo().getUserProfile().getUserProfileId()));

			fileUploads.add(tagFileUpload);
		}
		
		setFileList(null);
		
		return fileUploads;
	}

	@Override
	protected boolean validateData() {

		return true;
	}

	public ReportInfoVo getReportInfoVo() {
		return reportInfoVo;
	}

	public void setReportInfoVo(ReportInfoVo reportInfoVo) {
		this.reportInfoVo = reportInfoVo;
	}

	public void setReportInfoService(ReportInfoService reportInfoService) {
		this.reportInfoService = reportInfoService;
	}

	@Override
	public String getJsonValue() {

		return super.getJsonValue();
	}

	public String[] getFileList() {
		return fileList;
	}

	public void setFileList(String[] fileList) {
		this.fileList = fileList;
	}
	
}
