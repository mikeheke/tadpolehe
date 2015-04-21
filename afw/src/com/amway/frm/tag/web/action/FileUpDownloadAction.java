
package com.amway.frm.tag.web.action;

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
import com.amway.frm.tag.entity.TagFileUpload;
import com.amway.frm.tag.exception.TagInfoException;
import com.amway.frm.tag.service.FileUpDownloadTagService;
import com.amway.frm.tag.util.TagConstant;

/**
 * 文件上传Action
 * 
 */
public class FileUpDownloadAction extends BaseAction {

	private static final long serialVersionUID = -8815722493337011261L;
	
	private File upload;// 实际上传文件

    private String uploadContentType; // 文件的内容类型

    private String uploadFileName; // 上传文件名
    
	private Long maximumSize;  //上传的文件大小
	
	private Long number;
	
	private String path;
	
	private boolean isEnc;
	
	private String name;
	
	//标识
	private String id;
	
	//文件名
	private String downloadFileName; 
	
	//文件下载的输入流
	private InputStream inputStream;
	
	private String downloadContentType;
	
	//文件上传业务类
	private FileUpDownloadTagService fileUpDownloadTagService;

	/**
	 * 上传文件
	 * @throws IOException 
	 */
	public String uploadFile() throws IOException{
	
		if(null == upload){
			setFailMessage(TagConstant.FILE_NO_EMPTY_MSG);
			return NONE;
		}

		final Integer M_NUM = 1048576;
		if(upload.length() > maximumSize*M_NUM){
			setFailMessage(TagConstant.FILE_UP_DOWN_LOAD_SIZE_MSG+maximumSize+TagConstant.FILE_SIZE_UNIT);
			return NONE;
		}

		ReturnMessage<TagFileUpload> returnMessage = null;
		returnMessage = fileUpDownloadTagService.save(upload, uploadFileName, 
				uploadContentType, path, isEnc);
		
		this.setReturnMessageLink(returnMessage);
		
		return NONE;
	}
	
	
	/**
	 * 上传文件失败信息
	 * @throws IOException 
	 */	
	private void setFailMessage(String msg) throws IOException{

		StringBuffer buf = new StringBuffer();
		buf.append(TagConstant.JS_BEGIN);
		buf.append(TagConstant.FILE_UP_DOWN_LOAD_JS_FAIL_FUNC);
		buf.append(TagConstant.LEFT_X_KUO).append(TagConstant.QUO_S_SIGN);
		buf.append(this.number);
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.DOU_SIGN).append(TagConstant.QUO_S_SIGN);
		buf.append(msg);
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.RIGHT_X_KUO).append(TagConstant.FEN_SIGN);
		buf.append(TagConstant.JS_END);

		PrintWriter writer = getWriter();
		writer.write(buf.toString());

	}

	
	
	private void setReturnMessageLink(ReturnMessage<TagFileUpload> returnMessage) 
		throws IOException{
		
		TagFileUpload data = returnMessage.getReturnObject();
		StringBuffer buf =  new StringBuffer();
		buf.append(TagConstant.JS_BEGIN);
		buf.append(TagConstant.FILE_UP_DOWN_LOAD_JS_SUC_FUNC);
		buf.append(TagConstant.LEFT_X_KUO).append(TagConstant.QUO_S_SIGN);
		buf.append(getFileListLink(data));
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.DOU_SIGN).append(TagConstant.QUO_S_SIGN);
		buf.append(getFileListHidenCtl(data));
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.DOU_SIGN).append(TagConstant.QUO_S_SIGN);
		buf.append(this.number);
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.DOU_SIGN).append(TagConstant.QUO_S_SIGN);
		buf.append(returnMessage.getReturnCode());
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.DOU_SIGN).append(TagConstant.QUO_S_SIGN);
		buf.append(returnMessage.getReturnMsg());
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.RIGHT_X_KUO).append(TagConstant.FEN_SIGN);
		buf.append(TagConstant.JS_END);
		
		PrintWriter writer = getWriter();
		writer.write(buf.toString());

	}

	
	private String getFileListLink(TagFileUpload data){
		
		StringBuffer link = new StringBuffer();
		
		link.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TAG_A_BEGIN);
		link.append(data.getCnName());
		link.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TAG_A_END);
		
		return link.toString();
	}
	
	private String getFileListHidenCtl(TagFileUpload data){
		
		StringBuffer hidenCtl = new StringBuffer();
		hidenCtl.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.LEFT_J_KUO).append(TagConstant.TAG_INPUT);
		hidenCtl.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TYPE).append(TagConstant.EQUAL_SIGN);
		hidenCtl.append(TagConstant.HIDDEN).append(TagConstant.EMPTY_ONE_STR);
		hidenCtl.append((name!=null
				?(TagConstant.EMPTY_ONE_STR+TagConstant.NAME+TagConstant.EQUAL_SIGN+name):TagConstant.EMPTY_STR)
				+ TagConstant.EMPTY_ONE_STR+TagConstant.VALUE+TagConstant.EQUAL_SIGN+TagConstant.QUO_D_SIGN
				+ getFileListHidenValue(data)+TagConstant.QUO_D_SIGN+TagConstant.TAG_END);
		hidenCtl.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.LEFT_J_KUO).append(TagConstant.TAG_INPUT);
		hidenCtl.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TYPE).append(TagConstant.EQUAL_SIGN);
		hidenCtl.append(TagConstant.BUTTON).append(TagConstant.EMPTY_ONE_STR).append(TagConstant.ON_CLICK).append(TagConstant.EQUAL_SIGN);
		hidenCtl.append(TagConstant.FILE_UP_DOWN_LOAD_JS_DELETE_FUNC).append(TagConstant.EMPTY_ONE_STR);
		hidenCtl.append(TagConstant.VALUE).append(TagConstant.EQUAL_SIGN).append(TagConstant.QUO_D_SIGN);
		hidenCtl.append(TagConstant.FILE_UP_DOWN_LOAD_DELETE_MSG);
		hidenCtl.append(TagConstant.QUO_D_SIGN).append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TAG_END);
		
		return hidenCtl.toString();
	}
	
	private String getFileListHidenValue(TagFileUpload data){
		
		StringBuffer hiddenValue = new StringBuffer();
		hiddenValue.append(TagConstant.EMPTY_STR);
		hiddenValue.append(TagConstant.VER_LINE_SIGN);
		hiddenValue.append(data.getCnName());
		hiddenValue.append(TagConstant.VER_LINE_SIGN);
		hiddenValue.append(data.getFileEncryptName());
		hiddenValue.append(TagConstant.VER_LINE_SIGN);
		hiddenValue.append(data.getFileType());
		hiddenValue.append(TagConstant.VER_LINE_SIGN);
		hiddenValue.append(data.getSavePath());
		hiddenValue.append(TagConstant.VER_LINE_SIGN);
		hiddenValue.append(data.getUploadUser());

		return hiddenValue.toString();
	}
	
	private PrintWriter getWriter() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding(TagConstant.GBK);
		return response.getWriter();
	}
	
	/**
	 * 下载文件
	 * @return
	 * @throws FileNotFoundException 
	 */
	public String downloadFile() throws FileNotFoundException{
		
		if(!validateId(id)){
			throw new TagInfoException(TagConstant.TAG_INFO_EXP_0001);
		}
		
		TagFileUpload tagFileUpload = getEntity(id);
		
		String filePathName = getFilePathName(tagFileUpload);
			
		this.inputStream = getInputStream(filePathName);
		
		this.downloadFileName = DataConverter.strGbkToIso(
				tagFileUpload.getCnName());
		
		this.downloadContentType = tagFileUpload.getFileType();
		
		final String DOWN_LOAD_FILE = "downloadFile";
		return DOWN_LOAD_FILE;
	}
	
	private String getFilePathName(TagFileUpload tagFileUpload){
		
		String savePath = tagFileUpload.getSavePath();
		String enFileName = tagFileUpload.getFileEncryptName();
		
		String filePathName = savePath + TagConstant.UNIX_SEP + enFileName;
		
		return filePathName;
	}
	
	private InputStream getInputStream(String fileName) throws FileNotFoundException{
		
		InputStream fileIn = new FileInputStream(fileName);
		
		return fileIn;
	}

	@Override
	protected TagFileUpload getEntity(String id) {
		
		TagFileUpload tagFileUpload = new TagFileUpload();
		tagFileUpload.setUuid(id);
		
		tagFileUpload = (TagFileUpload) fileUpDownloadTagService.querySingle(tagFileUpload);
		
		return tagFileUpload;
	}
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public Long getMaximumSize() {
		return maximumSize;
	}

	public void setMaximumSize(Long maximumSize) {
		this.maximumSize = maximumSize;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean getIsEnc() {
		return isEnc;
	}

	public void setIsEnc(boolean isEnc) {
		this.isEnc = isEnc;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getDownloadContentType() {
		return downloadContentType;
	}

	public void setDownloadContentType(String downloadContentType) {
		this.downloadContentType = downloadContentType;
	}

	public FileUpDownloadTagService getFileUpDownloadTagService() {
		return fileUpDownloadTagService;
	}

	public void setFileUpDownloadTagService(
			FileUpDownloadTagService fileUpDownloadTagService) {
		this.fileUpDownloadTagService = fileUpDownloadTagService;
	}
	
}
