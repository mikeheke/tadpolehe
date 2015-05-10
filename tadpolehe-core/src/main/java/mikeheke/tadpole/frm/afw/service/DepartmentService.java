package mikeheke.tadpole.frm.afw.service;

import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Department;
import mikeheke.tadpole.frm.base.service.BaseService;

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-4-21
 * Time: 10:27:52
 * To change this template use File | Settings | File Templates.
 */
public interface DepartmentService extends BaseService<Department> {
    //获得部门列表
    List getDepartmentList();
    //获得部门信息
    Department getEntityById(String departmentId) ;
}
