package com.amway.frm.afw.dao;

import java.sql.SQLException;

import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.entity.RoleUser;
import com.amway.frm.base.dao.IBaseDao;

/**
 * 
 * @author lenovo
 *
 */
public interface IRoleUserDao extends IBaseDao<RoleUser, String> {

	int deleteRoleUsers(Role role);
	
	String findUserProfileIdByEmpNumber(String empNumber);
}
