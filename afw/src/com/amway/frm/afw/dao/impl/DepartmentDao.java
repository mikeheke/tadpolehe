package com.amway.frm.afw.dao.impl;

import com.amway.frm.afw.dao.IDepartmentDao;
import com.amway.frm.afw.entity.Department;
import com.amway.frm.base.dao.impl.BaseDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * @author huangweijin
 * Date: 2011-4-16
 * Time: 11:11:33
 * Declare: 部门信息DAO 实现
 */

public class DepartmentDao extends BaseDao<Department, Long> implements IDepartmentDao {
    /**
     * Declare：获得部门列表
     *
     * @param
     * @return List
     * @throws java.sql.SQLException
     */
    public List getDepartmentList() throws SQLException {
        final String jql = "from Department t where t.recordState = 1 and state = 1 ";
        return this.getResultList(jql);
    }
}
