function ajaxRequest(url, params, sucMethod){
	$.ajax({
		url : url,
		type : "POST",
		data : params,
		dataType : "json",
		error: function(request) {
			alert("出错了，请稍候再试");
		},
		success : function(returnData) {
			var datas = eval('("' + returnData.jsonValue + '");');
			eval(sucMethod+'("' + datas + '");');
		}
	});
}

function setEleDisplay(eleId, vi){
	var ele = document.getElementById(eleId);
	if(vi){
		ele.style.display='';
	}else{
		ele.style.display='none';
	}
}

function setEleVisible(eleId, vi){
	var ele = document.getElementById(eleId);
	if(vi){
		ele.style.visibility='visible';
	}else{
		ele.style.visibility='hidden';
	}
}

/*
 * 取某框架某元素值
 * @param {Object} frm
 * @param {Object} eleId
 * @return {TypeName} 
 */
function getFrmEleValById(frm, eleId){
	var parWin = window.parent;
	var parWinDoc = parWin.frames[frm].document;
	var val = parWinDoc.getElementById(eleId);
	return val;
}

/*
 * 取某框架某元素数组值
 * @param {Object} frm
 * @param {Object} eleName
 * @return {TypeName} 
 */
function getFrmEleValByName(frm, eleName){
	var parWin = window.parent;
	var parWinDoc = parWin.frames[frm].document;
	document.getElementsByName(name);
	var vals = parWinDoc.getElementsByName(eleName);
	return vals;
}

function setTabCBAllSel(curCk, tabId){
	var tabElement = document.getElementById(tabId);
	var checkBox_contents = tabElement.getElementsByTagName("input");
	for(var i=0;i<checkBox_contents.length;i++){
		var inputType = checkBox_contents[i].getAttribute("type");
		if(inputType.toUpperCase() != "CHECKBOX")continue;
		if(curCk.checked){
			checkBox_contents[i].checked = true;
		}else{
			checkBox_contents[i].checked = false;
		}	
	}
}

function setCurMdfIdValue(curEle){

	var trs = curEle.parentNode.childNodes;
	for (var i = 0; i < trs.length; i++) {
		if (trs[i] != curEle) {
			trs[i].childNodes[0].firstChild.checked = false;
		}
	}
	
	var tds = curEle.childNodes;
	for(var i=0;i<tds.length;i++){
		var srcId = tds[i].id;
		var srcValue;
		if(i == 0){
			srcValue = tds[i].firstChild.value;
		}else{
			srcValue = tds[i].innerText;
		}
		//scrValue = trim(srcValue);
		var desId = srcId.split("_")[0];
		setElementValueById(desId, srcValue);
	}
	changeRowColor(curEle);
}

function setElementsValueByIds(eleIds, eleValue){
	var eleIdArr = eleIds.split("|");
	var len = eleIdArr.length;
	for(var i=0; i<len; i++){
		setElementValueById(eleIdArr[i], eleValue);
	}
}

function setElementValueById(eleId, eleValue){
	if(!eleId){
		return;
	}
	eleValue = trim(eleValue);
	var desEle = document.getElementById(eleId);
	if(!desEle){
		return;
	}
	var nname = desEle.nodeName;
	if(nname.toUpperCase() == "SELECT"){
		for(var j=0;j<desEle.length;j++){   
    		var text = desEle.options[j].text; 
    		var va = desEle.options[j].value; 
   			if((va == eleValue) || (text == eleValue)){ 
   				desEle.options[j].selected = true; 
   			}else{
   				desEle.options[j].selected = false; 
   			}
		}  
		try{
			//desEle.onchange();
			desEle.fireEvent("onchange");
		}catch(e){
			;
		}
	}else if(nname.toUpperCase() == "INPUT"){		
		desEle.value = eleValue;
	}
}

