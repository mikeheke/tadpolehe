
/*************************************************
Validator 表单验证

*************************************************/
Validator = {
phyCard:/^([0-9A-Fa-f]|[A-F0-9a-f]|[a-fA-F0-9]|[a-f0-9A-F])+$/,
IPAddress:/^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$/,
MDate:/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/,
MDateTime:/^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}$/,
Require : /.+/,
NotNull : /.+/,
Email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
Phone : /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
Mobile : /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/,
Url : /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
IdCard : /^\d{15}(\d{2}[A-Za-z0-9])?$/,
Currency : /^\d+(\.\d+)?$/,
Number : /^\d+$/,
Zip : /^[1-9]\d{5}$/,
QQ : /^[1-9]\d{4,8}$/,
Integer : /^[-\+]?\d+$/,
integer : /^[+]?\d+$/,
Double : /^[-\+]?\d+(\.\d+)?$/,
double : /^[+]?\d+(\.\d+)?$/,
English : /^([A-Za-z]|[,\!\*\.\ \(\)\[\]\{\}<>\?\\\/\'\"])+$/,
Chinese : /^[\u0391-\uFFE5]|[,\!\*\.\ \(\)\[\]\{\}<>\?\\\/\'\"]+$/,
Username : /^[a-z]\w{3,}$/i,
	id:/^[0-9]\d{0,18}$/,
	order:/^[0-9]\d{0,10}$/,
	struts_id:/^[0-9]\d{0,2}$/,
	struts_value:/^[0-9]\d{0}$/,
	iccs_value:/^[0-9]\d{0}$/,
UnSafe : /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/,
IsSafe : function(str){return !this.UnSafe.test(str);},
SafeString : "this.IsSafe(value)",
Filter : "this.DoFilter(value, getAttribute('accept'))",
Limit : "this.limit(value.length,getAttribute('min'), getAttribute('max'))",
LimitB : "this.limit(this.LenB(value), getAttribute('min'), getAttribute('max'))",
Date : "this.IsDate(value, getAttribute('min'), getAttribute('format'))",
Repeat : "value == document.getElementsByName(getAttribute('to'))[0].value",
ThanDate : "value >= document.getElementsByName(getAttribute('to'))[0].value",
thanDate : "value > document.getElementsByName(getAttribute('to'))[0].value",
Range : "getAttribute('min') < (value|0) && (value|0) < getAttribute('max')",
Compare : "this.compare(value,getAttribute('operator'),getAttribute('to'))",
Custom : "this.Exec(value, getAttribute('regexp'))",
Group : "this.MustChecked(getAttribute('name'), getAttribute('min'), getAttribute('max'))",
NotEmpty:"this.IsNotEmpty(value)",
dateDiff:"this.dateTimeDiff(getAttribute('name'), getAttribute('to'))",
ErrorItem : [document.forms[0]],
ErrorMessage : [],
IsNotEmpty:function(ctrValue){
	if(ctrValue ==null)
		return false;
	//alert(ctrValue);
	for(i=0;i<ctrValue.length;i++){
	//	alert(ctrValue.charAt(i));
		if(ctrValue.charAt(i) !=' ')
			return true
	}
	return false;
},
Validate : function(theForm, mode){
var obj = theForm || event.srcElement;
var count = obj.elements.length;
this.ErrorMessage.length = 1;
this.ErrorItem.length = 1;
this.ErrorItem[0] = obj;
for(var i=0;i<count;i++){
	with(obj.elements[i]){
		var _dataType = "";
//getAttribute("dataType");
		var _dataTypes = getAttribute("dataType");
		if(_dataTypes == null)
			continue;
	//	alert("_dataTypes="+_dataTypes);
		var index =_dataTypes.indexOf("|");
		var _dataTypesArray = [];
		var result = true;
		if(index ==-1)
   			_dataTypesArray[0] =_dataTypes;
		else
    			_dataTypesArray = _dataTypes.split("|");
  
		for(j=0;j< _dataTypesArray.length;j++){
			_dataType = _dataTypesArray[j];
//			alert("_dataType="+_dataType);
			if(typeof(_dataType) == "object" || typeof(this[_dataType]) == "undefined") 
				continue;
			this.ClearState(obj.elements[i]);
			if(getAttribute("require") == "false" && value == "") 
				continue;
			switch(_dataType){
				case "Date" :
				case "Repeat" :
				case "Range" :
				case "Compare" :
				case "Custom" :
				case "Group" : 
				case "Limit" :
				case "LimitB" :
				case "SafeString" :
				case "Filter" :
				case "ThanDate" :
				case "dateDiff" :
				case "thanDate" :
				case "NotEmpty":
				if(!eval(this[_dataType])) {
					result = false;
//this.AddError(i, getAttribute("msg"));
				}
				break;
				default :
					if(!this[_dataType].test(value)){
//				this.AddError(i, getAttribute("msg"));
						result = false;
						}
				break;
			}//end switch
	 	}//end for j
	 	if(!result)
	    		this.AddError(i, getAttribute("msg"));
	 
	}//end with
}// end for i
if(this.ErrorMessage.length > 1){
mode = mode || 1;
var errCount = this.ErrorItem.length;
switch(mode){
case 2 :
for(var i=1;i<errCount;i++)
this.ErrorItem[i].style.color = "red";
case 1 :
alert(this.ErrorMessage.join("\n"));
this.ErrorItem[1].focus();
break;
case 3 :
for(var i=1;i<errCount;i++){
try{
var span = document.createElement("SPAN");
span.id = "__ErrorMessagePanel";
span.style.color = "red";
this.ErrorItem[i].parentNode.appendChild(span);
span.innerHTML = this.ErrorMessage[i].replace(/\d+:/,"*");
}
catch(e){alert(e.description);}
}
this.ErrorItem[1].focus();
break;

case 4 :
for(var i=1;i<errCount;i++){
try{
var div = document.createElement("DIV");
div.id = "__ErrorMessagePanel";
div.style.color = "red";
this.ErrorItem[i].parentNode.appendChild(div);
div.innerHTML = this.ErrorMessage[i].replace(/\d+:/,"");
}
catch(e){alert(e.description);}
}
this.ErrorItem[1].focus();
break;

default :
alert(this.ErrorMessage.join("\n"));
break;
}
return false;
}
return true;
},
limit : function(len,min, max){
min = min || 0;
max = max || Number.MAX_VALUE;
return min <= len && len <= max;
},
LenB : function(str){
return str.replace(/[^\x00-\xff]/g,"**").length;
},
ClearState : function(elem){
with(elem){
if(style.color == "red")
style.color = "";
var lastNode = parentNode.childNodes[parentNode.childNodes.length-1];
if(lastNode.id == "__ErrorMessagePanel")
parentNode.removeChild(lastNode);
}
},
AddError : function(index, str){
this.ErrorItem[this.ErrorItem.length] = this.ErrorItem[0].elements[index];
this.ErrorMessage[this.ErrorMessage.length] = this.ErrorMessage.length + ":" + str;
},
Exec : function(op, reg){
return new RegExp(reg,"g").test(op);
},
compare : function(op1,operator,op2){
switch (operator) {
case "NotEqual":
return (op1 != op2);
case "GreaterThan":
return (op1 > op2);
case "GreaterThanEqual":
return (op1 >= op2);
case "LessThan":
return (op1 < op2);
case "LessThanEqual":
return (op1 <= op2);
default:
return (op1 == op2); 
}
},
MustChecked : function(name, min, max){
var groups = document.getElementsByName(name);
var hasChecked = 0;
min = min || 1;
max = max || groups.length;
for(var i=groups.length-1;i>=0;i--)
if(groups[i].checked) hasChecked++;
return min <= hasChecked && hasChecked <= max;
},
DoFilter : function(input, filter){
return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, filter.split(/\s*,\s*/).join("|")), "gi").test(input);
},
IsDate : function(op, formatString){
formatString = formatString || "ymd";
var m, year, month, day;
switch(formatString){
case "ymd" :
m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
if(m == null ) return false;
day = m[6];
month = m[5]*1;
year = (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
break;
case "dmy" :
m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
if(m == null ) return false;
day = m[1];
month = m[3]*1;
year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
break;
default :
break;
}
if(!parseInt(month)) return false;
month = month==0 ?12:month;
var date = new Date(year, month-1, day);
return (typeof(date) == "object" && year == date.getFullYear() && month == (date.getMonth()+1) && day == date.getDate());
function GetFullYear(y){return ((y<30 ? "20" : "19") + y)|0;}
},
 dateTimeDiff:function dateDiff(datetime1,datetime2) {
        var   date1 = new Date();
         var   date2 = new Date();
         var   diff  = new Date();
   var M1= document.getElementById(datetime1).value;
       date1temp = new Date(M1.split("-")[0],M1.split("-")[1] - 1,M1.split("-")[2]);
       date1.setTime(date1temp.getTime());

      var M2= document.getElementById(datetime2).value;
      date2temp = new Date(M2.split("-")[0],M2.split("-")[1] - 1,M2.split("-")[2]);
      date2.setTime(date2temp.getTime());

       diff.setTime(Math.abs(date1.getTime() - date2.getTime()));
       timediff = diff.getTime();
      // alert(Math.floor(timediff / (1000 * 60 * 60 * 24)));
       if (Math.floor(timediff / (1000 * 60 * 60 * 24))<=90)
        return true;
      else
       return false;
    }

}
