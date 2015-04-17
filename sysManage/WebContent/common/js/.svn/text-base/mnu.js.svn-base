var TREEDIVID="treeDemo";
var firstNodeId = new Array();

var setting = {

		view:{
			//fontCss:getFont, 
			//dblClickExpand:false,
	        showLine: false
			//showIcon:showIconForTree
		}, 
		data:{
			simpleData:{enable:true}
		}
		//, 
		//callback:{
		//	onClick:onClick
		//}
		
};

function getFont(treeId, node) {
	var levelValue = node.level;
	if (levelValue == 0) {
		var tId = node.tId;
		firstNodeId.push(tId);
	}
	return {};
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(TREEDIVID);
	zTree.expandNode(treeNode);
}
function showIconForTree(treeId, treeNode) {
	return !treeNode.isParent;
}

//function addFirstClass() {
	//var length = firstNodeId.length;
	//for (var i = 0; i < length; i++) {
	//	var id_A = firstNodeId[i];
	//	$("#" + id_A + "_span").addClass("marginbottom");
	//	$("#" + id_A + "_a").addClass("black s14 b ind_left_menu");
	//	$("#" + id_A).addClass("firstNode");
	//}
//}

