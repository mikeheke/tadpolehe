package mikeheke.tadpole.frm.tag.util;

import mikeheke.tadpole.frm.base.util.AppConstant;

/**
 * 基础数据服务应用恒量
 * 
 */
public interface TagConstant extends AppConstant{
	//selectTag 下拉标签<option></option>第一项的默认value值
	public static final String DEFAULT_HEADER_KEY = "";
	//selectTag 下拉标签<option></option>第一项的默认text值
	public static final String DEFAULT_HEADER_TEXT = "--请选择--";
	
	public static final String CXT_BASE_PATH_NAME  = "basePath";
	
	public static final String GROUP_SEP = ",";
	
	public static final String GROUP_VALUE = "true";
	
	public static final String DS_TYPE_LIST_ENTITY = "1";
	
	public static final String DS_TYPE_LIST_MAP = "2";
	
	public static final String DS_TYPE_LIST_JSON = "3";
	
	public static final String DS_TYPE_DBS = "4";
	
	public static final String I18N_KEY = "$i18n.";
	
	public static final String AUTO_COMPLETE_TAG_JS_FUNC = "bindAutocomplete";
	
	public static final String AUTO_COMPLETE_TAG_ACTION = "autoCompleteTagAction!findDatas.action'";
	
	public static final String TAG_ID_BEF = "_tag_";
	
	public static final String FILTER_CLASS = "mikeheke.tadpole.frm.tag.filter.impl.TagDataDefaultFilter";
	
	public static final String FILE_NO_EMPTY_MSG = "文件不能为空";
	
	public static final String FILE_SIZE_UNIT = "M";
	
	public static final String FILE_UP_DOWN_LOAD_JS_FAIL_FUNC = "window.parent.uploadFailCallback";
	
	public static final String FILE_UP_DOWN_LOAD_JS_SUC_FUNC = "window.parent.uploadCallback";
	
	public static final String FILE_UP_DOWN_LOAD_JS_DELETE_FUNC = "deleteFile(this)";
	
	public static final String FILE_UP_DOWN_LOAD_ON_CLICK_FUNC = "uploadOnclick";
	
	public static final String FILE_UP_DOWN_LOAD_DELETE_MSG = "删除";
	
	public static final String FILE_UP_DOWN_LOAD_UPLOAD_MSG = "上传";
	
	public static final String FILE_UP_DOWN_LOAD_SIZE_MSG = "文件不能大于";
	
	public static final String TAG_INFO_EXP_0001 = "0001";
	
	public static final String FILE_UP_DOWN_LOAD_ACTION_UP = "fileUpDownloadAction!uploadFile.action";
	
	public static final String FILE_UP_DOWN_LOAD_ACTION_DOWN = "fileUpDownloadAction!downloadFile.action";
	
	public static final String FILE_UPLOAD_PATH = "fileUploadPath";
	
	public static final Long UPLOAD_MAX_SIZE = 2l;
}
