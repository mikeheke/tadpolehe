package com.amway.frm.afw.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amway.frm.afw.dao.IApplicationDao;
import com.amway.frm.afw.entity.Application;
import com.amway.frm.afw.entity.UserProfile;
import com.amway.frm.base.dao.impl.BaseDao;
import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataConverter;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.base.util.JDBCHelper;

/**
 * Created by IntelliJ IDEA.
 * @author huangweijin
 * Date: 2011-4-6
 * Time: 15:52:42
 * Declare：MstbApplicationDao表对应的DAO，适用于操作MstbApplicationDao表，包括SELECT,CREATE,UPDATE,DELETE各种操作
 */
public class ApplicationDao extends BaseDao<Application, Long> implements IApplicationDao {

	/**
     * Declare：获得应用列表
     *
     * @param
     * @return List
     * @throws java.sql.SQLException
     */
    public List<Application> getApplicationList() throws SQLException {
        final String jql = "from Application order by applicationId";
        return this.getResultList(jql);
    }
    
	/* (non-Javadoc)
	 * @see com.amway.frm.afw.dao.IApplicationDao#getApplicationList(com.amway.frm.afw.entity.UserProfile)
	 */
	@Override
	public List<Application> getApplicationListJDBC(UserProfile userProfile)
			throws SQLException {
		
		List<Application> applications = new ArrayList<Application>();
		
		JDBCHelper jdbcHelper = new JDBCHelper(getDataSource());
		StringBuffer sql = new StringBuffer();
		final String sql1 = "SELECT DISTINCT a.APPLICATION_ID, a.APPLICATION_CODE, a.APPLICATION_NAME,a.ORDER_NO ";
		sql.append(sql1);
		final String sql2 = " FROM MSTB_APPLICATION a ";
		sql.append(sql2);
		List<Object> params = new ArrayList<Object>();
		if(userProfile.getUserProfileId() != null){
			final String sql3 = " INNER JOIN MSTB_ROLE r ON a.APPLICATION_ID=r.APPLICATION_ID ";
			sql.append(sql3);
			final String sql4 = " INNER JOIN MSTB_ROLE_USER u ON r.ROLE_ID=u.ROLE_ID ";
			sql.append(sql4);
			final String sql5 = " AND USERPROFILE_ID=? ";
			sql.append(sql5);
			params.add(userProfile.getUserProfileId());
		}
		final String sql6 = " WHERE a.RECORD_STATE=1 ";
		sql.append(sql6);
		final String sql7 = " ORDER BY a.ORDER_NO ";
		sql.append(sql7);
		try{
			boolean result = jdbcHelper.getFirstDocument(sql.toString(), params.toArray());
			while(result){
				Application application = getApplication(jdbcHelper);
				applications.add(application);
				result = jdbcHelper.getNextDocument();
			}
		}finally{
			jdbcHelper.closeAll();
		}
		
		return applications;
	}
	
	/* (non-Javadoc)
	 * @see com.amway.frm.afw.dao.IApplicationDao#getApplicationList(com.amway.frm.afw.entity.UserProfile)
	 */
	@Override
	public List<Application> getApplicationListJDBC()
			throws SQLException {
		
		return getApplicationListJDBC(new UserProfile());
	}

	private Application getApplication(JDBCHelper jdbcHelper) throws SQLException{
		
		Application application = new Application();
		
		final String APPLICATION_ID = "APPLICATION_ID";
		long applicationId = jdbcHelper.getItemLongValue(APPLICATION_ID);
		final String APPLICATION_CODE = "APPLICATION_CODE";
		String applicationCode = jdbcHelper.getItemTrueValue(APPLICATION_CODE);
		final String APPLICATION_NAME = "APPLICATION_NAME";
		String applicationName = jdbcHelper.getItemTrueValue(APPLICATION_NAME);
		
		application.setApplicationId(applicationId);
		application.setApplicationCode(applicationCode);
		application.setApplicationName(applicationName);
		application.setCode(DataConverter.LongToString(applicationId));
		application.setDisplayname(applicationName);
		
		return application;
	}

	/* (non-Javadoc)
	 * @see com.amway.frm.afw.dao.IApplicationDao#getApplication(com.amway.frm.afw.entity.Application)
	 */
	@Override
	public List<Application> getApplications(Application application) throws SQLException{
		
		String applicationName = application.getApplicationName();
		String applicationCode = application.getApplicationCode();
		Integer state = application.getState();
		
		StringBuffer jql = new StringBuffer();
		final String jql1 = " FROM ";
		final String jql2 = " a ";
		jql.append(jql1).append(application.getClass().getSimpleName()).append(jql2);
		final String jql3 = " WHERE recordState=1 ";
		jql.append(jql3);
		if(!DataValidater.isStrEmpty(applicationName)){
			final String jql4 = " AND a.applicationName LIKE '%";
			final String jql5 = "%'";
			jql.append(jql4).append(applicationName).append(jql5);
		}
		if(!DataValidater.isStrEmpty(applicationCode)){
			final String jql6 = " AND applicationCode LIKE '%";
			final String jql7 = "%'";
			jql.append(jql6).append(applicationCode).append(jql7);
		}
		if(null != state){
			final String jql8 = " AND state = ";
			jql.append(jql8).append(state).append(AppConstant.EMPTY_STR);
		}

		return getResultList(jql.toString());
	}
}
