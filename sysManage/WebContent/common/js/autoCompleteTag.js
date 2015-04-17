
function bindAutocomplete(objId, url, minChar, maxNum,
                          dsType, source, filterColNames, fillColKey, 
                          showWidth, showHeight,
                          befSend, aftSend, filterClass, filterPattern, 
                          isFillForm) {
	var autoField = $('#'+objId);
	
	autoField.autocomplete(url, {
		httpMethod : "POST",
		dataType : "json",
		max : maxNum,
		width : showWidth,
		scrollHeight : showHeight,
		minChars : minChar,
		matchSubset : false,
		matchCase : true,
		cacheLength : 1,
		delay : 10,
		extraParams : {dsType : dsType,
					   source : source,
					   filterColNames : filterColNames,
					   filterClass : filterClass,
					   filterPattern: filterPattern
					  },
		parse: function(returnData) {
			var datas = eval('(' + returnData.jsonValue + ');');
			return $.map(datas, function(row) {
				return {
					data: row,
					result: getResult(row, fillColKey)
				}
			});
		},
		formatItem : function(row) {
			return getDisplay(row, filterColNames);
		}
	}).result(function(event, row, formatted) {
		
		setHiddenValue(row, objId, fillColKey);
		setDisplayValue(row, objId, filterColNames);
		
		eval(befSend + ";");
		
		if(isFillForm){
			fillFormEleValues(autoField, row);
		}
		
		eval(aftSend + ";");
	});
}

function getResult(row, fillColKey){
	
	var sep = "|";
	var colNames = fillColKey.split(sep);
	var colName = colNames[0];
	var result = row[colName];
	
	return result;
}

function getDisplay(row, filterColNames){
	
	var sep = "|";
	var colNames = filterColNames.split(sep);
	var len = colNames.length;
	var result = "";
	for(var i=0; i<len; i++){
		var colName = colNames[i];
		result = result + row[colName] + sep;
	}
	if(len>0){
		result = result.substring(0, result.length-1);
	}
	return result;
}

function setHiddenValue(row, textId, fillColKey){
	
	var sep = "|";
	var colNames = fillColKey.split(sep);
	if(colNames.length<2){
		return;
	}
	var colName = colNames[1];
	var hidText = document.getElementById("_"+textId);
	hidText.value = row[colName];
	
}

function setDisplayValue(row, objId, filterColNames){
	
	var result = getDisplay(row, filterColNames);
	var obj = document.getElementById(objId);
	obj.title = result;
}

function fillFormEleValues(autoField, jsonDatas){
	
	var form = autoField.parents("form");
	var elements = form.find("[id]");
	for (var i = 0; i < elements.size(); i++) {
		var e = elements[i];
		var eName = e.id;
		var eNodeName = e.nodeName;
		eName = eName.substr(eName.lastIndexOf(".")+1);
		eValue = jsonDatas[eName];
		if (!eValue) {
			continue;
		}
		if(eNodeName=="DIV"){
			fillFormContainerEleValue(e, eValue);
		}else{
			fillFormEleValue(e, eValue);
		}
	}
}

function fillFormEleValue(e, eValue){
	
	switch (e.type) {
		case 'checkbox':
		case 'radio':
			if (e.value == eValue) {
				e.checked = true;
			}
			break;
		case 'hidden':
		case 'password':
		case 'textarea':
		case 'text':
			e.value = eValue;
			break;
		case 'select-one':
		case 'select-multiple':
			fillSelectElementValue(e, eValue);
			break;
		case 'button':
		case 'file':
		case 'image':
		case 'reset':
		case 'submit':
		default:
			e.innerHTML=eValue;
		}
}

function fillFormContainerEleValue(e, eValue){
	var childs = e.childNodes;
	var len = childs.length;
	for(var i=0; i<len; i++){
		fillFormEleValue(childs[i], eValue);
	}
}

function fillSelectElementValue(e, eValue){
	for (j = 0; j < e.options.length; j++) {
		var op = e.options[j];
		if (op.value == eValue) {
			op.selected = true;
		}
	}
}
