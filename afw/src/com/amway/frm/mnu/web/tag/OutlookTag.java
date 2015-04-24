/**
 * 
 */
package com.amway.frm.mnu.web.tag;

import com.amway.frm.mnu.util.MnuConstant;
import com.amway.frm.mnu.vo.TreeNode;



/**
 * 
 * 
 * Date: 2011-3-22
 * Time: 10:47:54
 * Declare：OutLook风格菜单Tag类
 */
public class OutlookTag extends AbstractTreeTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6035759255302401076L;

	//根节点是否展开
	private boolean bExpanded;
	
	//中节点是否展开
	private boolean mExpanded;
	
	//叶子节点是否展开
	private boolean lExpanded;
	
	 /**
     * Declare：添加树前缀
     * @param tree
     *
     */
	@Override
	public void addTreePre(StringBuffer tree){
		final String DIV_BEGIN = "<div id=\"submenucontent\">\n";
		tree.append(DIV_BEGIN);
	}
	
	 /**
     * Declare：添加树后缀
     * @param tree
     *
     */
	@Override
	public void addTreeAft(StringBuffer tree){
		final String DIV_END = "</div>\n";
		tree.append(DIV_END);
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
		
		final String DIV_BEGIN = "<div class='";
		tree.append(DIV_BEGIN).append(this.getClassName())
			.append(MnuConstant.QUO_S_SIGN).append(MnuConstant.RIGHT_J_KUO).append(MnuConstant.ENTER_SIGN);
		final String H2 = "<h2 onclick='changeView(this.id)' id='submenu";
		tree.append(H2).append(treeNode.getNodeNo())
			.append(MnuConstant.QUO_S_SIGN).append(MnuConstant.RIGHT_J_KUO).append(MnuConstant.ENTER_SIGN);
		final String IMG = "<img src='";
		tree.append(IMG)
			.append(this.getImgPath()+(isbExpanded()?MnuConstant.ICO_NOHAVE_GIF:MnuConstant.ICO_HAVE_GIF));
		final String IMG_ID = "' id='ico_submenu";
		tree.append(IMG_ID).append(treeNode.getNodeNo());
		final String IMG_END = "'/>&nbsp;&nbsp;";
		tree.append(IMG_END).append(treeNode.getNodeName()).append(MnuConstant.H2_END);
		final String DIV_SUB = "<div class='navigation' id='div_submenu";
		tree.append(DIV_SUB).append(treeNode.getNodeNo());
		final String DIV_SUB_STYLE = "' style='display:";
		tree.append(DIV_SUB_STYLE).append(isbExpanded()?MnuConstant.STYLE_DISPLAY_BLOCK:MnuConstant.STYLE_DISPLAY_NONE);
		tree.append(MnuConstant.QUO_S_SIGN).append(MnuConstant.EMPTY_ONE_STR)
			.append(MnuConstant.RIGHT_J_KUO).append(MnuConstant.ENTER_SIGN);
		final String IMPORT = "<?IMPORT NAMESPACE = TVNS IMPLEMENTATION = '";
		tree.append(IMPORT).append(this.getJsPath()).append(MnuConstant.TREE_VIEW_HTC);
		final String IMPORT_END = "' />\n";
		tree.append(IMPORT_END);
		final String TVN_TREEVEIW = "<tvns:treeview systemimagespath='";
		tree.append(TVN_TREEVEIW).append(this.getImgPath());
		final String TVN_TREEVEIW_SEL_NODE_INDEX = "' selectednodeindex='99999'>\n";
		tree.append(TVN_TREEVEIW_SEL_NODE_INDEX);
		
	}

	 /**
     * Declare：添加根节点后缀
     *
	 * @param  tree 树
	 * @param  treeNode 节点
     * @return void
     */
	@Override
	public void appendBaseNodeAft(StringBuffer tree, TreeNode treeNode){
		
		final String TVN_TREEVIEW_END = "</tvns:treeview>";
		tree.append(TVN_TREEVIEW_END);
		final String DIV_END = "</div>\n";
		tree.append(DIV_END);
	}

	 /**
     * Declare：添加中间节点前缀
     *
	 * @param  tree 树
	 * @param  treeNode 节点
     * @return void
     */
	@Override
	public void appendMiddleNodePre(StringBuffer tree, TreeNode treeNode){
		
		final String TVNS_TREE_NODE = "<tvns:treenode navigateurl='";
		tree.append(TVNS_TREE_NODE).append(MnuConstant.JING_SIGN);
		final String TVNS_TREE_NODE_EXPANDED = "' expanded='";
		tree.append(TVNS_TREE_NODE_EXPANDED).append(ismExpanded());
		final String NODE_DATA = "' nodedata='1' target='";
		tree.append(NODE_DATA).append(treeNode.getTarget());
		tree.append(MnuConstant.QUO_S_SIGN).append(MnuConstant.RIGHT_J_KUO).append(MnuConstant.ENTER_SIGN);
		tree.append(treeNode.getNodeName()).append(MnuConstant.ENTER_SIGN);
	
	}
	
	 /**
     * Declare：添加中间节点后缀
     *
	 * @param  tree 树
	 * @param  treeNode 节点
     * @return void
     */
	@Override
	public void appendMiddleNodeAft(StringBuffer tree, TreeNode treeNode){
	
		final String TVN_TREE_NODE_END = "</tvns:treenode>";
		tree.append(TVN_TREE_NODE_END).append(MnuConstant.ENTER_SIGN);
	
	}

	 /**
     * Declare：添加叶子节点前缀
     *
	 * @param  tree 树
	 * @param  treeNode 节点
     * @return void
     */
	@Override
	public void appendLeafNodePre(StringBuffer tree, TreeNode treeNode){
		
		final String TVN_TREE_NODE = "<tvns:treenode navigateurl='";
		tree.append(TVN_TREE_NODE).append(treeNode.getLink());
		final String EXPANDED = "' expanded='";
		tree.append(EXPANDED).append(islExpanded());
		final String NODE_DATA = "' nodedata='2' target='";
		tree.append(NODE_DATA).append(treeNode.getTarget());
		tree.append(MnuConstant.QUO_S_SIGN).append(MnuConstant.RIGHT_J_KUO).append(MnuConstant.ENTER_SIGN);
		tree.append(treeNode.getNodeName()).append(MnuConstant.ENTER_SIGN);
	
	}
	
	 /**
     * Declare：添加叶子节点后缀
     *
	 * @param  tree 树
	 * @param  treeNode 节点
     * @return void
     */
	@Override
	public void appendLeafNodeAft(StringBuffer tree, TreeNode treeNode){
		
		final String TVN_TREE_NODE_END = "</tvns:treenode>";
		tree.append(TVN_TREE_NODE_END).append(MnuConstant.ENTER_SIGN);
	
	}
	
	 /**
     * Declare：添加树JS代码
     *
	 * @param  tree 树
     * @return void
     */
	@Override
	public void addTreeNodeJS(StringBuffer tree){
		
		if(null == tree){
			return ;
		}
		tree.append(MnuConstant.JS_BEGIN);
		final String js1 = " var treeViewLastNode = null; ";
		tree.append(js1);
		final String js2 = " function changeView(obj)";
		tree.append(js2);
		final String js3 = " {";
		tree.append(js3);
		final String js4 = " if(document.all('div_'+obj).style.display == 'block' )";
		tree.append(js4);
		tree.append(js3);
		final String js5 = " document.all('div_'+obj).style.display = 'none';";
		tree.append(js5);
		final String js6 = " document.all('ico_'+obj).src='";
		final String js7 = "/ico_have.gif';";
		tree.append(js6).append(this.getImgPath()).append(js7);
        final String js8 = " }";
        tree.append(js8);
        final String js9 = " else";
        tree.append(js9);
        tree.append(js3);
        final String js10 = " document.all('div_'+obj).style.display = 'block';";
        tree.append(js10);
        final String js11 = " document.all('ico_'+obj).src='";
        final String js12 = "/ico_nohave.gif';";
        tree.append(js11).append(this.getImgPath()).append(js12);
        tree.append(js8);
        final String js13 = " defaultView = obj;";
        tree.append(js13);
        tree.append(js8);
        tree.append(MnuConstant.JS_END);
	}
	
	public boolean isbExpanded() {
		return bExpanded;
	}

	public void setbExpanded(boolean bExpanded) {
		this.bExpanded = bExpanded;
	}

	public boolean ismExpanded() {
		return mExpanded;
	}

	public void setmExpanded(boolean mExpanded) {
		this.mExpanded = mExpanded;
	}

	public boolean islExpanded() {
		return lExpanded;
	}

	public void setlExpanded(boolean lExpanded) {
		this.lExpanded = lExpanded;
	}
}
