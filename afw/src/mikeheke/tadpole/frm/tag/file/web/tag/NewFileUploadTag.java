package mikeheke.tadpole.frm.tag.file.web.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.tag.file.entity.NewFileUploadTagEntity;
import mikeheke.tadpole.frm.tag.file.service.NewFileUploadTagService;
import mikeheke.tadpole.frm.tag.file.util.NewFileUploadTagConstant;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 文件上传标签
 * 
 * @author Mike He
 * @createDate 2013-11-28
 *
 */
public class NewFileUploadTag extends ComponentTagSupport {

	private static final long serialVersionUID = -6146800641340186178L;

	private String name;
	
	private String value;
	
	private String appCode;
	
	private String moduleCode;
	
	private String userCode;
	
	private String bId;
	
	private String group;
	
	private Integer size;
	
	private boolean isFileList = true;
	
	private String path = NewFileUploadTagConstant.EMPTY_STR;
	
	private boolean isEnc = true;
	
	/**
	 * 文件列表是否显示：上传人
	 * true:显示
	 * false:不显示
	 */
	private String isShowUploadUser = "true";
	
	/**
	 * 文件列表是否显示：上传时间
	 * true:显示
	 * false:不显示 
	 */
	private String isShowUploadDate = "true";
	
	private Long maximumSize = NewFileUploadTagConstant.UPLOAD_MAX_SIZE;
	
//	private FileUpDownloadTagService fileUpDownloadTagService;
	
	private NewFileUploadTagService newFileUploadTagService;
	
//	public FileUpDownloadTag(){
//		final String beanName = "FileUpDownloadTagService";
//		FileUpDownloadTagService fileUpDownloadTagService = (FileUpDownloadTagService) ContextFactory
//				.getBean(beanName);
//		this.fileUpDownloadTagService = fileUpDownloadTagService;
//	}
	
	public NewFileUploadTag() {
		final String beanName = "newFileUploadTagService";
		NewFileUploadTagService newFileUploadTagService = (NewFileUploadTagService) ContextFactory.getBean(beanName);
		this.newFileUploadTagService = newFileUploadTagService;
	}
	
	/**
	 * 设置应用路径以及返回标签体
	 * @param stack
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		
		return new Component(stack);
	}

	@Override
	protected String getBody() {
		
		StringBuffer buf = this.generateHtmlControl();
		
		return buf.toString();
	}
	
//	private TagFileUpload getEntity(){
//		
//		TagFileUpload fileUpload = new TagFileUpload();
//		if(DataValidater.isStrLong(appCode)){
//			fileUpload.setApplicationId((appCode));
//		}
//		//if(DataValidater.isStrLong(moduleCode)){
//			fileUpload.setModuleId(moduleCode);
//		//}
//		fileUpload.setUploadUser(userCode);
//		if(DataValidater.isStrInteger(bId)){
//			fileUpload.setBizId(DataConverter.stringToInteger(bId));
//		}
//	
//		return fileUpload;
//	}
	
	private NewFileUploadTagEntity getEntity(){
		
		NewFileUploadTagEntity fileUpload = new NewFileUploadTagEntity();
		if(DataValidater.isStrLong(appCode)){
			fileUpload.setApplicationId((appCode));
		}
		//if(DataValidater.isStrLong(moduleCode)){
			fileUpload.setModuleId(moduleCode);
		//}
		fileUpload.setUploadUser(userCode);
		if(DataValidater.isStrLong(bId)){
			fileUpload.setBizId(DataConverter.stringToLong(bId));
		}
	
		return fileUpload;
	}
	
	/**
	 * 生成页面HTML代码
	 * @return  StringBuffer字符对象
	 */
	public StringBuffer generateHtmlControl(){
		
		StringBuffer buf = new StringBuffer(NewFileUploadTagConstant.EMPTY_STR);
		
		long number = System.nanoTime();
		
		this.addHtmlControlPre(buf, number);
		
		this.generateSubForm(buf, number);
		
		this.generateFileList(buf, number);

		this.generateHideIFrame(buf, number);
		
		this.addHtmlControlAft(buf);
		
		return buf;
	}
	
