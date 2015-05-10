/**
 * 
 */
package mikeheke.tadpole.frm.mnu.web.tag;

import java.util.ArrayList;
import java.util.List;

import mikeheke.tadpole.frm.base.util.DataValidater;
import mikeheke.tadpole.frm.mnu.vo.NodeType;
import mikeheke.tadpole.frm.mnu.vo.TreeNode;

/**
 * 
 *
 * 2011-7-19 上午08:31:46
 */
public abstract class AbstractTreeTag extends MenuTag{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1728946334889995182L;

	//所有节点
	private List<TreeNode> allTreeNodes;
	
	/* (non-Javadoc)
	 * @see mikeheke.tadpole.frm.mnu.web.tag.MenuTag#generateMenuControl(java.util.List)
	 */
	@Override
	public StringBuffer generateMenuControl(List<TreeNode> treeNodes) {
		
		StringBuffer tree = new StringBuffer();
		
		String type = this.getType();
		if(type.equals(NodeType.BASENODE.toString())){
			this.addTopNodesToTree(tree, treeNodes);
		}else{
			this.addTreeNodesToTree(tree, treeNodes);
			this.addTreeNodeJS(tree);
		}
		
		return tree;
	}

	protected void addTreeNodesToTree(StringBuffer tree, List<TreeNode> treeNodes){
		
		allTreeNodes = new ArrayList<TreeNode>();
		
		allTreeNodes.addAll(treeNodes);
		
		this.setPTreeNodes(treeNodes);
		this.setCTreeNodes(treeNodes);
		
		List<TreeNode> baseTreeNodes = this.getBaseTreeNodes(treeNodes);
		
		this.addTreePre(tree);
		
		addCNodesToPNodes(tree, baseTreeNodes);
		
		this.addTreeAft(tree);
	}
	
	private boolean filterBaseTreeNode(TreeNode treeNode){
		
		String module = this.getModule();
		if(DataValidater.isStrEmpty(module)){
			return false;
		}
		
		if(treeNode.getpOldTreeNode().getNodeNo().equals(module)){
			return false;
		}
		
		return true;
	}
	
	 /**
     * Declare：生成根节点
     *
	 * @param  treeNodes 所有节点
     * @return List<TreeNode> 所有根节点
     */
	private List<TreeNode> getBaseTreeNodes(List<TreeNode> treeNodes){
		
		List<TreeNode> treeNodesRet = new ArrayList<TreeNode>();
		for(TreeNode treeNode: treeNodes){
			if(treeNode.getNodeType().ordinal() != NodeType.BASENODE.ordinal()){
				continue;
			}
			if(filterBaseTreeNode(treeNode)){
				continue;
			}
			treeNodesRet.add(treeNode);
		}
		
		return treeNodesRet;
	}

	 /**
     * Declare：设置父节点
     *
	 * @param  treeNodes 所有节点
     * @return void
     */
	private void setPTreeNodes(List<TreeNode> treeNodes){
		
		for(TreeNode treeNode: treeNodes){
			TreeNode pTreeNode = this.getPTreeNode(treeNode);
			treeNode.setpOldTreeNode(treeNode.getpTreeNode());
			treeNode.setpTreeNode(pTreeNode);
		}
	}

	 /**
     * Declare：取得父节点
     *
	 * @param  treeNode 节点
     * @return TreeNode 节点
     */
	private TreeNode getPTreeNode(TreeNode treeNode){
		
		TreeNode pTreeNodeRet = null;
		TreeNode pTreeNode = treeNode.getpTreeNode();
		for(TreeNode treeNode2: allTreeNodes){
			if(pTreeNode.getNodeNo().equals(treeNode2.getNodeNo())){
				pTreeNodeRet = treeNode2;
				break;
			}
		}
		
		if(null == pTreeNodeRet){
			pTreeNodeRet = treeNode;
			
		}
		
		return pTreeNodeRet;
	}

	 /**
     * Declare：设置子节点
     *
	 * @param  treeNodes 所有节点
     * @return void
     */
	private void setCTreeNodes(List<TreeNode> treeNodes){
		
		for(TreeNode treeNode: treeNodes){
			List<TreeNode> cTreeNodes = this.getCTreeNodes(treeNode);
			treeNode.setcTreeNodes(cTreeNodes);
		}
	}

