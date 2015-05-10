package mikeheke.tadpole.frm.afw.dao;

import java.sql.SQLException;
import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Department;
import mikeheke.tadpole.frm.base.dao.IBaseDao;

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
