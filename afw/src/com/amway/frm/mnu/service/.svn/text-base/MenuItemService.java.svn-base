package com.amway.frm.mnu.service;

import java.util.List;

import com.amway.frm.afw.entity.Module;
import com.amway.frm.base.service.BaseService;
import com.amway.frm.mnu.vo.MenuItem;
import com.amway.frm.mnu.vo.TreeNode;

/**
 * Created by MyElipse
 * @author huangweijin
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
