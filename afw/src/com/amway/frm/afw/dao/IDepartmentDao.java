package com.amway.frm.afw.dao;

import com.amway.frm.afw.entity.Department;
import com.amway.frm.base.dao.IBaseDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-4-16
 * Time: 11:10:44
 * Declare: 部门信息DAO接口
 */

public interface IDepartmentDao extends IBaseDao<Department, String> {
    //获得部门列表
    List getDepartmentList() throws SQLException;
}
