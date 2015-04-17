package com.amway.frm.tag.file.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.logging.service.LogService;
import com.amway.frm.logging.util.LogFactory;
import com.amway.frm.tag.file.entity.NewFileUploadTagEntity;
import com.amway.frm.tag.file.service.NewFileUploadTagService;
import com.amway.frm.tag.file.util.NewFileUploadTagConstant;

/**
 * 文件上传标签控制器
 * 
 * @author Mike He
 * @createDate 2013-11-28
 *
 */
public class NewFileUploadTagAction extends BaseAction  {
	
	private static LogService logger = LogFactory.getLogger(NewFileUploadTagAction.class);
	
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
//	private FileUpDownloadTagService fileUpDownloadTagService;
	
	private NewFileUploadTagService newFileUploadTagService;

	/**
	 * 上传文件
	 * @throws IOException 
	 */
	public String uploadFile() throws IOException{
	
		if(null == upload){
			setFailMessage(NewFileUploadTagConstant.FILE_NO_EMPTY_MSG);
			return NONE;
		}

		final Integer M_NUM = 1048576;
		if(upload.length() > maximumSize*M_NUM){
			setFailMessage(NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_SIZE_MSG+maximumSize+NewFileUploadTagConstant.FILE_SIZE_UNIT);
			return NONE;
		}

//		ReturnMessage<TagFileUpload> returnMessage = null;
//		returnMessage = fileUpDownloadTagService.save(upload, uploadFileName, 
//				uploadContentType, path, isEnc);
		
		ReturnMessage<NewFileUploadTagEntity> returnMessage = null;
		returnMessage = newFileUploadTagService.save(upload, uploadFileName, 
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
		buf.append(NewFileUploadTagConstant.JS_BEGIN);
		buf.append(NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_JS_FAIL_FUNC);
		buf.append(NewFileUploadTagConstant.LEFT_X_KUO).append(NewFileUploadTagConstant.QUO_S_SIGN);
		buf.append(this.number);
		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
		buf.append(msg);
		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.RIGHT_X_KUO).append(NewFileUploadTagConstant.FEN_SIGN);
		buf.append(NewFileUploadTagConstant.JS_END);

		PrintWriter writer = getWriter();
		writer.write(buf.toString());

	}

	
	
//	private void setReturnMessageLink(ReturnMessage<TagFileUpload> returnMessage) 
//		throws IOException{
//		
//		TagFileUpload data = returnMessage.getReturnObject();
//		StringBuffer buf =  new StringBuffer();
//		buf.append(NewFileUploadTagConstant.JS_BEGIN);
//		buf.append(NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_JS_SUC_FUNC);
//		buf.append(NewFileUploadTagConstant.LEFT_X_KUO).append(NewFileUploadTagConstant.QUO_S_SIGN);
//		buf.append(getFileListLink(data));
//		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
//		buf.append(getFileListHidenCtl(data));
//		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
//		buf.append(this.number);
//		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
//		buf.append(returnMessage.getReturnCode());
//		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
//		buf.append(returnMessage.getReturnMsg());
//		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.RIGHT_X_KUO).append(NewFileUploadTagConstant.FEN_SIGN);
//		buf.append(NewFileUploadTagConstant.JS_END);
//		
//		PrintWriter writer = getWriter();
//		writer.write(buf.toString());
//
//	}
	
	private void setReturnMessageLink(ReturnMessage<NewFileUploadTagEntity> returnMessage) 
	throws IOException{
	
	//上传人empNumber
	String empNumber = this.getSysInfo().getUserProfile().getEmpNumber();	
	//上传时间
	String uploadDate = new DataConverter().fmtDateToDateStr(new Date(), "yyyy-MM-dd");
		
	NewFileUploadTagEntity data = returnMessage.getReturnObject();
	StringBuffer buf =  new StringBuffer();
	buf.append(NewFileUploadTagConstant.JS_BEGIN);
	buf.append(NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_JS_SUC_FUNC);
	buf.append(NewFileUploadTagConstant.LEFT_X_KUO).append(NewFileUploadTagConstant.QUO_S_SIGN);
	buf.append(getFileListLink(data));
	buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
	buf.append(getFileListHidenCtl(data));
	buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
	buf.append(this.number);
	buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
	buf.append(returnMessage.getReturnCode());
	buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
	buf.append(returnMessage.getReturnMsg());
	buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN);
	buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(empNumber).append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN);
	buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(uploadDate).append(NewFileUploadTagConstant.QUO_S_SIGN);
	buf.append(NewFileUploadTagConstant.RIGHT_X_KUO).append(NewFileUploadTagConstant.FEN_SIGN);
	buf.append(NewFileUploadTagConstant.JS_END);
	
	logger.info("文件上传完成，回调函数=====> "+buf.toString());
//	System.out.println("文件上传完成，回调函数=====> "+buf.toString());
	
	PrintWriter writer = getWriter();
	writer.write(buf.toString());

}

