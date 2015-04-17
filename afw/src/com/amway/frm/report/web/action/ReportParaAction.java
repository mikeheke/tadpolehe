/**
 * 
 */
package com.amway.frm.report.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.base.web.action.BaseAction;
import com.amway.frm.report.entity.ReportInfo;
import com.amway.frm.report.entity.ReportPara;
import com.amway.frm.report.service.ReportParaService;
import com.amway.frm.report.util.ReportConstant;
import com.amway.frm.report.vo.ReportParaVo;


/**
 * @author huangweijin
 *
 * 2011-9-7 下午03:25:51
 */
public class ReportParaAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5129645097315662920L;

	private ReportParaVo reportParaVo;

	private ReportParaService reportParaService;
	
	@Override
	public String init() {
		
		ReportInfo reportInfo = getEntity(DataConverter.stringToLong(reportParaVo.getReportInfoId()));
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
		
		String[] reportParaIds = null;
		if(null != reportParaVo){
			reportParaIds = reportParaVo.getReportParaIds();
		}
		if(!validateIds0(reportParaIds)){
			return INIT_MODIFY_INPUT;
		}
		ReturnMessage<ReportPara> returnMessage = null;
		ReportPara reportPara = this.getEntity(reportParaIds[0]);
		returnMessage = reportParaService.query(reportPara);
		
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
		
		return result;
	}
	
	@Override
	public String add() {
		
		this.init();
		
		String result = ADD_SUCCESS;
		this.setOprt(AppConstant.ADD_OPRT);

		ReportPara reportPara = this.getEntity();
		ReturnMessage<ReportPara> returnMessage = new ReturnMessage<ReportPara>();
		
		if(!validateData()){
			returnMessage.setReturnObject(reportPara);
			setReturnMessage(returnMessage);
			return ADD_INPUT;
		}
		
		returnMessage = reportParaService.addOrUpdate(reportPara);
		if(returnMessage.isSuccess()){
			returnMessage.clearReturnObjects();
		}
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
		
		return result;
	}

	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] reportParaIds = null;
		if(null != reportParaVo){
			reportParaIds = reportParaVo.getReportParaIds();
		}
		if(validateIds(reportParaIds)){

			List<ReportPara> reportParas = this.getEntities(reportParaIds);
			ReturnMessage<ReportPara> returnMessage = reportParaService.logicDeleteList(reportParas);
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
		
		ReportPara reportPara = this.getEntity();
		ReturnMessage<ReportPara> returnMessage = new ReturnMessage<ReportPara>();
		
		if(!validateData()){
			returnMessage.setReturnObject(reportPara);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		String reportParaId = reportParaVo.getReportInfoId();
		if(!validateId(reportParaId)){
			returnMessage.setReturnObject(reportPara);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		
		returnMessage = reportParaService.update(reportPara);
		
		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		return result;
	}

	@Override
	protected boolean validateData() {

		return true;
	}
	
	@Override
	protected List<ReportPara> getEntities(String[] ids) {
		
		List<ReportPara> reportParas = new ArrayList<ReportPara>(); 
		
		for(String id: ids){
			ReportPara reportPara = getEntity(id);
			reportParas.add(reportPara);
		}
		
		return reportParas;
	}

	@Override
	protected ReportPara getEntity() {
		
		ReportPara reportPara = new ReportPara();
		reportPara.setReportParaId(DataConverter.stringToLong(reportParaVo.getReportParaId()));
		reportPara.setDataCoding(reportParaVo.getDataCoding());
		reportPara.setDataType(reportParaVo.getDataType());
		reportPara.setParaDisplayName(reportParaVo.getParaDisplayName());
		reportPara.setDataDisplayType(reportParaVo.getDataDisplayType());
		reportPara.setParaName(reportParaVo.getParaName());
		reportPara.setParaOrder(reportParaVo.getParaOrder());
		ReportInfo reportInfo = new ReportInfo();
		reportInfo.setReportInfoId(DataConverter.stringToLong(reportParaVo.getReportInfoId()));
		reportPara.setReportInfo(reportInfo);
		reportPara.setRemark(reportParaVo.getRemark());
		reportPara.setCreatedTime(new Date());
		reportPara.setCreatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		reportPara.setUpdatedTime(new Date());
		reportPara.setUpdatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		
		return reportPara;
	}

	@Override
	protected ReportPara getEntity(String id) {
		
		ReportPara reportPara = new ReportPara();
		reportPara.setReportParaId(DataConverter.stringToLong(id));
		
		return reportPara;
	}
	
	protected ReportInfo getEntity(Long id) {
		
		ReportInfo reportInfo = new ReportInfo();
		reportInfo.setReportInfoId(id);
		reportInfo = (ReportInfo) reportParaService.querySingle(reportInfo);
		
		return reportInfo;
	}

	@Override
	public String getJsonValue() {

		return super.getJsonValue();
	}

	public ReportParaVo getReportParaVo() {
		return reportParaVo;
	}

	public void setReportParaVo(ReportParaVo reportParaVo) {
		this.reportParaVo = reportParaVo;
	}

	public void setReportParaService(ReportParaService reportParaService) {
		this.reportParaService = reportParaService;
	}
	
}
