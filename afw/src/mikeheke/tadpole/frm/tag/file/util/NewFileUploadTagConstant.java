package mikeheke.tadpole.frm.tag.file.util;

import mikeheke.tadpole.frm.base.util.AppConstant;

/**
 * 文件上传标签-常量
 * 
 * @author Mike He
 * @createDate 2013-11-28
 *
 */
public interface NewFileUploadTagConstant extends AppConstant {
	
	public static final String FILE_UPLOAD_PATH = "fileUploadPath";

	public static final Long UPLOAD_MAX_SIZE = 2l;
	
	public static final String FILE_SIZE_UNIT = "M";
	
	public static final String FILE_UP_DOWN_LOAD_UPLOAD_MSG = "上传";
	
	public static final String FILE_UP_DOWN_LOAD_DELETE_MSG = "删除";
	
	public static final String FILE_NO_EMPTY_MSG = "文件不能为空";
	
	public static final String FILE_UP_DOWN_LOAD_SIZE_MSG = "文件不能大于";
	
	
	/**
	 * 页面JS上传函数
	 */
	public static final String FILE_UP_DOWN_LOAD_ON_CLICK_FUNC = "uploadOnclick_2";
	
	/**
	 * 文件上传成功，JS回调函数
	 */
	public static final String FILE_UP_DOWN_LOAD_JS_SUC_FUNC = "window.parent.uploadCallback_2";
	
	/**
	 * 文件上传失败，JS回调函数
	 */
	public static final String FILE_UP_DOWN_LOAD_JS_FAIL_FUNC = "window.parent.uploadFailCallback_2";
	
	/**
	 * 删除已上传文件，JS函数
	 */
	public static final String FILE_UP_DOWN_LOAD_JS_DELETE_FUNC = "deleteFile_2(this)";
	
	/**
	 * 上传文件url
	 */
	public static final String FILE_UP_DOWN_LOAD_ACTION_UP = "newFileUploadTagAction!uploadFile.action";
	
	/**
	 * 下载文件url
	 */
	public static final String FILE_UP_DOWN_LOAD_ACTION_DOWN = "newFileUploadTagAction!downloadFile.action";
}
