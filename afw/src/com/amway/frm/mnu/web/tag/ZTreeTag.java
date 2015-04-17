package com.amway.frm.mnu.web.tag;

import java.util.ArrayList;
import java.util.List;

import com.amway.frm.base.util.AppConstant;
import com.amway.frm.base.util.DataValidater;
import com.amway.frm.mnu.vo.NodeType;
import com.amway.frm.mnu.vo.TreeNode;

public class ZTreeTag extends MenuTag{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8615329994580487615L;
	
	//根节点是否展开
	private boolean bExpanded;
	
	private String iconSkin;
	
	//所有节点
	private List<TreeNode> allTreeNodes;
	
	
	@Override
	public StringBuffer generateMenuControl(List<TreeNode> treeNodes) {		
		StringBuffer tree=new StringBuffer();
		allTreeNodes = new ArrayList<TreeNode>();		
		allTreeNodes.addAll(treeNodes);
		this.setPTreeNodes(treeNodes);
		this.setCTreeNodes(treeNodes);		
		tree=getTreeNodes(treeNodes, tree);
		return tree;
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
			if(pTreeNode.getNodeType().ordinal() == NodeType.BASENODE.ordinal()){
				TreeNode rootNode=new TreeNode();
				rootNode.setNodeNo("0");
				treeNode.setpTreeNode(rootNode);
			}
		}
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

	private StringBuffer getTreeNodes(List<TreeNode> treeNodes,StringBuffer tree){
		int length=treeNodes.size();
		final String zNodes=" var zNodes=[";
		tree.append(zNodes);
		for(int i=0;i<length;i++){
			TreeNode node=treeNodes.get(i);
			String id=node.getNodeNo();
			String pId=node.getpTreeNode().getNodeNo();
			String name=node.getNodeName();
			if(name.indexOf("&nbsp;")!=-1){
				name = name.replaceAll("&nbsp;", " ");
			}
			String url=node.getLink();
			String target=node.getTarget();
			tree.append(AppConstant.LEFT_D_KUO);
			tree.append("id").append(AppConstant.MAO_SIGN).append(AppConstant.QUO_D_SIGN).append(id).append(AppConstant.QUO_D_SIGN).append(AppConstant.DOU_SIGN);
			tree.append("pId").append(AppConstant.MAO_SIGN).append(AppConstant.QUO_D_SIGN).append(pId).append(AppConstant.QUO_D_SIGN).append(AppConstant.DOU_SIGN);
			tree.append("name").append(AppConstant.MAO_SIGN).append(AppConstant.QUO_D_SIGN).append(name).append(AppConstant.QUO_D_SIGN).append(AppConstant.DOU_SIGN);			
			tree.append("url").append(AppConstant.MAO_SIGN).append(AppConstant.QUO_D_SIGN).append(url).append(AppConstant.QUO_D_SIGN).append(AppConstant.DOU_SIGN);
			tree.append("target").append(AppConstant.MAO_SIGN).append(AppConstant.QUO_D_SIGN).append(target).append(AppConstant.QUO_D_SIGN);
			appendOpen(tree);
			appendBIcon(tree, node);
			tree.append(AppConstant.RIGHT_D_KUO);
			if(i < length-1){
				tree.append(AppConstant.DOU_SIGN);
			}
		}	
		tree.append("];");
		return tree;
	}
	
	
	private void appendOpen(StringBuffer tree){
		if(Boolean.valueOf(bExpanded)){
			tree.append(AppConstant.DOU_SIGN).append("open").append(AppConstant.MAO_SIGN).append(bExpanded);
		}		
	}
	
	private void appendBIcon(StringBuffer tree,TreeNode node){		
		if(!DataValidater.isObjNullOrStrEmpty(iconSkin) && isBaseNode(node)){
			//tree.append(AppConstant.DOU_SIGN).append("iconSkin").append(AppConstant.MAO_SIGN).append(AppConstant.QUO_D_SIGN).append(iconSkin).append(AppConstant.QUO_D_SIGN);
			tree.append(AppConstant.DOU_SIGN).append("isParent").append(AppConstant.MAO_SIGN).append(true);
		}
	}
	
	private boolean isBaseNode(TreeNode node){
		return node.getpTreeNode().getNodeNo().equals("0");
	}

	public boolean isbExpanded() {
		return bExpanded;
	}

	public void setbExpanded(boolean bExpanded) {
		this.bExpanded = bExpanded;
	}
	
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	
	
}
