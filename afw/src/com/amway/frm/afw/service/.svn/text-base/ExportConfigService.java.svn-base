package com.amway.frm.afw.service;

import java.util.HashMap;
import java.util.List;

import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.vo.ReturnMessage;

public interface ExportConfigService  extends BaseService{
	
	List<HashMap> importSql(List<String> sqlList);

	List<String> generateExportSqls(String module, String applicationid,
			String startdate, String enddate);

	ReturnMessage<String> fetchExportDate(String appid);

	void markExportTime(String appid);

}
