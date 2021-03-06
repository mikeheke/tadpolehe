package mikeheke.tadpole.frm.afw.service;


import mikeheke.tadpole.frm.afw.authentication.IFWAuthenticator;
import mikeheke.tadpole.frm.afw.authentication.principal.Credentials;
import mikeheke.tadpole.frm.afw.vo.LoginVo;
import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;

/**
 * 登录验证接口类
 * 
 */

public interface LoginService extends BaseService {
    /**
     * 调用IFWAuthenticator接口的不同实现类进行验证，先调用FWLDAPAdapter验证，
     * 不存在帐号和连接失败的再调用FWDBAdapter进行验证
     *
     * @param loginVo 登录验证信息类
     * @return ReturnMessage ReturnMessage.getReturnCode等于1为成功其他值为失败
     */
	ReturnMessage<Credentials> webLogin(LoginVo loginVo);
	
	/**
	 * 通过编码取适配器
	 * 
	 * @param adaptorCode 适配器编码
	 * @return
	 */
	IFWAuthenticator selectAdaptor(String adaptorCode);
	
	/**
	 * 调用IFWAuthenticator接口的不同实现类进行验证
	 * 
	 * @param credentials
	 *            被验证的信息
	 * @return ReturnMessage ReturnMessage.getReturnCode等于1为成功其他值为失败
	 */
	ReturnMessage<Credentials> authenticate(Credentials credentials);
	
}