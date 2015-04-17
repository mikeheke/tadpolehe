package com.amway.frm.tag.file.service;

import java.io.File;

import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.tag.file.entity.NewFileUploadTagEntity;

/**
 * 文件上传标签实体业务逻辑接口
 * 
 * @author Mike He
 * @createDate 2013-11-28
 *
 */
public interface NewFileUploadTagService extends BaseService {

	/**
	 * 上传文件到服务器
	 * @param file
	 * @param fileName
	 * @param fileType
	 * @param path
	 * @param isEnc
	 * @return
	 */
	ReturnMessage<NewFileUploadTagEntity> save(File file, String fileName, String fileType,
			String path, boolean isEnc);
	
	/**
	 * 保存上传文件信息到DB
	 * @param fileInfoArr 文件信息字符串数组
	 * @param applicationId 应用ID
	 * @param moduleCode 模块编码
	 * @param businessTableId 业务表ID
	 */
	void saveFileInfoToDB(String[] fileInfoArr, 
			              Long applicationId, 
			              String moduleCode, 
			              Long businessTableId) throws Exception;
}
