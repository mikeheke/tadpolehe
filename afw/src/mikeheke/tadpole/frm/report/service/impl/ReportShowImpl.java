/**
 * 
 */
package mikeheke.tadpole.frm.report.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.util.MD5EncodeUtil;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.email.entity.EmailBasicPara;
import mikeheke.tadpole.frm.email.service.EmailService;
import mikeheke.tadpole.frm.report.dao.IReportShowDao;
import mikeheke.tadpole.frm.report.entity.ReportCache;
import mikeheke.tadpole.frm.report.entity.ReportInfo;
import mikeheke.tadpole.frm.report.entity.ReportPara;
import mikeheke.tadpole.frm.report.entity.ReportSql;
import mikeheke.tadpole.frm.report.exception.ReportInfoException;
import mikeheke.tadpole.frm.report.exception.ReportSysException;
import mikeheke.tadpole.frm.report.service.ReportInfoService;
import mikeheke.tadpole.frm.report.service.ReportParaService;
import mikeheke.tadpole.frm.report.service.ReportShowService;
import mikeheke.tadpole.frm.report.service.ReportSqlService;
import mikeheke.tadpole.frm.report.util.ReportConstant;
import mikeheke.tadpole.frm.report.vo.SQL;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.transaction.annotation.Transactional;

/**
 * 
 *
 * 2011-9-16 上午11:47:50
 */
