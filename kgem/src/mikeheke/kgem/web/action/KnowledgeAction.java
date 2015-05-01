package mikeheke.kgem.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mikeheke.kgem.entity.Knowledge;
import mikeheke.kgem.service.KnowledgeService;
import mikeheke.kgem.vo.KnowledgeVo;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.DataConverter;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.base.web.action.BaseAction;
import mikeheke.tadpole.frm.logging.service.LogService;
import mikeheke.tadpole.frm.logging.util.LogFactory;

/**
 * 
 * @author mike
 *
 */
public class KnowledgeAction extends BaseAction {

	
	private static LogService logger = LogFactory.getLogger(KnowledgeAction.class);
	
	/**
	 * 业务服务
	 */
	private KnowledgeService knowledgeService;
	
	/**
	 * 视图vo
	 */
	private KnowledgeVo knowledgeVo;
	
//	//业务Service
//	private RoleService roleService;
//	
//	private RoleVo roleVo;
	
	/**
	 * 设置实体数据
	 */
	@Override
	protected Knowledge getEntity() {
		
		Knowledge k = new Knowledge();
		
		//k.setKnowledgeId((knowledgeVo.getKnowledgeId()));
		k.setKnowledgeId(knowledgeVo.getKnowledgeId());
		k.setTitle(knowledgeVo.getTitle());
		k.setContent(knowledgeVo.getContent());
		k.setCreatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		k.setCreatedTime(new Date());
		k.setUpdatedUserId(this.getSysInfo().getUserProfile().getEmpNumber());
		k.setUpdatedTime(new Date());
		
		return k;
	}
	
	@Override
	protected Knowledge getEntity(String KnowledgeId) {
		
		Knowledge k = new Knowledge();
		//k.setKnowledgeId((KnowledgeId));
		k.setKnowledgeId(KnowledgeId);
		
		return k;
	}
	
	@Override
	protected List<Knowledge> getEntities(String[] knowledgeIds){
		
		List<Knowledge> knowledges = new ArrayList<Knowledge>(); 
		
		for(String knowledgeId: knowledgeIds){
			Knowledge knowledge = new Knowledge();
			//knowledge.setKnowledgeId(((knowledgeId)));
			knowledge.setKnowledgeId((knowledgeId));
			knowledges.add(knowledge);
		}
		
		return knowledges;
	}

	
	/**
	 * 表单输入校验
	 * @return
	 */
	protected boolean validateData() {
		
		boolean result = true;
//		if(DataValidater.isStrEmpty(roleVo.getRoleCode())){
//			result = setInputMessage(AfwConstant.ROLE_CODE_KEY, AfwConstant.ROLE_CODE_MSG);
//		}
//		if(DataValidater.isStrEmpty(roleVo.getRoleName())){
//			result = setInputMessage(AfwConstant.ROLE_NAME_KEY, AfwConstant.ROLE_NAME_MSG);
//		}
//		if(DataValidater.isStrEmpty(roleVo.getApplicationId())){
//			result = setInputMessage(AfwConstant.APPLICATION_ID_KEY, AfwConstant.APPLICATION_ID_MSG);
//		}
//		if(DataValidater.isStrEmpty(roleVo.getState())){
//			result = setInputMessage(AfwConstant.STATE_KEY, AfwConstant.STATE_MSG);
//		}
		
		if(DataValidater.isStrEmpty(knowledgeVo.getTitle())){//
			//设置错误提示消息到Request
			result = setInputMessage("title"+"Msg", "请填写知识标题");
		}
		
		return result;
	}
	
	/**
	 * 初始化－增加
	 */
	@Override
	public String initAdd(){
		//跳转的视图flag
		String result = INIT_ADD_SUCCESS;
		
		//设置数据到Request范围中
		//设置下一步操作标识：增加
		this.setMessage(AppConstant.OPRT, AppConstant.ADD_OPRT);
		
		return result;
	}
	
	@Override
	public String add() {

		String result = ADD_SUCCESS;
		
		//创建返回对象(它会把数据设置到Request范围中)
		ReturnMessage<Knowledge> returnMessage = new ReturnMessage<Knowledge>();
		
		//获取页面数据实体
		Knowledge k = this.getEntity();
		
		if(!validateData()){//校验表单
			//set entity to returnMessage
			returnMessage.setReturnObject(k);
			//set returnMessage to request
			setReturnMessage(returnMessage);
			return ADD_INPUT;
		}
		
		//业务操作
		returnMessage = knowledgeService.addOrUpdate(k);
		
		//增加成功，清除表单信息, 为了可以继续增加记录
		if(returnMessage.isSuccess()){
			returnMessage.clearReturnObjects();
		}
		
		//设置返回数据到Request
		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
		
		
		
//		Role role = this.getEntity();
//		ReturnMessage<Role> returnMessage = new ReturnMessage<Role>();
//		
//		if(!validateData()){
//			returnMessage.setReturnObject(role);
//			setReturnMessage(returnMessage);
//			return ADD_INPUT;
//		}
//		
//		returnMessage = roleService.addRole(role);
//		if(returnMessage.isSuccess()){
//			returnMessage.clearReturnObjects();
//		}
//		result = setReturnMessage(returnMessage, ADD_SUCCESS, ADD_INPUT);
//		
//		final String msg = "创建角色";
//		logger.writeOperLog(AppConstant.ADD_OPRT, msg + role.getRoleCode());
		
		return result;
	}
	
