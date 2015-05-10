package mikeheke.tadpole.frm.job.task;

import mikeheke.tadpole.frm.afw.task.RoleUserDistribute;
import mikeheke.tadpole.frm.base.util.ContextFactory;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.job.service.ShopAuthService;

import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Xie Jinnan
 *
 */
public class UserAuthRefresh {
	
	private ShopAuthService shopAuthService;
	
	public UserAuthRefresh(){
		final String beanName = "ShopAuthService";
		ShopAuthService shopAuthService = (ShopAuthService) ContextFactory.getBean(beanName);
		this.shopAuthService = shopAuthService;
	}
	
	@Transactional
	public void refresh() {
		ReturnMessage returnMessage = shopAuthService.enableAuth();
		if (returnMessage.isSuccess()) {
			returnMessage = shopAuthService.copyAuths();
			if (returnMessage.isSuccess()) {
				RoleUserDistribute roleUserDistribute = new RoleUserDistribute();
				roleUserDistribute.distribute();
			}
		}
	}

	public ShopAuthService getShopAuthService() {
		return shopAuthService;
	}

	public void setShopAuthService(ShopAuthService shopAuthService) {
		this.shopAuthService = shopAuthService;
	}

}
