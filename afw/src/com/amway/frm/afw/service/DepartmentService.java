package com.amway.frm.afw.service;

import com.amway.frm.afw.entity.Department;
import com.amway.frm.base.service.BaseService;

import java.util.List;

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