	private void addHtmlControlPre(StringBuffer buf, long number){
		
		buf.append(NewFileUploadTagConstant.TAG_DIV_BEGIN);
		buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.ID);
		buf.append(NewFileUploadTagConstant.EQUAL_SIGN);
		final String ID_PRE = "df_";
		buf.append(ID_PRE);
		buf.append(number);
		final String DIV_END = " align='left' style='margin:10px' >";
		buf.append(DIV_END);
		
	}
	
	private void addHtmlControlAft(StringBuffer buf){
		buf.append(NewFileUploadTagConstant.TAG_DIV_END);
	}

	private void generateSubForm(StringBuffer buf, long number){
		
		final String DIV_BEGIN = "<div id='m_";
		buf.append(DIV_BEGIN).append(number);
		final String DIV_END = "' style='color:red'></div>";
		buf.append(DIV_END);
		final String SPAN_BEGIN = "<span id='dl_";
		buf.append(SPAN_BEGIN).append(number);
		final String SPAN_STYLE = "' style='display: ";
		buf.append(SPAN_STYLE).append((isInGroup()?NewFileUploadTagConstant.EMPTY_STR:NewFileUploadTagConstant.STYLE_DISPLAY_NONE));
		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.RIGHT_J_KUO);
		final String INPUT_FILE = " <input type=file style='width: 350px;' id='f_";
		buf.append(INPUT_FILE).append(number).append(NewFileUploadTagConstant.QUO_S_SIGN);
		final String UPLOAD_NAME = " name='upload' ";
		buf.append(UPLOAD_NAME);
		buf.append((size != null
				?(NewFileUploadTagConstant.EMPTY_ONE_STR+NewFileUploadTagConstant.SIZE+NewFileUploadTagConstant.EQUAL_SIGN+size)
				:NewFileUploadTagConstant.EMPTY_ONE_STR)+NewFileUploadTagConstant.TAG_END+NewFileUploadTagConstant.ENTER_SIGN);
		buf.append(NewFileUploadTagConstant.TAG_SPAN_END);
		
		final String INPUT_BUTTON = " <input type=button value='";
		buf.append(INPUT_BUTTON);
		buf.append((null!=value?value:NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_UPLOAD_MSG));
		final String INPUT_BUTTON_STYLE = "' style='display: ";
		buf.append(INPUT_BUTTON_STYLE);
		buf.append((isInGroup()?NewFileUploadTagConstant.EMPTY_STR:NewFileUploadTagConstant.STYLE_DISPLAY_NONE));
		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.EMPTY_ONE_STR);
		buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.ON_CLICK).append(NewFileUploadTagConstant.EQUAL_SIGN);
		buf.append(NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_ON_CLICK_FUNC);
		buf.append(NewFileUploadTagConstant.LEFT_X_KUO).append(NewFileUploadTagConstant.QUO_S_SIGN);
		buf.append(getContextPath()).append(NewFileUploadTagConstant.UNIX_SEP).append(getFileUploadAction());
		buf.append(NewFileUploadTagConstant.QUS_SIGN).append(NewFileUploadTagConstant.NAME).append(NewFileUploadTagConstant.EQUAL_SIGN);
		buf.append(name).append(NewFileUploadTagConstant.AND_SIGN);
		final String NUMBER = "number";
		buf.append(NUMBER).append(NewFileUploadTagConstant.EQUAL_SIGN).append(number);
		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
		buf.append(number);
		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
		buf.append(getPath());
		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
		buf.append(getIsEnc());        	
		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.DOU_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
		buf.append(getMaximumSize());         				
		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.RIGHT_X_KUO).append(NewFileUploadTagConstant.FEN_SIGN);
		buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TAG_END).append(NewFileUploadTagConstant.ENTER_SIGN);

	}
	 
	private String getFileUploadAction(){
		return NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_ACTION_UP;
	}
	
	private String getFileDownloadAction(){
		return NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_ACTION_DOWN;
	}
	
	private String getContextPath(){
		return pageContext.getServletContext().getContextPath();
	}
	
	private void generateHideIFrame(StringBuffer buf, long number){
		
		final String IFRAME_BEGIN = "<iframe name='hif_";
		buf.append(IFRAME_BEGIN).append(number).append(NewFileUploadTagConstant.QUO_S_SIGN);
		final String ID = " id='hif_";
		buf.append(ID).append(number);
		final String STYLE = "' style='display: none;' >";
		buf.append(STYLE);
		final String IFRAME_END = "</iframe>\n";
		buf.append(IFRAME_END);
		
	}
	
	private void generateFileList(StringBuffer buf, long number){
		
		generaterFileListPre(buf, number);
		
		if(isFileList){
			//List<TagFileUpload> tagFileUpDownloads = fileUpDownloadTagService.queryList(getEntity());
			//List<NewFileUploadTagEntity> tagFileUpDownloads = newFileUploadTagService.queryList(getEntity());
			List<NewFileUploadTagEntity> tagFileUpDownloads = new ArrayList<NewFileUploadTagEntity>();
			
			//业务主表id保证不为null、0时，才进行查找 add by Mike He 20140109
			if (bId != null && !"".equals(bId) && !"0".equals(bId)) {
				tagFileUpDownloads = newFileUploadTagService.queryList(getEntity());
			}
			
			java.util.Collections.sort(tagFileUpDownloads);
			generaterFileList(buf, tagFileUpDownloads);
		}

		generaterFileListAft(buf);
	}
	
