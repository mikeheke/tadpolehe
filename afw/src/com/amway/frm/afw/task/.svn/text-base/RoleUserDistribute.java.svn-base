package com.amway.frm.afw.task;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.amway.frm.afw.entity.Role;
import com.amway.frm.afw.service.RoleUserService;
import com.amway.frm.base.util.ContextFactory;

/**
 * 
 * @author lenovo
 *
 */
public class RoleUserDistribute {

	private RoleUserService roleUserService;
	
	public RoleUserDistribute(){
		final String beanName = "RoleUserService";
		RoleUserService roleUserService = (RoleUserService) ContextFactory.getBean(beanName);
		this.roleUserService = roleUserService;
	}
	
	@Transactional
	public void distribute(){
		
		List<Role> roles = roleUserService.queryList(new Role());
		for(Role role: roles){
			roleUserService.addRoleUserBySQL(role);
		}
	}

	public void setRoleUserService(RoleUserService roleUserService) {
		this.roleUserService = roleUserService;
	}
	
	
}
