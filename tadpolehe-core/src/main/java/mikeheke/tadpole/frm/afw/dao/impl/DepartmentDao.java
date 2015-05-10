package mikeheke.tadpole.frm.afw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import mikeheke.tadpole.frm.afw.dao.IDepartmentDao;
import mikeheke.tadpole.frm.afw.entity.Department;
import mikeheke.tadpole.frm.base.dao.impl.BaseDao;

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-4-16
 * Time: 11:11:33
 * Declare: 部门信息DAO 实现
 */

public class DepartmentDao extends BaseDao<Department, String> implements IDepartmentDao {
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
