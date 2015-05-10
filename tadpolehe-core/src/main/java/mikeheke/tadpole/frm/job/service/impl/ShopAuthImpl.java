package mikeheke.tadpole.frm.job.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mikeheke.tadpole.frm.afw.entity.Department;
import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.util.AppConstant;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.base.vo.ReturnMessage;
import mikeheke.tadpole.frm.job.dao.impl.ShopAuthDao;
import mikeheke.tadpole.frm.job.entity.ShopAuth;
import mikeheke.tadpole.frm.job.entity.UserAuth;
import mikeheke.tadpole.frm.job.service.ShopAuthService;
import mikeheke.tadpole.frm.job.util.ShopAuthConstant;
import mikeheke.tadpole.frm.job.vo.ShopPropertyVo;

import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Xie Jinnan
 *
 */
public class ShopAuthImpl extends BaseImpl implements ShopAuthService {

	private ShopAuthDao shopAuthDao;

	public ShopAuthDao getShopAuthDao() {
		return shopAuthDao;
	}

	public void setShopAuthDao(ShopAuthDao shopAuthDao) {
		this.shopAuthDao = shopAuthDao;
	}
	
	@Transactional
	private ReturnMessage<ShopAuth> enableAuth(List<ShopAuth> shopAuths, List<ShopPropertyVo> shopList) {
		final String STR_MINUS = "-";

		Set<String> notDefaultShopSet = new HashSet<String>();
		List<ShopAuth> defaultShopAuths = new ArrayList<ShopAuth>();
		List<UserAuth> userAuthList = new ArrayList<UserAuth>();
		
		for (ShopAuth shopAuth : shopAuths) {
			if (!ShopAuthConstant.DEFAULT_CODE.equalsIgnoreCase(shopAuth.getShopCode())) {
				notDefaultShopSet.add(new StringBuffer(shopAuth.getShopCode()).append(STR_MINUS).append(shopAuth.getApplicationCode()).toString());
				ShopPropertyVo propertyVo = new ShopPropertyVo();
				for (ShopPropertyVo vo: shopList) {
					if (vo.getShopCode().equalsIgnoreCase(shopAuth.getShopCode())) {
						propertyVo = vo;
						break;
					}
				}
				
				if (null != propertyVo.getShopCode()) {
					userAuthList.addAll(getUserAuthList(shopAuth, propertyVo));
				}
			} else {
				defaultShopAuths.add(shopAuth);
			}
		}
		
		for (ShopAuth shopAuth: defaultShopAuths) {
			String applicationCode = shopAuth.getApplicationCode();
			for (ShopPropertyVo vo: shopList) {
				if (notDefaultShopSet.contains(new StringBuffer(vo.getShopCode()).append(STR_MINUS).append(applicationCode).toString())) {
					continue;
				}
				userAuthList.addAll(getUserAuthList(shopAuth, vo));
			}
		}
		
		ReturnMessage returnMessage = new ReturnMessage();
		List<UserAuth> list;
		try {
			list = shopAuthDao.createList(userAuthList);
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		} catch (SQLException e) {
			returnMessage.setReturnCode(ReturnMessage.FAIL_CODE);
		}
		
		return returnMessage;
	}
	
