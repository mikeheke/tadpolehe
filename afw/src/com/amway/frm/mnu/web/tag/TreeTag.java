package com.amway.frm.mnu.web.tag;

import java.util.List;

import com.amway.frm.mnu.util.MnuConstant;
import com.amway.frm.mnu.vo.TreeNode;

/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：树风格菜单Tag类
 */
public class TreeTag extends OutlookTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1295040714295492737L;
	
	@Override
	protected void addTopNodesToTree(StringBuffer tree, List<TreeNode> treeNodes){
		tree.append(MnuConstant.TAG_UL_BEGIN);
		final String A_HREF = "<a href='left.jsp?module=";
		final String TARGET = "' target='";
		final String IMG = "<img src='";
		for(TreeNode treeNode: treeNodes){
			tree.append(MnuConstant.TAG_LI_BEGIN);
			tree.append(A_HREF).append(treeNode.getNodeNo())
				.append(TARGET).append(MenuTag.LEFT_FRAME)
				.append(MnuConstant.QUO_S_SIGN).append(MnuConstant.RIGHT_J_KUO);
			if(treeNode.getIco()!=null){
				tree.append(IMG).append(this.getImgPath())
					.append(MnuConstant.UNIX_SEP).append(treeNode.getIco())
					.append(MnuConstant.QUO_S_SIGN).append(MnuConstant.TAG_END);
			}
			tree.append(treeNode.getNodeName());
			tree.append(MnuConstant.TAG_A_END);
			tree.append(MnuConstant.TAG_LI_END);
		}
		tree.append(MnuConstant.TAG_UL_END);
	}
	
	 /**
     * Declare：添加树前缀
     * @param tree
     *
     */
	@Override
	public void addTreePre(StringBuffer tree){
		final String DIV_BEGIN = "<div id=\"ind_left\">\n";
		tree.append(DIV_BEGIN);
	}
	
	 /**
     * Declare：添加树后缀
     * @param tree
     *
     */
	@Override
	public void addTreeAft(StringBuffer tree){
		tree.append(MnuConstant.TAG_DIV_END).append(MnuConstant.ENTER_SIGN);
	}

	 /**
     * Declare：添加根节点前缀
     *
	 * @param  tree 树
	 * @param  treeNode 节点
     * @return void
     */
	@Override
	public void appendBaseNodePre(StringBuffer tree, TreeNode treeNode){
		
		final String DIV_CLASS = "<div class='";
		tree.append(DIV_CLASS).append(this.getClassName());
		final String ONCLICK = "' onclick='changeView(this.id)' id='submenu";
		tree.append(ONCLICK).append(treeNode.getNodeNo())
			.append(MnuConstant.QUO_S_SIGN).append(MnuConstant.RIGHT_J_KUO).append(MnuConstant.ENTER_SIGN);
		final String A_HREF = "<a href='";
		tree.append(A_HREF).append(treeNode.getLink());
		final String TARGET = "' target='";
		tree.append(TARGET).append(treeNode.getTarget());
		final String A_CLASS = "' class='black s14 b' >";
		tree.append(A_CLASS);
		final String IMG = "<img src='";
		tree.append(IMG).append(this.getImgPath()+(isbExpanded()?MnuConstant.ICO_NOHAVE_GIF:MnuConstant.ICO_HAVE_GIF));
		final String IMG_ID = "' id='ico_submenu";
		tree.append(IMG_ID).append(treeNode.getNodeNo());
		final String IMG_END = "'/>&nbsp;&nbsp;";
		tree.append(IMG_END).append(treeNode.getNodeName()).append(MnuConstant.TAG_A_END)
			.append(MnuConstant.TAG_DIV_END).append(MnuConstant.ENTER_SIGN);
		final String DIV_CLASS2 = "<div class='ind_left_menu2' id='div_submenu";
		tree.append(DIV_CLASS2).append(treeNode.getNodeNo());
		final String STYLE = "' style='display:";
		tree.append(STYLE).append(isbExpanded()?MnuConstant.STYLE_DISPLAY_BLOCK:MnuConstant.STYLE_DISPLAY_NONE)
			.append(MnuConstant.QUO_S_SIGN).append(MnuConstant.RIGHT_J_KUO).append(MnuConstant.ENTER_SIGN);
		final String IMPORT = "<?IMPORT NAMESPACE = TVNS IMPLEMENTATION = '";
		tree.append(IMPORT).append(this.getJsPath());
		final String HTC = "/treeview.htc";
		tree.append(HTC).append(MnuConstant.QUO_S_SIGN).append(MnuConstant.EMPTY_ONE_STR);
		tree.append(MnuConstant.TAG_END).append(MnuConstant.ENTER_SIGN);
		final String TVN_TREE_VIEW = "<tvns:treeview systemimagespath='";
		tree.append(TVN_TREE_VIEW).append(this.getImgPath());
		final String TVN_TREE_VIEW_SEL_NODE_INDEX = "' selectednodeindex='99999'>\n";
		tree.append(TVN_TREE_VIEW_SEL_NODE_INDEX);
		
	}

}