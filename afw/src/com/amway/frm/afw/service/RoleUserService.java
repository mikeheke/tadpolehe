package com.amway.frm.afw.service;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleUser;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;

/**
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：角色用户服务接口
 */
public interface RoleUserService extends BaseService {
	
	/**
     * Declare：通过SQL增加角色用户
     *
	 * @param  roleUser 角色用户
	 * @param  dsCode 数服务代码
     * @return ReturnMessage<RoleUser> 返回信息 
     */
	ReturnMessage<RoleUser> addRoleUserBySQL(Role role);
	
	/**
	 * 根据用户和应用取得角色
	 * @param user
	 * @param app
	 * @return
	 */
	ReturnMessage<Role> getRoleList(UserProfile user, Application app);
	
	/**
	 * 根据用户和应用取得角色
	 * @param user
	 * @param app
	 * @return
	 */
	ReturnMessage<Role> getRoleAndDefaultList(UserProfile user, Application app);

}
