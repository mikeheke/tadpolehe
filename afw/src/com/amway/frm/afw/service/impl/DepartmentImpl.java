package com.amway.frm.afw.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.amway.frm.afw.dao.IDepartmentDao;
import com.amway.frm.afw.entity.Department;
import com.amway.frm.afw.service.DepartmentService;
import com.amway.frm.base.service.impl.BaseImpl;
import com.amway.frm.exception.exception.AmwaySysException;

/**
 * Created by IntelliJ IDEA.
 * @author huangweijin
 * Date: 2011-4-20
 * Time: 11:17:43
 * Declare:部门信息SERVICE
 */

public class DepartmentImpl extends BaseImpl<Department> implements DepartmentService {
    //部门信息DAO
    private IDepartmentDao departmentDao;

    /**
     * Declare：获得部门列表
     *
     * @param
     * @return List
     * @throws java.sql.SQLException
     */
    public List getDepartmentList() {
        try {
            return departmentDao.getDepartmentList();
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new AmwaySysException(e);
        }
    }

    /**
     * Declare：获得部门列表
     *
     * @param departmentId 部门id
     * @return Department
     */
    public Department getEntityById(long departmentId) {
        try {
            return departmentDao.getEntityById(departmentId);
        } catch (SQLException e) {
            throw new AmwaySysException(e);
        }
    }

    public IDepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(IDepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }


}
