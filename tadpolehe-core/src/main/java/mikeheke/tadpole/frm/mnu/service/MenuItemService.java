package mikeheke.tadpole.frm.mnu.service;

import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Module;
import mikeheke.tadpole.frm.base.service.BaseService;
import mikeheke.tadpole.frm.mnu.vo.MenuItem;
import mikeheke.tadpole.frm.mnu.vo.TreeNode;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：菜单Service接口
 */
public interface MenuItemService extends BaseService{
	
	 /**
     * Declare：从数据库取得节点结果集
     *
	 * @param  menuItem  菜单项
     * @return List<TreeNode> 节点结果集
     */
	List<TreeNode> getMenusFromDB(MenuItem menuItem);
	
	 /**
     * Declare：生成菜单节点集
     *
	 * @param  modules  模块结果集
     * @return List<TreeNode> 节点结果集
     */
	List<TreeNode> generateTreeNodes(List<Module> modules);
}
