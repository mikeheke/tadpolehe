
function deleteFile(curEle){
	var curRow = curEle.parentNode.parentNode;
	curRow.parentNode.removeChild(curRow);
}

function uploadCallback(cnName,data,number,rtCode,rtMsg){
	var msg = document.getElementById("m_"+number);
	msg.innerHTML = rtMsg;
	var tab = document.getElementById("fl_"+number);
	var row = tab.insertRow(0);
	var c1 = row.insertCell(0);
	c1.innerHTML = cnName;
	var c2 = row.insertCell(1);
	c2.innerHTML = data;
}

function uploadFailCallback(number,rtMsg){
	alert(rtMsg);
	var msg = document.getElementById("m_"+number);
	msg.innerHTML = rtMsg;
}

function uploadOnclick(act, num, path, isEnc, maximumSize){
	
	var submitFormStr = "<FORM" + " action='" + act + "'" + " method='POST' " + " enctype='multipart/form-data' " + " target='hif_" + num + "' style='display:none;'>" + " </FORM>";
	var pathEleStr = "<INPUT"
    	+ " type='hidden'"
    	+ " name='path'"
    	+ " value='" + path + "'"
    	+ " />";
    var isEncEleStr = "<INPUT"
    	+ " type='hidden'"
    	+ " name='isEnc'"
    	+ " value='" + isEnc + "'"
    	+ " />";
    var maximumSizeEleStr = "<INPUT"
    	+ " type='hidden'"
    	+ " name='maximumSize'"
    	+ " value='" + maximumSize + "'"
    	+ " />";
	
	var submitForm = document.createElement(submitFormStr);

	var dl = document.getElementById("dl_"+num);
    var fo = document.getElementById("f_"+num);
    var fn = fo.cloneNode(true);
    dl.replaceChild(fn,fo);
	fo.style.display = "none";
    submitForm.appendChild(fo);
    
    var pathEle = document.createElement(pathEleStr);
    var isEncEle = document.createElement(isEncEleStr);
    var maximumSizeEle = document.createElement(maximumSizeEleStr);
    
    var submit = document.createElement("INPUT");
    submit.setAttribute("type", "submit");
    
    submitForm.appendChild(pathEle);
    submitForm.appendChild(isEncEle);
    submitForm.appendChild(maximumSizeEle);
    submitForm.appendChild(submit);
    
    document.body.appendChild(submitForm);
	submit.click();
	
	submitForm.parentNode.removeChild(submitForm);
}