//	
//	private String getFileListLink(TagFileUpload data){
//		
//		StringBuffer link = new StringBuffer();
//		
//		link.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TAG_A_BEGIN);
//		link.append(data.getCnName());
//		link.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TAG_A_END);
//		
//		return link.toString();
//	}
	
	private String getFileListLink(NewFileUploadTagEntity data){
		
		StringBuffer link = new StringBuffer();
		
		link.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TAG_A_BEGIN);
		link.append(data.getCnName());
		link.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TAG_A_END);
		
		return link.toString();
	}
	
//	private String getFileListHidenCtl(TagFileUpload data){
//		
//		StringBuffer hidenCtl = new StringBuffer();
//		hidenCtl.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.LEFT_J_KUO).append(NewFileUploadTagConstant.TAG_INPUT);
//		hidenCtl.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TYPE).append(NewFileUploadTagConstant.EQUAL_SIGN);
//		hidenCtl.append(NewFileUploadTagConstant.HIDDEN).append(NewFileUploadTagConstant.EMPTY_ONE_STR);
//		hidenCtl.append((name!=null
//				?(NewFileUploadTagConstant.EMPTY_ONE_STR+NewFileUploadTagConstant.NAME+NewFileUploadTagConstant.EQUAL_SIGN+name):NewFileUploadTagConstant.EMPTY_STR)
//				+ NewFileUploadTagConstant.EMPTY_ONE_STR+NewFileUploadTagConstant.VALUE+NewFileUploadTagConstant.EQUAL_SIGN+NewFileUploadTagConstant.QUO_D_SIGN
//				+ getFileListHidenValue(data)+NewFileUploadTagConstant.QUO_D_SIGN+NewFileUploadTagConstant.TAG_END);
//		hidenCtl.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.LEFT_J_KUO).append(NewFileUploadTagConstant.TAG_INPUT);
//		hidenCtl.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TYPE).append(NewFileUploadTagConstant.EQUAL_SIGN);
//		hidenCtl.append(NewFileUploadTagConstant.BUTTON).append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.ON_CLICK).append(NewFileUploadTagConstant.EQUAL_SIGN);
//		hidenCtl.append(NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_JS_DELETE_FUNC).append(NewFileUploadTagConstant.EMPTY_ONE_STR);
//		hidenCtl.append(NewFileUploadTagConstant.VALUE).append(NewFileUploadTagConstant.EQUAL_SIGN).append(NewFileUploadTagConstant.QUO_D_SIGN);
//		hidenCtl.append(NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_DELETE_MSG);
//		hidenCtl.append(NewFileUploadTagConstant.QUO_D_SIGN).append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TAG_END);
//		
//		return hidenCtl.toString();
//	}
	
	private String getFileListHidenCtl(NewFileUploadTagEntity data){
		
		StringBuffer hidenCtl = new StringBuffer();
		hidenCtl.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.LEFT_J_KUO).append(NewFileUploadTagConstant.TAG_INPUT);
		hidenCtl.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TYPE).append(NewFileUploadTagConstant.EQUAL_SIGN);
		hidenCtl.append(NewFileUploadTagConstant.HIDDEN).append(NewFileUploadTagConstant.EMPTY_ONE_STR);
		hidenCtl.append((name!=null
				?(NewFileUploadTagConstant.EMPTY_ONE_STR+NewFileUploadTagConstant.NAME+NewFileUploadTagConstant.EQUAL_SIGN+name):NewFileUploadTagConstant.EMPTY_STR)
				+ NewFileUploadTagConstant.EMPTY_ONE_STR+NewFileUploadTagConstant.VALUE+NewFileUploadTagConstant.EQUAL_SIGN+NewFileUploadTagConstant.QUO_D_SIGN
				+ getFileListHidenValue(data)+NewFileUploadTagConstant.QUO_D_SIGN+NewFileUploadTagConstant.TAG_END);
		hidenCtl.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.LEFT_J_KUO).append(NewFileUploadTagConstant.TAG_INPUT);
		hidenCtl.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TYPE).append(NewFileUploadTagConstant.EQUAL_SIGN);
		hidenCtl.append(NewFileUploadTagConstant.BUTTON).append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.ON_CLICK).append(NewFileUploadTagConstant.EQUAL_SIGN);
		hidenCtl.append(NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_JS_DELETE_FUNC).append(NewFileUploadTagConstant.EMPTY_ONE_STR);
		hidenCtl.append(NewFileUploadTagConstant.VALUE).append(NewFileUploadTagConstant.EQUAL_SIGN).append(NewFileUploadTagConstant.QUO_D_SIGN);
		hidenCtl.append(NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_DELETE_MSG);
		hidenCtl.append(NewFileUploadTagConstant.QUO_D_SIGN).append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TAG_END);
		
		return hidenCtl.toString();
	}
	
