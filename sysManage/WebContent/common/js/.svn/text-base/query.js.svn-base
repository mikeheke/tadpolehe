/*
 * 常、变量
 */
var QUERY_LEFT_FRM = "query_left";
var QUERY_BOTTOM_FRM = "query_bottom";
var NAV_OLD_COLOR = "black";
var NAV_NEW_COLOR = "blue";
var LAST_PAGE_ID = "lastPage";
var NEXT_PAGE_ID = "nextPage";
var INIT_NAV_ID = "navBtn_0"

function initQuery(){
	var leftDoc = window.frames[QUERY_LEFT_FRM].document;
	var initPageNav = leftDoc.getElementById(INIT_NAV_ID);
	initPageNav.click();
}

/*
 * 导航按钮点击时事件
 * @param {Object} curNav
 * @param {Object} lastPageVi
 * @param {Object} nextPageVi
 */
function navBtnOnclick(curNav, lastPageVi, nextPageVi){
	
	var navs = document.getElementsByName(curNav.name);
	for(var i=0; i< navs.length; i++){
		if(curNav == navs[i]){
			navs[i].style.color = NAV_NEW_COLOR;
		}else{
			navs[i].style.color = NAV_OLD_COLOR;
		}
	}
	
	var lastPage = getFrmEleValByName(QUERY_BOTTOM_FRM, LAST_PAGE_ID)[0];
	var nextPage = getFrmEleValByName(QUERY_BOTTOM_FRM, NEXT_PAGE_ID)[0];
	var tmp = curNav.id.split("_");
	lastPage.id = tmp[0] + "_" + (parseInt(tmp[1])-1);
	nextPage.id = tmp[0] + "_" + (parseInt(tmp[1])+1);
	setBtmFrmLasNexBtnVi(lastPageVi, nextPageVi);
}

/*
 * 上一页、下一页点击事件
 * @param {Object} curPagBtn
 */
function pageBtnOnclick(curPagBtn){

	var curPagBtnId = curPagBtn.id;
	var curNav = getFrmEleValById(QUERY_LEFT_FRM, curPagBtnId);
	curNav.click();
}

/*
 * 设置底页面的上一页和下一页的可见性
 * @param {Object} lastPageVi
 * @param {Object} nextPageVi
 */
function setBtmFrmLasNexBtnVi(lastPageVi, nextPageVi){
	
	var lastPage = getFrmEleValById(QUERY_BOTTOM_FRM, LAST_PAGE_ID);
	var nextPage = getFrmEleValById(QUERY_BOTTOM_FRM, NEXT_PAGE_ID);
	if(lastPageVi){
		lastPage.style.visibility = 'visible';
	}else{
		lastPage.style.visibility = 'hidden';
	}
	if(nextPageVi){
		nextPage.style.visibility = 'visible';
	}else{
		nextPage.style.visibility = 'hidden';
	}
}

/*
 * 展示查询
 */
var basePath = "";
function initQueryShow(bp){
	basePath = bp;
}
function showQuery(servetPath){
	var url = basePath+"/"+servetPath;
	window.open(url,"","");
}


/*
 * 常量、变量
 */
var ADD_BUTTON_ID = "add";
var MODIFY_BUTTON_ID = "modify";
var DELETE_BUTTON_ID = "delete";
var SAVE_BUTTON_ID = "save";
var CANCLE_BUTTON_ID = "cancel";

function addBtnOnclick(curEle){

	//清除
//	clearTableInnerEleValues("editTable");
	
	var addButton = document.getElementById(ADD_BUTTON_ID);
	var modifyButton = document.getElementById(MODIFY_BUTTON_ID);
	var deleteButton = document.getElementById(DELETE_BUTTON_ID);
	var saveButton = document.getElementById(SAVE_BUTTON_ID);
	var cancleButton = document.getElementById(CANCLE_BUTTON_ID);
	addButton.disabled = true;
	modifyButton.disabled = true;
	deleteButton.disabled = true;
	saveButton.disabled = false;
	cancleButton.disabled = false;
	setFormQueryStr(curEle.name);
}

