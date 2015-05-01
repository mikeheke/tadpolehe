package mikeheke.tadpole.frm.afw.service;

import mikeheke.tadpole.frm.afw.entity.Module;
import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.afw.vo.UserProfileVo;
import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.exception.exception.AmwaySysException;

/**
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：用户服务接口
 */
public interface UserProfileService extends BaseService  {

	/**
	 * 通过WS导入外部用户
	 * @return
	 */
   ReturnMessage<UserProfile> leadInUsersByWebService();
   
   /**
    * 
    */
   ReturnMessage<UserProfile> modifyPwd(UserProfileVo userProfileVo);
   
   /**
    * 更新用户信息，复制原来的用户密码
	* @param userProfile
	* @return
	*/
	ReturnMessage<UserProfile> updateUserProfile(UserProfile userProfile);

	/**
	 * 重置密码
	 * @param userProfile
	 * @return
	 */
	ReturnMessage<UserProfile> resetPwd(UserProfile userProfile);

	/**
	 * @param userProfileVo
	 * @return
	 */
	ReturnMessage<Module> changeUser(UserProfileVo userProfileVo);
	
	
	UserProfile getUniqueUserProfile(UserProfile userProfile) throws AmwaySysException; 
   
} 
