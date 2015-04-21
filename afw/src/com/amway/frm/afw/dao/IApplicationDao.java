package com.amway.frm.afw.dao;

import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.base.dao.IBaseDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-4-6
 * Time: 15:52:24
 * Declare：MstbApplicationDao表对应的DAO，适用于操作MstbApplicationDao表，包括SELECT,CREATE,UPDATE,DELETE各种操作
 */
public interface IApplicationDao extends IBaseDao<Application, String> {
    
	List<Application> getApplicationListJDBC(UserProfile userProfile)
			throws SQLException;
	
	List<Application> getApplicationListJDBC()
			throws SQLException;

	//获得应用列表
    List<Application> getApplicationList() throws SQLException;
    
    List<Application> getApplications(Application application) throws SQLException;
}