function setParDocElementValueById(eleId, eleValue){
	if(!eleId){
		return;
	}
	eleValue = trim(eleValue);
	var pDoc = window.top.dialogArguments;
	var desEle = pDoc.getElementById(eleId);
	if(!desEle){
		return;
	}
	var nname = desEle.nodeName;
	if(nname.toUpperCase() == "SELECT"){
		for(var j=0;j<desEle.length;j++){   
    		var text = desEle.options[j].text; 
    		var va = desEle.options[j].value; 
   			if((va == eleValue) || (text == eleValue)){ 
   				desEle.options[j].selected = true; 
   			}else{
   				desEle.options[j].selected = false; 
   			}
		}  
		try{
			//desEle.onchange();
			desEle.fireEvent("onchange");
		}catch(e){
			;
		}
	}else if(nname.toUpperCase() == "INPUT"){		
		desEle.value = eleValue;
	}
}

var lastRow = "";
var lastBRowColor = "";
var lastFRowColor = "";
var clickBColor = "blue";
var clickFColor = "blue";
function changeRowColor(curRow){
	
	var ck = curRow.firstChild.firstChild;
	if(!ck.checked){
		return;
	}
	if(lastRow == ""){
		lastRowColor = curRow.style.backgroundColor;
		lastFRowColor = curRow.style.color;
	}else{
		lastRow.style.backgroundColor =lastBRowColor;
		lastRow.style.color =lastFRowColor;
	}
	lastRow = curRow;
	curRow.style.backgroundColor = clickBColor;
	curRow.style.color = clickFColor;
}

function popup(w, h, url){
		
	var l = (screen.width - w) / 2; 
    var t = (screen.height - h) / 2; 
	var options = "dialogWidth="+w+"px"+";dialogHeight="+h+"px"
		+";dialogLeft="+l+";dialogTop="+t
		+";scroll=0;status=0";

	window.showModalDialog(url,window.document,options); 
}

function popupRefresh(w, h, url){
		
	popup(w, h, url);
	
	window.parent.location = window.parent.location;
}

function popupRefresh2(w, h, url){
		
	popup(w, h, url);
	
	window.parent.parent.location = window.parent.parent.location;
}

function openWin(w, h, url, tar){
	
	var l = (screen.width - w) / 2; 
    var t = (screen.height - h) / 2; 
	
	var subWindow = window.open(url,tar,'height='+h+',width='+w+',top='+t+',left='+l
		+',toolbar=no,status=no,menubar=no,scrollbars=auto,resizable=no');
	
}

function setEleValueJson(jsonUrl,eleId){
	
    jQuery.ajax({ 
		url:jsonUrl,
		type:"POST",
		dataType:"json",
 		beforeSend: function(){         // 设置表单提交前方法  
            //alert("b s");
        },
        error: function(request) {       // 设置表单提交出错
        	alert("表单提交出错，请稍候再试");
        },
		success:function(data){ 
			var ele = document.getElementById(eleId);
			ele.value = data.jsonValue;
		}
	});    
}

//清除表域控件值
function clearTableInnerEleValues(tabId){
	var curTab = document.getElementById(tabId);
	row_num = curTab.rows.length; 
	for(var i=0;i<row_num;i++){
		cell_num = curTab.rows[i].cells.length; 
		for(var j=0;j<cell_num;j++){
			var childs = curTab.rows[i].cells[j].childNodes;
  			for(var k=0;k<childs.length;k++){
  				var nname = childs[k].nodeName;
  				var ntype = childs[k].type;
  				if(nname == "INPUT" && ntype == "text"){
	  				childs[k].value = "";
  				}
  				if(nname == "SELECT"){
	  				childs[k].options[0].selected = true;
  				}
  			}
		}
	}
}

function addEleOptionsNE(id, ops, bid, bname){
	var ele = document.getElementById(id);
	DWRUtil.removeAllOptions(ele);
	DWRUtil.addOptions(ele,ops,bid,bname);
	//ele.onchange();
}
		
/**
 * 设置元素提示信息
 * @param {Object} eleId
 * @param {Object} info
 */
function setEleTipInfo(eleId, info){
	var ele = document.getElementById(eleId);
	ele.innerHTML = info;
}

function ltrim(s){
	return s.replace(/^\s*/, "");
}

function rtrim(s){ 
  return s.replace(/\s*$/, ""); 
} 

function trim(s){ 
  return rtrim(ltrim(s)); 
}

function isNull(str){
  var re=/^[ \s]*$/;
  return re.test(str);
}