function modifyBtnOnclick(curEle){
	var ret = checkModify2(1);
	if (!ret) {
		return false;
	}

	var addButton = document.getElementById(ADD_BUTTON_ID);
	var modifyButton = document.getElementById(MODIFY_BUTTON_ID);
	var deleteButton = document.getElementById(DELETE_BUTTON_ID);
	var saveButton = document.getElementById(SAVE_BUTTON_ID);
	var cancleButton = document.getElementById(CANCLE_BUTTON_ID);
	addButton.disabled = true;
	modifyButton.disabled = true;
	deleteButton.disabled = true;
	saveButton.disabled = false;
	cancleButton.disabled = false;
	setFormQueryStr(curEle.name);
}

function deleteBtnOnclick(curEle){
	var ret = checkDelete2(1);
	if (!ret) {
		return false;
	}

	var addButton = document.getElementById(ADD_BUTTON_ID);
	var modifyButton = document.getElementById(MODIFY_BUTTON_ID);
	var deleteButton = document.getElementById(DELETE_BUTTON_ID);
	var saveButton = document.getElementById(SAVE_BUTTON_ID);
	var cancleButton = document.getElementById(CANCLE_BUTTON_ID);
	addButton.disabled = false;
	modifyButton.disabled = false;
	deleteButton.disabled = false;
	cancleButton.disabled = false;
	setFormQueryStr(curEle.name);
	
	var ret = window.confirm(curEle.title);
	if(ret == false){	
		saveButton.disabled = true;
		return ;
	}else{
		saveButton.disabled = false;
		document.forms[0].submit();
	}
		
}

function cancelBtnOnclick(curEle){
	var addButton = document.getElementById(ADD_BUTTON_ID);
	var modifyButton = document.getElementById(MODIFY_BUTTON_ID);
	var deleteButton = document.getElementById(DELETE_BUTTON_ID);
	var saveButton = document.getElementById(SAVE_BUTTON_ID);
	var cancleButton = document.getElementById(CANCLE_BUTTON_ID);
	addButton.disabled = false;
	modifyButton.disabled = false;
	deleteButton.disabled = false;
	saveButton.disabled = true;
	cancleButton.disabled = false;
	setFormQueryStr(curEle.name);
}

function setFormQueryStr(qryStr){
	var frm = document.forms[0];
	var sep = '!';
	var tmp = frm.action.split(sep);
	frm.action = tmp[0]+sep+qryStr;
}

function buttonOnclick(curEle, url, tarType, refresh){
	if(tarType == '0'){
		blankOpenWin(curEle, url, 'blank', refresh);
	}else if(tarType == '1'){
		selfOpenWin(curEle, url);
	}else if(tarType == '2'){
		commonOpenWin(curEle, url, '_parent');
	}else if(tarType == '3'){
		commonOpenWin(curEle, url, '_top');
	}else if(tarType == '4'){
		commonOpenWin(curEle, url, '_self');
	}
}

function commonOpenWin(curEle, url, tar){
	document.forms[0].action = url;
	document.forms[0].target = tar;
	//document.forms[0].submit();
}

function checkPopClose(subWindow) { 
    if(subWindow.closed){ 
       window.location = window.location;
    } 
    else{ 
       window.setTimeout(_checkPopClose(subWindow),1000); 
    } 
} 

function _checkPopClose(subWindow){
	return function(){
    	checkPopClose(subWindow);
    }
}

function blankOpenWin(curEle, url, tar, refresh){
	
	var width = 800;
	var height = 600;
	var left = (screen.width - width) / 2;
	var top = (screen.height - height) / 2; 
	
	var subWindow = window.open("",tar,'height='+height+',width='+width+',top='+top+',left='+left
		+',toolbar=no,status=no,menubar=no,scrollbars=yes,resizable=yes');
	
	if (refresh == 1) {
		window.setTimeout(_checkPopClose(subWindow),1000);
	} 
	
	commonOpenWin(curEle, url, tar);
}

function selfOpenWin(curEle, url){

	if(!window.confirm("确认请确定，否则请取消!")){
		event.returnValue=false;
		return false;
	};

    $("#ec").submit(function(){
        jQuery.ajax({
            url: url,
            data: $("#ec").serialize(),
			type: "POST",
			error: function(request) {
				alert("表单提交出错，请稍候再试");
			},
            success: function(data) {
				var retMsg = data.jsonValue.toUpperCase();
				if(retMsg.indexOf("SUCCESS")==-1){
					if(retMsg.indexOf("INPUT")==-1){
						alert(data.jsonValue);
					}else{
						alert("操作失败");
					}
				} else {
					if (retMsg.indexOf("SHOW") != -1) {
						alert(data.showMsg);
					}
				}
				window.location = window.location;
            }
        });
        return false;
    });
}

