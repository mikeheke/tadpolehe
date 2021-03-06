/**
 * 
 */
package mikeheke.tadpole.frm.mnu.web.tag;

import mikeheke.tadpole.frm.mnu.util.MnuConstant;
import mikeheke.tadpole.frm.mnu.vo.TreeNode;

/**
 * 
 *
 * 2011-7-15 下午08:34:50
 */
public class WindowTag extends AbstractTreeTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 276626945905515600L;
	
	@Override
	public void addTreePre(StringBuffer tree){
		final String UL_BEGIN = "<ul id=\"nav\">\n";
		tree.append(UL_BEGIN);
	}
	
	@Override
	public void addTreeAft(StringBuffer tree){
		final String UL_END = "</ul>\n";
		tree.append(UL_END);
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
		
		final String LI_CLASS = "<li class=\"top\">\n";
		tree.append(LI_CLASS);
		final String A_HREF = "<a href=\"";
		tree.append(A_HREF).append(treeNode.getLink());
		final String A_CLASS = "\" class=\"top_link\">";
		tree.append(A_CLASS);
		tree.append(MnuConstant.TAG_SPAN_BEGIN2);
		final String SPAN_CLASS = " class=\"down\"";
		tree.append((treeNode.getcTreeNodes().size()!=0)?SPAN_CLASS:MnuConstant.EMPTY_STR);
		tree.append(MnuConstant.RIGHT_J_KUO).append(MnuConstant.ENTER_SIGN);
		tree.append(treeNode.getNodeName()).append(MnuConstant.TAG_SPAN_END);
		tree.append(MnuConstant.ENTER_SIGN).append(MnuConstant.TAG_A_END).append(MnuConstant.ENTER_SIGN);
		final String UL_BEGIN = "<ul class=\"sub\">";
		tree.append(((treeNode.getcTreeNodes().size()!=0)?UL_BEGIN:MnuConstant.EMPTY_STR));
		tree.append(MnuConstant.ENTER_SIGN);
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
		
		tree.append(((treeNode.getcTreeNodes().size()!=0)?MnuConstant.TAG_UL_END:MnuConstant.EMPTY_STR));
		tree.append(MnuConstant.ENTER_SIGN);
		tree.append(MnuConstant.TAG_LI_END).append(MnuConstant.ENTER_SIGN);
	
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
		
		tree.append(MnuConstant.TAG_LI_BEGIN).append(MnuConstant.ENTER_SIGN);
		final String A_HREF = "<a href=\"";
		tree.append(A_HREF).append(treeNode.getLink());
		final String A_CLASS = "\" class=\"fly\">";
		tree.append(A_CLASS).append(treeNode.getNodeName());
		tree.append(MnuConstant.TAG_A_END).append(MnuConstant.ENTER_SIGN);
		tree.append(((treeNode.getcTreeNodes().size()!=0)?MnuConstant.TAG_UL_BEGIN:MnuConstant.EMPTY_STR));
		tree.append(MnuConstant.ENTER_SIGN);
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
	
		tree.append(((treeNode.getcTreeNodes().size()!=0)?MnuConstant.TAG_UL_END:MnuConstant.EMPTY_STR));
		tree.append(MnuConstant.ENTER_SIGN).append(MnuConstant.TAG_LI_END).append(MnuConstant.ENTER_SIGN);
	
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
		
		tree.append(MnuConstant.TAG_LI_BEGIN).append(MnuConstant.ENTER_SIGN);
		final String A_HREF = "<a href=\"";
		tree.append(A_HREF).append(treeNode.getLink()).append(MnuConstant.QUO_D_SIGN);
		final String TARGET = " target=\"";
		tree.append(TARGET).append(treeNode.getTarget()).append(MnuConstant.QUO_D_SIGN);
		tree.append(MnuConstant.RIGHT_J_KUO).append(MnuConstant.ENTER_SIGN);
		tree.append(treeNode.getNodeName()).append(MnuConstant.TAG_A_END);
		
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
		
		tree.append(MnuConstant.TAG_LI_END).append(MnuConstant.ENTER_SIGN);
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
		tree.append(MnuConstant.JS_BEGIN).append(MnuConstant.ENTER_SIGN);
		final String js1 = " stuHover = function(){\n";
		tree.append(js1);
		final String js2 = " var cssRule;\n";
		tree.append(js2);
		final String js3  =" var newSelector;\n";
		tree.append(js3);
		final String js41 = " for (var i = 0; i < document.styleSheets.length; i++){\n";
		tree.append(js41);
        final String js4 = " for (var x = 0; x < document.styleSheets[i].rules.length ; x++){\n";
        tree.append(js4);
        final String js5 = " cssRule = document.styleSheets[i].rules[x];\n";
        tree.append(js5);
        final String js6 = " if (cssRule.selectorText.indexOf(\"LI:hover\") != -1){\n";
        tree.append(js6);
        final String js7 = " newSelector = cssRule.selectorText.replace(/LI:hover/gi, \"LI.iehover\");\n";
        tree.append(js7);
        final String js8 = " document.styleSheets[i].addRule(newSelector , cssRule.style.cssText);\n";
        tree.append(js8);
        final String js9 = " }//end if\n";
        tree.append(js9);
        final String js10 = " }// end for\n";
        tree.append(js10);
        final String js11 = " }// end for\n";
        tree.append(js11);
        final String js12 = " var getElm = document.getElementById(\"nav\").getElementsByTagName(\"LI\");\n";
        tree.append(js12);
        final String js13 = " for (var i=0; i<getElm.length; i++){\n";
        tree.append(js13);
        final String js14 = " getElm[i].onmouseover=function(){\n";
        tree.append(js14);
        final String js15 = " this.className+=\" iehover\";\n";
        tree.append(js15);
        final String js16 = " }// end function\n";
        tree.append(js16);
        final String js17 = " getElm[i].onmouseout=function(){\n";
        tree.append(js17);
        final String js18 = " this.className=this.className.replace(new RegExp(\" iehover\"), \"\");\n";
        tree.append(js18);
        final String js19 = " }// end function\n";
        tree.append(js19);
        final String js20 = " }// end for\n";
        tree.append(js20);
        final String js21 = " }//end function\n";
        tree.append(js21);
        final String js22 = "if (window.attachEvent){\n";
        tree.append(js22);
        final String js23 = " window.attachEvent(\"onload\", stuHover);\n";
        tree.append(js23);
        final String js24 = " }// end if\n";
        tree.append(js24);
        tree.append(MnuConstant.JS_END).append(MnuConstant.ENTER_SIGN);
	}
}
