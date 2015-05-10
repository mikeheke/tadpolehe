package mikeheke.tadpole.frm.base.web.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.afw.util.AfwConstant;
import mikeheke.tadpole.frm.afw.vo.SysInfoBean;
import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：Action基类
 */
public class BaseAction<T extends Object> extends ActionSupport{

	private static final long serialVersionUID = 1105453742498878107L;
	
	//返回对象集
	public static final String RET_OBJ = "retObjs";
	
	//返回信息
	public static final String RET_INFO = "retInfo";
	
	//返回组信息
	public static final String RET_GROUP = "retGroup";
	
	//错误信息
	public static final String ERR_INFO = "errorInfo";
	
	//初始化成功
	public static final String INIT_SUCCESS = "initSuccess";
	
	//初始化失败
	public static final String INIT_INPUT = "initInput";
	
	//增加初始化成功
	public static final String INIT_ADD_SUCCESS = "initAddSuccess";
	
	//增加初始化失败
	public static final String INIT_ADD_INPUT = "initAddInput";
	
	//修改初始化成功
	public static final String INIT_MODIFY_SUCCESS = "initModifySuccess";
	
	//修改初始化失败
	public static final String INIT_MODIFY_INPUT = "initModifyInput";	
	//查询成功
	public static final String QRY_SUCCESS = "qrySuccess";
	
	//查询失败
	public static final String QRY_INPUT = "qryInput";
	
	//增加成功
	public static final String ADD_SUCCESS = "addSuccess";
	
	//增加失败
	public static final String ADD_INPUT = "addInput";
	
	//修改成功
	public static final String MDF_SUCCESS = "mdfSuccess";
	
	//修改失败
	public static final String MDF_INPUT = "mdfInput";
	
	//删除成功
	public static final String DEL_SUCCESS = "delSuccess";
	
	//删除失败
	public static final String DEL_INPUT = "delInput";
	
	//保存成功
	public static final String SAVE_SUCCESS = "saveSuccess";
	
	//保存失败
	public static final String SAVE_INPUT = "saveInput";
	
	//删除失败
	public static final String POPUP_INPUT = "popupInput";
	
	//删除失败
	public static final String POPUP_SUCCESS = "popupSuccess";
	
	//json返回
	public static final String JSON = "json";
	
	//jsonValue
	private String jsonValue;
	
	//操作
	private String oprt;
	
	private String id;
	
	private String[] ids;
	
	//基类服务
	private BaseService<T> baseService;
	
	/**
	 * 取当前系统信息
	 * @return SysInfoBean
	 */
	protected SysInfoBean getSysInfo(){
		return SysInfoBean.getSysInfoBean();
	}
	
	/**
	 * 从会话中获取当前用户信息,存入哈希表中
	 * @return userInfo 当前登录者信息
	 */
	protected Map<String, Object> getUserInfo() {
		Map<String, Object> userInfo = new Hashtable<String, Object>();
		// 加入当前用户信息
		UserProfile userProfile = (UserProfile)ServletActionContext.getRequest().getSession().
			getAttribute(AppConstant.USER_NAME);
		userInfo.put(AppConstant.USER_NAME, userProfile);
		return userInfo;
	}
	
	/**
	 * 取上下文路径
	 * @return SysInfoBean
	 */
	protected String getContextPath(){
		return ContextFactory.getContextPath();
	}
	
	/**
	 * 取上下文真实路径
	 * @return SysInfoBean
	 */
	protected String getContextRealPath(){
		return ContextFactory.getContextRealPath();
	}
	
	/**
	 * Declare：取查询(Form/地址栏)参数
	 * 
	 * @param name 名称
	 * @return String 值
	 */
	protected String getParam(String name){
		
		return ContextFactory.getParamFromRequest(name);
	}
	
	/**
	 * Declare：取查询(Form/地址栏)参数组
	 * 
	 * @param name 名称
	 * @return String[] 值组
	 */
	protected String[] getParams(String name){
		
		return ContextFactory.getParamsFromRequest(name);
	}
	
	/**
	 * Declare：设置对象到Request中
	 * 
	 * @param name 名称
	 * @param obj 对象
	 * @return void
	 */
	protected void setMessage(String name, Object obj){
		
		ContextFactory.setToRequest(name, obj);
	}
	
	/**
	 * Declare：设置对象到Request中,返回失败
	 * 
	 * @param name 名称
	 * @param obj 对象
	 * @return boolean
	 */
	protected boolean setInputMessage(String name, Object obj){
		
		this.setMessage(name, obj);
		
		return false;
	}
	
	/**
	 * Declare：设置组对象到Request中,返回失败
	 * 
	 * @param groups 组
	 * @return void
	 */
	protected void setGroupMessage(List<String[]> groups){
		
		for(String [] group : groups){
			this.setMessage(group[0], group[1]);
		}
	}
	
	/**
	 * Declare：从Request取对象
	 * 
	 * @param name 名称
	 * @return Object 对象
	 */
	protected Object getMessage(String name){
		
		return ContextFactory.getFromRequest(name);
	}
	
	/**
	 * Declare：设置返回信息
	 * 
	 * @param returnMessage 返回信息
	 * @param successStr 成功返回
	 * @param inputStr 失败返回信
	 * @return String 返回
	 */
	protected String setReturnMessage(ReturnMessage<T> returnMessage, 
			String successStr, String inputStr){
		
		if(null == returnMessage){
			return ERROR;
		}
		
		String success = SUCCESS;
		if(null != successStr){
			success = successStr;
		}
		
		String input = INPUT;
		if(null != inputStr){
			input = inputStr;
		}
	
		String retResult = success;
		if(!returnMessage.isSuccess()){
			retResult = input;
		}
		
		this.setMessage(RET_OBJ, returnMessage.getReturnObjects());
		this.setMessage(RET_INFO, returnMessage.getReturnMsg());
		this.setGroupMessage(returnMessage.getReturnGroups());
		
		return retResult;
	}
	
