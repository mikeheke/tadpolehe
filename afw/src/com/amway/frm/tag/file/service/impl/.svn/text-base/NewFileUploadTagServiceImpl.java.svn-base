package com.amway.frm.tag.file.service.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.amway.frm.afw.vo.SysInfoBean;
import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.util.FileHelper;
import com.amway.frm.base.util.MD5EncodeUtil;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.bds.service.BaseDataSourceService;
import com.amway.frm.bds.util.BdsConstant;
import com.amway.frm.bds.vo.BaseDataSourceVo;
import com.amway.frm.tag.file.dao.INewFileUploadTagDao;
import com.amway.frm.tag.file.entity.NewFileUploadTagEntity;
import com.amway.frm.tag.file.service.NewFileUploadTagService;
import com.amway.frm.tag.file.util.NewFileUploadTagConstant;

/**
 * 文件上传标签实体业务逻辑接口
 * 
 * @author Mike He
 * @createDate 2013-12-02
 *
 */
public class NewFileUploadTagServiceImpl extends BaseImpl implements NewFileUploadTagService {

	private  BaseDataSourceService baseDataSourceService;
	
	private INewFileUploadTagDao newFileUploadTagDao;
	
	/**
	 * 上传文件到服务器
	 * @param file
	 * @param fileName
	 * @param fileType
	 * @param path
	 * @param isEnc
	 * @return
	 */
	@Override
	public ReturnMessage<NewFileUploadTagEntity> save(File file, String fileName, String fileType,
			String path, boolean isEnc) {
		
		ReturnMessage<NewFileUploadTagEntity> returnMessage = new ReturnMessage<NewFileUploadTagEntity>();
		
		String basePath = getBasePath();
		String filePath = getFileSavePath(basePath, path);
		//String flieExtend = getFileExtendName(fileName);
		String saveFileName = getSaveFileName(fileName, isEnc);
		String saveFile = filePath + NewFileUploadTagConstant.UNIX_SEP + saveFileName ;
	
		FileHelper.copy(file, new File(saveFile));
		
		NewFileUploadTagEntity NewFileUploadTagEntity = getRetUploadEntity(fileName, saveFileName, 
				fileType, filePath);
		
		final String uploadMsg = "上传成功";
		returnMessage.setReturnMsg(uploadMsg);
		returnMessage.setReturnObject(NewFileUploadTagEntity);
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			
		return returnMessage;	
	}
	
	private String getBasePath(){
		BaseDataSourceVo baseDataSourceVo = baseDataSourceService.getBdsVoData(
				NewFileUploadTagConstant.FILE_UPLOAD_PATH, new HashMap<String, String[]>(),
				NewFileUploadTagConstant.SQL_AND).getReturnObject();
		String basePath = (String) baseDataSourceVo.getColumnSingle(BdsConstant.FIXED_COL_NAME_CODE);
		return basePath;
	}
	
	private NewFileUploadTagEntity getRetUploadEntity(String cnName, String fileEncryptName,
			String fileType, String savePath){
		
		NewFileUploadTagEntity NewFileUploadTagEntity = new NewFileUploadTagEntity();
		NewFileUploadTagEntity.setCnName(cnName);
		NewFileUploadTagEntity.setFileEncryptName(fileEncryptName);
		NewFileUploadTagEntity.setFileType(fileType);
		NewFileUploadTagEntity.setSavePath(savePath.replace(NewFileUploadTagConstant.WINDOW_SEP, NewFileUploadTagConstant.WINDOW_SEP2));
		NewFileUploadTagEntity.setUploadUser(getSysInfo().getUserProfile().getEmpNumber());
		
		return NewFileUploadTagEntity;
	}
	
	private String getSaveFileName(String fileName, boolean isEnc){
		
		String saveFileName = null;
		
		if(isEnc){
			saveFileName = MD5EncodeUtil.MD5Encode(AppConstant.MD5_KEY + fileName);
		}else{
			saveFileName = fileName;
		}
		
		return saveFileName;
	}
	
/*	private String getFileExtendName(String fileName){
		return DataConverter.getFileExtention(fileName);
	}*/
	
	private String getFileSavePath(String basePath, String path){
		
		String filePath = null;
		
		if(!DataValidater.isStrEmpty(path)){
			filePath = path;
		}else{
			String appCode = getSysInfo().getApplication().getApplicationCode();
			String nowDate = DataConverter.fmtDateToDateStr(new Date(), NewFileUploadTagConstant.YYYYMMDD_X);
			
			filePath = basePath+NewFileUploadTagConstant.UNIX_SEP+appCode+NewFileUploadTagConstant.UNIX_SEP+nowDate;
		}
		
		File saveFileDirs = new File(filePath);
		if(!saveFileDirs.exists()){
			saveFileDirs.mkdirs();  //当目录不存在时，创建目录
		}
		
		return filePath;
	}
	
