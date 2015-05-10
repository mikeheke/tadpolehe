/**
 * 
 */
package mikeheke.tadpole.frm.report.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.base.web.action.BaseAction;
import mikeheke.tadpole.frm.report.entity.ReportInfo;
import mikeheke.tadpole.frm.report.entity.ReportSql;
import mikeheke.tadpole.frm.report.service.ReportSqlService;
import mikeheke.tadpole.frm.report.util.ReportConstant;
import mikeheke.tadpole.frm.report.vo.ReportSqlVo;

/**
 * 
 *
 * 2011-9-7 下午04:38:24
 */
public class ReportSqlAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2596830666399113219L;

	private ReportSqlVo reportSqlVo;
	
	private ReportSqlService reportSqlService;
	
	@Override
	public String init() {
		
		ReportInfo reportInfo = getEntity(DataConverter.stringToLong(reportSqlVo.getReportInfoId()));
		this.setMessage(ReportConstant.REPORT_INFO, reportInfo);
		
		return super.init();
	}
	
	@Override
	public String initAdd() {
		
		this.init();
		
		String result = INIT_ADD_SUCCESS;
		this.setOprt(AppConstant.ADD_OPRT);
		
		return result;
	}

	@Override
	public String initModify() {
		
		this.init();
		
		String result = INIT_MODIFY_SUCCESS;
		this.setOprt(AppConstant.MDF_OPRT);
		
		String[] reportSqlIds = null;
		if(null != reportSqlVo){
			reportSqlIds = reportSqlVo.getReportSqlIds();
		}
		if(!validateIds0(reportSqlIds)){
			return INIT_MODIFY_INPUT;
		}
		ReturnMessage<ReportSql> returnMessage = null;
		ReportSql reportSql = this.getEntity(reportSqlIds[0]);
		returnMessage = reportSqlService.query(reportSql);
		
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
		
		return result;
	}
	
	@Override
	public String add() {
		
		this.init();
		
		String result = ADD_SUCCESS;
		this.setOprt(AppConstant.ADD_OPRT);

		ReportSql reportSql = this.getEntity();
		ReturnMessage<ReportSql> returnMessage = new ReturnMessage<ReportSql>();
		
		if(!validateData()){
			returnMessage.setReturnObject(reportSql);
			setReturnMessage(returnMessage);
			return ADD_INPUT;
		}
		
		returnMessage = reportSqlService.addOrUpdate(reportSql);
		if(returnMessage.isSuccess()){
			returnMessage.clearReturnObjects();
		}
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
		
		return result;
	}

	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] reportSqlIds = null;
		if(null != reportSqlVo){
			reportSqlIds = reportSqlVo.getReportSqlIds();
		}
		if(validateIds(reportSqlIds)){

			List<ReportSql> reportSqls = this.getEntities(reportSqlIds);
			ReturnMessage<ReportSql> returnMessage = reportSqlService.logicDeleteList(reportSqls);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		
		return JSON;
	}

	@Override
	public String modify() {
		
		this.init();
		
		String result = MDF_SUCCESS;
		this.setOprt(AppConstant.MDF_OPRT);
		
		ReportSql reportSql = this.getEntity();
		ReturnMessage<ReportSql> returnMessage = new ReturnMessage<ReportSql>();
		
		if(!validateData()){
			returnMessage.setReturnObject(reportSql);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		String reportSqlId = reportSqlVo.getReportInfoId();
		if(!validateId(reportSqlId)){
			returnMessage.setReturnObject(reportSql);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		
		returnMessage = reportSqlService.update(reportSql);
		
		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		return result;
	}

	@Override
	protected boolean validateData() {

		return true;
	}
	
	@Override
	protected List<ReportSql> getEntities(String[] ids) {
		
		List<ReportSql> reportSqls = new ArrayList<ReportSql>(); 
		
		for(String id: ids){
			ReportSql reportSql = getEntity(id);
			reportSqls.add(reportSql);
		}
		
		return reportSqls;
	}

	@Override
	protected ReportSql getEntity() {
		
		ReportSql reportSql = new ReportSql();
		reportSql.setReportSqlId((reportSqlVo.getReportSqlId()));
		reportSql.setSqlSelect(reportSqlVo.getSqlSelect());
		reportSql.setSqlWhere(reportSqlVo.getSqlWhere());
		reportSql.setSqlOrder(reportSqlVo.getSqlOrder());
		reportSql.setSqlType(reportSqlVo.getSqlType());
		reportSql.setMapKey(reportSqlVo.getMapKey());
		ReportInfo reportInfo = new ReportInfo();
		reportInfo.setReportInfoId((reportSqlVo.getReportInfoId()));
		reportSql.setReportInfo(reportInfo);
		reportSql.setRemark(reportSqlVo.getRemark());
		reportSql.setCreatedTime(new Date());
		reportSql.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		reportSql.setUpdatedTime(new Date());
		reportSql.setUpdatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		return reportSql;
	}

	@Override
	protected ReportSql getEntity(String id) {
		
		ReportSql reportSql = new ReportSql();
		reportSql.setReportSqlId((id));
		
		return reportSql;
	}
	
	protected ReportInfo getEntity(Long id) {
		
		ReportInfo reportInfo = new ReportInfo();
		reportInfo.setReportInfoId(id.toString());
		reportInfo = (ReportInfo) reportSqlService.querySingle(reportInfo);
		
		return reportInfo;
	}

	@Override
	public String getJsonValue() {

		return super.getJsonValue();
	}
	
	public ReportSqlVo getReportSqlVo() {
		return reportSqlVo;
	}

	public void setReportSqlVo(ReportSqlVo reportSqlVo) {
		this.reportSqlVo = reportSqlVo;
	}

	public void setReportSqlService(ReportSqlService reportSqlService) {
		this.reportSqlService = reportSqlService;
	}

}
