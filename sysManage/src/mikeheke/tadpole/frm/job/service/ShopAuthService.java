package mikeheke.tadpole.frm.job.service;


import java.util.List;

import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.job.entity.ShopAuth;

/**
 * 
 * @author lenovo
 *
 */
public interface ShopAuthService extends BaseService {
	
	ReturnMessage<ShopAuth> enableAuth();
	
	ReturnMessage copyAuths();
}
