package mikeheke.tadpole.frm.afw.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mikeheke.tadpole.frm.afw.dao.IRoleDao;
import mikeheke.tadpole.frm.afw.dao.IRoleUserDao;
import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.afw.entity.Role;
import mikeheke.tadpole.frm.afw.entity.RoleUser;
import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.afw.service.RoleUserService;
import mikeheke.tadpole.frm.afw.util.AfwConstant;
import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.bds.service.BaseDataSourceService;
import mikeheke.tadpole.frm.bds.util.BdsConstant;
import mikeheke.tadpole.frm.bds.vo.BaseDataSourceVo;
import mikeheke.tadpole.frm.exception.exception.AmwaySysException;

import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author lenovo
 *
 */
public class RoleUserImpl extends BaseImpl implements RoleUserService {
	
    private IRoleUserDao roleUserDao;
    
    private IRoleDao roleDao;
    
    private BaseDataSourceService baseDataSourceService;
    
    //系统同步用户角色权限
  	public static final int SYN_ROLE_USER = 0;
    

	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.afw.services.RoleUserService#addRoleUserBySQL(mikeheke.tadpole.frm.afw.entity.Role, java.lang.String)
	 */
    @Transactional
	@Override
	public ReturnMessage<RoleUser> addRoleUserBySQL(Role role) {
		
		String dictCode = role.getUserSql();
		if(DataValidater.isStrEmpty(dictCode)){
			return new ReturnMessage<RoleUser>();
		}
		ReturnMessage<BaseDataSourceVo> returnMessage = baseDataSourceService
				.getBdsVoData(dictCode, new HashMap<String, String[]>(), null);
		if(!returnMessage.isSuccess()){
			return new ReturnMessage<RoleUser>();
		}
		BaseDataSourceVo baseDataSourceVo = returnMessage.getReturnObject();
		if(null == baseDataSourceVo){
			return new ReturnMessage<RoleUser>();
		}
		Map<String, List<Object>> baseDataSourceMap = baseDataSourceVo
				.getColumnsMap();
		if(DataValidater.isMapEmpty(baseDataSourceMap)){
			return new ReturnMessage<RoleUser>();
		}
		List<Object> baseDataSourceList = baseDataSourceMap
				.get(BdsConstant.FIXED_COL_NAME_CODE);
		
		if(!deleteRoleUsers(role).isSuccess()){
			return new ReturnMessage<RoleUser>();
		}
		
		return addRoleUsers(role, baseDataSourceList);
	}

    @Transactional
	private ReturnMessage<RoleUser> deleteRoleUsers(Role role){

//		RoleUser roleUser = new RoleUser();
//		roleUser.setRoleId(role.getRoleId());
		
//		List<RoleUser> roleUsers = queryList(roleUser);
//		ReturnMessage<RoleUser> returnMessage = deleteList(roleUsers);

		ReturnMessage<RoleUser> returnMessage = new ReturnMessage<RoleUser>();
		if (roleUserDao.deleteRoleUsers(role) >= 0) {
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		}
		
		return returnMessage;
		
	}
	
    @Transactional
	private ReturnMessage<RoleUser> addRoleUsers(Role role,
			List<Object> userCodes) {
		
		if(DataValidater.isCollectionEmpty(userCodes)){
			return new ReturnMessage<RoleUser>();
		}
		List<RoleUser> roleUsers = getRoleUsers(role, userCodes);
		if(DataValidater.isCollectionEmpty(roleUsers)){
			return new ReturnMessage<RoleUser>();
		}
		
		ReturnMessage<RoleUser> returnMessage = addOrUpdateList(roleUsers);
		
		return returnMessage;
	}

    @Transactional
	private List<RoleUser> getRoleUsers(Role role, List<Object> userCodes) {
		
		List<RoleUser> roleUsers = new ArrayList<RoleUser>();
//		for(Object userCodeObj: userCodes){
//			UserProfile user = getUserProfile((String)userCodeObj);
//			if(null == user){
//				continue;
//			}
//			RoleUser roleUser = new RoleUser();
//			roleUser.setRoleId(role.getRoleId());
//			roleUser.setUserProfileId(user.getUserProfileId());
//			roleUsers.add(roleUser);
//		}
		
//		Map<String, UserProfile> userProfileMap = getAllUserProfiles();
		
		for(Object userCodeObj: userCodes){
			String userPorfileId = roleUserDao.findUserProfileIdByEmpNumber((String) userCodeObj);
			if(null == userPorfileId){
				continue;
			}
			RoleUser roleUser = new RoleUser();
			roleUser.setRoleId(role.getRoleId());
			roleUser.setUserProfileId(userPorfileId);
			roleUser.setIsLocalRight(SYN_ROLE_USER);
			roleUsers.add(roleUser);
		}
		return roleUsers;
	}
	
/*	private UserProfile getUserProfile(String userCode) {
		
		UserProfile user = new UserProfile();
		user.setEmpNumber(userCode);
		user = (UserProfile) querySingle(user);
		
		return user;
	}*/
	
	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.afw.services.RoleUserService#getRoleList(mikeheke.tadpole.frm.afw.entity.UserProfile, mikeheke.tadpole.frm.afw.entity.Application)
	 */
	@Override
	public ReturnMessage<Role> getRoleList(UserProfile userProfile, Application application) {

		ReturnMessage<Role> returnMessage = new ReturnMessage<Role>();
		
		List<Role> rolesRet = new ArrayList<Role>();
		
		try{
			List<Role> roles = roleDao.getRoleList(userProfile);
			for(Role role: roles){
				if(role.getApplication().getApplicationId().equals(application.getApplicationId())){
					rolesRet.add(role);
				}
			}
		} catch (SQLException e) {
			throw new AmwaySysException(e);
		}
		
		returnMessage.setReturnObjects(rolesRet);
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		
		return returnMessage;
	}
	
	@Override
	public ReturnMessage<Role> getRoleAndDefaultList(UserProfile userProfile, Application application) {

		ReturnMessage<Role> returnMessage = new ReturnMessage<Role>();
		
		List<Role> rolesRet = new ArrayList<Role>();
		
		List<Role> roles = getRoleList(userProfile, application).getReturnObjects();
		if(null != roles){
			rolesRet.addAll(roles);
		}
		Role dftRole = getDefaultRole(application);
		if(null != dftRole){
			rolesRet.add(dftRole);
		}
		
		returnMessage.setReturnObjects(rolesRet);
		returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		
		return returnMessage;
	}
	
	private Role getDefaultRole(Application application){
		
		Role role = new Role();
		role.setRoleCode(application.getApplicationCode()+AfwConstant.DEFAULT_USER_ROLE_AFT);
		return (Role) querySingle(role);
	}

	public void setRoleUserDao(IRoleUserDao roleUserDao) {
		this.roleUserDao = roleUserDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setBaseDataSourceService(BaseDataSourceService baseDataSourceService) {
		this.baseDataSourceService = baseDataSourceService;
	}
	
/*	private Map<String, UserProfile> getAllUserProfiles() {
		UserProfile userProfile = new UserProfile();
		List<UserProfile> userProfiles = queryList(userProfile);
		
		Map<String, UserProfile> userProfileMap = new HashMap<String, UserProfile>();
		for (UserProfile u: userProfiles) {
			userProfileMap.put(u.getEmpNumber(), u);
		}
		
		return userProfileMap;
	}*/
	
}