//	private String getFileListHidenValue(TagFileUpload data){
//		
//		StringBuffer hiddenValue = new StringBuffer();
//		hiddenValue.append(NewFileUploadTagConstant.EMPTY_STR);
//		hiddenValue.append(NewFileUploadTagConstant.VER_LINE_SIGN);
//		hiddenValue.append(data.getCnName());
//		hiddenValue.append(NewFileUploadTagConstant.VER_LINE_SIGN);
//		hiddenValue.append(data.getFileEncryptName());
//		hiddenValue.append(NewFileUploadTagConstant.VER_LINE_SIGN);
//		hiddenValue.append(data.getFileType());
//		hiddenValue.append(NewFileUploadTagConstant.VER_LINE_SIGN);
//		hiddenValue.append(data.getSavePath());
//		hiddenValue.append(NewFileUploadTagConstant.VER_LINE_SIGN);
//		hiddenValue.append(data.getUploadUser());
//
//		return hiddenValue.toString();
//	}
	
	/**
	 * 拼凑上传文件信息的字符串
	 * Y:说明该文件有效
	 * |cnName|fileEncryptName|fileType|savePath|uploadUser|Y
	 */
	private String getFileListHidenValue(NewFileUploadTagEntity data){
		
		StringBuffer hiddenValue = new StringBuffer();
		hiddenValue.append(NewFileUploadTagConstant.EMPTY_STR);
		hiddenValue.append(NewFileUploadTagConstant.VER_LINE_SIGN);
		hiddenValue.append(data.getCnName());
		hiddenValue.append(NewFileUploadTagConstant.VER_LINE_SIGN);
		hiddenValue.append(data.getFileEncryptName());
		hiddenValue.append(NewFileUploadTagConstant.VER_LINE_SIGN);
		hiddenValue.append(data.getFileType());
		hiddenValue.append(NewFileUploadTagConstant.VER_LINE_SIGN);
		hiddenValue.append(data.getSavePath());
		hiddenValue.append(NewFileUploadTagConstant.VER_LINE_SIGN);
		hiddenValue.append(data.getUploadUser());
		hiddenValue.append(NewFileUploadTagConstant.VER_LINE_SIGN);
		hiddenValue.append("Y");
		
		return hiddenValue.toString();
	}
	
	private PrintWriter getWriter() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		//response.setCharacterEncoding(NewFileUploadTagConstant.GBK);
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding(NewFileUploadTagConstant.UTF_8);
		return response.getWriter();
	}
	
	/**
	 * 下载文件
	 * @return
	 * @throws FileNotFoundException 
	 */
	public String downloadFile() throws FileNotFoundException{
		
		if(!validateId(id)){
			//throw new TagInfoException(NewFileUploadTagConstant.TAG_INFO_EXP_0001);
			throw new RuntimeException("id为空");
		}
		
//		TagFileUpload tagFileUpload = getEntity(id);
		
		NewFileUploadTagEntity tagFileUpload = getEntity(id);
		
		String filePathName = getFilePathName(tagFileUpload);
			
		this.inputStream = getInputStream(filePathName);
		
		this.downloadFileName = DataConverter.strGbkToIso(
				tagFileUpload.getCnName());
		
		this.downloadContentType = tagFileUpload.getFileType();
		
		final String DOWN_LOAD_FILE = "downloadFile";
		return DOWN_LOAD_FILE;
	}
	
//	private String getFilePathName(TagFileUpload tagFileUpload){
//		
//		String savePath = tagFileUpload.getSavePath();
//		String enFileName = tagFileUpload.getFileEncryptName();
//		
//		String filePathName = savePath + NewFileUploadTagConstant.UNIX_SEP + enFileName;
//		
//		return filePathName;
//	}
	
	private String getFilePathName(NewFileUploadTagEntity tagFileUpload){
		
		String savePath = tagFileUpload.getSavePath();
		String enFileName = tagFileUpload.getFileEncryptName();
		
		String filePathName = savePath + NewFileUploadTagConstant.UNIX_SEP + enFileName;
		
		return filePathName;
	}
	
	private InputStream getInputStream(String fileName) throws FileNotFoundException{
		
		InputStream fileIn = new FileInputStream(fileName);
		
		return fileIn;
	}

//	@Override
//	protected TagFileUpload getEntity(String id) {
//		
//		TagFileUpload tagFileUpload = new TagFileUpload();
//		tagFileUpload.setUuid(id);
//		
//		tagFileUpload = (TagFileUpload) fileUpDownloadTagService.querySingle(tagFileUpload);
//		
//		return tagFileUpload;
//	}
	
	@Override
	protected NewFileUploadTagEntity getEntity(String id) {
		
		NewFileUploadTagEntity tagFileUpload = new NewFileUploadTagEntity();
		tagFileUpload.setUuid(id);
		
//		tagFileUpload = (TagFileUpload) fileUpDownloadTagService.querySingle(tagFileUpload);
		tagFileUpload = (NewFileUploadTagEntity)newFileUploadTagService.querySingle(tagFileUpload);
		
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

//	public FileUpDownloadTagService getFileUpDownloadTagService() {
//		return fileUpDownloadTagService;
//	}
//
//	public void setFileUpDownloadTagService(
//			FileUpDownloadTagService fileUpDownloadTagService) {
//		this.fileUpDownloadTagService = fileUpDownloadTagService;
//	}
	
	public NewFileUploadTagService getNewFileUploadTagService() {
		return newFileUploadTagService;
	}

	public void setNewFileUploadTagService(
			NewFileUploadTagService newFileUploadTagService) {
		this.newFileUploadTagService = newFileUploadTagService;
	}

	
}