	 /**
     * Declare：取所有子节点
     *
	 * @param  treeNodes 节点
     * @return List<TreeNode> 所有子节点
     */
	private List<TreeNode> getCTreeNodes(TreeNode treeNode){
		
		List<TreeNode> cTreeNodeRet = new ArrayList<TreeNode>();
		for(TreeNode treeNode2: allTreeNodes){
			TreeNode pTreeNode2 = treeNode2.getpTreeNode();
			if(treeNode.getNodeNo().equals(pTreeNode2.getNodeNo())
					&& !treeNode.getNodeNo().equals(treeNode2.getNodeNo())){
				cTreeNodeRet.add(treeNode2);
			}
		}
		
		return cTreeNodeRet;
	}

	 /**
     * Declare：添加树节点
     *
	 * @param  tree 树
	 * @param  baseTreeNodes 根节点
     * @return void
     */
	private void addCNodesToPNodes(StringBuffer tree, List<TreeNode> baseTreeNodes){
		
		for(TreeNode treeNode: baseTreeNodes){
			this.appendTreeNodePre(tree, treeNode);
			List<TreeNode> childTreeNodes = treeNode.getcTreeNodes();
			if(childTreeNodes.size() > 0){
				this.addCNodesToPNodes(tree, childTreeNodes);
			}
			this.appendTreeNodeAft(tree, treeNode);
		}
	}
	
	/**
     * Declare：添加节点前缀
     *
	 * @param  tree 树
	 * @param  treeNode 节点
     * @return void
     */
	private void appendTreeNodePre(StringBuffer tree, TreeNode treeNode) {
		
		String nodeType = treeNode.getNodeType().toString();
		if(nodeType.equals(NodeType.BASENODE.toString())){
			this.appendBaseNodePre(tree, treeNode);
		}else if(nodeType.equals(NodeType.MODDLENODE.toString())){
			this.appendMiddleNodePre(tree, treeNode);
		}else{
			this.appendLeafNodePre(tree, treeNode);
		}
		
	}
	
	 /**
     * Declare：添加节点后缀
     *
	 * @param  tree 树
	 * @param  treeNode 节点
     * @return void
     */
	protected void appendTreeNodeAft(StringBuffer tree, TreeNode treeNode) {
		
		NodeType nodeType = treeNode.getNodeType();
		if(nodeType.ordinal() == NodeType.BASENODE.ordinal()){
			this.appendBaseNodeAft(tree, treeNode);
		}else if(nodeType.ordinal() == NodeType.MODDLENODE.ordinal()){
			this.appendMiddleNodeAft(tree, treeNode);
		}else{
			this.appendLeafNodeAft(tree, treeNode);
		}
		
	}
	
	/**
	 * 添回顶级节点，用于一级菜单和二三级菜单分开情况
	 * @param tree
	 * @param treeNodes
	 */
	protected void addTopNodesToTree(StringBuffer tree, List<TreeNode> treeNodes){}
	
	/**
	 * 添加JS
	 * @param tree
	 */
	protected void addTreeNodeJS(StringBuffer tree){}
	
	/**
	 * 添加树前缀，子类实现
	 * @param tree
	 */
	public abstract void addTreePre(StringBuffer tree);

	/**
	 * 添加树后缀，子类实现
	 * @param tree
	 */
	public abstract void addTreeAft(StringBuffer tree);
	
	/**
	 * 添加根节点前缀，子类实现
	 * @param tree
	 * @param treeNode
	 */
	public abstract void appendBaseNodePre(StringBuffer tree, TreeNode treeNode);
	
	/**
	 * 添加根节点后缀，子类实现
	 * @param tree
	 * @param treeNode
	 */
	public abstract void appendBaseNodeAft(StringBuffer tree, TreeNode treeNode);
	
	/**
	 * 添加中间节点前缀，子类实现
	 * @param tree
	 * @param treeNode
	 */
	public abstract void appendMiddleNodePre(StringBuffer tree, TreeNode treeNode);
	
	/**
	 * 添加中间节点后缀，子类实现
	 * @param tree
	 * @param treeNode
	 */
	public abstract void appendMiddleNodeAft(StringBuffer tree, TreeNode treeNode);
	
	/**
	 * 添加叶子节点前缀，子类实现
	 * @param tree
	 * @param treeNode
	 */
	public abstract void appendLeafNodePre(StringBuffer tree, TreeNode treeNode);
	
	/**
	 * 添加叶子节点后缀，子类实现
	 * @param tree
	 * @param treeNode
	 */
	public abstract void appendLeafNodeAft(StringBuffer tree, TreeNode treeNode);
}
