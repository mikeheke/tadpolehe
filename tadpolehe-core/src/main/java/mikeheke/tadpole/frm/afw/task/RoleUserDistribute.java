package mikeheke.tadpole.frm.afw.task;

import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Role;
import mikeheke.tadpole.frm.afw.service.RoleUserService;
import mikeheke.tadpole.frm.base.util.ContextFactory;

import org.springframework.transaction.annotation.Transactional;

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