/*query-base.jsp  start*/
function testBtnOnclick(jsonUrl, dsJndiId){
	var dsJndi = document.getElementById(dsJndiId);
	jsonUrl = jsonUrl + "?" + dsJndi.name + "=" + dsJndi.value;
	ajaxRequest(jsonUrl, {}, 'checkDsJndiCallBack');
}
function checkDsJndiCallBack(result){
	var dsJndiMsg = 'dsJndiMsg';
	if(result=='true'){
		setEleTipInfo(dsJndiMsg, '连接成功');
	}else{
		setEleTipInfo(dsJndiMsg, '连接失败');
	}
}
/*query-base.jsp  end*/

/*query-from.jsp start*/

/*query-from.jsp end*/

/*query-select.jsp start*/
function getTableLabelValue(lableName){
	var id = document.getElementById("id").value;
	id = id.split("|")[0];
	if(id==""){
		return "";
	}
	var fieldName = document.getElementById(lableName+"_"+id);
	if(!fieldName){
		return "";
	}
	return fieldName.innerText;
}

function setTableFieldVi(tableNameValue){
	if(tableNameValue==""){
		setEleDisplay("fieldName",false);
		setEleDisplay("fieldName2",true);
	}else{
		setEleDisplay("fieldName",true);
		setEleDisplay("fieldName2",false);
	}
}

function setTableFieldValue(){
	
	var fieldValue = getTableLabelValue("fieldName");
	setElementValueById('fieldName', fieldValue);
	var dataType = getTableLabelValue("dataType");
	setElementValueById('dataType', dataType);
	if ('' != fieldValue) {
		document.getElementById('dataTypeButton').click();
	}
}

function setSelectResult(){
	
	var tableNameEle = document.getElementById('tableName');
	var tableNameValue = tableNameEle[tableNameEle.options.selectedIndex].value;
	var tableNameText = tableNameEle[tableNameEle.options.selectedIndex].text;
	var tableName = tableNameText;
	if(tableNameValue == ""){
		tableName = "";
	}
	var fieldName = document.getElementById('fieldName').value;
	var textValue = "";
	if(tableName!="" && fieldName!=""){
		textValue = tableName + "." + fieldName;
	}
	setElementValueById('selectResult', textValue);
}

function setDataType(url, fieldDictCode){
	
	var tableName = document.getElementById('tableName').value;
	var fieldName = document.getElementById('fieldName').value;
	var params = {'selectVo.tableName':tableName,'selectVo.fieldName':fieldName,jsonValue:fieldDictCode};
	ajaxRequest(url, params, 'setDataTypeCallback');
}

function setDataTypeCallback(dataType){
	setElementValueById('dataType', dataType);
}

function btnTypeOnchange(curEle){
	var curEleValue = curEle.value;
	setDataInput(curEleValue);
}
function setDataInput(btnTypeValue){
	
	var dataInputValue = "";
	if(btnTypeValue=="0"){
		dataInputValue = "<input type='radio' name='id' value='$0'/>";
	}else if(btnTypeValue=="1"){
		dataInputValue = "<input type='checkbox' name='ids' value='$0'/>";
	}else if(btnTypeValue=="2"){
		dataInputValue = "<input type='button' />";
	}else if(btnTypeValue=="3"){
		dataInputValue = "<img src=''' />";
	}else if(btnTypeValue=="4"){
		dataInputValue = "<input type='text' />";
	}
	setElementValueById("dataInput", dataInputValue);
}
/*query-select.jsp end*/

/*query-where.jsp start*/
function setWhereFieldValue(fieldId){
	
	var fieldValue = getTableLabelValue(fieldId);
	setElementValueById(fieldId, fieldValue);

}