public class ReportShowImpl extends BaseImpl implements
		ReportShowService {

	private ReportSqlService reportSqlService;
	
	private ReportParaService reportParaService;
	
	private ReportInfoService reportInfoService;
	
	private EmailService emailService;
	
	private IReportShowDao reportShowDao;
	
	@Override
	@Transactional
	public void makeCache(){
		List<ReportCache> reportCaches = queryList(new ReportCache());
		for(ReportCache reportCache: reportCaches){
			ReportInfo reportInfo = getReportInfo(reportCache.getReportCode());
			Map<String, Object> params = DataConverter.convertStrToMap(reportCache.getReportParas());
			try {
				makeOnce(reportInfo, params, new FileOutputStream(reportCache.getFileRPath()));
				sendEmail(reportCache);
			} catch (Exception e) {
				e.getMessage();//e.printStackTrace();
			}
			deleteCache(reportCache);
		}
	}
	
	@Transactional
	private void sendEmail(ReportCache reportCache){
		
		UserProfile userProfile = getEmailUser(reportCache);
		if(null == userProfile){
			return;
		}
		String emailAddr = userProfile.getEmailAddr();
		if(DataValidater.isStrEmpty(emailAddr)){
			return;
		}
		
		EmailBasicPara emailBasicPara = new EmailBasicPara();
		emailBasicPara.setMailTo(new String[]{emailAddr});
		//emailBasicPara.setMailFrom("report@report.com");
		//emailBasicPara.setPersonalName("报表");
		final String REPORT_MSG = "-报表";
		final String REPORT_ADDR = "地址";
		emailBasicPara.setMailSubject(ReportConstant.LEFT_Z_KUO+reportCache.getReportCode()
				+REPORT_MSG+ReportConstant.RIGHT_Z_KUO);
		emailBasicPara.setMailContent(ReportConstant.LEFT_Z_KUO+REPORT_ADDR
				+ReportConstant.RIGHT_Z_KUO+reportCache.getFilePath());
		
		emailService.sendEmail(emailBasicPara);
	}
	
	private UserProfile  getEmailUser(ReportCache reportCache){
		
		String userCode = reportCache.getCreatedUserId();
		UserProfile userProfile = new UserProfile();
		userProfile.setEmpNumber(userCode);
		userProfile = (UserProfile) querySingle(userProfile);
		
		return userProfile;
	}
	
	@Override
	public String getCacheReportFilePath(ReportInfo reportInfo,
			Map<String, Object> params){
		
		String reportPath = getFilePath() + ReportConstant.UNIX_SEP
			+ getSysInfo().getSysApplication().getApplicationCode();
		String reportFile = reportInfo.getReportCode()+ReportConstant.QUS_SIGN
			+ DataConverter.convertMapToStr(params);
		String reportMakeType = ((String[]) params.get(ReportConstant.REPORT_MAKE_TYPE_NAME))[0];
		String extName = ReportConstant.REPORT_MAKE_TYPE_PDF.equals(
				reportMakeType)?ReportConstant.PDF_DOT:ReportConstant.XLS_DOT;
		String fileName = reportPath + ReportConstant.UNIX_SEP
			+MD5EncodeUtil.MD5Encode(AppConstant.MD5_KEY+reportFile)+extName;
		
		return fileName;
	}
	
	private String getFilePath(){
		
		createFilePath();
		
		return ReportConstant.REPORT_CARCH_PATH;
	}
	
	private void createFilePath(){
		
		String baseRPath = ContextFactory.getContextRealPath();	
		String reportRPath = baseRPath + ReportConstant.UNIX_SEP + ReportConstant.REPORT_CARCH_PATH
			+ ReportConstant.UNIX_SEP + getSysInfo().getSysApplication().getApplicationCode();
		File reportFile = new File(reportRPath);
		if(!reportFile.exists()){
			reportFile.mkdirs();
		}
	}
	
	@Transactional
	private void deleteCache(ReportCache reportCache){
		delete(reportCache);
	}
	
	private ReportInfo getReportInfo(String reportCode) {
		
		ReportInfo reportInfo = new ReportInfo();
		reportInfo.setReportCode(reportCode);
		ReportInfo reportInfoRet = (ReportInfo)querySingle(reportInfo);
		
		return reportInfoRet;
	}
	
	@Override
	public void makeOnce(ReportInfo reportInfo, Map<String, Object> paramIns,
			OutputStream outputStream) {
		
		File reportTemplateFile = getReportTemplateFile(reportInfo);
		if(!reportTemplateFile.exists()){
			throw new ReportInfoException(ReportConstant.REPORT_INFO_EXP_0005);
		}
		
		ReturnMessage<Map<String, Object>> returnMessage = null;
		Map<String, Object> params = new HashMap<String, Object>(paramIns);
		returnMessage = getDataResult(reportInfo, params);
		if(!returnMessage.isSuccess()){
			throw new ReportInfoException(returnMessage.getReturnMsg());
		}
		
		Map<String, Object> paremeters = getPrarmeters(params);
		List<Map<String, Object>> dataResult = returnMessage.getReturnObjects();
		if(DataValidater.isCollectionEmpty(dataResult)){
			dataResult.add(new HashMap<String, Object>(0));
		}
		try {
			makeReport(reportTemplateFile, paremeters, dataResult, outputStream);
		} catch (Exception e) {
			throw new ReportSysException(e);
		}

	}
	
	private File getReportTemplateFile(ReportInfo reportInfo){
		String filePath = getReportBasePath();
		filePath =  filePath + ReportConstant.UNIX_SEP+reportInfo.getReportUrl()
			+ReportConstant.JASPER_DOT;
		return new File(filePath);
	}
	
	private Map<String, Object> getPrarmeters(Map<String, Object> params){
		
		Map<String, Object> paremeters = new HashMap<String, Object>();
		
		Set<Entry<String, Object>> paramSet = params.entrySet();
		for(Entry<String, Object> paramEntry: paramSet){
			String paramKey = paramEntry.getKey();
			Object obj = paramEntry.getValue();;
			if(null == obj){
//				paremeters.put(paramKey, "");
			}else if(obj instanceof Object[]){
				Object[] objArr = (Object[])obj;
				if(objArr.length > 1){
					paremeters.put(paramKey, (Object[])obj);
				}
				else if(objArr.length > 0){
					paremeters.put(paramKey, objArr[0]);
				}else{
					paremeters.put(paramKey, ReportConstant.EMPTY_STR);
				}
			}else{
				paremeters.put(paramKey, obj);
			}
		}
		
		return paremeters;
	}
	
	private String getReportBasePath(){
		String filePath = reportInfoService.getReportFilePath();
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdir();
		}
		return filePath;
	}
	
	private void makeReport(File reportTemplateFile,
			Map<String, Object> params, List<Map<String, Object>> dataResult,
			OutputStream outputStream) throws Exception {

		String reportMakeType = (String) params.get(ReportConstant.REPORT_MAKE_TYPE_NAME);
		
		if(ReportConstant.REPORT_MAKE_TYPE_EXCEL.equals(reportMakeType)){
			makeExcelReport(reportTemplateFile, params, dataResult, outputStream);
		}else if(ReportConstant.REPORT_MAKE_TYPE_PDF.equals(reportMakeType)){
			makePdfReport(reportTemplateFile, params, dataResult, outputStream);
		}else{
			makeExcelReport(reportTemplateFile, params, dataResult, outputStream);
		}
	}
	
	private void makeExcelReport(File reportTemplateFile,
			Map<String, Object> params, List<Map<String, Object>> dataResult,
			OutputStream outputStream) throws Exception {

		JasperReport jasperReport = (JasperReport) JRLoader
				.loadObject(reportTemplateFile.getPath());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
				params, new JRBeanArrayDataSource(dataResult.toArray()));

		JRXlsExporter exporter = new JRXlsExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);

		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE,
				new Boolean(false));
		exporter.exportReport();
	
	}

	private void makePdfReport(File reportTemplateFile,
			Map<String, Object> params, List<Map<String, Object>> dataResult,
			OutputStream outputStream) throws Exception {
	
		byte[] bytes = JasperRunManager.runReportToPdf(
				reportTemplateFile.getPath(), params,
				new JRBeanArrayDataSource(dataResult.toArray()));

		outputStream.write(bytes, 0, bytes.length);
		outputStream.flush();
		outputStream.close();
	}
	
	@Override
	public ReturnMessage<Map<String, Object>> getDataResult(
			ReportInfo reportInfo, Map<String, Object> params) {
	
		ReturnMessage<Map<String, Object>> returnMessage 
			= new ReturnMessage<Map<String, Object>>();
		try {
			
			List<ReportPara> reportParas = getReportParas(reportInfo);
			List<ReportSql> reportSqls = getReportSqls(reportInfo);
			List<ReportSql> paraReportSqls = getParaReportSqls(reportSqls);
			Map<String, Object> parameters = getParameters(paraReportSqls,
					fmtInParams(reportParas, params));
			params.putAll(parameters);
			List<ReportSql> mainReportSqls = getMainReportSqls(reportSqls);
			List<ReportSql> subReportSqls = getSubReportSqls(reportSqls);
			
			List<Map<String, Object>> dataResult = makeDataResult(
					mainReportSqls, parameters);
			addSubDataResult(subReportSqls, dataResult, parameters);
			
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
			returnMessage.setReturnObjects(dataResult);
		} catch (SQLException e) {
			throw new ReportSysException(e);
		}
		
		return returnMessage;
	}
	
	private void addSubDataResult(List<ReportSql> subReportSqls,
			List<Map<String, Object>> mainDataResult,
			Map<String, Object> parameters) throws SQLException {

		if(DataValidater.isCollectionEmpty(subReportSqls)){
			return;
		}
		if(DataValidater.isCollectionEmpty(mainDataResult)){
			return;
		}
		for(Map<String, Object> mainDataMap: mainDataResult){
			
			Map<String, Object> parameterNews = new HashMap<String, Object>();
			parameterNews.putAll(parameters);
			parameterNews.putAll(mainDataMap);
			
			for(ReportSql subReportSql: subReportSqls){
				List<Map<String, Object>> dataResult = makeDataResult(
						subReportSql, parameterNews);
				mainDataMap.put(subReportSql.getMapKey(), dataResult);
			}	
		}
	}
	
	private List<Map<String, Object>> makeDataResult(List<ReportSql> reportSqls,
			Map<String, Object> parameters) throws SQLException{
		
		List<Map<String, Object>> dataResults = new ArrayList<Map<String, Object>>();
		if(DataValidater.isCollectionEmpty(reportSqls)){
			return new ArrayList<Map<String, Object>>();
		}
		for(ReportSql reportSql: reportSqls){
			List<Map<String, Object>> dataResult = makeDataResult(reportSql, parameters);
			dataResults.addAll(dataResult);
		}
		
		return dataResults;
	}
	
	private List<Map<String, Object>> makeDataResult(ReportSql reportSql,
			Map<String, Object> parameters) throws SQLException{
		
		SQL sql = makeSql(reportSql, parameters);
		List<Map<String, Object>> dataResult = reportShowDao.excuteSql(sql);
		
		return dataResult;
	}
	
	private Map<String, Object> getParameters(List<ReportSql> paraReportSqls,
			Map<String, Object> params)throws SQLException {
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.putAll(params);
		addParameters(parameters, paraReportSqls, params);
		
		return parameters;
	}
	
	private Map<String, Object> fmtInParams(List<ReportPara> reportParas,
			Map<String, Object> params) {
		
		Map<String, Object> paramsRet = new HashMap<String, Object>();
		
		Set<Entry<String, Object>> paramSet = params.entrySet();
		for(Entry<String, Object> paramEntry: paramSet){
			String paramKey = paramEntry.getKey();
			Object paramValue = paramEntry.getValue();
			Object[] paramValueArr = getParameterValues(getParameter(reportParas,
					paramKey), paramValue);
			if(paramValueArr.length > 1){
				StringBuffer rangFrom = new StringBuffer();
				rangFrom.append(paramKey).append(ReportConstant.RANG_FROM);
				paramsRet.put(rangFrom.toString(), paramValueArr[0]);
				StringBuffer rangTo = new StringBuffer();
				rangTo.append(paramKey).append(ReportConstant.RANG_TO);
				paramsRet.put(rangTo.toString(), paramValueArr[1]);
			}else if(paramValueArr.length > 0){
				paramsRet.put(paramKey, paramValueArr[0]);
			}
		}
		
		return paramsRet;
	}
	
	private void addParameters(Map<String, Object> parameters,
			List<ReportSql> paraReportSqls, Map<String, Object> params)
			throws SQLException {
		
		List<Map<String, Object>> dataResult = makeDataResult(paraReportSqls, params);
		for(Map<String, Object> dataMap: dataResult){
			parameters.putAll(dataMap);
		}
	}
	
	private ReportPara getParameter(List<ReportPara> reportParas, String name) {
		
		if(null == reportParas){
			return null;
		}
		ReportPara reportParaRet = null;
		for(ReportPara reportPara: reportParas){
			if(!name.equals(reportPara.getParaName())){
				continue;
			}
			reportParaRet = reportPara;
			break;
		}
		
		return reportParaRet;
	}
	
	private Object[] getParameterValues(ReportPara reportPara, Object values){
		
		if(null == reportPara){
			return new Object[0];
		}
		String type = reportPara.getDataType();
		String displayType = reportPara.getDataDisplayType();
		
		if(values instanceof String[]){
			String[] valueArr = (String[])values;
			if (ReportConstant._5_STR.equals(displayType) || ReportConstant._6_STR.equals(displayType)
					|| ReportConstant._7_STR.equals(displayType) || ReportConstant._9_STR.equals(displayType)) {
				Object obj1 = getParameterFmtValue(type, valueArr[0]);
				Object obj2 = getParameterFmtValue(type, valueArr[1]);
				return new Object[]{obj1, obj2};
			}else{
				Object obj1 = getParameterFmtValue(type, valueArr[0]);
				return new Object[]{obj1};
			}
		}else{
			String valueStr = (String)values;
			Object obj1 = getParameterFmtValue(type, valueStr);
			return new Object[]{obj1};
		}
	}
	
	private Object getParameterFmtValue(String type, String value){
		
		Object objValue = null;
		
		if(AppConstant.DATA_TYPE_STRING.equals(type)){
			objValue = new String(value);
		}else if(AppConstant.DATA_TYPE_LONG.equals(type)){
			objValue = new Long(value);
		}else if(AppConstant.DATA_TYPE_NUMBER.equals(type)){
			objValue = new Double(value);
		}else if(AppConstant.DATA_TYPE_DATE.equals(type)){
			objValue = DataConverter.fmtStrToTimestamp(value);
		}else{
			objValue = new String(value);
		}
		
		return objValue;
	}

	private SQL makeSql(ReportSql reportSql, Map<String, Object> parameters) {
		
		SQL sql = new SQL();
		
		addSelectSql(sql, reportSql);
		addWhereSql(sql, reportSql);
		addOrderSql(sql, reportSql);
		
		replaceSqlVar(sql, parameters);
		
		return sql;
	}
	
	private void replaceSqlVar(SQL sqlObj, Map<String, Object> paramters){
		
		if(DataValidater.isMapEmpty(paramters)){
			return;
		}
		String sql = sqlObj.getSql().toString();
		Set<Entry<String, Object>> parameterSet = paramters.entrySet();
		for(Entry<String, Object> parameterEntry: parameterSet){
			StringBuffer key = new StringBuffer(ReportConstant.DOLLOR_SIGN);
			key.append(parameterEntry.getKey());
			Object value = parameterEntry.getValue();
			sql = sql.replace(key.toString(), getStrValue(value));
		}
		sqlObj.setSql(new StringBuffer(sql));
	}
	
	private String getStrValue(Object value){
		
		if(null == value){
			return ReportConstant.EMPTY_STR;
		}
		return value.toString();
	}
	
	private void addSelectSql(SQL sql, ReportSql reportSql){
		
		String selectSql = reportSql.getSqlSelect();
		selectSql = DataConverter.valueOf(selectSql);
		sql.addSql(selectSql);
	}
	
	private void addWhereSql(SQL sql, ReportSql reportSql){
		
		String whereSql = reportSql.getSqlWhere();
		whereSql = DataConverter.valueOf(whereSql);
		sql.addSql(whereSql);
	}
	
	private void addOrderSql(SQL sql, ReportSql reportSql){
		
		String orderSql = reportSql.getSqlOrder();
		orderSql = DataConverter.valueOf(orderSql);
		sql.addSql(orderSql);
		
	}
	
	private List<ReportSql> getMainReportSqls(List<ReportSql> reportSqls){
		
		List<ReportSql> reportSqlsRet = getReportSqls(reportSqls,
				ReportConstant.REPORT_MAIN);
		
		return reportSqlsRet;
	}
	
	private List<ReportSql> getSubReportSqls(List<ReportSql> reportSqls){
		
		List<ReportSql> reportSqlsRet = getReportSqls(reportSqls,
				ReportConstant.REPORT_SUB);
	
		return reportSqlsRet;
	}
	
	private List<ReportSql> getParaReportSqls(List<ReportSql> reportSqls){
		
		List<ReportSql> reportSqlsRet = getReportSqls(reportSqls,
				ReportConstant.REPORT_PARA);
		
		return reportSqlsRet;
	}
	
	private List<ReportSql> getReportSqls(List<ReportSql> reportSqls, String type){

		List<ReportSql> reportSqlsRet = new ArrayList<ReportSql>();
		
		if(null == reportSqls){
			return null;
		}
		for(ReportSql reportSql: reportSqls){
			if(!type.equals(reportSql.getSqlType())){
				continue;
			}
			reportSqlsRet.add(reportSql);
		}
		
		return reportSqlsRet;
	}

	private List<ReportSql> getReportSqls(ReportInfo reportInfo){
		
		return reportSqlService.queryReportSqls(reportInfo).getReturnObjects();
	}
	
	private List<ReportPara> getReportParas(ReportInfo reportInfo){
	
		return reportParaService.queryReportParas(reportInfo).getReturnObjects();
	}
	
	public void setReportSqlService(ReportSqlService reportSqlService) {
		this.reportSqlService = reportSqlService;
	}

	public void setReportParaService(ReportParaService reportParaService) {
		this.reportParaService = reportParaService;
	}

	public void setReportInfoService(ReportInfoService reportInfoService) {
		this.reportInfoService = reportInfoService;
	}
	
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public void setReportShowDao(IReportShowDao reportShowDao) {
		this.reportShowDao = reportShowDao;
	}

}