	@Transactional(readOnly = true)
	private List<UserAuth> getUserAuthList(ShopAuth shopAuth, ShopPropertyVo vo) {
		final String ADMIN = "admin";
		final String G = "G";
		
		List<UserAuth> list = new ArrayList<UserAuth>();
		String userEmpNumber = ADMIN;
		Date date = new Date();
		
		if ((1 == shopAuth.getRight1ShopHead()) && isNotStrEmpOrNull(vo.getShopHead())) {
			UserAuth userAuth = new UserAuth();
			genCommonAttribute(userAuth, shopAuth, vo, userEmpNumber, date);
			userAuth.setRightCode(shopAuth.getRightCode1());
			userAuth.setEmpNumber(vo.getShopHead());
			if (isNotContain(list, userAuth)) {
				list.add(userAuth);
			}
		}
		if ((1 == shopAuth.getRight1ShopHead2()) && isNotStrEmpOrNull(vo.getShopHead2())) {
			UserAuth userAuth = new UserAuth();
			genCommonAttribute(userAuth, shopAuth, vo, userEmpNumber, date);
			userAuth.setRightCode(shopAuth.getRightCode1());
			userAuth.setEmpNumber(vo.getShopHead2());
			if (isNotContain(list, userAuth)) {
				list.add(userAuth);
			}
		}
		if (1 == shopAuth.getRight1ShopAssistant()) {
			UserProfile userProfile = new UserProfile();
			Department department = new Department();
			department.setUnitCode(vo.getUnitCode());
			userProfile.setDepartment(department);
			userProfile.setGradeCode(G);
			List<UserProfile> userList = queryList(userProfile);
			for (UserProfile profile: userList) {
				if (DataValidater.isStrEmptyOrNull(profile.getEmpNumber())) {
					continue;
				}
				UserAuth auth = new UserAuth();
				genCommonAttribute(auth, shopAuth, vo, userEmpNumber, date);
				auth.setRightCode(shopAuth.getRightCode1());
				auth.setEmpNumber(profile.getEmpNumber());
				if (isNotContain(list, auth)) {
					list.add(auth);
				}
			}
		}
		if ((1 == shopAuth.getRight1CityHead()) && isNotStrEmpOrNull(vo.getCityHead())) {
			UserAuth userAuth = new UserAuth();
			genCommonAttribute(userAuth, shopAuth, vo, userEmpNumber, date);
			userAuth.setRightCode(shopAuth.getRightCode1());
			userAuth.setEmpNumber(vo.getCityHead());
			if (isNotContain(list, userAuth)) {
				list.add(userAuth);
			}
		}
		if ((1 == shopAuth.getRight1TaxOperator()) && isNotStrEmpOrNull(vo.getTaxOperator())) {
			UserAuth userAuth = new UserAuth();
			genCommonAttribute(userAuth, shopAuth, vo, userEmpNumber, date);
			userAuth.setRightCode(shopAuth.getRightCode1());
			userAuth.setEmpNumber(vo.getTaxOperator());
			if (isNotContain(list, userAuth)) {
				list.add(userAuth);
			}
		}
		if (shopAuth.getRight1ManualArr() != null && shopAuth.getRight1ManualArr().size() > 0) {
			List<String> empNumbers = shopAuth.getRight1ManualArr();
			for (String empNumber: empNumbers) {
				if (DataValidater.isStrEmptyOrNull(empNumber)) {
					continue;
				}
				UserAuth auth = new UserAuth();
				genCommonAttribute(auth, shopAuth, vo, userEmpNumber, date);
				auth.setRightCode(shopAuth.getRightCode1());
				auth.setEmpNumber(empNumber);
				if (isNotContain(list, auth)) {
					list.add(auth);
				}
			}
		}
		
		// 加 submit 权限时，如果用户没有 read 权限，在添加 submit 权限的同时，也要添加 read 权限
		if ((1 == shopAuth.getRight2ShopHead()) && isNotStrEmpOrNull(vo.getShopHead())) {
			UserAuth userAuth = new UserAuth();
			genCommonAttribute(userAuth, shopAuth, vo, userEmpNumber, date);
			userAuth.setRightCode(shopAuth.getRightCode2());
			userAuth.setEmpNumber(vo.getShopHead());
			if (isNotContain(list, userAuth)) {
				list.add(userAuth);
			}
			
			UserAuth userAuth2 = new UserAuth();
			genCommonAttribute(userAuth2, shopAuth, vo, userEmpNumber, date);
			userAuth2.setRightCode(shopAuth.getRightCode1());
			userAuth2.setEmpNumber(vo.getShopHead());
			if (isNotContain(list, userAuth2)) {
				userAuth2.setUpdatedUserId(userEmpNumber);
				list.add(userAuth2);
			}
		}
		if ((1 == shopAuth.getRight2ShopHead2()) && isNotStrEmpOrNull(vo.getShopHead2())) {
			UserAuth userAuth = new UserAuth();
			genCommonAttribute(userAuth, shopAuth, vo, userEmpNumber, date);
			userAuth.setRightCode(shopAuth.getRightCode2());
			userAuth.setEmpNumber(vo.getShopHead2());
			if (isNotContain(list, userAuth)) {
				list.add(userAuth);
			}
			
			UserAuth userAuth2 = new UserAuth();
			genCommonAttribute(userAuth2, shopAuth, vo, userEmpNumber, date);
			userAuth2.setRightCode(shopAuth.getRightCode1());
			userAuth2.setEmpNumber(vo.getShopHead2());
			if (isNotContain(list, userAuth2)) {
				userAuth2.setUpdatedUserId(userEmpNumber);
				list.add(userAuth2);
			}
		}
		if (shopAuth.getRight2ManualArr() != null && shopAuth.getRight2ManualArr().size() > 0) {
			List<String> empNumbers = shopAuth.getRight2ManualArr();
			for (String empNumber: empNumbers) {
				if (DataValidater.isStrEmptyOrNull(empNumber)) {
					continue;
				}
				UserAuth auth = new UserAuth();
				genCommonAttribute(auth, shopAuth, vo, userEmpNumber, date);
				auth.setRightCode(shopAuth.getRightCode2());
				auth.setEmpNumber(empNumber);
				if (isNotContain(list, auth)) {
					list.add(auth);
				}
				
				UserAuth auth2 = new UserAuth();
				genCommonAttribute(auth2, shopAuth, vo, userEmpNumber, date);
				auth2.setRightCode(shopAuth.getRightCode1());
				auth2.setEmpNumber(empNumber);
				if (isNotContain(list, auth2)) {
					auth2.setUpdatedUserId(userEmpNumber);
					list.add(auth2);
				}
			}
		}
		
		if (shopAuth.getRight3ManualArr() != null && shopAuth.getRight3ManualArr().size() > 0) {
			List<String> empNumbers = shopAuth.getRight3ManualArr();
			for (String empNumber: empNumbers) {
				if (DataValidater.isStrEmptyOrNull(empNumber)) {
					continue;
				}
				UserAuth auth = new UserAuth();
				genCommonAttribute(auth, shopAuth, vo, userEmpNumber, date);
				auth.setRightCode(shopAuth.getRightCode3());
				auth.setEmpNumber(empNumber);
				if (isNotContain(list, auth)) {
					list.add(auth);
				}
			}
		}
		
		return list;
	}
	