var operatorValues = new Array("> ?","< ?","= ?",">= ?","<= ?","!= ?", " LIKE ?", " BETWEEN ? AND ?", " IN (*)");
function setWhereResult(){

	var tableNameEle = document.getElementById('tableName');
	var tableNameValue = tableNameEle[tableNameEle.options.selectedIndex].value;
	var tableNameText = tableNameEle[tableNameEle.options.selectedIndex].text;
	var tableName = tableNameText;
	if(tableNameValue == ""){
		tableName = "";
	}
	var linkTableEle = document.getElementById('linkTable2');
	var linkTableValue = linkTableEle[linkTableEle.options.selectedIndex].value;
	var linkTableText = linkTableEle[linkTableEle.options.selectedIndex].text;
	var linkTable = linkTableText;
	if(linkTableValue == ""){
		linkTable = "";
	}
	
	var whereResult = "";
	var fieldName = document.getElementById("fieldName").value;
	if(tableName!="" && fieldName!=""){
		whereResult = tableName+"."+fieldName;
	}
	var linkField = document.getElementById("linkField").value;
	var filterField = document.getElementById("filterField").value;
	var defaultValue = document.getElementById("defaultValue").value;
	
	var isInnerLink = document.getElementById("isInnerLink").value;
	var operator = document.getElementById("operator").value;
	var isIgnoreCase = document.getElementById("isIgnoreCase").value;
	if(isInnerLink=="1"){
		if(linkTable!="" && linkField!=""){
			whereResult = whereResult+"="+linkTable+"."+linkField;
		}
	}else if(isInnerLink=="0"){
		if(linkTable!="" && linkField!=""
			&& filterField!="" && operator!=""){
			if(isIgnoreCase=="1"){
				filterField = "UPPER("+filterField+")";
			}
			whereResult = whereResult+" EXISTS IN(SELECT "+linkField +" FROM "+linkTable+" WHERE " +filterField+operatorValues[operator]+")";
		}
	}else{
		if(operator!=""){
			if(isIgnoreCase=="1"){
				whereResult = "UPPER("+whereResult+")";
			}
			whereResult = whereResult+operatorValues[operator];
		}	
	}
	setElementValueById("whereResult", whereResult);
}

function setLinkTableValue(){
	var linkTableEle = document.getElementById('linkTable2');
	var linkTableValue = linkTableEle[linkTableEle.options.selectedIndex].value;
	var linkTableText = linkTableEle[linkTableEle.options.selectedIndex].text;
	var linkTable = linkTableText;
	if(linkTableValue == ""){
		linkTable = "";
	}
	setElementValueById('linkTable',linkTable);
}

function isInnerLinkOnchange(curEle){
	if(curEle.value=='1'){//内部
		setElementValueById("isUserIn", '0');
		setElementValueById("operator", '2');
		setEleVisible("controlType", false);
		setEleVisible("dataCoding", false);
		setEleVisible("defaultValue", false);
		setEleVisible("filterField", false);
		setEleVisible("isRequired", false);
		setEleVisible("isIgnoreCase", false);
		setEleVisible("regExp", false);
		setEleVisible("parTagId", false);
	}else{
		setEleVisible("controlType", true);
		setEleVisible("dataCoding", true);
		setEleVisible("defaultValue", true);
		setEleVisible("filterField", true);
		setEleVisible("isRequired", true);
		setEleVisible("isIgnoreCase", true);
		setEleVisible("regExp", true);
		setEleVisible("parTagId", true);
	}
}
/*query-where.jsp end*/

/*query-buttoncheck start*/
function checkAdd(index, level) {
	var last = $("#ec_table").children().last();
	var tr = last.children().first();
	if (tr == null)
	{
		return false;
	}
	var tds =tr.find("td");
	if (index < 1 || index >= tds.length) {
		return false;
	}

	var name = tds[index - 1].firstChild.name;
	var names = document.getElementsByName(name);
	var count = 0;
	for(var i=0; i< names.length; i++){
		if(names[i].checked){
			count++;
		}
	}
	
	if (count == 0) {
		alert('请选择要操作的项。');
		return false;
	}
	
	if (count > 1) {
		alert('只能选择一项进行操作。');
		return false;
	}
	
	return true;
}

function checkDelete(index) {
	var last = $("#ec_table").children().last();
	var tr = last.children().first();
	if (tr == null)
	{
		return false;
	}
	var tds =tr.find("td");
	if (index < 1 || index >= tds.length) {
		return false;
	}

	var name = tds[index - 1].firstChild.name;
	var names = document.getElementsByName(name);
	var count = 0;
	for(var i=0; i< names.length; i++){
		if(names[i].checked){
			count++;
		}
	}
	if (count == 0) {
		alert('请先选择要操作的项。');
		return false;
	}
	return true;
}