	/**
	 * 保存上传文件信息到DB
	 * @param fileInfoArr 文件信息字符串数组
	 * @param applicationId 应用ID
	 * @param moduleCode 模块编码
	 * @param businessTableId 业务表ID
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public void saveFileInfoToDB(String[] fileInfoArr, Long applicationId,
			String moduleCode, Long businessTableId) throws Exception {
		//----------参数值为空,不进行操作----------
		if (applicationId == null) {
			return;
		}
		if (moduleCode == null || "".equals(moduleCode)) {
			return;
		}
		if (businessTableId == null) {
			return;
		}
		if (fileInfoArr == null) {
			return;
		}
		
		List<NewFileUploadTagEntity> fileList = getNewFileUploadTagEntityList(fileInfoArr,applicationId,moduleCode,businessTableId);
	
		this.addOrUpdateList(fileList);
	}
	
	/**
	 * 从DB查询已经存在的文件(by ids)
	 * @param fileInfoArr
	 * @return
	 * @throws SQLException 
	 */
	private List<NewFileUploadTagEntity> getFileListForDB(String[] fileInfoArr) throws SQLException {
		List<String> ids = new ArrayList<String>();
		for (String fileInfo : fileInfoArr) {
			//如: Uuid|cnName|fileEncryptName|fileType|savePath|uploadUser|Y
			String[] singleFile = fileInfo.split(AppConstant.SPILT_OPER);
			String uuid = singleFile[0];
			
			if (uuid != null && !"".equals(uuid)) {
				ids.add(uuid);
			} 
		}
		
		return newFileUploadTagDao.getEntitiesByIds(ids);
	}
	
	/**
	 * 获取NewFileUploadTagEntity
	 * @param fileInfoArr: Uuid|cnName|fileEncryptName|fileType|savePath|uploadUser|Y
	 *  Uuid:字符串或"", 最后一位：Y/N
	 * @return
	 * @throws SQLException 
	 */
	private List<NewFileUploadTagEntity> getNewFileUploadTagEntityList(String[] fileInfoArr,Long applicationId,
			String moduleCode, Long businessTableId) throws SQLException {
		List<NewFileUploadTagEntity> newFileUploadTagEntityList = new ArrayList<NewFileUploadTagEntity>();
		
		//从DB中查找已经存在的文件
		List<NewFileUploadTagEntity> fileListInDB = getFileListForDB(fileInfoArr);
		
		for (String fileInfo : fileInfoArr) {
			NewFileUploadTagEntity newFileUploadTagEntity = new NewFileUploadTagEntity();
			//如: Uuid|cnName|fileEncryptName|fileType|savePath|uploadUser|Y
			String[] singleFile = fileInfo.split(AppConstant.SPILT_OPER);
			String uuid = singleFile[0];
			String cnName = singleFile[1];
			String fileEncryptName = singleFile[2];
			String fileType = singleFile[3];
			String savePath = singleFile[4];
			String uploadUser = singleFile[5];
			//Y:有效 N:删除
			String deleteFlag = singleFile[6];
			Integer recordState;
			
			//删除标识
			if ("Y".equals(deleteFlag)) {//文件有效
				recordState = AppConstant.START;
			} else {//文件无效
				recordState = AppConstant.STOP;
			}
			
			if (uuid != null && !"".equals(uuid)) {
				//newFileUploadTagEntity.setUuid(uuid);
				//从数据库查询
				//NewFileUploadTagEntity fileInDB = (NewFileUploadTagEntity)this.querySingle(newFileUploadTagEntity);
				
				NewFileUploadTagEntity fileInDB = null;
				for (NewFileUploadTagEntity f: fileListInDB) {//判断该文件是否在数据库中已经存在
					if (f.getUuid().equals(uuid)) {
						fileInDB = f;
					}
				}
				
				if (fileInDB != null) {//数据库已经存在该对象，更新信息
					fileInDB.setRecordState(recordState);
					fileInDB.setState(recordState);
					fileInDB.setUpdatedTime(new Date());
					fileInDB.setUpdatedUserId(SysInfoBean.getSysInfoBean().getUserProfile().getEmpNumber());
					
					//add to list
					newFileUploadTagEntityList.add(fileInDB);
				}
			} else {
				newFileUploadTagEntity.setApplicationId(applicationId);
				newFileUploadTagEntity.setBizId(businessTableId);
				newFileUploadTagEntity.setCnName(cnName);
				newFileUploadTagEntity.setFileEncryptName(fileEncryptName);
				newFileUploadTagEntity.setFileType(fileType);
				newFileUploadTagEntity.setModuleId(moduleCode);
				newFileUploadTagEntity.setSavePath(savePath);
				newFileUploadTagEntity.setUploadUser(uploadUser);
				newFileUploadTagEntity.setRecordState(recordState);
				newFileUploadTagEntity.setState(recordState);
				//time user
				newFileUploadTagEntity.setCreatedTime(new Date());
				newFileUploadTagEntity.setCreatedUserId(uploadUser);
				//add to list
				newFileUploadTagEntityList.add(newFileUploadTagEntity);
			}
		}
		
		return newFileUploadTagEntityList;
	}

	public void setBaseDataSourceService(BaseDataSourceService baseDataSourceService) {
		this.baseDataSourceService = baseDataSourceService;
	}

	public void setNewFileUploadTagDao(INewFileUploadTagDao newFileUploadTagDao) {
		this.newFileUploadTagDao = newFileUploadTagDao;
	}
}
