package mikeheke.tadpole.frm.tag.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.util.FileHelper;
import mikeheke.tadpole.frm.base.util.MD5EncodeUtil;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.bds.service.BaseDataSourceService;
import mikeheke.tadpole.frm.bds.util.BdsConstant;
import mikeheke.tadpole.frm.bds.vo.BaseDataSourceVo;
import mikeheke.tadpole.frm.tag.entity.TagFileUpload;
import mikeheke.tadpole.frm.tag.service.FileUpDownloadTagService;
import mikeheke.tadpole.frm.tag.util.TagConstant;

/**
 * 文件上传实现类
 * 
 */
public class FileUpDownloadTagImpl extends BaseImpl implements
		FileUpDownloadTagService {

	private  BaseDataSourceService baseDataSourceService;
	
	@Override
	public ReturnMessage<TagFileUpload> save(File file, String fileName, String fileType,
			String path, boolean isEnc) {
		
		ReturnMessage<TagFileUpload> returnMessage = new ReturnMessage<TagFileUpload>();
		
		String basePath = getBasePath();
		String filePath = getFileSavePath(basePath, path);
		//String flieExtend = getFileExtendName(fileName);
		String saveFileName = getSaveFileName(fileName, isEnc);
		String saveFile = filePath + TagConstant.UNIX_SEP + saveFileName ;
	
		FileHelper.copy(file, new File(saveFile));
		
		TagFileUpload tagFileUpload = getRetUploadEntity(fileName, saveFileName, 
				fileType, filePath);
		
		final String uploadMsg = "上传成功";
		returnMessage.setReturnMsg(uploadMsg);
		returnMessage.setReturnObject(tagFileUpload);
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			
		return returnMessage;	
	}
	
	private String getBasePath(){
		BaseDataSourceVo baseDataSourceVo = baseDataSourceService.getBdsVoData(
				TagConstant.FILE_UPLOAD_PATH, new HashMap<String, String[]>(),
				TagConstant.SQL_AND).getReturnObject();
		String basePath = (String) baseDataSourceVo.getColumnSingle(BdsConstant.FIXED_COL_NAME_CODE);
		return basePath;
	}
	
	private TagFileUpload getRetUploadEntity(String cnName, String fileEncryptName,
			String fileType, String savePath){
		
		TagFileUpload tagFileUpload = new TagFileUpload();
		tagFileUpload.setCnName(cnName);
		tagFileUpload.setFileEncryptName(fileEncryptName);
		tagFileUpload.setFileType(fileType);
		tagFileUpload.setSavePath(savePath.replace(TagConstant.WINDOW_SEP, TagConstant.WINDOW_SEP2));
		tagFileUpload.setUploadUser(getSysInfo().getUserProfile().getEmpNumber());
		
		return tagFileUpload;
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
			String nowDate = DataConverter.fmtDateToDateStr(new Date(), TagConstant.YYYYMMDD_X);
			
			filePath = basePath+TagConstant.UNIX_SEP+appCode+TagConstant.UNIX_SEP+nowDate;
		}
		
		File saveFileDirs = new File(filePath);
		if(!saveFileDirs.exists()){
			saveFileDirs.mkdirs();  //当目录不存在时，创建目录
		}
		
		return filePath;
	}

	public void setBaseDataSourceService(BaseDataSourceService baseDataSourceService) {
		this.baseDataSourceService = baseDataSourceService;
	}

}
