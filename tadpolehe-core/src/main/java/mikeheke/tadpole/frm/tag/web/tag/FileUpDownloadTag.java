
package mikeheke.tadpole.frm.tag.web.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.tag.entity.TagFileUpload;
import mikeheke.tadpole.frm.tag.service.FileUpDownloadTagService;
import mikeheke.tadpole.frm.tag.util.TagConstant;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 文件上传标签
 * 
 */
public class FileUpDownloadTag extends ComponentTagSupport {

	private static final long serialVersionUID = -5196480943592588920L;
	
	private String name;
	
	private String value;
	
	private String appCode;
	
	private String moduleCode;
	
	private String userCode;
	
	private String bId;
	
	private String group;
	
	private Integer size;
	
	private boolean isFileList = true;
	
	private String path = TagConstant.EMPTY_STR;
	
	private boolean isEnc = true;
	
	private Long maximumSize = TagConstant.UPLOAD_MAX_SIZE;
	
	private FileUpDownloadTagService fileUpDownloadTagService;
	
	public FileUpDownloadTag(){
		final String beanName = "FileUpDownloadTagService";
		FileUpDownloadTagService fileUpDownloadTagService = (FileUpDownloadTagService) ContextFactory
				.getBean(beanName);
		this.fileUpDownloadTagService = fileUpDownloadTagService;
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
	
	private TagFileUpload getEntity(){
		
		TagFileUpload fileUpload = new TagFileUpload();
		if(DataValidater.isStrLong(appCode)){
			fileUpload.setApplicationId((appCode));
		}
		//if(DataValidater.isStrLong(moduleCode)){
			fileUpload.setModuleId(moduleCode);
		//}
		fileUpload.setUploadUser(userCode);
		if(DataValidater.isStrInteger(bId)){
			fileUpload.setBizId(DataConverter.stringToInteger(bId));
		}
	
		return fileUpload;
	}
	/**
	 * 生成页面HTML代码
	 * @return  StringBuffer字符对象
	 */
	public StringBuffer generateHtmlControl(){
		
		StringBuffer buf = new StringBuffer(TagConstant.EMPTY_STR);
		
		long number = System.nanoTime();
		
		this.addHtmlControlPre(buf, number);
		
		this.generateSubForm(buf, number);
		
		this.generateFileList(buf, number);

		this.generateHideIFrame(buf, number);
		
		this.addHtmlControlAft(buf);
		
		return buf;
	}
	
	private void addHtmlControlPre(StringBuffer buf, long number){
		
		buf.append(TagConstant.TAG_DIV_BEGIN);
		buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.ID);
		buf.append(TagConstant.EQUAL_SIGN);
		final String ID_PRE = "df_";
		buf.append(ID_PRE);
		buf.append(number);
		final String DIV_END = " align='left' style='margin:10px' >";
		buf.append(DIV_END);
		
	}
	
	private void addHtmlControlAft(StringBuffer buf){
		buf.append(TagConstant.TAG_DIV_END);
	}

	private void generateSubForm(StringBuffer buf, long number){
		
		final String DIV_BEGIN = "<div id='m_";
		buf.append(DIV_BEGIN).append(number);
		final String DIV_END = "' style='color:red'></div>";
		buf.append(DIV_END);
		final String SPAN_BEGIN = "<span id='dl_";
		buf.append(SPAN_BEGIN).append(number);
		final String SPAN_STYLE = "' style='display: ";
		buf.append(SPAN_STYLE).append((isInGroup()?TagConstant.EMPTY_STR:TagConstant.STYLE_DISPLAY_NONE));
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.RIGHT_J_KUO);
		final String INPUT_FILE = " <input type=file id='f_";
		buf.append(INPUT_FILE).append(number).append(TagConstant.QUO_S_SIGN);
		final String UPLOAD_NAME = " name='upload' ";
		buf.append(UPLOAD_NAME);
		buf.append((size != null
				?(TagConstant.EMPTY_ONE_STR+TagConstant.SIZE+TagConstant.EQUAL_SIGN+size)
				:TagConstant.EMPTY_ONE_STR)+TagConstant.TAG_END+TagConstant.ENTER_SIGN);
		buf.append(TagConstant.TAG_SPAN_END);
		
