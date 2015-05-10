package mikeheke.tadpole.frm.tag.service;

import java.io.File;

import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.tag.entity.TagFileUpload;

/**
 * 文件上传接口
 * 
 */
public interface FileUpDownloadTagService extends BaseService {
	
	
	ReturnMessage<TagFileUpload> save(File file, String fileName, String fileType,
			String path, boolean isEnc);

}