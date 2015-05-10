package mikeheke.tadpole.frm.afw.dao;

import java.sql.SQLException;
import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Application;
import mikeheke.tadpole.frm.afw.entity.UserProfile;
import mikeheke.tadpole.frm.base.dao.IBaseDao;

/**
 * Created by IntelliJ IDEA.
 * 
 * Date: 2011-4-6
 * Time: 15:52:24
 * Declare：MstbApplicationDao表对应的DAO，适用于操作MstbApplicationDao表，包括SELECT,CREATE,UPDATE,DELETE各种操作
 */
public interface IApplicationDao extends IBaseDao<Application, String> {
    
	List<Application> getApplicationListJDBC(UserProfile userProfile)
			throws SQLException;
	
	List<Application> getApplicationListJDBC()
			throws SQLException;

	//获得应用列表
    List<Application> getApplicationList() throws SQLException;
    
    List<Application> getApplications(Application application) throws SQLException;
}
