package com.amway.frm.afw.service;

import com.amway.frm.afw.entity.Module;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.afw.vo.UserProfileVo;
import com.amway.frm.base.service.BaseService;
import com.amway.frm.base.vo.ReturnMessage;
import com.amway.frm.exception.exception.AmwaySysException;

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
