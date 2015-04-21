package com.amway.frm.afw.service;

import java.util.List;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleRight;
import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;

/**
 * Created by MyElipse
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：角色权限Service接口
 */
public interface RoleRightService extends BaseService {
	
	/**
	 * Declare：拷贝权限
	 * 
	 * @param role 目标角色
	 * @param sourceRole 源角色
	 * @return ReturnMessage<Module> 返回信息returnMessage
	 */
	ReturnMessage<RoleRight> copyRight(Role desRole, Role srcRole);
	
	/**
	 * Declare：取角色权限
	 * 
	 * @param role 目标角色
	 * @return ReturnMessage<Module> 返回信息returnMessage
	 */
	ReturnMessage<Module> getRightList(Role role);
	
	/**
	 * Declare：取应用权限
	 * 
	 * @param application 应用
	 * @return ReturnMessage<Module> 返回信息returnMessage
	 */
	ReturnMessage<Module> getRightList(Application application);

	/**
	 * Declare：取角色权限
	 * 
	 * @param roles 角色集
	 * @return ReturnMessage<Module> 返回信息returnMessage
	 */
	ReturnMessage<Module> getRightList(List<Role> roles);
	
	/**
	 * Declare：分配角色权限
	 * 
	 * @param role 角色
	 * @param modules 权限结果集
	 * @return ReturnMessage<Module> 返回信息returnMessage
	 */
	ReturnMessage<RoleRight> disRights(Role role, List<Module> modules);
	
	/**
	 * Declare：取角色模块权限
	 * 
	 * @param role 目标角色
	 * @return ReturnMessage<Module> 返回信息returnMessage
	 */
	ReturnMessage<Module> getMRightList(List<Role> roles, Application application);
}
