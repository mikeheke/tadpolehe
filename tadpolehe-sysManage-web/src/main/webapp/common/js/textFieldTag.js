/**
 * 字符串格式验证
 * @param elementIdOrName  原属ID或name值
 * @param regex 正则表达式
 * @param msg   提示消息
 * @param maxLine  限制输入的长度
 * @return
 */
function restrict(elementId, maxLine) {
/*	var target = document.getElementById(elementId);
	
	target.onkeydown = function() {
		checkLen(target, maxLine);
	}
	
	checkLen(target, maxLine);*/
}

function checkLen(target, maxLine){
	var len = target.value.length+1;
	if (len > maxLine) {
		alert("对不起，你输入的字符串超过限制（" + maxLine + "个字符），将被截断。");
		target.value = (target.value.substring(0, maxLine / 3));
	}
}