function checkModify(index) {
	var last = $("#ec_table").children().last();
	var tr = last.children().first();
	if (tr == null)
	{
		return false;
	}
	var tds =tr.find("td");
	
	if (index < 1 || index >= tds.length) {
		return false;
	}
	var name = tds[index - 1].firstChild.name;
 
	var names = document.getElementsByName(name);
	var count = 0;
	for(var i=0; i< names.length; i++){
		if(names[i].checked){
			count++;
		}
	}
	if (count == 0) {
		alert('请先选择要操作的项。');
		return false;
	}
	if (count > 1) {
		alert('只能选择一项进行操作。');
		return false;
	}
	return true;
}

function checkDelete2(index) {
	if (document.getElementById('qryList').lastChild.firstChild == null)
	{
		return false;
	}
	var tr = document.getElementById('qryList').lastChild.firstChild;
	
	var tds = tr.getElementsByTagName("td");
	if (index < 1 || index >= tds.length) {
		return false;
	}

	var name = tds[index - 1].firstChild.name;
	var names = document.getElementsByName(name);
	var count = 0;
	for(var i=0; i< names.length; i++){
		if(names[i].checked){
			count++;
		}
	}
	if (count == 0) {
		alert('请先选择要操作的项。');
		return false;
	}
	return true;
}

function checkModify2(index) {
	if (document.getElementById('qryList').lastChild.firstChild == null)
	{
		return false;
	}
	var tr = document.getElementById('qryList').lastChild.firstChild;
	
	var tds = tr.getElementsByTagName("td");
	if (index < 1 || index >= tds.length) {
		return false;
	}

	var name = tds[index - 1].firstChild.name;
	var names = document.getElementsByName(name);
	var count = 0;
	for(var i=0; i< names.length; i++){
		if(names[i].checked){
			count++;
		}
	}
	if (count == 0) {
		alert('请先选择要操作的项。');
		return false;
	}
	if (count > 1) {
		alert('只能选择一项进行操作。');
		return false;
	}
	return true;
}
/*query-buttoncheck end*/

/*----ec----*/
function getParameterMap(form) {
    var p = document.forms[form].elements;
    var map = new Object();
    for(var x=0; x < p.length; x++) {
        var key = p[x].name;
        var val = p[x].value;
        
        //Check if this field name is unique.
        //If the field name is repeated more than once
        //add it to the current array.
        var curVal = map[key]; 
        if (curVal) { // more than one field so append value to array
        	curVal[curVal.length] = val;
        } else { // add field and value
        	map[key]= [val];
        }
    }
    return map;
}

function setFormAction(form, action, method) {
	if (action) {
		document.forms[form].setAttribute('action', action);
	}
	
	if (method) {
		document.forms[form].setAttribute('method', method);
	}
	
	document.forms[form].ec_eti.value='';
}

function setEcTabCheckBoxAllSelected(curCk, tabId){
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
/*----------------------------------*/

function checkBetween(node, name, controlType) {
	var inputs = document.getElementsByName(name);
	if (inputs.length < 2 || inputs[0].value == '' || inputs[1].value == '') {
		return;
	}
	if (controlType == '5' || controlType == '11') {
		if (parseFloat(inputs[0].value) > parseFloat(inputs[1].value)) {
			alert('区间参数不合法。');
			node.value = '';
		}	
	} else {
		if (inputs[0].value > inputs[1].value) {
			alert('区间参数不合法。');
			node.value = '';
		}	
	}
}


/**
 * 弹出选择表字段窗体
 * @param {Object} sysTableFields:字段编码(基础数据服务)
 * @param {Object} tableNameEleId:表名元素ID
 * 
 * add by Mike He 2013-1-23
 */
function popupSelFieldName(sysTableFields,tableNameEleId) {
	var tableName = document.getElementById(tableNameEleId).value;
	
	if (tableName == '') {
		alert('请先选择表名!');
		return;
	}

	popup(600,500,'${_sysApplication_.goToUrl}/fromAction!popupSelFieldName.action?sysTableFields='+sysTableFields+'&tableName='+tableName);
}

