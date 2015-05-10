package mikeheke.tadpole.frm.afw.service;

import java.util.HashMap;
import java.util.List;

import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;

public interface ExportConfigService  extends BaseService{
	
	List<HashMap> importSql(List<String> sqlList);

	List<String> generateExportSqls(String module, String applicationid,
			String startdate, String enddate);

	ReturnMessage<String> fetchExportDate(String appid);

	void markExportTime(String appid);

}
