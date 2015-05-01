package mikeheke.tadpole.frm.afw.adapters;

import mikeheke.tadpole.frm.afw.authentication.IFWAuthenticator;
import mikeheke.tadpole.frm.afw.authentication.principal.Credentials;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.logging.service.LogService;
import mikeheke.tadpole.frm.logging.util.LogFactory;

public class FWSSOAdapter implements IFWAuthenticator{

	@Override
	public ReturnMessage<Credentials> authenticate(Credentials credentials) {
		// TODO Auto-generated method stub
		return null;
	}
    
	
}
