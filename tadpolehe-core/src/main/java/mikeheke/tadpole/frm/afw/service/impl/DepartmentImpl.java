package mikeheke.tadpole.frm.afw.service.impl;

import java.sql.SQLException;
import java.util.List;

import mikeheke.tadpole.frm.afw.dao.IDepartmentDao;
import mikeheke.tadpole.frm.afw.entity.Department;
import mikeheke.tadpole.frm.afw.service.DepartmentService;
import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.exception.exception.AmwaySysException;

/**
 * Created by IntelliJ IDEA.
 * 
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
    public Department getEntityById(String departmentId) {
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