	/**
	 * 修改－初始化
	 */
	@Override
	public String initModify() {
		
		String result = INIT_MODIFY_SUCCESS;
		this.setMessage(AppConstant.OPRT, AppConstant.MDF_OPRT);
		
		String [] knowledgeIds = null;
		if(null != knowledgeVo){
			knowledgeIds = knowledgeVo.getKnowledgeIds();
		}
		if(!validateIds0(knowledgeIds)){
			return this.MDF_INPUT;
		}
		
		//Knowledge qKnowledge = new Knowledge((knowledgeIds[0]));
		Knowledge qKnowledge = new Knowledge(knowledgeIds[0]);
		ReturnMessage<Knowledge> returnMessage = knowledgeService.query(qKnowledge);
		
		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
		
//		String [] roleIds = null;
//		if(null != roleVo){
//			roleIds = roleVo.getRoleIds();
//		}
//		if(!validateIds0(roleIds)){
//			return INIT_MODIFY_INPUT;
//		}
//		
//		
//		Role role = this.getEntity(roleIds[0]);
//		ReturnMessage<Role> returnMessage = roleService.query(role);
//		result = setReturnMessage(returnMessage, INIT_MODIFY_SUCCESS, INIT_MODIFY_INPUT);
		
		return result;
	}
	
	
	@Override
	public String modify(){
		
		String result = MDF_SUCCESS;
		
		//创建返回对象(它会把数据设置到Request范围中)
		ReturnMessage<Knowledge> returnMessage = new ReturnMessage<Knowledge>();
		
		//获取页面数据实体
		Knowledge k = this.getEntity();
		
		if(!validateData()){//校验表单
			//set entity to returnMessage
			returnMessage.setReturnObject(k);
			//set returnMessage to request
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		if(!validateId(knowledgeVo.getKnowledgeId())){
			returnMessage.setReturnObject(k);
			setReturnMessage(returnMessage);
			return MDF_INPUT;
		}
		
		//业务操作
		returnMessage = knowledgeService.update(k);
		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);


//		Role role = this.getEntity();
//		ReturnMessage<Role> returnMessage = new ReturnMessage<Role>();
//		
//		if(!validateData()){
//			returnMessage.setReturnObject(role);
//			setReturnMessage(returnMessage);
//			return MDF_INPUT;
//		}
//		String roleId = roleVo.getRoleId();
//		if(!validateId(roleId)){
//			returnMessage.setReturnObject(role);
//			setReturnMessage(returnMessage);
//			return MDF_INPUT;
//		}
//		
//		returnMessage = roleService.updateRole(role);
//		result = setReturnMessage(returnMessage, MDF_SUCCESS, MDF_INPUT);
//		
//		final String msg = "修改角色";
//		logger.writeOperLog(AppConstant.MDF_OPRT, msg + role.getRoleCode());
		
		return result;
	}
	
	/**
	 * 批量删除
	 */
	@Override
	public String delete() {
		
		String result = DEL_INPUT;
		
		String [] knowledgeIds = null;
		if(null != knowledgeVo){
			knowledgeIds = knowledgeVo.getKnowledgeIds();
		}
		
		if(validateIds(knowledgeIds)){
			
			List<Knowledge> knowledges = this.getEntities(knowledgeIds);
			ReturnMessage<Knowledge> returnMessage = knowledgeService.deleteList(knowledges);
			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
		}
		
		setJsonValue(result);
		//setJsonValue("delsuccess");
		
//		try {
//			
//			PrintWriter pw = ServletActionContext.getResponse().getWriter();
//			pw.write("success");
//			pw.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return null;
		
//		String[] roleIds = null;
//		if(null != roleVo){
//			roleIds = roleVo.getRoleIds();
//		}
//		if(validateIds(roleIds)){
//
//			List<Role> roles = this.getEntities(roleIds);
//			ReturnMessage<Role> returnMessage = roleService.logicDeleteList(roles);
//			result = setReturnMessage(returnMessage, DEL_SUCCESS, DEL_INPUT);
//		}
//		
//		setJsonValue(result);
		
		return JSON;
	}

	/**
	 * 查看记录明细
	 * @return
	 */
	public String view() {
		String result = "view";
		this.setMessage(AppConstant.OPRT, "view");
		
		String [] knowledgeIds = null;
		if(null != knowledgeVo){
			knowledgeIds = knowledgeVo.getKnowledgeIds();
		}
		if(!validateIds0(knowledgeIds)){
			return result;
		}
		
		//Knowledge qKnowledge = new Knowledge((knowledgeIds[0]));
		Knowledge qKnowledge = new Knowledge(knowledgeIds[0]);
		ReturnMessage<Knowledge> returnMessage = knowledgeService.query(qKnowledge);
		
		result = setReturnMessage(returnMessage, result, result);
		
		return result;
	}


	@Override
	public String getJsonValue() {
		return super.getJsonValue();
	}

	//此处会引起json 返回抛异常
//	public KnowledgeService getKnowledgeService() {
//		return knowledgeService;
//	}

	public void setKnowledgeService(KnowledgeService knowledgeService) {
		this.knowledgeService = knowledgeService;
	}

	public KnowledgeVo getKnowledgeVo() {
		return knowledgeVo;
	}

	public void setKnowledgeVo(KnowledgeVo knowledgeVo) {
		this.knowledgeVo = knowledgeVo;
	}
}
