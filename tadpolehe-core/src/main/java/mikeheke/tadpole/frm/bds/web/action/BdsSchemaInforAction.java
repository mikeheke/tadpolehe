package mikeheke.tadpole.frm.bds.web.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.afw.vo.SysInfoBean;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.base.web.action.BaseAction;
import mikeheke.tadpole.frm.bds.entity.BdsSchemaInfor;
import mikeheke.tadpole.frm.bds.service.BdsSchemaInforService;
import mikeheke.tadpole.frm.bds.util.BdsConstant;
import mikeheke.tadpole.frm.bds.util.BdsXmlUntil;
import mikeheke.tadpole.frm.bds.util.CacheFactory;
import mikeheke.tadpole.frm.bds.vo.BdsSchemaInforVo;
import mikeheke.tadpole.frm.logging.service.LogService;
import mikeheke.tadpole.frm.logging.util.LogFactory;

import org.apache.struts2.json.annotations.JSON;

/**
 * 基础数据服务ACTION类
 * 
 */
public class BdsSchemaInforAction  extends BaseAction {

	private static final long serialVersionUID = -1504305138746679277L;
	
	private static LogService logger = LogFactory.getLogger(BdsSchemaInforAction.class);
	
	//基础数据服务Vo
	private BdsSchemaInforVo bdsSchemaInforVo;

	//基础数据服务Service接口
	private BdsSchemaInforService bdsSchemaInforService;

	/**
	 * 进入增加页面时的初始化方法
	 * @return String
	 */
	@Override
	public String initAdd(){
		
		String result = INIT_ADD_SUCCESS;
		this.setMessage(AppConstant.OPRT, AppConstant.ADD_OPRT);
		
		return result;
	}	
	/**
	 * 进入修改页面时的初始化方法
	 * @return String
	 */
	@Override
	public String initModify() {

		String result = INIT_MODIFY_SUCCESS;
		this.setMessage(AppConstant.OPRT, AppConstant.MDF_OPRT);
		
		String[] bdsSchemaInforIds = null;
		if(null != bdsSchemaInforVo){
			bdsSchemaInforIds = bdsSchemaInforVo.getBdsSchemaInforIds();
		}
		if(!validateIds0(bdsSchemaInforIds)){
			return INIT_MODIFY_INPUT;
		}
		ReturnMessage<BdsSchemaInfor> returnMessage = null;
		
		BdsSchemaInfor bdsSchemaInfo = this.getEntity(bdsSchemaInforIds[0]);
        returnMessage = bdsSchemaInforService.getSchemaInforListByJDBC(bdsSchemaInfo);
        
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
	
	  return result;
	}
	
