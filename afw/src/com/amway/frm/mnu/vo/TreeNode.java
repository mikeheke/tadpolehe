/**
 * 
 */
package com.amway.frm.mnu.vo;

import java.util.ArrayList;
import java.util.List;

import com.amway.frm.base.util.DataValidater;
import com.amway.frm.mnu.util.MnuConstant;
import com.amway.frm.mnu.web.tag.MenuTag;

/**
 * 树节点，封装对象
 * 
 *
 * 2011-2-22 下午02:47:38
 */
public class TreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3996358073777317489L;

	//节点编号
	private String nodeNo;
	
	//父节点编号
	private TreeNode pTreeNode;
	
	private TreeNode pOldTreeNode;
	
	//子节点结果集
	private List<TreeNode> cTreeNodes = new ArrayList<TreeNode>();
	
	//节点描述
	private String nodeName;
	
	//联结URL
	private String link;
	
	//展示目标
	private String target;
	
	//图标
	private String ico;
	
	//节点类型
	//private NodeType nodeType;

	public String getNodeNo() {
		return nodeNo;
	}

	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}

	public TreeNode getpTreeNode() {
		return pTreeNode;
	}

	public void setpTreeNode(TreeNode pTreeNode) {
		this.pTreeNode = pTreeNode;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTarget() {
		String target = MnuConstant.EMPTY_STR;
		if(this.cTreeNodes.size()==0){
			if(DataValidater.isStrEmpty(this.target)){
				target = MenuTag.MAIN_FRAME;
			}else{
				target = this.target;
			}
		}else{
			target = MenuTag.LEFT_FRAME;
		}
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	public NodeType getNodeType() {
		
		if(this.nodeNo.equals(pTreeNode.nodeNo)){
			return NodeType.BASENODE;
		}else if(cTreeNodes.size() != 0){
			return NodeType.MODDLENODE;
		}else{
			return NodeType.LEAFNODE;
		}
	}
	
	public List<TreeNode> getcTreeNodes() {
		return cTreeNodes;
	}

	public void setcTreeNodes(List<TreeNode> cTreeNodes) {
		this.cTreeNodes = cTreeNodes;
	}

	public TreeNode getpOldTreeNode() {
		return pOldTreeNode;
	}

	public void setpOldTreeNode(TreeNode pOldTreeNode) {
		this.pOldTreeNode = pOldTreeNode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeNo == null) ? 0 : nodeNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (DataValidater.isFalse(getClass().equals(obj.getClass()))){
			return false;
		}
		TreeNode other = (TreeNode) obj;
		if (nodeNo == null) {
			if (other.nodeNo != null){
				return false;
			}
		} else if (!nodeNo.equals(other.nodeNo)){
			return false;
		}
		return true;
	}
}
