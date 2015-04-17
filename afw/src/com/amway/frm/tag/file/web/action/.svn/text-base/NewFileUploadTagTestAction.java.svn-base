package com.amway.frm.tag.file.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
//import com.amway.frm.tag.entity.TagFileUpload;
//import com.amway.frm.tag.exception.TagInfoException;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;
import com.amway.frm.tag.file.entity.NewFileUploadTagEntity;
import com.amway.frm.tag.file.service.NewFileUploadTagService;
import com.amway.frm.tag.file.util.NewFileUploadTagConstant;
//import com.amway.frm.tag.service.FileUpDownloadTagService;
//import com.amway.frm.tag.util.TagConstant;

/**
 * 文件上传标签控制器-TEST
 * 
 * @author Mike He
 * @createDate 2013-12-02
 *
 */
public class NewFileUploadTagTestAction extends BaseAction  {
	
	private static LogService logger = LogFactory.getLogger(NewFileUploadTagTestAction.class);
	
	//上传的文件信息(必须)
	private String[] fileInfoArr;
	
	private Long applicationId;
	
	private String moduleCode;
	
	private Long businessTableId;
	
	private String uploadUser;
	
	private NewFileUploadTagService newFileUploadTagService;

	/**
	 * 初始化文件上传页面
	 * @return
	 */
	public String initForm() {
		
		//init value
		applicationId = 241L;
		moduleCode = "shop_invoice_edit";
		businessTableId = 4941L;
		uploadUser = "";
		
		return SUCCESS;
	}
	
	/**
	 * 提交上传文件表单
	 * @return
	 * @throws Exception 
	 */
	public String submitForm() throws Exception {
		
		//----------init value----------
		applicationId = 241L;
		moduleCode = "shop_invoice_edit";
		businessTableId = 4941L;
		uploadUser = "";
		
		newFileUploadTagService.saveFileInfoToDB(fileInfoArr, applicationId, moduleCode, businessTableId);
		
		return SUCCESS;
	}
	
	public NewFileUploadTagService getNewFileUploadTagService() {
		return newFileUploadTagService;
	}

	public void setNewFileUploadTagService(
			NewFileUploadTagService newFileUploadTagService) {
		this.newFileUploadTagService = newFileUploadTagService;
	}

	public String[] getFileInfoArr() {
		return fileInfoArr;
	}

	public void setFileInfoArr(String[] fileInfoArr) {
		this.fileInfoArr = fileInfoArr;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public Long getBusinessTableId() {
		return businessTableId;
	}

	public void setBusinessTableId(Long businessTableId) {
		this.businessTableId = businessTableId;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}
}