	/**
	 * 增加数据方法
	 * @return String
	 */
	@Override
	public String add() {
		
		String result = ADD_SUCCESS;
		this.setMessage(AppConstant.OPRT, AppConstant.ADD_OPRT);
		
		BdsSchemaInfor bdsSchemaInfor = this.getEntity();
		ReturnMessage<BdsSchemaInfor> returnMessage = new ReturnMessage<BdsSchemaInfor>();
		
		if(!validateData()){
			returnMessage.setReturnObject(bdsSchemaInfor);
			setReturnMessage(returnMessage);
			return ADD_INPUT;
		}
		returnMessage = bdsSchemaInforService.addBdsSchemaInfor(bdsSchemaInfor);
		if(returnMessage.isSuccess()){
			returnMessage.clearReturnObjects();
		}
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
		
		final String msg = "新增基础数据服务";
		logger.writeOperLog(AppConstant.ADD_OPRT, msg + bdsSchemaInfor.getBdsSchemaInforCode());
		
		return result;
	}
	/**
	 * 修改数据方法
	 * @return String
	 */	
	@Override
	public String modify(){
		
		String result = MDF_SUCCESS;
		this.setMessage(AppConstant.OPRT, AppConstant.MDF_OPRT);
		
		BdsSchemaInfor bdsSchemaInfor = this.getEntity();
		ReturnMessage<BdsSchemaInfor> returnMessage = new ReturnMessage<BdsSchemaInfor>();
		
		if(!validateData()){
			returnMessage.setReturnObject(bdsSchemaInfor);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		String bdsSchemaInfoId = bdsSchemaInforVo.getBdsSchemaInforId();
		if(!validateId(bdsSchemaInfoId)){
			returnMessage.setReturnObject(bdsSchemaInfor);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		
			
		returnMessage = bdsSchemaInforService.update(bdsSchemaInfor);
		
		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
		
		final String msg = "修改基础数据服务";
		logger.writeOperLog(AppConstant.MDF_OPRT,  msg+bdsSchemaInfor.getBdsSchemaInforCode());
		
		return result;
	}
	
	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String[] bdsSchemaInfoIds = null;
		if(null != bdsSchemaInforVo){
			bdsSchemaInfoIds = bdsSchemaInforVo.getBdsSchemaInforIds();
		}
		if(validateIds(bdsSchemaInfoIds)){

			List<BdsSchemaInfor> bdsSchemaInfos = this.getEntities(bdsSchemaInfoIds);
			ReturnMessage<BdsSchemaInfor> returnMessage = bdsSchemaInforService.logicDeleteList(bdsSchemaInfos);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
			
			final String msg = "删除基础数据服务";
			logger.writeOperLog(AppConstant.DEL_OPRT, msg);
		}
		
		setJsonValue(result);
		
		return JSON;
	}

	public String clear(){
		
		final String CLEAR_SUCCESS = "clearSuccess";
		final String CLEAR_INPUT = "clearInput";
		String result = CLEAR_INPUT;
		
		if(SysInfoBean.getSysInfoBean().isCacheClearUser()){
			
			boolean flag = CacheFactory.clearCache(bdsSchemaInforVo
					.getBdsSchemaInforCode());
			if(flag){
				result = CLEAR_SUCCESS;
				final String msg = "清除缓存成功";
				this.setMessage(RET_INFO, msg);
			}else{
				final String msg = "清除缓存失败";
				this.setMessage(RET_INFO, msg);
			}
		}

		return result;
	}

	/**
	 * 把vo转化为entity的方法（增加时使用）
	 * @return String
	 */	
	@Override
	public BdsSchemaInfor getEntity() {
		
		BdsSchemaInfor bdsSchemaInfor = new BdsSchemaInfor();
        bdsSchemaInfor.setBdsSchemaInforId((bdsSchemaInforVo.getBdsSchemaInforId()));
		Application app = new Application();
		app.setApplicationId((bdsSchemaInforVo.getApplicationId()));
		bdsSchemaInfor.setApplication(app);
		bdsSchemaInfor.setBdsSchemaInforCode(bdsSchemaInforVo.getBdsSchemaInforCode());
		bdsSchemaInfor.setBdsSchemaInforNameCna(bdsSchemaInforVo.getBdsSchemaInforNameCna());
		bdsSchemaInfor.setBdsSchemaInforNameEng(bdsSchemaInforVo.getBdsSchemaInforNameEng());
		bdsSchemaInfor.setBdsSchemaInforType(bdsSchemaInforVo.getBdsSchemaInforType());
		bdsSchemaInfor.setDataStructureXml(bdsSchemaInforVo.getDataStructureXml());
		bdsSchemaInfor.setJndi(bdsSchemaInforVo.getJndi());
		bdsSchemaInfor.setRemark(bdsSchemaInforVo.getRemark());
		bdsSchemaInfor.setSql(bdsSchemaInforVo.getSql());
		bdsSchemaInfor.setState(DataConverter.stringToInteger(bdsSchemaInforVo.getState()));
		bdsSchemaInfor.setWebserviceAddress(bdsSchemaInforVo.getWebserviceAddress());
		bdsSchemaInfor.setWebserviceFunction(bdsSchemaInforVo.getWebserviceFunction());
		bdsSchemaInfor.setWebserviceNamespace(bdsSchemaInforVo.getWebserviceNamespace());
		bdsSchemaInfor.setWebserviceReqRoot(bdsSchemaInforVo.getWebserviceReqRoot());
		bdsSchemaInfor.setWebserviceResRoot(bdsSchemaInforVo.getWebserviceResRoot());
		bdsSchemaInfor.setWebserviceUser(bdsSchemaInforVo.getWebserviceUser());
		bdsSchemaInfor.setWebservicePwd(bdsSchemaInforVo.getWebservicePwd());
		bdsSchemaInfor.setCreatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		bdsSchemaInfor.setCreatedTime(new Date());
		bdsSchemaInfor.setUpdatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		bdsSchemaInfor.setUpdatedTime(new Date());
		
		return bdsSchemaInfor;
	}
	
	/**
	 * 把vo转化为entity的方法（修改时使用）
	 * @param id id
	 * @return BdsSchemaInfor
	 */		
	@Override
	public BdsSchemaInfor getEntity(String id) {
		
		BdsSchemaInfor bdsSchemaInfor = new BdsSchemaInfor();
		bdsSchemaInfor.setBdsSchemaInforId((id));
		bdsSchemaInfor.setUpdatedUserId(getSysInfo().getUserProfile().getEmpNumber());
		bdsSchemaInfor.setUpdatedTime(new Date());
		
		return bdsSchemaInfor;
	}
	/**
	 * 批量把vo转化为entity的方法（删除时使用）
	 * @param ids ids
	 * @return List<BdsSchemaInfor>
	 */	
	@Override
	public List<BdsSchemaInfor> getEntities(String[] ids){
		
		List<BdsSchemaInfor> bdsSchemaInfors = new ArrayList<BdsSchemaInfor>(); 
		
		for(String id: ids){
			BdsSchemaInfor bdsSchemaInfor = new BdsSchemaInfor();
			bdsSchemaInfor.setBdsSchemaInforId(((id)));
			bdsSchemaInfors.add(bdsSchemaInfor);
		}
		
		return bdsSchemaInfors;
	}
	

	/**
     * 检验对象属性合法性
     * @return true 合法数据; false 非法数据
     */
	public boolean validateData() {
    	
		boolean result = true;
        //if (!DataValidater.isStrLong(bdsSchemaInforVo.getApplicationId())) {
        //	result = setInputMessage(BdsConstant.APPLICATION_ID_KEY, BdsConstant.APPLICATION_ID_MSG);
        //}
        if (DataValidater.isStrEmpty(bdsSchemaInforVo.getBdsSchemaInforCode())){
        	result = setInputMessage(BdsConstant.BDS_SCHEMAINFOR_CODE_KEY, BdsConstant.BDS_SCHEMAINFOR_CODE_MSG);
        }
        if (DataValidater.isStrEmpty(bdsSchemaInforVo.getBdsSchemaInforNameEng())) {
        	result = setInputMessage(BdsConstant.BDS_SCHEMAINFOR_NAME_KEY, BdsConstant.BDS_SCHEMAINFOR_NAME_MSG);
        }
        if (DataValidater.isStrEmpty(bdsSchemaInforVo.getBdsSchemaInforNameCna())) {
        	result = setInputMessage(BdsConstant.BDS_SCHEMAINFOR_NAME_CNA_KEY, BdsConstant.BDS_SCHEMAINFOR_NAME_CNA_MSG);
        }
        if (DataValidater.isStrEmpty(bdsSchemaInforVo.getBdsSchemaInforType())) {
        	result = setInputMessage(BdsConstant.BDS_SCHEMAINFOR_TYPE_KEY, BdsConstant.BDS_SCHEMAINFOR_TYPE_MSG);
        }
        if(BdsConstant.SCHEMAINFO_TYPE_SQL.equals(bdsSchemaInforVo.getBdsSchemaInforType())){
        	if (DataValidater.isStrEmpty(bdsSchemaInforVo.getSql())) {
            	result = setInputMessage(BdsConstant.SQL_KEY, BdsConstant.SQL_MSG);
            }
            if (DataValidater.isStrEmpty(bdsSchemaInforVo.getJndi())) {
            	result = setInputMessage(BdsConstant.JNDI_KEY, BdsConstant.JNDI_MSG);
            }
        }
        if(BdsConstant.SCHEMAINFO_TYPE_WS.equals(bdsSchemaInforVo.getBdsSchemaInforType())){
        	 if (DataValidater.isStrEmpty(bdsSchemaInforVo.getWebserviceAddress())) {
        		 result = setInputMessage(BdsConstant.WEBSERVICE_ADDRESS_KEY, BdsConstant.WEBSERVICE_ADDRESS_MSG);
             }
             if (DataValidater.isStrEmpty(bdsSchemaInforVo.getWebserviceAddress())) {
            	 result = setInputMessage(BdsConstant.WEBSERVICE_FUNCTION_KEY, BdsConstant.WEBSERVICE_FUNCTION_MSG);
             }
        }
       
        String xmlDoc = bdsSchemaInforVo.getDataStructureXml();
        if(!vailidateColNames(xmlDoc)){
        	result = setInputMessage(BdsConstant.DATA_STRUCTURE_XML_KEY, BdsConstant.DATA_STRUCTURE_XML_MSG);
        }
        
        if (!DataValidater.isStrInteger(bdsSchemaInforVo.getState())) {
        	result = setInputMessage(BdsConstant.STATE_KEY, BdsConstant.STATE_MSG);
        }
      
        return result;
    }

	@Override
	public String getJsonValue() {
		return super.getJsonValue();
	}
	
	protected boolean vailidateColNames(String xmlDoc){
		boolean result = false;
		try{
			List<String> colNames = BdsXmlUntil.getColNamesFromXmlDoc(xmlDoc);
			if(colNames.contains(BdsConstant.FIXED_COL_NAME_CODE)
				&& colNames.contains(BdsConstant.FIXED_COL_NAME_DN)
				&& colNames.contains(BdsConstant.FIXED_COL_NAME_DN_EN)
				&& colNames.contains(BdsConstant.FIXED_COL_NAME_DN_TC)){
				result = true;
			}
		}catch(Exception e){
			result = false;
		}
		return result;
	}
	
	public BdsSchemaInforVo getBdsSchemaInforVo() {
		return bdsSchemaInforVo;
	}
	
	public void setBdsSchemaInforVo(BdsSchemaInforVo bdsSchemaInforVo) {
		this.bdsSchemaInforVo = bdsSchemaInforVo;
	}
	
	public void setBdsSchemaInforService(BdsSchemaInforService bdsSchemaInforService) {
		this.bdsSchemaInforService = bdsSchemaInforService;
	}
	
	@JSON(serialize=false)
	public BdsSchemaInforService getBdsSchemaInforService() {
		return bdsSchemaInforService;
	}
	
}