	/**
	 * Declare：设置默认返回信息
	 * 
	 * @param returnMessage 返回信息
	 * @return String 返回
	 */
	protected String setReturnMessage(ReturnMessage<T> returnMessage){
		
		return setReturnMessage(returnMessage, SUCCESS, INPUT);
	}
	
	/**
	 * Declare：页面初始化（修改）,子类重载
	 * @return
	 */
	public String initModify() {
		
		return INIT_MODIFY_SUCCESS;
	}
	
	/**
	 * Declare：页面初始化（增加）,子类重载
	 * @return
	 */
	public String initAdd() {
		
		return INIT_ADD_SUCCESS;
	}
	
	/**
	 * Declare：页面初始化,子类重载
	 * 
	 * @param  void
	 * @return String 返回
	 */
	public String init(){
		
		return INIT_SUCCESS;
	}
	
	/**
	 * Declare：页面增加,子类重载
	 * 
	 * @param  void
	 * @return String 返回
	 */
	public String add(){
		
		return ADD_SUCCESS;
	}
	
	/**
	 * Declare：页面删除,子类重载
	 * 
	 * @param  void
	 * @return String 返回
	 */
	public String delete(){
		
		return DEL_SUCCESS;
	}
	
	public String json(){
		
		return JSON;
	}
	
	/**
	 * Declare：页面修改,子类重载
	 * 
	 * @param  void
	 * @return String 返回
	 */
	public String modify(){
		
		return MDF_SUCCESS;
	}
	
	/**
	 * Declare：页面保存,子类重载
	 * 
	 * @param  void
	 * @return String 返回
	 */
	public String save(){
		
		return SAVE_SUCCESS;
	}
	
	/**
	 * Declare：页面查询
	 * 
	 * @param  void
	 * @return String 返回
	 */
	public String query(){
		
		return QRY_SUCCESS;
	}
	
	/**
	 * Declare：弹出页面
	 * 
	 * @param  void
	 * @return String 返回
	 */
	public String popup(){
		
		return POPUP_SUCCESS;
	}

	/**
	 * Declare：属性验证,子类重载
	 * 
	 * @param  void
	 * @return String 返回
	 */
	protected boolean validateData(){
		
		return false;
	}
	
	/**
	 * Declare：ids数组属性验证,子类可重载
	 * 
	 * @param  ids ids
	 * @return boolean 返回
	 */
	protected boolean validateIds(String[] ids){
		
		boolean result = true;
		if(null == ids || 0 == ids.length){
			this.setMessage(AfwConstant.ID_MSG, getText(AfwConstant.DELETE_ERROR_INFO_KEY));
			result = false;
		}
		
		return result;
	}
	
	/**
	 * Declare：id属性验证,子类可重载
	 * 
	 * @param id id
	 * @return boolean 返回
	 */
	protected boolean validateId(String id){
		
		boolean result = true;
		if(DataValidater.isStrEmpty(id)){
			this.setMessage(AfwConstant.ID_MSG, getText(AfwConstant.MODIFY_ERROR_INFO_KEY));
			result = false;
		}
		
		return result;
	}
	
	/**
	 * Declare：ids数组属性验证,子类可重载
	 * 
	 * @param  ids ids
	 * @return boolean 返回
	 */
	protected boolean validateIds0(String[] ids){
		
		boolean result = true;
		if(!validateIds(ids)){
			this.setMessage(AfwConstant.ID_MSG, getText(AfwConstant.MODIFY_ERROR_INFO_KEY));
			return false;
		}
		if(!validateId(ids[0])){
			this.setMessage(AfwConstant.ID_MSG, getText(AfwConstant.MODIFY_ERROR_INFO_KEY));
			return false;
		}
		
		return result;
	}
	
	/**
	 * Declare：取上下文实体,子类重载
	 * 
	 * @param  void
	 * @return T 实体
	 */
	protected T getEntity() {
		
		return null;
	}
	
	/**
	 * Declare：取上下文实体,子类重载
	 * 
	 * @param  void
	 * @return T 实体
	 */
	protected T getQryEntity() {
		
		return null;
	}
	
	/**
	 * Declare：取上下文实体集,子类重载
	 * 
	 * @param  void
	 * @return List<T> 实体集
	 */
	protected List<T> getEntities() {
		
		return new ArrayList<T>();
	}
	
	/**
	 * Declare：根据ID取上下文实体,子类重载
	 * 
	 * @param  id ID
	 * @return T 实体
	 */
	protected T getEntity(String id) {
		
		return null;
	}
	
	/**
	 * Declare：根据ID集取上下文实体集,子类重载
	 * 
	 * @param  ids IDS
	 * @return List<T> 实体集
	 */
	protected List<T> getEntities(String[] ids) {
		
		return new ArrayList<T>();
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public List<T> getList(T obj){
		return baseService.queryList(obj);
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public T getSingle(T obj){
		return baseService.querySingle(obj);
	}
	
	/**
	 * 取得排序号
	 * @return
	 */
	public String findOrderNo(){
		return JSON;
	}

	/**
	 * 清除对象
	 * @param obj
	 
	public void clearEntity(Object obj){
		obj = null;
	}
	*/
	
	public String getOprt() {
		return oprt;
	}

	public void setOprt(String oprt) {
		this.oprt = oprt;
	}
	
	public void setJsonValue(String jsonValue) {
		this.jsonValue = jsonValue;
	}

	public String getJsonValue() {
		return jsonValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public void setBaseService(BaseService<T> baseService) {
		this.baseService = baseService;
	}
	
}