		final String INPUT_BUTTON = " <input type=button value='";
		buf.append(INPUT_BUTTON);
		buf.append((null!=value?value:TagConstant.FILE_UP_DOWN_LOAD_UPLOAD_MSG));
		final String INPUT_BUTTON_STYLE = "' style='display: ";
		buf.append(INPUT_BUTTON_STYLE);
		buf.append((isInGroup()?TagConstant.EMPTY_STR:TagConstant.STYLE_DISPLAY_NONE));
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.EMPTY_ONE_STR);
		buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.ON_CLICK).append(TagConstant.EQUAL_SIGN);
		buf.append(TagConstant.FILE_UP_DOWN_LOAD_ON_CLICK_FUNC);
		buf.append(TagConstant.LEFT_X_KUO).append(TagConstant.QUO_S_SIGN);
		buf.append(getContextPath()).append(TagConstant.UNIX_SEP).append(getFileUploadAction());
		buf.append(TagConstant.QUS_SIGN).append(TagConstant.NAME).append(TagConstant.EQUAL_SIGN);
		buf.append(name).append(TagConstant.AND_SIGN);
		final String NUMBER = "number";
		buf.append(NUMBER).append(TagConstant.EQUAL_SIGN).append(number);
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.DOU_SIGN).append(TagConstant.QUO_S_SIGN);
		buf.append(number);
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.DOU_SIGN).append(TagConstant.QUO_S_SIGN);
		buf.append(getPath());
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.DOU_SIGN).append(TagConstant.QUO_S_SIGN);
		buf.append(getIsEnc());        	
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.DOU_SIGN).append(TagConstant.QUO_S_SIGN);
		buf.append(getMaximumSize());         				
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.RIGHT_X_KUO).append(TagConstant.FEN_SIGN);
		buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TAG_END).append(TagConstant.ENTER_SIGN);

	}
	 
	private String getFileUploadAction(){
		return TagConstant.FILE_UP_DOWN_LOAD_ACTION_UP;
	}
	
	private String getFileDownloadAction(){
		return TagConstant.FILE_UP_DOWN_LOAD_ACTION_DOWN;
	}
	
	private String getContextPath(){
		//return pageContext.getServletContext().getContextPath();
		//return pageContext.getServletContext().get
		return "";
	}
	
	private void generateHideIFrame(StringBuffer buf, long number){
		
		final String IFRAME_BEGIN = "<iframe name='hif_";
		buf.append(IFRAME_BEGIN).append(number).append(TagConstant.QUO_S_SIGN);
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
			List<TagFileUpload> tagFileUpDownloads = fileUpDownloadTagService.queryList(getEntity());
			generaterFileList(buf, tagFileUpDownloads);
		}

		generaterFileListAft(buf);
	}
	
	private void generaterFileList(StringBuffer buf, List<TagFileUpload> tagFileUpDownloads) {
		
		for(TagFileUpload data : tagFileUpDownloads) {
			buf.append(TagConstant.TR_BEGIN2).append(TagConstant.EMPTY_ONE_STR);
			buf.append(TagConstant.ID).append(TagConstant.EQUAL_SIGN).append(TagConstant.QUO_S_SIGN);
			buf.append(data.getUuid()).append(TagConstant.QUO_S_SIGN).append(TagConstant.RIGHT_J_KUO);
			buf.append(TagConstant.ENTER_SIGN);
			buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TD_BEGIN);
			buf.append(getFileListLink(data));
			buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TD_END).append(TagConstant.ENTER_SIGN);
			buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TD_BEGIN);
			buf.append(getFileListHidenCtl(data));
			buf.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.TD_END).append(TagConstant.ENTER_SIGN);
			buf.append(TagConstant.TR_END).append(TagConstant.ENTER_SIGN);
		}
	}
	
	private String getFileListLink(TagFileUpload data){
		
		StringBuffer link = new StringBuffer();
		
		final String A_BEGIN = " <a href='";
		link.append(A_BEGIN);
		link.append(getContextPath());
		link.append(TagConstant.UNIX_SEP);
		link.append(getFileDownloadAction());
		link.append(TagConstant.QUS_SIGN).append(TagConstant.ID).append(TagConstant.EQUAL_SIGN);
		link.append(data.getUuid()).append(TagConstant.QUO_S_SIGN).append(TagConstant.RIGHT_J_KUO);
		link.append(data.getCnName());
		link.append(TagConstant.TAG_A_END);
		
		return link.toString();
	}
	
	private String getFileListHidenCtl(TagFileUpload data){
		
		StringBuffer hidenCtl = new StringBuffer();
		final String INPUT_HIDDEN_BEGIN = " <input type=hidden ";
		hidenCtl.append(INPUT_HIDDEN_BEGIN);
		hidenCtl.append((name!=null
				?(TagConstant.EMPTY_ONE_STR+TagConstant.NAME+TagConstant.EQUAL_SIGN+name):TagConstant.EMPTY_STR));
		hidenCtl.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.VALUE);
		hidenCtl.append(TagConstant.EQUAL_SIGN).append(TagConstant.QUO_S_SIGN);
		hidenCtl.append(getFileListHidenValue(data)).append(TagConstant.QUO_S_SIGN);
		hidenCtl.append(TagConstant.TAG_END).append(TagConstant.ENTER_SIGN);
		final String INPUT_BUTTON_BEGIN = " <input type=button onclick=deleteFile(this) ";
		hidenCtl.append(INPUT_BUTTON_BEGIN);
		hidenCtl.append(TagConstant.EMPTY_ONE_STR).append(TagConstant.VALUE).append(TagConstant.EQUAL_SIGN);
		hidenCtl.append(TagConstant.QUO_S_SIGN).append(TagConstant.FILE_UP_DOWN_LOAD_DELETE_MSG);
		final String STYLE = "' style='display: ";
		hidenCtl.append(STYLE).append((isInGroup()?TagConstant.EMPTY_STR:TagConstant.STYLE_DISPLAY_NONE));
		hidenCtl.append(TagConstant.QUO_S_SIGN).append(TagConstant.TAG_END);
		
		return hidenCtl.toString();
	}
	
	private String getFileListHidenValue(TagFileUpload data){
		
		StringBuffer hiddenValue =  new StringBuffer();
		hiddenValue.append(data.getUuid());
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
	
	private void generaterFileListPre(StringBuffer buf, long number){
		
		final String DIV = " <div>\n";
		buf.append(DIV);
		final String TABLE = "<table id='fl_";
		buf.append(TABLE);
		buf.append(number);
		buf.append(TagConstant.QUO_S_SIGN).append(TagConstant.TAG_END).append(TagConstant.ENTER_SIGN);
	}
	
	private void generaterFileListAft(StringBuffer buf){
	
		buf.append(TagConstant.TAG_TABLE_END).append(TagConstant.ENTER_SIGN);	
		buf.append(TagConstant.TAG_DIV_END).append(TagConstant.ENTER_SIGN);	
	}
	
	protected boolean isInGroup() {
		
		boolean result = false;
		if(this.group == null){
			return true;
		}
		
		String [] groups = group.split(TagConstant.DOU_SIGN);
		for(String groupName : groups){
			String groupValue = (String) pageContext.getRequest().getAttribute(groupName);
			if(null == groupValue){
				continue;
			}
			if(TagConstant.TRUE_STR.equals(groupValue)){
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
	
	public void setFileUpDownloadTagService(
			FileUpDownloadTagService fileUpDownloadTagService) {
		this.fileUpDownloadTagService = fileUpDownloadTagService;
	}
	
}