//	private void generaterFileList(StringBuffer buf, List<TagFileUpload> tagFileUpDownloads) {
//		
//		for(TagFileUpload data : tagFileUpDownloads) {
//			buf.append(NewFileUploadTagConstant.TR_BEGIN2).append(NewFileUploadTagConstant.EMPTY_ONE_STR);
//			buf.append(NewFileUploadTagConstant.ID).append(NewFileUploadTagConstant.EQUAL_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
//			buf.append(data.getUuid()).append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.RIGHT_J_KUO);
//			buf.append(NewFileUploadTagConstant.ENTER_SIGN);
//			buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_BEGIN);
//			buf.append(getFileListLink(data));
//			buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_END).append(NewFileUploadTagConstant.ENTER_SIGN);
//			buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_BEGIN);
//			buf.append(getFileListHidenCtl(data));
//			buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_END).append(NewFileUploadTagConstant.ENTER_SIGN);
//			buf.append(NewFileUploadTagConstant.TR_END).append(NewFileUploadTagConstant.ENTER_SIGN);
//		}
//	}
	
	private void generaterFileList(StringBuffer buf, List<NewFileUploadTagEntity> tagFileUpDownloads) {
		
		//-------------------标题
		buf.append(NewFileUploadTagConstant.TR_BEGIN2).append(NewFileUploadTagConstant.EMPTY_ONE_STR);
		buf.append(NewFileUploadTagConstant.ID).append(NewFileUploadTagConstant.EQUAL_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
		buf.append("11111111").append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.RIGHT_J_KUO);
		buf.append(NewFileUploadTagConstant.ENTER_SIGN);
		
		//td
		buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_BEGIN);
		buf.append("文件名");
		buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_END).append(NewFileUploadTagConstant.ENTER_SIGN);
		
		//是否显示上传人
		if ("true".equals(isShowUploadUser)) {
			//td
			buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_BEGIN);
			buf.append("上传人");
			buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_END).append(NewFileUploadTagConstant.ENTER_SIGN);
		}
		
		//是否显示上传时间
		if ("true".equals(isShowUploadDate)) {
			//td
			buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_BEGIN);
			buf.append("上传时间");
			buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_END).append(NewFileUploadTagConstant.ENTER_SIGN);
		}
		
		//td
		buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_BEGIN);
		buf.append("操作");
		buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_END).append(NewFileUploadTagConstant.ENTER_SIGN);
		
		buf.append(NewFileUploadTagConstant.TR_END).append(NewFileUploadTagConstant.ENTER_SIGN);
		
		for(NewFileUploadTagEntity data : tagFileUpDownloads) {
			buf.append(NewFileUploadTagConstant.TR_BEGIN2).append(NewFileUploadTagConstant.EMPTY_ONE_STR);
			buf.append(NewFileUploadTagConstant.ID).append(NewFileUploadTagConstant.EQUAL_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
			buf.append(data.getUuid()).append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.RIGHT_J_KUO);
			buf.append(NewFileUploadTagConstant.ENTER_SIGN);
			
			//td
			buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_BEGIN);
			buf.append(getFileListLink(data));
			buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_END).append(NewFileUploadTagConstant.ENTER_SIGN);
			
			//是否显示上传人
			if ("true".equals(isShowUploadUser)) {
				//td
				buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_BEGIN);
				buf.append(getFileUploadUser(data));
				buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_END).append(NewFileUploadTagConstant.ENTER_SIGN);
			}
			
			//是否显示上传时间
			if ("true".equals(isShowUploadDate)) {
				//td
				buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_BEGIN);
				buf.append(getFileUploadDate(data));
				buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_END).append(NewFileUploadTagConstant.ENTER_SIGN);
			}
			
			//td
			buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_BEGIN);
			buf.append(getFileListHidenCtl(data));
			buf.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.TD_END).append(NewFileUploadTagConstant.ENTER_SIGN);
			
			
			buf.append(NewFileUploadTagConstant.TR_END).append(NewFileUploadTagConstant.ENTER_SIGN);
		}
	}
	
	/**
	 * 获取文件上传人
	 * @param tagFileUpDownloads
	 * @return
	 */
	private String getFileUploadUser(NewFileUploadTagEntity tagFileUpDownloads) {
		return tagFileUpDownloads.getUploadUser();
	}
	
	/**
	 * 获取文件上传时间
	 * @param tagFileUpDownloads
	 * @return
	 */
	private String getFileUploadDate(NewFileUploadTagEntity tagFileUpDownloads) {
		return DateFormatUtils.format(tagFileUpDownloads.getCreatedTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 获取文件类型
	 * @param tagFileUpDownloads
	 * @return
	 */
	private String getFileType(NewFileUploadTagEntity tagFileUpDownloads) {
		return tagFileUpDownloads.getFileType();
	}
	
//	private String getFileListLink(TagFileUpload data){
//		
//		StringBuffer link = new StringBuffer();
//		
//		final String A_BEGIN = " <a href='";
//		link.append(A_BEGIN);
//		link.append(getContextPath());
//		link.append(NewFileUploadTagConstant.UNIX_SEP);
//		link.append(getFileDownloadAction());
//		link.append(NewFileUploadTagConstant.QUS_SIGN).append(NewFileUploadTagConstant.ID).append(NewFileUploadTagConstant.EQUAL_SIGN);
//		link.append(data.getUuid()).append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.RIGHT_J_KUO);
//		link.append(data.getCnName());
//		link.append(NewFileUploadTagConstant.TAG_A_END);
//		
//		return link.toString();
//	}
	
	private String getFileListLink(NewFileUploadTagEntity data){
		
		StringBuffer link = new StringBuffer();
		
		final String A_BEGIN = " <a id='tttttttt' href='";
		link.append(A_BEGIN);
		link.append(getContextPath());
		link.append(NewFileUploadTagConstant.UNIX_SEP);
		link.append(getFileDownloadAction());
		link.append(NewFileUploadTagConstant.QUS_SIGN).append(NewFileUploadTagConstant.ID).append(NewFileUploadTagConstant.EQUAL_SIGN);
		link.append(data.getUuid()).append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.RIGHT_J_KUO);
		link.append(data.getCnName());
		link.append(NewFileUploadTagConstant.TAG_A_END);
		
		return link.toString();
	}
	
//	private String getFileListHidenCtl(TagFileUpload data){
//		
//		StringBuffer hidenCtl = new StringBuffer();
//		final String INPUT_HIDDEN_BEGIN = " <input type=hidden ";
//		hidenCtl.append(INPUT_HIDDEN_BEGIN);
//		hidenCtl.append((name!=null
//				?(NewFileUploadTagConstant.EMPTY_ONE_STR+NewFileUploadTagConstant.NAME+NewFileUploadTagConstant.EQUAL_SIGN+name):NewFileUploadTagConstant.EMPTY_STR));
//		hidenCtl.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.VALUE);
//		hidenCtl.append(NewFileUploadTagConstant.EQUAL_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
//		hidenCtl.append(getFileListHidenValue(data)).append(NewFileUploadTagConstant.QUO_S_SIGN);
//		hidenCtl.append(NewFileUploadTagConstant.TAG_END).append(NewFileUploadTagConstant.ENTER_SIGN);
//		final String INPUT_BUTTON_BEGIN = " <input type=button onclick=deleteFile(this) ";
//		hidenCtl.append(INPUT_BUTTON_BEGIN);
//		hidenCtl.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.VALUE).append(NewFileUploadTagConstant.EQUAL_SIGN);
//		hidenCtl.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_DELETE_MSG);
//		final String STYLE = "' style='display: ";
//		hidenCtl.append(STYLE).append((isInGroup()?NewFileUploadTagConstant.EMPTY_STR:NewFileUploadTagConstant.STYLE_DISPLAY_NONE));
//		hidenCtl.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.TAG_END);
//		
//		return hidenCtl.toString();
//	}
	
	private String getFileListHidenCtl(NewFileUploadTagEntity data){
		
		StringBuffer hidenCtl = new StringBuffer();
		final String INPUT_HIDDEN_BEGIN = " <input type=hidden ";
		hidenCtl.append(INPUT_HIDDEN_BEGIN);
		hidenCtl.append((name!=null
				?(NewFileUploadTagConstant.EMPTY_ONE_STR+NewFileUploadTagConstant.NAME+NewFileUploadTagConstant.EQUAL_SIGN+name):NewFileUploadTagConstant.EMPTY_STR));
		hidenCtl.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.VALUE);
		hidenCtl.append(NewFileUploadTagConstant.EQUAL_SIGN).append(NewFileUploadTagConstant.QUO_S_SIGN);
		hidenCtl.append(getFileListHidenValue(data)).append(NewFileUploadTagConstant.QUO_S_SIGN);
		hidenCtl.append(NewFileUploadTagConstant.TAG_END).append(NewFileUploadTagConstant.ENTER_SIGN);
		//final String INPUT_BUTTON_BEGIN = " <input type=button onclick=deleteFile(this) ";
		final String INPUT_BUTTON_BEGIN = " <input type=button onclick="+NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_JS_DELETE_FUNC + " ";
		hidenCtl.append(INPUT_BUTTON_BEGIN);
		hidenCtl.append(NewFileUploadTagConstant.EMPTY_ONE_STR).append(NewFileUploadTagConstant.VALUE).append(NewFileUploadTagConstant.EQUAL_SIGN);
		hidenCtl.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.FILE_UP_DOWN_LOAD_DELETE_MSG);
		final String STYLE = "' style='display: ";
		hidenCtl.append(STYLE).append((isInGroup()?NewFileUploadTagConstant.EMPTY_STR:NewFileUploadTagConstant.STYLE_DISPLAY_NONE));
		hidenCtl.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.TAG_END);
		
		return hidenCtl.toString();
	}
	
