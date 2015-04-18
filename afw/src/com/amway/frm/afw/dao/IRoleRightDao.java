package com.amway.frm.afw.dao;

import java.sql.SQLException;

import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleRight;
import com.amway.frm.base.dao.IBaseDao;


/**
 * Created by MyElipse
 * @author huangweijin
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：角色权限Dao接口
 */
public interface IRoleRightDao extends IBaseDao<RoleRight, Long>{
	
	/**
	 * Declare：删除角色所有权限
	 * 
	 * @param role 角色
	 * @return List<RoleRight> 权限结果集
	 */
	void deleteRightsByRole(Role role) throws SQLException;
	
}