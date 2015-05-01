package mikeheke.tadpole.frm.mnu.service.impl;

import java.util.ArrayList;
import java.util.List;

import mikeheke.tadpole.frm.afw.entity.Module;
import mikeheke.tadpole.frm.afw.util.AfwConstant;
import mikeheke.tadpole.frm.base.service.impl.BaseImpl;
import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.mnu.dao.IMenuItemDao;
import mikeheke.tadpole.frm.mnu.service.MenuItemService;
import mikeheke.tadpole.frm.mnu.vo.MenuItem;
import mikeheke.tadpole.frm.mnu.vo.NodeType;
import mikeheke.tadpole.frm.mnu.vo.TreeNode;

/**
 * 
 * @author lenovo
 *
 */
public class MenuItemImpl extends BaseImpl implements MenuItemService {
	
	private IMenuItemDao menuItemDao;

	public void setMenuItemDao(IMenuItemDao menuItemDao) {
		this.menuItemDao = menuItemDao;
	}


	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.mnu.service.MenuItemService#getMenusFromDB(mikeheke.tadpole.frm.mnu.dto.MenuItem)
	 */
	@Override
	public List<TreeNode> getMenusFromDB(MenuItem menuItem) {
		List<TreeNode> treeNodes = null;
		
		List<Module> modules = this.getModulesByMenuItem(menuItem);
		
		treeNodes = generateTreeNodes(modules);
		
		return treeNodes;
	}

	private List<Module> getModulesByMenuItem(MenuItem menuItem){
		
		List<Module> modules = null;
		String type = menuItem.getType();

		if(type.equals(NodeType.BASENODE.toString())){
			modules = menuItemDao.getBaseModules();
		}else if(type.equals(NodeType.MODDLENODE.toString())){
			modules = menuItemDao.getMidModules();
		}else if(type.equals(NodeType.LEAFNODE.toString())){
			modules = menuItemDao.getLeafModules();
		}else if(type.equals(NodeType.ALLNODE.toString())){
			modules = menuItemDao.getAllModules();
		}else{
			modules = new ArrayList<Module>();
		}
		
		return modules;
	}

	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.mnu.service.MenuItemService#generateTreeNodes(java.util.List)
	 */
	@Override
	public List<TreeNode> generateTreeNodes(List<Module> modules) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for(Module module: modules){
			TreeNode treeNode = new TreeNode();
			treeNode.setNodeNo(module.getModuleCode());
			treeNode.setNodeName(module.getModuleName());
			TreeNode pTreeNode = new TreeNode();
			pTreeNode.setNodeNo(module.getParentModule().getModuleCode());
			treeNode.setpTreeNode(pTreeNode);
			treeNode.setTarget(module.getTarget());
			String link = module.getLink();
			if(DataValidater.isStrEmpty(link)){
				link = AfwConstant.LINK_DEFAULT;
			}
			if(!AfwConstant.LINK_DEFAULT.equals(link)
					&& !link.toLowerCase().startsWith(AfwConstant.LINK_HTTP)){
				//add by hyc 对于应用开发员角色专用功能的特殊设定
				if(link.indexOf("$")!=-1){
					link="/sysManage"+link.substring(1);
				}
				else{
					StringBuffer linkTmp = new StringBuffer();
					linkTmp.append(AfwConstant.UNIX_SEP).append(module.getApplication().getContext());
					linkTmp.append(AfwConstant.UNIX_SEP).append(link);
					link = linkTmp.toString();
				}

			}
			treeNode.setLink(link);
			treeNode.setIco(module.getIco());
			
			treeNodes.add(treeNode);
		}
		
		return treeNodes;
	}


}