//	private String getFileListHidenValue(TagFileUpload data){
//		
//		StringBuffer hiddenValue =  new StringBuffer();
//		hiddenValue.append(data.getUuid());
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
	 * Uuid|cnName|fileEncryptName|fileType|savePath|uploadUser|Y
	 */
	private String getFileListHidenValue(NewFileUploadTagEntity data){
		
		StringBuffer hiddenValue =  new StringBuffer();
		hiddenValue.append(data.getUuid());
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
	
	private void generaterFileListPre(StringBuffer buf, long number){
		
		//table style
		final String tableStyle = " style='BORDER-COLLAPSE: collapse' borderColor='#000000' bgColor='#e1f8ff' cellSpacing='0' width='600' border='1' ";
		final String DIV = " <div>\n";
		buf.append(DIV);
		final String TABLE = "<table "+tableStyle+" id='fl_";
		buf.append(TABLE);
		buf.append(number);
		buf.append(NewFileUploadTagConstant.QUO_S_SIGN).append(NewFileUploadTagConstant.TAG_END).append(NewFileUploadTagConstant.ENTER_SIGN);
	}
	
	private void generaterFileListAft(StringBuffer buf){
	
		buf.append(NewFileUploadTagConstant.TAG_TABLE_END).append(NewFileUploadTagConstant.ENTER_SIGN);	
		buf.append(NewFileUploadTagConstant.TAG_DIV_END).append(NewFileUploadTagConstant.ENTER_SIGN);	
	}
	
	protected boolean isInGroup() {
		
		boolean result = false;
		if(this.group == null){
			return true;
		}
		
		String [] groups = group.split(NewFileUploadTagConstant.DOU_SIGN);
		for(String groupName : groups){
			String groupValue = (String) pageContext.getRequest().getAttribute(groupName);
			if(null == groupValue){
				continue;
			}
			if(NewFileUploadTagConstant.TRUE_STR.equals(groupValue)){
				return true;
			}
		}
	
		return result;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public boolean isFileList() {
		return isFileList;
	}
	
	public void setIsFileList(boolean isFileList) {
		this.isFileList = isFileList;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSize() {
		return size;
	}
	
	public void setSize(Integer size) {
		this.size = size;
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
	
	public Long getMaximumSize() {
		return maximumSize;
	}
	
	public void setMaximumSize(Long maximumSize) {
		this.maximumSize = maximumSize;
	}
	
//	public void setFileUpDownloadTagService(
//			FileUpDownloadTagService fileUpDownloadTagService) {
//		this.fileUpDownloadTagService = fileUpDownloadTagService;
//	}

	public void setNewFileUploadTagService(
			NewFileUploadTagService newFileUploadTagService) {
		this.newFileUploadTagService = newFileUploadTagService;
	}

	public String getIsShowUploadUser() {
		return isShowUploadUser;
	}

	public void setIsShowUploadUser(String isShowUploadUser) {
		this.isShowUploadUser = isShowUploadUser;
	}

	public String getIsShowUploadDate() {
		return isShowUploadDate;
	}

	public void setIsShowUploadDate(String isShowUploadDate) {
		this.isShowUploadDate = isShowUploadDate;
	}
}
