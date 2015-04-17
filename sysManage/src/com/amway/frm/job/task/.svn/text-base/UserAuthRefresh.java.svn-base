package com.amway.frm.job.task;

import org.springframework.transaction.annotation.Transactional;

import com.amway.frm.afw.task.RoleUserDistribute;
import com.amway.frm.base.util.ContextFactory;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.job.service.ShopAuthService;

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