	private boolean isNotStrEmpOrNull(String str) {
		return !DataValidater.isStrEmptyOrNull(str);
	}
	
	private boolean isNotContain(List<UserAuth> list, UserAuth ua) {
		return !list.contains(ua);
	}
	
	private void genCommonAttribute(UserAuth auth, ShopAuth shopAuth, ShopPropertyVo vo, String empNumber, Date date) {
		auth.setShopCode(vo.getShopCode());
		auth.setApplicationCode(shopAuth.getApplicationCode());
		auth.setRecordState(AppConstant.START);
		auth.setCreatedUserId(empNumber);
		auth.setCreatedTime(date);
	}
	
	@Transactional
	public ReturnMessage<ShopAuth> enableAuth() {
		try {
			final String DELETE_SQL = "delete from UserAuth";
			if (shopAuthDao.executeJpl(DELETE_SQL) < 0) {
				throw new SQLException();
			}
			
			List<ShopPropertyVo> shopList = shopAuthDao.findAllShopProps();
		
			ShopAuth shopAuth = new ShopAuth();
			shopAuth.setRecordState(AppConstant.START);
			shopAuth.setState(AppConstant.START);
			List<ShopAuth> shopAuths = queryList(shopAuth);
			return enableAuth(shopAuths, shopList);
		} catch (Exception e) {
			ReturnMessage<ShopAuth> returnMessage = new ReturnMessage<ShopAuth>();
			returnMessage.setReturnCode(ReturnMessage.FAIL_CODE);
			return returnMessage;
		}
	}
	
	public ReturnMessage copyAuths() {
		final String DELETE_SQL_1 = "delete from ";
		final String DELETE_SQL_2 = ".mstb_sa_user_auth";
		final String INSERT_SQL_1 = "insert into ";
		final String INSERT_SQL_2 = ".mstb_sa_user_auth select * from ";
		final String INSERT_SQL_3 = ".mstb_sa_user_auth_tmp";
		
		ReturnMessage returnMessage = new ReturnMessage();
		try {
			String deleteSql = DELETE_SQL_1 + AppConstant.APP_DEAULT_SCHEMA + DELETE_SQL_2;
			String insertSql = INSERT_SQL_1 + AppConstant.APP_DEAULT_SCHEMA + INSERT_SQL_2 + AppConstant.APP_DEAULT_SCHEMA + INSERT_SQL_3;
			if (shopAuthDao.executeUpdate(deleteSql) >= 0) {
				shopAuthDao.executeUpdate(insertSql);
			}
			returnMessage.setReturnCode(ReturnMessage.SUCCESS_CODE);
		} catch (Exception e) {
			returnMessage.setReturnCode(ReturnMessage.FAIL_CODE);
		}
		return returnMessage;
	}
}
