package mikeheke.tadpole.frm.afw.adapters;

import mikeheke.tadpole.frm.afw.authentication.IFWAuthenticator;
import mikeheke.tadpole.frm.afw.authentication.principal.Credentials;
import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.afw.service.UserProfileService;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.MD5EncodeUtil;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.logging.service.LogService;
import mikeheke.tadpole.frm.logging.util.LogFactory;

/**
 * 验证接口类
 */
/**
 * @author Administrator
 * 
 */
public class FWDBAdapter implements IFWAuthenticator {
	
	private static LogService logger = LogFactory.getLogger(FWDBAdapter.class);
	
	// 用户信息接口类
	private UserProfileService userProfileService;

	/**
	 * 本地数据库用户验证实现类
	 * 
	 * @param credentials
	 *            被验证的信息
	 * @return ReturnMessage ReturnMessage.getReturnCode等于1为成功其他值为失败
	 */
	public ReturnMessage<Credentials> authenticate(Credentials credentials) {

		ReturnMessage<Credentials> returnMessage = new ReturnMessage<Credentials>();
	
		UserProfile userProfile = this.generateUser(credentials);
		
		ReturnMessage<UserProfile> returnMessageFWD = null;
		returnMessageFWD = userProfileService.query(userProfile);
		
		UserProfile userProfileRet = returnMessageFWD.getReturnObject();
		if(null == userProfileRet){
			final String msg = "用户名或密码不正确";
			returnMessage.setReturnMsg(msg);
			return returnMessage;
		}	
		
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		returnMessage.setReturnObject(credentials);
		
		return returnMessage;
	}
	
	private UserProfile generateUser(Credentials credentials) {

		UserProfile userProfile = new UserProfile();
		userProfile.setEmpNumber(credentials.getUserCode());
		userProfile.setPassword(MD5EncodeUtil.MD5Encode(AppConstant.MD5_KEY
				+ credentials.getPassword()));

		return userProfile;
	}

	public void setUserProfileService(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}
}
