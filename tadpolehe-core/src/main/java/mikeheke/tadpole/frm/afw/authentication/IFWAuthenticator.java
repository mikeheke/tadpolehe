package mikeheke.tadpole.frm.afw.authentication;

import mikeheke.tadpole.frm.afw.authentication.principal.Credentials;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
/**
 * 验证接口类
 * 
 */
public interface IFWAuthenticator {
    /**
     * 调用IFWAuthenticator接口的不同实现类进行验证
     * @param credentials 被验证的信息
     * @return ReturnMessage ReturnMessage.getReturnCode等于1为成功其他值为失败
     */	
	public ReturnMessage<Credentials> authenticate(Credentials credentials);

}
